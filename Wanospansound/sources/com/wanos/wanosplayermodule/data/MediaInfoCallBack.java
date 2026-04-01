package com.wanos.wanosplayermodule.data;

import com.wanos.commonlibrary.bean.MediaInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaInfoCallBack {
    void mediaInfoUpdateSuccessful();

    void returnQueryMediaInfoList(List<MediaInfo> list);
}
