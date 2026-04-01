package com.wanos.media.cacheData.audiobook;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumsCache {
    private static AudioBookAlbumsCache instance;
    private final Map<Long, AudioBookAlbumInfoBean> audioBookAlbumInfoBeanMap = new HashMap();

    public static AudioBookAlbumsCache getInstance() {
        if (instance == null) {
            instance = new AudioBookAlbumsCache();
        }
        return instance;
    }

    public void addAll(List<AudioBookAlbumInfoBean> list) {
        for (int i = 0; i < list.size(); i++) {
            AudioBookAlbumInfoBean audioBookAlbumInfoBean = list.get(i);
            this.audioBookAlbumInfoBeanMap.put(Long.valueOf(audioBookAlbumInfoBean.getId()), audioBookAlbumInfoBean);
        }
    }

    public void add(AudioBookAlbumInfoBean bean) {
        this.audioBookAlbumInfoBeanMap.put(Long.valueOf(bean.getId()), bean);
    }

    public AudioBookAlbumInfoBean getAlbumBean(long id) {
        if (this.audioBookAlbumInfoBeanMap.containsKey(Long.valueOf(id))) {
            return this.audioBookAlbumInfoBeanMap.get(Long.valueOf(id));
        }
        return null;
    }

    public AudioBookAlbumInfoBean collectAlbum(long id, int state) {
        if (!this.audioBookAlbumInfoBeanMap.containsKey(Long.valueOf(id))) {
            return null;
        }
        AudioBookAlbumInfoBean audioBookAlbumInfoBean = this.audioBookAlbumInfoBeanMap.get(Long.valueOf(id));
        audioBookAlbumInfoBean.setIsCollect(state);
        return audioBookAlbumInfoBean;
    }
}
