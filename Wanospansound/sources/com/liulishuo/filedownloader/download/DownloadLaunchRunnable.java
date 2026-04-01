package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.download.ConnectTask;
import com.liulishuo.filedownloader.download.ConnectionProfile;
import com.liulishuo.filedownloader.download.DownloadRunnable;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.exception.FileDownloadSecurityException;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadLaunchRunnable implements Runnable, ProcessCallback {
    private static final ThreadPoolExecutor DOWNLOAD_EXECUTOR = FileDownloadExecutors.newFixedThreadPool("ConnectionBlock");
    private static final int HTTP_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    private static final int TOTAL_VALUE_IN_CHUNKED_RESOURCE = -1;
    private boolean acceptPartial;
    private final AtomicBoolean alive;
    private final FileDownloadDatabase database;
    private final int defaultConnectionCount;
    private final ArrayList<DownloadRunnable> downloadRunnableList;
    private volatile boolean error;
    private volatile Exception errorException;
    private boolean isChunked;
    private final boolean isForceReDownload;
    private boolean isNeedForceDiscardRange;
    private boolean isResumeAvailableOnDB;
    private boolean isSingleConnection;
    private boolean isTriedFixRangeNotSatisfiable;
    private final boolean isWifiRequired;
    private long lastCallbackBytes;
    private long lastCallbackTimestamp;
    private long lastUpdateBytes;
    private long lastUpdateTimestamp;
    private final FileDownloadModel model;
    private volatile boolean paused;
    private String redirectedUrl;
    private DownloadRunnable singleDownloadRunnable;
    private final DownloadStatusCallback statusCallback;
    private final boolean supportSeek;
    private final IThreadPoolMonitor threadPoolMonitor;
    private final FileDownloadHeader userRequestHeader;
    int validRetryTimes;

    private int determineConnectionCount() {
        return 5;
    }

    private DownloadLaunchRunnable(FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i, int i2, boolean z, boolean z2, int i3) {
        this.defaultConnectionCount = 5;
        this.isNeedForceDiscardRange = false;
        this.downloadRunnableList = new ArrayList<>(5);
        this.lastCallbackBytes = 0L;
        this.lastCallbackTimestamp = 0L;
        this.lastUpdateBytes = 0L;
        this.lastUpdateTimestamp = 0L;
        this.alive = new AtomicBoolean(true);
        this.paused = false;
        this.isTriedFixRangeNotSatisfiable = false;
        this.model = fileDownloadModel;
        this.userRequestHeader = fileDownloadHeader;
        this.isForceReDownload = z;
        this.isWifiRequired = z2;
        this.database = CustomComponentHolder.getImpl().getDatabaseInstance();
        this.supportSeek = CustomComponentHolder.getImpl().isSupportSeek();
        this.threadPoolMonitor = iThreadPoolMonitor;
        this.validRetryTimes = i3;
        this.statusCallback = new DownloadStatusCallback(fileDownloadModel, i3, i, i2);
    }

    private DownloadLaunchRunnable(DownloadStatusCallback downloadStatusCallback, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i, int i2, boolean z, boolean z2, int i3) {
        this.defaultConnectionCount = 5;
        this.isNeedForceDiscardRange = false;
        this.downloadRunnableList = new ArrayList<>(5);
        this.lastCallbackBytes = 0L;
        this.lastCallbackTimestamp = 0L;
        this.lastUpdateBytes = 0L;
        this.lastUpdateTimestamp = 0L;
        this.alive = new AtomicBoolean(true);
        this.paused = false;
        this.isTriedFixRangeNotSatisfiable = false;
        this.model = fileDownloadModel;
        this.userRequestHeader = fileDownloadHeader;
        this.isForceReDownload = z;
        this.isWifiRequired = z2;
        this.database = CustomComponentHolder.getImpl().getDatabaseInstance();
        this.supportSeek = CustomComponentHolder.getImpl().isSupportSeek();
        this.threadPoolMonitor = iThreadPoolMonitor;
        this.validRetryTimes = i3;
        this.statusCallback = downloadStatusCallback;
    }

    static DownloadLaunchRunnable createForTest(DownloadStatusCallback downloadStatusCallback, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i, int i2, boolean z, boolean z2, int i3) {
        return new DownloadLaunchRunnable(downloadStatusCallback, fileDownloadModel, fileDownloadHeader, iThreadPoolMonitor, i, i2, z, z2, i3);
    }

    public void pause() {
        this.paused = true;
        DownloadRunnable downloadRunnable = this.singleDownloadRunnable;
        if (downloadRunnable != null) {
            downloadRunnable.pause();
        }
        for (DownloadRunnable downloadRunnable2 : (ArrayList) this.downloadRunnableList.clone()) {
            if (downloadRunnable2 != null) {
                downloadRunnable2.pause();
            }
        }
    }

    public void pending() {
        inspectTaskModelResumeAvailableOnDB(this.database.findConnectionModel(this.model.getId()));
        this.statusCallback.onPending();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x009c, code lost:
    
        if (com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x009e, code lost:
    
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r10, "High concurrent cause, start runnable but already paused %d", java.lang.Integer.valueOf(r10.model.getId()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
    
        r10.statusCallback.discardAllMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b6, code lost:
    
        if (r10.paused == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bb, code lost:
    
        if (r10.error == false) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00be, code lost:
    
        r10.statusCallback.onCompletedDirectly();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e0, code lost:
    
        if (r10.paused == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e2, code lost:
    
        r10.model.setStatus((byte) -2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e7, code lost:
    
        r10.statusCallback.discardAllMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ee, code lost:
    
        if (r10.paused == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f4, code lost:
    
        if (r10.error == false) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f8, code lost:
    
        r10.statusCallback.onCompletedDirectly();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00fe, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0100, code lost:
    
        r5 = r10.model.getTotal();
        handlePreAllocate(r5, r10.model.getTempFilePath());
        r7 = calcConnectionCount(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0113, code lost:
    
        if (r7 <= 0) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0119, code lost:
    
        if (r5 != 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x011b, code lost:
    
        r10.statusCallback.discardAllMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0122, code lost:
    
        if (r10.paused == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0128, code lost:
    
        if (r10.error == false) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x012c, code lost:
    
        r10.statusCallback.onCompletedDirectly();
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0133, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0138, code lost:
    
        if (r10.paused == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x013a, code lost:
    
        r10.model.setStatus((byte) -2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x013f, code lost:
    
        r10.statusCallback.discardAllMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0146, code lost:
    
        if (r10.paused == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x014c, code lost:
    
        if (r10.error == false) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0150, code lost:
    
        r10.statusCallback.onCompletedDirectly();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0157, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x015a, code lost:
    
        if (r7 != 1) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x015c, code lost:
    
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x015e, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x015f, code lost:
    
        r10.isSingleConnection = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0161, code lost:
    
        if (r8 == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0163, code lost:
    
        realDownloadWithSingleConnection(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0167, code lost:
    
        r10.statusCallback.onMultiConnection();
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x016e, code lost:
    
        if (r10.isResumeAvailableOnDB == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0170, code lost:
    
        realDownloadWithMultiConnectionFromResume(r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0174, code lost:
    
        realDownloadWithMultiConnectionFromBeginning(r5, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
    
        throw new java.lang.IllegalAccessException(com.liulishuo.filedownloader.util.FileDownloadUtils.formatString("invalid connection count %d, the connection count must be larger than 0", java.lang.Integer.valueOf(r7)));
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01c0 A[Catch: all -> 0x01f5, TryCatch #11 {all -> 0x01f5, blocks: (B:3:0x0003, B:6:0x0012, B:8:0x001a, B:10:0x001e, B:11:0x0030, B:24:0x008d, B:26:0x0091, B:27:0x0096, B:29:0x009a, B:31:0x009e, B:42:0x00c6, B:44:0x00e2, B:55:0x0100, B:69:0x0136, B:71:0x013a, B:85:0x015f, B:87:0x0163, B:88:0x0167, B:90:0x0170, B:91:0x0174, B:92:0x0178, B:93:0x018b, B:94:0x018c, B:117:0x01ba, B:119:0x01c0, B:120:0x01c5), top: B:156:0x0003, inners: #15, #12, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0073  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.run():void");
    }

    private int calcConnectionCount(long j) {
        if (!isMultiConnectionAvailable()) {
            return 1;
        }
        if (this.isResumeAvailableOnDB) {
            return this.model.getConnectionCount();
        }
        return CustomComponentHolder.getImpl().determineConnectionCount(this.model.getId(), this.model.getUrl(), this.model.getPath(), j);
    }

    private void trialConnect() throws IllegalAccessException, FileDownloadSecurityException, IOException, RetryDirectly {
        ConnectionProfile connectionProfileBuildTrialConnectionProfile;
        FileDownloadConnection fileDownloadConnectionConnect = null;
        try {
            if (this.isNeedForceDiscardRange) {
                connectionProfileBuildTrialConnectionProfile = ConnectionProfile.ConnectionProfileBuild.buildTrialConnectionProfileNoRange();
            } else {
                connectionProfileBuildTrialConnectionProfile = ConnectionProfile.ConnectionProfileBuild.buildTrialConnectionProfile();
            }
            ConnectTask connectTaskBuild = new ConnectTask.Builder().setDownloadId(this.model.getId()).setUrl(this.model.getUrl()).setEtag(this.model.getETag()).setHeader(this.userRequestHeader).setConnectionProfile(connectionProfileBuildTrialConnectionProfile).build();
            fileDownloadConnectionConnect = connectTaskBuild.connect();
            handleTrialConnectResult(connectTaskBuild.getRequestHeader(), connectTaskBuild, fileDownloadConnectionConnect);
        } finally {
            if (fileDownloadConnectionConnect != null) {
                fileDownloadConnectionConnect.ending();
            }
        }
    }

    private boolean isMultiConnectionAvailable() {
        return (!this.isResumeAvailableOnDB || this.model.getConnectionCount() > 1) && this.acceptPartial && this.supportSeek && !this.isChunked;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void inspectTaskModelResumeAvailableOnDB(java.util.List<com.liulishuo.filedownloader.model.ConnectionModel> r11) {
        /*
            r10 = this;
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model
            int r0 = r0.getConnectionCount()
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r10.model
            java.lang.String r1 = r1.getTempFilePath()
            com.liulishuo.filedownloader.model.FileDownloadModel r2 = r10.model
            java.lang.String r2 = r2.getTargetFilePath()
            r3 = 0
            r4 = 1
            if (r0 <= r4) goto L18
            r5 = r4
            goto L19
        L18:
            r5 = r3
        L19:
            boolean r6 = r10.isNeedForceDiscardRange
            r7 = 0
            if (r6 == 0) goto L21
        L1f:
            r5 = r7
            goto L58
        L21:
            if (r5 == 0) goto L28
            boolean r6 = r10.supportSeek
            if (r6 != 0) goto L28
            goto L1f
        L28:
            com.liulishuo.filedownloader.model.FileDownloadModel r6 = r10.model
            int r6 = r6.getId()
            com.liulishuo.filedownloader.model.FileDownloadModel r9 = r10.model
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadUtils.isBreakpointAvailable(r6, r9)
            if (r6 == 0) goto L1f
            boolean r6 = r10.supportSeek
            if (r6 != 0) goto L44
            java.io.File r11 = new java.io.File
            r11.<init>(r1)
            long r5 = r11.length()
            goto L58
        L44:
            if (r5 == 0) goto L52
            int r5 = r11.size()
            if (r0 == r5) goto L4d
            goto L1f
        L4d:
            long r5 = com.liulishuo.filedownloader.model.ConnectionModel.getTotalOffset(r11)
            goto L58
        L52:
            com.liulishuo.filedownloader.model.FileDownloadModel r11 = r10.model
            long r5 = r11.getSoFar()
        L58:
            com.liulishuo.filedownloader.model.FileDownloadModel r11 = r10.model
            r11.setSoFar(r5)
            int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r11 <= 0) goto L62
            r3 = r4
        L62:
            r10.isResumeAvailableOnDB = r3
            if (r3 != 0) goto L74
            com.liulishuo.filedownloader.database.FileDownloadDatabase r11 = r10.database
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model
            int r0 = r0.getId()
            r11.removeConnections(r0)
            com.liulishuo.filedownloader.util.FileDownloadUtils.deleteTaskFiles(r2, r1)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.inspectTaskModelResumeAvailableOnDB(java.util.List):void");
    }

    private void handleTrialConnectResult(Map<String, List<String>> map, ConnectTask connectTask, FileDownloadConnection fileDownloadConnection) throws FileDownloadSecurityException, IOException, IllegalArgumentException, RetryDirectly {
        boolean z;
        int id = this.model.getId();
        int responseCode = fileDownloadConnection.getResponseCode();
        this.acceptPartial = FileDownloadUtils.isAcceptRange(responseCode, fileDownloadConnection);
        boolean z2 = responseCode == 200 || responseCode == 201 || responseCode == 0;
        long jFindInstanceLengthForTrial = FileDownloadUtils.findInstanceLengthForTrial(fileDownloadConnection);
        String eTag = this.model.getETag();
        String strFindEtag = FileDownloadUtils.findEtag(id, fileDownloadConnection);
        if (responseCode != 412 && ((eTag == null || eTag.equals(strFindEtag) || !(z2 || this.acceptPartial)) && !(responseCode == 201 && connectTask.isRangeNotFromBeginning()))) {
            if (responseCode == 416) {
                if (!this.acceptPartial || jFindInstanceLengthForTrial < 0) {
                    if (this.model.getSoFar() > 0) {
                        FileDownloadLog.w(this, "get 416, precondition failed and just retry", new Object[0]);
                    } else if (!this.isNeedForceDiscardRange) {
                        this.isNeedForceDiscardRange = true;
                        FileDownloadLog.w(this, "get 416, precondition failed and need to retry with discarding range", new Object[0]);
                    }
                    z = true;
                } else {
                    FileDownloadLog.w(this, "get 416 but the Content-Range is returned, no need to retry", new Object[0]);
                }
            }
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (this.isResumeAvailableOnDB) {
                FileDownloadLog.w(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response code is %d", Integer.valueOf(id), eTag, strFindEtag, Integer.valueOf(responseCode));
            }
            this.database.removeConnections(this.model.getId());
            FileDownloadUtils.deleteTaskFiles(this.model.getTargetFilePath(), this.model.getTempFilePath());
            this.isResumeAvailableOnDB = false;
            if (eTag != null && eTag.equals(strFindEtag)) {
                FileDownloadLog.w(this, "the old etag[%s] is the same to the new etag[%s], but the response status code is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", eTag, strFindEtag, Integer.valueOf(responseCode), Integer.valueOf(id));
                strFindEtag = null;
            }
            this.model.setSoFar(0L);
            this.model.setTotal(0L);
            this.model.setETag(strFindEtag);
            this.model.resetConnectionCount();
            this.database.updateOldEtagOverdue(id, this.model.getETag(), this.model.getSoFar(), this.model.getTotal(), this.model.getConnectionCount());
            throw new RetryDirectly();
        }
        this.redirectedUrl = connectTask.getFinalRedirectedUrl();
        if (this.acceptPartial || z2) {
            String strFindFilename = this.model.isPathAsDirectory() ? FileDownloadUtils.findFilename(fileDownloadConnection, this.model.getUrl()) : null;
            this.isChunked = jFindInstanceLengthForTrial == -1;
            this.statusCallback.onConnected(this.isResumeAvailableOnDB && this.acceptPartial, jFindInstanceLengthForTrial, strFindEtag, strFindFilename);
            return;
        }
        throw new FileDownloadHttpException(responseCode, map, fileDownloadConnection.getResponseHeaderFields());
    }

    private void realDownloadWithSingleConnection(long j) throws IllegalAccessException, IOException {
        ConnectionProfile connectionProfileBuildToEndConnectionProfile;
        if (!this.acceptPartial) {
            this.model.setSoFar(0L);
            connectionProfileBuildToEndConnectionProfile = ConnectionProfile.ConnectionProfileBuild.buildBeginToEndConnectionProfile(j);
        } else {
            connectionProfileBuildToEndConnectionProfile = ConnectionProfile.ConnectionProfileBuild.buildToEndConnectionProfile(this.model.getSoFar(), this.model.getSoFar(), j - this.model.getSoFar());
        }
        this.singleDownloadRunnable = new DownloadRunnable.Builder().setId(this.model.getId()).setConnectionIndex(-1).setCallback(this).setUrl(this.model.getUrl()).setEtag(this.model.getETag()).setHeader(this.userRequestHeader).setWifiRequired(this.isWifiRequired).setConnectionModel(connectionProfileBuildToEndConnectionProfile).setPath(this.model.getTempFilePath()).build();
        this.model.setConnectionCount(1);
        this.database.updateConnectionCount(this.model.getId(), 1);
        if (this.paused) {
            this.model.setStatus((byte) -2);
            this.singleDownloadRunnable.pause();
        } else {
            this.singleDownloadRunnable.run();
        }
    }

    private void realDownloadWithMultiConnectionFromResume(int i, List<ConnectionModel> list) throws InterruptedException {
        if (i <= 1 || list.size() != i) {
            throw new IllegalArgumentException();
        }
        fetchWithMultipleConnection(list, this.model.getTotal());
    }

    private void realDownloadWithMultiConnectionFromBeginning(long j, int i) throws InterruptedException {
        long j2 = j / ((long) i);
        int id = this.model.getId();
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        int i2 = 0;
        while (i2 < i) {
            long j4 = i2 == i + (-1) ? -1L : (j3 + j2) - 1;
            ConnectionModel connectionModel = new ConnectionModel();
            connectionModel.setId(id);
            connectionModel.setIndex(i2);
            connectionModel.setStartOffset(j3);
            connectionModel.setCurrentOffset(j3);
            connectionModel.setEndOffset(j4);
            arrayList.add(connectionModel);
            this.database.insertConnectionModel(connectionModel);
            j3 += j2;
            i2++;
        }
        this.model.setConnectionCount(i);
        this.database.updateConnectionCount(id, i);
        fetchWithMultipleConnection(arrayList, j);
    }

    private void fetchWithMultipleConnection(List<ConnectionModel> list, long j) throws InterruptedException {
        long endOffset;
        int id = this.model.getId();
        String eTag = this.model.getETag();
        String url = this.redirectedUrl;
        if (url == null) {
            url = this.model.getUrl();
        }
        String tempFilePath = this.model.getTempFilePath();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(id), Long.valueOf(j));
        }
        boolean z = this.isResumeAvailableOnDB;
        long j2 = 0;
        long currentOffset = 0;
        for (ConnectionModel connectionModel : list) {
            if (connectionModel.getEndOffset() == -1) {
                endOffset = j - connectionModel.getCurrentOffset();
            } else {
                endOffset = (connectionModel.getEndOffset() - connectionModel.getCurrentOffset()) + 1;
            }
            long j3 = endOffset;
            currentOffset += connectionModel.getCurrentOffset() - connectionModel.getStartOffset();
            if (j3 == j2) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(connectionModel.getId()), Integer.valueOf(connectionModel.getIndex()));
                }
            } else {
                DownloadRunnable downloadRunnableBuild = new DownloadRunnable.Builder().setId(id).setConnectionIndex(Integer.valueOf(connectionModel.getIndex())).setCallback(this).setUrl(url).setEtag(z ? eTag : null).setHeader(this.userRequestHeader).setWifiRequired(this.isWifiRequired).setConnectionModel(ConnectionProfile.ConnectionProfileBuild.buildConnectionProfile(connectionModel.getStartOffset(), connectionModel.getCurrentOffset(), connectionModel.getEndOffset(), j3)).setPath(tempFilePath).build();
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "enable multiple connection: %s", connectionModel);
                }
                if (downloadRunnableBuild == null) {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
                this.downloadRunnableList.add(downloadRunnableBuild);
            }
            j2 = 0;
        }
        if (currentOffset != this.model.getSoFar()) {
            FileDownloadLog.w(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.model.getSoFar()), Long.valueOf(currentOffset));
            this.model.setSoFar(currentOffset);
        }
        ArrayList arrayList = new ArrayList(this.downloadRunnableList.size());
        for (DownloadRunnable downloadRunnable : this.downloadRunnableList) {
            if (this.paused) {
                downloadRunnable.pause();
            } else {
                arrayList.add(Executors.callable(downloadRunnable));
            }
        }
        if (this.paused) {
            this.model.setStatus((byte) -2);
            return;
        }
        List<Future> listInvokeAll = DOWNLOAD_EXECUTOR.invokeAll(arrayList);
        if (FileDownloadLog.NEED_LOG) {
            for (Future future : listInvokeAll) {
                FileDownloadLog.d(this, "finish sub-task for [%d] %B %B", Integer.valueOf(id), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    private void handlePreAllocate(long j, String str) throws IllegalAccessException, IOException {
        FileDownloadOutputStream fileDownloadOutputStreamCreateOutputStream = null;
        if (j != -1) {
            try {
                fileDownloadOutputStreamCreateOutputStream = FileDownloadUtils.createOutputStream(this.model.getTempFilePath());
                long length = new File(str).length();
                long j2 = j - length;
                long freeSpaceBytes = FileDownloadUtils.getFreeSpaceBytes(str);
                if (freeSpaceBytes < j2) {
                    throw new FileDownloadOutOfSpaceException(freeSpaceBytes, j2, length);
                }
                if (!FileDownloadProperties.getImpl().fileNonPreAllocation) {
                    fileDownloadOutputStreamCreateOutputStream.setLength(j);
                }
            } finally {
                if (0 != 0) {
                    fileDownloadOutputStreamCreateOutputStream.close();
                }
            }
        }
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public void onProgress(long j) {
        if (this.paused) {
            return;
        }
        this.statusCallback.onProgress(j);
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public void onCompleted(DownloadRunnable downloadRunnable, long j, long j2) {
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.model.getId()));
                return;
            }
            return;
        }
        int i = downloadRunnable.connectionIndex;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.model.getTotal()));
        }
        if (!this.isSingleConnection) {
            synchronized (this.downloadRunnableList) {
                this.downloadRunnableList.remove(downloadRunnable);
            }
        } else {
            if (j == 0 || j2 == this.model.getTotal()) {
                return;
            }
            FileDownloadLog.e(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.model.getTotal()), Integer.valueOf(this.model.getId()));
        }
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public boolean isRetry(Exception exc) {
        if (exc instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exc).getCode();
            if (this.isSingleConnection && code == 416 && !this.isTriedFixRangeNotSatisfiable) {
                FileDownloadUtils.deleteTaskFiles(this.model.getTargetFilePath(), this.model.getTempFilePath());
                this.isTriedFixRangeNotSatisfiable = true;
                return true;
            }
        }
        return this.validRetryTimes > 0 && !(exc instanceof FileDownloadGiveUpRetryException);
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public void onError(Exception exc) {
        this.error = true;
        this.errorException = exc;
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.model.getId()));
            }
        } else {
            for (DownloadRunnable downloadRunnable : (ArrayList) this.downloadRunnableList.clone()) {
                if (downloadRunnable != null) {
                    downloadRunnable.discard();
                }
            }
        }
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public void onRetry(Exception exc) {
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.model.getId()));
            }
        } else {
            int i = this.validRetryTimes;
            int i2 = i - 1;
            this.validRetryTimes = i2;
            if (i < 0) {
                FileDownloadLog.e(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(i2), Integer.valueOf(this.model.getId()));
            }
            this.statusCallback.onRetry(exc, this.validRetryTimes);
        }
    }

    @Override // com.liulishuo.filedownloader.download.ProcessCallback
    public void syncProgressFromCache() {
        this.database.updateProgress(this.model.getId(), this.model.getSoFar());
    }

    private void checkupBeforeConnect() throws FileDownloadGiveUpRetryException {
        if (this.isWifiRequired && !FileDownloadUtils.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(FileDownloadUtils.formatString("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.model.getId()), "android.permission.ACCESS_NETWORK_STATE"));
        }
        if (this.isWifiRequired && FileDownloadUtils.isNetworkNotOnWifiType()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    private void checkupAfterGetFilename() throws DiscardSafely, RetryDirectly {
        int id = this.model.getId();
        if (this.model.isPathAsDirectory()) {
            String targetFilePath = this.model.getTargetFilePath();
            int iGenerateId = FileDownloadUtils.generateId(this.model.getUrl(), targetFilePath);
            if (FileDownloadHelper.inspectAndInflowDownloaded(id, targetFilePath, this.isForceReDownload, false)) {
                this.database.remove(id);
                this.database.removeConnections(id);
                throw new DiscardSafely();
            }
            FileDownloadModel fileDownloadModelFind = this.database.find(iGenerateId);
            if (fileDownloadModelFind != null) {
                if (FileDownloadHelper.inspectAndInflowDownloading(id, fileDownloadModelFind, this.threadPoolMonitor, false)) {
                    this.database.remove(id);
                    this.database.removeConnections(id);
                    throw new DiscardSafely();
                }
                List<ConnectionModel> listFindConnectionModel = this.database.findConnectionModel(iGenerateId);
                this.database.remove(iGenerateId);
                this.database.removeConnections(iGenerateId);
                FileDownloadUtils.deleteTargetFile(this.model.getTargetFilePath());
                if (FileDownloadUtils.isBreakpointAvailable(iGenerateId, fileDownloadModelFind)) {
                    this.model.setSoFar(fileDownloadModelFind.getSoFar());
                    this.model.setTotal(fileDownloadModelFind.getTotal());
                    this.model.setETag(fileDownloadModelFind.getETag());
                    this.model.setConnectionCount(fileDownloadModelFind.getConnectionCount());
                    this.database.update(this.model);
                    if (listFindConnectionModel != null) {
                        for (ConnectionModel connectionModel : listFindConnectionModel) {
                            connectionModel.setId(id);
                            this.database.insertConnectionModel(connectionModel);
                        }
                    }
                    throw new RetryDirectly();
                }
            }
            if (FileDownloadHelper.inspectAndInflowConflictPath(id, this.model.getSoFar(), this.model.getTempFilePath(), targetFilePath, this.threadPoolMonitor)) {
                this.database.remove(id);
                this.database.removeConnections(id);
                throw new DiscardSafely();
            }
        }
    }

    public int getId() {
        return this.model.getId();
    }

    public boolean isAlive() {
        return this.alive.get() || this.statusCallback.isAlive();
    }

    public String getTempFilePath() {
        return this.model.getTempFilePath();
    }

    class RetryDirectly extends Throwable {
        RetryDirectly() {
        }
    }

    class DiscardSafely extends Throwable {
        DiscardSafely() {
        }
    }

    public static class Builder {
        private Integer callbackProgressMaxCount;
        private FileDownloadHeader header;
        private Boolean isForceReDownload;
        private Boolean isWifiRequired;
        private Integer maxRetryTimes;
        private Integer minIntervalMillis;
        private FileDownloadModel model;
        private IThreadPoolMonitor threadPoolMonitor;

        public Builder setModel(FileDownloadModel fileDownloadModel) {
            this.model = fileDownloadModel;
            return this;
        }

        public Builder setHeader(FileDownloadHeader fileDownloadHeader) {
            this.header = fileDownloadHeader;
            return this;
        }

        public Builder setThreadPoolMonitor(IThreadPoolMonitor iThreadPoolMonitor) {
            this.threadPoolMonitor = iThreadPoolMonitor;
            return this;
        }

        public Builder setMinIntervalMillis(Integer num) {
            this.minIntervalMillis = num;
            return this;
        }

        public Builder setCallbackProgressMaxCount(Integer num) {
            this.callbackProgressMaxCount = num;
            return this;
        }

        public Builder setForceReDownload(Boolean bool) {
            this.isForceReDownload = bool;
            return this;
        }

        public Builder setWifiRequired(Boolean bool) {
            this.isWifiRequired = bool;
            return this;
        }

        public Builder setMaxRetryTimes(Integer num) {
            this.maxRetryTimes = num;
            return this;
        }

        public DownloadLaunchRunnable build() {
            if (this.model == null || this.threadPoolMonitor == null || this.minIntervalMillis == null || this.callbackProgressMaxCount == null || this.isForceReDownload == null || this.isWifiRequired == null || this.maxRetryTimes == null) {
                throw new IllegalArgumentException();
            }
            return new DownloadLaunchRunnable(this.model, this.header, this.threadPoolMonitor, this.minIntervalMillis.intValue(), this.callbackProgressMaxCount.intValue(), this.isForceReDownload.booleanValue(), this.isWifiRequired.booleanValue(), this.maxRetryTimes.intValue());
        }
    }
}
