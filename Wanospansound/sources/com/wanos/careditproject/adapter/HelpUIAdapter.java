package com.wanos.careditproject.adapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.wanos.careditproject.view.dialog.HelpUIFragment;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HelpUIAdapter extends FragmentStateAdapter {
    List<HelpUIBean> mList;

    public HelpUIAdapter(Fragment fragment, List<HelpUIBean> list) {
        super(fragment);
        this.mList = list;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        List<HelpUIBean> list = this.mList;
        if (list != null && i < list.size()) {
            HelpUIBean helpUIBean = this.mList.get(i);
            return HelpUIFragment.newInstance(helpUIBean.resId, helpUIBean.title, helpUIBean.desc);
        }
        return HelpUIFragment.newInstance(0, "", "");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public static class HelpUIBean {
        public String desc;
        public int resId;
        public String title;

        public HelpUIBean(int i, String str, String str2) {
            this.resId = i;
            this.title = str;
            this.desc = str2;
        }
    }
}
