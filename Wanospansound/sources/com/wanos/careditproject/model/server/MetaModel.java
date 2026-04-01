package com.wanos.careditproject.model.server;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MetaModel {
    private CurEffectModel CustomEffect;
    private List<PosModel> Pos = new ArrayList();
    private CurEffectModel CurEffect = new CurEffectModel();
    private int EffectMode = 1;

    public void setPos(List<PosModel> list) {
        this.Pos = list;
    }

    public List<PosModel> getPos() {
        return this.Pos;
    }

    public void setEffectMode(int i) {
        this.EffectMode = i;
    }

    public int getEffectMode() {
        return this.EffectMode;
    }

    public void setCustomEffect(CurEffectModel curEffectModel) {
        this.CustomEffect = curEffectModel;
    }

    public CurEffectModel getCustomEffect() {
        return this.CustomEffect;
    }

    public void setCurEffect(CurEffectModel curEffectModel) {
        this.CurEffect = curEffectModel;
    }

    public CurEffectModel getCurEffect() {
        return this.CurEffect;
    }
}
