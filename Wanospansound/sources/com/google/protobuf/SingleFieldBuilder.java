package com.google.protobuf;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
    private BType builder;
    private boolean isClean;
    private MType message;
    private GeneratedMessage.BuilderParent parent;

    public SingleFieldBuilder(MType message, GeneratedMessage.BuilderParent parent, boolean isClean) {
        this.message = (MType) Internal.checkNotNull(message);
        this.parent = parent;
        this.isClean = isClean;
    }

    public void dispose() {
        this.parent = null;
    }

    public MType getMessage() {
        if (this.message == null) {
            this.message = (MType) this.builder.buildPartial();
        }
        return this.message;
    }

    public MType build() {
        this.isClean = true;
        return (MType) getMessage();
    }

    public BType getBuilder() {
        if (this.builder == null) {
            BType btype = (BType) this.message.newBuilderForType(this);
            this.builder = btype;
            btype.mergeFrom(this.message);
            this.builder.markClean();
        }
        return this.builder;
    }

    public IType getMessageOrBuilder() {
        BType btype = this.builder;
        return btype != null ? btype : this.message;
    }

    public SingleFieldBuilder<MType, BType, IType> setMessage(MType message) {
        this.message = (MType) Internal.checkNotNull(message);
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.protobuf.SingleFieldBuilder<MType, BType, IType> mergeFrom(MType r3) {
        /*
            r2 = this;
            BType extends com.google.protobuf.GeneratedMessage$Builder r0 = r2.builder
            if (r0 != 0) goto Lf
            MType extends com.google.protobuf.GeneratedMessage r0 = r2.message
            com.google.protobuf.Message r1 = r0.getDefaultInstanceForType()
            if (r0 != r1) goto Lf
            r2.message = r3
            goto L16
        Lf:
            com.google.protobuf.GeneratedMessage$Builder r0 = r2.getBuilder()
            r0.mergeFrom(r3)
        L16:
            r2.onChanged()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.SingleFieldBuilder.mergeFrom(com.google.protobuf.GeneratedMessage):com.google.protobuf.SingleFieldBuilder");
    }

    public SingleFieldBuilder<MType, BType, IType> clear() {
        Message defaultInstanceForType;
        MType mtype = this.message;
        if (mtype != null) {
            defaultInstanceForType = mtype.getDefaultInstanceForType();
        } else {
            defaultInstanceForType = this.builder.getDefaultInstanceForType();
        }
        this.message = (MType) defaultInstanceForType;
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        this.isClean = true;
        return this;
    }

    private void onChanged() {
        GeneratedMessage.BuilderParent builderParent;
        if (this.builder != null) {
            this.message = null;
        }
        if (!this.isClean || (builderParent = this.parent) == null) {
            return;
        }
        builderParent.markDirty();
        this.isClean = false;
    }

    @Override // com.google.protobuf.AbstractMessage.BuilderParent
    public void markDirty() {
        onChanged();
    }
}
