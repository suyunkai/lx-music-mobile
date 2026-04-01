package com.wanos.media.repository;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.entity.ZeroThemeListEntity;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.util.ZeroPressureDataManager;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxHomeRepo {
    private static final int ALL_DATA = Integer.MAX_VALUE;
    private static final String TAG = "RelaxHomeRepo";

    public List<SpaceThemeBaseInfo> getXiaoQiThemeList() {
        BaseEntity<ZeroThemeListEntity> themeListSync = HttpTools.getInstance().getThemeListSync(1, 1, Integer.MAX_VALUE);
        String errorResult = HttpTools.formatErrorResult(themeListSync);
        if (!StringUtils.isEmpty(errorResult) || themeListSync == null) {
            ZeroLogcatTools.e(TAG, "getXiaoQiThemeList: ErrorMsg=" + errorResult);
            throw new RuntimeException(errorResult);
        }
        List<SpaceThemeBaseInfo> themeList = themeListSync.getData().getThemeList();
        ArrayList<ZeroThemeInfo> arrayList = new ArrayList<>();
        for (SpaceThemeBaseInfo spaceThemeBaseInfo : themeList) {
            spaceThemeBaseInfo.setEnterWay(1001);
            spaceThemeBaseInfo.setItemType(3002);
            arrayList.add(new ZeroThemeInfo(spaceThemeBaseInfo.getThemeId(), spaceThemeBaseInfo.getName(), spaceThemeBaseInfo.getBgImg(), spaceThemeBaseInfo.isVip()));
        }
        ZeroPressureDataManager.getInstance().setXqData(arrayList);
        return themeList;
    }

    public List<SpaceThemeBaseInfo> getMingXiangThemeList() {
        BaseEntity<ZeroThemeListEntity> themeListSync = HttpTools.getInstance().getThemeListSync(2, 1, Integer.MAX_VALUE);
        String errorResult = HttpTools.formatErrorResult(themeListSync);
        if (!StringUtils.isEmpty(errorResult) || themeListSync == null) {
            ZeroLogcatTools.e(TAG, "getMingXiangThemeList: ErrorMsg=" + errorResult);
            throw new RuntimeException(errorResult);
        }
        List<SpaceThemeBaseInfo> themeList = themeListSync.getData().getThemeList();
        ArrayList<ZeroThemeInfo> arrayList = new ArrayList<>();
        for (SpaceThemeBaseInfo spaceThemeBaseInfo : themeList) {
            spaceThemeBaseInfo.setEnterWay(2001);
            spaceThemeBaseInfo.setItemType(3002);
            arrayList.add(new ZeroThemeInfo(spaceThemeBaseInfo.getThemeId(), spaceThemeBaseInfo.getName(), spaceThemeBaseInfo.getBgImg(), spaceThemeBaseInfo.isVip()));
        }
        ZeroPressureDataManager.getInstance().setMxData(arrayList);
        return themeList;
    }

    public List<SpaceThemeBaseInfo> getCollectThemeList(int i, int i2, int i3) {
        return formatCollectThemeList(getCollectTheme(i, i2, i3));
    }

    public ZeroThemeListEntity getCollectTheme(int i, int i2, int i3) {
        BaseEntity<ZeroThemeListEntity> userThemeListSync = HttpTools.getInstance().getUserThemeListSync(i, i2, i3);
        String errorResult = HttpTools.formatErrorResult(userThemeListSync);
        if (!StringUtils.isEmpty(errorResult) || userThemeListSync == null) {
            ZeroLogcatTools.e(TAG, "getCollectThemeList: ErrorMsg=" + errorResult);
            throw new RuntimeException(errorResult);
        }
        return userThemeListSync.getData();
    }

    public List<SpaceThemeBaseInfo> formatCollectThemeList(ZeroThemeListEntity zeroThemeListEntity) {
        List<SpaceThemeBaseInfo> themeList = zeroThemeListEntity.getThemeList();
        if (themeList == null || themeList.isEmpty()) {
            return Collections.emptyList();
        }
        for (SpaceThemeBaseInfo spaceThemeBaseInfo : themeList) {
            if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(spaceThemeBaseInfo.getRootThemeType())) {
                spaceThemeBaseInfo.setEnterWay(1002);
                spaceThemeBaseInfo.setThemeTypeResId(R.drawable.icon_relax_xiao_qi);
            } else if (ExifInterface.GPS_MEASUREMENT_2D.equals(spaceThemeBaseInfo.getRootThemeType())) {
                spaceThemeBaseInfo.setEnterWay(2002);
                spaceThemeBaseInfo.setThemeTypeResId(R.drawable.icon_relax_ming_xiang);
            } else {
                ZeroLogcatTools.d(TAG, "错误模板类型：SourceThemeType = " + spaceThemeBaseInfo.getRootThemeType());
            }
            spaceThemeBaseInfo.setItemType(3004);
        }
        return themeList;
    }
}
