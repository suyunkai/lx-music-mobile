package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: -HostnamesJvm.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"toCanonicalHost", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _HostnamesJvmKt {
    public static final String toCanonicalHost(String str) {
        byte[] bArrDecodeIpv6;
        Intrinsics.checkNotNullParameter(str, "<this>");
        String str2 = null;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null)) {
            if (StringsKt.startsWith$default(str, "[", false, 2, (Object) null) && StringsKt.endsWith$default(str, "]", false, 2, (Object) null)) {
                bArrDecodeIpv6 = _HostnamesCommonKt.decodeIpv6(str, 1, str.length() - 1);
            } else {
                bArrDecodeIpv6 = _HostnamesCommonKt.decodeIpv6(str, 0, str.length());
            }
            if (bArrDecodeIpv6 == null) {
                return null;
            }
            InetAddress byAddress = InetAddress.getByAddress(bArrDecodeIpv6);
            byte[] address = byAddress.getAddress();
            if (address.length == 16) {
                Intrinsics.checkNotNullExpressionValue(address, "address");
                return _HostnamesCommonKt.inet6AddressToAscii(address);
            }
            if (address.length == 4) {
                return byAddress.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        try {
            String ascii = IDN.toASCII(str);
            Intrinsics.checkNotNullExpressionValue(ascii, "toASCII(host)");
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = ascii.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (lowerCase.length() == 0) {
                return null;
            }
            if (_HostnamesCommonKt.containsInvalidHostnameAsciiCodes(lowerCase) || _HostnamesCommonKt.containsInvalidLabelLengths(lowerCase)) {
            } else {
                str2 = lowerCase;
            }
        } catch (IllegalArgumentException unused) {
        }
        return str2;
    }
}
