package com.wanos.media.ui.info;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.BaseZeroFragment;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.BallCollectEntity;
import com.wanos.media.entity.CreateOrCollectUserThemeReply;
import com.wanos.media.entity.ThemeEvent;
import com.wanos.media.entity.ThemeSoundInfoEntity;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.presenter.ZeroPrePresenter;
import com.wanos.media.ui.info.ZeroResetSecure;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.CollectionDialog;
import com.wanos.media.view.HelpDialog;
import com.wanos.media.view.SoundMaterialDialog;
import com.wanos.media.view.VolumeDialog;
import com.wanos.media.view.ZeroPreView;
import com.wanos.media.viewmodel.RelaxPlayerInfoEditViewModel;
import com.wanos.media.viewmodel.ViewModeBundleFactory;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.widget.ball.BallSurfaceView;
import com.wanos.media.widget.sound.BallMoveWay;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.FragmentZeroProBinding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroProFragment extends BaseZeroFragment<FragmentZeroProBinding, ZeroPrePresenter> implements ZeroPreView, BallSurfaceView.IBallViewCallback, ZeroAudioBallTools.IAudioBallListener {
    private static final String TAG = "ZeroProFragment";
    private RelaxPlayerInfoEditViewModel viewModel;
    private final List<AudioInfoEntity> mHideInsertBallCache = new ArrayList();
    private final CollectionDialog.OnOptionClickListener mCollectionSaveCallback = new CollectionDialog.OnOptionClickListener() { // from class: com.wanos.media.ui.info.ZeroProFragment$$ExternalSyntheticLambda3
        @Override // com.wanos.media.view.CollectionDialog.OnOptionClickListener
        public final void onSaveClick(String str, String str2, String str3) {
            this.f$0.m501lambda$new$0$comwanosmediauiinfoZeroProFragment(str, str2, str3);
        }
    };

    @Override // com.wanos.media.base.BaseZeroFragment
    public int findSceneIndex(long j) {
        return -1;
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initData(Bundle bundle) {
    }

    public static ZeroProFragment newInstance(ZeroPageMode zeroPageMode, long j, AudioSceneInfoEntity audioSceneInfoEntity, String str, String str2, boolean z) {
        ZeroProFragment zeroProFragment = new ZeroProFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(RelaxPlayerInfoEditViewModel.KEY_THEME_ID, j);
        bundle.putString(RelaxPlayerInfoEditViewModel.KEY_BACKGROUND_IMAGE, str);
        bundle.putString(RelaxPlayerInfoEditViewModel.KEY_THEME_NAME, str2);
        bundle.putBoolean(RelaxPlayerInfoEditViewModel.KEY_IS_ME, z);
        bundle.putSerializable(RelaxPlayerInfoEditViewModel.KEY_THEME_INFO, audioSceneInfoEntity);
        bundle.putSerializable(RelaxPlayerInfoEditViewModel.KEY_OPEN_MODE, zeroPageMode);
        zeroProFragment.setArguments(bundle);
        return zeroProFragment;
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-ui-info-ZeroProFragment, reason: not valid java name */
    /* synthetic */ void m501lambda$new$0$comwanosmediauiinfoZeroProFragment(String str, String str2, String str3) {
        if (this.mPresenter == 0) {
            return;
        }
        ((ZeroPrePresenter) this.mPresenter).setSaveAsUserTheme(this.viewModel.getThemeInfo().getBindId(), str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.BaseZeroFragment
    public FragmentZeroProBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.viewModel = (RelaxPlayerInfoEditViewModel) new ViewModelProvider(this, new ViewModeBundleFactory(getArguments())).get(RelaxPlayerInfoEditViewModel.class);
        return FragmentZeroProBinding.inflate(layoutInflater, viewGroup, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.BaseZeroFragment
    public ZeroPrePresenter initPresenter(Bundle bundle) {
        return new ZeroPrePresenter(this);
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initSetting(Bundle bundle) {
        if (this.mPresenter == 0 || this.mViewBinding == 0) {
            return;
        }
        ((ZeroPrePresenter) this.mPresenter).formatBackgroundImage(getContext(), this.viewModel.getThemeBgImage());
        Fragment fragmentFindFragmentByTag = getChildFragmentManager().findFragmentByTag(CollectionDialog.TAG);
        if (fragmentFindFragmentByTag instanceof CollectionDialog) {
            ((CollectionDialog) fragmentFindFragmentByTag).setOnOptionClickListener(this.mCollectionSaveCallback);
        }
        if (getRelaxContentState() == RelaxInfoActionButton.State.TIMING) {
            this.viewModel.setBottomOptionVisibilityState(4);
        }
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initListener(Bundle bundle) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setBallCallback(this);
        ((FragmentZeroProBinding) this.mViewBinding).btnAdd.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.info.ZeroProFragment.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (ZeroProFragment.this.mViewBinding != null) {
                    if (ZeroAudioBallTools.getInstance().getSceneSoundIds().size() != ((FragmentZeroProBinding) ZeroProFragment.this.mViewBinding).ballView.getAudioVolumeInfo().size()) {
                        ZeroLogcatTools.d(ZeroProFragment.TAG, "音源未全部加载就绪，加载中");
                        ToastUtil.showMsg(R.string.toast_wait);
                        return;
                    } else if (ZeroResetSecure.getInstance().getResetState() != -1 || ZeroProFragment.this.viewModel.getThemeInfo() == null) {
                        ZeroLogcatTools.d(ZeroProFragment.TAG, "音源未全部加载就绪，重置中");
                        ToastUtil.showMsg(R.string.toast_wait);
                        return;
                    } else {
                        ZeroLogcatTools.i(ZeroProFragment.TAG, "onAnitClick: 弹出素材库对话框");
                        SoundMaterialDialog.showDialog(ZeroProFragment.this.getChildFragmentManager(), ZeroProFragment.this.viewModel.getThemeBgImage(), ZeroProFragment.this.viewModel.getOpenMode());
                        return;
                    }
                }
                ZeroLogcatTools.e(ZeroProFragment.TAG, "mViewBinding is null");
            }
        });
        ((FragmentZeroProBinding) this.mViewBinding).btnVolume.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.info.ZeroProFragment.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (ZeroProFragment.this.mViewBinding == null) {
                    return;
                }
                if (ZeroAudioBallTools.getInstance().getSceneSoundIds().size() != ((FragmentZeroProBinding) ZeroProFragment.this.mViewBinding).ballView.getAudioVolumeInfo().size()) {
                    ZeroLogcatTools.d(ZeroProFragment.TAG, "音源未全部加载就绪，加载中");
                    ToastUtil.showMsg(R.string.toast_wait);
                } else if (ZeroResetSecure.getInstance().getResetState() == -1 && ZeroProFragment.this.viewModel.getThemeInfo() != null) {
                    VolumeDialog.showVolumeDialog(ZeroProFragment.this.getChildFragmentManager(), ZeroProFragment.this.viewModel.getThemeBgImage(), ((FragmentZeroProBinding) ZeroProFragment.this.mViewBinding).ballView.getAudioVolumeInfo());
                } else {
                    ZeroLogcatTools.d(ZeroProFragment.TAG, "音源未全部加载就绪，重置中");
                    ToastUtil.showMsg(R.string.toast_wait);
                }
            }
        });
        ((FragmentZeroProBinding) this.mViewBinding).btnReset.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.info.ZeroProFragment.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (ZeroProFragment.this.mViewBinding == null || ZeroProFragment.this.viewModel.getThemeInfo() == null) {
                    return;
                }
                ZeroResetSecure zeroResetSecure = ZeroResetSecure.getInstance();
                ZeroProFragment zeroProFragment = ZeroProFragment.this;
                zeroResetSecure.onLaunch(zeroProFragment, ((FragmentZeroProBinding) zeroProFragment.mViewBinding).ballView.getBallSize(), new AnonymousClass1());
            }

            /* JADX INFO: renamed from: com.wanos.media.ui.info.ZeroProFragment$3$1, reason: invalid class name */
            class AnonymousClass1 implements ZeroResetSecure.IResetAction {
                AnonymousClass1() {
                }

                @Override // com.wanos.media.ui.info.ZeroResetSecure.IResetAction
                public void onDeleteBall() {
                    Iterator<Long> it = ZeroAudioBallTools.getInstance().getSceneSoundIds().iterator();
                    while (it.hasNext()) {
                        ZeroAudioBallTools.getInstance().deleteBall(it.next().longValue());
                    }
                }

                @Override // com.wanos.media.ui.info.ZeroResetSecure.IResetAction
                public void onInsertBall() {
                    ZeroAudioBallTools.getInstance().initScene(ZeroProFragment.this.viewModel.getThemeInfo().getDetailInfo(), new ZeroAudioBallTools.ILoadingCallback() { // from class: com.wanos.media.ui.info.ZeroProFragment$3$1$$ExternalSyntheticLambda0
                        @Override // com.wanos.media.util.ZeroAudioBallTools.ILoadingCallback
                        public final void onAudioReady() {
                            this.f$0.m503x3d139f4d();
                        }
                    });
                }

                /* JADX INFO: renamed from: lambda$onInsertBall$0$com-wanos-media-ui-info-ZeroProFragment$3$1, reason: not valid java name */
                /* synthetic */ void m503x3d139f4d() {
                    if (ZeroProFragment.this.viewModel.isMe()) {
                        ZeroProFragment.this.viewModel.setCollectBtnVisibilityState(8);
                    }
                    if (ZeroProFragment.this.getActivity() instanceof ZeroInfoActivity) {
                        ((ZeroInfoActivity) ZeroProFragment.this.getActivity()).setRelaxState(true);
                    }
                }
            }
        });
        ((FragmentZeroProBinding) this.mViewBinding).btnCollect.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.info.ZeroProFragment.4
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (ZeroProFragment.this.mViewBinding == null) {
                    ZeroLogcatTools.i(ZeroProFragment.TAG, "onAnitClick: mViewBinding == NULL");
                    return;
                }
                if (ZeroResetSecure.getInstance().getResetState() != -1 || ZeroProFragment.this.viewModel.getThemeInfo() == null) {
                    ToastUtil.showMsg(R.string.toast_wait);
                } else if (UserInfoUtil.isLogin()) {
                    CollectionDialog.show(ZeroProFragment.this.getChildFragmentManager(), ZeroProFragment.this.viewModel.getThemeName(), ZeroProFragment.this.getAudioConfig(), ZeroProFragment.this.viewModel.getThemeBgImage()).setOnOptionClickListener(ZeroProFragment.this.mCollectionSaveCallback);
                } else {
                    ZeroLogcatTools.i(ZeroProFragment.TAG, "onAnitClick: 用户未登录");
                    LoginUtils.getInstance().showLoginDialog();
                }
            }
        });
        ((FragmentZeroProBinding) this.mViewBinding).btnHelp.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.info.ZeroProFragment.5
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                HelpDialog.show(ZeroProFragment.this);
            }
        });
        this.viewModel.collectBtnVisibilityState.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroProFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m499lambda$initListener$1$comwanosmediauiinfoZeroProFragment((Integer) obj);
            }
        });
        this.viewModel.bottomOptionVisibilityState.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroProFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m500lambda$initListener$2$comwanosmediauiinfoZeroProFragment((Integer) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$1$com-wanos-media-ui-info-ZeroProFragment, reason: not valid java name */
    /* synthetic */ void m499lambda$initListener$1$comwanosmediauiinfoZeroProFragment(Integer num) {
        ((FragmentZeroProBinding) this.mViewBinding).btnCollect.setVisibility(num.intValue());
    }

    /* JADX INFO: renamed from: lambda$initListener$2$com-wanos-media-ui-info-ZeroProFragment, reason: not valid java name */
    /* synthetic */ void m500lambda$initListener$2$comwanosmediauiinfoZeroProFragment(Integer num) {
        ((FragmentZeroProBinding) this.mViewBinding).llActionRoom.setVisibility(num.intValue());
    }

    public void resetViewModelDta() {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.resetViewModelData();
    }

    @Override // com.wanos.media.view.ZeroPreView
    public void onViewBackground(String str) {
        if (this.mViewBinding == 0) {
            return;
        }
        try {
            ((FragmentZeroProBinding) this.mViewBinding).ivBackgroundImg.setImageBitmap(BitmapFactory.decodeFile(str));
        } catch (OutOfMemoryError unused) {
            ZeroLogcatTools.e(TAG, "个性化模式背景加载失败，OutOfMemoryError");
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setBackgroundImage(str);
        ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.ui.info.ZeroProFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m502xadff490f();
            }
        }, 400L);
    }

    /* JADX INFO: renamed from: lambda$onViewBackground$3$com-wanos-media-ui-info-ZeroProFragment, reason: not valid java name */
    /* synthetic */ void m502xadff490f() {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ivBackgroundImg.setImageDrawable(null);
        ((FragmentZeroProBinding) this.mViewBinding).ivBackgroundImg.setVisibility(8);
    }

    @Override // com.wanos.media.view.ZeroPreView
    public void onCollectSuccess(CreateOrCollectUserThemeReply createOrCollectUserThemeReply) {
        if (this.mViewBinding == 0 || createOrCollectUserThemeReply == null) {
            return;
        }
        ToastUtil.showMsg(getString(R.string.collected_my_space));
        EventBus.getDefault().post(new ThemeEvent(103));
        this.viewModel.getThemeInfo().setBeCollectBindId(createOrCollectUserThemeReply.bindId);
    }

    @Override // com.wanos.media.widget.ball.BallSurfaceView.IBallViewCallback
    public void onSurfaceCreate() {
        ZeroAudioBallTools.getInstance().onPersonalizationCreate(this);
        FragmentActivity activity = getActivity();
        if (activity instanceof ZeroInfoActivity) {
            ZeroInfoActivity zeroInfoActivity = (ZeroInfoActivity) activity;
            setControlState(zeroInfoActivity.isTaste(), zeroInfoActivity.isImmersion());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        V v = this.mViewBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        V v = this.mViewBinding;
    }

    @Override // com.wanos.media.widget.ball.BallSurfaceView.IBallViewCallback
    public void onBallAudioPositionXY(BallMoveWay ballMoveWay, int i, float f, float f2) {
        if (BallMoveWay.WAY_INIT == ballMoveWay || BallMoveWay.WAY_MEDIA_MOVE == ballMoveWay) {
            return;
        }
        ZeroAudioBallTools.getInstance().modifyAudioXY(i, f, f2);
    }

    @Override // com.wanos.media.widget.ball.BallSurfaceView.IBallViewCallback
    public void onBallAudioPositionZ(BallMoveWay ballMoveWay, int i, float f) {
        if (BallMoveWay.WAY_INIT == ballMoveWay || BallMoveWay.WAY_MEDIA_MOVE == ballMoveWay) {
            return;
        }
        ZeroAudioBallTools.getInstance().modifyAudioZ(i, f);
    }

    @Override // com.wanos.media.widget.ball.BallSurfaceView.IBallViewCallback
    public void onBallAudioVolume(BallMoveWay ballMoveWay, int i, float f) {
        if (BallMoveWay.WAY_INIT == ballMoveWay || BallMoveWay.WAY_MEDIA_MOVE == ballMoveWay) {
            return;
        }
        ZeroAudioBallTools.getInstance().modifyVolume(i, f);
    }

    @Override // com.wanos.media.widget.ball.BallSurfaceView.IBallViewCallback
    public void onBallEdit() {
        if (this.mViewBinding == 0 || ((FragmentZeroProBinding) this.mViewBinding).btnCollect.getVisibility() == 0) {
            return;
        }
        this.viewModel.setCollectBtnVisibilityState(0);
    }

    @Override // com.wanos.media.util.ZeroAudioBallTools.IAudioBallListener
    public void onBallInsert(AudioInfoEntity audioInfoEntity, int i) {
        Log.d(TAG, "onBallInsert: " + i);
        if (getContext() == null || this.mViewBinding == 0) {
            return;
        }
        if (isHidden()) {
            this.mHideInsertBallCache.add(audioInfoEntity);
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.insertBall(audioInfoEntity);
        if (i == 100 || ZeroResetSecure.getInstance().getResetState() != -1 || ((FragmentZeroProBinding) this.mViewBinding).btnCollect.getVisibility() == 0) {
            return;
        }
        this.viewModel.setCollectBtnVisibilityState(0);
    }

    @Override // com.wanos.media.util.ZeroAudioBallTools.IAudioBallListener
    public void onBallDelete(AudioInfoEntity audioInfoEntity) {
        ZeroLogcatTools.d(TAG, "onBallDelete: 收到播放器移除音源事件,移除音源ID = " + audioInfoEntity.getId());
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.deleteBall(audioInfoEntity.getPlayId());
        if (((FragmentZeroProBinding) this.mViewBinding).btnCollect.getVisibility() == 0 || ZeroResetSecure.getInstance().getResetState() != -1) {
            return;
        }
        this.viewModel.setCollectBtnVisibilityState(0);
    }

    @Override // com.wanos.media.util.ZeroAudioBallTools.IAudioBallListener
    public void onBallMove(int i, float f, float f2, float f3) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setMediaTrack(i, f, f2, f3);
    }

    @Override // com.wanos.media.util.ZeroAudioBallTools.IAudioBallListener
    public void onBallAutoMove(int i, ThemeSoundInfoEntity themeSoundInfoEntity) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setBallAutoMove(i, themeSoundInfoEntity.getAngle(), themeSoundInfoEntity.getSpeed());
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (this.mViewBinding != 0) {
            if (z) {
                ((FragmentZeroProBinding) this.mViewBinding).ballView.setVisibility(4);
            } else {
                ((FragmentZeroProBinding) this.mViewBinding).ballView.setVisibility(0);
            }
        }
        if (z || this.mHideInsertBallCache.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.mHideInsertBallCache.size(); i++) {
            onBallInsert(this.mHideInsertBallCache.get(i), 100);
        }
        this.mHideInsertBallCache.clear();
    }

    @Override // com.wanos.media.base.BaseZeroFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ZeroAudioBallTools.getInstance().onPersonalizationDestroy();
        this.mHideInsertBallCache.clear();
        ZeroResetSecure.onDestroy();
    }

    public void initCacheBall() {
        if (this.mHideInsertBallCache.isEmpty()) {
            return;
        }
        ZeroLogcatTools.d(TAG, "initCacheBall: 清理个性化模式不可见情况下，缓存的音源数据");
        this.mHideInsertBallCache.clear();
    }

    public void setSceneInfo(AudioSceneInfoEntity audioSceneInfoEntity) {
        if (this.mViewBinding == 0) {
            return;
        }
        if (audioSceneInfoEntity == null) {
            ZeroLogcatTools.d(TAG, "setSceneInfo: AudioSceneInfoEntity == NULL");
        } else {
            this.viewModel.setThemeInfo(audioSceneInfoEntity);
        }
    }

    public void setBallVolume(VolumeModifyEntity volumeModifyEntity) {
        if (volumeModifyEntity == null || this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setBallVolume(volumeModifyEntity.getBallId(), volumeModifyEntity.getBallVolume());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAudioConfig() {
        if (this.mViewBinding == 0) {
            return "";
        }
        ArrayList<BallCollectEntity> ballCollectEntity = ((FragmentZeroProBinding) this.mViewBinding).ballView.getBallCollectEntity();
        ZeroLogcatTools.d(TAG, GsonUtils.toJson(ballCollectEntity));
        return GsonUtils.toJson(ballCollectEntity);
    }

    public boolean isControlBall() {
        if (this.mViewBinding == 0) {
            return false;
        }
        return ((FragmentZeroProBinding) this.mViewBinding).ballView.isControl();
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    public void setControlState(boolean z, boolean z2) {
        Log.d(TAG, "setControlState: isTaste = " + z + ", isImmersion = " + z2);
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroProBinding) this.mViewBinding).ballView.setScenePositionFlatState((z || z2) ? false : true);
        if (z2) {
            ((FragmentZeroProBinding) this.mViewBinding).ballView.exitEditState();
            this.viewModel.setBottomOptionVisibilityState(4);
        } else {
            if (getRelaxContentState() == RelaxInfoActionButton.State.TIMING) {
                this.viewModel.setBottomOptionVisibilityState(4);
                return;
            }
            this.viewModel.setBottomOptionVisibilityState(0);
            if (this.viewModel.collectBtnVisibilityState.getValue().intValue() == 0) {
                ((FragmentZeroProBinding) this.mViewBinding).btnCollect.setVisibility(0);
            }
        }
    }

    public RelaxInfoActionButton.State getRelaxContentState() {
        if (getActivity() instanceof ZeroInfoActivity) {
            return ((ZeroInfoActivity) getActivity()).getRelaxButtonState();
        }
        return RelaxInfoActionButton.State.NORMAL;
    }
}
