package com.baidubce.services.bos.model;

import com.baidubce.model.AbstractBceRequest;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericBucketRequest extends AbstractBceRequest {
    private static final int MAX_BUCKET_NAME_LENGTH = 63;
    private static final int MIN_BUCKET_NAME_LENGTH = 3;
    private String bucketName;

    public abstract GenericBucketRequest withBucketName(String str);

    public GenericBucketRequest() {
    }

    public GenericBucketRequest(String str) {
        setBucketName(str);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        if (str == null || str.isEmpty()) {
            this.bucketName = str;
            return;
        }
        String strTrim = str.trim();
        if (strTrim.length() < 3) {
            throw new IllegalArgumentException("Invalid bucketNamse:" + strTrim + ". bucketName should not be less than 3.");
        }
        if (strTrim.length() > 63) {
            throw new IllegalArgumentException("Invalid bucketName:" + strTrim + ". bucketName should not be greater than 63.");
        }
        if (!isLowercaseOrDigit(strTrim.charAt(0))) {
            throw new IllegalArgumentException("Invalid bucketName:" + strTrim + ". bucketName should start with a lowercase letter or digit.");
        }
        if (!isLowercaseOrDigit(strTrim.charAt(strTrim.length() - 1))) {
            throw new IllegalArgumentException("Invalid bucketName:" + strTrim + ". bucketName should end with a lowercase letter or digit.");
        }
        for (int i = 1; i < strTrim.length() - 1; i++) {
            char cCharAt = strTrim.charAt(i);
            if (!isLowercaseOrDigit(cCharAt) && cCharAt != '-') {
                throw new IllegalArgumentException("Invalid bucketName:" + strTrim + ". bucketName should contain only lowercase leters, digits and hyphens(-).");
            }
        }
        this.bucketName = strTrim;
    }

    private static boolean isLowercaseOrDigit(char c2) {
        return Character.isDigit(c2) || (c2 >= 'a' && c2 <= 'z');
    }
}
