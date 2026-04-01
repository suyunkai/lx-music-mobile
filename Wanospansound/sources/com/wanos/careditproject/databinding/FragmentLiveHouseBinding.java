package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentLiveHouseBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final RecyclerView rvClass;
    public final RecyclerView rvLiveHouse;
    public final RecyclerView rvRank;
    public final TextView tvClass;
    public final TextView tvDifficult;

    private FragmentLiveHouseBinding(ConstraintLayout constraintLayout, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.rvClass = recyclerView;
        this.rvLiveHouse = recyclerView2;
        this.rvRank = recyclerView3;
        this.tvClass = textView;
        this.tvDifficult = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLiveHouseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentLiveHouseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_live_house, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentLiveHouseBinding bind(View view) {
        int i = R.id.rv_class;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.rv_live_house;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView2 != null) {
                i = R.id.rv_rank;
                RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView3 != null) {
                    i = R.id.tv_class;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tv_difficult;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            return new FragmentLiveHouseBinding((ConstraintLayout) view, recyclerView, recyclerView2, recyclerView3, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
