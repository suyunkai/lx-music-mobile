package com.wanos.media.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.KeyboardUtils;
import com.wanos.media.base.IDialogStateCallback;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WanosBaseDialog<VB extends ViewBinding, VM extends ViewModel> extends DialogFragment {
    private static final String TAG = "WanosBaseDialog";
    protected VB binding;
    private WeakReference<IDialogStateCallback> iDialogStateCallbackWeakReference;
    protected VM viewModel;

    protected ViewModelProvider.Factory getFactory() {
        return null;
    }

    protected void initAdapter(Bundle bundle) {
    }

    protected void initData(Bundle bundle) {
    }

    protected int initGravity() {
        return 17;
    }

    protected abstract void initListener(Bundle bundle);

    protected abstract void initObserve(Bundle bundle);

    protected void initSetting(Bundle bundle) {
    }

    protected abstract VB initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract Class<VM> initViewModel();

    protected boolean isCancel() {
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = (VB) initViewBinding(layoutInflater, viewGroup);
        if (getFactory() == null) {
            this.viewModel = (VM) new ViewModelProvider(this).get(initViewModel());
        } else {
            this.viewModel = (VM) new ViewModelProvider(this, getFactory()).get(initViewModel());
        }
        VB vb = this.binding;
        if (vb != null) {
            return vb.getRoot();
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        WeakReference<IDialogStateCallback> weakReference = this.iDialogStateCallbackWeakReference;
        if (weakReference != null && weakReference.get() != null) {
            this.iDialogStateCallbackWeakReference.get().onDialogStateChanged(IDialogStateCallback.DialogState.DISPLAY);
        }
        initSetting(bundle);
        initAdapter(bundle);
        initObserve(bundle);
        initListener(bundle);
        initData(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        final Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setDimAmount(0.6f);
        dialog.setCancelable(isCancel());
        dialog.setCanceledOnTouchOutside(false);
        int iInitGravity = initGravity();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (iInitGravity == 80) {
            attributes.width = -1;
        } else {
            attributes.width = -2;
        }
        attributes.height = -2;
        attributes.gravity = iInitGravity;
        window.setAttributes(attributes);
        window.getDecorView().setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.base.WanosBaseDialog.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || WanosBaseDialog.this.binding == null) {
                    return false;
                }
                Rect rect = new Rect();
                WanosBaseDialog.this.binding.getRoot().getDrawingRect(rect);
                if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return false;
                }
                Log.i(WanosBaseDialog.TAG, "onTouch: 点击对话框外部");
                KeyboardUtils.hideSoftInput(window);
                return false;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDialogStateCallback) {
            this.iDialogStateCallbackWeakReference = new WeakReference<>((IDialogStateCallback) context);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.iDialogStateCallbackWeakReference = null;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
        WeakReference<IDialogStateCallback> weakReference = this.iDialogStateCallbackWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iDialogStateCallbackWeakReference.get().onDialogStateChanged(IDialogStateCallback.DialogState.DISMISS);
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
}
