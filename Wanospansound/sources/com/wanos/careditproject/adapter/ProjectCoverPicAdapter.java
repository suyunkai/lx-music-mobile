package com.wanos.careditproject.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.commonlibrary.utils.GlideUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectCoverPicAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<String> dataList;
    private OnSelectConverPicListener onSelectConverPicListener;
    private String selectPic;

    public interface OnSelectConverPicListener {
        void onSelectConverPicListener(String str);
    }

    public ProjectCoverPicAdapter(Context context, List<String> list, String str) {
        this.context = context;
        this.dataList = list;
        this.selectPic = str;
    }

    public void setSelectPic(String str) {
        this.selectPic = str;
    }

    public void setOnSelectConverPicListener(OnSelectConverPicListener onSelectConverPicListener) {
        this.onSelectConverPicListener = onSelectConverPicListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_project_cover_pic_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final String str = this.dataList.get(i);
        GlideUtil.loadImage(str, viewHolder.coverIm);
        if (!TextUtils.isEmpty(this.selectPic) && this.selectPic.equals(str)) {
            viewHolder.itemView.setSelected(true);
            viewHolder.imCoverSelect.setVisibility(0);
        } else {
            viewHolder.itemView.setSelected(false);
            viewHolder.imCoverSelect.setVisibility(8);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.ProjectCoverPicAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(ProjectCoverPicAdapter.this.selectPic) || !ProjectCoverPicAdapter.this.selectPic.equals(str)) {
                    if (ProjectCoverPicAdapter.this.onSelectConverPicListener != null) {
                        ProjectCoverPicAdapter.this.onSelectConverPicListener.onSelectConverPicListener(str);
                    }
                    ProjectCoverPicAdapter.this.selectPic = str;
                    ProjectCoverPicAdapter.this.notifyDataSetChanged();
                }
            }
        });
    }

    public void setData(List<String> list) {
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverIm;
        public ImageView imCoverSelect;

        ViewHolder(View view) {
            super(view);
            this.coverIm = (ImageView) view.findViewById(R.id.im_cover);
            this.imCoverSelect = (ImageView) view.findViewById(R.id.im_cover_select);
        }
    }
}
