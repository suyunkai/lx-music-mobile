package com.baidubce.auth;

import com.baidubce.BceConfig;
import com.baidubce.http.Headers;
import com.baidubce.internal.InternalRequest;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HashUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JoinerUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import okhttp3.internal.http.HttpMethod;

/* JADX INFO: loaded from: classes.dex */
public class BceV1Signer implements Signer {
    private static final Set<String> defaultHeadersToSign;

    static {
        HashSet hashSet = new HashSet();
        defaultHeadersToSign = hashSet;
        hashSet.add("Host".toLowerCase());
        hashSet.add("Content-Length".toLowerCase());
        hashSet.add("Content-Type".toLowerCase());
        hashSet.add("Content-MD5".toLowerCase());
    }

    @Override // com.baidubce.auth.Signer
    public void sign(InternalRequest internalRequest, BceCredentials bceCredentials) {
        sign(internalRequest, bceCredentials, null);
    }

    @Override // com.baidubce.auth.Signer
    public void sign(InternalRequest internalRequest, BceCredentials bceCredentials, SignOptions signOptions) {
        CheckUtils.isNotNull(internalRequest, "request should not be null.");
        if (bceCredentials == null) {
            return;
        }
        if (signOptions == null) {
            if (internalRequest.getSignOptions() != null) {
                signOptions = internalRequest.getSignOptions();
            } else {
                signOptions = SignOptions.DEFAULT;
            }
        }
        String accessKeyId = bceCredentials.getAccessKeyId();
        String secretKey = bceCredentials.getSecretKey();
        internalRequest.addHeader("Host", HttpUtils.generateHostHeader(internalRequest.getUri()));
        String strName = internalRequest.getHttpMethod().name();
        boolean z = HttpMethod.requiresRequestBody(strName) || HttpMethod.permitsRequestBody(strName);
        if (internalRequest.getHeaders().get("Content-Length") == null && internalRequest.getContent() == null && z) {
            internalRequest.addHeader("Content-Length", "0");
        }
        if (bceCredentials instanceof BceSessionCredentials) {
            if (signOptions.getUseStsHeader().booleanValue()) {
                internalRequest.addHeader(Headers.BCE_SECURITY_TOKEN, ((BceSessionCredentials) bceCredentials).getSessionToken());
            } else {
                internalRequest.addParameter(Headers.BCE_SECURITY_TOKEN, ((BceSessionCredentials) bceCredentials).getSessionToken());
            }
        }
        Date timestamp = signOptions.getTimestamp();
        if (timestamp == null) {
            timestamp = new Date();
        }
        String strOn = JoinerUtils.on(BceConfig.BOS_DELIMITER, BceConfig.BCE_AUTH_VERSION, accessKeyId, DateUtils.alternateIso8601DateFormat(timestamp), Integer.valueOf(signOptions.getExpirationInSeconds()));
        String strSha256Hex = HashUtils.sha256Hex(secretKey, strOn);
        String canonicalURIPath = getCanonicalURIPath(internalRequest.getUri().getPath());
        String canonicalQueryString = HttpUtils.getCanonicalQueryString(internalRequest.getParameters(), true);
        SortedMap<String, String> headersToSign = getHeadersToSign(internalRequest.getHeaders(), signOptions.getHeadersToSign());
        String canonicalHeaders = getCanonicalHeaders(headersToSign);
        String lowerCase = signOptions.getHeadersToSign() != null ? JoinerUtils.on(";", headersToSign.keySet().toArray()).trim().toLowerCase() : "";
        String strOn2 = JoinerUtils.on("\n", internalRequest.getHttpMethod(), canonicalURIPath, canonicalQueryString, canonicalHeaders);
        String strOn3 = JoinerUtils.on(BceConfig.BOS_DELIMITER, strOn, lowerCase, HashUtils.sha256Hex(strSha256Hex, strOn2));
        BLog.debug("CanonicalRequest:{}\tAuthorization:{}", strOn2.replace("\n", "[\\n]"), strOn3);
        internalRequest.addHeader("Authorization", strOn3);
    }

    private String getCanonicalURIPath(String str) {
        if (str == null) {
            return BceConfig.BOS_DELIMITER;
        }
        if (str.startsWith(BceConfig.BOS_DELIMITER)) {
            return HttpUtils.normalizePath(str);
        }
        return BceConfig.BOS_DELIMITER + HttpUtils.normalizePath(str);
    }

    private String getCanonicalHeaders(SortedMap<String, String> sortedMap) {
        if (sortedMap.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                arrayList.add(HttpUtils.normalize(key.trim().toLowerCase()) + ':' + HttpUtils.normalize(value.trim()));
            }
        }
        Collections.sort(arrayList);
        return JoinerUtils.on("\n", arrayList);
    }

    private SortedMap<String, String> getHeadersToSign(Map<String, String> map, Set<String> set) {
        TreeMap treeMap = new TreeMap();
        if (set != null) {
            HashSet hashSet = new HashSet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().trim().toLowerCase());
            }
            set = hashSet;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() != null && !entry.getValue().isEmpty() && ((set == null && isDefaultHeaderToSign(key)) || (set != null && set.contains(key.toLowerCase()) && !"Authorization".equalsIgnoreCase(key)))) {
                treeMap.put(key, entry.getValue());
            }
        }
        return treeMap;
    }

    private boolean isDefaultHeaderToSign(String str) {
        String lowerCase = str.trim().toLowerCase();
        return lowerCase.startsWith(Headers.BCE_PREFIX) || defaultHeadersToSign.contains(lowerCase);
    }
}
