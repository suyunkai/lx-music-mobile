package com.wanos.media.widget.video;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.ImageLoadTools;
import com.wanos.media.zero_p.databinding.ItemZeroThemBinding;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LooperPageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String NOTIFY_ITEM_VISIBILITY = "state_visibility";
    private static final int mIncreaseCount = 2;
    private Context context;
    private final List<ZeroThemeInfo> mData;
    private final SparseBooleanArray mVideoState = new SparseBooleanArray();
    private final ImageLoadTools.Builder mImageLoadBuilder = new ImageLoadTools.Builder().setWidth(960).setHeight(540).setCovertDimens(false);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public LooperPageAdapter(List<ZeroThemeInfo> list) {
        this.mData = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        this.context = context;
        return new ViewHolder(ItemZeroThemBinding.inflate(LayoutInflater.from(context), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ZeroThemeInfo itemData = getItemData(i);
        viewHolder.mViewBinding.ivCove.setVisibility(this.mVideoState.get(i, true) ? 0 : 4);
        this.mImageLoadBuilder.onLoad(this.context, itemData.getThemeBgImage(), viewHolder.mViewBinding.ivCove);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        super.onBindViewHolder(viewHolder, i, list);
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            if (NOTIFY_ITEM_VISIBILITY.equals(it.next())) {
                viewHolder.mViewBinding.ivCove.setVisibility(this.mVideoState.get(i, true) ? 0 : 4);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (getRealCount() <= 1) {
            return getRealCount();
        }
        return getRealCount() + 2;
    }

    public void setCoveState(int i, boolean z) {
        if (this.mVideoState.get(i, true) == z) {
            return;
        }
        this.mVideoState.put(i, z);
        notifyItemChanged(i, NOTIFY_ITEM_VISIBILITY);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemZeroThemBinding mViewBinding;

        public ViewHolder(ItemZeroThemBinding itemZeroThemBinding) {
            super(itemZeroThemBinding.getRoot());
            this.mViewBinding = itemZeroThemBinding;
        }
    }

    public int getRealCount() {
        List<ZeroThemeInfo> list = this.mData;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public ZeroThemeInfo getItemData(int i) {
        return this.mData.get(getRealPosition(i));
    }

    public int getRealPosition(int i) {
        if (getRealCount() <= 1) {
            return 0;
        }
        if (i == 0) {
            i = getRealCount();
        } else if (i == getRealCount() + 1) {
            return 0;
        }
        return i - 1;
    }
}
