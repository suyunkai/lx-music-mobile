package com.wanos.editmain.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.EditProjectListBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.data.response.EditProjectListResponse;
import com.wanos.careditproject.databinding.FragmentCreatorProjectListBinding;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog;
import com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.editmain.adapter.CreatorProjectAdapter;
import com.wanos.editmain.dialog.CreatorShareDialog;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.presenter.BasePresenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorProjectListFragment extends WanosBaseFragment<BasePresenter> implements CreatorProjectAdapter.ProjectOpeareListener {
    public static final int TYPE_LIVE_HOUSE = 4;
    public static final int TYPE_MY_PROJECT = 0;
    public static final int TYPE_MY_WORKS = 1;
    public static final int TYPE_PROJECT_COLLECT = 2;
    public static final int TYPE_PROJECT_COMMUNITY = 3;
    private CreatorProjectAdapter adapter;
    private FragmentCreatorProjectListBinding binding;
    private int mPage;
    private final int mPageSize;
    private int pageType;
    private boolean preLoginState;
    private List<ProjectInfo> prjectInfoList;
    private View recyclerBottomPaddingView;
    private long udid;

    public CreatorProjectListFragment() {
        this.pageType = 0;
        this.mPage = 1;
        this.mPageSize = 50;
        this.preLoginState = false;
    }

    public CreatorProjectListFragment(int i) {
        this.mPage = 1;
        this.mPageSize = 50;
        this.preLoginState = false;
        this.pageType = i;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle != null) {
            this.pageType = bundle.getInt(MaterialListActivity.pageType);
        }
        this.binding = FragmentCreatorProjectListBinding.inflate(getLayoutInflater());
        iniView();
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(MaterialListActivity.pageType, this.pageType);
    }

    private void iniView() {
        iniRefreshView();
        createRecyclerBottomPaddingView();
        initRecyclerView();
        updateContentViewPadding();
    }

    public void getData() {
        getData(1, 50);
    }

    private void iniRefreshView() {
        this.binding.refreshLayout.setLoadMore(false);
        this.binding.refreshLayout.setCanDrag2Refresh(false);
        this.binding.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                CreatorProjectListFragment.this.getData(1, 50);
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                CreatorProjectListFragment.this.getData(CreatorProjectListFragment.this.mPage + 1, 50);
            }
        });
        getData(1, 50);
        showLoadingView();
    }

    private void initRecyclerView() {
        if (this.prjectInfoList == null) {
            this.prjectInfoList = new ArrayList();
        }
        this.binding.rvProjectList.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        this.adapter = new CreatorProjectAdapter(getActivity(), this.prjectInfoList, this.pageType);
        this.binding.rvProjectList.setAdapter(this.adapter);
        this.adapter.setProjectOpeareListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getData(int i, final int i2) {
        ResponseCallBack<EditProjectListResponse> responseCallBack = new ResponseCallBack<EditProjectListResponse>(getActivity()) { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectListResponse editProjectListResponse) {
                CreatorProjectListFragment.this.preLoginState = true;
                EditingUtils.log("getProjectList onResponseSuccessful ");
                if (CreatorProjectListFragment.this.isDetached() || CreatorProjectListFragment.this.isRemoving()) {
                    return;
                }
                CreatorProjectListFragment.this.closeLoadingView();
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefresh();
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefreshLoadMore();
                CreatorProjectListFragment.this.closeLoadingView();
                if (CreatorProjectListFragment.this.udid != getUdid()) {
                    return;
                }
                List<ProjectInfo> list = editProjectListResponse.data.getList();
                int page = editProjectListResponse.data.getPage();
                if (CreatorProjectListFragment.this.prjectInfoList == null) {
                    CreatorProjectListFragment.this.prjectInfoList = new ArrayList();
                }
                if (page == 1) {
                    CreatorProjectListFragment.this.prjectInfoList.clear();
                    if (list != null && list.size() > 0) {
                        CreatorProjectListFragment.this.prjectInfoList.addAll(list);
                    }
                    CreatorProjectListFragment.this.adapter.setData(CreatorProjectListFragment.this.prjectInfoList);
                    CreatorProjectListFragment.this.adapter.notifyDataSetChanged();
                    CreatorProjectListFragment.this.mPage = page;
                    if (CreatorProjectListFragment.this.prjectInfoList.size() == 0) {
                        CreatorProjectListFragment.this.binding.emptyView.getRoot().setVisibility(0);
                        CreatorProjectListFragment.this.binding.emptyView.tvEmpty.setText(R.string.creator_empty_0);
                    } else {
                        CreatorProjectListFragment.this.binding.emptyView.getRoot().setVisibility(8);
                    }
                } else if (list != null && list.size() > 0) {
                    CreatorProjectListFragment.this.prjectInfoList.addAll(list);
                    CreatorProjectListFragment.this.adapter.setData(CreatorProjectListFragment.this.prjectInfoList);
                    CreatorProjectListFragment.this.adapter.notifyDataSetChanged();
                    CreatorProjectListFragment.this.mPage = page;
                }
                if (CreatorProjectListFragment.this.prjectInfoList.size() > i2) {
                    CreatorProjectListFragment.this.binding.refreshLayout.setLoadMore(true);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i3, String str) {
                EditingUtils.log("getData onResponseFailure");
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefresh();
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefreshLoadMore();
                CreatorProjectListFragment.this.closeLoadingView();
                if (i3 == 20001) {
                    CreatorProjectListFragment.this.prjectInfoList.clear();
                    CreatorProjectListFragment.this.adapter.setData(CreatorProjectListFragment.this.prjectInfoList);
                    CreatorProjectListFragment.this.adapter.notifyDataSetChanged();
                    CreatorProjectListFragment.this.binding.emptyView.getRoot().setVisibility(0);
                    CreatorProjectListFragment.this.binding.emptyView.tvEmpty.setText(R.string.creator_empty_0);
                    return;
                }
                if (CreatorProjectListFragment.this.prjectInfoList == null || CreatorProjectListFragment.this.prjectInfoList.size() == 0) {
                    EditingUtils.log("getData onResponseFailure 1");
                    CreatorProjectListFragment.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.2.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            CreatorProjectListFragment.this.closeLoadingView();
                            CreatorProjectListFragment.this.getData(1, 50);
                        }
                    });
                }
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefresh();
                CreatorProjectListFragment.this.binding.refreshLayout.finishRefreshLoadMore();
            }
        };
        this.udid = responseCallBack.getUdid();
        System.out.println("pageType = " + this.pageType);
        int i3 = this.pageType;
        if (i3 == 0) {
            CreatorRetrofitUtil.getProjectList(i, i2, responseCallBack);
            return;
        }
        if (i3 == 2) {
            CreatorRetrofitUtil.getWorksCollectList(i, i2, responseCallBack);
        } else if (i3 == 3) {
            CreatorRetrofitUtil.getWorksCommunityList(i, i2, responseCallBack);
        } else {
            CreatorRetrofitUtil.getProductionList(i, i2, responseCallBack);
        }
    }

    private void updateContentViewPadding() {
        this.adapter.setFooterView(this.recyclerBottomPaddingView);
    }

    public void createRecyclerBottomPaddingView() {
        int dimension = (int) getResources().getDimension(R.dimen.c_play_bar_height);
        this.recyclerBottomPaddingView = new View(getActivity());
        this.recyclerBottomPaddingView.setLayoutParams(new RecyclerView.LayoutParams(-1, dimension));
    }

    private void getOneData() {
        List<ProjectInfo> list = this.prjectInfoList;
        int size = list != null ? list.size() + 1 : 1;
        ResponseCallBack<EditProjectListResponse> responseCallBack = new ResponseCallBack<EditProjectListResponse>(getActivity()) { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectListResponse editProjectListResponse) {
                EditProjectListBean editProjectListBean;
                if (CreatorProjectListFragment.this.isVisible() && (editProjectListBean = editProjectListResponse.data) != null && editProjectListBean.getList() != null && editProjectListBean.getList().size() > 0) {
                    CreatorProjectListFragment.this.prjectInfoList.addAll(editProjectListBean.getList());
                    if (CreatorProjectListFragment.this.adapter != null) {
                        CreatorProjectListFragment.this.adapter.setData(CreatorProjectListFragment.this.prjectInfoList);
                        CreatorProjectListFragment.this.adapter.notifyDataSetChanged();
                    }
                }
            }
        };
        System.out.println("pageType = " + this.pageType);
        int i = this.pageType;
        if (i == 0) {
            CreatorRetrofitUtil.getProjectList(size, 1, responseCallBack);
            return;
        }
        if (i == 2) {
            CreatorRetrofitUtil.getWorksCollectList(size, 1, responseCallBack);
        } else if (i == 3) {
            CreatorRetrofitUtil.getWorksCommunityList(size, 1, responseCallBack);
        } else {
            CreatorRetrofitUtil.getProductionList(size, 1, responseCallBack);
        }
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onDeleteProjectListener(final int i, final ProjectInfo projectInfo) {
        ResponseCallBack<BaseResponse> responseCallBack = new ResponseCallBack<BaseResponse>(getActivity()) { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse baseResponse) {
                ProjectInfo projectInfo2;
                if (i >= CreatorProjectListFragment.this.prjectInfoList.size() || (projectInfo2 = projectInfo) == null || projectInfo2.getId() == null || CreatorProjectListFragment.this.prjectInfoList.get(i) == null || !projectInfo.getId().equals(((ProjectInfo) CreatorProjectListFragment.this.prjectInfoList.get(i)).getId())) {
                    return;
                }
                CreatorProjectListFragment.this.prjectInfoList.remove(i);
                if (CreatorProjectListFragment.this.adapter != null) {
                    CreatorProjectListFragment.this.adapter.setData(CreatorProjectListFragment.this.prjectInfoList);
                    CreatorProjectListFragment.this.adapter.notifyDataSetChanged();
                }
                CreatorProjectListFragment.this.getData();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtils.showShort(str);
            }
        };
        String id = projectInfo.getId();
        if (this.pageType == 0) {
            CreatorRetrofitUtil.projectDelete(id, responseCallBack);
        } else {
            CreatorRetrofitUtil.worksDelete(id, responseCallBack);
        }
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onModifyProjectListener(int i, ProjectInfo projectInfo) {
        new CreatorProjectModifyDialog(getContext(), this.pageType == 0, false, projectInfo.getId(), projectInfo.getTitle(), projectInfo.getContent(), projectInfo.getPicture()).show();
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onCreateNewProjectListener(int i, ProjectInfo projectInfo) {
        CreatorRetrofitUtil.projectCopy(projectInfo.getId(), new ResponseCallBack<EditProjectCopyResponse>(getActivity()) { // from class: com.wanos.editmain.fragment.CreatorProjectListFragment.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                CreatorProjectListFragment.this.binding.refreshLayout.autoRefresh();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtils.showShort(str);
            }
        });
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onPublishProjectListener(int i, ProjectInfo projectInfo) {
        CreatorProjectPublishActivity.open(getContext(), projectInfo.getId(), projectInfo.getTitle(), projectInfo.getContent(), projectInfo.getPicture(), EditingUtils.EditProjectType.getEditProjectType(projectInfo.getProjectType()));
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onShareProjectListener(int i, ProjectInfo projectInfo) {
        new CreatorShareDialog(getContext(), CreatorShareDialog.TYPE_SHARE, projectInfo.getId()).show();
    }

    @Override // com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectOpeareListener
    public void onUpdateListener() {
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onColllectStatusChange(ProjectInfo projectInfo) {
        boolean z;
        CreatorProjectAdapter creatorProjectAdapter = this.adapter;
        if (creatorProjectAdapter == null) {
            return;
        }
        Iterator<ProjectInfo> it = creatorProjectAdapter.getDatas().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            ProjectInfo next = it.next();
            if (next != null && next.getId() != null && next.getId().equals(projectInfo.getId())) {
                next.setCollect(projectInfo.isCollect());
                z = true;
                break;
            }
        }
        if (z) {
            this.adapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void createOrUpdateProjectInfo(ProjectChangeEvent projectChangeEvent) {
        EditingUtils.log("createOrUpdateProjectInfo 0");
        if (projectChangeEvent != null) {
            EditingUtils.log("createOrUpdateProjectInfo 1");
            ProjectInfo projectInfo = projectChangeEvent.getProjectInfo();
            int oprateType = projectChangeEvent.getOprateType();
            int type = projectChangeEvent.getType();
            EditingUtils.log("createOrUpdateProjectInfo 2 type=" + type + ", pageType=" + this.pageType);
            if ((type == 0 && this.pageType == 0) || (type == 1 && this.pageType == 1)) {
                if (oprateType == 0) {
                    this.binding.refreshLayout.autoRefresh();
                } else if (oprateType == 1) {
                    updateInfo(projectInfo);
                }
            }
        }
    }

    private void updateInfo(ProjectInfo projectInfo) {
        if (projectInfo == null || this.prjectInfoList == null) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.prjectInfoList.size()) {
                i = -1;
                break;
            }
            ProjectInfo projectInfo2 = this.prjectInfoList.get(i);
            if (projectInfo2 != null && !TextUtils.isEmpty(projectInfo2.getId()) && projectInfo2.getId().equals(projectInfo.getId())) {
                projectInfo.setCreateTime(projectInfo2.getCreateTime());
                break;
            }
            i++;
        }
        if (i > -1) {
            this.prjectInfoList.set(i, projectInfo);
            CreatorProjectAdapter creatorProjectAdapter = this.adapter;
            if (creatorProjectAdapter != null) {
                creatorProjectAdapter.setData(this.prjectInfoList);
                this.adapter.notifyItemChanged(i);
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        if (!loginOrLogoutEvent.isLogin()) {
            if (this.pageType != 3) {
                this.preLoginState = loginOrLogoutEvent.isLogin();
                this.prjectInfoList.clear();
                this.adapter.setData(this.prjectInfoList);
                this.adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        EditingUtils.log("loginOrLogout preLoginState=" + this.preLoginState + ", event.isLogin()=" + loginOrLogoutEvent.isLogin());
        if (this.binding.refreshLayout == null || loginOrLogoutEvent.isLogin() == this.preLoginState) {
            return;
        }
        EditingUtils.log("loginOrLogout vvv");
        getData();
        this.preLoginState = loginOrLogoutEvent.isLogin();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
