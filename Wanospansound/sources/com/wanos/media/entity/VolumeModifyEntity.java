package com.wanos.media.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.blankj.utilcode.util.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeModifyEntity implements Parcelable {
    public static final Parcelable.Creator<VolumeModifyEntity> CREATOR = new Parcelable.Creator<VolumeModifyEntity>() { // from class: com.wanos.media.entity.VolumeModifyEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VolumeModifyEntity createFromParcel(Parcel parcel) {
            return new VolumeModifyEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VolumeModifyEntity[] newArray(int i) {
            return new VolumeModifyEntity[i];
        }
    };
    private String ballIcon;
    private int ballId;
    private String ballName;
    private float ballVolume;
    private boolean isSelect;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VolumeModifyEntity(int i, String str, String str2, float f) {
        this.ballId = i;
        this.ballName = str;
        this.ballIcon = str2;
        this.ballVolume = f;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public int getBallId() {
        return this.ballId;
    }

    public String getBallName() {
        if (StringUtils.isEmpty(this.ballName)) {
            return "";
        }
        if (this.ballName.length() <= 4) {
            return this.ballName;
        }
        return this.ballName.substring(0, 4);
    }

    public void setBallVolume(float f) {
        this.ballVolume = f;
    }

    public String getBallIcon() {
        return this.ballIcon;
    }

    public float getBallVolume() {
        return this.ballVolume;
    }

    protected VolumeModifyEntity(Parcel parcel) {
        this.ballId = parcel.readInt();
        this.ballName = parcel.readString();
        this.ballIcon = parcel.readString();
        this.ballVolume = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ballId);
        parcel.writeString(this.ballName);
        parcel.writeString(this.ballIcon);
        parcel.writeFloat(this.ballVolume);
    }
}
