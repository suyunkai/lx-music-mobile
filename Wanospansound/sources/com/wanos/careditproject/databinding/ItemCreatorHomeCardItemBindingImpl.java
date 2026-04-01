package com.wanos.careditproject.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.BR;
import com.wanos.careditproject.R;
import com.wanos.careditproject.ui.adapter.BindingAdapters;
import com.wanos.careditproject.utils.DateUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ItemCreatorHomeCardItemBindingImpl extends ItemCreatorHomeCardItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CardView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.project_content_ll, 6);
        sparseIntArray.put(R.id.cv_item, 7);
        sparseIntArray.put(R.id.btn_audio_book_play, 8);
        sparseIntArray.put(R.id.play_state, 9);
        sparseIntArray.put(R.id.pb_audiobook_play, 10);
        sparseIntArray.put(R.id.play_layout, 11);
        sparseIntArray.put(R.id.tv_play_time, 12);
        sparseIntArray.put(R.id.seekbar_progress, 13);
        sparseIntArray.put(R.id.tv_edit, 14);
        sparseIntArray.put(R.id.layout_collect, 15);
    }

    public ItemCreatorHomeCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private ItemCreatorHomeCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[8], (ImageView) objArr[5], (CardView) objArr[7], (ImageView) objArr[1], (CardView) objArr[15], (ProgressBar) objArr[10], (FrameLayout) objArr[11], (ImageView) objArr[9], (ConstraintLayout) objArr[6], (ProgressBar) objArr[13], (TextView) objArr[2], (TextView) objArr[3], (TextView) objArr[14], (TextView) objArr[12], (TextView) objArr[4]);
        this.mDirtyFlags = -1L;
        this.btnCollect.setTag(null);
        this.ivProjectItem.setTag(null);
        CardView cardView = (CardView) objArr[0];
        this.mboundView0 = cardView;
        cardView.setTag(null);
        this.tvDuration0.setTag(null);
        this.tvDuration1.setTag(null);
        this.tvProjectName.setTag(null);
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
        setData((ProjectInfo) obj);
        return true;
    }

    @Override // com.wanos.careditproject.databinding.ItemCreatorHomeCardItemBinding
    public void setData(ProjectInfo projectInfo) {
        this.mData = projectInfo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String strConvertFloatToMinSec;
        String picture;
        float duration;
        boolean zIsCollect;
        Context context;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ProjectInfo projectInfo = this.mData;
        long j2 = j & 3;
        Drawable drawable = null;
        String title = null;
        if (j2 != 0) {
            if (projectInfo != null) {
                title = projectInfo.getTitle();
                picture = projectInfo.getPicture();
                duration = projectInfo.getDuration();
                zIsCollect = projectInfo.isCollect();
            } else {
                duration = 0.0f;
                zIsCollect = false;
                picture = null;
            }
            if (j2 != 0) {
                j |= zIsCollect ? 8L : 4L;
            }
            strConvertFloatToMinSec = DateUtils.convertFloatToMinSec(duration);
            if (zIsCollect) {
                context = this.btnCollect.getContext();
                i = R.drawable.ic_creator_home_card_collected;
            } else {
                context = this.btnCollect.getContext();
                i = R.drawable.ic_creator_home_card_uncollect;
            }
            String str2 = title;
            drawable = AppCompatResources.getDrawable(context, i);
            str = str2;
        } else {
            str = null;
            strConvertFloatToMinSec = null;
            picture = null;
        }
        if ((j & 3) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btnCollect, drawable);
            BindingAdapters.setImageUrl(this.ivProjectItem, picture);
            TextViewBindingAdapter.setText(this.tvDuration0, strConvertFloatToMinSec);
            TextViewBindingAdapter.setText(this.tvDuration1, strConvertFloatToMinSec);
            TextViewBindingAdapter.setText(this.tvProjectName, str);
        }
    }
}
