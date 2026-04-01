package com.wanos.careditproject.material.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MySecondAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MySecondAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mFragments = list;
    }

    public MySecondAdapter(FragmentManager fragmentManager, int i, List<Fragment> list) {
        super(fragmentManager, i);
        this.mFragments = list;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.mFragments.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mFragments.size();
    }
}
