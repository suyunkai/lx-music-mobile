package com.wanos.careditproject.utils.http;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.android.arouter.utils.Consts;
import com.baidubce.BceConfig;
import com.google.gson.Gson;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.http.DownloadManager;
import cz.msebera.android.httpclient.HttpHost;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadUtils {
    private static DownloadUtils downloadUtils;
    private DownloadCall downloadCall;
    private DownloadCall downloadCall2;
    private DownloadFilesModel downloadFilesModel;
    private List<String> urlToDownload;
    private final String keyBaseIndex = "baseIndex";
    private final String url2LocalPathFileName = "wanosres.txt";
    private boolean isDownloading = false;
    private String localDir = EditingUtils.getResLocalPath();
    private String resJsonDir = EditingUtils.getResJsonPath();

    public interface DownloadCall {
        void onCall(String str, String str2, boolean z);
    }

    public void init(DownloadCall downloadCall) {
    }

    public static DownloadUtils getInstance() {
        if (downloadUtils == null) {
            downloadUtils = new DownloadUtils();
        }
        return downloadUtils;
    }

    public String getLocalFilePath(String str) {
        String url = parseUrl(str);
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        return files.containsKey(url) ? files.get(url).getLocalPath() : "";
    }

    DownloadUtils() throws Throwable {
        initJsonFile("wanosres.txt");
        this.urlToDownload = new ArrayList();
        EditingUtils.log("DownloadUtils : " + this.downloadFilesModel.toString());
    }

    public void put(String str, String str2) {
        String url = parseUrl(str);
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        File file = new File(str2);
        if (file.exists()) {
            FileInfoModel fileInfoModel = new FileInfoModel(url, str2, file.length(), getTime());
            EditingUtils.log("DownloadUtils put url = " + url);
            files.put(url, fileInfoModel);
        }
    }

    public long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    public String getOutResName() {
        String str = "res_" + this.downloadFilesModel.getBaseIndex();
        DownloadFilesModel downloadFilesModel = this.downloadFilesModel;
        downloadFilesModel.setBaseIndex(downloadFilesModel.getBaseIndex() + 1);
        return str;
    }

    private String parseUrl(String str) {
        return EditingUtils.removeParamOfUrl(str);
    }

    public void download(final String str, final DownloadCall downloadCall) {
        final String url = parseUrl(str);
        if (!url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            if (downloadCall != null) {
                downloadCall.onCall(str, "", false);
                return;
            }
            return;
        }
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        if (files.containsKey(url)) {
            FileInfoModel fileInfoModel = files.get(url);
            String localPath = fileInfoModel.getLocalPath();
            fileInfoModel.setLastTime(getTime());
            File file = new File(localPath);
            if (!localPath.equals("") && file.exists()) {
                if (downloadCall != null) {
                    downloadCall.onCall(str, localPath, true);
                    return;
                }
                return;
            }
        }
        String[] strArr = new String[0];
        try {
            String[] strArrSplit = URLDecoder.decode(url, "UTF-8").split(BceConfig.BOS_DELIMITER);
            if (strArrSplit.length <= 0) {
                if (downloadCall != null) {
                    downloadCall.onCall(str, "", false);
                    return;
                }
                return;
            }
            String[] strArrSplit2 = strArrSplit[strArrSplit.length - 1].split("\\.");
            if (strArrSplit2.length <= 0) {
                if (downloadCall != null) {
                    downloadCall.onCall(str, "", false);
                }
            } else {
                final String str2 = this.localDir + "/res_" + this.downloadFilesModel.getBaseIndex() + Consts.DOT + strArrSplit2[strArrSplit2.length - 1];
                DownloadFilesModel downloadFilesModel = this.downloadFilesModel;
                downloadFilesModel.setBaseIndex(downloadFilesModel.getBaseIndex() + 1);
                DownloadManager.getInstance().downAsynFile(str, str2, getUsedRes(), new DownloadManager.DownloadResponse() { // from class: com.wanos.careditproject.utils.http.DownloadUtils.1
                    @Override // com.wanos.careditproject.utils.http.DownloadManager.DownloadResponse
                    public void onResponse(boolean z) {
                        if (z) {
                            DownloadUtils.this.put(url, str2);
                            DownloadCall downloadCall2 = downloadCall;
                            if (downloadCall2 != null) {
                                downloadCall2.onCall(str, str2, true);
                            }
                            DownloadUtils.this.saveUrl2LocalPath();
                            return;
                        }
                        DownloadCall downloadCall3 = downloadCall;
                        if (downloadCall3 != null) {
                            downloadCall3.onCall(str, "", false);
                        }
                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            if (downloadCall != null) {
                downloadCall.onCall(str, "", false);
            }
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (downloadCall != null) {
                downloadCall.onCall(str, "", false);
            }
        }
    }

    public void downloadJsonFile(final String str, final DownloadCall downloadCall) {
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        if (files.containsKey(str)) {
            FileInfoModel fileInfoModel = files.get(str);
            String localPath = fileInfoModel.getLocalPath();
            fileInfoModel.setLastTime(getTime());
            if (downloadCall != null) {
                downloadCall.onCall(str, localPath, true);
                return;
            }
            return;
        }
        try {
            String[] strArrSplit = URLDecoder.decode(str, "UTF-8").split(BceConfig.BOS_DELIMITER);
            if (strArrSplit.length <= 0) {
                if (downloadCall != null) {
                    downloadCall.onCall(str, "", false);
                }
            } else {
                final String str2 = this.localDir + BceConfig.BOS_DELIMITER + strArrSplit[strArrSplit.length - 1];
                DownloadManager.getInstance().downAsynFile(str, str2, getUsedRes(), new DownloadManager.DownloadResponse() { // from class: com.wanos.careditproject.utils.http.DownloadUtils.2
                    @Override // com.wanos.careditproject.utils.http.DownloadManager.DownloadResponse
                    public void onResponse(boolean z) {
                        if (z) {
                            DownloadUtils.this.put(str, str2);
                            DownloadCall downloadCall2 = downloadCall;
                            if (downloadCall2 != null) {
                                downloadCall2.onCall(str, str2, true);
                            }
                            DownloadUtils.this.saveUrl2LocalPath();
                            return;
                        }
                        DownloadCall downloadCall3 = downloadCall;
                        if (downloadCall3 != null) {
                            downloadCall3.onCall(str, "", false);
                        }
                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkAdd(long j) {
        EditingUtils.log("checkAdd size = " + j);
        Iterator<Map.Entry<String, FileInfoModel>> it = this.downloadFilesModel.getFiles().entrySet().iterator();
        while (it.hasNext()) {
            j += it.next().getValue().getSize();
        }
        long maxMemOfM = EditingParams.getInstance().getMaxMemOfM() * 1024 * 1024;
        if (maxMemOfM > j) {
            return true;
        }
        return clearUnusedRes(j - maxMemOfM);
    }

    public List<File> getUsedRes() {
        ArrayList arrayList = new ArrayList();
        List<String> allResList = DataHelpAudioTrack.getAllResList();
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = allResList.iterator();
        while (it.hasNext()) {
            String url = parseUrl(it.next());
            if (url != null) {
                arrayList2.add(url);
            }
        }
        Iterator<Map.Entry<String, FileInfoModel>> it2 = files.entrySet().iterator();
        while (it2.hasNext()) {
            String key = it2.next().getKey();
            if (arrayList2.contains(key)) {
                arrayList.add(new File(files.get(key).getLocalPath()));
            }
        }
        return arrayList;
    }

    public boolean clearUnusedRes(long j) {
        List<String> allResList = DataHelpAudioTrack.getAllResList();
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = allResList.iterator();
        while (it.hasNext()) {
            String url = parseUrl(it.next());
            if (url != null) {
                arrayList.add(url);
            }
        }
        Iterator<Map.Entry<String, FileInfoModel>> it2 = files.entrySet().iterator();
        int size = 0;
        while (it2.hasNext()) {
            String key = it2.next().getKey();
            if (!arrayList.contains(key)) {
                FileInfoModel fileInfoModel = files.get(key);
                File file = new File(fileInfoModel.getLocalPath());
                if (file.exists()) {
                    file.delete();
                }
                it2.remove();
                size = (int) (((long) size) + fileInfoModel.getSize());
                if (size > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED + j) {
                    break;
                }
            }
        }
        saveUrl2LocalPath();
        return ((long) size) > j;
    }

    public void clearMoreFile() {
        int i;
        long time = getTime();
        Map<String, FileInfoModel> files = this.downloadFilesModel.getFiles();
        Iterator<Map.Entry<String, FileInfoModel>> it = files.entrySet().iterator();
        long size = 0;
        while (it.hasNext()) {
            size += it.next().getValue().getSize();
        }
        long maxMemOfM = size - ((long) ((EditingParams.getInstance().getMaxMemOfM() * 1024) * 1024));
        if (maxMemOfM > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, FileInfoModel>> it2 = files.entrySet().iterator();
            while (true) {
                i = 0;
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, FileInfoModel> next = it2.next();
                String key = next.getKey();
                FileInfoModel value = next.getValue();
                if (time - value.getLastTime() >= 180) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        }
                        if (value.getLastTime() < files.get((String) arrayList.get(i2)).getLastTime()) {
                            arrayList.add(i2, key);
                            i = 1;
                            break;
                        }
                        i2++;
                    }
                    if (i == 0) {
                        arrayList.add(key);
                    }
                }
            }
            while (i < arrayList.size()) {
                String str = (String) arrayList.get(i);
                FileInfoModel fileInfoModel = files.get(str);
                maxMemOfM -= fileInfoModel.getSize();
                File file = new File(fileInfoModel.getLocalPath());
                if (file.exists()) {
                    file.delete();
                }
                files.remove(str);
                EditingUtils.log("delete file:" + fileInfoModel.getLocalPath());
                if (maxMemOfM < 0) {
                    break;
                } else {
                    i++;
                }
            }
        }
        saveUrl2LocalPath();
    }

    private Map<String, String> stringToMap(String str) {
        try {
            return (Map) new Gson().fromJson(str, Map.class);
        } catch (Exception unused) {
            return new HashMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUrl2LocalPath() {
        try {
            saveFile(new Gson().toJson(this.downloadFilesModel), "wanosres.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveFile(String str, String str2) {
        File file = new File(this.resJsonDir, str2);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            EditingUtils.log("File written successfully.");
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.wanos.careditproject.utils.http.DownloadUtils.DownloadFilesModel initJsonFile(java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 100
            byte[] r1 = new byte[r1]
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r5.resJsonDir
            r2.<init>(r3, r6)
            r6 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46 java.io.IOException -> L52
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46 java.io.IOException -> L52
        L16:
            int r6 = r3.read(r1)     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r2 = -1
            if (r6 == r2) goto L27
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r4 = 0
            r2.<init>(r1, r4, r6)     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r0.append(r2)     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            goto L16
        L27:
            java.lang.String r6 = r0.toString()     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r0.<init>()     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            java.lang.Class<com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel> r1 = com.wanos.careditproject.utils.http.DownloadUtils.DownloadFilesModel.class
            java.lang.Object r6 = r0.fromJson(r6, r1)     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel r6 = (com.wanos.careditproject.utils.http.DownloadUtils.DownloadFilesModel) r6     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r5.downloadFilesModel = r6     // Catch: java.lang.Exception -> L3e java.io.IOException -> L40 java.lang.Throwable -> L76
            r3.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L3e:
            r6 = move-exception
            goto L49
        L40:
            r6 = move-exception
            goto L55
        L42:
            r0 = move-exception
            r3 = r6
            r6 = r0
            goto L77
        L46:
            r0 = move-exception
            r3 = r6
            r6 = r0
        L49:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L62
            r3.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L52:
            r0 = move-exception
            r3 = r6
            r6 = r0
        L55:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L62
            r3.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r6 = move-exception
            r6.printStackTrace()
        L62:
            com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel r6 = r5.downloadFilesModel
            if (r6 == 0) goto L6c
            java.util.Map r6 = com.wanos.careditproject.utils.http.DownloadUtils.DownloadFilesModel.access$100(r6)
            if (r6 != 0) goto L73
        L6c:
            com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel r6 = new com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel
            r6.<init>()
            r5.downloadFilesModel = r6
        L73:
            com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel r6 = r5.downloadFilesModel
            return r6
        L76:
            r6 = move-exception
        L77:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.io.IOException -> L7d
            goto L81
        L7d:
            r0 = move-exception
            r0.printStackTrace()
        L81:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.http.DownloadUtils.initJsonFile(java.lang.String):com.wanos.careditproject.utils.http.DownloadUtils$DownloadFilesModel");
    }

    public void stop() {
        DownloadManager.getInstance().stop();
    }

    public class DownloadFilesModel {
        private int baseIndex;
        private Map<String, FileInfoModel> files = new HashMap();

        public DownloadFilesModel() {
        }

        public int getBaseIndex() {
            return this.baseIndex;
        }

        public void setBaseIndex(int i) {
            this.baseIndex = i;
        }

        public Map<String, FileInfoModel> getFiles() {
            return this.files;
        }

        public void setFiles(Map<String, FileInfoModel> map) {
            this.files = map;
        }
    }

    public class FileInfoModel {
        private int fileType;
        private long lastTime;
        private String localPath;
        private long size;
        private String url;

        public FileInfoModel(String str, String str2, long j, long j2) {
            this.url = str;
            this.localPath = str2;
            this.size = j;
            this.lastTime = j2;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getLocalPath() {
            return this.localPath;
        }

        public void setLocalPath(String str) {
            this.localPath = str;
        }

        public long getSize() {
            return this.size;
        }

        public void setSize(long j) {
            this.size = j;
        }

        public long getLastTime() {
            return this.lastTime;
        }

        public void setLastTime(long j) {
            this.lastTime = j;
        }
    }
}
