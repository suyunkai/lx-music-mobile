package com.wanos.media.widget.controller;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ItemZeroTimeMingXiangBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WanosZeroControlTimeSelectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "WanosZeroControlTimeSel";
    private final String[] mData;
    private final SparseBooleanArray mLoaddingState;
    private OnItemClickListener mOnItemClickListener;
    private final SparseBooleanArray mState;

    public interface IItemStateCallback {
        void onItemState(boolean z);
    }

    public interface OnItemClickListener {
        void onItemClick(int i, int i2, IItemStateCallback iItemStateCallback);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public WanosZeroControlTimeSelectAdapter(String[] strArr) {
        this.mData = strArr;
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(strArr.length);
        this.mState = sparseBooleanArray;
        this.mLoaddingState = new SparseBooleanArray(strArr.length);
        if (strArr.length == 1) {
            sparseBooleanArray.put(0, true);
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            if ("10".equals(this.mData[i])) {
                this.mState.put(i, true);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemZeroTimeMingXiangBinding itemZeroTimeMingXiangBindingInflate = ItemZeroTimeMingXiangBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemZeroTimeMingXiangBindingInflate);
        itemZeroTimeMingXiangBindingInflate.getRoot().setSoundEffectsEnabled(false);
        itemZeroTimeMingXiangBindingInflate.getRoot().setOnClickListener(new AnonymousClass1(viewHolder));
        return viewHolder;
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter$1, reason: invalid class name */
    class AnonymousClass1 extends CustomClick {
        final /* synthetic */ ViewHolder val$viewHolder;

        AnonymousClass1(ViewHolder viewHolder) {
            this.val$viewHolder = viewHolder;
        }

        @Override // com.wanos.media.util.CustomClick
        public void onAnitClick(View view) {
            final int bindingAdapterPosition;
            if (WanosZeroControlTimeSelectAdapter.this.mOnItemClickListener != null && (bindingAdapterPosition = this.val$viewHolder.getBindingAdapterPosition()) >= 0 && bindingAdapterPosition < WanosZeroControlTimeSelectAdapter.this.mData.length) {
                for (int i = 0; i < WanosZeroControlTimeSelectAdapter.this.mState.size(); i++) {
                    try {
                        if (WanosZeroControlTimeSelectAdapter.this.mState.valueAt(i) && WanosZeroControlTimeSelectAdapter.this.mState.keyAt(i) == bindingAdapterPosition) {
                            ZeroLogcatTools.d(WanosZeroControlTimeSelectAdapter.TAG, "重复点击已选中场景");
                            return;
                        }
                    } catch (Exception e) {
                        ZeroLogcatTools.e("WanosZeroControlTimeSelectAdapter", "onItemLongClick: " + e);
                        return;
                    }
                }
                for (int i2 = 0; i2 < WanosZeroControlTimeSelectAdapter.this.mLoaddingState.size(); i2++) {
                    if (WanosZeroControlTimeSelectAdapter.this.mLoaddingState.valueAt(i2)) {
                        ZeroLogcatTools.d(WanosZeroControlTimeSelectAdapter.TAG, "有资源正在加载");
                        return;
                    }
                }
                int i3 = Integer.parseInt(WanosZeroControlTimeSelectAdapter.this.mData[bindingAdapterPosition]);
                WanosZeroControlTimeSelectAdapter.this.mLoaddingState.put(bindingAdapterPosition, true);
                WanosZeroControlTimeSelectAdapter.this.notifyItemChanged(bindingAdapterPosition, "Loading_State");
                WanosZeroControlTimeSelectAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition, i3, new IItemStateCallback() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter$1$$ExternalSyntheticLambda2
                    @Override // com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter.IItemStateCallback
                    public final void onItemState(boolean z) {
                        this.f$0.m645x5b15ba22(bindingAdapterPosition, z);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onAnitClick$2$com-wanos-media-widget-controller-WanosZeroControlTimeSelectAdapter$1, reason: not valid java name */
        /* synthetic */ void m645x5b15ba22(final int i, boolean z) {
            ZeroLogcatTools.d(WanosZeroControlTimeSelectAdapter.TAG, "冥想主题切换状态 = " + z);
            ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m643x94be53a0(i);
                }
            }, 500L);
            if (z) {
                ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m644x77ea06e1(i);
                    }
                }, 500L);
            }
        }

        /* JADX INFO: renamed from: lambda$onAnitClick$0$com-wanos-media-widget-controller-WanosZeroControlTimeSelectAdapter$1, reason: not valid java name */
        /* synthetic */ void m643x94be53a0(int i) {
            for (int i2 = 0; i2 < WanosZeroControlTimeSelectAdapter.this.mLoaddingState.size(); i2++) {
                if (WanosZeroControlTimeSelectAdapter.this.mLoaddingState.valueAt(i2)) {
                    WanosZeroControlTimeSelectAdapter.this.mLoaddingState.put(WanosZeroControlTimeSelectAdapter.this.mLoaddingState.keyAt(i2), false);
                    WanosZeroControlTimeSelectAdapter.this.notifyItemChanged(i, "Loading_State");
                    return;
                }
            }
        }

        /* JADX INFO: renamed from: lambda$onAnitClick$1$com-wanos-media-widget-controller-WanosZeroControlTimeSelectAdapter$1, reason: not valid java name */
        /* synthetic */ void m644x77ea06e1(int i) {
            for (int i2 = 0; i2 < WanosZeroControlTimeSelectAdapter.this.mState.size(); i2++) {
                if (WanosZeroControlTimeSelectAdapter.this.mState.valueAt(i2)) {
                    WanosZeroControlTimeSelectAdapter.this.mState.put(i2, false);
                    WanosZeroControlTimeSelectAdapter.this.notifyItemChanged(i2, "Select_State");
                }
            }
            WanosZeroControlTimeSelectAdapter.this.mState.put(i, true);
            WanosZeroControlTimeSelectAdapter.this.notifyItemChanged(i, "Select_State");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mViewBinding.tvTimeTitle.setText(getTitleText(i));
        viewHolder.mViewBinding.tvTimeSize.setText(getTitleTimeText(i));
        if (this.mState.get(i)) {
            viewHolder.mViewBinding.ivBackgroundImg.setBackgroundResource(R.drawable.icon_ming_xiang_sel);
        } else {
            viewHolder.mViewBinding.ivBackgroundImg.setBackground(null);
        }
        boolean z = this.mLoaddingState.get(i, false);
        viewHolder.mViewBinding.loadingBar.setVisibility(z ? 0 : 8);
        viewHolder.mViewBinding.tvTimeSize.setVisibility(z ? 8 : 0);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        super.onBindViewHolder(viewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if ("Select_State".equals(list.get(i2))) {
                if (this.mState.get(i)) {
                    viewHolder.mViewBinding.ivBackgroundImg.setBackgroundResource(R.drawable.icon_ming_xiang_sel);
                } else {
                    viewHolder.mViewBinding.ivBackgroundImg.setBackground(null);
                }
            }
            if ("Loading_State".equals(list.get(i2))) {
                boolean z = this.mLoaddingState.get(i, false);
                viewHolder.mViewBinding.loadingBar.setVisibility(z ? 0 : 8);
                viewHolder.mViewBinding.tvTimeSize.setVisibility(z ? 8 : 0);
            }
        }
    }

    public void setDefaultPosition(int i) {
        String[] strArr = this.mData;
        if (strArr == null) {
            ZeroLogcatTools.e(TAG, "setDefaultPosition: mData == null");
            return;
        }
        if (i < 0 || i >= strArr.length) {
            ZeroLogcatTools.e(TAG, "setDefaultPosition: mData.length = " + this.mData.length + ", position = " + i);
            return;
        }
        for (int i2 = 0; i2 < this.mState.size(); i2++) {
            if (this.mState.valueAt(i2)) {
                this.mState.put(i2, false);
                notifyItemChanged(i2, "Select_State");
            }
        }
        this.mState.put(i, true);
        notifyItemChanged(i, "Select_State");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemZeroTimeMingXiangBinding mViewBinding;

        public ViewHolder(ItemZeroTimeMingXiangBinding itemZeroTimeMingXiangBinding) {
            super(itemZeroTimeMingXiangBinding.getRoot());
            this.mViewBinding = itemZeroTimeMingXiangBinding;
        }
    }

    void onItemLongClick(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    boolean getItemLoadState() {
        for (int i = 0; i < this.mLoaddingState.size(); i++) {
            if (this.mLoaddingState.valueAt(i)) {
                return true;
            }
        }
        return false;
    }

    int getSelectTime() {
        for (int i = 0; i < this.mState.size(); i++) {
            try {
                if (this.mState.valueAt(i)) {
                    return Integer.parseInt(this.mData[this.mState.keyAt(i)]);
                }
            } catch (Exception unused) {
            }
            return 5;
        }
        return 5;
    }

    private String getTitleText(int i) {
        if ("5".equals(this.mData[i])) {
            return StringUtils.getString(R.string.meditation_fast);
        }
        if ("10".equals(this.mData[i])) {
            return StringUtils.getString(R.string.meditation_comfort);
        }
        if ("15".equals(this.mData[i])) {
            return StringUtils.getString(R.string.meditation_depth);
        }
        return StringUtils.getString(R.string.zero_table_ming_xiang);
    }

    private String getTitleTimeText(int i) {
        return this.mData[i] + "min";
    }
}
