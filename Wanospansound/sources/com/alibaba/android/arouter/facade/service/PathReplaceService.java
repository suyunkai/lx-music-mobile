package com.alibaba.android.arouter.facade.service;

import android.net.Uri;
import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface PathReplaceService extends IProvider {
    String forString(String str);

    Uri forUri(Uri uri);
}
