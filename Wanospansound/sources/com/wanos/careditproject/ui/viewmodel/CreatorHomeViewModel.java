package com.wanos.careditproject.ui.viewmodel;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.ProjectInfoListResponse;
import com.wanos.careditproject.data.response.ProjectTagListResponse;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeViewModel extends ViewModel {
    private Call<ProjectInfoListResponse> audiobookprojectListCall;
    private MutableLiveData<List<CreatorCommonItem>> creatorHomeItems;
    private Call<ProjectInfoListResponse> musicprojectListCall;
    private int tagAudioSelectId;
    private int tagMusicSelectId;

    public CreatorHomeViewModel() {
        MutableLiveData<List<CreatorCommonItem>> mutableLiveData = new MutableLiveData<>(new ArrayList());
        this.creatorHomeItems = mutableLiveData;
        this.tagAudioSelectId = -1;
        this.tagMusicSelectId = -1;
        mutableLiveData.getValue().add(new CreatorCommonItem(0, 0, null));
        this.creatorHomeItems.getValue().add(new CreatorCommonItem(1, 1, null));
        this.creatorHomeItems.getValue().add(new CreatorCommonItem(2, 2, null));
        this.creatorHomeItems.getValue().add(new CreatorCommonItem(3, 3, Collections.EMPTY_LIST));
        this.creatorHomeItems.getValue().add(new CreatorCommonItem(4, 2, null));
        this.creatorHomeItems.getValue().add(new CreatorCommonItem(5, 3, Collections.EMPTY_LIST));
    }

    public void loadData() {
        loadTagData(2);
        loadTagData(4);
    }

    public void clearCardList() {
        this.creatorHomeItems.getValue().remove(3);
        this.creatorHomeItems.getValue().add(3, new CreatorCommonItem(3, 3, Collections.EMPTY_LIST));
        this.creatorHomeItems.getValue().remove(5);
        this.creatorHomeItems.getValue().add(5, new CreatorCommonItem(5, 3, Collections.EMPTY_LIST));
        MutableLiveData<List<CreatorCommonItem>> mutableLiveData = this.creatorHomeItems;
        mutableLiveData.setValue(mutableLiveData.getValue());
    }

    private void loadTagData(final int i) {
        CreatorRetrofitUtil.getWorksTagList(i != 2 ? 1 : 2, new ResponseCallBack<ProjectTagListResponse>(null) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorHomeViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(ProjectTagListResponse projectTagListResponse) {
                ProjectTagBean projectTagBean = new ProjectTagBean();
                projectTagBean.setId(projectTagListResponse.data.id);
                projectTagBean.setName(projectTagListResponse.data.name);
                List<ProjectTagBean> list = projectTagListResponse.data.list;
                if (!list.isEmpty()) {
                    ProjectTagBean projectTagBean2 = new ProjectTagBean();
                    projectTagBean2.setId(projectTagListResponse.data.id);
                    projectTagBean2.setName("全部");
                    list.add(0, projectTagBean2);
                }
                projectTagBean.setList(list);
                ((List) CreatorHomeViewModel.this.creatorHomeItems.getValue()).remove(i);
                ((List) CreatorHomeViewModel.this.creatorHomeItems.getValue()).add(i, new CreatorCommonItem(i, 2, projectTagBean));
                CreatorHomeViewModel.this.onTagSelected(i, -1);
            }
        });
    }

    private void loadCardData(final int i, int i2, boolean z) {
        Call<ProjectInfoListResponse> call = i == 5 ? this.musicprojectListCall : this.audiobookprojectListCall;
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        Call<ProjectInfoListResponse> inspirationList = CreatorRetrofitUtil.getInspirationList(i2, z, 1, 6, new ResponseCallBack<ProjectInfoListResponse>(null) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorHomeViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i3, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(ProjectInfoListResponse projectInfoListResponse) {
                ((List) CreatorHomeViewModel.this.creatorHomeItems.getValue()).remove(i);
                ((List) CreatorHomeViewModel.this.creatorHomeItems.getValue()).add(i, new CreatorCommonItem(i, 3, projectInfoListResponse.data.list));
                CreatorHomeViewModel.this.creatorHomeItems.setValue((List) CreatorHomeViewModel.this.creatorHomeItems.getValue());
            }
        });
        if (i == 5) {
            this.musicprojectListCall = inspirationList;
        } else {
            this.audiobookprojectListCall = inspirationList;
        }
    }

    public void onTagSelected(int i, int i2) {
        if (i2 == -1) {
            if (i == 2) {
                i2 = this.tagAudioSelectId;
            } else if (i == 4) {
                i2 = this.tagMusicSelectId;
            }
        }
        ProjectTagBean projectTagBean = (ProjectTagBean) this.creatorHomeItems.getValue().get(i).getData();
        List<ProjectTagBean> list = projectTagBean.getList();
        ProjectTagBean projectTagBean2 = new ProjectTagBean();
        projectTagBean2.setName(projectTagBean.getName());
        projectTagBean2.setList(projectTagBean.getList());
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        while (i3 < list.size()) {
            ProjectTagBean projectTagBean3 = list.get(i3);
            if (projectTagBean3.getId() == i2) {
                z2 = i3 == 0;
                projectTagBean3.setSelected(true);
                z = true;
            } else {
                projectTagBean3.setSelected(false);
            }
            i3++;
        }
        if (!z && !list.isEmpty()) {
            ProjectTagBean projectTagBean4 = list.get(0);
            projectTagBean4.setSelected(true);
            i2 = projectTagBean4.getId();
            z = true;
            z2 = true;
        }
        if (z) {
            if (i == 2) {
                this.tagAudioSelectId = i2;
                loadCardData(3, i2, z2);
            } else if (i == 4) {
                this.tagMusicSelectId = i2;
                loadCardData(5, i2, z2);
            }
        }
        this.creatorHomeItems.getValue().remove(i);
        this.creatorHomeItems.getValue().add(i, new CreatorCommonItem(i, 2, projectTagBean2));
        MutableLiveData<List<CreatorCommonItem>> mutableLiveData = this.creatorHomeItems;
        mutableLiveData.setValue(mutableLiveData.getValue());
    }

    public LiveData<List<CreatorCommonItem>> getCreatorHomeItems() {
        return this.creatorHomeItems;
    }

    public void onProjectInfoChange(ProjectInfo projectInfo) {
        if (findTargetInCardList(3, projectInfo)) {
            MutableLiveData<List<CreatorCommonItem>> mutableLiveData = this.creatorHomeItems;
            mutableLiveData.setValue(mutableLiveData.getValue());
        } else if (findTargetInCardList(5, projectInfo)) {
            MutableLiveData<List<CreatorCommonItem>> mutableLiveData2 = this.creatorHomeItems;
            mutableLiveData2.setValue(mutableLiveData2.getValue());
        }
    }

    private boolean findTargetInCardList(int i, ProjectInfo projectInfo) {
        List<ProjectInfo> list;
        CreatorCommonItem creatorCommonItem = this.creatorHomeItems.getValue().get(i);
        if (creatorCommonItem != null && creatorCommonItem.getData() != null && (list = (List) creatorCommonItem.getData()) != null && !list.isEmpty()) {
            for (ProjectInfo projectInfo2 : list) {
                if (TextUtils.equals(projectInfo2.getId(), projectInfo.getId())) {
                    projectInfo2.setCollect(projectInfo.isCollect());
                    projectInfo2.setCollectCount(projectInfo.getCollectCount());
                    return true;
                }
            }
        }
        return false;
    }
}
