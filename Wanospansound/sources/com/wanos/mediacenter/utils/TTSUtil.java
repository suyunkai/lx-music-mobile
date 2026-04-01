package com.wanos.mediacenter.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.wanos.mediacenter.MediaCenterManager;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class TTSUtil {
    private static final String TAG = "wanos:[TTSUtil]";
    private Context context;
    private final TTSListener listener;
    private TextToSpeech textToSpeech;
    private boolean initialized = false;
    private float defaultSpeechRate = 1.0f;
    private float defaultPitch = 1.0f;
    private Locale defaultLocale = Locale.CHINESE;
    private String utteranceId = "utteranceId";
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() { // from class: com.wanos.mediacenter.utils.TTSUtil.1
        @Override // java.lang.Runnable
        public void run() {
            if (TTSUtil.this.listener != null) {
                TTSUtil.this.listener.onSpeechDone();
            }
        }
    };
    private int delayTime = AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS;

    public interface TTSListener {
        void onInitFailure();

        void onInitSuccess();

        void onSpeechDone();

        void onSpeechError(String str);

        void onSpeechStart();
    }

    public TTSUtil(Context context, final TTSListener tTSListener) {
        this.context = context;
        this.listener = tTSListener;
        TextToSpeech textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() { // from class: com.wanos.mediacenter.utils.TTSUtil.2
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public void onInit(int i) {
                if (i == 0) {
                    TTSUtil.this.initialized = true;
                    TTSListener tTSListener2 = tTSListener;
                    if (tTSListener2 != null) {
                        tTSListener2.onInitSuccess();
                        return;
                    }
                    return;
                }
                TTSListener tTSListener3 = tTSListener;
                if (tTSListener3 != null) {
                    tTSListener3.onInitFailure();
                }
            }
        });
        this.textToSpeech = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.wanos.mediacenter.utils.TTSUtil.3
            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                Log.d(TTSUtil.TAG, "TTS TTSUtil onStart: " + str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                Log.d(TTSUtil.TAG, "TTS TTSUtil onDone: " + str);
                TTSUtil.this.handler.removeCallbacks(TTSUtil.this.runnable);
                TTSListener tTSListener2 = tTSListener;
                if (tTSListener2 != null) {
                    tTSListener2.onSpeechDone();
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
                Log.d(TTSUtil.TAG, "TTS TTSUtil onError: " + str);
            }
        });
    }

    public void setDefaultSpeechRate(float f) {
        this.defaultSpeechRate = f;
    }

    public void setDefaultPitch(float f) {
        this.defaultPitch = f;
    }

    public void setDefaultLocale(Locale locale) {
        this.defaultLocale = locale;
    }

    public void speak(String str) {
        speak(str, this.utteranceId);
    }

    public void speak(String str, String str2) {
        speak(str, str2, this.defaultLocale, this.defaultSpeechRate, this.defaultPitch);
    }

    public void speak(String str, String str2, Locale locale, float f, float f2) {
        if (this.initialized) {
            if (MediaCenterManager.isNeedTtsAudioFocus) {
                System.out.println("wanos-----------------------------------初始化音源");
                this.textToSpeech.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build());
            }
            this.textToSpeech.setLanguage(locale);
            this.textToSpeech.setSpeechRate(f);
            this.textToSpeech.setPitch(f2);
            this.textToSpeech.speak(str, 0, new Bundle(), str2);
            this.handler.removeCallbacks(this.runnable);
            this.handler.postDelayed(this.runnable, this.delayTime);
        }
    }

    public void release() {
        TextToSpeech textToSpeech = this.textToSpeech;
        if (textToSpeech != null) {
            textToSpeech.stop();
            this.textToSpeech.shutdown();
        }
    }
}
