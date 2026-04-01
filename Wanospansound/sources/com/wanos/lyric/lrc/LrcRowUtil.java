package com.wanos.lyric.lrc;

import android.util.Log;
import com.wanos.lyric.lrc.impl.LrcRow;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LrcRowUtil {
    public static final String TAG = "wanos:[LrcRow]";

    public static List<LrcRow> createRows(String str) {
        try {
            if (str.indexOf("[") == 0 && str.contains("]")) {
                int iLastIndexOf = str.lastIndexOf("]") + 1;
                String strSubstring = str.substring(iLastIndexOf);
                String[] strArrSplit = str.substring(0, iLastIndexOf).replace("[", "-").replace("]", "-").split("-");
                ArrayList arrayList = new ArrayList();
                for (String str2 : strArrSplit) {
                    if (str2.trim().length() != 0) {
                        long jTimeConvert = timeConvert(str2);
                        if (jTimeConvert > -1) {
                            LrcRow lrcRow = new LrcRow();
                            lrcRow.setContent(strSubstring);
                            lrcRow.setStartTimeString(str2);
                            lrcRow.setStartTime(jTimeConvert);
                            arrayList.add(lrcRow);
                        }
                    }
                }
                return arrayList;
            }
            return null;
        } catch (Exception e) {
            Log.e("wanos:[LrcRow]", "createRows exception:" + Log.getStackTraceString(e));
            return null;
        }
    }

    private static long timeConvert(String str) {
        String[] strArrSplit = str.replace('.', ':').split(":");
        if (strArrSplit.length < 3) {
            return -1L;
        }
        String str2 = strArrSplit[2];
        if (str2 != null && str2.length() == 1) {
            strArrSplit[2] = strArrSplit[2] + "0";
        }
        try {
            return (Integer.valueOf(strArrSplit[0]).intValue() * 60 * 1000) + (Integer.valueOf(strArrSplit[1]).intValue() * 1000) + Integer.valueOf(strArrSplit[2]).intValue();
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}
