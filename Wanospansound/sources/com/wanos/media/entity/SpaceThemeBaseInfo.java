package com.wanos.media.entity;

import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class SpaceThemeBaseInfo implements Serializable {
    public static final int ENTER_WAY_MING_XIANG_COLLECT = 2002;
    public static final int ENTER_WAY_MING_XIANG_EXAMPLE = 2001;
    public static final int ENTER_WAY_XIAO_QI_COLLECT = 1002;
    public static final int ENTER_WAY_XIAO_QI_EXAMPLE = 1001;
    public static final int ITEM_TYPE_ADD = 3003;
    public static final int ITEM_TYPE_NORMAL = 3002;
    public static final int ITEM_TYPE_NORMAL_ME = 3004;
    public static final int ITEM_TYPE_TITLE = 3001;
    private String author;
    private String bgImg;
    private boolean canShare;
    private String coverImg;
    private String durationRange;
    private String id;
    private boolean isVip;
    private int itemType = 3002;
    private int mEnterWay = 1001;
    private String rootThemeType;
    private String sourceUserName;
    private int tempCoverPath;
    private long themeId;
    private String themeName;
    private int themeTypeResId;
    private long time;
    private String videoPath;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnterWay {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {
    }

    public int getThemeTypeResId() {
        return this.themeTypeResId;
    }

    public void setThemeTypeResId(int i) {
        this.themeTypeResId = i;
    }

    public static SpaceThemeBaseInfo getTitleEntity(int i, int i2) {
        SpaceThemeBaseInfo spaceThemeBaseInfo = new SpaceThemeBaseInfo();
        spaceThemeBaseInfo.setItemType(3001);
        spaceThemeBaseInfo.setEnterWay(i2);
        spaceThemeBaseInfo.setName(StringUtils.getString(i));
        return spaceThemeBaseInfo;
    }

    public static SpaceThemeBaseInfo getAddButton() {
        SpaceThemeBaseInfo spaceThemeBaseInfo = new SpaceThemeBaseInfo();
        spaceThemeBaseInfo.setItemType(3003);
        return spaceThemeBaseInfo;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public int getItemType() {
        return this.itemType;
    }

    public boolean isCanShare() {
        return this.canShare;
    }

    public void setCanShare(boolean z) {
        this.canShare = z;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCoverPath() {
        return this.coverImg;
    }

    public void setCoverPath(String str) {
        this.coverImg = str;
    }

    public String getName() {
        return this.themeName;
    }

    public String getSourceUserName() {
        if (StringUtils.isEmpty(this.sourceUserName)) {
            return UserInfoUtil.getUserInfo().getUserName();
        }
        return this.sourceUserName;
    }

    public void setSourceUserName(String str) {
        this.sourceUserName = str;
    }

    public void setName(String str) {
        this.themeName = str;
    }

    public int getEnterWay() {
        return this.mEnterWay;
    }

    public void setEnterWay(int i) {
        this.mEnterWay = i;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String str) {
        this.videoPath = str;
    }

    public int getTempCoverPath() {
        return this.tempCoverPath;
    }

    public void setTempCoverPath(int i) {
        this.tempCoverPath = i;
    }

    public long getThemeId() {
        return this.themeId;
    }

    public void setThemeId(long j) {
        this.themeId = j;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    public void setBgImg(String str) {
        this.bgImg = str;
    }

    public String getDurationRange() {
        return this.durationRange;
    }

    public void setDurationRange(String str) {
        this.durationRange = str;
    }

    public String getRootThemeType() {
        return this.rootThemeType;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }
}
