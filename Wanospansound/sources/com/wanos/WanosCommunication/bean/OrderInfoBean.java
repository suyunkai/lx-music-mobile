package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class OrderInfoBean {
    private long createTime;
    private long expireTime;
    private String goodsName;
    private String id;
    private float orderAmount;
    private String orderNo;
    private int orderStatus;
    private int payStatus;
    private String wanosUid;

    public static class OrderPayStatus {
        public static int STATUS_DEFAULT = 0;
        public static int STATUS_EXPIRE = 4;
        public static int STATUS_FAIL = 3;
        public static int STATUS_NONEEDPAY = 8;
        public static int STATUS_REFUNDFAIL = 7;
        public static int STATUS_REFUNDING = 5;
        public static int STATUS_REFUNDSUCCESS = 6;
        public static int STATUS_SUCCESS = 2;
        public static int STATUS_WAIT = 1;
    }

    public static class OrderPayType {
        public static String TYPE_PAY_ALIPAY = "Alipay";
        public static String TYPE_PAY_DEFAULT = "PayTypeDefault";
        public static String TYPE_PAY_WECHAT = "Wechat";
    }

    public static class OrderStatus {
        public static int STATUS_CANCEL = 2;
        public static int STATUS_CREATE = 1;
        public static int STATUS_TIMEOUT = 3;
    }

    public static class OrderType {
        public static String TYPE_END_ORDER = "EndOrder";
        public static String TYPE_SERIALIZE_ORDER = "SerializeOrder";
        public static String TYPE_VIP_ORDER = "VipOrder";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getWanosUid() {
        return this.wanosUid;
    }

    public void setWanosUid(String str) {
        this.wanosUid = str;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int i) {
        this.orderStatus = i;
    }

    public String getOrderAmount() {
        return this.orderAmount + "";
    }

    public void setOrderAmount(float f) {
        this.orderAmount = f;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public int getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(int i) {
        this.payStatus = i;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }
}
