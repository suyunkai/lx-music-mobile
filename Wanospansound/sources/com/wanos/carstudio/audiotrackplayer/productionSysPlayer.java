package com.wanos.carstudio.audiotrackplayer;

import com.wanos.play.AudioPlayer;
import com.wanos.util.Constant;
import com.wanos.util.NativeMethod;
import java.lang.reflect.Array;
import java.util.Arrays;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class productionSysPlayer {
    private static productionSysPlayer mInstance;
    private int playerChannelNum;
    String TAG = "wanos:[productionSysPlayer]";
    private EPlayerState playerState = EPlayerState.NONE;
    private float globalVolumn = 1.0f;
    private short[] audioData = null;
    private float[][] audioOutput = null;

    public enum EPlayerState {
        NONE,
        PLAYFADEIN,
        PlAYING,
        PAUSEFADEIN,
        PAUSE,
        PAUSEFADEOUT,
        STOPING,
        STOPED
    }

    private double fadeinLinear(int i, int i2) {
        return (i2 * 1.0f) / i;
    }

    private double fadeoutLinear(int i, int i2) {
        return 1.0f - ((i2 * 1.0f) / i);
    }

    public static productionSysPlayer getInstance() {
        if (mInstance == null) {
            IjkMediaPlayer.loadLibInit();
            productionSysPlayer productionsysplayer = new productionSysPlayer();
            mInstance = productionsysplayer;
            productionsysplayer.playerChannelNum = Constant.getPlayerChannelNum();
        }
        return mInstance;
    }

    public void freeAudioRender() {
        AudioPlayer.getInstance().freePan();
    }

    public void setPcmInfo(float[][] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, float f, int i, int[] iArr) {
        if (this.audioData == null) {
            this.audioData = new short[Constant.BUFFERSIZE * this.playerChannelNum];
        }
        if (this.audioOutput == null) {
            this.audioOutput = (float[][]) Array.newInstance((Class<?>) Float.TYPE, this.playerChannelNum, Constant.BUFFERSIZE);
        }
        if (i > 0) {
            NativeMethod.getInstance().playSpaceAudio(fArr, fArr2, fArr3, fArr4, fArr5, null, i, iArr, this.audioOutput);
            if (this.audioOutput != null) {
                for (int i2 = 0; i2 < Constant.BUFFERSIZE; i2++) {
                    if (this.playerState == EPlayerState.PLAYFADEIN) {
                        float f2 = this.globalVolumn + 0.01f;
                        this.globalVolumn = f2;
                        if (f2 >= 1.0f) {
                            this.globalVolumn = 1.0f;
                            this.playerState = EPlayerState.PlAYING;
                        }
                    }
                    if (this.playerState == EPlayerState.STOPING) {
                        float f3 = this.globalVolumn - 0.002f;
                        this.globalVolumn = f3;
                        if (f3 <= 0.0f) {
                            this.globalVolumn = 0.0f;
                            this.playerState = EPlayerState.STOPED;
                        }
                    }
                    if (this.playerState == EPlayerState.PAUSEFADEOUT) {
                        float f4 = this.globalVolumn - 0.001f;
                        this.globalVolumn = f4;
                        if (f4 <= 0.0f) {
                            this.globalVolumn = 0.0f;
                            this.playerState = EPlayerState.PAUSE;
                        }
                    }
                    if (this.playerState == EPlayerState.PAUSEFADEIN) {
                        float f5 = this.globalVolumn + 1.0E-4f;
                        this.globalVolumn = f5;
                        if (f5 >= 1.0f) {
                            this.globalVolumn = 1.0f;
                            this.playerState = EPlayerState.PlAYING;
                        }
                    }
                    int i3 = 0;
                    while (true) {
                        int i4 = this.playerChannelNum;
                        if (i3 < i4) {
                            float f6 = this.audioOutput[i3][i2] * this.globalVolumn;
                            double d2 = f6;
                            if (d2 > 0.99996948d) {
                                f6 = 0.9999695f;
                            } else if (d2 < -0.99996948d) {
                                f6 = -0.9999695f;
                            }
                            this.audioData[(i4 * i2) + i3] = (short) (f6 * 32767.0f);
                            i3++;
                        }
                    }
                }
            } else {
                Arrays.fill(this.audioData, (short) 0);
            }
        } else {
            Arrays.fill(this.audioData, (short) 0);
            if (this.playerState == EPlayerState.PLAYFADEIN) {
                this.globalVolumn = 1.0f;
                this.playerState = EPlayerState.PlAYING;
            }
            if (this.playerState == EPlayerState.STOPING) {
                this.globalVolumn = 0.0f;
                this.playerState = EPlayerState.STOPED;
            }
            if (this.playerState == EPlayerState.PAUSEFADEOUT) {
                this.globalVolumn = 0.0f;
                this.playerState = EPlayerState.PAUSE;
            }
            if (this.playerState == EPlayerState.PAUSEFADEIN) {
                this.globalVolumn = 1.0f;
                this.playerState = EPlayerState.PlAYING;
            }
        }
        AudioPlayer.getInstance().write(this.audioData);
    }

    public void play() {
        freeAudioRender();
        AudioPlayer.getInstance().initPan();
        AudioPlayer.getInstance().play();
        this.playerState = EPlayerState.PLAYFADEIN;
        this.globalVolumn = 0.0f;
    }

    public void playEx() {
        this.playerState = EPlayerState.PLAYFADEIN;
    }

    public void clearRender() {
        freeAudioRender();
        AudioPlayer.getInstance().initPan();
    }

    public EPlayerState getPlayerState() {
        return this.playerState;
    }

    public void toPause() {
        this.playerState = EPlayerState.PAUSEFADEOUT;
    }

    public void pause() {
        this.playerState = EPlayerState.PAUSE;
    }

    public boolean isPlaying() {
        return this.playerState == EPlayerState.PlAYING || this.playerState == EPlayerState.PLAYFADEIN;
    }

    public void resume() {
        getInstance().clearRender();
        this.playerState = EPlayerState.PAUSEFADEIN;
    }

    public void toStop() {
        this.playerState = EPlayerState.STOPING;
    }

    public void stop() {
        this.playerState = EPlayerState.STOPED;
        AudioPlayer.getInstance().stop();
        this.audioData = null;
        this.audioOutput = null;
    }

    public void setObjGain(int i, float[] fArr) {
        NativeMethod.getInstance().setObjGain(i, fArr);
    }

    private double fadeinsCurve(int i, int i2) {
        float f = (i2 * 1.0f) / i;
        if (i2 < i / 2) {
            return 0.5d - Math.sqrt(0.25d - ((double) (f * f)));
        }
        float f2 = f - 1.0f;
        return Math.sqrt(0.25d - ((double) (f2 * f2))) + 0.5d;
    }

    private double fadeinLogarithmic(int i, int i2) {
        return 1.0d - Math.pow(1.0f - ((i2 * 1.0f) / i), 2.0d);
    }

    private double fadeinExponential(int i, int i2) {
        return Math.pow((i2 * 1.0f) / i, 2.0d);
    }

    private double fadeoutCurve(int i, int i2) {
        float f = (i2 * 1.0f) / i;
        if (i2 < i / 2) {
            return Math.sqrt(0.25d - ((double) (f * f))) + 0.5d;
        }
        float f2 = f - 1.0f;
        return 0.5d - Math.sqrt(0.25d - ((double) (f2 * f2)));
    }

    private double fadeoutLogarithmic(int i, int i2) {
        return 1.0d - Math.pow((i2 * 1.0f) / i, 2.0d);
    }

    private double fadeoutExponential(int i, int i2) {
        return Math.pow(1.0f - ((i2 * 1.0f) / i), 2.0d);
    }

    public double processFadein(int i, String str, int i2, float f) {
        double dFadeinExponential;
        str.hashCode();
        switch (str) {
            case "exponential":
                dFadeinExponential = fadeinExponential(i, i2);
                break;
            case "linear":
                dFadeinExponential = fadeinLinear(i, i2);
                break;
            case "sCurve":
                dFadeinExponential = fadeinsCurve(i, i2);
                break;
            case "logarithmic":
                dFadeinExponential = fadeinLogarithmic(i, i2);
                break;
            default:
                dFadeinExponential = fadeinLinear(i, i2);
                break;
        }
        return dFadeinExponential * ((double) f);
    }

    public double processFadeout(int i, String str, int i2, float f) {
        double dFadeoutExponential;
        str.hashCode();
        switch (str) {
            case "exponential":
                dFadeoutExponential = fadeoutExponential(i, i2);
                break;
            case "linear":
                dFadeoutExponential = fadeoutLinear(i, i2);
                break;
            case "sCurve":
                dFadeoutExponential = fadeoutCurve(i, i2);
                break;
            case "logarithmic":
                dFadeoutExponential = fadeoutLogarithmic(i, i2);
                break;
            default:
                dFadeoutExponential = fadeoutLinear(i, i2);
                break;
        }
        return dFadeoutExponential * ((double) f);
    }

    public double processFadeinV2(int i, int i2, int i3, float f) {
        double dFadeinLinear;
        if (i2 == 0) {
            dFadeinLinear = fadeinLinear(i, i3);
        } else if (i2 == 1) {
            dFadeinLinear = fadeinsCurve(i, i3);
        } else if (i2 == 2) {
            dFadeinLinear = fadeinLogarithmic(i, i3);
        } else if (i2 == 3) {
            dFadeinLinear = fadeinExponential(i, i3);
        } else {
            dFadeinLinear = fadeinLinear(i, i3);
        }
        return dFadeinLinear * ((double) f);
    }

    public double processFadeoutV2(int i, int i2, int i3, float f) {
        double dFadeoutLinear;
        if (i2 == 0) {
            dFadeoutLinear = fadeoutLinear(i, i3);
        } else if (i2 == 1) {
            dFadeoutLinear = fadeoutCurve(i, i3);
        } else if (i2 == 2) {
            dFadeoutLinear = fadeoutLogarithmic(i, i3);
        } else if (i2 == 3) {
            dFadeoutLinear = fadeoutExponential(i, i3);
        } else {
            dFadeoutLinear = fadeoutLinear(i, i3);
        }
        return dFadeoutLinear * ((double) f);
    }
}
