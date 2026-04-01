package com.wanos.zero;

import android.util.Log;
import androidx.core.util.Pools;
import com.wanos.zero.ZeroPlayEngineTaskMate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroPlayEngineTaskMate {
    static final int MSG_DELETE_AUDIO = 102;
    static final int MSG_DESTROY = 302;
    static final int MSG_INSERT_AUDIO = 101;
    static final int MSG_REPLAY = 202;
    static final int MSG_RESET = 301;
    static final int MSG_START = 201;
    static final int MSG_STOP = 203;
    private static final String TAG = "wanos[Zero]-ZeroPlayEngineTaskMate";
    private final ITaskCallback iTaskCallback;
    private final ArrayBlockingQueue<MediaMessage> mTaskQueue = new ArrayBlockingQueue<>(100);
    private Pools.SynchronizedPool<MediaMessage> mTaskPools = new Pools.SynchronizedPool<>(50);

    public interface ITaskCallback {
        boolean onAudioTrackTask();

        void onDeleteTask(int i);

        void onDestroyMediaPlayer();

        void onInsertTask(BallEntity ballEntity);

        void onReplaying();

        void onResetMediaPlayer();

        void onStartPlaying();

        void onStopPlaying();
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface Message {
    }

    public ZeroPlayEngineTaskMate(ITaskCallback iTaskCallback) {
        this.iTaskCallback = iTaskCallback;
    }

    public boolean isTaskEmpty() {
        return this.mTaskQueue.isEmpty();
    }

    public boolean onExecuteTask() {
        MediaMessage mediaMessagePoll = this.mTaskQueue.poll();
        if (mediaMessagePoll != null) {
            int messageType = mediaMessagePoll.getMessageType();
            if (messageType == 101) {
                this.iTaskCallback.onInsertTask(mediaMessagePoll.getBallEntity());
            } else if (messageType == 102) {
                this.iTaskCallback.onDeleteTask(mediaMessagePoll.getBallId());
            } else if (messageType == 301) {
                this.iTaskCallback.onResetMediaPlayer();
            } else if (messageType != 302) {
                switch (messageType) {
                    case 201:
                        this.iTaskCallback.onStartPlaying();
                        break;
                    case 202:
                        this.iTaskCallback.onReplaying();
                        break;
                    case 203:
                        this.iTaskCallback.onStopPlaying();
                        break;
                }
            } else {
                this.iTaskCallback.onDestroyMediaPlayer();
            }
            if (this.mTaskPools != null) {
                mediaMessagePoll.setRelease();
                this.mTaskPools.release(mediaMessagePoll);
            }
        }
        return this.iTaskCallback.onAudioTrackTask();
    }

    public void destroy() {
        this.mTaskQueue.clear();
        this.mTaskPools = null;
    }

    public void setMediaInsert(BallEntity ballEntity) {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(101);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setMediaInsert: 播放器已销毁，无法执行当前操作");
            return;
        }
        mediaMessageObtainTaskEntity.setInsertBallEntity(ballEntity);
        if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
            return;
        }
        Log.e(TAG, "setMediaInsert: 音源添加失败，队列长度=" + this.mTaskQueue.size());
    }

    static /* synthetic */ boolean lambda$removeInsertTask$0(MediaMessage mediaMessage) {
        return mediaMessage.getMessageType() == 101;
    }

    public void removeInsertTask() {
        Log.i(TAG, "removeInsertTask: 清理添加音源任务 = " + this.mTaskQueue.removeIf(new Predicate() { // from class: com.wanos.zero.ZeroPlayEngineTaskMate$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ZeroPlayEngineTaskMate.lambda$removeInsertTask$0((ZeroPlayEngineTaskMate.MediaMessage) obj);
            }
        }));
    }

    public void setMediaDelete(int i) {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(102);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setMediaDelete: 播放器已销毁，无法执行当前操作");
            return;
        }
        mediaMessageObtainTaskEntity.setBallId(i);
        if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
            return;
        }
        Log.e(TAG, "setMediaDelete: 音源删除失败，队列长度=" + this.mTaskQueue.size());
    }

    public void setMediaDestroy() {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(302);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setMediaDestroy: 播放器已销毁，无法执行当前操作");
        } else {
            if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
                return;
            }
            Log.e(TAG, "setMediaDestroy: 销毁失败，队列长度=" + this.mTaskQueue.size());
        }
    }

    public void setStartMedia() {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(201);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setStartMedia: 播放器已销毁，无法执行当前操作");
        } else {
            if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
                return;
            }
            Log.e(TAG, "setStartMedia: 开始播放任务添加失败");
        }
    }

    public void setStopMedia() {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(203);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setStopMedia: 播放器已销毁，无法执行当前操作");
        } else {
            if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
                return;
            }
            Log.e(TAG, "setStopMedia: 停止播放任务添加失败");
        }
    }

    public void setMediaReset() {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(301);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setMediaReset: 播放器已销毁，无法执行当前操作");
        } else {
            if (this.mTaskQueue.offer(mediaMessageObtainTaskEntity)) {
                return;
            }
            Log.e(TAG, "setMediaReset: 重置失败，队列长度=" + this.mTaskQueue.size());
        }
    }

    public void setMediaReplay() {
        MediaMessage mediaMessageObtainTaskEntity = obtainTaskEntity(202);
        if (mediaMessageObtainTaskEntity == null) {
            Log.e(TAG, "setMediaReplay: 播放器已销毁，无法执行当前操作");
        } else {
            Log.d(TAG, "setMediaReplay: 任务添加结果=" + this.mTaskQueue.offer(mediaMessageObtainTaskEntity));
        }
    }

    private MediaMessage obtainTaskEntity(int i) {
        Pools.SynchronizedPool<MediaMessage> synchronizedPool = this.mTaskPools;
        if (synchronizedPool == null) {
            return null;
        }
        MediaMessage mediaMessageAcquire = synchronizedPool.acquire();
        if (mediaMessageAcquire == null) {
            mediaMessageAcquire = new MediaMessage();
        }
        mediaMessageAcquire.setMessageType(i);
        return mediaMessageAcquire;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class MediaMessage {
        private BallEntity ballEntity;
        private int ballId;
        private int messageType;

        private MediaMessage() {
            this.ballId = -1;
        }

        public int getMessageType() {
            return this.messageType;
        }

        public void setMessageType(int i) {
            this.messageType = i;
        }

        public BallEntity getBallEntity() {
            return this.ballEntity;
        }

        public void setInsertBallEntity(BallEntity ballEntity) {
            this.ballEntity = ballEntity;
        }

        public int getBallId() {
            return this.ballId;
        }

        public void setBallId(int i) {
            this.ballId = i;
        }

        public void setRelease() {
            this.ballId = -1;
            this.ballEntity = null;
        }
    }
}
