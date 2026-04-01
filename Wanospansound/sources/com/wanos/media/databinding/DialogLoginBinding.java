package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogLoginBinding implements ViewBinding {
    public final TextView btLogin;
    public final TextView btLoginCancel;
    public final TextView btSmsCode;
    public final ImageView btnBack;
    public final CardView cardView;
    public final EditText etPhoneNumber;
    public final EditText etSmsCode;
    public final FrameLayout flContentView;
    public final FrameLayout flWeChatCode;
    public final ImageView ivWeChatCode;
    public final View ivWeChatCodeLoad;
    public final IncludeLoginDialogAgreementBinding llAgreement;
    public final LinearLayout llContent;
    public final LinearLayout llPhoneNumber;
    public final LinearLayout llScaleCode;
    private final FrameLayout rootView;
    public final TextView tvPhoneNumberLogin;
    public final TextView tvScaleLogin;

    private DialogLoginBinding(FrameLayout rootView, TextView btLogin, TextView btLoginCancel, TextView btSmsCode, ImageView btnBack, CardView cardView, EditText etPhoneNumber, EditText etSmsCode, FrameLayout flContentView, FrameLayout flWeChatCode, ImageView ivWeChatCode, View ivWeChatCodeLoad, IncludeLoginDialogAgreementBinding llAgreement, LinearLayout llContent, LinearLayout llPhoneNumber, LinearLayout llScaleCode, TextView tvPhoneNumberLogin, TextView tvScaleLogin) {
        this.rootView = rootView;
        this.btLogin = btLogin;
        this.btLoginCancel = btLoginCancel;
        this.btSmsCode = btSmsCode;
        this.btnBack = btnBack;
        this.cardView = cardView;
        this.etPhoneNumber = etPhoneNumber;
        this.etSmsCode = etSmsCode;
        this.flContentView = flContentView;
        this.flWeChatCode = flWeChatCode;
        this.ivWeChatCode = ivWeChatCode;
        this.ivWeChatCodeLoad = ivWeChatCodeLoad;
        this.llAgreement = llAgreement;
        this.llContent = llContent;
        this.llPhoneNumber = llPhoneNumber;
        this.llScaleCode = llScaleCode;
        this.tvPhoneNumberLogin = tvPhoneNumberLogin;
        this.tvScaleLogin = tvScaleLogin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_login, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogLoginBinding bind(View rootView) {
        int i = R.id.bt_login;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.bt_login);
        if (textView != null) {
            i = R.id.bt_login_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.bt_login_cancel);
            if (textView2 != null) {
                i = R.id.bt_sms_code;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.bt_sms_code);
                if (textView3 != null) {
                    i = R.id.btn_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
                    if (imageView != null) {
                        i = R.id.card_view;
                        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.card_view);
                        if (cardView != null) {
                            i = R.id.et_phone_number;
                            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_phone_number);
                            if (editText != null) {
                                i = R.id.et_sms_code;
                                EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_sms_code);
                                if (editText2 != null) {
                                    i = R.id.fl_content_view;
                                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_content_view);
                                    if (frameLayout != null) {
                                        i = R.id.fl_we_chat_code;
                                        FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_we_chat_code);
                                        if (frameLayout2 != null) {
                                            i = R.id.iv_we_chat_code;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_we_chat_code);
                                            if (imageView2 != null) {
                                                i = R.id.iv_we_chat_code_load;
                                                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.iv_we_chat_code_load);
                                                if (viewFindChildViewById != null) {
                                                    i = R.id.ll_agreement;
                                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.ll_agreement);
                                                    if (viewFindChildViewById2 != null) {
                                                        IncludeLoginDialogAgreementBinding includeLoginDialogAgreementBindingBind = IncludeLoginDialogAgreementBinding.bind(viewFindChildViewById2);
                                                        i = R.id.ll_content;
                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_content);
                                                        if (linearLayout != null) {
                                                            i = R.id.ll_phone_number;
                                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_phone_number);
                                                            if (linearLayout2 != null) {
                                                                i = R.id.ll_scale_code;
                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_scale_code);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.tv_phone_number_login;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_phone_number_login);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_scale_login;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_scale_login);
                                                                        if (textView5 != null) {
                                                                            return new DialogLoginBinding((FrameLayout) rootView, textView, textView2, textView3, imageView, cardView, editText, editText2, frameLayout, frameLayout2, imageView2, viewFindChildViewById, includeLoginDialogAgreementBindingBind, linearLayout, linearLayout2, linearLayout3, textView4, textView5);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
