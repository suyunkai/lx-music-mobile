package com.liulishuo.filedownloader.event;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadServiceConnectChangedEvent extends IDownloadEvent {
    public static final String ID = "event.service.connect.changed";
    private final Class<?> serviceClass;
    private final ConnectStatus status;

    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super(ID);
        this.status = connectStatus;
        this.serviceClass = cls;
    }

    public ConnectStatus getStatus() {
        return this.status;
    }

    public boolean isSuchService(Class<?> cls) {
        Class<?> cls2 = this.serviceClass;
        return cls2 != null && cls2.getName().equals(cls.getName());
    }
}
