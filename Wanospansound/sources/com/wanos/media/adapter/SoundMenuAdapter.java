package com.wanos.media.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.zero_p.databinding.ItemSoundMenuBinding;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SoundMenuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String NOTIFY_BACKGROUND = "state_select";
    public static final String NOTIFY_DOWNLOADING = "state_down";
    private final List<ThemeAudioInfoEntity> mData;
    private final SparseArray<State> mItemState = new SparseArray<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public SoundMenuAdapter(List<ThemeAudioInfoEntity> list, List<Long> list2) {
        for (int i = 0; i < list.size(); i++) {
            ThemeAudioInfoEntity themeAudioInfoEntity = list.get(i);
            this.mItemState.put(i, new State(list2.contains(Long.valueOf(themeAudioInfoEntity.getSoundId())), MusicCacheUtils.isCache(themeAudioInfoEntity.getWanosPath()), false));
        }
        this.mData = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(ItemSoundMenuBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        viewHolder.mViewBinding.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.media.adapter.SoundMenuAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition;
                if (SoundMenuAdapter.this.mOnItemClickListener == null || (bindingAdapterPosition = viewHolder.getBindingAdapterPosition()) >= SoundMenuAdapter.this.mData.size()) {
                    return;
                }
                SoundMenuAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition);
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ThemeAudioInfoEntity themeAudioInfoEntity = this.mData.get(i);
        State state = this.mItemState.get(i);
        GlideUtil.setImageData(themeAudioInfoEntity.getImagePath(), viewHolder.mViewBinding.ivSound, 80, 80);
        viewHolder.mViewBinding.tvSoundName.setText(themeAudioInfoEntity.getSoundName());
        viewHolder.mViewBinding.vBg.setSelected(state.isSelect);
        viewHolder.mViewBinding.ivVip.setVisibility(isVip(i) ? 0 : 4);
        viewHolder.mViewBinding.ivFileFlag.setVisibility(state.isCache() ? 4 : 0);
        viewHolder.mViewBinding.loading.setVisibility(state.isDownloadState() ? 0 : 8);
        viewHolder.mViewBinding.ivSound.setVisibility(state.isDownloadState() ? 4 : 0);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        super.onBindViewHolder(viewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            State state = this.mItemState.get(i);
            if (NOTIFY_BACKGROUND.equals(list.get(i2))) {
                viewHolder.mViewBinding.vBg.setSelected(state.isSelect);
            }
            if (NOTIFY_DOWNLOADING.equals(list.get(i2))) {
                viewHolder.mViewBinding.ivFileFlag.setVisibility(state.isCache() ? 4 : 0);
                viewHolder.mViewBinding.loading.setVisibility(state.isDownloadState() ? 0 : 8);
                viewHolder.mViewBinding.ivSound.setVisibility(state.isDownloadState() ? 4 : 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public void setItemDownLoadState(int i, boolean z) {
        State itemState = getItemState(i);
        itemState.setDownloadState(z);
        File file = new File(MusicCacheUtils.getMusicCachePath(getItemData(i).getWanosPath()));
        itemState.setCache(file.exists() && file.isFile());
        notifyItemChanged(i, NOTIFY_DOWNLOADING);
    }

    public void setItemSelectState(int i, boolean z) {
        getItemState(i).setSelect(z);
        notifyItemChanged(i, NOTIFY_BACKGROUND);
    }

    public boolean isVip(int i) {
        return getItemData(i).getIsVip() == 1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSoundMenuBinding mViewBinding;

        public ViewHolder(ItemSoundMenuBinding itemSoundMenuBinding) {
            super(itemSoundMenuBinding.getRoot());
            this.mViewBinding = itemSoundMenuBinding;
        }
    }

    public ThemeAudioInfoEntity getItemData(int i) {
        return this.mData.get(i);
    }

    public State getItemState(int i) {
        return this.mItemState.get(i);
    }

    public static class State {
        private boolean downloadState;
        private boolean isCache;
        private boolean isSelect;

        public State(boolean z, boolean z2, boolean z3) {
            this.isSelect = z;
            this.isCache = z2;
            this.downloadState = z3;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }

        public boolean isCache() {
            return this.isCache;
        }

        public void setCache(boolean z) {
            this.isCache = z;
        }

        public boolean isDownloadState() {
            return this.downloadState;
        }

        public void setDownloadState(boolean z) {
            this.downloadState = z;
        }
    }
}
