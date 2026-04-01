package com.wanos.careditproject.utils.exPlayer;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.AiAudioFocusState;
import com.wanos.careditproject.data.bean.AiCancelResponse;
import com.wanos.careditproject.data.bean.AiCreateCancelResponse;
import com.wanos.careditproject.data.bean.AiCreateFail;
import com.wanos.careditproject.data.bean.AiCreateProgress;
import com.wanos.careditproject.data.bean.AiCreateResponse;
import com.wanos.careditproject.data.bean.AiCreateSuccess;
import com.wanos.careditproject.data.bean.AiFindCreateStateEntity;
import com.wanos.careditproject.data.bean.AiFindCreateStateResponse;
import com.wanos.careditproject.data.bean.AiImplState;
import com.wanos.careditproject.data.bean.AiPlayEntity;
import com.wanos.careditproject.data.bean.AiPlayState;
import com.wanos.careditproject.data.bean.IAiCreateState;
import com.wanos.careditproject.data.repo.AiRetrofitUtil;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.exPlayer.AiExPlayMediaHelp;
import com.wanos.careditproject.utils.exPlayer.AiExPreviewTask;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public class AiExPrePlayerViewModel extends ViewModel implements AiExPlayMediaHelp.IAudioFocusChange {
    private static final Long AI_MAX_TIME = 300000L;
    public static final String KEY_AI_CONTENT = "AI_CONTENT";
    private static final String TAG = "Wanos[AI]-AiExPrePlayerViewModel";
    private static final int WHAT_MAIN_PROGRESS = 102;
    private static final int WHAT_SEEK_BAR_TOUCH = 101;
    private final MutableLiveData<AiPlayEntity> _AiBtnState;
    private final MutableLiveData<AiImplState> _AiImplState;
    private final MutableLiveData<List<AiPlayEntity>> _AiMusicList;
    private final MutableLiveData<IAiCreateState> _AiMusicLoadState;
    private final MutableLiveData<AiPlayState> _AiPlayerState;
    private final MutableLiveData<Integer> _MediaCurrentDuration;
    private final MutableLiveData<Integer> _MediaDuration;
    private final MediatorLiveData<Boolean> _SeekBarEnable;
    public final LiveData<AiPlayEntity> aiBtnState;
    public final LiveData<Integer> aiCreateLottieVisible;
    private final AiExPlayDataHelp aiExPlayDataHelp;
    private final AiExPlayMediaHelp aiExPlayMediaHelp;
    private final AiExPlayRepo aiExPlayRepo;
    public final LiveData<AiImplState> aiImplState;
    public final LiveData<List<AiPlayEntity>> aiMusicList;
    public final LiveData<IAiCreateState> aiMusicLoadState;
    public final LiveData<Integer> aiPlayButtonVisible;
    public final LiveData<Boolean> aiPlayerLottiePlayState;
    public final LiveData<Integer> aiPlayerLottieVisible;
    public final LiveData<AiPlayState> aiPlayerState;
    public final LiveData<Integer> aiRecreateButtonVisible;
    private int implProjectId;
    private final AtomicBoolean isTouchSeekBar;
    private final String mAiKeyText;
    private final Handler mHandler;
    private final PlayerUtils.PlayerListener mMediaCallback;
    private String mNowPlayerTrackId;
    private String mNowPlayerTrackIndex;
    private AiExPreviewTask mPreviewTask;
    public final LiveData<Integer> mediaCurrentDuration;
    public final LiveData<Integer> mediaDuration;
    public final LiveData<Boolean> seekBarEnable;

    static /* synthetic */ Integer lambda$new$0(IAiCreateState iAiCreateState) {
        if (iAiCreateState instanceof AiCreateProgress) {
            return 0;
        }
        return 8;
    }

    static /* synthetic */ Integer lambda$new$1(IAiCreateState iAiCreateState) {
        if (iAiCreateState instanceof AiCreateSuccess) {
            return 0;
        }
        return 8;
    }

    static /* synthetic */ Integer lambda$new$2(IAiCreateState iAiCreateState) {
        if ((iAiCreateState instanceof AiCreateSuccess) || (iAiCreateState instanceof AiCreateFail)) {
            return 0;
        }
        return 4;
    }

    static /* synthetic */ Integer lambda$new$3(IAiCreateState iAiCreateState) {
        if (iAiCreateState instanceof AiCreateSuccess) {
            return 0;
        }
        return 4;
    }

    /* JADX INFO: renamed from: lambda$new$5$com-wanos-careditproject-utils-exPlayer-AiExPrePlayerViewModel, reason: not valid java name */
    /* synthetic */ boolean m411x398b7fc7(Message message) {
        int i = message.what;
        if (i == 101) {
            Log.i(TAG, "WHAT_SEEK_BAR_TOUCH: 离开SeekBar事件");
            this.isTouchSeekBar.set(false);
            this.aiExPlayMediaHelp.setProgressSampleNum(((Integer) message.obj).intValue());
            setMediaStart(true);
        } else if (i == 102) {
            if (AppUtils.isAppForeground() && AiPlayState.START == this.aiPlayerState.getValue()) {
                this._MediaCurrentDuration.setValue(Integer.valueOf(EditingUtils.getMsBySampleNum(((Integer) message.obj).intValue())));
            } else {
                Log.w(TAG, "跟新进度错误: state = " + this.aiPlayerState.getValue());
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: lambda$new$6$com-wanos-careditproject-utils-exPlayer-AiExPrePlayerViewModel, reason: not valid java name */
    /* synthetic */ void m412xb7ec83a6(IAiCreateState iAiCreateState) {
        initSeekBarEnable();
    }

    /* JADX INFO: renamed from: lambda$new$7$com-wanos-careditproject-utils-exPlayer-AiExPrePlayerViewModel, reason: not valid java name */
    /* synthetic */ void m413x364d8785(AiPlayState aiPlayState) {
        initSeekBarEnable();
    }

    public AiExPrePlayerViewModel(Intent intent) {
        MutableLiveData<AiPlayState> mutableLiveData = new MutableLiveData<>(AiPlayState.DEFAULT);
        this._AiPlayerState = mutableLiveData;
        this.aiPlayerState = mutableLiveData;
        this.aiExPlayDataHelp = new AiExPlayDataHelp();
        this.aiExPlayMediaHelp = new AiExPlayMediaHelp(this);
        this.aiExPlayRepo = new AiExPlayRepo();
        this.mPreviewTask = null;
        this.isTouchSeekBar = new AtomicBoolean(false);
        MutableLiveData<IAiCreateState> mutableLiveData2 = new MutableLiveData<>(new AiCreateProgress(R.string.ai_create_progress_1));
        this._AiMusicLoadState = mutableLiveData2;
        this.aiMusicLoadState = mutableLiveData2;
        this.aiCreateLottieVisible = Transformations.map(mutableLiveData2, new Function1() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AiExPrePlayerViewModel.lambda$new$0((IAiCreateState) obj);
            }
        });
        this.aiPlayerLottieVisible = Transformations.map(mutableLiveData2, new Function1() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AiExPrePlayerViewModel.lambda$new$1((IAiCreateState) obj);
            }
        });
        this.aiRecreateButtonVisible = Transformations.map(mutableLiveData2, new Function1() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AiExPrePlayerViewModel.lambda$new$2((IAiCreateState) obj);
            }
        });
        this.aiPlayButtonVisible = Transformations.map(mutableLiveData2, new Function1() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AiExPrePlayerViewModel.lambda$new$3((IAiCreateState) obj);
            }
        });
        MutableLiveData<List<AiPlayEntity>> mutableLiveData3 = new MutableLiveData<>();
        this._AiMusicList = mutableLiveData3;
        this.aiMusicList = mutableLiveData3;
        MutableLiveData<AiPlayEntity> mutableLiveData4 = new MutableLiveData<>();
        this._AiBtnState = mutableLiveData4;
        this.aiBtnState = mutableLiveData4;
        this.aiPlayerLottiePlayState = Transformations.map(mutableLiveData4, new Function1() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((AiPlayEntity) obj).getState() == AiPlayState.START);
            }
        });
        MediatorLiveData<Boolean> mediatorLiveData = new MediatorLiveData<>();
        this._SeekBarEnable = mediatorLiveData;
        this.seekBarEnable = mediatorLiveData;
        MutableLiveData<Integer> mutableLiveData5 = new MutableLiveData<>(0);
        this._MediaDuration = mutableLiveData5;
        this.mediaDuration = mutableLiveData5;
        MutableLiveData<Integer> mutableLiveData6 = new MutableLiveData<>(0);
        this._MediaCurrentDuration = mutableLiveData6;
        this.mediaCurrentDuration = mutableLiveData6;
        MutableLiveData<AiImplState> mutableLiveData7 = new MutableLiveData<>(AiImplState.DEFAULT);
        this._AiImplState = mutableLiveData7;
        this.aiImplState = mutableLiveData7;
        this.implProjectId = -1;
        this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda5
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f$0.m411x398b7fc7(message);
            }
        });
        this.mMediaCallback = new PlayerUtils.PlayerListener() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel.1
            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void setWebBallInfo(List<WebBallInfoModel> list, boolean z) {
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onProgress(int i) {
                Message messageObtainMessage = AiExPrePlayerViewModel.this.mHandler.obtainMessage(102);
                messageObtainMessage.obj = Integer.valueOf(i);
                AiExPrePlayerViewModel.this.mHandler.sendMessage(messageObtainMessage);
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onStop() {
                if (!AiExPrePlayerViewModel.this.isTouchSeekBar.get()) {
                    if (AiExPrePlayerViewModel.this._AiPlayerState.getValue() != AiPlayState.START) {
                        Log.i(AiExPrePlayerViewModel.TAG, "PlayerListener: 当前播放状态 = " + AiExPrePlayerViewModel.this._AiPlayerState.getValue() + ", 不需要进行修改状态");
                        return;
                    }
                    AiExPrePlayerViewModel.this._AiPlayerState.postValue(AiPlayState.STOP);
                    AiExPrePlayerViewModel aiExPrePlayerViewModel = AiExPrePlayerViewModel.this;
                    aiExPrePlayerViewModel.notifyItemState(aiExPrePlayerViewModel.mNowPlayerTrackIndex, AiPlayState.STOP);
                    AiExPrePlayerViewModel aiExPrePlayerViewModel2 = AiExPrePlayerViewModel.this;
                    int iMilliToSecond = aiExPrePlayerViewModel2.milliToSecond(aiExPrePlayerViewModel2.mediaCurrentDuration.getValue());
                    AiExPrePlayerViewModel aiExPrePlayerViewModel3 = AiExPrePlayerViewModel.this;
                    if (iMilliToSecond == aiExPrePlayerViewModel3.milliToSecond(aiExPrePlayerViewModel3.mediaDuration.getValue())) {
                        AiExPrePlayerViewModel.this._MediaCurrentDuration.postValue(0);
                        AiExPrePlayerViewModel.this.aiExPlayMediaHelp.setProgressSampleNum(0);
                        return;
                    }
                    return;
                }
                Log.i(AiExPrePlayerViewModel.TAG, "PlayerListener: 调整SeekBar导致暂停");
            }
        };
        mediatorLiveData.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m412xb7ec83a6((IAiCreateState) obj);
            }
        });
        mediatorLiveData.addSource(mutableLiveData, new Observer() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m413x364d8785((AiPlayState) obj);
            }
        });
        this.mAiKeyText = intent.getStringExtra(KEY_AI_CONTENT);
        initAiMusic();
    }

    public void onItemClick(AiPlayEntity aiPlayEntity) {
        if (this.aiPlayerState.getValue() == AiPlayState.LOADING) {
            ToastUtil.showMsg("资源正在加载中，请稍后~");
            return;
        }
        if (TextUtils.equals(aiPlayEntity.getId(), this.mNowPlayerTrackIndex)) {
            if (this.aiPlayerState.getValue() == AiPlayState.START) {
                setMediaStop();
                return;
            } else if (this.aiPlayerState.getValue() == AiPlayState.STOP) {
                setMediaStart();
                return;
            } else {
                Log.w(TAG, "onItemClick: state = " + this.aiPlayerState.getValue());
                return;
            }
        }
        if (this.aiPlayerState.getValue() == AiPlayState.START) {
            setMediaStop();
        }
        setAndPlayerIndex(this.mNowPlayerTrackId, aiPlayEntity.getId());
    }

    public void recreateAiMusic() {
        if (this.aiMusicLoadState.getValue() instanceof AiCreateProgress) {
            ToastUtil.showMsg(R.string.ai_load_action);
            return;
        }
        AiExPreviewTask aiExPreviewTask = this.mPreviewTask;
        if (aiExPreviewTask != null && aiExPreviewTask.isRunning()) {
            this.mPreviewTask.cancel();
        }
        this._AiMusicLoadState.setValue(new AiCreateProgress(R.string.ai_create_progress_1));
        this._MediaDuration.setValue(0);
        resetProgress();
        resetMedia();
        initAiMusic();
    }

    public void implStudio() {
        if (canAiBeUsed()) {
            this._AiImplState.setValue(AiImplState.IMPL_ING);
            setMediaStop();
            ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Integer>() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel.2
                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public Integer doInBackground() throws Throwable {
                    Log.i(AiExPrePlayerViewModel.TAG, "implStudio: track_id = " + AiExPrePlayerViewModel.this.mNowPlayerTrackId + ", track_index = " + AiExPrePlayerViewModel.this.mNowPlayerTrackIndex);
                    return Integer.valueOf(AiExPrePlayerViewModel.this.aiExPlayRepo.implAiProject(AiExPrePlayerViewModel.this.mNowPlayerTrackId, AiExPrePlayerViewModel.this.mNowPlayerTrackIndex));
                }

                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public void onSuccess(Integer num) {
                    Log.i(AiExPrePlayerViewModel.TAG, "onSuccess: 项目导入ID = " + num);
                    AiExPrePlayerViewModel.this.implProjectId = num.intValue();
                    AiExPrePlayerViewModel.this._AiImplState.setValue(AiImplState.IMPL_SUCCESS);
                }

                @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
                public void onFail(Throwable th) {
                    super.onFail(th);
                    Log.w(AiExPrePlayerViewModel.TAG, "onFail: " + th.getMessage());
                    AiExPrePlayerViewModel.this._AiImplState.setValue(AiImplState.IMPL_FAIL);
                    if (StringUtils.isEmpty(th.getMessage())) {
                        return;
                    }
                    ToastUtil.showMsg(th.getMessage());
                }
            });
        }
    }

    public void cancelImpl() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<AiImplState>() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel.3
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public AiImplState doInBackground() throws Throwable {
                AiCancelResponse aiCancelResponseCancelImpl = AiExPrePlayerViewModel.this.aiExPlayRepo.cancelImpl();
                if (aiCancelResponseCancelImpl == null) {
                    return AiImplState.IMPL_FAIL;
                }
                if (aiCancelResponseCancelImpl.code == 0) {
                    return AiImplState.DEFAULT;
                }
                return AiImplState.IMPL_FAIL;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(AiImplState aiImplState) {
                AiExPrePlayerViewModel.this._AiImplState.setValue(aiImplState);
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                ToastUtil.showMsg(th.getMessage());
                AiExPrePlayerViewModel.this._AiImplState.setValue(AiImplState.IMPL_FAIL);
            }
        });
    }

    public boolean implCommunity() {
        if (!canAiBeUsed()) {
            return false;
        }
        Log.i(TAG, "implCommunity: track_id = " + this.mNowPlayerTrackId + ", track_index = " + this.mNowPlayerTrackIndex);
        setMediaStop();
        return true;
    }

    public int getImplProjectId() {
        return this.implProjectId;
    }

    public String getNowTrackId() {
        return this.mNowPlayerTrackId;
    }

    public String getNowTrackIndex() {
        return this.mNowPlayerTrackIndex;
    }

    private boolean canAiBeUsed() {
        if (!(this.aiMusicLoadState.getValue() instanceof AiCreateSuccess) || StringUtils.isEmpty(this.mNowPlayerTrackId)) {
            ToastUtil.showMsg(R.string.ai_load_action);
            return false;
        }
        if (StringUtils.isEmpty(this.mNowPlayerTrackIndex)) {
            ToastUtil.showMsg(R.string.ai_player_action);
            return false;
        }
        if (this.aiPlayerState.getValue() == AiPlayState.LOADING) {
            ToastUtil.showMsg(R.string.ai_player_loading);
            return false;
        }
        if (this.mediaDuration.getValue() == null || this.mediaDuration.getValue().intValue() <= AI_MAX_TIME.longValue()) {
            return true;
        }
        ToastUtil.showMsg(R.string.ai_audio_time_error);
        return false;
    }

    private void initAiMusic() {
        if (StringUtils.isEmpty(this.mAiKeyText)) {
            this._AiMusicLoadState.setValue(new AiCreateFail(101, "关键字错误，请返回重试~"));
            return;
        }
        Log.i(TAG, "initAiMusic: AiKeY = " + this.mAiKeyText);
        this.mNowPlayerTrackId = "";
        this.mNowPlayerTrackIndex = "";
        ThreadUtils.executeByCpu(new AnonymousClass4(System.currentTimeMillis()));
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$4, reason: invalid class name */
    class AnonymousClass4 extends ThreadUtils.SimpleTask<AiFindCreateStateEntity> {
        final /* synthetic */ long val$mStartTime;

        AnonymousClass4(long j) {
            this.val$mStartTime = j;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public AiFindCreateStateEntity doInBackground() throws Throwable {
            long jCurrentTimeMillis;
            AiCreateResponse aiCreateResponseCreateAiMusic = AiRetrofitUtil.getInstance().createAiMusic(AiExPrePlayerViewModel.this.mAiKeyText);
            if (aiCreateResponseCreateAiMusic == null) {
                AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, "任务创建失败~"));
                return null;
            }
            if (aiCreateResponseCreateAiMusic.code != 0 || aiCreateResponseCreateAiMusic.getData() == null) {
                AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, aiCreateResponseCreateAiMusic.msg));
                return null;
            }
            String track_id = aiCreateResponseCreateAiMusic.getData().getTrack_id();
            if (StringUtils.isEmpty(track_id)) {
                AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, "任务创建异常~"));
                return null;
            }
            AiExPrePlayerViewModel.this.aiExPlayRepo.setNowTrackId(track_id);
            do {
                AiFindCreateStateResponse aiFindCreateStateResponseFindCreateAiMusicState = AiRetrofitUtil.getInstance().findCreateAiMusicState(track_id);
                if (aiFindCreateStateResponseFindCreateAiMusicState == null) {
                    Log.e(AiExPrePlayerViewModel.TAG, "initAiMusic: AI状态查询接口请求失败");
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, StringUtils.getString(R.string.ai_create_unknown_error)));
                    return null;
                }
                AiFindCreateStateEntity data = aiFindCreateStateResponseFindCreateAiMusicState.getData();
                if (aiFindCreateStateResponseFindCreateAiMusicState.code != 0 || data == null) {
                    Log.e(AiExPrePlayerViewModel.TAG, "initAiMusic: AI状态查询接口返回错误 code = " + aiFindCreateStateResponseFindCreateAiMusicState.code + ", msg = " + aiFindCreateStateResponseFindCreateAiMusicState.msg);
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, aiFindCreateStateResponseFindCreateAiMusicState.msg));
                    return null;
                }
                if (data.getState() == 4) {
                    Log.e(AiExPrePlayerViewModel.TAG, "initAiMusic: AI生成失败，error = " + data.getError());
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(101, data.getError()));
                    return null;
                }
                if (data.getState() == 5) {
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(102, "任务已取消"));
                    return null;
                }
                if (data.getState() == 0 || data.getState() == 1) {
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateProgress(R.string.ai_create_progress_1));
                    TimeUnit.MILLISECONDS.sleep(3000L);
                } else if (data.getState() == 6) {
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateProgress(R.string.ai_create_progress_2));
                    TimeUnit.MILLISECONDS.sleep(3000L);
                } else if (data.getState() == 7) {
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateProgress(R.string.ai_create_progress_3));
                    TimeUnit.MILLISECONDS.sleep(3000L);
                } else if (data.getState() == 3) {
                    AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateSuccess(data));
                    data.setTrack_id(track_id);
                    Log.i(AiExPrePlayerViewModel.TAG, "initAiMusic: 缓存清理结果 = " + AiExPrePlayerViewModel.this.aiExPlayDataHelp.deleteCache());
                    return data;
                }
                jCurrentTimeMillis = System.currentTimeMillis() - this.val$mStartTime;
                if (AiExPrePlayerViewModel.this.aiExPlayRepo.getDestroyState()) {
                    break;
                }
            } while (jCurrentTimeMillis <= 180000);
            if (jCurrentTimeMillis > 180000) {
                Log.w(AiExPrePlayerViewModel.TAG, "findProjectData: 项目生成超时，设定超时时间 = 180000, 当前已运行时间 = " + jCurrentTimeMillis + ", track_id = " + track_id);
                AiExPrePlayerViewModel.this._AiMusicLoadState.postValue(new AiCreateFail(103, StringUtils.getString(R.string.ai_create_out_time)));
            }
            return null;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(AiFindCreateStateEntity aiFindCreateStateEntity) {
            if (aiFindCreateStateEntity != null) {
                Log.i(AiExPrePlayerViewModel.TAG, "AI生成成功，耗时 = " + (System.currentTimeMillis() - this.val$mStartTime) + "ms");
                AiExPrePlayerViewModel aiExPrePlayerViewModel = AiExPrePlayerViewModel.this;
                aiExPrePlayerViewModel.mNowPlayerTrackId = aiExPrePlayerViewModel.aiExPlayRepo.getTrack_Id();
                final ArrayList arrayList = new ArrayList();
                aiFindCreateStateEntity.getOutputDatas().forEach(new BiConsumer() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel$4$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        arrayList.add(new AiPlayEntity((String) obj));
                    }
                });
                if (!arrayList.isEmpty()) {
                    AiExPrePlayerViewModel.this._AiMusicList.setValue(arrayList);
                    AiExPrePlayerViewModel aiExPrePlayerViewModel2 = AiExPrePlayerViewModel.this;
                    aiExPrePlayerViewModel2.onItemClick((AiPlayEntity) ((List) aiExPrePlayerViewModel2._AiMusicList.getValue()).get(0));
                    return;
                }
                onFail(new Throwable(StringUtils.getString(R.string.ai_load_error)));
                return;
            }
            Log.i(AiExPrePlayerViewModel.TAG, "AI生成失败，耗时 = " + (System.currentTimeMillis() - this.val$mStartTime) + "ms");
        }
    }

    public boolean onBackPressed() {
        return this.aiMusicLoadState.getValue() instanceof AiCreateProgress;
    }

    private void resetProgress() {
        this.aiExPlayMediaHelp.setProgressSampleNum(0);
        this._MediaCurrentDuration.setValue(0);
    }

    public void cancelAiCreate() {
        if ((this.aiMusicLoadState.getValue() instanceof AiCreateSuccess) || (this.aiMusicLoadState.getValue() instanceof AiCreateFail)) {
            Log.w(TAG, "cancelAiCreate: AI生成已经有结果了。不调用接口了");
            return;
        }
        this._AiMusicLoadState.setValue(new AiCreateFail(102, "任务已取消"));
        if (StringUtils.isEmpty(this.aiExPlayRepo.getTrack_Id())) {
            Log.i(TAG, "cancelAiCreate: AI未创建任务");
        } else {
            AiRetrofitUtil.getInstance().cancelAiCreate(this.aiExPlayRepo.getTrack_Id(), new ResponseCallBack<AiCreateCancelResponse>(null) { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(AiCreateCancelResponse aiCreateCancelResponse) {
                    Log.i(AiExPrePlayerViewModel.TAG, "onResponseSuccessful: Ai取消成功");
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    Log.w(AiExPrePlayerViewModel.TAG, "onResponseFailure: Ai取消失败，code = " + i + ", msg = " + str);
                }
            });
        }
    }

    private void setAndPlayerIndex(String str, String str2) {
        this._AiPlayerState.setValue(AiPlayState.LOADING);
        notifyItemState(str2, AiPlayState.LOADING);
        this._MediaDuration.setValue(0);
        this._MediaCurrentDuration.setValue(0);
        AiExPreviewTask aiExPreviewTask = new AiExPreviewTask(this.aiExPlayDataHelp, str, str2);
        this.mPreviewTask = aiExPreviewTask;
        aiExPreviewTask.execute(new AiExPreviewTask.ITaskCallback() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel.6
            @Override // com.wanos.careditproject.utils.exPlayer.AiExPreviewTask.ITaskCallback
            public void onSuccess(String str3, String str4) {
                int iInitAndPlayer = AiExPrePlayerViewModel.this.aiExPlayMediaHelp.initAndPlayer(AiExPrePlayerViewModel.this.mMediaCallback);
                if (iInitAndPlayer != -1) {
                    AiExPrePlayerViewModel.this.mNowPlayerTrackIndex = str4;
                    AiExPrePlayerViewModel.this._AiPlayerState.setValue(AiPlayState.START);
                    AiExPrePlayerViewModel.this._MediaDuration.setValue(Integer.valueOf(iInitAndPlayer));
                    AiExPrePlayerViewModel.this.notifyItemState(str4, AiPlayState.START);
                    return;
                }
                ToastUtil.showMsg(com.wanos.media.R.string.audio_focus_occupation_no_play);
            }

            @Override // com.wanos.careditproject.utils.exPlayer.AiExPreviewTask.ITaskCallback
            public void onFail(Throwable th) {
                ToastUtil.showMsg(th.getMessage());
                AiExPrePlayerViewModel.this._AiPlayerState.setValue(AiPlayState.DEFAULT);
                AiExPrePlayerViewModel aiExPrePlayerViewModel = AiExPrePlayerViewModel.this;
                aiExPrePlayerViewModel.notifyItemState(aiExPrePlayerViewModel.mNowPlayerTrackIndex, AiPlayState.DEFAULT);
            }
        });
        resetProgress();
    }

    public void setProgress(int i) {
        this._MediaCurrentDuration.setValue(Integer.valueOf(i));
    }

    public void onStartTrackingTouch() {
        boolean z = this.isTouchSeekBar.get();
        Log.i(TAG, "onStartTrackingTouch: isTouch = " + z);
        if (z) {
            this.mHandler.removeMessages(101);
        } else {
            this.isTouchSeekBar.set(true);
            this.aiExPlayMediaHelp.stop();
        }
    }

    public void onStopTrackingTouch(int i) {
        Log.i(TAG, "onStopTrackingTouch: ms = " + i);
        Message messageObtainMessage = this.mHandler.obtainMessage(101);
        messageObtainMessage.obj = Integer.valueOf(i);
        this.mHandler.sendMessageDelayed(messageObtainMessage, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyItemState(String str, AiPlayState aiPlayState) {
        AiPlayEntity aiPlayEntity = new AiPlayEntity(str);
        aiPlayEntity.setState(aiPlayState);
        if (ThreadUtils.isMainThread()) {
            this._AiBtnState.setValue(aiPlayEntity);
        } else {
            this._AiBtnState.postValue(aiPlayEntity);
        }
    }

    private void setMediaStop(AiAudioFocusState aiAudioFocusState) {
        if (this.aiPlayerState.getValue() == AiPlayState.START) {
            this.aiExPlayMediaHelp.stop(aiAudioFocusState);
            this._AiPlayerState.setValue(AiPlayState.STOP);
            notifyItemState(this.mNowPlayerTrackIndex, AiPlayState.STOP);
            return;
        }
        Log.w(TAG, "setMediaStop: State = " + this.aiPlayerState.getValue());
    }

    private void setMediaStop() {
        setMediaStop(AiAudioFocusState.USER);
    }

    private void setMediaStart() {
        setMediaStart(false);
    }

    private void setMediaStart(boolean z) {
        if (z || this.aiPlayerState.getValue() == AiPlayState.STOP) {
            if (this.aiExPlayMediaHelp.start()) {
                this._AiPlayerState.setValue(AiPlayState.START);
                notifyItemState(this.mNowPlayerTrackIndex, AiPlayState.START);
                return;
            } else {
                ToastUtil.showMsg(com.wanos.media.R.string.audio_focus_occupation_no_play);
                return;
            }
        }
        Log.w(TAG, "setMediaStart: State = " + this.aiPlayerState.getValue() + ", isTouchSeekBar = " + this.isTouchSeekBar.get());
    }

    private void resetMedia() {
        Log.i(TAG, "resetMedia: ");
        if (this.aiPlayerState.getValue() == AiPlayState.START) {
            this.aiExPlayMediaHelp.reset();
            notifyItemState(this.mNowPlayerTrackIndex, AiPlayState.DEFAULT);
        }
        this._AiPlayerState.setValue(AiPlayState.DEFAULT);
    }

    public String formatMillisToMinutesSeconds(long j) {
        if (j < 1000) {
            return "00:00";
        }
        long j2 = j / 1000;
        return String.format(Locale.US, "%02d:%02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60));
    }

    private void initSeekBarEnable() {
        if ((this.aiMusicLoadState.getValue() instanceof AiCreateSuccess) && (this.aiPlayerState.getValue() == AiPlayState.START || this.aiPlayerState.getValue() == AiPlayState.STOP)) {
            this._SeekBarEnable.setValue(true);
        } else {
            this._SeekBarEnable.setValue(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int milliToSecond(Integer num) {
        if (num == null) {
            return 0;
        }
        return (int) (num.intValue() / 1000.0f);
    }

    @Override // com.wanos.careditproject.utils.exPlayer.AiExPlayMediaHelp.IAudioFocusChange
    public void onAudioFocusGain() {
        setMediaStart();
    }

    @Override // com.wanos.careditproject.utils.exPlayer.AiExPlayMediaHelp.IAudioFocusChange
    public void onAudioFocusLoss() {
        setMediaStop(AiAudioFocusState.FOCUS);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.mHandler.removeMessages(101);
        this.mHandler.removeMessages(102);
        this.aiExPlayMediaHelp.onCleared();
        this.aiExPlayRepo.onCleared();
        this.aiExPlayDataHelp.onCleared();
    }
}
