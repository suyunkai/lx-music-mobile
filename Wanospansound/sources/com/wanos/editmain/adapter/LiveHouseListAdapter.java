package com.wanos.editmain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class LiveHouseListAdapter extends BaseRecycleAdapter<ProjectInfo, ViewHolder> implements OnStatusCallbackListener {
    protected boolean clickBtn;
    private final Context context;
    protected int curPlayState;
    protected MediaPlayerHelper mMediaPlayerHelper;
    private int playingPos;
    private String playingProjectId;
    private ProjectInfo playingProjectInfo;
    protected Map<String, ViewHolder> viewHolderMap;
    int vv;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 10;
    }

    public LiveHouseListAdapter(Context context, List<ProjectInfo> list) {
        super(list);
        this.playingProjectId = "";
        this.playingPos = -1;
        this.viewHolderMap = new HashMap();
        this.clickBtn = false;
        this.curPlayState = 0;
        this.vv = 0;
        this.context = context;
        MediaPlayerHelper mediaPlayerHelperCreate = MediaPlayerHelperUtil.create();
        this.mMediaPlayerHelper = mediaPlayerHelperCreate;
        mediaPlayerHelperCreate.setProgressInterval(100);
    }

    public void stopPlayer() {
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
            this.playingProjectId = "";
            notifyDataSetChanged();
        }
    }

    public void destroy() {
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
            this.mMediaPlayerHelper.release();
            this.mMediaPlayerHelper = null;
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4371) {
            return new ViewHolder(this.mFooterView);
        }
        if (i == 4370) {
            return new ViewHolder(this.mHeaderView);
        }
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_live_house_item, viewGroup, false));
    }

    public void setPlayingProjectId(String str) {
        this.playingProjectId = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(final ViewHolder viewHolder, final int i) {
        if (this.datas == null || this.datas.isEmpty()) {
            return;
        }
        final ProjectInfo projectInfo = (ProjectInfo) this.datas.get(i);
        viewHolder.pauseLayout.setVisibility(8);
        viewHolder.playLayout.setVisibility(8);
        viewHolder.seekBarProgress.setEnabled(false);
        if (projectInfo != null) {
            this.viewHolderMap.put(projectInfo.getId(), viewHolder);
            viewHolder.tvProjectName.setText(projectInfo.getTitle());
            GlideUtil.loadImage(projectInfo.getPicture(), viewHolder.ivProjectItem);
            String strStringForTime = EditingUtils.stringForTime((long) (projectInfo.getDuration() * 1000.0f));
            viewHolder.tvDuration0.setText(strStringForTime);
            viewHolder.tvDuration1.setText(strStringForTime);
            if (this.playingProjectId.equals(projectInfo.getId())) {
                viewHolder.playLayout.setVisibility(0);
                viewHolder.ivEditBg.setImageResource(R.drawable.creator_templete_edit_1);
                viewHolder.tvEdit.setTextColor(this.context.getColor(R.color.creator_templete_edit1));
                setPlayStateImage(viewHolder, true);
                updatePlayerProgress(viewHolder);
            } else {
                viewHolder.pauseLayout.setVisibility(0);
                viewHolder.ivEditBg.setImageResource(R.drawable.creator_templete_edit_0);
                viewHolder.tvEdit.setTextColor(this.context.getColor(R.color.creator_templete_edit0));
                setPlayStateImage(viewHolder, false);
            }
            if (projectInfo.isCollect()) {
                viewHolder.ivCollect.setImageResource(R.drawable.creator_templete_collect);
            } else {
                viewHolder.ivCollect.setImageResource(R.drawable.creator_templete_uncollect);
            }
            viewHolder.ivCollect.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LiveHouseListAdapter.this.collectWork(projectInfo.getId(), projectInfo, viewHolder.ivCollect);
                }
            });
            viewHolder.cvItem.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
            viewHolder.ivEditBg.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (LiveHouseListAdapter.this.clickBtn) {
                        return;
                    }
                    LiveHouseListAdapter.this.clickBtn = true;
                    LiveHouseListAdapter.this.stopOtherAudio();
                    LiveHouseListAdapter.this.stopPlayer();
                    CreatorRetrofitUtil.workCopy(projectInfo.getId(), new ResponseCallBack<EditProjectCopyResponse>(LiveHouseListAdapter.this.context) { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.3.1
                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                            LiveHouseListAdapter.this.clickBtn = false;
                            if (editProjectCopyResponse.data != null) {
                                EditingActivity.openEditActivity(LiveHouseListAdapter.this.context, editProjectCopyResponse.data.getProjectType(), editProjectCopyResponse.data.getId(), new Gson().toJson(editProjectCopyResponse.data));
                            }
                        }

                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseFailure(int i2, String str) {
                            LiveHouseListAdapter.this.clickBtn = false;
                            ToastUtil.showMsg(R.string.to_edit_fail);
                        }
                    });
                }
            });
            viewHolder.btnPlay.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.4
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (LiveHouseListAdapter.this.playingProjectId.equals("") || !LiveHouseListAdapter.this.playingProjectId.equals(projectInfo.getId())) {
                        EditingUtils.log("holder.ivPlayState 0");
                        LiveHouseListAdapter.this.stopOtherAudio();
                        LiveHouseListAdapter.this.initPlayer(projectInfo.getWanosPath(), projectInfo.getId(), i, projectInfo);
                        return false;
                    }
                    EditingUtils.log("holder.ivPlayState 1");
                    if (LiveHouseListAdapter.this.mMediaPlayerHelper == null) {
                        return false;
                    }
                    LiveHouseListAdapter.this.playingProjectId = "";
                    LiveHouseListAdapter.this.mMediaPlayerHelper.pause();
                    LiveHouseListAdapter.this.notifyDataSetChangedM();
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChangedM() {
        this.viewHolderMap.clear();
        notifyDataSetChanged();
    }

    private void setPlayStateImage(ViewHolder viewHolder, boolean z) {
        if (z) {
            if (this.curPlayState == 2) {
                GlideUtil.setImageGifData(com.wanos.media.R.drawable.ic_playing_2, viewHolder.ivPlayState);
                viewHolder.ivPlayState.setVisibility(0);
                viewHolder.pbAudiobookPlay.setVisibility(8);
                return;
            } else {
                viewHolder.ivPlayState.setVisibility(8);
                viewHolder.pbAudiobookPlay.setVisibility(0);
                return;
            }
        }
        viewHolder.ivPlayState.setImageResource(com.wanos.media.R.drawable.group_card_pause);
        viewHolder.ivPlayState.setVisibility(0);
        viewHolder.pbAudiobookPlay.setVisibility(8);
    }

    private void setPlayProgress(ViewHolder viewHolder, int i) {
        viewHolder.seekBarProgress.setProgress(i);
    }

    protected void stopOtherAudio() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || !MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            return;
        }
        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
    }

    protected void collectWork(String str, final ProjectInfo projectInfo, final ImageView imageView) {
        if (!projectInfo.isCollect()) {
            CreatorRetrofitUtil.workCollect(str, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    LiveHouseListAdapter.this.updateCollect(projectInfo.isCollect(), imageView);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("收藏失败");
                }
            });
        } else {
            CreatorRetrofitUtil.workCancelCollect(str, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.editmain.adapter.LiveHouseListAdapter.6
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    LiveHouseListAdapter.this.updateCollect(projectInfo.isCollect(), imageView);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("取消收藏失败");
                }
            });
        }
    }

    public void updateCollect(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.creator_templete_collect);
        } else {
            imageView.setImageResource(R.drawable.creator_templete_uncollect);
        }
    }

    public void initPlayer(String str, String str2, int i, ProjectInfo projectInfo) {
        this.playingProjectId = str2;
        notifyDataSetChangedM();
        this.playingPos = i;
        this.playingProjectInfo = projectInfo;
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
        }
        this.mMediaPlayerHelper.playUrl(this.context, str, false, Integer.parseInt(str2));
        this.mMediaPlayerHelper.setOnStatusCallbackListener(this);
    }

    public void updatePlayerProgress(ViewHolder viewHolder) {
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper == null || viewHolder == null) {
            return;
        }
        int currentPosition = mediaPlayerHelper.getCurrentPosition();
        int duration = (int) (this.playingProjectInfo.getDuration() * 1000.0f);
        float max = duration > 0 ? (currentPosition * viewHolder.seekBarProgress.getMax()) / duration : 0.0f;
        String strStringForTime = EditingUtils.stringForTime(currentPosition);
        viewHolder.seekBarProgress.setProgress((int) max);
        viewHolder.tvPlayTime.setText(strStringForTime);
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        EditingUtils.log("v onStatusonStatusCallbackNext status = " + callBackState);
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            this.curPlayState = 1;
            notifyDataSetChanged();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARE) {
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            this.curPlayState = 2;
            notifyDataSetChanged();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
            this.curPlayState = 0;
            notifyDataSetChanged();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION) {
            this.playingProjectId = "";
            this.curPlayState = 0;
            notifyDataSetChangedM();
        } else if (callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.playingProjectId = "";
            this.curPlayState = 0;
            notifyDataSetChangedM();
        } else if (callBackState == MediaPlayerEnum.CallBackState.SEEK_COMPLETE) {
            this.playingProjectId = "";
            this.curPlayState = 0;
            notifyDataSetChangedM();
        } else if (callBackState == MediaPlayerEnum.CallBackState.PROGRESS) {
            updatePlayerProgress(this.viewHolderMap.get(this.playingProjectId));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout btnPlay;
        public CardView cvItem;
        public FrameLayout flPlayState;
        public ImageView ivCollect;
        public ImageView ivEditBg;
        public ImageView ivPlayState;
        public ImageView ivProjectItem;
        public FrameLayout pauseLayout;
        public ProgressBar pbAudiobookPlay;
        public FrameLayout playLayout;
        public SeekBar seekBarProgress;
        public TextView tvDuration0;
        public TextView tvDuration1;
        public TextView tvEdit;
        public TextView tvPlayTime;
        public TextView tvProjectName;

        public ViewHolder(View view) {
            super(view);
            this.ivProjectItem = (ImageView) view.findViewById(R.id.iv_project_item);
            this.ivPlayState = (ImageView) view.findViewById(R.id.play_state);
            this.pauseLayout = (FrameLayout) view.findViewById(R.id.pause_layout);
            this.playLayout = (FrameLayout) view.findViewById(R.id.play_layout);
            this.tvDuration0 = (TextView) view.findViewById(R.id.tv_duration_0);
            this.tvPlayTime = (TextView) view.findViewById(R.id.tv_play_time);
            this.tvDuration1 = (TextView) view.findViewById(R.id.tv_duration_1);
            this.seekBarProgress = (SeekBar) view.findViewById(R.id.seekbar_progress);
            this.tvProjectName = (TextView) view.findViewById(R.id.tv_project_name);
            this.ivEditBg = (ImageView) view.findViewById(R.id.bg_edit);
            this.ivCollect = (ImageView) view.findViewById(R.id.btn_collect);
            this.tvEdit = (TextView) view.findViewById(R.id.tv_edit);
            this.cvItem = (CardView) view.findViewById(R.id.cv_item);
            this.pbAudiobookPlay = (ProgressBar) view.findViewById(R.id.pb_audiobook_play);
            this.btnPlay = (FrameLayout) view.findViewById(R.id.btn_audio_book_play);
        }
    }
}
