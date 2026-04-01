package com.wanos.media.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.zero_p.R;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SpaceClockAlertSoundAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<BellsEntity.BellsInfoEntity> dataList;
    private final SparseBooleanArray mItemState = new SparseBooleanArray();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i, BellsEntity.BellsInfoEntity bellsInfoEntity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public void setBellsList(List<BellsEntity.BellsInfoEntity> list) {
        this.dataList = list;
        notifyItemRangeChanged(0, list.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_space_clock_item, viewGroup, false));
        viewHolder.itemView.setOnClickListener(new AnitClick(700L) { // from class: com.wanos.media.adapter.SpaceClockAlertSoundAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (bindingAdapterPosition < 0 || bindingAdapterPosition >= SpaceClockAlertSoundAdapter.this.dataList.size() || SpaceClockAlertSoundAdapter.this.mOnItemClickListener == null) {
                    return;
                }
                SpaceClockAlertSoundAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition, (BellsEntity.BellsInfoEntity) SpaceClockAlertSoundAdapter.this.dataList.get(bindingAdapterPosition));
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        BellsEntity.BellsInfoEntity bellsInfoEntity = this.dataList.get(i);
        if (-1 == bellsInfoEntity.getId()) {
            viewHolder.avatarIm.setImageResource(R.drawable.src_default_album);
        } else {
            GlideUtil.setImageData(bellsInfoEntity.getCoverImg(), viewHolder.avatarIm, 228, 148);
        }
        viewHolder.nameTv.setText(bellsInfoEntity.getName());
        viewHolder.loading.setVisibility(bellsInfoEntity.isDownloading() ? 0 : 4);
        viewHolder.ivDownloadFlag.setVisibility(bellsInfoEntity.isCache() ? 4 : 0);
        if (this.mItemState.get(i, false)) {
            viewHolder.maskView.setVisibility(0);
            viewHolder.checkIm.setVisibility(0);
        } else {
            viewHolder.maskView.setVisibility(8);
            viewHolder.checkIm.setVisibility(8);
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        BellsEntity.BellsInfoEntity bellsInfoEntity = this.dataList.get(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if ("belle_cache_state".equals(list.get(i2))) {
                viewHolder.loading.setVisibility(bellsInfoEntity.isDownloading() ? 0 : 4);
                if (bellsInfoEntity.isCache()) {
                    viewHolder.ivDownloadFlag.setVisibility(4);
                } else {
                    viewHolder.ivDownloadFlag.setVisibility(bellsInfoEntity.isDownloading() ? 4 : 0);
                }
            }
            if ("belle_select_state".equals(list.get(i2))) {
                if (this.mItemState.get(i, false)) {
                    viewHolder.maskView.setVisibility(0);
                    viewHolder.checkIm.setVisibility(0);
                } else {
                    viewHolder.maskView.setVisibility(8);
                    viewHolder.checkIm.setVisibility(8);
                }
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BellsEntity.BellsInfoEntity> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final ImageView checkIm;
        private final View ivDownloadFlag;
        private final View loading;
        private final View maskView;
        private final TextView nameTv;

        ViewHolder(View view) {
            super(view);
            this.nameTv = (TextView) view.findViewById(R.id.tv_space_name);
            this.avatarIm = (ImageView) view.findViewById(R.id.im_space_bg);
            this.maskView = view.findViewById(R.id.im_space_conver_bg);
            this.checkIm = (ImageView) view.findViewById(R.id.im_check_btn);
            this.loading = view.findViewById(R.id.loading);
            this.ivDownloadFlag = view.findViewById(R.id.iv_download_flag);
        }
    }

    public String getSelectAudioPath() {
        for (int i = 0; i < this.mItemState.size(); i++) {
            if (this.mItemState.valueAt(i)) {
                return MusicCacheUtils.getMusicCachePath(this.dataList.get(this.mItemState.keyAt(i)).getPath());
            }
        }
        return "";
    }

    public void setDownLoadState(int i, boolean z, boolean z2) {
        List<BellsEntity.BellsInfoEntity> list = this.dataList;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.dataList.get(i).setDownloading(z2);
        this.dataList.get(i).setCache(z);
        notifyItemChanged(i, "belle_cache_state");
    }

    public void setSelectIndex(int i) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.mItemState.size()) {
                break;
            }
            if (this.mItemState.valueAt(i2)) {
                SparseBooleanArray sparseBooleanArray = this.mItemState;
                sparseBooleanArray.put(sparseBooleanArray.keyAt(i2), false);
                notifyItemChanged(this.mItemState.keyAt(i2));
                break;
            }
            i2++;
        }
        this.mItemState.put(i, true);
        notifyItemChanged(i, "belle_select_state");
    }
}
