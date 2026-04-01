package ecarx.xsf.mediacenter.session.proto.nano;

import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.ecarx.eas.framework.sdk.proto.CodedInputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.CodedOutputByteBufferNano;
import com.ecarx.eas.framework.sdk.proto.InternalNano;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MapFactories;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.framework.sdk.proto.WireFormatNano;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.IOException;
import java.util.Map;
import okhttp3.internal.http.HttpStatusCodesKt;

/* JADX INFO: loaded from: classes3.dex */
public interface Controller {

    public static final class a extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f746a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f747b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f748c = 0;
        private int w = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f749d = "";
        public String e = "";
        public String f = "";
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public long j = 0;
        public int[] k = WireFormatNano.EMPTY_INT_ARRAY;
        private int x = 0;
        private Mediabean.c[] y = Mediabean.c.a();
        public int l = 0;
        private Mediabean.g[] z = Mediabean.g.a();
        public String m = "";
        public Mediabean.h[] n = Mediabean.h.a();
        public Map<String, String> o = null;
        public Mediabean.a[] p = Mediabean.a.a();
        public int q = 0;
        public String r = "";
        public String s = "";
        public int t = 0;
        public int[] u = WireFormatNano.EMPTY_INT_ARRAY;
        public int v = 0;
        private int[] A = WireFormatNano.EMPTY_INT_ARRAY;
        private Mediabean.b[] B = Mediabean.b.a();
        private Mediabean.l C = null;
        private Mediabean.m D = null;

        public a() {
            this.cachedSize = -1;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f746a;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            int i2 = this.f747b;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(2, i2);
            }
            int i3 = this.f748c;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i3);
            }
            int i4 = this.w;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i4);
            }
            if (!this.f749d.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.f749d);
            }
            if (!this.e.equals("")) {
                codedOutputByteBufferNano.writeString(6, this.e);
            }
            if (!this.f.equals("")) {
                codedOutputByteBufferNano.writeString(7, this.f);
            }
            boolean z = this.g;
            if (z) {
                codedOutputByteBufferNano.writeBool(8, z);
            }
            boolean z2 = this.h;
            if (z2) {
                codedOutputByteBufferNano.writeBool(9, z2);
            }
            boolean z3 = this.i;
            if (z3) {
                codedOutputByteBufferNano.writeBool(10, z3);
            }
            long j = this.j;
            if (j != 0) {
                codedOutputByteBufferNano.writeFixed64(11, j);
            }
            int[] iArr = this.k;
            int i5 = 0;
            if (iArr != null && iArr.length > 0) {
                int i6 = 0;
                while (true) {
                    int[] iArr2 = this.k;
                    if (i6 >= iArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(12, iArr2[i6]);
                    i6++;
                }
            }
            int i7 = this.x;
            if (i7 != 0) {
                codedOutputByteBufferNano.writeInt32(13, i7);
            }
            Mediabean.c[] cVarArr = this.y;
            if (cVarArr != null && cVarArr.length > 0) {
                int i8 = 0;
                while (true) {
                    Mediabean.c[] cVarArr2 = this.y;
                    if (i8 >= cVarArr2.length) {
                        break;
                    }
                    Mediabean.c cVar = cVarArr2[i8];
                    if (cVar != null) {
                        codedOutputByteBufferNano.writeMessage(14, cVar);
                    }
                    i8++;
                }
            }
            int i9 = this.l;
            if (i9 != 0) {
                codedOutputByteBufferNano.writeInt32(15, i9);
            }
            Mediabean.g[] gVarArr = this.z;
            if (gVarArr != null && gVarArr.length > 0) {
                int i10 = 0;
                while (true) {
                    Mediabean.g[] gVarArr2 = this.z;
                    if (i10 >= gVarArr2.length) {
                        break;
                    }
                    Mediabean.g gVar = gVarArr2[i10];
                    if (gVar != null) {
                        codedOutputByteBufferNano.writeMessage(16, gVar);
                    }
                    i10++;
                }
            }
            if (!this.m.equals("")) {
                codedOutputByteBufferNano.writeString(17, this.m);
            }
            Mediabean.h[] hVarArr = this.n;
            if (hVarArr != null && hVarArr.length > 0) {
                int i11 = 0;
                while (true) {
                    Mediabean.h[] hVarArr2 = this.n;
                    if (i11 >= hVarArr2.length) {
                        break;
                    }
                    Mediabean.h hVar = hVarArr2[i11];
                    if (hVar != null) {
                        codedOutputByteBufferNano.writeMessage(18, hVar);
                    }
                    i11++;
                }
            }
            Map<String, String> map = this.o;
            if (map != null) {
                InternalNano.serializeMapField(codedOutputByteBufferNano, map, 19, 9, 9);
            }
            Mediabean.a[] aVarArr = this.p;
            if (aVarArr != null && aVarArr.length > 0) {
                int i12 = 0;
                while (true) {
                    Mediabean.a[] aVarArr2 = this.p;
                    if (i12 >= aVarArr2.length) {
                        break;
                    }
                    Mediabean.a aVar = aVarArr2[i12];
                    if (aVar != null) {
                        codedOutputByteBufferNano.writeMessage(20, aVar);
                    }
                    i12++;
                }
            }
            int i13 = this.q;
            if (i13 != 0) {
                codedOutputByteBufferNano.writeInt32(21, i13);
            }
            if (!this.r.equals("")) {
                codedOutputByteBufferNano.writeString(22, this.r);
            }
            if (!this.s.equals("")) {
                codedOutputByteBufferNano.writeString(23, this.s);
            }
            int i14 = this.t;
            if (i14 != 0) {
                codedOutputByteBufferNano.writeInt32(24, i14);
            }
            int[] iArr3 = this.u;
            if (iArr3 != null && iArr3.length > 0) {
                int i15 = 0;
                while (true) {
                    int[] iArr4 = this.u;
                    if (i15 >= iArr4.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(25, iArr4[i15]);
                    i15++;
                }
            }
            int i16 = this.v;
            if (i16 != 0) {
                codedOutputByteBufferNano.writeInt32(26, i16);
            }
            int[] iArr5 = this.A;
            if (iArr5 != null && iArr5.length > 0) {
                int i17 = 0;
                while (true) {
                    int[] iArr6 = this.A;
                    if (i17 >= iArr6.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(27, iArr6[i17]);
                    i17++;
                }
            }
            Mediabean.b[] bVarArr = this.B;
            if (bVarArr != null && bVarArr.length > 0) {
                while (true) {
                    Mediabean.b[] bVarArr2 = this.B;
                    if (i5 >= bVarArr2.length) {
                        break;
                    }
                    Mediabean.b bVar = bVarArr2[i5];
                    if (bVar != null) {
                        codedOutputByteBufferNano.writeMessage(28, bVar);
                    }
                    i5++;
                }
            }
            Mediabean.l lVar = this.C;
            if (lVar != null) {
                codedOutputByteBufferNano.writeMessage(29, lVar);
            }
            Mediabean.m mVar = this.D;
            if (mVar != null) {
                codedOutputByteBufferNano.writeMessage(30, mVar);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int[] iArr;
            int[] iArr2;
            int[] iArr3;
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f746a;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int i2 = this.f747b;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i2);
            }
            int i3 = this.f748c;
            if (i3 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i3);
            }
            int i4 = this.w;
            if (i4 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, i4);
            }
            if (!this.f749d.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.f749d);
            }
            if (!this.e.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(6, this.e);
            }
            if (!this.f.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(7, this.f);
            }
            boolean z = this.g;
            if (z) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(8, z);
            }
            boolean z2 = this.h;
            if (z2) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(9, z2);
            }
            boolean z3 = this.i;
            if (z3) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(10, z3);
            }
            long j = this.j;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFixed64Size(11, j);
            }
            int[] iArr4 = this.k;
            int i5 = 0;
            if (iArr4 != null && iArr4.length > 0) {
                int i6 = 0;
                int iComputeInt32SizeNoTag = 0;
                while (true) {
                    iArr3 = this.k;
                    if (i6 >= iArr3.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr3[i6]);
                    i6++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag + (iArr3.length * 1);
            }
            int i7 = this.x;
            if (i7 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(13, i7);
            }
            Mediabean.c[] cVarArr = this.y;
            if (cVarArr != null && cVarArr.length > 0) {
                int i8 = 0;
                while (true) {
                    Mediabean.c[] cVarArr2 = this.y;
                    if (i8 >= cVarArr2.length) {
                        break;
                    }
                    Mediabean.c cVar = cVarArr2[i8];
                    if (cVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(14, cVar);
                    }
                    i8++;
                }
            }
            int i9 = this.l;
            if (i9 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(15, i9);
            }
            Mediabean.g[] gVarArr = this.z;
            if (gVarArr != null && gVarArr.length > 0) {
                int i10 = 0;
                while (true) {
                    Mediabean.g[] gVarArr2 = this.z;
                    if (i10 >= gVarArr2.length) {
                        break;
                    }
                    Mediabean.g gVar = gVarArr2[i10];
                    if (gVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(16, gVar);
                    }
                    i10++;
                }
            }
            if (!this.m.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(17, this.m);
            }
            Mediabean.h[] hVarArr = this.n;
            if (hVarArr != null && hVarArr.length > 0) {
                int i11 = 0;
                while (true) {
                    Mediabean.h[] hVarArr2 = this.n;
                    if (i11 >= hVarArr2.length) {
                        break;
                    }
                    Mediabean.h hVar = hVarArr2[i11];
                    if (hVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(18, hVar);
                    }
                    i11++;
                }
            }
            Map<String, String> map = this.o;
            if (map != null) {
                iComputeSerializedSize += InternalNano.computeMapFieldSize(map, 19, 9, 9);
            }
            Mediabean.a[] aVarArr = this.p;
            if (aVarArr != null && aVarArr.length > 0) {
                int i12 = 0;
                while (true) {
                    Mediabean.a[] aVarArr2 = this.p;
                    if (i12 >= aVarArr2.length) {
                        break;
                    }
                    Mediabean.a aVar = aVarArr2[i12];
                    if (aVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(20, aVar);
                    }
                    i12++;
                }
            }
            int i13 = this.q;
            if (i13 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(21, i13);
            }
            if (!this.r.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(22, this.r);
            }
            if (!this.s.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(23, this.s);
            }
            int i14 = this.t;
            if (i14 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(24, i14);
            }
            int[] iArr5 = this.u;
            if (iArr5 != null && iArr5.length > 0) {
                int i15 = 0;
                int iComputeInt32SizeNoTag2 = 0;
                while (true) {
                    iArr2 = this.u;
                    if (i15 >= iArr2.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr2[i15]);
                    i15++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag2 + (iArr2.length * 2);
            }
            int i16 = this.v;
            if (i16 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(26, i16);
            }
            int[] iArr6 = this.A;
            if (iArr6 != null && iArr6.length > 0) {
                int i17 = 0;
                int iComputeInt32SizeNoTag3 = 0;
                while (true) {
                    iArr = this.A;
                    if (i17 >= iArr.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i17]);
                    i17++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag3 + (iArr.length * 2);
            }
            Mediabean.b[] bVarArr = this.B;
            if (bVarArr != null && bVarArr.length > 0) {
                while (true) {
                    Mediabean.b[] bVarArr2 = this.B;
                    if (i5 >= bVarArr2.length) {
                        break;
                    }
                    Mediabean.b bVar = bVarArr2[i5];
                    if (bVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(28, bVar);
                    }
                    i5++;
                }
            }
            Mediabean.l lVar = this.C;
            if (lVar != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(29, lVar);
            }
            Mediabean.m mVar = this.D;
            return mVar != null ? iComputeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(30, mVar) : iComputeSerializedSize;
        }

        public static a a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (a) MessageNano.mergeFrom(new a(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            MapFactories.MapFactory mapFactory = MapFactories.getMapFactory();
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        this.f746a = codedInputByteBufferNano.readInt32();
                        break;
                    case 16:
                        this.f747b = codedInputByteBufferNano.readInt32();
                        break;
                    case 24:
                        this.f748c = codedInputByteBufferNano.readInt32();
                        break;
                    case 32:
                        this.w = codedInputByteBufferNano.readInt32();
                        break;
                    case 42:
                        this.f749d = codedInputByteBufferNano.readString();
                        break;
                    case 50:
                        this.e = codedInputByteBufferNano.readString();
                        break;
                    case 58:
                        this.f = codedInputByteBufferNano.readString();
                        break;
                    case 64:
                        this.g = codedInputByteBufferNano.readBool();
                        break;
                    case 72:
                        this.h = codedInputByteBufferNano.readBool();
                        break;
                    case 80:
                        this.i = codedInputByteBufferNano.readBool();
                        break;
                    case 89:
                        this.j = codedInputByteBufferNano.readFixed64();
                        break;
                    case 96:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 96);
                        int[] iArr = this.k;
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
                        this.k = iArr2;
                        break;
                    case 98:
                        int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position = codedInputByteBufferNano.getPosition();
                        int i2 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i2++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position);
                        int[] iArr3 = this.k;
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
                        this.k = iArr4;
                        codedInputByteBufferNano.popLimit(iPushLimit);
                        break;
                    case 104:
                        this.x = codedInputByteBufferNano.readInt32();
                        break;
                    case 114:
                        int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 114);
                        Mediabean.c[] cVarArr = this.y;
                        int length3 = cVarArr == null ? 0 : cVarArr.length;
                        int i4 = repeatedFieldArrayLength2 + length3;
                        Mediabean.c[] cVarArr2 = new Mediabean.c[i4];
                        if (length3 != 0) {
                            System.arraycopy(cVarArr, 0, cVarArr2, 0, length3);
                        }
                        while (length3 < i4 - 1) {
                            Mediabean.c cVar = new Mediabean.c();
                            cVarArr2[length3] = cVar;
                            codedInputByteBufferNano.readMessage(cVar);
                            codedInputByteBufferNano.readTag();
                            length3++;
                        }
                        Mediabean.c cVar2 = new Mediabean.c();
                        cVarArr2[length3] = cVar2;
                        codedInputByteBufferNano.readMessage(cVar2);
                        this.y = cVarArr2;
                        break;
                    case 120:
                        this.l = codedInputByteBufferNano.readInt32();
                        break;
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                        int repeatedFieldArrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                        Mediabean.g[] gVarArr = this.z;
                        int length4 = gVarArr == null ? 0 : gVarArr.length;
                        int i5 = repeatedFieldArrayLength3 + length4;
                        Mediabean.g[] gVarArr2 = new Mediabean.g[i5];
                        if (length4 != 0) {
                            System.arraycopy(gVarArr, 0, gVarArr2, 0, length4);
                        }
                        while (length4 < i5 - 1) {
                            Mediabean.g gVar = new Mediabean.g();
                            gVarArr2[length4] = gVar;
                            codedInputByteBufferNano.readMessage(gVar);
                            codedInputByteBufferNano.readTag();
                            length4++;
                        }
                        Mediabean.g gVar2 = new Mediabean.g();
                        gVarArr2[length4] = gVar2;
                        codedInputByteBufferNano.readMessage(gVar2);
                        this.z = gVarArr2;
                        break;
                    case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                        this.m = codedInputByteBufferNano.readString();
                        break;
                    case 146:
                        int repeatedFieldArrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 146);
                        Mediabean.h[] hVarArr = this.n;
                        int length5 = hVarArr == null ? 0 : hVarArr.length;
                        int i6 = repeatedFieldArrayLength4 + length5;
                        Mediabean.h[] hVarArr2 = new Mediabean.h[i6];
                        if (length5 != 0) {
                            System.arraycopy(hVarArr, 0, hVarArr2, 0, length5);
                        }
                        while (length5 < i6 - 1) {
                            Mediabean.h hVar = new Mediabean.h();
                            hVarArr2[length5] = hVar;
                            codedInputByteBufferNano.readMessage(hVar);
                            codedInputByteBufferNano.readTag();
                            length5++;
                        }
                        Mediabean.h hVar2 = new Mediabean.h();
                        hVarArr2[length5] = hVar2;
                        codedInputByteBufferNano.readMessage(hVar2);
                        this.n = hVarArr2;
                        break;
                    case 154:
                        this.o = InternalNano.mergeMapEntry(codedInputByteBufferNano, this.o, mapFactory, 9, 9, null, 10, 18);
                        break;
                    case 162:
                        int repeatedFieldArrayLength5 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 162);
                        Mediabean.a[] aVarArr = this.p;
                        int length6 = aVarArr == null ? 0 : aVarArr.length;
                        int i7 = repeatedFieldArrayLength5 + length6;
                        Mediabean.a[] aVarArr2 = new Mediabean.a[i7];
                        if (length6 != 0) {
                            System.arraycopy(aVarArr, 0, aVarArr2, 0, length6);
                        }
                        while (length6 < i7 - 1) {
                            Mediabean.a aVar = new Mediabean.a();
                            aVarArr2[length6] = aVar;
                            codedInputByteBufferNano.readMessage(aVar);
                            codedInputByteBufferNano.readTag();
                            length6++;
                        }
                        Mediabean.a aVar2 = new Mediabean.a();
                        aVarArr2[length6] = aVar2;
                        codedInputByteBufferNano.readMessage(aVar2);
                        this.p = aVarArr2;
                        break;
                    case 168:
                        this.q = codedInputByteBufferNano.readInt32();
                        break;
                    case 178:
                        this.r = codedInputByteBufferNano.readString();
                        break;
                    case 186:
                        this.s = codedInputByteBufferNano.readString();
                        break;
                    case PsExtractor.AUDIO_STREAM /* 192 */:
                        this.t = codedInputByteBufferNano.readInt32();
                        break;
                    case 200:
                        int repeatedFieldArrayLength6 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 200);
                        int[] iArr5 = this.u;
                        int length7 = iArr5 == null ? 0 : iArr5.length;
                        int i8 = repeatedFieldArrayLength6 + length7;
                        int[] iArr6 = new int[i8];
                        if (length7 != 0) {
                            System.arraycopy(iArr5, 0, iArr6, 0, length7);
                        }
                        while (length7 < i8 - 1) {
                            iArr6[length7] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            length7++;
                        }
                        iArr6[length7] = codedInputByteBufferNano.readInt32();
                        this.u = iArr6;
                        break;
                    case 202:
                        int iPushLimit2 = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position2 = codedInputByteBufferNano.getPosition();
                        int i9 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i9++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position2);
                        int[] iArr7 = this.u;
                        int length8 = iArr7 == null ? 0 : iArr7.length;
                        int i10 = i9 + length8;
                        int[] iArr8 = new int[i10];
                        if (length8 != 0) {
                            System.arraycopy(iArr7, 0, iArr8, 0, length8);
                        }
                        while (length8 < i10) {
                            iArr8[length8] = codedInputByteBufferNano.readInt32();
                            length8++;
                        }
                        this.u = iArr8;
                        codedInputByteBufferNano.popLimit(iPushLimit2);
                        break;
                    case HttpStatusCodesKt.HTTP_ALREADY_REPORTED /* 208 */:
                        this.v = codedInputByteBufferNano.readInt32();
                        break;
                    case 216:
                        int repeatedFieldArrayLength7 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 216);
                        int[] iArr9 = this.A;
                        int length9 = iArr9 == null ? 0 : iArr9.length;
                        int i11 = repeatedFieldArrayLength7 + length9;
                        int[] iArr10 = new int[i11];
                        if (length9 != 0) {
                            System.arraycopy(iArr9, 0, iArr10, 0, length9);
                        }
                        while (length9 < i11 - 1) {
                            iArr10[length9] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            length9++;
                        }
                        iArr10[length9] = codedInputByteBufferNano.readInt32();
                        this.A = iArr10;
                        break;
                    case 218:
                        int iPushLimit3 = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position3 = codedInputByteBufferNano.getPosition();
                        int i12 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i12++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position3);
                        int[] iArr11 = this.A;
                        int length10 = iArr11 == null ? 0 : iArr11.length;
                        int i13 = i12 + length10;
                        int[] iArr12 = new int[i13];
                        if (length10 != 0) {
                            System.arraycopy(iArr11, 0, iArr12, 0, length10);
                        }
                        while (length10 < i13) {
                            iArr12[length10] = codedInputByteBufferNano.readInt32();
                            length10++;
                        }
                        this.A = iArr12;
                        codedInputByteBufferNano.popLimit(iPushLimit3);
                        break;
                    case HttpStatusCodesKt.HTTP_IM_USED /* 226 */:
                        int repeatedFieldArrayLength8 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, HttpStatusCodesKt.HTTP_IM_USED);
                        Mediabean.b[] bVarArr = this.B;
                        int length11 = bVarArr == null ? 0 : bVarArr.length;
                        int i14 = repeatedFieldArrayLength8 + length11;
                        Mediabean.b[] bVarArr2 = new Mediabean.b[i14];
                        if (length11 != 0) {
                            System.arraycopy(bVarArr, 0, bVarArr2, 0, length11);
                        }
                        while (length11 < i14 - 1) {
                            Mediabean.b bVar = new Mediabean.b();
                            bVarArr2[length11] = bVar;
                            codedInputByteBufferNano.readMessage(bVar);
                            codedInputByteBufferNano.readTag();
                            length11++;
                        }
                        Mediabean.b bVar2 = new Mediabean.b();
                        bVarArr2[length11] = bVar2;
                        codedInputByteBufferNano.readMessage(bVar2);
                        this.B = bVarArr2;
                        break;
                    case 234:
                        if (this.C == null) {
                            this.C = new Mediabean.l();
                        }
                        codedInputByteBufferNano.readMessage(this.C);
                        break;
                    case 242:
                        if (this.D == null) {
                            this.D = new Mediabean.m();
                        }
                        codedInputByteBufferNano.readMessage(this.D);
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

    public static final class b extends MessageNano {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f750a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int[] f751b = WireFormatNano.EMPTY_INT_ARRAY;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f752c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Mediabean.b[] f753d;
        public Mediabean.m e;
        private int f;
        private String g;
        private boolean h;
        private int i;
        private int[] j;
        private Mediabean.l k;

        public b() {
            this.cachedSize = -1;
            this.f = 0;
            this.f752c = WireFormatNano.EMPTY_INT_ARRAY;
            this.g = "";
            this.h = false;
            this.i = 0;
            this.j = WireFormatNano.EMPTY_INT_ARRAY;
            this.f753d = Mediabean.b.a();
            this.k = null;
            this.e = null;
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.f750a;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            int[] iArr = this.f751b;
            int i2 = 0;
            if (iArr != null && iArr.length > 0) {
                int i3 = 0;
                while (true) {
                    int[] iArr2 = this.f751b;
                    if (i3 >= iArr2.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(2, iArr2[i3]);
                    i3++;
                }
            }
            int i4 = this.f;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i4);
            }
            int[] iArr3 = this.f752c;
            if (iArr3 != null && iArr3.length > 0) {
                int i5 = 0;
                while (true) {
                    int[] iArr4 = this.f752c;
                    if (i5 >= iArr4.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(4, iArr4[i5]);
                    i5++;
                }
            }
            if (!this.g.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.g);
            }
            if (this.h) {
                codedOutputByteBufferNano.writeBool(6, true);
            }
            int i6 = this.i;
            if (i6 != 0) {
                codedOutputByteBufferNano.writeInt32(7, i6);
            }
            int[] iArr5 = this.j;
            if (iArr5 != null && iArr5.length > 0) {
                int i7 = 0;
                while (true) {
                    int[] iArr6 = this.j;
                    if (i7 >= iArr6.length) {
                        break;
                    }
                    codedOutputByteBufferNano.writeInt32(8, iArr6[i7]);
                    i7++;
                }
            }
            Mediabean.b[] bVarArr = this.f753d;
            if (bVarArr != null && bVarArr.length > 0) {
                while (true) {
                    Mediabean.b[] bVarArr2 = this.f753d;
                    if (i2 >= bVarArr2.length) {
                        break;
                    }
                    Mediabean.b bVar = bVarArr2[i2];
                    if (bVar != null) {
                        codedOutputByteBufferNano.writeMessage(9, bVar);
                    }
                    i2++;
                }
            }
            Mediabean.l lVar = this.k;
            if (lVar != null) {
                codedOutputByteBufferNano.writeMessage(10, lVar);
            }
            Mediabean.m mVar = this.e;
            if (mVar != null) {
                codedOutputByteBufferNano.writeMessage(11, mVar);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        protected final int computeSerializedSize() {
            int[] iArr;
            int[] iArr2;
            int[] iArr3;
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.f750a;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int[] iArr4 = this.f751b;
            int i2 = 0;
            if (iArr4 != null && iArr4.length > 0) {
                int i3 = 0;
                int iComputeInt32SizeNoTag = 0;
                while (true) {
                    iArr3 = this.f751b;
                    if (i3 >= iArr3.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr3[i3]);
                    i3++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag + (iArr3.length * 1);
            }
            int i4 = this.f;
            if (i4 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i4);
            }
            int[] iArr5 = this.f752c;
            if (iArr5 != null && iArr5.length > 0) {
                int i5 = 0;
                int iComputeInt32SizeNoTag2 = 0;
                while (true) {
                    iArr2 = this.f752c;
                    if (i5 >= iArr2.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr2[i5]);
                    i5++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag2 + (iArr2.length * 1);
            }
            if (!this.g.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.g);
            }
            if (this.h) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(6, true);
            }
            int i6 = this.i;
            if (i6 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(7, i6);
            }
            int[] iArr6 = this.j;
            if (iArr6 != null && iArr6.length > 0) {
                int i7 = 0;
                int iComputeInt32SizeNoTag3 = 0;
                while (true) {
                    iArr = this.j;
                    if (i7 >= iArr.length) {
                        break;
                    }
                    iComputeInt32SizeNoTag3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i7]);
                    i7++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag3 + (iArr.length * 1);
            }
            Mediabean.b[] bVarArr = this.f753d;
            if (bVarArr != null && bVarArr.length > 0) {
                while (true) {
                    Mediabean.b[] bVarArr2 = this.f753d;
                    if (i2 >= bVarArr2.length) {
                        break;
                    }
                    Mediabean.b bVar = bVarArr2[i2];
                    if (bVar != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(9, bVar);
                    }
                    i2++;
                }
            }
            Mediabean.l lVar = this.k;
            if (lVar != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(10, lVar);
            }
            Mediabean.m mVar = this.e;
            return mVar != null ? iComputeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(11, mVar) : iComputeSerializedSize;
        }

        public static b a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (b) MessageNano.mergeFrom(new b(), bArr);
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        this.f750a = codedInputByteBufferNano.readInt32();
                        break;
                    case 16:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 16);
                        int[] iArr = this.f751b;
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
                        this.f751b = iArr2;
                        break;
                    case 18:
                        int iPushLimit = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position = codedInputByteBufferNano.getPosition();
                        int i2 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i2++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position);
                        int[] iArr3 = this.f751b;
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
                        this.f751b = iArr4;
                        codedInputByteBufferNano.popLimit(iPushLimit);
                        break;
                    case 24:
                        this.f = codedInputByteBufferNano.readInt32();
                        break;
                    case 32:
                        int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 32);
                        int[] iArr5 = this.f752c;
                        int length3 = iArr5 == null ? 0 : iArr5.length;
                        int i4 = repeatedFieldArrayLength2 + length3;
                        int[] iArr6 = new int[i4];
                        if (length3 != 0) {
                            System.arraycopy(iArr5, 0, iArr6, 0, length3);
                        }
                        while (length3 < i4 - 1) {
                            iArr6[length3] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            length3++;
                        }
                        iArr6[length3] = codedInputByteBufferNano.readInt32();
                        this.f752c = iArr6;
                        break;
                    case 34:
                        int iPushLimit2 = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position2 = codedInputByteBufferNano.getPosition();
                        int i5 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i5++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position2);
                        int[] iArr7 = this.f752c;
                        int length4 = iArr7 == null ? 0 : iArr7.length;
                        int i6 = i5 + length4;
                        int[] iArr8 = new int[i6];
                        if (length4 != 0) {
                            System.arraycopy(iArr7, 0, iArr8, 0, length4);
                        }
                        while (length4 < i6) {
                            iArr8[length4] = codedInputByteBufferNano.readInt32();
                            length4++;
                        }
                        this.f752c = iArr8;
                        codedInputByteBufferNano.popLimit(iPushLimit2);
                        break;
                    case 42:
                        this.g = codedInputByteBufferNano.readString();
                        break;
                    case 48:
                        this.h = codedInputByteBufferNano.readBool();
                        break;
                    case 56:
                        this.i = codedInputByteBufferNano.readInt32();
                        break;
                    case 64:
                        int repeatedFieldArrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 64);
                        int[] iArr9 = this.j;
                        int length5 = iArr9 == null ? 0 : iArr9.length;
                        int i7 = repeatedFieldArrayLength3 + length5;
                        int[] iArr10 = new int[i7];
                        if (length5 != 0) {
                            System.arraycopy(iArr9, 0, iArr10, 0, length5);
                        }
                        while (length5 < i7 - 1) {
                            iArr10[length5] = codedInputByteBufferNano.readInt32();
                            codedInputByteBufferNano.readTag();
                            length5++;
                        }
                        iArr10[length5] = codedInputByteBufferNano.readInt32();
                        this.j = iArr10;
                        break;
                    case 66:
                        int iPushLimit3 = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                        int position3 = codedInputByteBufferNano.getPosition();
                        int i8 = 0;
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            codedInputByteBufferNano.readInt32();
                            i8++;
                        }
                        codedInputByteBufferNano.rewindToPosition(position3);
                        int[] iArr11 = this.j;
                        int length6 = iArr11 == null ? 0 : iArr11.length;
                        int i9 = i8 + length6;
                        int[] iArr12 = new int[i9];
                        if (length6 != 0) {
                            System.arraycopy(iArr11, 0, iArr12, 0, length6);
                        }
                        while (length6 < i9) {
                            iArr12[length6] = codedInputByteBufferNano.readInt32();
                            length6++;
                        }
                        this.j = iArr12;
                        codedInputByteBufferNano.popLimit(iPushLimit3);
                        break;
                    case 74:
                        int repeatedFieldArrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 74);
                        Mediabean.b[] bVarArr = this.f753d;
                        int length7 = bVarArr == null ? 0 : bVarArr.length;
                        int i10 = repeatedFieldArrayLength4 + length7;
                        Mediabean.b[] bVarArr2 = new Mediabean.b[i10];
                        if (length7 != 0) {
                            System.arraycopy(bVarArr, 0, bVarArr2, 0, length7);
                        }
                        while (length7 < i10 - 1) {
                            Mediabean.b bVar = new Mediabean.b();
                            bVarArr2[length7] = bVar;
                            codedInputByteBufferNano.readMessage(bVar);
                            codedInputByteBufferNano.readTag();
                            length7++;
                        }
                        Mediabean.b bVar2 = new Mediabean.b();
                        bVarArr2[length7] = bVar2;
                        codedInputByteBufferNano.readMessage(bVar2);
                        this.f753d = bVarArr2;
                        break;
                    case 82:
                        if (this.k == null) {
                            this.k = new Mediabean.l();
                        }
                        codedInputByteBufferNano.readMessage(this.k);
                        break;
                    case 90:
                        if (this.e == null) {
                            this.e = new Mediabean.m();
                        }
                        codedInputByteBufferNano.readMessage(this.e);
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
}
