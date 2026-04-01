package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdSetClipSize extends BaseCmd {
    private static CmdSetClipSize cur;
    private String clipId;
    private long end0;
    private long end1;
    private long originStart0;
    private long originStart1;
    private long sampleNum0;
    private long sampleNum1;
    private long start0;
    private long start1;

    public static void setCurrentCmd(CmdSetClipSize cmdSetClipSize) {
        cur = cmdSetClipSize;
    }

    public static CmdSetClipSize getCurrentCmd() {
        return cur;
    }

    public void saveOld(ClipModel clipModel) {
        this.clipId = clipModel.getID() + "";
        this.start0 = clipModel.getStart();
        this.end0 = clipModel.getEnd();
        this.originStart0 = clipModel.getOriginStart();
        this.sampleNum0 = clipModel.getSampleNum();
        setCurrentCmd(this);
    }

    public void saveNew(ClipModel clipModel) {
        if (this.clipId.equals(clipModel.getID() + "")) {
            this.start1 = clipModel.getStart();
            this.end1 = clipModel.getEnd();
            this.originStart1 = clipModel.getOriginStart();
            this.sampleNum1 = clipModel.getSampleNum();
            CmdListCache.save(this);
        }
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void undo() {
        DataHelpAudioTrack.setClipSize(this.clipId, this.start0, this.end0, this.originStart0);
    }

    @Override // com.wanos.careditproject.utils.cmd.BaseCmd
    public void redo() {
        DataHelpAudioTrack.setClipSize(this.clipId, this.start1, this.end1, this.originStart1);
    }
}
