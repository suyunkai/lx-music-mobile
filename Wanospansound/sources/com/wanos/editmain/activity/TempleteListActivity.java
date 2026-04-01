package com.wanos.editmain.activity;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectListResponse;
import com.wanos.careditproject.databinding.ActivityTempleteListBinding;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.editmain.adapter.CreatorHomeAdapter;
import com.wanos.media.base.WanosBaseActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TempleteListActivity extends WanosBaseActivity {
    ActivityTempleteListBinding binding;
    private CreatorHomeAdapter creatorHomeAdapter;
    private List<ProjectInfo> prjectInfoList = new ArrayList();
    private int pageSize = 40;
    private int page = 1;

    static /* synthetic */ int access$008(TempleteListActivity templeteListActivity) {
        int i = templeteListActivity.page;
        templeteListActivity.page = i + 1;
        return i;
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityTempleteListBinding activityTempleteListBindingInflate = ActivityTempleteListBinding.inflate(getLayoutInflater());
        this.binding = activityTempleteListBindingInflate;
        setContentView(activityTempleteListBindingInflate.getRoot());
        initData();
        initView();
    }

    @Override // android.app.Activity
    public void recreate() {
        super.recreate();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        CreatorHomeAdapter creatorHomeAdapter = this.creatorHomeAdapter;
        if (creatorHomeAdapter != null) {
            creatorHomeAdapter.destroy();
        }
        super.onDestroy();
    }

    public void initData() {
        this.page = 1;
        showLoadingView("正在加载中");
        this.binding.templeteRefreshLayout.setLoadMore(false);
        getData();
    }

    public void getData() {
        CreatorRetrofitUtil.getWorksTempleteList(this.page, this.pageSize, new ResponseCallBack<EditProjectListResponse>(this) { // from class: com.wanos.editmain.activity.TempleteListActivity.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectListResponse editProjectListResponse) {
                if (TempleteListActivity.this.isDestroyed()) {
                    return;
                }
                TempleteListActivity.access$008(TempleteListActivity.this);
                TempleteListActivity.this.closeLoadingView();
                List<ProjectInfo> list = editProjectListResponse.data.getList();
                if (list.size() < TempleteListActivity.this.pageSize) {
                    TempleteListActivity.this.binding.templeteRefreshLayout.setLoadMore(false);
                } else {
                    TempleteListActivity.this.binding.templeteRefreshLayout.setLoadMore(true);
                }
                int page = editProjectListResponse.data.getPage();
                if (TempleteListActivity.this.prjectInfoList == null) {
                    TempleteListActivity.this.prjectInfoList = new ArrayList();
                }
                if (page == 1) {
                    TempleteListActivity.this.prjectInfoList.clear();
                }
                if (list != null && list.size() > 0) {
                    TempleteListActivity.this.prjectInfoList.addAll(list);
                }
                TempleteListActivity.this.creatorHomeAdapter.setData(TempleteListActivity.this.prjectInfoList);
                TempleteListActivity.this.creatorHomeAdapter.notifyDataSetChanged();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                TempleteListActivity.this.closeLoadingView();
                EditingUtils.log("getData onResponseFailure 1");
                TempleteListActivity.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.editmain.activity.TempleteListActivity.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        TempleteListActivity.this.closeLoadingView();
                        TempleteListActivity.this.page = 1;
                        TempleteListActivity.this.getData();
                    }
                });
            }
        });
    }

    public void initView() {
        setPlayBarVisibility(8);
        setTitleText(R.string.creator_templete);
        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.wanos.editmain.activity.TempleteListActivity.2
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return 1;
            }
        });
        this.binding.templeteList.setLayoutManager(gridLayoutManager);
        this.creatorHomeAdapter = new CreatorHomeAdapter(this, this.prjectInfoList);
        this.binding.templeteList.setAdapter(this.creatorHomeAdapter);
        this.binding.templeteRefreshLayout.setLoadMore(true);
        this.binding.templeteRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.editmain.activity.TempleteListActivity.3
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                System.out.println("initRefreshView onRefresh");
                TempleteListActivity.this.binding.templeteRefreshLayout.finishRefresh();
                TempleteListActivity.this.binding.templeteRefreshLayout.finishRefreshLoadMore();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                System.out.println("initRefreshView onRefreshLoadMore");
                TempleteListActivity.this.binding.templeteRefreshLayout.finishRefresh();
                TempleteListActivity.this.binding.templeteRefreshLayout.finishRefreshLoadMore();
                TempleteListActivity.this.getData();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseActivity
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        super.loginOrLogout(loginOrLogoutEvent);
        this.page = 1;
        getData();
    }
}
