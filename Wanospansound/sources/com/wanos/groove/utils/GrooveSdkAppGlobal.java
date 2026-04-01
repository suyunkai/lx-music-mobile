package com.wanos.groove.utils;

import android.app.Application;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class GrooveSdkAppGlobal {
    public static final String CLASS_FOR_NAME = "android.app.ActivityThread";
    public static final String CURRENT_APPLICATION = "currentApplication";
    public static final String GET_INITIAL_APPLICATION = "getInitialApplication";
    private static final String TAG = "TAG_SDKGrooveSdkAppGlobal";
    public static final String TAG_SDK = "TAG_SDK";

    private GrooveSdkAppGlobal() {
    }

    public static Application getApplication() {
        Application application;
        try {
            Method declaredMethod = Class.forName(CLASS_FOR_NAME).getDeclaredMethod(CURRENT_APPLICATION, new Class[0]);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            Log.w(TAG, "getApplication: " + e);
            application = null;
        }
        if (application != null) {
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName(CLASS_FOR_NAME).getDeclaredMethod(GET_INITIAL_APPLICATION, new Class[0]);
            declaredMethod2.setAccessible(true);
            return (Application) declaredMethod2.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            Log.w(TAG, "getApplication: " + e2);
            return application;
        }
    }
}
