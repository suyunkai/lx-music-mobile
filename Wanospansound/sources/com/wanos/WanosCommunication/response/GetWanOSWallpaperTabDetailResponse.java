package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetWanOSWallpaperTabDetailResponse extends BaseResponse {
    public WallpaperTabDetailBean data;

    public class WallpaperTabDetailBean {
        public List<WallpaperSoundEffect> soundEffectList;

        public WallpaperTabDetailBean() {
        }
    }

    public class WallpaperSoundEffect {
        public boolean isCustom;
        public int soundEffectId;
        public String soundEffectName;
        public String soundEffectUrl;
        public int tabInfoId;
        public String tabInfoName;

        public WallpaperSoundEffect() {
        }
    }
}
