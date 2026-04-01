package com.ecarx.eas.sdk.mediacenter.control;

import com.ecarx.eas.sdk.mediacenter.control.bean.Media;
import com.ecarx.eas.sdk.mediacenter.control.bean.MusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaController extends AbstractMediaController {
    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void onControllerChanged(String str) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void updateCurrentProgress(long j) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void updateErrorMsg(int i, String str) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void updateMediaContentTypeList(List<IMediaContentType> list) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void updatePlaybackInfo(MusicPlaybackInfo musicPlaybackInfo) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.AbstractMediaController
    public void updatePlaylist(int i, List<Media> list) {
    }
}
