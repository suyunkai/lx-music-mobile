package com.ecarx.eas.sdk.vr.radio;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VrLocalRadioCtrlAPI extends ECarXAPIBase {
    public static final String TAG = "VrLocalRadioCtrlAPI";
    private static volatile VrLocalRadioCtrlAPI mInstance;

    public abstract boolean cancelRadioCtrlCapabilityDeclaration(RadioIntentHandling radioIntentHandling);

    public abstract boolean declareRadioCtrlCapability(int[] iArr, RadioIntentHandling radioIntentHandling);

    public abstract void declareVrCtrlPriority(String str, int i, RadioIntentHandling radioIntentHandling);

    public static VrLocalRadioCtrlAPI get(Context context) {
        if (mInstance == null) {
            synchronized (VrLocalRadioCtrlAPI.class) {
                if (mInstance == null) {
                    mInstance = createVrLocalRadioCtrlAPI(context);
                }
            }
        }
        return mInstance;
    }

    private static VrLocalRadioCtrlAPI createVrLocalRadioCtrlAPI(Context context) {
        if (context == null) {
            throw new InvalidParameterException("param Context is null!");
        }
        Log.i(TAG, "createVrLocalRadioCtrlAPI");
        try {
            return (VrLocalRadioCtrlAPI) Class.forName("com.ecarx.eas.sdk.mediacenter.a.i").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "createVrLocalRadioCtrlAPI failed");
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "createVrLocalRadioCtrlAPI failed");
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            Log.e(TAG, "createVrLocalRadioCtrlAPI failed");
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            Log.e(TAG, "createVrLocalRadioCtrlAPI failed");
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            Log.e(TAG, "createVrLocalRadioCtrlAPI failed");
            return null;
        }
    }
}
