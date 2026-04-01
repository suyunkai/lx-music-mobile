package com.wanos.careditproject.data.bean;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AiFindCreateStateEntity {
    public static final int STATE_AI_RUNNING = 6;
    public static final int STATE_CANCEL = 5;
    public static final int STATE_DEFAULT = 0;
    public static final int STATE_FAILED = 4;
    public static final int STATE_MIDI_RUNNING = 7;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_SUCCESS = 3;
    public static final int STATE_WAIT = 1;
    private String error;
    private Map<String, Object> outputDatas;
    private int state;
    private String style;
    private String title;
    private String track_id;

    public AiFindCreateStateEntity(int i, String str) {
        this.state = i;
        this.error = str;
    }

    public int getState() {
        return this.state;
    }

    public String getError() {
        return this.error;
    }

    public Map<String, Object> getOutputDatas() {
        return this.outputDatas;
    }

    public String getStyle() {
        return this.style;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTrack_id() {
        return this.track_id;
    }

    public void setTrack_id(String str) {
        this.track_id = str;
    }

    public int getAnimationRawId() {
        return AiCreateStyle.getAnimationRawId(this.style);
    }
}
