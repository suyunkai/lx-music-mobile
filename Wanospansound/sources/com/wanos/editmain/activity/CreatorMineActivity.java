package com.wanos.editmain.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.ActivityCreatorMineBinding;
import com.wanos.careditproject.ui.fragment.CreatorWorksFragment;
import com.wanos.editmain.fragment.CreatorProjectListFragment;
import com.wanos.media.base.WanosBaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorMineActivity extends WanosBaseActivity {
    private ActivityCreatorMineBinding binding;
    private String[] mTitles;

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.binding = ActivityCreatorMineBinding.inflate(getLayoutInflater());
        this.mTitles = new String[]{getString(R.string.create_mine_personal_projects), getString(R.string.create_mine_personal_works), getString(R.string.create_mine_collect_works)};
        setContentView(this.binding.getRoot());
        initView();
    }

    public void initView() {
        setPlayBarVisibility(8);
        setTitleText(R.string.create_my_space);
        this.binding.viewPager.setAdapter(new FragmentStateAdapter(this) { // from class: com.wanos.editmain.activity.CreatorMineActivity.1
            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int i) {
                if (i == 0) {
                    return new CreatorProjectListFragment(0);
                }
                if (i == 1) {
                    return CreatorMineActivity.this.createWorkFragment(1);
                }
                if (i != 2) {
                    return null;
                }
                return CreatorMineActivity.this.createWorkFragment(2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return CreatorMineActivity.this.mTitles.length;
            }
        });
        this.binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.editmain.activity.CreatorMineActivity.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                CreatorMineActivity.this.binding.tabLayout.getChildAt(i).setSelected(true);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.wanos.editmain.activity.CreatorMineActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m451xf00c64a3(view);
            }
        };
        for (String str : this.mTitles) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_creator_mine_tab_item, (ViewGroup) this.binding.tabLayout, false);
            textView.setOnClickListener(onClickListener);
            textView.setText(str);
            textView.setId(textView.hashCode());
            this.binding.tabLayout.addView(textView);
        }
        this.binding.viewPager.setUserInputEnabled(false);
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-editmain-activity-CreatorMineActivity, reason: not valid java name */
    /* synthetic */ void m451xf00c64a3(View view) {
        for (int i = 0; i < this.binding.tabLayout.getChildCount(); i++) {
            this.binding.tabLayout.getChildAt(i).setSelected(false);
        }
        view.setSelected(true);
        this.binding.viewPager.setCurrentItem(this.binding.tabLayout.indexOfChild(view), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Fragment createWorkFragment(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        CreatorWorksFragment creatorWorksFragment = new CreatorWorksFragment();
        creatorWorksFragment.setArguments(bundle);
        return creatorWorksFragment;
    }
}
