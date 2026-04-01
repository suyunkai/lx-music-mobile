package com.ecarx.eas.sdk.vr.channel;

import ecarx.xsf.mediacenter.vr.channel.IVrChannelInfo;

/* JADX INFO: loaded from: classes2.dex */
public class VrChannelInfo {
    private int channelDataType;
    private String mediaDescription;
    private String mediaPackageName;
    private String mediaVersion;

    public VrChannelInfo(String str, String str2, String str3, int i) {
        this.mediaPackageName = str;
        this.mediaVersion = str2;
        this.mediaDescription = str3;
        this.channelDataType = i;
    }

    public String getMediaPackageName() {
        return this.mediaPackageName;
    }

    public void setMediaPackageName(String str) {
        this.mediaPackageName = str;
    }

    public String getMediaVersion() {
        return this.mediaVersion;
    }

    public void setMediaVersion(String str) {
        this.mediaVersion = str;
    }

    public String getMediaDescription() {
        return this.mediaDescription;
    }

    public void setMediaDescription(String str) {
        this.mediaDescription = str;
    }

    public int getChannelDataType() {
        return this.channelDataType;
    }

    public void setChannelDataType(int i) {
        this.channelDataType = i;
    }

    public static IVrChannelInfo wrap(VrChannelInfo vrChannelInfo) {
        if (vrChannelInfo == null) {
            return null;
        }
        IVrChannelInfo iVrChannelInfo = new IVrChannelInfo();
        iVrChannelInfo.setMediaPackageName(vrChannelInfo.getMediaPackageName());
        iVrChannelInfo.setMediaVersion(vrChannelInfo.getMediaVersion());
        iVrChannelInfo.setMediaDescription(vrChannelInfo.getMediaDescription());
        iVrChannelInfo.setChannelDataType(vrChannelInfo.getChannelDataType());
        return iVrChannelInfo;
    }

    public String toString() {
        return "VrChannelInfo{mediaPackageName='" + this.mediaPackageName + "', mediaVersion='" + this.mediaVersion + "', mediaDescription='" + this.mediaDescription + "', channelDataType=" + this.channelDataType + '}';
    }
}
