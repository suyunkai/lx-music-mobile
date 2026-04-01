package com.wanos.media.ui.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.QAListItem;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.media.R;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class QAListAdapter extends BaseRecycleAdapter<QAListItem, QAViewHolder> {
    public QAListAdapter(List<QAListItem> datas) {
        super(datas);
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public QAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QAViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_qa_list, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(QAViewHolder holder, int position) {
        QAListItem qAListItem = (QAListItem) this.datas.get(position);
        holder.tvQuestion.setText(qAListItem.getTitle());
        holder.tvAnswer.setText(qAListItem.getContent());
    }

    public static class QAViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAnswer;
        public TextView tvQuestion;

        public QAViewHolder(View itemView) {
            super(itemView);
            this.tvAnswer = (TextView) itemView.findViewById(R.id.tv_answer);
            this.tvQuestion = (TextView) itemView.findViewById(R.id.tv_question);
        }
    }
}
