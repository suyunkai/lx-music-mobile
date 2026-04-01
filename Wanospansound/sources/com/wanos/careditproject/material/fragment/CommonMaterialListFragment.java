package com.wanos.careditproject.material.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialDataInfo;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.WanosCommunication.response.GetMaterialListResponse;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.databinding.FragmentMaterialListBinding;
import com.wanos.careditproject.event.CollectDataInfoEvent;
import com.wanos.careditproject.event.MaterialPlayEvent;
import com.wanos.careditproject.event.SelectedStatusEvent;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.material.SearchActivity;
import com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter;
import com.wanos.media.base.BaseLazyFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class CommonMaterialListFragment extends BaseLazyFragment implements MultiLevelTreeAdapter.OnTreeAdapterListener {
    private static final String ARG_PARAM1 = "titleType";
    private static final String TAG = "wanos:[MaterialListFragment]";
    private MultiLevelTreeAdapter adapter;
    private FragmentMaterialListBinding binding;
    private MaterialPlayEvent materialPlayEvent;
    private int mid;
    private final int mPageSize = 200;
    private int mPage = 1;
    private List<MaterialTypeInfoBean> materialDataList = new ArrayList();
    private int titleType = -1;
    private String keyword = "";
    private HashMap<Integer, Integer> listSizeMap = new HashMap<>();

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentMaterialListBinding fragmentMaterialListBindingInflate = FragmentMaterialListBinding.inflate(getLayoutInflater());
        this.binding = fragmentMaterialListBindingInflate;
        ((SimpleItemAnimator) fragmentMaterialListBindingInflate.materialRecyclerList.getItemAnimator()).setSupportsChangeAnimations(false);
        MultiLevelTreeAdapter multiLevelTreeAdapter = new MultiLevelTreeAdapter(getActivity(), this.materialDataList);
        this.adapter = multiLevelTreeAdapter;
        multiLevelTreeAdapter.setActivityFlag(this.titleType);
        this.adapter.setOnTreeAdapterListener(this);
        this.binding.materialRecyclerList.setAdapter(this.adapter);
        int i = this.titleType;
        if (i != -1) {
            if (i == 5) {
                updatePlayerStatus(((SearchActivity) getActivity()).getMaterialPlayEvent());
            } else {
                updatePlayerStatus(((MaterialListActivity) getActivity()).getMaterialPlayEvent());
            }
        }
        return this.binding.getRoot();
    }

    @Override // com.wanos.media.base.BaseLazyFragment
    public void lazyLoad() {
        showLoadingView(-1, R.color.loading_text_color, "");
        requestData(this.titleType);
    }

    public void setTypeList(int i, int i2) {
        this.titleType = i;
        this.mid = i2;
    }

    public void requestData(int i) {
        this.materialDataList.clear();
        if (i == 0 || i == 1) {
            getMusicOrSoundeffectList(false, this.mid);
            return;
        }
        if (i == 2) {
            getCollectMaterialList(false, this.mid);
            return;
        }
        if (!TextUtils.isEmpty(this.keyword) && i == 5) {
            showLoadingView(-1, R.color.loading_text_color, "");
            getSearchMaterialList(this.keyword);
        } else {
            this.adapter.setData(this.materialDataList);
            this.adapter.notifyDataSetChanged();
            refreshListUI(true);
        }
    }

    private void getCollectMaterialList(final boolean z, int i) {
        CreatorRetrofitUtil.getCollectMaterialList(i, this.mPage, 200, new ResponseCallBack<GetMaterialListResponse>(getActivity()) { // from class: com.wanos.careditproject.material.fragment.CommonMaterialListFragment.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialListResponse getMaterialListResponse) {
                CommonMaterialListFragment.this.materialDataList.clear();
                if (CommonMaterialListFragment.this.addMaterialDataToList(z, null, getMaterialListResponse.data.getList()) == null) {
                    CommonMaterialListFragment.this.adapter.setData(CommonMaterialListFragment.this.materialDataList);
                    CommonMaterialListFragment.this.adapter.notifyDataSetChanged();
                    CommonMaterialListFragment.this.refreshListUI(true);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtils.showShort(str);
                CommonMaterialListFragment.this.refreshListUI(true);
            }
        });
    }

    private void getMusicOrSoundeffectList(final boolean z, int i) {
        CreatorRetrofitUtil.getMaterialList(i, this.titleType + 1, this.mPage, 200, new ResponseCallBack<GetMaterialListResponse>(getActivity()) { // from class: com.wanos.careditproject.material.fragment.CommonMaterialListFragment.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialListResponse getMaterialListResponse) {
                if (CommonMaterialListFragment.this.addMaterialDataToList(z, null, getMaterialListResponse.data.getList()) == null) {
                    CommonMaterialListFragment.this.adapter.setData(CommonMaterialListFragment.this.materialDataList);
                    CommonMaterialListFragment.this.adapter.notifyDataSetChanged();
                    CommonMaterialListFragment.this.refreshListUI(true);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtils.showShort(str);
                CommonMaterialListFragment.this.refreshListUI(true);
            }
        });
    }

    public void requestSearchData(String str) {
        this.keyword = str;
        requestData(this.titleType);
    }

    private void getSearchMaterialList(String str) {
        CreatorRetrofitUtil.getSearchMaterialList(str, this.mPage, 200, new ResponseCallBack<GetMaterialListResponse>(getActivity()) { // from class: com.wanos.careditproject.material.fragment.CommonMaterialListFragment.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialListResponse getMaterialListResponse) {
                if (CommonMaterialListFragment.this.addMaterialDataToList(false, null, getMaterialListResponse.data.getList()) == null) {
                    CommonMaterialListFragment.this.adapter.setData(CommonMaterialListFragment.this.materialDataList);
                    CommonMaterialListFragment.this.adapter.notifyDataSetChanged();
                    CommonMaterialListFragment.this.refreshListUI(true);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str2) {
                ToastUtils.showShort(str2);
                CommonMaterialListFragment.this.refreshListUI(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MaterialTypeInfoBean addMaterialDataToList(boolean z, MaterialTypeInfoBean materialTypeInfoBean, List<MaterialDataInfo> list) {
        if (materialTypeInfoBean != null && this.listSizeMap.get(Integer.valueOf(materialTypeInfoBean.getId())) != null && !z && this.listSizeMap.get(Integer.valueOf(materialTypeInfoBean.getId())).intValue() == list.size()) {
            Log.i("zt", "该文件下素材数量相同");
            return null;
        }
        List<MaterialTypeInfoBean> children = materialTypeInfoBean != null ? materialTypeInfoBean.getChildren() : null;
        if (children == null || children.size() == 0) {
            children = new ArrayList<>();
        } else {
            children.removeIf(new Predicate() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialListFragment$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CommonMaterialListFragment.lambda$addMaterialDataToList$0((MaterialTypeInfoBean) obj);
                }
            });
        }
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                MaterialTypeInfoBean materialTypeInfoBean2 = new MaterialTypeInfoBean();
                MaterialDataInfo materialDataInfo = list.get(i);
                materialTypeInfoBean2.setViewType(2);
                materialTypeInfoBean2.setType(materialDataInfo.getType());
                materialTypeInfoBean2.setM_id(materialDataInfo.getId());
                materialTypeInfoBean2.setSampleNum(materialDataInfo.getSampleNum());
                materialTypeInfoBean2.setChannel(materialDataInfo.getChannel());
                materialTypeInfoBean2.setM_name(materialDataInfo.getName());
                materialTypeInfoBean2.setUrlSrc(materialDataInfo.getUrlSrc());
                materialTypeInfoBean2.setTimeLen(materialDataInfo.getTimeLen());
                materialTypeInfoBean2.setFormat(materialDataInfo.getFormat());
                materialTypeInfoBean2.setUrlSrcWanos(materialDataInfo.getUrlSrcWanos());
                materialTypeInfoBean2.setTypeId(materialDataInfo.getTypeId());
                materialTypeInfoBean2.setSampleTate(materialDataInfo.getSampleTate());
                materialTypeInfoBean2.setCollect(materialDataInfo.isCollect());
                materialTypeInfoBean2.setUserId(materialDataInfo.getUserId());
                materialTypeInfoBean2.setM_parentId(materialDataInfo.getParentId());
                materialTypeInfoBean2.setIsWanosAudio(materialDataInfo.getIsWanosAudio());
                materialTypeInfoBean2.setUrlSize(materialDataInfo.getUrlSize());
                materialTypeInfoBean2.setUrlWanosSize(materialDataInfo.getFileSize());
                materialTypeInfoBean2.setSampleRate(materialDataInfo.getSampleRate());
                materialTypeInfoBean2.setBallRouteMap(materialDataInfo.getBallRouteMap());
                materialTypeInfoBean2.setTransitionWanosStatus(materialDataInfo.getTransitionWanosStatus());
                materialTypeInfoBean2.setCreateTime(materialDataInfo.getCreateTime());
                materialTypeInfoBean2.setUpdateTime(materialDataInfo.getUpdateTime());
                if (materialTypeInfoBean == null) {
                    this.materialDataList.add(materialTypeInfoBean2);
                } else {
                    children.add(materialTypeInfoBean2);
                }
            }
            if (materialTypeInfoBean != null) {
                this.listSizeMap.put(Integer.valueOf(materialTypeInfoBean.getId()), Integer.valueOf(list.size()));
                materialTypeInfoBean.setChildren(children);
                return materialTypeInfoBean;
            }
        }
        return null;
    }

    static /* synthetic */ boolean lambda$addMaterialDataToList$0(MaterialTypeInfoBean materialTypeInfoBean) {
        return materialTypeInfoBean.getViewType() == 2;
    }

    @Override // com.wanos.media.base.BaseLazyFragment, com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.adapter.stopGetTaskState();
        super.onDestroyView();
        this.listSizeMap.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePlayerStatus(MaterialPlayEvent materialPlayEvent) {
        this.materialPlayEvent = materialPlayEvent;
        this.adapter.updatePlayStatus(materialPlayEvent.getStatus(), materialPlayEvent.getMaterialDataInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void SelectedStatusEvent(SelectedStatusEvent selectedStatusEvent) {
        if (this.adapter == null || selectedStatusEvent.getType() == this.titleType) {
            return;
        }
        this.adapter.setSelectId(-1);
        this.adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void CancelCollectDataEvent(CollectDataInfoEvent collectDataInfoEvent) {
        int titleType = collectDataInfoEvent.getTitleType();
        collectDataInfoEvent.getCurBean();
        int i = this.titleType;
        if (i == 2) {
            getCollectMaterialList(true, this.mid);
            return;
        }
        if (i == 0 || i == 1) {
            if (titleType == 2 || titleType == 5) {
                this.materialDataList.clear();
                getMusicOrSoundeffectList(true, this.mid);
            }
        }
    }

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void onCollectChange(int i, int i2, MaterialTypeInfoBean materialTypeInfoBean) {
        EventBus.getDefault().post(new CollectDataInfoEvent(i, i2, materialTypeInfoBean));
    }

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void closeLoadingListener() {
        closeLoadingView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshListUI(boolean z) {
        if (z) {
            closeLoadingView();
        }
        if (this.materialDataList.size() == 0) {
            this.binding.materialRecyclerList.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_str);
        } else {
            this.binding.materialRecyclerList.setVisibility(0);
            this.binding.emptyView.getRoot().setVisibility(8);
        }
    }
}
