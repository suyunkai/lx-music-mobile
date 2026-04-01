package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.view.DailyMusicView;
import com.wanos.media.ui.widget.banner.Banner;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutRec2HeaderBinding implements ViewBinding {
    public final Banner banner;
    public final CardView bannerLayout;
    public final DailyMusicView dailyView;
    private final ConstraintLayout rootView;
    public final TextView tvCollect;
    public final TextView tvRecent;

    private LayoutRec2HeaderBinding(ConstraintLayout constraintLayout, Banner banner, CardView cardView, DailyMusicView dailyMusicView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.banner = banner;
        this.bannerLayout = cardView;
        this.dailyView = dailyMusicView;
        this.tvCollect = textView;
        this.tvRecent = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutRec2HeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutRec2HeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_rec2_header, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutRec2HeaderBinding bind(View view) {
        int i = R.id.banner;
        Banner banner = (Banner) ViewBindings.findChildViewById(view, i);
        if (banner != null) {
            i = R.id.banner_layout;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.daily_view;
                DailyMusicView dailyMusicView = (DailyMusicView) ViewBindings.findChildViewById(view, i);
                if (dailyMusicView != null) {
                    i = R.id.tv_collect;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tv_recent;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            return new LayoutRec2HeaderBinding((ConstraintLayout) view, banner, cardView, dailyMusicView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
