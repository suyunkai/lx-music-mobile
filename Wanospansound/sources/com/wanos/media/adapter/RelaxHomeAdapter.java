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
import com.wanos.media.zero_p.databinding.ItemRelaxHomeAddBinding;
import com.wanos.media.zero_p.databinding.ItemRelaxHomeMeBinding;
import com.wanos.media.zero_p.databinding.ItemRelaxHomeNormalBinding;
import com.wanos.media.zero_p.databinding.ItemRelaxHomeTitleBinding;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<SpaceThemeBaseInfo> mData = new ArrayList();
    private final ImageLoadTools.Builder mImageLoadBuilder;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, SpaceThemeBaseInfo spaceThemeBaseInfo);

        void onItemMenuClick(View view, SpaceThemeBaseInfo spaceThemeBaseInfo);
    }

    public RelaxHomeAdapter(Context context) {
        this.context = context;
        this.mImageLoadBuilder = new ImageLoadTools.Builder().setSize((int) context.getResources().getDimension(R.dimen.zero_card_image_size)).setCovertDimens(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mData.get(i).getItemType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 3001) {
            ItemRelaxHomeTitleBinding itemRelaxHomeTitleBindingInflate = ItemRelaxHomeTitleBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
            final TitleViewHolder titleViewHolder = new TitleViewHolder(itemRelaxHomeTitleBindingInflate);
            itemRelaxHomeTitleBindingInflate.getRoot().setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxHomeAdapter.1
                @Override // com.wanos.media.util.CustomClick
                public void onAnitClick(View view) {
                    int bindingAdapterPosition = titleViewHolder.getBindingAdapterPosition();
                    if (RelaxHomeAdapter.this.onItemClickListener == null || bindingAdapterPosition >= RelaxHomeAdapter.this.mData.size()) {
                        return;
                    }
                    RelaxHomeAdapter.this.onItemClickListener.onItemClick(view, (SpaceThemeBaseInfo) RelaxHomeAdapter.this.mData.get(bindingAdapterPosition));
                }
            });
            return titleViewHolder;
        }
        if (i == 3003) {
            ItemRelaxHomeAddBinding itemRelaxHomeAddBindingInflate = ItemRelaxHomeAddBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
            final AddViewHolder addViewHolder = new AddViewHolder(itemRelaxHomeAddBindingInflate);
            itemRelaxHomeAddBindingInflate.getRoot().setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxHomeAdapter.2
                @Override // com.wanos.media.util.CustomClick
                public void onAnitClick(View view) {
                    int bindingAdapterPosition = addViewHolder.getBindingAdapterPosition();
                    if (RelaxHomeAdapter.this.onItemClickListener == null || bindingAdapterPosition >= RelaxHomeAdapter.this.mData.size()) {
                        return;
                    }
                    RelaxHomeAdapter.this.onItemClickListener.onItemClick(view, (SpaceThemeBaseInfo) RelaxHomeAdapter.this.mData.get(bindingAdapterPosition));
                }
            });
            return addViewHolder;
        }
        if (i == 3004) {
            ItemRelaxHomeMeBinding itemRelaxHomeMeBindingInflate = ItemRelaxHomeMeBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
            final MeViewHolder meViewHolder = new MeViewHolder(itemRelaxHomeMeBindingInflate);
            itemRelaxHomeMeBindingInflate.getRoot().setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxHomeAdapter.3
                @Override // com.wanos.media.util.CustomClick
                public void onAnitClick(View view) {
                    int bindingAdapterPosition = meViewHolder.getBindingAdapterPosition();
                    if (RelaxHomeAdapter.this.onItemClickListener == null || bindingAdapterPosition >= RelaxHomeAdapter.this.mData.size()) {
                        return;
                    }
                    RelaxHomeAdapter.this.onItemClickListener.onItemClick(view, (SpaceThemeBaseInfo) RelaxHomeAdapter.this.mData.get(bindingAdapterPosition));
                }
            });
            itemRelaxHomeMeBindingInflate.btnSpaceOpMore.setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxHomeAdapter.4
                @Override // com.wanos.media.util.CustomClick
                public void onAnitClick(View view) {
                    int bindingAdapterPosition = meViewHolder.getBindingAdapterPosition();
                    if (RelaxHomeAdapter.this.onItemClickListener == null || bindingAdapterPosition >= RelaxHomeAdapter.this.mData.size()) {
                        return;
                    }
                    RelaxHomeAdapter.this.onItemClickListener.onItemMenuClick(view, (SpaceThemeBaseInfo) RelaxHomeAdapter.this.mData.get(bindingAdapterPosition));
                }
            });
            return meViewHolder;
        }
        ItemRelaxHomeNormalBinding itemRelaxHomeNormalBindingInflate = ItemRelaxHomeNormalBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
        final NormalViewHolder normalViewHolder = new NormalViewHolder(itemRelaxHomeNormalBindingInflate);
        itemRelaxHomeNormalBindingInflate.getRoot().setOnClickListener(new CustomClick() { // from class: com.wanos.media.adapter.RelaxHomeAdapter.5
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = normalViewHolder.getBindingAdapterPosition();
                if (RelaxHomeAdapter.this.onItemClickListener == null || bindingAdapterPosition >= RelaxHomeAdapter.this.mData.size()) {
                    return;
                }
                RelaxHomeAdapter.this.onItemClickListener.onItemClick(view, (SpaceThemeBaseInfo) RelaxHomeAdapter.this.mData.get(bindingAdapterPosition));
            }
        });
        return normalViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SpaceThemeBaseInfo spaceThemeBaseInfo = this.mData.get(i);
        if (!(viewHolder instanceof TitleViewHolder)) {
            if (viewHolder instanceof NormalViewHolder) {
                NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;
                this.mImageLoadBuilder.onLoad(this.context, spaceThemeBaseInfo.getCoverPath(), normalViewHolder.normalBinding.imSpaceBg);
                normalViewHolder.normalBinding.tvSpaceName.setText(spaceThemeBaseInfo.getName());
                normalViewHolder.normalBinding.ivVip.setVisibility(spaceThemeBaseInfo.isVip() ? 0 : 8);
                return;
            }
            if (viewHolder instanceof MeViewHolder) {
                MeViewHolder meViewHolder = (MeViewHolder) viewHolder;
                this.mImageLoadBuilder.onLoad(this.context, spaceThemeBaseInfo.getCoverPath(), meViewHolder.meBinding.imSpaceBg);
                meViewHolder.meBinding.tvSpaceName.setText(spaceThemeBaseInfo.getName());
                meViewHolder.meBinding.ivVip.setVisibility(spaceThemeBaseInfo.isVip() ? 0 : 8);
                meViewHolder.meBinding.tvSubTitle.setText(StringUtils.format(StringUtils.getString(R.string.relax_me_author), spaceThemeBaseInfo.getSourceUserName()));
                meViewHolder.meBinding.ivIconTag.setImageResource(spaceThemeBaseInfo.getThemeTypeResId());
                return;
            }
            return;
        }
        ((TitleViewHolder) viewHolder).titleBinding.tvXqSpaceGroupMore.setText(spaceThemeBaseInfo.getName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public void submitList(List<SpaceThemeBaseInfo> list) {
        this.mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelaxHomeTitleBinding titleBinding;

        public TitleViewHolder(ItemRelaxHomeTitleBinding itemRelaxHomeTitleBinding) {
            super(itemRelaxHomeTitleBinding.getRoot());
            this.titleBinding = itemRelaxHomeTitleBinding;
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelaxHomeNormalBinding normalBinding;

        public NormalViewHolder(ItemRelaxHomeNormalBinding itemRelaxHomeNormalBinding) {
            super(itemRelaxHomeNormalBinding.getRoot());
            this.normalBinding = itemRelaxHomeNormalBinding;
        }
    }

    public static class AddViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelaxHomeAddBinding addBinding;

        public AddViewHolder(ItemRelaxHomeAddBinding itemRelaxHomeAddBinding) {
            super(itemRelaxHomeAddBinding.getRoot());
            this.addBinding = itemRelaxHomeAddBinding;
        }
    }

    public static class MeViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelaxHomeMeBinding meBinding;

        public MeViewHolder(ItemRelaxHomeMeBinding itemRelaxHomeMeBinding) {
            super(itemRelaxHomeMeBinding.getRoot());
            this.meBinding = itemRelaxHomeMeBinding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
