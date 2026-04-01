package com.wanos.careditproject.view.viewmodel;

import androidx.lifecycle.ViewModel;
import com.wanos.bean.ProjectInfo;
import com.wanos.wanosplayermodule.MediaPlayerHelper;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerPageFragment2ViewModel extends ViewModel {
    private boolean isEverPlayed;
    private MediaPlayerHelper mMediaPlayerHelper;
    private ProjectInfo projectInfo;

    public MediaPlayerHelper getmMediaPlayerHelper() {
        return this.mMediaPlayerHelper;
    }

    public void setmMediaPlayerHelper(MediaPlayerHelper mediaPlayerHelper) {
        this.mMediaPlayerHelper = mediaPlayerHelper;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public ProjectInfo getProjectInfo() {
        return this.projectInfo;
    }

    public boolean isEverPlayed() {
        return this.isEverPlayed;
    }

    public void setIsEverPlayed(boolean z) {
        this.isEverPlayed = z;
    }
}
