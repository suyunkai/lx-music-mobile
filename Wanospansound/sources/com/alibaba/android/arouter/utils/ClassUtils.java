package com.alibaba.android.arouter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class ClassUtils {
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String PREFS_FILE = "multidex.version";
    private static final String SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, 4);
    }

    public static Set<String> getFileNameByPackageName(Context context, final String str) throws InterruptedException, PackageManager.NameNotFoundException, IOException {
        final HashSet hashSet = new HashSet();
        List<String> sourcePaths = getSourcePaths(context);
        final CountDownLatch countDownLatch = new CountDownLatch(sourcePaths.size());
        for (final String str2 : sourcePaths) {
            DefaultPoolExecutor.getInstance().execute(new Runnable() { // from class: com.alibaba.android.arouter.utils.ClassUtils.1
                /* JADX WARN: Removed duplicated region for block: B:35:0x005b A[EXC_TOP_SPLITTER, PHI: r0
  0x005b: PHI (r0v3 dalvik.system.DexFile) = (r0v1 dalvik.system.DexFile), (r0v6 dalvik.system.DexFile) binds: [B:18:0x0059, B:14:0x004e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        r4 = this;
                        r0 = 0
                        java.lang.String r1 = r1     // Catch: java.lang.Throwable -> L51
                        java.lang.String r2 = ".zip"
                        boolean r1 = r1.endsWith(r2)     // Catch: java.lang.Throwable -> L51
                        if (r1 == 0) goto L28
                        java.lang.String r1 = r1     // Catch: java.lang.Throwable -> L51
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
                        r2.<init>()     // Catch: java.lang.Throwable -> L51
                        java.lang.String r3 = r1     // Catch: java.lang.Throwable -> L51
                        java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L51
                        java.lang.String r3 = ".tmp"
                        java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L51
                        java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L51
                        r3 = 0
                        dalvik.system.DexFile r0 = dalvik.system.DexFile.loadDex(r1, r2, r3)     // Catch: java.lang.Throwable -> L51
                        goto L30
                    L28:
                        dalvik.system.DexFile r1 = new dalvik.system.DexFile     // Catch: java.lang.Throwable -> L51
                        java.lang.String r2 = r1     // Catch: java.lang.Throwable -> L51
                        r1.<init>(r2)     // Catch: java.lang.Throwable -> L51
                        r0 = r1
                    L30:
                        java.util.Enumeration r1 = r0.entries()     // Catch: java.lang.Throwable -> L51
                    L34:
                        boolean r2 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L51
                        if (r2 == 0) goto L4e
                        java.lang.Object r2 = r1.nextElement()     // Catch: java.lang.Throwable -> L51
                        java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L51
                        java.lang.String r3 = r2     // Catch: java.lang.Throwable -> L51
                        boolean r3 = r2.startsWith(r3)     // Catch: java.lang.Throwable -> L51
                        if (r3 == 0) goto L34
                        java.util.Set r3 = r3     // Catch: java.lang.Throwable -> L51
                        r3.add(r2)     // Catch: java.lang.Throwable -> L51
                        goto L34
                    L4e:
                        if (r0 == 0) goto L5e
                        goto L5b
                    L51:
                        r1 = move-exception
                        java.lang.String r2 = "ARouter"
                        java.lang.String r3 = "Scan map file in dex files made error."
                        android.util.Log.e(r2, r3, r1)     // Catch: java.lang.Throwable -> L64
                        if (r0 == 0) goto L5e
                    L5b:
                        r0.close()     // Catch: java.lang.Throwable -> L5e
                    L5e:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        return
                    L64:
                        r1 = move-exception
                        if (r0 == 0) goto L6a
                        r0.close()     // Catch: java.lang.Throwable -> L6a
                    L6a:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.utils.ClassUtils.AnonymousClass1.run():void");
                }
            });
        }
        countDownLatch.await();
        Log.d("ARouter::", "Filter " + hashSet.size() + " classes by packageName <" + str + ">");
        return hashSet;
    }

    public static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + EXTRACTED_NAME_EXT;
        if (!isVMMultidexCapable()) {
            int i = getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1);
            File file2 = new File(applicationInfo.dataDir, SECONDARY_FOLDER_NAME);
            for (int i2 = 2; i2 <= i; i2++) {
                File file3 = new File(file2, str + i2 + EXTRACTED_SUFFIX);
                if (file3.isFile()) {
                    arrayList.add(file3.getAbsolutePath());
                } else {
                    throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
                }
            }
        }
        if (ARouter.debuggable()) {
            arrayList.addAll(tryLoadInstantRunDexFile(applicationInfo));
        }
        return arrayList;
    }

    private static List<String> tryLoadInstantRunDexFile(ApplicationInfo applicationInfo) {
        ArrayList arrayList = new ArrayList();
        if (applicationInfo.splitSourceDirs != null) {
            arrayList.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            Log.d("ARouter::", "Found InstantRun support");
        } else {
            try {
                File file = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", String.class).invoke(null, applicationInfo.packageName));
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists() && file2.isFile() && file2.getName().endsWith(".dex")) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    Log.d("ARouter::", "Found InstantRun support");
                }
            } catch (Exception e) {
                Log.e("ARouter::", "InstantRun support error, " + e.getMessage());
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d A[PHI: r1
  0x001d: PHI (r1v8 java.lang.String) = (r1v6 java.lang.String), (r1v6 java.lang.String), (r1v9 java.lang.String) binds: [B:13:0x004a, B:15:0x004e, B:6:0x001b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean isVMMultidexCapable() {
        /*
            r0 = 0
            r1 = 0
            boolean r2 = isYunOS()     // Catch: java.lang.Throwable -> L51
            r3 = 1
            if (r2 == 0) goto L1f
            java.lang.String r1 = "'YunOS'"
            java.lang.String r2 = "ro.build.version.sdk"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.Throwable -> L51
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L51
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L51
            r4 = 21
            if (r2 < r4) goto L51
        L1d:
            r0 = r3
            goto L51
        L1f:
            java.lang.String r1 = "'Android'"
            java.lang.String r2 = "java.vm.version"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L51
            java.lang.String r4 = "(\\d+)\\.(\\d+)(\\.\\d+)?"
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> L51
            java.util.regex.Matcher r2 = r4.matcher(r2)     // Catch: java.lang.Throwable -> L51
            boolean r4 = r2.matches()     // Catch: java.lang.Throwable -> L51
            if (r4 == 0) goto L51
            java.lang.String r4 = r2.group(r3)     // Catch: java.lang.Throwable -> L51
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.Throwable -> L51
            r5 = 2
            java.lang.String r2 = r2.group(r5)     // Catch: java.lang.Throwable -> L51
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Throwable -> L51
            if (r4 > r5) goto L1d
            if (r4 != r5) goto L51
            if (r2 < r3) goto L51
            goto L1d
        L51:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "VM with name "
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            if (r0 == 0) goto L61
            java.lang.String r2 = " has multidex support"
            goto L63
        L61:
            java.lang.String r2 = " does not have multidex support"
        L63:
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "ARouter::"
            android.util.Log.i(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.utils.ClassUtils.isVMMultidexCapable():boolean");
    }

    private static boolean isYunOS() {
        try {
            String property = System.getProperty("ro.yunos.version");
            String property2 = System.getProperty("java.vm.name");
            if (property2 == null || !property2.toLowerCase().contains("lemur")) {
                if (property == null) {
                    return false;
                }
                if (property.trim().length() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
