package com.wanos.careditproject.service.AudioPlayerManager;

/* JADX INFO: loaded from: classes3.dex */
public class PlayState {
    private String playId;
    private State state = State.IDLE;

    public enum State {
        IDLE,
        PREPARING,
        PLAYING,
        PAUSED,
        COMPLETED,
        ERROR
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPlayId() {
        return this.playId;
    }

    public void setPlayId(String str) {
        this.playId = str;
    }
}
