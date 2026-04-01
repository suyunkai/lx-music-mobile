package com.wanos.commonlibrary.manager;

import android.app.Activity;
import android.app.Application;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

/* JADX INFO: loaded from: classes3.dex */
public class AppViewModelProviders {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends ViewModel> T getApplicationViewModel(Application application, Class<T> cls) {
        return (T) new ViewModelProvider((ViewModelStoreOwner) application, getDefaultFactory(application)).get(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends ViewModel> T getApplicationViewModel(Activity activity, Class<T> cls) {
        Application application = activity.getApplication();
        return (T) new ViewModelProvider((ViewModelStoreOwner) application, getDefaultFactory(application)).get(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends ViewModel> T getApplicationViewModel(Fragment fragment, Class<T> cls) {
        Application application = fragment.requireActivity().getApplication();
        return (T) new ViewModelProvider((ViewModelStoreOwner) application, getDefaultFactory(application)).get(cls);
    }

    private static ViewModelProvider.Factory getDefaultFactory(Application application) {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }
}
