package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.audiotrackedit.editView.RecordWaveView;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomRecordBinding implements ViewBinding {
    public final ImageView btnCloseRecord;
    public final ImageView btnDelete;
    public final FrameLayout btnPlay;
    public final ImageView btnPlayImg;
    public final LottieAnimationView btnPlayLottie;
    public final ImageView btnRecord;
    public final ImageView btnSelect;
    private final LinearLayout rootView;
    public final TextView tvRecord;
    public final TextView tvTimeLast;
    public final TextView tvTimePre;
    public final TextView tvTitleSpeed;
    public final LinearLayout viewPlayer;
    public final LinearLayout viewRecord;
    public final RecordWaveView viewRecordWav;
    public final ConstraintLayout viewRoot;
    public final View viewTop;

    private PopBottomRecordBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, FrameLayout frameLayout, ImageView imageView3, LottieAnimationView lottieAnimationView, ImageView imageView4, ImageView imageView5, TextView textView, TextView textView2, TextView textView3, TextView textView4, LinearLayout linearLayout2, LinearLayout linearLayout3, RecordWaveView recordWaveView, ConstraintLayout constraintLayout, View view) {
        this.rootView = linearLayout;
        this.btnCloseRecord = imageView;
        this.btnDelete = imageView2;
        this.btnPlay = frameLayout;
        this.btnPlayImg = imageView3;
        this.btnPlayLottie = lottieAnimationView;
        this.btnRecord = imageView4;
        this.btnSelect = imageView5;
        this.tvRecord = textView;
        this.tvTimeLast = textView2;
        this.tvTimePre = textView3;
        this.tvTitleSpeed = textView4;
        this.viewPlayer = linearLayout2;
        this.viewRecord = linearLayout3;
        this.viewRecordWav = recordWaveView;
        this.viewRoot = constraintLayout;
        this.viewTop = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_record, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomRecordBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_close_record;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_delete;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.btn_play;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.btn_play_img;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView3 != null) {
                        i = R.id.btn_play_lottie;
                        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                        if (lottieAnimationView != null) {
                            i = R.id.btn_record;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView4 != null) {
                                i = R.id.btn_select;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView5 != null) {
                                    i = R.id.tv_record;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView != null) {
                                        i = R.id.tv_time_last;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            i = R.id.tv_time_pre;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView3 != null) {
                                                i = R.id.tv_title_speed;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView4 != null) {
                                                    i = R.id.view_player;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                    if (linearLayout != null) {
                                                        i = R.id.view_record;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.view_record_wav;
                                                            RecordWaveView recordWaveView = (RecordWaveView) ViewBindings.findChildViewById(view, i);
                                                            if (recordWaveView != null) {
                                                                i = R.id.view_root;
                                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                if (constraintLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_top))) != null) {
                                                                    return new PopBottomRecordBinding((LinearLayout) view, imageView, imageView2, frameLayout, imageView3, lottieAnimationView, imageView4, imageView5, textView, textView2, textView3, textView4, linearLayout, linearLayout2, recordWaveView, constraintLayout, viewFindChildViewById);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
