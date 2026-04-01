package com.ecarx.eas.sdk.vr.radio;

import com.ecarx.eas.sdk.vr.common.IResponse;
import com.ecarx.eas.sdk.vr.common.MediaCtrlIntent;

/* JADX INFO: loaded from: classes2.dex */
public interface IRadioIntentHandling {
    void handleCtrlApp(MediaCtrlIntent mediaCtrlIntent, IResponse iResponse);

    void handleCtrlRadio(ICtrlLocalRadioIntent iCtrlLocalRadioIntent, IResponse iResponse);
}
