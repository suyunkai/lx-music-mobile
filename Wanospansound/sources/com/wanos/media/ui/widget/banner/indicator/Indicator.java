package com.wanos.media.ui.widget.banner.indicator;

import android.view.View;
import com.wanos.media.ui.widget.banner.config.IndicatorConfig;
import com.wanos.media.ui.widget.banner.listener.OnPageChangeListener;

/* JADX INFO: loaded from: classes3.dex */
public interface Indicator extends OnPageChangeListener {
    IndicatorConfig getIndicatorConfig();

    View getIndicatorView();

    void onPageChanged(int i, int i2);
}
