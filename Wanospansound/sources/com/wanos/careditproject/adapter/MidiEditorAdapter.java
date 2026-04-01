package com.wanos.careditproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class MidiEditorAdapter extends RecyclerView.Adapter<MidiEditorViewHolder> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 88;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MidiEditorViewHolder midiEditorViewHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MidiEditorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView recyclerView = new RecyclerView(viewGroup.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), 0, false));
        recyclerView.setAdapter(new MidiEditorNoteListAdapter());
        return new MidiEditorViewHolder(recyclerView);
    }

    public static class MidiEditorViewHolder extends RecyclerView.ViewHolder {
        public MidiEditorViewHolder(View view) {
            super(view);
        }
    }
}
