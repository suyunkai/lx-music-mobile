package com.wanos.media.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.wanos.media.entity.ZeroMeTableEntity;
import com.wanos.media.view.RelaxCollectListFragment;

/* JADX INFO: loaded from: classes3.dex */
public class MeThemeTableAdapter extends FragmentStateAdapter {
    private final ZeroMeTableEntity[] mTableData;

    public MeThemeTableAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, ZeroMeTableEntity[] zeroMeTableEntityArr) {
        super(fragmentManager, lifecycle);
        this.mTableData = zeroMeTableEntityArr;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        return RelaxCollectListFragment.newInstance(this.mTableData[i].getTableId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mTableData.length;
    }
}
