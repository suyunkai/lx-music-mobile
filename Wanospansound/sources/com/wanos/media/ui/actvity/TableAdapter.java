package com.wanos.media.ui.actvity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.R;
import com.wanos.media.databinding.ItemMainTableBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TableAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private int mSelectId;
    private final List<TableEntity> mTableList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(TableEntity item);
    }

    public TableAdapter(List<TableEntity> tableList, Context context) {
        this.mTableList = tableList;
        this.context = context;
        this.mSelectId = tableList.get(0).getId();
    }

    public List<TableEntity> getTableData() {
        return this.mTableList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelectTableId(final int id) {
        this.mSelectId = id;
        if (this.onItemClickListener != null) {
            int i = 0;
            while (true) {
                if (i >= this.mTableList.size()) {
                    break;
                }
                if (this.mTableList.get(i).getId() == id) {
                    this.onItemClickListener.onItemClick(this.mTableList.get(i));
                    break;
                }
                i++;
            }
        }
        notifyItemRangeChanged(0, this.mTableList.size());
    }

    public int getSelectId() {
        return this.mSelectId;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMainTableBinding itemMainTableBindingInflate = ItemMainTableBinding.inflate(LayoutInflater.from(this.context), parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemMainTableBindingInflate);
        itemMainTableBindingInflate.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.actvity.TableAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m483x1f96b934(viewHolder, view);
            }
        });
        return viewHolder;
    }

    /* JADX INFO: renamed from: lambda$onCreateViewHolder$0$com-wanos-media-ui-actvity-TableAdapter, reason: not valid java name */
    /* synthetic */ void m483x1f96b934(ViewHolder viewHolder, View view) {
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (this.onItemClickListener == null || bindingAdapterPosition >= this.mTableList.size()) {
            return;
        }
        this.mSelectId = this.mTableList.get(bindingAdapterPosition).getId();
        notifyItemRangeChanged(0, this.mTableList.size());
        this.onItemClickListener.onItemClick(this.mTableList.get(bindingAdapterPosition));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        int dimensionPixelSize = holder.itemView.getResources().getDimensionPixelSize(R.dimen.home_tab_common_bottom_margin);
        if (position == this.mTableList.size() - 1) {
            dimensionPixelSize = holder.itemView.getResources().getDimensionPixelSize(R.dimen.home_tab_last_bottom_margin);
        }
        ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = dimensionPixelSize;
        holder.mBinding.ivTableIcon.setImageResource(this.mTableList.get(position).getTableIcon());
        holder.mBinding.tvTableText.setText(this.mTableList.get(position).getTableName());
        holder.mBinding.getRoot().setSelected(this.mTableList.get(position).getId() == this.mSelectId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mTableList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMainTableBinding mBinding;

        public ViewHolder(ItemMainTableBinding itemView) {
            super(itemView.getRoot());
            this.mBinding = itemView;
        }
    }
}
