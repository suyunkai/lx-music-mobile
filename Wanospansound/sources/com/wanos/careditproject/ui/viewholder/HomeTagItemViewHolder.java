package com.wanos.careditproject.ui.viewholder;

import android.widget.CompoundButton;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagItemBinding;

/* JADX INFO: loaded from: classes3.dex */
public class HomeTagItemViewHolder extends BaseViewHolder<ItemCreatorHomeTagItemBinding, ProjectTagBean> {
    private OnCheckListener listener;
    private int position;

    public interface OnCheckListener {
        void onCheck(int i, int i2);
    }

    public HomeTagItemViewHolder(ItemCreatorHomeTagItemBinding itemCreatorHomeTagItemBinding) {
        super(itemCreatorHomeTagItemBinding);
    }

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        ((ItemCreatorHomeTagItemBinding) this.binding).tvTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.m406x14056c5a(compoundButton, z);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onBind$0$com-wanos-careditproject-ui-viewholder-HomeTagItemViewHolder, reason: not valid java name */
    /* synthetic */ void m406x14056c5a(CompoundButton compoundButton, boolean z) {
        OnCheckListener onCheckListener;
        if (!z || ((ProjectTagBean) this.data).isSelected() || (onCheckListener = this.listener) == null) {
            return;
        }
        onCheckListener.onCheck(this.position, ((ProjectTagBean) this.data).getId());
    }

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
        ((ItemCreatorHomeTagItemBinding) this.binding).tvTag.setOnClickListener(null);
    }

    public void setOnCheckListener(OnCheckListener onCheckListener, int i) {
        this.listener = onCheckListener;
        this.position = i;
    }
}
