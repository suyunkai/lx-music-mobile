package com.wanos.commonlibrary.mediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerEnum {

    public enum AppType {
        App,
        Sdk
    }

    public enum MediaInfoCallbackType {
        supporFavor,
        favorStatus,
        mediaInfoType,
        playMode
    }

    public enum MediaType {
        Music,
        Audiobook,
        Unknown
    }

    public enum PauseStatus {
        NO_PAUSE,
        PAUSING,
        PAUSED
    }

    public enum PlayListLoadStatus {
        NO_LOAD,
        START_LOAD,
        COMPLETE_LOAD,
        FAIL_LOAD
    }

    public enum PreprareStatus {
        NO_PREPRARE,
        ONLY_PREPRAREING,
        PREPRAREING,
        PREPRAREED,
        PREPRARE_ERROR
    }

    public enum UpdateLrcCallbackType {
        START,
        SUCCESS,
        LOADING,
        FAILED
    }

    public enum CallBackState {
        PREPARING("MediaPlayer--准备中..."),
        PREPARE("MediaPlayer--准备完毕"),
        STARTED("MediaPlayer--播放开始"),
        COMPLETE("MediaPlayer--播放结束"),
        ERROR("MediaPlayer--播放错误"),
        EXCEPTION("MediaPlayer--播放异常"),
        INFO("MediaPlayer--媒体播放时出现信息或者警告"),
        PAUSING("MediaPlayer--播放暂停中..."),
        PAUSE("MediaPlayer--播放暂停"),
        PROGRESS("MediaPlayer--播放进度回调"),
        SEEK_COMPLETE("MediaPlayer--拖动到尾端"),
        VIDEO_SIZE_CHANGE("MediaPlayer--读取视频大小"),
        BUFFER_UPDATE("MediaPlayer--更新流媒体缓存状态"),
        FORMATE_NOT_SURPORT("MediaPlayer--音视频格式可能不支持"),
        SURFACEVIEW_NULL("SurfaceView--还没初始化"),
        SURFACEVIEW_NOT_ARREADY("SurfaceView--还没准备好"),
        SURFACEVIEW_CHANGE("SurfaceView--Holder改变"),
        SURFACEVIEW_CREATE("SurfaceView--Holder创建"),
        SURFACEVIEW_DESTROY("SurfaceView--Holder销毁"),
        TIMEOUT("MediaPlayer--加载数据超时"),
        POSITION("MediaPlayer--播放位置回调 单位毫秒");

        private final String state;

        CallBackState(String str) {
            this.state = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.state;
        }
    }

    public enum PlayMode {
        randomplay,
        orderplay,
        singleloopplay,
        listloopplay;

        public static PlayMode intToEnum(int i) {
            if (i == 0) {
                return randomplay;
            }
            if (i == 1) {
                return orderplay;
            }
            if (i == 2) {
                return singleloopplay;
            }
            if (i != 3) {
                return null;
            }
            return listloopplay;
        }

        public static int LoopModeMap(PlayMode playMode) {
            int i = AnonymousClass1.$SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[playMode.ordinal()];
            if (i == 1) {
                return 2;
            }
            if (i != 2) {
                return i != 3 ? -1 : 1;
            }
            return 0;
        }
    }

    /* JADX INFO: renamed from: com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode;

        static {
            int[] iArr = new int[PlayMode.values().length];
            $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode = iArr;
            try {
                iArr[PlayMode.randomplay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[PlayMode.listloopplay.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[PlayMode.singleloopplay.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
