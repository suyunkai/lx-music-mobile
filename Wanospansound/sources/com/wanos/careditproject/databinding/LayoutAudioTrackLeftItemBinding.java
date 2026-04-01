package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAudioTrackLeftItemBinding implements ViewBinding {
    public final TextView btnM;
    public final TextView btnS;
    public final CardView cardView;
    public final LinearLayout itemView;
    public final ImageView ivInstrument;
    public final ImageView ivMore;
    public final ConstraintLayout ivShowPos;
    public final ImageView ivShowPosIcon;
    public final LinearLayout llMore;
    public final ConstraintLayout llNameAndGain;
    public final LinearLayout llShowPos;
    public final View rectangle1;
    public final View rectangle2;
    private final FrameLayout rootView;
    public final SeekBar seekBarGain;
    public final TextView tvName;

    private LayoutAudioTrackLeftItemBinding(FrameLayout frameLayout, TextView textView, TextView textView2, CardView cardView, LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, ImageView imageView3, LinearLayout linearLayout2, ConstraintLayout constraintLayout2, LinearLayout linearLayout3, View view, View view2, SeekBar seekBar, TextView textView3) {
        this.rootView = frameLayout;
        this.btnM = textView;
        this.btnS = textView2;
        this.cardView = cardView;
        this.itemView = linearLayout;
        this.ivInstrument = imageView;
        this.ivMore = imageView2;
        this.ivShowPos = constraintLayout;
        this.ivShowPosIcon = imageView3;
        this.llMore = linearLayout2;
        this.llNameAndGain = constraintLayout2;
        this.llShowPos = linearLayout3;
        this.rectangle1 = view;
        this.rectangle2 = view2;
        this.seekBarGain = seekBar;
        this.tvName = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioTrackLeftItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutAudioTrackLeftItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_audio_track_left_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioTrackLeftItemBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.btn_m;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.btn_s;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.card_view;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.item_view;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.iv_instrument;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.iv_more;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView2 != null) {
                                i = R.id.iv_show_pos;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                if (constraintLayout != null) {
                                    i = R.id.iv_show_pos_icon;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView3 != null) {
                                        i = R.id.ll_more;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout2 != null) {
                                            i = R.id.ll_name_and_gain;
                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                            if (constraintLayout2 != null) {
                                                i = R.id.ll_show_pos;
                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                if (linearLayout3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.rectangle_1))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.rectangle_2))) != null) {
                                                    i = R.id.seek_bar_gain;
                                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
                                                    if (seekBar != null) {
                                                        i = R.id.tv_name;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView3 != null) {
                                                            return new LayoutAudioTrackLeftItemBinding((FrameLayout) view, textView, textView2, cardView, linearLayout, imageView, imageView2, constraintLayout, imageView3, linearLayout2, constraintLayout2, linearLayout3, viewFindChildViewById, viewFindChildViewById2, seekBar, textView3);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
