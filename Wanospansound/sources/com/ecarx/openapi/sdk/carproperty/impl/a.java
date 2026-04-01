package com.ecarx.openapi.sdk.carproperty.impl;

import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.SupportServerErrorException;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelUtil;
import com.ecarx.openapi.sdk.carproperty.ICarProperty;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;

/* JADX INFO: loaded from: classes2.dex */
public class a extends IApi<IEASFrameworkService> implements ICarProperty {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Singleton<a> f360a = new Singleton<a>() { // from class: com.ecarx.openapi.sdk.carproperty.impl.a.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ a create() {
            return new a((byte) 0);
        }
    };

    /* synthetic */ a(byte b2) {
        this();
    }

    private a() {
    }

    public static a a() {
        return f360a.get();
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyId(int i, int i2) throws EASFrameworkException {
        return a("getPropertyId", i, i2, -1);
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyValue(int i, int i2, int i3) throws EASFrameworkException {
        return a("getPropertyValue", i, i2, i3);
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyAdaptValue(int i, int i2, int i3) throws EASFrameworkException {
        return a("getPropertyAdaptValue", i, i2, i3);
    }

    private int a(String str, int i, int i2, int i3) throws EASFrameworkException {
        if (isNotAlive()) {
            Log.d("CarPropertyAPIImpl", str + " service is not alive");
            throw new SupportServerErrorException("service is not alive");
        }
        SupportServiceRetMessage supportServiceRetMessageSendMsg = MsgAPI.sendMsg((IEASFrameworkService) this.mService, new EASFrameworkMessage("carproperty", "wrapper", str, SafeParcelUtil.asByteArray(new WrapperRequest(i, i2, i3)), null));
        if (supportServiceRetMessageSendMsg == null) {
            Log.w("CarPropertyAPIImpl", str + " response is null");
            throw new SupportServerErrorException("response is null");
        }
        if (supportServiceRetMessageSendMsg.mCode == 200 && supportServiceRetMessageSendMsg.mData != null && supportServiceRetMessageSendMsg.mData.length > 0) {
            try {
                return Integer.parseInt(new String(supportServiceRetMessageSendMsg.mData));
            } catch (NumberFormatException e) {
                Log.w("CarPropertyAPIImpl", str + " parse failed", e);
                throw new SupportServerErrorException("parse failed");
            }
        }
        Log.w("CarPropertyAPIImpl", str + " response " + supportServiceRetMessageSendMsg.mCode + " " + supportServiceRetMessageSendMsg.mMsg);
        throw new SupportServerErrorException(supportServiceRetMessageSendMsg.mMsg);
    }
}
