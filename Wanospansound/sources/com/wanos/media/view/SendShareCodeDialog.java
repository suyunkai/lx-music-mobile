package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.entity.PageState;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import com.wanos.media.viewmodel.ViewModeBundleFactory;
import com.wanos.media.zero_p.databinding.DialogSendShareCodeBinding;

/* JADX INFO: loaded from: classes3.dex */
public class SendShareCodeDialog extends WanosBaseDialog<DialogSendShareCodeBinding, SendShareCodeViewModel> {
    private static final String TAG = "SendShareCodeDialog";

    public static void showSendCode(FragmentManager fragmentManager, long j) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof SendShareCodeDialog) {
            ((SendShareCodeDialog) fragmentFindFragmentByTag).dismiss();
        }
        SendShareCodeDialog sendShareCodeDialog = new SendShareCodeDialog();
        Bundle bundle = new Bundle();
        bundle.putString(SendShareCodeViewModel.KEY_THEME_ID, j + "");
        sendShareCodeDialog.setArguments(bundle);
        sendShareCodeDialog.show(fragmentManager, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogSendShareCodeBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogSendShareCodeBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<SendShareCodeViewModel> initViewModel() {
        return SendShareCodeViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModeBundleFactory(getArguments());
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        ((SendShareCodeViewModel) this.viewModel).themeShareCode.observe(this, new Observer() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m588lambda$initObserve$0$comwanosmediaviewSendShareCodeDialog((String) obj);
            }
        });
        ((SendShareCodeViewModel) this.viewModel).toastMessage.observe(this, new Observer() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SendShareCodeDialog.lambda$initObserve$1((String) obj);
            }
        });
        ((SendShareCodeViewModel) this.viewModel).pageState.observe(this, new Observer() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m589lambda$initObserve$2$comwanosmediaviewSendShareCodeDialog((PageState) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$0$com-wanos-media-view-SendShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m588lambda$initObserve$0$comwanosmediaviewSendShareCodeDialog(String str) {
        if (this.binding != 0) {
            ((DialogSendShareCodeBinding) this.binding).wanosVerification.setText(str);
        }
    }

    static /* synthetic */ void lambda$initObserve$1(String str) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        ToastUtil.showMsg(str);
    }

    /* JADX INFO: renamed from: com.wanos.media.view.SendShareCodeDialog$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$PageState;

        static {
            int[] iArr = new int[PageState.values().length];
            $SwitchMap$com$wanos$media$entity$PageState = iArr;
            try {
                iArr[PageState.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$2$com-wanos-media-view-SendShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m589lambda$initObserve$2$comwanosmediaviewSendShareCodeDialog(PageState pageState) {
        if (this.binding != 0) {
            int i = AnonymousClass1.$SwitchMap$com$wanos$media$entity$PageState[pageState.ordinal()];
            if (i == 1) {
                ((DialogSendShareCodeBinding) this.binding).errorView.setVisibility(8);
                ((DialogSendShareCodeBinding) this.binding).loadingView.setVisibility(0);
                ((DialogSendShareCodeBinding) this.binding).tvSendHint.setVisibility(4);
                ((DialogSendShareCodeBinding) this.binding).wanosVerification.setVisibility(4);
                return;
            }
            if (i == 2) {
                ((DialogSendShareCodeBinding) this.binding).errorView.setVisibility(8);
                ((DialogSendShareCodeBinding) this.binding).loadingView.setVisibility(8);
                ((DialogSendShareCodeBinding) this.binding).tvSendHint.setVisibility(0);
                ((DialogSendShareCodeBinding) this.binding).wanosVerification.setVisibility(0);
                return;
            }
            if (i != 3) {
                return;
            }
            ((DialogSendShareCodeBinding) this.binding).errorView.setVisibility(0);
            ((DialogSendShareCodeBinding) this.binding).loadingView.setVisibility(8);
            ((DialogSendShareCodeBinding) this.binding).tvSendHint.setVisibility(4);
            ((DialogSendShareCodeBinding) this.binding).wanosVerification.setVisibility(4);
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogSendShareCodeBinding) this.binding).btnOk.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m585lambda$initListener$3$comwanosmediaviewSendShareCodeDialog(view);
            }
        });
        ((DialogSendShareCodeBinding) this.binding).btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m586lambda$initListener$4$comwanosmediaviewSendShareCodeDialog(view);
            }
        });
        ((DialogSendShareCodeBinding) this.binding).title.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.view.SendShareCodeDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m587lambda$initListener$5$comwanosmediaviewSendShareCodeDialog(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$3$com-wanos-media-view-SendShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m585lambda$initListener$3$comwanosmediaviewSendShareCodeDialog(View view) {
        dismiss();
    }

    /* JADX INFO: renamed from: lambda$initListener$4$com-wanos-media-view-SendShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m586lambda$initListener$4$comwanosmediaviewSendShareCodeDialog(View view) {
        dismiss();
    }

    /* JADX INFO: renamed from: lambda$initListener$5$com-wanos-media-view-SendShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m587lambda$initListener$5$comwanosmediaviewSendShareCodeDialog(View view) {
        dismiss();
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        if (this.binding != 0) {
            ((DialogSendShareCodeBinding) this.binding).wanosVerification.setEnabled(false);
        }
    }
}
