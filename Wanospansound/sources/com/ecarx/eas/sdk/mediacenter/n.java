package com.ecarx.eas.sdk.mediacenter;

import android.net.Uri;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IRecommend;

/* JADX INFO: loaded from: classes2.dex */
public final class n extends IRecommend.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RecommendInfo f184a;

    n(RecommendInfo recommendInfo) {
        this.f184a = recommendInfo;
    }

    private RecommendInfo a() {
        RecommendInfo recommendInfo = this.f184a;
        if (recommendInfo == null) {
            return null;
        }
        return recommendInfo;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final int getRecommendType() throws RemoteException {
        if (a() != null) {
            return a().getRecommendType();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final String getId() throws RemoteException {
        if (a() != null) {
            return a().getId();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final String getTitle() throws RemoteException {
        if (a() != null) {
            return a().getTitle();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final String getArtist() throws RemoteException {
        if (a() != null) {
            return a().getArtist();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final Uri getArtwork() throws RemoteException {
        if (a() != null) {
            return a().getArtwork();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IRecommend
    public final String getTextDescription() throws RemoteException {
        if (a() != null) {
            return a().getTextDescription();
        }
        return null;
    }
}
