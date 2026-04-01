package com.ecarx.eas.sdk.radio;

/* JADX INFO: loaded from: classes2.dex */
public enum Band {
    FM(0),
    AM(1);

    private int id;

    Band(int i) {
        this.id = i;
    }

    public static Band bandFromId(int i) {
        for (Band band : values()) {
            if (band.getId() == i) {
                return band;
            }
        }
        return FM;
    }

    public final int getId() {
        return this.id;
    }
}
