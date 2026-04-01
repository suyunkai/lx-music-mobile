package com.wanos.careditproject.view.playerUI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.AppUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.PlayerPosViewAdapter;
import com.wanos.careditproject.databinding.FragmentPrviewPageBinding;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.DebounceClick;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.view.playerUI.Player3dGLRender;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PreviewPageFragment extends PlayerGlFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private FragmentPrviewPageBinding binding;
    private long curSampleNum;
    private Handler delayHandler;
    private Runnable delayRunnable;
    private long maxSampleNum;
    private PlayerUtils.PlayerListener playerListener;
    private PlayerPosViewAdapter playerPosViewAdapter;
    private boolean viewPause = false;
    private int curShowType = 0;
    private String preStr = "";
    private boolean isPreBallModelClear = false;
    private List<WebBallInfoModel> preBallModel = new ArrayList();
    private List<Integer> tmpList = new ArrayList();
    private int drawModelListDrawMax = 30;
    private WebBallInfoModel[] drawModelList = new WebBallInfoModel[30];
    private WebBallInfoModel[] drawModelListDraw = new WebBallInfoModel[30];
    private int curProgress = 0;
    private int progressIndex = 0;
    private boolean isPlayerStart = false;
    private int touchCount = 0;
    Handler resumeHandler = new Handler();
    Runnable resumeRunnable = new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.9
        @Override // java.lang.Runnable
        public void run() {
            if (PlayerUtils.isStop()) {
                PlayerUtils.clearReadData();
                PlayerUtils.play(PreviewPageFragment.this.playerListener, true, true, true);
            } else {
                PlayerUtils.resume(true);
            }
        }
    };

    @Override // com.wanos.careditproject.view.playerUI.PlayerGlFragment, com.wanos.commonlibrary.base.BaseFragment
    public void notifyMediaPlayBarVisiable(boolean z) {
    }

    public void updateSampleNum() {
    }

    static /* synthetic */ int access$508(PreviewPageFragment previewPageFragment) {
        int i = previewPageFragment.progressIndex;
        previewPageFragment.progressIndex = i + 1;
        return i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentPrviewPageBinding.inflate(getLayoutInflater());
        for (int i = 0; i < this.drawModelListDrawMax; i++) {
            this.drawModelListDraw[i] = new WebBallInfoModel();
        }
        initView();
        initListenter();
        startDrawThread();
        return this.binding.getRoot();
    }

    public void startPlay() {
        PlayerUtils.play(this.playerListener, true, true, false);
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.1
            @Override // java.lang.Runnable
            public void run() {
                PreviewPageFragment.this.playerPlay();
            }
        });
    }

    public void initView() {
        initProgress();
        initSpinner();
    }

    public void initProgress() {
        this.maxSampleNum = DataHelpAudioTrack.getMaxSampleNum();
        long progressSampleNumPreview = EditingParams.getInstance().getProgressSampleNumPreview();
        this.curSampleNum = progressSampleNumPreview;
        this.binding.seekbarProgress.setProgress(this.maxSampleNum == 0 ? 0 : (int) ((progressSampleNumPreview * ((long) this.binding.seekbarProgress.getMax())) / this.maxSampleNum));
        updateTimeText();
        this.binding.play3dGlView.setGlCreatedCallback(new Player3dGLRender.IGlCreatedCallback() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.2
            @Override // com.wanos.careditproject.view.playerUI.Player3dGLRender.IGlCreatedCallback
            public void onCallback() {
                PreviewPageFragment previewPageFragment = PreviewPageFragment.this;
                previewPageFragment.setShowType(previewPageFragment.curShowType);
                PreviewPageFragment.this.initBallInfo();
            }
        });
    }

    public void initSpinner() {
        EditingUtils.log("position initSpinner");
        this.playerPosViewAdapter = new PlayerPosViewAdapter(getContext(), R.layout.layout_spinner_player_show_item, EditingUtils.playerShowTypeList);
        this.binding.spinnerShowType.setAdapter((SpinnerAdapter) this.playerPosViewAdapter);
        this.binding.spinnerShowType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditingUtils.log("CreatorProjectPublishDialog onItemClick position = " + i);
                PreviewPageFragment.this.playerPosViewAdapter.setSelectIndex(i);
                PreviewPageFragment.this.playerPosViewAdapter.notifyDataSetChanged();
                PreviewPageFragment.this.curShowType = i;
                PreviewPageFragment.this.binding.play3dGlView.setShowType(PreviewPageFragment.this.curShowType);
            }
        });
        this.binding.spinnerShowType.setSelection(this.curShowType, false);
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

    public String getTimeText() {
        return EditingUtils.getTimeBySampleNum(this.curSampleNum) + BceConfig.BOS_DELIMITER + EditingUtils.getTimeBySampleNum(this.maxSampleNum);
    }

    protected String getPlayTime() {
        return EditingUtils.getTimeBySampleNum(this.curSampleNum);
    }

    protected String getDuration() {
        String timeBySampleNum = EditingUtils.getTimeBySampleNum(this.maxSampleNum);
        return this.maxSampleNum == 0 ? "00:00" : timeBySampleNum.equals("00:00") ? "00:01" : timeBySampleNum;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeText() {
        if (this.curSampleNum >= this.maxSampleNum) {
            this.binding.textPlayTime0.setText(getDuration());
        } else {
            this.binding.textPlayTime0.setText(getPlayTime());
        }
        this.binding.textPlayTime.setText(BceConfig.BOS_DELIMITER + getDuration());
    }

    public void initListenter() {
        this.binding.btnPlayPreview.setOnClickListener(new DebounceClick(new View.OnClickListener() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PreviewPageFragment.this.progressIndex = 0;
                if (PreviewPageFragment.this.isPlayerStart) {
                    if (PlayerUtils.isStop()) {
                        if (PreviewPageFragment.this.curSampleNum >= PreviewPageFragment.this.maxSampleNum - 1024) {
                            PreviewPageFragment.this.curSampleNum = 0L;
                            EditingParams.getInstance().setProgressSampleNumPreview(PreviewPageFragment.this.curSampleNum);
                        }
                        PreviewPageFragment.this.playerPlay();
                        PlayerUtils.play(PreviewPageFragment.this.playerListener, true, true, false);
                        return;
                    }
                    PreviewPageFragment.this.playerStop();
                    PlayerUtils.stop(false);
                }
            }
        }, 500L));
        this.binding.seekbarProgress.setOnSeekBarChangeListener(this);
        this.playerListener = new PlayerUtils.PlayerListener() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.5
            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onProgress(int i) {
                int max;
                PreviewPageFragment.this.curSampleNum = i;
                if (PreviewPageFragment.this.getActivity() != null && AppUtils.isAppForeground() && PreviewPageFragment.this.isAdded() && PreviewPageFragment.this.maxSampleNum != 0 && ((PreviewPageFragment.this.progressIndex % 4 == 0 || PreviewPageFragment.this.curSampleNum >= PreviewPageFragment.this.maxSampleNum) && (max = (int) ((PreviewPageFragment.this.curSampleNum * ((long) PreviewPageFragment.this.binding.seekbarProgress.getMax())) / PreviewPageFragment.this.maxSampleNum)) != PreviewPageFragment.this.curProgress)) {
                    PreviewPageFragment.this.curProgress = max;
                    PreviewPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PreviewPageFragment.this.curProgress >= PreviewPageFragment.this.binding.seekbarProgress.getMax()) {
                                PreviewPageFragment.this.binding.seekbarProgress.setProgress(PreviewPageFragment.this.binding.seekbarProgress.getMax());
                            } else {
                                PreviewPageFragment.this.binding.seekbarProgress.setProgress(PreviewPageFragment.this.curProgress);
                            }
                            PreviewPageFragment.this.updateTimeText();
                        }
                    });
                }
                PreviewPageFragment.access$508(PreviewPageFragment.this);
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void setWebBallInfo(List<WebBallInfoModel> list, boolean z) {
                if (AppUtils.isAppForeground()) {
                    PreviewPageFragment.this.updateWebView(list, z);
                }
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onStop() {
                EditingUtils.log("PlayerUtils.PlayerListener onstop");
                PreviewPageFragment.this.playerStop();
            }
        };
        toPlay();
    }

    private void toPlay() {
        EditingUtils.log("toPlay start maxSampleNum=" + this.maxSampleNum);
        if (this.maxSampleNum == 0) {
            return;
        }
        this.isPlayerStart = false;
        cancelToPlay();
        if (this.delayHandler == null) {
            this.delayHandler = new Handler(Looper.getMainLooper());
        }
        Runnable runnable = new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.6
            @Override // java.lang.Runnable
            public void run() {
                EditingUtils.log("toPlay in run");
                PreviewPageFragment.this.isPlayerStart = true;
                if (PreviewPageFragment.this.isHidden() || PreviewPageFragment.this.isRemoving()) {
                    return;
                }
                PreviewPageFragment.this.startPlay();
            }
        };
        this.delayRunnable = runnable;
        this.delayHandler.postDelayed(runnable, 1000L);
    }

    private void cancelToPlay() {
        Runnable runnable;
        EditingUtils.log("toPlay cancel");
        Handler handler = this.delayHandler;
        if (handler == null || (runnable = this.delayRunnable) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
        this.delayRunnable = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        EditingUtils.log("PreviewPageFragment onHiddenChanged");
        super.onHiddenChanged(z);
        if (z) {
            this.viewPause = true;
            stopDrawThread();
            this.binding.play3dGlView.setVisibility(8);
            PlayerUtils.stop(true);
            cancelToPlay();
            return;
        }
        this.maxSampleNum = DataHelpAudioTrack.getMaxSampleNum();
        startDrawThread();
        initBallInfo();
        this.binding.play3dGlView.setVisibility(0);
        initView();
        this.viewPause = false;
        toPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBallInfo() {
        this.isPreBallModelClear = true;
        updateWebView(new ArrayList(), true);
        this.isPreBallModelClear = false;
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
        EditingUtils.log("PreviewPageFragment onDestroy");
        stopDrawThread();
        PlayerUtils.stop(true);
        this.binding.play3dGlView.destroy();
        Handler handler = this.delayHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.delayHandler = null;
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_play_preview) {
            if (PlayerUtils.isStop()) {
                if (this.curSampleNum >= this.maxSampleNum - 1024) {
                    this.curSampleNum = 0L;
                    EditingParams.getInstance().setProgressSampleNumPreview(this.curSampleNum);
                }
                playerPlay();
                PlayerUtils.play(this.playerListener, true, true, false);
                return;
            }
            playerStop();
            PlayerUtils.stop(false);
        }
    }

    public void updateWebView(List<WebBallInfoModel> list, boolean z) {
        int i;
        synchronized (this.preBallModel) {
            if (this.isPreBallModelClear) {
                this.preBallModel.clear();
                this.isPreBallModelClear = false;
            }
            EditingUtils.mergeBallList(this.preBallModel, list, this.tmpList);
        }
        if (z) {
            for (i = 0; i < this.preBallModel.size(); i++) {
                this.drawModelList[i] = this.preBallModel.get(i);
                this.drawModelList[i].alpha = 100;
            }
            this.binding.play3dGlView.setPlayerPos(this.drawModelList, this.preBallModel.size());
            this.binding.play3dGlView.draw();
        }
    }

    public void drawGL() {
        this.binding.play3dGlView.draw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playerPlay() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    PreviewPageFragment.this.binding.btnPlayPreview.setImageResource(R.drawable.preview_stop);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playerStop() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PreviewPageFragment.8
                @Override // java.lang.Runnable
                public void run() {
                    PreviewPageFragment.this.binding.btnPlayPreview.setImageResource(R.drawable.preview_play);
                }
            });
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z && seekBar.getId() == R.id.seekbar_progress) {
            this.touchCount++;
            this.curSampleNum = (((long) i) * this.maxSampleNum) / ((long) this.binding.seekbarProgress.getMax());
            updateTimeText();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar_progress) {
            this.touchCount = 0;
            if (PlayerUtils.isStop()) {
                return;
            }
            PlayerUtils.pause();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar_progress) {
            this.isPreBallModelClear = true;
            EditingParams.getInstance().setProgressSampleNumPreview(this.curSampleNum);
            this.resumeHandler.removeCallbacks(this.resumeRunnable);
            if (this.touchCount < 5) {
                this.resumeHandler.postDelayed(this.resumeRunnable, 300L);
            } else {
                this.resumeHandler.postDelayed(this.resumeRunnable, 40L);
            }
        }
    }
}
