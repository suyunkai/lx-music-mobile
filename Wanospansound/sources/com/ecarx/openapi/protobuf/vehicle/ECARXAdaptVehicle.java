package com.ecarx.openapi.protobuf.vehicle;

import com.ecarx.eas.framework.sdk.proto.CodedInputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.CodedOutputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.InternalNano;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.framework.sdk.proto.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface ECARXAdaptVehicle {

    public static final class SetFunctionValue extends MessageNano {
        private static volatile SetFunctionValue[] _emptyArray;
        public int funcValue;
        public int function;

        public static SetFunctionValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SetFunctionValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SetFunctionValue() {
            clear();
        }

        public final SetFunctionValue clear() {
            this.function = 0;
            this.funcValue = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.function);
            codedOutputByteBufferNano.writeInt32(2, this.funcValue);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.function) + CodedOutputByteBufferNano.computeInt32Size(2, this.funcValue);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final SetFunctionValue mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.function = codedInputByteBufferNano.readInt32();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.funcValue = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static SetFunctionValue parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (SetFunctionValue) MessageNano.mergeFrom(new SetFunctionValue(), bArr);
        }

        public static SetFunctionValue parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new SetFunctionValue().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class SetFunctionZoneValue extends MessageNano {
        private static volatile SetFunctionZoneValue[] _emptyArray;
        public int funcValue;
        public int function;
        public int zone;

        public static SetFunctionZoneValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SetFunctionZoneValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SetFunctionZoneValue() {
            clear();
        }

        public final SetFunctionZoneValue clear() {
            this.function = 0;
            this.zone = 0;
            this.funcValue = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.function);
            codedOutputByteBufferNano.writeInt32(2, this.zone);
            codedOutputByteBufferNano.writeInt32(3, this.funcValue);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.function) + CodedOutputByteBufferNano.computeInt32Size(2, this.zone) + CodedOutputByteBufferNano.computeInt32Size(3, this.funcValue);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final SetFunctionZoneValue mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.function = codedInputByteBufferNano.readInt32();
                } else if (tag == 16) {
                    this.zone = codedInputByteBufferNano.readInt32();
                } else if (tag != 24) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.funcValue = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static SetFunctionZoneValue parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (SetFunctionZoneValue) MessageNano.mergeFrom(new SetFunctionZoneValue(), bArr);
        }

        public static SetFunctionZoneValue parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new SetFunctionZoneValue().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class GetFunctionValue extends MessageNano {
        private static volatile GetFunctionValue[] _emptyArray;
        public int function;

        public static GetFunctionValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GetFunctionValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GetFunctionValue() {
            clear();
        }

        public final GetFunctionValue clear() {
            this.function = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.function);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.function);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final GetFunctionValue mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.function = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static GetFunctionValue parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (GetFunctionValue) MessageNano.mergeFrom(new GetFunctionValue(), bArr);
        }

        public static GetFunctionValue parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new GetFunctionValue().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class GetFunctionZoneValue extends MessageNano {
        private static volatile GetFunctionZoneValue[] _emptyArray;
        public int function;
        public int zone;

        public static GetFunctionZoneValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GetFunctionZoneValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GetFunctionZoneValue() {
            clear();
        }

        public final GetFunctionZoneValue clear() {
            this.function = 0;
            this.zone = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.function);
            codedOutputByteBufferNano.writeInt32(2, this.zone);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.function) + CodedOutputByteBufferNano.computeInt32Size(2, this.zone);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final GetFunctionZoneValue mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.function = codedInputByteBufferNano.readInt32();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.zone = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static GetFunctionZoneValue parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (GetFunctionZoneValue) MessageNano.mergeFrom(new GetFunctionZoneValue(), bArr);
        }

        public static GetFunctionZoneValue parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new GetFunctionZoneValue().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class MileageInfo extends MessageNano {
        private static volatile MileageInfo[] _emptyArray;
        public long enduranceMileage;
        public long nextMaintenanceMileage;
        public long totalMileage;
        public long tripDuration;
        public long tripMileage;

        public static MileageInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MileageInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MileageInfo() {
            clear();
        }

        public final MileageInfo clear() {
            this.totalMileage = 0L;
            this.tripMileage = 0L;
            this.tripDuration = 0L;
            this.enduranceMileage = 0L;
            this.nextMaintenanceMileage = 0L;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt64(1, this.totalMileage);
            codedOutputByteBufferNano.writeInt64(2, this.tripMileage);
            codedOutputByteBufferNano.writeInt64(3, this.tripDuration);
            codedOutputByteBufferNano.writeInt64(4, this.enduranceMileage);
            codedOutputByteBufferNano.writeInt64(5, this.nextMaintenanceMileage);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt64Size(1, this.totalMileage) + CodedOutputByteBufferNano.computeInt64Size(2, this.tripMileage) + CodedOutputByteBufferNano.computeInt64Size(3, this.tripDuration) + CodedOutputByteBufferNano.computeInt64Size(4, this.enduranceMileage) + CodedOutputByteBufferNano.computeInt64Size(5, this.nextMaintenanceMileage);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final MileageInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.totalMileage = codedInputByteBufferNano.readInt64();
                } else if (tag == 16) {
                    this.tripMileage = codedInputByteBufferNano.readInt64();
                } else if (tag == 24) {
                    this.tripDuration = codedInputByteBufferNano.readInt64();
                } else if (tag == 32) {
                    this.enduranceMileage = codedInputByteBufferNano.readInt64();
                } else if (tag != 40) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.nextMaintenanceMileage = codedInputByteBufferNano.readInt64();
                }
            }
        }

        public static MileageInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (MileageInfo) MessageNano.mergeFrom(new MileageInfo(), bArr);
        }

        public static MileageInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new MileageInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class WarningInfo extends MessageNano {
        private static volatile WarningInfo[] _emptyArray;
        public int warningId;
        public int warningPriority;

        public static WarningInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WarningInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WarningInfo() {
            clear();
        }

        public final WarningInfo clear() {
            this.warningId = 0;
            this.warningPriority = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.warningId);
            codedOutputByteBufferNano.writeInt32(2, this.warningPriority);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.warningId) + CodedOutputByteBufferNano.computeInt32Size(2, this.warningPriority);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final WarningInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.warningId = codedInputByteBufferNano.readInt32();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.warningPriority = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static WarningInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (WarningInfo) MessageNano.mergeFrom(new WarningInfo(), bArr);
        }

        public static WarningInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new WarningInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class VehicleSpeedObserverInfo extends MessageNano {
        private static volatile VehicleSpeedObserverInfo[] _emptyArray;
        public int duration;
        public double[] speeds;
        public int trend;

        public static VehicleSpeedObserverInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VehicleSpeedObserverInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VehicleSpeedObserverInfo() {
            clear();
        }

        public final VehicleSpeedObserverInfo clear() {
            this.speeds = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.trend = 0;
            this.duration = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            double[] dArr = this.speeds;
            if (dArr != null && dArr.length > 0) {
                int i = 0;
                while (true) {
                    double[] dArr2 = this.speeds;
                    if (i >= dArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeDouble(1, dArr2[i]);
                    i++;
                }
            }
            codedOutputByteBufferNano.writeInt32(2, this.trend);
            codedOutputByteBufferNano.writeInt32(3, this.duration);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            double[] dArr = this.speeds;
            if (dArr != null && dArr.length > 0) {
                iComputeSerializedSize = iComputeSerializedSize + (dArr.length * 8) + (dArr.length * 1);
            }
            return iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(2, this.trend) + CodedOutputByteBufferNano.computeInt32Size(3, this.duration);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final VehicleSpeedObserverInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 16) {
                    this.trend = codedInputByteBufferNano.readInt32();
                } else if (tag == 24) {
                    this.duration = codedInputByteBufferNano.readInt32();
                } else if (tag == 9) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 9);
                    double[] dArr = this.speeds;
                    int length = dArr == null ? 0 : dArr.length;
                    int i = repeatedFieldArrayLength + length;
                    double[] dArr2 = new double[i];
                    if (length != 0) {
                        System.arraycopy(dArr, 0, dArr2, 0, length);
                    }
                    while (length < i - 1) {
                        dArr2[length] = codedInputByteBufferNano.readDouble();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    dArr2[length] = codedInputByteBufferNano.readDouble();
                    this.speeds = dArr2;
                } else if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int rawVarint32 = codedInputByteBufferNano.readRawVarint32();
                    int iPushLimit = codedInputByteBufferNano.pushLimit(rawVarint32);
                    int i2 = rawVarint32 / 8;
                    double[] dArr3 = this.speeds;
                    int length2 = dArr3 == null ? 0 : dArr3.length;
                    int i3 = i2 + length2;
                    double[] dArr4 = new double[i3];
                    if (length2 != 0) {
                        System.arraycopy(dArr3, 0, dArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        dArr4[length2] = codedInputByteBufferNano.readDouble();
                        length2++;
                    }
                    this.speeds = dArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                }
            }
        }

        public static VehicleSpeedObserverInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (VehicleSpeedObserverInfo) MessageNano.mergeFrom(new VehicleSpeedObserverInfo(), bArr);
        }

        public static VehicleSpeedObserverInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new VehicleSpeedObserverInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ReqPreCharge2Pra extends MessageNano {
        private static volatile ReqPreCharge2Pra[] _emptyArray;
        public long delayTime;
        public long duration;

        public static ReqPreCharge2Pra[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReqPreCharge2Pra[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReqPreCharge2Pra() {
            clear();
        }

        public final ReqPreCharge2Pra clear() {
            this.delayTime = 0L;
            this.duration = 0L;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt64(1, this.delayTime);
            codedOutputByteBufferNano.writeInt64(2, this.duration);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt64Size(1, this.delayTime) + CodedOutputByteBufferNano.computeInt64Size(2, this.duration);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final ReqPreCharge2Pra mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.delayTime = codedInputByteBufferNano.readInt64();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.duration = codedInputByteBufferNano.readInt64();
                }
            }
        }

        public static ReqPreCharge2Pra parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ReqPreCharge2Pra) MessageNano.mergeFrom(new ReqPreCharge2Pra(), bArr);
        }

        public static ReqPreCharge2Pra parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ReqPreCharge2Pra().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ReqPreChargePerDay2Pre extends MessageNano {
        private static volatile ReqPreChargePerDay2Pre[] _emptyArray;
        public long delayTime;
        public long duration;

        public static ReqPreChargePerDay2Pre[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReqPreChargePerDay2Pre[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReqPreChargePerDay2Pre() {
            clear();
        }

        public final ReqPreChargePerDay2Pre clear() {
            this.delayTime = 0L;
            this.duration = 0L;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt64(1, this.delayTime);
            codedOutputByteBufferNano.writeInt64(2, this.duration);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt64Size(1, this.delayTime) + CodedOutputByteBufferNano.computeInt64Size(2, this.duration);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final ReqPreChargePerDay2Pre mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.delayTime = codedInputByteBufferNano.readInt64();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.duration = codedInputByteBufferNano.readInt64();
                }
            }
        }

        public static ReqPreChargePerDay2Pre parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ReqPreChargePerDay2Pre) MessageNano.mergeFrom(new ReqPreChargePerDay2Pre(), bArr);
        }

        public static ReqPreChargePerDay2Pre parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ReqPreChargePerDay2Pre().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class UsageVolumeInfo extends MessageNano {
        private static volatile UsageVolumeInfo[] _emptyArray;
        public int flags;
        public String usage;
        public int volume;

        public static UsageVolumeInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UsageVolumeInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UsageVolumeInfo() {
            clear();
        }

        public final UsageVolumeInfo clear() {
            this.usage = "";
            this.volume = 0;
            this.flags = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.usage);
            codedOutputByteBufferNano.writeInt32(2, this.volume);
            codedOutputByteBufferNano.writeInt32(3, this.flags);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.usage) + CodedOutputByteBufferNano.computeInt32Size(2, this.volume) + CodedOutputByteBufferNano.computeInt32Size(3, this.flags);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final UsageVolumeInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.usage = codedInputByteBufferNano.readString();
                } else if (tag == 16) {
                    this.volume = codedInputByteBufferNano.readInt32();
                } else if (tag != 24) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.flags = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static UsageVolumeInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (UsageVolumeInfo) MessageNano.mergeFrom(new UsageVolumeInfo(), bArr);
        }

        public static UsageVolumeInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new UsageVolumeInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class RegisterSensorInfo extends MessageNano {
        private static volatile RegisterSensorInfo[] _emptyArray;
        public int rate;
        public int sensor;

        public static RegisterSensorInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RegisterSensorInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RegisterSensorInfo() {
            clear();
        }

        public final RegisterSensorInfo clear() {
            this.sensor = 0;
            this.rate = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.sensor);
            codedOutputByteBufferNano.writeInt32(2, this.rate);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.sensor) + CodedOutputByteBufferNano.computeInt32Size(2, this.rate);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final RegisterSensorInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.sensor = codedInputByteBufferNano.readInt32();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.rate = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static RegisterSensorInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (RegisterSensorInfo) MessageNano.mergeFrom(new RegisterSensorInfo(), bArr);
        }

        public static RegisterSensorInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new RegisterSensorInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class UsageDisplayParam extends MessageNano {
        private static volatile UsageDisplayParam[] _emptyArray;
        public int displayId;
        public String usage;

        public static UsageDisplayParam[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UsageDisplayParam[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UsageDisplayParam() {
            clear();
        }

        public final UsageDisplayParam clear() {
            this.usage = "";
            this.displayId = Integer.MIN_VALUE;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.usage);
            codedOutputByteBufferNano.writeInt32(2, this.displayId);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.usage) + CodedOutputByteBufferNano.computeInt32Size(2, this.displayId);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final UsageDisplayParam mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.usage = codedInputByteBufferNano.readString();
                } else if (tag != 16) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.displayId = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static UsageVolumeInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (UsageVolumeInfo) MessageNano.mergeFrom(new UsageVolumeInfo(), bArr);
        }

        public static UsageVolumeInfo parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new UsageVolumeInfo().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DisplayLightParam extends MessageNano {
        private static volatile DisplayLightParam[] _emptyArray;
        public int displayId;
        public float light;

        public static DisplayLightParam[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisplayLightParam[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisplayLightParam() {
            clear();
        }

        public final DisplayLightParam clear() {
            this.displayId = 0;
            this.light = 0.0f;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.displayId);
            codedOutputByteBufferNano.writeFloat(2, this.light);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.displayId) + CodedOutputByteBufferNano.computeFloatSize(2, this.light);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final DisplayLightParam mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.displayId = codedInputByteBufferNano.readInt32();
                } else if (tag != 21) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.light = codedInputByteBufferNano.readFloat();
                }
            }
        }

        public static DisplayLightParam parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DisplayLightParam) MessageNano.mergeFrom(new DisplayLightParam(), bArr);
        }

        public static DisplayLightParam parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DisplayLightParam().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DisplayLightStepParam extends MessageNano {
        private static volatile DisplayLightStepParam[] _emptyArray;
        public int displayId;
        public float step;

        public static DisplayLightStepParam[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisplayLightStepParam[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisplayLightStepParam() {
            clear();
        }

        public final DisplayLightStepParam clear() {
            this.displayId = 0;
            this.step = 0.0f;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.displayId);
            codedOutputByteBufferNano.writeFloat(2, this.step);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.displayId) + CodedOutputByteBufferNano.computeFloatSize(2, this.step);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final DisplayLightStepParam mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.displayId = codedInputByteBufferNano.readInt32();
                } else if (tag != 21) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.step = codedInputByteBufferNano.readFloat();
                }
            }
        }

        public static DisplayLightStepParam parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DisplayLightStepParam) MessageNano.mergeFrom(new DisplayLightStepParam(), bArr);
        }

        public static DisplayLightStepParam parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DisplayLightStepParam().mergeFrom(codedInputByteBufferNano);
        }
    }
}
