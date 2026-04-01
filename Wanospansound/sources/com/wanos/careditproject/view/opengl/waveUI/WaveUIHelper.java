package com.wanos.careditproject.view.opengl.waveUI;

import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView;

/* JADX INFO: loaded from: classes3.dex */
public class WaveUIHelper {
    protected static AudioTracksGLView glView;

    public static void init(AudioTracksGLView audioTracksGLView) {
        glView = audioTracksGLView;
    }

    public static void destory() {
        glView = null;
    }

    public static void showToolBar(int i, float f, float f2, String str, boolean z) {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.showPcmToolBarV2(i, f, f2, str, z);
        }
    }

    public static void hideToolBar() {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.hidePcmToolBar();
        }
    }

    public static void showPasteView(float f, float f2) {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.showPasteView(f, f2);
        }
    }

    public static void setCurSampleNum(long j) {
        EditingParams.getInstance().setProgressSampleNum(j, true, true);
    }

    public static void setPcmViewStartOfEndOff(boolean z, String str, long j) {
        if (glView != null) {
            EditingUtils.log("WaveUIHelper setPcmViewStartOfEndOff id = " + str + ", offSampleNum=" + j + ",isFront=" + z);
            glView.setPcmViewStartOfEndOff(z, str, j);
            glView.update();
        }
    }

    public static void setBallViewStartOfEndOff(boolean z, String str, long j) {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.setBallViewStartOfEndOff(z, str, j);
        }
    }

    public static void setScrollY(float f) {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.setScrollY(f);
        }
    }

    public static void drawAll() {
        AudioTracksGLView audioTracksGLView = glView;
        if (audioTracksGLView != null) {
            audioTracksGLView.drawAll();
        }
    }

    public static void setTrackIndex(int i) {
        EditingUtils.log("WaveUIHelper setTrackIndex index = " + i);
    }
}
