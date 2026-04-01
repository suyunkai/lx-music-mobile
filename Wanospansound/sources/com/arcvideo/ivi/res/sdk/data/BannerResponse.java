package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BannerResponse {
    private final String albumId;
    private final String albumName;
    private final String albumPic;
    private final String content;
    private final Html html;
    private final String name;
    private final String qipuId;
    private final String router;
    private final String sourceType;
    private final int type;
    private final Video video;

    public static final class DefaultEpi {
        private final int contentType;
        private final int is3D;
        private final String name;
        private final String publishTime;
        private final String qipuId;

        public DefaultEpi(String qipuId, String publishTime, int i, String name, int i2) {
            Intrinsics.checkNotNullParameter(qipuId, "qipuId");
            Intrinsics.checkNotNullParameter(publishTime, "publishTime");
            Intrinsics.checkNotNullParameter(name, "name");
            this.qipuId = qipuId;
            this.publishTime = publishTime;
            this.is3D = i;
            this.name = name;
            this.contentType = i2;
        }

        public static /* synthetic */ DefaultEpi copy$default(DefaultEpi defaultEpi, String str, String str2, int i, String str3, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = defaultEpi.qipuId;
            }
            if ((i3 & 2) != 0) {
                str2 = defaultEpi.publishTime;
            }
            String str4 = str2;
            if ((i3 & 4) != 0) {
                i = defaultEpi.is3D;
            }
            int i4 = i;
            if ((i3 & 8) != 0) {
                str3 = defaultEpi.name;
            }
            String str5 = str3;
            if ((i3 & 16) != 0) {
                i2 = defaultEpi.contentType;
            }
            return defaultEpi.copy(str, str4, i4, str5, i2);
        }

        public final String component1() {
            return this.qipuId;
        }

        public final String component2() {
            return this.publishTime;
        }

        public final int component3() {
            return this.is3D;
        }

        public final String component4() {
            return this.name;
        }

        public final int component5() {
            return this.contentType;
        }

        public final DefaultEpi copy(String qipuId, String publishTime, int i, String name, int i2) {
            Intrinsics.checkNotNullParameter(qipuId, "qipuId");
            Intrinsics.checkNotNullParameter(publishTime, "publishTime");
            Intrinsics.checkNotNullParameter(name, "name");
            return new DefaultEpi(qipuId, publishTime, i, name, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DefaultEpi)) {
                return false;
            }
            DefaultEpi defaultEpi = (DefaultEpi) obj;
            return Intrinsics.areEqual(this.qipuId, defaultEpi.qipuId) && Intrinsics.areEqual(this.publishTime, defaultEpi.publishTime) && this.is3D == defaultEpi.is3D && Intrinsics.areEqual(this.name, defaultEpi.name) && this.contentType == defaultEpi.contentType;
        }

        public final int getContentType() {
            return this.contentType;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPublishTime() {
            return this.publishTime;
        }

        public final String getQipuId() {
            return this.qipuId;
        }

        public int hashCode() {
            return (((((((this.qipuId.hashCode() * 31) + this.publishTime.hashCode()) * 31) + Integer.hashCode(this.is3D)) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.contentType);
        }

        public final int is3D() {
            return this.is3D;
        }

        public String toString() {
            return "DefaultEpi(qipuId=" + this.qipuId + ", publishTime=" + this.publishTime + ", is3D=" + this.is3D + ", name=" + this.name + ", contentType=" + this.contentType + ')';
        }
    }

    public static final class Html {
        private final String url;

        public Html(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
        }

        public static /* synthetic */ Html copy$default(Html html, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = html.url;
            }
            return html.copy(str);
        }

        public final String component1() {
            return this.url;
        }

        public final Html copy(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new Html(url);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Html) && Intrinsics.areEqual(this.url, ((Html) obj).url);
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return this.url.hashCode();
        }

        public String toString() {
            return "Html(url=" + this.url + ')';
        }
    }

    public static final class Video {
        private final int chnId;
        private final String chnName;
        private final DefaultEpi defaultEpi;
        private final String score;
        private final String videoUrl;

        public Video(int i, String chnName, DefaultEpi defaultEpi, String score, String videoUrl) {
            Intrinsics.checkNotNullParameter(chnName, "chnName");
            Intrinsics.checkNotNullParameter(score, "score");
            Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
            this.chnId = i;
            this.chnName = chnName;
            this.defaultEpi = defaultEpi;
            this.score = score;
            this.videoUrl = videoUrl;
        }

        public static /* synthetic */ Video copy$default(Video video, int i, String str, DefaultEpi defaultEpi, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = video.chnId;
            }
            if ((i2 & 2) != 0) {
                str = video.chnName;
            }
            String str4 = str;
            if ((i2 & 4) != 0) {
                defaultEpi = video.defaultEpi;
            }
            DefaultEpi defaultEpi2 = defaultEpi;
            if ((i2 & 8) != 0) {
                str2 = video.score;
            }
            String str5 = str2;
            if ((i2 & 16) != 0) {
                str3 = video.videoUrl;
            }
            return video.copy(i, str4, defaultEpi2, str5, str3);
        }

        public final int component1() {
            return this.chnId;
        }

        public final String component2() {
            return this.chnName;
        }

        public final DefaultEpi component3() {
            return this.defaultEpi;
        }

        public final String component4() {
            return this.score;
        }

        public final String component5() {
            return this.videoUrl;
        }

        public final Video copy(int i, String chnName, DefaultEpi defaultEpi, String score, String videoUrl) {
            Intrinsics.checkNotNullParameter(chnName, "chnName");
            Intrinsics.checkNotNullParameter(score, "score");
            Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
            return new Video(i, chnName, defaultEpi, score, videoUrl);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Video)) {
                return false;
            }
            Video video = (Video) obj;
            return this.chnId == video.chnId && Intrinsics.areEqual(this.chnName, video.chnName) && Intrinsics.areEqual(this.defaultEpi, video.defaultEpi) && Intrinsics.areEqual(this.score, video.score) && Intrinsics.areEqual(this.videoUrl, video.videoUrl);
        }

        public final int getChnId() {
            return this.chnId;
        }

        public final String getChnName() {
            return this.chnName;
        }

        public final DefaultEpi getDefaultEpi() {
            return this.defaultEpi;
        }

        public final String getScore() {
            return this.score;
        }

        public final String getVideoUrl() {
            return this.videoUrl;
        }

        public int hashCode() {
            int iHashCode = ((Integer.hashCode(this.chnId) * 31) + this.chnName.hashCode()) * 31;
            DefaultEpi defaultEpi = this.defaultEpi;
            return ((((iHashCode + (defaultEpi == null ? 0 : defaultEpi.hashCode())) * 31) + this.score.hashCode()) * 31) + this.videoUrl.hashCode();
        }

        public String toString() {
            return "Video(chnId=" + this.chnId + ", chnName=" + this.chnName + ", defaultEpi=" + this.defaultEpi + ", score=" + this.score + ", videoUrl=" + this.videoUrl + ')';
        }
    }

    public BannerResponse(String albumPic, Video video, int i, String content, String router, String sourceType, String qipuId, String name, String albumName, String albumId, Html html) {
        Intrinsics.checkNotNullParameter(albumPic, "albumPic");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(sourceType, "sourceType");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        this.albumPic = albumPic;
        this.video = video;
        this.type = i;
        this.content = content;
        this.router = router;
        this.sourceType = sourceType;
        this.qipuId = qipuId;
        this.name = name;
        this.albumName = albumName;
        this.albumId = albumId;
        this.html = html;
    }

    public final String component1() {
        return this.albumPic;
    }

    public final String component10() {
        return this.albumId;
    }

    public final Html component11() {
        return this.html;
    }

    public final Video component2() {
        return this.video;
    }

    public final int component3() {
        return this.type;
    }

    public final String component4() {
        return this.content;
    }

    public final String component5() {
        return this.router;
    }

    public final String component6() {
        return this.sourceType;
    }

    public final String component7() {
        return this.qipuId;
    }

    public final String component8() {
        return this.name;
    }

    public final String component9() {
        return this.albumName;
    }

    public final BannerResponse copy(String albumPic, Video video, int i, String content, String router, String sourceType, String qipuId, String name, String albumName, String albumId, Html html) {
        Intrinsics.checkNotNullParameter(albumPic, "albumPic");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(sourceType, "sourceType");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        return new BannerResponse(albumPic, video, i, content, router, sourceType, qipuId, name, albumName, albumId, html);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BannerResponse)) {
            return false;
        }
        BannerResponse bannerResponse = (BannerResponse) obj;
        return Intrinsics.areEqual(this.albumPic, bannerResponse.albumPic) && Intrinsics.areEqual(this.video, bannerResponse.video) && this.type == bannerResponse.type && Intrinsics.areEqual(this.content, bannerResponse.content) && Intrinsics.areEqual(this.router, bannerResponse.router) && Intrinsics.areEqual(this.sourceType, bannerResponse.sourceType) && Intrinsics.areEqual(this.qipuId, bannerResponse.qipuId) && Intrinsics.areEqual(this.name, bannerResponse.name) && Intrinsics.areEqual(this.albumName, bannerResponse.albumName) && Intrinsics.areEqual(this.albumId, bannerResponse.albumId) && Intrinsics.areEqual(this.html, bannerResponse.html);
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

    public final String getContent() {
        return this.content;
    }

    public final Html getHtml() {
        return this.html;
    }

    public final String getName() {
        return this.name;
    }

    public final String getQipuId() {
        return this.qipuId;
    }

    public final String getRouter() {
        return this.router;
    }

    public final String getSourceType() {
        return this.sourceType;
    }

    public final int getType() {
        return this.type;
    }

    public final Video getVideo() {
        return this.video;
    }

    public int hashCode() {
        int iHashCode = this.albumPic.hashCode() * 31;
        Video video = this.video;
        int iHashCode2 = (((((((((((((((((iHashCode + (video == null ? 0 : video.hashCode())) * 31) + Integer.hashCode(this.type)) * 31) + this.content.hashCode()) * 31) + this.router.hashCode()) * 31) + this.sourceType.hashCode()) * 31) + this.qipuId.hashCode()) * 31) + this.name.hashCode()) * 31) + this.albumName.hashCode()) * 31) + this.albumId.hashCode()) * 31;
        Html html = this.html;
        return iHashCode2 + (html != null ? html.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BannerResponse(albumPic=");
        sb.append(this.albumPic).append(", video=").append(this.video).append(", type=").append(this.type).append(", content=").append(this.content).append(", router=").append(this.router).append(", sourceType=").append(this.sourceType).append(", qipuId=").append(this.qipuId).append(", name=").append(this.name).append(", albumName=").append(this.albumName).append(", albumId=").append(this.albumId).append(", html=").append(this.html).append(')');
        return sb.toString();
    }
}
