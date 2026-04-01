package com.wanos.careditproject.model.server;

import android.net.Uri;
import com.google.gson.annotations.Expose;
import com.wanos.careditproject.utils.WanosStringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class OriginModel {
    private int BitDepth;
    private String Name;
    private int NumChannels;
    private long SampleNum;
    private int SampleRate;
    private String Url;

    @Expose(serialize = false)
    private boolean isChangeUrl = false;
    private String Format = "m4a";

    public void setUrl(String str) {
        this.Url = str;
    }

    public String getUrl() {
        if (this.isChangeUrl) {
            return this.Url;
        }
        String str = this.Url;
        if (str == null) {
            return "";
        }
        if (WanosStringUtils.containsChineseCharacter(str)) {
            String strEncode = Uri.encode(this.Url, "-![.:/,%?&=]");
            this.Url = strEncode;
            this.isChangeUrl = true;
            return strEncode;
        }
        this.isChangeUrl = true;
        return this.Url;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getName() {
        return this.Name;
    }

    public void setFormat(String str) {
        this.Format = str;
    }

    public String getFormat() {
        return this.Format;
    }

    public void setNumChannels(int i) {
        this.NumChannels = i;
    }

    public int getNumChannels() {
        return this.NumChannels;
    }

    public void setSampleRate(int i) {
        this.SampleRate = i;
    }

    public int getSampleRate() {
        return this.SampleRate;
    }

    public void setBitDepth(int i) {
        this.BitDepth = i;
    }

    public int getBitDepth() {
        return this.BitDepth;
    }

    public void setSampleNum(long j) {
        this.SampleNum = j;
    }

    public long getSampleNum() {
        return this.SampleNum;
    }
}
