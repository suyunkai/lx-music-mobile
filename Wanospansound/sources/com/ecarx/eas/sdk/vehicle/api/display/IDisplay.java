package com.ecarx.eas.sdk.vehicle.api.display;

import android.view.Display;

/* JADX INFO: loaded from: classes2.dex */
public interface IDisplay {
    float getBrightnessStep(Display display);

    float getCurrentBrightness(Display display);

    float getMaxBrightness(Display display);

    float getMinBrightness(Display display);

    boolean setBrightness(Display display, float f);

    boolean setDisplayOff(Display display);
}
