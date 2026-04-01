package com.wanos.media.ui.music;

import android.widget.ImageView;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class MusicTagStateHelp {
    private static final String TAG = "wanos[MusicTagStateHelp]";

    public static void initMusicTagState(boolean isVip, boolean isFree, ImageView imageView) {
        int i = isVip ? isFree ? R.drawable.ic_limited_free : R.drawable.ic_vip : -1;
        if (i == -1) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            imageView.setImageResource(i);
        }
    }
}
