package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogPlayerSpeedSettingBinding implements ViewBinding {
    public final LinearLayout btn0;
    public final LinearLayout btn1;
    public final LinearLayout btn2;
    public final LinearLayout btn3;
    public final LinearLayout btn4;
    public final LinearLayout btn5;
    public final ImageView btnBack;
    public final CardView cvView;
    public final ImageView iv0;
    public final ImageView iv1;
    public final ImageView iv2;
    public final ImageView iv3;
    public final ImageView iv4;
    public final ImageView iv5;
    private final FrameLayout rootView;
    public final TextView tvAudioBookName;
    public final View viewWin;

    private DialogPlayerSpeedSettingBinding(FrameLayout rootView, LinearLayout btn0, LinearLayout btn1, LinearLayout btn2, LinearLayout btn3, LinearLayout btn4, LinearLayout btn5, ImageView btnBack, CardView cvView, ImageView iv0, ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5, TextView tvAudioBookName, View viewWin) {
        this.rootView = rootView;
        this.btn0 = btn0;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.btn5 = btn5;
        this.btnBack = btnBack;
        this.cvView = cvView;
        this.iv0 = iv0;
        this.iv1 = iv1;
        this.iv2 = iv2;
        this.iv3 = iv3;
        this.iv4 = iv4;
        this.iv5 = iv5;
        this.tvAudioBookName = tvAudioBookName;
        this.viewWin = viewWin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogPlayerSpeedSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogPlayerSpeedSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_player_speed_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogPlayerSpeedSettingBinding bind(View rootView) {
        int i = R.id.btn_0;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_0);
        if (linearLayout != null) {
            i = R.id.btn_1;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_1);
            if (linearLayout2 != null) {
                i = R.id.btn_2;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_2);
                if (linearLayout3 != null) {
                    i = R.id.btn_3;
                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_3);
                    if (linearLayout4 != null) {
                        i = R.id.btn_4;
                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_4);
                        if (linearLayout5 != null) {
                            i = R.id.btn_5;
                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_5);
                            if (linearLayout6 != null) {
                                i = R.id.btn_back;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
                                if (imageView != null) {
                                    i = R.id.cv_view;
                                    CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cv_view);
                                    if (cardView != null) {
                                        i = R.id.iv_0;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_0);
                                        if (imageView2 != null) {
                                            i = R.id.iv_1;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_1);
                                            if (imageView3 != null) {
                                                i = R.id.iv_2;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_2);
                                                if (imageView4 != null) {
                                                    i = R.id.iv_3;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_3);
                                                    if (imageView5 != null) {
                                                        i = R.id.iv_4;
                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_4);
                                                        if (imageView6 != null) {
                                                            i = R.id.iv_5;
                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_5);
                                                            if (imageView7 != null) {
                                                                i = R.id.tv_audio_book_name;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_name);
                                                                if (textView != null) {
                                                                    i = R.id.view_win;
                                                                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.view_win);
                                                                    if (viewFindChildViewById != null) {
                                                                        return new DialogPlayerSpeedSettingBinding((FrameLayout) rootView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, imageView, cardView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, textView, viewFindChildViewById);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
