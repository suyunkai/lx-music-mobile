package cn.toside.music.mobile.spatialAudio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.util.Log;

/**
 * 声道测试音生成器
 * 支持单次播放和持续循环播放，在指定声道上播放正弦波测试音
 */
public class TestToneGenerator {

    private static final String TAG = "TestToneGenerator";
    private static final int SAMPLE_RATE = 48000;
    private static final int FADE_MS = 30;

    private AudioTrack audioTrack;
    private Thread playThread;
    private volatile boolean isPlaying = false;
    private volatile boolean shouldLoop = false;

    /**
     * 播放测试音（单次，1.5秒）
     */
    public synchronized void play(Context context, ChannelLayout layout,
                                  int channelIndex, float frequency, float volume) {
        playInternal(context, layout, channelIndex, frequency, volume, false);
    }

    /**
     * 持续播放测试音（循环，直到调用 stop）
     */
    public synchronized void playLoop(Context context, ChannelLayout layout,
                                      int channelIndex, float frequency, float volume) {
        playInternal(context, layout, channelIndex, frequency, volume, true);
    }

    private void playInternal(Context context, ChannelLayout layout,
                              int channelIndex, float frequency, float volume, boolean loop) {
        stop();

        int channelCount = layout.getChannelCount();
        if (channelIndex < 0 || channelIndex >= channelCount) {
            Log.e(TAG, "Invalid channel index " + channelIndex + " for " + layout.getDisplayName());
            return;
        }

        frequency = Math.max(20f, Math.min(4000f, frequency));
        volume = Math.max(0f, Math.min(1f, volume));
        shouldLoop = loop;

        final float freq = frequency;
        final float vol = volume;
        final Context ctx = context;

        playThread = new Thread(() -> {
            try {
                streamTone(ctx, layout, channelIndex, freq, vol);
            } catch (Exception e) {
                Log.e(TAG, "Test tone failed: " + e.getMessage());
            } finally {
                isPlaying = false;
                shouldLoop = false;
            }
        }, "TestToneThread");
        isPlaying = true;
        playThread.start();
    }

    private void streamTone(Context context, ChannelLayout layout,
                            int channelIndex, float frequency, float volume) {
        int channelCount = layout.getChannelCount();
        int channelMask = layout.getChannelMask();

        // 每次写入 0.1 秒的数据块
        int chunkSamples = SAMPLE_RATE / 10;
        int fadeSamples = (SAMPLE_RATE * FADE_MS) / 1000;
        // 单次模式播放 1.5 秒
        int totalSamplesOnce = (int) (SAMPLE_RATE * 1.5);

        int bufferSize = chunkSamples * channelCount * 4; // float = 4 bytes
        int minBuf = AudioTrack.getMinBufferSize(SAMPLE_RATE,
                channelMask, AudioFormat.ENCODING_PCM_FLOAT);
        if (bufferSize < minBuf) bufferSize = minBuf;

        audioTrack = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setSampleRate(SAMPLE_RATE)
                        .setEncoding(AudioFormat.ENCODING_PCM_FLOAT)
                        .setChannelMask(channelMask)
                        .build())
                .setBufferSizeInBytes(bufferSize * 2)
                .setTransferMode(AudioTrack.MODE_STREAM)
                .build();

        routeToMultichannelBus(context, audioTrack);
        audioTrack.play();

        float[] chunk = new float[chunkSamples * channelCount];
        int samplePos = 0;

        while (isPlaying && (shouldLoop || samplePos < totalSamplesOnce)) {
            int samplesToWrite = Math.min(chunkSamples,
                    shouldLoop ? chunkSamples : totalSamplesOnce - samplePos);

            java.util.Arrays.fill(chunk, 0f);

            for (int i = 0; i < samplesToWrite; i++) {
                int globalPos = samplePos + i;
                float sample = (float) (Math.sin(2.0 * Math.PI * frequency * globalPos / SAMPLE_RATE) * volume);

                // 淡入（起始时）
                if (!shouldLoop && globalPos < fadeSamples) {
                    sample *= (float) globalPos / fadeSamples;
                }
                // 淡出（单次模式结束时）
                if (!shouldLoop && globalPos > totalSamplesOnce - fadeSamples) {
                    sample *= (float) (totalSamplesOnce - globalPos) / fadeSamples;
                }

                chunk[i * channelCount + channelIndex] = sample;
            }

            int written = audioTrack.write(chunk, 0, samplesToWrite * channelCount,
                    AudioTrack.WRITE_BLOCKING);
            if (written < 0) break;

            samplePos += samplesToWrite;

            // 循环模式下持续重置位置（避免 float 精度问题）
            if (shouldLoop && samplePos > SAMPLE_RATE * 60) {
                samplePos = 0;
            }
        }

        releaseTrack();
    }

    private void routeToMultichannelBus(Context context, AudioTrack track) {
        if (Build.VERSION.SDK_INT < 23) return;

        HardwareDetector.Result hw = HardwareDetector.getCachedResult();
        if (hw == null) hw = HardwareDetector.detect(context);
        if (!hw.available || hw.deviceId < 0) return;

        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null) return;

        for (AudioDeviceInfo device : am.getDevices(AudioManager.GET_DEVICES_OUTPUTS)) {
            if (device.getId() == hw.deviceId) {
                track.setPreferredDevice(device);
                return;
            }
        }
    }

    public synchronized void stop() {
        isPlaying = false;
        shouldLoop = false;
        if (playThread != null) {
            playThread.interrupt();
            try { playThread.join(200); } catch (InterruptedException ignored) {}
            playThread = null;
        }
        releaseTrack();
    }

    private void releaseTrack() {
        if (audioTrack != null) {
            try {
                if (audioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
                    audioTrack.stop();
                }
                audioTrack.release();
            } catch (Exception e) {
                Log.w(TAG, "Error releasing AudioTrack: " + e.getMessage());
            }
            audioTrack = null;
        }
    }

    public boolean isPlaying() { return isPlaying; }
}
