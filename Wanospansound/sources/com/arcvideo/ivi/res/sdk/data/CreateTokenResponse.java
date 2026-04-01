package com.arcvideo.ivi.res.sdk.data;

import com.google.gson.annotations.SerializedName;
import com.wanos.media.ui.advertise.WanosJsBridge;

/* JADX INFO: loaded from: classes.dex */
public class CreateTokenResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private TokenInfo result;

    @SerializedName("success")
    private T success;

    @SerializedName("tokenExpireDuration")
    private String tokenExpireDuration;

    public static class TokenInfo {

        @SerializedName(WanosJsBridge.METHOD_KEY_TOKEN)
        private String token;

        @SerializedName("tokenExpireDuration")
        private String tokenExpireDuration;

        public String getToken() {
            return this.token;
        }

        public String getTokenExpireDuration() {
            return this.tokenExpireDuration;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public void setTokenExpireDuration(String str) {
            this.tokenExpireDuration = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public TokenInfo getResult() {
        return this.result;
    }

    public T getSuccess() {
        return this.success;
    }

    public String getTokenExpireDuration() {
        return this.tokenExpireDuration;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResult(TokenInfo tokenInfo) {
        this.result = tokenInfo;
    }

    public void setSuccess(T t) {
        this.success = t;
    }

    public void setTokenExpireDuration(String str) {
        this.tokenExpireDuration = str;
    }
}
