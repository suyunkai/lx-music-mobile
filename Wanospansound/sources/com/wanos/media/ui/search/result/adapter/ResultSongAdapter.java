package com.wanos.media.ui.search.result.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.ui.music.MusicTagStateHelp;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.util.MediaInfoChangeUitl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultSongAdapter extends BaseRecycleAdapter<MusicInfoBean, ViewHolder> {
    MediaPlayerEnum.CallBackState callBackState;
    private final Context context;
    private long currPlayMusicId;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position;
    }

    public ResultSongAdapter(Context context, List<MusicInfoBean> list) {
        super(list);
        this.callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        this.context = context;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_song, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, final int position) {
        final MusicInfoBean musicInfoBean = (MusicInfoBean) this.datas.get(position);
        if (musicInfoBean != null) {
            if (CommonUtils.isChannelNot245()) {
                holder.itemView.setContentDescription(this.context.getResources().getString(R.string.play_music, musicInfoBean.getName().replaceAll(" ", "")));
                holder.avatarIm.setContentDescription(this.context.getResources().getString(R.string.play_music1, (position + 1) + ""));
            }
            if (this.currPlayMusicId == musicInfoBean.getMusicId()) {
                if (this.callBackState != MediaPlayerEnum.CallBackState.PREPARING && this.callBackState != MediaPlayerEnum.CallBackState.PAUSING && this.callBackState != MediaPlayerEnum.CallBackState.PAUSE) {
                    if (this.callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                        GlideUtil.setImageGifData(R.drawable.ic_playing, holder.imPlayingStatus);
                    }
                } else {
                    holder.imPlayingStatus.setImageResource(R.drawable.playing_icon);
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
            int i = position + 1;
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
            GlideUtil.setImageData(musicInfoBean.getAvatar(), holder.avatarIm, 120, 120);
            Util.setTextWeight(holder.nameTv, 500);
            holder.nameTv.setText(musicInfoBean.getName());
            holder.singerNameTv.setText(musicSingerName);
            holder.tvMusicTimeLenght.setText(str2 + ":" + str);
            MusicTagStateHelp.initMusicTagState(musicInfoBean.isVipMusic(), musicInfoBean.isFree(), holder.vipIm);
            ImageView imageView = holder.collectIm;
            boolean zIsLogin = UserInfoUtil.isLogin();
            int i4 = R.drawable.ic_no_collect;
            if (zIsLogin && musicInfoBean.isCollection()) {
                i4 = R.drawable.ic_collect;
            }
            imageView.setImageResource(i4);
            holder.collectBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m555xd30d0b29(musicInfoBean, position, view);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.adapter.ResultSongAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m556xd910d688(musicInfoBean, view);
                }
            });
            return;
        }
        holder.itemView.setOnClickListener(null);
        holder.collectBtn.setOnClickListener(null);
        holder.itemView.setSelected(false);
        holder.imPlayingStatus.setVisibility(8);
        holder.musicNumTv.setVisibility(0);
        holder.vipIm.setVisibility(8);
    }

    /* JADX INFO: renamed from: lambda$bindData$0$com-wanos-media-ui-search-result-adapter-ResultSongAdapter, reason: not valid java name */
    /* synthetic */ void m555xd30d0b29(final MusicInfoBean musicInfoBean, final int i, View view) {
        if (!UserInfoUtil.isLogin()) {
            Context context = this.context;
            if (context == null || !(context instanceof WanosBaseActivity)) {
                return;
            }
            ((WanosBaseActivity) context).showLoginDialog();
            return;
        }
        if (musicInfoBean.isCollection()) {
            WanOSRetrofitUtil.musicCollectCancel(musicInfoBean.getMusicId(), 1, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.media.ui.search.result.adapter.ResultSongAdapter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    musicInfoBean.setCollection(false);
                    ToastUtil.showMsg(R.string.music_cancel_collect_complete);
                    ResultSongAdapter.this.notifyItemChanged(i);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicInfoBean.getMusicId(), false);
                    }
                }
            });
        } else {
            WanOSRetrofitUtil.musicCollect(musicInfoBean.getMusicId(), 1, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.media.ui.search.result.adapter.ResultSongAdapter.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    musicInfoBean.setCollection(true);
                    ToastUtil.showMsg(R.string.music_collect_complete);
                    ResultSongAdapter.this.notifyItemChanged(i);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicInfoBean.getMusicId(), true);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$bindData$1$com-wanos-media-ui-search-result-adapter-ResultSongAdapter, reason: not valid java name */
    /* synthetic */ void m556xd910d688(MusicInfoBean musicInfoBean, View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(AppConstants.MEDIA_PLAY_URI + musicInfoBean.getMusicId() + "&isAppIn=true"));
        this.context.startActivity(intent);
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarIm;
        private final View collectBtn;
        private final ImageView collectIm;
        private final ImageView imPlayingStatus;
        private final CardView musicBg;
        private final TextView musicNumTv;
        private final TextView nameTv;
        private final TextView singerNameTv;
        private final TextView tvMusicTimeLenght;
        private final ImageView vipIm;

        ViewHolder(View itemView) {
            super(itemView);
            this.musicBg = (CardView) itemView.findViewById(R.id.result_item_layout);
            this.musicNumTv = (TextView) itemView.findViewById(R.id.tv_music_num);
            this.imPlayingStatus = (ImageView) itemView.findViewById(R.id.im_palying_status);
            this.nameTv = (TextView) itemView.findViewById(R.id.tv_music_name);
            this.avatarIm = (ImageView) itemView.findViewById(R.id.im_music_bg);
            this.singerNameTv = (TextView) itemView.findViewById(R.id.tv_singer_name);
            this.tvMusicTimeLenght = (TextView) itemView.findViewById(R.id.tv_music_time);
            this.collectBtn = itemView.findViewById(R.id.fl_collect_view);
            this.collectIm = (ImageView) itemView.findViewById(R.id.iv_collect);
            this.vipIm = (ImageView) itemView.findViewById(R.id.iv_vip);
        }
    }
}
