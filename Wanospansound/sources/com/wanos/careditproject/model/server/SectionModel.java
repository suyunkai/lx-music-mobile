package com.wanos.careditproject.model.server;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SectionModel {
    private long Offset = 0;
    private List<Short> Data = new ArrayList();

    public long getOffset() {
        return this.Offset;
    }

    public void setOffset(long j) {
        this.Offset = j;
    }

    public List<Short> getData() {
        return this.Data;
    }

    public void setData(List<Short> list) {
        this.Data = list;
    }

    public int getDataSize() {
        List<Short> list = this.Data;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void addData(short s, short s2, short s3) {
        if (this.Data == null) {
            this.Data = new ArrayList();
        }
        this.Data.add(Short.valueOf(s));
        this.Data.add(Short.valueOf(s2));
        this.Data.add(Short.valueOf(s3));
    }

    public void mergeData(List<Short> list) {
        this.Data.addAll(list);
    }

    public void insertXY(int i, short s, short s2) {
        int i2 = i * 3;
        List<Short> list = this.Data;
        if (list == null || list.size() <= i2) {
            return;
        }
        this.Data.set(i2, Short.valueOf(s));
        this.Data.set(i2 + 1, Short.valueOf(s2));
    }

    public void insertZ(int i, short s) {
        int i2;
        int i3 = i * 3;
        List<Short> list = this.Data;
        if (list == null || list.size() <= (i2 = i3 + 2)) {
            return;
        }
        this.Data.set(i2, Short.valueOf(s));
    }

    public List<Short> getXYZ(int i) {
        ArrayList arrayList = new ArrayList(3);
        int i2 = i * 3;
        if (i2 + 3 < this.Data.size()) {
            for (int i3 = 0; i3 < 3; i3++) {
                arrayList.add(this.Data.get(i2 + i3));
            }
        } else {
            arrayList.add((short) 0);
            arrayList.add((short) 0);
            arrayList.add((short) 0);
        }
        return arrayList;
    }

    public List<Short> getDataItem(int i) {
        ArrayList arrayList = new ArrayList(3);
        int i2 = i * 3;
        int i3 = 0;
        if (i2 + 3 < this.Data.size()) {
            while (i3 < 3) {
                arrayList.add(this.Data.get(i2 + i3));
                i3++;
            }
            return arrayList;
        }
        if (this.Data.size() >= 3) {
            int size = this.Data.size() - 3;
            while (i3 < 3) {
                arrayList.add(this.Data.get(size + i3));
                i3++;
            }
            return arrayList;
        }
        arrayList.add((short) 0);
        arrayList.add((short) 0);
        arrayList.add((short) 0);
        return arrayList;
    }
}
