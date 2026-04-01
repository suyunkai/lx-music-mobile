package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogAddSourceAudioBinding implements ViewBinding {
    public final ImageView ivAudio;
    public final ImageView ivMidi;
    private final ConstraintLayout rootView;
    public final TextView tvAddInstrumentParts;
    public final TextView tvAudioAndRecord;
    public final TextView tvAudioTrack;
    public final TextView tvInstrumentTrack;
    public final TextView tvMidi;
    public final View viewInstrument;
    public final View viewMidi;

    private DialogAddSourceAudioBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, View view, View view2) {
        this.rootView = constraintLayout;
        this.ivAudio = imageView;
        this.ivMidi = imageView2;
        this.tvAddInstrumentParts = textView;
        this.tvAudioAndRecord = textView2;
        this.tvAudioTrack = textView3;
        this.tvInstrumentTrack = textView4;
        this.tvMidi = textView5;
        this.viewInstrument = view;
        this.viewMidi = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogAddSourceAudioBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAddSourceAudioBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_add_source_audio, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAddSourceAudioBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.iv_audio;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_midi;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.tv_add_instrument_parts;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_audio_and_record;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.tv_audio_track;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.tv_instrument_track;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.tv_midi;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_instrument))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.view_midi))) != null) {
                                    return new DialogAddSourceAudioBinding((ConstraintLayout) view, imageView, imageView2, textView, textView2, textView3, textView4, textView5, viewFindChildViewById, viewFindChildViewById2);
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
