package com.ecarx.eas.framework.sdk.common.wrappers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PackageManagerWrapper {
    private final String TAG = "PackageManagerWrapper";
    private final Context mContext;

    public PackageManagerWrapper(Context context) {
        this.mContext = context;
    }

    public ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.mContext.getPackageManager().getApplicationInfo(str, i);
    }

    public PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.mContext.getPackageManager().getPackageInfo(str, i);
    }

    public final String[] getPackagesForUid(int i) {
        return this.mContext.getPackageManager().getPackagesForUid(i);
    }

    public ServiceInfo findService(Intent intent) {
        try {
            List<ResolveInfo> listQueryIntentServices = this.mContext.getPackageManager().queryIntentServices(intent, 0);
            if (listQueryIntentServices == null || listQueryIntentServices.size() == 0) {
                return null;
            }
            if (listQueryIntentServices.size() > 1) {
                Log.e("PackageManagerWrapper", "More than one Client Service's found in package " + intent.getPackage() + ", action " + intent.getAction() + ". They'll all be ignored.");
                return null;
            }
            return listQueryIntentServices.get(0).serviceInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProviderInfo findSmartSceneContentProvider(String str) {
        List<ProviderInfo> listQueryContentProviders;
        String processName = getProcessName(str);
        int uid = getUid(str);
        Log.e(this.TAG, "processName = " + str + ", uid = " + uid);
        if (!TextUtils.isEmpty(processName) && uid != -1 && (listQueryContentProviders = this.mContext.getPackageManager().queryContentProviders(processName, uid, 0)) != null && listQueryContentProviders.size() != 0) {
            for (ProviderInfo providerInfo : listQueryContentProviders) {
                Log.d(this.TAG, "providerInfo.authorit = " + providerInfo.authority);
                if ("com.ecarx.smartscene.appsetting.provider".equals(providerInfo.authority)) {
                    return providerInfo;
                }
            }
        }
        return null;
    }

    public int getUid(String str) {
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProcessName(String str) {
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(str, 0);
            return applicationInfo != null ? applicationInfo.processName : "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
