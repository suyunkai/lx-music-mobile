package com.wanos.careditproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.LayoutSpinnerPlayerShowItem2Binding;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerPosViewAdapter extends ArrayAdapter<String> {
    private LayoutSpinnerPlayerShowItem2Binding binding2;
    private Context context;
    private int selectIndex;

    public PlayerPosViewAdapter(Context context, int i, String[] strArr) {
        super(context, i, strArr);
        this.selectIndex = 0;
        this.context = context;
    }

    public void setSelectIndex(int i) {
        this.selectIndex = i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.layout_spinner_player_show_item2, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.tv_text)).setText(getItem(i));
        return viewInflate;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) super.getDropDownView(i, view, viewGroup);
        if (i == this.selectIndex) {
            textView.setTextColor(this.context.getColor(R.color.edit_color_main));
        } else {
            textView.setTextColor(this.context.getColor(R.color.player_show_tv_color));
        }
        return textView;
    }
}
