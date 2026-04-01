package com.wanos.careditproject.ui.viewholder;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.BaseResponse2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.CreatorPageRouter;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CollectionRepo;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.data.response.WorkDetail;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardItemBinding;
import com.wanos.careditproject.service.AudioPlayerManager.AudioPlayerManager;
import com.wanos.careditproject.service.AudioPlayerManager.PlayProgress;
import com.wanos.careditproject.service.AudioPlayerManager.PlayState;
import com.wanos.careditproject.service.AudioPlayerManager.PlayStateViewModel;
import com.wanos.careditproject.ui.fragment.CreatorHomeFragment;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;

/* JADX INFO: loaded from: classes3.dex */
public class HomeCardItemViewHolder extends BaseViewHolder<ItemCreatorHomeCardItemBinding, ProjectInfo> {
    private CollectionRepo collectionRepo;
    private LifecycleEventObserver observer;
    private PlayStateViewModel playStateViewModel;

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
    }

    public HomeCardItemViewHolder(ItemCreatorHomeCardItemBinding itemCreatorHomeCardItemBinding) {
        super(itemCreatorHomeCardItemBinding);
        this.collectionRepo = new CollectionRepo();
        this.observer = new LifecycleEventObserver() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder.3
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_PAUSE || event == Lifecycle.Event.ON_STOP) {
                    HomeCardItemViewHolder.this.stopPlayer();
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    HomeCardItemViewHolder.this.stopPlayer();
                    ((AppCompatActivity) HomeCardItemViewHolder.this.itemView.getContext()).getLifecycle().removeObserver(this);
                }
            }
        };
    }

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) this.itemView.getContext();
        this.playStateViewModel = (PlayStateViewModel) new ViewModelProvider(appCompatActivity).get(PlayStateViewModel.class);
        AudioPlayerManager.getInstance().setViewModel(this.playStateViewModel);
        ((ItemCreatorHomeCardItemBinding) this.binding).btnAudioBookPlay.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m397xc7b34d8(view);
            }
        });
        this.playStateViewModel.getPlayStateLiveData().observe(appCompatActivity, new Observer() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m398xab919((PlayState) obj);
            }
        });
        this.playStateViewModel.getPlayProgressLiveData().observe(appCompatActivity, new Observer() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m399xf39a3d5a((PlayProgress) obj);
            }
        });
        m398xab919(this.playStateViewModel.getPlayStateLiveData().getValue());
        ((ItemCreatorHomeCardItemBinding) this.binding).getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m400xe729c19b(view);
            }
        });
        ((ItemCreatorHomeCardItemBinding) this.binding).tvEdit.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder.1
            private boolean isHandleClick = false;

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (this.isHandleClick) {
                    return;
                }
                if (!UserInfoUtil.isLogin() && (HomeCardItemViewHolder.this.itemView.getContext() instanceof WanosBaseActivity)) {
                    ((WanosBaseActivity) HomeCardItemViewHolder.this.itemView.getContext()).showLoginDialog();
                    return;
                }
                this.isHandleClick = true;
                HomeCardItemViewHolder.this.stopOtherAudio();
                HomeCardItemViewHolder.this.stopPlayer();
                CreatorRetrofitUtil.workCopy(((ProjectInfo) HomeCardItemViewHolder.this.data).getId(), new ResponseCallBack<EditProjectCopyResponse>(HomeCardItemViewHolder.this.itemView.getContext()) { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder.1.1
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                        AnonymousClass1.this.isHandleClick = false;
                        if (editProjectCopyResponse.data != null) {
                            EditingActivity.openEditActivity(HomeCardItemViewHolder.this.itemView.getContext(), editProjectCopyResponse.data.getProjectType(), editProjectCopyResponse.data.getId(), new Gson().toJson(editProjectCopyResponse.data));
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i, String str) {
                        AnonymousClass1.this.isHandleClick = false;
                        ToastUtil.showMsg(R.string.to_edit_fail);
                    }
                });
            }
        });
        ((ItemCreatorHomeCardItemBinding) this.binding).layoutCollect.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m402xce48ca1d(view);
            }
        });
        ((AppCompatActivity) this.itemView.getContext()).getLifecycle().addObserver(this.observer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$0$com-wanos-careditproject-ui-viewholder-HomeCardItemViewHolder, reason: not valid java name */
    /* synthetic */ void m397xc7b34d8(View view) {
        if (AudioPlayerManager.getInstance().isPlaying() && ((ProjectInfo) this.data).getId().equals(AudioPlayerManager.getInstance().getCurrentPlayId())) {
            AudioPlayerManager.getInstance().pause();
        } else {
            playWithId(((ProjectInfo) this.data).getId());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$2$com-wanos-careditproject-ui-viewholder-HomeCardItemViewHolder, reason: not valid java name */
    /* synthetic */ void m399xf39a3d5a(PlayProgress playProgress) {
        if (this.data == 0 || !((ProjectInfo) this.data).getId().equals(AudioPlayerManager.getInstance().getCurrentPlayId())) {
            return;
        }
        ((ItemCreatorHomeCardItemBinding) this.binding).tvPlayTime.setText(playProgress.getCurrentTime());
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration0.setText(playProgress.getTotalTime());
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration1.setText(playProgress.getTotalTime());
        ((ItemCreatorHomeCardItemBinding) this.binding).seekbarProgress.setVisibility(0);
        ((ItemCreatorHomeCardItemBinding) this.binding).seekbarProgress.setMax(playProgress.getDuration());
        ((ItemCreatorHomeCardItemBinding) this.binding).seekbarProgress.setProgress(playProgress.getProgress());
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration0.setVisibility(4);
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration1.setVisibility(0);
        ((ItemCreatorHomeCardItemBinding) this.binding).tvPlayTime.setVisibility(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$3$com-wanos-careditproject-ui-viewholder-HomeCardItemViewHolder, reason: not valid java name */
    /* synthetic */ void m400xe729c19b(View view) {
        CreatorPageRouter.toCreatePlayPage((ProjectInfo) this.data, true, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$5$com-wanos-careditproject-ui-viewholder-HomeCardItemViewHolder, reason: not valid java name */
    /* synthetic */ void m402xce48ca1d(View view) {
        if (!UserInfoUtil.isLogin() && (this.itemView.getContext() instanceof WanosBaseActivity)) {
            ((WanosBaseActivity) this.itemView.getContext()).showLoginDialog();
        } else {
            this.collectionRepo.toggleWorkCollectRequest((ProjectInfo) this.data).observe((LifecycleOwner) this.itemView.getContext(), new Observer() { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.m401xdab945dc((Boolean) obj);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$4$com-wanos-careditproject-ui-viewholder-HomeCardItemViewHolder, reason: not valid java name */
    /* synthetic */ void m401xdab945dc(Boolean bool) {
        ((ProjectInfo) this.data).setCollect(bool.booleanValue());
        ((ItemCreatorHomeCardItemBinding) this.binding).setData((ProjectInfo) this.data);
        ((ItemCreatorHomeCardItemBinding) this.binding).executePendingBindings();
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$careditproject$service$AudioPlayerManager$PlayState$State;

        static {
            int[] iArr = new int[PlayState.State.values().length];
            $SwitchMap$com$wanos$careditproject$service$AudioPlayerManager$PlayState$State = iArr;
            try {
                iArr[PlayState.State.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$service$AudioPlayerManager$PlayState$State[PlayState.State.PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: updatePlayerUI, reason: merged with bridge method [inline-methods] */
    public void m398xab919(PlayState playState) {
        if (playState != null && this.data != 0 && playState.getPlayId() != null && playState.getPlayId().equals(((ProjectInfo) this.data).getId())) {
            int i = AnonymousClass4.$SwitchMap$com$wanos$careditproject$service$AudioPlayerManager$PlayState$State[playState.getState().ordinal()];
            if (i == 1) {
                setPlayStateImage(true, true);
                return;
            } else if (i == 2) {
                setPlayStateImage(false, true);
                return;
            } else {
                setPlayStateImage(false, false);
                return;
            }
        }
        if (this.data != 0) {
            setPlayStateImage(false, false);
        }
    }

    private void playWithId(String str) {
        CreatorRetrofitUtil.getWorkDetail(str, new ResponseCallBack<BaseResponse2<WorkDetail>>(this.itemView.getContext()) { // from class: com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str2) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse2<WorkDetail> baseResponse2) {
                AudioPlayerManager.getInstance().play(HomeCardItemViewHolder.this.itemView.getContext(), baseResponse2.data.getInfo());
            }
        });
    }

    private void setPlayStateImage(boolean z, boolean z2) {
        if (z2) {
            ((ItemCreatorHomeCardItemBinding) this.binding).tvEdit.setSelected(true);
            if (!z) {
                GlideUtil.setImageGifData(com.wanos.media.R.drawable.ic_playing_2, ((ItemCreatorHomeCardItemBinding) this.binding).playState);
                ((ItemCreatorHomeCardItemBinding) this.binding).playState.setVisibility(0);
                ((ItemCreatorHomeCardItemBinding) this.binding).pbAudiobookPlay.setVisibility(8);
                return;
            } else {
                ((ItemCreatorHomeCardItemBinding) this.binding).playState.setVisibility(8);
                ((ItemCreatorHomeCardItemBinding) this.binding).pbAudiobookPlay.setVisibility(0);
                return;
            }
        }
        ((ItemCreatorHomeCardItemBinding) this.binding).tvEdit.setSelected(false);
        ((ItemCreatorHomeCardItemBinding) this.binding).playState.setImageResource(com.wanos.media.R.drawable.group_card_pause);
        ((ItemCreatorHomeCardItemBinding) this.binding).playState.setVisibility(0);
        ((ItemCreatorHomeCardItemBinding) this.binding).pbAudiobookPlay.setVisibility(8);
        ((ItemCreatorHomeCardItemBinding) this.binding).seekbarProgress.setVisibility(4);
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration0.setVisibility(0);
        ((ItemCreatorHomeCardItemBinding) this.binding).tvDuration1.setVisibility(4);
        ((ItemCreatorHomeCardItemBinding) this.binding).tvPlayTime.setVisibility(4);
    }

    protected void stopOtherAudio() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || !MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            return;
        }
        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void stopPlayer() {
        if (CreatorHomeFragment.isUiModeChanged()) {
            return;
        }
        setPlayStateImage(false, false);
        if (AudioPlayerManager.getInstance().getCurrentPlayId() == null || this.data == 0 || !AudioPlayerManager.getInstance().getCurrentPlayId().equals(((ProjectInfo) this.data).getId())) {
            return;
        }
        AudioPlayerManager.getInstance().stop();
    }
}
