package okhttp3.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Closeable;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.FileSystem;
import okio.Options;
import okio.Path;

/* JADX INFO: compiled from: -UtilCommon.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000ê\u0001\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0000\u001a\u001a\u0010\u001a\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u001cH\u0080\bø\u0001\u0000\u001a0\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u001e\"\u0004\b\u0000\u0010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001f0!H\u0000\u001a\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013H\u0000\u001a%\u0010&\u001a\u00020\u0015\"\u0004\b\u0000\u0010'*\b\u0012\u0004\u0012\u0002H'0(2\u0006\u0010)\u001a\u0002H'H\u0000¢\u0006\u0002\u0010*\u001a\u0015\u0010+\u001a\u00020,*\u00020-2\u0006\u0010.\u001a\u00020,H\u0080\u0004\u001a\u0015\u0010+\u001a\u00020\u0017*\u00020,2\u0006\u0010.\u001a\u00020\u0017H\u0080\u0004\u001a\u0015\u0010+\u001a\u00020,*\u00020/2\u0006\u0010.\u001a\u00020,H\u0080\u0004\u001a\u000e\u00100\u001a\u00020\u0015*\u000601j\u0002`2\u001a%\u00103\u001a\b\u0012\u0004\u0012\u00020\u001304*\b\u0012\u0004\u0012\u00020\u0013042\u0006\u00105\u001a\u00020\u0013H\u0000¢\u0006\u0002\u00106\u001a\u0014\u00107\u001a\u00020\u0015*\u0002082\u0006\u00109\u001a\u00020:H\u0000\u001a\u0014\u0010;\u001a\u00020\u0015*\u0002082\u0006\u0010<\u001a\u00020:H\u0000\u001a&\u0010=\u001a\u00020,*\u00020\u00132\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020,2\b\b\u0002\u0010A\u001a\u00020,\u001a&\u0010=\u001a\u00020,*\u00020\u00132\u0006\u0010B\u001a\u00020\u00132\b\b\u0002\u0010@\u001a\u00020,2\b\b\u0002\u0010A\u001a\u00020,\u001a;\u0010C\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u001e\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0!2\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u00020$0E¢\u0006\u0002\bFH\u0080\bø\u0001\u0000\u001aC\u0010G\u001a\u00020$*\b\u0012\u0004\u0012\u00020\u0013042\u000e\u0010H\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001042\u001a\u0010I\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00130Jj\n\u0012\u0006\b\u0000\u0012\u00020\u0013`KH\u0000¢\u0006\u0002\u0010L\u001a7\u0010M\u001a\u00020,*\b\u0012\u0004\u0012\u00020\u0013042\u0006\u00105\u001a\u00020\u00132\u0016\u0010I\u001a\u0012\u0012\u0004\u0012\u00020\u00130Jj\b\u0012\u0004\u0012\u00020\u0013`KH\u0000¢\u0006\u0002\u0010N\u001a\f\u0010O\u001a\u00020,*\u00020\u0013H\u0000\u001a \u0010P\u001a\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001a\u00020,2\b\b\u0002\u0010A\u001a\u00020,H\u0000\u001a \u0010Q\u001a\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001a\u00020,2\b\b\u0002\u0010A\u001a\u00020,H\u0000\u001a\u0016\u0010R\u001a\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001a\u00020,H\u0000\u001aG\u0010S\u001a\b\u0012\u0004\u0012\u00020\u001304*\b\u0012\u0004\u0012\u00020\u0013042\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u0013042\u001a\u0010I\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00130Jj\n\u0012\u0006\b\u0000\u0012\u00020\u0013`KH\u0000¢\u0006\u0002\u0010T\u001a\u0014\u0010U\u001a\u00020$*\u0002082\u0006\u0010V\u001a\u00020:H\u0000\u001a\u001e\u0010W\u001a\u0004\u0018\u00010X*\u00020Y2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020,H\u0000\u001a\f\u0010]\u001a\u00020,*\u00020?H\u0000\u001a\f\u0010^\u001a\u00020,*\u00020_H\u0000\u001a\u0014\u0010`\u001a\u00020,*\u00020a2\u0006\u0010\"\u001a\u00020-H\u0000\u001a\u0012\u0010b\u001a\u00020\u0017*\u00020\u00132\u0006\u0010c\u001a\u00020\u0017\u001a\u0016\u0010d\u001a\u00020,*\u0004\u0018\u00010\u00132\u0006\u0010c\u001a\u00020,H\u0000\u001a\u001e\u0010e\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010@\u001a\u00020,2\b\b\u0002\u0010A\u001a\u00020,\u001a\"\u0010f\u001a\u00020g*\u00060hj\u0002`i2\u0010\u0010j\u001a\f\u0012\b\u0012\u00060hj\u0002`i0\u001eH\u0000\u001a\u0014\u0010k\u001a\u00020\u0015*\u00020l2\u0006\u0010m\u001a\u00020,H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006n"}, d2 = {"EMPTY_BYTE_ARRAY", "", "UNICODE_BOMS", "Lokio/Options;", "getUNICODE_BOMS", "()Lokio/Options;", "commonEmptyHeaders", "Lokhttp3/Headers;", "getCommonEmptyHeaders", "()Lokhttp3/Headers;", "commonEmptyRequestBody", "Lokhttp3/RequestBody;", "getCommonEmptyRequestBody", "()Lokhttp3/RequestBody;", "commonEmptyResponse", "Lokhttp3/ResponseBody;", "getCommonEmptyResponse", "()Lokhttp3/ResponseBody;", "userAgent", "", "checkOffsetAndCount", "", "arrayLength", "", "offset", "count", "ignoreIoExceptions", "block", "Lkotlin/Function0;", "interleave", "", ExifInterface.GPS_DIRECTION_TRUE, CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "", "b", "isSensitiveHeader", "", "name", "addIfAbsent", ExifInterface.LONGITUDE_EAST, "", "element", "(Ljava/util/List;Ljava/lang/Object;)V", "and", "", "", "mask", "", "closeQuietly", "Ljava/io/Closeable;", "Lokio/Closeable;", "concat", "", "value", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "deleteContents", "Lokio/FileSystem;", "directory", "Lokio/Path;", "deleteIfExists", "path", "delimiterOffset", TtmlNode.RUBY_DELIMITER, "", "startIndex", "endIndex", "delimiters", "filterList", "predicate", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "hasIntersection", "other", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "indexOf", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "indexOfControlOrNonAscii", "indexOfFirstNonAsciiWhitespace", "indexOfLastNonAsciiWhitespace", "indexOfNonWhitespace", "intersect", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "isCivilized", "file", "matchAtPolyfill", "Lkotlin/text/MatchResult;", "Lkotlin/text/Regex;", "input", "", "index", "parseHexDigit", "readMedium", "Lokio/BufferedSource;", "skipAll", "Lokio/Buffer;", "toLongOrDefault", "defaultValue", "toNonNegativeInt", "trimSubstring", "withSuppressed", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "suppressed", "writeMedium", "Lokio/BufferedSink;", "medium", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _UtilCommonKt {
    public static final byte[] EMPTY_BYTE_ARRAY;
    private static final Options UNICODE_BOMS = Options.INSTANCE.of(ByteString.INSTANCE.decodeHex("efbbbf"), ByteString.INSTANCE.decodeHex("feff"), ByteString.INSTANCE.decodeHex("fffe"), ByteString.INSTANCE.decodeHex("0000ffff"), ByteString.INSTANCE.decodeHex("ffff0000"));
    private static final Headers commonEmptyHeaders = Headers.INSTANCE.of(new String[0]);
    private static final RequestBody commonEmptyRequestBody;
    private static final ResponseBody commonEmptyResponse;
    public static final String userAgent = "okhttp/5.0.0-alpha.11";

    public static final int and(byte b2, int i) {
        return b2 & i;
    }

    public static final int and(short s, int i) {
        return s & i;
    }

    public static final long and(int i, long j) {
        return ((long) i) & j;
    }

    public static final int parseHexDigit(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (!('a' <= c2 && c2 < 'g')) {
            c3 = 'A';
            if (!('A' <= c2 && c2 < 'G')) {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static final MatchResult matchAtPolyfill(Regex regex, CharSequence input, int i) {
        Intrinsics.checkNotNullParameter(regex, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        MatchResult matchResultFind = regex.find(input, i);
        if (matchResultFind != null && matchResultFind.getRange().getFirst() == i) {
            return matchResultFind;
        }
        return null;
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        commonEmptyRequestBody = RequestBody.Companion.create$default(RequestBody.INSTANCE, bArr, (MediaType) null, 0, 0, 7, (Object) null);
        commonEmptyResponse = ResponseBody.Companion.create$default(ResponseBody.INSTANCE, bArr, (MediaType) null, 1, (Object) null);
    }

    public static final Options getUNICODE_BOMS() {
        return UNICODE_BOMS;
    }

    public static final String[] intersect(String[] strArr, String[] other, Comparator<? super String> comparator) {
        Intrinsics.checkNotNullParameter(strArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = other.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (comparator.compare(str, other[i]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[]) array;
    }

    public static final boolean hasIntersection(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        Intrinsics.checkNotNullParameter(strArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!(strArr.length == 0) && strArr2 != null) {
            if (!(strArr2.length == 0)) {
                for (String str : strArr) {
                    Iterator it = ArrayIteratorKt.iterator(strArr2);
                    while (it.hasNext()) {
                        if (comparator.compare(str, (String) it.next()) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final String[] concat(String[] strArr, String value) {
        Intrinsics.checkNotNullParameter(strArr, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length + 1);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        String[] strArr2 = (String[]) objArrCopyOf;
        strArr2[ArraysKt.getLastIndex(strArr2)] = value;
        Intrinsics.checkNotNull(strArr2, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
        return strArr2;
    }

    public static /* synthetic */ int indexOfFirstNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfFirstNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfFirstNonAsciiWhitespace(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (!((((cCharAt == '\t' || cCharAt == '\n') || cCharAt == '\f') || cCharAt == '\r') || cCharAt == ' ')) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int indexOfLastNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfLastNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfLastNonAsciiWhitespace(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i3 = i2 - 1;
        if (i <= i3) {
            while (true) {
                char cCharAt = str.charAt(i3);
                if (!((((cCharAt == '\t' || cCharAt == '\n') || cCharAt == '\f') || cCharAt == '\r') || cCharAt == ' ')) {
                    return i3 + 1;
                }
                if (i3 == i) {
                    break;
                }
                i3--;
            }
        }
        return i;
    }

    public static /* synthetic */ String trimSubstring$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return trimSubstring(str, i, i2);
    }

    public static final String trimSubstring(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int iIndexOfFirstNonAsciiWhitespace = indexOfFirstNonAsciiWhitespace(str, i, i2);
        String strSubstring = str.substring(iIndexOfFirstNonAsciiWhitespace, indexOfLastNonAsciiWhitespace(str, iIndexOfFirstNonAsciiWhitespace, i2));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, str2, i, i2);
    }

    public static final int delimiterOffset(String str, String delimiters, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        while (i < i2) {
            if (StringsKt.contains$default((CharSequence) delimiters, str.charAt(i), false, 2, (Object) null)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, char c2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, c2, i, i2);
    }

    public static final int delimiterOffset(String str, char c2, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        while (i < i2) {
            if (str.charAt(i) == c2) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int indexOfControlOrNonAscii(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Intrinsics.compare((int) cCharAt, 31) <= 0 || Intrinsics.compare((int) cCharAt, 127) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static final boolean isSensitiveHeader(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return StringsKt.equals(name, "Authorization", true) || StringsKt.equals(name, "Cookie", true) || StringsKt.equals(name, "Proxy-Authorization", true) || StringsKt.equals(name, "Set-Cookie", true);
    }

    public static final void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSink, "<this>");
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    public static final int readMedium(BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        return and(bufferedSource.readByte(), 255) | (and(bufferedSource.readByte(), 255) << 16) | (and(bufferedSource.readByte(), 255) << 8);
    }

    public static final void ignoreIoExceptions(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            block.invoke();
        } catch (IOException unused) {
        }
    }

    public static final int skipAll(Buffer buffer, byte b2) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        int i = 0;
        while (!buffer.exhausted() && buffer.getByte(0L) == b2) {
            i++;
            buffer.readByte();
        }
        return i;
    }

    public static /* synthetic */ int indexOfNonWhitespace$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return indexOfNonWhitespace(str, i);
    }

    public static final int indexOfNonWhitespace(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt != ' ' && cCharAt != '\t') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    public static final long toLongOrDefault(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static final int toNonNegativeInt(String str, int i) {
        if (str != null) {
            try {
                long j = Long.parseLong(str);
                if (j > 2147483647L) {
                    return Integer.MAX_VALUE;
                }
                if (j < 0) {
                    return 0;
                }
                return (int) j;
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static final void closeQuietly(Closeable closeable) {
        Intrinsics.checkNotNullParameter(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean isCivilized(okio.FileSystem r4, okio.Path r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            okio.Sink r0 = r4.sink(r5)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            r2 = r0
            okio.Sink r2 = (okio.Sink) r2     // Catch: java.lang.Throwable -> L1c
            r4.delete(r5)     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L1c
            r4 = 1
            return r4
        L19:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1c
            goto L20
        L1c:
            r2 = move-exception
            r3 = r2
            r2 = r1
            r1 = r3
        L20:
            if (r0 == 0) goto L2e
            r0.close()     // Catch: java.lang.Throwable -> L26
            goto L2e
        L26:
            r0 = move-exception
            if (r1 != 0) goto L2b
            r1 = r0
            goto L2e
        L2b:
            kotlin.ExceptionsKt.addSuppressed(r1, r0)
        L2e:
            if (r1 != 0) goto L38
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r4.delete(r5)
            r4 = 0
            return r4
        L38:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal._UtilCommonKt.isCivilized(okio.FileSystem, okio.Path):boolean");
    }

    public static final void deleteIfExists(FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            fileSystem.delete(path);
        } catch (FileNotFoundException unused) {
        }
    }

    public static final void deleteContents(FileSystem fileSystem, Path directory) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(directory, "directory");
        try {
            IOException iOException = null;
            for (Path path : fileSystem.list(directory)) {
                try {
                    if (fileSystem.metadata(path).getIsDirectory()) {
                        deleteContents(fileSystem, path);
                    }
                    fileSystem.delete(path);
                } catch (IOException e) {
                    if (iOException == null) {
                        iOException = e;
                    }
                }
            }
            if (iOException != null) {
                throw iOException;
            }
        } catch (FileNotFoundException unused) {
        }
    }

    public static final <E> void addIfAbsent(List<E> list, E e) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.contains(e)) {
            return;
        }
        list.add(e);
    }

    public static final Throwable withSuppressed(Exception exc, List<? extends Exception> suppressed) {
        Intrinsics.checkNotNullParameter(exc, "<this>");
        Intrinsics.checkNotNullParameter(suppressed, "suppressed");
        Iterator<? extends Exception> it = suppressed.iterator();
        while (it.hasNext()) {
            ExceptionsKt.addSuppressed(exc, it.next());
        }
        return exc;
    }

    public static final <T> List<T> filterList(Iterable<? extends T> iterable, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayListEmptyList = CollectionsKt.emptyList();
        for (T t : iterable) {
            if (predicate.invoke(t).booleanValue()) {
                if (arrayListEmptyList.isEmpty()) {
                    arrayListEmptyList = new ArrayList();
                }
                Intrinsics.checkNotNull(arrayListEmptyList, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal._UtilCommonKt.filterList>");
                TypeIntrinsics.asMutableList(arrayListEmptyList).add(t);
            }
        }
        return arrayListEmptyList;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("length=" + j + ", offset=" + j2 + ", count=" + j2);
        }
    }

    public static final Headers getCommonEmptyHeaders() {
        return commonEmptyHeaders;
    }

    public static final RequestBody getCommonEmptyRequestBody() {
        return commonEmptyRequestBody;
    }

    public static final ResponseBody getCommonEmptyResponse() {
        return commonEmptyResponse;
    }

    public static final <T> List<T> interleave(Iterable<? extends T> a2, Iterable<? extends T> b2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        Iterator<? extends T> it = a2.iterator();
        Iterator<? extends T> it2 = b2.iterator();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        while (true) {
            if (!it.hasNext() && !it2.hasNext()) {
                return CollectionsKt.build(listCreateListBuilder);
            }
            if (it.hasNext()) {
                listCreateListBuilder.add(it.next());
            }
            if (it2.hasNext()) {
                listCreateListBuilder.add(it2.next());
            }
        }
    }

    public static final int indexOf(String[] strArr, String value, Comparator<String> comparator) {
        Intrinsics.checkNotNullParameter(strArr, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(strArr[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }
}
