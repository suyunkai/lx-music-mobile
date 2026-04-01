package com.wanos.groove;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class LyricInfo implements Parcelable {
    public static final Parcelable.Creator<LyricInfo> CREATOR = new Parcelable.Creator<LyricInfo>() { // from class: com.wanos.groove.LyricInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LyricInfo createFromParcel(Parcel parcel) {
            return new LyricInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LyricInfo[] newArray(int i) {
            return new LyricInfo[i];
        }
    };
    long song_id_str;
    String song_lyric;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LyricInfo() {
    }

    protected LyricInfo(Parcel parcel) {
        this.song_id_str = parcel.readLong();
        this.song_lyric = parcel.readString();
    }

    public long getSong_id_str() {
        return this.song_id_str;
    }

    public void setSong_id_str(long j) {
        this.song_id_str = j;
    }

    public String getSong_lyric() {
        return this.song_lyric;
    }

    public void setSong_lyric(String str) {
        this.song_lyric = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.song_id_str);
        parcel.writeString(this.song_lyric);
    }
}
