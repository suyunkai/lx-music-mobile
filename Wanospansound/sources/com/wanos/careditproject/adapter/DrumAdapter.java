package com.wanos.careditproject.adapter;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class DrumAdapter extends RecyclerView.Adapter<DrumHolder> {
    private static final int[] DRUMS_ICON = {R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_digu, R.drawable.icon_btn_bianji, R.drawable.icon_btn_paishou, R.drawable.icon_btn_qiangyinca, R.drawable.icon_btn_jungu, R.drawable.icon_btn_niuling, R.drawable.icon_btn_bica, R.drawable.icon_btn_kaica, R.drawable.icon_btn_bianji};
    private final HashSet<Integer> pressedPos = new HashSet<>();
    private int themeColor;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DrumHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DrumHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_instrument, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DrumHolder drumHolder, int i) {
        drumHolder.ivIcon.setImageResource(DRUMS_ICON[i]);
        if (this.pressedPos.contains(Integer.valueOf(i))) {
            drumHolder.itemView.setBackground(new ColorDrawable(this.themeColor));
        } else {
            drumHolder.itemView.setBackground(new ColorDrawable(-1));
        }
    }

    public void setThemeColor(int i) {
        this.themeColor = i;
    }

    public void addPressedPos(int i) {
        this.pressedPos.add(Integer.valueOf(i));
    }

    public void removePressedPos(int i) {
        this.pressedPos.remove(Integer.valueOf(i));
    }

    static class DrumHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;

        public DrumHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.instrument_name);
            this.ivIcon = (ImageView) view.findViewById(R.id.instrument_icon);
        }
    }
}
