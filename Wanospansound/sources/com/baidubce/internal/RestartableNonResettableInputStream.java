package com.baidubce.internal;

import com.baidubce.BceClientException;
import com.baidubce.util.CheckUtils;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class RestartableNonResettableInputStream extends RestartableInputStream {
    private byte[] buffer;
    private boolean eof;
    private InputStream input;
    private int offset = 0;
    private int length = 0;

    public RestartableNonResettableInputStream(InputStream inputStream, int i) {
        this.eof = false;
        CheckUtils.isNotNull(inputStream, "input should not be null.");
        CheckUtils.checkArgument(i >= 0, "bufferSize should not be negative: " + i);
        this.buffer = new byte[i];
        this.input = inputStream;
        while (true) {
            int i2 = this.length;
            if (i2 >= i) {
                return;
            }
            try {
                int i3 = this.input.read(this.buffer, i2, i - i2);
                if (i3 < 0) {
                    this.eof = true;
                    return;
                }
                this.length += i3;
            } catch (IOException e) {
                throw new BceClientException("Fail to read data from input.", e);
            }
        }
    }

    @Override // com.baidubce.internal.RestartableInputStream
    public void restart() {
        if (this.buffer == null) {
            throw new IllegalStateException("Fail to restart. Input buffer exhausted.");
        }
        this.offset = 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        CheckUtils.isNotNull(bArr, "b should not be null.");
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.offset;
        int i4 = this.length;
        if (i3 < i4) {
            int i5 = i4 - i3;
            if (i5 <= i2) {
                i2 = i5;
            }
            System.arraycopy(this.buffer, i3, bArr, i, i2);
            this.offset += i2;
            return i2;
        }
        if (this.eof) {
            return -1;
        }
        int i6 = this.input.read(bArr, i, i2);
        if (i6 < 0) {
            this.eof = true;
            return -1;
        }
        this.buffer = null;
        return i6;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.offset;
        if (i < this.length) {
            byte[] bArr = this.buffer;
            this.offset = i + 1;
            return bArr[i] & 255;
        }
        if (this.eof) {
            return -1;
        }
        int i2 = this.input.read();
        if (i2 < 0) {
            this.eof = true;
            return -1;
        }
        this.buffer = null;
        return i2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.input.close();
    }
}
