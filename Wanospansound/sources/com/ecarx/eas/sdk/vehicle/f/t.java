package com.ecarx.eas.sdk.vehicle.f;

import android.util.Log;
import android.view.Display;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.display.IDisplay;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.openapi.protobuf.vehicle.ECARXAdaptVehicle;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t implements IDisplay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f245b = getClass().getSimpleName();

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f244a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public boolean setDisplayOff(Display display) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "setDisplayOff  mService =  " + this.f244a + "  display = " + display);
            return false;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = display.getDisplayId();
        return EASCallUtils.callBoolean(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "setDisplayOff", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getBrightnessStep(Display display) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "getBrightnessStep  mService =  " + this.f244a + "  display = " + display);
            return Float.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = display.getDisplayId();
        return EASCallUtils.callFloat(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "getBrightnessStep", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public boolean setBrightness(Display display, float f) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "setBrightness  mService =  " + this.f244a + "  display = " + display);
            return false;
        }
        ECARXAdaptVehicle.DisplayLightParam displayLightParam = new ECARXAdaptVehicle.DisplayLightParam();
        displayLightParam.displayId = display.getDisplayId();
        displayLightParam.light = f;
        return EASCallUtils.callBoolean(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "setBrightness", MessageNano.toByteArray(displayLightParam), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getMaxBrightness(Display display) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "getMaxBrightness  mService =  " + this.f244a + "  display = " + display);
            return Float.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = display.getDisplayId();
        return EASCallUtils.callFloat(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "getMaxBrightness", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getMinBrightness(Display display) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "getMinBrightness  mService =  " + this.f244a + "  display = " + display);
            return Float.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = display.getDisplayId();
        return EASCallUtils.callFloat(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "getMinBrightness", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getCurrentBrightness(Display display) {
        if (this.f244a == null || display == null) {
            Log.e(this.f245b, "getCurrentBrightness  mService =  " + this.f244a + "  display = " + display);
            return Float.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = display.getDisplayId();
        return EASCallUtils.callFloat(this.f244a, IServiceManager.SERVICE_VEHICLE, "Display", "getCurrentBrightness", MessageNano.toByteArray(intMsg), null);
    }
}
