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
 * 在指定声道上播放正弦波测试音，用于验证各喇叭位置
 */
public class TestToneGenerator {

    private static final String TAG = "TestToneGenerator";
    private static final int SAMPLE_RATE = 48000;
    private static final int DEFAULT_DURATION_MS = 1500;
    private static final int FADE_MS = 50; // 淡入淡出时间

    private AudioTrack audioTrack;
    private Thread playThread;
    private volatile boolean isPlaying = false;

    /**
     * 播放测试音
     * @param layout   目标声道布局
     * @param channelIndex 声道索引（在布局内的位置）
     * @param frequency 频率 (Hz)，范围 100~2000
     * @param volume    音量 0.0~1.0
     */
    public synchronized void play(Context context, ChannelLayout layout,
                                  int channelIndex, float frequency, float volume) {
        stop(); // 停止之前的播放

        int channelCount = layout.getChannelCount();
        if (channelIndex < 0 || channelIndex >= channelCount) {
            Log.e(TAG, "Invalid channel index " + channelIndex + " for layout " + layout.getDisplayName());
            return;
        }

        frequency = Math.max(100f, Math.min(2000f, frequency));
        volume = Math.max(0f, Math.min(1f, volume));

        final float freq = frequency;
        final float vol = volume;

        playThread = new Thread(() -> {
            try {
                playToneInternal(context, layout, channelIndex, freq, vol);
            } catch (Exception e) {
                Log.e(TAG, "Test tone playback failed: " + e.getMessage());
            } finally {
                isPlaying = false;
            }
        }, "TestToneThread");
        isPlaying = true;
        playThread.start();
    }

    private void playToneInternal(Context context, ChannelLayout layout,
                                  int channelIndex, float frequency, float volume) {
        int channelCount = layout.getChannelCount();
        int channelMask = layout.getChannelMask();
        int totalSamples = (SAMPLE_RATE * DEFAULT_DURATION_MS) / 1000;
        int fadeSamples = (SAMPLE_RATE * FADE_MS) / 1000;

        // 生成交错浮点 PCM：仅目标声道有声，其余静音
        float[] buffer = new float[totalSamples * channelCount];
        for (int i = 0; i < totalSamples; i++) {
            float sample = (float) (Math.sin(2.0 * Math.PI * frequency * i / SAMPLE_RATE) * volume);

            // 淡入淡出包络，避免爆音
            if (i < fadeSamples) {
                sample *= (float) i / fadeSamples;
            } else if (i > totalSamples - fadeSamples) {
                sample *= (float) (totalSamples - i) / fadeSamples;
            }

            buffer[i * channelCount + channelIndex] = sample;
        }

        int bufferBytes = buffer.length * 4; // float = 4 bytes

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
                .setBufferSizeInBytes(bufferBytes)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build();

        // 路由到多声道总线
        routeToMultichannelBus(context, audioTrack);

        audioTrack.write(buffer, 0, buffer.length, AudioTrack.WRITE_BLOCKING);
        audioTrack.play();

        // 等待播放完成
        try {
            Thread.sleep(DEFAULT_DURATION_MS + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        releaseTrack();
    }

    /**
     * 将 AudioTrack 路由到多声道总线设备
     */
    private void routeToMultichannelBus(Context context, AudioTrack track) {
        if (Build.VERSION.SDK_INT < 23) return;

        HardwareDetector.Result hw = HardwareDetector.getCachedResult();
        if (hw == null) {
            hw = HardwareDetector.detect(context);
        }
        if (!hw.available || hw.deviceId < 0) return;

        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null) return;

        AudioDeviceInfo[] devices = am.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for (AudioDeviceInfo device : devices) {
            if (device.getId() == hw.deviceId) {
                track.setPreferredDevice(device);
                Log.d(TAG, "Test tone routed to device id=" + hw.deviceId +
                        " (" + hw.busAddress + ")");
                return;
            }
        }
    }

    /**
     * 停止当前测试音
     */
    public synchronized void stop() {
        isPlaying = false;
        if (playThread != null) {
            playThread.interrupt();
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

    public boolean isPlaying() {
        return isPlaying;
    }
}
