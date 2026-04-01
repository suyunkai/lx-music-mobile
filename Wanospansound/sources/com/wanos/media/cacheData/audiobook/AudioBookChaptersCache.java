package com.wanos.media.cacheData.audiobook;

import android.util.Log;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChaptersCache {
    public static final String TAG = "wanos:[AudioBookChaptersCache]";
    protected static AudioBookChaptersCache instance;
    private int count = 0;
    private final Map<Long, AudioBookChapterItemCache> data = new HashMap();

    public static AudioBookChaptersCache getInstance() {
        if (instance == null) {
            instance = new AudioBookChaptersCache();
        }
        return instance;
    }

    public void onInit() {
        Log.d(TAG, "AudioBookChaptersCache onInit");
        this.count++;
    }

    public void onDestory() {
        Log.d(TAG, "AudioBookChaptersCache onDestory");
        int i = this.count - 1;
        this.count = i;
        if (i == 0) {
            Log.d(TAG, "AudioBookChaptersCache onDestory 2");
            this.data.clear();
        }
    }

    public List<AudioBookChapterItemBean> addAll(long albumId, int page, List<AudioBookChapterItemBean> addList, int pageSize) {
        for (int i = 0; i < addList.size(); i++) {
            addList.get(i).setIndex(((page - 1) * pageSize) + i);
        }
        if (this.data.containsKey(Long.valueOf(albumId))) {
            AudioBookChapterItemCache audioBookChapterItemCache = this.data.get(Long.valueOf(albumId));
            if (page == audioBookChapterItemCache.prePage - 1) {
                audioBookChapterItemCache.addAll(0, addList);
                audioBookChapterItemCache.setPrePage(audioBookChapterItemCache.getPrePage() - 1);
            } else if (page == audioBookChapterItemCache.getNextPage() + 1) {
                audioBookChapterItemCache.addAll(addList);
                audioBookChapterItemCache.setNextPage(audioBookChapterItemCache.getNextPage() + 1);
            } else {
                audioBookChapterItemCache.setPrePage(page);
                audioBookChapterItemCache.setNextPage(page);
                audioBookChapterItemCache.setList(addList);
            }
            return audioBookChapterItemCache.getList();
        }
        this.data.clear();
        AudioBookChapterItemCache audioBookChapterItemCache2 = new AudioBookChapterItemCache(page, page, addList);
        this.data.put(Long.valueOf(albumId), audioBookChapterItemCache2);
        return audioBookChapterItemCache2.getList();
    }

    public List<AudioBookChapterItemBean> replaceAll(long albumId, List<AudioBookChapterItemBean> addList, int nextPage) {
        this.data.clear();
        AudioBookChapterItemCache audioBookChapterItemCache = new AudioBookChapterItemCache(1, nextPage, addList);
        this.data.put(Long.valueOf(albumId), audioBookChapterItemCache);
        return audioBookChapterItemCache.getList();
    }

    public void clearChapterData(long albumId) {
        this.data.remove(Long.valueOf(albumId));
    }

    public List<AudioBookChapterItemBean> getList(long albumId, int page) {
        AudioBookChapterItemCache audioBookChapterItemCache;
        if (!this.data.containsKey(Long.valueOf(albumId)) || (audioBookChapterItemCache = this.data.get(Long.valueOf(albumId))) == null || audioBookChapterItemCache.getPrePage() > page || audioBookChapterItemCache.getNextPage() < page) {
            return null;
        }
        return audioBookChapterItemCache.getList();
    }

    public int getPrePage(long albumId) {
        AudioBookChapterItemCache audioBookChapterItemCache;
        if (!this.data.containsKey(Long.valueOf(albumId)) || (audioBookChapterItemCache = this.data.get(Long.valueOf(albumId))) == null) {
            return 1;
        }
        return audioBookChapterItemCache.getPrePage();
    }

    public int getNextPage(long albumId) {
        AudioBookChapterItemCache audioBookChapterItemCache;
        if (!this.data.containsKey(Long.valueOf(albumId)) || (audioBookChapterItemCache = this.data.get(Long.valueOf(albumId))) == null) {
            return 1;
        }
        return audioBookChapterItemCache.getNextPage();
    }

    public AudioBookChapterItemBean collectChapter(long albumId, long chapterId, int state) {
        AudioBookChapterItemCache audioBookChapterItemCache;
        if (!this.data.containsKey(Long.valueOf(albumId)) || (audioBookChapterItemCache = this.data.get(Long.valueOf(albumId))) == null || audioBookChapterItemCache.getList() == null) {
            return null;
        }
        for (int i = 0; i < audioBookChapterItemCache.getList().size(); i++) {
            AudioBookChapterItemBean audioBookChapterItemBean = audioBookChapterItemCache.getList().get(i);
            if (audioBookChapterItemBean.getId() == chapterId) {
                audioBookChapterItemBean.setIsCollect(state);
                return audioBookChapterItemBean;
            }
        }
        return null;
    }

    public class AudioBookChapterItemCache {
        private List<AudioBookChapterItemBean> list;
        private int nextPage;
        private int prePage;

        public AudioBookChapterItemCache(int prePage, int nextPage, List<AudioBookChapterItemBean> list) {
            this.prePage = prePage;
            this.nextPage = nextPage;
            this.list = list;
        }

        public int getPrePage() {
            return this.prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return this.nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public List<AudioBookChapterItemBean> getList() {
            return this.list;
        }

        public void setList(List<AudioBookChapterItemBean> list) {
            this.list = list;
        }

        public void addAll(List<AudioBookChapterItemBean> addList) {
            List<AudioBookChapterItemBean> list = this.list;
            if (list != null) {
                list.addAll(addList);
            } else {
                this.list = addList;
            }
        }

        public void addAll(int insertIndex, List<AudioBookChapterItemBean> addList) {
            List<AudioBookChapterItemBean> list = this.list;
            if (list != null) {
                list.addAll(insertIndex, addList);
            } else {
                this.list = addList;
            }
        }
    }
}
