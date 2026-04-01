package com.wanos.careditproject.utils.exPlayer;

import android.util.Log;
import com.flyme.auto.sdk.restriction.RestrictionManager;
import com.wanos.careditproject.data.bean.AiCancelResponse;
import com.wanos.careditproject.data.bean.AiImplProjectResponse;
import com.wanos.careditproject.data.bean.AiImplProjectStateResponse;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
class AiExPlayRepo {
    private static final String TAG = "wanos[AI]-AiPlayRepo";
    private String mCurrentTrackId;
    private volatile boolean isDestroy = false;
    private int implProjectId = -1;
    private int implTaskId = -1;

    AiExPlayRepo() {
    }

    public int implAiProject(String str, String str2) throws Throwable {
        AiImplProjectResponse aiImplProjectEntity = CreatorRetrofitUtil.getAiImplProjectEntity(str, str2);
        if (aiImplProjectEntity == null) {
            throw new Throwable("项目导入失败，Studio项目创建失败");
        }
        if (aiImplProjectEntity.code != 0 || aiImplProjectEntity.getData() == null) {
            throw new Throwable(aiImplProjectEntity.msg);
        }
        int restrictionState = RestrictionManager.getInstance().getRestrictionState();
        Log.i(TAG, "openEditActivity: 当前限制状态 = " + restrictionState);
        if (restrictionState == 2 || restrictionState == 3) {
            throw new Throwable("请在P档时使用该功能");
        }
        this.implProjectId = aiImplProjectEntity.getData().getProjectId();
        this.implTaskId = aiImplProjectEntity.getData().getTaskId();
        Log.d(TAG, "implAiProject: implProjectId = " + this.implProjectId + ", implTaskId = " + this.implTaskId);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jCurrentTimeMillis2 = 0;
        do {
            AiImplProjectStateResponse aiImplProjectStateResponseFindAiImplProjectState = CreatorRetrofitUtil.findAiImplProjectState(this.implProjectId);
            if (aiImplProjectStateResponseFindAiImplProjectState == null) {
                throw new Throwable("项目导入失败");
            }
            if (aiImplProjectStateResponseFindAiImplProjectState.code != 0 || aiImplProjectStateResponseFindAiImplProjectState.getData() == null) {
                throw new Throwable(aiImplProjectStateResponseFindAiImplProjectState.msg);
            }
            int status = aiImplProjectStateResponseFindAiImplProjectState.getData().getStatus();
            if (status == 1 || status == 2) {
                TimeUnit.MILLISECONDS.sleep(3000L);
            } else {
                if (status == 3) {
                    Log.d(TAG, "AI项目导入成功，耗时: " + jCurrentTimeMillis2);
                    return this.implProjectId;
                }
                if (status == 4) {
                    this.implProjectId = -1;
                    this.implTaskId = -1;
                    throw new Throwable("项目上传失败，" + aiImplProjectStateResponseFindAiImplProjectState.getData().getStatus());
                }
                if (status == 5) {
                    this.implProjectId = -1;
                    this.implTaskId = -1;
                    throw new Throwable("");
                }
            }
            jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (this.isDestroy) {
                break;
            }
        } while (jCurrentTimeMillis2 <= 180000);
        Log.w(TAG, "findProjectData: 项目导入超时，设定超时时间 = 180000, 当前已运行时间 = " + jCurrentTimeMillis2 + ", isDestroy = " + this.isDestroy);
        throw new Throwable("项目执行失败");
    }

    public boolean getDestroyState() {
        return this.isDestroy;
    }

    public AiCancelResponse cancelImpl() {
        int i;
        int i2 = this.implProjectId;
        if (i2 == -1 || (i = this.implTaskId) == -1) {
            return null;
        }
        return CreatorRetrofitUtil.cancelAiImplProject(i2, i);
    }

    public void setNowTrackId(String str) {
        this.mCurrentTrackId = str;
    }

    public String getTrack_Id() {
        return this.mCurrentTrackId;
    }

    public boolean isDestroy() {
        return this.isDestroy;
    }

    public void onCleared() {
        this.isDestroy = true;
    }
}
