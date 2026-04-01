package com.baidubce.services.bos;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceConfig;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.BosMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableFileInputStream;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.internal.RestartableMultiByteArrayInputStream;
import com.baidubce.internal.RestartableNonResettableInputStream;
import com.baidubce.internal.RestartableResettableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.User;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.services.bos.common.utils.BosUtils;
import com.baidubce.services.bos.model.AbortMultipartUploadRequest;
import com.baidubce.services.bos.model.AppendObjectRequest;
import com.baidubce.services.bos.model.AppendObjectResponse;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BosResponse;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.CompleteMultipartUploadRequest;
import com.baidubce.services.bos.model.CompleteMultipartUploadResponse;
import com.baidubce.services.bos.model.CopyObjectRequest;
import com.baidubce.services.bos.model.CopyObjectResponse;
import com.baidubce.services.bos.model.CopyObjectResponseWithExceptionInfo;
import com.baidubce.services.bos.model.CreateBucketRequest;
import com.baidubce.services.bos.model.CreateBucketResponse;
import com.baidubce.services.bos.model.DeleteBucketRequest;
import com.baidubce.services.bos.model.DeleteObjectAclRequest;
import com.baidubce.services.bos.model.DeleteObjectRequest;
import com.baidubce.services.bos.model.DoesBucketExistRequest;
import com.baidubce.services.bos.model.GeneratePresignedUrlRequest;
import com.baidubce.services.bos.model.GenericBucketRequest;
import com.baidubce.services.bos.model.GenericObjectRequest;
import com.baidubce.services.bos.model.GetBosAccountOwnerRequest;
import com.baidubce.services.bos.model.GetBucketAclRequest;
import com.baidubce.services.bos.model.GetBucketAclResponse;
import com.baidubce.services.bos.model.GetBucketLocationRequest;
import com.baidubce.services.bos.model.GetBucketLocationResponse;
import com.baidubce.services.bos.model.GetObjectAclRequest;
import com.baidubce.services.bos.model.GetObjectAclResponse;
import com.baidubce.services.bos.model.GetObjectMetadataRequest;
import com.baidubce.services.bos.model.GetObjectRequest;
import com.baidubce.services.bos.model.GetObjectResponse;
import com.baidubce.services.bos.model.Grant;
import com.baidubce.services.bos.model.Grantee;
import com.baidubce.services.bos.model.InitiateMultipartUploadRequest;
import com.baidubce.services.bos.model.InitiateMultipartUploadResponse;
import com.baidubce.services.bos.model.ListBucketsRequest;
import com.baidubce.services.bos.model.ListBucketsResponse;
import com.baidubce.services.bos.model.ListMultipartUploadsRequest;
import com.baidubce.services.bos.model.ListMultipartUploadsResponse;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ListPartsRequest;
import com.baidubce.services.bos.model.ListPartsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PartETag;
import com.baidubce.services.bos.model.Permission;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.PutSuperObjectRequest;
import com.baidubce.services.bos.model.PutSuperObjectResponse;
import com.baidubce.services.bos.model.ResponseHeaderOverrides;
import com.baidubce.services.bos.model.SetBucketAclRequest;
import com.baidubce.services.bos.model.SetObjectAclRequest;
import com.baidubce.services.bos.model.UploadPartRequest;
import com.baidubce.services.bos.model.UploadPartResponse;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.ConvertUtils;
import com.baidubce.util.HashUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.MD5DigestCalculatingInputStream;
import com.baidubce.util.Mimetypes;
import com.loopj.android.http.RequestParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BosClient extends AbstractBceClient {
    public static final String STORAGE_CLASS_COLD = "COLD";
    public static final String STORAGE_CLASS_STANDARD = "STANDARD";
    public static final String STORAGE_CLASS_STANDARD_IA = "STANDARD_IA";
    private static final HttpResponseHandler[] bos_handlers = {new BceMetadataResponseHandler(), new BosMetadataResponseHandler(), new BceErrorResponseHandler(), new BosObjectResponseHandler(), new BceJsonResponseHandler()};

    public BosClient() {
        this(new BosClientConfiguration());
    }

    public BosClient(BosClientConfiguration bosClientConfiguration) {
        super(bosClientConfiguration, bos_handlers);
    }

    public User getBosAccountOwner() {
        return getBosAccountOwner(new GetBosAccountOwnerRequest());
    }

    public User getBosAccountOwner(GetBosAccountOwnerRequest getBosAccountOwnerRequest) {
        CheckUtils.isNotNull(getBosAccountOwnerRequest, "request should not be null.");
        return ((ListBucketsResponse) invokeHttpClient(createRequest(getBosAccountOwnerRequest, HttpMethodName.GET), ListBucketsResponse.class)).getOwner();
    }

    public ListBucketsResponse listBuckets() {
        return listBuckets(new ListBucketsRequest());
    }

    public ListBucketsResponse listBuckets(ListBucketsRequest listBucketsRequest) {
        CheckUtils.isNotNull(listBucketsRequest, "request should not be null.");
        return (ListBucketsResponse) invokeHttpClient(createRequest(listBucketsRequest, HttpMethodName.GET), ListBucketsResponse.class);
    }

    public CreateBucketResponse createBucket(String str) {
        return createBucket(new CreateBucketRequest(str));
    }

    public CreateBucketResponse createBucket(CreateBucketRequest createBucketRequest) {
        CheckUtils.isNotNull(createBucketRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(createBucketRequest, HttpMethodName.PUT);
        setZeroContentLength(internalRequestCreateRequest);
        BosResponse bosResponse = (BosResponse) invokeHttpClient(internalRequestCreateRequest, BosResponse.class);
        CreateBucketResponse createBucketResponse = new CreateBucketResponse();
        createBucketResponse.setName(createBucketRequest.getBucketName());
        createBucketResponse.setLocation(bosResponse.getMetadata().getLocation());
        return createBucketResponse;
    }

    public GetBucketLocationResponse getBucketLocation(String str) {
        return getBucketLocation(new GetBucketLocationRequest(str));
    }

    public GetBucketLocationResponse getBucketLocation(GetBucketLocationRequest getBucketLocationRequest) {
        CheckUtils.isNotNull(getBucketLocationRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(getBucketLocationRequest, HttpMethodName.GET);
        internalRequestCreateRequest.addParameter("location", null);
        return (GetBucketLocationResponse) invokeHttpClient(internalRequestCreateRequest, GetBucketLocationResponse.class);
    }

    public boolean doesBucketExist(String str) {
        return doesBucketExist(new DoesBucketExistRequest(str));
    }

    public boolean doesBucketExist(DoesBucketExistRequest doesBucketExistRequest) {
        CheckUtils.isNotNull(doesBucketExistRequest, "request should not be null.");
        try {
            invokeHttpClient(createRequest(doesBucketExistRequest, HttpMethodName.HEAD), BosResponse.class);
            return true;
        } catch (BceServiceException e) {
            if (e.getStatusCode() == 403) {
                return true;
            }
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    public GetBucketAclResponse getBucketAcl(String str) {
        return getBucketAcl(new GetBucketAclRequest(str));
    }

    public GetBucketAclResponse getBucketAcl(GetBucketAclRequest getBucketAclRequest) {
        CheckUtils.isNotNull(getBucketAclRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(getBucketAclRequest, HttpMethodName.GET);
        internalRequestCreateRequest.addParameter("acl", null);
        GetBucketAclResponse getBucketAclResponse = (GetBucketAclResponse) invokeHttpClient(internalRequestCreateRequest, GetBucketAclResponse.class);
        if (getBucketAclResponse.getVersion() <= 1) {
            return getBucketAclResponse;
        }
        throw new BceClientException("Unsupported acl version.");
    }

    public void setBucketAcl(String str, CannedAccessControlList cannedAccessControlList) throws JSONException {
        setBucketAcl(new SetBucketAclRequest(str, cannedAccessControlList));
    }

    public void setBucketAcl(SetBucketAclRequest setBucketAclRequest) throws JSONException {
        CheckUtils.isNotNull(setBucketAclRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(setBucketAclRequest, HttpMethodName.PUT);
        internalRequestCreateRequest.addParameter("acl", null);
        if (setBucketAclRequest.getCannedAcl() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_ACL, setBucketAclRequest.getCannedAcl().toString());
            setZeroContentLength(internalRequestCreateRequest);
        } else if (setBucketAclRequest.getAccessControlList() != null) {
            try {
                byte[] bytes = JsonUtils.setAclJson(setBucketAclRequest.getAccessControlList()).getBytes("UTF-8");
                internalRequestCreateRequest.addHeader("Content-Length", String.valueOf(bytes.length));
                internalRequestCreateRequest.addHeader("Content-Type", RequestParams.APPLICATION_JSON);
                internalRequestCreateRequest.setContent(RestartableInputStream.wrap(bytes));
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes:" + e.getMessage(), e);
            }
        } else {
            CheckUtils.isNotNull(null, "request.acl should not be null.");
        }
        invokeHttpClient(internalRequestCreateRequest, BosResponse.class);
    }

    public void deleteBucket(String str) {
        deleteBucket(new DeleteBucketRequest(str));
    }

    public void deleteBucket(DeleteBucketRequest deleteBucketRequest) {
        CheckUtils.isNotNull(deleteBucketRequest, "request should not be null.");
        invokeHttpClient(createRequest(deleteBucketRequest, HttpMethodName.DELETE), BosResponse.class);
    }

    public URL generatePresignedUrl(String str, String str2, int i) {
        return generatePresignedUrl(str, str2, i, HttpMethodName.GET);
    }

    public URL generatePresignedUrl(String str, String str2, int i, HttpMethodName httpMethodName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(str, str2, httpMethodName);
        generatePresignedUrlRequest.setExpiration(i);
        return generatePresignedUrl(generatePresignedUrlRequest);
    }

    public URL generatePresignedUrl(GeneratePresignedUrlRequest generatePresignedUrlRequest) {
        CheckUtils.isNotNull(generatePresignedUrlRequest, "The request parameter must be specified when generating a pre-signed URL");
        HttpMethodName httpMethodNameValueOf = HttpMethodName.valueOf(generatePresignedUrlRequest.getMethod().toString());
        Boolean boolIsCnameEnabled = ((BosClientConfiguration) this.config).isCnameEnabled();
        InternalRequest internalRequest = new InternalRequest(httpMethodNameValueOf, HttpUtils.appendUri(getEndpoint(), AbstractBceClient.URL_PREFIX, (boolIsCnameEnabled == Boolean.FALSE || (boolIsCnameEnabled == null && !BosUtils.isCnameLikeHost(getEndpoint().getHost()))) ? generatePresignedUrlRequest.getBucketName() : null, generatePresignedUrlRequest.getKey()));
        internalRequest.setCredentials(generatePresignedUrlRequest.getRequestCredentials());
        SignOptions signOptions = new SignOptions();
        signOptions.setUseStsHeader(false);
        signOptions.setExpirationInSeconds(generatePresignedUrlRequest.getExpiration());
        for (Map.Entry<String, String> entry : generatePresignedUrlRequest.getRequestHeaders().entrySet()) {
            if (entry.getValue() == null) {
                internalRequest.addHeader(entry.getKey(), "");
            } else {
                internalRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry2 : generatePresignedUrlRequest.getRequestParameters().entrySet()) {
            if (entry2.getValue() == null) {
                internalRequest.addParameter(entry2.getKey(), "");
            } else {
                internalRequest.addParameter(entry2.getKey(), entry2.getValue());
            }
        }
        if (generatePresignedUrlRequest.getContentType() != null) {
            internalRequest.addHeader("Content-Type", generatePresignedUrlRequest.getContentType());
        }
        if (generatePresignedUrlRequest.getContentMd5() != null) {
            internalRequest.addHeader("Content-MD5", generatePresignedUrlRequest.getContentMd5());
        }
        addResponseHeaderParameters(internalRequest, generatePresignedUrlRequest.getResponseHeaders());
        new BceV1Signer().sign(internalRequest, this.config.getCredentials(), signOptions);
        return convertRequestToUrl(internalRequest);
    }

    public ListObjectsResponse listObjects(String str) {
        return listObjects(new ListObjectsRequest(str));
    }

    public ListObjectsResponse listObjects(String str, String str2) {
        return listObjects(new ListObjectsRequest(str, str2));
    }

    public ListObjectsResponse listObjects(ListObjectsRequest listObjectsRequest) {
        CheckUtils.isNotNull(listObjectsRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(listObjectsRequest, HttpMethodName.GET);
        if (listObjectsRequest.getPrefix() != null) {
            internalRequestCreateRequest.addParameter("prefix", listObjectsRequest.getPrefix());
        }
        if (listObjectsRequest.getMarker() != null) {
            internalRequestCreateRequest.addParameter("marker", listObjectsRequest.getMarker());
        }
        if (listObjectsRequest.getDelimiter() != null) {
            internalRequestCreateRequest.addParameter(TtmlNode.RUBY_DELIMITER, listObjectsRequest.getDelimiter());
        }
        if (listObjectsRequest.getMaxKeys() >= 0) {
            internalRequestCreateRequest.addParameter("maxKeys", String.valueOf(listObjectsRequest.getMaxKeys()));
        }
        ListObjectsResponse listObjectsResponse = (ListObjectsResponse) invokeHttpClient(internalRequestCreateRequest, ListObjectsResponse.class);
        listObjectsResponse.setBucketName(listObjectsRequest.getBucketName());
        Iterator<BosObjectSummary> it = listObjectsResponse.getContents().iterator();
        while (it.hasNext()) {
            it.next().setBucketName(listObjectsRequest.getBucketName());
        }
        return listObjectsResponse;
    }

    public ListObjectsResponse listNextBatchOfObjects(ListObjectsResponse listObjectsResponse) {
        CheckUtils.isNotNull(listObjectsResponse, "previousResponse should not be null.");
        if (!listObjectsResponse.isTruncated()) {
            ListObjectsResponse listObjectsResponse2 = new ListObjectsResponse();
            listObjectsResponse2.setBucketName(listObjectsResponse.getBucketName());
            listObjectsResponse2.setDelimiter(listObjectsResponse.getDelimiter());
            listObjectsResponse2.setMarker(listObjectsResponse.getNextMarker());
            listObjectsResponse2.setMaxKeys(listObjectsResponse.getMaxKeys());
            listObjectsResponse2.setPrefix(listObjectsResponse.getPrefix());
            listObjectsResponse2.setTruncated(false);
            return listObjectsResponse2;
        }
        return listObjects(new ListObjectsRequest(listObjectsResponse.getBucketName()).withPrefix(listObjectsResponse.getPrefix()).withMarker(listObjectsResponse.getNextMarker()).withDelimiter(listObjectsResponse.getDelimiter()).withMaxKeys(listObjectsResponse.getMaxKeys()));
    }

    public BosObject getObject(String str, String str2) {
        return getObject(new GetObjectRequest(str, str2));
    }

    public ObjectMetadata getObject(String str, String str2, File file) {
        return getObject(new GetObjectRequest(str, str2), file);
    }

    public BosObject getObject(GetObjectRequest getObjectRequest) {
        CheckUtils.isNotNull(getObjectRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(getObjectRequest, HttpMethodName.GET);
        long[] range = getObjectRequest.getRange();
        if (range != null) {
            internalRequestCreateRequest.addHeader("Range", "bytes=" + range[0] + "-" + range[1]);
        }
        if (getObjectRequest.getTrafficLimitBitPS() > 0) {
            internalRequestCreateRequest.addHeader(Headers.BOS_TRAFFIC_LIMIT, String.valueOf(getObjectRequest.getTrafficLimitBitPS()));
        }
        BosObject object = ((GetObjectResponse) invokeHttpClient(internalRequestCreateRequest, GetObjectResponse.class)).getObject();
        object.setBucketName(getObjectRequest.getBucketName());
        object.setKey(getObjectRequest.getKey());
        return object;
    }

    public ObjectMetadata getObject(GetObjectRequest getObjectRequest, File file) throws Throwable {
        CheckUtils.isNotNull(getObjectRequest, "request should not be null.");
        CheckUtils.isNotNull(file, "destinationFile should not be null.");
        BosObject object = getObject(getObjectRequest);
        downloadObjectToFile(object, file, getObjectRequest.getRange() == null);
        return object.getObjectMetadata();
    }

    public byte[] getObjectContent(String str, String str2) {
        return getObjectContent(new GetObjectRequest(str, str2));
    }

    public byte[] getObjectContent(GetObjectRequest getObjectRequest) {
        BosObjectInputStream objectContent = getObject(getObjectRequest).getObjectContent();
        try {
            try {
                return ConvertUtils.inputStreamToByte(objectContent);
            } finally {
                try {
                    objectContent.close();
                } catch (IOException unused) {
                }
            }
        } catch (IOException e) {
            try {
                objectContent.close();
            } catch (IOException unused2) {
            }
            throw new BceClientException("Fail read object content:" + e.getMessage(), e);
        }
    }

    public ObjectMetadata getObjectMetadata(String str, String str2) {
        return getObjectMetadata(new GetObjectMetadataRequest(str, str2));
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest) {
        CheckUtils.isNotNull(getObjectMetadataRequest, "request should not be null.");
        return ((GetObjectResponse) invokeHttpClient(createRequest(getObjectMetadataRequest, HttpMethodName.HEAD), GetObjectResponse.class)).getObject().getObjectMetadata();
    }

    public PutObjectResponse putObject(String str, String str2, File file) {
        return putObject(new PutObjectRequest(str, str2, file));
    }

    public PutObjectResponse putObject(String str, String str2, File file, ObjectMetadata objectMetadata) {
        return putObject(new PutObjectRequest(str, str2, file, objectMetadata));
    }

    public PutObjectResponse putObject(String str, String str2, String str3) {
        try {
            return putObject(str, str2, str3.getBytes("UTF-8"), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes:" + e.getMessage(), e);
        }
    }

    public PutObjectResponse putObject(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        try {
            return putObject(str, str2, str3.getBytes("UTF-8"), objectMetadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes:" + e.getMessage(), e);
        }
    }

    public PutObjectResponse putObject(String str, String str2, byte[] bArr) {
        return putObject(str, str2, bArr, new ObjectMetadata());
    }

    public AppendObjectResponse appendObject(String str, String str2, File file) {
        return appendObject(new AppendObjectRequest(str, str2, file));
    }

    public AppendObjectResponse appendObject(String str, String str2, File file, ObjectMetadata objectMetadata) {
        return appendObject(new AppendObjectRequest(str, str2, file, objectMetadata));
    }

    public AppendObjectResponse appendObject(String str, String str2, String str3) {
        try {
            return appendObject(str, str2, str3.getBytes("UTF-8"), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    public AppendObjectResponse appendObject(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        try {
            return appendObject(str, str2, str3.getBytes("UTF-8"), objectMetadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    public AppendObjectResponse appendObject(String str, String str2, byte[] bArr) {
        return appendObject(str, str2, bArr, new ObjectMetadata());
    }

    public AppendObjectResponse appendObject(String str, String str2, byte[] bArr, ObjectMetadata objectMetadata) {
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
        if (objectMetadata.getContentLength() == -1) {
            objectMetadata.setContentLength(bArr.length);
        }
        return appendObject(new AppendObjectRequest(str, str2, RestartableInputStream.wrap(bArr), objectMetadata));
    }

    public AppendObjectResponse appendObject(String str, String str2, InputStream inputStream) {
        return appendObject(new AppendObjectRequest(str, str2, inputStream));
    }

    public AppendObjectResponse appendObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        return appendObject(new AppendObjectRequest(str, str2, inputStream, objectMetadata));
    }

    public AppendObjectResponse appendObject(AppendObjectRequest appendObjectRequest) throws Throwable {
        CheckUtils.isNotNull(appendObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(appendObjectRequest.getKey(), "object key should not be null or empty");
        InternalRequest internalRequestCreateRequest = createRequest(appendObjectRequest, HttpMethodName.POST);
        internalRequestCreateRequest.addParameter("append", null);
        if (appendObjectRequest.getOffset() != null) {
            internalRequestCreateRequest.addParameter("offset", appendObjectRequest.getOffset().toString());
        }
        BosResponse bosResponseUploadObject = uploadObject(appendObjectRequest, internalRequestCreateRequest);
        AppendObjectResponse appendObjectResponse = new AppendObjectResponse();
        appendObjectResponse.setNextAppendOffset(bosResponseUploadObject.getMetadata().getNextAppendOffset());
        appendObjectResponse.setContentMd5(bosResponseUploadObject.getMetadata().getContentMd5());
        appendObjectResponse.setETag(bosResponseUploadObject.getMetadata().getETag());
        appendObjectResponse.setCrc32(bosResponseUploadObject.getMetadata().getCrc32());
        appendObjectResponse.setNextAppendOffset(bosResponseUploadObject.getMetadata().getNextAppendOffset());
        return appendObjectResponse;
    }

    public PutObjectResponse putObject(String str, String str2, byte[] bArr, ObjectMetadata objectMetadata) {
        if (objectMetadata.getContentLength() == -1) {
            objectMetadata.setContentLength(bArr.length);
        }
        return putObject(new PutObjectRequest(str, str2, RestartableInputStream.wrap(bArr), objectMetadata));
    }

    public PutObjectResponse putObject(String str, String str2, InputStream inputStream) {
        return putObject(new PutObjectRequest(str, str2, inputStream));
    }

    public PutObjectResponse putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        return putObject(new PutObjectRequest(str, str2, inputStream, objectMetadata));
    }

    public PutObjectResponse putObject(PutObjectRequest putObjectRequest) throws Throwable {
        CheckUtils.isNotNull(putObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(putObjectRequest.getKey(), "object key should not be null or empty");
        BosResponse bosResponseUploadObject = uploadObject(putObjectRequest, createRequest(putObjectRequest, HttpMethodName.PUT));
        PutObjectResponse putObjectResponse = new PutObjectResponse();
        putObjectResponse.setServerCallbackReturnBody(bosResponseUploadObject.getServerCallbackReturnBody());
        putObjectResponse.setHttpResponse(bosResponseUploadObject.getHttpResponse());
        putObjectResponse.setETag(bosResponseUploadObject.getMetadata().getETag());
        putObjectResponse.setCrc32(bosResponseUploadObject.getMetadata().getCrc32());
        return putObjectResponse;
    }

    @Deprecated
    public PutObjectResponse putObject(PutObjectRequest putObjectRequest, BosProgressCallback bosProgressCallback) {
        putObjectRequest.setProgressCallback(bosProgressCallback);
        return putObject(putObjectRequest);
    }

    private BosResponse uploadObject(PutObjectRequest putObjectRequest, InternalRequest internalRequest) throws Throwable {
        FileInputStream fileInputStream;
        ObjectMetadata objectMetadata = putObjectRequest.getObjectMetadata();
        InputStream inputStream = putObjectRequest.getInputStream();
        if (putObjectRequest.getFile() != null) {
            File file = putObjectRequest.getFile();
            if (file.length() > 5368709120L) {
                BceServiceException bceServiceException = new BceServiceException("Your proposed upload exceeds the maximum allowed object size.");
                bceServiceException.setStatusCode(400);
                bceServiceException.setErrorCode("EntityTooLarge");
                bceServiceException.setErrorType(BceServiceException.ErrorType.Client);
                throw bceServiceException;
            }
            if (objectMetadata.getContentLength() < 0) {
                objectMetadata.setContentLength(file.length());
            }
            if (objectMetadata.getContentType() == null) {
                objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }
            if (objectMetadata.getContentLength() == file.length()) {
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e) {
                    e = e;
                }
                try {
                    objectMetadata.setBceContentSha256(new String(ConvertUtils.encodeHex(HashUtils.computeSha256Hash(fileInputStream))));
                    try {
                        fileInputStream.close();
                    } catch (Exception unused) {
                        BLog.error("The inputStream accured error");
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw new BceClientException("Unable to calculate SHA-256 hash", e);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception unused2) {
                            BLog.error("The inputStream accured error");
                        }
                    }
                    throw th;
                }
            }
            try {
                internalRequest.setContent(new RestartableFileInputStream(file));
            } catch (FileNotFoundException e3) {
                throw new BceClientException("Unable to find file to upload", e3);
            }
        } else {
            CheckUtils.isNotNull(inputStream, "Either file or inputStream should be set.");
            if (objectMetadata.getContentLength() < 0) {
                BLog.warn("No content length specified for stream data. Trying to read them all into memory.");
                internalRequest.setContent(new RestartableMultiByteArrayInputStream(readAll(inputStream, objectMetadata), objectMetadata.getContentLength()));
            } else if (inputStream instanceof RestartableInputStream) {
                internalRequest.setContent((RestartableInputStream) inputStream);
            } else {
                internalRequest.setContent(wrapRestartableInputStream(inputStream));
            }
            if (objectMetadata.getContentType() == null) {
                objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(putObjectRequest.getKey()));
            }
        }
        if (putObjectRequest.getStorageClass() != null) {
            objectMetadata.setStorageClass(putObjectRequest.getStorageClass());
        }
        if (putObjectRequest.getProcess() != null) {
            internalRequest.addHeader(Headers.BCE_PROCESS, putObjectRequest.getProcess());
        }
        if (putObjectRequest.getTrafficLimitBitPS() > 0) {
            internalRequest.addHeader(Headers.BOS_TRAFFIC_LIMIT, String.valueOf(putObjectRequest.getTrafficLimitBitPS()));
        }
        internalRequest.addHeader("Content-Length", String.valueOf(objectMetadata.getContentLength()));
        populateRequestMetadata(internalRequest, objectMetadata);
        try {
            return (BosResponse) invokeHttpClient(internalRequest, BosResponse.class, putObjectRequest.getProgressCallback());
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e4) {
                BLog.error("Fail to close input stream", (Throwable) e4);
            }
        }
    }

    public CopyObjectResponse copyObject(String str, String str2, String str3, String str4) {
        return copyObject(new CopyObjectRequest(str, str2, str3, str4));
    }

    public CopyObjectResponse copyObject(CopyObjectRequest copyObjectRequest) {
        CheckUtils.isNotNull(copyObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(copyObjectRequest.getSourceKey(), "object key should not be null or empty");
        InternalRequest internalRequestCreateRequest = createRequest(copyObjectRequest, HttpMethodName.PUT);
        internalRequestCreateRequest.addHeader(Headers.BCE_COPY_SOURCE, HttpUtils.normalizePath(BceConfig.BOS_DELIMITER + copyObjectRequest.getSourceBucketName() + BceConfig.BOS_DELIMITER + copyObjectRequest.getSourceKey()));
        if (copyObjectRequest.getETag() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_MATCH, "\"" + copyObjectRequest.getETag() + "\"");
        }
        if (copyObjectRequest.getNoneMatchETagConstraint() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_NONE_MATCH, "\"" + copyObjectRequest.getNoneMatchETagConstraint() + "\"");
        }
        if (copyObjectRequest.getUnmodifiedSinceConstraint() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_UNMODIFIED_SINCE, copyObjectRequest.getUnmodifiedSinceConstraint());
        }
        if (copyObjectRequest.getModifiedSinceConstraint() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_MODIFIED_SINCE, copyObjectRequest.getModifiedSinceConstraint());
        }
        if (copyObjectRequest.getStorageClass() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_STORAGE_CLASS, copyObjectRequest.getStorageClass());
        }
        if (copyObjectRequest.getTrafficLimitBitPS() > 0) {
            internalRequestCreateRequest.addHeader(Headers.BOS_TRAFFIC_LIMIT, String.valueOf(copyObjectRequest.getTrafficLimitBitPS()));
        }
        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "replace");
            populateRequestMetadata(internalRequestCreateRequest, newObjectMetadata);
        } else {
            internalRequestCreateRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "copy");
        }
        setZeroContentLength(internalRequestCreateRequest);
        CopyObjectResponseWithExceptionInfo copyObjectResponseWithExceptionInfo = (CopyObjectResponseWithExceptionInfo) invokeHttpClient(internalRequestCreateRequest, CopyObjectResponseWithExceptionInfo.class);
        if (copyObjectResponseWithExceptionInfo.getETag() != null || copyObjectResponseWithExceptionInfo.getLastModified() != null || copyObjectResponseWithExceptionInfo.getMessage() == null) {
            return copyObjectResponseWithExceptionInfo;
        }
        BceServiceException bceServiceException = new BceServiceException(copyObjectResponseWithExceptionInfo.getMessage());
        bceServiceException.setErrorCode(copyObjectResponseWithExceptionInfo.getCode());
        bceServiceException.setRequestId(copyObjectResponseWithExceptionInfo.getRequestId());
        if (bceServiceException.getErrorCode() == "InternalError") {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Service);
        } else {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Client);
        }
        bceServiceException.setStatusCode(500);
        throw bceServiceException;
    }

    public void deleteObject(String str, String str2) {
        deleteObject(new DeleteObjectRequest(str, str2));
    }

    public void deleteObject(DeleteObjectRequest deleteObjectRequest) {
        CheckUtils.isNotNull(deleteObjectRequest, "request should not be null.");
        assertStringNotNullOrEmpty(deleteObjectRequest.getKey(), "object key should not be null or empty");
        invokeHttpClient(createRequest(deleteObjectRequest, HttpMethodName.DELETE), BosResponse.class);
    }

    public InitiateMultipartUploadResponse initiateMultipartUpload(String str, String str2) {
        return initiateMultipartUpload(new InitiateMultipartUploadRequest(str, str2));
    }

    public InitiateMultipartUploadResponse initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        CheckUtils.isNotNull(initiateMultipartUploadRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(initiateMultipartUploadRequest, HttpMethodName.POST);
        internalRequestCreateRequest.addParameter("uploads", null);
        if (initiateMultipartUploadRequest.getStorageClass() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_STORAGE_CLASS, initiateMultipartUploadRequest.getStorageClass());
        }
        setZeroContentLength(internalRequestCreateRequest);
        if (initiateMultipartUploadRequest.getObjectMetadata() != null) {
            populateRequestMetadata(internalRequestCreateRequest, initiateMultipartUploadRequest.getObjectMetadata());
        }
        if (internalRequestCreateRequest.getHeaders().get("Content-Type") == null) {
            internalRequestCreateRequest.addHeader("Content-Type", Mimetypes.getInstance().getMimetype(initiateMultipartUploadRequest.getKey()));
        }
        return (InitiateMultipartUploadResponse) invokeHttpClient(internalRequestCreateRequest, InitiateMultipartUploadResponse.class);
    }

    @Deprecated
    public UploadPartResponse uploadPart(UploadPartRequest uploadPartRequest, BosProgressCallback bosProgressCallback) {
        uploadPartRequest.setProgressCallback(bosProgressCallback);
        return uploadPart(uploadPartRequest);
    }

    public UploadPartResponse uploadPart(UploadPartRequest uploadPartRequest) {
        MD5DigestCalculatingInputStream mD5DigestCalculatingInputStream;
        CheckUtils.isNotNull(uploadPartRequest, "request should not be null.");
        CheckUtils.isNotNull(Long.valueOf(uploadPartRequest.getPartSize()), "partSize should not be null");
        CheckUtils.isNotNull(Integer.valueOf(uploadPartRequest.getPartNumber()), "partNumber should not be null");
        if (uploadPartRequest.getPartSize() > 5368709120L) {
            throw new BceClientException("PartNumber " + uploadPartRequest.getPartNumber() + " : Part Size should not be more than 5GB.");
        }
        InternalRequest internalRequestCreateRequest = createRequest(uploadPartRequest, HttpMethodName.PUT);
        internalRequestCreateRequest.addParameter("uploadId", uploadPartRequest.getUploadId());
        internalRequestCreateRequest.addParameter("partNumber", String.valueOf(uploadPartRequest.getPartNumber()));
        internalRequestCreateRequest.addHeader("Content-Length", String.valueOf(uploadPartRequest.getPartSize()));
        InputStream inputStream = uploadPartRequest.getInputStream();
        if (uploadPartRequest.getMd5Digest() == null) {
            try {
                mD5DigestCalculatingInputStream = new MD5DigestCalculatingInputStream(inputStream);
                inputStream = mD5DigestCalculatingInputStream;
            } catch (NoSuchAlgorithmException e) {
                BLog.error("Unable to verify data integrity.", (Throwable) e);
                mD5DigestCalculatingInputStream = null;
            }
        } else {
            mD5DigestCalculatingInputStream = null;
        }
        if (uploadPartRequest.getCrc32() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_CRC32, String.valueOf(uploadPartRequest.getCrc32()));
        }
        if (uploadPartRequest.getTrafficLimitBitPS() > 0) {
            internalRequestCreateRequest.addHeader(Headers.BOS_TRAFFIC_LIMIT, String.valueOf(uploadPartRequest.getTrafficLimitBitPS()));
        }
        try {
            internalRequestCreateRequest.setContent(wrapRestartableInputStream(inputStream, Long.valueOf(uploadPartRequest.getPartSize())));
            BosResponse bosResponse = (BosResponse) invokeHttpClient(internalRequestCreateRequest, BosResponse.class, uploadPartRequest.getProgressCallback());
            if (mD5DigestCalculatingInputStream != null) {
                try {
                    if (!Arrays.equals(mD5DigestCalculatingInputStream.getMd5Digest(), ConvertUtils.decodeHex(bosResponse.getMetadata().getETag().toCharArray()))) {
                        throw new BceClientException("Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by Baidu BOS.  You may need to delete the data stored in Baidu BOS.");
                    }
                } catch (Exception e2) {
                    throw new BceClientException("Unable to verify integrity of data upload:" + e2.getMessage(), e2);
                }
            }
            UploadPartResponse uploadPartResponse = new UploadPartResponse();
            uploadPartResponse.setETag(bosResponse.getMetadata().getETag());
            uploadPartResponse.setCrc32(bosResponse.getMetadata().getCrc32());
            uploadPartResponse.setPartNumber(uploadPartRequest.getPartNumber());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused) {
                }
            }
            return uploadPartResponse;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public ListPartsResponse listParts(String str, String str2, String str3) {
        return listParts(new ListPartsRequest(str, str2, str3));
    }

    public ListPartsResponse listParts(ListPartsRequest listPartsRequest) {
        CheckUtils.isNotNull(listPartsRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(listPartsRequest, HttpMethodName.GET);
        internalRequestCreateRequest.addParameter("uploadId", listPartsRequest.getUploadId());
        int maxParts = listPartsRequest.getMaxParts();
        if (maxParts >= 0) {
            internalRequestCreateRequest.addParameter("maxParts", String.valueOf(maxParts));
        }
        internalRequestCreateRequest.addParameter("partNumberMarker", String.valueOf(listPartsRequest.getPartNumberMarker()));
        ListPartsResponse listPartsResponse = (ListPartsResponse) invokeHttpClient(internalRequestCreateRequest, ListPartsResponse.class);
        listPartsResponse.setBucketName(listPartsRequest.getBucketName());
        return listPartsResponse;
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(String str, String str2, String str3, List<PartETag> list) throws JSONException {
        return completeMultipartUpload(new CompleteMultipartUploadRequest(str, str2, str3, list));
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(String str, String str2, String str3, List<PartETag> list, ObjectMetadata objectMetadata) throws JSONException {
        return completeMultipartUpload(new CompleteMultipartUploadRequest(str, str2, str3, list, objectMetadata));
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws JSONException {
        CheckUtils.isNotNull(completeMultipartUploadRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(completeMultipartUploadRequest, HttpMethodName.POST);
        internalRequestCreateRequest.addParameter("uploadId", completeMultipartUploadRequest.getUploadId());
        ObjectMetadata objectMetadata = completeMultipartUploadRequest.getObjectMetadata();
        if (objectMetadata != null) {
            populateRequestMetadata(internalRequestCreateRequest, objectMetadata);
        }
        try {
            byte[] bytes = JsonUtils.setPartETag(completeMultipartUploadRequest.getPartETags()).getBytes("UTF-8");
            internalRequestCreateRequest.addHeader("Content-Length", String.valueOf(bytes.length));
            if (!internalRequestCreateRequest.getHeaders().containsKey("Content-Type")) {
                internalRequestCreateRequest.addHeader("Content-Type", RequestParams.APPLICATION_JSON);
            }
            if (completeMultipartUploadRequest.getProcess() != null) {
                internalRequestCreateRequest.addHeader(Headers.BCE_PROCESS, completeMultipartUploadRequest.getProcess());
            }
            internalRequestCreateRequest.setContent(RestartableInputStream.wrap(bytes));
            CompleteMultipartUploadResponse completeMultipartUploadResponse = (CompleteMultipartUploadResponse) invokeHttpClient(internalRequestCreateRequest, CompleteMultipartUploadResponse.class);
            completeMultipartUploadResponse.setBucketName(completeMultipartUploadRequest.getBucketName());
            completeMultipartUploadResponse.setCrc32(completeMultipartUploadResponse.getMetadata().getCrc32());
            return completeMultipartUploadResponse;
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes:" + e.getMessage(), e);
        }
    }

    public void abortMultipartUpload(String str, String str2, String str3) {
        abortMultipartUpload(new AbortMultipartUploadRequest(str, str2, str3));
    }

    public void abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        CheckUtils.isNotNull(abortMultipartUploadRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(abortMultipartUploadRequest, HttpMethodName.DELETE);
        internalRequestCreateRequest.addParameter("uploadId", abortMultipartUploadRequest.getUploadId());
        invokeHttpClient(internalRequestCreateRequest, BosResponse.class);
    }

    public ListMultipartUploadsResponse listMultipartUploads(String str) {
        return listMultipartUploads(new ListMultipartUploadsRequest(str));
    }

    public ListMultipartUploadsResponse listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) {
        CheckUtils.isNotNull(listMultipartUploadsRequest, "request should not be null.");
        InternalRequest internalRequestCreateRequest = createRequest(listMultipartUploadsRequest, HttpMethodName.GET);
        internalRequestCreateRequest.addParameter("uploads", null);
        String keyMarker = listMultipartUploadsRequest.getKeyMarker();
        if (keyMarker != null) {
            internalRequestCreateRequest.addParameter("keyMarker", keyMarker);
        }
        int maxUploads = listMultipartUploadsRequest.getMaxUploads();
        if (maxUploads >= 0) {
            internalRequestCreateRequest.addParameter("maxUploads", String.valueOf(maxUploads));
        }
        String delimiter = listMultipartUploadsRequest.getDelimiter();
        if (delimiter != null) {
            internalRequestCreateRequest.addParameter(TtmlNode.RUBY_DELIMITER, delimiter);
        }
        String prefix = listMultipartUploadsRequest.getPrefix();
        if (prefix != null) {
            internalRequestCreateRequest.addParameter("prefix", prefix);
        }
        ListMultipartUploadsResponse listMultipartUploadsResponse = (ListMultipartUploadsResponse) invokeHttpClient(internalRequestCreateRequest, ListMultipartUploadsResponse.class);
        listMultipartUploadsResponse.setBucketName(listMultipartUploadsRequest.getBucketName());
        return listMultipartUploadsResponse;
    }

    private static void populateRequestMetadata(InternalRequest internalRequest, ObjectMetadata objectMetadata) {
        if (objectMetadata.getContentType() != null) {
            internalRequest.addHeader("Content-Type", objectMetadata.getContentType());
        }
        if (objectMetadata.getContentMd5() != null) {
            internalRequest.addHeader("Content-MD5", objectMetadata.getContentMd5());
        }
        if (objectMetadata.getContentEncoding() != null) {
            internalRequest.addHeader("Content-Encoding", HttpUtils.normalize(objectMetadata.getContentEncoding()));
        }
        if (objectMetadata.getBceContentSha256() != null) {
            internalRequest.addHeader(Headers.BCE_CONTENT_SHA256, objectMetadata.getBceContentSha256());
        }
        if (objectMetadata.getContentDisposition() != null) {
            internalRequest.addHeader("Content-Disposition", HttpUtils.normalize(objectMetadata.getContentDisposition()));
        }
        if (objectMetadata.getETag() != null) {
            internalRequest.addHeader("ETag", objectMetadata.getETag());
        }
        if (objectMetadata.getExpires() != null) {
            internalRequest.addHeader("Expires", objectMetadata.getExpires());
        }
        if (objectMetadata.getCacheControl() != null) {
            internalRequest.addHeader("Cache-Control", objectMetadata.getCacheControl());
        }
        if (objectMetadata.getStorageClass() != null) {
            internalRequest.addHeader(Headers.BCE_STORAGE_CLASS, objectMetadata.getStorageClass());
        }
        if (objectMetadata.getCrc32() != null) {
            internalRequest.addHeader(Headers.BCE_CRC32, String.valueOf(objectMetadata.getCrc32()));
        }
        if (objectMetadata.getxBceAcl() != null) {
            internalRequest.addHeader(Headers.BCE_ACL, String.valueOf(objectMetadata.getxBceAcl()));
        }
        Map<String, String> userMetadata = objectMetadata.getUserMetadata();
        if (userMetadata != null) {
            for (Map.Entry<String, String> entry : userMetadata.entrySet()) {
                String key = entry.getKey();
                if (key != null) {
                    String value = entry.getValue();
                    if (value == null) {
                        value = "";
                    }
                    if (key.length() + value.length() > 32768) {
                        throw new BceClientException("MetadataTooLarge");
                    }
                    internalRequest.addHeader(Headers.BCE_USER_METADATA_PREFIX + HttpUtils.normalize(key.trim()), HttpUtils.normalize(value));
                }
            }
        }
    }

    private <T extends AbstractBceRequest> URI getUri(T t, URI uri) {
        String bucketName;
        Boolean boolIsCnameEnabled;
        if (uri == null) {
            return null;
        }
        if (!(t instanceof GenericBucketRequest) || ((boolIsCnameEnabled = ((BosClientConfiguration) this.config).isCnameEnabled()) != Boolean.FALSE && (boolIsCnameEnabled != null || BosUtils.isCnameLikeHost(uri.getHost())))) {
            bucketName = null;
        } else {
            bucketName = ((GenericBucketRequest) t).getBucketName();
            if (getBktVirEndpoint(bucketName) == null) {
                computeBktVirEndpoint(bucketName);
            }
            if (getBktVirEndpoint(bucketName) != null && !((BosClientConfiguration) this.config).isPathStyleAccessEnable() && uri.equals(getEndpoint())) {
                uri = getBktVirEndpoint(bucketName);
                bucketName = null;
            }
        }
        return HttpUtils.appendUri(uri, AbstractBceClient.URL_PREFIX, bucketName, t instanceof GenericObjectRequest ? ((GenericObjectRequest) t).getKey() : null);
    }

    private <T extends AbstractBceRequest> InternalRequest createRequest(T t, HttpMethodName httpMethodName) {
        URI endpoint = getEndpoint();
        URI uri = null;
        if ((this.config instanceof BosClientConfiguration) && ((BosClientConfiguration) this.config).getBackupEndpoint() != null) {
            try {
                uri = new URI(((BosClientConfiguration) this.config).getBackupEndpoint());
            } catch (URISyntaxException unused) {
            }
        }
        InternalRequest internalRequest = new InternalRequest(httpMethodName, getUri(t, endpoint), getUri(t, uri));
        internalRequest.setCredentials(t.getRequestCredentials());
        internalRequest.setRequest(t);
        return internalRequest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v7, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v4, types: [com.baidubce.services.bos.model.ObjectMetadata] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void downloadObjectToFile(com.baidubce.services.bos.model.BosObject r9, java.io.File r10, boolean r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.services.bos.BosClient.downloadObjectToFile(com.baidubce.services.bos.model.BosObject, java.io.File, boolean):void");
    }

    private List<byte[]> readAll(InputStream inputStream, ObjectMetadata objectMetadata) {
        ArrayList arrayList = new ArrayList();
        int streamBufferSize = getStreamBufferSize();
        long j = 0;
        while (true) {
            byte[] bArr = new byte[streamBufferSize];
            arrayList.add(bArr);
            int i = 0;
            while (i < streamBufferSize) {
                try {
                    int i2 = inputStream.read(bArr, i, streamBufferSize - i);
                    if (i2 < 0) {
                        objectMetadata.setContentLength(j);
                        return arrayList;
                    }
                    j += (long) i2;
                    i += i2;
                } catch (IOException e) {
                    throw new BceClientException("Fail to read data:" + e.getMessage(), e);
                }
            }
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream inputStream) {
        if (inputStream.markSupported()) {
            return new RestartableResettableInputStream(inputStream);
        }
        return new RestartableNonResettableInputStream(inputStream, getStreamBufferSize());
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream inputStream, Long l) {
        if (inputStream.markSupported()) {
            return new RestartableResettableInputStream(inputStream);
        }
        return new RestartableNonResettableInputStream(inputStream, l.longValue() > ((long) getStreamBufferSize()) ? getStreamBufferSize() : l.intValue());
    }

    private void setZeroContentLength(InternalRequest internalRequest) {
        internalRequest.addHeader("Content-Length", String.valueOf(0));
    }

    private int getStreamBufferSize() {
        return ((BosClientConfiguration) this.config).getStreamBufferSize();
    }

    private void assertStringNotNullOrEmpty(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException(str2);
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException(str2);
        }
    }

    private void addResponseHeaderParameters(InternalRequest internalRequest, ResponseHeaderOverrides responseHeaderOverrides) {
        if (responseHeaderOverrides != null) {
            if (responseHeaderOverrides.getCacheControl() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL, responseHeaderOverrides.getCacheControl());
            }
            if (responseHeaderOverrides.getContentDisposition() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION, responseHeaderOverrides.getContentDisposition());
            }
            if (responseHeaderOverrides.getContentEncoding() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING, responseHeaderOverrides.getContentEncoding());
            }
            if (responseHeaderOverrides.getContentLanguage() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE, responseHeaderOverrides.getContentLanguage());
            }
            if (responseHeaderOverrides.getContentType() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE, responseHeaderOverrides.getContentType());
            }
            if (responseHeaderOverrides.getExpires() != null) {
                internalRequest.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES, responseHeaderOverrides.getExpires());
            }
        }
    }

    private URL convertRequestToUrl(InternalRequest<AbstractBceRequest> internalRequest) {
        String str;
        String str2;
        String strNormalizePath = HttpUtils.normalizePath(internalRequest.getUri().getPath());
        boolean z = true;
        if (strNormalizePath.startsWith(BceConfig.BOS_DELIMITER)) {
            strNormalizePath = strNormalizePath.substring(1);
        }
        String str3 = getEndpoint() + (BceConfig.BOS_DELIMITER + strNormalizePath).replaceAll("(?<=/)/", "%2F");
        for (String str4 : internalRequest.getParameters().keySet()) {
            if (z) {
                str2 = str3 + "?";
                z = false;
            } else {
                str2 = str3 + "&";
            }
            str3 = str2 + str4 + "=" + HttpUtils.normalize(internalRequest.getParameters().get(str4));
        }
        String str5 = internalRequest.getHeaders().get("Authorization");
        if (str5 != null) {
            if (z) {
                str = str3 + "?";
            } else {
                str = str3 + "&";
            }
            str3 = str + "authorization=" + HttpUtils.normalize(str5);
        }
        try {
            return new URL(str3);
        } catch (MalformedURLException e) {
            throw new BceClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, long j, int i) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, j, i));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, long j) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, j));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(File file, String str, String str2, int i) {
        return putSuperObjectFromFile(new PutSuperObjectRequest(str, str2, file, i));
    }

    public PutSuperObjectResponse putSuperObjectFromFile(final PutSuperObjectRequest putSuperObjectRequest) throws Throwable {
        boolean z;
        PutSuperObjectResponse putSuperObjectResponse = new PutSuperObjectResponse();
        File file = putSuperObjectRequest.getFile();
        long partSize = putSuperObjectRequest.getPartSize();
        if (partSize <= 0) {
            throw new BceClientException("the partsize must be greater than 0");
        }
        String bucketName = putSuperObjectRequest.getBucketName();
        String key = putSuperObjectRequest.getKey();
        putSuperObjectResponse.setBucketName(bucketName);
        putSuperObjectResponse.setKey(key);
        int i = putSuperObjectRequest.getnThreads();
        AtomicBoolean isSuperObjectUploadCancelled = putSuperObjectRequest.getIsSuperObjectUploadCancelled();
        long length = file.length();
        int i2 = (int) (length / partSize);
        if (length % partSize > 0) {
            i2++;
        }
        if (i2 > BceClientConfiguration.MAX_PARTS) {
            throw new BceClientException("Total parts count should not exceed 10000");
        }
        if (length <= this.config.getMultipartBlockSize()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            if (putSuperObjectRequest.getProgressCallback() != null) {
                putObjectRequest.setProgressCallback(new BosProgressCallback<PutObjectRequest>() { // from class: com.baidubce.services.bos.BosClient.1
                    @Override // com.baidubce.services.bos.callback.BosProgressCallback, com.baidubce.callback.BceProgressCallback
                    public void onProgress(PutObjectRequest putObjectRequest2, long j, long j2) {
                        if (putSuperObjectRequest.getIsSuperObjectUploadCancelled().get()) {
                            putObjectRequest2.cancel();
                            putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, 0L, j2);
                        }
                        putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, j, j2);
                    }
                });
            }
            PutObjectResponse putObjectResponsePutObject = putObject(putObjectRequest);
            putSuperObjectResponse.setIsUploadPart(false);
            putSuperObjectResponse.setServerCallbackReturnBody(putObjectResponsePutObject.getServerCallbackReturnBody());
            putSuperObjectResponse.setCrc32(putObjectResponsePutObject.getCrc32());
            putSuperObjectResponse.setETag(putObjectResponsePutObject.getETag());
            putSuperObjectResponse.setHttpResponse(putObjectResponsePutObject.getHttpResponse());
            return putSuperObjectResponse;
        }
        putSuperObjectRequest.initSuperFileTask(length, i2);
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, key);
        ObjectMetadata objectMetadata = putSuperObjectRequest.getObjectMetadata();
        if (objectMetadata != null) {
            initiateMultipartUploadRequest.setObjectMetadata(objectMetadata);
        }
        String uploadId = initiateMultipartUpload(initiateMultipartUploadRequest).getUploadId();
        putSuperObjectRequest.setUploadId(uploadId);
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(i);
        ArrayList arrayList = new ArrayList();
        List listSynchronizedList = Collections.synchronizedList(new ArrayList());
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(executorServiceNewFixedThreadPool.submit(new UploadPartTask(this, putSuperObjectRequest, i3, listSynchronizedList)));
        }
        int i4 = 0;
        while (i4 < arrayList.size()) {
            try {
                ArrayList arrayList2 = arrayList;
                if (((Boolean) ((Future) arrayList.get(i4)).get()).booleanValue()) {
                    BLog.info("The upload task [ " + i4 + "] completed.");
                    i4++;
                    arrayList = arrayList2;
                } else {
                    BLog.error("The upload task [ " + i4 + "] failed.");
                }
            } catch (InterruptedException | ExecutionException unused) {
            }
            z = false;
        }
        z = true;
        executorServiceNewFixedThreadPool.shutdown();
        try {
            if (!executorServiceNewFixedThreadPool.awaitTermination(500L, TimeUnit.MILLISECONDS)) {
                executorServiceNewFixedThreadPool.shutdownNow();
            }
            if ((isSuperObjectUploadCancelled.get() || listSynchronizedList.size() != i2) ? false : z) {
                Collections.sort(listSynchronizedList, new Comparator<PartETag>() { // from class: com.baidubce.services.bos.BosClient.2
                    @Override // java.util.Comparator
                    public int compare(PartETag partETag, PartETag partETag2) {
                        return partETag.getPartNumber() - partETag2.getPartNumber();
                    }
                });
                try {
                    CompleteMultipartUploadResponse completeMultipartUploadResponseCompleteMultipartUpload = completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, key, uploadId, listSynchronizedList));
                    putSuperObjectResponse.setHttpResponse(completeMultipartUploadResponseCompleteMultipartUpload.getHttpResponse());
                    putSuperObjectResponse.setETag(completeMultipartUploadResponseCompleteMultipartUpload.getETag());
                    putSuperObjectResponse.setCrc32(completeMultipartUploadResponseCompleteMultipartUpload.getCrc32());
                    putSuperObjectResponse.setServerCallbackReturnBody(completeMultipartUploadResponseCompleteMultipartUpload.getServerCallbackReturnBody());
                    putSuperObjectResponse.setLocation(completeMultipartUploadResponseCompleteMultipartUpload.getLocation());
                    if (putSuperObjectRequest.getProgressCallback() != null && putSuperObjectRequest.getCurrentSize() != length) {
                        putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, length, length);
                    }
                    BLog.info("Success to upload file: " + file.getAbsolutePath() + " to BOS with ETag: " + completeMultipartUploadResponseCompleteMultipartUpload.getETag());
                } catch (JSONException unused2) {
                    BLog.error("Failed to completeMultipartUpload: [upload] = " + uploadId);
                }
            } else {
                abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, key, uploadId));
                putSuperObjectRequest.setCurrentSize(0L);
                if (putSuperObjectRequest.getProgressCallback() != null) {
                    putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, 0L, length);
                }
                BLog.info("Failed to upload file: " + file.getAbsolutePath());
            }
            return putSuperObjectResponse;
        } catch (InterruptedException e) {
            throw new BceClientException("close thread pool fail exception", e);
        }
    }

    public boolean uploadFilePart(final PutSuperObjectRequest putSuperObjectRequest, int i, List<PartETag> list) throws Throwable {
        long j;
        FileInputStream fileInputStream;
        long j2;
        UploadPartRequest uploadPartRequest;
        BosClient bosClient = this;
        int uploadRetry = bosClient.config.getUploadRetry();
        File file = putSuperObjectRequest.getFile();
        long partSize = putSuperObjectRequest.getPartSize();
        String bucketName = putSuperObjectRequest.getBucketName();
        String key = putSuperObjectRequest.getKey();
        String uploadId = putSuperObjectRequest.getUploadId();
        AtomicBoolean isSuperObjectUploadCancelled = putSuperObjectRequest.getIsSuperObjectUploadCancelled();
        while (true) {
            if (uploadRetry <= 0 || isSuperObjectUploadCancelled.get()) {
                break;
            }
            int i2 = i + 1;
            long currentSize = putSuperObjectRequest.getCurrentSize(i2);
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                    j = currentSize;
                    j2 = ((long) i) * partSize;
                } catch (IOException unused) {
                    j = currentSize;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    fileInputStream.skip(j2);
                    long jMin = Math.min(partSize, file.length() - j2);
                    uploadPartRequest = new UploadPartRequest();
                    uploadPartRequest.setBucketName(bucketName);
                    uploadPartRequest.setKey(key);
                    uploadPartRequest.setUploadId(uploadId);
                    uploadPartRequest.setInputStream(fileInputStream);
                    uploadPartRequest.setPartSize(jMin);
                    uploadPartRequest.setPartNumber(i2);
                    if (putSuperObjectRequest.getTrafficLimitBitPS() > 0) {
                        uploadPartRequest.setTrafficLimitBitPS(putSuperObjectRequest.getTrafficLimitBitPS());
                    }
                    if (putSuperObjectRequest.getProgressCallback() != null) {
                        uploadPartRequest.setProgressCallback(new BosProgressCallback<UploadPartRequest>() { // from class: com.baidubce.services.bos.BosClient.3
                            @Override // com.baidubce.services.bos.callback.BosProgressCallback, com.baidubce.callback.BceProgressCallback
                            public void onProgress(UploadPartRequest uploadPartRequest2, long j3, long j4) {
                                if (putSuperObjectRequest.getIsSuperObjectUploadCancelled().get()) {
                                    uploadPartRequest2.cancel();
                                    return;
                                }
                                long updateCurrentSize = putSuperObjectRequest.getUpdateCurrentSize(uploadPartRequest2.getPartNumber(), j3);
                                synchronized (putSuperObjectRequest.getProgressCallback()) {
                                    BosProgressCallback progressCallback = putSuperObjectRequest.getProgressCallback();
                                    PutSuperObjectRequest putSuperObjectRequest2 = putSuperObjectRequest;
                                    progressCallback.onProgress(putSuperObjectRequest2, updateCurrentSize, putSuperObjectRequest2.getTotalSize());
                                }
                            }
                        });
                    }
                } catch (IOException unused2) {
                }
                if (!isSuperObjectUploadCancelled.get()) {
                    UploadPartResponse uploadPartResponseUploadPart = bosClient.uploadPart(uploadPartRequest);
                    try {
                        list.add(uploadPartResponseUploadPart.getPartETag());
                        BLog.info("Complete upload with ETag: " + uploadPartResponseUploadPart.getPartETag());
                        try {
                            fileInputStream.close();
                        } catch (Exception unused3) {
                        }
                    } catch (IOException unused4) {
                        fileInputStream2 = fileInputStream;
                        BLog.error("Failed to upload the part " + i + " [tryCount] = " + uploadRetry);
                        int i3 = uploadRetry - 1;
                        if (putSuperObjectRequest.getProgressCallback() != null) {
                            putSuperObjectRequest.addCurrentSize(0 - (putSuperObjectRequest.getCurrentSize(i2) - j));
                            putSuperObjectRequest.setCurrentSize(i2, j);
                            synchronized (putSuperObjectRequest.getProgressCallback()) {
                                putSuperObjectRequest.getProgressCallback().onProgress(putSuperObjectRequest, putSuperObjectRequest.getCurrentSize(), putSuperObjectRequest.getTotalSize());
                            }
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception unused5) {
                            }
                        }
                        uploadRetry = i3;
                    }
                    if (uploadRetry > 0) {
                        break;
                    }
                    bosClient = this;
                } else {
                    try {
                        fileInputStream.close();
                        break;
                    } catch (Exception unused6) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception unused7) {
                    }
                }
                throw th;
            }
        }
        if (isSuperObjectUploadCancelled.get()) {
            BLog.info("Request is canceled");
        } else if (uploadRetry == 0) {
            BLog.error("Failed to upload the part " + i);
        } else {
            BLog.info("Success to upload the part " + i);
        }
        return uploadRetry > 0 && !isSuperObjectUploadCancelled.get();
    }

    private static class UploadPartTask implements Callable<Boolean> {
        BosClient client;
        List<PartETag> partETags;
        int partNum;
        PutSuperObjectRequest putSuperObjectRequest;

        UploadPartTask(BosClient bosClient, PutSuperObjectRequest putSuperObjectRequest, int i, List<PartETag> list) {
            this.client = bosClient;
            this.putSuperObjectRequest = putSuperObjectRequest;
            this.partNum = i;
            this.partETags = list;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            return Boolean.valueOf(this.client.uploadFilePart(this.putSuperObjectRequest, this.partNum, this.partETags));
        }
    }

    public void setObjectAcl(String str, String str2, String str3) {
        setObjectAcl(new SetObjectAclRequest(str, str2, str3));
    }

    public void setObjectAcl(String str, String str2, CannedAccessControlList cannedAccessControlList) {
        setObjectAcl(new SetObjectAclRequest(str, str2, cannedAccessControlList));
    }

    public void setObjectAcl(SetObjectAclRequest setObjectAclRequest) {
        InternalRequest internalRequestCreateRequest = createRequest(setObjectAclRequest, HttpMethodName.PUT);
        internalRequestCreateRequest.addParameter("acl", null);
        if (setObjectAclRequest.getCannedAcl() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_ACL, setObjectAclRequest.getCannedAcl().toString());
            setZeroContentLength(internalRequestCreateRequest);
        } else if (setObjectAclRequest.getAccessControlList() != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                JSONArray jSONArray = new JSONArray();
                for (Grant grant : setObjectAclRequest.getAccessControlList()) {
                    JSONObject jSONObject2 = new JSONObject();
                    JSONArray jSONArray2 = new JSONArray();
                    JSONArray jSONArray3 = new JSONArray();
                    for (Grantee grantee : grant.getGrantee()) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("id", grantee.getId());
                        jSONArray2.put(jSONObject3);
                    }
                    Iterator<Permission> it = grant.getPermission().iterator();
                    while (it.hasNext()) {
                        jSONArray3.put(it.next());
                    }
                    jSONObject2.put("grantee", jSONArray2);
                    jSONObject2.put("permission", jSONArray3);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("accessControlList", jSONArray);
                String string = jSONObject.toString();
                internalRequestCreateRequest.addHeader("Content-Length", String.valueOf(string.length()));
                internalRequestCreateRequest.addHeader("Content-Type", RequestParams.APPLICATION_JSON);
                internalRequestCreateRequest.setContent(RestartableInputStream.wrap(string.getBytes()));
            } catch (JSONException e) {
                throw new BceClientException("Fail to set object acl request", e);
            }
        } else if (setObjectAclRequest.getJsonObjectAcl() != null) {
            try {
                byte[] bytes = setObjectAclRequest.getJsonObjectAcl().getBytes("UTF-8");
                internalRequestCreateRequest.addHeader("Content-Length", String.valueOf(bytes.length));
                internalRequestCreateRequest.addHeader("Content-Type", RequestParams.APPLICATION_JSON);
                internalRequestCreateRequest.setContent(RestartableInputStream.wrap(bytes));
            } catch (UnsupportedEncodingException e2) {
                throw new BceClientException("Fail to get UTF-8 bytes", e2);
            }
        } else if (setObjectAclRequest.getxBceGrantRead() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_ACL_GRANT_READ, setObjectAclRequest.getxBceGrantRead());
            setZeroContentLength(internalRequestCreateRequest);
        } else if (setObjectAclRequest.getxBceGrantFullControl() != null) {
            internalRequestCreateRequest.addHeader(Headers.BCE_ACL_GRANT_FULL_CONTROL, setObjectAclRequest.getxBceGrantFullControl());
            setZeroContentLength(internalRequestCreateRequest);
        } else {
            CheckUtils.isNotNull(null, "request.acl should not be null.");
        }
        invokeHttpClient(internalRequestCreateRequest, BosResponse.class);
    }

    public GetObjectAclResponse getObjectAcl(GetObjectAclRequest getObjectAclRequest) {
        CheckUtils.isNotNull(getObjectAclRequest, "request should not be null.");
        assertStringNotNullOrEmpty(getObjectAclRequest.getKey(), "object key should not be null or empty");
        InternalRequest internalRequestCreateRequest = createRequest(getObjectAclRequest, HttpMethodName.GET);
        internalRequestCreateRequest.addParameter("acl", null);
        GetObjectAclResponse getObjectAclResponse = (GetObjectAclResponse) invokeHttpClient(internalRequestCreateRequest, GetObjectAclResponse.class);
        if (getObjectAclResponse.getVersion() <= 1) {
            return getObjectAclResponse;
        }
        throw new BceClientException("Unsupported acl version.");
    }

    public void deleteObjectAcl(DeleteObjectAclRequest deleteObjectAclRequest) {
        CheckUtils.isNotNull(deleteObjectAclRequest, "request should not be null.");
        assertStringNotNullOrEmpty(deleteObjectAclRequest.getKey(), "object key should not be null or empty");
        InternalRequest internalRequestCreateRequest = createRequest(deleteObjectAclRequest, HttpMethodName.DELETE);
        internalRequestCreateRequest.addParameter("acl", null);
        invokeHttpClient(internalRequestCreateRequest, BosResponse.class);
    }
}
