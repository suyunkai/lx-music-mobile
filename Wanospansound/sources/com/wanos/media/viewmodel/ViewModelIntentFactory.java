package com.wanos.media.viewmodel;

import android.content.Intent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* JADX INFO: loaded from: classes3.dex */
public class ViewModelIntentFactory implements ViewModelProvider.Factory {
    private final Intent intent;

    public ViewModelIntentFactory(Intent intent) {
        this.intent = intent;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> cls) {
        T tCast;
        if (cls.isAssignableFrom(RelaxListViewModel.class)) {
            T tCast2 = cls.cast(new RelaxListViewModel(this.intent));
            if (tCast2 != null) {
                return tCast2;
            }
        } else if (cls.isAssignableFrom(RelaxCollectListViewModel.class) && (tCast = cls.cast(new RelaxCollectListViewModel(this.intent))) != null) {
            return tCast;
        }
        return (T) super.create(cls);
    }
}
