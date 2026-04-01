package com.wanos.media.ui.advertise.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.FragmentManager;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.media.R;
import com.wanos.media.base.BaseAdDialog;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.databinding.DialogAdBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AdvertiseDialog extends BaseAdDialog implements DialogInterface.OnKeyListener {
    private static final float DialogDimAmount = 0.0f;
    private DialogAdBinding binding;
    private DialogInterface.OnDismissListener disListener;
    private FragmentManager fm;
    private String imageUrl;
    private OnLeftClickListener listener;
    private Window window;
    private boolean cancelable = false;
    private boolean isShowing = false;

    public interface OnLeftClickListener {
        default void onCancel() {
        }

        default void onConfirm(Context context) {
        }
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    public static AdvertiseDialog newInstance() {
        BaseAdDialog.setInstance(new AdvertiseDialog());
        return (AdvertiseDialog) BaseAdDialog.getInstance();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
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
        this.binding = DialogAdBinding.inflate(inflater, container, false);
        if (this.imageUrl != null) {
            new GlideOptions().setPlaceholder(-1).setError(-1).setSize(1176, 668).setCallback(new GlideOptions.OnLoadListener() { // from class: com.wanos.media.ui.advertise.dialog.AdvertiseDialog.1
                @Override // com.wanos.commonlibrary.utils.GlideOptions.OnLoadListener
                public void onLoadError(String msg) {
                    AdvertiseDialog.this.dismissAllowingStateLoss();
                }

                @Override // com.wanos.commonlibrary.utils.GlideOptions.OnLoadListener
                public void onLoadSuccess(Bitmap bitmap) {
                    AdvertiseDialog.this.binding.ivCancel.setVisibility(0);
                }
            }).onLoad(this.imageUrl, this.binding.ivConfirm);
        }
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.ivConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.advertise.dialog.AdvertiseDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m489x9fbd745e(view2);
            }
        });
        this.binding.ivCancel.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.advertise.dialog.AdvertiseDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AdvertiseDialog.this.listener != null) {
                    AdvertiseDialog.this.listener.onCancel();
                    AdvertiseDialog.this.dismissAllowingStateLoss();
                    AdvertiseDialog.this.needShowNextDialog();
                }
            }
        });
        this.binding.clDialogLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.advertise.dialog.AdvertiseDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AdvertiseDialog.this.listener.onCancel();
                AdvertiseDialog.this.dismissAllowingStateLoss();
                AdvertiseDialog.this.needShowNextDialog();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-ui-advertise-dialog-AdvertiseDialog, reason: not valid java name */
    /* synthetic */ void m489x9fbd745e(View view) {
        OnLeftClickListener onLeftClickListener = this.listener;
        if (onLeftClickListener != null) {
            onLeftClickListener.onConfirm(getContext());
            dismissAllowingStateLoss();
            needShowNextDialog();
        }
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.AD;
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public void showDialog() {
        FragmentManager fragmentManager = this.fm;
        if (fragmentManager != null) {
            show(fragmentManager, "AdvertiseDialog");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void needShowNextDialog() {
        if (this.nextDialog != null) {
            this.nextDialog.showDialog();
        }
    }

    public void showDialogList(List<BaseAdDialog> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            if (i2 < list.size()) {
                list.get(i).setNextDialog(list.get(i2));
            }
            i = i2;
        }
    }

    public boolean isShowing() {
        return this.isShowing && getDialog() != null && getDialog().isShowing();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = this.disListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    public void setOnLeftClickListener(OnLeftClickListener listener) {
        this.listener = listener;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        this.disListener = listener;
    }
}
