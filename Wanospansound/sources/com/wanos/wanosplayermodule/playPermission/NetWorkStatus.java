package com.wanos.wanosplayermodule.playPermission;

/* JADX INFO: loaded from: classes3.dex */
public class NetWorkStatus {
    public static NetWorkChangeLis lis = null;
    public static boolean netSuc = true;

    public static void setListener(NetWorkChangeLis netWorkChangeLis) {
        lis = netWorkChangeLis;
    }

    public static void onNetSuccess() {
        if (netSuc) {
            return;
        }
        netSuc = true;
        NetWorkChangeLis netWorkChangeLis = lis;
        if (netWorkChangeLis != null) {
            netWorkChangeLis.onNetSuccess();
        }
    }

    public static void onNetError() {
        if (netSuc) {
            netSuc = false;
            NetWorkChangeLis netWorkChangeLis = lis;
            if (netWorkChangeLis != null) {
                netWorkChangeLis.onNetError();
            }
        }
    }
}
