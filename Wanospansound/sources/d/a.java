package d;

import android.content.Context;
import android.media.AudioManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    public static AudioManager a(Context context) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        if (audioManager == null) {
            c.b("Audio Manager load fail!!!!");
        }
        return audioManager;
    }
}
