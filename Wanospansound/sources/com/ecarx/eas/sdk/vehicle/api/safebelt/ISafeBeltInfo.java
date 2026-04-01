package com.ecarx.eas.sdk.vehicle.api.safebelt;

/* JADX INFO: loaded from: classes2.dex */
public interface ISafeBeltInfo {
    public static final int LF_SAFE_BELT = 1;
    public static final int LR_SAFE_BELT = 2;
    public static final int RF_SAFE_BELT = 3;
    public static final int RR_SAFE_BELT = 4;

    public interface SeatBeltBuckledChangedObserver {
        void notifySeatBeltBuckledChanged(int i, boolean z);
    }

    boolean attachSeatBeltBuckledChangedObserver(SeatBeltBuckledChangedObserver seatBeltBuckledChangedObserver);

    boolean detachSeatBeltBuckledChangedObserver(SeatBeltBuckledChangedObserver seatBeltBuckledChangedObserver);

    boolean isSafeBeltLocked(int i);
}
