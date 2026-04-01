package com.wanos.media.ui.search.result.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
public class ResultAudioBookAdapter extends BaseRecycleAdapter<AudioBookAlbumInfoBean, ViewHolder> {
    public static final String TAG = "wanos:[ResultAudioBookAdapter]";
    MediaPlayerEnum.CallBackState callBackState;
    private final Context mContext;
    private long playingAlbumId;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position;
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState, long playingAlbumId) {
        this.callBackState = callBackState;
        this.playingAlbumId = playingAlbumId;
        notifyDataSetChanged();
    }

    public ResultAudioBookAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
        super(datas);
        this.playingAlbumId = -1L;
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.mContext = context;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_song_list, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        final AudioBookAlbumInfoBean audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) this.datas.get(position);
        if (audioBookAlbumInfoBean != null) {
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.open_audiobook, audioBookAlbumInfoBean.getName().replaceAll(" ", "")));
                holder.coverIm.setContentDescription(this.mContext.getResources().getString(R.string.open_audiobook1, (position + 1) + ""));
                ViewCompat.setAccessibilityDelegate(holder.itemView, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1009) {
                            ResultAudioBookAdapter resultAudioBookAdapter = ResultAudioBookAdapter.this;
                            resultAudioBookAdapter.openAudioBookAlbum(resultAudioBookAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1010) {
                            ResultAudioBookAdapter.this.playAudioBookAlbum(audioBookAlbumInfoBean);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
                ViewCompat.setAccessibilityDelegate(holder.coverIm, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.2
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View host, int action, Bundle args) {
                        if (action == 1009) {
                            ResultAudioBookAdapter resultAudioBookAdapter = ResultAudioBookAdapter.this;
                            resultAudioBookAdapter.openAudioBookAlbum(resultAudioBookAdapter.mContext, audioBookAlbumInfoBean);
                        }
                        if (action == 1010) {
                            ResultAudioBookAdapter.this.playAudioBookAlbum(audioBookAlbumInfoBean);
                        }
                        return super.performAccessibilityAction(host, action, args);
                    }
                });
            }
            Util.setTextWeight(holder.nameTv, 500);
            holder.nameTv.setText(audioBookAlbumInfoBean.getName());
            holder.authorTv.setText(audioBookAlbumInfoBean.getSpeaker() != null ? audioBookAlbumInfoBean.getSpeaker().getName() : "");
            GlideUtil.setImageData(audioBookAlbumInfoBean.getAvatar(), holder.coverIm, 280, 280);
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
            holder.isAlbumState.setVisibility(0);
            holder.isAlbumState.setImageResource(audioBookAlbumInfoBean.getWritingStatus() == 1 ? R.drawable.ab_album_serialization : R.drawable.ab_album_end);
            if (audioBookAlbumInfoBean.getIsVip() == 1) {
                holder.isPayState.setVisibility(0);
                holder.isPayState.setImageResource(R.drawable.ab_list_vip);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m553x9b402121(audioBookAlbumInfoBean, view);
                }
            });
            holder.btnMusicPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m554xe8ff9922(audioBookAlbumInfoBean, view);
                }
            });
            return;
        }
        holder.itemView.setOnClickListener(null);
        holder.btnMusicPlay.setOnClickListener(null);
    }

    /* JADX INFO: renamed from: lambda$bindData$0$com-wanos-media-ui-search-result-adapter-ResultAudioBookAdapter, reason: not valid java name */
    /* synthetic */ void m553x9b402121(AudioBookAlbumInfoBean audioBookAlbumInfoBean, View view) {
        openAudioBookAlbum(this.mContext, audioBookAlbumInfoBean);
    }

    /* JADX INFO: renamed from: lambda$bindData$1$com-wanos-media-ui-search-result-adapter-ResultAudioBookAdapter, reason: not valid java name */
    /* synthetic */ void m554xe8ff9922(AudioBookAlbumInfoBean audioBookAlbumInfoBean, View view) {
        playAudioBookAlbum(audioBookAlbumInfoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAudioBookAlbum(Context context, AudioBookAlbumInfoBean audioBookBean) {
        AudioBookAlbumActivity.buildAlbumPage(context, audioBookBean.getId(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playAudioBookAlbum(AudioBookAlbumInfoBean audioBookBean) {
        if (this.playingAlbumId == audioBookBean.getId()) {
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
        clickPlayBtn(this.mContext, audioBookBean);
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
            WanosDbUtils.getAudioBookHistoryItem(id, new DbCallBack<AudioBookMineChapterItemBean>() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.3
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(AudioBookMineChapterItemBean data) {
                    if (data != null) {
                        ResultAudioBookAdapter.toPlay(data, context);
                        return;
                    }
                    Log.i(ResultAudioBookAdapter.TAG, "requestChapterList: ->3");
                    final int i = 1;
                    WanOSRetrofitUtil.getAudioBookChapterListV1(1, AudioBookAlbumPresenter.chapterCountOfPage, id, new ResponseCallBack<GetAudioBookChapterListResponse>(context) { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.3.1
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
                                    WanosDbUtils.updateAudioBookHistory(audioBookMineChapterItemBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.3.1.1
                                        @Override // com.wanos.media.db.DbCallBack
                                        public void callBackData(Boolean data2) {
                                        }
                                    });
                                    ResultAudioBookAdapter.toPlay(audioBookMineChapterItemBean, context);
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
        WanOSRetrofitUtil.saveRecordAudioBookAlbum(bean.getRadioId(), "", new ResponseCallBack<GetAudioBookStatistic>(null) { // from class: com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookStatistic response) {
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView authorTv;
        private final View btnMusicPlay;
        private final ImageView coverIm;
        private final ImageView isAlbumState;
        private final ImageView isPayState;
        private final ImageView ivPlayState;
        private final TextView nameTv;
        private final ProgressBar pbAudiobookPlay;

        ViewHolder(View itemView) {
            super(itemView);
            this.authorTv = (TextView) itemView.findViewById(R.id.tv_author_name);
            this.coverIm = (ImageView) itemView.findViewById(R.id.im_music_bg);
            this.btnMusicPlay = itemView.findViewById(R.id.btn_music_play);
            this.isAlbumState = (ImageView) itemView.findViewById(R.id.iv_album_state);
            this.isPayState = (ImageView) itemView.findViewById(R.id.iv_pay_state);
            this.nameTv = (TextView) itemView.findViewById(R.id.tv_audio_book_name);
            this.ivPlayState = (ImageView) itemView.findViewById(R.id.iv_music_play);
            this.pbAudiobookPlay = (ProgressBar) itemView.findViewById(R.id.pb_music_play);
        }
    }
}
