package androidx.media3.exoplayer.upstream;

import android.content.Context;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.SignedBytes;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.io.encoding.Base64;
import okhttp3.internal.http.HttpStatusCodesKt;
import okio.Utf8;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    private static final int COUNTRY_GROUP_INDEX_2G = 1;
    private static final int COUNTRY_GROUP_INDEX_3G = 2;
    private static final int COUNTRY_GROUP_INDEX_4G = 3;
    private static final int COUNTRY_GROUP_INDEX_5G_NSA = 4;
    private static final int COUNTRY_GROUP_INDEX_5G_SA = 5;
    private static final int COUNTRY_GROUP_INDEX_WIFI = 0;
    public static final long DEFAULT_INITIAL_BITRATE_ESTIMATE = 1000000;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_3G;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_4G;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA;
    public static final int DEFAULT_SLIDING_WINDOW_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private static DefaultBandwidthMeter singletonInstance;
    private long bitrateEstimate;
    private final Clock clock;
    private final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    private final ImmutableMap<Integer, Long> initialBitrateEstimates;
    private long lastReportedBitrateEstimate;
    private int networkType;
    private int networkTypeOverride;
    private boolean networkTypeOverrideSet;
    private final boolean resetOnNetworkTypeChange;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = ImmutableList.of(4300000L, 3200000L, 2400000L, 1700000L, 860000L);
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = ImmutableList.of(1500000L, 980000L, 750000L, 520000L, 290000L);

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public TransferListener getTransferListener() {
        return this;
    }

    @Override // androidx.media3.datasource.TransferListener
    public void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z) {
    }

    static {
        Long lValueOf = Long.valueOf(SilenceSkippingAudioProcessor.DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = ImmutableList.of((long) lValueOf, 1300000L, 1000000L, 860000L, 610000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = ImmutableList.of(2500000L, 1700000L, 1200000L, 970000L, 680000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA = ImmutableList.of(4700000L, 2800000L, 2100000L, 1700000L, 980000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA = ImmutableList.of(2700000L, (long) lValueOf, 1600000L, 1300000L, 1000000L);
    }

    public static final class Builder {
        private Clock clock;
        private final Context context;
        private Map<Integer, Long> initialBitrateEstimates;
        private boolean resetOnNetworkTypeChange;
        private int slidingWindowMaxWeight;

        public Builder(Context context) {
            this.context = context == null ? null : context.getApplicationContext();
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.getCountryCode(context));
            this.slidingWindowMaxWeight = 2000;
            this.clock = Clock.DEFAULT;
            this.resetOnNetworkTypeChange = true;
        }

        public Builder setSlidingWindowMaxWeight(int i) {
            this.slidingWindowMaxWeight = i;
            return this;
        }

        public Builder setInitialBitrateEstimate(long j) {
            Iterator<Integer> it = this.initialBitrateEstimates.keySet().iterator();
            while (it.hasNext()) {
                setInitialBitrateEstimate(it.next().intValue(), j);
            }
            return this;
        }

        public Builder setInitialBitrateEstimate(int i, long j) {
            this.initialBitrateEstimates.put(Integer.valueOf(i), Long.valueOf(j));
            return this;
        }

        public Builder setInitialBitrateEstimate(String str) {
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Ascii.toUpperCase(str));
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setResetOnNetworkTypeChange(boolean z) {
            this.resetOnNetworkTypeChange = z;
            return this;
        }

        public DefaultBandwidthMeter build() {
            return new DefaultBandwidthMeter(this.context, this.initialBitrateEstimates, this.slidingWindowMaxWeight, this.clock, this.resetOnNetworkTypeChange);
        }

        private static Map<Integer, Long> getInitialBitrateEstimatesForCountry(String str) {
            int[] initialBitrateCountryGroupAssignment = DefaultBandwidthMeter.getInitialBitrateCountryGroupAssignment(str);
            HashMap map = new HashMap(8);
            map.put(0, 1000000L);
            map.put(2, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI.get(initialBitrateCountryGroupAssignment[0]));
            map.put(3, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_2G.get(initialBitrateCountryGroupAssignment[1]));
            map.put(4, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_3G.get(initialBitrateCountryGroupAssignment[2]));
            map.put(5, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_4G.get(initialBitrateCountryGroupAssignment[3]));
            map.put(10, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA.get(initialBitrateCountryGroupAssignment[4]));
            map.put(9, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA.get(initialBitrateCountryGroupAssignment[5]));
            map.put(7, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI.get(initialBitrateCountryGroupAssignment[0]));
            return map;
        }
    }

    public static synchronized DefaultBandwidthMeter getSingletonInstance(Context context) {
        if (singletonInstance == null) {
            singletonInstance = new Builder(context).build();
        }
        return singletonInstance;
    }

    private DefaultBandwidthMeter(Context context, Map<Integer, Long> map, int i, Clock clock, boolean z) {
        this.initialBitrateEstimates = ImmutableMap.copyOf((Map) map);
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.slidingPercentile = new SlidingPercentile(i);
        this.clock = clock;
        this.resetOnNetworkTypeChange = z;
        if (context != null) {
            NetworkTypeObserver networkTypeObserver = NetworkTypeObserver.getInstance(context);
            int networkType = networkTypeObserver.getNetworkType();
            this.networkType = networkType;
            this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(networkType);
            networkTypeObserver.register(new NetworkTypeObserver.Listener() { // from class: androidx.media3.exoplayer.upstream.DefaultBandwidthMeter$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.NetworkTypeObserver.Listener
                public final void onNetworkTypeChanged(int i2) {
                    this.f$0.onNetworkTypeChanged(i2);
                }
            });
            return;
        }
        this.networkType = 0;
        this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(0);
    }

    public synchronized void setNetworkTypeOverride(int i) {
        this.networkTypeOverride = i;
        this.networkTypeOverrideSet = true;
        onNetworkTypeChanged(i);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        Assertions.checkNotNull(handler);
        Assertions.checkNotNull(eventListener);
        this.eventDispatcher.addListener(handler, eventListener);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (isTransferAtFullNetworkSpeed(dataSpec, z)) {
            if (this.streamCount == 0) {
                this.sampleStartTimeMs = this.clock.elapsedRealtime();
            }
            this.streamCount++;
        }
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int i) {
        if (isTransferAtFullNetworkSpeed(dataSpec, z)) {
            this.sampleBytesTransferred += (long) i;
        }
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (isTransferAtFullNetworkSpeed(dataSpec, z)) {
            Assertions.checkState(this.streamCount > 0);
            long jElapsedRealtime = this.clock.elapsedRealtime();
            int i = (int) (jElapsedRealtime - this.sampleStartTimeMs);
            this.totalElapsedTimeMs += (long) i;
            long j = this.totalBytesTransferred;
            long j2 = this.sampleBytesTransferred;
            this.totalBytesTransferred = j + j2;
            if (i > 0) {
                this.slidingPercentile.addSample((int) Math.sqrt(j2), (j2 * 8000.0f) / i);
                if (this.totalElapsedTimeMs >= ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS || this.totalBytesTransferred >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    this.bitrateEstimate = (long) this.slidingPercentile.getPercentile(0.5f);
                }
                maybeNotifyBandwidthSample(i, this.sampleBytesTransferred, this.bitrateEstimate);
                this.sampleStartTimeMs = jElapsedRealtime;
                this.sampleBytesTransferred = 0L;
            }
            this.streamCount--;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onNetworkTypeChanged(int i) {
        int i2 = this.networkType;
        if (i2 == 0 || this.resetOnNetworkTypeChange) {
            if (this.networkTypeOverrideSet) {
                i = this.networkTypeOverride;
            }
            if (i2 == i) {
                return;
            }
            this.networkType = i;
            if (i != 1 && i != 0 && i != 8) {
                this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(i);
                long jElapsedRealtime = this.clock.elapsedRealtime();
                maybeNotifyBandwidthSample(this.streamCount > 0 ? (int) (jElapsedRealtime - this.sampleStartTimeMs) : 0, this.sampleBytesTransferred, this.bitrateEstimate);
                this.sampleStartTimeMs = jElapsedRealtime;
                this.sampleBytesTransferred = 0L;
                this.totalBytesTransferred = 0L;
                this.totalElapsedTimeMs = 0L;
                this.slidingPercentile.reset();
            }
        }
    }

    private void maybeNotifyBandwidthSample(int i, long j, long j2) {
        if (i == 0 && j == 0 && j2 == this.lastReportedBitrateEstimate) {
            return;
        }
        this.lastReportedBitrateEstimate = j2;
        this.eventDispatcher.bandwidthSample(i, j, j2);
    }

    private long getInitialBitrateEstimateForNetworkType(int i) {
        Long l = this.initialBitrateEstimates.get(Integer.valueOf(i));
        if (l == null) {
            l = this.initialBitrateEstimates.get(0);
        }
        if (l == null) {
            l = 1000000L;
        }
        return l.longValue();
    }

    private static boolean isTransferAtFullNetworkSpeed(DataSpec dataSpec, boolean z) {
        return z && !dataSpec.isFlagSet(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int[] getInitialBitrateCountryGroupAssignment(String str) {
        str.hashCode();
        byte b2 = -1;
        switch (str.hashCode()) {
            case 2083:
                if (str.equals("AD")) {
                    b2 = 0;
                }
                break;
            case 2084:
                if (str.equals("AE")) {
                    b2 = 1;
                }
                break;
            case 2085:
                if (str.equals("AF")) {
                    b2 = 2;
                }
                break;
            case 2086:
                if (str.equals("AG")) {
                    b2 = 3;
                }
                break;
            case 2088:
                if (str.equals("AI")) {
                    b2 = 4;
                }
                break;
            case 2091:
                if (str.equals("AL")) {
                    b2 = 5;
                }
                break;
            case 2092:
                if (str.equals("AM")) {
                    b2 = 6;
                }
                break;
            case 2094:
                if (str.equals("AO")) {
                    b2 = 7;
                }
                break;
            case 2096:
                if (str.equals("AQ")) {
                    b2 = 8;
                }
                break;
            case 2097:
                if (str.equals("AR")) {
                    b2 = 9;
                }
                break;
            case 2098:
                if (str.equals("AS")) {
                    b2 = 10;
                }
                break;
            case 2099:
                if (str.equals("AT")) {
                    b2 = 11;
                }
                break;
            case 2100:
                if (str.equals("AU")) {
                    b2 = Ascii.FF;
                }
                break;
            case 2102:
                if (str.equals("AW")) {
                    b2 = Ascii.CR;
                }
                break;
            case 2103:
                if (str.equals("AX")) {
                    b2 = Ascii.SO;
                }
                break;
            case 2105:
                if (str.equals("AZ")) {
                    b2 = Ascii.SI;
                }
                break;
            case 2111:
                if (str.equals("BA")) {
                    b2 = Ascii.DLE;
                }
                break;
            case 2112:
                if (str.equals("BB")) {
                    b2 = 17;
                }
                break;
            case 2114:
                if (str.equals("BD")) {
                    b2 = Ascii.DC2;
                }
                break;
            case 2115:
                if (str.equals("BE")) {
                    b2 = 19;
                }
                break;
            case 2116:
                if (str.equals("BF")) {
                    b2 = Ascii.DC4;
                }
                break;
            case 2117:
                if (str.equals("BG")) {
                    b2 = Ascii.NAK;
                }
                break;
            case 2118:
                if (str.equals("BH")) {
                    b2 = Ascii.SYN;
                }
                break;
            case 2119:
                if (str.equals("BI")) {
                    b2 = Ascii.ETB;
                }
                break;
            case 2120:
                if (str.equals("BJ")) {
                    b2 = Ascii.CAN;
                }
                break;
            case 2122:
                if (str.equals("BL")) {
                    b2 = Ascii.EM;
                }
                break;
            case 2123:
                if (str.equals("BM")) {
                    b2 = Ascii.SUB;
                }
                break;
            case 2124:
                if (str.equals("BN")) {
                    b2 = Ascii.ESC;
                }
                break;
            case 2125:
                if (str.equals("BO")) {
                    b2 = Ascii.FS;
                }
                break;
            case 2127:
                if (str.equals("BQ")) {
                    b2 = Ascii.GS;
                }
                break;
            case 2128:
                if (str.equals("BR")) {
                    b2 = Ascii.RS;
                }
                break;
            case 2129:
                if (str.equals("BS")) {
                    b2 = Ascii.US;
                }
                break;
            case 2130:
                if (str.equals("BT")) {
                    b2 = 32;
                }
                break;
            case 2133:
                if (str.equals("BW")) {
                    b2 = 33;
                }
                break;
            case 2135:
                if (str.equals("BY")) {
                    b2 = 34;
                }
                break;
            case 2136:
                if (str.equals("BZ")) {
                    b2 = 35;
                }
                break;
            case 2142:
                if (str.equals("CA")) {
                    b2 = 36;
                }
                break;
            case 2145:
                if (str.equals("CD")) {
                    b2 = 37;
                }
                break;
            case 2147:
                if (str.equals("CF")) {
                    b2 = 38;
                }
                break;
            case 2148:
                if (str.equals("CG")) {
                    b2 = 39;
                }
                break;
            case 2149:
                if (str.equals("CH")) {
                    b2 = 40;
                }
                break;
            case 2150:
                if (str.equals("CI")) {
                    b2 = 41;
                }
                break;
            case 2152:
                if (str.equals("CK")) {
                    b2 = 42;
                }
                break;
            case 2153:
                if (str.equals("CL")) {
                    b2 = 43;
                }
                break;
            case 2154:
                if (str.equals("CM")) {
                    b2 = 44;
                }
                break;
            case 2155:
                if (str.equals("CN")) {
                    b2 = 45;
                }
                break;
            case 2156:
                if (str.equals("CO")) {
                    b2 = 46;
                }
                break;
            case 2159:
                if (str.equals("CR")) {
                    b2 = 47;
                }
                break;
            case 2162:
                if (str.equals("CU")) {
                    b2 = 48;
                }
                break;
            case 2163:
                if (str.equals("CV")) {
                    b2 = 49;
                }
                break;
            case 2164:
                if (str.equals("CW")) {
                    b2 = 50;
                }
                break;
            case 2165:
                if (str.equals("CX")) {
                    b2 = 51;
                }
                break;
            case 2166:
                if (str.equals("CY")) {
                    b2 = 52;
                }
                break;
            case 2167:
                if (str.equals("CZ")) {
                    b2 = 53;
                }
                break;
            case 2177:
                if (str.equals("DE")) {
                    b2 = 54;
                }
                break;
            case 2182:
                if (str.equals("DJ")) {
                    b2 = 55;
                }
                break;
            case 2183:
                if (str.equals("DK")) {
                    b2 = 56;
                }
                break;
            case 2185:
                if (str.equals("DM")) {
                    b2 = 57;
                }
                break;
            case 2187:
                if (str.equals("DO")) {
                    b2 = 58;
                }
                break;
            case 2198:
                if (str.equals("DZ")) {
                    b2 = 59;
                }
                break;
            case 2206:
                if (str.equals("EC")) {
                    b2 = 60;
                }
                break;
            case 2208:
                if (str.equals("EE")) {
                    b2 = Base64.padSymbol;
                }
                break;
            case 2210:
                if (str.equals("EG")) {
                    b2 = 62;
                }
                break;
            case 2221:
                if (str.equals("ER")) {
                    b2 = Utf8.REPLACEMENT_BYTE;
                }
                break;
            case 2222:
                if (str.equals("ES")) {
                    b2 = SignedBytes.MAX_POWER_OF_TWO;
                }
                break;
            case 2223:
                if (str.equals("ET")) {
                    b2 = 65;
                }
                break;
            case 2243:
                if (str.equals("FI")) {
                    b2 = 66;
                }
                break;
            case 2244:
                if (str.equals("FJ")) {
                    b2 = 67;
                }
                break;
            case 2245:
                if (str.equals("FK")) {
                    b2 = 68;
                }
                break;
            case 2247:
                if (str.equals("FM")) {
                    b2 = 69;
                }
                break;
            case 2249:
                if (str.equals("FO")) {
                    b2 = 70;
                }
                break;
            case 2252:
                if (str.equals("FR")) {
                    b2 = 71;
                }
                break;
            case 2266:
                if (str.equals("GA")) {
                    b2 = 72;
                }
                break;
            case 2267:
                if (str.equals("GB")) {
                    b2 = 73;
                }
                break;
            case 2269:
                if (str.equals("GD")) {
                    b2 = 74;
                }
                break;
            case 2270:
                if (str.equals("GE")) {
                    b2 = 75;
                }
                break;
            case 2271:
                if (str.equals("GF")) {
                    b2 = 76;
                }
                break;
            case 2272:
                if (str.equals("GG")) {
                    b2 = 77;
                }
                break;
            case 2273:
                if (str.equals("GH")) {
                    b2 = 78;
                }
                break;
            case 2274:
                if (str.equals("GI")) {
                    b2 = 79;
                }
                break;
            case 2277:
                if (str.equals("GL")) {
                    b2 = 80;
                }
                break;
            case 2278:
                if (str.equals("GM")) {
                    b2 = 81;
                }
                break;
            case 2279:
                if (str.equals("GN")) {
                    b2 = 82;
                }
                break;
            case 2281:
                if (str.equals("GP")) {
                    b2 = 83;
                }
                break;
            case 2282:
                if (str.equals("GQ")) {
                    b2 = 84;
                }
                break;
            case 2283:
                if (str.equals("GR")) {
                    b2 = 85;
                }
                break;
            case 2285:
                if (str.equals("GT")) {
                    b2 = 86;
                }
                break;
            case 2286:
                if (str.equals("GU")) {
                    b2 = 87;
                }
                break;
            case 2288:
                if (str.equals("GW")) {
                    b2 = 88;
                }
                break;
            case 2290:
                if (str.equals("GY")) {
                    b2 = 89;
                }
                break;
            case 2307:
                if (str.equals("HK")) {
                    b2 = 90;
                }
                break;
            case 2314:
                if (str.equals("HR")) {
                    b2 = 91;
                }
                break;
            case 2316:
                if (str.equals("HT")) {
                    b2 = 92;
                }
                break;
            case 2317:
                if (str.equals("HU")) {
                    b2 = 93;
                }
                break;
            case 2331:
                if (str.equals("ID")) {
                    b2 = 94;
                }
                break;
            case 2332:
                if (str.equals("IE")) {
                    b2 = 95;
                }
                break;
            case 2339:
                if (str.equals("IL")) {
                    b2 = 96;
                }
                break;
            case 2340:
                if (str.equals("IM")) {
                    b2 = 97;
                }
                break;
            case 2341:
                if (str.equals("IN")) {
                    b2 = 98;
                }
                break;
            case 2342:
                if (str.equals("IO")) {
                    b2 = 99;
                }
                break;
            case 2344:
                if (str.equals("IQ")) {
                    b2 = 100;
                }
                break;
            case 2345:
                if (str.equals("IR")) {
                    b2 = 101;
                }
                break;
            case 2346:
                if (str.equals("IS")) {
                    b2 = 102;
                }
                break;
            case 2347:
                if (str.equals("IT")) {
                    b2 = 103;
                }
                break;
            case 2363:
                if (str.equals("JE")) {
                    b2 = 104;
                }
                break;
            case 2371:
                if (str.equals("JM")) {
                    b2 = 105;
                }
                break;
            case 2373:
                if (str.equals("JO")) {
                    b2 = 106;
                }
                break;
            case 2374:
                if (str.equals("JP")) {
                    b2 = 107;
                }
                break;
            case 2394:
                if (str.equals("KE")) {
                    b2 = 108;
                }
                break;
            case 2396:
                if (str.equals(ExpandedProductParsedResult.KILOGRAM)) {
                    b2 = 109;
                }
                break;
            case 2397:
                if (str.equals("KH")) {
                    b2 = 110;
                }
                break;
            case 2398:
                if (str.equals("KI")) {
                    b2 = 111;
                }
                break;
            case 2402:
                if (str.equals("KM")) {
                    b2 = 112;
                }
                break;
            case 2403:
                if (str.equals("KN")) {
                    b2 = 113;
                }
                break;
            case 2407:
                if (str.equals("KR")) {
                    b2 = 114;
                }
                break;
            case 2412:
                if (str.equals("KW")) {
                    b2 = 115;
                }
                break;
            case 2414:
                if (str.equals("KY")) {
                    b2 = 116;
                }
                break;
            case 2415:
                if (str.equals("KZ")) {
                    b2 = 117;
                }
                break;
            case 2421:
                if (str.equals("LA")) {
                    b2 = 118;
                }
                break;
            case 2422:
                if (str.equals(ExpandedProductParsedResult.POUND)) {
                    b2 = 119;
                }
                break;
            case 2423:
                if (str.equals("LC")) {
                    b2 = 120;
                }
                break;
            case 2429:
                if (str.equals("LI")) {
                    b2 = 121;
                }
                break;
            case 2431:
                if (str.equals("LK")) {
                    b2 = 122;
                }
                break;
            case 2438:
                if (str.equals("LR")) {
                    b2 = 123;
                }
                break;
            case 2439:
                if (str.equals("LS")) {
                    b2 = 124;
                }
                break;
            case 2440:
                if (str.equals("LT")) {
                    b2 = 125;
                }
                break;
            case 2441:
                if (str.equals("LU")) {
                    b2 = 126;
                }
                break;
            case 2442:
                if (str.equals("LV")) {
                    b2 = 127;
                }
                break;
            case 2445:
                if (str.equals("LY")) {
                    b2 = 128;
                }
                break;
            case 2452:
                if (str.equals("MA")) {
                    b2 = 129;
                }
                break;
            case 2454:
                if (str.equals("MC")) {
                    b2 = 130;
                }
                break;
            case 2455:
                if (str.equals("MD")) {
                    b2 = 131;
                }
                break;
            case 2456:
                if (str.equals("ME")) {
                    b2 = 132;
                }
                break;
            case 2457:
                if (str.equals("MF")) {
                    b2 = 133;
                }
                break;
            case 2458:
                if (str.equals("MG")) {
                    b2 = 134;
                }
                break;
            case 2459:
                if (str.equals("MH")) {
                    b2 = 135;
                }
                break;
            case 2462:
                if (str.equals("MK")) {
                    b2 = 136;
                }
                break;
            case 2463:
                if (str.equals("ML")) {
                    b2 = 137;
                }
                break;
            case 2464:
                if (str.equals("MM")) {
                    b2 = 138;
                }
                break;
            case 2465:
                if (str.equals("MN")) {
                    b2 = 139;
                }
                break;
            case 2466:
                if (str.equals("MO")) {
                    b2 = 140;
                }
                break;
            case 2467:
                if (str.equals("MP")) {
                    b2 = 141;
                }
                break;
            case 2468:
                if (str.equals("MQ")) {
                    b2 = 142;
                }
                break;
            case 2469:
                if (str.equals("MR")) {
                    b2 = 143;
                }
                break;
            case 2470:
                if (str.equals("MS")) {
                    b2 = 144;
                }
                break;
            case 2471:
                if (str.equals("MT")) {
                    b2 = 145;
                }
                break;
            case 2472:
                if (str.equals("MU")) {
                    b2 = 146;
                }
                break;
            case 2473:
                if (str.equals("MV")) {
                    b2 = 147;
                }
                break;
            case 2474:
                if (str.equals("MW")) {
                    b2 = 148;
                }
                break;
            case 2475:
                if (str.equals("MX")) {
                    b2 = 149;
                }
                break;
            case 2476:
                if (str.equals("MY")) {
                    b2 = 150;
                }
                break;
            case 2477:
                if (str.equals("MZ")) {
                    b2 = 151;
                }
                break;
            case 2483:
                if (str.equals("NA")) {
                    b2 = 152;
                }
                break;
            case 2485:
                if (str.equals("NC")) {
                    b2 = 153;
                }
                break;
            case 2487:
                if (str.equals("NE")) {
                    b2 = 154;
                }
                break;
            case 2488:
                if (str.equals("NF")) {
                    b2 = 155;
                }
                break;
            case 2489:
                if (str.equals("NG")) {
                    b2 = 156;
                }
                break;
            case 2491:
                if (str.equals("NI")) {
                    b2 = 157;
                }
                break;
            case 2494:
                if (str.equals("NL")) {
                    b2 = 158;
                }
                break;
            case 2497:
                if (str.equals("NO")) {
                    b2 = 159;
                }
                break;
            case 2498:
                if (str.equals("NP")) {
                    b2 = 160;
                }
                break;
            case DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS /* 2500 */:
                if (str.equals("NR")) {
                    b2 = 161;
                }
                break;
            case 2503:
                if (str.equals("NU")) {
                    b2 = 162;
                }
                break;
            case 2508:
                if (str.equals("NZ")) {
                    b2 = 163;
                }
                break;
            case 2526:
                if (str.equals("OM")) {
                    b2 = 164;
                }
                break;
            case 2545:
                if (str.equals("PA")) {
                    b2 = 165;
                }
                break;
            case 2549:
                if (str.equals("PE")) {
                    b2 = 166;
                }
                break;
            case 2550:
                if (str.equals("PF")) {
                    b2 = 167;
                }
                break;
            case 2551:
                if (str.equals("PG")) {
                    b2 = 168;
                }
                break;
            case 2552:
                if (str.equals("PH")) {
                    b2 = 169;
                }
                break;
            case 2555:
                if (str.equals("PK")) {
                    b2 = 170;
                }
                break;
            case 2556:
                if (str.equals("PL")) {
                    b2 = 171;
                }
                break;
            case 2557:
                if (str.equals("PM")) {
                    b2 = 172;
                }
                break;
            case 2562:
                if (str.equals("PR")) {
                    b2 = 173;
                }
                break;
            case 2563:
                if (str.equals("PS")) {
                    b2 = 174;
                }
                break;
            case 2564:
                if (str.equals("PT")) {
                    b2 = 175;
                }
                break;
            case 2567:
                if (str.equals("PW")) {
                    b2 = 176;
                }
                break;
            case 2569:
                if (str.equals("PY")) {
                    b2 = 177;
                }
                break;
            case 2576:
                if (str.equals("QA")) {
                    b2 = 178;
                }
                break;
            case 2611:
                if (str.equals("RE")) {
                    b2 = 179;
                }
                break;
            case 2621:
                if (str.equals("RO")) {
                    b2 = 180;
                }
                break;
            case 2625:
                if (str.equals("RS")) {
                    b2 = 181;
                }
                break;
            case 2627:
                if (str.equals("RU")) {
                    b2 = 182;
                }
                break;
            case 2629:
                if (str.equals("RW")) {
                    b2 = 183;
                }
                break;
            case 2638:
                if (str.equals("SA")) {
                    b2 = 184;
                }
                break;
            case 2639:
                if (str.equals("SB")) {
                    b2 = 185;
                }
                break;
            case 2640:
                if (str.equals("SC")) {
                    b2 = 186;
                }
                break;
            case 2641:
                if (str.equals("SD")) {
                    b2 = 187;
                }
                break;
            case 2642:
                if (str.equals("SE")) {
                    b2 = 188;
                }
                break;
            case 2644:
                if (str.equals("SG")) {
                    b2 = 189;
                }
                break;
            case 2645:
                if (str.equals("SH")) {
                    b2 = 190;
                }
                break;
            case 2646:
                if (str.equals("SI")) {
                    b2 = 191;
                }
                break;
            case 2647:
                if (str.equals("SJ")) {
                    b2 = 192;
                }
                break;
            case 2648:
                if (str.equals("SK")) {
                    b2 = 193;
                }
                break;
            case 2649:
                if (str.equals("SL")) {
                    b2 = 194;
                }
                break;
            case 2650:
                if (str.equals("SM")) {
                    b2 = 195;
                }
                break;
            case 2651:
                if (str.equals("SN")) {
                    b2 = 196;
                }
                break;
            case 2652:
                if (str.equals("SO")) {
                    b2 = 197;
                }
                break;
            case 2655:
                if (str.equals("SR")) {
                    b2 = 198;
                }
                break;
            case 2656:
                if (str.equals("SS")) {
                    b2 = 199;
                }
                break;
            case 2657:
                if (str.equals("ST")) {
                    b2 = 200;
                }
                break;
            case 2659:
                if (str.equals("SV")) {
                    b2 = 201;
                }
                break;
            case 2661:
                if (str.equals("SX")) {
                    b2 = 202;
                }
                break;
            case 2662:
                if (str.equals("SY")) {
                    b2 = 203;
                }
                break;
            case 2663:
                if (str.equals("SZ")) {
                    b2 = 204;
                }
                break;
            case 2671:
                if (str.equals("TC")) {
                    b2 = 205;
                }
                break;
            case 2672:
                if (str.equals("TD")) {
                    b2 = 206;
                }
                break;
            case 2675:
                if (str.equals("TG")) {
                    b2 = 207;
                }
                break;
            case 2676:
                if (str.equals("TH")) {
                    b2 = 208;
                }
                break;
            case 2678:
                if (str.equals("TJ")) {
                    b2 = 209;
                }
                break;
            case 2680:
                if (str.equals("TL")) {
                    b2 = 210;
                }
                break;
            case 2681:
                if (str.equals("TM")) {
                    b2 = 211;
                }
                break;
            case 2682:
                if (str.equals("TN")) {
                    b2 = 212;
                }
                break;
            case 2683:
                if (str.equals("TO")) {
                    b2 = 213;
                }
                break;
            case 2686:
                if (str.equals("TR")) {
                    b2 = 214;
                }
                break;
            case 2688:
                if (str.equals("TT")) {
                    b2 = 215;
                }
                break;
            case 2690:
                if (str.equals("TV")) {
                    b2 = 216;
                }
                break;
            case 2691:
                if (str.equals("TW")) {
                    b2 = 217;
                }
                break;
            case 2694:
                if (str.equals("TZ")) {
                    b2 = 218;
                }
                break;
            case 2700:
                if (str.equals("UA")) {
                    b2 = 219;
                }
                break;
            case 2706:
                if (str.equals("UG")) {
                    b2 = 220;
                }
                break;
            case 2718:
                if (str.equals("US")) {
                    b2 = 221;
                }
                break;
            case 2724:
                if (str.equals("UY")) {
                    b2 = 222;
                }
                break;
            case 2725:
                if (str.equals("UZ")) {
                    b2 = 223;
                }
                break;
            case 2731:
                if (str.equals("VA")) {
                    b2 = 224;
                }
                break;
            case 2733:
                if (str.equals("VC")) {
                    b2 = 225;
                }
                break;
            case 2735:
                if (str.equals("VE")) {
                    b2 = 226;
                }
                break;
            case 2737:
                if (str.equals("VG")) {
                    b2 = 227;
                }
                break;
            case 2739:
                if (str.equals("VI")) {
                    b2 = 228;
                }
                break;
            case 2744:
                if (str.equals("VN")) {
                    b2 = 229;
                }
                break;
            case 2751:
                if (str.equals("VU")) {
                    b2 = 230;
                }
                break;
            case 2767:
                if (str.equals("WF")) {
                    b2 = 231;
                }
                break;
            case 2780:
                if (str.equals("WS")) {
                    b2 = 232;
                }
                break;
            case 2803:
                if (str.equals("XK")) {
                    b2 = 233;
                }
                break;
            case 2828:
                if (str.equals("YE")) {
                    b2 = 234;
                }
                break;
            case 2843:
                if (str.equals("YT")) {
                    b2 = 235;
                }
                break;
            case 2855:
                if (str.equals("ZA")) {
                    b2 = 236;
                }
                break;
            case 2867:
                if (str.equals("ZM")) {
                    b2 = 237;
                }
                break;
            case 2877:
                if (str.equals("ZW")) {
                    b2 = 238;
                }
                break;
        }
        switch (b2) {
            case 0:
            case 4:
            case 17:
            case 29:
            case 50:
            case 57:
            case 113:
            case 116:
            case 202:
            case 225:
                return new int[]{1, 2, 0, 0, 2, 2};
            case 1:
                return new int[]{1, 4, 2, 3, 4, 1};
            case 2:
            case 204:
                return new int[]{4, 4, 3, 4, 2, 2};
            case 3:
            case 41:
                return new int[]{2, 4, 3, 4, 2, 2};
            case 5:
                return new int[]{1, 1, 1, 2, 2, 2};
            case 6:
            case 165:
                return new int[]{2, 3, 2, 3, 2, 2};
            case 7:
                return new int[]{3, 4, 4, 3, 2, 2};
            case 8:
            case 63:
            case 162:
            case 186:
            case 190:
                return new int[]{4, 2, 2, 2, 2, 2};
            case 9:
                return new int[]{2, 2, 2, 2, 1, 2};
            case 10:
                return new int[]{2, 2, 3, 3, 2, 2};
            case 11:
            case 61:
            case 93:
            case 102:
            case 127:
            case 145:
            case TsExtractor.TS_PACKET_SIZE /* 188 */:
                return new int[]{0, 0, 0, 0, 0, 2};
            case 12:
                return new int[]{0, 3, 1, 1, 3, 0};
            case 13:
                return new int[]{2, 2, 3, 4, 2, 2};
            case 14:
            case 51:
            case 121:
            case IjkMediaMeta.FF_PROFILE_H264_HIGH_444 /* 144 */:
            case TsExtractor.TS_STREAM_TYPE_AC4 /* 172 */:
            case 195:
            case 224:
                return new int[]{0, 2, 2, 2, 2, 2};
            case 15:
            case 55:
            case 128:
            case 194:
                return new int[]{4, 2, 3, 3, 2, 2};
            case 16:
            case 106:
            case 214:
                return new int[]{1, 1, 1, 1, 2, 2};
            case 18:
                return new int[]{2, 1, 3, 2, 4, 2};
            case 19:
                return new int[]{0, 0, 1, 0, 1, 2};
            case 20:
            case 187:
            case 203:
            case 206:
                return new int[]{4, 3, 4, 4, 2, 2};
            case 21:
            case 175:
            case 191:
                return new int[]{0, 0, 0, 0, 1, 2};
            case 22:
                return new int[]{1, 3, 1, 3, 4, 2};
            case 23:
            case 84:
            case 92:
            case 154:
            case HttpStatusCodesKt.HTTP_IM_USED /* 226 */:
            case 234:
                return new int[]{4, 4, 4, 4, 2, 2};
            case 24:
                return new int[]{4, 4, 2, 3, 2, 2};
            case 25:
            case 141:
            case 177:
                return new int[]{1, 2, 2, 2, 2, 2};
            case 26:
                return new int[]{0, 2, 0, 0, 2, 2};
            case 27:
                return new int[]{3, 2, 0, 0, 2, 2};
            case 28:
                return new int[]{1, 2, 4, 4, 2, 2};
            case 30:
                return new int[]{1, 1, 1, 1, 2, 4};
            case 31:
                return new int[]{3, 2, 1, 1, 2, 2};
            case 32:
                return new int[]{3, 1, 2, 2, 3, 2};
            case 33:
                return new int[]{3, 2, 1, 0, 2, 2};
            case 34:
                return new int[]{1, 2, 3, 3, 2, 2};
            case 35:
            case 42:
                return new int[]{2, 2, 2, 1, 2, 2};
            case 36:
            case 219:
                return new int[]{0, 2, 1, 2, 3, 3};
            case 37:
            case 137:
                return new int[]{3, 3, 2, 2, 2, 2};
            case 38:
                return new int[]{4, 2, 4, 2, 2, 2};
            case 39:
            case 62:
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
                return new int[]{3, 4, 3, 3, 2, 2};
            case 40:
                return new int[]{0, 1, 0, 0, 0, 2};
            case 43:
            case HttpStatusCodesKt.HTTP_ALREADY_REPORTED /* 208 */:
                return new int[]{0, 1, 2, 2, 2, 2};
            case 44:
            case 143:
                return new int[]{4, 3, 3, 4, 2, 2};
            case 45:
                return new int[]{2, 0, 1, 1, 3, 1};
            case 46:
                return new int[]{2, 3, 3, 2, 2, 2};
            case 47:
            case 157:
                return new int[]{2, 4, 4, 4, 2, 2};
            case 48:
            case 111:
            case 161:
            case 210:
                return new int[]{4, 2, 4, 4, 2, 2};
            case 49:
                return new int[]{2, 3, 0, 1, 2, 2};
            case 52:
                return new int[]{1, 0, 1, 0, 0, 2};
            case 53:
                return new int[]{0, 0, 2, 0, 1, 2};
            case 54:
                return new int[]{0, 1, 4, 2, 2, 1};
            case 56:
                return new int[]{0, 0, 2, 0, 0, 2};
            case 58:
            case 123:
                return new int[]{3, 4, 4, 4, 2, 2};
            case 59:
            case 209:
                return new int[]{3, 3, 4, 4, 2, 2};
            case 60:
                return new int[]{1, 3, 2, 1, 2, 2};
            case 64:
                return new int[]{0, 0, 0, 0, 1, 0};
            case 65:
                return new int[]{4, 3, 4, 4, 4, 2};
            case 66:
                return new int[]{0, 0, 0, 1, 0, 2};
            case 67:
                return new int[]{3, 2, 2, 3, 2, 2};
            case 68:
            case 155:
            case PsExtractor.AUDIO_STREAM /* 192 */:
                return new int[]{3, 2, 2, 2, 2, 2};
            case 69:
                return new int[]{4, 2, 4, 0, 2, 2};
            case 70:
                return new int[]{0, 2, 2, 0, 2, 2};
            case 71:
                return new int[]{1, 1, 1, 1, 0, 2};
            case 72:
                return new int[]{3, 4, 0, 0, 2, 2};
            case 73:
                return new int[]{1, 1, 3, 2, 2, 2};
            case 74:
                return new int[]{2, 2, 0, 0, 2, 2};
            case 75:
                return new int[]{1, 1, 0, 2, 2, 2};
            case 76:
                return new int[]{3, 2, 3, 3, 2, 2};
            case 77:
                return new int[]{0, 2, 1, 1, 2, 2};
            case 78:
                return new int[]{3, 3, 3, 2, 2, 2};
            case 79:
            case 97:
            case 104:
                return new int[]{0, 2, 0, 1, 2, 2};
            case 80:
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                return new int[]{1, 2, 2, 0, 2, 2};
            case 81:
            case 199:
                return new int[]{4, 3, 2, 4, 2, 2};
            case 82:
                return new int[]{3, 4, 4, 2, 2, 2};
            case 83:
                return new int[]{2, 1, 1, 3, 2, 2};
            case 85:
                return new int[]{1, 0, 0, 0, 1, 2};
            case 86:
                return new int[]{2, 1, 2, 1, 2, 2};
            case 87:
                return new int[]{2, 2, 4, 3, 3, 2};
            case 88:
                return new int[]{4, 4, 1, 2, 2, 2};
            case 89:
                return new int[]{3, 1, 1, 3, 2, 2};
            case 90:
                return new int[]{0, 1, 0, 1, 1, 0};
            case 91:
            case 115:
                return new int[]{1, 0, 0, 0, 0, 2};
            case 94:
                return new int[]{3, 1, 3, 3, 2, 4};
            case 95:
                return new int[]{1, 1, 1, 1, 1, 2};
            case 96:
                return new int[]{1, 2, 2, 3, 4, 2};
            case 98:
                return new int[]{1, 1, 3, 2, 2, 3};
            case 99:
                return new int[]{3, 2, 2, 0, 2, 2};
            case 100:
                return new int[]{3, 2, 3, 2, 2, 2};
            case 101:
                return new int[]{4, 2, 3, 3, 4, 3};
            case 103:
                return new int[]{0, 1, 1, 2, 1, 2};
            case 105:
                return new int[]{2, 4, 3, 1, 2, 2};
            case 107:
                return new int[]{0, 3, 2, 3, 4, 2};
            case 108:
                return new int[]{3, 2, 1, 1, 1, 2};
            case 109:
                return new int[]{2, 1, 1, 2, 2, 2};
            case 110:
                return new int[]{1, 0, 4, 2, 2, 2};
            case 112:
            case 230:
                return new int[]{4, 3, 3, 2, 2, 2};
            case 114:
                return new int[]{0, 2, 2, 4, 4, 4};
            case 117:
                return new int[]{2, 1, 2, 2, 3, 2};
            case 118:
                return new int[]{1, 2, 1, 3, 2, 2};
            case 119:
                return new int[]{3, 1, 1, 2, 2, 2};
            case 120:
                return new int[]{2, 2, 1, 1, 2, 2};
            case 122:
            case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                return new int[]{3, 2, 3, 3, 4, 2};
            case 124:
            case 168:
                return new int[]{4, 3, 3, 3, 2, 2};
            case 125:
                return new int[]{0, 1, 0, 1, 0, 2};
            case 126:
                return new int[]{4, 0, 3, 2, 1, 3};
            case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                return new int[]{3, 3, 1, 1, 2, 2};
            case 131:
                return new int[]{1, 0, 0, 0, 2, 2};
            case 132:
                return new int[]{2, 0, 0, 1, 3, 2};
            case 133:
                return new int[]{1, 2, 2, 3, 2, 2};
            case TsExtractor.TS_STREAM_TYPE_E_AC3 /* 135 */:
            case 211:
            case 216:
            case 231:
                return new int[]{4, 2, 2, 4, 2, 2};
            case TsExtractor.TS_STREAM_TYPE_DTS_HD /* 136 */:
                return new int[]{1, 0, 0, 1, 3, 2};
            case TsExtractor.TS_STREAM_TYPE_DTS_UHD /* 139 */:
                return new int[]{2, 0, 2, 2, 2, 2};
            case 140:
                return new int[]{0, 2, 4, 4, 3, 1};
            case 142:
                return new int[]{2, 1, 2, 3, 2, 2};
            case 146:
                return new int[]{3, 1, 0, 2, 2, 2};
            case 147:
                return new int[]{3, 2, 1, 3, 4, 2};
            case 148:
                return new int[]{3, 2, 2, 1, 2, 2};
            case 149:
                return new int[]{2, 4, 4, 4, 3, 2};
            case 150:
                return new int[]{1, 0, 4, 1, 1, 0};
            case 151:
            case 232:
                return new int[]{3, 1, 2, 2, 2, 2};
            case 152:
                return new int[]{3, 4, 3, 2, 2, 2};
            case 153:
            case 235:
                return new int[]{2, 3, 3, 4, 2, 2};
            case 156:
                return new int[]{3, 4, 2, 1, 2, 2};
            case 158:
                return new int[]{2, 1, 4, 3, 0, 4};
            case 159:
                return new int[]{0, 0, 3, 0, 0, 2};
            case 160:
                return new int[]{2, 2, 4, 3, 2, 2};
            case 163:
                return new int[]{0, 0, 1, 2, 4, 2};
            case 164:
                return new int[]{2, 3, 1, 2, 4, 2};
            case 166:
                return new int[]{1, 2, 4, 4, 3, 2};
            case 167:
                return new int[]{2, 2, 3, 1, 2, 2};
            case 169:
                return new int[]{2, 1, 2, 3, 2, 1};
            case 170:
                return new int[]{3, 3, 3, 3, 2, 2};
            case 171:
                return new int[]{1, 0, 2, 2, 4, 4};
            case 173:
                return new int[]{2, 0, 2, 1, 2, 0};
            case 174:
                return new int[]{3, 4, 1, 3, 2, 2};
            case 176:
                return new int[]{2, 2, 4, 1, 2, 2};
            case 178:
                return new int[]{1, 4, 4, 4, 4, 2};
            case 179:
                return new int[]{0, 3, 2, 3, 1, 2};
            case 180:
                return new int[]{0, 0, 1, 1, 3, 2};
            case 181:
                return new int[]{1, 0, 0, 1, 2, 2};
            case 182:
                return new int[]{1, 0, 0, 1, 3, 3};
            case 183:
                return new int[]{3, 3, 2, 0, 2, 2};
            case 184:
                return new int[]{3, 1, 1, 2, 2, 0};
            case 185:
            case 238:
                return new int[]{4, 2, 4, 3, 2, 2};
            case PsExtractor.PRIVATE_STREAM_1 /* 189 */:
                return new int[]{2, 3, 3, 3, 1, 1};
            case 193:
                return new int[]{0, 1, 1, 1, 2, 2};
            case 196:
                return new int[]{4, 4, 3, 2, 2, 2};
            case 197:
                return new int[]{2, 2, 3, 4, 4, 2};
            case 198:
                return new int[]{2, 4, 4, 1, 2, 2};
            case 200:
                return new int[]{2, 2, 1, 2, 2, 2};
            case 201:
                return new int[]{2, 3, 2, 1, 2, 2};
            case 205:
                return new int[]{3, 2, 1, 2, 2, 2};
            case 207:
                return new int[]{3, 4, 1, 0, 2, 2};
            case 212:
                return new int[]{3, 1, 1, 1, 2, 2};
            case 213:
                return new int[]{3, 2, 4, 3, 2, 2};
            case 215:
                return new int[]{2, 4, 1, 0, 2, 2};
            case 217:
                return new int[]{0, 0, 0, 0, 0, 0};
            case 218:
                return new int[]{3, 4, 2, 1, 3, 2};
            case 220:
                return new int[]{3, 3, 2, 3, 4, 2};
            case 221:
                return new int[]{2, 2, 4, 1, 3, 1};
            case 222:
                return new int[]{2, 1, 1, 2, 1, 2};
            case 223:
                return new int[]{1, 2, 3, 4, 3, 2};
            case 227:
                return new int[]{2, 2, 1, 1, 2, 4};
            case 228:
                return new int[]{0, 2, 1, 2, 2, 2};
            case 229:
                return new int[]{0, 0, 1, 2, 2, 2};
            case 233:
                return new int[]{1, 2, 1, 1, 2, 2};
            case 236:
                return new int[]{2, 4, 2, 1, 1, 2};
            case 237:
                return new int[]{4, 4, 4, 3, 2, 2};
            default:
                return new int[]{2, 2, 2, 2, 2, 2};
        }
    }
}
