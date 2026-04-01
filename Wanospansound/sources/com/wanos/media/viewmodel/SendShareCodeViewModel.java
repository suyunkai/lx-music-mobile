package com.wanos.media.viewmodel;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.GetShareCodeEntity;
import com.wanos.media.entity.PageState;

/* JADX INFO: loaded from: classes3.dex */
public class SendShareCodeViewModel extends ViewModel {
    public static final String KEY_THEME_ID = "themeId";
    private final SingleLiveEvent<String> _ToastMessage;
    private final MutableLiveData<PageState> _pageState;
    private final MutableLiveData<String> _themeShareCode;
    public final LiveData<PageState> pageState;
    public LiveData<String> themeShareCode;
    public LiveData<String> toastMessage;

    public SendShareCodeViewModel(Bundle bundle) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        this._themeShareCode = mutableLiveData;
        this.themeShareCode = mutableLiveData;
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        this._ToastMessage = singleLiveEvent;
        this.toastMessage = singleLiveEvent;
        MutableLiveData<PageState> mutableLiveData2 = new MutableLiveData<>();
        this._pageState = mutableLiveData2;
        this.pageState = mutableLiveData2;
        if (bundle == null) {
            mutableLiveData2.setValue(PageState.ERROR);
            return;
        }
        String string = bundle.getString(KEY_THEME_ID);
        if (StringUtils.isEmpty(string)) {
            mutableLiveData2.setValue(PageState.ERROR);
        } else {
            mutableLiveData2.setValue(PageState.LOADING);
            HttpTools.getInstance().getThemeShareCode(string, new HttpCallback<GetShareCodeEntity>() { // from class: com.wanos.media.viewmodel.SendShareCodeViewModel.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseEntity<GetShareCodeEntity> baseEntity) {
                    if (baseEntity.code == 0) {
                        SendShareCodeViewModel.this._pageState.setValue(PageState.SUCCESS);
                        SendShareCodeViewModel.this._themeShareCode.setValue(baseEntity.getData().getShareCode());
                    } else {
                        SendShareCodeViewModel.this._pageState.setValue(PageState.ERROR);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    SendShareCodeViewModel.this._pageState.setValue(PageState.ERROR);
                    SendShareCodeViewModel.this._ToastMessage.setValue(str);
                }
            });
        }
    }
}
