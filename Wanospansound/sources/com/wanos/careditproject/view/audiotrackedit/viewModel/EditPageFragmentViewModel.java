package com.wanos.careditproject.view.audiotrackedit.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class EditPageFragmentViewModel extends ViewModel {
    public MutableLiveData<EditLoadingState> loadingState = new MutableLiveData<>(EditLoadingState.LOADING);

    public enum EditLoadingState {
        LOADING,
        LOADED,
        ERROR
    }

    public void setLoadingState(EditLoadingState editLoadingState) {
        this.loadingState.postValue(editLoadingState);
    }
}
