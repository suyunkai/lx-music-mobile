package com.ecarx.eas.sdk.mediacenter.control;

import com.ecarx.eas.sdk.mediacenter.control.bean.Media;
import com.ecarx.eas.sdk.mediacenter.control.bean.MusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IMediaControllerAPI {
    List<IMediaContentType> getMediaContentTypeList(Object obj);

    List<Media> getMediaList(Object obj);

    MusicPlaybackInfo getMusicPlaybackInfo(Object obj);

    int getSourceType(Object obj);

    boolean playCtlPlay(Object obj, int i, String str);

    boolean playCtrlPause(Object obj);

    boolean playCtrlPause(Object obj, int i);

    boolean playCtrlPlay(Object obj);

    boolean playCtrlPlayByContent(Object obj, int i, String str);

    boolean playCtrlPlayByMediaID(Object obj, int i, String str);

    Object register(String str, MediaController mediaController);

    boolean requestControl(Object obj);

    boolean unregister(Object obj);
}
