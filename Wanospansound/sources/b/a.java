package b;

import android.content.Context;
import android.media.AudioManager;
import com.wanos.sdk.effect.IWanosAudioEffect;
import d.b;
import d.c;

/* JADX INFO: loaded from: classes.dex */
public class a implements IWanosAudioEffect {
    public a(Context context) {
        b(context, getWanosEffectSwitchState(context) ? 1 : 0);
        a(context, b.getInstance(context).getInt(a()));
    }

    public final String a() {
        return "wanos_setWanosTuningConfigModemodelIndex";
    }

    public final IWanosAudioEffect.IWanosEffectSceneMode[] a(IWanosAudioEffect.IWanosEffectSceneMode[] iWanosEffectSceneModeArr) {
        IWanosAudioEffect.IWanosEffectSceneMode[] iWanosEffectSceneModeArr2 = new IWanosAudioEffect.IWanosEffectSceneMode[(iWanosEffectSceneModeArr == null ? 0 : iWanosEffectSceneModeArr.length) + 3];
        IWanosAudioEffect.WanosEffectSceneMode[] wanosEffectSceneModeArr = {IWanosAudioEffect.WanosEffectSceneMode.SCENE, IWanosAudioEffect.WanosEffectSceneMode.MUSIC, IWanosAudioEffect.WanosEffectSceneMode.MOIVE};
        int i = 0;
        while (i < 3) {
            iWanosEffectSceneModeArr2[i] = wanosEffectSceneModeArr[i];
            i++;
        }
        if (iWanosEffectSceneModeArr != null) {
            for (int i2 = 0; i2 < iWanosEffectSceneModeArr.length; i2++) {
                iWanosEffectSceneModeArr2[i + i2] = iWanosEffectSceneModeArr[i2];
            }
        }
        return iWanosEffectSceneModeArr2;
    }

    public final String b() {
        return "wanos_setWanosTuningConfigMode_scene";
    }

    public final String c() {
        return "wanos_setWanosTuningConfigMode_spation_pos";
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public IWanosAudioEffect.IWanosEffectSpatialPostionMode getCurrentEffectPositionMode(Context context, IWanosAudioEffect.IWanosEffectSpatialPostionMode... iWanosEffectSpatialPostionModeArr) {
        int i = b.getInstance(context).getInt(c());
        IWanosAudioEffect.IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArrA = a(iWanosEffectSpatialPostionModeArr);
        for (int i2 = 0; i2 < iWanosEffectSpatialPostionModeArrA.length; i2++) {
            if (i2 == i) {
                return iWanosEffectSpatialPostionModeArrA[i2];
            }
        }
        return null;
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public IWanosAudioEffect.IWanosEffectSceneMode getCurrentEffectSceneMode(Context context, IWanosAudioEffect.IWanosEffectSceneMode... iWanosEffectSceneModeArr) {
        int i = b.getInstance(context).getInt(b());
        IWanosAudioEffect.IWanosEffectSceneMode[] iWanosEffectSceneModeArrA = a(iWanosEffectSceneModeArr);
        for (int i2 = 0; i2 < iWanosEffectSceneModeArrA.length; i2++) {
            if (i2 == i) {
                return iWanosEffectSceneModeArrA[i2];
            }
        }
        return null;
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public boolean getWanosEffectSwitchState(Context context) {
        return b.getInstance(context).getInt("wanos_effect", 0) > 0;
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public void setWanosTuningConfigMode(Context context, IWanosAudioEffect.IWanosEffectSceneMode iWanosEffectSceneMode) {
        setWanosTuningConfigMode(context, iWanosEffectSceneMode, IWanosAudioEffect.WanosEffectSpatialPostionMode.WHOLE_CAR);
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public void toggleWanosEffect(Context context, boolean z) {
        b(context, z ? 1 : 0);
        b.getInstance(context).put("wanos_effect", z ? 1 : 0);
    }

    public final IWanosAudioEffect.IWanosEffectSpatialPostionMode[] a(IWanosAudioEffect.IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArr) {
        IWanosAudioEffect.IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArr2 = new IWanosAudioEffect.IWanosEffectSpatialPostionMode[(iWanosEffectSpatialPostionModeArr == null ? 0 : iWanosEffectSpatialPostionModeArr.length) + 5];
        IWanosAudioEffect.WanosEffectSpatialPostionMode[] wanosEffectSpatialPostionModeArr = {IWanosAudioEffect.WanosEffectSpatialPostionMode.WHOLE_CAR, IWanosAudioEffect.WanosEffectSpatialPostionMode.MAIN_DRIVER, IWanosAudioEffect.WanosEffectSpatialPostionMode.COPILOT_DRIVER, IWanosAudioEffect.WanosEffectSpatialPostionMode.LEFT_BACK, IWanosAudioEffect.WanosEffectSpatialPostionMode.RIGHT_BACK};
        int i = 0;
        while (i < 5) {
            iWanosEffectSpatialPostionModeArr2[i] = wanosEffectSpatialPostionModeArr[i];
            i++;
        }
        if (iWanosEffectSpatialPostionModeArr != null) {
            for (int i2 = 0; i2 < iWanosEffectSpatialPostionModeArr.length; i2++) {
                iWanosEffectSpatialPostionModeArr2[i + i2] = iWanosEffectSpatialPostionModeArr[i2];
            }
        }
        return iWanosEffectSpatialPostionModeArr2;
    }

    public final void b(Context context, int i) {
        AudioManager audioManagerA = d.a.a(context);
        if (audioManagerA != null) {
            audioManagerA.setParameters(String.format("wanos_effect=(%s)", Integer.valueOf(i)));
        }
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public void setWanosTuningConfigMode(Context context, IWanosAudioEffect.IWanosEffectSceneMode iWanosEffectSceneMode, IWanosAudioEffect.IWanosEffectSpatialPostionMode iWanosEffectSpatialPostionMode) {
        setWanosTuningConfigMode(context, iWanosEffectSceneMode, iWanosEffectSpatialPostionMode, null, null);
    }

    @Override // com.wanos.sdk.effect.IWanosAudioEffect
    public void setWanosTuningConfigMode(Context context, IWanosAudioEffect.IWanosEffectSceneMode iWanosEffectSceneMode, IWanosAudioEffect.IWanosEffectSpatialPostionMode iWanosEffectSpatialPostionMode, IWanosAudioEffect.IWanosEffectSceneMode[] iWanosEffectSceneModeArr, IWanosAudioEffect.IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArr) {
        IWanosAudioEffect.IWanosEffectSceneMode[] iWanosEffectSceneModeArrA = a(iWanosEffectSceneModeArr);
        IWanosAudioEffect.IWanosEffectSpatialPostionMode[] iWanosEffectSpatialPostionModeArrA = a(iWanosEffectSpatialPostionModeArr);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= iWanosEffectSceneModeArrA.length) {
                i2 = 0;
                break;
            } else if (iWanosEffectSceneModeArrA[i2] == iWanosEffectSceneMode) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = 0;
        while (true) {
            if (i3 >= iWanosEffectSpatialPostionModeArrA.length) {
                break;
            }
            if (iWanosEffectSpatialPostionModeArrA[i3] == iWanosEffectSpatialPostionMode) {
                i = i3;
                break;
            }
            i3++;
        }
        int length = (iWanosEffectSceneModeArrA.length * i) + i2;
        a(context, length);
        b.getInstance(context).put(b(), i2);
        b.getInstance(context).put(c(), i);
        b.getInstance(context).put(a(), length);
        c.a("wanosAudioEffect modelIndex:" + length + "---parameters:wanos_setWanosTuningConfigMode=(%s)");
    }

    public final void a(Context context, int i) {
        AudioManager audioManagerA = d.a.a(context);
        if (audioManagerA != null) {
            audioManagerA.setParameters(String.format("wanos_setWanosTuningConfigMode=(%s)", Integer.valueOf(i)));
        }
    }
}
