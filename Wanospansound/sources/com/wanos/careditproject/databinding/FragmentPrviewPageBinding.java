package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.playerUI.Player3dGLView;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentPrviewPageBinding implements ViewBinding {
    public final ImageView btnPlayPreview;
    public final ProgressBar pbLoading;
    public final Player3dGLView play3dGlView;
    private final ConstraintLayout rootView;
    public final SeekBar seekbarProgress;
    public final Spinner spinnerShowType;
    public final TextView textPlayTime;
    public final TextView textPlayTime0;
    public final ConstraintLayout viewLoading;

    private FragmentPrviewPageBinding(ConstraintLayout constraintLayout, ImageView imageView, ProgressBar progressBar, Player3dGLView player3dGLView, SeekBar seekBar, Spinner spinner, TextView textView, TextView textView2, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.btnPlayPreview = imageView;
        this.pbLoading = progressBar;
        this.play3dGlView = player3dGLView;
        this.seekbarProgress = seekBar;
        this.spinnerShowType = spinner;
        this.textPlayTime = textView;
        this.textPlayTime0 = textView2;
        this.viewLoading = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPrviewPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPrviewPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_prview_page, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPrviewPageBinding bind(View view) {
        int i = R.id.btn_play_preview;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.pb_loading;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.play3d_gl_view;
                Player3dGLView player3dGLView = (Player3dGLView) ViewBindings.findChildViewById(view, i);
                if (player3dGLView != null) {
                    i = R.id.seekbar_progress;
                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
                    if (seekBar != null) {
                        i = R.id.spinner_show_type;
                        Spinner spinner = (Spinner) ViewBindings.findChildViewById(view, i);
                        if (spinner != null) {
                            i = R.id.text_play_time;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.text_play_time0;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.view_loading;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout != null) {
                                        return new FragmentPrviewPageBinding((ConstraintLayout) view, imageView, progressBar, player3dGLView, seekBar, spinner, textView, textView2, constraintLayout);
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
