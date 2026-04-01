package com.wanos.careditproject.ui.viewholder;

import android.view.View;
import com.wanos.careditproject.CreatorPageRouter;
import com.wanos.careditproject.databinding.ItemCreatorHomeHeaderBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.editmain.dialog.NewProjectDialog;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;

/* JADX INFO: loaded from: classes3.dex */
public class HomeHeaderViewHolder extends BaseViewHolder<ItemCreatorHomeHeaderBinding, CreatorCommonItem> {
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
    }

    public HomeHeaderViewHolder(ItemCreatorHomeHeaderBinding itemCreatorHomeHeaderBinding) {
        super(itemCreatorHomeHeaderBinding);
    }

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        ((ItemCreatorHomeHeaderBinding) this.binding).btnStartCreate.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeHeaderViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m403xb7aaf1a2(view);
            }
        });
        ((ItemCreatorHomeHeaderBinding) this.binding).btnMySpace.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeHeaderViewHolder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m404x72209223(view);
            }
        });
        ((ItemCreatorHomeHeaderBinding) this.binding).btnSoundArea.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.ui.viewholder.HomeHeaderViewHolder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m405x2c9632a4(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBind$0$com-wanos-careditproject-ui-viewholder-HomeHeaderViewHolder, reason: not valid java name */
    /* synthetic */ void m403xb7aaf1a2(View view) {
        if (!UserInfoUtil.isLogin()) {
            if (this.itemView.getContext() instanceof WanosBaseActivity) {
                ((WanosBaseActivity) this.itemView.getContext()).showLoginDialog();
            }
        } else {
            stopOtherAudio();
            new NewProjectDialog().show(((WanosBaseActivity) this.itemView.getContext()).getSupportFragmentManager(), "newProject");
        }
    }

    /* JADX INFO: renamed from: lambda$onBind$1$com-wanos-careditproject-ui-viewholder-HomeHeaderViewHolder, reason: not valid java name */
    /* synthetic */ void m404x72209223(View view) {
        if (!UserInfoUtil.isLogin()) {
            if (this.itemView.getContext() instanceof WanosBaseActivity) {
                ((WanosBaseActivity) this.itemView.getContext()).showLoginDialog();
            }
        } else {
            stopOtherAudio();
            CreatorPageRouter.toCreateMinePage();
        }
    }

    /* JADX INFO: renamed from: lambda$onBind$2$com-wanos-careditproject-ui-viewholder-HomeHeaderViewHolder, reason: not valid java name */
    /* synthetic */ void m405x2c9632a4(View view) {
        stopOtherAudio();
        CreatorPageRouter.toCreateCommunityPage();
    }

    protected void stopOtherAudio() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || !MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            return;
        }
        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
    }
}
