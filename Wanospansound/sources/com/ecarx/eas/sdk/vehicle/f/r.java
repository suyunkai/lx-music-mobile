package com.ecarx.eas.sdk.vehicle.f;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;
import com.ecarx.openapi.protobuf.ECARXCommon;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r implements ICarInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f238a;

    public abstract void a(IEASFrameworkService iEASFrameworkService);

    public r() {
        getClass().getSimpleName();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarInfoInt(int i) {
        if (this.f238a == null) {
            return 0;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callInt(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getCarInfoInt", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public float getCarInfoFloat(int i) {
        if (this.f238a == null) {
            return 0.0f;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callFloat(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getCarInfoFloat", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public String getCarInfoString(int i) {
        if (this.f238a == null) {
            return null;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callString(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getCarInfoString", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarInfoByArray(int i, int i2) {
        if (this.f238a == null) {
            return -1;
        }
        ECARXCommon.IntMsgList intMsgList = new ECARXCommon.IntMsgList();
        intMsgList.value = new int[]{i, i2};
        return EASCallUtils.callInt(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getCarInfoByArray", MessageNano.toByteArray(intMsgList), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarConfig(int i) {
        if (this.f238a == null) {
            return -1;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callInt(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getCarConfig", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public Display getDisplay(int i) {
        if (this.f238a == null) {
            return null;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        int iCallInt = EASCallUtils.callInt(this.f238a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getDisplay", MessageNano.toByteArray(intMsg), null);
        if (iCallInt == Integer.MIN_VALUE) {
            Log.e("EASCarInfoWrapper", "EASCallUtils.call.getDisplay return null");
            return null;
        }
        Context appContext = EASFrameworkApiClient.getInstance().getAppContext();
        if (appContext == null) {
            Log.e("EASCarInfoWrapper", "EASFrameworkApiClient.getInstance().getAppContext() return null");
            return null;
        }
        DisplayManager displayManager = (DisplayManager) appContext.getSystemService("display");
        if (displayManager == null) {
            Log.e("EASCarInfoWrapper", "get DisplayService failed! return null");
            return null;
        }
        return displayManager.getDisplay(iCallInt);
    }
}
