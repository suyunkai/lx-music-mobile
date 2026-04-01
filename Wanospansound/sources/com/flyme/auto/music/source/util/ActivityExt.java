package com.flyme.auto.music.source.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityExt {
    public static String referrerAuthority(Activity activity) {
        String authority;
        if (activity == null) {
            return "";
        }
        try {
            authority = (String) ReflectUtils.from(activity).field("mReferrer").get(activity);
            if (authority != null) {
                return authority;
            }
            try {
                Uri referrer = activity.getReferrer();
                if (referrer != null) {
                    authority = referrer.getAuthority();
                }
                return authority != null ? authority : "";
            } catch (Exception unused) {
                Uri referrer2 = activity.getReferrer();
                if (referrer2 != null) {
                    authority = referrer2.getAuthority();
                }
                return authority == null ? "" : authority;
            }
        } catch (Exception unused2) {
            authority = "";
        }
    }

    public static Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (!(context instanceof ContextWrapper)) {
            return null;
        }
        Context baseContext = ((ContextWrapper) context).getBaseContext();
        if (baseContext instanceof Activity) {
            return (Activity) baseContext;
        }
        return null;
    }
}
