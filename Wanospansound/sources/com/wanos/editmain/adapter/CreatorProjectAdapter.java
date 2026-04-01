package com.wanos.editmain.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.editmain.dialog.ProjectMoreDialog;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorProjectAdapter extends BaseRecycleAdapter<ProjectInfo, ProjectViewHolder> implements ProjectMoreDialog.ProjectOpeareListener {
    private final Context context;
    private int pageType;
    private ProjectMoreDialog projectMoreDialog;
    private ProjectOpeareListener projectOpeareListener;

    public interface ProjectOpeareListener {
        void onCreateNewProjectListener(int i, ProjectInfo projectInfo);

        void onDeleteProjectListener(int i, ProjectInfo projectInfo);

        void onModifyProjectListener(int i, ProjectInfo projectInfo);

        void onPublishProjectListener(int i, ProjectInfo projectInfo);

        void onShareProjectListener(int i, ProjectInfo projectInfo);

        void onUpdateListener();
    }

    public CreatorProjectAdapter(Context context, List<ProjectInfo> list, int i) {
        super(list);
        this.context = context;
        this.pageType = i;
    }

    public void setProjectOpeareListener(ProjectOpeareListener projectOpeareListener) {
        this.projectOpeareListener = projectOpeareListener;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ProjectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4371) {
            return new ProjectViewHolder(this.mFooterView);
        }
        if (i == 4370) {
            return new ProjectViewHolder(this.mHeaderView);
        }
        return new ProjectViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_creator_project_item, viewGroup, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01d4  */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bindData(final com.wanos.editmain.adapter.CreatorProjectAdapter.ProjectViewHolder r14, final int r15) {
        /*
            Method dump skipped, instruction units count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.editmain.adapter.CreatorProjectAdapter.bindData(com.wanos.editmain.adapter.CreatorProjectAdapter$ProjectViewHolder, int):void");
    }

    protected void collectWork(String str, final ProjectInfo projectInfo, final ImageView imageView) {
        if (!projectInfo.isCollect()) {
            CreatorRetrofitUtil.workCollect(str, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.editmain.adapter.CreatorProjectAdapter.4
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    CreatorProjectAdapter.this.updateCollect(projectInfo.isCollect(), imageView);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("收藏失败");
                }
            });
        } else {
            CreatorRetrofitUtil.workCancelCollect(str, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.editmain.adapter.CreatorProjectAdapter.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    projectInfo.setCollect(!r3.isCollect());
                    CreatorProjectAdapter.this.updateCollect(projectInfo.isCollect(), imageView);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str2) {
                    ToastUtil.showMsg("取消收藏失败");
                }
            });
        }
    }

    public void updateCollect(boolean z, ImageView imageView) {
        ProjectOpeareListener projectOpeareListener;
        if (z) {
            imageView.setImageResource(R.drawable.creator_collect);
        } else {
            imageView.setImageResource(R.drawable.creator_no_collect);
        }
        if (this.pageType != 2 || (projectOpeareListener = this.projectOpeareListener) == null) {
            return;
        }
        projectOpeareListener.onUpdateListener();
    }

    protected boolean isEdit(ProjectInfo projectInfo) {
        if (projectInfo.getCanTemplate() == 1) {
            return true;
        }
        return projectInfo.getAutherInfo() != null && isMine(projectInfo.getAutherInfo().getUserId());
    }

    protected boolean isMine(String str) {
        return (UserInfoUtil.getUserInfo() == null || UserInfoUtil.getUserInfo().getUserId() == null || !UserInfoUtil.getUserInfo().getUserId().equals(str)) ? false : true;
    }

    @Override // com.wanos.editmain.dialog.ProjectMoreDialog.ProjectOpeareListener
    public void onDeleteProjectListener(int i, ProjectInfo projectInfo) {
        ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
        if (projectOpeareListener != null) {
            projectOpeareListener.onDeleteProjectListener(i, projectInfo);
        }
    }

    @Override // com.wanos.editmain.dialog.ProjectMoreDialog.ProjectOpeareListener
    public void onModifyProjectListener(int i, ProjectInfo projectInfo) {
        ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
        if (projectOpeareListener != null) {
            projectOpeareListener.onModifyProjectListener(i, projectInfo);
        }
    }

    @Override // com.wanos.editmain.dialog.ProjectMoreDialog.ProjectOpeareListener
    public void onCreateNewProjectListener(int i, ProjectInfo projectInfo) {
        ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
        if (projectOpeareListener != null) {
            projectOpeareListener.onCreateNewProjectListener(i, projectInfo);
        }
    }

    @Override // com.wanos.editmain.dialog.ProjectMoreDialog.ProjectOpeareListener
    public void onPublishProjectListener(int i, ProjectInfo projectInfo) {
        ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
        if (projectOpeareListener != null) {
            projectOpeareListener.onPublishProjectListener(i, projectInfo);
        }
    }

    @Override // com.wanos.editmain.dialog.ProjectMoreDialog.ProjectOpeareListener
    public void onShareProjectListener(int i, ProjectInfo projectInfo) {
        ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
        if (projectOpeareListener != null) {
            projectOpeareListener.onShareProjectListener(i, projectInfo);
        }
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        private View addContentll;
        private CardView cardHead;
        private LinearLayout itemIcons;
        private ImageView ivCollect;
        private ImageView ivHead;
        private ImageView ivState;
        private ImageView projectBtn;
        private View projectContentll;
        private ImageView projectImage;
        private TextView projectName;
        private TextView projectTime;
        private TextView tvAddItem;

        ProjectViewHolder(View view) {
            super(view);
            this.projectName = (TextView) view.findViewById(R.id.tv_project_name_item);
            this.projectImage = (ImageView) view.findViewById(R.id.iv_my_project_item);
            this.projectBtn = (ImageView) view.findViewById(R.id.iv_my_project_more);
            this.projectContentll = view.findViewById(R.id.project_content_ll);
            this.projectTime = (TextView) view.findViewById(R.id.tv_project_time_item);
            this.ivState = (ImageView) view.findViewById(R.id.iv_state);
            this.cardHead = (CardView) view.findViewById(R.id.card_user_head);
            this.ivHead = (ImageView) view.findViewById(R.id.iv_user_head);
            this.itemIcons = (LinearLayout) view.findViewById(R.id.item_community);
            this.ivCollect = (ImageView) view.findViewById(R.id.iv_collect);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProjectMoreDialog(View view, int i, ProjectInfo projectInfo, boolean z) {
        if (this.context != null) {
            if (this.projectMoreDialog == null) {
                ProjectMoreDialog projectMoreDialog = new ProjectMoreDialog(this.context, com.wanos.commonlibrary.R.style.DialogStyle, z, i, projectInfo);
                this.projectMoreDialog = projectMoreDialog;
                projectMoreDialog.setProjectOpeareListener(this);
            }
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            view.getRootView().getWindowVisibleDisplayFrame(rect);
            this.projectMoreDialog.showAtLocation(view, 0, (iArr[0] - rect.left) - Util.dip2px(this.context, 20.0f), iArr[1] + Util.dip2px(this.context, 70.0f), i, projectInfo);
        }
    }
}
