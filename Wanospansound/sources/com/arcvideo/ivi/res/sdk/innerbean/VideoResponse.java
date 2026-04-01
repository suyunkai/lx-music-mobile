package com.arcvideo.ivi.res.sdk.innerbean;

import com.ecarx.eas.sdk.mediacenter.SemanticsLevelOneType;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoResponse {
    private final String albumId;
    private final String albumName;
    private final String albumPic;
    private List<AtomsEpgResponse> atomsEpgs;
    private final CastResponse cast;
    private final int chnId;
    private final String chnName;
    private final int contentType;
    private final Integer count;
    private final DefaultEpiResponse defaultEpi;
    private final String desc;
    private final String drm;
    private final String focus;
    private final String hdr;
    private final int hot;
    private final String initIssueTime;
    private final int is1080;
    private final int is3D;
    private final int isAudioMode;
    private final int isDolby;
    private final int isExclusive;
    private boolean isSelect;
    private final int len;
    private final String name;
    private final int order;
    private final String posterPic;
    private final String publishTime;
    private final String qipuId;
    private final int qiyiProd;
    private final String reason;
    private final String score;
    private final String shortName;
    private final long sourceCode;
    private final String subTitle;
    private final String tag;
    private final Integer total;
    private final String type4k;
    private final VipInfoResponse vipInfo;
    private final String vipType;

    public VideoResponse() {
        this(null, 0, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, 0, 0, 0, 0, 0, 0L, 0, 0, 0, null, null, null, null, null, null, null, null, null, false, null, 0, null, -1, 127, null);
    }

    public VideoResponse(String reason, int i, String chnName, String qipuId, String albumId, String albumName, String name, String subTitle, String focus, String albumPic, String posterPic, String score, String drm, int i2, String hdr, String shortName, String vipType, int i3, int i4, int i5, int i6, int i7, long j, int i8, int i9, int i10, String initIssueTime, String publishTime, String desc, String tag, Integer num, Integer num2, VipInfoResponse vipInfoResponse, DefaultEpiResponse defaultEpiResponse, CastResponse castResponse, boolean z, String type4k, int i11, List<AtomsEpgResponse> list) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(chnName, "chnName");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(focus, "focus");
        Intrinsics.checkNotNullParameter(albumPic, "albumPic");
        Intrinsics.checkNotNullParameter(posterPic, "posterPic");
        Intrinsics.checkNotNullParameter(score, "score");
        Intrinsics.checkNotNullParameter(drm, "drm");
        Intrinsics.checkNotNullParameter(hdr, "hdr");
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        Intrinsics.checkNotNullParameter(vipType, "vipType");
        Intrinsics.checkNotNullParameter(initIssueTime, "initIssueTime");
        Intrinsics.checkNotNullParameter(publishTime, "publishTime");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(type4k, "type4k");
        this.reason = reason;
        this.chnId = i;
        this.chnName = chnName;
        this.qipuId = qipuId;
        this.albumId = albumId;
        this.albumName = albumName;
        this.name = name;
        this.subTitle = subTitle;
        this.focus = focus;
        this.albumPic = albumPic;
        this.posterPic = posterPic;
        this.score = score;
        this.drm = drm;
        this.hot = i2;
        this.hdr = hdr;
        this.shortName = shortName;
        this.vipType = vipType;
        this.isExclusive = i3;
        this.qiyiProd = i4;
        this.is3D = i5;
        this.isDolby = i6;
        this.is1080 = i7;
        this.sourceCode = j;
        this.order = i8;
        this.len = i9;
        this.contentType = i10;
        this.initIssueTime = initIssueTime;
        this.publishTime = publishTime;
        this.desc = desc;
        this.tag = tag;
        this.total = num;
        this.count = num2;
        this.vipInfo = vipInfoResponse;
        this.defaultEpi = defaultEpiResponse;
        this.cast = castResponse;
        this.isSelect = z;
        this.type4k = type4k;
        this.isAudioMode = i11;
        this.atomsEpgs = list;
    }

    public final String component1() {
        return this.reason;
    }

    public final String component10() {
        return this.albumPic;
    }

    public final String component11() {
        return this.posterPic;
    }

    public final String component12() {
        return this.score;
    }

    public final String component13() {
        return this.drm;
    }

    public final int component14() {
        return this.hot;
    }

    public final String component15() {
        return this.hdr;
    }

    public final String component16() {
        return this.shortName;
    }

    public final String component17() {
        return this.vipType;
    }

    public final int component18() {
        return this.isExclusive;
    }

    public final int component19() {
        return this.qiyiProd;
    }

    public final int component2() {
        return this.chnId;
    }

    public final int component20() {
        return this.is3D;
    }

    public final int component21() {
        return this.isDolby;
    }

    public final int component22() {
        return this.is1080;
    }

    public final long component23() {
        return this.sourceCode;
    }

    public final int component24() {
        return this.order;
    }

    public final int component25() {
        return this.len;
    }

    public final int component26() {
        return this.contentType;
    }

    public final String component27() {
        return this.initIssueTime;
    }

    public final String component28() {
        return this.publishTime;
    }

    public final String component29() {
        return this.desc;
    }

    public final String component3() {
        return this.chnName;
    }

    public final String component30() {
        return this.tag;
    }

    public final Integer component31() {
        return this.total;
    }

    public final Integer component32() {
        return this.count;
    }

    public final VipInfoResponse component33() {
        return this.vipInfo;
    }

    public final DefaultEpiResponse component34() {
        return this.defaultEpi;
    }

    public final CastResponse component35() {
        return this.cast;
    }

    public final boolean component36() {
        return this.isSelect;
    }

    public final String component37() {
        return this.type4k;
    }

    public final int component38() {
        return this.isAudioMode;
    }

    public final List<AtomsEpgResponse> component39() {
        return this.atomsEpgs;
    }

    public final String component4() {
        return this.qipuId;
    }

    public final String component5() {
        return this.albumId;
    }

    public final String component6() {
        return this.albumName;
    }

    public final String component7() {
        return this.name;
    }

    public final String component8() {
        return this.subTitle;
    }

    public final String component9() {
        return this.focus;
    }

    public final VideoResponse copy(String reason, int i, String chnName, String qipuId, String albumId, String albumName, String name, String subTitle, String focus, String albumPic, String posterPic, String score, String drm, int i2, String hdr, String shortName, String vipType, int i3, int i4, int i5, int i6, int i7, long j, int i8, int i9, int i10, String initIssueTime, String publishTime, String desc, String tag, Integer num, Integer num2, VipInfoResponse vipInfoResponse, DefaultEpiResponse defaultEpiResponse, CastResponse castResponse, boolean z, String type4k, int i11, List<AtomsEpgResponse> list) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(chnName, "chnName");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(focus, "focus");
        Intrinsics.checkNotNullParameter(albumPic, "albumPic");
        Intrinsics.checkNotNullParameter(posterPic, "posterPic");
        Intrinsics.checkNotNullParameter(score, "score");
        Intrinsics.checkNotNullParameter(drm, "drm");
        Intrinsics.checkNotNullParameter(hdr, "hdr");
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        Intrinsics.checkNotNullParameter(vipType, "vipType");
        Intrinsics.checkNotNullParameter(initIssueTime, "initIssueTime");
        Intrinsics.checkNotNullParameter(publishTime, "publishTime");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(type4k, "type4k");
        return new VideoResponse(reason, i, chnName, qipuId, albumId, albumName, name, subTitle, focus, albumPic, posterPic, score, drm, i2, hdr, shortName, vipType, i3, i4, i5, i6, i7, j, i8, i9, i10, initIssueTime, publishTime, desc, tag, num, num2, vipInfoResponse, defaultEpiResponse, castResponse, z, type4k, i11, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoResponse)) {
            return false;
        }
        VideoResponse videoResponse = (VideoResponse) obj;
        return Intrinsics.areEqual(this.reason, videoResponse.reason) && this.chnId == videoResponse.chnId && Intrinsics.areEqual(this.chnName, videoResponse.chnName) && Intrinsics.areEqual(this.qipuId, videoResponse.qipuId) && Intrinsics.areEqual(this.albumId, videoResponse.albumId) && Intrinsics.areEqual(this.albumName, videoResponse.albumName) && Intrinsics.areEqual(this.name, videoResponse.name) && Intrinsics.areEqual(this.subTitle, videoResponse.subTitle) && Intrinsics.areEqual(this.focus, videoResponse.focus) && Intrinsics.areEqual(this.albumPic, videoResponse.albumPic) && Intrinsics.areEqual(this.posterPic, videoResponse.posterPic) && Intrinsics.areEqual(this.score, videoResponse.score) && Intrinsics.areEqual(this.drm, videoResponse.drm) && this.hot == videoResponse.hot && Intrinsics.areEqual(this.hdr, videoResponse.hdr) && Intrinsics.areEqual(this.shortName, videoResponse.shortName) && Intrinsics.areEqual(this.vipType, videoResponse.vipType) && this.isExclusive == videoResponse.isExclusive && this.qiyiProd == videoResponse.qiyiProd && this.is3D == videoResponse.is3D && this.isDolby == videoResponse.isDolby && this.is1080 == videoResponse.is1080 && this.sourceCode == videoResponse.sourceCode && this.order == videoResponse.order && this.len == videoResponse.len && this.contentType == videoResponse.contentType && Intrinsics.areEqual(this.initIssueTime, videoResponse.initIssueTime) && Intrinsics.areEqual(this.publishTime, videoResponse.publishTime) && Intrinsics.areEqual(this.desc, videoResponse.desc) && Intrinsics.areEqual(this.tag, videoResponse.tag) && Intrinsics.areEqual(this.total, videoResponse.total) && Intrinsics.areEqual(this.count, videoResponse.count) && Intrinsics.areEqual(this.vipInfo, videoResponse.vipInfo) && Intrinsics.areEqual(this.defaultEpi, videoResponse.defaultEpi) && Intrinsics.areEqual(this.cast, videoResponse.cast) && this.isSelect == videoResponse.isSelect && Intrinsics.areEqual(this.type4k, videoResponse.type4k) && this.isAudioMode == videoResponse.isAudioMode && Intrinsics.areEqual(this.atomsEpgs, videoResponse.atomsEpgs);
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

    public final List<AtomsEpgResponse> getAtomsEpgs() {
        return this.atomsEpgs;
    }

    public final CastResponse getCast() {
        return this.cast;
    }

    public final int getChnId() {
        return this.chnId;
    }

    public final String getChnName() {
        return this.chnName;
    }

    public final int getContentType() {
        return this.contentType;
    }

    public final Integer getCount() {
        return this.count;
    }

    public final DefaultEpiResponse getDefaultEpi() {
        return this.defaultEpi;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getDrm() {
        return this.drm;
    }

    public final String getFocus() {
        return this.focus;
    }

    public final String getHdr() {
        return this.hdr;
    }

    public final int getHot() {
        return this.hot;
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

    public final String getPosterPic() {
        return this.posterPic;
    }

    public final String getPublishTime() {
        return this.publishTime;
    }

    public final String getQipuId() {
        return this.qipuId;
    }

    public final int getQiyiProd() {
        return this.qiyiProd;
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getScore() {
        return this.score;
    }

    public final String getShortName() {
        return this.shortName;
    }

    public final long getSourceCode() {
        return this.sourceCode;
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

    public final String getType4k() {
        return this.type4k;
    }

    public final VipInfoResponse getVipInfo() {
        return this.vipInfo;
    }

    public final String getVipType() {
        return this.vipType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v74, types: [int] */
    /* JADX WARN: Type inference failed for: r1v80 */
    /* JADX WARN: Type inference failed for: r1v86 */
    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.reason.hashCode() * 31) + Integer.hashCode(this.chnId)) * 31) + this.chnName.hashCode()) * 31) + this.qipuId.hashCode()) * 31) + this.albumId.hashCode()) * 31) + this.albumName.hashCode()) * 31) + this.name.hashCode()) * 31) + this.subTitle.hashCode()) * 31) + this.focus.hashCode()) * 31) + this.albumPic.hashCode()) * 31) + this.posterPic.hashCode()) * 31) + this.score.hashCode()) * 31) + this.drm.hashCode()) * 31) + Integer.hashCode(this.hot)) * 31) + this.hdr.hashCode()) * 31) + this.shortName.hashCode()) * 31) + this.vipType.hashCode()) * 31) + Integer.hashCode(this.isExclusive)) * 31) + Integer.hashCode(this.qiyiProd)) * 31) + Integer.hashCode(this.is3D)) * 31) + Integer.hashCode(this.isDolby)) * 31) + Integer.hashCode(this.is1080)) * 31) + Long.hashCode(this.sourceCode)) * 31) + Integer.hashCode(this.order)) * 31) + Integer.hashCode(this.len)) * 31) + Integer.hashCode(this.contentType)) * 31) + this.initIssueTime.hashCode()) * 31) + this.publishTime.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.tag.hashCode()) * 31;
        Integer num = this.total;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.count;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        VipInfoResponse vipInfoResponse = this.vipInfo;
        int iHashCode4 = (iHashCode3 + (vipInfoResponse == null ? 0 : vipInfoResponse.hashCode())) * 31;
        DefaultEpiResponse defaultEpiResponse = this.defaultEpi;
        int iHashCode5 = (iHashCode4 + (defaultEpiResponse == null ? 0 : defaultEpiResponse.hashCode())) * 31;
        CastResponse castResponse = this.cast;
        int iHashCode6 = (iHashCode5 + (castResponse == null ? 0 : castResponse.hashCode())) * 31;
        boolean z = this.isSelect;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iHashCode7 = (((((iHashCode6 + r1) * 31) + this.type4k.hashCode()) * 31) + Integer.hashCode(this.isAudioMode)) * 31;
        List<AtomsEpgResponse> list = this.atomsEpgs;
        return iHashCode7 + (list != null ? list.hashCode() : 0);
    }

    public final int is1080() {
        return this.is1080;
    }

    public final int is3D() {
        return this.is3D;
    }

    public final int isAudioMode() {
        return this.isAudioMode;
    }

    public final int isDolby() {
        return this.isDolby;
    }

    public final int isExclusive() {
        return this.isExclusive;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setAtomsEpgs(List<AtomsEpgResponse> list) {
        this.atomsEpgs = list;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoResponse(reason=");
        sb.append(this.reason).append(", chnId=").append(this.chnId).append(", chnName=").append(this.chnName).append(", qipuId=").append(this.qipuId).append(", albumId=").append(this.albumId).append(", albumName=").append(this.albumName).append(", name=").append(this.name).append(", subTitle=").append(this.subTitle).append(", focus=").append(this.focus).append(", albumPic=").append(this.albumPic).append(", posterPic=").append(this.posterPic).append(", score=");
        sb.append(this.score).append(", drm=").append(this.drm).append(", hot=").append(this.hot).append(", hdr=").append(this.hdr).append(", shortName=").append(this.shortName).append(", vipType=").append(this.vipType).append(", isExclusive=").append(this.isExclusive).append(", qiyiProd=").append(this.qiyiProd).append(", is3D=").append(this.is3D).append(", isDolby=").append(this.isDolby).append(", is1080=").append(this.is1080).append(", sourceCode=").append(this.sourceCode);
        sb.append(", order=").append(this.order).append(", len=").append(this.len).append(", contentType=").append(this.contentType).append(", initIssueTime=").append(this.initIssueTime).append(", publishTime=").append(this.publishTime).append(", desc=").append(this.desc).append(", tag=").append(this.tag).append(", total=").append(this.total).append(", count=").append(this.count).append(", vipInfo=").append(this.vipInfo).append(", defaultEpi=").append(this.defaultEpi).append(", cast=");
        sb.append(this.cast).append(", isSelect=").append(this.isSelect).append(", type4k=").append(this.type4k).append(", isAudioMode=").append(this.isAudioMode).append(", atomsEpgs=").append(this.atomsEpgs).append(')');
        return sb.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r38v1 */
    /* JADX WARN: Type inference failed for: r38v2 */
    /* JADX WARN: Type inference failed for: r38v3 */
    /* JADX WARN: Type inference failed for: r79v1, types: [boolean] */
    public /* synthetic */ VideoResponse(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i2, String str13, String str14, String str15, int i3, int i4, int i5, int i6, int i7, long j, int i8, int i9, int i10, String str16, String str17, String str18, String str19, Integer num, Integer num2, VipInfoResponse vipInfoResponse, DefaultEpiResponse defaultEpiResponse, CastResponse castResponse, boolean z, String str20, int i11, List list, int i12, int i13, DefaultConstructorMarker defaultConstructorMarker) {
        int i14;
        Integer num3;
        String str21 = (i12 & 1) != 0 ? "" : str;
        int i15 = (i12 & 2) != 0 ? 0 : i;
        String str22 = (i12 & 4) != 0 ? "" : str2;
        String str23 = (i12 & 8) != 0 ? "" : str3;
        String str24 = (i12 & 16) != 0 ? "" : str4;
        String str25 = (i12 & 32) != 0 ? "" : str5;
        String str26 = (i12 & 64) != 0 ? "" : str6;
        String str27 = (i12 & 128) != 0 ? "" : str7;
        String str28 = (i12 & 256) != 0 ? "" : str8;
        String str29 = (i12 & 512) != 0 ? "" : str9;
        String str30 = (i12 & 1024) != 0 ? "" : str10;
        String str31 = (i12 & 2048) != 0 ? "" : str11;
        String str32 = (i12 & 4096) != 0 ? "" : str12;
        int i16 = (i12 & 8192) != 0 ? 0 : i2;
        String str33 = (i12 & 16384) != 0 ? "" : str13;
        String str34 = (i12 & 32768) != 0 ? "" : str14;
        String str35 = (i12 & 65536) != 0 ? "" : str15;
        int i17 = (i12 & 131072) != 0 ? 0 : i3;
        int i18 = (i12 & 262144) != 0 ? 0 : i4;
        int i19 = (i12 & 524288) != 0 ? 0 : i5;
        int i20 = (i12 & 1048576) != 0 ? 0 : i6;
        int i21 = (i12 & 2097152) != 0 ? 0 : i7;
        long j2 = (i12 & 4194304) != 0 ? 0L : j;
        int i22 = (i12 & 8388608) != 0 ? 0 : i8;
        int i23 = (i12 & 16777216) != 0 ? 0 : i9;
        int i24 = (i12 & SemanticsLevelOneType.CONTROL) != 0 ? 0 : i10;
        String str36 = (i12 & 67108864) != 0 ? "" : str16;
        String str37 = (i12 & 134217728) != 0 ? "" : str17;
        String str38 = (i12 & 268435456) != 0 ? "" : str18;
        String str39 = (i12 & 536870912) != 0 ? "" : str19;
        if ((i12 & 1073741824) != 0) {
            i14 = 0;
            num3 = 0;
        } else {
            i14 = 0;
            num3 = num;
        }
        this(str21, i15, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32, i16, str33, str34, str35, i17, i18, i19, i20, i21, j2, i22, i23, i24, str36, str37, str38, str39, num3, (i12 & Integer.MIN_VALUE) != 0 ? Integer.valueOf(i14) : num2, (i13 & 1) != 0 ? null : vipInfoResponse, (i13 & 2) != 0 ? null : defaultEpiResponse, (i13 & 4) != 0 ? null : castResponse, (i13 & 8) != 0 ? i14 : z, (i13 & 16) != 0 ? "" : str20, (i13 & 32) == 0 ? i11 : i14, (i13 & 64) == 0 ? list : null);
    }
}
