package com.ecarx.eas.sdk.mediacenter.control;

import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaControlClient extends AbstractMediaControlClient {
    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public List<IMediaContentType> getMediaContentTypeList() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public void onControlledChanged(String str) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onPause(int i) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onPauseNow() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onPlay(int i, String str) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onPlayByContent(int i, String str) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onPlayByMediaId(int i, String str) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaControlClient
    public boolean onResumeNow() {
        return false;
    }
}
