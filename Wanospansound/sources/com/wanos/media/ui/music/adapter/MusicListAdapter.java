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
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.AudioBookLikeChapterResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.CollectChange2ColEvent;
import com.wanos.commonlibrary.event.CollectChange2HisEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.CarConstants;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.media.juyihall.bean.RecMediaInfo;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.music.MusicTagStateHelp;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.media.util.AnitClick;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.util.MediaInfoChangeUitl;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class MusicListAdapter extends BaseRecycleAdapter<MusicInfoBean, ViewHolder> {
    private final String TAG;
    MediaPlayerEnum.CallBackState callBackState;
    public long clickPlayMusicId;
    private long currPlayMusicId;
    private long groupId;
    private long groupType;
    private boolean isHandRefreshPlayList;
    private final Context mContext;

    public MusicListAdapter(Context context, List<MusicInfoBean> dataList, long groupId, long groupType) {
        super(dataList);
        this.TAG = "wanos:[MusicListAdapter]";
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.isHandRefreshPlayList = true;
        this.mContext = context;
        this.groupId = groupId;
        this.groupType = groupType;
    }

    public boolean isHandRefreshPlayList() {
        return this.isHandRefreshPlayList;
    }

    public void setHandRefreshPlayList(boolean handRefreshPlayList) {
        this.isHandRefreshPlayList = handRefreshPlayList;
    }

    public void setCurrPlayMusicId(long currPlayMusicId) {
        this.currPlayMusicId = currPlayMusicId;
    }

    public long getCurrPlayMusicId() {
        return this.currPlayMusicId;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getGroupType() {
        return this.groupType;
    }

    public void setGroupType(long groupType) {
        this.groupType = groupType;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wanos_music_music_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder viewHolder, final int i) {
        final MusicInfoBean musicInfoBean = (MusicInfoBean) this.datas.get(i);
        if (musicInfoBean != null) {
            if (CommonUtils.isChannelNot245()) {
                viewHolder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.play_music, musicInfoBean.getName().replaceAll(" ", "")));
                viewHolder.avatarIm.setContentDescription(this.mContext.getResources().getString(R.string.play_music1, (i + 1) + ""));
            }
            if (this.currPlayMusicId == musicInfoBean.getMusicId()) {
                if (this.callBackState == MediaPlayerEnum.CallBackState.PREPARING || this.callBackState == MediaPlayerEnum.CallBackState.PAUSING || this.callBackState == MediaPlayerEnum.CallBackState.PAUSE || this.callBackState != MediaPlayerEnum.CallBackState.STARTED) {
                    viewHolder.imPlayingStatus.setImageResource(R.drawable.playing_icon);
                } else {
                    GlideUtil.setImageGifData(R.drawable.ic_playing, viewHolder.imPlayingStatus);
                }
                viewHolder.itemView.setSelected(true);
                viewHolder.imPlayingStatus.setVisibility(0);
                viewHolder.musicNumTv.setVisibility(8);
                viewHolder.nameTv.setHorizontallyScrolling(true);
                viewHolder.nameTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            } else {
                viewHolder.itemView.setSelected(false);
                viewHolder.imPlayingStatus.setVisibility(8);
                viewHolder.musicNumTv.setVisibility(0);
                viewHolder.imPlayingStatus.setImageResource(R.drawable.playing_icon);
                viewHolder.nameTv.setHorizontallyScrolling(false);
                viewHolder.nameTv.setEllipsize(TextUtils.TruncateAt.END);
            }
            int i2 = i + 1;
            if (i2 < 10) {
                viewHolder.musicNumTv.setText("0" + i2);
            } else {
                viewHolder.musicNumTv.setText("" + i2);
            }
            String musicSingerName = MediaInfoChangeUitl.getMusicSingerName(musicInfoBean.getSingerList());
            float duration = musicInfoBean.getDuration();
            int i3 = (int) (duration % 60.0f);
            int i4 = (int) (duration / 60.0f);
            String str = i3 + "";
            String str2 = i4 + "";
            if (i4 < 10) {
                str2 = "0" + i4;
            }
            if (i3 < 10) {
                str = "0" + str;
            }
            GlideUtil.setImageData(SizeMode.PLAY_LIST_ICON, musicInfoBean.getAvatar(), viewHolder.avatarIm);
            Util.setTextWeight(viewHolder.nameTv, 500);
            viewHolder.nameTv.setText(musicInfoBean.getName());
            viewHolder.singerNameTv.setText(musicSingerName);
            viewHolder.tvMusicTimeLenght.setText(str2 + ":" + str);
            ImageView imageView = viewHolder.collectIm;
            boolean zIsLogin = UserInfoUtil.isLogin();
            int i5 = R.drawable.ic_no_collect;
            if (zIsLogin && musicInfoBean.isCollection()) {
                i5 = R.drawable.ic_collect;
            }
            imageView.setImageResource(i5);
            MusicTagStateHelp.initMusicTagState(musicInfoBean.isVipMusic(), musicInfoBean.isFree(), viewHolder.ivVip);
            final int i6 = !musicInfoBean.isCollection() ? 1 : 0;
            viewHolder.collectBtn.setOnClickListener(new AnitClick(800L) { // from class: com.wanos.media.ui.music.adapter.MusicListAdapter.1
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    if (!UserInfoUtil.isLogin()) {
                        if (MusicListAdapter.this.mContext == null || !(MusicListAdapter.this.mContext instanceof WanosBaseActivity)) {
                            return;
                        }
                        ((WanosBaseActivity) MusicListAdapter.this.mContext).showLoginDialog();
                        return;
                    }
                    if (musicInfoBean.getContentType() > 2) {
                        WanOSRetrofitUtil.audioBookLikeChapter(musicInfoBean.getMusicId(), !musicInfoBean.isCollection() ? 1 : 0, new ResponseCallBack<AudioBookLikeChapterResponse>(MusicListAdapter.this.mContext) { // from class: com.wanos.media.ui.music.adapter.MusicListAdapter.1.1
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(AudioBookLikeChapterResponse response) {
                                AudioBookGlobalParams.getInstance().setLikeChapterIsUpdate(true);
                                AudioBookChaptersCache.getInstance().collectChapter(musicInfoBean.getAlbumId(), musicInfoBean.getMusicId(), i6);
                                ToastUtil.showMsg(i6 == 1 ? R.string.music_collect_complete : R.string.music_cancel_collect_complete);
                                musicInfoBean.setCollection(i6 == 1);
                                MusicListAdapter.this.notifyItemChanged(i);
                                if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                                    MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicInfoBean.getMusicId(), i6 == 1);
                                }
                                EventBus.getDefault().post(new CollectChange2ColEvent());
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                                if (i6 == 1) {
                                    ToastUtil.showMsg("收藏失败");
                                } else {
                                    ToastUtil.showMsg("取消收藏失败");
                                }
                            }
                        });
                    } else if (musicInfoBean.isCollection()) {
                        WanOSRetrofitUtil.musicCollectCancel(musicInfoBean.getMusicId(), 1, new ResponseCallBack<BaseResponse>(MusicListAdapter.this.mContext) { // from class: com.wanos.media.ui.music.adapter.MusicListAdapter.1.2
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(BaseResponse response) {
                                if (MusicListAdapter.this.groupId == -4) {
                                    MusicListAdapter.this.datas.remove(musicInfoBean);
                                    MusicListAdapter.this.notifyDataSetChanged();
                                    EventBus.getDefault().post(new CollectChange2HisEvent(musicInfoBean.getMusicId()));
                                } else {
                                    EventBus.getDefault().post(new CollectChange2ColEvent());
                                    musicInfoBean.setCollection(false);
                                    MusicListAdapter.this.notifyItemChanged(i);
                                }
                                ToastUtil.showMsg(R.string.music_cancel_collect_complete);
                                if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                                    MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicInfoBean.getMusicId(), false);
                                }
                            }
                        });
                    } else {
                        WanOSRetrofitUtil.musicCollect(musicInfoBean.getMusicId(), 1, new ResponseCallBack<BaseResponse>(MusicListAdapter.this.mContext) { // from class: com.wanos.media.ui.music.adapter.MusicListAdapter.1.3
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(BaseResponse response) {
                                musicInfoBean.setCollection(true);
                                ToastUtil.showMsg(R.string.music_collect_complete);
                                MusicListAdapter.this.notifyItemChanged(i);
                                if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                                    MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicInfoBean.getMusicId(), true);
                                }
                                EventBus.getDefault().post(new CollectChange2ColEvent());
                            }
                        });
                    }
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.adapter.MusicListAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        Log.d("wanos:[MusicListAdapter]", "点击列表歌曲");
                        musicInfoBean.setPageSize(100);
                        musicInfoBean.setIndex(i);
                        MediaInfo mediaInfo = new MediaInfo();
                        if (CarConstants.isShowJuYiHall()) {
                            mediaInfo = RecMediaInfo.createRecommendMediaInfo(musicInfoBean);
                        }
                        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                        mediaInfo.setGroupId(MusicListAdapter.this.groupId);
                        if (MusicListAdapter.this.groupId > -1) {
                            mediaInfo.setMediaGroupType(MusicListAdapter.this.groupType);
                        } else {
                            mediaInfo.setMediaGroupType(MusicListAdapter.this.groupId);
                        }
                        mediaInfo.setMusicInfoBean(musicInfoBean);
                        MusicListAdapter.this.clickPlayMusicId = musicInfoBean.getMusicId();
                        Log.i("wanos:[MusicListAdapter]", "onClick: -> playMedia");
                        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo, MusicListAdapter.this.isHandRefreshPlayList);
                        if (MusicListAdapter.this.isHandRefreshPlayList) {
                            MusicPlayActivity.startMusicPlayActivity(MusicListAdapter.this.mContext);
                        }
                        if (MusicListAdapter.this.groupId == -6) {
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_GROUP_PLAY, "" + MusicListAdapter.this.groupId, "", "", "", 0);
                        }
                    }
                }
            });
            return;
        }
        viewHolder.itemView.setOnClickListener(null);
        viewHolder.collectBtn.setOnClickListener(null);
        viewHolder.itemView.setSelected(false);
        viewHolder.imPlayingStatus.setVisibility(8);
        viewHolder.musicNumTv.setVisibility(0);
        viewHolder.ivVip.setVisibility(8);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final View collectBtn;
        private final ImageView collectIm;
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
            this.collectBtn = itemView.findViewById(R.id.fl_collect_view);
            this.collectIm = (ImageView) itemView.findViewById(R.id.iv_collect);
            this.ivVip = (ImageView) itemView.findViewById(R.id.iv_vip);
        }
    }
}
