package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChildrenAlbumListAdapter extends BaseRecycleAdapter<AudioBookAlbumInfoBean, ViewHolder> {
    MediaPlayerEnum.CallBackState callBackState;
    protected Context mContext;
    private long playingAlbumId;

    public AudioBookChildrenAlbumListAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
        super(datas);
        this.playingAlbumId = -1L;
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.mContext = context;
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState, long playingAlbumId) {
        this.callBackState = callBackState;
        this.playingAlbumId = playingAlbumId;
        notifyDataSetChanged();
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_audio_book_children_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        final AudioBookAlbumInfoBean audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) this.datas.get(position);
        if (audioBookAlbumInfoBean != null) {
            int color = ContextCompat.getColor(this.mContext, R.color.ab_children_item_0);
            int color2 = ContextCompat.getColor(this.mContext, R.color.ab_children_item_image_0);
            int i = position % 4;
            if (i == 1) {
                color = ContextCompat.getColor(this.mContext, R.color.ab_children_item_1);
                color2 = ContextCompat.getColor(this.mContext, R.color.ab_children_item_image_1);
            } else if (i == 2) {
                color = ContextCompat.getColor(this.mContext, R.color.ab_children_item_2);
                color2 = ContextCompat.getColor(this.mContext, R.color.ab_children_item_image_2);
            } else if (i == 3) {
                color = ContextCompat.getColor(this.mContext, R.color.ab_children_item_3);
                color2 = ContextCompat.getColor(this.mContext, R.color.ab_children_item_image_3);
            }
            holder.cv0.setBackground(createRoundedDrawable(color2));
            holder.cv1.setBackground(createRoundedDrawable(Color.parseColor("#FFFFFF")));
            holder.cvMain.setBackground(createRoundedDrawable(color));
            Util.setTextWeight(holder.tvName, 500);
            holder.tvName.setText(audioBookAlbumInfoBean.getName());
            if (audioBookAlbumInfoBean.getSpeaker() != null) {
                holder.tvAuthorName.setText(audioBookAlbumInfoBean.getSpeaker().getName());
            }
            GlideUtil.setImageData(audioBookAlbumInfoBean.getAvatar(), holder.ivCover, 360, 360);
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.open_children_audiobook, audioBookAlbumInfoBean.getName().replaceAll(" ", "")));
                holder.cvMain.setContentDescription(this.mContext.getResources().getString(R.string.open_children_audiobook1, (position + 1) + ""));
                ViewCompat.setAccessibilityDelegate(holder.itemView, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChildrenAlbumListAdapter.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1011) {
                            AudioBookChildrenAlbumListAdapter audioBookChildrenAlbumListAdapter = AudioBookChildrenAlbumListAdapter.this;
                            audioBookChildrenAlbumListAdapter.openChildrenAlbum(audioBookChildrenAlbumListAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1012) {
                            AudioBookChildrenAlbumListAdapter.this.playChildrenAlbum(audioBookAlbumInfoBean);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
                ViewCompat.setAccessibilityDelegate(holder.cvMain, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChildrenAlbumListAdapter.2
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1011) {
                            AudioBookChildrenAlbumListAdapter audioBookChildrenAlbumListAdapter = AudioBookChildrenAlbumListAdapter.this;
                            audioBookChildrenAlbumListAdapter.openChildrenAlbum(audioBookChildrenAlbumListAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1012) {
                            AudioBookChildrenAlbumListAdapter.this.playChildrenAlbum(audioBookAlbumInfoBean);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
            }
            if (this.playingAlbumId == audioBookAlbumInfoBean.getId()) {
                if (this.callBackState != MediaPlayerEnum.CallBackState.PREPARING) {
                    if (this.callBackState != MediaPlayerEnum.CallBackState.PAUSING && this.callBackState != MediaPlayerEnum.CallBackState.PAUSE) {
                        if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                            GlideUtil.setImageGifData(R.drawable.ic_playing_2, holder.ivPlayState);
                            holder.ivPlayState.setVisibility(0);
                            holder.pbAudiobookPlay.setVisibility(8);
                        }
                    } else {
                        holder.ivPlayState.setImageResource(R.drawable.group_card_pause);
                        holder.ivPlayState.setVisibility(0);
                        holder.pbAudiobookPlay.setVisibility(8);
                    }
                } else {
                    holder.ivPlayState.setVisibility(8);
                    holder.pbAudiobookPlay.setVisibility(0);
                }
            } else {
                holder.ivPlayState.setImageResource(R.drawable.group_card_pause);
                holder.ivPlayState.setVisibility(0);
                holder.pbAudiobookPlay.setVisibility(8);
            }
            holder.ivPayState.setVisibility(0);
            if (audioBookAlbumInfoBean.getIsPay() != 1) {
                if (audioBookAlbumInfoBean.getIsVip() == 1) {
                    holder.ivPayState.setImageResource(R.drawable.ab_list_vip);
                } else {
                    holder.ivPayState.setVisibility(8);
                }
            } else {
                holder.ivPayState.setImageResource(R.drawable.ab_list_pay);
            }
            if (audioBookAlbumInfoBean.getWritingStatus() == 1) {
                holder.ivAlbumState.setImageResource(R.drawable.ab_album_serialization);
            } else {
                holder.ivAlbumState.setImageResource(R.drawable.ab_album_end);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChildrenAlbumListAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AudioBookChildrenAlbumListAdapter audioBookChildrenAlbumListAdapter = AudioBookChildrenAlbumListAdapter.this;
                    audioBookChildrenAlbumListAdapter.openChildrenAlbum(audioBookChildrenAlbumListAdapter.mContext, audioBookAlbumInfoBean);
                }
            });
            holder.btnPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChildrenAlbumListAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AudioBookChildrenAlbumListAdapter.this.playChildrenAlbum(audioBookAlbumInfoBean);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openChildrenAlbum(Context context, AudioBookAlbumInfoBean item) {
        AudioBookAlbumActivity.buildAlbumPage(this.mContext, item.getId(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playChildrenAlbum(AudioBookAlbumInfoBean item) {
        if (this.playingAlbumId == item.getId()) {
            if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                MediaPlayEngine.getInstance().getMediaPlayerService().pause();
                return;
            } else if (this.callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                MediaPlayEngine.getInstance().getMediaPlayerService().start();
                return;
            } else {
                MediaPlayEngine.getInstance().getMediaPlayerService().pause();
                return;
            }
        }
        AudioBookAlbumListBaseAdapter.clickPlayBtn(this.mContext, item);
    }

    public static GradientDrawable createRoundedDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(16.0f);
        return gradientDrawable;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final View btnPlay;
        private final LinearLayout cv0;
        private final LinearLayout cv1;
        private final ViewGroup cvMain;
        private final ImageView ivAlbumState;
        public ImageView ivCover;
        private final ImageView ivPayState;
        private final ImageView ivPlayState;
        private final ProgressBar pbAudiobookPlay;
        public TextView tvAuthorName;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_audio_book_name);
            this.tvAuthorName = (TextView) itemView.findViewById(R.id.tv_audio_book_author_name);
            this.ivCover = (ImageView) itemView.findViewById(R.id.im_cover);
            this.btnPlay = itemView.findViewById(R.id.btn_audio_book_play);
            this.ivPayState = (ImageView) itemView.findViewById(R.id.iv_pay_state);
            this.ivAlbumState = (ImageView) itemView.findViewById(R.id.iv_album_state);
            this.ivPlayState = (ImageView) itemView.findViewById(R.id.iv_play_state);
            this.pbAudiobookPlay = (ProgressBar) itemView.findViewById(R.id.pb_audiobook_play);
            this.cv0 = (LinearLayout) itemView.findViewById(R.id.cv_0);
            this.cv1 = (LinearLayout) itemView.findViewById(R.id.cv_1);
            ViewGroup viewGroup = (ViewGroup) itemView.findViewById(R.id.cv_main);
            this.cvMain = viewGroup;
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            layoutParams.width = (itemView.getContext().getResources().getDisplayMetrics().widthPixels * 492) / AudioBookGlobalParams.getScreenWidth();
            layoutParams.height = (itemView.getContext().getResources().getDisplayMetrics().widthPixels * 628) / AudioBookGlobalParams.getScreenWidth();
            viewGroup.setLayoutParams(layoutParams);
        }
    }
}
