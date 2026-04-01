package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class MaterialListItemBinding implements ViewBinding {
    public final FrameLayout btnPlay;
    public final ImageView materialAdd;
    public final ImageView materialCollect;
    public final TextView materialMusicName;
    public final ImageView materialPlay;
    public final LottieAnimationView materialPlayState;
    public final TextView materialSize;
    public final TextView materialTotalTime;
    public final ProgressBar pbPlay;
    private final FrameLayout rootView;
    public final ConstraintLayout viewItem;

    private MaterialListItemBinding(FrameLayout frameLayout, FrameLayout frameLayout2, ImageView imageView, ImageView imageView2, TextView textView, ImageView imageView3, LottieAnimationView lottieAnimationView, TextView textView2, TextView textView3, ProgressBar progressBar, ConstraintLayout constraintLayout) {
        this.rootView = frameLayout;
        this.btnPlay = frameLayout2;
        this.materialAdd = imageView;
        this.materialCollect = imageView2;
        this.materialMusicName = textView;
        this.materialPlay = imageView3;
        this.materialPlayState = lottieAnimationView;
        this.materialSize = textView2;
        this.materialTotalTime = textView3;
        this.pbPlay = progressBar;
        this.viewItem = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static MaterialListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MaterialListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.material_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MaterialListItemBinding bind(View view) {
        int i = R.id.btn_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.material_add;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.material_collect;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView2 != null) {
                    i = R.id.material_music_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.material_play;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView3 != null) {
                            i = R.id.material_play_state;
                            LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                            if (lottieAnimationView != null) {
                                i = R.id.material_size;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.material_total_time;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView3 != null) {
                                        i = R.id.pb_play;
                                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                        if (progressBar != null) {
                                            i = R.id.view_item;
                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                            if (constraintLayout != null) {
                                                return new MaterialListItemBinding((FrameLayout) view, frameLayout, imageView, imageView2, textView, imageView3, lottieAnimationView, textView2, textView3, progressBar, constraintLayout);
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
