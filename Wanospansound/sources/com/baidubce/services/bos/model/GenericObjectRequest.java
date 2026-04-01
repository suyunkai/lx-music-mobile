package com.baidubce.services.bos.model;

import com.baidubce.BceConfig;
import com.baidubce.util.CheckUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericObjectRequest extends GenericBucketRequest {
    private static final int MAX_OBJECT_KEY_LENGTH = 1024;
    private static final int MIN_OBJECT_KEY_LENGTH = 1;
    private String key;
    protected long trafficLimitBitPS;

    public abstract GenericObjectRequest withKey(String str);

    public GenericObjectRequest() {
        this.trafficLimitBitPS = -1L;
    }

    public GenericObjectRequest(String str, String str2) {
        super(str);
        this.trafficLimitBitPS = -1L;
        setKey(str2);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        CheckUtils.isNotNull(str, "key should not be null.");
        boolean z = true;
        if (str.length() < 1) {
            throw new IllegalArgumentException("Invalid objectKey:" + str + ". objectKey should not be less than 1.");
        }
        if (str.length() > 1024) {
            throw new IllegalArgumentException("Invalid objectKey:" + str + ". objectKey should not be greater than 1024.");
        }
        if (str.startsWith(BceConfig.BOS_DELIMITER)) {
            int i = 1;
            while (true) {
                if (i >= str.length()) {
                    break;
                }
                if (str.charAt(i) != '/') {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                throw new IllegalArgumentException("Invalid objectKey:" + str + ". objectKey should not be delimiter.");
            }
        }
        this.key = str;
    }
}
