package com.wanos.careditproject.ui.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.CreatorPageRouter;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CollectionRepo;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.databinding.PopCreatorAuditMoreBinding;
import com.wanos.careditproject.event.DeleteWorkEvent;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.careditproject.view.widget.PrivacyChangePopupWindow;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorMineWorkItemViewModel {
    private CollectionRepo collectionRepo;
    private MutableLiveData<ProjectInfo> info;
    private boolean isCollectionClickHandled;
    private boolean isHandleClick;
    private MutableLiveData<Boolean> refreshEvent = new MutableLiveData<>(false);
    public CreatorWorkTypeViewModel workType;

    public LiveData<ProjectInfo> getInfo() {
        return this.info;
    }

    public LiveData<Boolean> getRefreshEvent() {
        return this.refreshEvent;
    }

    public CreatorMineWorkItemViewModel(ProjectInfo projectInfo, CreatorWorkTypeViewModel creatorWorkTypeViewModel) {
        MutableLiveData<ProjectInfo> mutableLiveData = new MutableLiveData<>();
        this.info = mutableLiveData;
        this.isCollectionClickHandled = false;
        this.isHandleClick = false;
        mutableLiveData.setValue(projectInfo);
        this.workType = creatorWorkTypeViewModel;
        creatorWorkTypeViewModel.setData(projectInfo);
        this.collectionRepo = new CollectionRepo();
    }

    public void onClickCollection(View view) {
        if (this.isCollectionClickHandled) {
            return;
        }
        this.isCollectionClickHandled = true;
        if (!UserInfoUtil.isLogin() && (view.getContext() instanceof WanosBaseActivity)) {
            ((WanosBaseActivity) view.getContext()).showLoginDialog();
            view.postDelayed(new Runnable() { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m407xeabf4930();
                }
            }, 500L);
        } else {
            this.collectionRepo.toggleWorkCollectRequest(this.info.getValue()).observeForever(new Observer() { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.m408x872d458f((Boolean) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onClickCollection$0$com-wanos-careditproject-ui-viewmodel-CreatorMineWorkItemViewModel, reason: not valid java name */
    /* synthetic */ void m407xeabf4930() {
        this.isCollectionClickHandled = false;
    }

    /* JADX INFO: renamed from: lambda$onClickCollection$1$com-wanos-careditproject-ui-viewmodel-CreatorMineWorkItemViewModel, reason: not valid java name */
    /* synthetic */ void m408x872d458f(Boolean bool) {
        int i;
        this.info.getValue().setCollect(bool.booleanValue());
        int collectCount = this.info.getValue().getCollectCount();
        if (bool.booleanValue()) {
            i = collectCount + 1;
        } else {
            i = collectCount - 1;
            if (this.workType.getType() == 2) {
                EventBus.getDefault().post(new DeleteWorkEvent(this.info.getValue().getId()));
            }
        }
        this.info.getValue().setCollectCount(i);
        MutableLiveData<ProjectInfo> mutableLiveData = this.info;
        mutableLiveData.setValue(mutableLiveData.getValue());
        this.refreshEvent.setValue(true);
        this.isCollectionClickHandled = false;
    }

    public void onClickCreateSame(final View view) {
        if (this.isHandleClick) {
            return;
        }
        if (!UserInfoUtil.isLogin() && (view.getContext() instanceof WanosBaseActivity)) {
            ((WanosBaseActivity) view.getContext()).showLoginDialog();
        } else {
            this.isHandleClick = true;
            CreatorRetrofitUtil.workCopy(this.info.getValue().getId(), new ResponseCallBack<EditProjectCopyResponse>(view.getContext()) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                    CreatorMineWorkItemViewModel.this.isHandleClick = false;
                    if (editProjectCopyResponse.data != null) {
                        EditingActivity.openEditActivity(view.getContext(), editProjectCopyResponse.data.getProjectType(), editProjectCopyResponse.data.getId(), new Gson().toJson(editProjectCopyResponse.data));
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    CreatorMineWorkItemViewModel.this.isHandleClick = false;
                    ToastUtil.showMsg(R.string.to_edit_fail);
                }
            });
        }
    }

    public void clickToPlay(View view) {
        if (this.info.getValue().getStatus() == 1) {
            CreatorPageRouter.toCreatePlayPage(this.info.getValue(), true, true);
        } else {
            ToastUtil.showMsg(this.info.getValue().getStatus() == 2 ? R.string.create_mine_publish_status_making_fail_play_tips : R.string.create_mine_publish_status_making_play_tips);
        }
    }

    public void clickMore(final View view) {
        final boolean z = this.info.getValue().getPublishRange() == 1;
        PrivacyChangePopupWindow privacyChangePopupWindow = new PrivacyChangePopupWindow(view.getContext(), !z, this.info.getValue().getStatus() == 1, (ViewGroup) view.getParent());
        privacyChangePopupWindow.setOnButtonClickListener(new PrivacyChangePopupWindow.OnButtonClickListener() { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.wanos.careditproject.view.widget.PrivacyChangePopupWindow.OnButtonClickListener
            public void onDeleteClick() {
                EventBus.getDefault().post(new DeleteWorkEvent(((ProjectInfo) CreatorMineWorkItemViewModel.this.info.getValue()).getId()));
                CreatorRetrofitUtil.worksDelete(((ProjectInfo) CreatorMineWorkItemViewModel.this.info.getValue()).getId(), new ResponseCallBack<BaseResponse>(view.getContext()) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel.2.1
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(BaseResponse baseResponse) {
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i, String str) {
                        ToastUtils.showShort(str);
                    }
                });
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.wanos.careditproject.view.widget.PrivacyChangePopupWindow.OnButtonClickListener
            public void onPrivacyClick() {
                CreatorRetrofitUtil.updateWorkPublishRangeStatus(Integer.parseInt(((ProjectInfo) CreatorMineWorkItemViewModel.this.info.getValue()).getId()), !z, new ResponseCallBack<BaseResponse>(view.getContext()) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel.2.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(BaseResponse baseResponse) {
                        ToastUtil.showMsg(!z ? R.string.create_mine_publish_change_status_me : R.string.create_mine_publish_change_status_all);
                        ((ProjectInfo) CreatorMineWorkItemViewModel.this.info.getValue()).setPublishRange(!z ? 1 : 0);
                        CreatorMineWorkItemViewModel.this.refreshEvent.setValue(true);
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i, String str) {
                        ToastUtils.showShort(str);
                    }
                });
            }
        });
        privacyChangePopupWindow.show(view);
    }

    public void clickAuditMore(View view) {
        PopCreatorAuditMoreBinding popCreatorAuditMoreBindingInflate = PopCreatorAuditMoreBinding.inflate(LayoutInflater.from(view.getContext()), (ViewGroup) view.getParent(), false);
        popCreatorAuditMoreBindingInflate.textView.setText(this.info.getValue().getNoPassReason());
        PopupWindow popupWindow = new PopupWindow(popCreatorAuditMoreBindingInflate.getRoot(), popCreatorAuditMoreBindingInflate.getRoot().getLayoutParams().width, popCreatorAuditMoreBindingInflate.getRoot().getLayoutParams().height, true);
        popupWindow.setOutsideTouchable(true);
        popCreatorAuditMoreBindingInflate.getRoot().measure(0, 0);
        int measuredWidth = popCreatorAuditMoreBindingInflate.getRoot().getMeasuredWidth();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        popupWindow.showAtLocation(view, 0, iArr[0] + ((view.getWidth() - measuredWidth) / 2), (int) (iArr[1] + (view.getHeight() * 1.5f)));
    }
}
