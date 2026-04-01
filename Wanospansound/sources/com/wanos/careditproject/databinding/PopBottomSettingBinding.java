package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.widget.LongClickImageView;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomSettingBinding implements ViewBinding {
    public final ImageView btnBack;
    public final LongClickImageView btnSpeedMinus;
    public final LongClickImageView btnSpeedPlus;
    public final ConstraintLayout dialogView;
    public final ConstraintLayout dialogWin;
    public final LinearLayout llBeatNumber;
    public final LinearLayout llBeatSwitch;
    public final LinearLayout llInputDevices;
    public final LinearLayout llMidiDevices;
    public final LinearLayout llSpeed;
    private final ConstraintLayout rootView;
    public final ImageView swichBeat;
    public final TextView tvBeatNumber;
    public final TextView tvBeatSpeed;
    public final TextView tvInputDevices;
    public final TextView tvMidiDeviceName;
    public final TextView tvTitleDb;

    private PopBottomSettingBinding(ConstraintLayout constraintLayout, ImageView imageView, LongClickImageView longClickImageView, LongClickImageView longClickImageView2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = constraintLayout;
        this.btnBack = imageView;
        this.btnSpeedMinus = longClickImageView;
        this.btnSpeedPlus = longClickImageView2;
        this.dialogView = constraintLayout2;
        this.dialogWin = constraintLayout3;
        this.llBeatNumber = linearLayout;
        this.llBeatSwitch = linearLayout2;
        this.llInputDevices = linearLayout3;
        this.llMidiDevices = linearLayout4;
        this.llSpeed = linearLayout5;
        this.swichBeat = imageView2;
        this.tvBeatNumber = textView;
        this.tvBeatSpeed = textView2;
        this.tvInputDevices = textView3;
        this.tvMidiDeviceName = textView4;
        this.tvTitleDb = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomSettingBinding bind(View view) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_speed_minus;
            LongClickImageView longClickImageView = (LongClickImageView) ViewBindings.findChildViewById(view, i);
            if (longClickImageView != null) {
                i = R.id.btn_speed_plus;
                LongClickImageView longClickImageView2 = (LongClickImageView) ViewBindings.findChildViewById(view, i);
                if (longClickImageView2 != null) {
                    i = R.id.dialog_view;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                        i = R.id.ll_beat_number;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.ll_beat_switch;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.ll_input_devices;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout3 != null) {
                                    i = R.id.ll_midi_devices;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout4 != null) {
                                        i = R.id.ll_speed;
                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout5 != null) {
                                            i = R.id.swich_beat;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView2 != null) {
                                                i = R.id.tv_beat_number;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView != null) {
                                                    i = R.id.tv_beat_speed;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_input_devices;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_midi_device_name;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView4 != null) {
                                                                i = R.id.tv_title_db;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView5 != null) {
                                                                    return new PopBottomSettingBinding(constraintLayout2, imageView, longClickImageView, longClickImageView2, constraintLayout, constraintLayout2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, imageView2, textView, textView2, textView3, textView4, textView5);
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
