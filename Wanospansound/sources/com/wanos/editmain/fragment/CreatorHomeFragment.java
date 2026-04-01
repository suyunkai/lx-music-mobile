package com.wanos.editmain.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectListResponse;
import com.wanos.careditproject.databinding.FragmentCreatorHomeBinding;
import com.wanos.careditproject.databinding.LayoutCreatorHomeRecyclerviewHeaderBinding;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.editmain.activity.LiveHouseListActivity;
import com.wanos.editmain.activity.TempleteListActivity;
import com.wanos.editmain.adapter.CreatorHomeAdapter;
import com.wanos.editmain.dialog.NewProjectDialog;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeFragment extends WanosBaseFragment implements View.OnClickListener {
    private FragmentCreatorHomeBinding binding;
    private CreatorHomeAdapter creatorHomeAdapter;
    private LayoutCreatorHomeRecyclerviewHeaderBinding layoutCreatorHomeRecyclerviewHeaderBinding;
    private List<ProjectInfo> prjectInfoList = new ArrayList();
    private int pageSize = 10;

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCreatorHomeBinding fragmentCreatorHomeBindingInflate = FragmentCreatorHomeBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = fragmentCreatorHomeBindingInflate;
        RecyclerView root = fragmentCreatorHomeBindingInflate.getRoot();
        this.layoutCreatorHomeRecyclerviewHeaderBinding = LayoutCreatorHomeRecyclerviewHeaderBinding.inflate(layoutInflater, viewGroup, false);
        initData();
        initView();
        initListener();
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        EditingUtils.log("create onHiddenChanged 0");
        super.onHiddenChanged(z);
        if (z) {
            EditingUtils.log("create onHiddenChanged 1");
            if (this.creatorHomeAdapter != null) {
                EditingUtils.log("create onHiddenChanged 2");
                this.creatorHomeAdapter.stopPlayer();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CreatorHomeAdapter creatorHomeAdapter = this.creatorHomeAdapter;
        if (creatorHomeAdapter != null) {
            creatorHomeAdapter.stopPlayer();
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        EditingUtils.log("create onResume");
        super.onResume();
        initData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        CreatorHomeAdapter creatorHomeAdapter = this.creatorHomeAdapter;
        if (creatorHomeAdapter != null) {
            creatorHomeAdapter.destroy();
        }
        super.onDestroy();
    }

    public void initData() {
        getData();
    }

    public void initListener() {
        this.layoutCreatorHomeRecyclerviewHeaderBinding.cdTopOne.setOnClickListener(this);
        this.layoutCreatorHomeRecyclerviewHeaderBinding.cdTopTwo.setOnClickListener(this);
        this.layoutCreatorHomeRecyclerviewHeaderBinding.cdTopThree.setOnClickListener(this);
        this.layoutCreatorHomeRecyclerviewHeaderBinding.cdTopFour.setOnClickListener(this);
        this.layoutCreatorHomeRecyclerviewHeaderBinding.tvTempleteAll.setOnClickListener(this);
    }

    public void getData() {
        CreatorRetrofitUtil.getWorksTempleteList(1, this.pageSize, new ResponseCallBack<EditProjectListResponse>(getActivity()) { // from class: com.wanos.editmain.fragment.CreatorHomeFragment.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectListResponse editProjectListResponse) {
                if (CreatorHomeFragment.this.isVisible()) {
                    CreatorHomeFragment.this.closeLoadingView();
                    List<ProjectInfo> list = editProjectListResponse.data.getList();
                    int page = editProjectListResponse.data.getPage();
                    if (CreatorHomeFragment.this.prjectInfoList == null) {
                        CreatorHomeFragment.this.prjectInfoList = new ArrayList();
                    }
                    if (page == 1) {
                        CreatorHomeFragment.this.prjectInfoList.clear();
                        if (list != null && list.size() > 0) {
                            CreatorHomeFragment.this.prjectInfoList.addAll(list);
                        }
                        CreatorHomeFragment.this.creatorHomeAdapter.setData(CreatorHomeFragment.this.prjectInfoList);
                        CreatorHomeFragment.this.creatorHomeAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                EditingUtils.log("getData onResponseFailure 1");
                CreatorHomeFragment.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.editmain.fragment.CreatorHomeFragment.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CreatorHomeFragment.this.closeLoadingView();
                        CreatorHomeFragment.this.getData();
                    }
                });
            }
        });
    }

    public void initView() {
        initRecyclerView();
        Util.setTextWeight(this.layoutCreatorHomeRecyclerviewHeaderBinding.tvTempleteAll, 500);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        final int spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.wanos.editmain.fragment.CreatorHomeFragment.2
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (i == 0) {
                    return spanCount;
                }
                return 1;
            }
        });
        this.binding.rcCreatorHome.setLayoutManager(gridLayoutManager);
        this.creatorHomeAdapter = new CreatorHomeAdapter(getActivity(), this.prjectInfoList);
        this.binding.rcCreatorHome.setAdapter(this.creatorHomeAdapter);
        this.creatorHomeAdapter.setHeaderView(this.layoutCreatorHomeRecyclerviewHeaderBinding.getRoot());
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        super.loginOrLogout(loginOrLogoutEvent);
        getData();
    }

    protected void stopOtherAudio() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || !MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            return;
        }
        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cd_top_one) {
            if (!UserInfoUtil.isLogin()) {
                if (getActivity() == null || !(getActivity() instanceof WanosBaseActivity)) {
                    return;
                }
                ((WanosBaseActivity) getActivity()).showLoginDialog();
                return;
            }
            stopOtherAudio();
            new NewProjectDialog().show(getParentFragmentManager(), "newProject");
            return;
        }
        if (id == R.id.cd_top_three) {
            if (!UserInfoUtil.isLogin()) {
                if (getActivity() == null || !(getActivity() instanceof WanosBaseActivity)) {
                    return;
                }
                ((WanosBaseActivity) getActivity()).showLoginDialog();
                return;
            }
            stopOtherAudio();
            return;
        }
        if (id == R.id.cd_top_two) {
            stopOtherAudio();
            return;
        }
        if (id == R.id.cd_top_four) {
            LiveHouseListActivity.build(getContext());
        } else if (id == R.id.tv_templete_all) {
            stopOtherAudio();
            startActivity(new Intent(getContext(), (Class<?>) TempleteListActivity.class));
        }
    }
}
