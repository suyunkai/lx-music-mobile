package com.ecarx.eas.sdk.device;

import android.util.Log;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static int a(IEASFrameworkService iEASFrameworkService, String str) {
        if (iEASFrameworkService == null) {
            return -1;
        }
        ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
        voidMsg.value = "";
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "daymode", str, MessageNano.toByteArray(voidMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DayNightAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1;
            }
            return MsgAPI.getInt(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DayNightAPI", e.getMessage(), e);
            return -1;
        }
    }
}
