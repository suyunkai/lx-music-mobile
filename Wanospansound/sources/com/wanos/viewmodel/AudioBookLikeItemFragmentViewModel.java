package com.wanos.viewmodel;

import android.os.Parcelable;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookLikeItemFragmentViewModel extends ViewModel {
    private int curPage;
    private Parcelable saveState;

    public void saveInstanceState(Parcelable parcelable, int curPage) {
        this.saveState = parcelable;
        this.curPage = curPage;
    }

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
