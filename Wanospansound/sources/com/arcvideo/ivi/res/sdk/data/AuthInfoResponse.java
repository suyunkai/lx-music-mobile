package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AuthInfoResponse {
    private final int code;
    private final String message;
    private final Result result;
    private final boolean success;

    public static final class Result {
        private final AuthInfo authInfo;
        private final AuthInfo testAuthInfo;

        public Result(AuthInfo authInfo, AuthInfo authInfo2) {
            this.authInfo = authInfo;
            this.testAuthInfo = authInfo2;
        }

        public static /* synthetic */ Result copy$default(Result result, AuthInfo authInfo, AuthInfo authInfo2, int i, Object obj) {
            if ((i & 1) != 0) {
                authInfo = result.authInfo;
            }
            if ((i & 2) != 0) {
                authInfo2 = result.testAuthInfo;
            }
            return result.copy(authInfo, authInfo2);
        }

        public final AuthInfo component1() {
            return this.authInfo;
        }

        public final AuthInfo component2() {
            return this.testAuthInfo;
        }

        public final Result copy(AuthInfo authInfo, AuthInfo authInfo2) {
            return new Result(authInfo, authInfo2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Result)) {
                return false;
            }
            Result result = (Result) obj;
            return Intrinsics.areEqual(this.authInfo, result.authInfo) && Intrinsics.areEqual(this.testAuthInfo, result.testAuthInfo);
        }

        public final AuthInfo getAuthInfo() {
            return this.authInfo;
        }

        public final AuthInfo getTestAuthInfo() {
            return this.testAuthInfo;
        }

        public int hashCode() {
            AuthInfo authInfo = this.authInfo;
            int iHashCode = (authInfo == null ? 0 : authInfo.hashCode()) * 31;
            AuthInfo authInfo2 = this.testAuthInfo;
            return iHashCode + (authInfo2 != null ? authInfo2.hashCode() : 0);
        }

        public String toString() {
            return "Result(authInfo=" + this.authInfo + ", testAuthInfo=" + this.testAuthInfo + ')';
        }
    }

    public AuthInfoResponse(int i, String message, Result result, boolean z) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(result, "result");
        this.code = i;
        this.message = message;
        this.result = result;
        this.success = z;
    }

    public static /* synthetic */ AuthInfoResponse copy$default(AuthInfoResponse authInfoResponse, int i, String str, Result result, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = authInfoResponse.code;
        }
        if ((i2 & 2) != 0) {
            str = authInfoResponse.message;
        }
        if ((i2 & 4) != 0) {
            result = authInfoResponse.result;
        }
        if ((i2 & 8) != 0) {
            z = authInfoResponse.success;
        }
        return authInfoResponse.copy(i, str, result, z);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final Result component3() {
        return this.result;
    }

    public final boolean component4() {
        return this.success;
    }

    public final AuthInfoResponse copy(int i, String message, Result result, boolean z) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(result, "result");
        return new AuthInfoResponse(i, message, result, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthInfoResponse)) {
            return false;
        }
        AuthInfoResponse authInfoResponse = (AuthInfoResponse) obj;
        return this.code == authInfoResponse.code && Intrinsics.areEqual(this.message, authInfoResponse.message) && Intrinsics.areEqual(this.result, authInfoResponse.result) && this.success == authInfoResponse.success;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Result getResult() {
        return this.result;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public int hashCode() {
        int iHashCode = ((((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + this.result.hashCode()) * 31;
        boolean z = this.success;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "AuthInfoResponse(code=" + this.code + ", message=" + this.message + ", result=" + this.result + ", success=" + this.success + ')';
    }
}
