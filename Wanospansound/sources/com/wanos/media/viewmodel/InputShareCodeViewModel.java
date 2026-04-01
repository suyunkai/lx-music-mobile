package com.wanos.media.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.EmptyEntity;
import com.wanos.media.entity.ThemeEvent;
import com.wanos.media.zero_p.R;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class InputShareCodeViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _closeDialogEvent;
    private final MutableLiveData<Boolean> _positiveBtnEnableState;
    public final LiveData<Boolean> closeDialogEvent;
    public final LiveData<Boolean> positiveBtnEnableState;

    public InputShareCodeViewModel() {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>(false);
        this._positiveBtnEnableState = mutableLiveData;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>(false);
        this._closeDialogEvent = mutableLiveData2;
        this.positiveBtnEnableState = mutableLiveData;
        this.closeDialogEvent = mutableLiveData2;
    }

    public void setPositiveBtnEnableState(boolean z) {
        this._positiveBtnEnableState.setValue(Boolean.valueOf(z));
    }

    public void addShareCode(String str) {
        if (StringUtils.isEmpty(str) || str.length() < 6) {
            ToastUtil.showMsg(R.string.zero_me_verification_input_error);
        } else {
            HttpTools.getInstance().addThemeFromShareCode(str, new HttpCallback<EmptyEntity>() { // from class: com.wanos.media.viewmodel.InputShareCodeViewModel.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseEntity<EmptyEntity> baseEntity) {
                    if (baseEntity.code != 0) {
                        InputShareCodeViewModel.this._closeDialogEvent.setValue(false);
                        ToastUtil.showMsg(baseEntity.msg);
                        return;
                    }
                    ToastUtil.showMsg(R.string.added_success);
                    EventBus.getDefault().post(new ThemeEvent(103));
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_ADD_SHARE_CODE_SUCC, "", "", "", "", 0);
                    InputShareCodeViewModel.this._closeDialogEvent.setValue(true);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    InputShareCodeViewModel.this._closeDialogEvent.setValue(false);
                    ToastUtil.showMsg(str2);
                }
            });
        }
    }
}
