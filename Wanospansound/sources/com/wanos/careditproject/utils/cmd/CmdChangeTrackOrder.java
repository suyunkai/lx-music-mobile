package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class CmdChangeTrackOrder extends BaseValueCmd<String> {
    private int trackIndex;

    public CmdChangeTrackOrder(String str, String str2) {
        super(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.changeTrackIdOrder(Arrays.asList(((String) this.value0).split(",")));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.changeTrackIdOrder(Arrays.asList(((String) this.value1).split(",")));
    }
}
