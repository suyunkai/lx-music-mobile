package a;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.sdk.audio.IWanosAudioConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class a implements IWanosAudioConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f6b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ScheduledExecutorService f7c = null;

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String strA;
            synchronized (a.this.f6b) {
                if (a.this.f5a == null) {
                    strA = "";
                } else {
                    a aVar = a.this;
                    strA = aVar.a(aVar.f5a, "wanos_sourceTag", "-1");
                }
                IWanosAudioConfig.WanosPlayState wanosPlayState = TextUtils.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, strA) ? IWanosAudioConfig.WanosPlayState.PLAY_STATE_WANOS_VIRTUAL_SOURCE_PLAYING : TextUtils.equals("0", strA) ? IWanosAudioConfig.WanosPlayState.PLAY_STATE_WANOS_SOURCE_PLAYING : IWanosAudioConfig.WanosPlayState.PLAY_STATE_OTHER;
                for (int i = 0; i < a.this.f6b.size(); i++) {
                    ((IWanosAudioConfig.IWanosAudioPlayStateListener) a.this.f6b.get(i)).onAudioPlayState(wanosPlayState);
                }
            }
        }
    }

    public a(Context context) {
        this.f5a = context;
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public void addWanosAudioPlayStateListener(IWanosAudioConfig.IWanosAudioPlayStateListener iWanosAudioPlayStateListener) {
        synchronized (this.f6b) {
            if (!this.f6b.contains(iWanosAudioPlayStateListener)) {
                this.f6b.add(iWanosAudioPlayStateListener);
            }
            if (this.f6b.size() == 1 || this.f7c == null) {
                a();
            }
        }
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public String getDeviceChannelId(Context context) {
        return a(context, "wanos_product_channel_id", "000000");
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public IWanosAudioConfig.WanosIntegratedState getDeviceIntegratedState(Context context) {
        String strA = a(context, "wanos_integrated_state", "unknow");
        IWanosAudioConfig.WanosIntegratedState wanosIntegratedState = IWanosAudioConfig.WanosIntegratedState.WANOS_DECODER;
        if (TextUtils.equals(strA, wanosIntegratedState.getLabelName())) {
            return wanosIntegratedState;
        }
        IWanosAudioConfig.WanosIntegratedState wanosIntegratedState2 = IWanosAudioConfig.WanosIntegratedState.WANOS_DECODER_UPMIX;
        if (TextUtils.equals(strA, wanosIntegratedState2.getLabelName())) {
            return wanosIntegratedState2;
        }
        IWanosAudioConfig.WanosIntegratedState wanosIntegratedState3 = IWanosAudioConfig.WanosIntegratedState.WANOS_DECODER_UPMIX_TUNING;
        if (TextUtils.equals(strA, wanosIntegratedState3.getLabelName())) {
            return wanosIntegratedState3;
        }
        IWanosAudioConfig.WanosIntegratedState wanosIntegratedState4 = IWanosAudioConfig.WanosIntegratedState.WANOS_DECODER_UNINTEGRATE;
        TextUtils.equals(strA, wanosIntegratedState4.getLabelName());
        return wanosIntegratedState4;
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public String getDeviceManuFacturer(Context context) {
        return a(context, "wanos_product_channel_name", "");
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public String getDeviceToken(Context context) {
        return a(context, "wanos_product_channel_token", "");
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public String getDeviceWanosDecoderVersion(Context context) {
        return a(context, "wanos_decoder_version", "");
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public int getWanosAudioOutputChannelNum(Context context) {
        return Integer.valueOf(a(context, "wanos_audio_out_put_channel", "0")).intValue();
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public boolean isWanosSourcePlaying() {
        return TextUtils.equals(a(this.f5a, "wanos_sourceTag", "-1"), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public boolean isWanosVirtualSourcePlaying() {
        return TextUtils.equals(a(this.f5a, "wanos_sourceTag", "-1"), "0");
    }

    @Override // com.wanos.sdk.audio.IWanosAudioConfig
    public void removeWanosAudioPlayStateListener(IWanosAudioConfig.IWanosAudioPlayStateListener iWanosAudioPlayStateListener) {
        ScheduledExecutorService scheduledExecutorService;
        synchronized (this.f6b) {
            if (this.f6b.contains(iWanosAudioPlayStateListener)) {
                this.f6b.remove(iWanosAudioPlayStateListener);
            }
            if (this.f6b.size() == 0 && (scheduledExecutorService = this.f7c) != null) {
                scheduledExecutorService.shutdownNow();
                this.f7c = null;
            }
        }
    }

    public final String a(Context context, String str, String str2) {
        AudioManager audioManagerA = d.a.a(context);
        if (audioManagerA == null) {
            return str2;
        }
        String parameters = audioManagerA.getParameters(str);
        return TextUtils.isEmpty(parameters) ? str2 : parameters;
    }

    public final void a() {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1);
        this.f7c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(new b(), 10L, 3L, TimeUnit.SECONDS);
    }
}
