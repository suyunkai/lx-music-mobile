package com.wanos.media.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wanos.media.adapter.FragmentAdapter;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.CustomClick;
import com.wanos.media.viewmodel.SoundMaterialViewModel;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.DialogSoundMenuBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SoundMaterialDialog extends WanosBaseDialog<DialogSoundMenuBinding, SoundMaterialViewModel> {
    private static final String TAG = "SoundMaterialDialog";

    static /* synthetic */ boolean lambda$initObserve$1(View view) {
        return true;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected int initGravity() {
        return 80;
    }

    public static void showDialog(FragmentManager fragmentManager, String str, ZeroPageMode zeroPageMode) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof SoundMaterialDialog) {
            ((SoundMaterialDialog) fragmentFindFragmentByTag).dismiss();
        }
        SoundMaterialDialog soundMaterialDialog = new SoundMaterialDialog();
        Bundle bundle = new Bundle();
        bundle.putString("dialog_bg", str);
        bundle.putSerializable("zeroPageMode", zeroPageMode);
        soundMaterialDialog.setArguments(bundle);
        soundMaterialDialog.show(fragmentManager, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogSoundMenuBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        DialogSoundMenuBinding dialogSoundMenuBindingInflate = DialogSoundMenuBinding.inflate(layoutInflater, viewGroup, false);
        ViewGroup.LayoutParams layoutParams = dialogSoundMenuBindingInflate.clDialog.getLayoutParams();
        layoutParams.height = (int) (ScreenUtils.getScreenHeight() * 0.72f);
        dialogSoundMenuBindingInflate.clDialog.setLayoutParams(layoutParams);
        return dialogSoundMenuBindingInflate;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<SoundMaterialViewModel> initViewModel() {
        return SoundMaterialViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        ((SoundMaterialViewModel) this.viewModel).getItemData().observe(this, new Observer() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m596lambda$initObserve$2$comwanosmediaviewSoundMaterialDialog((List) obj);
            }
        });
        ((SoundMaterialViewModel) this.viewModel).getBackgroundImage().observe(this, new Observer() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m597lambda$initObserve$3$comwanosmediaviewSoundMaterialDialog((Drawable) obj);
            }
        });
        ((SoundMaterialViewModel) this.viewModel).getErrorState().observe(this, new Observer() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setErrorLayoutState(((Boolean) obj).booleanValue());
            }
        });
        ((SoundMaterialViewModel) this.viewModel).getLoadingState().observe(this, new Observer() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setLoadingLayoutState(((Boolean) obj).booleanValue());
            }
        });
        ((SoundMaterialViewModel) this.viewModel).getContentState().observe(this, new Observer() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setContentState(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$2$com-wanos-media-view-SoundMaterialDialog, reason: not valid java name */
    /* synthetic */ void m596lambda$initObserve$2$comwanosmediaviewSoundMaterialDialog(final List list) {
        if (this.binding == 0) {
            return;
        }
        Bundle arguments = getArguments();
        if (list == null || arguments == null || list.isEmpty()) {
            return;
        }
        ((DialogSoundMenuBinding) this.binding).vpTab.setAdapter(new FragmentAdapter(this, list, (ZeroPageMode) arguments.getSerializable("zeroPageMode")));
        new TabLayoutMediator(((DialogSoundMenuBinding) this.binding).tabSound, ((DialogSoundMenuBinding) this.binding).vpTab, true, false, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda5
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                tab.setText(((GetAudioInfoResp.TagGroupDTO) list.get(i)).getTagName());
            }
        }).attach();
        for (int i = 0; i < ((DialogSoundMenuBinding) this.binding).tabSound.getTabCount(); i++) {
            TabLayout.Tab tabAt = ((DialogSoundMenuBinding) this.binding).tabSound.getTabAt(i);
            if (tabAt != null) {
                tabAt.view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wanos.media.view.SoundMaterialDialog$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return SoundMaterialDialog.lambda$initObserve$1(view);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$3$com-wanos-media-view-SoundMaterialDialog, reason: not valid java name */
    /* synthetic */ void m597lambda$initObserve$3$comwanosmediaviewSoundMaterialDialog(Drawable drawable) {
        if (this.binding != 0) {
            ((DialogSoundMenuBinding) this.binding).ivBg.setImageDrawable(drawable);
            ((DialogSoundMenuBinding) this.binding).vBlurColor.setVisibility(8);
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogSoundMenuBinding) this.binding).btnFinish.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.SoundMaterialDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                SoundMaterialDialog.this.dismissAllowingStateLoss();
            }
        });
        ((DialogSoundMenuBinding) this.binding).layoutError.btnRetry.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.SoundMaterialDialog.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                ((SoundMaterialViewModel) SoundMaterialDialog.this.viewModel).initSoundMaterialData();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments == null || this.binding == 0) {
            return;
        }
        ((SoundMaterialViewModel) this.viewModel).setBackgroundImage(arguments.getString("dialog_bg"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorLayoutState(boolean z) {
        if (this.binding == 0) {
            return;
        }
        ((DialogSoundMenuBinding) this.binding).layoutError.getRoot().setVisibility(z ? 0 : 4);
        ((DialogSoundMenuBinding) this.binding).layoutError.tvErrorMessage.setText(R.string.zero_error_empty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoadingLayoutState(boolean z) {
        if (this.binding == 0) {
            return;
        }
        ((DialogSoundMenuBinding) this.binding).layoutLoading.getRoot().setVisibility(z ? 0 : 4);
        ((DialogSoundMenuBinding) this.binding).layoutLoading.tvLoading.setTextColor(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentState(boolean z) {
        if (this.binding == 0) {
            return;
        }
        ((DialogSoundMenuBinding) this.binding).tabSound.setVisibility(z ? 0 : 4);
        ((DialogSoundMenuBinding) this.binding).vpTab.setVisibility(z ? 0 : 4);
        ((DialogSoundMenuBinding) this.binding).layoutError.tvErrorMessage.setTextColor(-1);
    }
}
