package com.wanos.media.widget.ball;

import android.graphics.Color;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.DefaultCacheFileEntity;
import com.wanos.media.util.ImageLoadTools;
import com.wanos.media.util.PictureCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroBallMate extends Thread {
    private static final String TAG = "ZeroBallMate";
    private final IOpenGlBallChangeListener iOpenGlBallChangeListener;
    private final ArrayBlockingQueue<BallMessage> mEnterQueue;
    private final ArrayBlockingQueue<OpenGlEntity> mExitQueue;
    public static final String FILE_DEFAULT_ICON = "icon_default_audio.PNG";
    private static final DefaultCacheFileEntity DEFAULT_AUDIO_ICON = new DefaultCacheFileEntity(R.drawable.icon_default_audio, FILE_DEFAULT_ICON);
    private static final String FILE_VOCAL_ICON = "icon_vocal.PNG";
    private static final DefaultCacheFileEntity DEFAULT_VOCAL_ICON = new DefaultCacheFileEntity(R.drawable.icon_vocal, FILE_VOCAL_ICON);
    public static final String FILE_BREATHE = "icon_breath.PNG";
    public static final DefaultCacheFileEntity DEFAULT_BREATHE_SRC = new DefaultCacheFileEntity(R.drawable.number_picker_bg, FILE_BREATHE);
    public static final String FILE_VIEW = "icon_view.PNG";
    public static final DefaultCacheFileEntity DEFAULT_SELF_SRC = new DefaultCacheFileEntity(R.drawable.src_zero_self, FILE_VIEW);

    public interface IOpenGlBallChangeListener {
        void onOpenGLDelete(int i);

        void onOpenGlInsertInfoMate(AudioInfoEntity audioInfoEntity);

        void onOpenGlInsertMoveHelp(OpenGlEntity openGlEntity);
    }

    public ZeroBallMate(IOpenGlBallChangeListener iOpenGlBallChangeListener) {
        super(TAG);
        this.mEnterQueue = new ArrayBlockingQueue<>(50);
        this.mExitQueue = new ArrayBlockingQueue<>(50);
        this.iOpenGlBallChangeListener = iOpenGlBallChangeListener;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        BallMessage ballMessageTake;
        super.run();
        while (true) {
            try {
                ballMessageTake = this.mEnterQueue.take();
            } catch (InterruptedException unused) {
                ZeroLogcatTools.e(TAG, "线程异常中断");
                ballMessageTake = null;
            }
            if (ballMessageTake == null) {
                ZeroLogcatTools.e(TAG, "获取到异常元素，Task=NULL");
            } else {
                switch (ballMessageTake.getType()) {
                    case 101:
                        insertBall(ballMessageTake.getInsertEntity());
                        break;
                    case 102:
                        deleteBall(ballMessageTake.getBallId());
                        break;
                    case 103:
                        ZeroLogcatTools.d(TAG, "跳出-ZeroBallMate-线程");
                        return;
                }
            }
        }
    }

    private void insertBall(AudioInfoEntity audioInfoEntity) {
        String imageCachePath;
        if (audioInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "添加小球错误，Task未携带AudioInfoEntity信息");
            return;
        }
        float x = audioInfoEntity.getX();
        float y = audioInfoEntity.getY();
        float z = audioInfoEntity.getZ();
        int color = Color.parseColor(audioInfoEntity.getAudioIconBackgroundColor());
        float fRed = Color.red(color) / 255.0f;
        float fGreen = Color.green(color) / 255.0f;
        float fBlue = Color.blue(color) / 255.0f;
        float fAlpha = Color.alpha(color) / 255.0f;
        if (audioInfoEntity.isVocal()) {
            imageCachePath = PictureCacheUtils.getDefaultLocalImage(DEFAULT_VOCAL_ICON);
        } else {
            imageCachePath = PictureCacheUtils.getImageCachePath(audioInfoEntity.getAudioIconUrl());
            if (!FileUtils.isFileExists(imageCachePath) || !ImageUtils.isImage(imageCachePath)) {
                imageCachePath = PictureCacheUtils.toLocalImage(ImageLoadTools.formatImagePath(audioInfoEntity.getAudioIconUrl(), 200, 200, true), DEFAULT_AUDIO_ICON);
            }
        }
        if (this.mExitQueue.offer(new OpenGlEntity(101, audioInfoEntity.getPlayId(), imageCachePath, fRed, fGreen, fBlue, fAlpha, x, y, z, audioInfoEntity))) {
            return;
        }
        ZeroLogcatTools.e(TAG, "OpenGl添加小球失败，小球ID=" + audioInfoEntity.getPlayId());
    }

    private void deleteBall(int i) {
        if (this.mExitQueue.offer(new OpenGlEntity(102, i))) {
            return;
        }
        ZeroLogcatTools.e(TAG, "OpenGl删除小球失败，小球ID=" + i);
    }

    public ArrayBlockingQueue<OpenGlEntity> getQueue() {
        return this.mExitQueue;
    }

    public void onInsertBall(AudioInfoEntity audioInfoEntity) {
        BallMessage ballMessage = new BallMessage();
        ballMessage.setType(101);
        ballMessage.setInsertEntity(audioInfoEntity);
        this.mEnterQueue.offer(ballMessage);
    }

    public void onDeleteBall(int i) {
        BallMessage ballMessage = new BallMessage();
        ballMessage.setType(102);
        ballMessage.setBallId(i);
        this.mEnterQueue.offer(ballMessage);
    }

    public void onViewDestroy() {
        BallMessage ballMessage = new BallMessage();
        ballMessage.setType(103);
        this.mEnterQueue.offer(ballMessage);
    }

    public static class OpenGlEntity {
        private float audioCx;
        private float audioCy;
        private float audioCz;
        private float ballA;
        private float ballB;
        private float ballG;
        private final int ballId;
        private float ballR;
        private AudioInfoEntity entity;
        private String iconLocalPath;
        private final int type;

        public OpenGlEntity(int i, int i2, String str, float f, float f2, float f3, float f4, float f5, float f6, float f7, AudioInfoEntity audioInfoEntity) {
            this.type = i;
            this.ballId = i2;
            this.iconLocalPath = str;
            this.ballR = f;
            this.ballG = f2;
            this.ballB = f3;
            this.ballA = f4;
            this.audioCx = f5;
            this.audioCy = f6;
            this.audioCz = f7;
            this.entity = audioInfoEntity;
        }

        public AudioInfoEntity getEntity() {
            return this.entity;
        }

        public int getType() {
            return this.type;
        }

        public int getBallId() {
            return this.ballId;
        }

        public String getIconLocalPath() {
            return this.iconLocalPath;
        }

        public float getBallR() {
            return this.ballR;
        }

        public float getBallG() {
            return this.ballG;
        }

        public float getBallB() {
            return this.ballB;
        }

        public float getBallA() {
            return this.ballA;
        }

        public float getAudioCx() {
            return this.audioCx;
        }

        public float getAudioCy() {
            return this.audioCy;
        }

        public float getAudioCz() {
            return this.audioCz;
        }

        public OpenGlEntity(int i, int i2) {
            this.type = i;
            this.ballId = i2;
        }
    }

    public static class BallMessage {
        public static final int MSG_TYPE_DELETE = 102;
        public static final int MSG_TYPE_DESTROY = 103;
        public static final int MSG_TYPE_INSERT = 101;
        private int ballId;
        private AudioInfoEntity insertEntity;
        private int type;

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public AudioInfoEntity getInsertEntity() {
            return this.insertEntity;
        }

        public void setInsertEntity(AudioInfoEntity audioInfoEntity) {
            this.insertEntity = audioInfoEntity;
        }

        public int getBallId() {
            return this.ballId;
        }

        public void setBallId(int i) {
            this.ballId = i;
        }
    }
}
