package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdTracksChange extends BaseValueCmd<String> {
    private String newValue0;
    private String newValue1;
    private int trackIndex0;
    private int trackIndex1;

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    protected boolean isSaveStorage() {
        return true;
    }

    public CmdTracksChange(String str, String str2, String str3, String str4, int i, int i2) {
        super(str, str2);
        this.trackIndex0 = i;
        this.trackIndex1 = i2;
        this.newValue0 = str3;
        this.newValue1 = str4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.updateTrack(this.trackIndex0, (String) this.value0);
        DataHelpAudioTrack.updateTrack(this.trackIndex1, (String) this.value1);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.updateTrack(this.trackIndex0, this.newValue0);
        DataHelpAudioTrack.updateTrack(this.trackIndex1, this.newValue1);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void clearCacheData() {
        EditingUtils.log("CmdTracksChange clearCacheData");
        super.clearCacheData();
        this.newValue0 = null;
        this.newValue1 = null;
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    protected void getCacheData(BaseCmd baseCmd) {
        if (baseCmd instanceof CmdTracksChange) {
            super.getCacheData(baseCmd);
            CmdTracksChange cmdTracksChange = (CmdTracksChange) baseCmd;
            this.newValue0 = cmdTracksChange.newValue0;
            this.newValue1 = cmdTracksChange.newValue1;
            this.trackIndex0 = cmdTracksChange.trackIndex0;
            this.trackIndex1 = cmdTracksChange.trackIndex1;
        }
    }
}
