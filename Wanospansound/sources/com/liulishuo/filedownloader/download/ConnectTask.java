package com.liulishuo.filedownloader.download;

import android.text.TextUtils;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.connection.RedirectHandler;
import com.liulishuo.filedownloader.download.ConnectionProfile;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class ConnectTask {
    final int downloadId;
    private String etag;
    final FileDownloadHeader header;
    private ConnectionProfile profile;
    private List<String> redirectedUrlList;
    private Map<String, List<String>> requestHeader;
    final String url;

    private ConnectTask(ConnectionProfile connectionProfile, int i, String str, String str2, FileDownloadHeader fileDownloadHeader) {
        this.downloadId = i;
        this.url = str;
        this.etag = str2;
        this.header = fileDownloadHeader;
        this.profile = connectionProfile;
    }

    void updateConnectionProfile(long j) {
        if (j == this.profile.currentOffset) {
            FileDownloadLog.w(this, "no data download, no need to update", new Object[0]);
            return;
        }
        this.profile = ConnectionProfile.ConnectionProfileBuild.buildConnectionProfile(this.profile.startOffset, j, this.profile.endOffset, this.profile.contentLength - (j - this.profile.currentOffset));
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.i(this, "after update profile:%s", this.profile);
        }
    }

    FileDownloadConnection connect() throws IllegalAccessException, IOException {
        FileDownloadConnection fileDownloadConnectionCreateConnection = CustomComponentHolder.getImpl().createConnection(this.url);
        addUserRequiredHeader(fileDownloadConnectionCreateConnection);
        addRangeHeader(fileDownloadConnectionCreateConnection);
        fixNeededHeader(fileDownloadConnectionCreateConnection);
        this.requestHeader = fileDownloadConnectionCreateConnection.getRequestHeaderFields();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "<---- %s request header %s", Integer.valueOf(this.downloadId), this.requestHeader);
        }
        fileDownloadConnectionCreateConnection.execute();
        ArrayList arrayList = new ArrayList();
        this.redirectedUrlList = arrayList;
        FileDownloadConnection fileDownloadConnectionProcess = RedirectHandler.process(this.requestHeader, fileDownloadConnectionCreateConnection, arrayList);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "----> %s response header %s", Integer.valueOf(this.downloadId), fileDownloadConnectionProcess.getResponseHeaderFields());
        }
        return fileDownloadConnectionProcess;
    }

    private void addUserRequiredHeader(FileDownloadConnection fileDownloadConnection) {
        HashMap<String, List<String>> headers;
        FileDownloadHeader fileDownloadHeader = this.header;
        if (fileDownloadHeader == null || (headers = fileDownloadHeader.getHeaders()) == null) {
            return;
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "%d add outside header: %s", Integer.valueOf(this.downloadId), headers);
        }
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value != null) {
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    fileDownloadConnection.addHeader(key, it.next());
                }
            }
        }
    }

    private void addRangeHeader(FileDownloadConnection fileDownloadConnection) throws ProtocolException {
        if (fileDownloadConnection.dispatchAddResumeOffset(this.etag, this.profile.startOffset)) {
            return;
        }
        if (!TextUtils.isEmpty(this.etag)) {
            fileDownloadConnection.addHeader("If-Match", this.etag);
        }
        this.profile.processProfile(fileDownloadConnection);
    }

    private void fixNeededHeader(FileDownloadConnection fileDownloadConnection) {
        FileDownloadHeader fileDownloadHeader = this.header;
        if (fileDownloadHeader == null || fileDownloadHeader.getHeaders().get("User-Agent") == null) {
            fileDownloadConnection.addHeader("User-Agent", FileDownloadUtils.defaultUserAgent());
        }
    }

    boolean isRangeNotFromBeginning() {
        return this.profile.currentOffset > 0;
    }

    String getFinalRedirectedUrl() {
        List<String> list = this.redirectedUrlList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.redirectedUrlList.get(r0.size() - 1);
    }

    public Map<String, List<String>> getRequestHeader() {
        return this.requestHeader;
    }

    public ConnectionProfile getProfile() {
        return this.profile;
    }

    public void retryOnConnectedWithNewParam(ConnectionProfile connectionProfile, String str) throws Reconnect {
        if (connectionProfile == null) {
            throw new IllegalArgumentException();
        }
        this.profile = connectionProfile;
        this.etag = str;
        throw new Reconnect();
    }

    class Reconnect extends Throwable {
        Reconnect() {
        }
    }

    static class Builder {
        private ConnectionProfile connectionProfile;
        private Integer downloadId;
        private String etag;
        private FileDownloadHeader header;
        private String url;

        Builder() {
        }

        public Builder setDownloadId(int i) {
            this.downloadId = Integer.valueOf(i);
            return this;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder setEtag(String str) {
            this.etag = str;
            return this;
        }

        public Builder setHeader(FileDownloadHeader fileDownloadHeader) {
            this.header = fileDownloadHeader;
            return this;
        }

        public Builder setConnectionProfile(ConnectionProfile connectionProfile) {
            this.connectionProfile = connectionProfile;
            return this;
        }

        ConnectTask build() {
            if (this.downloadId == null || this.connectionProfile == null || this.url == null) {
                throw new IllegalArgumentException();
            }
            return new ConnectTask(this.connectionProfile, this.downloadId.intValue(), this.url, this.etag, this.header);
        }
    }
}
