package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMusicGroupDetailsBinding implements ViewBinding {
    public final FrameLayout btnCollect;
    public final LinearLayout btnPlay;
    public final ImageFilterView imTopCard;
    public final ImageView ivCollect;
    public final ImageView ivPlay;
    public final View leftLine2;
    public final View rightLine2;
    private final ConstraintLayout rootView;
    public final TextView tvMusicGroupName;
    public final TextView tvPlay;

    private LayoutMusicGroupDetailsBinding(ConstraintLayout rootView, FrameLayout btnCollect, LinearLayout btnPlay, ImageFilterView imTopCard, ImageView ivCollect, ImageView ivPlay, View leftLine2, View rightLine2, TextView tvMusicGroupName, TextView tvPlay) {
        this.rootView = rootView;
        this.btnCollect = btnCollect;
        this.btnPlay = btnPlay;
        this.imTopCard = imTopCard;
        this.ivCollect = ivCollect;
        this.ivPlay = ivPlay;
        this.leftLine2 = leftLine2;
        this.rightLine2 = rightLine2;
        this.tvMusicGroupName = tvMusicGroupName;
        this.tvPlay = tvPlay;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMusicGroupDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMusicGroupDetailsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_music_group_details, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMusicGroupDetailsBinding bind(View rootView) {
        int i = R.id.btn_collect;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_collect);
        if (frameLayout != null) {
            i = R.id.btn_play;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_play);
            if (linearLayout != null) {
                i = R.id.im_top_card;
                ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, R.id.im_top_card);
                if (imageFilterView != null) {
                    i = R.id.iv_collect;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                    if (imageView != null) {
                        i = R.id.iv_play;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_play);
                        if (imageView2 != null) {
                            i = R.id.left_line2;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.left_line2);
                            if (viewFindChildViewById != null) {
                                i = R.id.right_line2;
                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.right_line2);
                                if (viewFindChildViewById2 != null) {
                                    i = R.id.tv_music_group_name;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_group_name);
                                    if (textView != null) {
                                        i = R.id.tv_play;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_play);
                                        if (textView2 != null) {
                                            return new LayoutMusicGroupDetailsBinding((ConstraintLayout) rootView, frameLayout, linearLayout, imageFilterView, imageView, imageView2, viewFindChildViewById, viewFindChildViewById2, textView, textView2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
