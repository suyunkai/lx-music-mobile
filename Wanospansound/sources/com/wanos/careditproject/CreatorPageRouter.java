package com.wanos.careditproject;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wanos.bean.ProjectInfo;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorPageRouter {
    public static final String CREATOR_COMMUNITY_ACTIVITY = "/catstudio/CreatorCommunityActivity";
    public static final String CREATOR_MINE_ACTIVITY = "/catstudio/CreatorMineActivity";
    public static final String CREATOR_PLAY_ACTIVITY = "/catstudio/WanosPlayerActivity";

    public static void toCreateMinePage() {
        ARouter.getInstance().build(CREATOR_MINE_ACTIVITY).navigation();
    }

    public static void toCreatePlayPage(ProjectInfo projectInfo, boolean z, boolean z2) {
        ARouter.getInstance().build(CREATOR_PLAY_ACTIVITY).withSerializable("projectInfo", projectInfo).withInt("type", z ? 1 : 0).withBoolean("EDIT", z2).navigation();
    }

    public static void toCreateCommunityPage() {
        ARouter.getInstance().build(CREATOR_COMMUNITY_ACTIVITY).navigation();
    }
}
