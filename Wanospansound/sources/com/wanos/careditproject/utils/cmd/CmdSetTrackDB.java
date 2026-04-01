package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetTrackDB extends BaseCmd {
    private static CmdSetTrackDB cur;
    private int newValue;
    private int oldValue;
    private int trackIndex;

    public static void setCurrentCmd(CmdSetTrackDB cmdSetTrackDB) {
        cur = cmdSetTrackDB;
    }

    public static CmdSetTrackDB getCurrentCmd() {
        return cur;
    }

    public void saveOld(int i) {
        this.trackIndex = i;
        TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(i);
        if (trackByIndex != null) {
            this.oldValue = trackByIndex.getDB();
        }
        setCurrentCmd(this);
    }

    public void saveNew() {
        TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(this.trackIndex);
        if (trackByIndex != null) {
            this.newValue = trackByIndex.getDB();
            EditingUtils.log("cmd saveNew index=" + this.trackIndex + ", " + this.oldValue + "," + this.newValue);
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        EditingUtils.log("cmd undo ," + this.oldValue);
        DataHelpAudioTrack.setTrackDB(this.trackIndex, this.oldValue);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setTrackDB(this.trackIndex, this.newValue);
    }
}
