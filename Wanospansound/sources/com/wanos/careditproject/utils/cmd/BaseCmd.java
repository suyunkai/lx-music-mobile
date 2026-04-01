package com.wanos.careditproject.utils.cmd;

import com.google.gson.Gson;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseCmd {
    protected transient String saveFilePath = "";

    public enum CmdType {
        setTrackDB
    }

    protected void clearCacheData() {
    }

    protected void getCacheData(BaseCmd baseCmd) {
    }

    protected boolean isSaveStorage() {
        return false;
    }

    protected BaseCmd jsonToClass(String str) {
        return null;
    }

    abstract void redo();

    abstract void undo();

    public void save() {
        saveCacheInit();
        CmdListCache.save(this);
    }

    public void undoCmd() {
        undoRedoCacheBefore();
        undo();
        undoRedoCacheAfter();
    }

    public void redoCmd() {
        undoRedoCacheBefore();
        redo();
        undoRedoCacheAfter();
    }

    protected void getCache() {
        String str = this.saveFilePath;
        if (str == null || str.equals("")) {
            return;
        }
        String cmdData = CmdFileCache.getCmdData(this.saveFilePath);
        if (cmdData.equals("")) {
            return;
        }
        getCacheData((BaseCmd) new Gson().fromJson(cmdData, (Class) getClass()));
    }

    protected void undoRedoCacheBefore() {
        if (isSaveStorage()) {
            getCache();
        }
    }

    protected void undoRedoCacheAfter() {
        if (isSaveStorage()) {
            clearCacheData();
        }
    }

    protected void saveCacheInit() {
        if (isSaveStorage()) {
            this.saveFilePath = CmdFileCache.saveCmdData(new Gson().toJson(this));
            if (!r0.equals("")) {
                clearCacheData();
            }
        }
    }

    public void clearCache() {
        if (isSaveStorage()) {
            CmdFileCache.deleteCmdData(this.saveFilePath);
        }
    }
}
