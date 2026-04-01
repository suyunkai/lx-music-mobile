package com.wanos.media;

/* JADX INFO: loaded from: classes3.dex */
public class ComResCenter {
    private static ComResCenter comResCenter;
    public MainServer mainServer;

    public static ComResCenter getInstance() {
        if (comResCenter == null) {
            comResCenter = new ComResCenter();
        }
        return comResCenter;
    }
}
