package com.wanos.careditproject.data.repo;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.R;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionRepo {
    public LiveData<Boolean> toggleWorkCollectRequest(final ProjectInfo projectInfo) {
        final MutableLiveData mutableLiveData = new MutableLiveData();
        String id = projectInfo.getId();
        Context context = null;
        if (!projectInfo.isCollect()) {
            CreatorRetrofitUtil.workCollect(id, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.careditproject.data.repo.CollectionRepo.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    EventBus.getDefault().post(projectInfo);
                    mutableLiveData.setValue(true);
                    ToastUtil.showMsg(R.string.music_collect_complete);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    ToastUtil.showMsg("收藏失败");
                }
            });
        } else {
            CreatorRetrofitUtil.workCancelCollect(id, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.careditproject.data.repo.CollectionRepo.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r2.isCollect());
                    EventBus.getDefault().post(projectInfo);
                    mutableLiveData.setValue(false);
                    ToastUtil.showMsg(R.string.music_cancel_collect_complete);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    ToastUtil.showMsg("取消收藏失败");
                }
            });
        }
        return mutableLiveData;
    }
}
