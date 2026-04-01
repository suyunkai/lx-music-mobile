package com.wanos.media.juyihall.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.commonlibrary.bean.IPlayable;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.view.PlayableLayout;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2PlayableVH<T extends IPlayable> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected T mData;
    private PlayableLayout playableLayout;

    protected void realPlay() {
    }

    public Rec2PlayableVH(View view) {
        super(view);
        this.playableLayout = (PlayableLayout) view.findViewById(R.id.btn_music_play);
        view.setOnClickListener(this);
        this.playableLayout.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_music_play) {
            if (this.mData.getPlayStatus() == null || this.mData.getPlayStatus() == MediaPlayerEnum.CallBackState.COMPLETE) {
                startPlay();
            } else if (this.mData.getPlayStatus() == MediaPlayerEnum.CallBackState.PAUSE) {
                ServiceRouter.getMediaPlayService().resume();
            } else if (this.mData.getPlayStatus() == MediaPlayerEnum.CallBackState.STARTED) {
                ServiceRouter.getMediaPlayService().pause();
            }
        }
    }

    protected void startPlay() {
        if (this.mData == null) {
            return;
        }
        realPlay();
    }

    public void bindData(T t) {
        this.mData = t;
        setCallBackState(t.getPlayStatus());
    }

    private void setCallBackState(MediaPlayerEnum.CallBackState callBackState) {
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            this.playableLayout.showAsLoading();
            return;
        }
        if (callBackState == null || callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.playableLayout.showAsPause();
        } else if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            this.playableLayout.showAsPlay();
        }
    }
}
