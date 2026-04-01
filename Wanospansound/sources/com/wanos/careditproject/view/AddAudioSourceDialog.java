package com.wanos.careditproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class AddAudioSourceDialog extends PopupWindow {
    private OnSourceSelectedListener listener;

    public interface OnSourceSelectedListener {
        void onInstrumentSelected();

        void onMidiSelected();
    }

    public void setOnSourceSelectedListener(OnSourceSelectedListener onSourceSelectedListener) {
        this.listener = onSourceSelectedListener;
    }

    public AddAudioSourceDialog(Context context) {
        super(-2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_add_source_audio, (ViewGroup) null);
        setContentView(viewInflate);
        setOutsideTouchable(true);
        setFocusable(true);
        viewInflate.findViewById(R.id.view_instrument).setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.AddAudioSourceDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AddAudioSourceDialog.this.listener != null) {
                    AddAudioSourceDialog.this.listener.onInstrumentSelected();
                    AddAudioSourceDialog.this.dismiss();
                }
            }
        });
        viewInflate.findViewById(R.id.view_midi).setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.AddAudioSourceDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AddAudioSourceDialog.this.listener != null) {
                    AddAudioSourceDialog.this.listener.onMidiSelected();
                    AddAudioSourceDialog.this.dismiss();
                }
            }
        });
    }
}
