package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class LiveHouseDifficultAdapter extends RecyclerView.Adapter<LiveHouseDifficultHolder> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LiveHouseDifficultHolder liveHouseDifficultHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LiveHouseDifficultHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LiveHouseDifficultHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_live_house_difficult, viewGroup, false));
    }

    static class LiveHouseDifficultHolder extends RecyclerView.ViewHolder {
        public LiveHouseDifficultHolder(View view) {
            super(view);
        }
    }
}
