package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    public static int compare(byte a2, byte b2) {
        return a2 - b2;
    }

    public static byte saturatedCast(long value) {
        if (value > 127) {
            return (byte) 127;
        }
        if (value < -128) {
            return (byte) -128;
        }
        return (byte) value;
    }

    private SignedBytes() {
    }

    public static byte checkedCast(long value) {
        byte b2 = (byte) value;
        Preconditions.checkArgument(((long) b2) == value, "Out of range: %s", value);
        return b2;
    }

    public static byte min(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        byte b2 = array[0];
        for (int i = 1; i < array.length; i++) {
            byte b3 = array[i];
            if (b3 < b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    public static byte max(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        byte b2 = array[0];
        for (int i = 1; i < array.length; i++) {
            byte b3 = array[i];
            if (b3 > b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    public static String join(String separator, byte... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 5);
        sb.append((int) array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator).append((int) array[i]);
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(byte[] left, byte[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = SignedBytes.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    public static void sortDescending(byte[] array) {
        Preconditions.checkNotNull(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Bytes.reverse(array, fromIndex, toIndex);
    }
}
