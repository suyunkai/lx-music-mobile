package com.wanos.media.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public interface IZeroCallback {
    public static final int LANDSCAPE_FULL = 101;
    public static final int LANDSCAPE_HALF = 102;
    public static final int VERTICAL_FULL = 201;
    public static final int VERTICAL_HALF = 202;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenState {
    }

    void addScreenStateChangeListener(IScreenStateChange iScreenStateChange);

    int getScreenState();

    void onRelaxEnter();

    void onRelaxExit();

    void removeScreenStateChangeListener(IScreenStateChange iScreenStateChange);
}
