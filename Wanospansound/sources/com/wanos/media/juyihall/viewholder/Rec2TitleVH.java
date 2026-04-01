package com.wanos.media.juyihall.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.Recommend2ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2TitleVH extends Rec2BaseVH implements View.OnClickListener {
    public TextView textView;
    public TextView tvNext;
    public TextView tvPlayAll;
    private int type;
    private Recommend2ViewModel viewModel;

    public Rec2TitleVH(View view) {
        super(view);
        this.textView = (TextView) view.findViewById(R.id.text_view);
        this.tvPlayAll = (TextView) view.findViewById(R.id.tv_play_all);
        this.tvNext = (TextView) view.findViewById(R.id.tv_next_batch);
        this.viewModel = (Recommend2ViewModel) new ViewModelProvider((ViewModelStoreOwner) view.getContext()).get(Recommend2ViewModel.class);
        this.tvNext.setOnClickListener(this);
        this.tvPlayAll.setOnClickListener(this);
    }

    public void setType(int i) {
        this.type = i;
        if (i == 201) {
            this.tvPlayAll.setVisibility(0);
            this.textView.setText(this.itemView.getResources().getString(R.string.rec2_title_music));
            this.tvNext.setVisibility(8);
            return;
        }
        if (i == 301) {
            this.tvPlayAll.setVisibility(8);
            this.textView.setText(this.itemView.getResources().getString(R.string.rec2_title_radio));
            this.tvNext.setVisibility(0);
            return;
        }
        if (i == 401) {
            this.textView.setText(this.itemView.getResources().getString(R.string.rec2_title_album));
            this.tvPlayAll.setVisibility(8);
            this.tvNext.setVisibility(0);
        } else if (i == 501) {
            this.tvPlayAll.setVisibility(0);
            this.textView.setText(this.itemView.getResources().getString(R.string.rec2_title_relax));
            this.tvNext.setVisibility(8);
        } else {
            if (i != 601) {
                return;
            }
            this.tvPlayAll.setVisibility(8);
            this.textView.setText(this.itemView.getResources().getString(R.string.rec2_title_tag));
            this.tvNext.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_next_batch) {
            this.viewModel.refreshBlock(this.type);
        } else if (view.getId() == R.id.tv_play_all) {
            this.viewModel.playAll(this.type);
        }
    }
}
