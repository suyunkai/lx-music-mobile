package com.wanos.zero;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public class S73_Volume implements IVolumeMateData {
    private static final SparseArray<Float> VOLUME_MAP;

    @Override // com.wanos.zero.IVolumeMateData
    public String getMateDataName() {
        return "东风S73";
    }

    static {
        SparseArray<Float> sparseArray = new SparseArray<>(61);
        VOLUME_MAP = sparseArray;
        sparseArray.put(-60, Float.valueOf(-112.0f));
        sparseArray.put(-59, Float.valueOf(-94.4f));
        sparseArray.put(-58, Float.valueOf(-76.8f));
        sparseArray.put(-57, Float.valueOf(-59.0f));
        sparseArray.put(-56, Float.valueOf(-57.0f));
        sparseArray.put(-55, Float.valueOf(-55.0f));
        sparseArray.put(-54, Float.valueOf(-53.0f));
        sparseArray.put(-53, Float.valueOf(-51.4f));
        sparseArray.put(-52, Float.valueOf(-49.8f));
        sparseArray.put(-51, Float.valueOf(-48.0f));
        sparseArray.put(-50, Float.valueOf(-46.4f));
        sparseArray.put(-49, Float.valueOf(-44.8f));
        sparseArray.put(-48, Float.valueOf(-43.0f));
        sparseArray.put(-47, Float.valueOf(-41.4f));
        sparseArray.put(-46, Float.valueOf(-39.8f));
        sparseArray.put(-45, Float.valueOf(-38.0f));
        sparseArray.put(-44, Float.valueOf(-36.4f));
        sparseArray.put(-43, Float.valueOf(-34.8f));
        sparseArray.put(-42, Float.valueOf(-33.0f));
        sparseArray.put(-41, Float.valueOf(-31.7f));
        sparseArray.put(-40, Float.valueOf(-30.4f));
        sparseArray.put(-39, Float.valueOf(-29.0f));
        sparseArray.put(-38, Float.valueOf(-27.7f));
        sparseArray.put(-37, Float.valueOf(-26.4f));
        sparseArray.put(-36, Float.valueOf(-25.0f));
        sparseArray.put(-35, Float.valueOf(-23.7f));
        sparseArray.put(-34, Float.valueOf(-22.4f));
        sparseArray.put(-33, Float.valueOf(-21.0f));
        sparseArray.put(-32, Float.valueOf(-20.0f));
        sparseArray.put(-31, Float.valueOf(-19.0f));
        sparseArray.put(-30, Float.valueOf(-18.0f));
        sparseArray.put(-29, Float.valueOf(-17.0f));
        sparseArray.put(-28, Float.valueOf(-16.0f));
        sparseArray.put(-27, Float.valueOf(-15.0f));
        sparseArray.put(-26, Float.valueOf(-14.4f));
        sparseArray.put(-25, Float.valueOf(-13.8f));
        sparseArray.put(-24, Float.valueOf(-13.0f));
        sparseArray.put(-23, Float.valueOf(-12.4f));
        sparseArray.put(-22, Float.valueOf(-11.8f));
        sparseArray.put(-21, Float.valueOf(-11.0f));
        sparseArray.put(-20, Float.valueOf(-10.4f));
        sparseArray.put(-19, Float.valueOf(-9.8f));
        sparseArray.put(-18, Float.valueOf(-9.0f));
        sparseArray.put(-17, Float.valueOf(-8.4f));
        sparseArray.put(-16, Float.valueOf(-7.8f));
        sparseArray.put(-15, Float.valueOf(-7.0f));
        sparseArray.put(-14, Float.valueOf(-6.4f));
        sparseArray.put(-13, Float.valueOf(-5.8f));
        sparseArray.put(-12, Float.valueOf(-5.0f));
        sparseArray.put(-11, Float.valueOf(-4.4f));
        sparseArray.put(-10, Float.valueOf(-3.8f));
        sparseArray.put(-9, Float.valueOf(-3.0f));
        sparseArray.put(-8, Float.valueOf(-2.7f));
        sparseArray.put(-7, Float.valueOf(-2.4f));
        sparseArray.put(-6, Float.valueOf(-2.0f));
        sparseArray.put(-5, Float.valueOf(-1.7f));
        sparseArray.put(-4, Float.valueOf(-1.4f));
        sparseArray.put(-3, Float.valueOf(-1.0f));
        sparseArray.put(-2, Float.valueOf(-0.7f));
        sparseArray.put(-1, Float.valueOf(-0.4f));
        sparseArray.put(0, Float.valueOf(0.0f));
    }

    @Override // com.wanos.zero.IVolumeMateData
    public float getVolume(int i) {
        return VOLUME_MAP.get(i, Float.valueOf(-90.0f)).floatValue();
    }
}
