package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetClipSpeed extends BaseCmd {
    private static CmdSetClipSpeed cur;
    private String curClipId;
    private float newValue;
    private float oldValue;

    public static void setCurrentCmd(CmdSetClipSpeed cmdSetClipSpeed) {
        cur = cmdSetClipSpeed;
    }

    public static CmdSetClipSpeed getCurrentCmd() {
        return cur;
    }

    public void saveOld(String str) {
        this.curClipId = str;
        ClipModel clipById = DataHelpAudioTrack.getClipById(str);
        if (clipById != null) {
            this.oldValue = clipById.getSpeed();
        }
        setCurrentCmd(this);
    }

    public void saveNew() {
        if (DataHelpAudioTrack.getClipById(this.curClipId) != null) {
            this.newValue = r0.getDB();
            EditingUtils.log("cmd saveNew curClipId=" + this.curClipId + ", " + this.oldValue + "," + this.newValue);
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        EditingUtils.log("cmd undo ," + this.oldValue);
        DataHelpAudioTrack.setClipSpeed(this.curClipId, this.oldValue);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setClipSpeed(this.curClipId, this.newValue);
    }
}
