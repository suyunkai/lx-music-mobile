package com.wanos.sdk.effect;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface IWanosAudioEffect {
    public static final int WANOS_EFFECT_SCENE_MODE_MAX_NUM = 3;
    public static final int WANOS_EFFECT_SPATIAL_POS_MODE_MAX_NUM = 5;

    public interface IWanosEffectSceneMode {
    }

    public interface IWanosEffectSpatialPostionMode {
    }

    public enum WanosEffectSceneMode implements IWanosEffectSceneMode {
        SCENE,
        MUSIC,
        MOIVE
    }

    public enum WanosEffectSpatialPostionMode implements IWanosEffectSpatialPostionMode {
        WHOLE_CAR,
        MAIN_DRIVER,
        COPILOT_DRIVER,
        LEFT_BACK,
        RIGHT_BACK
    }

    IWanosEffectSpatialPostionMode getCurrentEffectPositionMode(Context context, IWanosEffectSpatialPostionMode... iWanosEffectSpatialPostionModeArr);

    IWanosEffectSceneMode getCurrentEffectSceneMode(Context context, IWanosEffectSceneMode... iWanosEffectSceneModeArr);

    boolean getWanosEffectSwitchState(Context context);

    void setWanosTuningConfigMode(Context context, IWanosEffectSceneMode iWanosEffectSceneMode);

    void setWanosTuningConfigMode(Context context, IWanosEffectSceneMode iWanosEffectSceneMode, IWanosEffectSpatialPostionMode iWanosEffectSpatialPostionMode);

    void setWanosTuningConfigMode(Context context, IWanosEffectSceneMode iWanosEffectSceneMode, IWanosEffectSpatialPostionMode iWanosEffectSpatialPostionMode, IWanosEffectSceneMode[] iWanosEffectSceneModeArr, IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArr);

    void toggleWanosEffect(Context context, boolean z);
}
