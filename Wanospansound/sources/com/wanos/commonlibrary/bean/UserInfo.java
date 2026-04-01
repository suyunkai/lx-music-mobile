package com.wanos.commonlibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.wanos.commonlibrary.bean.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };
    private String avatar;
    private long birthday;
    private boolean isVip;
    private String mobile;
    private int sex;
    private String token;
    private String userId;
    private String userName;
    private long vipEndTime;
    private long vipStartTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getToken() {
        return this.token;
    }

    public String getUserId() {
        if (this.userId == null) {
            this.userId = "";
        }
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getMobile() {
        return this.mobile;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public int getSex() {
        return this.sex;
    }

    public long getBirthday() {
        return this.birthday;
    }

    public long getVipStartTime() {
        return this.vipStartTime;
    }

    public long getVipEndTime() {
        return this.vipEndTime;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }

    public void setSex(int i) {
        this.sex = i;
    }

    public void setBirthday(long j) {
        this.birthday = j;
    }

    public void setVipStartTime(long j) {
        this.vipStartTime = j;
    }

    public void setVipEndTime(long j) {
        this.vipEndTime = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
        parcel.writeString(this.userId);
        parcel.writeString(this.userName);
        parcel.writeString(this.avatar);
        parcel.writeString(this.mobile);
        parcel.writeByte(this.isVip ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.sex);
        parcel.writeLong(this.birthday);
        parcel.writeLong(this.vipStartTime);
        parcel.writeLong(this.vipEndTime);
    }

    public void readFromParcel(Parcel parcel) {
        this.token = parcel.readString();
        this.userId = parcel.readString();
        this.userName = parcel.readString();
        this.avatar = parcel.readString();
        this.mobile = parcel.readString();
        this.isVip = parcel.readByte() != 0;
        this.sex = parcel.readInt();
        this.birthday = parcel.readLong();
        this.vipStartTime = parcel.readLong();
        this.vipEndTime = parcel.readLong();
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel parcel) {
        this.token = parcel.readString();
        this.userId = parcel.readString();
        this.userName = parcel.readString();
        this.avatar = parcel.readString();
        this.mobile = parcel.readString();
        this.isVip = parcel.readByte() != 0;
        this.sex = parcel.readInt();
        this.birthday = parcel.readLong();
        this.vipStartTime = parcel.readLong();
        this.vipEndTime = parcel.readLong();
    }

    public String toString() {
        return "UserInfo{token='" + this.token + "', userId='" + this.userId + "', userName='" + this.userName + "', avatar='" + this.avatar + "', mobile='" + this.mobile + "', isVip=" + this.isVip + ", sex=" + this.sex + ", birthday=" + this.birthday + ", vipStartTime=" + this.vipStartTime + ", vipEndTime=" + this.vipEndTime + '}';
    }
}
