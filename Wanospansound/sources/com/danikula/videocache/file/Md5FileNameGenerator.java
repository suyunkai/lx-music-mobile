package com.danikula.videocache.file;

import android.text.TextUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.danikula.videocache.ProxyCacheUtils;

/* JADX INFO: loaded from: classes2.dex */
public class Md5FileNameGenerator implements FileNameGenerator {
    private static final int MAX_EXTENSION_LENGTH = 4;

    @Override // com.danikula.videocache.file.FileNameGenerator
    public String generate(String str) {
        String extension = getExtension(str);
        String strComputeMD5 = ProxyCacheUtils.computeMD5(str);
        return TextUtils.isEmpty(extension) ? strComputeMD5 : strComputeMD5 + Consts.DOT + extension;
    }

    private String getExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf <= str.lastIndexOf(47) || (iLastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(iLastIndexOf + 1);
    }
}
