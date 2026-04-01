package com.wanos.bean;

import android.util.Log;
import com.wanos.play.PressurePlayer;
import com.wanos.util.Constant;
import com.wanos.util.EditingUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PressureBean implements Cloneable {
    private static final String TAG = "PressureBean";
    public String audioPath;
    public int audioType;
    private byte[] buffer;
    private float[] buffer_float;
    public int channelCount;
    public int current;
    private float[][] floats;
    private float formatVolume;
    public int id;
    public boolean isUsePressureLine;
    public List<PressureLine> line;
    public PressureLine pressureLine;
    public int repeatType;
    public int sampleRate;
    public float size;
    public int operationType = PressurePlayer.ZERO_PlayerState.NONE.ordinal();
    private float volume = -100.0f;
    private float audioWidth = -1.0f;

    public void setCurrent(int i) {
        this.current = i;
    }

    public int getChannelCount() {
        return this.audioType == 2 ? 2 : 1;
    }

    public float[][] getFloats() {
        float[][] fArr = this.floats;
        if (fArr == null) {
            float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, this.audioType == 2 ? 2 : 1, Constant.BUFFERSIZE);
            this.floats = fArr2;
            return fArr2;
        }
        for (float[] fArr3 : fArr) {
            Arrays.fill(fArr3, 0.0f);
        }
        return this.floats;
    }

    public float[] getBuffer_float() {
        float[] fArr = this.buffer_float;
        if (fArr == null) {
            float[] fArr2 = new float[Constant.BUFFERSIZE * (this.audioType != 2 ? 1 : 2)];
            this.buffer_float = fArr2;
            return fArr2;
        }
        Arrays.fill(fArr, 0.0f);
        return this.buffer_float;
    }

    public byte[] getBuffer() {
        byte[] bArr = this.buffer;
        if (bArr == null) {
            byte[] bArr2 = new byte[Constant.BUFFERSIZE * EditingUtils.sizeOfShort * (this.audioType != 2 ? 1 : 2)];
            this.buffer = bArr2;
            return bArr2;
        }
        Arrays.fill(bArr, (byte) 0);
        return this.buffer;
    }

    public void setVolume(float f) {
        this.formatVolume = (float) Math.pow(10.0d, (f - (-20.0f)) / 20.0f);
        this.volume = f;
    }

    public float getFormatVolume() {
        if (this.volume == -100.0f) {
            List<PressureLine> list = this.line;
            if (list == null || list.isEmpty()) {
                Log.d(TAG, "getFormatVolume: 异常，设置默认音量 == -20");
                setVolume(-20.0f);
            } else {
                Log.d(TAG, "getFormatVolume: 通过模板获取 == " + this.line.get(0).volume);
                setVolume(this.line.get(0).volume);
            }
        }
        return this.formatVolume;
    }

    public float getAudioWidth() {
        float f = this.audioWidth;
        if (f != -1.0f) {
            return f;
        }
        List<PressureLine> list = this.line;
        if (list == null || list.isEmpty()) {
            return 0.0f;
        }
        float f2 = this.line.get(0).size;
        this.audioWidth = f2;
        return f2;
    }

    public String toString() {
        return "PressureBean{id=" + this.id + ", channelCount=" + this.channelCount + ", sampleRate=" + this.sampleRate + ", audioType=" + this.audioType + ", repeatType=" + this.repeatType + ", audioPath='" + this.audioPath + "', pressureLine=" + this.pressureLine + ", isUsePressureLine=" + this.isUsePressureLine + ", current=" + this.current + ", line=" + this.line + '}';
    }

    public static class PressureLine implements Cloneable {
        public double ballFrameTime;
        public int index;
        public long seek;
        public float size;
        public float volume = 0.0f;
        public float x_l;
        public float x_m;
        public float x_r;
        public float y_l;
        public float y_m;
        public float y_r;
        public float z_l;
        public float z_m;
        public float z_r;

        public String toString() {
            return "PressureLine{index=" + this.index + ", seek=" + this.seek + ", x_m=" + this.x_m + ", y_m=" + this.y_m + ", z_m=" + this.z_m + ", volume=" + this.volume + '}';
        }

        public Object clone() throws CloneNotSupportedException {
            return (PressureLine) super.clone();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        PressureBean pressureBean = (PressureBean) super.clone();
        if (this.line != null) {
            pressureBean.line = new ArrayList(this.line);
        }
        return pressureBean;
    }
}
