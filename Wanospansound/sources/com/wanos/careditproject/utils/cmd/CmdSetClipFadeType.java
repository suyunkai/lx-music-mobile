package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetClipFadeType extends BaseCmd {
    private static CmdSetClipFadeType cur;
    private String curClipId;
    private String newValueI;
    private String newValueO;
    private String oldValueI;
    private String oldValueO;

    public static void setCurrentCmd(CmdSetClipFadeType cmdSetClipFadeType) {
        cur = cmdSetClipFadeType;
    }

    public static CmdSetClipFadeType getCurrentCmd() {
        return cur;
    }

    public void saveOld(String str, String str2, String str3) {
        this.curClipId = str;
        this.oldValueI = str2;
        this.oldValueO = str3;
    }

    public void saveNew(String str, String str2) {
        this.newValueI = str;
        this.newValueO = str2;
        CmdListCache.save(this);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        EditingUtils.log("CmdSetClipFade undo + " + this.oldValueI + ", " + this.oldValueO);
        DataHelpAudioTrack.setClipFadeTypeValue(this.curClipId, this.oldValueI, this.oldValueO);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setClipFadeTypeValue(this.curClipId, this.newValueI, this.newValueO);
    }
}
