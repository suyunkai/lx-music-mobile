package com.ecarx.eas.framework.sdk.proto;

import com.ecarx.eas.framework.sdk.proto.ExtendableMessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ExtendableMessageNano<M extends ExtendableMessageNano<M>> extends MessageNano {
    protected FieldArray unknownFieldData;

    @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
    protected int computeSerializedSize() {
        if (this.unknownFieldData == null) {
            return 0;
        }
        int iA = 0;
        for (int i = 0; i < this.unknownFieldData.size(); i++) {
            iA += this.unknownFieldData.dataAt(i).a();
        }
        return iA;
    }

    @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (this.unknownFieldData == null) {
            return;
        }
        for (int i = 0; i < this.unknownFieldData.size(); i++) {
            this.unknownFieldData.dataAt(i).a(codedOutputByteBufferNano);
        }
    }

    public final boolean hasExtension(Extension<M, ?> extension) {
        FieldArray fieldArray = this.unknownFieldData;
        return (fieldArray == null || fieldArray.get(WireFormatNano.getTagFieldNumber(extension.tag)) == null) ? false : true;
    }

    public final <T> T getExtension(Extension<M, T> extension) {
        a aVar;
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray == null || (aVar = fieldArray.get(WireFormatNano.getTagFieldNumber(extension.tag))) == null) {
            return null;
        }
        return (T) aVar.a(extension);
    }

    public final <T> M setExtension(Extension<M, T> extension, T t) {
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(extension.tag);
        a aVar = null;
        if (t == null) {
            FieldArray fieldArray = this.unknownFieldData;
            if (fieldArray != null) {
                fieldArray.remove(tagFieldNumber);
                if (this.unknownFieldData.isEmpty()) {
                    this.unknownFieldData = null;
                }
            }
        } else {
            FieldArray fieldArray2 = this.unknownFieldData;
            if (fieldArray2 == null) {
                this.unknownFieldData = new FieldArray();
            } else {
                aVar = fieldArray2.get(tagFieldNumber);
            }
            if (aVar == null) {
                this.unknownFieldData.put(tagFieldNumber, new a(extension, t));
            } else {
                aVar.a(extension, t);
            }
        }
        return this;
    }

    protected final boolean storeUnknownField(CodedInputByteBufferNano codedInputByteBufferNano, int i) throws IOException {
        a aVar;
        int position = codedInputByteBufferNano.getPosition();
        if (!codedInputByteBufferNano.skipField(i)) {
            return false;
        }
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(i);
        b bVar = new b(i, codedInputByteBufferNano.getData(position, codedInputByteBufferNano.getPosition() - position));
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray == null) {
            this.unknownFieldData = new FieldArray();
            aVar = null;
        } else {
            aVar = fieldArray.get(tagFieldNumber);
        }
        if (aVar == null) {
            aVar = new a();
            this.unknownFieldData.put(tagFieldNumber, aVar);
        }
        aVar.a(bVar);
        return true;
    }

    @Override // com.ecarx.eas.framework.sdk.proto.MessageNano
    /* JADX INFO: renamed from: clone */
    public M mo229clone() throws CloneNotSupportedException {
        M m = (M) super.mo229clone();
        InternalNano.cloneUnknownFieldData(this, m);
        return m;
    }
}
