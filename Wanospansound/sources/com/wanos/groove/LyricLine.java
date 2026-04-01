package com.wanos.groove;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class LyricLine implements Parcelable {
    public static final Parcelable.Creator<LyricLine> CREATOR = new Parcelable.Creator<LyricLine>() { // from class: com.wanos.groove.LyricLine.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LyricLine createFromParcel(Parcel parcel) {
            return new LyricLine(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LyricLine[] newArray(int i) {
            return new LyricLine[i];
        }
    };
    long mCurrent;
    int mLineNumber;
    String mLyric;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LyricLine() {
    }

    protected LyricLine(Parcel parcel) {
        this.mCurrent = parcel.readLong();
        this.mLyric = parcel.readString();
        this.mLineNumber = parcel.readInt();
    }

    public long getmCurrent() {
        return this.mCurrent;
    }

    public void setmCurrent(long j) {
        this.mCurrent = j;
    }

    public String getmLyric() {
        return this.mLyric;
    }

    public void setmLyric(String str) {
        this.mLyric = str;
    }

    public int getmLineNumber() {
        return this.mLineNumber;
    }

    public void setmLineNumber(int i) {
        this.mLineNumber = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mCurrent);
        parcel.writeString(this.mLyric);
        parcel.writeInt(this.mLineNumber);
    }
}
