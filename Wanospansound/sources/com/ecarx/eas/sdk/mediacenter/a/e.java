package com.ecarx.eas.sdk.mediacenter.a;

import android.util.Log;
import com.ecarx.eas.sdk.vr.music.MusicPlayIntent;
import ecarx.xsf.mediacenter.vr.QMusicResult;

/* JADX INFO: loaded from: classes2.dex */
public class e extends MusicPlayIntent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f111a = "e";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private QMusicResult f112b;

    public e(QMusicResult qMusicResult) {
        this.f112b = qMusicResult;
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicPlayIntent
    public String getRawText() {
        try {
            return this.f112b.rawText;
        } catch (Exception e) {
            Log.e(f111a, " getRawText error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getSourceType() {
        try {
            return this.f112b.sourceType;
        } catch (Exception e) {
            Log.e(f111a, " getSourceType error : " + e.getMessage());
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getAlbum() {
        try {
            return this.f112b.album;
        } catch (Exception e) {
            Log.e(f111a, " getAlbum error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getArtist() {
        try {
            return this.f112b.artist;
        } catch (Exception e) {
            Log.e(f111a, " getArtist error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getTitle() {
        try {
            return this.f112b.song;
        } catch (Exception e) {
            Log.e(f111a, " getTitle error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getWeakMatch() {
        try {
            return this.f112b.weakmatch;
        } catch (Exception e) {
            Log.e(f111a, " getWeakMatch error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getTargetPlayType() {
        try {
            return this.f112b.targetPlayType;
        } catch (Exception e) {
            Log.e(f111a, " getTargetPlayType error : " + e.getMessage());
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getItems() {
        try {
            return this.f112b.items;
        } catch (Exception e) {
            Log.e(f111a, " getItems error : " + e.getMessage());
            return "";
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getAuthor() {
        try {
            return this.f112b.author;
        } catch (Exception e) {
            Log.e(f111a, " getAuthor error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getComposer() {
        try {
            return this.f112b.composer;
        } catch (Exception e) {
            Log.e(f111a, " getComposer error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getDescription() {
        try {
            return this.f112b.description;
        } catch (Exception e) {
            Log.e(f111a, " getDescription error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getSubtitle() {
        try {
            return this.f112b.subtitle;
        } catch (Exception e) {
            Log.e(f111a, " getSubtitle error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getRating() {
        try {
            return this.f112b.rating;
        } catch (Exception e) {
            Log.e(f111a, " getRating error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getYear() {
        try {
            return this.f112b.year;
        } catch (Exception e) {
            Log.e(f111a, " getYear error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getAlbumIndex() {
        try {
            return this.f112b.albumIndex;
        } catch (Exception e) {
            Log.e(f111a, " getAlbumIndex error : " + e.getMessage());
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getCategoryStr() {
        try {
            return this.f112b.categoryStr;
        } catch (Exception e) {
            Log.e(f111a, " getCategoryStr error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getSubCategoryStr() {
        try {
            return this.f112b.subCategoryStr;
        } catch (Exception e) {
            Log.e(f111a, " getSubCategoryStr error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public int getMediaType() {
        try {
            return this.f112b.mediaType;
        } catch (Exception e) {
            Log.e(f111a, " getMediaType error : " + e.getMessage());
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getMediaId() {
        try {
            return this.f112b.mediaId;
        } catch (Exception e) {
            Log.e(f111a, " getMediaId error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getMediaCp() {
        try {
            return this.f112b.mediaCp;
        } catch (Exception e) {
            Log.e(f111a, " getMediaCp error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.music.MusicIntent
    public String getTargetType() {
        try {
            return this.f112b.targetType;
        } catch (Exception e) {
            Log.e(f111a, " getTargetType error : " + e.getMessage());
            return null;
        }
    }
}
