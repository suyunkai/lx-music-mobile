package com.google.common.base;

import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public final class Utf8 {
    public static int encodedLength(CharSequence sequence) {
        int length = sequence.length();
        int i = 0;
        while (i < length && sequence.charAt(i) < 128) {
            i++;
        }
        int iEncodedLengthGeneral = length;
        while (true) {
            if (i < length) {
                char cCharAt = sequence.charAt(i);
                if (cCharAt >= 2048) {
                    iEncodedLengthGeneral += encodedLengthGeneral(sequence, i);
                    break;
                }
                iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) iEncodedLengthGeneral) + IjkMediaMeta.AV_CH_WIDE_RIGHT));
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        int length = sequence.length();
        int i = 0;
        while (start < length) {
            char cCharAt = sequence.charAt(start);
            if (cCharAt < 2048) {
                i += (127 - cCharAt) >>> 31;
            } else {
                i += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(sequence, start) == cCharAt) {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(start));
                    }
                    start++;
                }
            }
            start++;
        }
        return i;
    }

    public static boolean isWellFormed(byte[] bytes) {
        return isWellFormed(bytes, 0, bytes.length);
    }

    public static boolean isWellFormed(byte[] bytes, int off, int len) {
        int i = len + off;
        Preconditions.checkPositionIndexes(off, i, bytes.length);
        while (off < i) {
            if (bytes[off] < 0) {
                return isWellFormedSlowPath(bytes, off, i);
            }
            off++;
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bytes, int off, int end) {
        byte b2;
        while (off < end) {
            int i = off + 1;
            byte b3 = bytes[off];
            if (b3 < 0) {
                if (b3 < -32) {
                    if (i != end && b3 >= -62) {
                        off = i + 1;
                        if (bytes[i] > -65) {
                        }
                    }
                    return false;
                }
                if (b3 < -16) {
                    int i2 = i + 1;
                    if (i2 < end && (b2 = bytes[i]) <= -65 && ((b3 != -32 || b2 >= -96) && (b3 != -19 || -96 > b2))) {
                        off = i2 + 1;
                        if (bytes[i2] > -65) {
                        }
                    }
                    return false;
                }
                if (i + 2 >= end) {
                    return false;
                }
                int i3 = i + 1;
                byte b4 = bytes[i];
                if (b4 <= -65 && (((b3 << Ascii.FS) + (b4 + 112)) >> 30) == 0) {
                    int i4 = i3 + 1;
                    if (bytes[i3] <= -65) {
                        i = i4 + 1;
                        if (bytes[i4] > -65) {
                        }
                    }
                }
                return false;
            }
            off = i;
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i) {
        return "Unpaired surrogate at index " + i;
    }

    private Utf8() {
    }
}
