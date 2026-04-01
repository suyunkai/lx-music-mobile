package com.wanos.editmain.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.careditproject.adapter.LiveHouseDifficultAdapter;
import com.wanos.careditproject.adapter.LiveHouseTypeAdapter;
import com.wanos.careditproject.databinding.FragmentLiveHouseBinding;
import com.wanos.editmain.adapter.LiveHouseListAdapter;
import com.wanos.media.base.WanosBaseFragment;

/* JADX INFO: loaded from: classes3.dex */
public class LiveHouseFragment extends WanosBaseFragment {
    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentLiveHouseBinding fragmentLiveHouseBindingInflate = FragmentLiveHouseBinding.inflate(layoutInflater, viewGroup, false);
        initView(fragmentLiveHouseBindingInflate);
        return fragmentLiveHouseBindingInflate.getRoot();
    }

    private void initView(FragmentLiveHouseBinding fragmentLiveHouseBinding) {
        fragmentLiveHouseBinding.rvClass.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        fragmentLiveHouseBinding.rvClass.setAdapter(new LiveHouseTypeAdapter());
        fragmentLiveHouseBinding.rvRank.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        fragmentLiveHouseBinding.rvRank.setAdapter(new LiveHouseDifficultAdapter());
        fragmentLiveHouseBinding.rvLiveHouse.setLayoutManager(new GridLayoutManager(getContext(), 5));
        fragmentLiveHouseBinding.rvLiveHouse.setAdapter(new LiveHouseListAdapter(getContext(), null));
    }

    @Override // androidx.fragment.app.Fragment
    public void setInitialSavedState(Fragment.SavedState savedState) {
        super.setInitialSavedState(savedState);
    }
}
