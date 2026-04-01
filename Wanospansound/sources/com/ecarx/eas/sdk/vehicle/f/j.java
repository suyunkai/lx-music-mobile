package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IWindowPos;
import com.ecarx.openapi.protobuf.vehicle.ECARXAdaptVehicle;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends e implements IWindowPos {
    @Override // com.ecarx.eas.sdk.vehicle.f.e, com.ecarx.eas.sdk.vehicle.api.a
    public final /* bridge */ /* synthetic */ void a(ClientType clientType, ServiceVersionInfo serviceVersionInfo) {
        super.a(clientType, serviceVersionInfo);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.e, com.ecarx.eas.sdk.vehicle.api.a
    public final /* bridge */ /* synthetic */ boolean a(ClientType clientType, int i) {
        return super.a(clientType, i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.e
    /* JADX INFO: renamed from: a_ */
    public final /* bridge */ /* synthetic */ IEASFrameworkService a() {
        return super.a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.able.IZoneSetable
    public final /* synthetic */ boolean setState(int i, Integer num) throws UnsupportedOperationException {
        Integer num2 = num;
        if (super.a() == null) {
            throw new UnsupportedOperationException("Service 未连接异常!!");
        }
        ECARXAdaptVehicle.SetFunctionZoneValue setFunctionZoneValue = new ECARXAdaptVehicle.SetFunctionZoneValue();
        setFunctionZoneValue.function = 553845504;
        setFunctionZoneValue.zone = i;
        setFunctionZoneValue.funcValue = num2.intValue();
        return EASCallUtils.callBoolean(super.a(), this.f214b, this.f215c, "setWindowPos", MessageNano.toByteArray(setFunctionZoneValue), null);
    }

    public j(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.able.IZoneGetable
    public final /* synthetic */ Integer getState(int i) throws UnsupportedOperationException {
        if (super.a() == null) {
            throw new UnsupportedOperationException("Service 未连接异常!!");
        }
        ECARXAdaptVehicle.GetFunctionZoneValue getFunctionZoneValue = new ECARXAdaptVehicle.GetFunctionZoneValue();
        getFunctionZoneValue.function = 553845504;
        getFunctionZoneValue.zone = i;
        return Integer.valueOf(EASCallUtils.callInt(super.a(), this.f214b, this.f215c, "getWindowPos", MessageNano.toByteArray(getFunctionZoneValue), null));
    }
}
