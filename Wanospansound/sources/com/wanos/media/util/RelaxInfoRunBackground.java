package com.wanos.media.util;

import androidx.lifecycle.LiveData;
import com.wanos.commonlibrary.event.SingleLiveEvent;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxInfoRunBackground {
    private static final SingleLiveEvent<String> mRelaxInfoRunBackground;
    public static final LiveData<String> relaxInfoRunBackground;

    static {
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        mRelaxInfoRunBackground = singleLiveEvent;
        relaxInfoRunBackground = singleLiveEvent;
    }

    public static void showRunBackgroundMsg(String str) {
        mRelaxInfoRunBackground.setValue(str);
    }
}
