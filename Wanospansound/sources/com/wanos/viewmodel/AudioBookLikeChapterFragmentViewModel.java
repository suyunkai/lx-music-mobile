package com.wanos.viewmodel;

import android.os.Parcelable;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookLikeChapterFragmentViewModel extends ViewModel {
    private int page = 1;
    private Parcelable rvState;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Parcelable getRvState() {
        return this.rvState;
    }

    public void setRvState(Parcelable rvState) {
        this.rvState = rvState;
    }
}
