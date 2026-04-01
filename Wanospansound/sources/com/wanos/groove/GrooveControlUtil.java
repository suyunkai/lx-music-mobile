package com.wanos.groove;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.wanos.groove.IGrooveDataInterface;
import com.wanos.groove.IGrooveDataListener;
import com.wanos.groove.base.GrooveSdk;
import com.wanos.groove.base.GrooveSdkInterface;
import com.wanos.groove.listener.GrooveStateListener;
import com.wanos.groove.utils.GrooveRemote;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class GrooveControlUtil extends GrooveSdk<IGrooveDataInterface> implements GrooveSdkInterface {
    private static volatile GrooveControlUtil instance;
    Set<GrooveStateListener> listener = new HashSet();
    IGrooveDataListener.Stub mCallBackImpl = new IGrooveDataListener.Stub() { // from class: com.wanos.groove.GrooveControlUtil.1
        @Override // com.wanos.groove.IGrooveDataListener
        public void lyricChanged(LyricLine lyricLine) throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                for (GrooveStateListener grooveStateListener : GrooveControlUtil.this.listener) {
                    Log.i("AI律动", "\r\n----单行歌词监听");
                    grooveStateListener.lyricChanged(lyricLine);
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void getLyricChanged(LyricInfo lyricInfo) throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                if (!it.hasNext()) {
                    Log.i("AI律动", "----未注册该接口---无法监听歌词变化");
                }
                while (it.hasNext()) {
                    GrooveStateListener next = it.next();
                    Log.i("AI律动", "\r\n----全部歌词监听");
                    next.getLyricChanged(lyricInfo);
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onIndexChanged(MediaInfo mediaInfo) throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                if (!it.hasNext()) {
                    Log.i("AI律动", "----未注册该接口---无法监听当前播放歌曲变化");
                }
                while (it.hasNext()) {
                    GrooveStateListener next = it.next();
                    Log.i("AI律动", "\r\n----播放歌曲监听");
                    next.onIndexChanged(mediaInfo);
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onListChanged() throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                if (!it.hasNext()) {
                    Log.i("AI律动", "----未注册该接口---无法监听播放列表变化");
                }
                while (it.hasNext()) {
                    GrooveStateListener next = it.next();
                    Log.i("AI律动", "\r\n----播放列表监听");
                    next.onListChanged();
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onListAdded() throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                while (it.hasNext()) {
                    it.next().onListAdded();
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void playState(int i) throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                if (!it.hasNext()) {
                    Log.i("AI律动", "----未注册该接口---无法监听播放状态变化");
                }
                while (it.hasNext()) {
                    GrooveStateListener next = it.next();
                    Log.i("AI律动", "\r\n----播放状态监听");
                    next.playState(i);
                }
            }
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onPlayModeChanged(int i) throws RemoteException {
            if (GrooveControlUtil.this.listener != null) {
                Iterator<GrooveStateListener> it = GrooveControlUtil.this.listener.iterator();
                if (!it.hasNext()) {
                    Log.i("AI律动", "----未注册该接口---无法监听播放模式变化");
                }
                while (it.hasNext()) {
                    GrooveStateListener next = it.next();
                    Log.i("AI律动", "\r\n----播放模式监听");
                    next.onPlayModeChanged(i);
                }
            }
        }
    };

    public static GrooveControlUtil getInstance() {
        if (instance == null) {
            synchronized (GrooveControlUtil.class) {
                if (instance == null) {
                    instance = new GrooveControlUtil();
                }
            }
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.groove.base.GrooveSdk
    public IGrooveDataInterface asInterface(IBinder iBinder) {
        return IGrooveDataInterface.Stub.asInterface(iBinder);
    }

    public MediaInfo getCurrentMusicInfo() {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法获取当前播放歌曲");
            return null;
        }
        try {
            Log.i("AI律动", "\r\n----getCurrentMusicInfo()获取当前播放媒体信息");
            return getProxy().getCurrentMediaInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MediaInfo> getCurrentList() {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法获取播放列表");
            return null;
        }
        try {
            Log.i("AI律动", "\r\n----getCurrentList()获取当前播放列表");
            return getProxy().getCurrentList();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void doPlay() {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda6
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m454lambda$doPlay$0$comwanosgrooveGrooveControlUtil();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$doPlay$0$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m454lambda$doPlay$0$comwanosgrooveGrooveControlUtil() throws RemoteException {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法播放");
            return false;
        }
        Log.i("AI律动", "\r\n----doPlay()播放\r\n");
        getProxy().doPlay();
        return true;
    }

    public void playAreaContentData() {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda2
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m457lambda$playAreaContentData$1$comwanosgrooveGrooveControlUtil();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$playAreaContentData$1$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m457lambda$playAreaContentData$1$comwanosgrooveGrooveControlUtil() throws RemoteException {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法播放推荐歌曲");
            return false;
        }
        Log.i("AI律动", "\r\n----playAreaContentData()播放推荐歌曲\r\n");
        getProxy().playAreaContentData();
        return true;
    }

    public void pre() {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda5
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m458lambda$pre$2$comwanosgrooveGrooveControlUtil();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$pre$2$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m458lambda$pre$2$comwanosgrooveGrooveControlUtil() throws RemoteException {
        if (getProxy() != null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法播放上一首歌曲");
            return false;
        }
        Log.i("AI律动", "\r\n----pre()播放上一首歌曲\r\n");
        getProxy().pre();
        return true;
    }

    public void next() {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda4
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m455lambda$next$3$comwanosgrooveGrooveControlUtil();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$next$3$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m455lambda$next$3$comwanosgrooveGrooveControlUtil() throws RemoteException {
        if (getProxy() != null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法播放下一首歌曲");
            return false;
        }
        Log.i("AI律动", "\r\n----next()播放下一首歌曲\r\n");
        getProxy().next();
        return true;
    }

    public void pause() {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda0
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m456lambda$pause$4$comwanosgrooveGrooveControlUtil();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$pause$4$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m456lambda$pause$4$comwanosgrooveGrooveControlUtil() throws RemoteException {
        if (getProxy() != null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法暂停播放");
            return false;
        }
        Log.i("AI律动", "\r\n----pause()暂停\r\n");
        getProxy().pause();
        return true;
    }

    public int getCurrentPlayMode() {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法获取当前播放模式");
            return -1;
        }
        try {
            Log.i("AI律动", "\r\n----getCurrentPlayMode()获取当前播放模式");
            return getProxy().getCurrentPlayMode();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public LyricInfo getCurrentLyric() {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法获取全部歌词");
            return null;
        }
        try {
            Log.i("AI律动", "\r\n----getCurrentLyric()获取歌曲的全部歌词");
            return getProxy().getCurrentLyric();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getMediaPlayer() {
        try {
            if (getProxy() == null && !getConnectState()) {
                Log.i("AI律动", "----未连接---无法获取播放器");
            } else {
                getProxy().getMediaPlayer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.wanos.groove.base.GrooveSdkInterface
    public void registerCallback(final GrooveStateListener grooveStateListener) {
        GrooveRemote.exec(new GrooveRemote.RemoteFunction() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda1
            @Override // com.wanos.groove.utils.GrooveRemote.RemoteFunction
            public final Object call() {
                return this.f$0.m460lambda$registerCallback$6$comwanosgrooveGrooveControlUtil(grooveStateListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$registerCallback$6$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ Boolean m460lambda$registerCallback$6$comwanosgrooveGrooveControlUtil(final GrooveStateListener grooveStateListener) throws RemoteException {
        if (getProxy() == null && !getConnectState()) {
            Log.i("AI律动", "----未连接---无法注册接口");
            return false;
        }
        if (isServiceConnected()) {
            this.listener.add(grooveStateListener);
            Log.i("AI律动", "----注册接口成功");
            getProxy().registerCallback(this.mCallBackImpl);
            return true;
        }
        isRetryConnected();
        getTaskQueue().offer(new Runnable() { // from class: com.wanos.groove.GrooveControlUtil$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m459lambda$registerCallback$5$comwanosgrooveGrooveControlUtil(grooveStateListener);
            }
        });
        return false;
    }

    /* JADX INFO: renamed from: lambda$registerCallback$5$com-wanos-groove-GrooveControlUtil, reason: not valid java name */
    /* synthetic */ void m459lambda$registerCallback$5$comwanosgrooveGrooveControlUtil(GrooveStateListener grooveStateListener) {
        Log.i("AI律动", "----未连接将接口放入队列");
        registerCallback(grooveStateListener);
    }

    @Override // com.wanos.groove.base.GrooveSdkInterface
    public void unregisterCallback(GrooveStateListener grooveStateListener) {
        Set<GrooveStateListener> set = this.listener;
        if (set != null) {
            set.remove(grooveStateListener);
            try {
                getProxy().unregisterCallback(this.mCallBackImpl);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.i("AI律动", "----注销接口成功");
        }
    }
}
