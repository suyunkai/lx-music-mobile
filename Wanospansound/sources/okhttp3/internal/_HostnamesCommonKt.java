package okhttp3.internal;

import com.ecarx.eas.sdk.IServiceManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okio.Buffer;

/* JADX INFO: compiled from: -HostnamesCommon.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\u001a0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0000\u001a\"\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\n\u0010\u000e\u001a\u00020\u0003*\u00020\u0005\u001a\f\u0010\u000f\u001a\u00020\u0003*\u00020\u0005H\u0000\u001a\f\u0010\u0010\u001a\u00020\u0003*\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "decodeIpv4Suffix", "", "input", "", IServiceManager.SERVICE_POS, "", "limit", "address", "", "addressOffset", "decodeIpv6", "inet6AddressToAscii", "canParseAsIpAddress", "containsInvalidHostnameAsciiCodes", "containsInvalidLabelLengths", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _HostnamesCommonKt {
    private static final Regex VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static final boolean canParseAsIpAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return VERIFY_AS_IP_ADDRESS.matches(str);
    }

    public static final boolean containsInvalidLabelLengths(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        if (!(1 <= length && length < 254)) {
            return true;
        }
        int i = 0;
        while (true) {
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '.', i, false, 4, (Object) null);
            int length2 = iIndexOf$default == -1 ? str.length() - i : iIndexOf$default - i;
            if (!(1 <= length2 && length2 < 64)) {
                return true;
            }
            if (iIndexOf$default == -1 || iIndexOf$default == str.length() - 1) {
                break;
            }
            i = iIndexOf$default + 1;
        }
        return false;
    }

    public static final boolean containsInvalidHostnameAsciiCodes(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Intrinsics.compare((int) cCharAt, 31) <= 0 || Intrinsics.compare((int) cCharAt, 127) >= 0 || StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", cCharAt, 0, false, 6, (Object) null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x009b, code lost:
    
        if (r13 == 16) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009d, code lost:
    
        if (r14 != (-1)) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009f, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a0, code lost:
    
        kotlin.collections.ArraysKt.copyInto(r9, r9, 16 - (r13 - r14), r14, r13);
        kotlin.collections.ArraysKt.fill(r9, (byte) 0, r14, (16 - r13) + r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ad, code lost:
    
        return r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final byte[] decodeIpv6(java.lang.String r18, int r19, int r20) {
        /*
            r6 = r18
            r7 = r20
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r8 = 16
            byte[] r9 = new byte[r8]
            r11 = -1
            r12 = r19
            r14 = r11
            r15 = r14
            r13 = 0
        L13:
            r16 = 0
            if (r12 >= r7) goto L9b
            if (r13 != r8) goto L1a
            return r16
        L1a:
            int r5 = r12 + 2
            if (r5 > r7) goto L3d
            java.lang.String r1 = "::"
            r3 = 0
            r4 = 4
            r17 = 0
            r0 = r18
            r2 = r12
            r10 = r5
            r5 = r17
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4, r5)
            if (r0 == 0) goto L3d
            if (r14 == r11) goto L33
            return r16
        L33:
            int r13 = r13 + 2
            if (r10 != r7) goto L3a
            r14 = r13
            goto L9b
        L3a:
            r15 = r10
            r14 = r13
            goto L6c
        L3d:
            if (r13 == 0) goto L6b
            java.lang.String r1 = ":"
            r3 = 0
            r4 = 4
            r5 = 0
            r0 = r18
            r2 = r12
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4, r5)
            if (r0 == 0) goto L50
            int r12 = r12 + 1
            goto L6b
        L50:
            java.lang.String r1 = "."
            r3 = 0
            r4 = 4
            r5 = 0
            r0 = r18
            r2 = r12
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4, r5)
            if (r0 == 0) goto L6a
            int r0 = r13 + (-2)
            boolean r0 = decodeIpv4Suffix(r6, r15, r7, r9, r0)
            if (r0 != 0) goto L67
            return r16
        L67:
            int r13 = r13 + 2
            goto L9b
        L6a:
            return r16
        L6b:
            r15 = r12
        L6c:
            r12 = r15
            r0 = 0
        L6e:
            if (r12 >= r7) goto L80
            char r1 = r6.charAt(r12)
            int r1 = okhttp3.internal._UtilCommonKt.parseHexDigit(r1)
            if (r1 == r11) goto L80
            int r0 = r0 << 4
            int r0 = r0 + r1
            int r12 = r12 + 1
            goto L6e
        L80:
            int r1 = r12 - r15
            if (r1 == 0) goto L9a
            r2 = 4
            if (r1 <= r2) goto L88
            goto L9a
        L88:
            int r1 = r13 + 1
            int r2 = r0 >>> 8
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r9[r13] = r2
            int r13 = r1 + 1
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r9[r1] = r0
            goto L13
        L9a:
            return r16
        L9b:
            if (r13 == r8) goto Lad
            if (r14 != r11) goto La0
            return r16
        La0:
            int r0 = r13 - r14
            int r0 = 16 - r0
            kotlin.collections.ArraysKt.copyInto(r9, r9, r0, r14, r13)
            int r8 = r8 - r13
            int r8 = r8 + r14
            r0 = 0
            kotlin.collections.ArraysKt.fill(r9, r0, r14, r8)
        Lad:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal._HostnamesCommonKt.decodeIpv6(java.lang.String, int, int):byte[]");
    }

    public static final boolean decodeIpv4Suffix(String input, int i, int i2, byte[] address, int i3) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(address, "address");
        int i4 = i3;
        while (i < i2) {
            if (i4 == address.length) {
                return false;
            }
            if (i4 != i3) {
                if (input.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int i5 = i;
            int i6 = 0;
            while (i5 < i2) {
                char cCharAt = input.charAt(i5);
                if (Intrinsics.compare((int) cCharAt, 48) < 0 || Intrinsics.compare((int) cCharAt, 57) > 0) {
                    break;
                }
                if ((i6 == 0 && i != i5) || (i6 = ((i6 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i5++;
            }
            if (i5 - i == 0) {
                return false;
            }
            address[i4] = (byte) i6;
            i4++;
            i = i5;
        }
        return i4 == i3 + 4;
    }

    public static final String inet6AddressToAscii(byte[] address) {
        Intrinsics.checkNotNullParameter(address, "address");
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < address.length) {
            int i5 = i3;
            while (i5 < 16 && address[i5] == 0 && address[i5 + 1] == 0) {
                i5 += 2;
            }
            int i6 = i5 - i3;
            if (i6 > i4 && i6 >= 4) {
                i = i3;
                i4 = i6;
            }
            i3 = i5 + 2;
        }
        Buffer buffer = new Buffer();
        while (i2 < address.length) {
            if (i2 == i) {
                buffer.writeByte(58);
                i2 += i4;
                if (i2 == 16) {
                    buffer.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    buffer.writeByte(58);
                }
                buffer.writeHexadecimalUnsignedLong((_UtilCommonKt.and(address[i2], 255) << 8) | _UtilCommonKt.and(address[i2 + 1], 255));
                i2 += 2;
            }
        }
        return buffer.readUtf8();
    }
}
