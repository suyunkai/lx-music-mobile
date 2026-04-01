package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdDeleteTrack extends BaseValueCmd<String> {
    private int trackIndex;

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    protected boolean isSaveStorage() {
        return true;
    }

    public CmdDeleteTrack(String str, String str2, int i) {
        super(str, str2);
        this.trackIndex = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.insertTrack((String) this.value1, this.trackIndex);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.removeTrack((String) this.value0);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void clearCacheData() {
        super.clearCacheData();
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void getCacheData(BaseCmd baseCmd) {
        if (baseCmd instanceof CmdDeleteTrack) {
            super.getCacheData(baseCmd);
            this.trackIndex = ((CmdDeleteTrack) baseCmd).trackIndex;
        }
    }
}
