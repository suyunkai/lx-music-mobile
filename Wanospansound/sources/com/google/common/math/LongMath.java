package com.google.common.math;

import androidx.media3.common.C;
import androidx.media3.exoplayer.MediaPeriodQueue;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;
import okhttp3.internal.connection.RealConnection;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public final class LongMath {
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, 11, 11, 11, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    static final long[] powersOf10 = {1, 10, 100, 1000, Renderer.DEFAULT_DURATION_TO_PROGRESS_US, SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, 1000000, 10000000, 100000000, C.NANOS_PER_SECOND, RealConnection.IDLE_CONNECTION_HEALTHY_NS, 100000000000L, MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    static boolean fitsInInt(long x) {
        return ((long) ((int) x)) == x;
    }

    public static boolean isPowerOfTwo(long x) {
        return (x > 0) & ((x & (x - 1)) == 0);
    }

    static int lessThanBranchFree(long x, long y) {
        return (int) ((~(~(x - y))) >>> 63);
    }

    public static long mean(long x, long y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    public static long saturatedAdd(long a2, long b2) {
        long j = a2 + b2;
        return (((b2 ^ a2) > 0L ? 1 : ((b2 ^ a2) == 0L ? 0 : -1)) < 0) | ((a2 ^ j) >= 0) ? j : ((j >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long saturatedSubtract(long a2, long b2) {
        long j = a2 - b2;
        return (((b2 ^ a2) > 0L ? 1 : ((b2 ^ a2) == 0L ? 0 : -1)) >= 0) | ((a2 ^ j) >= 0) ? j : ((j >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long ceilingPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        if (x > 4611686018427387904L) {
            throw new ArithmeticException("ceilingPowerOfTwo(" + x + ") is not representable as a long");
        }
        return 1 << (-Long.numberOfLeadingZeros(x - 1));
    }

    public static long floorPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        return 1 << (63 - Long.numberOfLeadingZeros(x));
    }

    /* JADX INFO: renamed from: com.google.common.math.LongMath$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static int log2(long x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(x));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(x - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(x);
                return (63 - iNumberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> iNumberOfLeadingZeros, x);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(x);
    }

    public static int log10(long x, RoundingMode mode) {
        int iLessThanBranchFree;
        MathPreconditions.checkPositive("x", x);
        int iLog10Floor = log10Floor(x);
        long j = powersOf10[iLog10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(x == j);
            case 2:
            case 3:
                return iLog10Floor;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(j, x);
                return iLog10Floor + iLessThanBranchFree;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree(halfPowersOf10[iLog10Floor], x);
                return iLog10Floor + iLessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    static int log10Floor(long x) {
        byte b2 = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(x)];
        return b2 - lessThanBranchFree(x, powersOf10[b2]);
    }

    public static long pow(long b2, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (-2 > b2 || b2 > 2) {
            long j = 1;
            while (k != 0) {
                if (k == 1) {
                    return j * b2;
                }
                j *= (k & 1) == 0 ? 1L : b2;
                b2 *= b2;
                k >>= 1;
            }
            return j;
        }
        int i = (int) b2;
        if (i == -2) {
            if (k < 64) {
                return (k & 1) == 0 ? 1 << k : -(1 << k);
            }
            return 0L;
        }
        if (i == -1) {
            return (k & 1) == 0 ? 1L : -1L;
        }
        if (i == 0) {
            return k == 0 ? 1L : 0L;
        }
        if (i == 1) {
            return 1L;
        }
        if (i != 2) {
            throw new AssertionError();
        }
        if (k < 64) {
            return 1 << k;
        }
        return 0L;
    }

    public static long sqrt(long j, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", j);
        if (fitsInInt(j)) {
            return IntMath.sqrt((int) j, roundingMode);
        }
        long jSqrt = (long) Math.sqrt(j);
        long j2 = jSqrt * jSqrt;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j2 == j);
                return jSqrt;
            case 2:
            case 3:
                return j < j2 ? jSqrt - 1 : jSqrt;
            case 4:
            case 5:
                return j > j2 ? jSqrt + 1 : jSqrt;
            case 6:
            case 7:
            case 8:
                long j3 = jSqrt - ((long) (j >= j2 ? 0 : 1));
                return j3 + ((long) lessThanBranchFree((j3 * j3) + j3, j));
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long divide(long r9, long r11, java.math.RoundingMode r13) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r13)
            long r0 = r9 / r11
            long r2 = r11 * r0
            long r2 = r9 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L10
            return r0
        L10:
            long r9 = r9 ^ r11
            r7 = 63
            long r9 = r9 >> r7
            int r9 = (int) r9
            r10 = 1
            r9 = r9 | r10
            int[] r7 = com.google.common.math.LongMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r8 = r13.ordinal()
            r7 = r7[r8]
            r8 = 0
            switch(r7) {
                case 1: goto L50;
                case 2: goto L57;
                case 3: goto L4d;
                case 4: goto L58;
                case 5: goto L4a;
                case 6: goto L29;
                case 7: goto L29;
                case 8: goto L29;
                default: goto L23;
            }
        L23:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L29:
            long r2 = java.lang.Math.abs(r2)
            long r11 = java.lang.Math.abs(r11)
            long r11 = r11 - r2
            long r2 = r2 - r11
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 != 0) goto L47
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_UP
            if (r13 == r11) goto L58
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_EVEN
            if (r13 != r11) goto L57
            r11 = 1
            long r11 = r11 & r0
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 == 0) goto L57
            goto L58
        L47:
            if (r11 <= 0) goto L57
            goto L58
        L4a:
            if (r9 <= 0) goto L57
            goto L58
        L4d:
            if (r9 >= 0) goto L57
            goto L58
        L50:
            if (r6 != 0) goto L53
            goto L54
        L53:
            r10 = r8
        L54:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r10)
        L57:
            r10 = r8
        L58:
            if (r10 == 0) goto L5c
            long r9 = (long) r9
            long r0 = r0 + r9
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.divide(long, long, java.math.RoundingMode):long");
    }

    public static int mod(long x, int m) {
        return (int) mod(x, m);
    }

    public static long mod(long x, long m) {
        if (m <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        long j = x % m;
        return j >= 0 ? j : j + m;
    }

    public static long gcd(long a2, long b2) {
        MathPreconditions.checkNonNegative(CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, a2);
        MathPreconditions.checkNonNegative("b", b2);
        if (a2 == 0) {
            return b2;
        }
        if (b2 == 0) {
            return a2;
        }
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(a2);
        long jNumberOfTrailingZeros = a2 >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(b2);
        long j = b2 >> iNumberOfTrailingZeros2;
        while (jNumberOfTrailingZeros != j) {
            long j2 = jNumberOfTrailingZeros - j;
            long j3 = (j2 >> 63) & j2;
            long j4 = (j2 - j3) - j3;
            j += j3;
            jNumberOfTrailingZeros = j4 >> Long.numberOfTrailingZeros(j4);
        }
        return jNumberOfTrailingZeros << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros2);
    }

    public static long checkedAdd(long a2, long b2) {
        long j = a2 + b2;
        MathPreconditions.checkNoOverflow(((a2 ^ b2) < 0) | ((a2 ^ j) >= 0), "checkedAdd", a2, b2);
        return j;
    }

    public static long checkedSubtract(long a2, long b2) {
        long j = a2 - b2;
        MathPreconditions.checkNoOverflow(((a2 ^ b2) >= 0) | ((a2 ^ j) >= 0), "checkedSubtract", a2, b2);
        return j;
    }

    public static long checkedMultiply(long a2, long b2) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(a2) + Long.numberOfLeadingZeros(~a2) + Long.numberOfLeadingZeros(b2) + Long.numberOfLeadingZeros(~b2);
        if (iNumberOfLeadingZeros > 65) {
            return a2 * b2;
        }
        MathPreconditions.checkNoOverflow(iNumberOfLeadingZeros >= 64, "checkedMultiply", a2, b2);
        MathPreconditions.checkNoOverflow((a2 >= 0) | (b2 != Long.MIN_VALUE), "checkedMultiply", a2, b2);
        long j = a2 * b2;
        MathPreconditions.checkNoOverflow(a2 == 0 || j / a2 == b2, "checkedMultiply", a2, b2);
        return j;
    }

    public static long checkedPow(long b2, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        long jCheckedMultiply = 1;
        if ((b2 >= -2) && (b2 <= 2)) {
            int i = (int) b2;
            if (i == -2) {
                MathPreconditions.checkNoOverflow(k < 64, "checkedPow", b2, k);
                return (k & 1) == 0 ? 1 << k : (-1) << k;
            }
            if (i == -1) {
                return (k & 1) == 0 ? 1L : -1L;
            }
            if (i == 0) {
                return k == 0 ? 1L : 0L;
            }
            if (i == 1) {
                return 1L;
            }
            if (i == 2) {
                MathPreconditions.checkNoOverflow(k < 63, "checkedPow", b2, k);
                return 1 << k;
            }
            throw new AssertionError();
        }
        long j = b2;
        int i2 = k;
        while (i2 != 0) {
            if (i2 == 1) {
                return checkedMultiply(jCheckedMultiply, j);
            }
            if ((i2 & 1) != 0) {
                jCheckedMultiply = checkedMultiply(jCheckedMultiply, j);
            }
            long j2 = jCheckedMultiply;
            int i3 = i2 >> 1;
            if (i3 > 0) {
                MathPreconditions.checkNoOverflow(-3037000499L <= j && j <= FLOOR_SQRT_MAX_LONG, "checkedPow", j, i3);
                j *= j;
            }
            i2 = i3;
            jCheckedMultiply = j2;
        }
        return jCheckedMultiply;
    }

    public static long saturatedMultiply(long a2, long b2) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(a2) + Long.numberOfLeadingZeros(~a2) + Long.numberOfLeadingZeros(b2) + Long.numberOfLeadingZeros(~b2);
        if (iNumberOfLeadingZeros > 65) {
            return a2 * b2;
        }
        long j = ((a2 ^ b2) >>> 63) + Long.MAX_VALUE;
        if ((iNumberOfLeadingZeros < 64) || ((b2 == Long.MIN_VALUE) & (a2 < 0))) {
            return j;
        }
        long j2 = a2 * b2;
        return (a2 == 0 || j2 / a2 == b2) ? j2 : j;
    }

    public static long saturatedPow(long b2, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        long jSaturatedMultiply = 1;
        if (!(b2 >= -2) || !(b2 <= 2)) {
            long j = ((b2 >>> 63) & ((long) (k & 1))) + Long.MAX_VALUE;
            while (k != 0) {
                if (k == 1) {
                    return saturatedMultiply(jSaturatedMultiply, b2);
                }
                if ((k & 1) != 0) {
                    jSaturatedMultiply = saturatedMultiply(jSaturatedMultiply, b2);
                }
                k >>= 1;
                if (k > 0) {
                    if ((-3037000499L > b2) || (b2 > FLOOR_SQRT_MAX_LONG)) {
                        return j;
                    }
                    b2 *= b2;
                }
            }
            return jSaturatedMultiply;
        }
        int i = (int) b2;
        if (i == -2) {
            return k >= 64 ? ((long) (k & 1)) + Long.MAX_VALUE : (k & 1) == 0 ? 1 << k : (-1) << k;
        }
        if (i == -1) {
            return (k & 1) == 0 ? 1L : -1L;
        }
        if (i == 0) {
            return k == 0 ? 1L : 0L;
        }
        if (i == 1) {
            return 1L;
        }
        if (i != 2) {
            throw new AssertionError();
        }
        if (k >= 63) {
            return Long.MAX_VALUE;
        }
        return 1 << k;
    }

    public static long factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        long[] jArr = factorials;
        if (n < jArr.length) {
            return jArr[n];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int n, int k) {
        MathPreconditions.checkNonNegative("n", n);
        MathPreconditions.checkNonNegative("k", k);
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        long jMultiplyFraction = 1;
        if (k == 0) {
            return 1L;
        }
        if (k == 1) {
            return n;
        }
        long[] jArr = factorials;
        if (n < jArr.length) {
            return jArr[n] / (jArr[k] * jArr[n - k]);
        }
        int[] iArr = biggestBinomials;
        if (k >= iArr.length || n > iArr[k]) {
            return Long.MAX_VALUE;
        }
        int[] iArr2 = biggestSimpleBinomials;
        if (k < iArr2.length && n <= iArr2[k]) {
            int i = n - 1;
            long j = n;
            for (int i2 = 2; i2 <= k; i2++) {
                j = (j * ((long) i)) / ((long) i2);
                i--;
            }
            return j;
        }
        long j2 = n;
        int iLog2 = log2(j2, RoundingMode.CEILING);
        int i3 = n - 1;
        int i4 = iLog2;
        int i5 = 2;
        long j3 = j2;
        long j4 = 1;
        while (i5 <= k) {
            i4 += iLog2;
            if (i4 < 63) {
                j3 *= (long) i3;
                j4 *= (long) i5;
            } else {
                jMultiplyFraction = multiplyFraction(jMultiplyFraction, j3, j4);
                j3 = i3;
                j4 = i5;
                i4 = iLog2;
            }
            i5++;
            i3--;
        }
        return multiplyFraction(jMultiplyFraction, j3, j4);
    }

    static long multiplyFraction(long x, long numerator, long denominator) {
        if (x == 1) {
            return numerator / denominator;
        }
        long jGcd = gcd(x, denominator);
        return (x / jGcd) * (numerator / (denominator / jGcd));
    }

    public static boolean isPrime(long n) {
        if (n < 2) {
            MathPreconditions.checkNonNegative("n", n);
            return false;
        }
        if (n < 66) {
            return ((722865708377213483 >> (((int) n) + (-2))) & 1) != 0;
        }
        if (((1 << ((int) (n % 30))) & SIEVE_30) != 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0) {
            return false;
        }
        if (n < 289) {
            return true;
        }
        for (long[] jArr : millerRabinBaseSets) {
            if (n <= jArr[0]) {
                for (int i = 1; i < jArr.length; i++) {
                    if (!MillerRabinTester.test(jArr[i], n)) {
                        return false;
                    }
                }
                return true;
            }
        }
        throw new AssertionError();
    }

    private enum MillerRabinTester {
        SMALL { // from class: com.google.common.math.LongMath.MillerRabinTester.1
            @Override // com.google.common.math.LongMath.MillerRabinTester
            long mulMod(long a2, long b2, long m) {
                return (a2 * b2) % m;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long squareMod(long a2, long m) {
                return (a2 * a2) % m;
            }
        },
        LARGE { // from class: com.google.common.math.LongMath.MillerRabinTester.2
            private long plusMod(long a2, long b2, long m) {
                long j = a2 + b2;
                return a2 >= m - b2 ? j - m : j;
            }

            private long times2ToThe32Mod(long a2, long m) {
                int i = 32;
                do {
                    int iMin = Math.min(i, Long.numberOfLeadingZeros(a2));
                    a2 = UnsignedLongs.remainder(a2 << iMin, m);
                    i -= iMin;
                } while (i > 0);
                return a2;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long mulMod(long a2, long b2, long m) {
                long j = a2 >>> 32;
                long j2 = b2 >>> 32;
                long j3 = a2 & 4294967295L;
                long j4 = b2 & 4294967295L;
                long jTimes2ToThe32Mod = times2ToThe32Mod(j * j2, m) + (j * j4);
                if (jTimes2ToThe32Mod < 0) {
                    jTimes2ToThe32Mod = UnsignedLongs.remainder(jTimes2ToThe32Mod, m);
                }
                return plusMod(times2ToThe32Mod(jTimes2ToThe32Mod + (j2 * j3), m), UnsignedLongs.remainder(j3 * j4, m), m);
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long squareMod(long a2, long m) {
                long j = a2 >>> 32;
                long j2 = a2 & 4294967295L;
                long jTimes2ToThe32Mod = times2ToThe32Mod(j * j, m);
                long jRemainder = j * j2 * 2;
                if (jRemainder < 0) {
                    jRemainder = UnsignedLongs.remainder(jRemainder, m);
                }
                return plusMod(times2ToThe32Mod(jTimes2ToThe32Mod + jRemainder, m), UnsignedLongs.remainder(j2 * j2, m), m);
            }
        };

        abstract long mulMod(long a2, long b2, long m);

        abstract long squareMod(long a2, long m);

        /* synthetic */ MillerRabinTester(AnonymousClass1 anonymousClass1) {
            this();
        }

        static boolean test(long base, long n) {
            return (n <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(base, n);
        }

        private long powMod(long a2, long p, long m) {
            long jMulMod = 1;
            while (p != 0) {
                if ((p & 1) != 0) {
                    jMulMod = mulMod(jMulMod, a2, m);
                }
                a2 = squareMod(a2, m);
                p >>= 1;
            }
            return jMulMod;
        }

        private boolean testWitness(long base, long n) {
            long j = n - 1;
            int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j);
            long j2 = j >> iNumberOfTrailingZeros;
            long j3 = base % n;
            if (j3 == 0) {
                return true;
            }
            long jPowMod = powMod(j3, j2, n);
            if (jPowMod == 1) {
                return true;
            }
            int i = 0;
            while (jPowMod != j) {
                i++;
                if (i == iNumberOfTrailingZeros) {
                    return false;
                }
                jPowMod = squareMod(jPowMod, n);
            }
            return true;
        }
    }

    public static double roundToDouble(long x, RoundingMode mode) {
        long jCeil;
        double dNextUp;
        double d2 = x;
        long jFloor = (long) d2;
        int iCompare = jFloor == Long.MAX_VALUE ? -1 : Longs.compare(x, jFloor);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(iCompare == 0);
                return d2;
            case 2:
                return x >= 0 ? iCompare >= 0 ? d2 : DoubleUtils.nextDown(d2) : iCompare <= 0 ? d2 : Math.nextUp(d2);
            case 3:
                return iCompare >= 0 ? d2 : DoubleUtils.nextDown(d2);
            case 4:
                return x >= 0 ? iCompare <= 0 ? d2 : Math.nextUp(d2) : iCompare >= 0 ? d2 : DoubleUtils.nextDown(d2);
            case 5:
                return iCompare <= 0 ? d2 : Math.nextUp(d2);
            case 6:
            case 7:
            case 8:
                if (iCompare >= 0) {
                    dNextUp = Math.nextUp(d2);
                    jCeil = (long) Math.ceil(dNextUp);
                } else {
                    double dNextDown = DoubleUtils.nextDown(d2);
                    jFloor = (long) Math.floor(dNextDown);
                    jCeil = jFloor;
                    d2 = dNextDown;
                    dNextUp = d2;
                }
                long j = x - jFloor;
                long j2 = jCeil - x;
                if (jCeil == Long.MAX_VALUE) {
                    j2++;
                }
                int iCompare2 = Longs.compare(j, j2);
                if (iCompare2 < 0) {
                    return d2;
                }
                if (iCompare2 > 0) {
                    return dNextUp;
                }
                int i = AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()];
                if (i == 6) {
                    return x >= 0 ? d2 : dNextUp;
                }
                if (i == 7) {
                    return x >= 0 ? dNextUp : d2;
                }
                if (i == 8) {
                    return (DoubleUtils.getSignificand(d2) & 1) == 0 ? d2 : dNextUp;
                }
                throw new AssertionError("impossible");
            default:
                throw new AssertionError("impossible");
        }
    }

    private LongMath() {
    }
}
