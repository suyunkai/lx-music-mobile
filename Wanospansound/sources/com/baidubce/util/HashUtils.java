package com.baidubce.util;

import com.baidubce.BceClientException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class HashUtils {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static byte[] computeMd5Hash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        return computeHash(inputStream, MessageDigest.getInstance("MD5"));
    }

    public static byte[] computeSha256Hash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        return computeHash(inputStream, MessageDigest.getInstance("SHA-256"));
    }

    public static byte[] computeHash(InputStream inputStream, MessageDigest messageDigest) throws IOException {
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i = inputStream.read(bArr, 0, 16384);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    byte[] bArrDigest = messageDigest.digest();
                    try {
                        inputStream.close();
                        return bArrDigest;
                    } catch (Exception e) {
                        throw new BceClientException("Fail to close InputStream.", e);
                    }
                }
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
                throw th;
            } catch (Exception e2) {
                throw new BceClientException("Fail to close InputStream.", e2);
            }
        }
    }

    public static String sha256Hex(String str, String str2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            Charset charset = UTF8;
            mac.init(new SecretKeySpec(str.getBytes(charset), "HmacSHA256"));
            return new String(ConvertUtils.encodeHex(mac.doFinal(str2.getBytes(charset))));
        } catch (Exception e) {
            throw new BceClientException("Fail to generate the signature", e);
        }
    }
}
