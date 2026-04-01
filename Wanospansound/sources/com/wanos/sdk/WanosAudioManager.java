package com.wanos.sdk;

import android.content.Context;
import c.a;

/* JADX INFO: loaded from: classes3.dex */
public class WanosAudioManager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile WanosAudioManager f675b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IWanosAudio f676a;

    public WanosAudioManager(Context context) {
        this.f676a = new a(context);
    }

    public static synchronized WanosAudioManager a(Context context) {
        if (f675b == null) {
            synchronized (WanosAudioManager.class) {
                if (f675b == null) {
                    f675b = new WanosAudioManager(context);
                }
            }
        }
        return f675b;
    }

    public static IWanosAudio getWanosAudioInstance(Context context) {
        return a(context).f676a;
    }
}
