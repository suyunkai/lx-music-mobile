package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdNewClip extends BaseValueCmd<String> {
    private int trackIndex;

    public CmdNewClip(String str, String str2, int i) {
        super(str, str2);
        this.trackIndex = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.removeClip(this.trackIndex, (String) this.value0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.insertClip(this.trackIndex, (String) this.value1);
    }
}
