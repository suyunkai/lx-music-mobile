package com.bumptech.glide.repackaged.com.google.common.collect;

/* JADX INFO: loaded from: classes2.dex */
final class Hashing {
    private static int MAX_TABLE_SIZE = 1073741824;

    static int smear(int i) {
        return Integer.rotateLeft(i * (-862048943), 15) * 461845907;
    }

    static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    static int closedTableSize(int i, double d2) {
        int iMax = Math.max(i, 2);
        int iHighestOneBit = Integer.highestOneBit(iMax);
        if (iMax <= ((int) (d2 * ((double) iHighestOneBit)))) {
            return iHighestOneBit;
        }
        int i2 = iHighestOneBit << 1;
        return i2 > 0 ? i2 : MAX_TABLE_SIZE;
    }
}
