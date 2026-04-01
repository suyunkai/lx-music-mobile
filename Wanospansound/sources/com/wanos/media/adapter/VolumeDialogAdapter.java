package com.wanos.media.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.widget.WanosVolumeView;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ItemVolumeBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeDialogAdapter extends RecyclerView.Adapter<VolumeViewHolder> {
    private static final String NOTIFICATION_SELECT = "item_select";
    private final List<VolumeModifyEntity> data;
    private OnItemListener mOnItemListener;

    public interface OnItemListener {
        void onItemVolume(VolumeModifyEntity volumeModifyEntity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((VolumeViewHolder) viewHolder, i, (List<Object>) list);
    }

    public VolumeDialogAdapter(List<VolumeModifyEntity> list) {
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VolumeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final VolumeViewHolder volumeViewHolder = new VolumeViewHolder(ItemVolumeBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        volumeViewHolder.itemView.setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.VolumeDialogAdapter.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                int absoluteAdapterPosition = volumeViewHolder.getAbsoluteAdapterPosition();
                int i2 = 0;
                while (true) {
                    if (i2 >= VolumeDialogAdapter.this.data.size()) {
                        break;
                    }
                    if (((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(i2)).isSelect()) {
                        ((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(i2)).setSelect(false);
                        VolumeDialogAdapter.this.notifyItemChanged(i2, VolumeDialogAdapter.NOTIFICATION_SELECT);
                        break;
                    }
                    i2++;
                }
                ((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(absoluteAdapterPosition)).setSelect(true);
                VolumeDialogAdapter.this.notifyItemChanged(absoluteAdapterPosition, VolumeDialogAdapter.NOTIFICATION_SELECT);
            }
        });
        volumeViewHolder.mBinding.volume.setListener(new WanosVolumeView.OnVolumeViewListener() { // from class: com.wanos.media.adapter.VolumeDialogAdapter.2
            @Override // com.wanos.media.widget.WanosVolumeView.OnVolumeViewListener
            public void onViewTouch() {
                int absoluteAdapterPosition = volumeViewHolder.getAbsoluteAdapterPosition();
                int i2 = 0;
                while (true) {
                    if (i2 >= VolumeDialogAdapter.this.data.size()) {
                        break;
                    }
                    if (((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(i2)).isSelect()) {
                        ((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(i2)).setSelect(false);
                        VolumeDialogAdapter.this.notifyItemChanged(i2, VolumeDialogAdapter.NOTIFICATION_SELECT);
                        break;
                    }
                    i2++;
                }
                ((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(absoluteAdapterPosition)).setSelect(true);
                VolumeDialogAdapter.this.notifyItemChanged(absoluteAdapterPosition, VolumeDialogAdapter.NOTIFICATION_SELECT);
            }

            @Override // com.wanos.media.widget.WanosVolumeView.OnVolumeViewListener
            public void onVolumeChange(float f) {
                int absoluteAdapterPosition = volumeViewHolder.getAbsoluteAdapterPosition();
                ((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(absoluteAdapterPosition)).setBallVolume(f);
                if (VolumeDialogAdapter.this.mOnItemListener != null) {
                    VolumeDialogAdapter.this.mOnItemListener.onItemVolume((VolumeModifyEntity) VolumeDialogAdapter.this.data.get(absoluteAdapterPosition));
                }
            }
        });
        return volumeViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VolumeViewHolder volumeViewHolder, int i) {
        VolumeModifyEntity volumeModifyEntity = this.data.get(i);
        if (StringUtils.isEmpty(volumeModifyEntity.getBallIcon())) {
            volumeViewHolder.mBinding.ivAudioIcon.setImageResource(R.drawable.icon_vocal_default);
        } else {
            GlideUtil.setImageData(volumeModifyEntity.getBallIcon(), volumeViewHolder.mBinding.ivAudioIcon, 80, 80);
        }
        volumeViewHolder.mBinding.tvAudioName.setText(volumeModifyEntity.getBallName());
        volumeViewHolder.mBinding.volume.setVolume(volumeModifyEntity.getBallVolume());
    }

    public void onBindViewHolder(VolumeViewHolder volumeViewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(volumeViewHolder, i, list);
        } else {
            volumeViewHolder.mBinding.tvAudioName.setSelected(this.data.get(i).isSelect());
            volumeViewHolder.mBinding.volume.setSelected(this.data.get(i).isSelect());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.mOnItemListener = onItemListener;
    }

    public static class VolumeViewHolder extends RecyclerView.ViewHolder {
        final ItemVolumeBinding mBinding;

        public VolumeViewHolder(ItemVolumeBinding itemVolumeBinding) {
            super(itemVolumeBinding.getRoot());
            this.mBinding = itemVolumeBinding;
        }
    }
}
