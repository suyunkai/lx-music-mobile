package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.TracksModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetTotalDB extends BaseCmd {
    private static CmdSetTotalDB cur;
    private int newValue;
    private int oldValue;

    public static void setCurrentCmd(CmdSetTotalDB cmdSetTotalDB) {
        cur = cmdSetTotalDB;
    }

    public static CmdSetTotalDB getCurrentCmd() {
        return cur;
    }

    public void saveOld() {
        TracksModel tracksInfo = DataHelpAudioTrack.getTracksInfo();
        if (tracksInfo != null) {
            this.oldValue = tracksInfo.getDB();
        }
        setCurrentCmd(this);
    }

    public void saveNew() {
        TracksModel tracksInfo = DataHelpAudioTrack.getTracksInfo();
        if (tracksInfo != null) {
            this.newValue = tracksInfo.getDB();
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.setTotalDB(this.oldValue);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setTotalDB(this.newValue);
    }
}
