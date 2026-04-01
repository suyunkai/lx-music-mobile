package com.ecarx.eas.framework.sdk.common.internal;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Constant {
    public static final int CONNECT_STATE_BINDDIED = 4;
    public static final int CONNECT_STATE_CONNECTED = 2;
    public static final int CONNECT_STATE_CONNECTING = 1;
    public static final int CONNECT_STATE_DESTORY = 5;
    public static final int CONNECT_STATE_DISCONNECTED = 3;
    public static final int CONNECT_STATE_NOT_START = 0;
    public static final int ILLEGAL_ARGUMENT = 403;
    public static final String ILLEGAL_ARGUMENT_STRING = "非法参数";
    public static final String LOG_TAG = "OpenAPI_eascoreiap";
    public static final int NOT_FOUND = 404;
    public static final int NOT_SUPPORTED = 402;
    public static final String NOT_SUPPORTED_STRING = "不支持该API";
    public static final int OK = 200;
    public static final String OK_STRING = "操作成功";
    public static final int SERVICE_IS_INITING = 503;
    public static final String SERVICE_IS_INITING_STRING = "Service初始化中";
    public static final String SERVICE_IS_NOT_EXISTS = "Service不存在";
    public static final String SERVICE_IS_UNAUTHORIZED_STRING = "没有权限";
    public static final int SERVICE_TYPE_V3 = 1;
    public static final int SERVICE_TYPE_V4 = 2;
    public static final int SUPPORT_SERVER_ERROR = 502;
    public static final String SUPPORT_SERVER_ERROR_STRING = "Service异常";
    public static final int SUPPORT_SERVER_NOT_EXISTS = 501;
    public static final String TAG = "EASFramework";
    public static final int UNAUTHORIZED = 401;

    public static String getMsgToCode(int i) {
        if (i == 200) {
            return "操作成功";
        }
        switch (i) {
            case 401:
                return SERVICE_IS_UNAUTHORIZED_STRING;
            case 402:
            case 404:
                return "不支持该API";
            case 403:
                return "非法参数";
            default:
                switch (i) {
                    case 501:
                        return SERVICE_IS_NOT_EXISTS;
                    case 502:
                        return "Service异常";
                    case 503:
                        return SERVICE_IS_INITING_STRING;
                    default:
                        return "";
                }
        }
    }
}
