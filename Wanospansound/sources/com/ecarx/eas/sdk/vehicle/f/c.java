package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.vehicle.able.ICallbackable;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IDippedBeamLight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.ILeftTurnLight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.ILight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IRightTurnLight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IStopLight;
import com.ecarx.eas.sdk.vehicle.v3.d;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends e implements ILight {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IDippedBeamLight f209d;
    private ILeftTurnLight e;
    private IRightTurnLight f;
    private IStopLight g;

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

    public c(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
        this.f215c = "Havc";
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.ILight
    public final IDippedBeamLight getDippedBeamLight() {
        IDippedBeamLight iDippedBeamLight = this.f209d;
        if (iDippedBeamLight != null) {
            return iDippedBeamLight;
        }
        C0173a c0173a = new C0173a(this.f213a, this.f214b, this.f215c);
        this.f209d = c0173a;
        return c0173a;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.ILight
    public final ILeftTurnLight getLeftTurnLight() {
        ILeftTurnLight iLeftTurnLight = this.e;
        if (iLeftTurnLight != null) {
            return iLeftTurnLight;
        }
        C0174b c0174b = new C0174b(this.f213a, this.f214b, this.f215c);
        this.e = c0174b;
        return c0174b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.ILight
    public final IRightTurnLight getRightTurnLight() {
        IRightTurnLight iRightTurnLight = this.f;
        if (iRightTurnLight != null) {
            return iRightTurnLight;
        }
        d dVar = new d(this.f213a, this.f214b, this.f215c);
        this.f = dVar;
        return dVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.ILight
    public final IStopLight getStopLight() {
        IStopLight iStopLight = this.g;
        if (iStopLight != null) {
            return iStopLight;
        }
        f fVar = new f(this.f213a, this.f214b, this.f215c);
        this.g = fVar;
        return fVar;
    }

    public static abstract class a extends e implements ICallbackable<ILight.ILightCallback> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ConcurrentHashMap<ILight.ILightCallback, b> f210d;

        protected abstract String a(String str);

        protected abstract int b();

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

        public a(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
            super(aVar, str, str2);
            this.f210d = new ConcurrentHashMap<>();
        }

        @Override // com.ecarx.eas.sdk.vehicle.able.ICallbackable
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public boolean unregisterCallback(ILight.ILightCallback iLightCallback) throws UnsupportedOperationException {
            if (iLightCallback == null) {
                return false;
            }
            b bVar = this.f210d.get(iLightCallback);
            if (bVar == null) {
                return true;
            }
            if (super.a() == null) {
                throw new UnsupportedOperationException("Service 未连接异常!!");
            }
            if (EASCallUtils.callBoolean(super.a(), this.f214b, this.f215c, a("unregisterCallback"), null, bVar)) {
                this.f210d.remove(iLightCallback);
                Log.d("EASDippedBeamLightImpl", "Succeed unregisterCallback");
                return true;
            }
            Log.d("EASDippedBeamLightImpl", "Can't unregisterCallback");
            return false;
        }

        @Override // com.ecarx.eas.sdk.vehicle.able.ICallbackable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean registerCallback(ILight.ILightCallback iLightCallback) throws UnsupportedOperationException {
            if (iLightCallback == null) {
                return false;
            }
            if (super.a() == null) {
                throw new UnsupportedOperationException("Service 未连接异常!!");
            }
            if (this.f210d.get(iLightCallback) == null) {
                b bVar = new b(b(), iLightCallback);
                ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
                intMsg.value = b();
                if (EASCallUtils.callBoolean(super.a(), this.f214b, this.f215c, a("registerCallback"), MessageNano.toByteArray(intMsg), bVar)) {
                    this.f210d.put(iLightCallback, bVar);
                    Log.d("EASDippedBeamLightImpl", "Succeed registerCallback");
                    return true;
                }
                Log.d("EASDippedBeamLightImpl", "Can't registerCallback");
                return false;
            }
            Log.d("EASDippedBeamLightImpl", "Has registerCallback");
            return true;
        }
    }

    static class b extends d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ILight.ILightCallback f212b;

        public b(int i, ILight.ILightCallback iLightCallback) {
            this.f211a = i;
            this.f212b = iLightCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.d
        public final void a(int i, int i2) throws RemoteException {
            ILight.ILightCallback iLightCallback;
            if (this.f211a != i || (iLightCallback = this.f212b) == null) {
                return;
            }
            if (1 == i2) {
                iLightCallback.onLightOn();
            } else if (i2 == 0) {
                iLightCallback.onLightOff();
            }
        }
    }
}
