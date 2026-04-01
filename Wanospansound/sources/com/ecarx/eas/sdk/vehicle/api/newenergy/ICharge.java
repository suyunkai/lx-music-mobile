package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface ICharge {

    public interface IChargeStateListener {
        void onChargeStateChanged(IChargeState iChargeState);

        void onPlugStateChanged(int i);

        void onPreChargeStateChanged(int i);
    }

    void cancelPreCharge();

    IChargeState getChargeState();

    long getSupportMaxPreChargeDelay();

    long getSupportMaxPreChargeDuration();

    long getSupportMinPreChargeDelay();

    long getSupportMinPreChargeDuration();

    boolean isSupportPreChargePerDay();

    void registerStateListener(IChargeStateListener iChargeStateListener);

    boolean requestChargeImmediately();

    boolean requestChargeImmediately(long j);

    boolean requestPreCharge(long j);

    boolean requestPreCharge(long j, long j2);

    boolean requestPreChargePerDay(long j);

    boolean requestPreChargePerDay(long j, long j2);

    void unregisterStateListener(IChargeStateListener iChargeStateListener);
}
