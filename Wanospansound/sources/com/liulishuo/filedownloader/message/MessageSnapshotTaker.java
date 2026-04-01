package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.download.DownloadStatusCallback;
import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class MessageSnapshotTaker {
    public static MessageSnapshot take(byte b2, FileDownloadModel fileDownloadModel) {
        return take(b2, fileDownloadModel, null);
    }

    public static MessageSnapshot catchCanReusedOldFile(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, length);
            }
            return new LargeMessageSnapshot.CompletedSnapshot(i, true, length);
        }
        if (z) {
            return new SmallMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, (int) length);
        }
        return new SmallMessageSnapshot.CompletedSnapshot(i, true, (int) length);
    }

    public static MessageSnapshot catchWarn(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.WarnFlowDirectlySnapshot(i, j, j2);
            }
            return new LargeMessageSnapshot.WarnMessageSnapshot(i, j, j2);
        }
        if (z) {
            return new SmallMessageSnapshot.WarnFlowDirectlySnapshot(i, (int) j, (int) j2);
        }
        return new SmallMessageSnapshot.WarnMessageSnapshot(i, (int) j, (int) j2);
    }

    public static MessageSnapshot catchException(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new LargeMessageSnapshot.ErrorMessageSnapshot(i, j, th);
        }
        return new SmallMessageSnapshot.ErrorMessageSnapshot(i, (int) j, th);
    }

    public static MessageSnapshot catchPause(BaseDownloadTask baseDownloadTask) {
        if (baseDownloadTask.isLargeFile()) {
            return new LargeMessageSnapshot.PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getLargeFileSoFarBytes(), baseDownloadTask.getLargeFileTotalBytes());
        }
        return new SmallMessageSnapshot.PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
    }

    public static MessageSnapshot takeBlockCompleted(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.getStatus() != -3) {
            throw new IllegalStateException(FileDownloadUtils.formatString("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus())));
        }
        return new BlockCompleteMessage.BlockCompleteMessageImpl(messageSnapshot);
    }

    public static MessageSnapshot take(byte b2, FileDownloadModel fileDownloadModel, DownloadStatusCallback.ProcessParams processParams) {
        MessageSnapshot errorMessageSnapshot;
        IllegalStateException illegalStateException;
        int id = fileDownloadModel.getId();
        if (b2 == -4) {
            throw new IllegalStateException(FileDownloadUtils.formatString("please use #catchWarn instead %d", Integer.valueOf(id)));
        }
        if (b2 == -3) {
            if (fileDownloadModel.isLargeFile()) {
                return new LargeMessageSnapshot.CompletedSnapshot(id, false, fileDownloadModel.getTotal());
            }
            return new SmallMessageSnapshot.CompletedSnapshot(id, false, (int) fileDownloadModel.getTotal());
        }
        if (b2 != -1) {
            if (b2 == 1) {
                if (fileDownloadModel.isLargeFile()) {
                    return new LargeMessageSnapshot.PendingMessageSnapshot(id, fileDownloadModel.getSoFar(), fileDownloadModel.getTotal());
                }
                return new SmallMessageSnapshot.PendingMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), (int) fileDownloadModel.getTotal());
            }
            if (b2 == 2) {
                String filename = fileDownloadModel.isPathAsDirectory() ? fileDownloadModel.getFilename() : null;
                if (fileDownloadModel.isLargeFile()) {
                    return new LargeMessageSnapshot.ConnectedMessageSnapshot(id, processParams.isResuming(), fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
                }
                return new SmallMessageSnapshot.ConnectedMessageSnapshot(id, processParams.isResuming(), (int) fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
            }
            if (b2 == 3) {
                if (fileDownloadModel.isLargeFile()) {
                    return new LargeMessageSnapshot.ProgressMessageSnapshot(id, fileDownloadModel.getSoFar());
                }
                return new SmallMessageSnapshot.ProgressMessageSnapshot(id, (int) fileDownloadModel.getSoFar());
            }
            if (b2 != 5) {
                if (b2 == 6) {
                    return new MessageSnapshot.StartedMessageSnapshot(id);
                }
                String string = FileDownloadUtils.formatString("it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b2));
                FileDownloadLog.w(MessageSnapshotTaker.class, "it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b2));
                if (processParams.getException() != null) {
                    illegalStateException = new IllegalStateException(string, processParams.getException());
                } else {
                    illegalStateException = new IllegalStateException(string);
                }
                if (fileDownloadModel.isLargeFile()) {
                    return new LargeMessageSnapshot.ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), illegalStateException);
                }
                return new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), illegalStateException);
            }
            if (fileDownloadModel.isLargeFile()) {
                errorMessageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(id, fileDownloadModel.getSoFar(), processParams.getException(), processParams.getRetryingTimes());
            } else {
                errorMessageSnapshot = new SmallMessageSnapshot.RetryMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), processParams.getException(), processParams.getRetryingTimes());
            }
        } else if (fileDownloadModel.isLargeFile()) {
            errorMessageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), processParams.getException());
        } else {
            errorMessageSnapshot = new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), processParams.getException());
        }
        return errorMessageSnapshot;
    }
}
