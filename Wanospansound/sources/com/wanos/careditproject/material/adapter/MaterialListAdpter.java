package com.wanos.careditproject.material.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialListAdpter extends FragmentStateAdapter {
    private List<Long> hashCodes;
    private List<Fragment> mFragments;

    public MaterialListAdpter(FragmentManager fragmentManager, List<Fragment> list, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        ArrayList arrayList = new ArrayList();
        this.hashCodes = arrayList;
        this.mFragments = list;
        arrayList.clear();
        Iterator<Fragment> it = list.iterator();
        while (it.hasNext()) {
            this.hashCodes.add(Long.valueOf(it.next().hashCode()));
        }
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        List<Fragment> list = this.mFragments;
        if (list == null || i >= list.size()) {
            return null;
        }
        return this.mFragments.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Fragment> list = this.mFragments;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.hashCodes.get(i).longValue();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public boolean containsItem(long j) {
        return this.hashCodes.contains(Long.valueOf(j));
    }
}
