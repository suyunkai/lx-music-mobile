package com.wanos.careditproject.view.viewmodel;

import android.graphics.Color;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.AiRandomPromptResponse;
import com.wanos.careditproject.data.bean.AiRandomState;
import com.wanos.careditproject.data.repo.AiRetrofitUtil;
import com.wanos.careditproject.view.widget.CustomAiEditView;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public class CustomAiEditViewModel extends ViewModel {
    private final MediatorLiveData<Boolean> _CreateButtonEnable;
    private final MutableLiveData<CustomAiEditView.EditState> _EditModel;
    private final MutableLiveData<String> _EmotionContent;
    private final MutableLiveData<CharSequence> _InputContent;
    private final MutableLiveData<AiRandomState> _RandomPromptState;
    private final SingleLiveEvent<String> _RequestRandomPrompt;
    private final MutableLiveData<String> _StyleContent;
    public final LiveData<Boolean> createButtonEnable;
    public final LiveData<CustomAiEditView.EditState> editModel;
    public final LiveData<String> emotionContent;
    public final LiveData<AiRandomState> randomPromptState;
    public final LiveData<String> requestRandomPrompt;
    public final LiveData<Integer> selectedTextBackgroundColor;
    public final LiveData<String> styleContent;

    static /* synthetic */ Integer lambda$new$0(CustomAiEditView.EditState editState) {
        if (editState == CustomAiEditView.EditState.SELECT_ED_MODE) {
            return Integer.valueOf(Color.parseColor("#4D1368FB"));
        }
        return 0;
    }

    public CustomAiEditViewModel() {
        MediatorLiveData<Boolean> mediatorLiveData = new MediatorLiveData<>(true);
        this._CreateButtonEnable = mediatorLiveData;
        this.createButtonEnable = mediatorLiveData;
        MutableLiveData<CustomAiEditView.EditState> mutableLiveData = new MutableLiveData<>(CustomAiEditView.EditState.SELECT_MODE);
        this._EditModel = mutableLiveData;
        this.editModel = mutableLiveData;
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        this._RequestRandomPrompt = singleLiveEvent;
        this.requestRandomPrompt = singleLiveEvent;
        MutableLiveData<CharSequence> mutableLiveData2 = new MutableLiveData<>("");
        this._InputContent = mutableLiveData2;
        this.selectedTextBackgroundColor = Transformations.map(mutableLiveData, new Function1() { // from class: com.wanos.careditproject.view.viewmodel.CustomAiEditViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CustomAiEditViewModel.lambda$new$0((CustomAiEditView.EditState) obj);
            }
        });
        MutableLiveData<AiRandomState> mutableLiveData3 = new MutableLiveData<>(AiRandomState.DEFAULT);
        this._RandomPromptState = mutableLiveData3;
        this.randomPromptState = mutableLiveData3;
        mediatorLiveData.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.careditproject.view.viewmodel.CustomAiEditViewModel$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m432x2cde689d((CharSequence) obj);
            }
        });
        mediatorLiveData.addSource(mutableLiveData, new Observer() { // from class: com.wanos.careditproject.view.viewmodel.CustomAiEditViewModel$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m433x206decde((CustomAiEditView.EditState) obj);
            }
        });
        MutableLiveData<String> mutableLiveData4 = new MutableLiveData<>(Utils.getApp().getResources().getStringArray(R.array.ai_style)[0]);
        this._StyleContent = mutableLiveData4;
        this.styleContent = mutableLiveData4;
        MutableLiveData<String> mutableLiveData5 = new MutableLiveData<>(Utils.getApp().getResources().getStringArray(R.array.ai_emotion)[0]);
        this._EmotionContent = mutableLiveData5;
        this.emotionContent = mutableLiveData5;
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-careditproject-view-viewmodel-CustomAiEditViewModel, reason: not valid java name */
    /* synthetic */ void m432x2cde689d(CharSequence charSequence) {
        initCreateButtonState();
    }

    /* JADX INFO: renamed from: lambda$new$2$com-wanos-careditproject-view-viewmodel-CustomAiEditViewModel, reason: not valid java name */
    /* synthetic */ void m433x206decde(CustomAiEditView.EditState editState) {
        initCreateButtonState();
    }

    public void setEditMode(CustomAiEditView.EditState editState) {
        if (editState == this.editModel.getValue()) {
            return;
        }
        this._EditModel.setValue(editState);
    }

    public void setInputContent(CharSequence charSequence) {
        this._InputContent.setValue(charSequence);
    }

    public void setStyleContent(String str) {
        this._StyleContent.setValue(str);
    }

    public void setEmotionContent(String str) {
        this._EmotionContent.setValue(str);
    }

    private void initCreateButtonState() {
        if (this.editModel.getValue() == CustomAiEditView.EditState.SELECT_MODE || this.editModel.getValue() == CustomAiEditView.EditState.SELECT_ED_MODE) {
            this._CreateButtonEnable.setValue(true);
        } else {
            this._CreateButtonEnable.setValue(Boolean.valueOf(this._InputContent.getValue().length() > 0));
        }
    }

    public void requestRandomPrompt() {
        this._RandomPromptState.setValue(AiRandomState.LOADING);
        AiRetrofitUtil.getInstance().getRandomPrompt(new ResponseCallBack<AiRandomPromptResponse>(null) { // from class: com.wanos.careditproject.view.viewmodel.CustomAiEditViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(AiRandomPromptResponse aiRandomPromptResponse) {
                CustomAiEditViewModel.this._RandomPromptState.setValue(AiRandomState.DEFAULT);
                if (aiRandomPromptResponse == null || aiRandomPromptResponse.code != 0 || aiRandomPromptResponse.getData() == null || StringUtils.isEmpty(aiRandomPromptResponse.getData().getAnswer())) {
                    return;
                }
                CustomAiEditViewModel.this._RequestRandomPrompt.setValue(aiRandomPromptResponse.getData().getAnswer());
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                CustomAiEditViewModel.this._RandomPromptState.setValue(AiRandomState.DEFAULT);
                ToastUtil.showMsg(str);
            }
        });
    }
}
