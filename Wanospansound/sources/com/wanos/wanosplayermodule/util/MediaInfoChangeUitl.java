package com.wanos.wanosplayermodule.util;

import android.text.TextUtils;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SingerInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaInfoChangeUitl {
    public static String getMusicSingerName(List<SingerInfoBean> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        for (int i2 = 0; i2 < list.size(); i2++) {
            SingerInfoBean singerInfoBean = list.get(i2);
            if (singerInfoBean != null) {
                String name = singerInfoBean.getName();
                if (!TextUtils.isEmpty(name)) {
                    i++;
                    if (i == 0) {
                        stringBuffer.append(name);
                    } else {
                        stringBuffer.append("、" + name);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String getSubTitleByInfo(MusicInfoBean musicInfoBean) {
        if (musicInfoBean.getContentType() > 2) {
            return (musicInfoBean.getAlbumList() == null || TextUtils.isEmpty(musicInfoBean.getAlbumList().getAlbumName())) ? "" : musicInfoBean.getAlbumList().getAlbumName();
        }
        return getMusicSingerName(musicInfoBean.getSingerList());
    }
}
