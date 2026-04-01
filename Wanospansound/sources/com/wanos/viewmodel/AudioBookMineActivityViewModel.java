package com.wanos.viewmodel;

import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookMineActivityViewModel extends ViewModel {
    private int currentTabIndex = 0;

    public int getCurrentTabIndex() {
        return this.currentTabIndex;
    }

    public void setCurrentTabIndex(int currentTabIndex) {
        this.currentTabIndex = currentTabIndex;
    }
}
