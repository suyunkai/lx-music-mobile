package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSwapTrackOrder extends BaseValueCmd<Integer> {
    public CmdSwapTrackOrder(Integer num, Integer num2) {
        super(num, num2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.swapTrackOrder(((Integer) this.value0).intValue(), ((Integer) this.value1).intValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.utils.cmd.BaseValueCmd, com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.swapTrackOrder(((Integer) this.value0).intValue(), ((Integer) this.value1).intValue());
    }
}
