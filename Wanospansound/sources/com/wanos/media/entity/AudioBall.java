package com.wanos.media.entity;

import android.util.Log;
import com.wanos.media.entity.ThemeSoundInfoEntity;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.zero.BallEntity;
import com.wanos.zero.BallPosEntity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBall {
    private final String TAG;
    private final AudioDefaultPosition audioDefaultPosition;
    private final ThemeAudioInfoEntity audioInfo;
    private final int ballId;
    private boolean isCustomAudio;
    private boolean isNotifyCustomMove = false;
    private final BallEntity pressureBean;

    public AudioBall(ThemeAudioInfoEntity themeAudioInfoEntity, String str) {
        this.isCustomAudio = false;
        this.TAG = str;
        this.audioInfo = themeAudioInfoEntity;
        ThemeSoundInfoEntity soundInfo = themeAudioInfoEntity.getSoundInfo();
        if (soundInfo != null) {
            this.isCustomAudio = soundInfo.getSpeed() > 0.0f;
        }
        this.ballId = (int) themeAudioInfoEntity.getSoundId();
        this.pressureBean = getBallPlayEntity(themeAudioInfoEntity);
        this.audioDefaultPosition = getDefaultPosition(themeAudioInfoEntity);
    }

    public void setDefaultPosition(float f, float f2, float f3) {
        this.audioDefaultPosition.x = f;
        this.audioDefaultPosition.y = f2;
        this.audioDefaultPosition.z = f3;
    }

    public void setCustomMove() {
        this.isNotifyCustomMove = true;
    }

    public boolean isCustomAudio() {
        return this.isCustomAudio;
    }

    public boolean isNotifyCustomMove() {
        return this.isNotifyCustomMove;
    }

    public int getBallId() {
        return this.ballId;
    }

    public ThemeAudioInfoEntity getAudioInfo() {
        return this.audioInfo;
    }

    public AudioDefaultPosition getAudioDefaultPosition() {
        return this.audioDefaultPosition;
    }

    public BallEntity getPressureBean() {
        return this.pressureBean;
    }

    public AudioInfoEntity getAudioInfoForUI() {
        return getAudioInfoEntity(getBallId(), getAudioInfo(), getAudioDefaultPosition());
    }

    private AudioInfoEntity getAudioInfoEntity(int i, ThemeAudioInfoEntity themeAudioInfoEntity, AudioDefaultPosition audioDefaultPosition) {
        AudioInfoEntity audioInfoEntity = new AudioInfoEntity();
        audioInfoEntity.setId(themeAudioInfoEntity.getSoundId());
        audioInfoEntity.setPlayId(i);
        audioInfoEntity.setAudioIconUrl(themeAudioInfoEntity.getImagePathV2());
        audioInfoEntity.setAudioIconUrlDefault(themeAudioInfoEntity.getImagePath());
        audioInfoEntity.setX(audioDefaultPosition.getX());
        audioInfoEntity.setY(audioDefaultPosition.getY());
        audioInfoEntity.setZ(audioDefaultPosition.getZ());
        audioInfoEntity.setAudioFilePath(MusicCacheUtils.getMusicCachePath(themeAudioInfoEntity.getWanosPath()));
        audioInfoEntity.setAudioUrl(themeAudioInfoEntity.getWanosPath());
        audioInfoEntity.setAudioName(themeAudioInfoEntity.getSoundName());
        audioInfoEntity.setAudioIconBackgroundColor(getAudioBgColor(themeAudioInfoEntity.getTagName()));
        audioInfoEntity.setVocal(themeAudioInfoEntity.isVoice());
        ThemeSoundInfoEntity soundInfo = themeAudioInfoEntity.getSoundInfo();
        if (soundInfo != null) {
            audioInfoEntity.setVolume(soundInfo.getVolume());
        } else {
            audioInfoEntity.setVolume(-20.0f);
        }
        return audioInfoEntity;
    }

    private BallEntity getBallPlayEntity(ThemeAudioInfoEntity themeAudioInfoEntity) {
        if (themeAudioInfoEntity == null) {
            ZeroLogcatTools.e(this.TAG, "getBallPlayEntity: 场景信息异常，ThemeAudioInfoEntity = null");
            return null;
        }
        ThemeSoundInfoEntity soundInfo = themeAudioInfoEntity.getSoundInfo();
        if (soundInfo == null) {
            ZeroLogcatTools.e(this.TAG, "getBallPlayEntity: 音源信息异常，ThemeSoundInfoEntity = null");
            return null;
        }
        if (soundInfo.getBallRouteList().isEmpty()) {
            ZeroLogcatTools.e(this.TAG, "getBallPlayEntity: 轨迹数据异常,该音源缺少轨迹数据");
            return null;
        }
        if (soundInfo.getNumChannels() != 1 && soundInfo.getNumChannels() != 2) {
            ZeroLogcatTools.e(this.TAG, "getBallPlayEntity: 音源非单声道和立体音，请检查音源，NumChannels = " + soundInfo.getNumChannels() + ", 音源名称 = " + themeAudioInfoEntity.getSoundName());
            return null;
        }
        Log.d(this.TAG, "getBallPlayEntity: " + soundInfo.getVolume());
        int soundId = (int) themeAudioInfoEntity.getSoundId();
        BallEntity ballEntity = new BallEntity();
        ballEntity.setBallId(soundId);
        ballEntity.setChannelCount(soundInfo.getNumChannels());
        ballEntity.setAudioPath(MusicCacheUtils.getMusicCachePath(themeAudioInfoEntity.getWanosPath()));
        ballEntity.setVolume(soundInfo.getVolume());
        ballEntity.setSize(soundInfo.getBallRouteList().get(0).getPos().get(3).floatValue());
        ballEntity.setBallPos(getBallRouteList(soundId, soundInfo.getBallRouteList(), soundInfo.getNumChannels()));
        ballEntity.setLoopMode(themeAudioInfoEntity.isVoice() ? 1002 : 1001);
        return ballEntity;
    }

    private AudioDefaultPosition getDefaultPosition(ThemeAudioInfoEntity themeAudioInfoEntity) {
        if (themeAudioInfoEntity == null) {
            ZeroLogcatTools.e(this.TAG, "getDefaultPosition: 场景数据异常");
            return new AudioDefaultPosition();
        }
        ThemeSoundInfoEntity soundInfo = themeAudioInfoEntity.getSoundInfo();
        if (soundInfo == null) {
            ZeroLogcatTools.e(this.TAG, "getDefaultPosition: 音源数据异常");
            return new AudioDefaultPosition();
        }
        List<ThemeSoundInfoEntity.BallRouteList> ballRouteList = soundInfo.getBallRouteList();
        if (ballRouteList == null || ballRouteList.isEmpty()) {
            ZeroLogcatTools.e(this.TAG, "getDefaultPosition: 轨迹数据异常");
            return new AudioDefaultPosition();
        }
        List<Float> pos = ballRouteList.get(0).getPos();
        if (pos == null) {
            return new AudioDefaultPosition();
        }
        if (pos.size() == 4) {
            return new AudioDefaultPosition(pos.get(0).floatValue(), pos.get(1).floatValue(), pos.get(2).floatValue(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, pos.get(3).floatValue());
        }
        if (pos.size() == 12) {
            return new AudioDefaultPosition(pos.get(8).floatValue(), pos.get(9).floatValue(), pos.get(10).floatValue(), pos.get(0).floatValue(), pos.get(1).floatValue(), pos.get(2).floatValue(), pos.get(4).floatValue(), pos.get(5).floatValue(), pos.get(6).floatValue(), pos.get(3).floatValue());
        }
        ZeroLogcatTools.d(this.TAG, "getDefaultPosition: 异常的坐标长度");
        return new AudioDefaultPosition();
    }

    private ArrayList<BallPosEntity> getBallRouteList(int i, List<ThemeSoundInfoEntity.BallRouteList> list, int i2) {
        ArrayList<BallPosEntity> arrayList = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            ZeroLogcatTools.e(this.TAG, "getBallRouteList: 当前音源缺少轨迹信息");
            arrayList.add(new BallPosEntity(i));
        } else {
            for (int i3 = 0; i3 < list.size(); i3++) {
                ThemeSoundInfoEntity.BallRouteList ballRouteList = list.get(i3);
                BallPosEntity ballPosEntity = new BallPosEntity(i);
                ballPosEntity.setSeek(ballRouteList.getSeek());
                if (i2 == 1) {
                    ballPosEntity.setX_m(ballRouteList.getPos().get(0).floatValue());
                    ballPosEntity.setY_m(ballRouteList.getPos().get(1).floatValue());
                    ballPosEntity.setZ_m(ballRouteList.getPos().get(2).floatValue());
                } else {
                    ballPosEntity.setX_l(ballRouteList.getPos().get(0).floatValue());
                    ballPosEntity.setY_l(ballRouteList.getPos().get(1).floatValue());
                    ballPosEntity.setZ_l(ballRouteList.getPos().get(2).floatValue());
                    ballPosEntity.setX_r(ballRouteList.getPos().get(4).floatValue());
                    ballPosEntity.setY_r(ballRouteList.getPos().get(5).floatValue());
                    ballPosEntity.setZ_r(ballRouteList.getPos().get(6).floatValue());
                    ballPosEntity.setX_m(ballRouteList.getPos().get(8).floatValue());
                    ballPosEntity.setY_m(ballRouteList.getPos().get(9).floatValue());
                    ballPosEntity.setZ_m(ballRouteList.getPos().get(10).floatValue());
                }
                arrayList.add(ballPosEntity);
            }
        }
        return arrayList;
    }

    public String getAudioBgColor(String str) {
        return "自然".equals(str) ? "#58A655" : "动物".equals(str) ? "#EAB44C" : "乐器".equals(str) ? "#7978D4" : "城市".equals(str) ? "#1368FB" : "生活".equals(str) ? "#FF8D6A" : "#6494EC";
    }

    public static class AudioDefaultPosition {
        private float size;
        private float x;
        private float xL;
        private float xR;
        private float y;
        private float yL;
        private float yR;
        private float z;
        private float zL;
        private float zR;

        public AudioDefaultPosition() {
        }

        public AudioDefaultPosition(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
            this.x = f;
            this.y = f2;
            this.z = f3;
            this.xL = f4;
            this.yL = f5;
            this.zL = f6;
            this.xR = f7;
            this.yR = f8;
            this.zR = f9;
            this.size = f10;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public float getZ() {
            return this.z;
        }

        public float getxL() {
            return this.xL;
        }

        public float getyL() {
            return this.yL;
        }

        public float getzL() {
            return this.zL;
        }

        public float getxR() {
            return this.xR;
        }

        public float getyR() {
            return this.yR;
        }

        public float getzR() {
            return this.zR;
        }

        public float getSize() {
            return this.size;
        }

        public String toString() {
            return "AudioDefaultPosition{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", xL=" + this.xL + ", yL=" + this.yL + ", zL=" + this.zL + ", xR=" + this.xR + ", yR=" + this.yR + ", zR=" + this.zR + ", size=" + this.size + '}';
        }
    }
}
