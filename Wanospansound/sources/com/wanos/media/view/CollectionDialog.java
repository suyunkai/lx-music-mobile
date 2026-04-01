package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.bean.SoundCollectionEntity;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.adapter.CollectionCoveAdapter;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.entity.PageState;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.CustomClick;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import com.wanos.media.viewmodel.ViewModeBundleFactory;
import com.wanos.media.widget.ItemDecoration;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.DialogCollectionBinding;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionDialog extends WanosBaseDialog<DialogCollectionBinding, CollectionDialogViewModel> {
    public static final String TAG = "CollectionDialog";
    private CollectionCoveAdapter mAdapter;
    private OnOptionClickListener onOptionClickListener;

    public interface OnOptionClickListener {
        void onSaveClick(String str, String str2, String str3);
    }

    public static CollectionDialog show(FragmentManager fragmentManager, String str, String str2, String str3) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof CollectionDialog) {
            ((CollectionDialog) fragmentFindFragmentByTag).dismissAllowingStateLoss();
        }
        CollectionDialog collectionDialog = new CollectionDialog();
        Bundle bundle = new Bundle();
        bundle.putString(CollectionDialogViewModel.KEY_TITLE, str);
        bundle.putString(CollectionDialogViewModel.KEY_AUDIO_CONFIG, str2);
        bundle.putString(CollectionDialogViewModel.KEY_DEFAULT_COVER, str3);
        collectionDialog.setArguments(bundle);
        collectionDialog.show(fragmentManager, TAG);
        return collectionDialog;
    }

    public void setOnOptionClickListener(OnOptionClickListener onOptionClickListener) {
        this.onOptionClickListener = onOptionClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogCollectionBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogCollectionBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<CollectionDialogViewModel> initViewModel() {
        return CollectionDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModeBundleFactory(getArguments());
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initAdapter(Bundle bundle) {
        initSet();
        if (this.binding == 0) {
            return;
        }
        this.mAdapter = new CollectionCoveAdapter(((CollectionDialogViewModel) this.viewModel).getItemData());
        ((DialogCollectionBinding) this.binding).rvCove.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        ((DialogCollectionBinding) this.binding).rvCove.addItemDecoration(new ItemDecoration(getResources().getDimension(R.dimen.dialog_collection_cove_space)));
        ((DialogCollectionBinding) this.binding).rvCove.setAdapter(this.mAdapter);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        ((CollectionDialogViewModel) this.viewModel).title.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.CollectionDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m569lambda$initObserve$0$comwanosmediaviewCollectionDialog((String) obj);
            }
        });
        ((CollectionDialogViewModel) this.viewModel).upDataCount.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.CollectionDialog$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m570lambda$initObserve$1$comwanosmediaviewCollectionDialog((Integer) obj);
            }
        });
        ((CollectionDialogViewModel) this.viewModel).pageState.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.CollectionDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m571lambda$initObserve$2$comwanosmediaviewCollectionDialog((PageState) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$0$com-wanos-media-view-CollectionDialog, reason: not valid java name */
    /* synthetic */ void m569lambda$initObserve$0$comwanosmediaviewCollectionDialog(String str) {
        if (this.binding != 0) {
            ((DialogCollectionBinding) this.binding).inputName.setText(str);
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$1$com-wanos-media-view-CollectionDialog, reason: not valid java name */
    /* synthetic */ void m570lambda$initObserve$1$comwanosmediaviewCollectionDialog(Integer num) {
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: lambda$initObserve$2$com-wanos-media-view-CollectionDialog, reason: not valid java name */
    /* synthetic */ void m571lambda$initObserve$2$comwanosmediaviewCollectionDialog(PageState pageState) {
        if (pageState == PageState.ERROR) {
            setErrorState();
        } else if (pageState == PageState.SUCCESS) {
            setDataState();
        } else if (pageState == PageState.LOADING) {
            setLoadingState();
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogCollectionBinding) this.binding).btnCancel.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.CollectionDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                CollectionDialog.this.dismiss();
            }
        });
        ((DialogCollectionBinding) this.binding).title.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.CollectionDialog.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                CollectionDialog.this.dismiss();
            }
        });
        ((DialogCollectionBinding) this.binding).btnSave.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.CollectionDialog.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                SoundCollectionEntity soundCollectionEntity;
                if (CollectionDialog.this.binding != null) {
                    String strTrim = String.valueOf(((DialogCollectionBinding) CollectionDialog.this.binding).inputName.getText()).trim();
                    if (StringUtils.isEmpty(strTrim)) {
                        ToastUtil.showMsg(R.string.dialog_collection_input_title);
                        return;
                    }
                    if (strTrim.trim().length() <= 8) {
                        int i = 0;
                        while (true) {
                            if (i >= ((CollectionDialogViewModel) CollectionDialog.this.viewModel).getItemData().size()) {
                                soundCollectionEntity = null;
                                break;
                            } else {
                                if (((CollectionDialogViewModel) CollectionDialog.this.viewModel).getItemData().get(i).isSelect()) {
                                    soundCollectionEntity = ((CollectionDialogViewModel) CollectionDialog.this.viewModel).getItemData().get(i);
                                    break;
                                }
                                i++;
                            }
                        }
                        if (soundCollectionEntity != null && CollectionDialog.this.onOptionClickListener != null) {
                            CollectionDialog.this.onOptionClickListener.onSaveClick(strTrim, soundCollectionEntity.getImagePath(), ((CollectionDialogViewModel) CollectionDialog.this.viewModel).getAudioConfig());
                            CollectionDialog.this.dismiss();
                            return;
                        } else {
                            ToastUtil.showMsg(R.string.unknown_state);
                            CollectionDialog.this.dismiss();
                            return;
                        }
                    }
                    ToastUtil.showMsg(R.string.dialog_collection_input_size);
                    return;
                }
                ToastUtil.showMsg(R.string.unknown_state);
                CollectionDialog.this.dismiss();
            }
        });
        ((DialogCollectionBinding) this.binding).includeError.btnRetry.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.CollectionDialog.4
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                ((CollectionDialogViewModel) CollectionDialog.this.viewModel).initCoverListData();
            }
        });
        this.mAdapter.setOnItemClickListener(new CollectionCoveAdapter.OnItemClickListener() { // from class: com.wanos.media.view.CollectionDialog.5
            @Override // com.wanos.media.adapter.CollectionCoveAdapter.OnItemClickListener
            public void onItemClick(int i) {
                ((CollectionDialogViewModel) CollectionDialog.this.viewModel).setSelectIndex(i);
            }
        });
    }

    private void initSet() {
        if (this.binding == 0) {
            return;
        }
        ((DialogCollectionBinding) this.binding).includeLoading.getRoot().setBackgroundResource(R.color.search_clear_dialog_bg);
        ((DialogCollectionBinding) this.binding).includeError.getRoot().setBackgroundResource(R.color.search_clear_dialog_bg);
    }

    private void setLoadingState() {
        if (this.binding == 0) {
            return;
        }
        ((DialogCollectionBinding) this.binding).includeLoading.getRoot().setVisibility(0);
        ((DialogCollectionBinding) this.binding).includeError.getRoot().setVisibility(8);
    }

    private void setErrorState() {
        if (this.binding == 0) {
            return;
        }
        ((DialogCollectionBinding) this.binding).includeError.getRoot().setVisibility(0);
        ((DialogCollectionBinding) this.binding).includeLoading.getRoot().setVisibility(4);
    }

    private void setDataState() {
        if (this.binding == 0) {
            return;
        }
        ((DialogCollectionBinding) this.binding).includeError.getRoot().setVisibility(8);
        ((DialogCollectionBinding) this.binding).includeLoading.getRoot().setVisibility(4);
        ((DialogCollectionBinding) this.binding).rvCove.setVisibility(0);
        ((DialogCollectionBinding) this.binding).tvCoveTitle.setVisibility(0);
    }
}
