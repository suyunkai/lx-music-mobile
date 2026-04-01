package com.ecarx.eas.sdk.device;

import android.util.Log;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;

/* JADX INFO: loaded from: classes2.dex */
abstract class l {
    public static int a(IEASFrameworkService iEASFrameworkService, int i) {
        if (iEASFrameworkService == null) {
            return -1;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage(IServiceManager.SERVICE_DRIVE_POLICY, "funpolicy", "getState", MessageNano.toByteArray(intMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DrivePolicyAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1;
            }
            return MsgAPI.getInt(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DrivePolicyAPI", e.getMessage(), e);
            return -1;
        }
    }

    public static Object a(IEASFrameworkService iEASFrameworkService, int i, m mVar) {
        if (iEASFrameworkService == null) {
            return null;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage(IServiceManager.SERVICE_DRIVE_POLICY, "funpolicy", "registerListener", MessageNano.toByteArray(intMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsgAndBinder = MsgAPI.sendMsgAndBinder(iEASFrameworkService, eASFrameworkMessage, mVar);
            if (supportServiceRetMessageSendMsgAndBinder.mCode != 200) {
                Log.e("DrivePolicyAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsgAndBinder.mCode), supportServiceRetMessageSendMsgAndBinder.mMsg));
                return null;
            }
            return supportServiceRetMessageSendMsgAndBinder.mBinder;
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DrivePolicyAPI", e.getMessage(), e);
            return null;
        }
    }
}
