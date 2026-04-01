package com.ecarx.eas.framework.sdk.common.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.IllegalArgumentEASException;
import com.ecarx.eas.framework.sdk.common.exception.NotExistSupportServerException;
import com.ecarx.eas.framework.sdk.common.exception.NotFoundException;
import com.ecarx.eas.framework.sdk.common.exception.NotSupportedException;
import com.ecarx.eas.framework.sdk.common.exception.ProtoBufException;
import com.ecarx.eas.framework.sdk.common.exception.SupportServerErrorException;
import com.ecarx.eas.framework.sdk.common.exception.UnAuthorizedException;
import com.ecarx.eas.framework.sdk.common.exception.UnknownException;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MsgAPI {
    public static SupportServiceRetMessage sendMsg(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage) throws EASFrameworkException {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageCall = iEASFrameworkService.call(eASFrameworkMessage);
            if (eASFrameworkRetMessageCall == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageCall.mCode != 200) {
                throw getException(eASFrameworkRetMessageCall.mCode, eASFrameworkRetMessageCall.mMsg);
            }
            return eASFrameworkRetMessageCall.mRetMsg;
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static SupportServiceRetMessage sendMsgAndCallback(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage, IEASFrameworkCallback iEASFrameworkCallback) throws EASFrameworkException {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageAsyncCall = iEASFrameworkService.asyncCall(eASFrameworkMessage, iEASFrameworkCallback);
            if (eASFrameworkRetMessageAsyncCall == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageAsyncCall.mCode != 200) {
                throw getException(eASFrameworkRetMessageAsyncCall.mCode, eASFrameworkRetMessageAsyncCall.mMsg);
            }
            return eASFrameworkRetMessageAsyncCall.mRetMsg;
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static SupportServiceRetMessage sendMsgAndBinder(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) throws EASFrameworkException {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageAsyncBinderCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            if (eASFrameworkRetMessageAsyncBinderCall == null) {
                throw getException(502, "Service异常");
            }
            if (eASFrameworkRetMessageAsyncBinderCall.mCode != 200) {
                throw getException(eASFrameworkRetMessageAsyncBinderCall.mCode, eASFrameworkRetMessageAsyncBinderCall.mMsg);
            }
            return eASFrameworkRetMessageAsyncBinderCall.mRetMsg;
        } catch (RemoteException e) {
            throw new com.ecarx.eas.framework.sdk.common.exception.a(e.getMessage(), e);
        }
    }

    public static String getString(byte[] bArr) throws EASFrameworkException {
        if (bArr == null || bArr.length <= 0) {
            throw new ProtoBufException("数据为空！！！");
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
            throw new ProtoBufException("数据为空！！！");
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
            throw new ProtoBufException("数据为空！！！");
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
            throw new ProtoBufException("数据为空！！！");
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
            throw new ProtoBufException("数据为空！！！");
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
