package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.wanos.careditproject.databinding.PopPcmToolbarBinding;

/* JADX INFO: loaded from: classes3.dex */
public class PcmWaveViewTools extends LinearLayout {
    PopPcmToolbarBinding binding;

    public PcmWaveViewTools(Context context) {
        super(context);
    }

    public PcmWaveViewTools(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void init(Context context) {
        this.binding = PopPcmToolbarBinding.inflate(LayoutInflater.from(context), this, true);
    }
}
