package com.ecarx.eas.sdk.vehicle.api.dashboard;

/* JADX INFO: loaded from: classes2.dex */
public interface IMileageInfo {
    long getEnduranceMileage();

    long getNextMaintenanceMileage();

    long getTotalMileage();

    long getTripDuration();

    long getTripMileage();
}
