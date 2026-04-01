package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.adapter.HelpAdapter;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.util.CustomClick;
import com.wanos.media.viewmodel.HelpDialogViewModel;
import com.wanos.media.widget.DummyCircleNavigator;
import com.wanos.media.zero_p.databinding.DialogHelpBinding;

/* JADX INFO: loaded from: classes3.dex */
public class HelpDialog extends WanosBaseDialog<DialogHelpBinding, HelpDialogViewModel> {
    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
    }

    public static void show(Fragment fragment) {
        new HelpDialog().show(fragment.getChildFragmentManager(), "HelpDialog");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogHelpBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogHelpBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<HelpDialogViewModel> initViewModel() {
        return HelpDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initAdapter(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        HelpAdapter helpAdapter = new HelpAdapter(getContext());
        ((DialogHelpBinding) this.binding).viewPager.setAdapter(helpAdapter);
        ((DialogHelpBinding) this.binding).indicator.setNavigator(new DummyCircleNavigator(getContext(), helpAdapter.getItemCount()));
        ((DialogHelpBinding) this.binding).viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.media.view.HelpDialog.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                if (HelpDialog.this.binding == null) {
                    return;
                }
                ((DialogHelpBinding) HelpDialog.this.binding).indicator.onPageSelected(i);
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogHelpBinding) this.binding).ivDialogBack.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.HelpDialog.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                HelpDialog.this.dismiss();
            }
        });
        ((DialogHelpBinding) this.binding).title.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.HelpDialog.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                HelpDialog.this.dismiss();
            }
        });
    }
}
