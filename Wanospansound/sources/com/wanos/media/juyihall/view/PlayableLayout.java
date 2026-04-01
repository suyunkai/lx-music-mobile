package com.wanos.media.juyihall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.juyihall.R;

/* JADX INFO: loaded from: classes3.dex */
public class PlayableLayout extends FrameLayout {
    private ImageView ivMusicPlay;
    private ProgressBar pbMusicPlay;

    private void init() {
    }

    public PlayableLayout(Context context) {
        super(context);
        init();
    }

    public PlayableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PlayableLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public PlayableLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivMusicPlay = (ImageView) findViewById(R.id.iv_music_play);
        this.pbMusicPlay = (ProgressBar) findViewById(R.id.pb_music_play);
    }

    public void showAsPlay() {
        this.ivMusicPlay.setVisibility(8);
        this.pbMusicPlay.setVisibility(8);
        GlideUtil.setImageGifData(com.wanos.media.R.drawable.ic_playing_2, this.ivMusicPlay);
        this.ivMusicPlay.setVisibility(0);
    }

    public void showAsPause() {
        this.ivMusicPlay.setImageResource(com.wanos.media.R.drawable.group_card_pause);
        this.ivMusicPlay.setVisibility(0);
        this.pbMusicPlay.setVisibility(8);
    }

    public void showAsLoading() {
        this.ivMusicPlay.setVisibility(8);
        this.pbMusicPlay.setVisibility(0);
    }
}
