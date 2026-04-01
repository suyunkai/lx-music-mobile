package com.wanos.wanosplayermodule;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.data.ExtendOperaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayEngine {
    public static final String TAG = "wanos:[MediaPlayEngine]";
    private static volatile MediaPlayEngine mediaPlayEngine;
    private ExtendOperaData extendOperaData;
    private List<MediaPlayServiceConnectListener> mediaPlayServiceConnectListenerList;
    private MediaPlayerService mediaPlayerService;
    private boolean isConnectedService = false;
    public boolean startFroeSuc = false;
    ServiceConnection connection = new ServiceConnection() { // from class: com.wanos.wanosplayermodule.MediaPlayEngine.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaPlayEngine.this.isConnectedService = true;
            if (iBinder instanceof MediaPlayerService.LocalBinder) {
                MediaPlayerService.LocalBinder localBinder = (MediaPlayerService.LocalBinder) iBinder;
                MediaPlayEngine.this.mediaPlayerService = localBinder.getMediaPlayerService();
                if (localBinder == null || MediaPlayEngine.this.mediaPlayerService == null) {
                    return;
                }
                if (MediaPlayEngine.this.mediaPlayServiceConnectListenerList != null) {
                    Iterator it = MediaPlayEngine.this.mediaPlayServiceConnectListenerList.iterator();
                    while (it.hasNext()) {
                        ((MediaPlayServiceConnectListener) it.next()).onServiceConnected(MediaPlayEngine.this.mediaPlayerService);
                    }
                }
                if (MediaPlayEngine.this.extendOperaData == null || MediaPlayEngine.this.mediaPlayerService == null) {
                    return;
                }
                MediaPlayEngine.this.mediaPlayerService.setExtendOperaData(MediaPlayEngine.this.extendOperaData);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MediaPlayEngine.this.isConnectedService = false;
            if (MediaPlayEngine.this.mediaPlayServiceConnectListenerList != null) {
                Iterator it = MediaPlayEngine.this.mediaPlayServiceConnectListenerList.iterator();
                while (it.hasNext()) {
                    ((MediaPlayServiceConnectListener) it.next()).onServiceDisconnected();
                }
            }
        }
    };

    private MediaPlayEngine() {
    }

    public static MediaPlayEngine getInstance() {
        if (mediaPlayEngine == null) {
            synchronized (MediaPlayEngine.class) {
                if (mediaPlayEngine == null) {
                    mediaPlayEngine = new MediaPlayEngine();
                }
            }
        }
        return mediaPlayEngine;
    }

    public void init(Application application) {
        if (application == null) {
            return;
        }
        bindService(application);
    }

    public ExtendOperaData getExtendOperaData() {
        return this.extendOperaData;
    }

    public void setExtendOperaData(ExtendOperaData extendOperaData) {
        this.extendOperaData = extendOperaData;
        MediaPlayerService mediaPlayerService = this.mediaPlayerService;
        if (mediaPlayerService != null) {
            mediaPlayerService.setExtendOperaData(extendOperaData);
        }
    }

    private void bindService(Application application) {
        Intent intent = new Intent(application, (Class<?>) MediaPlayerService.class);
        try {
            application.startForegroundService(intent);
            this.startFroeSuc = true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        application.bindService(intent, this.connection, 1);
    }

    public MediaPlayerService getMediaPlayerService() {
        return this.mediaPlayerService;
    }

    public void addMediaPlayServiceConnectListener(MediaPlayServiceConnectListener mediaPlayServiceConnectListener, boolean z) {
        MediaPlayerService mediaPlayerService;
        if (mediaPlayServiceConnectListener != null) {
            if (this.mediaPlayServiceConnectListenerList == null) {
                this.mediaPlayServiceConnectListenerList = new ArrayList();
            }
            this.mediaPlayServiceConnectListenerList.add(mediaPlayServiceConnectListener);
            if (z && this.isConnectedService && (mediaPlayerService = this.mediaPlayerService) != null) {
                mediaPlayServiceConnectListener.onServiceConnected(mediaPlayerService);
            }
        }
    }

    public void removeMediaPlayServiceConnectListener(MediaPlayServiceConnectListener mediaPlayServiceConnectListener) {
        List<MediaPlayServiceConnectListener> list;
        if (mediaPlayServiceConnectListener == null || (list = this.mediaPlayServiceConnectListenerList) == null) {
            return;
        }
        list.remove(mediaPlayServiceConnectListener);
    }
}
