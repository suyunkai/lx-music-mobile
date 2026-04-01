package com.wanos.careditproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.model.BeatNumberInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectBeatNumbeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<BeatNumberInfo> dataList;
    private OnSelectBeatListener onSelectBeatListener;
    private BeatNumberInfo selectBeatNumberInfo;

    public interface OnSelectBeatListener {
        void onSelectBeatListener(BeatNumberInfo beatNumberInfo);
    }

    public ProjectBeatNumbeAdapter(Context context, List<BeatNumberInfo> list, BeatNumberInfo beatNumberInfo) {
        this.context = context;
        this.dataList = list;
        this.selectBeatNumberInfo = beatNumberInfo;
    }

    public void setOnSelectBeatListener(OnSelectBeatListener onSelectBeatListener) {
        this.onSelectBeatListener = onSelectBeatListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_project_beat_number_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final BeatNumberInfo beatNumberInfo = this.dataList.get(i);
        if (beatNumberInfo.getBeatTotal() != this.selectBeatNumberInfo.getBeatTotal() || beatNumberInfo.getBar() != this.selectBeatNumberInfo.getBar()) {
            viewHolder.ivSelect.setVisibility(8);
        } else {
            viewHolder.ivSelect.setVisibility(0);
        }
        viewHolder.tvBeatBar.setText(beatNumberInfo.getBar() + "");
        viewHolder.tvBeatNumber.setText(beatNumberInfo.getBeatTotal() + "");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.ProjectBeatNumbeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (beatNumberInfo.getBeatTotal() == ProjectBeatNumbeAdapter.this.selectBeatNumberInfo.getBeatTotal() && beatNumberInfo.getBar() == ProjectBeatNumbeAdapter.this.selectBeatNumberInfo.getBar()) {
                    return;
                }
                if (ProjectBeatNumbeAdapter.this.onSelectBeatListener != null) {
                    ProjectBeatNumbeAdapter.this.onSelectBeatListener.onSelectBeatListener(beatNumberInfo);
                }
                ProjectBeatNumbeAdapter.this.selectBeatNumberInfo = beatNumberInfo;
                ProjectBeatNumbeAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void setData(List<BeatNumberInfo> list) {
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BeatNumberInfo> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSelect;
        private TextView tvBeatBar;
        private TextView tvBeatNumber;

        ViewHolder(View view) {
            super(view);
            this.tvBeatBar = (TextView) view.findViewById(R.id.tv_beat_bar);
            this.tvBeatNumber = (TextView) view.findViewById(R.id.tv_beat_number);
            this.ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        }
    }
}
