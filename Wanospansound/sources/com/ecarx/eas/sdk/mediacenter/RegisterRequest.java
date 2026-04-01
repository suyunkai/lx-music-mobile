package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterRequest {
    private String args;
    private int displayId;
    private int[] displayList;
    private String mediaSessionPkgName;
    private String packageName;
    private int zoneId;

    private RegisterRequest(Builder builder) {
        this.displayId = 0;
        this.displayList = new int[]{0};
        this.zoneId = 0;
        this.packageName = builder.packageName;
        this.displayId = builder.displayId;
        this.displayList = builder.displayList;
        this.zoneId = builder.zoneId;
        this.args = builder.args;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getDisplayId() {
        return this.displayId;
    }

    public int[] getDisplayList() {
        return this.displayList;
    }

    public int getZoneId() {
        return this.zoneId;
    }

    public static final class Builder {
        private String args;
        private int displayId;
        private int[] displayList;
        private String packageName;
        private int zoneId;

        public final Builder packageName(String str) {
            this.packageName = str;
            return this;
        }

        public final Builder displayId(int i) {
            this.displayId = i;
            return this;
        }

        public final Builder displayList(int[] iArr) {
            this.displayList = iArr;
            return this;
        }

        public final Builder zoneId(int i) {
            this.zoneId = i;
            return this;
        }

        public final Builder args(String str) {
            this.args = str;
            return this;
        }

        public final RegisterRequest build() {
            return new RegisterRequest(this);
        }
    }
}
