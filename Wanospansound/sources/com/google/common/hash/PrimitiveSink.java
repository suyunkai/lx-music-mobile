package com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public interface PrimitiveSink {
    PrimitiveSink putBoolean(boolean b2);

    PrimitiveSink putByte(byte b2);

    PrimitiveSink putBytes(ByteBuffer bytes);

    PrimitiveSink putBytes(byte[] bytes);

    PrimitiveSink putBytes(byte[] bytes, int off, int len);

    PrimitiveSink putChar(char c2);

    PrimitiveSink putDouble(double d2);

    PrimitiveSink putFloat(float f);

    PrimitiveSink putInt(int i);

    PrimitiveSink putLong(long l);

    PrimitiveSink putShort(short s);

    PrimitiveSink putString(CharSequence charSequence, Charset charset);

    PrimitiveSink putUnencodedChars(CharSequence charSequence);
}
