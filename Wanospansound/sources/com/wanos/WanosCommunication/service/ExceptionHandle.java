package com.wanos.WanosCommunication.service;

/* JADX INFO: loaded from: classes3.dex */
public class ExceptionHandle {
    private static final int BAD_GATEWAY = 502;
    private static final int FORBIDDEN = 403;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int SERVICE_UNAVAILABLE = 503;
    public static final String TAG = "wanos:[ExceptionHandle]";
    private static final int UNAUTHORIZED = 401;

    public static class ERROR {
        public static final int HTTP_ERROR = 1003;
        public static final int NETWORD_ERROR = 1002;
        public static final int PARSE_ERROR = 1001;
        private static final int SEVERERROR = 500;
        public static final int SSL_ERROR = 1005;
        public static final int UNALLOCATED = 101;
        private static final int UNAUTHORIZED = 401;
        public static final int UNCONFIGURED = -1;
        private static final int UNFINDSOURCE = 404;
        public static final int UNKNOWN = 1000;
        private static final int UNREQUEST = 403;
        public static final int UNSHELVE = 207;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.wanos.WanosCommunication.service.ExceptionHandle.ResponseException handleException(java.lang.Throwable r4) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.WanosCommunication.service.ExceptionHandle.handleException(java.lang.Throwable):com.wanos.WanosCommunication.service.ExceptionHandle$ResponseException");
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable th, int i) {
            super(th);
            this.code = i;
        }
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;

        public ServerException() {
        }
    }

    public static class ResponseException extends Exception {
        public int code;
        public String message;

        public ResponseException(Throwable th, int i) {
            super(th);
            this.code = i;
        }
    }
}
