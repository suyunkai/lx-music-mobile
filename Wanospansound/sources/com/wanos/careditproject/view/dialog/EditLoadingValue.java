package com.wanos.careditproject.view.dialog;

import android.util.ArrayMap;
import com.wanos.careditproject.utils.EditingUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class EditLoadingValue {
    public static int decodeFileTotal = 200;
    private static EditLoadingValue instance = null;
    public static int loadFileTotal = 700;
    public static int loadJsonTotal = 100;
    private static int seekBarTotal = 1000;
    private EditLoadingDialog cur;
    private LoadStatus downloadStatus;
    private int resFileCount = 0;
    private Map<String, Integer> url2Progress = new ArrayMap();
    private Map<String, Long> url2DecodeSize = new ArrayMap();
    private Map<String, Long> url2DecodedSize = new ArrayMap();
    private Map<String, Integer> url2DecodeProgress = new ArrayMap();
    private int curProgress = 0;
    private int loadFileStep = 2;
    private int decodeFileStep = 1;

    public enum LoadStatus {
        LOADJSON,
        LOADFILE
    }

    public static EditLoadingValue getInstance() {
        if (instance == null) {
            instance = new EditLoadingValue();
        }
        return instance;
    }

    public void setLoadingDialog(EditLoadingDialog editLoadingDialog) {
        this.cur = editLoadingDialog;
    }

    public void setResFileCount(int i) {
        this.resFileCount = i;
    }

    public int getProgress() {
        if (this.downloadStatus == LoadStatus.LOADJSON) {
            int i = this.curProgress + 1;
            this.curProgress = i;
            int i2 = loadJsonTotal;
            if (i > i2) {
                this.curProgress = i2;
            }
        } else {
            this.curProgress = loadJsonTotal;
            String str = "";
            for (Map.Entry<String, Integer> entry : this.url2Progress.entrySet()) {
                entry.getKey();
                int iIntValue = entry.getValue().intValue();
                this.curProgress += iIntValue;
                str = str + "," + iIntValue;
            }
            Iterator<Map.Entry<String, Integer>> it = this.url2DecodeProgress.entrySet().iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().getValue().intValue();
                this.curProgress += iIntValue2;
                str = str + "," + iIntValue2;
            }
        }
        return this.curProgress;
    }

    public void startLoadJson() {
        this.curProgress = 0;
        this.url2Progress.clear();
        this.url2DecodeProgress.clear();
        this.url2DecodeSize.clear();
        this.url2DecodedSize.clear();
        this.downloadStatus = LoadStatus.LOADJSON;
    }

    public void stopLoadJson(List<String> list) {
        int i;
        this.downloadStatus = LoadStatus.LOADFILE;
        this.resFileCount = list.size();
        int i2 = 0;
        while (true) {
            i = this.resFileCount;
            if (i2 >= i) {
                break;
            }
            this.url2Progress.put(list.get(i2), 0);
            this.url2DecodeProgress.put(list.get(i2), 0);
            i2++;
        }
        if (i == 0) {
            return;
        }
        this.loadFileStep = loadFileTotal / i;
        this.decodeFileStep = decodeFileTotal / i;
    }

    public void setFileProgress(String str, long j, long j2) {
        this.url2Progress.put(str, Integer.valueOf((int) ((j * ((long) this.loadFileStep)) / j2)));
    }

    public void setFileProgressLoaded(String str) {
        this.url2Progress.put(str, Integer.valueOf(this.loadFileStep));
    }

    public synchronized void setDecodeProgressInit(String str, long j) {
        this.url2DecodeSize.put(str, Long.valueOf(j));
        this.url2DecodedSize.put(str, 0L);
    }

    public void setDecodeProgress(String str, int i) {
        try {
            if (this.url2DecodeSize.containsKey(str) && this.url2DecodedSize.containsKey(str)) {
                long jLongValue = this.url2DecodeSize.get(str).longValue();
                long jLongValue2 = this.url2DecodedSize.get(str).longValue() + ((long) i);
                this.url2DecodedSize.put(str, Long.valueOf(jLongValue2));
                if (jLongValue != 0) {
                    this.url2DecodeProgress.put(str, Integer.valueOf((int) ((jLongValue2 * ((long) this.decodeFileStep)) / jLongValue)));
                } else {
                    this.url2DecodeProgress.put(str, 0);
                }
            }
        } catch (Exception e) {
            for (Map.Entry<String, Long> entry : this.url2DecodeSize.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            EditingUtils.log("setDecodeProgress url = " + str);
            e.printStackTrace();
        }
    }

    public LoadStatus getDownloadStatus() {
        return this.downloadStatus;
    }
}
