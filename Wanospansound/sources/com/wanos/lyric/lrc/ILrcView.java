package com.wanos.lyric.lrc;

import com.wanos.lyric.lrc.impl.LrcRow;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ILrcView {
    void seekLrcToTime(long j);

    void setListener(ILrcViewListener iLrcViewListener);

    void setLoadingTipText(String str);

    void setLrc(List<LrcRow> list);
}
