package com.ecarx.eas.framework.sdk.proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class a implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Extension<?, ?> f50a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Object f51b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<b> f52c;

    /* JADX WARN: Multi-variable type inference failed */
    <T> a(Extension<?, T> extension, T t) {
        this.f50a = extension;
        this.f51b = t;
    }

    a() {
        this.f52c = new ArrayList();
    }

    final void a(b bVar) {
        this.f52c.add(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final <T> T a(Extension<?, T> extension) {
        if (this.f51b != null) {
            if (this.f50a != extension) {
                throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
            }
        } else {
            this.f50a = extension;
            this.f51b = extension.getValueFrom(this.f52c);
            this.f52c = null;
        }
        return (T) this.f51b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final <T> void a(Extension<?, T> extension, T t) {
        this.f50a = extension;
        this.f51b = t;
        this.f52c = null;
    }

    final int a() {
        Object obj = this.f51b;
        if (obj != null) {
            return this.f50a.computeSerializedSize(obj);
        }
        int iComputeRawVarint32Size = 0;
        for (b bVar : this.f52c) {
            iComputeRawVarint32Size += CodedOutputByteBufferNano.computeRawVarint32Size(bVar.f53a) + 0 + bVar.f54b.length;
        }
        return iComputeRawVarint32Size;
    }

    final void a(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        Object obj = this.f51b;
        if (obj != null) {
            this.f50a.writeTo(obj, codedOutputByteBufferNano);
            return;
        }
        for (b bVar : this.f52c) {
            codedOutputByteBufferNano.writeRawVarint32(bVar.f53a);
            codedOutputByteBufferNano.writeRawBytes(bVar.f54b);
        }
    }

    public boolean equals(Object obj) {
        List<b> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f51b != null && aVar.f51b != null) {
            Extension<?, ?> extension = this.f50a;
            if (extension != aVar.f50a) {
                return false;
            }
            if (!extension.clazz.isArray()) {
                return this.f51b.equals(aVar.f51b);
            }
            Object obj2 = this.f51b;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) aVar.f51b);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) aVar.f51b);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) aVar.f51b);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) aVar.f51b);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) aVar.f51b);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) aVar.f51b);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) aVar.f51b);
        }
        List<b> list2 = this.f52c;
        if (list2 != null && (list = aVar.f52c) != null) {
            return list2.equals(list);
        }
        try {
            return Arrays.equals(c(), aVar.c());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(c()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private byte[] c() throws IOException {
        byte[] bArr = new byte[a()];
        a(CodedOutputByteBufferNano.newInstance(bArr));
        return bArr;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final a clone() {
        a aVar = new a();
        try {
            aVar.f50a = this.f50a;
            List<b> list = this.f52c;
            if (list == null) {
                aVar.f52c = null;
            } else {
                aVar.f52c.addAll(list);
            }
            Object obj = this.f51b;
            if (obj != null) {
                if (obj instanceof MessageNano) {
                    aVar.f51b = ((MessageNano) obj).mo229clone();
                } else if (obj instanceof byte[]) {
                    aVar.f51b = ((byte[]) obj).clone();
                } else {
                    int i = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length][];
                        aVar.f51b = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (obj instanceof boolean[]) {
                        aVar.f51b = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        aVar.f51b = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        aVar.f51b = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        aVar.f51b = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        aVar.f51b = ((double[]) obj).clone();
                    } else if (obj instanceof MessageNano[]) {
                        MessageNano[] messageNanoArr = (MessageNano[]) obj;
                        MessageNano[] messageNanoArr2 = new MessageNano[messageNanoArr.length];
                        aVar.f51b = messageNanoArr2;
                        while (i < messageNanoArr.length) {
                            messageNanoArr2[i] = messageNanoArr[i].mo229clone();
                            i++;
                        }
                    }
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
