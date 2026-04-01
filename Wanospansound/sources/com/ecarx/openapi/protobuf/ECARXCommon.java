package com.ecarx.openapi.protobuf;

import com.ecarx.eas.framework.sdk.proto.CodedInputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.CodedOutputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.InternalNano;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.framework.sdk.proto.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface ECARXCommon {

    public static final class VoidMsg extends MessageNano {
        private static volatile VoidMsg[] _emptyArray;
        public String value;

        public static VoidMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VoidMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VoidMsg() {
            clear();
        }

        public final VoidMsg clear() {
            this.value = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.value.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.value);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            return !this.value.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(1, this.value) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final VoidMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.value = codedInputByteBufferNano.readString();
                }
            }
        }

        public static VoidMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (VoidMsg) MessageNano.mergeFrom(new VoidMsg(), bArr);
        }

        public static VoidMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new VoidMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class BoolMsg extends MessageNano {
        private static volatile BoolMsg[] _emptyArray;
        public boolean value;

        public static BoolMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BoolMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BoolMsg() {
            clear();
        }

        public final BoolMsg clear() {
            this.value = false;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            boolean z = this.value;
            if (z) {
                codedOutputByteBufferNano.writeBool(1, z);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            boolean z = this.value;
            return z ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBoolSize(1, z) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final BoolMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
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
                    this.value = codedInputByteBufferNano.readBool();
                }
            }
        }

        public static BoolMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (BoolMsg) MessageNano.mergeFrom(new BoolMsg(), bArr);
        }

        public static BoolMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new BoolMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DoubleMsg extends MessageNano {
        private static volatile DoubleMsg[] _emptyArray;
        public double value;

        public static DoubleMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DoubleMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DoubleMsg() {
            clear();
        }

        public final DoubleMsg clear() {
            this.value = 0.0d;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeDouble(1, this.value);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeDoubleSize(1, this.value);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final DoubleMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 9) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.value = codedInputByteBufferNano.readDouble();
                }
            }
        }

        public static DoubleMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DoubleMsg) MessageNano.mergeFrom(new DoubleMsg(), bArr);
        }

        public static DoubleMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DoubleMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class FloatMsg extends MessageNano {
        private static volatile FloatMsg[] _emptyArray;
        public float value;

        public static FloatMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FloatMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FloatMsg() {
            clear();
        }

        public final FloatMsg clear() {
            this.value = 0.0f;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeFloat(1, this.value);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeFloatSize(1, this.value);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final FloatMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 13) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.value = codedInputByteBufferNano.readFloat();
                }
            }
        }

        public static FloatMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (FloatMsg) MessageNano.mergeFrom(new FloatMsg(), bArr);
        }

        public static FloatMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new FloatMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class StringMsg extends MessageNano {
        private static volatile StringMsg[] _emptyArray;
        public String value;

        public static StringMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new StringMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public StringMsg() {
            clear();
        }

        public final StringMsg clear() {
            this.value = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.value);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.value);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final StringMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.value = codedInputByteBufferNano.readString();
                }
            }
        }

        public static StringMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (StringMsg) MessageNano.mergeFrom(new StringMsg(), bArr);
        }

        public static StringMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new StringMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class IntMsg extends MessageNano {
        private static volatile IntMsg[] _emptyArray;
        public int value;

        public static IntMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IntMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IntMsg() {
            clear();
        }

        public final IntMsg clear() {
            this.value = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt32(1, this.value);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.value);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final IntMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
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
                    this.value = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static IntMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (IntMsg) MessageNano.mergeFrom(new IntMsg(), bArr);
        }

        public static IntMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new IntMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class LongMsg extends MessageNano {
        private static volatile LongMsg[] _emptyArray;
        public long value;

        public static LongMsg[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LongMsg[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LongMsg() {
            clear();
        }

        public final LongMsg clear() {
            this.value = 0L;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeInt64(1, this.value);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt64Size(1, this.value);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final LongMsg mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
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
                    this.value = codedInputByteBufferNano.readInt64();
                }
            }
        }

        public static LongMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (LongMsg) MessageNano.mergeFrom(new LongMsg(), bArr);
        }

        public static LongMsg parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new LongMsg().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ComponentName extends MessageNano {
        private static volatile ComponentName[] _emptyArray;
        public String cls;
        public String pkg;

        public static ComponentName[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ComponentName[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ComponentName() {
            clear();
        }

        public final ComponentName clear() {
            this.pkg = "";
            this.cls = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.pkg);
            codedOutputByteBufferNano.writeString(2, this.cls);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.pkg) + CodedOutputByteBufferNano.computeStringSize(2, this.cls);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final ComponentName mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.pkg = codedInputByteBufferNano.readString();
                } else if (tag != 18) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.cls = codedInputByteBufferNano.readString();
                }
            }
        }

        public static ComponentName parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ComponentName) MessageNano.mergeFrom(new ComponentName(), bArr);
        }

        public static ComponentName parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ComponentName().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class ComponentNameList extends MessageNano {
        private static volatile ComponentNameList[] _emptyArray;
        public ComponentName[] item;

        public static ComponentNameList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ComponentNameList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ComponentNameList() {
            clear();
        }

        public final ComponentNameList clear() {
            this.item = ComponentName.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            ComponentName[] componentNameArr = this.item;
            if (componentNameArr != null && componentNameArr.length > 0) {
                int i = 0;
                while (true) {
                    ComponentName[] componentNameArr2 = this.item;
                    if (i >= componentNameArr2.length) {
                        break;
                    }
                    ComponentName componentName = componentNameArr2[i];
                    if (componentName != null) {
                        codedOutputByteBufferNano.writeMessage(1, componentName);
                    }
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            ComponentName[] componentNameArr = this.item;
            if (componentNameArr != null && componentNameArr.length > 0) {
                int i = 0;
                while (true) {
                    ComponentName[] componentNameArr2 = this.item;
                    if (i >= componentNameArr2.length) {
                        break;
                    }
                    ComponentName componentName = componentNameArr2[i];
                    if (componentName != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, componentName);
                    }
                    i++;
                }
            }
            return iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final ComponentNameList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    ComponentName[] componentNameArr = this.item;
                    int length = componentNameArr == null ? 0 : componentNameArr.length;
                    int i = repeatedFieldArrayLength + length;
                    ComponentName[] componentNameArr2 = new ComponentName[i];
                    if (length != 0) {
                        System.arraycopy(componentNameArr, 0, componentNameArr2, 0, length);
                    }
                    while (length < i - 1) {
                        ComponentName componentName = new ComponentName();
                        componentNameArr2[length] = componentName;
                        codedInputByteBufferNano.readMessage(componentName);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    ComponentName componentName2 = new ComponentName();
                    componentNameArr2[length] = componentName2;
                    codedInputByteBufferNano.readMessage(componentName2);
                    this.item = componentNameArr2;
                }
            }
        }

        public static ComponentNameList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (ComponentNameList) MessageNano.mergeFrom(new ComponentNameList(), bArr);
        }

        public static ComponentNameList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new ComponentNameList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class BtDevice extends MessageNano {
        private static volatile BtDevice[] _emptyArray;
        public String address;
        public String name;

        public static BtDevice[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BtDevice[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BtDevice() {
            clear();
        }

        public final BtDevice clear() {
            this.address = "";
            this.name = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.address);
            codedOutputByteBufferNano.writeString(2, this.name);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.address) + CodedOutputByteBufferNano.computeStringSize(2, this.name);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final BtDevice mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.address = codedInputByteBufferNano.readString();
                } else if (tag != 18) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.name = codedInputByteBufferNano.readString();
                }
            }
        }

        public static BtDevice parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (BtDevice) MessageNano.mergeFrom(new BtDevice(), bArr);
        }

        public static BtDevice parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new BtDevice().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class BtDeviceList extends MessageNano {
        private static volatile BtDeviceList[] _emptyArray;
        public BtDevice[] item;

        public static BtDeviceList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BtDeviceList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BtDeviceList() {
            clear();
        }

        public final BtDeviceList clear() {
            this.item = BtDevice.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            BtDevice[] btDeviceArr = this.item;
            if (btDeviceArr != null && btDeviceArr.length > 0) {
                int i = 0;
                while (true) {
                    BtDevice[] btDeviceArr2 = this.item;
                    if (i >= btDeviceArr2.length) {
                        break;
                    }
                    BtDevice btDevice = btDeviceArr2[i];
                    if (btDevice != null) {
                        codedOutputByteBufferNano.writeMessage(1, btDevice);
                    }
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            BtDevice[] btDeviceArr = this.item;
            if (btDeviceArr != null && btDeviceArr.length > 0) {
                int i = 0;
                while (true) {
                    BtDevice[] btDeviceArr2 = this.item;
                    if (i >= btDeviceArr2.length) {
                        break;
                    }
                    BtDevice btDevice = btDeviceArr2[i];
                    if (btDevice != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, btDevice);
                    }
                    i++;
                }
            }
            return iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final BtDeviceList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    BtDevice[] btDeviceArr = this.item;
                    int length = btDeviceArr == null ? 0 : btDeviceArr.length;
                    int i = repeatedFieldArrayLength + length;
                    BtDevice[] btDeviceArr2 = new BtDevice[i];
                    if (length != 0) {
                        System.arraycopy(btDeviceArr, 0, btDeviceArr2, 0, length);
                    }
                    while (length < i - 1) {
                        BtDevice btDevice = new BtDevice();
                        btDeviceArr2[length] = btDevice;
                        codedInputByteBufferNano.readMessage(btDevice);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    BtDevice btDevice2 = new BtDevice();
                    btDeviceArr2[length] = btDevice2;
                    codedInputByteBufferNano.readMessage(btDevice2);
                    this.item = btDeviceArr2;
                }
            }
        }

        public static BtDeviceList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (BtDeviceList) MessageNano.mergeFrom(new BtDeviceList(), bArr);
        }

        public static BtDeviceList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new BtDeviceList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class BoolMsgList extends MessageNano {
        private static volatile BoolMsgList[] _emptyArray;
        public boolean[] value;

        public static BoolMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BoolMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BoolMsgList() {
            clear();
        }

        public final BoolMsgList clear() {
            this.value = WireFormatNano.EMPTY_BOOLEAN_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            boolean[] zArr = this.value;
            if (zArr != null && zArr.length > 0) {
                int i = 0;
                while (true) {
                    boolean[] zArr2 = this.value;
                    if (i >= zArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeBool(1, zArr2[i]);
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            boolean[] zArr = this.value;
            return (zArr == null || zArr.length <= 0) ? iComputeSerializedSize : iComputeSerializedSize + (zArr.length * 1) + (zArr.length * 1);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final BoolMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 8);
                    boolean[] zArr = this.value;
                    int length = zArr == null ? 0 : zArr.length;
                    int i = repeatedFieldArrayLength + length;
                    boolean[] zArr2 = new boolean[i];
                    if (length != 0) {
                        System.arraycopy(zArr, 0, zArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zArr2[length] = codedInputByteBufferNano.readBool();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    zArr2[length] = codedInputByteBufferNano.readBool();
                    this.value = zArr2;
                } else if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    int i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        codedInputByteBufferNano.readBool();
                        i2++;
                    }
                    codedInputByteBufferNano.rewindToPosition(position);
                    boolean[] zArr3 = this.value;
                    int length2 = zArr3 == null ? 0 : zArr3.length;
                    int i3 = i2 + length2;
                    boolean[] zArr4 = new boolean[i3];
                    if (length2 != 0) {
                        System.arraycopy(zArr3, 0, zArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        zArr4[length2] = codedInputByteBufferNano.readBool();
                        length2++;
                    }
                    this.value = zArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                }
            }
        }

        public static BoolMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (BoolMsgList) MessageNano.mergeFrom(new BoolMsgList(), bArr);
        }

        public static BoolMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new BoolMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DoubleMsgList extends MessageNano {
        private static volatile DoubleMsgList[] _emptyArray;
        public double[] value;

        public static DoubleMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DoubleMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DoubleMsgList() {
            clear();
        }

        public final DoubleMsgList clear() {
            this.value = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            double[] dArr = this.value;
            if (dArr != null && dArr.length > 0) {
                int i = 0;
                while (true) {
                    double[] dArr2 = this.value;
                    if (i >= dArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeDouble(1, dArr2[i]);
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            double[] dArr = this.value;
            return (dArr == null || dArr.length <= 0) ? iComputeSerializedSize : iComputeSerializedSize + (dArr.length * 8) + (dArr.length * 1);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final DoubleMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 9) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 9);
                    double[] dArr = this.value;
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
                    this.value = dArr2;
                } else if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int rawVarint32 = codedInputByteBufferNano.readRawVarint32();
                    int iPushLimit = codedInputByteBufferNano.pushLimit(rawVarint32);
                    int i2 = rawVarint32 / 8;
                    double[] dArr3 = this.value;
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
                    this.value = dArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                }
            }
        }

        public static DoubleMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DoubleMsgList) MessageNano.mergeFrom(new DoubleMsgList(), bArr);
        }

        public static DoubleMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DoubleMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class FloatMsgList extends MessageNano {
        private static volatile FloatMsgList[] _emptyArray;
        public float[] value;

        public static FloatMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FloatMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FloatMsgList() {
            clear();
        }

        public final FloatMsgList clear() {
            this.value = WireFormatNano.EMPTY_FLOAT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            float[] fArr = this.value;
            if (fArr != null && fArr.length > 0) {
                int i = 0;
                while (true) {
                    float[] fArr2 = this.value;
                    if (i >= fArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeFloat(1, fArr2[i]);
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            float[] fArr = this.value;
            return (fArr == null || fArr.length <= 0) ? iComputeSerializedSize : iComputeSerializedSize + (fArr.length * 4) + (fArr.length * 1);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final FloatMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    int rawVarint32 = codedInputByteBufferNano.readRawVarint32();
                    int iPushLimit = codedInputByteBufferNano.pushLimit(rawVarint32);
                    int i = rawVarint32 / 4;
                    float[] fArr = this.value;
                    int length = fArr == null ? 0 : fArr.length;
                    int i2 = i + length;
                    float[] fArr2 = new float[i2];
                    if (length != 0) {
                        System.arraycopy(fArr, 0, fArr2, 0, length);
                    }
                    while (length < i2) {
                        fArr2[length] = codedInputByteBufferNano.readFloat();
                        length++;
                    }
                    this.value = fArr2;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                } else if (tag != 13) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 13);
                    float[] fArr3 = this.value;
                    int length2 = fArr3 == null ? 0 : fArr3.length;
                    int i3 = repeatedFieldArrayLength + length2;
                    float[] fArr4 = new float[i3];
                    if (length2 != 0) {
                        System.arraycopy(fArr3, 0, fArr4, 0, length2);
                    }
                    while (length2 < i3 - 1) {
                        fArr4[length2] = codedInputByteBufferNano.readFloat();
                        codedInputByteBufferNano.readTag();
                        length2++;
                    }
                    fArr4[length2] = codedInputByteBufferNano.readFloat();
                    this.value = fArr4;
                }
            }
        }

        public static FloatMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (FloatMsgList) MessageNano.mergeFrom(new FloatMsgList(), bArr);
        }

        public static FloatMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new FloatMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class StringMsgList extends MessageNano {
        private static volatile StringMsgList[] _emptyArray;
        public String[] value;

        public static StringMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new StringMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public StringMsgList() {
            clear();
        }

        public final StringMsgList clear() {
            this.value = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            String[] strArr = this.value;
            if (strArr != null && strArr.length > 0) {
                int i = 0;
                while (true) {
                    String[] strArr2 = this.value;
                    if (i >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i];
                    if (str != null) {
                        codedOutputByteBufferNano.writeString(1, str);
                    }
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            String[] strArr = this.value;
            if (strArr == null || strArr.length <= 0) {
                return iComputeSerializedSize;
            }
            int i = 0;
            int iComputeStringSizeNoTag = 0;
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.value;
                if (i >= strArr2.length) {
                    return iComputeSerializedSize + iComputeStringSizeNoTag + (i2 * 1);
                }
                String str = strArr2[i];
                if (str != null) {
                    i2++;
                    iComputeStringSizeNoTag += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                }
                i++;
            }
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final StringMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    String[] strArr = this.value;
                    int length = strArr == null ? 0 : strArr.length;
                    int i = repeatedFieldArrayLength + length;
                    String[] strArr2 = new String[i];
                    if (length != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                    }
                    while (length < i - 1) {
                        strArr2[length] = codedInputByteBufferNano.readString();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    strArr2[length] = codedInputByteBufferNano.readString();
                    this.value = strArr2;
                }
            }
        }

        public static StringMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (StringMsgList) MessageNano.mergeFrom(new StringMsgList(), bArr);
        }

        public static StringMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new StringMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class IntMsgList extends MessageNano {
        private static volatile IntMsgList[] _emptyArray;
        public int[] value;

        public static IntMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IntMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IntMsgList() {
            clear();
        }

        public final IntMsgList clear() {
            this.value = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int[] iArr = this.value;
            if (iArr != null && iArr.length > 0) {
                int i = 0;
                while (true) {
                    int[] iArr2 = this.value;
                    if (i >= iArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(1, iArr2[i]);
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int[] iArr = this.value;
            if (iArr == null || iArr.length <= 0) {
                return iComputeSerializedSize;
            }
            int i = 0;
            int iComputeInt32SizeNoTag = 0;
            while (true) {
                int[] iArr2 = this.value;
                if (i < iArr2.length) {
                    iComputeInt32SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr2[i]);
                    i++;
                } else {
                    return iComputeSerializedSize + iComputeInt32SizeNoTag + (iArr2.length * 1);
                }
            }
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final IntMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 8);
                    int[] iArr = this.value;
                    int length = iArr == null ? 0 : iArr.length;
                    int i = repeatedFieldArrayLength + length;
                    int[] iArr2 = new int[i];
                    if (length != 0) {
                        System.arraycopy(iArr, 0, iArr2, 0, length);
                    }
                    while (length < i - 1) {
                        iArr2[length] = codedInputByteBufferNano.readInt32();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    iArr2[length] = codedInputByteBufferNano.readInt32();
                    this.value = iArr2;
                } else if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    int i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        codedInputByteBufferNano.readInt32();
                        i2++;
                    }
                    codedInputByteBufferNano.rewindToPosition(position);
                    int[] iArr3 = this.value;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int i3 = i2 + length2;
                    int[] iArr4 = new int[i3];
                    if (length2 != 0) {
                        System.arraycopy(iArr3, 0, iArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        iArr4[length2] = codedInputByteBufferNano.readInt32();
                        length2++;
                    }
                    this.value = iArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                }
            }
        }

        public static IntMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (IntMsgList) MessageNano.mergeFrom(new IntMsgList(), bArr);
        }

        public static IntMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new IntMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class LongMsgList extends MessageNano {
        private static volatile LongMsgList[] _emptyArray;
        public long[] value;

        public static LongMsgList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LongMsgList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LongMsgList() {
            clear();
        }

        public final LongMsgList clear() {
            this.value = WireFormatNano.EMPTY_LONG_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            long[] jArr = this.value;
            if (jArr != null && jArr.length > 0) {
                int i = 0;
                while (true) {
                    long[] jArr2 = this.value;
                    if (i >= jArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt64(1, jArr2[i]);
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            long[] jArr = this.value;
            if (jArr == null || jArr.length <= 0) {
                return iComputeSerializedSize;
            }
            int i = 0;
            int iComputeInt64SizeNoTag = 0;
            while (true) {
                long[] jArr2 = this.value;
                if (i < jArr2.length) {
                    iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeInt64SizeNoTag(jArr2[i]);
                    i++;
                } else {
                    return iComputeSerializedSize + iComputeInt64SizeNoTag + (jArr2.length * 1);
                }
            }
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final LongMsgList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 8);
                    long[] jArr = this.value;
                    int length = jArr == null ? 0 : jArr.length;
                    int i = repeatedFieldArrayLength + length;
                    long[] jArr2 = new long[i];
                    if (length != 0) {
                        System.arraycopy(jArr, 0, jArr2, 0, length);
                    }
                    while (length < i - 1) {
                        jArr2[length] = codedInputByteBufferNano.readInt64();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    jArr2[length] = codedInputByteBufferNano.readInt64();
                    this.value = jArr2;
                } else if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    int i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        codedInputByteBufferNano.readInt64();
                        i2++;
                    }
                    codedInputByteBufferNano.rewindToPosition(position);
                    long[] jArr3 = this.value;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    int i3 = i2 + length2;
                    long[] jArr4 = new long[i3];
                    if (length2 != 0) {
                        System.arraycopy(jArr3, 0, jArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        jArr4[length2] = codedInputByteBufferNano.readInt64();
                        length2++;
                    }
                    this.value = jArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                }
            }
        }

        public static LongMsgList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (LongMsgList) MessageNano.mergeFrom(new LongMsgList(), bArr);
        }

        public static LongMsgList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new LongMsgList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class SetDefaultAppScheme extends MessageNano {
        private static volatile SetDefaultAppScheme[] _emptyArray;
        public String category;
        public String scheme;

        public static SetDefaultAppScheme[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SetDefaultAppScheme[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SetDefaultAppScheme() {
            clear();
        }

        public final SetDefaultAppScheme clear() {
            this.category = "";
            this.scheme = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.category);
            codedOutputByteBufferNano.writeString(2, this.scheme);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.category) + CodedOutputByteBufferNano.computeStringSize(2, this.scheme);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final SetDefaultAppScheme mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.category = codedInputByteBufferNano.readString();
                } else if (tag != 18) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.scheme = codedInputByteBufferNano.readString();
                }
            }
        }

        public static SetDefaultAppScheme parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (SetDefaultAppScheme) MessageNano.mergeFrom(new SetDefaultAppScheme(), bArr);
        }

        public static SetDefaultAppScheme parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new SetDefaultAppScheme().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class CategoryApp extends MessageNano {
        private static volatile CategoryApp[] _emptyArray;
        public String appname;
        public String category;
        public int isIntalled;
        public String packagename;
        public String scheme;
        public int sortorder;

        public static CategoryApp[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CategoryApp[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CategoryApp() {
            clear();
        }

        public final CategoryApp clear() {
            this.scheme = "";
            this.packagename = "";
            this.category = "";
            this.appname = "";
            this.isIntalled = 0;
            this.sortorder = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.scheme);
            codedOutputByteBufferNano.writeString(2, this.packagename);
            codedOutputByteBufferNano.writeString(3, this.category);
            codedOutputByteBufferNano.writeString(4, this.appname);
            codedOutputByteBufferNano.writeInt32(5, this.isIntalled);
            codedOutputByteBufferNano.writeInt32(6, this.sortorder);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.scheme) + CodedOutputByteBufferNano.computeStringSize(2, this.packagename) + CodedOutputByteBufferNano.computeStringSize(3, this.category) + CodedOutputByteBufferNano.computeStringSize(4, this.appname) + CodedOutputByteBufferNano.computeInt32Size(5, this.isIntalled) + CodedOutputByteBufferNano.computeInt32Size(6, this.sortorder);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final CategoryApp mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.scheme = codedInputByteBufferNano.readString();
                } else if (tag == 18) {
                    this.packagename = codedInputByteBufferNano.readString();
                } else if (tag == 26) {
                    this.category = codedInputByteBufferNano.readString();
                } else if (tag == 34) {
                    this.appname = codedInputByteBufferNano.readString();
                } else if (tag == 40) {
                    this.isIntalled = codedInputByteBufferNano.readInt32();
                } else if (tag != 48) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.sortorder = codedInputByteBufferNano.readInt32();
                }
            }
        }

        public static CategoryApp parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (CategoryApp) MessageNano.mergeFrom(new CategoryApp(), bArr);
        }

        public static CategoryApp parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new CategoryApp().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class CategoryList extends MessageNano {
        private static volatile CategoryList[] _emptyArray;
        public CategoryApp[] item;

        public static CategoryList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CategoryList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CategoryList() {
            clear();
        }

        public final CategoryList clear() {
            this.item = CategoryApp.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            CategoryApp[] categoryAppArr = this.item;
            if (categoryAppArr != null && categoryAppArr.length > 0) {
                int i = 0;
                while (true) {
                    CategoryApp[] categoryAppArr2 = this.item;
                    if (i >= categoryAppArr2.length) {
                        break;
                    }
                    CategoryApp categoryApp = categoryAppArr2[i];
                    if (categoryApp != null) {
                        codedOutputByteBufferNano.writeMessage(1, categoryApp);
                    }
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            CategoryApp[] categoryAppArr = this.item;
            if (categoryAppArr != null && categoryAppArr.length > 0) {
                int i = 0;
                while (true) {
                    CategoryApp[] categoryAppArr2 = this.item;
                    if (i >= categoryAppArr2.length) {
                        break;
                    }
                    CategoryApp categoryApp = categoryAppArr2[i];
                    if (categoryApp != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, categoryApp);
                    }
                    i++;
                }
            }
            return iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final CategoryList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 10) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    CategoryApp[] categoryAppArr = this.item;
                    int length = categoryAppArr == null ? 0 : categoryAppArr.length;
                    int i = repeatedFieldArrayLength + length;
                    CategoryApp[] categoryAppArr2 = new CategoryApp[i];
                    if (length != 0) {
                        System.arraycopy(categoryAppArr, 0, categoryAppArr2, 0, length);
                    }
                    while (length < i - 1) {
                        CategoryApp categoryApp = new CategoryApp();
                        categoryAppArr2[length] = categoryApp;
                        codedInputByteBufferNano.readMessage(categoryApp);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    CategoryApp categoryApp2 = new CategoryApp();
                    categoryAppArr2[length] = categoryApp2;
                    codedInputByteBufferNano.readMessage(categoryApp2);
                    this.item = categoryAppArr2;
                }
            }
        }

        public static CategoryList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (CategoryList) MessageNano.mergeFrom(new CategoryList(), bArr);
        }

        public static CategoryList parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new CategoryList().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class DefaultCategory extends MessageNano {
        private static volatile DefaultCategory[] _emptyArray;
        public String defaultMapScheme;
        public String selectedMapScheme;

        public static DefaultCategory[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DefaultCategory[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DefaultCategory() {
            clear();
        }

        public final DefaultCategory clear() {
            this.selectedMapScheme = "";
            this.defaultMapScheme = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            codedOutputByteBufferNano.writeString(1, this.selectedMapScheme);
            codedOutputByteBufferNano.writeString(2, this.defaultMapScheme);
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.selectedMapScheme) + CodedOutputByteBufferNano.computeStringSize(2, this.defaultMapScheme);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final DefaultCategory mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.selectedMapScheme = codedInputByteBufferNano.readString();
                } else if (tag != 18) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.defaultMapScheme = codedInputByteBufferNano.readString();
                }
            }
        }

        public static DefaultCategory parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (DefaultCategory) MessageNano.mergeFrom(new DefaultCategory(), bArr);
        }

        public static DefaultCategory parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new DefaultCategory().mergeFrom(codedInputByteBufferNano);
        }
    }
}
