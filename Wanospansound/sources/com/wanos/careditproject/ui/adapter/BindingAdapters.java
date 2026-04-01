package com.wanos.careditproject.ui.adapter;

import android.widget.ImageView;
import com.wanos.commonlibrary.utils.GlideOptions;

/* JADX INFO: loaded from: classes3.dex */
public class BindingAdapters {
    public static void setImageUrl(ImageView imageView, String str) {
        new GlideOptions().onLoad(str, imageView);
    }
}
