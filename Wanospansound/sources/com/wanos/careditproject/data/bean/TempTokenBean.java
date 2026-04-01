package com.wanos.careditproject.data.bean;

/* JADX INFO: loaded from: classes3.dex */
public class TempTokenBean {
    private String EndPoint;
    private String accessKeyId;
    private String bucketName;
    private String createTime;
    private String expiredTime;
    private String objectKey;
    private String secretAccessKey;
    private String sessionToken;

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public String getSecretAccessKey() {
        return this.secretAccessKey;
    }

    public void setSecretAccessKey(String str) {
        this.secretAccessKey = str;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public String getExpiredTime() {
        return this.expiredTime;
    }

    public void setExpiredTime(String str) {
        this.expiredTime = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getEndPoint() {
        return this.EndPoint;
    }

    public void setEndPoint(String str) {
        this.EndPoint = str;
    }
}
