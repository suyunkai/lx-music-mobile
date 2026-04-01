package com.wanos.media.viewmodel;

import androidx.lifecycle.ViewModel;
import com.wanos.media.view.TrialEndDialog;

/* JADX INFO: loaded from: classes3.dex */
public class TrialEndDialogViewModel extends ViewModel {
    private TrialEndDialog.ITrialCallback iTrialCallback;

    public void setTrialCallback(TrialEndDialog.ITrialCallback iTrialCallback) {
        this.iTrialCallback = iTrialCallback;
    }

    public void onExitScene(TrialEndDialog trialEndDialog) {
        TrialEndDialog.ITrialCallback iTrialCallback = this.iTrialCallback;
        if (iTrialCallback != null) {
            iTrialCallback.onExitScene(trialEndDialog);
        }
    }

    public void onContinue(TrialEndDialog trialEndDialog) {
        TrialEndDialog.ITrialCallback iTrialCallback = this.iTrialCallback;
        if (iTrialCallback != null) {
            iTrialCallback.onContinuePlay(trialEndDialog);
        }
    }
}
