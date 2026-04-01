package com.wanos.media.ui.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.ui.music.activity.MusicListActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_PADDING = 1;
    private List<MusicGroupInfo> dataList;
    private final Context mContext;
    private long curMusicGroupId = -100;
    private long curMusicGroupType = -100;
    MediaPlayerEnum.CallBackState callBackState = MediaPlayerEnum.CallBackState.PAUSE;
    private boolean isHomePageShow = false;

    public void setHomePageShow(boolean homePageShow) {
        this.isHomePageShow = homePageShow;
    }

    public MusicGroupAdapter(Context context, List<MusicGroupInfo> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    public long getCurMusicGroupId() {
        return this.curMusicGroupId;
    }

    public void setCurMusicGroupIdAndType(long curMusicGroupId, long curMusicGroupType) {
        this.curMusicGroupId = curMusicGroupId;
        this.curMusicGroupType = curMusicGroupType;
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState) {
        this.callBackState = callBackState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new PaddingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_wanos_music_group_item_padding_bottom, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_wanos_music_group_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder_, int position) {
        if (holder_.getItemViewType() == 1) {
            return;
        }
        final ViewHolder viewHolder = (ViewHolder) holder_;
        final MusicGroupInfo musicGroupInfo = this.dataList.get(position);
        if (musicGroupInfo != null) {
            long musicGroupId = musicGroupInfo.getMusicGroupId();
            Util.setTextWeight(viewHolder.nameTv, 400);
            viewHolder.nameTv.setText(musicGroupInfo.getName());
            GlideUtil.setImageData(SizeMode.HOME_RECOMMEND, musicGroupInfo.getAvatar(), viewHolder.avatarIm);
            if (CommonUtils.isChannelNot245()) {
                viewHolder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.open_music_group, musicGroupInfo.getName().replaceAll(" ", "")));
                viewHolder.avatarIm.setContentDescription(this.mContext.getResources().getString(R.string.open_music_group1, (position + 1) + ""));
                ViewCompat.setAccessibilityDelegate(viewHolder.itemView, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.music.adapter.MusicGroupAdapter.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1005) {
                            MusicGroupAdapter.this.openMusicGroup(musicGroupInfo);
                        }
                        if (action == 1006) {
                            MusicGroupAdapter.this.playMusicGroup(musicGroupInfo, viewHolder);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
                ViewCompat.setAccessibilityDelegate(viewHolder.avatarIm, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.music.adapter.MusicGroupAdapter.2
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1005) {
                            MusicGroupAdapter.this.openMusicGroup(musicGroupInfo);
                        }
                        if (action == 1006) {
                            MusicGroupAdapter.this.playMusicGroup(musicGroupInfo, viewHolder);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
            }
            if (this.curMusicGroupId == musicGroupId && this.curMusicGroupType == -6) {
                if (this.callBackState != MediaPlayerEnum.CallBackState.PREPARING) {
                    if (this.callBackState != MediaPlayerEnum.CallBackState.PAUSING && this.callBackState != MediaPlayerEnum.CallBackState.PAUSE) {
                        if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                            GlideUtil.setImageGifData(R.drawable.ic_playing_2, viewHolder.ivMusicPlay);
                            viewHolder.ivMusicPlay.setVisibility(0);
                            viewHolder.pbMusicPlay.setVisibility(8);
                        }
                    } else {
                        viewHolder.ivMusicPlay.setImageResource(R.drawable.group_card_pause);
                        viewHolder.ivMusicPlay.setVisibility(0);
                        viewHolder.pbMusicPlay.setVisibility(8);
                    }
                } else {
                    viewHolder.ivMusicPlay.setVisibility(8);
                    viewHolder.pbMusicPlay.setVisibility(0);
                }
                viewHolder.btnMusicPlay.setTag(this.callBackState);
            } else {
                viewHolder.pbMusicPlay.setVisibility(8);
                viewHolder.ivMusicPlay.setVisibility(0);
                viewHolder.ivMusicPlay.setImageResource(R.drawable.group_card_pause);
                viewHolder.btnMusicPlay.setTag(MediaPlayerEnum.CallBackState.PAUSE);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.adapter.MusicGroupAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MusicGroupAdapter.this.openMusicGroup(musicGroupInfo);
                }
            });
            viewHolder.btnMusicPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.adapter.MusicGroupAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MusicGroupAdapter.this.playMusicGroup(musicGroupInfo, viewHolder);
                }
            });
            return;
        }
        viewHolder.itemView.setOnClickListener(null);
        viewHolder.btnMusicPlay.setOnClickListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openMusicGroup(MusicGroupInfo mediaMusicBean) {
        MusicListActivity.startMusicListActivity(this.mContext, mediaMusicBean.getMusicGroupId());
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_GROUP_PLAY, "" + mediaMusicBean.getMusicGroupId(), "", "", "", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playMusicGroup(MusicGroupInfo mediaMusicBean, ViewHolder holder) {
        MusicInfoBean musicInfoBean;
        if (mediaMusicBean == null || mediaMusicBean.getMusicList() == null || mediaMusicBean.getMusicList().size() <= 0 || (musicInfoBean = mediaMusicBean.getMusicList().get(0)) == null || MediaPlayEngine.getInstance().getMediaPlayerService() == null) {
            return;
        }
        if (((MediaPlayerEnum.CallBackState) holder.btnMusicPlay.getTag()) == MediaPlayerEnum.CallBackState.PAUSE) {
            musicInfoBean.setPageSize(100);
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
            mediaInfo.setGroupId(mediaMusicBean.getMusicGroupId());
            mediaInfo.setMediaGroupType(-6L);
            mediaInfo.setMusicInfoBean(musicInfoBean);
            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_GROUP_PLAY, "" + mediaMusicBean.getMusicGroupId(), "", "", "", 0);
            return;
        }
        if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            MediaPlayEngine.getInstance().getMediaPlayerService().pause();
        }
    }

    public void setData(List<MusicGroupInfo> data) {
        this.dataList = data;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position >= this.dataList.size() ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MusicGroupInfo> list = this.dataList;
        if (list == null) {
            return 0;
        }
        if (this.isHomePageShow) {
            return list.size();
        }
        return list.size() + 7;
    }

    public static class PaddingViewHolder extends RecyclerView.ViewHolder {
        public PaddingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final View btnMusicPlay;
        private final ImageView ivMusicPlay;
        private final TextView nameTv;
        private final ProgressBar pbMusicPlay;

        ViewHolder(View itemView) {
            super(itemView);
            this.nameTv = (TextView) itemView.findViewById(R.id.tv_music_name);
            this.avatarIm = (ImageView) itemView.findViewById(R.id.im_music_bg);
            this.btnMusicPlay = itemView.findViewById(R.id.btn_music_play);
            this.ivMusicPlay = (ImageView) itemView.findViewById(R.id.iv_music_play);
            this.pbMusicPlay = (ProgressBar) itemView.findViewById(R.id.pb_music_play);
        }
    }
}
