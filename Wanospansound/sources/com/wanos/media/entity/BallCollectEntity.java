package com.wanos.media.entity;

import com.wanos.media.entity.AudioBall;
import com.wanos.media.util.ZeroAudioBallTools;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BallCollectEntity {
    private final int isVocal;
    private final int modify;
    private final SoundDetail soundDetail;
    private final long soundId;

    public BallCollectEntity(AudioBall audioBall, int i, float f, float f2, float f3) {
        int numChannels;
        float f4;
        ThemeAudioInfoEntity audioInfo = audioBall.getAudioInfo();
        ThemeSoundInfoEntity soundInfo = audioInfo.getSoundInfo();
        if (soundInfo == null) {
            f4 = f - 20.0f;
            numChannels = 1;
        } else {
            float volume = soundInfo.getVolume();
            numChannels = soundInfo.getNumChannels();
            f4 = volume;
        }
        this.soundId = audioInfo.getSoundId();
        this.modify = i;
        this.isVocal = audioBall.getAudioInfo().isVoice() ? 1 : 0;
        AudioBall.AudioDefaultPosition audioDefaultPosition = audioBall.getAudioDefaultPosition();
        if (i == 0 && audioInfo.getModify() == 0) {
            this.soundDetail = new SoundDetail(f4, f2, f3, f, numChannels);
            return;
        }
        ArrayList arrayList = new ArrayList();
        float[] ballCurrentPos = ZeroAudioBallTools.getInstance().getBallCurrentPos((int) audioInfo.getSoundId());
        if (ballCurrentPos == null) {
            if (numChannels == 2) {
                arrayList.add(Float.valueOf(audioDefaultPosition.getxL()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getyL()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getzL()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getxR()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getyR()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getzR()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getX()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getY()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getZ()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
            } else {
                arrayList.add(Float.valueOf(audioDefaultPosition.getX()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getY()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getZ()));
                arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
            }
        } else if (ballCurrentPos.length == 9) {
            arrayList.add(Float.valueOf(ballCurrentPos[3]));
            arrayList.add(Float.valueOf(ballCurrentPos[4]));
            arrayList.add(Float.valueOf(ballCurrentPos[5]));
            arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
            arrayList.add(Float.valueOf(ballCurrentPos[6]));
            arrayList.add(Float.valueOf(ballCurrentPos[7]));
            arrayList.add(Float.valueOf(ballCurrentPos[8]));
            arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
            arrayList.add(Float.valueOf(ballCurrentPos[0]));
            arrayList.add(Float.valueOf(ballCurrentPos[1]));
            arrayList.add(Float.valueOf(ballCurrentPos[2]));
            arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
        } else {
            arrayList.add(Float.valueOf(ballCurrentPos[0]));
            arrayList.add(Float.valueOf(ballCurrentPos[1]));
            arrayList.add(Float.valueOf(ballCurrentPos[2]));
            arrayList.add(Float.valueOf(audioDefaultPosition.getSize()));
        }
        this.soundDetail = new SoundDetail(f4, f2, f3, f, numChannels, arrayList);
    }

    public static class SoundDetail {
        private final float Angle;
        private final List<BallRouteList> BallRouteList;
        private final float DB;
        private final int NumChannels;
        private final float Speed;
        private final float Volume;

        public SoundDetail(float f, float f2, float f3, float f4, int i, List<Float> list) {
            this.Volume = f;
            this.Angle = f2;
            this.Speed = f3;
            this.DB = f4;
            this.NumChannels = i;
            ArrayList arrayList = new ArrayList();
            this.BallRouteList = arrayList;
            arrayList.add(new BallRouteList(list));
        }

        public SoundDetail(float f, float f2, float f3, float f4, int i) {
            this.Volume = f;
            this.Angle = f2;
            this.Speed = f3;
            this.DB = f4;
            this.NumChannels = i;
            this.BallRouteList = new ArrayList();
        }

        public static class BallRouteList {
            private final List<Float> Pos;
            private final long Seek = 0;

            public BallRouteList(List<Float> list) {
                this.Pos = list;
            }
        }
    }
}
