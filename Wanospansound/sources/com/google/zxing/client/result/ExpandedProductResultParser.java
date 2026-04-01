package com.google.zxing.client.result;

import com.google.common.base.Ascii;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.zxing.client.result.ResultParser
    public ExpandedProductParsedResult parse(Result result) {
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = getMassagedText(result);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String str10 = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        int i = 0;
        while (i < massagedText.length()) {
            String strFindAIvalue = findAIvalue(i, massagedText);
            if (strFindAIvalue == null) {
                return expandedProductParsedResult;
            }
            byte b2 = 2;
            int length = i + strFindAIvalue.length() + 2;
            String strFindValue = findValue(length, massagedText);
            int length2 = length + strFindValue.length();
            strFindAIvalue.hashCode();
            switch (strFindAIvalue.hashCode()) {
                case 1536:
                    b2 = !strFindAIvalue.equals("00") ? (byte) -1 : (byte) 0;
                    break;
                case 1537:
                    b2 = !strFindAIvalue.equals("01") ? (byte) -1 : (byte) 1;
                    break;
                case 1567:
                    if (!strFindAIvalue.equals("10")) {
                        b2 = -1;
                    }
                    break;
                case 1568:
                    b2 = !strFindAIvalue.equals("11") ? (byte) -1 : (byte) 3;
                    break;
                case 1570:
                    b2 = !strFindAIvalue.equals("13") ? (byte) -1 : (byte) 4;
                    break;
                case 1572:
                    b2 = !strFindAIvalue.equals("15") ? (byte) -1 : (byte) 5;
                    break;
                case 1574:
                    b2 = !strFindAIvalue.equals("17") ? (byte) -1 : (byte) 6;
                    break;
                case 1567966:
                    b2 = !strFindAIvalue.equals("3100") ? (byte) -1 : (byte) 7;
                    break;
                case 1567967:
                    b2 = !strFindAIvalue.equals("3101") ? (byte) -1 : (byte) 8;
                    break;
                case 1567968:
                    b2 = !strFindAIvalue.equals("3102") ? (byte) -1 : (byte) 9;
                    break;
                case 1567969:
                    b2 = !strFindAIvalue.equals("3103") ? (byte) -1 : (byte) 10;
                    break;
                case 1567970:
                    b2 = !strFindAIvalue.equals("3104") ? (byte) -1 : (byte) 11;
                    break;
                case 1567971:
                    b2 = !strFindAIvalue.equals("3105") ? (byte) -1 : Ascii.FF;
                    break;
                case 1567972:
                    b2 = !strFindAIvalue.equals("3106") ? (byte) -1 : Ascii.CR;
                    break;
                case 1567973:
                    b2 = !strFindAIvalue.equals("3107") ? (byte) -1 : Ascii.SO;
                    break;
                case 1567974:
                    b2 = !strFindAIvalue.equals("3108") ? (byte) -1 : Ascii.SI;
                    break;
                case 1567975:
                    b2 = !strFindAIvalue.equals("3109") ? (byte) -1 : Ascii.DLE;
                    break;
                case 1568927:
                    b2 = !strFindAIvalue.equals("3200") ? (byte) -1 : (byte) 17;
                    break;
                case 1568928:
                    b2 = !strFindAIvalue.equals("3201") ? (byte) -1 : Ascii.DC2;
                    break;
                case 1568929:
                    b2 = !strFindAIvalue.equals("3202") ? (byte) -1 : (byte) 19;
                    break;
                case 1568930:
                    b2 = !strFindAIvalue.equals("3203") ? (byte) -1 : Ascii.DC4;
                    break;
                case 1568931:
                    b2 = !strFindAIvalue.equals("3204") ? (byte) -1 : Ascii.NAK;
                    break;
                case 1568932:
                    b2 = !strFindAIvalue.equals("3205") ? (byte) -1 : Ascii.SYN;
                    break;
                case 1568933:
                    b2 = !strFindAIvalue.equals("3206") ? (byte) -1 : Ascii.ETB;
                    break;
                case 1568934:
                    b2 = !strFindAIvalue.equals("3207") ? (byte) -1 : Ascii.CAN;
                    break;
                case 1568935:
                    b2 = !strFindAIvalue.equals("3208") ? (byte) -1 : Ascii.EM;
                    break;
                case 1568936:
                    b2 = !strFindAIvalue.equals("3209") ? (byte) -1 : Ascii.SUB;
                    break;
                case 1575716:
                    b2 = !strFindAIvalue.equals("3920") ? (byte) -1 : Ascii.ESC;
                    break;
                case 1575717:
                    b2 = !strFindAIvalue.equals("3921") ? (byte) -1 : Ascii.FS;
                    break;
                case 1575718:
                    b2 = !strFindAIvalue.equals("3922") ? (byte) -1 : Ascii.GS;
                    break;
                case 1575719:
                    b2 = !strFindAIvalue.equals("3923") ? (byte) -1 : Ascii.RS;
                    break;
                case 1575747:
                    b2 = !strFindAIvalue.equals("3930") ? (byte) -1 : Ascii.US;
                    break;
                case 1575748:
                    b2 = !strFindAIvalue.equals("3931") ? (byte) -1 : (byte) 32;
                    break;
                case 1575749:
                    b2 = !strFindAIvalue.equals("3932") ? (byte) -1 : (byte) 33;
                    break;
                case 1575750:
                    b2 = !strFindAIvalue.equals("3933") ? (byte) -1 : (byte) 34;
                    break;
                default:
                    b2 = -1;
                    break;
            }
            switch (b2) {
                case 0:
                    str2 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 1:
                    str = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 2:
                    str3 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 3:
                    str4 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 4:
                    str5 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 5:
                    str6 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 6:
                    str7 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                    str8 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                    str8 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    strSubstring2 = strFindAIvalue.substring(3);
                    str10 = strFindValue;
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    if (strFindValue.length() < 4) {
                        return null;
                    }
                    String strSubstring4 = strFindValue.substring(3);
                    strSubstring3 = strFindValue.substring(0, 3);
                    str10 = strSubstring4;
                    i = length2;
                    expandedProductParsedResult = null;
                    strSubstring2 = strFindAIvalue.substring(3);
                    break;
                default:
                    map.put(strFindAIvalue, strFindValue);
                    i = length2;
                    expandedProductParsedResult = null;
                    break;
            }
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, str10, strSubstring2, strSubstring3, map);
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String strSubstring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == ')') {
                return sb.toString();
            }
            if (cCharAt < '0' || cCharAt > '9') {
                return null;
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(i);
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == '(') {
                if (findAIvalue(i2, strSubstring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
}
