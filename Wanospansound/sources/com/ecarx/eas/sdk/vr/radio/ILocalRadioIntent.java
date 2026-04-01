package com.ecarx.eas.sdk.vr.radio;

import com.ecarx.eas.sdk.radio.Band;

/* JADX INFO: loaded from: classes2.dex */
public interface ILocalRadioIntent {
    Band getBand();

    String getCategory();

    int getFrequency();

    String getLocation();

    int getMediaType();

    String getName();

    int getSourceType();

    int getTargetPlayType();
}
