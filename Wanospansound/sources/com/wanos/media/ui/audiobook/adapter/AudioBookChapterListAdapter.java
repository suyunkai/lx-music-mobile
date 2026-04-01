package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ActivityUtils;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter;
import com.wanos.media.util.AnitClick;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChapterListAdapter extends AudioBookChapterListBaseAdapter<AudioBookChapterItemBean, ViewHolder> {
    protected AudioBookAlbumInfoBean albumInfoBean;
    protected long playChapterId;

    public AudioBookChapterListAdapter(Context context, List<AudioBookChapterItemBean> datas) {
        super(context, datas);
        this.playChapterId = -1L;
    }

    public long getPlayChapterId() {
        return this.playChapterId;
    }

    public void setPlayChapterId(long playChapterId) {
        this.playChapterId = playChapterId;
    }

    public AudioBookAlbumInfoBean getAlbumInfoBean() {
        return this.albumInfoBean;
    }

    public void setAlbumInfoBean(AudioBookAlbumInfoBean albumInfoBean) {
        this.albumInfoBean = albumInfoBean;
    }

    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_album_chapter_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter
    public void updateCollect(ViewHolder holder, AudioBookChapterItemBean item) {
        if (item.getIsCollect() == 1) {
            holder.ivCollect.setImageResource(R.drawable.ic_collect);
        } else {
            holder.ivCollect.setImageResource(R.drawable.ic_no_collect);
        }
    }

    protected boolean checkCurIsPlaying(long albumId) {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        return mediaPlayerService != null && mediaPlayerService.getCurMediaInfo() != null && mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook && mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getRadioId() == albumId;
    }

    protected void updateChapterName(ViewHolder holder, boolean isMove) {
        if (isMove) {
            holder.tvName.setMarqueeRepeatLimit(-1);
            holder.tvName.setSingleLine();
            holder.tvName.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            return;
        }
        holder.tvName.setEllipsize(TextUtils.TruncateAt.END);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        final AudioBookChapterItemBean audioBookChapterItemBean = (AudioBookChapterItemBean) this.datas.get(position);
        if (audioBookChapterItemBean != null) {
            Util.setTextWeight(holder.tvName, 500);
            if (audioBookChapterItemBean.getId() == getPlayChapterId()) {
                holder.item.setBackgroundResource(R.drawable.ab_chapter_item_bg_select);
                AudioBookAlbumInfoBean audioBookAlbumInfoBean = this.albumInfoBean;
                if (audioBookAlbumInfoBean != null && checkCurIsPlaying(audioBookAlbumInfoBean.getId())) {
                    if (AudioBookGlobalParams.getPlayingStatus() == MediaPlayerEnum.CallBackState.STARTED) {
                        updateChapterName(holder, true);
                        GlideUtil.setImageGifData(R.drawable.ic_playing, holder.ivPlayState);
                    } else {
                        updateChapterName(holder, true);
                        holder.ivPlayState.setImageResource(R.drawable.playing_icon);
                    }
                    holder.tvIndex.setVisibility(8);
                    holder.ivPlayState.setVisibility(0);
                } else {
                    updateChapterName(holder, false);
                    holder.tvIndex.setVisibility(0);
                    holder.ivPlayState.setVisibility(8);
                }
                holder.tvName.setTextColor(this.mContext.getResources().getColor(R.color.ab_chapter_select_text_color));
                holder.tvDuration.setTextColor(this.mContext.getResources().getColor(R.color.ab_chapter_select_text_color));
            } else {
                updateChapterName(holder, false);
                holder.item.setBackgroundResource(R.drawable.ab_chapter_item_bg);
                holder.tvIndex.setVisibility(0);
                holder.ivPlayState.setVisibility(8);
                holder.tvName.setTextColor(this.mContext.getResources().getColor(R.color.text_color));
                holder.tvDuration.setTextColor(this.mContext.getResources().getColor(R.color.text_color));
            }
            updateUI(holder, audioBookChapterItemBean);
            holder.item.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (audioBookChapterItemBean.getId() == AudioBookChapterListAdapter.this.getPlayChapterId() && (ActivityUtils.getTopActivity() instanceof AudioBookPlayerActivity)) {
                        return;
                    }
                    AudioBookChapterListAdapter.this.setPlayChapterId(audioBookChapterItemBean.getId());
                    AudioBookChapterListAdapter.this.notifyDataSetChanged();
                    if (AudioBookChapterListAdapter.this.listener != null) {
                        AudioBookChapterListAdapter.this.listener.onPlay(audioBookChapterItemBean.getId(), audioBookChapterItemBean.getIndex());
                    }
                }
            });
            holder.ivCollect.setOnClickListener(new AnitClick(800L) { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListAdapter.2
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View v) {
                    AudioBookChapterListAdapter.this.collectChapter(audioBookChapterItemBean.getId(), audioBookChapterItemBean.getIsCollect(), AudioBookChapterListAdapter.this.albumInfoBean.getId(), null);
                }
            });
        }
    }

    static class ViewHolder extends AudioBookChapterListBaseAdapter.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
