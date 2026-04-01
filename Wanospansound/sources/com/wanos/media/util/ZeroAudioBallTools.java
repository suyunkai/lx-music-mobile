package com.wanos.media.util;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.entity.AudioBall;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.entity.ThemeSoundInfoEntity;
import com.wanos.media.services.MediaPlayerServices;
import com.wanos.zero.IAudioFrameCallback;
import com.wanos.zero.ZeroMediaTools;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioBallTools {
    private static final ZeroAudioBallTools INSTANCES = new ZeroAudioBallTools();
    private static final String TAG = "ZeroAudioBallTools";
    private IAudioBallListener iAudioBallListener;
    private ZeroMediaTools mZeroMediaTools;
    private final SparseArray<AudioBall> AUDIO_BALL = new SparseArray<>();
    private boolean isEnterPersonalization = false;
    private final ServiceConnection mServiceConnection = new AnonymousClass1();

    public interface IAudioBallListener {
        public static final int INSERT_TYPE_INIT = 100;
        public static final int INSERT_TYPE_LIBRARY = 101;

        @Retention(RetentionPolicy.SOURCE)
        public @interface InsertType {
        }

        void onBallAutoMove(int i, ThemeSoundInfoEntity themeSoundInfoEntity);

        void onBallDelete(AudioInfoEntity audioInfoEntity);

        void onBallInsert(AudioInfoEntity audioInfoEntity, int i);

        void onBallMove(int i, float f, float f2, float f3);
    }

    public interface ILoadingCallback {
        void onAudioReady();
    }

    /* JADX INFO: renamed from: com.wanos.media.util.ZeroAudioBallTools$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(ZeroAudioBallTools.TAG, "onServiceConnected: " + componentName);
            if (iBinder instanceof MediaPlayerServices.LocalBinder) {
                MediaPlayerServices services = ((MediaPlayerServices.LocalBinder) iBinder).getServices();
                if (services == null) {
                    Log.d(ZeroAudioBallTools.TAG, "onServiceConnected: MediaPlayerServices == NULL");
                    return;
                }
                ZeroAudioBallTools.this.mZeroMediaTools = services.getZeroMediaTools();
                if (ZeroAudioBallTools.this.mZeroMediaTools != null) {
                    ZeroAudioBallTools.this.isEnterPersonalization = false;
                    if (ZeroAudioBallTools.this.AUDIO_BALL.size() != 0) {
                        ZeroAudioBallTools.this.AUDIO_BALL.clear();
                    }
                    ZeroAudioBallTools.this.mZeroMediaTools.setMediaFrameCallback(new IAudioFrameCallback() { // from class: com.wanos.media.util.ZeroAudioBallTools$1$$ExternalSyntheticLambda0
                        @Override // com.wanos.zero.IAudioFrameCallback
                        public final void onAudioFrame(float[][] fArr, int i) {
                            this.f$0.m568x4b350a84(fArr, i);
                        }
                    });
                    return;
                }
                Log.e(ZeroAudioBallTools.TAG, "onServiceConnected: 播放控制对象获取失败");
            }
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$0$com-wanos-media-util-ZeroAudioBallTools$1, reason: not valid java name */
        /* synthetic */ void m568x4b350a84(float[][] fArr, int i) {
            ThemeSoundInfoEntity soundInfo;
            for (int i2 = 0; i2 < i; i2++) {
                float[] fArr2 = fArr[i2];
                int i3 = (int) fArr2[0];
                AudioBall audioBall = (AudioBall) ZeroAudioBallTools.this.AUDIO_BALL.get(i3);
                if (audioBall != null) {
                    audioBall.setDefaultPosition(fArr2[1], fArr2[2], fArr2[3]);
                    if (ZeroAudioBallTools.this.isEnterPersonalization && ZeroAudioBallTools.this.iAudioBallListener != null && (soundInfo = audioBall.getAudioInfo().getSoundInfo()) != null) {
                        if (audioBall.isCustomAudio()) {
                            ZeroAudioBallTools.this.iAudioBallListener.onBallAutoMove(i3, soundInfo);
                        } else {
                            ZeroAudioBallTools.this.iAudioBallListener.onBallMove(i3, fArr2[1], fArr2[2], fArr2[3]);
                        }
                    }
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(ZeroAudioBallTools.TAG, "onServiceDisconnected: " + componentName);
            ZeroAudioBallTools.this.mZeroMediaTools = null;
        }
    }

    public static ZeroAudioBallTools getInstance() {
        return INSTANCES;
    }

    public void onActivityCreate() {
        Utils.getApp().bindService(new Intent(Utils.getApp(), (Class<?>) MediaPlayerServices.class), this.mServiceConnection, 1);
    }

    public void onActivityDestroy() {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools != null) {
            try {
                try {
                    zeroMediaTools.onZeroActivityDestroy();
                    Utils.getApp().unbindService(this.mServiceConnection);
                } catch (Exception e) {
                    ZeroLogcatTools.w(TAG, "onActivityDestroy: " + e);
                }
            } finally {
                this.mZeroMediaTools = null;
            }
        }
    }

    public void onPersonalizationCreate(IAudioBallListener iAudioBallListener) {
        ZeroLogcatTools.d(TAG, "onPersonalizationCreate: 个性化模式初始化");
        this.isEnterPersonalization = true;
        this.iAudioBallListener = iAudioBallListener;
        for (int i = 0; i < this.AUDIO_BALL.size(); i++) {
            iAudioBallListener.onBallInsert(this.AUDIO_BALL.valueAt(i).getAudioInfoForUI(), 100);
        }
    }

    public void onPersonalizationDestroy() {
        ZeroLogcatTools.d(TAG, "onPersonalizationDestroy: 个性化模式销毁");
        this.isEnterPersonalization = false;
        this.iAudioBallListener = null;
    }

    public float[] getBallCurrentPos(int i) {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            return null;
        }
        return zeroMediaTools.getBallCurrentPos(i);
    }

    public void initScene(final List<ThemeAudioInfoEntity> list, boolean z, final ILoadingCallback iLoadingCallback) {
        if (list == null || list.isEmpty()) {
            ZeroLogcatTools.e(TAG, "initScene: 音源添加失败，audioInfo = null 获取 长度为 0");
            return;
        }
        ZeroLogcatTools.d(TAG, "initScene: 当前场景音源数量 = " + this.AUDIO_BALL.size() + "| 待初始化场景音源数量 = " + list.size());
        if (this.AUDIO_BALL.size() > 0) {
            List<Long> sceneSoundIds = getSceneSoundIds();
            for (int i = 0; i < sceneSoundIds.size(); i++) {
                deleteBall(sceneSoundIds.get(i).longValue());
            }
        }
        ThreadUtils.executeByCpu(new ThreadUtils.SimpleTask<List<AudioBall>>() { // from class: com.wanos.media.util.ZeroAudioBallTools.2
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<AudioBall> doInBackground() {
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    ThemeAudioInfoEntity themeAudioInfoEntity = (ThemeAudioInfoEntity) list.get(i3);
                    if (themeAudioInfoEntity == null) {
                        ZeroLogcatTools.e(ZeroAudioBallTools.TAG, "音源错误");
                    } else {
                        AudioBall audioBall = new AudioBall(themeAudioInfoEntity, ZeroAudioBallTools.TAG);
                        if (audioBall.getPressureBean() != null) {
                            arrayList.add(audioBall);
                        }
                    }
                }
                int playState = ZeroAudioBallTools.this.getPlayState();
                while (playState != 100 && playState != 103 && i2 <= 5) {
                    try {
                        ZeroLogcatTools.d(ZeroAudioBallTools.TAG, "initScene: 零压空间播放服务未准备就绪，等待中...");
                        TimeUnit.MILLISECONDS.sleep(80L);
                        i2++;
                        playState = ZeroAudioBallTools.this.getPlayState();
                    } catch (InterruptedException unused) {
                        return Collections.emptyList();
                    }
                }
                return arrayList;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<AudioBall> list2) {
                ZeroLogcatTools.d(ZeroAudioBallTools.TAG, "initScene: 音源数据装箱完成，Size = " + list2.size());
                if (ZeroAudioBallTools.this.mZeroMediaTools == null) {
                    ZeroLogcatTools.e(ZeroAudioBallTools.TAG, "initScene: 零压空间播放服务未启动，场景初始化失败");
                    return;
                }
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    AudioBall audioBall = list2.get(i2);
                    ZeroAudioBallTools.this.AUDIO_BALL.put(audioBall.getBallId(), audioBall);
                    ZeroAudioBallTools.this.mZeroMediaTools.onMediaInsertBall(audioBall.getPressureBean());
                    if (ZeroAudioBallTools.this.iAudioBallListener != null) {
                        ZeroAudioBallTools.this.iAudioBallListener.onBallInsert(audioBall.getAudioInfoForUI(), 100);
                    }
                }
                ZeroLogcatTools.d(ZeroAudioBallTools.TAG, "initScene: 音源场景初始化完成，初始化音源数量 = " + list2.size());
                ILoadingCallback iLoadingCallback2 = iLoadingCallback;
                if (iLoadingCallback2 != null) {
                    iLoadingCallback2.onAudioReady();
                }
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                com.wanos.commonlibrary.utils.ToastUtil.showMsg(th.getMessage());
            }
        });
    }

    public void initScene(List<ThemeAudioInfoEntity> list, ILoadingCallback iLoadingCallback) {
        initScene(list, false, iLoadingCallback);
    }

    public void modifyAudioXY(int i, float f, float f2) {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，XY坐标设置失败");
        } else {
            zeroMediaTools.onMediaModifyXY(i, f, f2);
        }
    }

    public void modifyAudioZ(int i, float f) {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，Z坐标设置失败");
        } else {
            zeroMediaTools.onMediaModifyZ(i, f);
        }
    }

    public void modifyVolume(int i, float f) {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，音量设置失败");
        } else {
            zeroMediaTools.onMediaVolume(i, f);
        }
    }

    public void deleteBall(long j) {
        for (int i = 0; i < this.AUDIO_BALL.size(); i++) {
            AudioBall audioBallValueAt = this.AUDIO_BALL.valueAt(i);
            if (audioBallValueAt.getAudioInfo().getSoundId() == j) {
                deleteBall(audioBallValueAt.getBallId());
                return;
            }
        }
    }

    public void deleteBall(int i) {
        AudioBall audioBall = this.AUDIO_BALL.get(i);
        if (audioBall == null) {
            ZeroLogcatTools.e(TAG, "deleteBall: 音源不存在");
            return;
        }
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，音源删除失败");
            return;
        }
        zeroMediaTools.onMediaDeleteBall(i);
        this.AUDIO_BALL.remove(i);
        IAudioBallListener iAudioBallListener = this.iAudioBallListener;
        if (iAudioBallListener != null) {
            iAudioBallListener.onBallDelete(audioBall.getAudioInfoForUI());
        }
    }

    public void insertBall(final ThemeAudioInfoEntity themeAudioInfoEntity) {
        if (themeAudioInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "insertBall: 音源添加失败，audioInfo = null");
        } else {
            ThreadUtils.executeByCpu(new ThreadUtils.SimpleTask<AudioBall>() { // from class: com.wanos.media.util.ZeroAudioBallTools.3
                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public AudioBall doInBackground() throws Throwable {
                    return new AudioBall(themeAudioInfoEntity, ZeroAudioBallTools.TAG);
                }

                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public void onSuccess(AudioBall audioBall) {
                    if (audioBall.getPressureBean() != null) {
                        if (ZeroAudioBallTools.this.mZeroMediaTools != null) {
                            ZeroAudioBallTools.this.AUDIO_BALL.put(audioBall.getBallId(), audioBall);
                            ZeroAudioBallTools.this.mZeroMediaTools.onMediaInsertBall(audioBall.getPressureBean());
                            if (ZeroAudioBallTools.this.iAudioBallListener != null) {
                                ZeroAudioBallTools.this.iAudioBallListener.onBallInsert(audioBall.getAudioInfoForUI(), 101);
                            }
                            ZeroLogcatTools.d(ZeroAudioBallTools.TAG, "insertBall: 添加音源:" + audioBall.getBallId());
                            return;
                        }
                        ZeroLogcatTools.e(ZeroAudioBallTools.TAG, "initScene: 零压空间播放服务未启动，音源添加失败");
                        return;
                    }
                    ZeroLogcatTools.e(ZeroAudioBallTools.TAG, "insertBall: 音源添加失败，构建播放实体错误");
                }
            });
        }
    }

    public List<Long> getSceneSoundIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.AUDIO_BALL.size(); i++) {
            arrayList.add(Long.valueOf(this.AUDIO_BALL.valueAt(i).getAudioInfo().getSoundId()));
        }
        return arrayList;
    }

    public AudioBall getAudioInfo(int i) {
        return this.AUDIO_BALL.get(i, null);
    }

    public void onMediaStart(int i) {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，播放器启动失败");
        } else {
            zeroMediaTools.onMediaStart();
            ZeroLogcatTools.d(TAG, "onMediaStart: 启动播放器 i = " + i);
        }
    }

    public void onMediaReplay() {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，播放器重新播放失败");
        } else {
            zeroMediaTools.onMediaReplay();
        }
    }

    public void onMediaReset() {
        if (this.AUDIO_BALL.size() != 0) {
            this.AUDIO_BALL.clear();
        }
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，播放器重置播放失败");
        } else {
            zeroMediaTools.onMediaReset();
            ZeroLogcatTools.d(TAG, "onMediaReset: 重置播放器");
        }
    }

    public void onMediaStop(int i) {
        ZeroLogcatTools.d(TAG, "onMediaStop: 停止播放器 i = " + i);
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            ZeroLogcatTools.e(TAG, "initScene: 零压空间播放服务未启动，播放器暂停失败");
        } else {
            zeroMediaTools.onMediaStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPlayState() {
        ZeroMediaTools zeroMediaTools = this.mZeroMediaTools;
        if (zeroMediaTools == null) {
            return 100;
        }
        return zeroMediaTools.getPlayState();
    }
}
