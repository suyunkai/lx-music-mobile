package com.wanos.media.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ItemHelpBinding;

/* JADX INFO: loaded from: classes3.dex */
public class HelpAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 3;
    }

    public HelpAdapter(Context context) {
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemHelpBinding.inflate(LayoutInflater.from(this.context), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i == 0) {
            viewHolder.binding.tvHint.setText(StringUtils.getString(R.string.help_text_1));
            Glide.with(this.context).asGif().load(Integer.valueOf(R.drawable.help_zero_1)).into(viewHolder.binding.ivImage);
        } else if (i == 1) {
            viewHolder.binding.tvHint.setText(StringUtils.getString(R.string.help_text_2));
            Glide.with(this.context).asGif().load(Integer.valueOf(R.drawable.help_zero_2)).into(viewHolder.binding.ivImage);
        } else {
            if (i != 2) {
                return;
            }
            viewHolder.binding.tvHint.setText(StringUtils.getString(R.string.help_text_3));
            Glide.with(this.context).asGif().load(Integer.valueOf(R.drawable.help_zero_3)).into(viewHolder.binding.ivImage);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemHelpBinding binding;

        public ViewHolder(ItemHelpBinding itemHelpBinding) {
            super(itemHelpBinding.getRoot());
            this.binding = itemHelpBinding;
        }
    }
}
