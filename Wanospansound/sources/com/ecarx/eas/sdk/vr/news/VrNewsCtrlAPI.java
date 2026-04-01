package com.ecarx.eas.sdk.vr.news;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VrNewsCtrlAPI extends ECarXAPIBase {
    public static final String TAG = "VrNewsCtrlAPI";
    private static volatile VrNewsCtrlAPI mInstance;

    public abstract boolean cancelNewsCtrlCapabilityDeclaration(NewsIntentListener newsIntentListener);

    public abstract boolean declareNewsCtrlCapability(NewsIntentListener newsIntentListener);

    public abstract void declareVrCtrlPriority(String str, int i, NewsIntentListener newsIntentListener);

    public static VrNewsCtrlAPI get(Context context) {
        if (mInstance == null) {
            synchronized (VrNewsCtrlAPI.class) {
                if (mInstance == null) {
                    mInstance = createVrNewsCtrlAPIAPI(context);
                }
            }
        }
        return mInstance;
    }

    private static VrNewsCtrlAPI createVrNewsCtrlAPIAPI(Context context) {
        if (context == null) {
            throw new InvalidParameterException("param Context is null!");
        }
        Log.i(TAG, "createVrNewsCtrlAPIAPI");
        try {
            return (VrNewsCtrlAPI) Class.forName("com.ecarx.eas.sdk.mediacenter.a.k").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "createVrNewsCtrlAPIAPI failed");
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "createVrNewsCtrlAPIAPI failed");
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            Log.e(TAG, "createVrNewsCtrlAPIAPI failed");
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            Log.e(TAG, "createVrNewsCtrlAPIAPI failed");
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            Log.e(TAG, "createVrNewsCtrlAPIAPI failed");
            return null;
        }
    }
}
