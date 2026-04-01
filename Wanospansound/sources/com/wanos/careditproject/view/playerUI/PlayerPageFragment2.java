package com.wanos.careditproject.view.playerUI;

import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.ExoPlayer;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.BaseResponse2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.bean.WorkType;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.PlayerPosViewAdapter;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.WorkDetail;
import com.wanos.careditproject.databinding.FragmentPreviewPage2Binding;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.DateUtils;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.ImageUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.WanosPlayerParamUtils;
import com.wanos.careditproject.view.viewmodel.PlayerPageFragment2ViewModel;
import com.wanos.commonlibrary.base.BaseFragment;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerPageFragment2 extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, OnStatusCallbackListener {
    private AudioManager audioManager;
    private FragmentPreviewPage2Binding binding;
    private long curSampleNum;
    private Boolean edit;
    private MediaPlayerHelper mMediaPlayerHelper;
    private long maxSampleNum;
    private int maxVolume;
    private OnCreateAnotherListener onCreateAnotherListener;
    private PlayerUtils.PlayerListener playerListener;
    private PlayerPosViewAdapter playerPosViewAdapter;
    private ProjectInfo projectInfo;
    private PlayerPageFragment2ViewModel viewModel;
    private String url = "";
    private String id = "";
    private String jsonPath = "";
    private int mediaId = -1;
    private long audioDuration = 0;
    private long curPos = 0;
    private boolean webOk = false;
    private boolean resOk = false;
    protected long duration = 0;
    private boolean viewPause = false;
    private boolean isPreBallModelClear = false;
    private List<WebBallInfoModel> preBallModel = new ArrayList();
    private List<Integer> tmpList = new ArrayList();
    private int drawModelListDrawMax = 30;
    private WebBallInfoModel[] drawModelList = new WebBallInfoModel[30];
    private WebBallInfoModel[] drawModelListDraw = new WebBallInfoModel[30];
    private int curShowType = 0;
    private boolean touchPause = false;

    public interface OnCreateAnotherListener {
        void onCreateAnotheClick();
    }

    @Override // com.wanos.commonlibrary.base.BaseFragment
    public void notifyMediaPlayBarVisiable(boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.viewModel = (PlayerPageFragment2ViewModel) new ViewModelProvider(getActivity()).get(PlayerPageFragment2ViewModel.class);
        EventBus.getDefault().register(this);
    }

    public void setData(String str, String str2, String str3, float f) {
        this.url = str;
        this.id = str2;
        this.jsonPath = str3;
        this.duration = (long) (f * 1000.0f);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentPreviewPage2Binding.inflate(getLayoutInflater());
        for (int i = 0; i < this.drawModelListDrawMax; i++) {
            this.drawModelListDraw[i] = new WebBallInfoModel();
        }
        showLoadingView();
        if (this.viewModel.getProjectInfo() == null) {
            initData();
        } else {
            ProjectInfo projectInfo = this.viewModel.getProjectInfo();
            this.projectInfo = projectInfo;
            dealProjectInfo(projectInfo);
        }
        initView();
        initListenter();
        return this.binding.getRoot();
    }

    protected void initData() {
        getWorkDetail();
    }

    public void updateCollect(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.creator_collect2);
        } else {
            imageView.setImageResource(R.drawable.creator_no_collect2);
        }
    }

    private void getWorkDetail() {
        ProjectInfo projectInfo = this.projectInfo;
        if (projectInfo == null) {
            return;
        }
        CreatorRetrofitUtil.getWorkDetail(projectInfo.getId(), new ResponseCallBack<BaseResponse2<WorkDetail>>(getActivity() != null ? getActivity() : null) { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment2.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse2<WorkDetail> baseResponse2) {
                WorkDetail workDetail = baseResponse2.data;
                if (workDetail == null || workDetail.getInfo() == null) {
                    return;
                }
                ProjectInfo info = workDetail.getInfo();
                info.getWorkType();
                PlayerPageFragment2.this.dealProjectInfo(info);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealProjectInfo(ProjectInfo projectInfo) {
        this.url = projectInfo.getWanosPath();
        updateCollect(projectInfo.isCollect(), this.binding.ivCollect);
        this.projectInfo.setCollect(projectInfo.isCollect());
        this.resOk = true;
        initPlayer();
    }

    protected void collectWork(String str, final ProjectInfo projectInfo, final ImageView imageView) {
        if (!projectInfo.isCollect()) {
            CreatorRetrofitUtil.workCollect(str, new ResponseCallBack<BaseResponse>(getActivity() != null ? getActivity() : null) { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment2.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(true);
                    ProjectInfo projectInfo2 = projectInfo;
                    projectInfo2.setCollectCount(projectInfo2.getCollectCount() + 1);
                    EventBus.getDefault().post(projectInfo);
                    PlayerPageFragment2.this.updateCollect(projectInfo.isCollect(), imageView);
                    ToastUtil.showMsg("收藏成功！");
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("收藏失败");
                }
            });
        } else {
            CreatorRetrofitUtil.workCancelCollect(str, new ResponseCallBack<BaseResponse>(getActivity() != null ? getActivity() : null) { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment2.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    projectInfo.setCollectCount(r3.getCollectCount() - 1);
                    PlayerPageFragment2.this.updateCollect(projectInfo.isCollect(), imageView);
                    EventBus.getDefault().post(projectInfo);
                    ToastUtil.showMsg("取消收藏！");
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("取消收藏失败");
                }
            });
        }
    }

    public void initView() {
        this.binding.ivCollect.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment2.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!UserInfoUtil.isLogin()) {
                    if (PlayerPageFragment2.this.getActivity() == null || !(PlayerPageFragment2.this.getActivity() instanceof WanosBaseActivity)) {
                        return;
                    }
                    ((WanosBaseActivity) PlayerPageFragment2.this.getActivity()).showLoginDialog();
                    return;
                }
                PlayerPageFragment2 playerPageFragment2 = PlayerPageFragment2.this;
                playerPageFragment2.collectWork(playerPageFragment2.projectInfo.getId(), PlayerPageFragment2.this.projectInfo, PlayerPageFragment2.this.binding.ivCollect);
            }
        });
        ProjectInfo projectInfo = this.projectInfo;
        if (projectInfo != null) {
            this.viewModel.setProjectInfo(projectInfo);
        } else {
            this.projectInfo = this.viewModel.getProjectInfo();
        }
        this.binding.tvSongName.setText(this.projectInfo.getTitle());
        GlideUtil.loadImage(ImageUtils.getCreatorAvatarUrl(this.projectInfo.getPicture()), this.binding.ivAlbum);
        if (this.projectInfo.getAutherInfo() != null && !StringUtils.isEmpty(this.projectInfo.getAutherInfo().getAvatar())) {
            GlideUtil.loadImage(ImageUtils.getCreatorAvatarUrl(this.projectInfo.getAutherInfo().getAvatar()), this.binding.ivAvatar);
        }
        this.binding.tvCreateAnother.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.binding.tvDesc.setText(this.projectInfo.getContent());
        this.binding.tvNickname.setText(this.projectInfo.getAutherInfo().getUserName());
        this.binding.tvCreateAnother.setVisibility(this.projectInfo.getCanTemplate() == 1 ? 0 : 8);
        WorkType workType = this.projectInfo.getWorkType();
        if (workType != null && !TextUtils.isEmpty(workType.typeName)) {
            this.binding.tvSongType.setText(workType.typeName);
            this.binding.tvSongType.setVisibility(0);
        }
        if (this.projectInfo.getReviewStatus() == 3) {
            this.binding.ivCollect.setVisibility(0);
        }
        updateCollect(this.projectInfo.isCollect(), this.binding.ivCollect);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        if (UserInfoUtil.isLogin()) {
            getWorkDetail();
        } else {
            updateCollect(false, this.binding.ivCollect);
            this.projectInfo.setCollect(false);
        }
    }

    public void initPlayer() {
        if (isAlive()) {
            MediaPlayerHelper mediaPlayerHelper = this.viewModel.getmMediaPlayerHelper();
            this.mMediaPlayerHelper = mediaPlayerHelper;
            if (mediaPlayerHelper == null) {
                MediaPlayerHelper mediaPlayerHelperCreate = MediaPlayerHelperUtil.create();
                this.mMediaPlayerHelper = mediaPlayerHelperCreate;
                this.viewModel.setmMediaPlayerHelper(mediaPlayerHelperCreate);
                this.mMediaPlayerHelper.setProgressInterval(100);
                this.mMediaPlayerHelper.playUrl(getContext(), this.url, false, Integer.parseInt(this.projectInfo.getId()));
            }
            if (this.viewModel.isEverPlayed()) {
                closeLoadingView();
                this.binding.seekbarProgress.setAllowedSeek(true);
            }
            if (this.mMediaPlayerHelper.isPlaying()) {
                this.binding.btnPlayPreview.setImageResource(R.drawable.preview_stop2);
            } else {
                this.binding.btnPlayPreview.setImageResource(R.drawable.preview_play2);
            }
            updatePlayer();
            LogUtils.d("initPlayer url = " + this.url);
            this.mMediaPlayerHelper.setOnStatusCallbackListener(this);
        }
    }

    public void updatePlayer() {
        if (this.mMediaPlayerHelper != null) {
            if (this.curPos <= r0.getCurrentPosition()) {
                this.curPos = this.mMediaPlayerHelper.getCurrentPosition();
            }
            long j = this.duration;
            this.audioDuration = j;
            if (j > 0) {
                this.binding.seekbarProgress.setProgress((int) ((this.curPos * ((long) this.binding.seekbarProgress.getMax())) / this.audioDuration));
            }
            WanosPlayerParamUtils.getInstance().setCurSampleNum((this.curPos * ((long) DataHelpAudioTrack.getSampleRate())) / 1000);
            updateTimeText();
        }
    }

    private String stringForTime(long j) {
        long j2 = (j / 1000) + ((long) (((int) (j % 1000)) > 0 ? 1 : 0));
        return new Formatter().format("%02d:%02d", Long.valueOf((j2 / 60) % 60), Long.valueOf(j2 % 60)).toString();
    }

    protected String getPlayTime() {
        return DateUtils.convertLongToMinSec(this.curPos);
    }

    protected String getDuration() {
        return DateUtils.convertFloatToMinSec(this.projectInfo.getDuration());
    }

    private void updateTimeText() {
        this.binding.textPlayTime0.setText(getPlayTime());
        this.binding.textPlayTime.setText(getDuration());
    }

    public void initListenter() {
        this.binding.btnPlayPreview.setOnClickListener(this);
        this.binding.seekbarProgress.setOnSeekBarChangeListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.viewPause = true;
            PlayerUtils.stop(true);
        } else {
            initView();
            this.viewPause = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.viewPause = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.viewPause = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        MediaPlayerHelper mediaPlayerHelper;
        PlayerUtils.forceStop();
        if (getActivity().isFinishing() && (mediaPlayerHelper = this.mMediaPlayerHelper) != null) {
            mediaPlayerHelper.stop();
            this.mMediaPlayerHelper.release();
            this.mMediaPlayerHelper = null;
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnCreateAnotherListener onCreateAnotherListener;
        int id = view.getId();
        if (id == R.id.btn_play_preview) {
            MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
            if (mediaPlayerHelper != null) {
                if (mediaPlayerHelper.isPlaying()) {
                    this.mMediaPlayerHelper.pause();
                    playerStop();
                    PlayerUtils.forceStop();
                    return;
                } else {
                    if (this.duration - this.curPos <= 800) {
                        this.curPos = 0L;
                        this.binding.seekbarProgress.setProgress(0);
                        this.mMediaPlayerHelper.seekTo(0L);
                    }
                    this.mMediaPlayerHelper.start();
                    playerPlay();
                    return;
                }
            }
            return;
        }
        if (id != R.id.tv_create_another || (onCreateAnotherListener = this.onCreateAnotherListener) == null) {
            return;
        }
        onCreateAnotherListener.onCreateAnotheClick();
    }

    public void updateWebView(List<WebBallInfoModel> list, boolean z) {
        if (this.isPreBallModelClear) {
            this.preBallModel.clear();
            this.isPreBallModelClear = false;
        }
        EditingUtils.mergeBallList(this.preBallModel, list, this.tmpList);
        if (z) {
            for (int i = 0; i < this.preBallModel.size(); i++) {
                this.drawModelList[i] = this.preBallModel.get(i);
            }
        }
    }

    private void playerPlay() {
        this.binding.btnPlayPreview.setImageResource(R.drawable.preview_stop2);
    }

    private void playerStop() {
        this.binding.btnPlayPreview.setImageResource(R.drawable.preview_play2);
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        if (callBackState != MediaPlayerEnum.CallBackState.PROGRESS) {
            EditingUtils.log("onStatusonStatusCallbackNext status = " + callBackState);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            playerStop();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARE) {
            this.duration = this.mMediaPlayerHelper.getDuration();
            playerPlay();
            updatePlayer();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            this.binding.seekbarProgress.setAllowedSeek(true);
            this.viewModel.setIsEverPlayed(true);
            closeLoadingView();
            playerPlay();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
            if (this.touchPause) {
                playerPlay();
                return;
            } else {
                playerStop();
                return;
            }
        }
        if (callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION) {
            playerStop();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.curPos = this.duration;
            this.binding.textPlayTime0.setText(getDuration());
            this.binding.textPlayTime.setText(getDuration());
            playerStop();
            return;
        }
        if (callBackState != MediaPlayerEnum.CallBackState.SEEK_COMPLETE && callBackState == MediaPlayerEnum.CallBackState.PROGRESS) {
            updatePlayer();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z && seekBar.getId() == R.id.seekbar_progress) {
            this.curPos = (((long) i) * this.audioDuration) / ((long) this.binding.seekbarProgress.getMax());
            updateTimeText();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar_progress) {
            this.isPreBallModelClear = true;
            MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
            if (mediaPlayerHelper != null) {
                long j = this.audioDuration;
                long j2 = this.curPos;
                if (j - j2 > 100) {
                    mediaPlayerHelper.seekTo(j2, 3);
                    if (this.touchPause) {
                        this.mMediaPlayerHelper.start();
                        playerPlay();
                    }
                } else {
                    mediaPlayerHelper.pause();
                    playerStop();
                }
            }
        }
        this.touchPause = false;
    }

    public void showLoadingView() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        this.binding.btnLoading.startAnimation(rotateAnimation);
        this.binding.btnLoading.setVisibility(0);
    }

    public void closeLoadingView() {
        this.binding.btnLoading.clearAnimation();
        this.binding.btnLoading.setVisibility(8);
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public boolean isAlive() {
        return (!isAdded() || isRemoving() || isDetached() || getActivity() == null || getActivity().isFinishing()) ? false : true;
    }

    public void setEdit(Boolean bool) {
        this.edit = bool;
    }

    public void setOnCreateAnotherListener(OnCreateAnotherListener onCreateAnotherListener) {
        this.onCreateAnotherListener = onCreateAnotherListener;
    }
}
