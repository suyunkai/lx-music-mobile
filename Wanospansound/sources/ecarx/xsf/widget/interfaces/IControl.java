package ecarx.xsf.widget.interfaces;

import ecarx.eas.xsf.mediacenter.IMediaListsEx;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.widget.IReceiveWidgetInfoCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IControl {
    void ctrlCancelRecommend();

    int ctrlCollect(int i, boolean z);

    void ctrlNext();

    void ctrlPause();

    void ctrlPauseMediaList(int i);

    void ctrlPlay();

    void ctrlPlayMediaList(int i);

    void ctrlPlayRecommend();

    void ctrlPrevious();

    List<IContent> getContentList();

    IMediaLists getMultiMediaList();

    IMediaListsEx getMultiMediaListEx();

    void init(IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback);

    void selectListMediaPlay(int i, int i2, String str);

    void selectMediaPlay(int i, String str);
}
