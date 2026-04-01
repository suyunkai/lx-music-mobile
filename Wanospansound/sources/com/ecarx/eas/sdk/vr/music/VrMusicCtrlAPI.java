package com.ecarx.eas.sdk.vr.music;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VrMusicCtrlAPI extends ECarXAPIBase {
    public static final String TAG = "VrMusicCtrlAPI";
    private static volatile VrMusicCtrlAPI mInstance;

    public abstract boolean cancelMusicCtrlCapabilityDeclaration(MusicIntentListener musicIntentListener) throws RemoteException;

    public abstract boolean declareMusicCtrlCapability(int[] iArr, MusicIntentListener musicIntentListener);

    public abstract void declareVrCtrlPriority(String str, int i, MusicIntentListener musicIntentListener);

    public static VrMusicCtrlAPI get(Context context) {
        if (mInstance == null) {
            synchronized (VrMusicCtrlAPI.class) {
                if (mInstance == null) {
                    mInstance = createVrMusicCtrlAPI(context);
                }
            }
        }
        return mInstance;
    }

    private static VrMusicCtrlAPI createVrMusicCtrlAPI(Context context) {
        if (context == null) {
            throw new InvalidParameterException("param Context is null!");
        }
        Log.i(TAG, "createVrMediaCtrlAPI");
        try {
            return (VrMusicCtrlAPI) Class.forName("com.ecarx.eas.sdk.mediacenter.a.j").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "createVrMediaCtrlAPI failed");
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "createVrMediaCtrlAPI failed");
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            Log.e(TAG, "createVrMediaCtrlAPI failed");
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            Log.e(TAG, "createVrMediaCtrlAPI failed");
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            Log.e(TAG, "createVrMediaCtrlAPI failed");
            return null;
        }
    }
}
