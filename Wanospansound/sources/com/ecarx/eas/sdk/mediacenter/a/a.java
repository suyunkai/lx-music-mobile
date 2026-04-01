package com.ecarx.eas.sdk.mediacenter.a;

import com.ecarx.eas.sdk.vr.common.MediaCtrlAction;
import com.ecarx.eas.sdk.vr.common.MediaCtrlIntent;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends MediaCtrlIntent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f108b;

    public a(int i) {
        this.f108b = -1;
        this.f107a = i;
    }

    public a(int i, int i2) {
        this.f107a = i2;
        this.f108b = i;
    }

    @Override // com.ecarx.eas.sdk.vr.common.MediaCtrlIntent
    public final int getSourceType() {
        return this.f108b;
    }

    @Override // com.ecarx.eas.sdk.vr.common.MediaCtrlIntent
    public final MediaCtrlAction getAction() {
        if (this.f107a >= MediaCtrlAction.OPEN.ordinal() && this.f107a <= MediaCtrlAction.EXIT.ordinal()) {
            return MediaCtrlAction.values()[this.f107a];
        }
        return null;
    }
}
