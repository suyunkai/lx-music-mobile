package com.wanos.viewmodel;

import android.os.Parcelable;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookFollowFragmentViewModel extends ViewModel {
    private int curPage = 1;
    private Parcelable saveState;

    public Parcelable getSaveInstanceState() {
        return this.saveState;
    }

    public void setSaveInstanceState(Parcelable saveState) {
        this.saveState = saveState;
    }

    public int getCurPage() {
        return this.curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
