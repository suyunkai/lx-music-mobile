package com.baidubce.util;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.baidubce.BceErrorResponse;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.User;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BosResponse;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.CompleteMultipartUploadResponse;
import com.baidubce.services.bos.model.CopyObjectResponse;
import com.baidubce.services.bos.model.CopyObjectResponseWithExceptionInfo;
import com.baidubce.services.bos.model.GetBucketAclResponse;
import com.baidubce.services.bos.model.GetBucketLocationResponse;
import com.baidubce.services.bos.model.GetObjectAclResponse;
import com.baidubce.services.bos.model.Grant;
import com.baidubce.services.bos.model.Grantee;
import com.baidubce.services.bos.model.InitiateMultipartUploadResponse;
import com.baidubce.services.bos.model.ListBucketsResponse;
import com.baidubce.services.bos.model.ListMultipartUploadsResponse;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ListPartsResponse;
import com.baidubce.services.bos.model.MultipartUploadSummary;
import com.baidubce.services.bos.model.PartETag;
import com.baidubce.services.bos.model.PartSummary;
import com.baidubce.services.bos.model.Permission;
import com.baidubce.services.sts.model.GetSessionTokenResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class JsonUtils {
    public static void loadFromString(String str, AbstractBceResponse abstractBceResponse) throws JSONException, IllegalAccessException, NoSuchMethodException, IOException, IllegalArgumentException, ParseException, InvocationTargetException {
        JSONObject jSONObject = new JSONObject(str);
        if (abstractBceResponse.getClass() == ListBucketsResponse.class) {
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("owner"));
            User user = new User();
            user.setDisplayName(jSONObject2.getString("displayName"));
            user.setId(jSONObject2.getString("id"));
            JSONArray jSONArray = jSONObject.getJSONArray("buckets");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                BucketSummary bucketSummary = new BucketSummary();
                bucketSummary.setName(jSONObjectOptJSONObject.getString("name"));
                bucketSummary.setLocation(jSONObjectOptJSONObject.getString("location"));
                bucketSummary.setCreationDate(DateUtils.parseAlternateIso8601Date(jSONObjectOptJSONObject.getString("creationDate")));
                arrayList.add(bucketSummary);
            }
            abstractBceResponse.getClass().getMethod("setOwner", User.class).invoke(abstractBceResponse, user);
            abstractBceResponse.getClass().getMethod("setBuckets", List.class).invoke(abstractBceResponse, arrayList);
            return;
        }
        if (abstractBceResponse.getClass() == ListObjectsResponse.class) {
            abstractBceResponse.getClass().getMethod("setBucketName", String.class).invoke(abstractBceResponse, jSONObject.getString("name"));
            abstractBceResponse.getClass().getMethod("setMarker", String.class).invoke(abstractBceResponse, jSONObject.getString("marker"));
            if (jSONObject.has("nextMarker")) {
                abstractBceResponse.getClass().getMethod("setNextMarker", String.class).invoke(abstractBceResponse, jSONObject.getString("nextMarker"));
            }
            abstractBceResponse.getClass().getMethod("setMaxKeys", Integer.TYPE).invoke(abstractBceResponse, Integer.valueOf(jSONObject.getInt("maxKeys")));
            if (jSONObject.has("prefix")) {
                abstractBceResponse.getClass().getMethod("setPrefix", String.class).invoke(abstractBceResponse, jSONObject.getString("prefix"));
            }
            if (jSONObject.has(TtmlNode.RUBY_DELIMITER)) {
                abstractBceResponse.getClass().getMethod("setDelimiter", String.class).invoke(abstractBceResponse, jSONObject.getString(TtmlNode.RUBY_DELIMITER));
            }
            abstractBceResponse.getClass().getMethod("setTruncated", Boolean.TYPE).invoke(abstractBceResponse, Boolean.valueOf(jSONObject.getBoolean("isTruncated")));
            if (jSONObject.has("commonPrefixes")) {
                Method method = abstractBceResponse.getClass().getMethod("setCommonPrefixes", List.class);
                JSONArray jSONArray2 = jSONObject.getJSONArray("commonPrefixes");
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList2.add(jSONArray2.optJSONObject(i2).getString("prefix"));
                }
                method.invoke(abstractBceResponse, arrayList2);
            }
            JSONArray jSONArray3 = jSONObject.getJSONArray("contents");
            if (jSONArray3.length() > 0) {
                ArrayList arrayList3 = new ArrayList();
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    JSONObject jSONObjectOptJSONObject2 = jSONArray3.optJSONObject(i3);
                    BosObjectSummary bosObjectSummary = new BosObjectSummary();
                    bosObjectSummary.setETag(jSONObjectOptJSONObject2.getString("eTag"));
                    bosObjectSummary.setKey(jSONObjectOptJSONObject2.getString("key"));
                    bosObjectSummary.setSize(jSONObjectOptJSONObject2.getLong("size"));
                    bosObjectSummary.setLastModified(DateUtils.parseAlternateIso8601Date(jSONObjectOptJSONObject2.getString("lastModified")));
                    bosObjectSummary.setStorageClass(jSONObjectOptJSONObject2.getString("storageClass"));
                    JSONObject jSONObject3 = jSONObjectOptJSONObject2.getJSONObject("owner");
                    User user2 = new User();
                    user2.setId(jSONObject3.getString("id"));
                    user2.setDisplayName(jSONObject3.getString("displayName"));
                    bosObjectSummary.setOwner(user2);
                    arrayList3.add(bosObjectSummary);
                }
                abstractBceResponse.getClass().getMethod("setContents", List.class).invoke(abstractBceResponse, arrayList3);
                return;
            }
            return;
        }
        if (abstractBceResponse.getClass() == GetBucketAclResponse.class) {
            JSONObject jSONObject4 = new JSONObject(jSONObject.getString("owner"));
            Grantee grantee = new Grantee();
            grantee.setId(jSONObject4.getString("id"));
            abstractBceResponse.getClass().getMethod("setOwner", Grantee.class).invoke(abstractBceResponse, grantee);
            JSONArray jSONArray4 = jSONObject.getJSONArray("accessControlList");
            ArrayList arrayList4 = new ArrayList();
            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                JSONObject jSONObject5 = new JSONObject(jSONArray4.optJSONObject(i4).toString());
                JSONArray jSONArray5 = jSONObject5.getJSONArray("grantee");
                for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                    JSONObject jSONObjectOptJSONObject3 = jSONArray5.optJSONObject(i5);
                    Grantee grantee2 = new Grantee();
                    grantee2.setId(jSONObjectOptJSONObject3.getString("id"));
                    arrayList5.add(grantee2);
                }
                JSONArray jSONArray6 = jSONObject5.getJSONArray("permission");
                for (int i6 = 0; i6 < jSONArray6.length(); i6++) {
                    int i7 = AnonymousClass1.$SwitchMap$com$baidubce$services$bos$model$Permission[Permission.valueOf(jSONArray6.get(i6).toString()).ordinal()];
                    if (i7 == 1) {
                        arrayList6.add(Permission.FULL_CONTROL);
                    } else if (i7 == 2) {
                        arrayList6.add(Permission.READ);
                    } else if (i7 == 3) {
                        arrayList6.add(Permission.WRITE);
                    }
                }
                arrayList4.add(new Grant(arrayList5, arrayList6));
            }
            abstractBceResponse.getClass().getMethod("setAccessControlList", List.class).invoke(abstractBceResponse, arrayList4);
            return;
        }
        if (abstractBceResponse.getClass() == GetObjectAclResponse.class) {
            JSONArray jSONArray7 = jSONObject.getJSONArray("accessControlList");
            ArrayList arrayList7 = new ArrayList();
            for (int i8 = 0; i8 < jSONArray7.length(); i8++) {
                ArrayList arrayList8 = new ArrayList();
                ArrayList arrayList9 = new ArrayList();
                JSONObject jSONObject6 = new JSONObject(jSONArray7.optJSONObject(i8).toString());
                JSONArray jSONArray8 = jSONObject6.getJSONArray("grantee");
                for (int i9 = 0; i9 < jSONArray8.length(); i9++) {
                    JSONObject jSONObjectOptJSONObject4 = jSONArray8.optJSONObject(i9);
                    Grantee grantee3 = new Grantee();
                    grantee3.setId(jSONObjectOptJSONObject4.getString("id"));
                    arrayList8.add(grantee3);
                }
                JSONArray jSONArray9 = jSONObject6.getJSONArray("permission");
                for (int i10 = 0; i10 < jSONArray9.length(); i10++) {
                    int i11 = AnonymousClass1.$SwitchMap$com$baidubce$services$bos$model$Permission[Permission.valueOf(jSONArray9.get(i10).toString()).ordinal()];
                    if (i11 == 1) {
                        arrayList9.add(Permission.FULL_CONTROL);
                    } else if (i11 == 2) {
                        arrayList9.add(Permission.READ);
                    } else if (i11 == 3) {
                        arrayList9.add(Permission.WRITE);
                    }
                }
                arrayList7.add(new Grant(arrayList8, arrayList9));
            }
            abstractBceResponse.getClass().getMethod("setAccessControlList", List.class).invoke(abstractBceResponse, arrayList7);
            return;
        }
        if (abstractBceResponse.getClass() == CopyObjectResponse.class) {
            abstractBceResponse.getClass().getMethod("setLastModified", Date.class).invoke(abstractBceResponse, DateUtils.parseAlternateIso8601Date(String.valueOf(jSONObject.get("lastModified"))));
            abstractBceResponse.getClass().getMethod("setETag", String.class).invoke(abstractBceResponse, jSONObject.get("eTag"));
            return;
        }
        if (abstractBceResponse.getClass() == CopyObjectResponseWithExceptionInfo.class) {
            abstractBceResponse.getClass().getMethod("setLastModified", Date.class).invoke(abstractBceResponse, DateUtils.parseAlternateIso8601Date(String.valueOf(jSONObject.get("lastModified"))));
            abstractBceResponse.getClass().getMethod("setETag", String.class).invoke(abstractBceResponse, jSONObject.get("eTag"));
            return;
        }
        if (abstractBceResponse.getClass() == InitiateMultipartUploadResponse.class) {
            abstractBceResponse.getClass().getMethod("setBucketName", String.class).invoke(abstractBceResponse, jSONObject.get("bucket"));
            abstractBceResponse.getClass().getMethod("setKey", String.class).invoke(abstractBceResponse, jSONObject.get("key"));
            abstractBceResponse.getClass().getMethod("setUploadId", String.class).invoke(abstractBceResponse, jSONObject.get("uploadId"));
            return;
        }
        if (abstractBceResponse.getClass() == CompleteMultipartUploadResponse.class) {
            abstractBceResponse.getClass().getMethod("setBucketName", String.class).invoke(abstractBceResponse, jSONObject.get("bucket"));
            abstractBceResponse.getClass().getMethod("setKey", String.class).invoke(abstractBceResponse, jSONObject.get("key"));
            abstractBceResponse.getClass().getMethod("setLocation", String.class).invoke(abstractBceResponse, jSONObject.get("location"));
            abstractBceResponse.getClass().getMethod("setETag", String.class).invoke(abstractBceResponse, jSONObject.get("eTag"));
            if (jSONObject.has("callback")) {
                abstractBceResponse.getClass().getMethod("setServerCallbackReturnBody", String.class).invoke(abstractBceResponse, jSONObject.getString("callback"));
                return;
            }
            return;
        }
        if (abstractBceResponse.getClass() == ListMultipartUploadsResponse.class) {
            abstractBceResponse.getClass().getMethod("setBucketName", String.class).invoke(abstractBceResponse, jSONObject.get("bucket"));
            if (jSONObject.has("keyMarker")) {
                abstractBceResponse.getClass().getMethod("setKeyMarker", String.class).invoke(abstractBceResponse, jSONObject.get("keyMarker"));
            }
            if (jSONObject.has("nextKeyMarker")) {
                abstractBceResponse.getClass().getMethod("setNextKeyMarker", String.class).invoke(abstractBceResponse, jSONObject.get("nextKeyMarker"));
            }
            abstractBceResponse.getClass().getMethod("setMaxUploads", Integer.TYPE).invoke(abstractBceResponse, jSONObject.get("maxUploads"));
            abstractBceResponse.getClass().getMethod("setPrefix", String.class).invoke(abstractBceResponse, jSONObject.get("prefix"));
            if (jSONObject.has(TtmlNode.RUBY_DELIMITER)) {
                abstractBceResponse.getClass().getMethod("setDelimiter", String.class).invoke(abstractBceResponse, jSONObject.get(TtmlNode.RUBY_DELIMITER));
            }
            abstractBceResponse.getClass().getMethod("setTruncated", Boolean.TYPE).invoke(abstractBceResponse, jSONObject.get("isTruncated"));
            if (jSONObject.has("commonPrefixes")) {
                Method method2 = abstractBceResponse.getClass().getMethod("setCommonPrefixes", List.class);
                JSONArray jSONArray10 = jSONObject.getJSONArray("commonPrefixes");
                ArrayList arrayList10 = new ArrayList();
                for (int i12 = 0; i12 < jSONArray10.length(); i12++) {
                    arrayList10.add(jSONArray10.optJSONObject(i12).getString("prefix"));
                }
                method2.invoke(abstractBceResponse, arrayList10);
            }
            ArrayList arrayList11 = new ArrayList();
            JSONArray jSONArray11 = jSONObject.getJSONArray("uploads");
            for (int i13 = 0; i13 < jSONArray11.length(); i13++) {
                JSONObject jSONObjectOptJSONObject5 = jSONArray11.optJSONObject(i13);
                MultipartUploadSummary multipartUploadSummary = new MultipartUploadSummary();
                multipartUploadSummary.setUploadId(jSONObjectOptJSONObject5.getString("uploadId"));
                multipartUploadSummary.setKey(jSONObjectOptJSONObject5.getString("key"));
                multipartUploadSummary.setInitiated(DateUtils.parseAlternateIso8601Date(jSONObjectOptJSONObject5.getString("initiated")));
                multipartUploadSummary.setStorageClass(jSONObjectOptJSONObject5.getString("storageClass"));
                JSONObject jSONObject7 = jSONObjectOptJSONObject5.getJSONObject("owner");
                User user3 = new User();
                user3.setId(jSONObject7.getString("id"));
                user3.setDisplayName(jSONObject7.getString("displayName"));
                multipartUploadSummary.setOwner(user3);
                arrayList11.add(multipartUploadSummary);
            }
            abstractBceResponse.getClass().getMethod("setMultipartUploads", List.class).invoke(abstractBceResponse, arrayList11);
            return;
        }
        if (abstractBceResponse.getClass() == ListPartsResponse.class) {
            abstractBceResponse.getClass().getMethod("setBucketName", String.class).invoke(abstractBceResponse, jSONObject.get("bucket"));
            abstractBceResponse.getClass().getMethod("setKey", String.class).invoke(abstractBceResponse, jSONObject.get("key"));
            abstractBceResponse.getClass().getMethod("setUploadId", String.class).invoke(abstractBceResponse, jSONObject.get("uploadId"));
            abstractBceResponse.getClass().getMethod("setInitiated", Date.class).invoke(abstractBceResponse, DateUtils.parseAlternateIso8601Date(jSONObject.getString("initiated")));
            abstractBceResponse.getClass().getMethod("setStorageClass", String.class).invoke(abstractBceResponse, jSONObject.get("storageClass"));
            abstractBceResponse.getClass().getMethod("setPartNumberMarker", Integer.TYPE).invoke(abstractBceResponse, jSONObject.get("partNumberMarker"));
            abstractBceResponse.getClass().getMethod("setNextPartNumberMarker", Integer.TYPE).invoke(abstractBceResponse, jSONObject.get("nextPartNumberMarker"));
            abstractBceResponse.getClass().getMethod("setMaxParts", Integer.TYPE).invoke(abstractBceResponse, Integer.valueOf(jSONObject.getInt("maxParts")));
            abstractBceResponse.getClass().getMethod("setTruncated", Boolean.TYPE).invoke(abstractBceResponse, jSONObject.get("isTruncated"));
            JSONObject jSONObject8 = new JSONObject(jSONObject.getString("owner"));
            User user4 = new User();
            user4.setDisplayName(jSONObject8.getString("displayName"));
            user4.setId(jSONObject8.getString("id"));
            abstractBceResponse.getClass().getMethod("setOwner", User.class).invoke(abstractBceResponse, user4);
            ArrayList arrayList12 = new ArrayList();
            JSONArray jSONArray12 = jSONObject.getJSONArray("parts");
            for (int i14 = 0; i14 < jSONArray12.length(); i14++) {
                JSONObject jSONObjectOptJSONObject6 = jSONArray12.optJSONObject(i14);
                PartSummary partSummary = new PartSummary();
                partSummary.setPartNumber(jSONObjectOptJSONObject6.getInt("partNumber"));
                partSummary.setETag(jSONObjectOptJSONObject6.getString("eTag"));
                partSummary.setSize(jSONObjectOptJSONObject6.getInt("size"));
                partSummary.setLastModified(DateUtils.parseAlternateIso8601Date(jSONObjectOptJSONObject6.getString("lastModified")));
                arrayList12.add(partSummary);
            }
            abstractBceResponse.getClass().getMethod("setParts", List.class).invoke(abstractBceResponse, arrayList12);
            return;
        }
        if (abstractBceResponse.getClass() == GetSessionTokenResponse.class) {
            abstractBceResponse.getClass().getMethod("setAccessKeyId", String.class).invoke(abstractBceResponse, jSONObject.get("accessKeyId"));
            abstractBceResponse.getClass().getMethod("setSecretAccessKey", String.class).invoke(abstractBceResponse, jSONObject.get("secretAccessKey"));
            abstractBceResponse.getClass().getMethod("setSessionToken", String.class).invoke(abstractBceResponse, jSONObject.get("sessionToken"));
            abstractBceResponse.getClass().getMethod("setExpiration", Date.class).invoke(abstractBceResponse, DateUtils.parseAlternateIso8601Date(jSONObject.getString("expiration")));
            return;
        }
        if (abstractBceResponse.getClass() == GetBucketLocationResponse.class) {
            abstractBceResponse.getClass().getMethod("setLocationConstraint", String.class).invoke(abstractBceResponse, jSONObject.get("locationConstraint"));
        } else if (abstractBceResponse.getClass() == BosResponse.class && jSONObject.has("callback")) {
            abstractBceResponse.getClass().getMethod("setServerCallbackReturnBody", String.class).invoke(abstractBceResponse, jSONObject.getString("callback"));
        }
    }

    /* JADX INFO: renamed from: com.baidubce.util.JsonUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$baidubce$services$bos$model$Permission;

        static {
            int[] iArr = new int[Permission.values().length];
            $SwitchMap$com$baidubce$services$bos$model$Permission = iArr;
            try {
                iArr[Permission.FULL_CONTROL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidubce$services$bos$model$Permission[Permission.READ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidubce$services$bos$model$Permission[Permission.WRITE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void load(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws JSONException, IllegalAccessException, NoSuchMethodException, IOException, ParseException, IllegalArgumentException, InvocationTargetException {
        load(bceHttpResponse.getContent(), abstractBceResponse);
    }

    public static void load(InputStream inputStream, AbstractBceResponse abstractBceResponse) throws JSONException, IllegalAccessException, NoSuchMethodException, IOException, ParseException, IllegalArgumentException, InvocationTargetException {
        if (inputStream == null) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                str = str + line;
            } else {
                inputStream.close();
                loadFromString(str, abstractBceResponse);
                return;
            }
        }
    }

    public static BceErrorResponse loadError(InputStream inputStream) throws JSONException, IOException {
        BceErrorResponse bceErrorResponse = new BceErrorResponse();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            str = str + line;
        }
        if (str.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        bceErrorResponse.setCode(jSONObject.getString("code"));
        bceErrorResponse.setMessage(jSONObject.getString("message"));
        bceErrorResponse.setRequestId(jSONObject.getString("requestId"));
        return bceErrorResponse;
    }

    public static String setAclJson(List<Grant> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        for (Grant grant : list) {
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            Iterator<Permission> it = grant.getPermission().iterator();
            while (it.hasNext()) {
                jSONArray2.put(it.next().toString());
            }
            jSONObject2.put("permission", jSONArray2);
            JSONArray jSONArray3 = new JSONArray();
            for (Grantee grantee : grant.getGrantee()) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("id", grantee.getId());
                jSONArray3.put(jSONObject3);
            }
            jSONObject2.put("grantee", jSONArray3);
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("accessControlList", jSONArray);
        return jSONObject.toString();
    }

    public static String setPartETag(List<PartETag> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        for (PartETag partETag : list) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("eTag", partETag.getETag());
            jSONObject2.put("partNumber", partETag.getPartNumber());
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("parts", jSONArray);
        return jSONObject.toString();
    }
}
