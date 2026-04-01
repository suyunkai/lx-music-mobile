package com.wanos.media.viewmodel;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ZeroPageMode;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxPlayerInfoEditViewModel extends ViewModel {
    public static final String KEY_BACKGROUND_IMAGE = "bg_image";
    public static final String KEY_IS_ME = "is_me";
    public static final String KEY_OPEN_MODE = "mode";
    public static final String KEY_THEME_ID = "theme_id";
    public static final String KEY_THEME_INFO = "theme_info";
    public static final String KEY_THEME_NAME = "theme_name";
    private final MutableLiveData<Integer> _BottomOptionVisibilityState;
    private final MutableLiveData<Integer> _CollectBtnVisibilityState;
    public final LiveData<Integer> bottomOptionVisibilityState;
    public final LiveData<Integer> collectBtnVisibilityState;
    private final boolean isMe;
    private final ZeroPageMode openMode;
    private final String themeBgImage;
    private final long themeId;
    private AudioSceneInfoEntity themeInfo;
    private final String themeName;

    public RelaxPlayerInfoEditViewModel(Bundle bundle) {
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(8);
        this._CollectBtnVisibilityState = mutableLiveData;
        this.collectBtnVisibilityState = mutableLiveData;
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(0);
        this._BottomOptionVisibilityState = mutableLiveData2;
        this.bottomOptionVisibilityState = mutableLiveData2;
        if (bundle == null) {
            throw new NullPointerException("Open Edit Fragment Error, Lack Params");
        }
        this.themeName = bundle.getString(KEY_THEME_NAME);
        this.themeBgImage = bundle.getString(KEY_BACKGROUND_IMAGE);
        this.isMe = bundle.getBoolean(KEY_IS_ME, false);
        this.themeId = bundle.getLong(KEY_THEME_ID, -1L);
        this.openMode = (ZeroPageMode) bundle.getSerializable(KEY_OPEN_MODE);
        this.themeInfo = (AudioSceneInfoEntity) bundle.getSerializable(KEY_THEME_INFO);
        if (isMe()) {
            mutableLiveData.setValue(8);
        } else {
            mutableLiveData.setValue(0);
        }
    }

    public String getThemeName() {
        return this.themeName;
    }

    public String getThemeBgImage() {
        return this.themeBgImage;
    }

    public boolean isMe() {
        return this.isMe;
    }

    public long getThemeId() {
        return this.themeId;
    }

    public ZeroPageMode getOpenMode() {
        return this.openMode;
    }

    public AudioSceneInfoEntity getThemeInfo() {
        return this.themeInfo;
    }

    public void setThemeInfo(AudioSceneInfoEntity audioSceneInfoEntity) {
        this.themeInfo = audioSceneInfoEntity;
    }

    public void setCollectBtnVisibilityState(int i) {
        this._CollectBtnVisibilityState.setValue(Integer.valueOf(i));
    }

    public void setBottomOptionVisibilityState(int i) {
        this._BottomOptionVisibilityState.setValue(Integer.valueOf(i));
    }
}
