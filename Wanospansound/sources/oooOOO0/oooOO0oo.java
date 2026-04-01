package oooOOO0;

import com.arcvideo.ivi.res.sdk.data.Actor;
import com.arcvideo.ivi.res.sdk.data.BannerResponse;
import com.arcvideo.ivi.res.sdk.data.DefaultEpi;
import com.arcvideo.ivi.res.sdk.data.Director;
import com.arcvideo.ivi.res.sdk.data.RankRequestBean;
import com.arcvideo.ivi.res.sdk.data.TagChildrenInfo;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.arcvideo.ivi.res.sdk.data.VideoTagInfo;
import com.arcvideo.ivi.res.sdk.innerbean.CastResponse;
import com.arcvideo.ivi.res.sdk.innerbean.ChildrenResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DefaultEpiResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DirectorResponse;
import com.arcvideo.ivi.res.sdk.innerbean.MainActorResponse;
import com.arcvideo.ivi.res.sdk.innerbean.TagResponse;
import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOO0oo {

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public static final oooOO0oo f863oooOO0oo = new oooOO0oo();

    public final List<VideoTagInfo> oooOO(List<TagResponse> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (TagResponse tagResponse : list) {
                VideoTagInfo videoTagInfo = new VideoTagInfo();
                videoTagInfo.setId(tagResponse.getId());
                videoTagInfo.setName(tagResponse.getName());
                ArrayList arrayList2 = new ArrayList();
                for (ChildrenResponse childrenResponse : tagResponse.getChildren()) {
                    TagChildrenInfo tagChildrenInfo = new TagChildrenInfo();
                    tagChildrenInfo.setId(childrenResponse.getId());
                    tagChildrenInfo.setName(childrenResponse.getName());
                    arrayList2.add(tagChildrenInfo);
                }
                videoTagInfo.setChildren(arrayList2);
                arrayList.add(videoTagInfo);
            }
        }
        return arrayList;
    }

    public final RankRequestBean oooOO0oo(int i, boolean z, int i2) {
        RankRequestBean rankRequestBean = new RankRequestBean(0, 0, null, 7, null);
        if (i == 0) {
            rankRequestBean.setChnId(-1);
        } else {
            rankRequestBean.setChnId(i);
        }
        if (z) {
            rankRequestBean.setVip(1);
        } else {
            rankRequestBean.setVip(0);
        }
        if (i2 == 1) {
            rankRequestBean.setType("rise");
        } else {
            rankRequestBean.setType("hot");
        }
        return rankRequestBean;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.arcvideo.ivi.res.sdk.data.VideoInfo> oooOOO00(java.util.List<com.arcvideo.ivi.res.sdk.innerbean.VideoResponse> r4) {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r4 == 0) goto L10
            boolean r1 = r4.isEmpty()
            r2 = 1
            r1 = r1 ^ r2
            if (r1 != r2) goto L10
            goto L11
        L10:
            r2 = 0
        L11:
            if (r2 == 0) goto L2d
            java.util.Iterator r4 = r4.iterator()
        L17:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L2d
            java.lang.Object r1 = r4.next()
            com.arcvideo.ivi.res.sdk.innerbean.VideoResponse r1 = (com.arcvideo.ivi.res.sdk.innerbean.VideoResponse) r1
            oooOOO0.oooOO0oo r2 = oooOOO0.oooOO0oo.f863oooOO0oo
            com.arcvideo.ivi.res.sdk.data.VideoInfo r1 = r2.oooOO0oo(r1)
            r0.add(r1)
            goto L17
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: oooOOO0.oooOO0oo.oooOOO00(java.util.List):java.util.List");
    }

    public final VideoInfo oooOO0oo(VideoResponse videoResponse) {
        VideoInfo videoInfo = new VideoInfo();
        if (videoResponse != null) {
            videoInfo.setAlbumId(videoResponse.getAlbumId());
            videoInfo.setAlbumPic(videoResponse.getAlbumPic());
            videoInfo.setChnId(videoResponse.getChnId());
            videoInfo.setDesc(videoResponse.getDesc());
            videoInfo.setChnName(videoResponse.getChnName());
            videoInfo.setFocus(videoResponse.getFocus());
            videoInfo.setSubTitle(videoResponse.getSubTitle());
            videoInfo.setName(videoResponse.getName());
            String albumName = videoResponse.getAlbumName();
            if (albumName.length() == 0) {
                albumName = videoResponse.getName();
            }
            videoInfo.setAlbumName(albumName);
            videoInfo.setTvId(videoResponse.getQipuId());
            videoInfo.setProd(videoResponse.getQiyiProd());
            videoInfo.setVip(videoResponse.getVipInfo() != null ? videoResponse.getVipInfo().isVip() : 0);
            videoInfo.setExclusive(videoResponse.isExclusive());
            videoInfo.setPublishTime(videoResponse.getPublishTime());
            videoInfo.setLen(videoResponse.getLen());
            videoInfo.setOrder(videoResponse.getOrder());
            videoInfo.setScore(videoResponse.getScore());
            videoInfo.setTag(videoResponse.getTag());
            videoInfo.setTotal(videoResponse.getTotal());
            videoInfo.setCount(videoResponse.getCount());
            videoInfo.setHot(Integer.valueOf(videoResponse.getHot()));
            videoInfo.setInitIssueTime(videoResponse.getInitIssueTime());
            videoInfo.setContentType(Integer.valueOf(videoResponse.getContentType()));
            videoInfo.setShortTitle(videoResponse.getShortName());
            DefaultEpiResponse defaultEpi = videoResponse.getDefaultEpi();
            if (defaultEpi != null) {
                DefaultEpi defaultEpi2 = new DefaultEpi();
                defaultEpi2.setQipuId(defaultEpi.getQipuId());
                defaultEpi2.setName(defaultEpi.getName());
                defaultEpi2.set3D(defaultEpi.is3D());
                defaultEpi2.setContentType(defaultEpi.getContentType());
                defaultEpi2.setPublishTime(defaultEpi.getPublishTime());
                defaultEpi2.setLen(defaultEpi.getLen());
                videoInfo.setDefaultEpi(defaultEpi2);
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            CastResponse cast = videoResponse.getCast();
            if (cast != null) {
                List<MainActorResponse> mainActor = cast.getMainActor();
                if (mainActor != null) {
                    for (MainActorResponse mainActorResponse : mainActor) {
                        Actor actor = new Actor();
                        actor.setCover(mainActorResponse.getCover());
                        actor.setId(mainActorResponse.getId());
                        actor.setName(mainActorResponse.getN());
                        actor.setCharacter(mainActorResponse.getCharacter());
                        arrayList.add(actor);
                    }
                }
                List<DirectorResponse> director = cast.getDirector();
                if (director != null) {
                    for (DirectorResponse directorResponse : director) {
                        String strComponent1 = directorResponse.component1();
                        String strComponent2 = directorResponse.component2();
                        Director director2 = new Director();
                        director2.setId(strComponent1);
                        director2.setName(strComponent2);
                        arrayList2.add(director2);
                    }
                }
            }
            videoInfo.setActor(arrayList);
            videoInfo.setDirector(arrayList2);
        }
        return videoInfo;
    }

    public final List<VideoInfo> oooOO0oo(List<BannerResponse> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BannerResponse bannerResponse : list) {
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.setAlbumPic(bannerResponse.getAlbumPic());
                videoInfo.setAlbumId(bannerResponse.getAlbumId());
                videoInfo.setAlbumName(bannerResponse.getAlbumName());
                String albumName = videoInfo.getAlbumName();
                if (albumName == null || albumName.length() == 0) {
                    videoInfo.setAlbumName(bannerResponse.getName());
                }
                videoInfo.setTvId(bannerResponse.getQipuId());
                videoInfo.setType(Integer.valueOf(bannerResponse.getType()));
                if (bannerResponse.getHtml() != null) {
                    videoInfo.setHtmlUrl(bannerResponse.getHtml().getUrl());
                }
                if (bannerResponse.getVideo() != null) {
                    videoInfo.setChnId(bannerResponse.getVideo().getChnId());
                    videoInfo.setChnName(bannerResponse.getVideo().getChnName());
                    videoInfo.setScore(bannerResponse.getVideo().getScore());
                    BannerResponse.DefaultEpi defaultEpi = bannerResponse.getVideo().getDefaultEpi();
                    if (defaultEpi != null) {
                        DefaultEpi defaultEpi2 = new DefaultEpi();
                        defaultEpi2.setQipuId(defaultEpi.getQipuId());
                        defaultEpi2.setName(defaultEpi.getName());
                        defaultEpi2.set3D(defaultEpi.is3D());
                        defaultEpi2.setContentType(defaultEpi.getContentType());
                        defaultEpi2.setPublishTime(defaultEpi.getPublishTime());
                        videoInfo.setDefaultEpi(defaultEpi2);
                    }
                }
                arrayList.add(videoInfo);
            }
        }
        return arrayList;
    }
}
