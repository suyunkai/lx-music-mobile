package com.wanos.careditproject.data.bean;

/* JADX INFO: loaded from: classes3.dex */
public class AiImplProjectStateEntity {
    public static final int IMPL_AI_PROJECT_CANCEL = 5;
    public static final int IMPL_AI_PROJECT_FAIL = 4;
    public static final int IMPL_AI_PROJECT_STATE_RUNNING = 2;
    public static final int IMPL_AI_PROJECT_STATE_WAIT = 1;
    public static final int IMPL_AI_PROJECT_SUCCESS = 3;
    private int status;

    public int getStatus() {
        return this.status;
    }

    public AiImplProjectStateEntity(int i) {
        this.status = i;
    }
}
