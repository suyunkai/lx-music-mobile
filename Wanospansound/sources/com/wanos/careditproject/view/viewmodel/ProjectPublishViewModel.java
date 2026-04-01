package com.wanos.careditproject.view.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.data.bean.WorkCateBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditGetCateListResponse;
import com.wanos.careditproject.data.response.EditProjectExportResponse;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.utils.EditingUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectPublishViewModel extends ViewModel {
    public String title = "";
    public String desc = "";
    public String pic = "";
    public String projectId = "";
    public int workTypeIndex = 0;
    public boolean openTemplate = false;
    public boolean toAgree = false;
    public boolean toCommunity = true;
    public int projectType = 1;
    public MutableLiveData<List<WorkCateBean>> workCateList = new MutableLiveData<>(new ArrayList());
    public MutableLiveData<PublishResult> publishResult = new MutableLiveData<>();

    public void init(String str, String str2, String str3, String str4, int i) {
        EditingUtils.log("ProjectPublishViewModel init");
        this.projectId = str;
        if (str2 != null) {
            EditingUtils.log("ProjectPublishViewModel init title = " + str2);
            this.title = str2;
        }
        if (str3 != null) {
            this.desc = str3;
        }
        this.pic = str4;
        this.projectType = i;
    }

    public void initWorkCateList(int i) {
        CreatorRetrofitUtil.getWorksCateList(i, new ResponseCallBack<EditGetCateListResponse>(null) { // from class: com.wanos.careditproject.view.viewmodel.ProjectPublishViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditGetCateListResponse editGetCateListResponse) {
                if (editGetCateListResponse.data == null || editGetCateListResponse.data.getList() == null || editGetCateListResponse.data.getList().size() <= 0) {
                    return;
                }
                ProjectPublishViewModel.this.workCateList.postValue(editGetCateListResponse.data.getList());
            }
        });
    }

    public void publishProject(boolean z, final String str, final String str2, int i, int i2, int i3, String str3, String str4) {
        Context context = null;
        if (!z) {
            CreatorRetrofitUtil.editExport(this.projectId, str, str2, this.desc, i, i2, i3, new ResponseCallBack<EditProjectExportResponse>(context) { // from class: com.wanos.careditproject.view.viewmodel.ProjectPublishViewModel.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(EditProjectExportResponse editProjectExportResponse) {
                    ProjectInfo projectInfo = new ProjectInfo();
                    projectInfo.setId(ProjectPublishViewModel.this.projectId);
                    projectInfo.setTitle(str);
                    projectInfo.setPicture(str2);
                    EventBus.getDefault().post(new ProjectChangeEvent(0, 1, projectInfo));
                    ProjectPublishViewModel.this.publishResult.postValue(new PublishResult(true, ""));
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i4, String str5) {
                    ProjectPublishViewModel.this.publishResult.postValue(new PublishResult(false, str5));
                }
            });
        } else {
            CreatorRetrofitUtil.editAiExport(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, str, str2, this.desc, i, i2, i3, str3, str4, new ResponseCallBack<EditProjectExportResponse>(context) { // from class: com.wanos.careditproject.view.viewmodel.ProjectPublishViewModel.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(EditProjectExportResponse editProjectExportResponse) {
                    ProjectPublishViewModel.this.publishResult.postValue(new PublishResult(true, ""));
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i4, String str5) {
                    ProjectPublishViewModel.this.publishResult.postValue(new PublishResult(false, str5));
                }
            });
        }
    }

    public static class PublishResult {
        public boolean isSuccess;
        public String msg;

        public PublishResult(boolean z, String str) {
            this.isSuccess = z;
            this.msg = str;
        }
    }
}
