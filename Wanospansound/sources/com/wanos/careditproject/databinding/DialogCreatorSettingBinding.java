package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.widget.LongClickImageView;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorSettingBinding implements ViewBinding {
    public final LinearLayout btnBack;
    public final LongClickImageView btnSpeedMinus;
    public final LongClickImageView btnSpeedPlus;
    public final FrameLayout dialogWin;
    public final LinearLayout llBeatNumber;
    public final LinearLayout llBeatSwitch;
    public final LinearLayout llInputDevices;
    public final LinearLayout llSpeed;
    private final FrameLayout rootView;
    public final Switch swichBeat;
    public final TextView tvBeatNumber;
    public final TextView tvBeatSpeed;
    public final TextView tvInputDevices;

    private DialogCreatorSettingBinding(FrameLayout frameLayout, LinearLayout linearLayout, LongClickImageView longClickImageView, LongClickImageView longClickImageView2, FrameLayout frameLayout2, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, Switch r10, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.btnBack = linearLayout;
        this.btnSpeedMinus = longClickImageView;
        this.btnSpeedPlus = longClickImageView2;
        this.dialogWin = frameLayout2;
        this.llBeatNumber = linearLayout2;
        this.llBeatSwitch = linearLayout3;
        this.llInputDevices = linearLayout4;
        this.llSpeed = linearLayout5;
        this.swichBeat = r10;
        this.tvBeatNumber = textView;
        this.tvBeatSpeed = textView2;
        this.tvInputDevices = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorSettingBinding bind(View view) {
        int i = R.id.btn_back;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.btn_speed_minus;
            LongClickImageView longClickImageView = (LongClickImageView) ViewBindings.findChildViewById(view, i);
            if (longClickImageView != null) {
                i = R.id.btn_speed_plus;
                LongClickImageView longClickImageView2 = (LongClickImageView) ViewBindings.findChildViewById(view, i);
                if (longClickImageView2 != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    i = R.id.ll_beat_number;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.ll_beat_switch;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout3 != null) {
                            i = R.id.ll_input_devices;
                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout4 != null) {
                                i = R.id.ll_speed;
                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout5 != null) {
                                    i = R.id.swich_beat;
                                    Switch r13 = (Switch) ViewBindings.findChildViewById(view, i);
                                    if (r13 != null) {
                                        i = R.id.tv_beat_number;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.tv_beat_speed;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                i = R.id.tv_input_devices;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView3 != null) {
                                                    return new DialogCreatorSettingBinding(frameLayout, linearLayout, longClickImageView, longClickImageView2, frameLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, r13, textView, textView2, textView3);
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
