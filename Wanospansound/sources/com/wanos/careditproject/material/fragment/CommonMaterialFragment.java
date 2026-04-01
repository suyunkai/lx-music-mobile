package com.wanos.careditproject.material.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoListBean;
import com.wanos.WanosCommunication.response.GetMaterialTypeListResponse;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.databinding.FragmentCommonMaterialBinding;
import com.wanos.careditproject.databinding.LayoutFirstTabTitleBinding;
import com.wanos.careditproject.material.adapter.MaterialListAdpter;
import com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter;
import com.wanos.media.R;
import com.wanos.media.base.BaseLazyFragment;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

/* JADX INFO: loaded from: classes3.dex */
public class CommonMaterialFragment extends BaseLazyFragment implements MultiLevelTreeAdapter.OnTreeAdapterListener {
    private static final String TAG = "wanos:[MaterialListFragment]";
    private FragmentCommonMaterialBinding binding;
    private List<MaterialTypeInfoBean> materialTypeList = new ArrayList();
    private int titleType;

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCommonMaterialBinding fragmentCommonMaterialBindingInflate = FragmentCommonMaterialBinding.inflate(getLayoutInflater());
        this.binding = fragmentCommonMaterialBindingInflate;
        return fragmentCommonMaterialBindingInflate.getRoot();
    }

    @Override // com.wanos.media.base.BaseLazyFragment
    public void lazyLoad() {
        requestData(this.titleType);
    }

    public void setTypeList(int i) {
        this.titleType = i;
    }

    public void requestData(int i) {
        this.materialTypeList.clear();
        if (i == 0 || i == 1) {
            getMusicOrSoundeffectFileList();
        } else if (i == 2) {
            getCollectFileList();
        } else {
            initMagicIndicator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMagicIndicator() {
        initTab();
        this.binding.commonMaterialVp.setUserInputEnabled(false);
        this.binding.commonMaterialVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialFragment.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
                CommonMaterialFragment.this.binding.commonMaterialMi.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                CommonMaterialFragment.this.binding.commonMaterialMi.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                super.onPageScrollStateChanged(i);
                CommonMaterialFragment.this.binding.commonMaterialMi.onPageScrollStateChanged(i);
            }
        });
    }

    private void initViewPager() {
        ArrayList arrayList = new ArrayList();
        if (this.materialTypeList.size() != 0) {
            for (int i = 0; i < this.materialTypeList.size(); i++) {
                List<MaterialTypeInfoBean> children = this.materialTypeList.get(i).getChildren();
                if (children == null || children.size() != 0) {
                    CommonMaterialTabFragment commonMaterialTabFragment = new CommonMaterialTabFragment();
                    commonMaterialTabFragment.setTypeList(this.titleType, children);
                    arrayList.add(commonMaterialTabFragment);
                } else {
                    CommonMaterialTabFragment commonMaterialTabFragment2 = new CommonMaterialTabFragment();
                    commonMaterialTabFragment2.setTypeList(this.titleType, i + 1);
                    arrayList.add(commonMaterialTabFragment2);
                }
            }
        }
        this.binding.commonMaterialVp.setAdapter(new MaterialListAdpter(getParentFragmentManager(), arrayList, getLifecycle()));
    }

    private void initTab() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        initViewPager();
        commonNavigator.setAdapter(new CommonNavigatorAdapter() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialFragment.2
            @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
            public int getCount() {
                if (CommonMaterialFragment.this.materialTypeList == null) {
                    return 0;
                }
                return CommonMaterialFragment.this.materialTypeList.size();
            }

            @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
            public IPagerTitleView getTitleView(Context context, final int i) {
                final CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                LayoutFirstTabTitleBinding layoutFirstTabTitleBindingInflate = LayoutFirstTabTitleBinding.inflate(CommonMaterialFragment.this.getLayoutInflater());
                commonPagerTitleView.setContentView(layoutFirstTabTitleBindingInflate.getRoot());
                layoutFirstTabTitleBindingInflate.tvTabTitle.setText(((MaterialTypeInfoBean) CommonMaterialFragment.this.materialTypeList.get(i)).getName());
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialFragment.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CommonMaterialFragment.this.binding.commonMaterialVp.setCurrentItem(i, false);
                    }
                });
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialFragment.2.2
                    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener
                    public void onEnter(int i2, int i3, float f, boolean z) {
                    }

                    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener
                    public void onLeave(int i2, int i3, float f, boolean z) {
                    }

                    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener
                    public void onSelected(int i2, int i3) {
                        commonPagerTitleView.setSelected(true);
                    }

                    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener
                    public void onDeselected(int i2, int i3) {
                        commonPagerTitleView.setSelected(false);
                    }
                });
                return commonPagerTitleView;
            }

            @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setColors(0);
                linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 3.0d));
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, 25.0d));
                linePagerIndicator.setMode(2);
                return linePagerIndicator;
            }
        });
        this.binding.commonMaterialMi.setNavigator(commonNavigator);
    }

    private void getCollectFileList() {
        for (int i = 1; i <= 2; i++) {
            MaterialTypeInfoBean materialTypeInfoBean = new MaterialTypeInfoBean();
            materialTypeInfoBean.setId(i);
            if (i == 1) {
                materialTypeInfoBean.setName(getString(R.string.material_title_music));
            } else {
                materialTypeInfoBean.setName(getString(R.string.material_title_sound_effect));
            }
            materialTypeInfoBean.setChildren(new ArrayList());
            this.materialTypeList.add(materialTypeInfoBean);
        }
        if (this.materialTypeList == null) {
            this.materialTypeList = new ArrayList();
        }
        initMagicIndicator();
    }

    private void getMusicOrSoundeffectFileList() {
        CreatorRetrofitUtil.getMaterialType(this.titleType + 1, new ResponseCallBack<GetMaterialTypeListResponse>(getContext()) { // from class: com.wanos.careditproject.material.fragment.CommonMaterialFragment.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialTypeListResponse getMaterialTypeListResponse) {
                MaterialTypeInfoListBean materialTypeInfoListBean = getMaterialTypeListResponse.data;
                if (materialTypeInfoListBean == null || materialTypeInfoListBean.getList().size() == 0 || CommonMaterialFragment.this.getActivity() == null) {
                    return;
                }
                CommonMaterialFragment.this.materialTypeList.addAll(materialTypeInfoListBean.getList());
                if (CommonMaterialFragment.this.materialTypeList == null) {
                    CommonMaterialFragment.this.materialTypeList = new ArrayList();
                }
                CommonMaterialFragment.this.initMagicIndicator();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                Log.i(CommonMaterialFragment.TAG, "请求素材库类型失败");
            }
        });
    }
}
