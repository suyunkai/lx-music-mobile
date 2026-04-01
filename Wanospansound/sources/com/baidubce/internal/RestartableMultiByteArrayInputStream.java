package com.baidubce.internal;

import com.baidubce.util.CheckUtils;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestartableMultiByteArrayInputStream extends RestartableInputStream {
    private int blockSize;
    private List<byte[]> byteArrayList;
    private long length;
    private long pos = 0;

    public RestartableMultiByteArrayInputStream(List<byte[]> list, long j) {
        long length = 0;
        CheckUtils.isNotNull(list, "byteArrayList should not be null.");
        CheckUtils.checkArgument(!list.isEmpty(), "byteArrayList should not be empty.");
        Iterator<byte[]> it = list.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            byte[] next = it.next();
            CheckUtils.isNotNull(next, "byteArrayList should not contain null element.");
            if (next.length > 0) {
                z = true;
            }
            CheckUtils.checkArgument(z, "byteArrayList should not contain empty byte array.");
            length += (long) next.length;
        }
        CheckUtils.checkArgument(length >= j, "The specified length(%s) is greater than the total length(%s) of elements in byteArrayList.", Long.valueOf(j), Long.valueOf(length));
        this.blockSize = list.get(0).length;
        for (int i = 1; i < list.size() - 1; i++) {
            int length2 = list.get(i).length;
            int i2 = this.blockSize;
            CheckUtils.checkArgument(length2 == i2, "All elements in byteArrayList except the last one should have the same length. The first element's length is %s but the %sth element's length is %s.", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(length2));
        }
        this.byteArrayList = list;
        this.length = j;
    }

    @Override // com.baidubce.internal.RestartableInputStream
    public void restart() {
        this.pos = 0L;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        CheckUtils.isNotNull(bArr, "b should not be null.");
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (this.pos == this.length) {
            return -1;
        }
        int i3 = 0;
        while (i2 > 0) {
            long j = this.pos;
            if (j >= this.length) {
                break;
            }
            int i4 = this.blockSize;
            int i5 = (int) (j / ((long) i4));
            int i6 = (int) (j % ((long) i4));
            byte[] bArr2 = this.byteArrayList.get(i5);
            int length = bArr2.length - i6;
            if (length > i2) {
                length = i2;
            }
            System.arraycopy(bArr2, i6, bArr, i, length);
            this.pos += (long) length;
            i += length;
            i2 -= length;
            i3 += length;
        }
        return i3;
    }

    @Override // java.io.InputStream
    public int read() {
        long j = this.pos;
        if (j == this.length) {
            return -1;
        }
        int i = this.blockSize;
        int i2 = (int) (j / ((long) i));
        int i3 = (int) (j % ((long) i));
        this.pos = j + 1;
        return this.byteArrayList.get(i2)[i3] & 255;
    }
}
