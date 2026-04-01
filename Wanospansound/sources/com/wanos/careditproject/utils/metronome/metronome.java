package com.wanos.careditproject.utils.metronome;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;
import com.wanos.careditproject.R;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.util.Constant;

/* JADX INFO: loaded from: classes3.dex */
public class metronome {
    public static final String PREFS_FILE = "prefs_file";
    private int soundId_4_2;
    private int soundId_4_3;
    private int soundIdp_4_2;
    private int soundIdp_4_3;
    private SoundPool soundPool;
    private int strong_8_6;
    private int super_strong_8_6;
    private int weak_8_6;
    int beats = 4;
    private boolean on_off = true;
    private int n8_6 = -2;

    public metronome(Context context) {
        set_SoundPool(context);
    }

    public void setBeat(int i) {
        this.beats = i;
    }

    public int getBeat() {
        return this.beats;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.wanos.careditproject.utils.metronome.metronome$1] */
    private void myPlay(final int i) {
        if (Constant.isXiaomi()) {
            new Thread(WanosJsBridge.METHOD_KEY_PLAY) { // from class: com.wanos.careditproject.utils.metronome.metronome.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    metronome.this.soundPool.play(i, 1.0f, 1.0f, 1, 0, 1.0f);
                }
            }.start();
        } else {
            this.soundPool.play(i, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void changeTick(long j) {
        if (j == 1) {
            this.n8_6 = -2;
        }
        int i = this.beats;
        if (i == 2) {
            if (this.on_off) {
                if (j % ((long) i) == 0) {
                    Log.i("zxm", "222222222222 z");
                    myPlay(this.soundIdp_4_2);
                } else {
                    Log.i("zxm", "222222222222 q");
                    myPlay(this.soundId_4_2);
                }
                Log.i("zxm", "play q 0");
                return;
            }
            return;
        }
        if (i == 3) {
            if (this.on_off) {
                if ((j - 1) % ((long) i) == 0) {
                    Log.i("zxm", "33333333333333 q");
                    myPlay(this.soundId_4_3);
                    return;
                } else {
                    Log.i("zxm", "33333333333333 z");
                    myPlay(this.soundIdp_4_3);
                    return;
                }
            }
            return;
        }
        if (i == 4) {
            if (this.on_off) {
                if (j % ((long) (i - 2)) == 0) {
                    Log.i("zxm", "4444444444444 z");
                    myPlay(this.soundIdp_4_2);
                    return;
                } else {
                    Log.i("zxm", "4444444444444 q");
                    myPlay(this.soundId_4_2);
                    return;
                }
            }
            return;
        }
        if (i == 6 && this.on_off) {
            if ((j - 1) % ((long) i) == 0) {
                Log.i("zxm", "6666666666666666 z");
                myPlay(this.strong_8_6);
            } else if (j - ((long) i) == this.n8_6) {
                Log.i("zxm", "6666666666666666 zz");
                this.n8_6 += 6;
                myPlay(this.super_strong_8_6);
            } else {
                Log.i("zxm", "6666666666666666 qq");
                myPlay(this.weak_8_6);
            }
        }
    }

    public void setOn(boolean z) {
        this.on_off = z;
    }

    public boolean getOn() {
        return this.on_off;
    }

    private void set_SoundPool(Context context) {
        SoundPool soundPoolBuild = new SoundPool.Builder().setMaxStreams(8).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).build();
        this.soundPool = soundPoolBuild;
        this.soundId_4_2 = soundPoolBuild.load(context, R.raw.weak_4_2, 1);
        this.soundIdp_4_2 = this.soundPool.load(context, R.raw.strong_4_2, 1);
        this.soundId_4_3 = this.soundPool.load(context, R.raw.weak_4_3, 1);
        this.soundIdp_4_3 = this.soundPool.load(context, R.raw.strong_4_3, 1);
        this.super_strong_8_6 = this.soundPool.load(context, R.raw.super_strong_8_6, 1);
        this.strong_8_6 = this.soundPool.load(context, R.raw.strong_8_6, 1);
        this.weak_8_6 = this.soundPool.load(context, R.raw.weak_8_6, 1);
    }
}
