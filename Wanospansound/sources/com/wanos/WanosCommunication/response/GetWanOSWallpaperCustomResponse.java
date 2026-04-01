package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetWanOSWallpaperCustomResponse extends BaseResponse {
    public WallpaperCustomBean data;

    public class WallpaperCustomBean {
        public String name;
        public String soundThemeIntro;
        public List<WallpaperCustomDetail> tabList;
        public String type;

        public WallpaperCustomBean() {
        }
    }

    public class WallpaperCustomDetail {
        public String name;
        public int tabId;

        public WallpaperCustomDetail() {
        }
    }
}
