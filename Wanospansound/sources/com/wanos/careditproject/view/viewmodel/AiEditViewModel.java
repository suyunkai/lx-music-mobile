package com.wanos.careditproject.view.viewmodel;

import android.graphics.Color;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.careditproject.data.bean.AiPromptEntity;
import com.wanos.careditproject.data.bean.AiPromptResponse;
import com.wanos.careditproject.data.repo.AiRetrofitUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AiEditViewModel extends ViewModel {
    private final MutableLiveData<List<AiPromptEntity.PromptBean>> _LabelsData;
    public final LiveData<List<AiPromptEntity.PromptBean>> labelsData;
    private final int[] mLabelsItemStrokeColors = {Color.parseColor("#9746FF"), Color.parseColor("#0B99FF")};
    private long mLastClickTime;

    public int[] getStrokeColors() {
        return this.mLabelsItemStrokeColors;
    }

    public void setLastClickTime() {
        this.mLastClickTime = System.currentTimeMillis();
    }

    public boolean isCanCreate() {
        return System.currentTimeMillis() - this.mLastClickTime >= 3000;
    }

    public AiEditViewModel() {
        MutableLiveData<List<AiPromptEntity.PromptBean>> mutableLiveData = new MutableLiveData<>();
        this._LabelsData = mutableLiveData;
        this.labelsData = mutableLiveData;
        AiRetrofitUtil.getInstance().getAiPromptList(new ResponseCallBack<AiPromptResponse>(null) { // from class: com.wanos.careditproject.view.viewmodel.AiEditViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(AiPromptResponse aiPromptResponse) {
                AiPromptEntity data = aiPromptResponse.getData();
                if (data != null) {
                    AiEditViewModel.this._LabelsData.setValue(data.getPrompts());
                } else {
                    onResponseFailure(aiPromptResponse.code, aiPromptResponse.msg);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                if (StringUtils.isEmpty(str)) {
                    return;
                }
                ToastUtil.showMsg(str);
            }
        });
    }
}
