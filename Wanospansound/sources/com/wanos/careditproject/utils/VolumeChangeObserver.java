package com.wanos.careditproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeChangeObserver {
    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private AudioManager mAudioManager;
    private Context mContext;
    private boolean mRegistered = false;
    private VolumeBroadcastReceiver mVolumeBroadcastReceiver;
    private VolumeChangeListener mVolumeChangeListener;

    public interface VolumeChangeListener {
        void onVolumeChanged(int i);
    }

    public VolumeChangeObserver(Context context) {
        this.mContext = context;
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }
    }

    public int getCurrentMusicVolume() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return -1;
    }

    public void setCurrentMusicVolume(int i) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.setStreamVolume(3, i, 1);
        }
    }

    public int getMaxMusicVolume() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamMaxVolume(3);
        }
        return 15;
    }

    public VolumeChangeListener getVolumeChangeListener() {
        return this.mVolumeChangeListener;
    }

    public void setVolumeChangeListener(VolumeChangeListener volumeChangeListener) {
        this.mVolumeChangeListener = volumeChangeListener;
    }

    public void registerReceiver() {
        this.mVolumeBroadcastReceiver = new VolumeBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VOLUME_CHANGED_ACTION);
        this.mContext.registerReceiver(this.mVolumeBroadcastReceiver, intentFilter);
        this.mRegistered = true;
    }

    public void unregisterReceiver() {
        if (this.mRegistered) {
            try {
                this.mContext.unregisterReceiver(this.mVolumeBroadcastReceiver);
                this.mVolumeChangeListener = null;
                this.mRegistered = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class VolumeBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<VolumeChangeObserver> mObserverWeakReference;

        public VolumeBroadcastReceiver(VolumeChangeObserver volumeChangeObserver) {
            this.mObserverWeakReference = new WeakReference<>(volumeChangeObserver);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            VolumeChangeObserver volumeChangeObserver;
            VolumeChangeListener volumeChangeListener;
            int currentMusicVolume;
            if (!VolumeChangeObserver.VOLUME_CHANGED_ACTION.equals(intent.getAction()) || intent.getIntExtra(VolumeChangeObserver.EXTRA_VOLUME_STREAM_TYPE, -1) != 3 || (volumeChangeObserver = this.mObserverWeakReference.get()) == null || (volumeChangeListener = volumeChangeObserver.getVolumeChangeListener()) == null || (currentMusicVolume = volumeChangeObserver.getCurrentMusicVolume()) < 0) {
                return;
            }
            volumeChangeListener.onVolumeChanged(currentMusicVolume);
        }
    }
}
