package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetWanOSWallpaperListResponse extends BaseResponse {
    public WallpaperListBean data;

    public class WallpaperListBean {
        public List<WallpaperListDetail> wallpaperList;

        public WallpaperListBean() {
        }
    }

    public class WallpaperListDetail {
        public String coverUrl;
        public int goodsId;
        public boolean isPurchased;
        public String name;
        public float price;
        public String type;
        public int wallpaperId;

        public WallpaperListDetail() {
        }
    }
}
