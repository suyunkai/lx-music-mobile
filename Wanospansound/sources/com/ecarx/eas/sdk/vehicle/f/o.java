package com.ecarx.eas.sdk.vehicle.f;

import android.util.Log;
import android.view.Display;
import com.ecarx.eas.sdk.vehicle.api.display.IDisplay;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o implements IDisplay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b.d.a f231a;

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f231a = eVar.f();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public boolean setDisplayOff(Display display) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar = this.f231a;
            if (aVar != null && display != null) {
                return aVar.a(display.getDisplayId());
            }
            Log.e("DisplayWrapper", "setDisplayOff  mRemoteDisplay =  " + this.f231a + "  display = " + display);
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public boolean setBrightness(Display display, float f) {
        com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar;
        try {
            aVar = this.f231a;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (aVar != null && display != null) {
            aVar.a(display.getDisplayId(), f);
            return false;
        }
        Log.e("DisplayWrapper", "setBrightness  mRemoteDisplay =  " + this.f231a + "  display = " + display);
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getBrightnessStep(Display display) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar = this.f231a;
            if (aVar != null && display != null) {
                return aVar.b(display.getDisplayId());
            }
            Log.e("DisplayWrapper", "getBrightnessStep  mRemoteDisplay =  " + this.f231a + "  display = " + display);
            return Float.MIN_VALUE;
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e("DisplayWrapper", "getBrightnessStep", th);
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getMaxBrightness(Display display) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar = this.f231a;
            if (aVar != null && display != null) {
                return aVar.c(display.getDisplayId());
            }
            Log.e("DisplayWrapper", "getMaxBrightness  mRemoteDisplay =  " + this.f231a + "  display = " + display);
            return Float.MIN_VALUE;
        } catch (Throwable th) {
            th.printStackTrace();
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getMinBrightness(Display display) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar = this.f231a;
            if (aVar != null && display != null) {
                return aVar.d(display.getDisplayId());
            }
            Log.e("DisplayWrapper", "getMinBrightness  mRemoteDisplay =  " + this.f231a + "  display = " + display);
            return Float.MIN_VALUE;
        } catch (Throwable th) {
            th.printStackTrace();
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.display.IDisplay
    public float getCurrentBrightness(Display display) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.d.a aVar = this.f231a;
            if (aVar != null && display != null) {
                return aVar.e(display.getDisplayId());
            }
            Log.e("DisplayWrapper", "getCurrentBrightness  mRemoteDisplay =  " + this.f231a + "  display = " + display);
            return Float.MIN_VALUE;
        } catch (Throwable th) {
            th.printStackTrace();
            return Float.MIN_VALUE;
        }
    }
}
