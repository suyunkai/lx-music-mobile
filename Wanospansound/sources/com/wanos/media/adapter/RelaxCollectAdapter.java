package com.wanos.media.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.ImageLoadTools;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ItemRelaxCollectBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxCollectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final ImageLoadTools.Builder mImageLoadBuilder;
    private final List<SpaceThemeBaseInfo> mItemData;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(SpaceThemeBaseInfo spaceThemeBaseInfo);

        void onMoreClick(View view, SpaceThemeBaseInfo spaceThemeBaseInfo);
    }

    public RelaxCollectAdapter(Context context, List<SpaceThemeBaseInfo> list) {
        this.context = context;
        this.mItemData = list;
        this.mImageLoadBuilder = new ImageLoadTools.Builder().setSize((int) context.getResources().getDimension(R.dimen.zero_card_image_size)).setCovertDimens(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(ItemRelaxCollectBinding.inflate(LayoutInflater.from(this.context), viewGroup, false));
        viewHolder.binding.getRoot().setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxCollectAdapter.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (RelaxCollectAdapter.this.mOnItemClickListener == null || bindingAdapterPosition >= RelaxCollectAdapter.this.mItemData.size()) {
                    return;
                }
                RelaxCollectAdapter.this.mOnItemClickListener.onItemClick((SpaceThemeBaseInfo) RelaxCollectAdapter.this.mItemData.get(bindingAdapterPosition));
            }
        });
        viewHolder.binding.btnSpaceOpMore.setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxCollectAdapter.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (RelaxCollectAdapter.this.mOnItemClickListener == null || bindingAdapterPosition >= RelaxCollectAdapter.this.mItemData.size()) {
                    return;
                }
                RelaxCollectAdapter.this.mOnItemClickListener.onMoreClick(view, (SpaceThemeBaseInfo) RelaxCollectAdapter.this.mItemData.get(bindingAdapterPosition));
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SpaceThemeBaseInfo spaceThemeBaseInfo = this.mItemData.get(i);
        this.mImageLoadBuilder.onLoad(this.context, spaceThemeBaseInfo.getCoverPath(), viewHolder.binding.imSpaceBg);
        viewHolder.binding.tvSpaceName.setText(spaceThemeBaseInfo.getName());
        viewHolder.binding.ivVip.setVisibility(spaceThemeBaseInfo.isVip() ? 0 : 8);
        viewHolder.binding.tvSubTitle.setText(StringUtils.format(StringUtils.getString(R.string.relax_me_author), spaceThemeBaseInfo.getSourceUserName()));
        viewHolder.binding.ivIconTag.setImageResource(spaceThemeBaseInfo.getThemeTypeResId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItemData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelaxCollectBinding binding;

        public ViewHolder(ItemRelaxCollectBinding itemRelaxCollectBinding) {
            super(itemRelaxCollectBinding.getRoot());
            this.binding = itemRelaxCollectBinding;
        }
    }
}
