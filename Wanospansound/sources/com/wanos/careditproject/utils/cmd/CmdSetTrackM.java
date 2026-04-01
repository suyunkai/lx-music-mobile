package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetTrackM extends BaseCmd {
    private int trackIndex;

    public CmdSetTrackM(int i) {
        this.trackIndex = i;
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.setTrackM(this.trackIndex, false);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setTrackM(this.trackIndex, false);
    }
}
