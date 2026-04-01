package com.wanos.careditproject.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.bean.EditGetPictureListBean;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.dialog.SelectProjectIconDialog;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.utils.GlideUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SelectProjectIconAdapter extends BaseRecycleAdapter<EditGetPictureListBean.ProjectPictureInfo, ViewHolder> {
    private final Context context;
    private SelectProjectIconDialog.SelectIconListener listener;
    private String selectPic;

    public SelectProjectIconAdapter(Context context, List<EditGetPictureListBean.ProjectPictureInfo> list, String str) {
        super(list);
        this.context = context;
        this.selectPic = str;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_select_icon_item, viewGroup, false));
    }

    public String getSelectPic() {
        return this.selectPic;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder viewHolder, int i) {
        final EditGetPictureListBean.ProjectPictureInfo projectPictureInfo = (EditGetPictureListBean.ProjectPictureInfo) this.datas.get(i);
        if (projectPictureInfo != null) {
            GlideUtil.setImageData(projectPictureInfo.getPicture(), viewHolder.ivIcon);
            if (projectPictureInfo.getPicture().equals(this.selectPic)) {
                viewHolder.ivSelect.setSelected(true);
            } else {
                viewHolder.ivSelect.setSelected(false);
            }
            viewHolder.ivIcon.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.adapter.SelectProjectIconAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SelectProjectIconAdapter.this.selectPic = projectPictureInfo.getPicture();
                    SelectProjectIconAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivIcon;
        public ImageView ivSelect;

        public ViewHolder(View view) {
            super(view);
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        }
    }
}
