package com.baidubce.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class MD5DigestCalculatingInputStream extends FilterInputStream {
    private MessageDigest digest;

    public MD5DigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        super(inputStream);
        this.digest = MessageDigest.getInstance("MD5");
    }

    public byte[] getMd5Digest() {
        return this.digest.digest();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
        }
        this.in.reset();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = this.in.read();
        if (i != -1) {
            this.digest.update((byte) i);
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.in.read(bArr, i, i2);
        if (i3 != -1) {
            this.digest.update(bArr, i, i3);
        }
        return i3;
    }
}
