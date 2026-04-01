package com.wanos.careditproject.utils.rcDrag;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.adapter.AudioTrackLeftAdapter;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.cmd.CmdChangeTrackOrder;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public class RcItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final AudioTrackLeftAdapter adapter;
    private String jsonTrackIdList;
    private OnItemTouchListener listener;
    private int lastToPos = 0;
    private int sFromPos = 0;

    public interface OnItemTouchListener {
        void onLongClickStart();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public RcItemTouchHelperCallback(AudioTrackLeftAdapter audioTrackLeftAdapter) {
        this.adapter = audioTrackLeftAdapter;
    }

    public void setListener(OnItemTouchListener onItemTouchListener) {
        this.listener = onItemTouchListener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(viewHolder.getBindingAdapterPosition() >= this.adapter.getDatas().size() ? 0 : 3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        int bindingAdapterPosition2 = viewHolder2.getBindingAdapterPosition();
        if (bindingAdapterPosition >= this.adapter.getDatas().size() || bindingAdapterPosition2 >= this.adapter.getDatas().size()) {
            return false;
        }
        this.lastToPos = bindingAdapterPosition2;
        Collections.swap(this.adapter.getDatas(), bindingAdapterPosition, bindingAdapterPosition2);
        AudioTrackLeftAdapter audioTrackLeftAdapter = this.adapter;
        if (audioTrackLeftAdapter == null) {
            return true;
        }
        audioTrackLeftAdapter.notifyItemMoved(bindingAdapterPosition, bindingAdapterPosition2);
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        EditingUtils.log("RcItemTouchHelperCallback onSelectedChanged actionState = " + i);
        if (i != 0) {
            this.jsonTrackIdList = String.join(",", DataHelpAudioTrack.getTrackIdList());
            EditingUtils.log("RcItemTouchHelperCallback 0:  " + this.jsonTrackIdList);
            OnItemTouchListener onItemTouchListener = this.listener;
            if (onItemTouchListener != null) {
                onItemTouchListener.onLongClickStart();
            }
            this.sFromPos = viewHolder.getBindingAdapterPosition();
            if (viewHolder instanceof AudioTrackLeftAdapter.AudioTrackLeftViewHolder) {
                AudioTrackLeftAdapter.AudioTrackLeftViewHolder audioTrackLeftViewHolder = (AudioTrackLeftAdapter.AudioTrackLeftViewHolder) viewHolder;
                audioTrackLeftViewHolder.setSelected(true);
                EditingUtils.log("RcItemTouchHelperCallback 1:  " + audioTrackLeftViewHolder.getTrackId());
            }
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        EditingUtils.log("RcItemTouchHelperCallback clearView");
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof AudioTrackLeftAdapter.AudioTrackLeftViewHolder) {
            AudioTrackLeftAdapter.AudioTrackLeftViewHolder audioTrackLeftViewHolder = (AudioTrackLeftAdapter.AudioTrackLeftViewHolder) viewHolder;
            audioTrackLeftViewHolder.setSelected(false);
            audioTrackLeftViewHolder.selectTrack();
        }
        AudioTrackLeftAdapter audioTrackLeftAdapter = this.adapter;
        if (audioTrackLeftAdapter != null) {
            audioTrackLeftAdapter.updateTrackView();
            String strJoin = String.join(",", DataHelpAudioTrack.getTrackIdList());
            EditingUtils.log("RcItemTouchHelperCallback 0:  " + strJoin);
            if (this.jsonTrackIdList.equals(strJoin)) {
                return;
            }
            new CmdChangeTrackOrder(this.jsonTrackIdList, strJoin).save();
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        EditingUtils.log("RcItemTouchHelperCallback : " + PlayerUtils.isPlaying());
        return (RecordUtils.getInstance().isRecording() || PlayerUtils.isPlaying()) ? false : true;
    }
}
