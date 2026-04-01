package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.AiPlayEntity;
import com.wanos.careditproject.data.bean.AiPlayState;
import com.wanos.careditproject.databinding.AiItemPlayBtnBinding;
import com.wanos.media.util.AnitClick;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AiPlayerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String NOTIFY_STATE = "notify_state";
    private final List<AiPlayEntity> data = new ArrayList();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i, AiPlayEntity aiPlayEntity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
    }

    public void submit(List<AiPlayEntity> list) {
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AiItemPlayBtnBinding aiItemPlayBtnBindingInflate = AiItemPlayBtnBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(aiItemPlayBtnBindingInflate);
        aiItemPlayBtnBindingInflate.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.adapter.AiPlayerAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (AiPlayerAdapter.this.mOnItemClickListener == null || bindingAdapterPosition >= AiPlayerAdapter.this.data.size()) {
                    return;
                }
                AiPlayerAdapter.this.mOnItemClickListener.onItemClick(bindingAdapterPosition, AiPlayerAdapter.this.getItemData(bindingAdapterPosition));
            }
        });
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AiPlayEntity itemData = getItemData(i);
        if (itemData != null) {
            viewHolder.binding.tvPlay.setText(String.valueOf(i + 1));
            int i2 = AnonymousClass2.$SwitchMap$com$wanos$careditproject$data$bean$AiPlayState[itemData.getState().ordinal()];
            if (i2 == 1) {
                viewHolder.binding.tvPlay.setVisibility(0);
                viewHolder.binding.ivPlay.setVisibility(4);
                viewHolder.binding.progressPlay.setVisibility(4);
                return;
            }
            if (i2 == 2) {
                viewHolder.binding.tvPlay.setVisibility(4);
                viewHolder.binding.ivPlay.setVisibility(0);
                viewHolder.binding.ivPlay.setImageResource(R.drawable.icon_ai_play_player);
                viewHolder.binding.progressPlay.setVisibility(4);
                return;
            }
            if (i2 == 3) {
                viewHolder.binding.tvPlay.setVisibility(4);
                viewHolder.binding.ivPlay.setVisibility(0);
                viewHolder.binding.ivPlay.setImageResource(R.drawable.icon_ai_play_state_stop);
                viewHolder.binding.progressPlay.setVisibility(4);
                return;
            }
            if (i2 != 4) {
                return;
            }
            viewHolder.binding.tvPlay.setVisibility(4);
            viewHolder.binding.ivPlay.setVisibility(4);
            viewHolder.binding.progressPlay.setVisibility(0);
        }
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.adapter.AiPlayerAdapter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$careditproject$data$bean$AiPlayState;

        static {
            int[] iArr = new int[AiPlayState.values().length];
            $SwitchMap$com$wanos$careditproject$data$bean$AiPlayState = iArr;
            try {
                iArr[AiPlayState.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiPlayState[AiPlayState.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiPlayState[AiPlayState.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiPlayState[AiPlayState.LOADING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        AiPlayEntity itemData;
        super.onBindViewHolder(viewHolder, i, list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (NOTIFY_STATE.equals(list.get(i2)) && (itemData = getItemData(i)) != null) {
                int i3 = AnonymousClass2.$SwitchMap$com$wanos$careditproject$data$bean$AiPlayState[itemData.getState().ordinal()];
                if (i3 == 1) {
                    viewHolder.binding.tvPlay.setVisibility(0);
                    viewHolder.binding.ivPlay.setVisibility(4);
                    viewHolder.binding.progressPlay.setVisibility(4);
                } else if (i3 == 2) {
                    viewHolder.binding.tvPlay.setVisibility(4);
                    viewHolder.binding.ivPlay.setVisibility(0);
                    viewHolder.binding.progressPlay.setVisibility(4);
                } else if (i3 == 4) {
                    viewHolder.binding.tvPlay.setVisibility(4);
                    viewHolder.binding.ivPlay.setVisibility(4);
                    viewHolder.binding.progressPlay.setVisibility(0);
                }
            }
        }
    }

    public void setItemState(AiPlayEntity aiPlayEntity) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getId().equals(aiPlayEntity.getId())) {
                this.data.get(i).setState(aiPlayEntity.getState());
                notifyItemChanged(i, NOTIFY_STATE);
            } else {
                this.data.get(i).setState(AiPlayState.DEFAULT);
                notifyItemChanged(i, NOTIFY_STATE);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public AiPlayEntity getItemData(int i) {
        return this.data.get(i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AiItemPlayBtnBinding binding;

        public ViewHolder(AiItemPlayBtnBinding aiItemPlayBtnBinding) {
            super(aiItemPlayBtnBinding.getRoot());
            this.binding = aiItemPlayBtnBinding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
