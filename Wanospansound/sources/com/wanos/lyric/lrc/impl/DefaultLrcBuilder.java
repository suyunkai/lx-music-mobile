package com.wanos.lyric.lrc.impl;

import android.util.Log;
import androidx.media3.exoplayer.Renderer;
import com.wanos.lyric.lrc.ILrcBuilder;
import com.wanos.lyric.lrc.LrcRowUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultLrcBuilder implements ILrcBuilder {
    static final String TAG = "wanos:[DefaultLrcBuilder]";

    @Override // com.wanos.lyric.lrc.ILrcBuilder
    public List<LrcRow> getLrcRows(String str) {
        String line;
        List<LrcRow> listCreateRows;
        Log.d(TAG, "getLrcRows by rawString");
        if (str == null || str.length() == 0) {
            Log.e(TAG, "getLrcRows rawLrc null or empty");
            return null;
        }
        StringReader stringReader = new StringReader(str);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        ArrayList arrayList = new ArrayList();
        do {
            try {
                try {
                    line = bufferedReader.readLine();
                    if (line != null && line.length() > 0 && (listCreateRows = LrcRowUtil.createRows(line)) != null && listCreateRows.size() > 0) {
                        Iterator<LrcRow> it = listCreateRows.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next());
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "parse exceptioned:" + Log.getStackTraceString(e));
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    stringReader.close();
                    return null;
                }
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                stringReader.close();
            }
        } while (line != null);
        if (arrayList.size() > 0) {
            Collections.sort(arrayList);
            if (arrayList.size() > 0) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    LrcRow lrcRow = (LrcRow) arrayList.get(i);
                    if (i < size - 1) {
                        lrcRow.setEndTime(((LrcRow) arrayList.get(i + 1)).getStartTime());
                    } else {
                        lrcRow.setEndTime(lrcRow.getStartTime() + Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
                    }
                }
            }
        }
        return arrayList;
    }
}
