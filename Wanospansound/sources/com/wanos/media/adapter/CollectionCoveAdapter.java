package com.wanos.media.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.SoundCollectionEntity;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.util.AnitClick;
import com.wanos.media.zero_p.databinding.ItemSoundCollectBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionCoveAdapter extends RecyclerView.Adapter<CoveViewHolder> {
    private static final String NOTIFY_STATE = "notify_state";
    private List<SoundCollectionEntity> data;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((CoveViewHolder) viewHolder, i, (List<Object>) list);
    }

    public CollectionCoveAdapter(List<SoundCollectionEntity> list) {
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CoveViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final CoveViewHolder coveViewHolder = new CoveViewHolder(ItemSoundCollectBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        coveViewHolder.mViewBinding.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.media.adapter.CollectionCoveAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = coveViewHolder.getBindingAdapterPosition();
                if (bindingAdapterPosition >= CollectionCoveAdapter.this.getItemCount()) {
                    return;
                }
                for (int i2 = 0; i2 < CollectionCoveAdapter.this.data.size(); i2++) {
                    SoundCollectionEntity soundCollectionEntity = (SoundCollectionEntity) CollectionCoveAdapter.this.data.get(i2);
                    if (soundCollectionEntity.isSelect()) {
                        soundCollectionEntity.setSelect(false);
                        CollectionCoveAdapter.this.notifyItemChanged(i2, CollectionCoveAdapter.NOTIFY_STATE);
                    }
                }
                ((SoundCollectionEntity) CollectionCoveAdapter.this.data.get(bindingAdapterPosition)).setSelect(true);
                CollectionCoveAdapter.this.notifyItemChanged(bindingAdapterPosition, CollectionCoveAdapter.NOTIFY_STATE);
                if (CollectionCoveAdapter.this.mOnItemClickListener != null) {
                    CollectionCoveAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition);
                }
            }
        });
        return coveViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CoveViewHolder coveViewHolder, int i) {
        SoundCollectionEntity soundCollectionEntity = this.data.get(i);
        coveViewHolder.mViewBinding.vState.setVisibility(soundCollectionEntity.isSelect() ? 0 : 4);
        coveViewHolder.mViewBinding.ivState.setVisibility(soundCollectionEntity.isSelect() ? 0 : 4);
        GlideUtil.setImageData(soundCollectionEntity.getImagePath(), coveViewHolder.mViewBinding.ivCove, 228, 148);
    }

    public void onBindViewHolder(CoveViewHolder coveViewHolder, int i, List<Object> list) {
        super.onBindViewHolder(coveViewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (NOTIFY_STATE.equals(list.get(i2))) {
                boolean zIsSelect = this.data.get(i).isSelect();
                coveViewHolder.mViewBinding.vState.setVisibility(zIsSelect ? 0 : 4);
                coveViewHolder.mViewBinding.ivState.setVisibility(zIsSelect ? 0 : 4);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public static class CoveViewHolder extends RecyclerView.ViewHolder {
        private final ItemSoundCollectBinding mViewBinding;

        public CoveViewHolder(ItemSoundCollectBinding itemSoundCollectBinding) {
            super(itemSoundCollectBinding.getRoot());
            this.mViewBinding = itemSoundCollectBinding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
