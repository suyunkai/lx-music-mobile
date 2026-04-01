package com.wanos.carstudio.audiotrackplayer.Interaction;

import android.os.Handler;
import android.os.Process;
import com.wanos.play.AudioPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackUtils {
    public static AudioTrackUtils audioTrackUtils;
    private final int curSimpleNum = 0;
    private boolean isPlaying = false;
    private final Runnable playRunnable = new Runnable() { // from class: com.wanos.carstudio.audiotrackplayer.Interaction.AudioTrackUtils.1
        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            AudioTrackUtils.this.isPlaying = true;
            AudioPlayer.getInstance().play();
            while (AudioTrackUtils.this.isPlaying) {
            }
            AudioTrackUtils.this.isPlaying = false;
            AudioPlayer.getInstance().stop();
            AudioTrackUtils.this.readListThread = null;
        }
    };
    Handler progressHandler;
    public float[] readBuf;
    private Thread readListThread;

    public static AudioTrackUtils getInstance() {
        if (audioTrackUtils == null) {
            audioTrackUtils = new AudioTrackUtils();
        }
        return audioTrackUtils;
    }

    public void play() {
        if (this.readListThread == null) {
            Thread thread = new Thread(this.playRunnable);
            this.readListThread = thread;
            thread.start();
        }
    }

    public void stopAudio() {
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }
}
