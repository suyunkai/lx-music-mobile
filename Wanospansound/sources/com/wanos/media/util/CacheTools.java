package com.wanos.media.util;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class CacheTools {
    private static final String TAG = "CacheTools";

    public static boolean toObjectLocal(Context context, String str, Object obj) {
        if (context == null) {
            ZeroLogcatTools.e(TAG, "Context is Null");
            return false;
        }
        if (obj == null) {
            ZeroLogcatTools.e(TAG, "Object is Null");
            return false;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.getApplicationContext().openFileOutput(str, 0);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                try {
                    objectOutputStream.writeObject(obj);
                    ZeroLogcatTools.d(TAG, "Object Serializable Success");
                    objectOutputStream.close();
                    if (fileOutputStreamOpenFileOutput == null) {
                        return true;
                    }
                    fileOutputStreamOpenFileOutput.close();
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            ZeroLogcatTools.e(TAG, "Object To Local Error: " + e.getMessage());
            return false;
        }
    }

    public static <T> T getLocalObject(Context context, String str, Class<T> cls) {
        if (context == null) {
            ZeroLogcatTools.e(TAG, "Context is Null");
            return null;
        }
        try {
            FileInputStream fileInputStreamOpenFileInput = context.getApplicationContext().openFileInput(str);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStreamOpenFileInput);
                try {
                    ZeroLogcatTools.d(TAG, "Object Read Success");
                    T tCast = cls.cast(objectInputStream.readObject());
                    objectInputStream.close();
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    return tCast;
                } finally {
                }
            } catch (Throwable th) {
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | ClassNotFoundException e) {
            ZeroLogcatTools.e(TAG, "Read Object Error: " + e.getMessage());
            return null;
        }
    }
}
