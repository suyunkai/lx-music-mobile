package com.wanos.careditproject.ui.viewmodel;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.repo.LoadStatus;
import com.wanos.careditproject.data.response.ProjectInfoListResponse;
import com.wanos.careditproject.data.response.ProjectTagListResponse;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorMineWorkListViewModel extends ViewModel {
    private int type;
    private int selectedTagId = -1;
    private boolean isSelectedAll = false;
    private int mPage = 1;
    private boolean hasMore = true;
    private MutableLiveData<List<ProjectInfo>> projectInfoList = new MutableLiveData<>(new ArrayList());
    private MutableLiveData<List<ProjectTagBean>> tagList = new MutableLiveData<>(new ArrayList());
    private MutableLiveData<LoadStatus> loadStatus = new MutableLiveData<>(LoadStatus.DEFAULT);

    public LiveData<List<ProjectInfo>> getProjectInfoList() {
        return this.projectInfoList;
    }

    public LiveData<List<ProjectTagBean>> getTagList() {
        return this.tagList;
    }

    public LiveData<LoadStatus> getLoadStatus() {
        return this.loadStatus;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public int getPage() {
        return this.mPage;
    }

    public void loadData() {
        if (this.type == 4) {
            loadCommunityMoreTags();
        } else {
            loadDataByType(1);
        }
    }

    public void loadMore() {
        if (this.loadStatus.getValue() == LoadStatus.LOADING) {
            return;
        }
        int i = this.mPage + 1;
        if (this.type == 4) {
            loadDataByCommunityMore(this.selectedTagId, this.isSelectedAll, i, createProjectWorkCallback(i));
        } else {
            loadDataByType(i);
        }
    }

    private ResponseCallBack<ProjectInfoListResponse> createProjectWorkCallback(final int i) {
        if (this.projectInfoList.getValue().isEmpty() && this.type != 4) {
            this.loadStatus.setValue(LoadStatus.LOADING);
        }
        return new ResponseCallBack<ProjectInfoListResponse>(null) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkListViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(ProjectInfoListResponse projectInfoListResponse) {
                List<ProjectInfo> list = (List) CreatorMineWorkListViewModel.this.projectInfoList.getValue();
                if (i == 1) {
                    list = projectInfoListResponse.data.list;
                    CreatorMineWorkListViewModel.this.hasMore = true;
                } else {
                    list.addAll(projectInfoListResponse.data.list);
                    CreatorMineWorkListViewModel.this.hasMore = list.size() < projectInfoListResponse.data.total;
                }
                CreatorMineWorkListViewModel.this.projectInfoList.setValue(list);
                CreatorMineWorkListViewModel.this.loadStatus.setValue(LoadStatus.SUCCESS);
                CreatorMineWorkListViewModel.this.mPage = i;
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                CreatorMineWorkListViewModel.this.loadStatus.setValue(LoadStatus.FAIL);
            }
        };
    }

    private void loadCommunityMoreTags() {
        if (this.tagList.getValue().isEmpty()) {
            this.loadStatus.setValue(LoadStatus.LOADING);
        }
        CreatorRetrofitUtil.getCommunityTagList(new ResponseCallBack<ProjectTagListResponse>(null) { // from class: com.wanos.careditproject.ui.viewmodel.CreatorMineWorkListViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(ProjectTagListResponse projectTagListResponse) {
                List<ProjectTagBean> list = projectTagListResponse.data.list;
                if (!list.isEmpty()) {
                    ProjectTagBean projectTagBean = new ProjectTagBean();
                    projectTagBean.setId(projectTagListResponse.data.id);
                    projectTagBean.setName("全部");
                    list.add(0, projectTagBean);
                }
                CreatorMineWorkListViewModel.this.tagList.setValue(list);
                CreatorMineWorkListViewModel creatorMineWorkListViewModel = CreatorMineWorkListViewModel.this;
                creatorMineWorkListViewModel.onTagSelected(creatorMineWorkListViewModel.selectedTagId);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                CreatorMineWorkListViewModel.this.loadStatus.setValue(LoadStatus.FAIL);
            }
        });
    }

    private void loadDataByType(int i) {
        ResponseCallBack<ProjectInfoListResponse> responseCallBackCreateProjectWorkCallback = createProjectWorkCallback(i);
        int i2 = this.type;
        if (i2 == 1) {
            CreatorRetrofitUtil.getMyWorkList(i, 100, responseCallBackCreateProjectWorkCallback);
        } else if (i2 == 2) {
            CreatorRetrofitUtil.getMyCollectionList(i, 100, responseCallBackCreateProjectWorkCallback);
        } else {
            if (i2 != 3) {
                return;
            }
            CreatorRetrofitUtil.getCommunityChoiceList(i, 100, responseCallBackCreateProjectWorkCallback);
        }
    }

    private void loadDataByCommunityMore(int i, boolean z, int i2, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        CreatorRetrofitUtil.getCommunityMoreList(i, z, i2, 100, responseCallBack);
    }

    public void onTagSelected(int i) {
        this.isSelectedAll = false;
        int i2 = 0;
        boolean z = false;
        while (i2 < this.tagList.getValue().size()) {
            ProjectTagBean projectTagBean = this.tagList.getValue().get(i2);
            if (projectTagBean.getId() == i) {
                this.selectedTagId = i;
                this.isSelectedAll = i2 == 0;
                projectTagBean.setSelected(true);
                z = true;
            } else {
                projectTagBean.setSelected(false);
            }
            i2++;
        }
        if (!z && !this.tagList.getValue().isEmpty()) {
            ProjectTagBean projectTagBean2 = this.tagList.getValue().get(0);
            projectTagBean2.setSelected(true);
            i = projectTagBean2.getId();
            this.selectedTagId = i;
            this.isSelectedAll = true;
            z = true;
        }
        MutableLiveData<List<ProjectTagBean>> mutableLiveData = this.tagList;
        mutableLiveData.setValue(mutableLiveData.getValue());
        if (z) {
            loadDataByCommunityMore(i, this.isSelectedAll, 1, createProjectWorkCallback(1));
        }
    }

    public void deleteWork(String str) {
        List<ProjectInfo> value = this.projectInfoList.getValue();
        if (value == null || value.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ProjectInfo projectInfo : value) {
            if (!TextUtils.equals(projectInfo.getId(), str)) {
                arrayList.add(projectInfo);
            }
        }
        this.projectInfoList.setValue(arrayList);
    }

    public boolean isHasMoreData() {
        return this.hasMore;
    }

    public void onProjectInfoChange(ProjectInfo projectInfo) {
        if (this.projectInfoList.getValue() != null) {
            for (ProjectInfo projectInfo2 : this.projectInfoList.getValue()) {
                if (TextUtils.equals(projectInfo2.getId(), projectInfo.getId())) {
                    projectInfo2.setCollect(projectInfo.isCollect());
                    projectInfo2.setCollectCount(projectInfo.getCollectCount());
                    projectInfo2.setOpen(projectInfo.isOpen());
                }
            }
            MutableLiveData<List<ProjectInfo>> mutableLiveData = this.projectInfoList;
            mutableLiveData.setValue(mutableLiveData.getValue());
        }
    }
}
