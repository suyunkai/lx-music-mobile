package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.wanos.careditproject.databinding.LayoutHelpItemBinding;
import com.wanos.commonlibrary.utils.GlideUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HelpUIFragment extends Fragment {
    public static final String HELP_DESC = "HELP_DESC";
    public static final String HELP_IMAGE = "HELP_IMAGE";
    public static final String HELP_TITLE = "HELP_TITLE";
    protected LayoutHelpItemBinding binding;

    public static HelpUIFragment newInstance(int i, String str, String str2) {
        HelpUIFragment helpUIFragment = new HelpUIFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(HELP_IMAGE, i);
        bundle.putString(HELP_TITLE, str);
        bundle.putString(HELP_DESC, str2);
        helpUIFragment.setArguments(bundle);
        return helpUIFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = LayoutHelpItemBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        GlideUtil.setImageGifData(getArguments().getInt(HELP_IMAGE), this.binding.ivItem);
        this.binding.tvTitle.setText(getArguments().getString(HELP_TITLE));
        this.binding.tvDesc.setText(getArguments().getString(HELP_DESC));
    }
}
