package com.ecarx.eas.sdk.mediacenter.control;

import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMediaControlClient {
    public abstract List<IMediaContentType> getMediaContentTypeList();

    public abstract void onControlledChanged(String str);

    public abstract boolean onPause(int i);

    public abstract boolean onPauseNow();

    public abstract boolean onPlay(int i, String str);

    public abstract boolean onPlayByContent(int i, String str);

    public abstract boolean onPlayByMediaId(int i, String str);

    public abstract boolean onResumeNow();
}
