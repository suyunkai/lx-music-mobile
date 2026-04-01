package ecarx.xsf.mediacenter.session;

import android.graphics.Bitmap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class AppSourceInfo {
    private String appName;
    private String iconPath;
    private Bitmap iconRes;
    private boolean isRunning;
    private String packageName;
    private int priorityLevel;
    private int[] sourceTypeList;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String str) {
        this.iconPath = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public int[] getSourceTypeList() {
        return this.sourceTypeList;
    }

    public void setSourceTypeList(int[] iArr) {
        this.sourceTypeList = iArr;
    }

    public Bitmap getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(Bitmap bitmap) {
        this.iconRes = bitmap;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public void setPriorityLevel(int i) {
        this.priorityLevel = i;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean z) {
        this.isRunning = z;
    }

    private AppSourceInfo(Builder builder) {
        this.packageName = builder.packageName;
        this.iconPath = builder.iconPath;
        this.appName = builder.appName;
        this.sourceTypeList = builder.sourceTypeList;
        this.priorityLevel = builder.priorityLevel;
        this.isRunning = builder.isRunning;
        this.iconRes = builder.iconRes;
    }

    public String toString() {
        return "AppSourceInfo{packageName='" + this.packageName + "', iconPath='" + this.iconPath + "', iconRes=" + this.iconRes + ", appName='" + this.appName + "', sourceTypeList=" + Arrays.toString(this.sourceTypeList) + ", priorityLevel=" + this.priorityLevel + ", isRunning=" + this.isRunning + '}';
    }

    public static final class Builder {
        private String appName;
        private String iconPath;
        private Bitmap iconRes;
        private boolean isRunning;
        private String packageName;
        private int priorityLevel;
        private int[] sourceTypeList;

        public final Builder packageName(String str) {
            this.packageName = str;
            return this;
        }

        public final Builder iconPath(String str) {
            this.iconPath = str;
            return this;
        }

        public final Builder appName(String str) {
            this.appName = str;
            return this;
        }

        public final Builder sourceTypeList(int[] iArr) {
            this.sourceTypeList = iArr;
            return this;
        }

        public final Builder priorityLevel(int i) {
            this.priorityLevel = i;
            return this;
        }

        public final Builder isRunning(boolean z) {
            this.isRunning = z;
            return this;
        }

        public final Builder iconRes(Bitmap bitmap) {
            this.iconRes = bitmap;
            return this;
        }

        public final AppSourceInfo build() {
            return new AppSourceInfo(this);
        }
    }
}
