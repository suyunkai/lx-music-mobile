package com.wanos.media.entity;

/* JADX INFO: loaded from: classes3.dex */
public class ThemeEvent {
    public static final int EVENT_TYPE_DELETE = 101;
    public static final int EVENT_TYPE_REFRESH = 103;
    public static final int EVENT_TYPE_TOP = 102;
    private long changeThemeId = -1;
    private final int eventType;

    public ThemeEvent(int i) {
        this.eventType = i;
    }

    public long getThemeId() {
        return this.changeThemeId;
    }

    public void setThemeId(long j) {
        this.changeThemeId = j;
    }

    public int getEventType() {
        return this.eventType;
    }
}
