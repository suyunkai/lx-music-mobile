package c;

import a.b;
import android.content.Context;
import com.wanos.sdk.IWanosAudio;
import com.wanos.sdk.audio.IWanosAudioConfig;
import com.wanos.sdk.audio.IWanosAudioUpmix;
import com.wanos.sdk.effect.IWanosAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class a implements IWanosAudio {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IWanosAudioConfig f11a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IWanosAudioUpmix f12b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IWanosAudioEffect f13c;

    public a(Context context) {
        a.a aVar = new a.a(context);
        this.f11a = aVar;
        this.f12b = new b(context, aVar);
        this.f13c = new b.a(context);
    }

    @Override // com.wanos.sdk.IWanosAudio
    public IWanosAudioConfig getWanosAudioConfig() {
        return this.f11a;
    }

    @Override // com.wanos.sdk.IWanosAudio
    public IWanosAudioEffect getWanosAudioEffect() {
        return this.f13c;
    }

    @Override // com.wanos.sdk.IWanosAudio
    public IWanosAudioUpmix getWanosAudioUpmix() {
        return this.f12b;
    }
}
