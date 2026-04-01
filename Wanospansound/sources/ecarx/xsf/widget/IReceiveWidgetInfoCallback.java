package ecarx.xsf.widget;

import ecarx.eas.xsf.mediacenter.IMediaListsEx;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.IRecommend;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IReceiveWidgetInfoCallback {
    void updateCollectMsg(int i, String str);

    void updateMediaContent(List<IContent> list);

    void updateMediaList(int i, int i2, List<IMedia> list);

    void updateMultiMediaListEx(IMediaListsEx iMediaListsEx);

    void updateMusicPlayInfo(IMusicPlaybackInfo iMusicPlaybackInfo);

    void updateProgress(long j);

    void updateRecommendInfo(IRecommend iRecommend);
}
