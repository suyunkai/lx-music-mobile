package com.wanos.media.ui.search.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.FragmentManager;
import com.wanos.media.R;
import com.wanos.media.base.BaseAdDialog;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.databinding.FragmentCommonDialogBinding;

/* JADX INFO: loaded from: classes3.dex */
public class CommonDialog extends BaseAdDialog implements DialogInterface.OnKeyListener {
    private static final float DialogDimAmount = 0.0f;
    private FragmentCommonDialogBinding binding;
    private FragmentManager fm;
    private String leftString;
    private OnClickListener listener;
    private String msg;
    private String rightString;
    private String title;
    private Window window;
    private boolean cancelable = true;
    private boolean isDoubleBtn = true;
    private boolean isShowing = false;

    public interface OnClickListener {
        default void onLeftClick() {
        }

        default void onRightClick() {
        }
    }

    static /* synthetic */ void lambda$onViewCreated$3(View view) {
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    public static CommonDialog newInstance() {
        BaseAdDialog.setInstance(new CommonDialog());
        return (CommonDialog) BaseAdDialog.getInstance();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.FullScreenDialogTheme);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        this.window = window;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            this.window.setAttributes(attributes);
        }
        this.isShowing = true;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.isShowing = false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(1);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(this);
        FragmentCommonDialogBinding fragmentCommonDialogBindingInflate = FragmentCommonDialogBinding.inflate(inflater, container, false);
        this.binding = fragmentCommonDialogBindingInflate;
        return fragmentCommonDialogBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.btnDialogLeft.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.dialog.CommonDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m541x6b28b4c1(view2);
            }
        });
        this.binding.btnDialogRight.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.dialog.CommonDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m542xe0a2db02(view2);
            }
        });
        this.binding.clDialogLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.dialog.CommonDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m543x561d0143(view2);
            }
        });
        this.binding.cvDialog.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.dialog.CommonDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CommonDialog.lambda$onViewCreated$3(view2);
            }
        });
        if (!TextUtils.isEmpty(this.leftString)) {
            this.binding.tvDialogLeft.setText(this.leftString);
        }
        if (!TextUtils.isEmpty(this.rightString)) {
            this.binding.tvDialogRight.setText(this.rightString);
        }
        if (!TextUtils.isEmpty(this.title)) {
            this.binding.tvDialogTitle.setText(this.title);
        }
        if (!TextUtils.isEmpty(this.msg)) {
            this.binding.tvDialogMsg.setText(this.msg);
        }
        if (this.isDoubleBtn) {
            this.binding.btnDialogRight.setVisibility(0);
        } else {
            this.binding.btnDialogRight.setVisibility(8);
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-ui-search-dialog-CommonDialog, reason: not valid java name */
    /* synthetic */ void m541x6b28b4c1(View view) {
        OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onLeftClick();
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-media-ui-search-dialog-CommonDialog, reason: not valid java name */
    /* synthetic */ void m542xe0a2db02(View view) {
        OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onRightClick();
        }
        dismissAllowingStateLoss();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$2$com-wanos-media-ui-search-dialog-CommonDialog, reason: not valid java name */
    /* synthetic */ void m543x561d0143(View view) {
        OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onRightClick();
        }
        dismissAllowingStateLoss();
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.History;
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public void showDialog() {
        FragmentManager fragmentManager = this.fm;
        if (fragmentManager != null) {
            show(fragmentManager, "CommonDialog");
        }
    }

    public boolean isShowing() {
        return this.isShowing && getDialog() != null && getDialog().isShowing();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean getCancelable() {
        return this.cancelable;
    }

    public String getLeftString() {
        return this.leftString;
    }

    public void setLeftString(String leftString) {
        this.leftString = leftString;
    }

    public String getRightString() {
        return this.rightString;
    }

    public void setRightString(String rightString) {
        this.rightString = rightString;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isDoubleBtn() {
        return this.isDoubleBtn;
    }

    public void setDoubleBtn(boolean doubleBtn) {
        this.isDoubleBtn = doubleBtn;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public void setOnClickDialogListener(OnClickListener listener) {
        this.listener = listener;
    }
}
