package com.baidubce.services.sts.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class GetSessionTokenResponse extends AbstractBceResponse {
    private String accessKeyId;
    private Date expiration;
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

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date date) {
        this.expiration = date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Credentials{accessKeyId='");
        sb.append(this.accessKeyId).append("', secretAccessKey='");
        sb.append(this.secretAccessKey).append("', sessionToken='");
        sb.append(this.sessionToken).append("', expiration=");
        sb.append(this.expiration);
        sb.append('}');
        return sb.toString();
    }
}
