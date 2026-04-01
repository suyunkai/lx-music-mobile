package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetAudioBookChapterListIsLikeResponse extends BaseResponse {
    public AudioBookChapterListIsLikeBean data;

    public class AudioBookChapterListIsLikeBean {
        public List<AudioBookChapterIsLikeBean> list;

        public AudioBookChapterListIsLikeBean() {
        }
    }

    public class AudioBookChapterIsLikeBean {
        public long chapterId;
        public int isCollect;

        public AudioBookChapterIsLikeBean() {
        }
    }
}
