package com.wanos.careditproject.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LiveData;
import com.wanos.bean.AutherInfo;
import com.wanos.bean.ProjectInfo;
import com.wanos.bean.WorkType;
import com.wanos.careditproject.BR;
import com.wanos.careditproject.R;
import com.wanos.careditproject.ui.adapter.BindingAdapters;
import com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel;
import com.wanos.careditproject.ui.viewmodel.CreatorWorkTypeViewModel;
import com.wanos.careditproject.utils.DateUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ItemCreatorMineWorkBindingImpl extends ItemCreatorMineWorkBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl1 mDataClickAuditMoreAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mDataClickMoreAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mDataClickToPlayAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mDataOnClickCollectionAndroidViewViewOnClickListener;
    private OnClickListenerImpl mDataOnClickCreateSameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final ImageView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final TextView mboundView4;
    private final LinearLayout mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_user, 17);
        sparseIntArray.put(R.id.layout_avatar, 18);
        sparseIntArray.put(R.id.layout_image, 19);
        sparseIntArray.put(R.id.layout_title, 20);
    }

    public ItemCreatorMineWorkBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 21, sIncludes, sViewsWithIds));
    }

    private ItemCreatorMineWorkBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[1], (CardView) objArr[18], (CardView) objArr[19], (LinearLayout) objArr[20], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[9], (TextView) objArr[3], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.ivUser.setTag(null);
        this.layoutWork.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) objArr[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) objArr[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) objArr[13];
        this.mboundView13 = textView3;
        textView3.setTag(null);
        ImageView imageView2 = (ImageView) objArr[14];
        this.mboundView14 = imageView2;
        imageView2.setTag(null);
        TextView textView4 = (TextView) objArr[15];
        this.mboundView15 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) objArr[16];
        this.mboundView16 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) objArr[4];
        this.mboundView4 = textView6;
        textView6.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[5];
        this.mboundView5 = linearLayout;
        linearLayout.setTag(null);
        TextView textView7 = (TextView) objArr[6];
        this.mboundView6 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) objArr[7];
        this.mboundView7 = textView8;
        textView8.setTag(null);
        ImageView imageView3 = (ImageView) objArr[8];
        this.mboundView8 = imageView3;
        imageView3.setTag(null);
        this.tvUserDesc.setTag(null);
        this.tvUserName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        setData((CreatorMineWorkItemViewModel) obj);
        return true;
    }

    @Override // com.wanos.careditproject.databinding.ItemCreatorMineWorkBinding
    public void setData(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
        this.mData = creatorMineWorkItemViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeDataInfo((LiveData) obj, i2);
    }

    private boolean onChangeDataInfo(LiveData<ProjectInfo> liveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        long j2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        String str;
        String str2;
        String str3;
        Drawable drawable;
        String str4;
        String str5;
        String strConvertLongToDate;
        String str6;
        String str7;
        OnClickListenerImpl3 onClickListenerImpl3;
        OnClickListenerImpl value;
        String str8;
        OnClickListenerImpl2 onClickListenerImpl2;
        Drawable drawable2;
        String str9;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl4 onClickListenerImpl4;
        OnClickListenerImpl onClickListenerImpl;
        Drawable auditStatusBg;
        int i9;
        OnClickListenerImpl3 value2;
        OnClickListenerImpl4 value3;
        int i10;
        int auditStatusColor;
        String auditStatusText;
        OnClickListenerImpl2 value4;
        String openStatusText;
        OnClickListenerImpl1 value5;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        Drawable drawable3;
        int collectCount;
        AutherInfo autherInfo;
        WorkType workType;
        long j3;
        String str10;
        String str11;
        String title;
        long createTime;
        long j4;
        Drawable drawable4;
        String avatar;
        String str12;
        CreatorWorkTypeViewModel creatorWorkTypeViewModel;
        boolean zIsShowOpenStatus;
        boolean zIsShowWorkTag;
        boolean zIsShowAuditStatus;
        boolean zIsShowAuditStatusMore;
        boolean zIsShowCreateSame;
        boolean zIsShowCollection;
        boolean zIsShowMore;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CreatorMineWorkItemViewModel creatorMineWorkItemViewModel = this.mData;
        if ((j & 7) != 0) {
            long j5 = j & 6;
            if (j5 != 0) {
                if (creatorMineWorkItemViewModel != null) {
                    creatorWorkTypeViewModel = creatorMineWorkItemViewModel.workType;
                    OnClickListenerImpl onClickListenerImpl5 = this.mDataOnClickCreateSameAndroidViewViewOnClickListener;
                    if (onClickListenerImpl5 == null) {
                        onClickListenerImpl5 = new OnClickListenerImpl();
                        this.mDataOnClickCreateSameAndroidViewViewOnClickListener = onClickListenerImpl5;
                    }
                    value = onClickListenerImpl5.setValue(creatorMineWorkItemViewModel);
                    OnClickListenerImpl1 onClickListenerImpl12 = this.mDataClickAuditMoreAndroidViewViewOnClickListener;
                    if (onClickListenerImpl12 == null) {
                        onClickListenerImpl12 = new OnClickListenerImpl1();
                        this.mDataClickAuditMoreAndroidViewViewOnClickListener = onClickListenerImpl12;
                    }
                    value5 = onClickListenerImpl12.setValue(creatorMineWorkItemViewModel);
                    OnClickListenerImpl2 onClickListenerImpl22 = this.mDataOnClickCollectionAndroidViewViewOnClickListener;
                    if (onClickListenerImpl22 == null) {
                        onClickListenerImpl22 = new OnClickListenerImpl2();
                        this.mDataOnClickCollectionAndroidViewViewOnClickListener = onClickListenerImpl22;
                    }
                    value4 = onClickListenerImpl22.setValue(creatorMineWorkItemViewModel);
                    OnClickListenerImpl3 onClickListenerImpl32 = this.mDataClickToPlayAndroidViewViewOnClickListener;
                    if (onClickListenerImpl32 == null) {
                        onClickListenerImpl32 = new OnClickListenerImpl3();
                        this.mDataClickToPlayAndroidViewViewOnClickListener = onClickListenerImpl32;
                    }
                    value2 = onClickListenerImpl32.setValue(creatorMineWorkItemViewModel);
                    OnClickListenerImpl4 onClickListenerImpl42 = this.mDataClickMoreAndroidViewViewOnClickListener;
                    if (onClickListenerImpl42 == null) {
                        onClickListenerImpl42 = new OnClickListenerImpl4();
                        this.mDataClickMoreAndroidViewViewOnClickListener = onClickListenerImpl42;
                    }
                    value3 = onClickListenerImpl42.setValue(creatorMineWorkItemViewModel);
                } else {
                    value2 = null;
                    value3 = null;
                    value4 = null;
                    creatorWorkTypeViewModel = null;
                    value = null;
                    value5 = null;
                }
                if (creatorWorkTypeViewModel != null) {
                    zIsShowAuditStatus = creatorWorkTypeViewModel.isShowAuditStatus();
                    openStatusText = creatorWorkTypeViewModel.getOpenStatusText(getRoot().getContext());
                    auditStatusBg = creatorWorkTypeViewModel.getAuditStatusBg(getRoot().getContext());
                    zIsShowOpenStatus = creatorWorkTypeViewModel.isShowOpenStatus();
                    zIsShowAuditStatusMore = creatorWorkTypeViewModel.isShowAuditStatusMore();
                    zIsShowCreateSame = creatorWorkTypeViewModel.isShowCreateSame();
                    auditStatusColor = creatorWorkTypeViewModel.getAuditStatusColor(getRoot().getContext());
                    auditStatusText = creatorWorkTypeViewModel.getAuditStatusText(getRoot().getContext());
                    zIsShowCollection = creatorWorkTypeViewModel.isShowCollection();
                    zIsShowMore = creatorWorkTypeViewModel.isShowMore();
                    zIsShowWorkTag = creatorWorkTypeViewModel.isShowWorkTag();
                } else {
                    auditStatusBg = null;
                    zIsShowOpenStatus = false;
                    auditStatusColor = 0;
                    auditStatusText = null;
                    openStatusText = null;
                    zIsShowWorkTag = false;
                    zIsShowAuditStatus = false;
                    zIsShowAuditStatusMore = false;
                    zIsShowCreateSame = false;
                    zIsShowCollection = false;
                    zIsShowMore = false;
                }
                if (j5 != 0) {
                    j |= zIsShowAuditStatus ? 65536L : 32768L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowOpenStatus ? 64L : 32L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowAuditStatusMore ? 16384L : 8192L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowCreateSame ? 1024L : 512L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowCollection ? PlaybackStateCompat.ACTION_SET_REPEAT_MODE : 131072L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowMore ? 16L : 8L;
                }
                if ((j & 6) != 0) {
                    j |= zIsShowWorkTag ? 256L : 128L;
                }
                i10 = 8;
                i11 = zIsShowAuditStatus ? 0 : 8;
                i9 = zIsShowOpenStatus ? 0 : 8;
                i12 = zIsShowAuditStatusMore ? 0 : 8;
                i13 = zIsShowCreateSame ? 0 : 8;
                i14 = zIsShowCollection ? 0 : 8;
                i15 = zIsShowMore ? 0 : 8;
                if (zIsShowWorkTag) {
                    i10 = 0;
                }
            } else {
                auditStatusBg = null;
                i9 = 0;
                value2 = null;
                value3 = null;
                i10 = 0;
                auditStatusColor = 0;
                auditStatusText = null;
                value4 = null;
                openStatusText = null;
                value = null;
                value5 = null;
                i11 = 0;
                i12 = 0;
                i13 = 0;
                i14 = 0;
                i15 = 0;
            }
            LiveData<ProjectInfo> info = creatorMineWorkItemViewModel != null ? creatorMineWorkItemViewModel.getInfo() : null;
            boolean z = false;
            updateLiveDataRegistration(0, info);
            ProjectInfo value6 = info != null ? info.getValue() : null;
            if (value6 != null) {
                String content = value6.getContent();
                collectCount = value6.getCollectCount();
                String picture = value6.getPicture();
                boolean zIsCollect = value6.isCollect();
                title = value6.getTitle();
                WorkType workType2 = value6.getWorkType();
                createTime = value6.getCreateTime();
                autherInfo = value6.getAutherInfo();
                j3 = 7;
                drawable3 = auditStatusBg;
                workType = workType2;
                str10 = content;
                z = zIsCollect;
                str11 = picture;
            } else {
                drawable3 = auditStatusBg;
                collectCount = 0;
                autherInfo = null;
                workType = null;
                j3 = 7;
                str10 = null;
                str11 = null;
                title = null;
                createTime = 0;
            }
            if ((j & j3) != 0) {
                j |= z ? 4096L : 2048L;
            }
            String strValueOf = String.valueOf(collectCount);
            if (z) {
                j4 = j;
                drawable4 = AppCompatResources.getDrawable(this.mboundView14.getContext(), R.drawable.ic_creator_home_card_collected);
            } else {
                j4 = j;
                drawable4 = AppCompatResources.getDrawable(this.mboundView14.getContext(), R.drawable.ic_creator_home_card_uncollect);
            }
            long j6 = createTime * 1000;
            String str13 = workType != null ? workType.typeName : null;
            if (autherInfo != null) {
                String userName = autherInfo.getUserName();
                avatar = autherInfo.getAvatar();
                str12 = userName;
            } else {
                avatar = null;
                str12 = null;
            }
            i4 = i9;
            onClickListenerImpl4 = value3;
            i6 = auditStatusColor;
            str9 = auditStatusText;
            onClickListenerImpl2 = value4;
            onClickListenerImpl1 = value5;
            str6 = str12;
            i5 = i11;
            i7 = i12;
            str4 = strValueOf;
            i = i13;
            i3 = i14;
            i8 = i15;
            drawable2 = drawable3;
            j2 = 7;
            str7 = avatar;
            str3 = str13;
            strConvertLongToDate = DateUtils.convertLongToDate(j6);
            i2 = i10;
            str8 = openStatusText;
            str5 = str10;
            str = str11;
            str2 = title;
            onClickListenerImpl3 = value2;
            drawable = drawable4;
            j = j4;
        } else {
            i = 0;
            j2 = 7;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
            str = null;
            str2 = null;
            str3 = null;
            drawable = null;
            str4 = null;
            str5 = null;
            strConvertLongToDate = null;
            str6 = null;
            str7 = null;
            onClickListenerImpl3 = null;
            value = null;
            str8 = null;
            onClickListenerImpl2 = null;
            drawable2 = null;
            str9 = null;
            onClickListenerImpl1 = null;
            onClickListenerImpl4 = null;
        }
        if ((j & j2) != 0) {
            onClickListenerImpl = value;
            BindingAdapters.setImageUrl(this.ivUser, str7);
            BindingAdapters.setImageUrl(this.mboundView10, str);
            TextViewBindingAdapter.setText(this.mboundView11, str2);
            TextViewBindingAdapter.setText(this.mboundView12, str3);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView14, drawable);
            TextViewBindingAdapter.setText(this.mboundView15, str4);
            TextViewBindingAdapter.setText(this.mboundView16, str5);
            TextViewBindingAdapter.setText(this.tvUserDesc, strConvertLongToDate);
            TextViewBindingAdapter.setText(this.tvUserName, str6);
        } else {
            onClickListenerImpl = value;
        }
        if ((j & 6) != 0) {
            this.layoutWork.setOnClickListener(onClickListenerImpl3);
            this.mboundView12.setVisibility(i2);
            this.mboundView13.setVisibility(i);
            this.mboundView13.setOnClickListener(onClickListenerImpl);
            this.mboundView14.setOnClickListener(onClickListenerImpl2);
            int i16 = i3;
            this.mboundView14.setVisibility(i16);
            this.mboundView15.setVisibility(i16);
            this.mboundView4.setVisibility(i4);
            TextViewBindingAdapter.setText(this.mboundView4, str8);
            this.mboundView5.setVisibility(i5);
            ViewBindingAdapter.setBackground(this.mboundView5, drawable2);
            this.mboundView6.setTextColor(i6);
            TextViewBindingAdapter.setText(this.mboundView6, str9);
            this.mboundView7.setOnClickListener(onClickListenerImpl1);
            this.mboundView7.setVisibility(i7);
            this.mboundView8.setVisibility(i8);
            this.mboundView8.setOnClickListener(onClickListenerImpl4);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private CreatorMineWorkItemViewModel value;

        public OnClickListenerImpl setValue(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
            this.value = creatorMineWorkItemViewModel;
            if (creatorMineWorkItemViewModel == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onClickCreateSame(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private CreatorMineWorkItemViewModel value;

        public OnClickListenerImpl1 setValue(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
            this.value = creatorMineWorkItemViewModel;
            if (creatorMineWorkItemViewModel == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.clickAuditMore(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private CreatorMineWorkItemViewModel value;

        public OnClickListenerImpl2 setValue(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
            this.value = creatorMineWorkItemViewModel;
            if (creatorMineWorkItemViewModel == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onClickCollection(view);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private CreatorMineWorkItemViewModel value;

        public OnClickListenerImpl3 setValue(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
            this.value = creatorMineWorkItemViewModel;
            if (creatorMineWorkItemViewModel == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.clickToPlay(view);
        }
    }

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private CreatorMineWorkItemViewModel value;

        public OnClickListenerImpl4 setValue(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel) {
            this.value = creatorMineWorkItemViewModel;
            if (creatorMineWorkItemViewModel == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.clickMore(view);
        }
    }
}
