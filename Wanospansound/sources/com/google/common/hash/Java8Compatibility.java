package com.google.common.hash;

import java.nio.Buffer;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
final class Java8Compatibility {
    static void clear(Buffer b2) {
        b2.clear();
    }

    static void flip(Buffer b2) {
        b2.flip();
    }

    static void limit(Buffer b2, int limit) {
        b2.limit(limit);
    }

    static void position(Buffer b2, int position) {
        b2.position(position);
    }

    private Java8Compatibility() {
    }
}
