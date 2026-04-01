package com.wanos.editmain.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.ActivityCreatorCommunityBinding;
import com.wanos.careditproject.ui.fragment.CreatorWorksFragment;
import com.wanos.careditproject.view.widget.CreatorPagerTitleView;
import com.wanos.media.base.WanosBaseActivity;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorCommunityActivity extends WanosBaseActivity {
    private ActivityCreatorCommunityBinding binding;
    private List<String> mTitleList = new ArrayList();
    List<Fragment> fragments = new ArrayList();

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityCreatorCommunityBinding activityCreatorCommunityBindingInflate = ActivityCreatorCommunityBinding.inflate(getLayoutInflater());
        this.binding = activityCreatorCommunityBindingInflate;
        setContentView(activityCreatorCommunityBindingInflate.getRoot());
        initView();
    }

    public void initView() {
        setPlayBarVisibility(8);
        setTitleBarVisibility(8);
        this.binding.cTitleBar.tvTitle.setText(R.string.creator_community);
        this.mTitleList.add(getString(R.string.creator_community_picks));
        this.mTitleList.add(getString(R.string.creator_community_recommend));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new AnonymousClass1());
        this.binding.cTab.setNavigator(commonNavigator);
        if (this.fragments.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", 3);
            CreatorWorksFragment creatorWorksFragment = new CreatorWorksFragment();
            creatorWorksFragment.setArguments(bundle);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("type", 4);
            CreatorWorksFragment creatorWorksFragment2 = new CreatorWorksFragment();
            creatorWorksFragment2.setArguments(bundle2);
            this.fragments.add(creatorWorksFragment);
            this.fragments.add(creatorWorksFragment2);
        }
        this.binding.cPager.setAdapter(new FragmentStateAdapter(this) { // from class: com.wanos.editmain.activity.CreatorCommunityActivity.2
            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int i) {
                return CreatorCommunityActivity.this.fragments.get(i);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return CreatorCommunityActivity.this.fragments.size();
            }
        });
        this.binding.cPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.editmain.activity.CreatorCommunityActivity.3
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                CreatorCommunityActivity.this.binding.cTab.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                CreatorCommunityActivity.this.binding.cTab.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                CreatorCommunityActivity.this.binding.cTab.onPageScrollStateChanged(i);
            }
        });
        this.binding.cTitleBar.titleLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.editmain.activity.CreatorCommunityActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m449xa6720e97(view);
            }
        });
    }

    /* JADX INFO: renamed from: com.wanos.editmain.activity.CreatorCommunityActivity$1, reason: invalid class name */
    class AnonymousClass1 extends CommonNavigatorAdapter {
        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerIndicator getIndicator(Context context) {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public int getCount() {
            return CreatorCommunityActivity.this.mTitleList.size();
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerTitleView getTitleView(Context context, final int i) {
            CreatorPagerTitleView creatorPagerTitleView = new CreatorPagerTitleView(context);
            creatorPagerTitleView.setNormalColor(CreatorCommunityActivity.this.getColor(R.color.text_tab_click));
            creatorPagerTitleView.setSelectedColor(CreatorCommunityActivity.this.getColor(com.wanos.media.R.color.text_color));
            creatorPagerTitleView.setText((String) CreatorCommunityActivity.this.mTitleList.get(i));
            creatorPagerTitleView.setPadding(CreatorCommunityActivity.this.getResources().getDimensionPixelOffset(R.dimen.creator_tab_padding), 0, CreatorCommunityActivity.this.getResources().getDimensionPixelOffset(R.dimen.creator_tab_padding), 0);
            creatorPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.editmain.activity.CreatorCommunityActivity$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m450x1fdd0592(i, view);
                }
            });
            return creatorPagerTitleView;
        }

        /* JADX INFO: renamed from: lambda$getTitleView$0$com-wanos-editmain-activity-CreatorCommunityActivity$1, reason: not valid java name */
        /* synthetic */ void m450x1fdd0592(int i, View view) {
            CreatorCommunityActivity.this.binding.cPager.setCurrentItem(i, false);
        }
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-editmain-activity-CreatorCommunityActivity, reason: not valid java name */
    /* synthetic */ void m449xa6720e97(View view) {
        onBackPressed();
    }
}
