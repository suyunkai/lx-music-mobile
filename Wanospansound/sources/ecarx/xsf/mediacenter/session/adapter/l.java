package ecarx.xsf.mediacenter.session.adapter;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ecarx.eas.sdk.mediacenter.RecommendInfo;
import com.ecarx.eas.sdk.mediacenter.RecommendInfoBean;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import ecarx.xsf.mediacenter.IRecommend;
import ecarx.xsf.mediacenter.session.AppSourceInfo;
import ecarx.xsf.mediacenter.session.MediaItem;
import ecarx.xsf.mediacenter.session.MediaQueue;
import ecarx.xsf.mediacenter.session.proto.nano.Controller;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class l {
    private static Application f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    private int f731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f732c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f733d;
    private int e;

    public static Mediabean.e a(MediaItem mediaItem) {
        Mediabean.e eVar = new Mediabean.e();
        eVar.f769a = mediaItem.getId();
        eVar.f770b = mediaItem.getSourceType();
        eVar.f771c = mediaItem.getTitle();
        eVar.f772d = mediaItem.getSubtitle();
        eVar.e = mediaItem.getAlbum();
        eVar.f = mediaItem.getArtist();
        if (mediaItem.getArtwork() != null) {
            eVar.g = mediaItem.getArtwork().toString();
        }
        if (mediaItem.getLyricUri() != null) {
            eVar.h = mediaItem.getLyricUri().toString();
        }
        eVar.i = mediaItem.getLyricContent();
        eVar.j = mediaItem.getDateTime();
        eVar.k = mediaItem.getDuration();
        eVar.l = mediaItem.isFavoriteSupported();
        eVar.m = mediaItem.isDownloadSupported();
        eVar.n = mediaItem.isVipNeeded();
        eVar.o = mediaItem.isDownloaded();
        eVar.p = mediaItem.isFavorited();
        eVar.q = mediaItem.getRadioFrequency();
        eVar.s = mediaItem.getRadioName();
        eVar.r = mediaItem.getRadioBand();
        if (mediaItem.getArtworkPath() != null) {
            eVar.t = mediaItem.getArtworkPath().toString();
        }
        eVar.u = mediaItem.isLoopModeSupported();
        return eVar;
    }

    public static MediaItem a(Mediabean.e eVar) {
        MediaItem.Builder builder = new MediaItem.Builder();
        builder.id(eVar.f769a);
        builder.sourceType(eVar.f770b);
        builder.title(eVar.f771c);
        builder.subtitle(eVar.f772d);
        builder.album(eVar.e);
        builder.artist(eVar.f);
        if (eVar.g != null) {
            builder.artwork(Uri.parse(eVar.g));
        }
        if (eVar.h != null) {
            builder.lyricUri(Uri.parse(eVar.h));
        }
        builder.lyricContent(eVar.i);
        builder.dateTime(eVar.j);
        builder.duration(eVar.k);
        builder.favoriteSupported(eVar.l);
        builder.downloadSupported(eVar.m);
        builder.vipNeeded(eVar.n);
        builder.downloaded(eVar.o);
        builder.favorited(eVar.p);
        builder.radioFrequency(eVar.q);
        builder.radioName(eVar.s);
        builder.radioBand(eVar.r);
        if (eVar.t != null) {
            builder.artworkPath(Uri.parse(eVar.t));
        }
        builder.loopModeSupported(eVar.u);
        return builder.build();
    }

    public static Mediabean.h a(MediaQueue mediaQueue) {
        Mediabean.h hVar = new Mediabean.h();
        if (mediaQueue == null) {
            return hVar;
        }
        if (mediaQueue.getCover() != null) {
            hVar.e = mediaQueue.getCover().toString();
        }
        hVar.f781a = mediaQueue.getId();
        hVar.f782b = mediaQueue.getQueueType();
        hVar.f784d = mediaQueue.getPkgName();
        hVar.f783c = mediaQueue.getTitle();
        if (mediaQueue.getQueue() != null && mediaQueue.getQueue().size() > 0) {
            hVar.f = new Mediabean.e[mediaQueue.getQueue().size()];
            for (int i = 0; i < mediaQueue.getQueue().size(); i++) {
                hVar.f[i] = a(mediaQueue.getQueue().get(i));
            }
        }
        return hVar;
    }

    public static MediaQueue a(Mediabean.h hVar) {
        MediaQueue.Builder builder = new MediaQueue.Builder();
        builder.id(hVar.f781a);
        builder.title(hVar.f783c);
        builder.queueType(hVar.f782b);
        builder.pkgName(hVar.f784d);
        if (hVar.e != null) {
            builder.cover(Uri.parse(hVar.e));
        }
        ArrayList arrayList = new ArrayList();
        if (hVar.f != null && hVar.f.length > 0) {
            for (int i = 0; i < hVar.f.length; i++) {
                arrayList.add(a(hVar.f[i]));
            }
        }
        builder.queue(arrayList);
        return builder.build();
    }

    public static List<MediaQueue> a(IExContent iExContent) {
        if (iExContent == null || TextUtils.isEmpty(iExContent.getData())) {
            return Collections.emptyList();
        }
        try {
            String data = iExContent.getData();
            Mediabean.h[] hVarArr = Controller.a.a(data != null ? data.getBytes("ISO-8859-1") : new byte[0]).n;
            ArrayList arrayList = new ArrayList();
            if (iExContent.getPendingIntents() == null) {
                ArrayList arrayList2 = new ArrayList(hVarArr.length);
                Collections.fill(arrayList2, null);
                iExContent.setPendingIntents(arrayList2);
            } else if (hVarArr.length != iExContent.getPendingIntents().size()) {
                throw new IllegalArgumentException("queueList length != pendingIntentList size");
            }
            for (int i = 0; i < hVarArr.length; i++) {
                arrayList.add(new MediaQueue.Builder(a(hVarArr[i])).queueIntent(iExContent.getPendingIntents().get(i)).build());
            }
            return arrayList;
        } catch (Exception e) {
            throw new IllegalStateException("Fail to transform IExContent", e);
        }
    }

    public static Mediabean.a a(AppSourceInfo appSourceInfo) {
        byte[] byteArray;
        Mediabean.a aVar = new Mediabean.a();
        if (appSourceInfo == null) {
            return aVar;
        }
        aVar.f754a = appSourceInfo.getPackageName() != null ? appSourceInfo.getPackageName() : "";
        aVar.f755b = appSourceInfo.getIconPath() != null ? appSourceInfo.getIconPath() : "";
        aVar.f756c = appSourceInfo.getAppName() != null ? appSourceInfo.getAppName() : "";
        aVar.f757d = appSourceInfo.getSourceTypeList() != null ? appSourceInfo.getSourceTypeList() : new int[0];
        aVar.e = appSourceInfo.getPriorityLevel();
        aVar.f = appSourceInfo.isRunning();
        Bitmap iconRes = appSourceInfo.getIconRes();
        if (iconRes != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            iconRes.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        } else {
            byteArray = new byte[0];
        }
        aVar.g = byteArray;
        return aVar;
    }

    private static AppSourceInfo a(Mediabean.a aVar) {
        AppSourceInfo.Builder builder = new AppSourceInfo.Builder();
        builder.packageName(!TextUtils.isEmpty(aVar.f754a) ? aVar.f754a : "");
        builder.iconPath(!TextUtils.isEmpty(aVar.f755b) ? aVar.f755b : "");
        builder.appName(TextUtils.isEmpty(aVar.f756c) ? "" : aVar.f756c);
        builder.sourceTypeList(aVar.f757d != null ? aVar.f757d : new int[0]);
        builder.priorityLevel(aVar.e);
        builder.isRunning(aVar.f);
        byte[] bArr = aVar.g;
        builder.iconRes((bArr == null || bArr.length == 0) ? null : BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
        return builder.build();
    }

    public static List<AppSourceInfo> a(Controller.a aVar) {
        ArrayList arrayList = new ArrayList();
        if (aVar.p != null && aVar.p.length != 0) {
            for (Mediabean.a aVar2 : aVar.p) {
                arrayList.add(a(aVar2));
            }
        }
        return arrayList;
    }

    public void a(String str) {
        this.f730a = str;
    }

    @Deprecated
    public void a(int i) {
        this.f731b = i;
    }

    public void b(int i) {
        this.f732c = i;
    }

    public void c(int i) {
        this.f733d = i;
    }

    public void d(int i) {
        this.e = i;
    }

    public String toString() {
        return "FocusInfo{packageName='" + this.f730a + "', displayId=" + this.f731b + ", occupantZoneId=" + this.f732c + ", audioZoneId=" + this.f733d + ", playState=" + this.e + '}';
    }

    public static RecommendInfo a(IRecommend iRecommend) throws RemoteException {
        if (iRecommend == null) {
            return null;
        }
        try {
            RecommendInfoBean recommendInfoBean = new RecommendInfoBean();
            recommendInfoBean.setRecommendType(iRecommend.getRecommendType());
            recommendInfoBean.setId(iRecommend.getId());
            recommendInfoBean.setTitle(iRecommend.getTitle());
            recommendInfoBean.setArtist(iRecommend.getArtist());
            recommendInfoBean.setArtwork(iRecommend.getArtwork());
            recommendInfoBean.setTextDes(iRecommend.getTextDescription());
            return recommendInfoBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int a() {
        try {
            Class<?> cls = Class.forName("com.ecarx.sdk.ECarXAPIBase", true, ClassLoader.getSystemClassLoader());
            for (Field field : cls.getFields()) {
                if ("VERSION_INT".equals(field.getName())) {
                    field.setAccessible(true);
                    return field.getInt(cls);
                }
            }
            return 346;
        } catch (Throwable unused) {
            return 346;
        }
    }

    private static Application c() {
        try {
            Class<?> cls = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME);
            Object objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) objInvoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    public static Application b() {
        Application application = f;
        if (application != null) {
            return application;
        }
        Application applicationC = c();
        if (f == null) {
            if (applicationC == null) {
                f = c();
            } else {
                f = applicationC;
            }
        }
        return applicationC;
    }
}
