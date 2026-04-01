package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetTrackS extends BaseCmd {
    private int trackIndex;

    public CmdSetTrackS(int i) {
        this.trackIndex = i;
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.setTrackS(this.trackIndex, false);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setTrackS(this.trackIndex, false);
    }
}
