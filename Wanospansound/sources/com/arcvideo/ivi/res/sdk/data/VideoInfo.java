package com.arcvideo.ivi.res.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoInfo implements Parcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new Creator();
    private List<Actor> actor;
    private String albumId;
    private String albumName;
    private String albumPic;
    private int chnId;
    private String chnName;
    private DefaultEpi defaultEpi;
    private String desc;
    private List<Director> director;
    private String duration;
    private String focus;
    private String htmlUrl;
    private String initIssueTime;
    private int isExclusive;
    private int isProd;
    private int isVip;
    private int len;
    private String name;
    private int order;
    private String publishTime;
    private String score;
    private String shortTitle;
    private String subTitle;
    private String tag;
    private String tvId;
    private Integer total = 0;
    private Integer count = 0;
    private Integer hot = 0;
    private Integer contentType = 0;
    private Integer type = 1;

    public static final class Creator implements Parcelable.Creator<VideoInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final VideoInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return new VideoInfo();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final VideoInfo[] newArray(int i) {
            return new VideoInfo[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final List<Actor> getActor() {
        return this.actor;
    }

    public final String getAlbumId() {
        return this.albumId;
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final String getAlbumPic() {
        return this.albumPic;
    }

    public final int getChnId() {
        return this.chnId;
    }

    public final String getChnName() {
        return this.chnName;
    }

    public final Integer getContentType() {
        return this.contentType;
    }

    public final Integer getCount() {
        return this.count;
    }

    public final DefaultEpi getDefaultEpi() {
        return this.defaultEpi;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final List<Director> getDirector() {
        return this.director;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final String getFocus() {
        return this.focus;
    }

    public final Integer getHot() {
        return this.hot;
    }

    public final String getHtmlUrl() {
        return this.htmlUrl;
    }

    public final String getInitIssueTime() {
        return this.initIssueTime;
    }

    public final int getLen() {
        return this.len;
    }

    public final String getName() {
        return this.name;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getPublishTime() {
        return this.publishTime;
    }

    public final String getScore() {
        return this.score;
    }

    public final String getShortTitle() {
        return this.shortTitle;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getTag() {
        return this.tag;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public final String getTvId() {
        return this.tvId;
    }

    public final Integer getType() {
        return this.type;
    }

    public final int isExclusive() {
        return this.isExclusive;
    }

    public final int isProd() {
        return this.isProd;
    }

    public final int isVip() {
        return this.isVip;
    }

    public final void setActor(List<Actor> list) {
        this.actor = list;
    }

    public final void setAlbumId(String str) {
        this.albumId = str;
    }

    public final void setAlbumName(String str) {
        this.albumName = str;
    }

    public final void setAlbumPic(String str) {
        this.albumPic = str;
    }

    public final void setChnId(int i) {
        this.chnId = i;
    }

    public final void setChnName(String str) {
        this.chnName = str;
    }

    public final void setContentType(Integer num) {
        this.contentType = num;
    }

    public final void setCount(Integer num) {
        this.count = num;
    }

    public final void setDefaultEpi(DefaultEpi defaultEpi) {
        this.defaultEpi = defaultEpi;
    }

    public final void setDesc(String str) {
        this.desc = str;
    }

    public final void setDirector(List<Director> list) {
        this.director = list;
    }

    public final void setDuration(String str) {
        this.duration = str;
    }

    public final void setExclusive(int i) {
        this.isExclusive = i;
    }

    public final void setFocus(String str) {
        this.focus = str;
    }

    public final void setHot(Integer num) {
        this.hot = num;
    }

    public final void setHtmlUrl(String str) {
        this.htmlUrl = str;
    }

    public final void setInitIssueTime(String str) {
        this.initIssueTime = str;
    }

    public final void setLen(int i) {
        this.len = i;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setOrder(int i) {
        this.order = i;
    }

    public final void setProd(int i) {
        this.isProd = i;
    }

    public final void setPublishTime(String str) {
        this.publishTime = str;
    }

    public final void setScore(String str) {
        this.score = str;
    }

    public final void setShortTitle(String str) {
        this.shortTitle = str;
    }

    public final void setSubTitle(String str) {
        this.subTitle = str;
    }

    public final void setTag(String str) {
        this.tag = str;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }

    public final void setTvId(String str) {
        this.tvId = str;
    }

    public final void setType(Integer num) {
        this.type = num;
    }

    public final void setVip(int i) {
        this.isVip = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(1);
    }
}
