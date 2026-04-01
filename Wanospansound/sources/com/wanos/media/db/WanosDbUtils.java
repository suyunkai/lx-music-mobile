package com.wanos.media.db;

import android.os.Handler;
import android.os.Looper;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.media.db.entity.music.DbAudioBookInfo;
import com.wanos.media.db.entity.music.DbMusicInfo;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public class WanosDbUtils {
    public static final String TAG = "wanos:[WanosDbUtils]";
    private static final ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
    private static final MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            this.handler.post(r);
        }
    }

    public static void getMusicHistoryList(final DbCallBack<List<MusicInfoBean>> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.1
            /* JADX WARN: Removed duplicated region for block: B:44:0x0101  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 459
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.db.WanosDbUtils.AnonymousClass1.run():void");
            }
        });
    }

    public static void updateMusicHistory(final MusicInfoBean mediaMusicBean, final DbCallBack<Boolean> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.2
            @Override // java.lang.Runnable
            public void run() {
                final boolean z;
                DbMusicInfo dbMusicInfoFromMusicToDbMusic = DbInfoChangeBeanUtils.fromMusicToDbMusic(mediaMusicBean);
                if (dbMusicInfoFromMusicToDbMusic != null) {
                    WanosDatabase.getInstance().musicDao().insertOrUpdateDbMusicInfo(dbMusicInfoFromMusicToDbMusic);
                    z = true;
                } else {
                    z = false;
                }
                WanosDbUtils.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (callBack != null) {
                            callBack.callBackData(Boolean.valueOf(z));
                        }
                    }
                });
            }
        });
    }

    public static void deleteMusicHistory(final MusicInfoBean mediaMusicBean, final DbCallBack<Boolean> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.3
            @Override // java.lang.Runnable
            public void run() {
                final boolean z;
                DbMusicInfo dbMusicInfoFromMusicToDbMusic = DbInfoChangeBeanUtils.fromMusicToDbMusic(mediaMusicBean);
                if (dbMusicInfoFromMusicToDbMusic != null) {
                    WanosDatabase.getInstance().musicDao().deleteDbMusicInfo(dbMusicInfoFromMusicToDbMusic);
                    z = true;
                } else {
                    z = false;
                }
                WanosDbUtils.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (callBack != null) {
                            callBack.callBackData(Boolean.valueOf(z));
                        }
                    }
                });
            }
        });
    }

    public static void getAudioBookHistoryList(final DbCallBack<List<AudioBookMineChapterItemBean>> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.4
            @Override // java.lang.Runnable
            public void run() {
                final List<DbAudioBookInfo> listQueryAllDbDbAudioBookInfoInfo = WanosDatabase.getInstance().audioBookDao().queryAllDbDbAudioBookInfoInfo();
                if (listQueryAllDbDbAudioBookInfoInfo.size() > 100) {
                    WanosDatabase.getInstance().audioBookDao().deleteDbAudioBookInfoList(listQueryAllDbDbAudioBookInfoInfo.subList(100, listQueryAllDbDbAudioBookInfoInfo.size()));
                    listQueryAllDbDbAudioBookInfoInfo = listQueryAllDbDbAudioBookInfoInfo.subList(0, 100);
                }
                WanosDbUtils.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (callBack != null) {
                            callBack.callBackData(DbInfoChangeBeanUtils.fromDbAudioBookListToAudioBookList(listQueryAllDbDbAudioBookInfoInfo));
                        }
                    }
                });
            }
        });
    }

    public static void updateAudioBookHistory(final AudioBookMineChapterItemBean bean, final DbCallBack<Boolean> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.5
            @Override // java.lang.Runnable
            public void run() {
                final boolean z;
                DbAudioBookInfo dbAudioBookInfoFromAudioBookToDbAudioBook = DbInfoChangeBeanUtils.fromAudioBookToDbAudioBook(bean);
                if (dbAudioBookInfoFromAudioBookToDbAudioBook != null) {
                    WanosDatabase.getInstance().audioBookDao().insertOrUpdateDbAudioBookInfo(dbAudioBookInfoFromAudioBookToDbAudioBook);
                    z = true;
                } else {
                    z = false;
                }
                WanosDbUtils.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (callBack != null) {
                            callBack.callBackData(Boolean.valueOf(z));
                        }
                    }
                });
            }
        });
    }

    public static void getAudioBookHistoryItem(final long albumId, final DbCallBack<AudioBookMineChapterItemBean> callBack) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.6
            @Override // java.lang.Runnable
            public void run() {
                final DbAudioBookInfo dbAudioBookInfoQueryDbAudioBookInfoBean = WanosDatabase.getInstance().audioBookDao().queryDbAudioBookInfoBean(albumId);
                WanosDbUtils.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.media.db.WanosDbUtils.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (callBack != null) {
                            callBack.callBackData(DbInfoChangeBeanUtils.fromDbAudioBookToAudioBook(dbAudioBookInfoQueryDbAudioBookInfoBean));
                        }
                    }
                });
            }
        });
    }
}
