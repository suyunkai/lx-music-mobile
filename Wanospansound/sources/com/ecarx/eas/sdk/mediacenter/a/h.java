package com.ecarx.eas.sdk.mediacenter.a;

import android.util.Log;
import com.ecarx.eas.sdk.vr.music.MusicSearchIntent;
import ecarx.xsf.mediacenter.vr.QMusicResult;

/* JADX INFO: loaded from: classes2.dex */
public class h extends MusicSearchIntent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f115a = "h";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private QMusicResult f116b;

    public h(QMusicResult qMusicResult) {
        this.f116b = qMusicResult;
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicSearchIntent
    public String getRawText() {
        try {
            return this.f116b.rawText;
        } catch (Exception e) {
            Log.e(f115a, " getRawText error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getSourceType() {
        try {
            return this.f116b.sourceType;
        } catch (Exception e) {
            Log.e(f115a, " getSourceType error : " + e.getMessage());
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getAlbum() {
        try {
            return this.f116b.album;
        } catch (Exception e) {
            Log.e(f115a, " getAlbum error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getArtist() {
        try {
            return this.f116b.artist;
        } catch (Exception e) {
            Log.e(f115a, " getArtist error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getTitle() {
        try {
            return this.f116b.song;
        } catch (Exception e) {
            Log.e(f115a, " getTitle error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getWeakMatch() {
        try {
            return this.f116b.weakmatch;
        } catch (Exception e) {
            Log.e(f115a, " getWeakMatch error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getTargetPlayType() {
        try {
            return this.f116b.targetPlayType;
        } catch (Exception e) {
            Log.e(f115a, " getTargetPlayType error : " + e.getMessage());
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getItems() {
        try {
            return this.f116b.items;
        } catch (Exception e) {
            Log.e(f115a, " getItems error : " + e.getMessage());
            return "";
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getAuthor() {
        try {
            return this.f116b.author;
        } catch (Exception e) {
            Log.e(f115a, " getAuthor error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getComposer() {
        try {
            return this.f116b.composer;
        } catch (Exception e) {
            Log.e(f115a, " getComposer error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getDescription() {
        try {
            return this.f116b.description;
        } catch (Exception e) {
            Log.e(f115a, " getDescription error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getSubtitle() {
        try {
            return this.f116b.subtitle;
        } catch (Exception e) {
            Log.e(f115a, " getSubtitle error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getRating() {
        try {
            return this.f116b.rating;
        } catch (Exception e) {
            Log.e(f115a, " getRating error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getYear() {
        try {
            return this.f116b.year;
        } catch (Exception e) {
            Log.e(f115a, " getYear error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getAlbumIndex() {
        try {
            return this.f116b.albumIndex;
        } catch (Exception e) {
            Log.e(f115a, " getAlbumIndex error : " + e.getMessage());
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getCategoryStr() {
        try {
            return this.f116b.categoryStr;
        } catch (Exception e) {
            Log.e(f115a, " getCategoryStr error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getSubCategoryStr() {
        try {
            return this.f116b.subCategoryStr;
        } catch (Exception e) {
            Log.e(f115a, " getSubCategoryStr error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getMediaType() {
        try {
            return this.f116b.mediaType;
        } catch (Exception e) {
            Log.e(f115a, " getMediaType error : " + e.getMessage());
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getMediaId() {
        try {
            return this.f116b.mediaId;
        } catch (Exception e) {
            Log.e(f115a, " getMediaId error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getMediaCp() {
        try {
            return this.f116b.mediaCp;
        } catch (Exception e) {
            Log.e(f115a, " getMediaCp error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getTargetType() {
        try {
            return this.f116b.targetType;
        } catch (Exception e) {
            Log.e(f115a, " getMediaCp error : " + e.getMessage());
            return null;
        }
    }
}
