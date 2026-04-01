package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdRenameTrackName extends BaseValueCmd<String> {
    private int trackIndex;

    public CmdRenameTrackName(String str, String str2, int i) {
        super(str, str2);
        this.trackIndex = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.renameTrackName(this.trackIndex, (String) this.value0, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.renameTrackName(this.trackIndex, (String) this.value1, false);
    }
}
