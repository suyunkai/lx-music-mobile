package com.wanos.media.ui.search.result.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.R;
import com.wanos.media.ui.music.activity.MusicListActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultSongListAdapter extends BaseRecycleAdapter<MusicGroupInfo, ViewHolder> {
    MediaPlayerEnum.CallBackState callBackState;
    private long curMusicGroupId;
    private long curMusicGroupType;
    private final Context mContext;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position;
    }

    public ResultSongListAdapter(Context context, List<MusicGroupInfo> datas) {
        super(datas);
        this.curMusicGroupId = -100L;
        this.curMusicGroupType = -100L;
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.mContext = context;
    }

    public long getCurMusicGroupId() {
        return this.curMusicGroupId;
    }

    public long getCurMusicGroupType() {
        return this.curMusicGroupType;
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

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_song_list, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(final ViewHolder holder, int position) {
        final MusicGroupInfo musicGroupInfo = (MusicGroupInfo) this.datas.get(position);
        if (musicGroupInfo != null) {
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.open_music_group, musicGroupInfo.getName().replaceAll(" ", "")));
                holder.avatarIm.setContentDescription(this.mContext.getResources().getString(R.string.open_music_group1, (position + 1) + ""));
                ViewCompat.setAccessibilityDelegate(holder.itemView, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongListAdapter.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1005) {
                            ResultSongListAdapter resultSongListAdapter = ResultSongListAdapter.this;
                            resultSongListAdapter.openMusicGroup(resultSongListAdapter.mContext, musicGroupInfo.getMusicGroupId());
                        }
                        if (action == 1006) {
                            ResultSongListAdapter.this.playMusicGroup(musicGroupInfo, holder);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
                ViewCompat.setAccessibilityDelegate(holder.avatarIm, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongListAdapter.2
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1005) {
                            ResultSongListAdapter resultSongListAdapter = ResultSongListAdapter.this;
                            resultSongListAdapter.openMusicGroup(resultSongListAdapter.mContext, musicGroupInfo.getMusicGroupId());
                        }
                        if (action == 1006) {
                            ResultSongListAdapter.this.playMusicGroup(musicGroupInfo, holder);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
            }
            long musicGroupId = musicGroupInfo.getMusicGroupId();
            holder.nameTv.setMaxLines(2);
            holder.nameTv.setEllipsize(TextUtils.TruncateAt.END);
            holder.nameTv.setTextSize(28.0f);
            holder.nameTv.setTextColor(ResourcesCompat.getColor(this.mContext.getResources(), R.color.search_result_music_group_item_title_color, null));
            holder.nameTv.setText(musicGroupInfo.getName());
            GlideUtil.setImageData(musicGroupInfo.getAvatar(), holder.avatarIm, 280, 280);
            if (this.curMusicGroupId == musicGroupId && this.curMusicGroupType == -6) {
                if (this.callBackState != MediaPlayerEnum.CallBackState.PREPARING) {
                    if (this.callBackState != MediaPlayerEnum.CallBackState.PAUSING && this.callBackState != MediaPlayerEnum.CallBackState.PAUSE) {
                        if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                            GlideUtil.setImageGifData(R.drawable.ic_playing_2, holder.ivMusicPlay);
                            holder.ivMusicPlay.setVisibility(0);
                            holder.pbMusicPlay.setVisibility(8);
                        }
                    } else {
                        holder.ivMusicPlay.setImageResource(R.drawable.group_card_pause);
                        holder.ivMusicPlay.setVisibility(0);
                        holder.pbMusicPlay.setVisibility(8);
                    }
                } else {
                    holder.ivMusicPlay.setVisibility(8);
                    holder.pbMusicPlay.setVisibility(0);
                }
                holder.btnMusicPlay.setTag(this.callBackState);
            } else {
                holder.pbMusicPlay.setVisibility(8);
                holder.ivMusicPlay.setVisibility(0);
                holder.ivMusicPlay.setImageResource(R.drawable.group_card_pause);
                holder.btnMusicPlay.setTag(MediaPlayerEnum.CallBackState.PAUSE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongListAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m557xcd1b27ab(musicGroupInfo, view);
                }
            });
            holder.btnMusicPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongListAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m558x4b7c2b8a(musicGroupInfo, holder, view);
                }
            });
            return;
        }
        holder.itemView.setOnClickListener(null);
        holder.btnMusicPlay.setOnClickListener(null);
    }

    /* JADX INFO: renamed from: lambda$bindData$0$com-wanos-media-ui-search-result-adapter-ResultSongListAdapter, reason: not valid java name */
    /* synthetic */ void m557xcd1b27ab(MusicGroupInfo musicGroupInfo, View view) {
        openMusicGroup(this.mContext, musicGroupInfo.getMusicGroupId());
    }

    /* JADX INFO: renamed from: lambda$bindData$1$com-wanos-media-ui-search-result-adapter-ResultSongListAdapter, reason: not valid java name */
    /* synthetic */ void m558x4b7c2b8a(MusicGroupInfo musicGroupInfo, ViewHolder viewHolder, View view) {
        playMusicGroup(musicGroupInfo, viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openMusicGroup(Context context, long groupId) {
        MusicListActivity.startMusicListActivity(context, groupId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playMusicGroup(MusicGroupInfo songListBean, ViewHolder holder) {
        MusicInfoBean musicInfoBean;
        if (songListBean == null || songListBean.getMusicList() == null || songListBean.getMusicList().size() <= 0 || (musicInfoBean = songListBean.getMusicList().get(0)) == null || MediaPlayEngine.getInstance().getMediaPlayerService() == null) {
            return;
        }
        if (((MediaPlayerEnum.CallBackState) holder.btnMusicPlay.getTag()) == MediaPlayerEnum.CallBackState.PAUSE) {
            musicInfoBean.setPageSize(100);
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
            mediaInfo.setGroupId(songListBean.getMusicGroupId());
            mediaInfo.setMediaGroupType(-6L);
            mediaInfo.setMusicInfoBean(musicInfoBean);
            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
            return;
        }
        if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            MediaPlayEngine.getInstance().getMediaPlayerService().pause();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final View btnMusicPlay;
        private final ImageView ivMusicPlay;
        private final TextView nameTv;
        private final ProgressBar pbMusicPlay;

        ViewHolder(View itemView) {
            super(itemView);
            this.avatarIm = (ImageView) itemView.findViewById(R.id.im_music_bg);
            this.btnMusicPlay = itemView.findViewById(R.id.btn_music_play);
            this.nameTv = (TextView) itemView.findViewById(R.id.tv_audio_book_name);
            this.ivMusicPlay = (ImageView) itemView.findViewById(R.id.iv_music_play);
            this.pbMusicPlay = (ProgressBar) itemView.findViewById(R.id.pb_music_play);
        }
    }
}
