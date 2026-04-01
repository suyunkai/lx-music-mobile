package com.wanos.careditproject.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.wanos.careditproject.BR;
import com.wanos.careditproject.data.bean.ProjectTagBean;

/* JADX INFO: loaded from: classes3.dex */
public class ItemCreatorHomeTagItemBindingImpl extends ItemCreatorHomeTagItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public ItemCreatorHomeTagItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private ItemCreatorHomeTagItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CheckBox) objArr[1]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.tvTag.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (BR.data != i) {
            return false;
        }
        setData((ProjectTagBean) obj);
        return true;
    }

    @Override // com.wanos.careditproject.databinding.ItemCreatorHomeTagItemBinding
    public void setData(ProjectTagBean projectTagBean) {
        this.mData = projectTagBean;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ProjectTagBean projectTagBean = this.mData;
        long j2 = j & 3;
        boolean zIsSelected = false;
        String name = null;
        if (j2 != 0) {
            if (projectTagBean != null) {
                zIsSelected = projectTagBean.isSelected();
                name = projectTagBean.getName();
            }
            z = !zIsSelected;
        } else {
            z = false;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.tvTag, name);
            CompoundButtonBindingAdapter.setChecked(this.tvTag, zIsSelected);
            this.tvTag.setClickable(z);
        }
    }
}
