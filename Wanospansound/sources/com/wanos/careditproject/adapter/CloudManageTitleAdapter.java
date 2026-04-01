package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ColorUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.CloudInfoEntity;
import com.wanos.careditproject.databinding.ItemDialogCloudTitleBinding;
import com.wanos.media.util.AnitClick;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CloudManageTitleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<CloudInfoEntity> mCloudDirList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CloudInfoEntity cloudInfoEntity);
    }

    public CloudManageTitleAdapter(List<CloudInfoEntity> list) {
        this.mCloudDirList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(ItemDialogCloudTitleBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        viewHolder.mViewBinding.getRoot().setOnClickListener(new AnitClick(800L) { // from class: com.wanos.careditproject.adapter.CloudManageTitleAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                boolean z;
                if (CloudManageTitleAdapter.this.mOnItemClickListener == null) {
                    return;
                }
                CloudInfoEntity itemData = CloudManageTitleAdapter.this.getItemData(viewHolder.getBindingAdapterPosition());
                if (itemData == null) {
                    return;
                }
                int itemCount = CloudManageTitleAdapter.this.getItemCount();
                do {
                    CloudInfoEntity cloudInfoEntity = (CloudInfoEntity) CloudManageTitleAdapter.this.mCloudDirList.get(CloudManageTitleAdapter.this.mCloudDirList.size() - 1);
                    z = cloudInfoEntity.getId() != itemData.getId();
                    if (z) {
                        CloudManageTitleAdapter.this.mCloudDirList.remove(cloudInfoEntity);
                    }
                } while (z);
                CloudManageTitleAdapter.this.notifyItemRangeChanged(0, itemCount);
                CloudManageTitleAdapter.this.mOnItemClickListener.onItemClick(itemData);
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
        if (i == 0) {
            viewHolder.mViewBinding.tvCloudTitle.setTextColor(ColorUtils.getColor(R.color.cloud_dialog_directory_1_text));
            viewHolder.mViewBinding.tvCloudTitle.setText(itemData.getName());
        } else if (i == this.mCloudDirList.size() - 1) {
            viewHolder.mViewBinding.tvCloudTitle.setTextColor(ColorUtils.getColor(R.color.cloud_dialog_directory_2_text));
            viewHolder.mViewBinding.tvCloudTitle.setText(" > " + itemData.getName());
        } else {
            viewHolder.mViewBinding.tvCloudTitle.setTextColor(ColorUtils.getColor(R.color.cloud_dialog_directory_1_text));
            viewHolder.mViewBinding.tvCloudTitle.setText(" > " + itemData.getName());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CloudInfoEntity> list = this.mCloudDirList;
        if (list == null) {
            return 0;
        }
        return list.size();
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemDialogCloudTitleBinding mViewBinding;

        public ViewHolder(ItemDialogCloudTitleBinding itemDialogCloudTitleBinding) {
            super(itemDialogCloudTitleBinding.getRoot());
            this.mViewBinding = itemDialogCloudTitleBinding;
        }
    }
}
