package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetWanOSWallpaperVipListResponse extends BaseResponse {
    public WallpaperVipListBean data;

    public class WallpaperVipListBean {
        public List<WallpaperVipListDetail> wallpaperList;

        public WallpaperVipListBean() {
        }
    }

    public class WallpaperVipListDetail {
        public String coverUrl;
        public int goodsId;
        public boolean isPurchased = true;
        public String name;
        public float price;
        public String type;
        public int wallpaperId;

        public WallpaperVipListDetail() {
        }
    }
}
