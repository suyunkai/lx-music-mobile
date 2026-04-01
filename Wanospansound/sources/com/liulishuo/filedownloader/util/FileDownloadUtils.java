package com.liulishuo.filedownloader.util;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.baidubce.BceConfig;
import com.liulishuo.filedownloader.BuildConfig;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadSecurityException;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.wanos.media.ui.advertise.WanosJsBridge;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class FileDownloadUtils {
    private static final String FILEDOWNLOADER_PREFIX = "FileDownloader";
    private static final String INTERNAL_DOCUMENT_NAME = "filedownloader";
    private static final String OLD_FILE_CONVERTED_FILE_NAME = ".old_file_converted";
    private static String defaultSaveRootPath = null;
    private static Boolean filenameConverted = null;
    private static Boolean isDownloaderProcess = null;
    private static int minProgressStep = 65536;
    private static long minProgressTime = 2000;
    private static final Pattern CONTENT_DISPOSITION_WITH_ASTERISK_PATTERN = Pattern.compile("attachment;\\s*filename\\*\\s*=\\s*\"*([^\"]*)'\\S*'([^\"]*)\"*");
    private static final Pattern CONTENT_DISPOSITION_WITHOUT_ASTERISK_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"*([^\"\\n]*)\"*");

    public static boolean isFilenameValid(String str) {
        return true;
    }

    public static void setMinProgressStep(int i) throws IllegalAccessException {
        if (isDownloaderProcess(FileDownloadHelper.getAppContext())) {
            minProgressStep = i;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-step'.");
    }

    public static void setMinProgressTime(long j) throws IllegalAccessException {
        if (isDownloaderProcess(FileDownloadHelper.getAppContext())) {
            minProgressTime = j;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-time'.");
    }

    public static int getMinProgressStep() {
        return minProgressStep;
    }

    public static long getMinProgressTime() {
        return minProgressTime;
    }

    public static String getDefaultSaveRootPath() {
        if (!TextUtils.isEmpty(defaultSaveRootPath)) {
            return defaultSaveRootPath;
        }
        if (FileDownloadHelper.getAppContext().getExternalCacheDir() != null && Environment.getExternalStorageState().equals("mounted") && Environment.getExternalStorageDirectory().getFreeSpace() > 0) {
            return FileDownloadHelper.getAppContext().getExternalCacheDir().getAbsolutePath();
        }
        return FileDownloadHelper.getAppContext().getCacheDir().getAbsolutePath();
    }

    public static String getDefaultSaveFilePath(String str) {
        return generateFilePath(getDefaultSaveRootPath(), generateFileName(str));
    }

    public static String generateFileName(String str) {
        return md5(str);
    }

    public static String generateFilePath(String str, String str2) {
        if (str2 == null) {
            throw new IllegalStateException("can't generate real path, the file name is null");
        }
        if (str != null) {
            return formatString("%s%s%s", str, File.separator, str2);
        }
        throw new IllegalStateException("can't generate real path, the directory is null");
    }

    public static void setDefaultSaveRootPath(String str) {
        defaultSaveRootPath = str;
    }

    public static String getTempPath(String str) {
        return formatString("%s.temp", str);
    }

    public static int generateId(String str, String str2) {
        return CustomComponentHolder.getImpl().getIdGeneratorInstance().generateId(str, str2, false);
    }

    public static int generateId(String str, String str2, boolean z) {
        return CustomComponentHolder.getImpl().getIdGeneratorInstance().generateId(str, str2, z);
    }

    public static String md5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                int i = b2 & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    public static String getStack() {
        return getStack(true);
    }

    public static String getStack(boolean z) {
        return getStack(new Throwable().getStackTrace(), z);
    }

    public static String getStack(StackTraceElement[] stackTraceElementArr, boolean z) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 4) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (stackTraceElementArr[i].getClassName().contains(BuildConfig.APPLICATION_ID)) {
                sb.append("[");
                sb.append(stackTraceElementArr[i].getClassName().substring(28));
                sb.append(":");
                sb.append(stackTraceElementArr[i].getMethodName());
                if (z) {
                    sb.append("(").append(stackTraceElementArr[i].getLineNumber()).append(")]");
                } else {
                    sb.append("]");
                }
            }
        }
        return sb.toString();
    }

    public static boolean isDownloaderProcess(Context context) {
        boolean zEndsWith;
        Boolean bool = isDownloaderProcess;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (!FileDownloadProperties.getImpl().processNonSeparate) {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(WanosJsBridge.H5_KEY_ACTIVITY);
            if (activityManager == null) {
                FileDownloadLog.w(FileDownloadUtils.class, "fail to get the activity manager!", new Object[0]);
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
                FileDownloadLog.w(FileDownloadUtils.class, "The running app process info list from ActivityManager is null or empty, maybe current App is not running.", new Object[0]);
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    zEndsWith = false;
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == iMyPid) {
                    zEndsWith = next.processName.endsWith(":filedownloader");
                    break;
                }
            }
        } else {
            zEndsWith = true;
        }
        Boolean boolValueOf = Boolean.valueOf(zEndsWith);
        isDownloaderProcess = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static String[] convertHeaderString(String str) {
        String[] strArrSplit = str.split("\n");
        String[] strArr = new String[strArrSplit.length * 2];
        for (int i = 0; i < strArrSplit.length; i++) {
            String[] strArrSplit2 = strArrSplit[i].split(": ");
            int i2 = i * 2;
            strArr[i2] = strArrSplit2[0];
            strArr[i2 + 1] = strArrSplit2[1];
        }
        return strArr;
    }

    public static long getFreeSpaceBytes(String str) {
        return new StatFs(str).getAvailableBytes();
    }

    public static String formatString(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public static void markConverted(Context context) {
        File convertedMarkedFile = getConvertedMarkedFile(context);
        try {
            convertedMarkedFile.getParentFile().mkdirs();
            convertedMarkedFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFilenameConverted(Context context) {
        if (filenameConverted == null) {
            filenameConverted = Boolean.valueOf(getConvertedMarkedFile(context).exists());
        }
        return filenameConverted.booleanValue();
    }

    public static File getConvertedMarkedFile(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separator + "filedownloader", OLD_FILE_CONVERTED_FILE_NAME);
    }

    public static long parseContentRangeFoInstanceLength(String str) {
        if (str == null) {
            return -1L;
        }
        String[] strArrSplit = str.split(BceConfig.BOS_DELIMITER);
        if (strArrSplit.length >= 2) {
            try {
                return Long.parseLong(strArrSplit[1]);
            } catch (NumberFormatException unused) {
                FileDownloadLog.w(FileDownloadUtils.class, "parse instance length failed with %s", str);
            }
        }
        return -1L;
    }

    public static String parseContentDisposition(String str) {
        Matcher matcher;
        if (str == null) {
            return null;
        }
        try {
            matcher = CONTENT_DISPOSITION_WITH_ASTERISK_PATTERN.matcher(str);
        } catch (UnsupportedEncodingException | IllegalStateException unused) {
        }
        if (matcher.find()) {
            return URLDecoder.decode(matcher.group(2), matcher.group(1));
        }
        Matcher matcher2 = CONTENT_DISPOSITION_WITHOUT_ASTERISK_PATTERN.matcher(str);
        if (matcher2.find()) {
            return matcher2.group(1);
        }
        return null;
    }

    public static String getTargetFilePath(String str, boolean z, String str2) {
        if (str == null) {
            return null;
        }
        if (!z) {
            return str;
        }
        if (str2 == null) {
            return null;
        }
        return generateFilePath(str, str2);
    }

    public static String getParent(String str) {
        int length = str.length();
        int i = (File.separatorChar == '\\' && length > 2 && str.charAt(1) == ':') ? 2 : 0;
        int iLastIndexOf = str.lastIndexOf(File.separatorChar);
        int i2 = (iLastIndexOf != -1 || i <= 0) ? iLastIndexOf : 2;
        if (i2 == -1 || str.charAt(length - 1) == File.separatorChar) {
            return null;
        }
        if (str.indexOf(File.separatorChar) == i2 && str.charAt(i) == File.separatorChar) {
            return str.substring(0, i2 + 1);
        }
        return str.substring(0, i2);
    }

    public static String getThreadPoolName(String str) {
        return "FileDownloader-" + str;
    }

    public static boolean isNetworkNotOnWifiType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FileDownloadHelper.getAppContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            FileDownloadLog.w(FileDownloadUtils.class, "failed to get connectivity manager!", new Object[0]);
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || activeNetworkInfo.getType() != 1;
    }

    public static boolean checkPermission(String str) {
        return FileDownloadHelper.getAppContext().checkCallingOrSelfPermission(str) == 0;
    }

    public static long convertContentLengthString(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static String findEtag(int i, FileDownloadConnection fileDownloadConnection) {
        if (fileDownloadConnection == null) {
            throw new RuntimeException("connection is null when findEtag");
        }
        String responseHeaderField = fileDownloadConnection.getResponseHeaderField("Etag");
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(FileDownloadUtils.class, "etag find %s for task(%d)", responseHeaderField, Integer.valueOf(i));
        }
        return responseHeaderField;
    }

    public static boolean isAcceptRange(int i, FileDownloadConnection fileDownloadConnection) {
        if (i == 206 || i == 1) {
            return true;
        }
        return "bytes".equals(fileDownloadConnection.getResponseHeaderField("Accept-Ranges"));
    }

    public static long findInstanceLengthForTrial(FileDownloadConnection fileDownloadConnection) {
        long jFindInstanceLengthFromContentRange = findInstanceLengthFromContentRange(fileDownloadConnection);
        if (jFindInstanceLengthFromContentRange < 0) {
            FileDownloadLog.w(FileDownloadUtils.class, "don't get instance length fromContent-Range header", new Object[0]);
            jFindInstanceLengthFromContentRange = -1;
        }
        if (jFindInstanceLengthFromContentRange == 0 && FileDownloadProperties.getImpl().trialConnectionHeadMethod) {
            return -1L;
        }
        return jFindInstanceLengthFromContentRange;
    }

    public static long findInstanceLengthFromContentRange(FileDownloadConnection fileDownloadConnection) {
        return parseContentRangeFoInstanceLength(getContentRangeHeader(fileDownloadConnection));
    }

    private static String getContentRangeHeader(FileDownloadConnection fileDownloadConnection) {
        return fileDownloadConnection.getResponseHeaderField("Content-Range");
    }

    public static long findContentLength(int i, FileDownloadConnection fileDownloadConnection) {
        long jConvertContentLengthString = convertContentLengthString(fileDownloadConnection.getResponseHeaderField("Content-Length"));
        String responseHeaderField = fileDownloadConnection.getResponseHeaderField("Transfer-Encoding");
        if (jConvertContentLengthString >= 0) {
            return jConvertContentLengthString;
        }
        if (!(responseHeaderField != null && responseHeaderField.equals(HTTP.CHUNK_CODING))) {
            if (FileDownloadProperties.getImpl().httpLenient) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(FileDownloadUtils.class, "%d response header is not legal but HTTP lenient is true, so handle as the case of transfer encoding chunk", Integer.valueOf(i));
                }
            } else {
                throw new FileDownloadGiveUpRetryException("can't know the size of the download file, and its Transfer-Encoding is not Chunked either.\nyou can ignore such exception by add http.lenient=true to the filedownloader.properties");
            }
        }
        return -1L;
    }

    public static long findContentLengthFromContentRange(FileDownloadConnection fileDownloadConnection) {
        long contentLengthFromContentRange = parseContentLengthFromContentRange(getContentRangeHeader(fileDownloadConnection));
        if (contentLengthFromContentRange < 0) {
            return -1L;
        }
        return contentLengthFromContentRange;
    }

    public static long parseContentLengthFromContentRange(String str) {
        if (str != null && str.length() != 0) {
            try {
                Matcher matcher = Pattern.compile("bytes (\\d+)-(\\d+)/\\d+").matcher(str);
                if (matcher.find()) {
                    return (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
                }
            } catch (Exception e) {
                FileDownloadLog.e(FileDownloadUtils.class, e, "parse content length from content range error", new Object[0]);
            }
        }
        return -1L;
    }

    public static String findFilename(FileDownloadConnection fileDownloadConnection, String str) throws FileDownloadSecurityException {
        String contentDisposition = parseContentDisposition(fileDownloadConnection.getResponseHeaderField("Content-Disposition"));
        if (TextUtils.isEmpty(contentDisposition)) {
            contentDisposition = findFileNameFromUrl(str);
        }
        if (TextUtils.isEmpty(contentDisposition)) {
            return generateFileName(str);
        }
        if (contentDisposition.contains("../")) {
            throw new FileDownloadSecurityException(formatString("The filename [%s] from the response is not allowable, because it contains '../', which can raise the directory traversal vulnerability", contentDisposition));
        }
        return contentDisposition;
    }

    public static FileDownloadOutputStream createOutputStream(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        }
        if (!isFilenameValid(str)) {
            throw new RuntimeException(formatString("found invalid internal destination filename %s", str));
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            throw new RuntimeException(formatString("found invalid internal destination path[%s], & path is directory[%B]", str, Boolean.valueOf(file.isDirectory())));
        }
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException(formatString("create new file error  %s", file.getAbsolutePath()));
        }
        return CustomComponentHolder.getImpl().createOutputStream(file);
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel) {
        return isBreakpointAvailable(i, fileDownloadModel, null);
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel, Boolean bool) {
        if (fileDownloadModel == null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d model == null", Integer.valueOf(i));
            }
            return false;
        }
        if (fileDownloadModel.getTempFilePath() == null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d temp path == null", Integer.valueOf(i));
            }
            return false;
        }
        return isBreakpointAvailable(i, fileDownloadModel, fileDownloadModel.getTempFilePath(), bool);
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel, String str, Boolean bool) {
        if (str == null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d path = null", Integer.valueOf(i));
            }
        } else {
            File file = new File(str);
            boolean zExists = file.exists();
            boolean zIsDirectory = file.isDirectory();
            if (!zExists || zIsDirectory) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d file not suit, exists[%B], directory[%B]", Integer.valueOf(i), Boolean.valueOf(zExists), Boolean.valueOf(zIsDirectory));
                }
            } else {
                long length = file.length();
                long soFar = fileDownloadModel.getSoFar();
                if (fileDownloadModel.getConnectionCount() <= 1 && soFar == 0) {
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d the downloaded-record is zero.", Integer.valueOf(i));
                    }
                } else {
                    long total = fileDownloadModel.getTotal();
                    if (length < soFar || (total != -1 && (length > total || soFar >= total))) {
                        if (FileDownloadLog.NEED_LOG) {
                            FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d dirty data fileLength[%d] sofar[%d] total[%d]", Integer.valueOf(i), Long.valueOf(length), Long.valueOf(soFar), Long.valueOf(total));
                        }
                    } else {
                        if (bool == null || bool.booleanValue() || total != length) {
                            return true;
                        }
                        if (FileDownloadLog.NEED_LOG) {
                            FileDownloadLog.d(FileDownloadUtils.class, "can't continue %d, because of the output stream doesn't support seek, but the task has already pre-allocated, so we only can download it from the very beginning.", Integer.valueOf(i));
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void deleteTaskFiles(String str, String str2) {
        deleteTempFile(str2);
        deleteTargetFile(str);
    }

    public static void deleteTempFile(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void deleteTargetFile(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static boolean isNeedSync(long j, long j2) {
        return j > ((long) getMinProgressStep()) && j2 > getMinProgressTime();
    }

    public static String defaultUserAgent() {
        return formatString("FileDownloader/%s", BuildConfig.VERSION_NAME);
    }

    private static boolean isAppOnForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        PowerManager powerManager;
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(WanosJsBridge.H5_KEY_ACTIVITY);
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || (powerManager = (PowerManager) context.getSystemService("power")) == null || !powerManager.isInteractive()) {
            return false;
        }
        String packageName = context.getApplicationContext().getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static boolean needMakeServiceForeground(Context context) {
        return !isAppOnForeground(context);
    }

    static String findFileNameFromUrl(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                String path = new URL(str).getPath();
                String strSubstring = path.substring(path.lastIndexOf(47) + 1);
                if (strSubstring.isEmpty()) {
                    return null;
                }
                return strSubstring;
            } catch (MalformedURLException unused) {
            }
        }
        return null;
    }
}
