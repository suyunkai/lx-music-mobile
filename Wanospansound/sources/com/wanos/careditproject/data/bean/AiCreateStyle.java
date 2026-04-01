package com.wanos.careditproject.data.bean;

import android.util.Log;
import com.wanos.careditproject.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class AiCreateStyle {
    public static final HashMap<String, Integer> STYLE_DATA;
    private static final String TAG = "wanos[AI]-AiCreateStyle";

    static {
        HashMap<String, Integer> map = new HashMap<>();
        STYLE_DATA = map;
        map.put("摇滚", Integer.valueOf(R.raw.ai_shizi));
        map.put("新世纪", Integer.valueOf(R.raw.ai_hu_die));
        map.put("乡村", Integer.valueOf(R.raw.ai_jun_ma));
        map.put("朋克", Integer.valueOf(R.raw.ai_ye_zhu));
        map.put("民谣", Integer.valueOf(R.raw.ai_lu));
        map.put("流行", Integer.valueOf(R.raw.ai_yin_wu));
        map.put("拉丁", Integer.valueOf(R.raw.ai_huo_lie_niao));
        map.put("爵士", Integer.valueOf(R.raw.ai_hei_bao));
        map.put("古典", Integer.valueOf(R.raw.ai_ying));
        map.put("电子", Integer.valueOf(R.raw.ai_tang_lang));
        map.put("迪斯科", Integer.valueOf(R.raw.ai_ge_zi));
        map.put("布鲁斯", Integer.valueOf(R.raw.ai_lao_lang));
        map.put("R&B", Integer.valueOf(R.raw.ai_hei_mao));
        map.put("雷鬼", Integer.valueOf(R.raw.ai_shu_lan));
        map.put("放克", Integer.valueOf(R.raw.ai_qin_wa));
    }

    public static int getAnimationRawId(String str) {
        Log.d(TAG, "getAnimationRawId: style = " + str);
        Integer orDefault = STYLE_DATA.getOrDefault(str, Integer.valueOf(getRandomAnimationRes()));
        if (orDefault == null) {
            return getRandomAnimationRes();
        }
        return orDefault.intValue();
    }

    private static int getRandomAnimationRes() {
        Log.w(TAG, "getAnimationRawId: 获取随机样式");
        ArrayList arrayList = new ArrayList(STYLE_DATA.values());
        if (arrayList.isEmpty()) {
            return R.raw.ai_ge_zi;
        }
        Collections.shuffle(arrayList);
        return ((Integer) arrayList.get(0)).intValue();
    }
}
