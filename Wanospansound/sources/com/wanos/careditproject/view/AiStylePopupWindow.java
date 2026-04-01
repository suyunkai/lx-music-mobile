package com.wanos.careditproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.AiStyleAdapter;
import com.wanos.careditproject.databinding.PopupWindowAiStyleBinding;

/* JADX INFO: loaded from: classes3.dex */
public class AiStylePopupWindow extends PopupWindow {
    private final AiStyleAdapter adapter;

    public AiStylePopupWindow(Context context, int i, String str) {
        super(context.getResources().getDimensionPixelOffset(R.dimen.ai_window_width), context.getResources().getDimensionPixelOffset(R.dimen.ai_window_height));
        PopupWindowAiStyleBinding popupWindowAiStyleBindingInflate = PopupWindowAiStyleBinding.inflate(LayoutInflater.from(context), null, false);
        setContentView(popupWindowAiStyleBindingInflate.getRoot());
        setOutsideTouchable(true);
        popupWindowAiStyleBindingInflate.rvAiType.setLayoutManager(new LinearLayoutManager(context));
        AiStyleAdapter aiStyleAdapter = new AiStyleAdapter(i, str);
        this.adapter = aiStyleAdapter;
        popupWindowAiStyleBindingInflate.rvAiType.setAdapter(aiStyleAdapter);
    }

    public void setItemClickListener(AiStyleAdapter.OnItemClickListener onItemClickListener) {
        this.adapter.setItemClickListener(onItemClickListener);
    }
}
