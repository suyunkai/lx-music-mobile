package com.wanos.commonlibrary.base;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;
import com.wanos.commonlibrary.R;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseFragmentDialog<T extends ViewBinding> extends DialogFragment {
    private static final String TAG = "wanos-[BaseFragmentDialog]";
    protected T mViewBinding;

    protected abstract void initAdapter(Bundle bundle);

    protected abstract void initData(Bundle bundle);

    protected abstract T initLayout(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void initListener(Bundle bundle);

    protected abstract void initSetting(Bundle bundle);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        T t = (T) initLayout(layoutInflater, viewGroup, bundle);
        this.mViewBinding = t;
        return t.getRoot();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.FullScreenDialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initSetting(bundle);
        initListener(bundle);
        initAdapter(bundle);
        initData(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        try {
            Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
            Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
            declaredField2.setAccessible(true);
            declaredField2.set(this, true);
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.add(this, str);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "showAllowingStateLoss: " + e.getMessage());
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        dismissAllowingStateLoss();
    }
}
