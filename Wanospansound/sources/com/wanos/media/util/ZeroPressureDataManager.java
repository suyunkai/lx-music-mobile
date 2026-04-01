package com.wanos.media.util;

import com.wanos.media.entity.ZeroThemeInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroPressureDataManager {
    private static volatile ZeroPressureDataManager instance;
    private ArrayList<ZeroThemeInfo> mxData;
    private ArrayList<ZeroThemeInfo> xqData;

    private ZeroPressureDataManager() {
    }

    public static ZeroPressureDataManager getInstance() {
        if (instance == null) {
            synchronized (ZeroPressureDataManager.class) {
                if (instance == null) {
                    instance = new ZeroPressureDataManager();
                }
            }
        }
        return instance;
    }

    public ArrayList<ZeroThemeInfo> getXqData() {
        return this.xqData;
    }

    public void setXqData(ArrayList<ZeroThemeInfo> arrayList) {
        this.xqData = arrayList;
    }

    public ArrayList<ZeroThemeInfo> getMxData() {
        return this.mxData;
    }

    public void setMxData(ArrayList<ZeroThemeInfo> arrayList) {
        this.mxData = arrayList;
    }
}
