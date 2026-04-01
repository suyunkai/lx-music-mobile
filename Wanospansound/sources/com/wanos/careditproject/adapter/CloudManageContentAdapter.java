package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.data.bean.CloudInfoEntity;
import com.wanos.careditproject.databinding.ItemDialogCloudBinding;
import com.wanos.media.util.AnitClick;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CloudManageContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<CloudInfoEntity> mCloudDirList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CloudInfoEntity cloudInfoEntity);
    }

    public CloudManageContentAdapter(List<CloudInfoEntity> list) {
        this.mCloudDirList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(ItemDialogCloudBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        viewHolder.mViewBinding.getRoot().setOnClickListener(new AnitClick(800L) { // from class: com.wanos.careditproject.adapter.CloudManageContentAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (CloudManageContentAdapter.this.mOnItemClickListener == null) {
                    return;
                }
                CloudInfoEntity itemData = CloudManageContentAdapter.this.getItemData(viewHolder.getBindingAdapterPosition());
                if (itemData == null) {
                    return;
                }
                CloudManageContentAdapter.this.mOnItemClickListener.onItemClick(itemData);
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CloudInfoEntity itemData = getItemData(i);
        if (itemData == null) {
            return;
        }
        viewHolder.mViewBinding.tvDirTitle.setText(itemData.getName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CloudInfoEntity> list = this.mCloudDirList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemDialogCloudBinding mViewBinding;

        public ViewHolder(ItemDialogCloudBinding itemDialogCloudBinding) {
            super(itemDialogCloudBinding.getRoot());
            this.mViewBinding = itemDialogCloudBinding;
        }
    }

    public CloudInfoEntity getItemData(int i) {
        List<CloudInfoEntity> list = this.mCloudDirList;
        if (list != null && i >= 0 && i < list.size()) {
            return this.mCloudDirList.get(i);
        }
        return null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
