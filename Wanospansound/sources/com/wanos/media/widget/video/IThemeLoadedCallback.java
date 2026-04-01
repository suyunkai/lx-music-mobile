package com.wanos.media.widget.video;

import com.wanos.media.entity.ThemeInfoEntity;

/* JADX INFO: loaded from: classes3.dex */
public interface IThemeLoadedCallback {
    void onThemeDurationInfo(String[] strArr);

    void onThemeErrorInfo(String str);

    void onThemeInfo(ThemeInfoEntity themeInfoEntity);

    void onVideoInfo(ThemeInfoEntity themeInfoEntity);
}
