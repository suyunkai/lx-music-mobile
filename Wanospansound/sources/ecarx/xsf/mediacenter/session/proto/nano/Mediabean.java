package ecarx.xsf.mediacenter.session.proto.nano;

import androidx.media3.extractor.ts.TsExtractor;
import com.ecarx.eas.framework.sdk.proto.CodedInputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.CodedOutputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.InternalNano;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.framework.sdk.proto.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes3.dex */
public interface Mediabean {

    public static final class e extends MessageNano {
        private static volatile e[] v;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f769a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f770b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f771c = "";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f772d = "";
        public String e = "";
        public String f = "";
        public String g = "";
        public String h = "";
        public String i = "";
        public long j = 0;
        public long k = 0;
        public boolean l = false;
        public boolean m = false;
        public boolean n = false;
        public boolean o = false;
        public boolean p = false;
        public String q = "";
        public int r = 0;
        public String s = "";
        public String t = "";
        public boolean u = false;

        public static e[] a() {
            if (v == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (v == null) {
                        v = new e[0];
                    }
                }
            }
            return v;
        }

        public e() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f769a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f769a);
            }
            int i = this.f770b;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(2, i);
            }
            if (!this.f771c.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.f771c);
            }
            if (!this.f772d.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.f772d);
            }
            if (!this.e.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.e);
            }
            if (!this.f.equals("")) {
                codedOutputByteBufferNano.writeString(6, this.f);
            }
            if (!this.g.equals("")) {
                codedOutputByteBufferNano.writeString(7, this.g);
            }
            if (!this.h.equals("")) {
                codedOutputByteBufferNano.writeString(8, this.h);
            }
            if (!this.i.equals("")) {
                codedOutputByteBufferNano.writeString(9, this.i);
            }
            long j = this.j;
            if (j != 0) {
                codedOutputByteBufferNano.writeFixed64(10, j);
            }
            long j2 = this.k;
            if (j2 != 0) {
                codedOutputByteBufferNano.writeFixed64(11, j2);
            }
            boolean z = this.l;
            if (z) {
                codedOutputByteBufferNano.writeBool(12, z);
            }
            boolean z2 = this.m;
            if (z2) {
                codedOutputByteBufferNano.writeBool(13, z2);
            }
            boolean z3 = this.n;
            if (z3) {
                codedOutputByteBufferNano.writeBool(14, z3);
            }
            boolean z4 = this.o;
            if (z4) {
                codedOutputByteBufferNano.writeBool(15, z4);
            }
            boolean z5 = this.p;
            if (z5) {
                codedOutputByteBufferNano.writeBool(16, z5);
            }
            if (!this.q.equals("")) {
                codedOutputByteBufferNano.writeString(17, this.q);
            }
            int i2 = this.r;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(18, i2);
            }
            if (!this.s.equals("")) {
                codedOutputByteBufferNano.writeString(19, this.s);
            }
            if (!this.t.equals("")) {
                codedOutputByteBufferNano.writeString(20, this.t);
            }
            boolean z6 = this.u;
            if (z6) {
                codedOutputByteBufferNano.writeBool(21, z6);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f769a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f769a);
            }
            int i = this.f770b;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i);
            }
            if (!this.f771c.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f771c);
            }
            if (!this.f772d.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f772d);
            }
            if (!this.e.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.e);
            }
            if (!this.f.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(6, this.f);
            }
            if (!this.g.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(7, this.g);
            }
            if (!this.h.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(8, this.h);
            }
            if (!this.i.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(9, this.i);
            }
            long j = this.j;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(10, j);
            }
            long j2 = this.k;
            if (j2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(11, j2);
            }
            boolean z = this.l;
            if (z) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(12, z);
            }
            boolean z2 = this.m;
            if (z2) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(13, z2);
            }
            boolean z3 = this.n;
            if (z3) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(14, z3);
            }
            boolean z4 = this.o;
            if (z4) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(15, z4);
            }
            boolean z5 = this.p;
            if (z5) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(16, z5);
            }
            if (!this.q.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(17, this.q);
            }
            int i2 = this.r;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(18, i2);
            }
            if (!this.s.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(19, this.s);
            }
            if (!this.t.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(20, this.t);
            }
            boolean z6 = this.u;
            return z6 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBoolSize(21, z6) : iComputeSerializedSize;
        }

        public static e a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (e) MessageNano.mergeFrom(new e(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        this.f769a = codedInputByteBufferNano.readString();
                        break;
                    case 16:
                        this.f770b = codedInputByteBufferNano.readInt32();
                        break;
                    case 26:
                        this.f771c = codedInputByteBufferNano.readString();
                        break;
                    case 34:
                        this.f772d = codedInputByteBufferNano.readString();
                        break;
                    case 42:
                        this.e = codedInputByteBufferNano.readString();
                        break;
                    case 50:
                        this.f = codedInputByteBufferNano.readString();
                        break;
                    case 58:
                        this.g = codedInputByteBufferNano.readString();
                        break;
                    case 66:
                        this.h = codedInputByteBufferNano.readString();
                        break;
                    case 74:
                        this.i = codedInputByteBufferNano.readString();
                        break;
                    case 81:
                        this.j = codedInputByteBufferNano.readFixed64();
                        break;
                    case 89:
                        this.k = codedInputByteBufferNano.readFixed64();
                        break;
                    case 96:
                        this.l = codedInputByteBufferNano.readBool();
                        break;
                    case 104:
                        this.m = codedInputByteBufferNano.readBool();
                        break;
                    case 112:
                        this.n = codedInputByteBufferNano.readBool();
                        break;
                    case 120:
                        this.o = codedInputByteBufferNano.readBool();
                        break;
                    case 128:
                        this.p = codedInputByteBufferNano.readBool();
                        break;
                    case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                        this.q = codedInputByteBufferNano.readString();
                        break;
                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_444 /* 144 */:
                        this.r = codedInputByteBufferNano.readInt32();
                        break;
                    case 154:
                        this.s = codedInputByteBufferNano.readString();
                        break;
                    case 162:
                        this.t = codedInputByteBufferNano.readString();
                        break;
                    case 168:
                        this.u = codedInputByteBufferNano.readBool();
                        break;
                    default:
                        if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                            return this;
                        }
                        break;
                        break;
                }
            }
        }
    }

    public static final class f extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f773a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f774b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f775c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f776d = 0;
        public String e = "";

        public f() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f773a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f773a);
            }
            if (!this.f774b.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f774b);
            }
            int i = this.f775c;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(3, i);
            }
            int i2 = this.f776d;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i2);
            }
            if (!this.e.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.e);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f773a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f773a);
            }
            if (!this.f774b.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f774b);
            }
            int i = this.f775c;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i);
            }
            int i2 = this.f776d;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, i2);
            }
            return !this.e.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(5, this.e) : iComputeSerializedSize;
        }

        public static f a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (f) MessageNano.mergeFrom(new f(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f773a = codedInputByteBufferNano.readString();
                } else if (tag == 18) {
                    this.f774b = codedInputByteBufferNano.readString();
                } else if (tag == 24) {
                    this.f775c = codedInputByteBufferNano.readInt32();
                } else if (tag == 32) {
                    this.f776d = codedInputByteBufferNano.readInt32();
                } else if (tag != 42) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.e = codedInputByteBufferNano.readString();
                }
            }
        }
    }

    public static final class h extends MessageNano {
        private static volatile h[] g;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f781a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f782b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f783c = "";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f784d = "";
        public String e = "";
        public e[] f = e.a();

        public static h[] a() {
            if (g == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (g == null) {
                        g = new h[0];
                    }
                }
            }
            return g;
        }

        public h() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f781a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f781a);
            }
            int i = this.f782b;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(2, i);
            }
            if (!this.f783c.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.f783c);
            }
            if (!this.f784d.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.f784d);
            }
            if (!this.e.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.e);
            }
            e[] eVarArr = this.f;
            if (eVarArr != null && eVarArr.length > 0) {
                int i2 = 0;
                while (true) {
                    e[] eVarArr2 = this.f;
                    if (i2 >= eVarArr2.length) {
                        break;
                    }
                    e eVar = eVarArr2[i2];
                    if (eVar != null) {
                        codedOutputByteBufferNano.writeMessage(6, eVar);
                    }
                    i2++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f781a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f781a);
            }
            int i = this.f782b;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i);
            }
            if (!this.f783c.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f783c);
            }
            if (!this.f784d.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f784d);
            }
            if (!this.e.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.e);
            }
            e[] eVarArr = this.f;
            if (eVarArr != null && eVarArr.length > 0) {
                int i2 = 0;
                while (true) {
                    e[] eVarArr2 = this.f;
                    if (i2 >= eVarArr2.length) {
                        break;
                    }
                    e eVar = eVarArr2[i2];
                    if (eVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(6, eVar);
                    }
                    i2++;
                }
            }
            return iComputeSerializedSize;
        }

        public static h a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (h) MessageNano.mergeFrom(new h(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f781a = codedInputByteBufferNano.readString();
                } else if (tag == 16) {
                    this.f782b = codedInputByteBufferNano.readInt32();
                } else if (tag == 26) {
                    this.f783c = codedInputByteBufferNano.readString();
                } else if (tag == 34) {
                    this.f784d = codedInputByteBufferNano.readString();
                } else if (tag == 42) {
                    this.e = codedInputByteBufferNano.readString();
                } else if (tag != 50) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 50);
                    e[] eVarArr = this.f;
                    int length = eVarArr == null ? 0 : eVarArr.length;
                    int i = repeatedFieldArrayLength + length;
                    e[] eVarArr2 = new e[i];
                    if (length != 0) {
                        System.arraycopy(eVarArr, 0, eVarArr2, 0, length);
                    }
                    while (length < i - 1) {
                        e eVar = new e();
                        eVarArr2[length] = eVar;
                        codedInputByteBufferNano.readMessage(eVar);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    e eVar2 = new e();
                    eVarArr2[length] = eVar2;
                    codedInputByteBufferNano.readMessage(eVar2);
                    this.f = eVarArr2;
                }
            }
        }
    }

    public static final class j extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f789a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f790b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f791c = "";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f792d = 0;
        public e e = null;

        public j() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f789a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f789a);
            }
            if (!this.f790b.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f790b);
            }
            if (!this.f791c.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.f791c);
            }
            long j = this.f792d;
            if (j != 0) {
                codedOutputByteBufferNano.writeFixed64(4, j);
            }
            e eVar = this.e;
            if (eVar != null) {
                codedOutputByteBufferNano.writeMessage(5, eVar);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f789a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f789a);
            }
            if (!this.f790b.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f790b);
            }
            if (!this.f791c.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f791c);
            }
            long j = this.f792d;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(4, j);
            }
            e eVar = this.e;
            return eVar != null ? iComputeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(5, eVar) : iComputeSerializedSize;
        }

        public static j a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (j) MessageNano.mergeFrom(new j(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f789a = codedInputByteBufferNano.readString();
                } else if (tag == 18) {
                    this.f790b = codedInputByteBufferNano.readString();
                } else if (tag == 26) {
                    this.f791c = codedInputByteBufferNano.readString();
                } else if (tag == 33) {
                    this.f792d = codedInputByteBufferNano.readFixed64();
                } else if (tag != 42) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    if (this.e == null) {
                        this.e = new e();
                    }
                    codedInputByteBufferNano.readMessage(this.e);
                }
            }
        }
    }

    public static final class k extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f793a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f794b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f795c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f796d = "";
        public String e = "";
        public int f = 0;
        public String g = "";

        public k() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f793a;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            int i2 = this.f794b;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(2, i2);
            }
            int i3 = this.f795c;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i3);
            }
            if (!this.f796d.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.f796d);
            }
            if (!this.e.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.e);
            }
            int i4 = this.f;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(6, i4);
            }
            if (!this.g.equals("")) {
                codedOutputByteBufferNano.writeString(7, this.g);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f793a;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int i2 = this.f794b;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i2);
            }
            int i3 = this.f795c;
            if (i3 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i3);
            }
            if (!this.f796d.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f796d);
            }
            if (!this.e.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.e);
            }
            int i4 = this.f;
            if (i4 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(6, i4);
            }
            return !this.g.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(7, this.g) : iComputeSerializedSize;
        }

        public static k a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (k) MessageNano.mergeFrom(new k(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.f793a = codedInputByteBufferNano.readInt32();
                } else if (tag == 16) {
                    this.f794b = codedInputByteBufferNano.readInt32();
                } else if (tag == 24) {
                    this.f795c = codedInputByteBufferNano.readInt32();
                } else if (tag == 34) {
                    this.f796d = codedInputByteBufferNano.readString();
                } else if (tag == 42) {
                    this.e = codedInputByteBufferNano.readString();
                } else if (tag == 48) {
                    this.f = codedInputByteBufferNano.readInt32();
                } else if (tag != 58) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.g = codedInputByteBufferNano.readString();
                }
            }
        }
    }

    public static final class g extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static volatile g[] f777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f778b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f779c = "";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f780d = false;

        public static g[] a() {
            if (f777a == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f777a == null) {
                        f777a = new g[0];
                    }
                }
            }
            return f777a;
        }

        public g() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f778b;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            if (!this.f779c.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f779c);
            }
            boolean z = this.f780d;
            if (z) {
                codedOutputByteBufferNano.writeBool(3, z);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f778b;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            if (!this.f779c.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f779c);
            }
            boolean z = this.f780d;
            return z ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBoolSize(3, z) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.f778b = codedInputByteBufferNano.readInt32();
                } else if (tag == 18) {
                    this.f779c = codedInputByteBufferNano.readString();
                } else if (tag != 24) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.f780d = codedInputByteBufferNano.readBool();
                }
            }
        }
    }

    public static final class c extends MessageNano {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static volatile c[] f762c;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f763a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f764b = "";

        public static c[] a() {
            if (f762c == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f762c == null) {
                        f762c = new c[0];
                    }
                }
            }
            return f762c;
        }

        public c() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f763a;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            if (!this.f764b.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f764b);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f763a;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            return !this.f764b.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(2, this.f764b) : iComputeSerializedSize;
        }

        public static c a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (c) MessageNano.mergeFrom(new c(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.f763a = codedInputByteBufferNano.readInt32();
                } else if (tag != 18) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.f764b = codedInputByteBufferNano.readString();
                }
            }
        }
    }

    public static final class a extends MessageNano {
        private static volatile a[] h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f754a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f755b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f756c = "";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int[] f757d = WireFormatNano.EMPTY_INT_ARRAY;
        public int e = 0;
        public boolean f = false;
        public byte[] g = WireFormatNano.EMPTY_BYTES;

        public static a[] a() {
            if (h == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (h == null) {
                        h = new a[0];
                    }
                }
            }
            return h;
        }

        public a() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f754a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f754a);
            }
            if (!this.f755b.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f755b);
            }
            if (!this.f756c.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.f756c);
            }
            int[] iArr = this.f757d;
            if (iArr != null && iArr.length > 0) {
                int i = 0;
                while (true) {
                    int[] iArr2 = this.f757d;
                    if (i >= iArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(4, iArr2[i]);
                    i++;
                }
            }
            int i2 = this.e;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(5, i2);
            }
            boolean z = this.f;
            if (z) {
                codedOutputByteBufferNano.writeBool(6, z);
            }
            if (!Arrays.equals(this.g, WireFormatNano.EMPTY_BYTES)) {
                codedOutputByteBufferNano.writeBytes(7, this.g);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int[] iArr;
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f754a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f754a);
            }
            if (!this.f755b.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f755b);
            }
            if (!this.f756c.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f756c);
            }
            int[] iArr2 = this.f757d;
            if (iArr2 != null && iArr2.length > 0) {
                int i = 0;
                int iComputeInt32SizeNoTag = 0;
                while (true) {
                    iArr = this.f757d;
                    if (i >= iArr.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i]);
                    i++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag + (iArr.length * 1);
            }
            int i2 = this.e;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(5, i2);
            }
            boolean z = this.f;
            if (z) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(6, z);
            }
            return !Arrays.equals(this.g, WireFormatNano.EMPTY_BYTES) ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBytesSize(7, this.g) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f754a = codedInputByteBufferNano.readString();
                } else if (tag == 18) {
                    this.f755b = codedInputByteBufferNano.readString();
                } else if (tag == 26) {
                    this.f756c = codedInputByteBufferNano.readString();
                } else if (tag == 32) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 32);
                    int[] iArr = this.f757d;
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
                    this.f757d = iArr2;
                } else if (tag == 34) {
                    int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    int i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        codedInputByteBufferNano.readInt32();
                        i2++;
                    }
                    codedInputByteBufferNano.rewindToPosition(position);
                    int[] iArr3 = this.f757d;
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
                    this.f757d = iArr4;
                    codedInputByteBufferNano.popLimit(iPushLimit);
                } else if (tag == 40) {
                    this.e = codedInputByteBufferNano.readInt32();
                } else if (tag == 48) {
                    this.f = codedInputByteBufferNano.readBool();
                } else if (tag != 58) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.g = codedInputByteBufferNano.readBytes();
                }
            }
        }
    }

    public static final class d extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f765a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f766b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f767c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f768d = "";

        public d() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f765a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f765a);
            }
            int i = this.f766b;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(2, i);
            }
            int i2 = this.f767c;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i2);
            }
            if (!this.f768d.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.f768d);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f765a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f765a);
            }
            int i = this.f766b;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i);
            }
            int i2 = this.f767c;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i2);
            }
            return !this.f768d.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(4, this.f768d) : iComputeSerializedSize;
        }

        public static d a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (d) MessageNano.mergeFrom(new d(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f765a = codedInputByteBufferNano.readString();
                } else if (tag == 16) {
                    this.f766b = codedInputByteBufferNano.readInt32();
                } else if (tag == 24) {
                    this.f767c = codedInputByteBufferNano.readInt32();
                } else if (tag != 34) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.f768d = codedInputByteBufferNano.readString();
                }
            }
        }
    }

    public static final class i extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f785a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f786b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f787c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f788d = 0;

        public i() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f785a;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            int i2 = this.f786b;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(2, i2);
            }
            h hVar = this.f787c;
            if (hVar != null) {
                codedOutputByteBufferNano.writeMessage(3, hVar);
            }
            int i3 = this.f788d;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i3);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f785a;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int i2 = this.f786b;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i2);
            }
            h hVar = this.f787c;
            if (hVar != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, hVar);
            }
            int i3 = this.f788d;
            return i3 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(4, i3) : iComputeSerializedSize;
        }

        public static i a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (i) MessageNano.mergeFrom(new i(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    this.f785a = codedInputByteBufferNano.readInt32();
                } else if (tag == 16) {
                    this.f786b = codedInputByteBufferNano.readInt32();
                } else if (tag == 26) {
                    if (this.f787c == null) {
                        this.f787c = new h();
                    }
                    codedInputByteBufferNano.readMessage(this.f787c);
                } else if (tag != 32) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.f788d = codedInputByteBufferNano.readInt32();
                }
            }
        }
    }

    public static final class b extends MessageNano {
        private static volatile b[] f;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f758a = "";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f759b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f760c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f761d = 0;
        public int e = 0;

        public static b[] a() {
            if (f == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f == null) {
                        f = new b[0];
                    }
                }
            }
            return f;
        }

        public b() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f758a.equals("")) {
                codedOutputByteBufferNano.writeString(1, this.f758a);
            }
            int i = this.f759b;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(2, i);
            }
            int i2 = this.f760c;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i2);
            }
            int i3 = this.f761d;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i3);
            }
            int i4 = this.e;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(5, i4);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.f758a.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f758a);
            }
            int i = this.f759b;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i);
            }
            int i2 = this.f760c;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i2);
            }
            int i3 = this.f761d;
            if (i3 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, i3);
            }
            int i4 = this.e;
            return i4 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(5, i4) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.f758a = codedInputByteBufferNano.readString();
                } else if (tag == 16) {
                    this.f759b = codedInputByteBufferNano.readInt32();
                } else if (tag == 24) {
                    this.f760c = codedInputByteBufferNano.readInt32();
                } else if (tag == 32) {
                    this.f761d = codedInputByteBufferNano.readInt32();
                } else if (tag != 40) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.e = codedInputByteBufferNano.readInt32();
                }
            }
        }
    }

    public static final class l extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f797a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f798b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f799c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f800d = 0;
        private int e = 0;
        private String f = "";
        private int g = 0;
        private String h = "";

        public l() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            long j = this.f797a;
            if (j != 0) {
                codedOutputByteBufferNano.writeFixed64(1, j);
            }
            long j2 = this.f798b;
            if (j2 != 0) {
                codedOutputByteBufferNano.writeFixed64(2, j2);
            }
            int i = this.f799c;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(3, i);
            }
            int i2 = this.f800d;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i2);
            }
            int i3 = this.e;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(5, i3);
            }
            if (!this.f.equals("")) {
                codedOutputByteBufferNano.writeString(6, this.f);
            }
            int i4 = this.g;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(7, i4);
            }
            if (!this.h.equals("")) {
                codedOutputByteBufferNano.writeString(8, this.h);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            long j = this.f797a;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(1, j);
            }
            long j2 = this.f798b;
            if (j2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(2, j2);
            }
            int i = this.f799c;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i);
            }
            int i2 = this.f800d;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, i2);
            }
            int i3 = this.e;
            if (i3 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(5, i3);
            }
            if (!this.f.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(6, this.f);
            }
            int i4 = this.g;
            if (i4 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(7, i4);
            }
            return !this.h.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(8, this.h) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 9) {
                    this.f797a = codedInputByteBufferNano.readFixed64();
                } else if (tag == 17) {
                    this.f798b = codedInputByteBufferNano.readFixed64();
                } else if (tag == 24) {
                    this.f799c = codedInputByteBufferNano.readInt32();
                } else if (tag == 32) {
                    this.f800d = codedInputByteBufferNano.readInt32();
                } else if (tag == 40) {
                    this.e = codedInputByteBufferNano.readInt32();
                } else if (tag == 50) {
                    this.f = codedInputByteBufferNano.readString();
                } else if (tag == 56) {
                    this.g = codedInputByteBufferNano.readInt32();
                } else if (tag != 66) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.h = codedInputByteBufferNano.readString();
                }
            }
        }
    }

    public static final class m extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f801a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f802b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f803c = 0;

        public m() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            long j = this.f801a;
            if (j != 0) {
                codedOutputByteBufferNano.writeFixed64(1, j);
            }
            if (!this.f802b.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.f802b);
            }
            long j2 = this.f803c;
            if (j2 != 0) {
                codedOutputByteBufferNano.writeFixed64(3, j2);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            long j = this.f801a;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(1, j);
            }
            if (!this.f802b.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f802b);
            }
            long j2 = this.f803c;
            return j2 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeFixed64Size(3, j2) : iComputeSerializedSize;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 9) {
                    this.f801a = codedInputByteBufferNano.readFixed64();
                } else if (tag == 18) {
                    this.f802b = codedInputByteBufferNano.readString();
                } else if (tag != 25) {
                    if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                        return this;
                    }
                } else {
                    this.f803c = codedInputByteBufferNano.readFixed64();
                }
            }
        }
    }
}
