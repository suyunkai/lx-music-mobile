package com.wanos.careditproject.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class EditingActivityViewModel extends ViewModel {
    public final MutableLiveData<Long> curSampleNum = new MutableLiveData<>(0L);
    public final MutableLiveData<Long> maxSampleNum = new MutableLiveData<>(0L);
}
