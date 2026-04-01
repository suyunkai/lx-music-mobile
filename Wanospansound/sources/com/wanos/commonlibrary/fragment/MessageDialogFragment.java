package com.wanos.commonlibrary.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

/* JADX INFO: loaded from: classes3.dex */
public class MessageDialogFragment extends DialogFragment {
    private static final String BUNDLE_KEY_CANCELABLE = "BUNDLE_KEY_CANCELABLE";
    private static final String BUNDLE_KEY_MESSAGE = "BUNDLE_KEY_MESSAGE";
    private static final String BUNDLE_KEY_NEGATIVE_LABEL = "BUNDLE_KEY_NEGATIVE_LABEL";
    private static final String BUNDLE_KEY_POSITIVE_LABEL = "BUNDLE_KEY_POSITIVE_LABEL";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    private static final String KEY_REQUEST_CODE = "KEY_REQUEST_CODE";
    private Callback callback;
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() { // from class: com.wanos.commonlibrary.fragment.MessageDialogFragment.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            MessageDialogFragment.this.dismiss();
            if (MessageDialogFragment.this.callback != null) {
                MessageDialogFragment.this.callback.onSuccess(MessageDialogFragment.this.getRequestCode(), i);
            }
        }
    };

    public interface Callback {
        void onCancel(int i);

        void onSuccess(int i, int i2);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String string = arguments.getString(BUNDLE_KEY_TITLE);
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle(string);
        }
        String string2 = arguments.getString(BUNDLE_KEY_MESSAGE);
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage(string2);
        }
        String string3 = arguments.getString(BUNDLE_KEY_POSITIVE_LABEL);
        if (!TextUtils.isEmpty(string3)) {
            builder.setPositiveButton(string3, this.listener);
        }
        String string4 = arguments.getString(BUNDLE_KEY_NEGATIVE_LABEL);
        if (!TextUtils.isEmpty(string4)) {
            builder.setNegativeButton(string4, this.listener);
        }
        setCancelable(arguments.getBoolean(BUNDLE_KEY_CANCELABLE));
        return builder.create();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.callback = null;
        this.listener = null;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onCancel(getRequestCode());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRequestCode() {
        return getArguments().getInt(KEY_REQUEST_CODE);
    }

    public static final class Builder {
        private final AppCompatActivity activity;
        private String message;
        private String negativeLabel;
        private String positiveLabel;
        private String title;
        private boolean cancelable = true;
        private int requestCode = -1;
        private final String tag = "MessageDialog";

        public <T extends AppCompatActivity & Callback> Builder(T t) {
            this.activity = t;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder message(int i) {
            this.message = getContext().getString(i);
            return this;
        }

        public Builder cancelable(boolean z) {
            this.cancelable = z;
            return this;
        }

        public Builder positiveLabel(String str) {
            this.positiveLabel = str;
            return this;
        }

        public Builder positiveLabel(int i) {
            this.positiveLabel = getContext().getString(i);
            return this;
        }

        public Builder negativeLabel(String str) {
            this.negativeLabel = str;
            return this;
        }

        public Builder negativeLabel(int i) {
            this.negativeLabel = getContext().getString(i);
            return this;
        }

        public Builder requestCode(int i) {
            this.requestCode = i;
            return this;
        }

        public MessageDialogFragment build() {
            Bundle bundle = new Bundle();
            bundle.putString(MessageDialogFragment.BUNDLE_KEY_TITLE, this.title);
            bundle.putString(MessageDialogFragment.BUNDLE_KEY_MESSAGE, this.message);
            bundle.putString(MessageDialogFragment.BUNDLE_KEY_POSITIVE_LABEL, this.positiveLabel);
            bundle.putString(MessageDialogFragment.BUNDLE_KEY_NEGATIVE_LABEL, this.negativeLabel);
            bundle.putBoolean(MessageDialogFragment.BUNDLE_KEY_CANCELABLE, this.cancelable);
            MessageDialogFragment messageDialogFragment = new MessageDialogFragment();
            bundle.putInt(MessageDialogFragment.KEY_REQUEST_CODE, this.requestCode);
            messageDialogFragment.setArguments(bundle);
            return messageDialogFragment;
        }

        public MessageDialogFragment buildAndShow() {
            MessageDialogFragment messageDialogFragmentBuild = build();
            messageDialogFragmentBuild.show(this.activity.getSupportFragmentManager(), "MessageDialog");
            return messageDialogFragmentBuild;
        }

        private Context getContext() {
            return this.activity.getApplicationContext();
        }
    }
}
