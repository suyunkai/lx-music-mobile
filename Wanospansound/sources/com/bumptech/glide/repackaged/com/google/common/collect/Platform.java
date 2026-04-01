package com.bumptech.glide.repackaged.com.google.common.collect;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes2.dex */
final class Platform {
    static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
    }
}
