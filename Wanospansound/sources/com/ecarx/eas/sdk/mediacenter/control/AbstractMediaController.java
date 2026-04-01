package com.ecarx.eas.sdk.mediacenter.control;

import com.ecarx.eas.sdk.mediacenter.control.bean.Media;
import com.ecarx.eas.sdk.mediacenter.control.bean.MusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMediaController {
    public abstract void onControllerChanged(String str);

    public abstract void updateCurrentProgress(long j);

    public abstract void updateErrorMsg(int i, String str);

    public abstract void updateMediaContentTypeList(List<IMediaContentType> list);

    public abstract void updatePlaybackInfo(MusicPlaybackInfo musicPlaybackInfo);

    public abstract void updatePlaylist(int i, List<Media> list);
}
