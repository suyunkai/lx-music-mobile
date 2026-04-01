package okhttp3.internal;

import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.MediaType;

/* JADX INFO: compiled from: -MediaTypeCommon.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\bH\u0000\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\u0003*\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001a\f\u0010\u000f\u001a\u00020\b*\u00020\u0003H\u0000\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\b*\u00020\u0003\u001a\f\u0010\u0011\u001a\u00020\u0003*\u00020\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"PARAMETER", "Lkotlin/text/Regex;", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "commonEquals", "", "Lokhttp3/MediaType;", "other", "", "commonHashCode", "", "commonParameter", "name", "commonToMediaType", "commonToMediaTypeOrNull", "commonToString", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _MediaTypeCommonKt {
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final Regex TYPE_SUBTYPE = new Regex("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private static final Regex PARAMETER = new Regex(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    public static final String commonParameter(MediaType mediaType, String name) {
        Intrinsics.checkNotNullParameter(mediaType, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, mediaType.getParameterNamesAndValues().length - 1, 2);
        if (progressionLastElement < 0) {
            return null;
        }
        while (!StringsKt.equals(mediaType.getParameterNamesAndValues()[i], name, true)) {
            if (i == progressionLastElement) {
                return null;
            }
            i += 2;
        }
        return mediaType.getParameterNamesAndValues()[i + 1];
    }

    public static final boolean commonEquals(MediaType mediaType, Object obj) {
        Intrinsics.checkNotNullParameter(mediaType, "<this>");
        return (obj instanceof MediaType) && Intrinsics.areEqual(((MediaType) obj).getMediaType(), mediaType.getMediaType());
    }

    public static final String commonToString(MediaType mediaType) {
        Intrinsics.checkNotNullParameter(mediaType, "<this>");
        return mediaType.getMediaType();
    }

    public static final int commonHashCode(MediaType mediaType) {
        Intrinsics.checkNotNullParameter(mediaType, "<this>");
        return mediaType.getMediaType().hashCode();
    }

    public static final MediaType commonToMediaType(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String str2 = str;
        MatchResult matchResultMatchAtPolyfill = _UtilCommonKt.matchAtPolyfill(TYPE_SUBTYPE, str2, 0);
        if (matchResultMatchAtPolyfill == null) {
            throw new IllegalArgumentException("No subtype found for: \"" + str + '\"');
        }
        String lowerCase = matchResultMatchAtPolyfill.getGroupValues().get(1).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String lowerCase2 = matchResultMatchAtPolyfill.getGroupValues().get(2).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        ArrayList arrayList = new ArrayList();
        int last = matchResultMatchAtPolyfill.getRange().getLast();
        while (true) {
            int i = last + 1;
            if (i < str.length()) {
                MatchResult matchResultMatchAtPolyfill2 = _UtilCommonKt.matchAtPolyfill(PARAMETER, str2, i);
                if (!(matchResultMatchAtPolyfill2 != null)) {
                    StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                    String strSubstring = str.substring(i);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                    throw new IllegalArgumentException(sb.append(strSubstring).append("\" for: \"").append(str).append('\"').toString().toString());
                }
                MatchGroup matchGroup = matchResultMatchAtPolyfill2.getGroups().get(1);
                String value = matchGroup != null ? matchGroup.getValue() : null;
                if (value == null) {
                    last = matchResultMatchAtPolyfill2.getRange().getLast();
                } else {
                    MatchGroup matchGroup2 = matchResultMatchAtPolyfill2.getGroups().get(2);
                    String value2 = matchGroup2 != null ? matchGroup2.getValue() : null;
                    if (value2 == null) {
                        MatchGroup matchGroup3 = matchResultMatchAtPolyfill2.getGroups().get(3);
                        Intrinsics.checkNotNull(matchGroup3);
                        value2 = matchGroup3.getValue();
                    } else if (StringsKt.startsWith$default(value2, "'", false, 2, (Object) null) && StringsKt.endsWith$default(value2, "'", false, 2, (Object) null) && value2.length() > 2) {
                        value2 = value2.substring(1, value2.length() - 1);
                        Intrinsics.checkNotNullExpressionValue(value2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    ArrayList arrayList2 = arrayList;
                    arrayList2.add(value);
                    arrayList2.add(value2);
                    last = matchResultMatchAtPolyfill2.getRange().getLast();
                }
            } else {
                Object[] array = arrayList.toArray(new String[0]);
                Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                return new MediaType(str, lowerCase, lowerCase2, (String[]) array);
            }
        }
    }

    public static final MediaType commonToMediaTypeOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            return commonToMediaType(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
