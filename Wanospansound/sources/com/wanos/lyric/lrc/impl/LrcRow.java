package com.wanos.lyric.lrc.impl;

/* JADX INFO: loaded from: classes3.dex */
public class LrcRow implements Comparable<LrcRow> {
    public static final String TAG = "wanos:[LrcRow]";
    public String content;
    public long endTime;
    public long startTime;
    public String startTimeString;

    public String getStartTimeString() {
        return this.startTimeString;
    }

    public void setStartTimeString(String str) {
        this.startTimeString = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        return "LrcRow{startTimeString='" + this.startTimeString + "', startTime=" + this.startTime + ", endTime=" + this.endTime + ", content='" + this.content + "'}";
    }

    @Override // java.lang.Comparable
    public int compareTo(LrcRow lrcRow) {
        return (int) (this.startTime - lrcRow.startTime);
    }
}
