package com.wanos.util;

/**
 * Wanos 全景声原生方法桥接
 *
 * 包名和类名必须与 libWanosPlayer.so 中的 JNI 注册完全一致。
 * 这些 native 方法由 Wanos 的 C++ 库实现空间音频渲染。
 */
public class NativeMethod {
    private static NativeMethod singleton;
    private static boolean libraryLoaded = false;

    /**
     * 初始化全景声渲染引擎
     *
     * @param processingChannels 处理声道数 (固定传 30)
     * @param reverbMode         混响模式 (0=关闭)
     * @param bufferSize         缓冲区大小 (1024)
     * @param sampleRate         采样率 (48000)
     * @param reserved           保留参数 (0)
     * @param layoutName         声道布局名 ("200"/"400"/"510"/"512"/"714" 等)
     */
    public native void initWanosRender(int processingChannels, int reverbMode,
                                       int bufferSize, int sampleRate,
                                       int reserved, String layoutName);

    /**
     * 释放全景声渲染引擎
     */
    public native void freeWanosRender();

    /**
     * 空间音频渲染 - 将多个音频对象混合为多声道输出
     *
     * @param inputData   输入PCM数据 float[对象数][bufferSize]
     * @param posX        各对象X坐标 float[]
     * @param posY        各对象Y坐标 float[]
     * @param posZ        各对象Z坐标 float[]
     * @param volume      各对象音量 float[]
     * @param size        各对象大小 float[] (可传null)
     * @param objectCount 活跃对象数量
     * @param objectIds   各对象ID int[]
     * @param outputData  输出多声道PCM float[声道数][bufferSize]
     */
    public native void playSpaceAudio(float[][] inputData, float[] posX, float[] posY,
                                      float[] posZ, float[] volume, float[] size,
                                      int objectCount, int[] objectIds,
                                      float[][] outputData);

    /**
     * 设置对象增益
     *
     * @param objectId 对象ID
     * @param gains    各声道增益 float[]
     */
    public native void setObjGain(int objectId, float[] gains);

    /**
     * 设置声场类型
     *
     * @param type 声场类型
     */
    public native void setSFType(int type);

    /**
     * 立体声对象空间定位
     *
     * @param position [x, y, z] 坐标
     * @return 9元素数组 [x,y,z, posL_x,posL_y,posL_z, posR_x,posR_y,posR_z]
     */
    public native float[] stereoPos(float[] position);

    public static NativeMethod getInstance() {
        if (singleton == null) {
            synchronized (NativeMethod.class) {
                if (singleton == null) {
                    singleton = new NativeMethod();
                }
            }
        }
        return singleton;
    }

    /**
     * 加载原生库
     * @return 是否加载成功
     */
    public static boolean loadLibrary() {
        if (libraryLoaded) return true;
        try {
            System.loadLibrary("WanosPlayer");
            libraryLoaded = true;
            return true;
        } catch (UnsatisfiedLinkError e) {
            android.util.Log.e("WanosNative", "Failed to load libWanosPlayer.so: " + e.getMessage());
            return false;
        }
    }

    public static boolean isLibraryLoaded() {
        return libraryLoaded;
    }
}
