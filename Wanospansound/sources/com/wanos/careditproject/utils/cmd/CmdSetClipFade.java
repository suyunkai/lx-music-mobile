package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetClipFade extends BaseCmd {
    private static CmdSetClipFade cur;
    private String curClipId;
    private int newValueI;
    private int newValueO;
    private int oldValueI;
    private int oldValueO;

    public static void setCurrentCmd(CmdSetClipFade cmdSetClipFade) {
        cur = cmdSetClipFade;
    }

    public static CmdSetClipFade getCurrentCmd() {
        return cur;
    }

    public void saveOld(String str) {
        this.curClipId = str;
        ClipModel clipById = DataHelpAudioTrack.getClipById(str);
        if (clipById != null) {
            this.oldValueI = clipById.getFadeIn();
            this.oldValueO = clipById.getFadeOut();
            EditingUtils.log("CmdSetClipFade saveOld + " + this.oldValueI + ", " + this.oldValueO);
        }
        setCurrentCmd(this);
    }

    public void saveNew() {
        ClipModel clipById = DataHelpAudioTrack.getClipById(this.curClipId);
        if (clipById != null) {
            this.newValueI = clipById.getFadeIn();
            this.newValueO = clipById.getFadeOut();
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        EditingUtils.log("CmdSetClipFade undo + " + this.oldValueI + ", " + this.oldValueO);
        DataHelpAudioTrack.setClipFadeValue(this.curClipId, this.oldValueI, this.oldValueO);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setClipFadeValue(this.curClipId, this.newValueI, this.newValueO);
    }
}
