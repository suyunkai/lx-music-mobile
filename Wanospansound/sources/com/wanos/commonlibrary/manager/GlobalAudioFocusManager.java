package com.wanos.commonlibrary.manager;

import android.media.AudioManager;
import com.wanos.commonlibrary.router.ServiceRouter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class GlobalAudioFocusManager {
    private static volatile GlobalAudioFocusManager instance;
    private WeakReference<IPlayer> currentActivePlayerRef = new WeakReference<>(null);
    private final AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.commonlibrary.manager.GlobalAudioFocusManager$$ExternalSyntheticLambda0
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            this.f$0.m448x1ffc1385(i);
        }
    };
    private final AudioManager audioManager = (AudioManager) ServiceRouter.getCommonService().getApplication().getSystemService("audio");

    public interface FocusRequestCallback {
        void onAudioFocusDenied();

        void onAudioFocusGranted();
    }

    public interface IPlayer {
        default void onAudioFocusGained() {
        }

        default void onAudioFocusLossTransientCanDuck() {
        }

        default void onAudioFocusLost() {
        }

        default void onAudioFocusLostTransient() {
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-commonlibrary-manager-GlobalAudioFocusManager, reason: not valid java name */
    /* synthetic */ void m448x1ffc1385(int i) {
        IPlayer iPlayer = this.currentActivePlayerRef.get();
        if (iPlayer == null) {
            return;
        }
        if (i == -3) {
            iPlayer.onAudioFocusLossTransientCanDuck();
            return;
        }
        if (i == -2) {
            iPlayer.onAudioFocusLostTransient();
        } else if (i == -1) {
            releaseCurrentFocus();
        } else {
            if (i != 1) {
                return;
            }
            iPlayer.onAudioFocusGained();
        }
    }

    private GlobalAudioFocusManager() {
    }

    public static GlobalAudioFocusManager getInstance() {
        if (instance == null) {
            synchronized (GlobalAudioFocusManager.class) {
                if (instance == null) {
                    instance = new GlobalAudioFocusManager();
                }
            }
        }
        return instance;
    }

    public boolean requestFocus(IPlayer iPlayer) {
        return requestFocus(iPlayer, null);
    }

    public boolean requestFocus(IPlayer iPlayer, FocusRequestCallback focusRequestCallback) {
        IPlayer iPlayer2 = this.currentActivePlayerRef.get();
        if (iPlayer2 != null && iPlayer2 != iPlayer) {
            iPlayer2.onAudioFocusLost();
        }
        if (this.audioManager.requestAudioFocus(this.focusChangeListener, 3, 1) != 1) {
            if (focusRequestCallback == null) {
                return false;
            }
            focusRequestCallback.onAudioFocusDenied();
            return false;
        }
        this.currentActivePlayerRef = new WeakReference<>(iPlayer);
        iPlayer.onAudioFocusGained();
        if (focusRequestCallback != null) {
            focusRequestCallback.onAudioFocusGranted();
        }
        return true;
    }

    public void abandonFocus(IPlayer iPlayer) {
        if (this.currentActivePlayerRef.get() == iPlayer) {
            releaseCurrentFocus();
        }
    }

    private void releaseCurrentFocus() {
        IPlayer iPlayer = this.currentActivePlayerRef.get();
        if (iPlayer != null) {
            this.audioManager.abandonAudioFocus(this.focusChangeListener);
            iPlayer.onAudioFocusLost();
        }
        this.currentActivePlayerRef.clear();
    }

    public void destroy() {
        releaseCurrentFocus();
    }
}
