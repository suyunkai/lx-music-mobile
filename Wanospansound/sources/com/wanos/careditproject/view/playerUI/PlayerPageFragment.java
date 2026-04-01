package com.wanos.careditproject.view.playerUI;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.PlayerPosViewAdapter;
import com.wanos.careditproject.databinding.FragmentPrviewPageBinding;
import com.wanos.careditproject.model.server.RootModel;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.WanosPlayerParamUtils;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.careditproject.view.playerUI.Player3dGLRender;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerPageFragment extends PlayerGlFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, OnStatusCallbackListener {
    private AudioManager audioManager;
    private FragmentPrviewPageBinding binding;
    private long curSampleNum;
    private MediaPlayerHelper mMediaPlayerHelper;
    private long maxSampleNum;
    private int maxVolume;
    private PlayerUtils.PlayerListener playerListener;
    private PlayerPosViewAdapter playerPosViewAdapter;
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

    @Override // com.wanos.careditproject.view.playerUI.PlayerGlFragment, com.wanos.commonlibrary.base.BaseFragment
    public void notifyMediaPlayBarVisiable(boolean z) {
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void setData(String str, String str2, String str3, float f) {
        this.url = str;
        this.id = str2;
        this.jsonPath = str3;
        this.duration = (long) (f * 1000.0f);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentPrviewPageBinding.inflate(getLayoutInflater());
        for (int i = 0; i < this.drawModelListDrawMax; i++) {
            this.drawModelListDraw[i] = new WebBallInfoModel();
        }
        showLoadingView();
        this.playerListener = new PlayerUtils.PlayerListener() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment.1
            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onProgress(int i2) {
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onStop() {
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void setWebBallInfo(List<WebBallInfoModel> list, boolean z) {
                if (PlayerPageFragment.this.viewPause) {
                    return;
                }
                PlayerPageFragment.this.updateWebView(list, z);
            }
        };
        initData();
        initView();
        initListenter();
        startDrawThread();
        return this.binding.getRoot();
    }

    protected void initData() {
        DownloadUtils.getInstance().downloadJsonFile(this.jsonPath, new DownloadUtils.DownloadCall() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment.2
            @Override // com.wanos.careditproject.utils.http.DownloadUtils.DownloadCall
            public void onCall(String str, String str2, boolean z) {
                if (PlayerPageFragment.this.isDetached() || PlayerPageFragment.this.isRemoving()) {
                    return;
                }
                if (!str2.equals("")) {
                    try {
                        DataHelpAudioTrack.setRootModel((RootModel) new Gson().fromJson((Reader) new InputStreamReader(new FileInputStream(str2)), RootModel.class));
                        PlayerUtils.play(PlayerPageFragment.this.playerListener, true, false, false);
                        PlayerPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                PlayerPageFragment.this.resOk = true;
                                PlayerPageFragment.this.initPlayer();
                            }
                        });
                        return;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        ToastUtils.showShort("加载文件失败！");
                        return;
                    }
                }
                ToastUtils.showShort("加载文件失败！");
            }
        });
    }

    public void initView() {
        initSpinner();
    }

    public void initSpinner() {
        EditingUtils.log("position initSpinner");
        this.playerPosViewAdapter = new PlayerPosViewAdapter(getContext(), R.layout.layout_spinner_player_show_item, EditingUtils.playerShowTypeList);
        this.binding.spinnerShowType.setAdapter((SpinnerAdapter) this.playerPosViewAdapter);
        this.binding.spinnerShowType.setSelection(0, false);
        this.binding.spinnerShowType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditingUtils.log("CreatorProjectPublishDialog onItemClick position = " + i);
                PlayerPageFragment.this.playerPosViewAdapter.setSelectIndex(i);
                PlayerPageFragment.this.playerPosViewAdapter.notifyDataSetChanged();
                PlayerPageFragment.this.binding.play3dGlView.setShowType(i);
            }
        });
    }

    @Override // com.wanos.careditproject.view.playerUI.PlayerGlFragment
    public void drawGLView() {
        int i;
        super.drawGLView();
        if (PlayerUtils.isPlaying()) {
            synchronized (this.preBallModel) {
                List<WebBallInfoModel> list = this.preBallModel;
                i = 0;
                if (list != null && list.size() > 0) {
                    int size = this.preBallModel.size();
                    while (i < size) {
                        this.drawModelListDraw[i].cloneModel(this.preBallModel.get(i));
                        i++;
                    }
                    i = size;
                }
            }
            this.binding.play3dGlView.setPlayerPos(this.drawModelListDraw, i);
            this.binding.play3dGlView.draw();
            if (this.showTypeAnimateNum < 30) {
                this.showTypeAnimateNum++;
                return;
            }
            return;
        }
        if (this.showTypeAnimateNum < 30) {
            this.binding.play3dGlView.draw();
            this.showTypeAnimateNum++;
        }
    }

    public void setShowType(int i) {
        this.curShowType = i;
        this.binding.play3dGlView.setShowType(i);
        showTypeAniStart();
    }

    public void initPlayer() {
        if (isDetached() || isRemoving()) {
            return;
        }
        MediaPlayerHelper mediaPlayerHelperCreate = MediaPlayerHelperUtil.create();
        this.mMediaPlayerHelper = mediaPlayerHelperCreate;
        mediaPlayerHelperCreate.setProgressInterval(100);
        this.mMediaPlayerHelper.playUrl(getContext(), this.url, false, this.mediaId);
        this.mMediaPlayerHelper.setOnStatusCallbackListener(this);
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
        return stringForTime(this.curPos);
    }

    protected String getDuration() {
        return stringForTime(this.duration);
    }

    private void updateTimeText() {
        this.binding.textPlayTime0.setText(getPlayTime());
        this.binding.textPlayTime.setText(BceConfig.BOS_DELIMITER + getDuration());
    }

    public void initListenter() {
        this.binding.btnPlayPreview.setOnClickListener(this);
        this.binding.seekbarProgress.setOnSeekBarChangeListener(this);
        this.binding.play3dGlView.setGlCreatedCallback(new Player3dGLRender.IGlCreatedCallback() { // from class: com.wanos.careditproject.view.playerUI.PlayerPageFragment.4
            @Override // com.wanos.careditproject.view.playerUI.Player3dGLRender.IGlCreatedCallback
            public void onCallback() {
                PlayerPageFragment playerPageFragment = PlayerPageFragment.this;
                playerPageFragment.setShowType(playerPageFragment.curShowType);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.viewPause = true;
            stopDrawThread();
            PlayerUtils.stop(true);
        } else {
            initView();
            startDrawThread();
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
        stopDrawThread();
        PlayerUtils.forceStop();
        this.binding.play3dGlView.destroy();
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
            this.mMediaPlayerHelper.release();
            this.mMediaPlayerHelper = null;
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MediaPlayerHelper mediaPlayerHelper;
        if (view.getId() != R.id.btn_play_preview || (mediaPlayerHelper = this.mMediaPlayerHelper) == null) {
            return;
        }
        if (mediaPlayerHelper.isPlaying()) {
            this.mMediaPlayerHelper.pause();
            playerStop();
            PlayerUtils.forceStop();
            return;
        }
        if (this.duration - this.curPos <= 800) {
            this.curPos = 0L;
            this.binding.seekbarProgress.setProgress(0);
            this.mMediaPlayerHelper.seekTo(0L);
        }
        this.mMediaPlayerHelper.start();
        PlayerUtils.play(this.playerListener, true, false, false);
        playerPlay();
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
            this.binding.play3dGlView.setPlayerPos(this.drawModelList, this.preBallModel.size());
            this.binding.play3dGlView.draw();
        }
    }

    public void drawGL() {
        if (this.binding.play3dGlView != null) {
            this.binding.play3dGlView.draw();
        }
    }

    private void playerPlay() {
        this.binding.btnPlayPreview.setImageResource(R.drawable.preview_stop);
    }

    private void playerStop() {
        this.binding.btnPlayPreview.setImageResource(R.drawable.preview_play);
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        EditingUtils.log("onStatusonStatusCallbackNext status = " + callBackState);
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            playerStop();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARE) {
            playerPlay();
            updatePlayer();
            return;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
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
            this.binding.textPlayTime.setText(BceConfig.BOS_DELIMITER + getDuration());
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
    public void onStartTrackingTouch(SeekBar seekBar) {
        MediaPlayerHelper mediaPlayerHelper;
        if (seekBar.getId() != R.id.seekbar_progress || (mediaPlayerHelper = this.mMediaPlayerHelper) == null) {
            return;
        }
        this.touchPause = true;
        mediaPlayerHelper.pause();
        PlayerUtils.forceStop();
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
                        PlayerUtils.play(this.playerListener, true, false, false);
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
        this.binding.viewLoading.setVisibility(0);
    }

    public void closeLoadingView() {
        this.binding.viewLoading.setVisibility(8);
    }
}
