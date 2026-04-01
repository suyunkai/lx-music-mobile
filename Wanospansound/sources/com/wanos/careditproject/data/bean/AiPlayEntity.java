package com.wanos.careditproject.data.bean;

/* JADX INFO: loaded from: classes3.dex */
public class AiPlayEntity {
    private String id;
    private AiPlayState state = AiPlayState.DEFAULT;

    public AiPlayEntity(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public AiPlayState getState() {
        return this.state;
    }

    public void setState(AiPlayState aiPlayState) {
        this.state = aiPlayState;
    }

    public String toString() {
        return "AiPlayEntity{id='" + this.id + "', state=" + this.state + '}';
    }
}
