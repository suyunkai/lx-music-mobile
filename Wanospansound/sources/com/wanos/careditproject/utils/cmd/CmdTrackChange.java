package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdTrackChange extends BaseValueCmd<String> {
    private boolean newTrack;
    private int trackIndex;

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    protected boolean isSaveStorage() {
        return true;
    }

    public CmdTrackChange(String str, String str2, int i, boolean z) {
        super(str, str2);
        this.trackIndex = i;
        this.newTrack = z;
    }

    public CmdTrackChange(String str, String str2, int i) {
        super(str, str2);
        this.newTrack = false;
        this.trackIndex = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        if (this.newTrack) {
            DataHelpAudioTrack.removeTrackByIndex(this.trackIndex);
        } else {
            DataHelpAudioTrack.updateTrack(this.trackIndex, (String) this.value0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        if (this.newTrack) {
            DataHelpAudioTrack.insertTrack((String) this.value1, this.trackIndex);
        } else {
            DataHelpAudioTrack.updateTrack(this.trackIndex, (String) this.value1);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void clearCacheData() {
        super.clearCacheData();
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void getCacheData(BaseCmd baseCmd) {
        if (baseCmd instanceof CmdTrackChange) {
            super.getCacheData(baseCmd);
            CmdTrackChange cmdTrackChange = (CmdTrackChange) baseCmd;
            this.trackIndex = cmdTrackChange.trackIndex;
            this.newTrack = cmdTrackChange.newTrack;
        }
    }
}
