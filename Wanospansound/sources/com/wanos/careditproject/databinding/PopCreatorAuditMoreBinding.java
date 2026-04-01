package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PopCreatorAuditMoreBinding extends ViewDataBinding {
    public final TextView textView;

    protected PopCreatorAuditMoreBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.textView = textView;
    }

    public static PopCreatorAuditMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PopCreatorAuditMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PopCreatorAuditMoreBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.pop_creator_audit_more, viewGroup, z, obj);
    }

    public static PopCreatorAuditMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PopCreatorAuditMoreBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PopCreatorAuditMoreBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.pop_creator_audit_more, null, false, obj);
    }

    public static PopCreatorAuditMoreBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PopCreatorAuditMoreBinding bind(View view, Object obj) {
        return (PopCreatorAuditMoreBinding) bind(obj, view, R.layout.pop_creator_audit_more);
    }
}
