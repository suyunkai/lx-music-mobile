package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter;
import com.wanos.media.util.AnitClick;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChapterListMineAdapter extends AudioBookChapterListBaseAdapter<AudioBookMineChapterItemBean, ViewHolder> {
    MediaPlayerEnum.CallBackState callBackState;
    public MineType mType;
    private long playingAlbumId;
    private long playingChapterId;

    public enum MineType {
        LikeChapter,
        PlayedChapter
    }

    public AudioBookChapterListMineAdapter(Context context, List<AudioBookMineChapterItemBean> datas) {
        super(context, datas);
        this.mType = MineType.LikeChapter;
        this.playingAlbumId = -1L;
        this.playingChapterId = -1L;
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
    }

    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_audio_book_mine_chapter_item, parent, false));
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState, long playingAlbumId, long playingChapterId) {
        this.callBackState = callBackState;
        this.playingAlbumId = playingAlbumId;
        this.playingChapterId = playingChapterId;
        notifyDataSetChanged();
    }

    public void updateProgress(int position, long progress) {
        AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) this.datas.get(position);
        if (audioBookMineChapterItemBean == null || audioBookMineChapterItemBean.getDuration() == 0) {
            return;
        }
        int progress2 = (int) ((audioBookMineChapterItemBean.getProgress() / 10) / audioBookMineChapterItemBean.getDuration());
        int duration = (int) ((progress / 10) / audioBookMineChapterItemBean.getDuration());
        audioBookMineChapterItemBean.setProgress(progress);
        if (progress2 != duration) {
            notifyItemChanged(position);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, final int position) {
        final AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) this.datas.get(position);
        if (audioBookMineChapterItemBean != null) {
            Util.setTextWeight(holder.tvName, 500);
            updateUI(holder, audioBookMineChapterItemBean);
            GlideUtil.setImageData(audioBookMineChapterItemBean.getAvatar(), holder.ivCover, 120, 120);
            holder.tvIndex.setText((position + 1) + "");
            if (this.playingChapterId == audioBookMineChapterItemBean.getId()) {
                holder.item.setBackgroundResource(R.drawable.ab_chapter_item_bg_select);
                holder.tvName.setTextColor(this.mContext.getResources().getColor(R.color.ab_chapter_select_text_color));
                if (AudioBookGlobalParams.getPlayingStatus() == MediaPlayerEnum.CallBackState.STARTED) {
                    GlideUtil.setImageGifData(R.drawable.ic_playing, holder.ivPlayState);
                } else {
                    holder.ivPlayState.setImageResource(R.drawable.playing_icon);
                }
                holder.tvIndex.setVisibility(8);
                holder.ivPlayState.setVisibility(0);
            } else {
                holder.item.setBackgroundResource(R.drawable.ab_chapter_item_bg);
                holder.tvName.setTextColor(this.mContext.getResources().getColor(R.color.text_color));
                holder.tvIndex.setVisibility(0);
                holder.ivPlayState.setVisibility(8);
            }
            if (audioBookMineChapterItemBean.getSpeaker() != null) {
                holder.tvSpeakerName.setText(audioBookMineChapterItemBean.getSpeaker().getName());
            }
            if (this.mType == MineType.PlayedChapter) {
                holder.tvProgress.setVisibility(0);
                if (audioBookMineChapterItemBean.getDuration() != 0) {
                    int progress = (int) ((audioBookMineChapterItemBean.getProgress() / 10) / audioBookMineChapterItemBean.getDuration());
                    if (progress > 100) {
                        progress = 100;
                    }
                    holder.tvProgress.setText("已播" + progress + "%");
                } else {
                    holder.tvProgress.setText("已播0%");
                }
            } else {
                holder.tvProgress.setVisibility(8);
            }
            holder.item.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListMineAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AudioBookChapterListMineAdapter.this.checkVip(audioBookMineChapterItemBean)) {
                        AudioBookPlayerActivity.buildPlayPage(AudioBookChapterListMineAdapter.this.mContext, audioBookMineChapterItemBean.getRadioId(), audioBookMineChapterItemBean.getId(), audioBookMineChapterItemBean.getIndex(), true);
                    }
                }
            });
            holder.ivCollect.setOnClickListener(new AnitClick(800L) { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListMineAdapter.2
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View v) {
                    AudioBookChapterListMineAdapter.this.collectChapter(audioBookMineChapterItemBean.getId(), audioBookMineChapterItemBean.getIsCollect(), audioBookMineChapterItemBean.getRadioId(), new AudioBookChapterListBaseAdapter.CollectChapterListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListMineAdapter.2.1
                        @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter.CollectChapterListener
                        public void onCollect(long id, int isCollect) {
                            if (isCollect == 0 && AudioBookChapterListMineAdapter.this.mType == MineType.LikeChapter) {
                                if (AudioBookChapterListMineAdapter.this.datas != null && !AudioBookChapterListMineAdapter.this.datas.isEmpty()) {
                                    AudioBookChapterListMineAdapter.this.datas.remove(position);
                                }
                                AudioBookChapterListMineAdapter.this.notifyDataSetChanged();
                            }
                        }
                    });
                }
            });
        }
    }

    public void setType(MineType type) {
        this.mType = type;
    }

    static class ViewHolder extends AudioBookChapterListBaseAdapter.ViewHolder {
        public ImageView ivCover;
        public TextView tvProgress;
        public TextView tvSpeakerName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvProgress = (TextView) itemView.findViewById(R.id.tv_chapter_progress);
            this.ivCover = (ImageView) itemView.findViewById(R.id.im_cover);
            this.tvSpeakerName = (TextView) itemView.findViewById(R.id.tv_audio_book_author_name);
        }
    }
}
