package com.ecarx.eas.framework.sdk.common.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.IllegalArgumentEASException;
import com.ecarx.eas.framework.sdk.common.exception.NotExistSupportServerException;
import com.ecarx.eas.framework.sdk.common.exception.NotFoundException;
import com.ecarx.eas.framework.sdk.common.exception.NotSupportedException;
import com.ecarx.eas.framework.sdk.common.exception.ProtoBufException;
import com.ecarx.eas.framework.sdk.common.exception.SupportServerErrorException;
import com.ecarx.eas.framework.sdk.common.exception.UnAuthorizedException;
import com.ecarx.eas.framework.sdk.common.exception.UnknownException;
import com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkCallbackRemote;
import com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessageRemote;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessageRemote;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MsgRemoteAPI {
    public static void sendMsgAndCallback(IEASFrameworkRemoteService iEASFrameworkRemoteService, EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws EASFrameworkException {
        try {
            if (iEASFrameworkRemoteService == null) {
                throw new IllegalArgumentEASException("IEASFrameworkRemoteService is null");
            }
            if (eASFrameworkMessageRemote == null) {
                throw new IllegalArgumentEASException("EASFrameworkMessageRemote is null");
            }
            if (iEASFrameworkCallbackRemote == null) {
                throw new IllegalArgumentEASException("IEASFrameworkCallbackRemote is null");
            }
            EASFrameworkRetMessageRemote eASFrameworkRetMessageRemoteAsyncCall = iEASFrameworkRemoteService.asyncCall(eASFrameworkMessageRemote, iEASFrameworkCallbackRemote);
            if (eASFrameworkRetMessageRemoteAsyncCall == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageRemoteAsyncCall.mCode != 200) {
                throw getException(eASFrameworkRetMessageRemoteAsyncCall.mCode, eASFrameworkRetMessageRemoteAsyncCall.mMsg);
            }
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static String sendMsgRegisterListener(IEASFrameworkRemoteService iEASFrameworkRemoteService, EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws EASFrameworkException {
        try {
            if (iEASFrameworkRemoteService == null) {
                throw new IllegalArgumentEASException("IEASFrameworkRemoteService is null");
            }
            if (eASFrameworkMessageRemote == null) {
                throw new IllegalArgumentEASException("EASFrameworkMessageRemote is null");
            }
            if (iEASFrameworkCallbackRemote == null) {
                throw new IllegalArgumentEASException("IEASFrameworkCallbackRemote is null");
            }
            EASFrameworkRetMessageRemote eASFrameworkRetMessageRemoteRegisterListener = iEASFrameworkRemoteService.registerListener(eASFrameworkMessageRemote, iEASFrameworkCallbackRemote);
            if (eASFrameworkRetMessageRemoteRegisterListener == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageRemoteRegisterListener.mCode != 200) {
                throw getException(eASFrameworkRetMessageRemoteRegisterListener.mCode, eASFrameworkRetMessageRemoteRegisterListener.mMsg);
            }
            if (eASFrameworkRetMessageRemoteRegisterListener.mResult == null || eASFrameworkRetMessageRemoteRegisterListener.mResult.length == 0) {
                throw new com.ecarx.eas.framework.sdk.common.exception.a("mResult is null");
            }
            return new String(eASFrameworkRetMessageRemoteRegisterListener.mResult);
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static void sendMsgUnregisterListener(IEASFrameworkRemoteService iEASFrameworkRemoteService, EASFrameworkMessageRemote eASFrameworkMessageRemote, String str) throws EASFrameworkException {
        try {
            if (iEASFrameworkRemoteService == null) {
                throw new IllegalArgumentEASException("IEASFrameworkRemoteService is null");
            }
            if (eASFrameworkMessageRemote == null) {
                throw new IllegalArgumentEASException("EASFrameworkMessageRemote is null");
            }
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentEASException("token is null");
            }
            EASFrameworkRetMessageRemote eASFrameworkRetMessageRemoteUnregisterListener = iEASFrameworkRemoteService.unregisterListener(eASFrameworkMessageRemote, str);
            if (eASFrameworkRetMessageRemoteUnregisterListener == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageRemoteUnregisterListener.mCode != 200) {
                throw getException(eASFrameworkRetMessageRemoteUnregisterListener.mCode, eASFrameworkRetMessageRemoteUnregisterListener.mMsg);
            }
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static String getString(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("data is null！！！");
        }
        try {
            return ECARXCommon.StringMsg.parseFrom(bArr).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }

    public static int getInt(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("data is null！！！");
        }
        try {
            return ECARXCommon.IntMsg.parseFrom(bArr).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }

    public static boolean getBool(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("data is null！！！");
        }
        try {
            return ECARXCommon.BoolMsg.parseFrom(bArr).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }

    public static long getLong(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("data is null！！！");
        }
        try {
            return ECARXCommon.LongMsg.parseFrom(bArr).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }

    public static double getDouble(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("data is null！！！");
        }
        try {
            return ECARXCommon.DoubleMsg.parseFrom(bArr).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }

    public static EASFrameworkException getException(int i, String str) {
        if (i == 501) {
            return new NotExistSupportServerException(str);
        }
        if (i == 502) {
            return new SupportServerErrorException(str);
        }
        if (i == 600) {
            return new ProtoBufException(str);
        }
        if (i == 601) {
            return new com.ecarx.eas.framework.sdk.common.exception.a(str);
        }
        switch (i) {
            case 401:
                return new UnAuthorizedException(str);
            case 402:
                return new NotSupportedException(str);
            case 403:
                return new IllegalArgumentEASException(str);
            case 404:
                return new NotFoundException(str);
            default:
                return new UnknownException(str);
        }
    }
}
