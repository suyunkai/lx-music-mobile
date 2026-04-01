package com.wanos.sdk.audio;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface IWanosAudioUpmix {
    boolean getWanosUpmixSwitchState(Context context);

    void toggleWanosUpmix(Context context, boolean z);
}
