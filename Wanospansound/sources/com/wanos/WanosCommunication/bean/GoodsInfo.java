package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class GoodsInfo {
    public static final int AUTH_DURATION_TYPE_DAY = 1;
    public static final int AUTH_DURATION_TYPE_HALF_YEAR = 5;
    public static final int AUTH_DURATION_TYPE_MONTH = 3;
    public static final int AUTH_DURATION_TYPE_PERMANENT = 0;
    public static final int AUTH_DURATION_TYPE_SEASON = 4;
    public static final int AUTH_DURATION_TYPE_WEEK = 2;
    public static final int AUTH_DURATION_TYPE_YEAR = 6;
    private float goodPrice;
    private int goodsId;
    private String goodsName;
    private String goodsNo;
    private float originPrice;

    public int getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(int i) {
        this.goodsId = i;
    }

    public String getGoodsNo() {
        return this.goodsNo;
    }

    public void setGoodsNo(String str) {
        this.goodsNo = str;
    }

    public float getGoodsPrice() {
        return this.goodPrice;
    }

    public void setGoodsPrice(float f) {
        this.goodPrice = f;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public float getOriginPrice() {
        return this.originPrice;
    }

    public void setOriginPrice(float f) {
        this.originPrice = f;
    }
}
