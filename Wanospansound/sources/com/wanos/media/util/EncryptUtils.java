package com.wanos.media.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes3.dex */
public class EncryptUtils {
    public static String sha256(String plainString) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(plainString.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
