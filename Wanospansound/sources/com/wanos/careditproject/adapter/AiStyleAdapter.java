package com.wanos.careditproject.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.Utils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.ItemAiStyleBinding;
import com.wanos.media.util.AnitClick;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AiStyleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String NOTIFY_SELECT_STATE = "notify_select_state";
    public static final int SELECT_EMOTION = 1;
    public static final int SELECT_STYLE = 0;
    private final String[] mItemData;
    private final SparseBooleanArray mItemState;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public AiStyleAdapter(int i, String str) {
        int i2 = 0;
        if (i == 0) {
            this.mItemData = Utils.getApp().getResources().getStringArray(R.array.ai_style);
        } else if (i == 1) {
            this.mItemData = Utils.getApp().getResources().getStringArray(R.array.ai_emotion);
        } else {
            this.mItemData = new String[0];
        }
        this.mItemState = new SparseBooleanArray(this.mItemData.length);
        while (true) {
            String[] strArr = this.mItemData;
            if (i2 >= strArr.length) {
                return;
            }
            if (str.equals(strArr[i2])) {
                this.mItemState.put(i2, true);
                return;
            }
            i2++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemAiStyleBinding itemAiStyleBindingInflate = ItemAiStyleBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(itemAiStyleBindingInflate);
        itemAiStyleBindingInflate.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.adapter.AiStyleAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (AiStyleAdapter.this.mOnItemClickListener != null) {
                    int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                    for (int i2 = 0; i2 < AiStyleAdapter.this.mItemState.size(); i2++) {
                        if (AiStyleAdapter.this.mItemState.valueAt(i2)) {
                            AiStyleAdapter.this.mItemState.put(i2, false);
                        }
                    }
                    AiStyleAdapter.this.mItemState.put(bindingAdapterPosition, true);
                    AiStyleAdapter aiStyleAdapter = AiStyleAdapter.this;
                    aiStyleAdapter.notifyItemRangeChanged(0, aiStyleAdapter.mItemData.length, AiStyleAdapter.NOTIFY_SELECT_STATE);
                    AiStyleAdapter.this.mOnItemClickListener.onItemClick(AiStyleAdapter.this.mItemData[bindingAdapterPosition]);
                }
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.binding.tvItemName.setText(this.mItemData[i]);
        viewHolder.binding.tvItemName.setSelected(this.mItemState.get(i, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        super.onBindViewHolder(viewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (NOTIFY_SELECT_STATE.equals(list.get(i2))) {
                viewHolder.binding.tvItemName.setSelected(this.mItemState.get(i, false));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItemData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAiStyleBinding binding;

        public ViewHolder(ItemAiStyleBinding itemAiStyleBinding) {
            super(itemAiStyleBinding.getRoot());
            this.binding = itemAiStyleBinding;
        }
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
