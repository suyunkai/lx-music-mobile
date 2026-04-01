package com.wanos.media.widget.video;

import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ThemeInfoEntity;

/* JADX INFO: loaded from: classes3.dex */
public interface ISceneLoadedCallback {
    void onSceneErrorInfo(String str);

    void onSceneInfo(ThemeInfoEntity themeInfoEntity, AudioSceneInfoEntity audioSceneInfoEntity);
}
