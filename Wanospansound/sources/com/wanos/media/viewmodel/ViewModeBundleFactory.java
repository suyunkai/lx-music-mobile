package com.wanos.media.viewmodel;

import android.os.Bundle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* JADX INFO: loaded from: classes3.dex */
public class ViewModeBundleFactory implements ViewModelProvider.Factory {
    private final Bundle arguments;

    public ViewModeBundleFactory(Bundle bundle) {
        this.arguments = bundle;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> cls) {
        T tCast;
        if (cls.isAssignableFrom(RelaxPlayerInfoEditViewModel.class)) {
            T tCast2 = cls.cast(new RelaxPlayerInfoEditViewModel(this.arguments));
            if (tCast2 != null) {
                return tCast2;
            }
        } else if (cls.isAssignableFrom(CollectionDialogViewModel.class)) {
            T tCast3 = cls.cast(new CollectionDialogViewModel(this.arguments));
            if (tCast3 != null) {
                return tCast3;
            }
        } else if (cls.isAssignableFrom(SendShareCodeViewModel.class) && (tCast = cls.cast(new SendShareCodeViewModel(this.arguments))) != null) {
            return tCast;
        }
        return (T) super.create(cls);
    }
}
