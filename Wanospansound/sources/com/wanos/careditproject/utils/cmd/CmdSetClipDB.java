package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetClipDB extends BaseCmd {
    private static CmdSetClipDB cur;
    private String curClipId;
    private int newValue;
    private int oldValue;

    public static void setCurrentCmd(CmdSetClipDB cmdSetClipDB) {
        cur = cmdSetClipDB;
    }

    public static CmdSetClipDB getCurrentCmd() {
        return cur;
    }

    public void saveOld(String str) {
        this.curClipId = str;
        ClipModel clipById = DataHelpAudioTrack.getClipById(str);
        if (clipById != null) {
            this.oldValue = clipById.getDB();
        }
        setCurrentCmd(this);
    }

    public void saveNew() {
        ClipModel clipById = DataHelpAudioTrack.getClipById(this.curClipId);
        if (clipById != null) {
            this.newValue = clipById.getDB();
            EditingUtils.log("cmd saveNew curClipId=" + this.curClipId + ", " + this.oldValue + "," + this.newValue);
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        EditingUtils.log("cmd undo ," + this.oldValue);
        DataHelpAudioTrack.setClipDB(this.curClipId, this.oldValue);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setClipDB(this.curClipId, this.newValue);
    }
}
