package com.wanos.media.adapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.ZeroAudioEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.view.SoundMenuFragment;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentAdapter extends FragmentStateAdapter {
    private final List<GetAudioInfoResp.TagGroupDTO> data;
    private final List<ZeroAudioEntity.TagsEntity> mData;
    private final ZeroPageMode zeroPageMode;

    public FragmentAdapter(Fragment fragment, List<GetAudioInfoResp.TagGroupDTO> list, ZeroPageMode zeroPageMode) {
        super(fragment);
        this.data = list;
        this.mData = null;
        this.zeroPageMode = zeroPageMode;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        return SoundMenuFragment.getInstance(this.data.get(i), this.zeroPageMode);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }
}
