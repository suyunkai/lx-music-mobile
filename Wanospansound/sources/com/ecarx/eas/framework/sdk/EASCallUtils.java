package com.ecarx.eas.framework.sdk;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EASCallUtils {
    private static final String TAG = "EASCallUtils";

    public static boolean callBoolean(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return false;
        }
        try {
            return ECARXCommon.BoolMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int callInt(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return -1;
        }
        try {
            return ECARXCommon.IntMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static float callFloat(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return -1.0f;
        }
        try {
            return ECARXCommon.FloatMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static String callString(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return "";
        }
        try {
            return ECARXCommon.StringMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static double callDouble(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return 0.0d;
        }
        try {
            return ECARXCommon.DoubleMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static long callLong(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return 0L;
        }
        try {
            return ECARXCommon.LongMsg.parseFrom(bArrCall).value;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static List<ECARXCommon.BtDevice> callBtDeviceList(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        byte[] bArrCall = call(iEASFrameworkService, str, str2, str3, bArr, iBinder);
        if (bArrCall == null) {
            return null;
        }
        try {
            return Arrays.asList(ECARXCommon.BtDeviceList.parseFrom(bArrCall).item);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] call(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        EASFrameworkRetMessage eASFrameworkRetMessageCall;
        try {
            if (iEASFrameworkService == null) {
                Log.d(TAG, "fwkService == null");
                return null;
            }
            String str4 = TAG;
            Log.d(str4, "serviceName = " + str + ", methodName = " + str3 + ", moduleName = " + str2);
            EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage(str, str2, str3, bArr, null);
            if (iBinder != null) {
                eASFrameworkRetMessageCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            } else {
                eASFrameworkRetMessageCall = iEASFrameworkService.call(eASFrameworkMessage);
            }
            if (eASFrameworkRetMessageCall == null) {
                Log.e(str4, "retMessage == null");
                return null;
            }
            if (eASFrameworkRetMessageCall.mCode == 200 && eASFrameworkRetMessageCall.mRetMsg != null) {
                SupportServiceRetMessage supportServiceRetMessage = eASFrameworkRetMessageCall.mRetMsg;
                if (supportServiceRetMessage.mCode != 200) {
                    Log.e(str4, "errorCode = " + supportServiceRetMessage.mCode);
                    Log.e(str4, "error = " + supportServiceRetMessage.mMsg);
                    return null;
                }
                return supportServiceRetMessage.mData;
            }
            Log.d(str4, "retMessage.mCode = " + eASFrameworkRetMessageCall.mCode + "|| retMessage.mRetMsg == null");
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "method = " + str3);
            e.printStackTrace();
            return null;
        }
    }

    public static IBinder callBinder(IEASFrameworkService iEASFrameworkService, String str, String str2, String str3, byte[] bArr, IBinder iBinder) {
        EASFrameworkRetMessage eASFrameworkRetMessageCall;
        try {
            if (iEASFrameworkService == null) {
                Log.d(TAG, "fwkService == null");
                return null;
            }
            String str4 = TAG;
            Log.d(str4, "serviceName = " + str + ", methodName = " + str3);
            EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage(str, str2, str3, bArr, null);
            if (iBinder != null) {
                eASFrameworkRetMessageCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            } else {
                eASFrameworkRetMessageCall = iEASFrameworkService.call(eASFrameworkMessage);
            }
            if (eASFrameworkRetMessageCall == null) {
                return null;
            }
            SupportServiceRetMessage supportServiceRetMessage = eASFrameworkRetMessageCall.mRetMsg;
            if (supportServiceRetMessage == null) {
                Log.e(str4, "SupportServiceRetMessage is NULL");
                return null;
            }
            if (supportServiceRetMessage.mCode != 200) {
                Log.e(str4, "errorCode = " + supportServiceRetMessage.mCode);
                Log.e(str4, "error = " + supportServiceRetMessage.mMsg);
                return null;
            }
            return supportServiceRetMessage.mBinder;
        } catch (RemoteException e) {
            Log.e(TAG, "method = " + str3);
            e.printStackTrace();
            return null;
        }
    }
}
