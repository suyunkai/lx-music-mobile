package com.wanosj.musicsourceview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public class MusicSourceContentProvider {
    public static int buildIndex;

    public static View getMusicSource(Context context, int i) {
        buildIndex = i;
        return LayoutInflater.from(context).inflate(R.layout.layout_music_source, (ViewGroup) null);
    }

    public static View getIconView(View view) {
        return view.findViewById(R.id.icon_image_view);
    }

    public static View getContainer(View view) {
        return view.findViewById(R.id.music_source_pressed_item);
    }

    public static View getArrow(View view) {
        return view.findViewById(R.id.arrow_image_view);
    }
}
