package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class LiveHouseTypeAdapter extends RecyclerView.Adapter<LiveHouseTypeHolder> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LiveHouseTypeHolder liveHouseTypeHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LiveHouseTypeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LiveHouseTypeHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_live_house_type, viewGroup, false));
    }

    static class LiveHouseTypeHolder extends RecyclerView.ViewHolder {
        public LiveHouseTypeHolder(View view) {
            super(view);
        }
    }
}
