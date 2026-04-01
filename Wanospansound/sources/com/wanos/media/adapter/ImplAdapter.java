package com.wanos.media.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.ImplListEntity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.zero_p.databinding.ItemZeroImplBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImplAdapter extends RecyclerView.Adapter<ImplViewHolder> {
    private static final String NOTIFY = "Box_Change";
    private final List<ImplListEntity> data;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i, ImplListEntity implListEntity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ImplViewHolder) viewHolder, i, (List<Object>) list);
    }

    public ImplAdapter(List<ImplListEntity> list) {
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ImplViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ImplViewHolder implViewHolder = new ImplViewHolder(ItemZeroImplBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        implViewHolder.mViewBinding.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.media.adapter.ImplAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition;
                if (ImplAdapter.this.mOnItemClickListener != null && (bindingAdapterPosition = implViewHolder.getBindingAdapterPosition()) >= 0 && bindingAdapterPosition < ImplAdapter.this.data.size()) {
                    ImplAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition, (ImplListEntity) ImplAdapter.this.data.get(bindingAdapterPosition));
                }
            }
        });
        return implViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ImplViewHolder implViewHolder, int i) {
        ImplListEntity implListEntity = this.data.get(i);
        implViewHolder.mViewBinding.tvNumber.setText(initNumber(i));
        implViewHolder.mViewBinding.tvTitle.setText(implListEntity.getTitle());
        implViewHolder.mViewBinding.tvTime.setText(implListEntity.getTime());
        implViewHolder.mViewBinding.ivBox.setSelected(implListEntity.isSelected());
        implViewHolder.mViewBinding.getRoot().setSelected(implListEntity.isSelected());
    }

    public void onBindViewHolder(ImplViewHolder implViewHolder, int i, List<Object> list) {
        super.onBindViewHolder(implViewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (NOTIFY.equals(list.get(i2))) {
                boolean zIsSelected = this.data.get(i).isSelected();
                implViewHolder.mViewBinding.ivBox.setSelected(zIsSelected);
                implViewHolder.mViewBinding.getRoot().setSelected(zIsSelected);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setItemState(int i) {
        if (i < 0 || i >= this.data.size()) {
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.data.size()) {
                break;
            }
            ImplListEntity implListEntity = this.data.get(i2);
            if (implListEntity.isSelected()) {
                implListEntity.setSelected(false);
                notifyItemChanged(i2, NOTIFY);
                break;
            }
            i2++;
        }
        this.data.get(i).setSelected(true);
        notifyItemChanged(i, NOTIFY);
    }

    public static class ImplViewHolder extends RecyclerView.ViewHolder {
        public final ItemZeroImplBinding mViewBinding;

        public ImplViewHolder(ItemZeroImplBinding itemZeroImplBinding) {
            super(itemZeroImplBinding.getRoot());
            this.mViewBinding = itemZeroImplBinding;
        }
    }

    private String initNumber(int i) {
        int i2 = i + 1;
        if (i2 <= 9) {
            return "0" + i2;
        }
        return "" + i2;
    }
}
