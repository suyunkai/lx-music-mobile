package com.ecarx.eas.sdk.device;

import android.content.ComponentName;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.ProtoBufException;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {
    public static String a(IEASFrameworkService iEASFrameworkService, String str) {
        if (iEASFrameworkService == null) {
            return null;
        }
        ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
        voidMsg.value = "";
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(voidMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return null;
            }
            return MsgAPI.getString(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return null;
        }
    }

    public static int b(IEASFrameworkService iEASFrameworkService, String str) {
        if (iEASFrameworkService == null) {
            return -1;
        }
        ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
        voidMsg.value = "";
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(voidMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1;
            }
            return MsgAPI.getInt(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return -1;
        }
    }

    public static String a(IEASFrameworkService iEASFrameworkService, String str, String str2) {
        if (iEASFrameworkService == null) {
            return null;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringMsg.value = str2;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(stringMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return null;
            }
            return MsgAPI.getString(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return null;
        }
    }

    public static boolean b(IEASFrameworkService iEASFrameworkService, String str, String str2) {
        if (iEASFrameworkService == null) {
            return false;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringMsg.value = str2;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(stringMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return false;
            }
            return MsgAPI.getBool(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return false;
        }
    }

    public static int c(IEASFrameworkService iEASFrameworkService, String str, String str2) {
        if (iEASFrameworkService == null) {
            return -1;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringMsg.value = str2;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(stringMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1;
            }
            return MsgAPI.getInt(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return -1;
        }
    }

    public static long d(IEASFrameworkService iEASFrameworkService, String str, String str2) {
        if (iEASFrameworkService == null) {
            return -1L;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringMsg.value = str2;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(stringMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1L;
            }
            return MsgAPI.getLong(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return -1L;
        }
    }

    public static double e(IEASFrameworkService iEASFrameworkService, String str, String str2) {
        if (iEASFrameworkService == null) {
            return -1.0d;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringMsg.value = str2;
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", str, MessageNano.toByteArray(stringMsg), new byte[0]);
        try {
            SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
            if (supportServiceRetMessageSendMsg.mCode != 200) {
                Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
                return -1.0d;
            }
            return MsgAPI.getLong(supportServiceRetMessageSendMsg.mData);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", e.getMessage(), e);
            return -1.0d;
        }
    }

    public static List<ComponentName> a(IEASFrameworkService iEASFrameworkService) throws EASFrameworkException {
        if (iEASFrameworkService == null) {
            return Collections.emptyList();
        }
        ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
        voidMsg.value = "";
        byte[] byteArray = MessageNano.toByteArray(voidMsg);
        EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "master", "getSupportedComponents", byteArray, new byte[0]);
        SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg(iEASFrameworkService, eASFrameworkMessage);
        if (supportServiceRetMessageSendMsg.mCode != 200) {
            Log.e("DeviceAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsg.mCode), supportServiceRetMessageSendMsg.mMsg));
            return Collections.emptyList();
        }
        try {
            ECARXCommon.ComponentNameList from = ECARXCommon.ComponentNameList.parseFrom(supportServiceRetMessageSendMsg.mData);
            if (from != null && from.item != null && from.item.length > 0) {
                ArrayList arrayList = new ArrayList();
                for (ECARXCommon.ComponentName componentName : from.item) {
                    arrayList.add(new ComponentName(componentName.pkg, componentName.cls));
                }
                return arrayList;
            }
            return Collections.emptyList();
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            throw new ProtoBufException(e.getMessage(), e);
        }
    }
}
