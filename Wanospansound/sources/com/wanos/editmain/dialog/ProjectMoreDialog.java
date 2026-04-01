package com.wanos.editmain.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectMoreDialog extends PopupWindow implements View.OnClickListener {
    private static final int FULL_SCREEN_FLAG = 4870;
    private ProjectInfo info;
    private boolean isCanCreate;
    private int position;
    private ProjectOpeareListener projectOpeareListener;
    TextView tvProjectCreate;
    TextView tvProjectDelete;
    TextView tvProjectModify;
    TextView tvProjectPublic;
    TextView tvProjectShare;

    public interface ProjectOpeareListener {
        void onCreateNewProjectListener(int i, ProjectInfo projectInfo);

        void onDeleteProjectListener(int i, ProjectInfo projectInfo);

        void onModifyProjectListener(int i, ProjectInfo projectInfo);

        void onPublishProjectListener(int i, ProjectInfo projectInfo);

        void onShareProjectListener(int i, ProjectInfo projectInfo);
    }

    public ProjectMoreDialog(Context context, int i, boolean z, int i2, ProjectInfo projectInfo) {
        super(context);
        this.isCanCreate = z;
        this.position = i2;
        this.info = projectInfo;
        if (z) {
            View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_project_more, (ViewGroup) null);
            setContentView(viewInflate);
            this.tvProjectModify = (TextView) viewInflate.findViewById(R.id.tv_project_modify);
            this.tvProjectCreate = (TextView) viewInflate.findViewById(R.id.tv_project_create);
            this.tvProjectDelete = (TextView) viewInflate.findViewById(R.id.tv_project_delete);
            this.tvProjectPublic = (TextView) viewInflate.findViewById(R.id.tv_project_public);
            this.tvProjectShare = (TextView) viewInflate.findViewById(R.id.tv_project_share);
            this.tvProjectModify.setOnClickListener(this);
            this.tvProjectCreate.setOnClickListener(this);
            this.tvProjectDelete.setOnClickListener(this);
            this.tvProjectPublic.setOnClickListener(this);
            this.tvProjectShare.setOnClickListener(this);
        } else {
            View viewInflate2 = LayoutInflater.from(context).inflate(R.layout.dialog_works_more, (ViewGroup) null);
            setContentView(viewInflate2);
            TextView textView = (TextView) viewInflate2.findViewById(R.id.tv_project_delete);
            this.tvProjectDelete = textView;
            textView.setOnClickListener(this);
        }
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(true);
        setFocusable(true);
    }

    public void setProjectOpeareListener(ProjectOpeareListener projectOpeareListener) {
        this.projectOpeareListener = projectOpeareListener;
    }

    public void showAsDropDown(View view, int i, ProjectInfo projectInfo) {
        this.position = i;
        this.info = projectInfo;
        showAsDropDown(view);
    }

    public void showAsDropDown(View view, int i, int i2, int i3, ProjectInfo projectInfo) {
        this.position = i3;
        this.info = projectInfo;
        showAsDropDown(view, i, i2);
    }

    public void showAsDropDown(View view, int i, int i2, int i3, int i4, ProjectInfo projectInfo) {
        this.position = i4;
        this.info = projectInfo;
        showAsDropDown(view, i, i2, i3);
    }

    public void showAtLocation(View view, int i, int i2, int i3, int i4, ProjectInfo projectInfo) {
        this.position = i4;
        this.info = projectInfo;
        super.showAtLocation(view, i, i2, i3);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_project_modify) {
            ProjectOpeareListener projectOpeareListener = this.projectOpeareListener;
            if (projectOpeareListener != null) {
                projectOpeareListener.onModifyProjectListener(this.position, this.info);
            }
            dismiss();
            return;
        }
        if (id == R.id.tv_project_delete) {
            ProjectOpeareListener projectOpeareListener2 = this.projectOpeareListener;
            if (projectOpeareListener2 != null) {
                projectOpeareListener2.onDeleteProjectListener(this.position, this.info);
            }
            dismiss();
            return;
        }
        if (id == R.id.tv_project_create) {
            ProjectOpeareListener projectOpeareListener3 = this.projectOpeareListener;
            if (projectOpeareListener3 != null) {
                projectOpeareListener3.onCreateNewProjectListener(this.position, this.info);
            }
            dismiss();
            return;
        }
        if (id == R.id.tv_project_public) {
            ProjectOpeareListener projectOpeareListener4 = this.projectOpeareListener;
            if (projectOpeareListener4 != null) {
                projectOpeareListener4.onPublishProjectListener(this.position, this.info);
            }
            dismiss();
            return;
        }
        if (id == R.id.tv_project_share) {
            ProjectOpeareListener projectOpeareListener5 = this.projectOpeareListener;
            if (projectOpeareListener5 != null) {
                projectOpeareListener5.onShareProjectListener(this.position, this.info);
            }
            dismiss();
        }
    }
}
