package com.wanos.careditproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class MidiEditorNoteListAdapter extends RecyclerView.Adapter<MidiEditorNoteViewHolder> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1000;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MidiEditorNoteViewHolder midiEditorNoteViewHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MidiEditorNoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MidiEditorNoteViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_midi_editor_note_item, viewGroup, false));
    }

    static class MidiEditorNoteViewHolder extends RecyclerView.ViewHolder {
        public MidiEditorNoteViewHolder(View view) {
            super(view);
        }
    }
}
