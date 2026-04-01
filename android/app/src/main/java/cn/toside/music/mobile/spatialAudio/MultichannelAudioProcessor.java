package cn.toside.music.mobile.spatialAudio;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.media3.common.C;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.UnstableApi;

import com.wanos.util.NativeMethod;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * ExoPlayer AudioProcessor - 使用 Wanos 全景声 C++ 引擎进行多声道渲染
 *
 * 支持 PCM_16BIT 和 PCM_FLOAT 输入，输出为 PCM_FLOAT 多声道。
 */
@UnstableApi
public class MultichannelAudioProcessor implements AudioProcessor {

    private static final String TAG = "WanosAudioProcessor";
    private static final int BUFFER_SIZE = 1024;
    private static final int SAMPLE_RATE = 48000;

    private boolean inputIsFloat = false; // 输入是否为 PCM_FLOAT

    private boolean enabled = false;
    private boolean upmixEnabled = true;
    private boolean wanosInitialized = false;
    private boolean libraryAvailable = false;

    private AudioFormat inputAudioFormat = AudioFormat.NOT_SET;
    private AudioFormat pendingInputFormat = AudioFormat.NOT_SET;
    private AudioFormat pendingOutputFormat = AudioFormat.NOT_SET;

    private ChannelLayout targetLayout = ChannelLayout.SURROUND_5_1;

    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private boolean inputEnded;

    // Wanos 处理所需的缓冲区
    private float[][] wanosInput;     // [对象数][BUFFER_SIZE] 输入
    private float[][] wanosOutput;    // [输出声道数][BUFFER_SIZE] 输出
    private float[] posX, posY, posZ; // 对象位置
    private float[] volume;           // 对象音量
    private int[] objectIds;          // 对象ID

    // 输入累积缓冲（ExoPlayer 可能传入非 1024 整数倍的数据）
    private float[] accumLeft;
    private float[] accumRight;
    private int accumCount = 0;

    // 输出暂存（处理后等待 getOutput 读取）
    private ByteBuffer pendingOutput = EMPTY_BUFFER;

    // 是否启用多声道直通保护（防止 ExoPlayer 降混多声道为立体声）
    private boolean multichannelPassthroughEnabled = true;

    // 重映射模式："auto" 自动选择, "passthrough" 强制直通, "remap" 强制降混, "fill" 强制补足
    private String remapMode = "auto";

    // 混音矩阵（REMAP/FILL 模式使用）
    private float[][] mixMatrix = null;
    private int mixSourceCh = 0;
    private int mixTargetCh = 0;

    private enum ProcessMode { PASSTHROUGH, UPMIX, MULTICHANNEL_PASSTHROUGH, MULTICHANNEL_REMAP, MULTICHANNEL_FILL }
    private ProcessMode processMode = ProcessMode.PASSTHROUGH;

    public MultichannelAudioProcessor() {
        libraryAvailable = NativeMethod.loadLibrary();
        if (libraryAvailable) {
            Log.i(TAG, "Wanos native library loaded successfully");
        } else {
            Log.w(TAG, "Wanos native library not available, will use passthrough");
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        Log.d(TAG, "Multichannel " + (enabled ? "enabled" : "disabled"));
    }

    public boolean isEnabled() { return enabled; }

    public void setUpmixEnabled(boolean upmixEnabled) {
        this.upmixEnabled = upmixEnabled;
    }

    public boolean isUpmixEnabled() { return upmixEnabled; }

    public void setTargetLayout(ChannelLayout layout) {
        boolean needReinit = this.targetLayout != layout && wanosInitialized;
        this.targetLayout = layout;
        if (needReinit) {
            initWanos();
        }
        Log.d(TAG, "Target layout: " + layout.getDisplayName() +
                " (wanos: " + layout.getWanosName() + ")");
    }

    public ChannelLayout getTargetLayout() { return targetLayout; }

    public void setRemapMode(String mode) {
        this.remapMode = mode != null ? mode : "auto";
        Log.d(TAG, "Remap mode: " + this.remapMode);
    }

    public String getRemapMode() { return remapMode; }

    public String getProcessModeDescription() {
        if (!enabled) return "passthrough";
        return processMode == ProcessMode.UPMIX
                ? "wanos_upmix_" + targetLayout.getDisplayName()
                : "passthrough";
    }

    public int getInputChannelCount() {
        return inputAudioFormat != AudioFormat.NOT_SET ? inputAudioFormat.channelCount : 0;
    }

    /**
     * 初始化 Wanos 渲染引擎
     */
    private synchronized void initWanos() {
        if (!libraryAvailable) return;
        try {
            NativeMethod nm = NativeMethod.getInstance();
            if (wanosInitialized) {
                nm.freeWanosRender();
            }
            // 使用实际输入采样率（常见: 44100 或 48000）
            int sampleRate = (inputAudioFormat != AudioFormat.NOT_SET)
                    ? inputAudioFormat.sampleRate : SAMPLE_RATE;
            nm.initWanosRender(30, 0, BUFFER_SIZE, sampleRate, 0,
                    targetLayout.getWanosName());
            wanosInitialized = true;

            int outCh = targetLayout.getChannelCount();
            // 立体声输入 = 2个对象（左声道 + 右声道）
            wanosInput = new float[2][BUFFER_SIZE];
            wanosOutput = new float[outCh][BUFFER_SIZE];
            posX = new float[]{-0.5f, 0.5f};  // 左声道偏左, 右声道偏右
            posY = new float[]{1.0f, 1.0f};    // 前方
            posZ = new float[]{0.0f, 0.0f};    // 水平
            volume = new float[]{1.0f, 1.0f};
            objectIds = new int[]{0, 1};

            accumLeft = new float[BUFFER_SIZE];
            accumRight = new float[BUFFER_SIZE];
            accumCount = 0;

            Log.i(TAG, "Wanos engine initialized: layout=" + targetLayout.getWanosName() +
                    ", outCh=" + outCh);
        } catch (Exception e) {
            Log.e(TAG, "Failed to init Wanos: " + e.getMessage());
            wanosInitialized = false;
        }
    }

    @NonNull
    @Override
    public AudioFormat configure(@NonNull AudioFormat inputAudioFormat) throws UnhandledAudioFormatException {
        this.pendingInputFormat = inputAudioFormat;
        int inputChannels = inputAudioFormat.channelCount;
        int encoding = inputAudioFormat.encoding;

        // 只支持 PCM_16BIT 和 PCM_FLOAT
        boolean supportedEncoding = (encoding == C.ENCODING_PCM_16BIT || encoding == C.ENCODING_PCM_FLOAT);

        // 多声道输入处理（≥3ch）
        if (supportedEncoding && inputChannels > 2 && multichannelPassthroughEnabled) {
            int targetCh = targetLayout.getChannelCount();
            ChannelLayout sourceLayout = ChannelLayout.fromInputChannels(inputChannels);

            // 根据 remapMode 和声道数关系决定处理模式
            boolean needRemap = false;
            boolean needFill = false;

            if ("passthrough".equals(remapMode)) {
                // 强制直通
            } else if ("remap".equals(remapMode) && inputChannels > targetCh) {
                needRemap = true;
            } else if ("fill".equals(remapMode) && inputChannels < targetCh) {
                needFill = true;
            } else if ("auto".equals(remapMode)) {
                if (inputChannels > targetCh) needRemap = true;
                else if (inputChannels < targetCh) needFill = true;
            }

            if (needRemap) {
                // 源声道 > 目标声道：降混重映射
                processMode = ProcessMode.MULTICHANNEL_REMAP;
                mixMatrix = ChannelRemapper.buildMixMatrix(sourceLayout, targetLayout);
                mixSourceCh = inputChannels;
                mixTargetCh = targetCh;
                inputIsFloat = (encoding == C.ENCODING_PCM_FLOAT);
                pendingOutputFormat = new AudioFormat(
                        inputAudioFormat.sampleRate, targetCh, encoding);
                Log.d(TAG, "configure: " + inputChannels + "ch -> REMAP to " +
                        targetLayout.getDisplayName() + " (" + targetCh + "ch)");
                return pendingOutputFormat;
            }

            if (needFill) {
                // 源声道 < 目标声道：补足
                processMode = ProcessMode.MULTICHANNEL_FILL;
                mixMatrix = ChannelRemapper.buildMixMatrix(sourceLayout, targetLayout);
                mixSourceCh = inputChannels;
                mixTargetCh = targetCh;
                inputIsFloat = (encoding == C.ENCODING_PCM_FLOAT);
                pendingOutputFormat = new AudioFormat(
                        inputAudioFormat.sampleRate, targetCh, encoding);
                Log.d(TAG, "configure: " + inputChannels + "ch -> FILL to " +
                        targetLayout.getDisplayName() + " (" + targetCh + "ch)");
                return pendingOutputFormat;
            }

            // 声道匹配或强制直通：原样传递
            processMode = ProcessMode.MULTICHANNEL_PASSTHROUGH;
            pendingOutputFormat = inputAudioFormat;
            Log.d(TAG, "configure: " + inputChannels + "ch " +
                    inputAudioFormat.sampleRate + "Hz -> multichannel passthrough" +
                    (enabled ? "" : " (processor disabled, passthrough protection only)"));
            return pendingOutputFormat;
        }

        if (!supportedEncoding || !enabled || !libraryAvailable) {
            processMode = ProcessMode.PASSTHROUGH;
            pendingOutputFormat = inputAudioFormat;
            if (enabled && !supportedEncoding) {
                Log.w(TAG, "configure: unsupported encoding " + encoding + ", passthrough");
            }
            return inputAudioFormat;
        }

        inputIsFloat = (encoding == C.ENCODING_PCM_FLOAT);

        if (inputChannels == 2 && upmixEnabled && targetLayout.getChannelCount() > 2) {
            processMode = ProcessMode.UPMIX;
            pendingOutputFormat = new AudioFormat(
                    inputAudioFormat.sampleRate,
                    targetLayout.getChannelCount(),
                    encoding
            );
            Log.d(TAG, "configure: stereo " + inputAudioFormat.sampleRate + "Hz " +
                    (inputIsFloat ? "FLOAT" : "PCM16") +
                    " -> Wanos upmix to " + targetLayout.getDisplayName());
            return pendingOutputFormat;
        }

        processMode = ProcessMode.PASSTHROUGH;
        pendingOutputFormat = inputAudioFormat;
        return inputAudioFormat;
    }

    @Override
    public boolean isActive() {
        // UPMIX: 上混处理需要激活
        // MULTICHANNEL_PASSTHROUGH/REMAP/FILL: 必须激活，否则 ExoPlayer 会降混多声道为立体声
        return (enabled && libraryAvailable && processMode == ProcessMode.UPMIX)
                || (multichannelPassthroughEnabled && (
                    processMode == ProcessMode.MULTICHANNEL_PASSTHROUGH
                    || processMode == ProcessMode.MULTICHANNEL_REMAP
                    || processMode == ProcessMode.MULTICHANNEL_FILL));
    }

    @Override
    public void queueInput(@NonNull ByteBuffer input) {
        if (!input.hasRemaining()) return;

        // 多声道直通：原样传递数据，不做任何处理
        if (processMode == ProcessMode.MULTICHANNEL_PASSTHROUGH) {
            int size = input.remaining();
            ByteBuffer out = ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
            out.put(input);
            out.flip();
            pendingOutput = out;
            return;
        }

        // 多声道重映射/补足：应用混音矩阵
        if ((processMode == ProcessMode.MULTICHANNEL_REMAP || processMode == ProcessMode.MULTICHANNEL_FILL)
                && mixMatrix != null) {
            applyMixMatrix(input);
            return;
        }

        if (processMode != ProcessMode.UPMIX) return;

        int inputBytes = input.remaining();
        int bytesPerSample = inputIsFloat ? 4 : 2;
        int bytesPerFrame = 2 * bytesPerSample; // 2 channels
        int totalFrames = inputBytes / bytesPerFrame;
        if (totalFrames == 0) return;

        int outCh = targetLayout.getChannelCount();
        int outBytesPerSample = inputIsFloat ? 4 : 2;
        int maxOutputBlocks = (accumCount + totalFrames + BUFFER_SIZE - 1) / BUFFER_SIZE;
        int maxOutputBytes = maxOutputBlocks * BUFFER_SIZE * outCh * outBytesPerSample;

        ByteBuffer outBuf;
        if (pendingOutput != EMPTY_BUFFER && pendingOutput.hasRemaining()) {
            int existing = pendingOutput.remaining();
            outBuf = ByteBuffer.allocateDirect(existing + maxOutputBytes)
                    .order(ByteOrder.nativeOrder());
            outBuf.put(pendingOutput);
        } else {
            outBuf = ByteBuffer.allocateDirect(maxOutputBytes)
                    .order(ByteOrder.nativeOrder());
        }

        ByteBuffer inputOrdered = input.order(ByteOrder.nativeOrder());
        int outStartPos = outBuf.position();
        int framesRead = 0;

        while (framesRead < totalFrames) {
            int toRead = Math.min(BUFFER_SIZE - accumCount, totalFrames - framesRead);

            if (inputIsFloat) {
                FloatBuffer floatBuf = inputOrdered.asFloatBuffer();
                for (int i = 0; i < toRead; i++) {
                    accumLeft[accumCount + i] = floatBuf.get();
                    accumRight[accumCount + i] = floatBuf.get();
                }
                input.position(input.position() + toRead * 2 * 4);
            } else {
                ShortBuffer shortBuf = inputOrdered.asShortBuffer();
                for (int i = 0; i < toRead; i++) {
                    accumLeft[accumCount + i] = shortBuf.get() / 32768f;
                    accumRight[accumCount + i] = shortBuf.get() / 32768f;
                }
                input.position(input.position() + toRead * 2 * 2);
            }
            accumCount += toRead;
            framesRead += toRead;

            if (accumCount >= BUFFER_SIZE) {
                processWanosBlock(outBuf);
                accumCount = 0;
            }
        }

        int written = outBuf.position() - outStartPos;
        outBuf.limit(outBuf.position());
        outBuf.position(outStartPos);

        if (written > 0) {
            pendingOutput = outBuf;
        }
    }

    /**
     * 处理一个 BUFFER_SIZE 的音频块
     */
    private void processWanosBlock(ByteBuffer outBuf) {
        if (!wanosInitialized) {
            initWanos();
            if (!wanosInitialized) return;
        }

        int outCh = targetLayout.getChannelCount();

        System.arraycopy(accumLeft, 0, wanosInput[0], 0, BUFFER_SIZE);
        System.arraycopy(accumRight, 0, wanosInput[1], 0, BUFFER_SIZE);

        for (int ch = 0; ch < outCh; ch++) {
            java.util.Arrays.fill(wanosOutput[ch], 0f);
        }

        boolean wanosOk = false;
        try {
            NativeMethod.getInstance().playSpaceAudio(
                    wanosInput, posX, posY, posZ, volume, null,
                    2, objectIds, wanosOutput
            );
            wanosOk = true;
        } catch (Exception e) {
            Log.e(TAG, "Wanos playSpaceAudio failed: " + e.getMessage());
        }

        // 将 Wanos 输出 [channel][sample] 交错写入 outBuf [sample][channel]
        if (inputIsFloat) {
            FloatBuffer floatOut = outBuf.asFloatBuffer();
            for (int s = 0; s < BUFFER_SIZE; s++) {
                for (int ch = 0; ch < outCh; ch++) {
                    if (wanosOk) {
                        floatOut.put(Math.max(-1.0f, Math.min(1.0f, wanosOutput[ch][s])));
                    } else {
                        // 降级：立体声直通到前两声道
                        floatOut.put(ch == 0 ? accumLeft[s] : ch == 1 ? accumRight[s] : 0f);
                    }
                }
            }
            outBuf.position(outBuf.position() + BUFFER_SIZE * outCh * 4);
        } else {
            ShortBuffer shortOut = outBuf.asShortBuffer();
            for (int s = 0; s < BUFFER_SIZE; s++) {
                for (int ch = 0; ch < outCh; ch++) {
                    if (wanosOk) {
                        float val = Math.max(-1.0f, Math.min(1.0f, wanosOutput[ch][s]));
                        shortOut.put((short) (val * 32767f));
                    } else {
                        float val = ch == 0 ? accumLeft[s] : ch == 1 ? accumRight[s] : 0f;
                        shortOut.put((short) (val * 32767f));
                    }
                }
            }
            outBuf.position(outBuf.position() + BUFFER_SIZE * outCh * 2);
        }
    }

    /**
     * 应用混音矩阵进行声道重映射或补足
     * output[t] = sum(matrix[t][s] * input[s]) 对每个 sample
     */
    private void applyMixMatrix(@NonNull ByteBuffer input) {
        int inputBytes = input.remaining();
        int bytesPerSample = inputIsFloat ? 4 : 2;
        int bytesPerFrame = mixSourceCh * bytesPerSample;
        int totalFrames = inputBytes / bytesPerFrame;
        if (totalFrames == 0) return;

        int outBytesPerFrame = mixTargetCh * bytesPerSample;
        int outBytes = totalFrames * outBytesPerFrame;

        ByteBuffer outBuf = ByteBuffer.allocateDirect(outBytes).order(ByteOrder.nativeOrder());
        ByteBuffer inputOrdered = input.order(ByteOrder.nativeOrder());

        if (inputIsFloat) {
            FloatBuffer floatIn = inputOrdered.asFloatBuffer();
            FloatBuffer floatOut = outBuf.asFloatBuffer();
            float[] srcFrame = new float[mixSourceCh];

            for (int f = 0; f < totalFrames; f++) {
                // 读取一帧源数据
                for (int s = 0; s < mixSourceCh; s++) {
                    srcFrame[s] = floatIn.get();
                }
                // 应用矩阵生成目标帧
                for (int t = 0; t < mixTargetCh; t++) {
                    float sample = 0f;
                    for (int s = 0; s < mixSourceCh; s++) {
                        if (mixMatrix[t][s] != 0f) {
                            sample += mixMatrix[t][s] * srcFrame[s];
                        }
                    }
                    floatOut.put(Math.max(-1.0f, Math.min(1.0f, sample)));
                }
            }
        } else {
            ShortBuffer shortIn = inputOrdered.asShortBuffer();
            ShortBuffer shortOut = outBuf.asShortBuffer();
            float[] srcFrame = new float[mixSourceCh];

            for (int f = 0; f < totalFrames; f++) {
                for (int s = 0; s < mixSourceCh; s++) {
                    srcFrame[s] = shortIn.get() / 32768f;
                }
                for (int t = 0; t < mixTargetCh; t++) {
                    float sample = 0f;
                    for (int s = 0; s < mixSourceCh; s++) {
                        if (mixMatrix[t][s] != 0f) {
                            sample += mixMatrix[t][s] * srcFrame[s];
                        }
                    }
                    sample = Math.max(-1.0f, Math.min(1.0f, sample));
                    shortOut.put((short) (sample * 32767f));
                }
            }
        }

        input.position(input.limit()); // 消费所有输入
        outBuf.position(0);
        outBuf.limit(outBytes);
        pendingOutput = outBuf;
    }

    @NonNull
    @Override
    public ByteBuffer getOutput() {
        if (pendingOutput != EMPTY_BUFFER && pendingOutput.hasRemaining()) {
            ByteBuffer out = pendingOutput;
            pendingOutput = EMPTY_BUFFER;
            return out;
        }
        ByteBuffer out = outputBuffer;
        outputBuffer = EMPTY_BUFFER;
        return out;
    }

    @Override
    public boolean isEnded() {
        return inputEnded && pendingOutput == EMPTY_BUFFER && outputBuffer == EMPTY_BUFFER;
    }

    @Override
    public void queueEndOfStream() {
        if (accumCount > 0 && wanosInitialized && processMode == ProcessMode.UPMIX) {
            for (int i = accumCount; i < BUFFER_SIZE; i++) {
                accumLeft[i] = 0f;
                accumRight[i] = 0f;
            }
            int outCh = targetLayout.getChannelCount();
            int outBytesPerSample = inputIsFloat ? 4 : 2;
            int outBytes = BUFFER_SIZE * outCh * outBytesPerSample;
            ByteBuffer outBuf = ByteBuffer.allocateDirect(outBytes)
                    .order(ByteOrder.nativeOrder());
            processWanosBlock(outBuf);
            int validBytes = accumCount * outCh * outBytesPerSample;
            outBuf.position(0);
            outBuf.limit(validBytes);
            if (pendingOutput != EMPTY_BUFFER && pendingOutput.hasRemaining()) {
                ByteBuffer combined = ByteBuffer.allocateDirect(
                        pendingOutput.remaining() + validBytes)
                        .order(ByteOrder.nativeOrder());
                combined.put(pendingOutput);
                combined.put(outBuf);
                combined.flip();
                pendingOutput = combined;
            } else {
                pendingOutput = outBuf;
            }
            accumCount = 0;
        }
        inputEnded = true;
    }

    @Override
    public void flush() {
        outputBuffer = EMPTY_BUFFER;
        pendingOutput = EMPTY_BUFFER;
        inputEnded = false;
        inputAudioFormat = pendingInputFormat;
        accumCount = 0;

        if (enabled && libraryAvailable && processMode == ProcessMode.UPMIX) {
            initWanos();
        }
    }

    @Override
    public void reset() {
        flush();
        if (wanosInitialized && libraryAvailable) {
            try {
                NativeMethod.getInstance().freeWanosRender();
            } catch (Exception e) {
                Log.w(TAG, "freeWanosRender failed: " + e.getMessage());
            }
            wanosInitialized = false;
        }
        inputAudioFormat = AudioFormat.NOT_SET;
        pendingInputFormat = AudioFormat.NOT_SET;
        pendingOutputFormat = AudioFormat.NOT_SET;
        processMode = ProcessMode.PASSTHROUGH;
    }
}
