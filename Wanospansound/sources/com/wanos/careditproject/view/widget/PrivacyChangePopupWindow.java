package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import androidx.core.view.GravityCompat;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class PrivacyChangePopupWindow {
    private OnButtonClickListener buttonClickListener;
    private Context context;
    boolean isPrivacy;
    private PopupWindow popupWindow;
    boolean showPrivacyButton;

    public interface OnButtonClickListener {
        void onDeleteClick();

        void onPrivacyClick();
    }

    public PrivacyChangePopupWindow(Context context, boolean z, boolean z2, ViewGroup viewGroup) {
        this.context = context;
        this.isPrivacy = z;
        this.showPrivacyButton = z2;
        initPopupWindow(viewGroup);
    }

    private void initPopupWindow(ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.pop_privacy_change, viewGroup, false);
        PopupWindow popupWindow = new PopupWindow(viewInflate, viewInflate.getLayoutParams().width, viewInflate.getLayoutParams().height, false);
        this.popupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setFocusable(true);
        Button button = (Button) viewInflate.findViewById(R.id.btn_privacy);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_delete);
        button.setVisibility(this.showPrivacyButton ? 0 : 8);
        if (this.isPrivacy) {
            button.setText(viewGroup.getResources().getString(R.string.create_mine_publish_change_status_to_me));
        } else {
            button.setText(viewGroup.getResources().getString(R.string.create_mine_publish_change_status_to_all));
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.widget.PrivacyChangePopupWindow$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m444xc2b997d(view);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.widget.PrivacyChangePopupWindow$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m445xffbb1dbe(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initPopupWindow$0$com-wanos-careditproject-view-widget-PrivacyChangePopupWindow, reason: not valid java name */
    /* synthetic */ void m444xc2b997d(View view) {
        OnButtonClickListener onButtonClickListener = this.buttonClickListener;
        if (onButtonClickListener != null) {
            onButtonClickListener.onDeleteClick();
        }
        this.popupWindow.dismiss();
    }

    /* JADX INFO: renamed from: lambda$initPopupWindow$1$com-wanos-careditproject-view-widget-PrivacyChangePopupWindow, reason: not valid java name */
    /* synthetic */ void m445xffbb1dbe(View view) {
        OnButtonClickListener onButtonClickListener = this.buttonClickListener;
        if (onButtonClickListener != null) {
            onButtonClickListener.onPrivacyClick();
        }
        this.popupWindow.dismiss();
    }

    public void show(View view) {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow == null || popupWindow.isShowing()) {
            return;
        }
        this.popupWindow.showAsDropDown(view, 20, 20, GravityCompat.END);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.buttonClickListener = onButtonClickListener;
    }

    public void dismiss() {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.popupWindow.dismiss();
    }
}
