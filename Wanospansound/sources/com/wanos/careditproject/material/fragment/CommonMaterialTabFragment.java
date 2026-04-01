package com.wanos.careditproject.material.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.careditproject.databinding.FragmentCommonMaterialTabBinding;
import com.wanos.careditproject.databinding.LayoutSecondTabTitleBinding;
import com.wanos.careditproject.material.adapter.MaterialListAdpter;
import com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter;
import com.wanos.media.base.BaseLazyFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

/* JADX INFO: loaded from: classes3.dex */
public class CommonMaterialTabFragment extends BaseLazyFragment implements MultiLevelTreeAdapter.OnTreeAdapterListener {
    private FragmentCommonMaterialTabBinding binding;
    private int titleType;
    private List<MaterialTypeInfoBean> childrenList = new ArrayList();
    private HashMap<Integer, Integer> listSizeMap = new HashMap<>();
    private int collectId = -1;

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCommonMaterialTabBinding fragmentCommonMaterialTabBindingInflate = FragmentCommonMaterialTabBinding.inflate(getLayoutInflater());
        this.binding = fragmentCommonMaterialTabBindingInflate;
        return fragmentCommonMaterialTabBindingInflate.getRoot();
    }

    @Override // com.wanos.media.base.BaseLazyFragment
    public void lazyLoad() {
        requestData();
    }

    public void setTypeList(int i, List<MaterialTypeInfoBean> list) {
        this.titleType = i;
        this.childrenList = list;
    }

    public void setTypeList(int i, int i2) {
        this.titleType = i;
        this.collectId = i2;
    }

    public void requestData() {
        this.binding.commonMaterialMi.setVisibility(this.childrenList.size() == 0 ? 8 : 0);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        initTab();
        this.binding.commonMaterialVp.setUserInputEnabled(false);
        this.binding.commonMaterialVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialTabFragment.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
                CommonMaterialTabFragment.this.binding.commonMaterialMi.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                CommonMaterialTabFragment.this.binding.commonMaterialMi.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                super.onPageScrollStateChanged(i);
                CommonMaterialTabFragment.this.binding.commonMaterialMi.onPageScrollStateChanged(i);
            }
        });
    }

    private void initViewPager() {
        ArrayList arrayList = new ArrayList();
        List<MaterialTypeInfoBean> list = this.childrenList;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < this.childrenList.size(); i++) {
                CommonMaterialListFragment commonMaterialListFragment = new CommonMaterialListFragment();
                commonMaterialListFragment.setTypeList(this.titleType, this.childrenList.get(i).getId());
                arrayList.add(commonMaterialListFragment);
            }
        } else {
            CommonMaterialListFragment commonMaterialListFragment2 = new CommonMaterialListFragment();
            commonMaterialListFragment2.setTypeList(this.titleType, this.collectId);
            arrayList.add(commonMaterialListFragment2);
        }
        this.binding.commonMaterialVp.setAdapter(new MaterialListAdpter(getChildFragmentManager(), arrayList, getLifecycle()));
    }

    private void initTab() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        initViewPager();
        commonNavigator.setAdapter(new AnonymousClass2());
        this.binding.commonMaterialMi.setNavigator(commonNavigator);
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.material.fragment.CommonMaterialTabFragment$2, reason: invalid class name */
    class AnonymousClass2 extends CommonNavigatorAdapter {
        AnonymousClass2() {
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public int getCount() {
            if (CommonMaterialTabFragment.this.childrenList == null) {
                return 0;
            }
            return CommonMaterialTabFragment.this.childrenList.size();
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerTitleView getTitleView(Context context, final int i) {
            final CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
            LayoutSecondTabTitleBinding layoutSecondTabTitleBindingInflate = LayoutSecondTabTitleBinding.inflate(CommonMaterialTabFragment.this.getLayoutInflater());
            commonPagerTitleView.setContentView(layoutSecondTabTitleBindingInflate.getRoot());
            layoutSecondTabTitleBindingInflate.tvTabTitle.setText(((MaterialTypeInfoBean) CommonMaterialTabFragment.this.childrenList.get(i)).getName());
            commonPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialTabFragment$2$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m380x41eaee5d(i, view);
                }
            });
            commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() { // from class: com.wanos.careditproject.material.fragment.CommonMaterialTabFragment.2.1
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

        /* JADX INFO: renamed from: lambda$getTitleView$0$com-wanos-careditproject-material-fragment-CommonMaterialTabFragment$2, reason: not valid java name */
        /* synthetic */ void m380x41eaee5d(int i, View view) {
            CommonMaterialTabFragment.this.binding.commonMaterialVp.setCurrentItem(i, false);
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
    }

    @Override // com.wanos.media.base.BaseLazyFragment, com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.listSizeMap.clear();
    }

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void closeLoadingListener() {
        closeLoadingView();
    }
}
