package kotlin.collections;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", TtmlNode.LEFT, TtmlNode.RIGHT, "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UArraySortingKt {
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m1174partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM794getw2LRezQ = UByteArray.m794getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM794getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m794getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m794getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM794getw2LRezQ2 = UByteArray.m794getw2LRezQ(bArr, i);
                UByteArray.m799setVurrAj0(bArr, i, UByteArray.m794getw2LRezQ(bArr, i2));
                UByteArray.m799setVurrAj0(bArr, i2, bM794getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m1178quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM1174partition4UcCI2c = m1174partition4UcCI2c(bArr, i, i2);
        int i3 = iM1174partition4UcCI2c - 1;
        if (i < i3) {
            m1178quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM1174partition4UcCI2c < i2) {
            m1178quickSort4UcCI2c(bArr, iM1174partition4UcCI2c, i2);
        }
    }

    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m1175partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM1057getMh2AYeg = UShortArray.m1057getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM1057getMh2AYeg = UShortArray.m1057getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = sM1057getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM1057getMh2AYeg, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m1057getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM1057getMh2AYeg2 = UShortArray.m1057getMh2AYeg(sArr, i);
                UShortArray.m1062set01HTLdE(sArr, i, UShortArray.m1057getMh2AYeg(sArr, i2));
                UShortArray.m1062set01HTLdE(sArr, i2, sM1057getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m1179quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM1175partitionAa5vz7o = m1175partitionAa5vz7o(sArr, i, i2);
        int i3 = iM1175partitionAa5vz7o - 1;
        if (i < i3) {
            m1179quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM1175partitionAa5vz7o < i2) {
            m1179quickSortAa5vz7o(sArr, iM1175partitionAa5vz7o, i2);
        }
    }

    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m1176partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM873getpVg5ArA = UIntArray.m873getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compareUnsigned(UIntArray.m873getpVg5ArA(iArr, i), iM873getpVg5ArA) < 0) {
                i++;
            }
            while (Integer.compareUnsigned(UIntArray.m873getpVg5ArA(iArr, i2), iM873getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM873getpVg5ArA2 = UIntArray.m873getpVg5ArA(iArr, i);
                UIntArray.m878setVXSXFK8(iArr, i, UIntArray.m873getpVg5ArA(iArr, i2));
                UIntArray.m878setVXSXFK8(iArr, i2, iM873getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m1180quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM1176partitionoBK06Vg = m1176partitionoBK06Vg(iArr, i, i2);
        int i3 = iM1176partitionoBK06Vg - 1;
        if (i < i3) {
            m1180quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM1176partitionoBK06Vg < i2) {
            m1180quickSortoBK06Vg(iArr, iM1176partitionoBK06Vg, i2);
        }
    }

    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m1173partitionnroSd4(long[] jArr, int i, int i2) {
        long jM952getsVKNKU = ULongArray.m952getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compareUnsigned(ULongArray.m952getsVKNKU(jArr, i), jM952getsVKNKU) < 0) {
                i++;
            }
            while (Long.compareUnsigned(ULongArray.m952getsVKNKU(jArr, i2), jM952getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM952getsVKNKU2 = ULongArray.m952getsVKNKU(jArr, i);
                ULongArray.m957setk8EXiF4(jArr, i, ULongArray.m952getsVKNKU(jArr, i2));
                ULongArray.m957setk8EXiF4(jArr, i2, jM952getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m1177quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM1173partitionnroSd4 = m1173partitionnroSd4(jArr, i, i2);
        int i3 = iM1173partitionnroSd4 - 1;
        if (i < i3) {
            m1177quickSortnroSd4(jArr, i, i3);
        }
        if (iM1173partitionnroSd4 < i2) {
            m1177quickSortnroSd4(jArr, iM1173partitionnroSd4, i2);
        }
    }

    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m1182sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1178quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m1183sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1179quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m1184sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1180quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m1181sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1177quickSortnroSd4(array, i, i2 - 1);
    }
}
