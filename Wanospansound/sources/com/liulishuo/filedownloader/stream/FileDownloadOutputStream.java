package com.liulishuo.filedownloader.stream;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface FileDownloadOutputStream {
    void close() throws IOException;

    void flushAndSync() throws IOException;

    void seek(long j) throws IllegalAccessException, IOException;

    void setLength(long j) throws IllegalAccessException, IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
