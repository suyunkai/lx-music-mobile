package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.AudioBookLikeChapterResponse;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.AudioBookCollectEvent;
import com.wanos.commonlibrary.event.AudioBookCollectUpdateEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter.ViewHolder;
import com.wanos.media.ui.login.dialog.LoginDialog;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AudioBookChapterListBaseAdapter<T extends AudioBookChapterItemBean, VH extends ViewHolder> extends BaseRecycleAdapter<T, VH> {
    protected boolean isCollect;
    protected boolean isFromBar;
    protected ChapterItemListener listener;
    protected Context mContext;

    public interface ChapterItemListener {
        void onCollect(long id, int isCollect);

        void onPlay(long id, int index);
    }

    public interface CollectChapterListener {
        void onCollect(long id, int isCollect);
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected void updateChapterPlayState() {
    }

    public AudioBookChapterListBaseAdapter(Context context, List<T> datas) {
        super(datas);
        this.isFromBar = false;
        this.isCollect = false;
        this.mContext = context;
    }

    public boolean isCollect() {
        return this.isCollect;
    }

    public void setCollect(boolean collect) {
        this.isCollect = collect;
    }

    public boolean isFromBar() {
        return this.isFromBar;
    }

    public void setFromBar(boolean fromBar) {
        this.isFromBar = fromBar;
    }

    public void setListener(ChapterItemListener listener) {
        this.listener = listener;
    }

    protected void updateCollect(VH holder, T item) {
        if (item.getIsCollect() == 1) {
            holder.ivCollect.setImageResource(R.drawable.ic_collect);
        } else {
            holder.ivCollect.setImageResource(R.drawable.ic_no_collect);
        }
    }

    protected boolean checkVip(AudioBookChapterItemBean bean) {
        if (bean.getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired()) {
            return true;
        }
        ((WanosBaseActivity) this.mContext).openWeChatPayActivity();
        return false;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public List<T> getDatas() {
        return this.datas;
    }

    protected void updateUI(VH holder, T item) {
        holder.tvIndex.setText((item.getIndex() + 1) + "");
        if (CommonUtils.isChannelNot245()) {
            holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.play_audiobook_chapter1, item.getName().replaceAll(" ", "")));
            holder.item.setContentDescription(this.mContext.getResources().getString(R.string.play_audiobook_chapter1, (item.getIndex() + 1) + ""));
            holder.ivCollect.setContentDescription(this.mContext.getResources().getString(R.string.collect_audiobook_chapter, item.getName()));
        }
        updateCollect(holder, item);
        if (item.getCanPreview() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
            holder.ivPayState.setVisibility(0);
            holder.ivPayState.setImageResource(R.drawable.ab_chapter_listen_free);
        } else if (item.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
            holder.ivPayState.setVisibility(0);
            holder.ivPayState.setImageResource(R.drawable.ab_chapter_vip);
        } else {
            holder.ivPayState.setVisibility(8);
        }
        holder.tvName.setText(item.getName());
        float duration = item.getDuration();
        int i = (int) (duration % 60.0f);
        int i2 = (int) (duration / 60.0f);
        String str = i + "";
        String str2 = i2 + "";
        if (i2 < 10) {
            str2 = "0" + i2;
        }
        if (i < 10) {
            str = "0" + str;
        }
        holder.tvDuration.setText(str2 + ":" + str);
    }

    public void collectChapter(final long chapterId, int curIsCollect, final long albumId, final CollectChapterListener collectListener) {
        if (!UserInfoUtil.isLogin()) {
            LoginDialog loginDialog = new LoginDialog(this.mContext);
            loginDialog.setCancelable(true);
            loginDialog.show();
        } else {
            final int i = curIsCollect == 1 ? 0 : 1;
            WanOSRetrofitUtil.audioBookLikeChapter(chapterId, i, new ResponseCallBack<AudioBookLikeChapterResponse>(this.mContext) { // from class: com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(AudioBookLikeChapterResponse response) {
                    AudioBookGlobalParams.getInstance().setLikeChapterIsUpdate(true);
                    AudioBookChaptersCache.getInstance().collectChapter(albumId, chapterId, i);
                    ToastUtil.showMsg(i == 1 ? R.string.music_collect_complete : R.string.music_cancel_collect_complete);
                    for (int i2 = 0; i2 < AudioBookChapterListBaseAdapter.this.datas.size(); i2++) {
                        AudioBookChapterItemBean audioBookChapterItemBean = (AudioBookChapterItemBean) AudioBookChapterListBaseAdapter.this.datas.get(i2);
                        if (audioBookChapterItemBean.getId() == chapterId) {
                            audioBookChapterItemBean.setIsCollect(i);
                        }
                    }
                    AudioBookChapterListBaseAdapter.this.notifyDataSetChanged();
                    CollectChapterListener collectChapterListener = collectListener;
                    if (collectChapterListener != null) {
                        collectChapterListener.onCollect(chapterId, i);
                    }
                    if (AudioBookChapterListBaseAdapter.this.listener != null) {
                        AudioBookChapterListBaseAdapter.this.listener.onCollect(chapterId, i);
                    }
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Audiobook, chapterId, i == 1);
                    }
                    if (AudioBookChapterListBaseAdapter.this.isCollect()) {
                        if (i != 1) {
                            AudioBookCollectEvent audioBookCollectEvent = new AudioBookCollectEvent(chapterId);
                            audioBookCollectEvent.setCollected(false);
                            EventBus.getDefault().post(audioBookCollectEvent);
                            return;
                        }
                        EventBus.getDefault().post(new AudioBookCollectUpdateEvent());
                        return;
                    }
                    if (AudioBookChapterListBaseAdapter.this.isFromBar()) {
                        EventBus.getDefault().post(new AudioBookCollectEvent(AudioBookChapterListBaseAdapter.this.isFromBar()));
                    } else {
                        EventBus.getDefault().post(new AudioBookCollectUpdateEvent());
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (i == 1) {
                        ToastUtil.showMsg("收藏失败");
                    } else {
                        ToastUtil.showMsg("取消收藏失败");
                    }
                }
            });
        }
    }

    public void play(long albumId, AudioBookMineChapterItemBean bean) {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setGroupId(albumId);
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
        mediaInfo.setAudioBookInfoBean(bean);
        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public ImageView ivCollect;
        public ImageView ivPayState;
        public ImageView ivPlayState;
        public TextView tvDuration;
        public TextView tvIndex;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvIndex = (TextView) itemView.findViewById(R.id.tv_chapter_index);
            this.ivPlayState = (ImageView) itemView.findViewById(R.id.im_palying_status);
            this.ivCollect = (ImageView) itemView.findViewById(R.id.iv_collect);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_chapter_name);
            this.tvDuration = (TextView) itemView.findViewById(R.id.tv_chapter_duration);
            this.ivPayState = (ImageView) itemView.findViewById(R.id.iv_chapter_pay_state);
            this.item = (LinearLayout) itemView.findViewById(R.id.chapter_item);
        }
    }
}
