package a;

import android.content.Context;
import android.media.AudioManager;
import com.wanos.sdk.audio.IWanosAudioConfig;
import com.wanos.sdk.audio.IWanosAudioUpmix;

/* JADX INFO: loaded from: classes.dex */
public class b implements IWanosAudioUpmix {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IWanosAudioConfig f9a;

    public b(Context context, IWanosAudioConfig iWanosAudioConfig) {
        this.f9a = iWanosAudioConfig;
        a(context, d.b.getInstance(context).getInt("wanos_upmix", -1));
    }

    public final void a(Context context, int i) {
        AudioManager audioManagerA = d.a.a(context);
        if (audioManagerA != null) {
            audioManagerA.setParameters(String.format("wanos_upmix=(%s)", Integer.valueOf(i)));
        }
    }

    @Override // com.wanos.sdk.audio.IWanosAudioUpmix
    public boolean getWanosUpmixSwitchState(Context context) {
        return d.b.getInstance(context).getInt("wanos_upmix") > 0;
    }

    @Override // com.wanos.sdk.audio.IWanosAudioUpmix
    public void toggleWanosUpmix(Context context, boolean z) {
        IWanosAudioConfig iWanosAudioConfig = this.f9a;
        int wanosAudioOutputChannelNum = iWanosAudioConfig == null ? 0 : iWanosAudioConfig.getWanosAudioOutputChannelNum(context);
        if (wanosAudioOutputChannelNum == 0) {
            wanosAudioOutputChannelNum = 10;
        }
        if (!z) {
            wanosAudioOutputChannelNum = -1;
        }
        a(context, wanosAudioOutputChannelNum);
        d.b.getInstance(context).put("wanos_upmix", wanosAudioOutputChannelNum);
    }
}
