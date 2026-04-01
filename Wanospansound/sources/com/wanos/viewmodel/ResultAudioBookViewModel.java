package com.wanos.viewmodel;

import android.os.Parcelable;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class ResultAudioBookViewModel extends ViewModel {
    private Parcelable recyclerViewStateData;

    public void saveRecyclerViewStateData(Parcelable parcelable) {
        this.recyclerViewStateData = parcelable;
    }

    public Parcelable getRecyclerViewStateData() {
        return this.recyclerViewStateData;
    }
}
