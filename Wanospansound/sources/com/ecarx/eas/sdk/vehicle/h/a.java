package com.ecarx.eas.sdk.vehicle.h;

import com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements ISafeBeltInfo {
    @Override // com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo
    public final boolean attachSeatBeltBuckledChangedObserver(ISafeBeltInfo.SeatBeltBuckledChangedObserver seatBeltBuckledChangedObserver) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo
    public final boolean detachSeatBeltBuckledChangedObserver(ISafeBeltInfo.SeatBeltBuckledChangedObserver seatBeltBuckledChangedObserver) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo
    public final boolean isSafeBeltLocked(int i) {
        return false;
    }
}
