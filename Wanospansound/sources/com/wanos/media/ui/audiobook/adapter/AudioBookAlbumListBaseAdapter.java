package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookStatistic;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.presenter.AudioBookAlbumPresenter;
import com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumListBaseAdapter extends BaseRecycleAdapter<AudioBookAlbumInfoBean, ViewHolder> {
    public static final String TAG = "wanos:[AudioBookAlbumListBaseAdapter]";
    MediaPlayerEnum.CallBackState callBackState;
    protected Context mContext;
    private long playingAlbumId;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public AudioBookAlbumListBaseAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        final AudioBookAlbumInfoBean audioBookAlbumInfoBean;
        if (position < this.datas.size() && (audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) this.datas.get(position)) != null) {
            Util.setTextWeight(holder.tvName, 500);
            holder.tvName.setText(audioBookAlbumInfoBean.getName());
            if (audioBookAlbumInfoBean.getSpeaker() != null) {
                holder.tvAuthorName.setText(audioBookAlbumInfoBean.getSpeaker().getName());
            }
            GlideUtil.setImageData(SizeMode.HOME_RECOMMEND, audioBookAlbumInfoBean.getAvatar(), holder.ivCover);
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.open_audiobook, audioBookAlbumInfoBean.getName().replaceAll(" ", "")));
                holder.ivCover.setContentDescription(this.mContext.getResources().getString(R.string.open_audiobook1, (position + 1) + ""));
                ViewCompat.setAccessibilityDelegate(holder.itemView, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1009) {
                            AudioBookAlbumListBaseAdapter audioBookAlbumListBaseAdapter = AudioBookAlbumListBaseAdapter.this;
                            audioBookAlbumListBaseAdapter.openAudioBookAlbum(audioBookAlbumListBaseAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1010) {
                            AudioBookAlbumListBaseAdapter.this.playAudioBookAlbum(audioBookAlbumInfoBean);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
                ViewCompat.setAccessibilityDelegate(holder.ivCover, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.2
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1009) {
                            AudioBookAlbumListBaseAdapter audioBookAlbumListBaseAdapter = AudioBookAlbumListBaseAdapter.this;
                            audioBookAlbumListBaseAdapter.openAudioBookAlbum(audioBookAlbumListBaseAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1010) {
                            AudioBookAlbumListBaseAdapter.this.playAudioBookAlbum(audioBookAlbumInfoBean);
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
            if (audioBookAlbumInfoBean.getIsVip() == 1) {
                holder.ivPayState.setVisibility(0);
                holder.ivPayState.setImageResource(R.drawable.ab_list_vip);
            } else {
                holder.ivPayState.setVisibility(8);
            }
            holder.ivAlbumState.setVisibility(0);
            if (audioBookAlbumInfoBean.getWritingStatus() == 1) {
                holder.ivAlbumState.setImageResource(R.drawable.ab_album_serialization);
            } else {
                holder.ivAlbumState.setImageResource(R.drawable.ab_album_end);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AudioBookAlbumListBaseAdapter audioBookAlbumListBaseAdapter = AudioBookAlbumListBaseAdapter.this;
                    audioBookAlbumListBaseAdapter.openAudioBookAlbum(audioBookAlbumListBaseAdapter.mContext, audioBookAlbumInfoBean);
                }
            });
            holder.btnPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AudioBookAlbumListBaseAdapter.this.playAudioBookAlbum(audioBookAlbumInfoBean);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAudioBookAlbum(Context context, AudioBookAlbumInfoBean item) {
        AudioBookAlbumActivity.buildAlbumPage(this.mContext, item.getId(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playAudioBookAlbum(AudioBookAlbumInfoBean item) {
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
        clickPlayBtn(this.mContext, item);
    }

    public static void clickPlayBtn(final Context context, final AudioBookAlbumInfoBean bean) {
        final long id = bean.getId();
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService == null) {
            return;
        }
        if (mediaPlayerService.getCurMediaInfo() != null && mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook && mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean() != null && mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getRadioId() == id) {
            mediaPlayerService.start();
        } else {
            WanosDbUtils.getAudioBookHistoryItem(id, new DbCallBack<AudioBookMineChapterItemBean>() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.5
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(AudioBookMineChapterItemBean data) {
                    if (data != null) {
                        AudioBookAlbumListBaseAdapter.toPlay(data, context);
                        return;
                    }
                    Log.i(AudioBookAlbumListBaseAdapter.TAG, "requestChapterList: ->2");
                    final int i = 1;
                    WanOSRetrofitUtil.getAudioBookChapterListV1(1, AudioBookAlbumPresenter.chapterCountOfPage, id, new ResponseCallBack<GetAudioBookChapterListResponse>(context) { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.5.1
                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseFailure(int code, String msg) {
                        }

                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseSuccessful(GetAudioBookChapterListResponse response) {
                            if (response.data == null || response.data.list == null) {
                                return;
                            }
                            List<AudioBookChapterItemBean> listAddAll = AudioBookChaptersCache.getInstance().addAll(id, i, response.data.list, AudioBookAlbumPresenter.chapterCountOfPage);
                            if (listAddAll.size() > 0) {
                                AudioBookChapterItemBean audioBookChapterItemBean = listAddAll.get(0);
                                try {
                                    Gson gson = new Gson();
                                    AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) gson.fromJson(gson.toJson(audioBookChapterItemBean), AudioBookMineChapterItemBean.class);
                                    audioBookMineChapterItemBean.setRadioId(bean.getId());
                                    audioBookMineChapterItemBean.setRadioName(bean.getName());
                                    audioBookMineChapterItemBean.setAvatar(bean.getAvatar());
                                    audioBookMineChapterItemBean.setSpeaker(bean.getSpeaker());
                                    audioBookMineChapterItemBean.setAuthor(bean.getAuthor());
                                    audioBookMineChapterItemBean.setPageSize(AudioBookAlbumPresenter.chapterCountOfPage);
                                    WanosDbUtils.updateAudioBookHistory(audioBookMineChapterItemBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.5.1.1
                                        @Override // com.wanos.media.db.DbCallBack
                                        public void callBackData(Boolean data2) {
                                        }
                                    });
                                    AudioBookAlbumListBaseAdapter.toPlay(audioBookMineChapterItemBean, context);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    });
                }
            });
        }
    }

    public static void toPlay(AudioBookMineChapterItemBean bean, Context context) {
        if (bean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
            ((WanosBaseActivity) context).openWeChatPayActivity();
            return;
        }
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setGroupId(bean.getRadioId());
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
        mediaInfo.setAudioBookInfoBean(bean);
        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_AUDIO_BOOK_ALBUM_PLAY, "" + bean.getRadioId(), "", "", "", 0);
        WanOSRetrofitUtil.saveRecordAudioBookAlbum(bean.getRadioId(), "", new ResponseCallBack<GetAudioBookStatistic>(null) { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookStatistic response) {
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final View btnPlay;
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
        }
    }
}
