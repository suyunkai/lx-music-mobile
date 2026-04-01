package com.wanos.media.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.commonlibrary.event.DisconnectEvent;
import com.wanos.commonlibrary.utils.NetworkUtils;
import com.wanos.media.base.CarConstants;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class NetUtil {
    private static final String TAG = "wanos:[NetUtil]";
    private static NetUtil netUtil;
    DisconnectEvent disconnectEvent = new DisconnectEvent();

    private NetUtil() {
    }

    public static void init(Context context) {
        if (netUtil == null) {
            netUtil = new NetUtil();
        }
        netUtil.registerDefaultNetworkCallback(context);
        if (NetworkUtils.isConnectedAvailableNetwork(context)) {
            Log.d(TAG, "onCapabilitiesChanged ---> ====网络可正常上网===网络类型为： " + NetworkUtils.getConnectedNetworkType(context));
            WanOSRetrofitUtil.isNetConnect = true;
        } else {
            WanOSRetrofitUtil.isNetConnect = false;
        }
    }

    private void registerDefaultNetworkCallback(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() { // from class: com.wanos.media.util.NetUtil.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    if (NetworkUtils.isConnectedAvailableNetwork(context)) {
                        Log.d(NetUtil.TAG, "onCapabilitiesChanged ---> ====网络可正常上网===网络类型为： " + NetworkUtils.getConnectedNetworkType(context));
                        WanOSRetrofitUtil.isNetConnect = true;
                        NetUtil.this.disconnectEvent.setFlag(!WanOSRetrofitUtil.isNetConnect);
                        EventBus.getDefault().post(NetUtil.this.disconnectEvent);
                    } else {
                        Log.d(NetUtil.TAG, "onCapabilitiesChanged ---> ====网络不可正常上网");
                        WanOSRetrofitUtil.isNetConnect = false;
                        NetUtil.this.disconnectEvent.setFlag(!WanOSRetrofitUtil.isNetConnect);
                        EventBus.getDefault().post(NetUtil.this.disconnectEvent);
                    }
                    if (networkCapabilities.hasCapability(16)) {
                        if (networkCapabilities.hasTransport(0)) {
                            Log.d(NetUtil.TAG, "===当前在使用Mobile流量上网===");
                        } else if (networkCapabilities.hasTransport(1)) {
                            Log.d(NetUtil.TAG, "====当前在使用WiFi上网===");
                        } else if (networkCapabilities.hasTransport(2)) {
                            Log.d(NetUtil.TAG, "=====当前使用蓝牙上网=====");
                        } else if (networkCapabilities.hasTransport(3)) {
                            Log.d(NetUtil.TAG, "=====当前使用以太网上网=====");
                        } else if (networkCapabilities.hasTransport(4)) {
                            Log.d(NetUtil.TAG, "===当前使用VPN上网====");
                        } else if (networkCapabilities.hasTransport(5)) {
                            Log.d(NetUtil.TAG, "===表示此网络使用Wi-Fi感知传输====");
                        } else if (networkCapabilities.hasTransport(6)) {
                            Log.d(NetUtil.TAG, "=====表示此网络使用LoWPAN传输=====");
                        } else if (networkCapabilities.hasTransport(0)) {
                            Log.d(NetUtil.TAG, "=====表示此网络使用USB传输=====");
                        }
                    }
                    Log.e(NetUtil.TAG, "是否有活动的网络连接" + NetUtil.hasNetWorkConnection(context));
                    if (CarConstants.needAvailableByPing[CarConstants.buildIndex]) {
                        NetUtil.this.checkNetworkAvailable();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.i(NetUtil.TAG, "==网络连接成功，通知可以使用的时候调用====onAvailable===");
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    Log.e(NetUtil.TAG, "==当网络连接超时或网络请求达不到可用要求时调用====onUnavailable===");
                    super.onUnavailable();
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onBlockedStatusChanged(Network network, boolean blocked) {
                    Log.e(NetUtil.TAG, "==当访问指定的网络被阻止或解除阻塞时调用===onBlockedStatusChanged==:" + blocked);
                    super.onBlockedStatusChanged(network, blocked);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLosing(Network network, int maxMsToLive) {
                    Log.e(NetUtil.TAG, "==当网络正在断开连接时调用===onLosing===");
                    super.onLosing(network, maxMsToLive);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    Log.e(NetUtil.TAG, "==当网络已断开连接时调用===onLost===");
                    WanOSRetrofitUtil.isNetConnect = false;
                    NetUtil.this.disconnectEvent.setFlag(!WanOSRetrofitUtil.isNetConnect);
                    EventBus.getDefault().post(NetUtil.this.disconnectEvent);
                    super.onLost(network);
                    Log.e(NetUtil.TAG, "是否有活动的网络连接" + NetUtil.hasNetWorkConnection(context));
                    if (CarConstants.needAvailableByPing[CarConstants.buildIndex]) {
                        NetUtil.this.checkNetworkAvailable();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                    Log.i(NetUtil.TAG, "==当网络连接的属性被修改时调用===onLinkPropertiesChanged===");
                    super.onLinkPropertiesChanged(network, linkProperties);
                }
            });
        }
    }

    void checkNetworkAvailable() {
        new Thread(new Runnable() { // from class: com.wanos.media.util.NetUtil.2
            @Override // java.lang.Runnable
            public void run() {
                if (NetUtil.this.isNetworkHasTraffic("www.baidu.com")) {
                    Log.d(NetUtil.TAG, " 百度网络正常 ");
                } else {
                    Log.d(NetUtil.TAG, " 百度当前网络不可用 ");
                }
            }
        }).start();
        new Thread(new Runnable() { // from class: com.wanos.media.util.NetUtil.3
            @Override // java.lang.Runnable
            public void run() {
                if (NetUtil.this.isNetworkHasTraffic("jili.wavworks.com")) {
                    Log.d(NetUtil.TAG, " wanos网络正常 ");
                } else {
                    Log.d(NetUtil.TAG, " wanos当前网络不可用 ");
                }
            }
        }).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0173 A[Catch: IOException -> 0x016f, TRY_LEAVE, TryCatch #7 {IOException -> 0x016f, blocks: (B:109:0x016b, B:113:0x0173), top: B:123:0x016b }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x014a A[Catch: IOException -> 0x0146, TRY_LEAVE, TryCatch #12 {IOException -> 0x0146, blocks: (B:95:0x0142, B:99:0x014a), top: B:127:0x0142 }] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v15, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isNetworkHasTraffic(java.lang.String r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.util.NetUtil.isNetworkHasTraffic(java.lang.String):boolean");
    }

    public static boolean hasNetWorkConnection(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
