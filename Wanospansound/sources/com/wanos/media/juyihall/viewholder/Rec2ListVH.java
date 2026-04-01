package com.wanos.media.juyihall.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.juyihall.R;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2ListVH extends Rec2BaseVH {
    public RecyclerView recyclerView;

    public Rec2ListVH(View view) {
        super(view);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }
}
