package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.QAListItem;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface QuestionView extends IBaseView {
    void updateQAList(boolean isRefresh, List<QAListItem> list);
}
