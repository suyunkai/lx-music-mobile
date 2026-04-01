package com.wanos.commonlibrary.utils;

/* JADX INFO: loaded from: classes3.dex */
public class ReStartUtil {
    public static final int TYPE_JUYIHALL = 6;
    private boolean isBookRestart;
    private boolean isJuyihallRestart;
    private boolean isMemberRestart;
    private boolean isMusicRestart;

    private ReStartUtil() {
        this.isMusicRestart = true;
        this.isBookRestart = true;
        this.isMemberRestart = true;
        this.isJuyihallRestart = true;
    }

    public static ReStartUtil getInstance() {
        return RestartUtilHolder.instance;
    }

    private static class RestartUtilHolder {
        private static final ReStartUtil instance = new ReStartUtil();

        private RestartUtilHolder() {
        }
    }

    public boolean isMusicRestart() {
        return this.isMusicRestart;
    }

    public void setMusicRestart(boolean z) {
        this.isMusicRestart = z;
    }

    public boolean isBookRestart() {
        return this.isBookRestart;
    }

    public void setBookRestart(boolean z) {
        this.isBookRestart = z;
    }

    public boolean isMemberRestart() {
        return this.isMemberRestart;
    }

    public void setMemberRestart(boolean z) {
        this.isMemberRestart = z;
    }

    public boolean isJuyihallRestart() {
        return this.isJuyihallRestart;
    }

    public void setJuyihallRestart(boolean z) {
        this.isJuyihallRestart = z;
    }
}
