package com.liulishuo.filedownloader;

/* JADX INFO: loaded from: classes2.dex */
public interface IDownloadSpeed {

    public interface Lookup {
        int getSpeed();

        void setMinIntervalUpdateSpeed(int i);
    }

    public interface Monitor {
        void end(long j);

        void reset();

        void start(long j);

        void update(long j);
    }
}
