package com.wanos.media.ui.login.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.BaseAdDialog;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.databinding.FragmentCodeDialogBinding;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class CodeDialog extends BaseAdDialog implements DialogInterface.OnKeyListener {
    private static final float DialogDimAmount = 0.0f;
    private FragmentCodeDialogBinding binding;
    private FragmentManager fm;
    private InputMethodManager imm;
    private int layoutHeight;
    private String leftString;
    private OnClickListener listener;
    private String msg;
    private String rightString;
    private String title;
    private Window window;
    private boolean cancelable = false;
    private boolean isDoubleBtn = true;
    private final boolean isShowSoft = false;

    public interface OnClickListener {
        void onCloseClick();

        void onLeftClick(String content);

        void onProtocolClick(Context context);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    public static CodeDialog newInstance() {
        BaseAdDialog.setInstance(new CodeDialog());
        return (CodeDialog) BaseAdDialog.getInstance();
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
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(1);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setOnKeyListener(this);
        this.binding = FragmentCodeDialogBinding.inflate(inflater, container, false);
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        Util.setTextWeight(this.binding.tvDialogTitle, 500);
        initProtocol();
        initVisibleIsSpoken();
        autoScrollView(this.binding.tvDialogProtocol);
        return this.binding.getRoot();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.tvDialogProtocol.setContentDescription(getResources().getString(R.string.open_four_protocol));
            this.binding.ivDialogBack.setContentDescription(getResources().getString(R.string.close_back_click));
            ViewCompat.setAccessibilityDelegate(this.binding.tvDialogProtocol, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.login.dialog.CodeDialog.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (CodeDialog.this.listener != null) {
                        CodeDialog.this.listener.onProtocolClick(CodeDialog.this.getContext());
                    }
                    return super.performAccessibilityAction(host, action, args);
                }
            });
        }
    }

    private void initProtocol() {
        String string = getString(R.string.p_redeem_protocol);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ClickableSpan() { // from class: com.wanos.media.ui.login.dialog.CodeDialog.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (CodeDialog.this.listener != null) {
                    CodeDialog.this.listener.onProtocolClick(CodeDialog.this.getContext());
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 5, string.length(), 18);
        spannableString.setSpan(new ForegroundColorSpan(getActivity().getColor(R.color.member_pay_agreement_text_color)), 5, string.length(), 18);
        this.binding.tvDialogProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        this.binding.tvDialogProtocol.setHighlightColor(getActivity().getColor(android.R.color.transparent));
        this.binding.tvDialogProtocol.setText(spannableString);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.etDialogView.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.m514xf1774be8(textView, i, keyEvent);
            }
        });
        this.binding.btnDialogLeft.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m515xb463b547(view2);
            }
        });
        this.binding.btnDialogRight.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m516x77501ea6(view2);
            }
        });
        this.binding.ivDialogBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m517x3a3c8805(view2);
            }
        });
        this.binding.clDialogLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m518xfd28f164(view2);
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
        if (this.isDoubleBtn) {
            this.binding.btnDialogRight.setVisibility(0);
        } else {
            this.binding.btnDialogRight.setVisibility(8);
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ boolean m514xf1774be8(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 0 && i != 6) {
            return false;
        }
        String strTrim = this.binding.etDialogView.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showMsg(R.string.p_redeem_code_empty_text);
            return true;
        }
        Pattern patternCompile = Pattern.compile("^[a-zA-Z0-9]+$");
        if (strTrim.length() != 16) {
            ToastUtil.showMsg(R.string.redeem_code_input_no_length_text);
            return true;
        }
        if (!strTrim.matches(patternCompile.pattern())) {
            ToastUtil.showMsg(R.string.redeem_code_input_error_text);
            return true;
        }
        OnClickListener onClickListener = this.listener;
        if (onClickListener == null) {
            return true;
        }
        onClickListener.onLeftClick(strTrim);
        return true;
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ void m515xb463b547(View view) {
        if (this.listener != null) {
            String string = this.binding.etDialogView.getText().toString();
            if (TextUtils.isEmpty(string.trim())) {
                ToastUtil.showMsg(getString(R.string.p_redeem_code_empty_text));
                return;
            }
            Pattern patternCompile = Pattern.compile("^[a-zA-Z0-9]+$");
            if (string.length() != 16) {
                ToastUtil.showMsg(R.string.redeem_code_input_no_length_text);
                return;
            }
            if (!string.matches(patternCompile.pattern())) {
                ToastUtil.showMsg(R.string.redeem_code_input_error_text);
                return;
            }
            OnClickListener onClickListener = this.listener;
            if (onClickListener != null) {
                onClickListener.onLeftClick(string);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$2$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ void m516x77501ea6(View view) {
        if (view != this.binding.btnDialogRight || this.listener == null) {
            return;
        }
        hideSoftInput();
        dismissAllowingStateLoss();
        this.listener.onCloseClick();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$3$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ void m517x3a3c8805(View view) {
        if (view != this.binding.ivDialogBack || this.listener == null) {
            return;
        }
        hideSoftInput();
        dismissAllowingStateLoss();
        this.listener.onCloseClick();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$4$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ void m518xfd28f164(View view) {
        if (view == this.binding.clDialogLayout) {
            hideSoftInput();
        }
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.Code;
    }

    @Override // com.wanos.media.base.BaseAdDialog
    public void showDialog() {
        FragmentManager fragmentManager = this.fm;
        if (fragmentManager != null) {
            show(fragmentManager, "CodeDialog");
        }
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

    public void autoScrollView(final TextView scrollToView) {
        final View viewFindViewById = getDialog().getWindow().getDecorView().findViewById(android.R.id.content);
        getDialog().getWindow().setSoftInputMode(16);
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.wanos.media.ui.login.dialog.CodeDialog$$ExternalSyntheticLambda5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.m513x2e65d28c(viewFindViewById, scrollToView);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$autoScrollView$5$com-wanos-media-ui-login-dialog-CodeDialog, reason: not valid java name */
    /* synthetic */ void m513x2e65d28c(View view, TextView textView) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        if (view.getRootView().getHeight() - rect.bottom > Util.dip2px(getContext(), 150.0f)) {
            int[] iArr = new int[2];
            textView.getLocationInWindow(iArr);
            this.layoutHeight += ((iArr[1] + textView.getHeight()) + Util.dip2px(getContext(), 20.0f)) - rect.bottom;
        } else {
            this.layoutHeight = 0;
        }
        if (this.layoutHeight < 0) {
            this.layoutHeight = 0;
        }
        view.scrollTo(0, this.layoutHeight);
    }

    public void hideSoftInput() {
        if (this.binding.etDialogView.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(), 2);
            this.binding.etDialogView.clearFocus();
        }
    }

    public void setOnClickDialogListener(OnClickListener listener) {
        this.listener = listener;
    }
}
