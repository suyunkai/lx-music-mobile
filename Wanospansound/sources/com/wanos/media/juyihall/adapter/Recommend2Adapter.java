package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.Recommend2ViewModel;
import com.wanos.media.juyihall.view.EqualLengthItemDecoration;
import com.wanos.media.juyihall.viewholder.Rec2BaseVH;
import com.wanos.media.juyihall.viewholder.Rec2HeaderVH;
import com.wanos.media.juyihall.viewholder.Rec2ListVH;
import com.wanos.media.juyihall.viewholder.Rec2TitleVH;
import com.wanos.media.ui.widget.banner.indicator.RectangleIndicator;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class Recommend2Adapter extends BaseListAdapter<Integer, Rec2BaseVH> {
    Recommend2ViewModel viewModel;

    public Recommend2Adapter(Recommend2ViewModel recommend2ViewModel) {
        this.viewModel = recommend2ViewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2BaseVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.Adapter rec2MusicAdapter;
        if (i == 1) {
            return new Rec2TitleVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_title, viewGroup, false));
        }
        if (i == 102) {
            Rec2HeaderVH rec2HeaderVH = new Rec2HeaderVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_rec2_header, viewGroup, false));
            rec2HeaderVH.banner.setAdapter(new RecBannerViewAdapter(viewGroup.getContext(), new ArrayList()));
            rec2HeaderVH.banner.setIndicator(new RectangleIndicator(viewGroup.getContext()));
            return rec2HeaderVH;
        }
        EqualLengthItemDecoration equalLengthItemDecoration = new EqualLengthItemDecoration();
        equalLengthItemDecoration.setItemSpace(viewGroup.getResources().getDimensionPixelOffset(R.dimen.rec2_common_item_padding));
        Rec2ListVH rec2ListVH = new Rec2ListVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_common_list, viewGroup, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewGroup.getContext(), 0, false);
        if (i == 202) {
            rec2MusicAdapter = new Rec2MusicAdapter();
        } else if (i == 302) {
            rec2MusicAdapter = new Rec2RatioAdapter();
        } else if (i == 402) {
            equalLengthItemDecoration.setItemSpace(viewGroup.getResources().getDimensionPixelOffset(R.dimen.rec2_album_item_padding));
            rec2MusicAdapter = new Rec2AlbumAdapter();
        } else if (i == 602) {
            rec2MusicAdapter = new Rec2TagsAdapter();
        } else {
            rec2MusicAdapter = new Rec2RelaxAdapter();
        }
        rec2ListVH.recyclerView.addItemDecoration(equalLengthItemDecoration);
        rec2ListVH.recyclerView.setLayoutManager(linearLayoutManager);
        rec2ListVH.recyclerView.setAdapter(rec2MusicAdapter);
        return rec2ListVH;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2BaseVH rec2BaseVH, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) rec2BaseVH.itemView.getLayoutParams();
        marginLayoutParams.topMargin = i == 0 ? rec2BaseVH.itemView.getResources().getDimensionPixelOffset(R.dimen.rec_page_top_margin) : 0;
        marginLayoutParams.bottomMargin = i == getItemCount() + (-1) ? rec2BaseVH.itemView.getResources().getDimensionPixelOffset(R.dimen.rec2_common_item_padding) : 0;
        if (rec2BaseVH instanceof Rec2ListVH) {
            BaseListAdapter baseListAdapter = (BaseListAdapter) ((Rec2ListVH) rec2BaseVH).recyclerView.getAdapter();
            int iIntValue = getItem(i).intValue();
            if (iIntValue == 202) {
                baseListAdapter.setData(this.viewModel.musicList);
                baseListAdapter.notifyDataSetChanged();
                return;
            }
            if (iIntValue == 302) {
                baseListAdapter.setData(this.viewModel.radioList);
                baseListAdapter.notifyDataSetChanged();
                return;
            }
            if (iIntValue == 402) {
                baseListAdapter.setData(this.viewModel.albumList);
                baseListAdapter.notifyDataSetChanged();
                return;
            } else if (iIntValue == 502) {
                baseListAdapter.setData(this.viewModel.relaxList);
                baseListAdapter.notifyDataSetChanged();
                return;
            } else {
                if (iIntValue != 602) {
                    return;
                }
                baseListAdapter.setData(this.viewModel.tagList);
                baseListAdapter.notifyDataSetChanged();
                return;
            }
        }
        int iIntValue2 = getItem(i).intValue();
        if (iIntValue2 == 102) {
            Rec2HeaderVH rec2HeaderVH = (Rec2HeaderVH) rec2BaseVH;
            rec2HeaderVH.banner.setDatas(this.viewModel.bannerList);
            rec2HeaderVH.dailyMusicView.refresh();
        } else if (iIntValue2 == 201 || iIntValue2 == 301 || iIntValue2 == 401 || iIntValue2 == 501 || iIntValue2 == 601) {
            ((Rec2TitleVH) rec2BaseVH).setType(getItem(i).intValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (getItem(i).intValue() % 2 == 1) {
            return 1;
        }
        return getItem(i).intValue();
    }
}
