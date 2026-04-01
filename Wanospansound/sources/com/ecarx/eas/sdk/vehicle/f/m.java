package com.ecarx.eas.sdk.vehicle.f;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.sdk.log.LogProxy;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m implements ICarInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.a f225a;

    public abstract void a(com.ecarx.eas.sdk.vehicle.v3.e eVar);

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarConfig(int i) {
        return -1;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarInfoByArray(int i, int i2) {
        return -1;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public int getCarInfoInt(int i) {
        try {
            return this.f225a.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public float getCarInfoFloat(int i) {
        try {
            return this.f225a.b(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public String getCarInfoString(int i) {
        try {
            return this.f225a.c(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public Display getDisplay(int i) {
        try {
            if (EASFrameworkApiClient.getInstance().getVersion() <= 0) {
                Log.e(LogProxy.TAG, getClass().getSimpleName() + ">> 服务版本小于等于0");
                return null;
            }
            com.ecarx.eas.sdk.vehicle.v3.a aVar = this.f225a;
            if (aVar == null) {
                Log.e(getClass().getName(), " exter  getDisplay mRemoteCarInfo is null");
                return null;
            }
            int iD = aVar.d(i);
            if (iD == Integer.MIN_VALUE) {
                Log.e("CarInfoWrapper", "EmRemoteCarInfo.getDisplay return null");
                return null;
            }
            Context appContext = EASFrameworkApiClient.getInstance().getAppContext();
            if (appContext == null) {
                Log.e("CarInfoWrapper", "EASFrameworkApiClient.getInstance().getAppContext() return null");
                return null;
            }
            DisplayManager displayManager = (DisplayManager) appContext.getSystemService("display");
            if (displayManager == null) {
                Log.e("CarInfoWrapper", "get DisplayService failed! return null");
                return null;
            }
            return displayManager.getDisplay(iD);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
