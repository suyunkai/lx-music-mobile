package com.wanos.careditproject.utils.cmd;

/* JADX INFO: loaded from: classes3.dex */
public class BaseValueCmd<T> extends BaseCmd {
    public T value0;
    public T value1;

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    void redo() {
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    void undo() {
    }

    public BaseValueCmd(T t, T t2) {
        this.value0 = t;
        this.value1 = t2;
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    protected void clearCacheData() {
        this.value0 = null;
        this.value1 = null;
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    protected void getCacheData(BaseCmd baseCmd) {
        if (baseCmd instanceof BaseValueCmd) {
            BaseValueCmd baseValueCmd = (BaseValueCmd) baseCmd;
            this.value0 = baseValueCmd.value0;
            this.value1 = baseValueCmd.value1;
        }
    }
}
