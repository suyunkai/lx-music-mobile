package com.wanos.careditproject.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.AiPlayerAdapter;
import com.wanos.careditproject.data.bean.AiCreateFail;
import com.wanos.careditproject.data.bean.AiCreateProgress;
import com.wanos.careditproject.data.bean.AiCreateSuccess;
import com.wanos.careditproject.data.bean.AiImplState;
import com.wanos.careditproject.data.bean.AiPlayEntity;
import com.wanos.careditproject.data.bean.IAiCreateState;
import com.wanos.careditproject.databinding.ActivityAiPlayBinding;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.exPlayer.AiExPrePlayerViewModel;
import com.wanos.careditproject.view.AiExitDialog;
import com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.ui.widget.WanosTextView;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ToastUtil;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AiPlayActivity extends WanosBaseActivity implements AiExitDialog.IExitDialogListener {
    private static final String TAG = "wanos[AI]-AiPlayActivity";
    private AiPlayerAdapter adapter;
    private AiImplLoadingDialog aiImplLoadingDialog;
    private ActivityAiPlayBinding binding;
    private AiExPrePlayerViewModel exPlayerViewModel;

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) AiPlayActivity.class);
        intent.putExtra(AiExPrePlayerViewModel.KEY_AI_CONTENT, str);
        activity.startActivity(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.exPlayerViewModel = (AiExPrePlayerViewModel) new ViewModelProvider(this, new ViewModelProvider.Factory() { // from class: com.wanos.careditproject.view.AiPlayActivity.1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> cls) {
                return new AiExPrePlayerViewModel(AiPlayActivity.this.getIntent());
            }
        }).get(AiExPrePlayerViewModel.class);
        this.binding = ActivityAiPlayBinding.inflate(getLayoutInflater());
        BarUtils.setStatusBarColor(this, Color.parseColor("#20272E"));
        BarUtils.setStatusBarLightMode((Activity) this, false);
        setContentView(this.binding.getRoot());
        setTitleBarVisibility(8);
        setPlayBarVisibility(8);
        this.binding.lottieCreate.setImageAssetsFolder("animations/imageLoading/");
        this.binding.lottieCreate.setAnimation(R.raw.loading_ball_precom);
        initAdapter(this.binding, this.exPlayerViewModel);
        initObserve(this.binding, this.exPlayerViewModel);
        initListener(this.binding, this.exPlayerViewModel);
    }

    private void initAdapter(ActivityAiPlayBinding activityAiPlayBinding, AiExPrePlayerViewModel aiExPrePlayerViewModel) {
        this.adapter = new AiPlayerAdapter();
        activityAiPlayBinding.rvPlayerBtn.setLayoutManager(new LinearLayoutManager(this, 0, false));
        activityAiPlayBinding.rvPlayerBtn.setAdapter(this.adapter);
    }

    private void initObserve(final ActivityAiPlayBinding activityAiPlayBinding, final AiExPrePlayerViewModel aiExPrePlayerViewModel) {
        aiExPrePlayerViewModel.aiCreateLottieVisible.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$0(activityAiPlayBinding, (Integer) obj);
            }
        });
        aiExPrePlayerViewModel.aiPlayerLottieVisible.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$1(activityAiPlayBinding, (Integer) obj);
            }
        });
        LiveData<Integer> liveData = aiExPrePlayerViewModel.aiRecreateButtonVisible;
        final WanosTextView wanosTextView = activityAiPlayBinding.btnRecreate;
        Objects.requireNonNull(wanosTextView);
        liveData.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                wanosTextView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData2 = aiExPrePlayerViewModel.aiPlayButtonVisible;
        final RecyclerView recyclerView = activityAiPlayBinding.rvPlayerBtn;
        Objects.requireNonNull(recyclerView);
        liveData2.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                recyclerView.setVisibility(((Integer) obj).intValue());
            }
        });
        aiExPrePlayerViewModel.aiMusicLoadState.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$2(activityAiPlayBinding, (IAiCreateState) obj);
            }
        });
        aiExPrePlayerViewModel.aiPlayerLottiePlayState.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$3(activityAiPlayBinding, (Boolean) obj);
            }
        });
        aiExPrePlayerViewModel.aiMusicList.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m416x9e5ba1b7((List) obj);
            }
        });
        aiExPrePlayerViewModel.aiBtnState.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m417x57d32f56((AiPlayEntity) obj);
            }
        });
        aiExPrePlayerViewModel.mediaDuration.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$6(activityAiPlayBinding, aiExPrePlayerViewModel, (Integer) obj);
            }
        });
        aiExPrePlayerViewModel.mediaCurrentDuration.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiPlayActivity.lambda$initObserve$7(activityAiPlayBinding, aiExPrePlayerViewModel, (Integer) obj);
            }
        });
        LiveData<Boolean> liveData3 = aiExPrePlayerViewModel.seekBarEnable;
        final AppCompatSeekBar appCompatSeekBar = activityAiPlayBinding.seekBar;
        Objects.requireNonNull(appCompatSeekBar);
        liveData3.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                appCompatSeekBar.setEnabled(((Boolean) obj).booleanValue());
            }
        });
        aiExPrePlayerViewModel.aiImplState.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiPlayActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m418x8439d833(aiExPrePlayerViewModel, (AiImplState) obj);
            }
        });
    }

    static /* synthetic */ void lambda$initObserve$0(ActivityAiPlayBinding activityAiPlayBinding, Integer num) {
        activityAiPlayBinding.lottieCreate.setVisibility(num.intValue());
        activityAiPlayBinding.aiStateView.setVisibility(num.intValue());
    }

    static /* synthetic */ void lambda$initObserve$1(ActivityAiPlayBinding activityAiPlayBinding, Integer num) {
        activityAiPlayBinding.lottiePlay.setVisibility(num.intValue());
        activityAiPlayBinding.tvTitle.setVisibility(num.intValue());
    }

    static /* synthetic */ void lambda$initObserve$2(ActivityAiPlayBinding activityAiPlayBinding, IAiCreateState iAiCreateState) {
        if (iAiCreateState instanceof AiCreateProgress) {
            AiCreateProgress aiCreateProgress = (AiCreateProgress) iAiCreateState;
            if (!activityAiPlayBinding.lottieCreate.isAnimating()) {
                activityAiPlayBinding.lottieCreate.playAnimation();
            }
            activityAiPlayBinding.aiStateView.setHintMsg(aiCreateProgress.getMsg());
            return;
        }
        if (iAiCreateState instanceof AiCreateSuccess) {
            AiCreateSuccess aiCreateSuccess = (AiCreateSuccess) iAiCreateState;
            activityAiPlayBinding.lottieCreate.pauseAnimation();
            activityAiPlayBinding.tvTitle.setText(aiCreateSuccess.getData().getTitle());
            activityAiPlayBinding.lottiePlay.setAnimation(aiCreateSuccess.getData().getAnimationRawId());
            return;
        }
        if (iAiCreateState instanceof AiCreateFail) {
            AiCreateFail aiCreateFail = (AiCreateFail) iAiCreateState;
            Log.e(TAG, "initObserve: code = " + aiCreateFail.getCode() + ", msg = " + aiCreateFail.getMsg());
            activityAiPlayBinding.lottieCreate.pauseAnimation();
            if (aiCreateFail.getCode() != 102) {
                ToastUtil.showMsg(aiCreateFail.getMsg());
            }
        }
    }

    static /* synthetic */ void lambda$initObserve$3(ActivityAiPlayBinding activityAiPlayBinding, Boolean bool) {
        if (bool.booleanValue()) {
            activityAiPlayBinding.lottiePlay.resumeAnimation();
        } else {
            activityAiPlayBinding.lottiePlay.pauseAnimation();
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$4$com-wanos-careditproject-view-AiPlayActivity, reason: not valid java name */
    /* synthetic */ void m416x9e5ba1b7(List list) {
        this.adapter.submit(list);
    }

    /* JADX INFO: renamed from: lambda$initObserve$5$com-wanos-careditproject-view-AiPlayActivity, reason: not valid java name */
    /* synthetic */ void m417x57d32f56(AiPlayEntity aiPlayEntity) {
        this.adapter.setItemState(aiPlayEntity);
    }

    static /* synthetic */ void lambda$initObserve$6(ActivityAiPlayBinding activityAiPlayBinding, AiExPrePlayerViewModel aiExPrePlayerViewModel, Integer num) {
        activityAiPlayBinding.tvEndTime.setText(aiExPrePlayerViewModel.formatMillisToMinutesSeconds(num.intValue()));
        if (num.intValue() != -1) {
            activityAiPlayBinding.seekBar.setMax(num.intValue());
        }
    }

    static /* synthetic */ void lambda$initObserve$7(ActivityAiPlayBinding activityAiPlayBinding, AiExPrePlayerViewModel aiExPrePlayerViewModel, Integer num) {
        activityAiPlayBinding.seekBar.setProgress(num.intValue());
        activityAiPlayBinding.tvStartTime.setText(aiExPrePlayerViewModel.formatMillisToMinutesSeconds(num.intValue()));
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.view.AiPlayActivity$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$careditproject$data$bean$AiImplState;

        static {
            int[] iArr = new int[AiImplState.values().length];
            $SwitchMap$com$wanos$careditproject$data$bean$AiImplState = iArr;
            try {
                iArr[AiImplState.IMPL_ING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiImplState[AiImplState.IMPL_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiImplState[AiImplState.IMPL_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$bean$AiImplState[AiImplState.DEFAULT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$8$com-wanos-careditproject-view-AiPlayActivity, reason: not valid java name */
    /* synthetic */ void m418x8439d833(final AiExPrePlayerViewModel aiExPrePlayerViewModel, AiImplState aiImplState) {
        AiImplLoadingDialog aiImplLoadingDialog;
        int i = AnonymousClass9.$SwitchMap$com$wanos$careditproject$data$bean$AiImplState[aiImplState.ordinal()];
        if (i == 1) {
            AiImplLoadingDialog aiImplLoadingDialog2 = this.aiImplLoadingDialog;
            if (aiImplLoadingDialog2 != null && aiImplLoadingDialog2.isShowing()) {
                this.aiImplLoadingDialog.dismiss();
            }
            AiImplLoadingDialog aiImplLoadingDialog3 = new AiImplLoadingDialog(this, new AnitClick() { // from class: com.wanos.careditproject.view.AiPlayActivity.2
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    aiExPrePlayerViewModel.cancelImpl();
                }
            });
            this.aiImplLoadingDialog = aiImplLoadingDialog3;
            aiImplLoadingDialog3.show();
            return;
        }
        if (i != 2) {
            if ((i == 3 || i == 4) && (aiImplLoadingDialog = this.aiImplLoadingDialog) != null && aiImplLoadingDialog.isShowing()) {
                this.aiImplLoadingDialog.dismiss();
                return;
            }
            return;
        }
        AiImplLoadingDialog aiImplLoadingDialog4 = this.aiImplLoadingDialog;
        if (aiImplLoadingDialog4 != null && aiImplLoadingDialog4.isShowing()) {
            this.aiImplLoadingDialog.dismiss();
        }
        EditingActivity.openEditActivity(this, EditingUtils.EditProjectType.MUSIC, aiExPrePlayerViewModel.getImplProjectId() + "", "", true);
        ActivityUtils.finishActivity((Class<? extends Activity>) AiEditActivity.class);
        ActivityUtils.finishActivity((Class<? extends Activity>) AiPlayActivity.class);
    }

    private void initListener(ActivityAiPlayBinding activityAiPlayBinding, final AiExPrePlayerViewModel aiExPrePlayerViewModel) {
        activityAiPlayBinding.btnBack.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiPlayActivity.3
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                AiPlayActivity.this.onBackPressed();
            }
        });
        activityAiPlayBinding.btnRecreate.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiPlayActivity.4
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                aiExPrePlayerViewModel.recreateAiMusic();
            }
        });
        activityAiPlayBinding.btnSendCommunity.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiPlayActivity.5
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (UserInfoUtil.isLogin()) {
                    if (aiExPrePlayerViewModel.implCommunity()) {
                        if (aiExPrePlayerViewModel.aiMusicLoadState.getValue() instanceof AiCreateSuccess) {
                            AiCreateSuccess aiCreateSuccess = (AiCreateSuccess) aiExPrePlayerViewModel.aiMusicLoadState.getValue();
                            String title = aiCreateSuccess.getData().getTitle();
                            aiCreateSuccess.getData().getStyle();
                            CreatorProjectPublishActivity.openByAi(AiPlayActivity.this, aiExPrePlayerViewModel.getNowTrackId(), aiExPrePlayerViewModel.getNowTrackIndex(), title);
                            return;
                        }
                        Log.e(AiPlayActivity.TAG, "onAnitClick: aiMusicLoadState = " + aiExPrePlayerViewModel.aiMusicLoadState.getValue());
                        return;
                    }
                    return;
                }
                AiPlayActivity.this.showLoginDialog();
            }
        });
        activityAiPlayBinding.btnImplStudio.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiPlayActivity.6
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (UserInfoUtil.isLogin()) {
                    aiExPrePlayerViewModel.implStudio();
                } else {
                    AiPlayActivity.this.showLoginDialog();
                }
            }
        });
        activityAiPlayBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wanos.careditproject.view.AiPlayActivity.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    aiExPrePlayerViewModel.setProgress(i);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                aiExPrePlayerViewModel.onStartTrackingTouch();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                aiExPrePlayerViewModel.onStopTrackingTouch(seekBar.getProgress());
            }
        });
        this.adapter.setOnItemClickListener(new AiPlayerAdapter.OnItemClickListener() { // from class: com.wanos.careditproject.view.AiPlayActivity.8
            @Override // com.wanos.careditproject.adapter.AiPlayerAdapter.OnItemClickListener
            public void onItemClick(int i, AiPlayEntity aiPlayEntity) {
                aiExPrePlayerViewModel.onItemClick(aiPlayEntity);
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.exPlayerViewModel.onBackPressed()) {
            AiExitDialog.show(getSupportFragmentManager());
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AiImplLoadingDialog aiImplLoadingDialog = this.aiImplLoadingDialog;
        if (aiImplLoadingDialog == null || !aiImplLoadingDialog.isShowing()) {
            return;
        }
        this.aiImplLoadingDialog.dismiss();
    }

    @Override // com.wanos.careditproject.view.AiExitDialog.IExitDialogListener
    public void onActive() {
        this.exPlayerViewModel.cancelAiCreate();
        onBackPressed();
    }
}
