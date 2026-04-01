package com.wanos.careditproject.ui.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorWorkTypeViewModel {
    private ProjectInfo data;
    private final int type;

    public CreatorWorkTypeViewModel(int i) {
        this.type = i;
    }

    public void setData(ProjectInfo projectInfo) {
        this.data = projectInfo;
    }

    public int getType() {
        return this.type;
    }

    public boolean isShowOpenStatus() {
        return this.type == 1;
    }

    public String getOpenStatusText(Context context) {
        return context.getResources().getString(this.data.getPublishRange() == 1 ? R.string.create_mine_open_status_me : R.string.create_mine_open_status_all);
    }

    public boolean isShowAuditStatus() {
        return this.type == 1 && getWorkStatusLocal() < 5;
    }

    public String getAuditStatusText(Context context) {
        int workStatusLocal = getWorkStatusLocal();
        if (workStatusLocal == 1) {
            return context.getResources().getString(R.string.create_mine_publish_status_making);
        }
        if (workStatusLocal == 2) {
            return context.getResources().getString(R.string.create_mine_publish_status_making_fail);
        }
        if (workStatusLocal != 3) {
            return workStatusLocal != 4 ? "" : context.getResources().getString(R.string.create_mine_publish_status_audit_fail);
        }
        return context.getResources().getString(R.string.create_mine_publish_status_auditing);
    }

    public boolean isShowAuditStatusMore() {
        return this.type == 1 && getWorkStatusLocal() == 4;
    }

    public int getAuditStatusColor(Context context) {
        int workStatusLocal = getWorkStatusLocal();
        if (workStatusLocal == 1) {
            return context.getResources().getColor(R.color.creator_mine_publish_making);
        }
        if (workStatusLocal != 2) {
            if (workStatusLocal == 3) {
                return context.getResources().getColor(R.color.creator_mine_publish_auditing);
            }
            if (workStatusLocal != 4) {
                return 0;
            }
        }
        return context.getResources().getColor(R.color.creator_mine_publish_audit_fail);
    }

    public Drawable getAuditStatusBg(Context context) {
        int workStatusLocal = getWorkStatusLocal();
        if (workStatusLocal == 1) {
            return context.getResources().getDrawable(R.drawable.bg_creator_mine_publish_making);
        }
        if (workStatusLocal != 2) {
            if (workStatusLocal == 3) {
                return context.getResources().getDrawable(R.drawable.bg_creator_mine_publish_auditing);
            }
            if (workStatusLocal != 4) {
                return null;
            }
        }
        return context.getResources().getDrawable(R.drawable.bg_creator_mine_publish_audit_fail);
    }

    private int getWorkStatusLocal() {
        if (this.data.getStatus() == 0) {
            return 1;
        }
        if (this.data.getStatus() == 2) {
            return 2;
        }
        if (this.data.getReviewStatus() <= 2) {
            return 3;
        }
        if (this.data.getReviewStatus() == 4) {
            return 4;
        }
        return this.data.getReviewStatus() == 3 ? 5 : 6;
    }

    public boolean isShowMore() {
        return this.type == 1;
    }

    public boolean isShowCreateSame() {
        return this.type != 1 && this.data.getCanTemplate() == 1;
    }

    public boolean isShowWorkTag() {
        return (this.data.getWorkType() == null || TextUtils.isEmpty(this.data.getWorkType().typeName)) ? false : true;
    }

    public boolean isShowCollection() {
        return this.type != 1;
    }
}
