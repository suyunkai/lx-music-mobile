package com.wanos.media.ui.music.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.ui.music.MusicTagStateHelp;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.media.ui.widget.banner.Banner;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.util.MediaInfoChangeUitl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicHomeAdapter extends BaseRecycleAdapter<MusicInfoBean, ViewHolder> {
    private final String TAG;
    MediaPlayerEnum.CallBackState callBackState;
    private long currPlayMusicId;
    private final Context mContext;

    public MusicHomeAdapter(Context context, List<MusicInfoBean> dataList) {
        super(dataList);
        this.TAG = "wanos:[MusicHomeAdapter]";
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.mContext = context;
    }

    public void setCurrPlayMusicId(long currPlayMusicId) {
        this.currPlayMusicId = currPlayMusicId;
    }

    public long getCurrPlayMusicId() {
        return this.currPlayMusicId;
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState) {
        this.callBackState = callBackState;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 4371) {
            return new ViewHolder(this.mFooterView);
        }
        if (viewType == 4370) {
            return new ViewHolder(this.mHeaderView);
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_wanos_music_music_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        final MusicInfoBean musicInfoBean = (MusicInfoBean) this.datas.get(position);
        if (musicInfoBean != null) {
            int index = musicInfoBean.getIndex();
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.play_ranking_list, musicInfoBean.getName().replaceAll(" ", "")));
                holder.avatarIm.setContentDescription(this.mContext.getResources().getString(R.string.play_ranking_list1, (position + 1) + ""));
            }
            if (this.currPlayMusicId == musicInfoBean.getMusicId()) {
                if (this.callBackState == MediaPlayerEnum.CallBackState.PREPARING || this.callBackState == MediaPlayerEnum.CallBackState.PAUSING || this.callBackState == MediaPlayerEnum.CallBackState.PAUSE || this.callBackState != MediaPlayerEnum.CallBackState.STARTED) {
                    holder.imPlayingStatus.setImageResource(R.drawable.playing_icon);
                } else {
                    GlideUtil.setImageGifData(R.drawable.ic_playing, holder.imPlayingStatus);
                }
                holder.itemView.setSelected(true);
                holder.imPlayingStatus.setVisibility(0);
                holder.musicNumTv.setVisibility(8);
                holder.nameTv.setHorizontallyScrolling(true);
                holder.nameTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            } else {
                holder.itemView.setSelected(false);
                holder.imPlayingStatus.setVisibility(8);
                holder.musicNumTv.setVisibility(0);
                holder.imPlayingStatus.setImageResource(R.drawable.playing_icon);
                holder.nameTv.setHorizontallyScrolling(false);
                holder.nameTv.setEllipsize(TextUtils.TruncateAt.END);
            }
            int i = index + 1;
            if (i < 10) {
                holder.musicNumTv.setText("0" + i);
            } else {
                holder.musicNumTv.setText("" + i);
            }
            String musicSingerName = MediaInfoChangeUitl.getMusicSingerName(musicInfoBean.getSingerList());
            float duration = musicInfoBean.getDuration();
            int i2 = (int) (duration % 60.0f);
            int i3 = (int) (duration / 60.0f);
            String str = i2 + "";
            String str2 = i3 + "";
            if (i3 < 10) {
                str2 = "0" + i3;
            }
            if (i2 < 10) {
                str = "0" + str;
            }
            GlideUtil.setImageData(SizeMode.HOME_RANKING, musicInfoBean.getAvatar(), holder.avatarIm);
            Util.setTextWeight(holder.nameTv, 500);
            holder.nameTv.setText(musicInfoBean.getName());
            holder.singerNameTv.setText(musicSingerName);
            holder.tvMusicTimeLenght.setText(str2 + ":" + str);
            MusicTagStateHelp.initMusicTagState(musicInfoBean.isVipMusic(), musicInfoBean.isFree(), holder.ivVip);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.adapter.MusicHomeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Log.d("wanos:[MusicHomeAdapter]", "点击主页面歌曲");
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        musicInfoBean.setPageSize(100);
                        MediaInfo mediaInfo = new MediaInfo();
                        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                        mediaInfo.setGroupId(-3L);
                        mediaInfo.setMediaGroupType(-3L);
                        mediaInfo.setMusicInfoBean(musicInfoBean);
                        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
                        MusicPlayActivity.startMusicPlayActivity(MusicHomeAdapter.this.mContext);
                    }
                }
            });
            return;
        }
        holder.itemView.setOnClickListener(null);
        holder.itemView.setSelected(false);
        holder.imPlayingStatus.setVisibility(8);
        holder.musicNumTv.setVisibility(0);
        holder.ivVip.setVisibility(8);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final ImageView imPlayingStatus;
        private final ImageView ivVip;
        private final TextView musicNumTv;
        private final TextView nameTv;
        private final TextView singerNameTv;
        private final TextView tvMusicTimeLenght;

        ViewHolder(View itemView) {
            super(itemView);
            this.musicNumTv = (TextView) itemView.findViewById(R.id.tv_music_num);
            this.imPlayingStatus = (ImageView) itemView.findViewById(R.id.im_palying_status);
            this.nameTv = (TextView) itemView.findViewById(R.id.tv_music_name);
            this.avatarIm = (ImageView) itemView.findViewById(R.id.im_music_bg);
            this.singerNameTv = (TextView) itemView.findViewById(R.id.tv_singer_name);
            this.tvMusicTimeLenght = (TextView) itemView.findViewById(R.id.tv_music_time);
            this.ivVip = (ImageView) itemView.findViewById(R.id.iv_vip);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ViewHolder holder) {
        Banner banner;
        super.onViewDetachedFromWindow(holder);
        if (holder.getAdapterPosition() == 0 && isHasHeader() && (banner = (Banner) this.mHeaderView.findViewById(R.id.music_home_banner)) != null) {
            banner.stop();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolder holder) {
        Banner banner;
        super.onViewAttachedToWindow(holder);
        if (isHasHeader() && holder.getAdapterPosition() == 0 && (banner = (Banner) this.mHeaderView.findViewById(R.id.music_home_banner)) != null) {
            banner.start();
        }
    }
}
