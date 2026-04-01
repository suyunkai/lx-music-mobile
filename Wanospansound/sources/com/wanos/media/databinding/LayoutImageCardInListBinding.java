package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.media.R;
import com.wanos.media.ui.widget.ImageCardView;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutImageCardInListBinding implements ViewBinding {
    public final AppCompatImageView btnMore;
    public final FrameLayout btnPlay;
    public final CardView cdItemView;
    public final ImageCardView imCover;
    public final ImageView imLeftTop;
    public final LottieAnimationView ivPlayState;
    public final ProgressBar pbAudiobookPlay;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;
    public final TextView tvTitleSub;

    private LayoutImageCardInListBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, FrameLayout frameLayout, CardView cardView, ImageCardView imageCardView, ImageView imageView, LottieAnimationView lottieAnimationView, ProgressBar progressBar, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.btnMore = appCompatImageView;
        this.btnPlay = frameLayout;
        this.cdItemView = cardView;
        this.imCover = imageCardView;
        this.imLeftTop = imageView;
        this.ivPlayState = lottieAnimationView;
        this.pbAudiobookPlay = progressBar;
        this.tvTitle = textView;
        this.tvTitleSub = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutImageCardInListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutImageCardInListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_image_card_in_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutImageCardInListBinding bind(View view) {
        int i = R.id.btn_more;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.btn_play;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.cd_item_view;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.im_cover;
                    ImageCardView imageCardView = (ImageCardView) ViewBindings.findChildViewById(view, i);
                    if (imageCardView != null) {
                        i = R.id.im_left_top;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.iv_play_state;
                            LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                            if (lottieAnimationView != null) {
                                i = R.id.pb_audiobook_play;
                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                if (progressBar != null) {
                                    i = R.id.tv_title;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView != null) {
                                        i = R.id.tv_title_sub;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            return new LayoutImageCardInListBinding((ConstraintLayout) view, appCompatImageView, frameLayout, cardView, imageCardView, imageView, lottieAnimationView, progressBar, textView, textView2);
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
