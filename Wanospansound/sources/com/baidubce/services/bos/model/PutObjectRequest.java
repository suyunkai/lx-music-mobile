package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.util.CheckUtils;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class PutObjectRequest extends GenericObjectRequest {
    private File file;
    private InputStream inputStream;
    private ObjectMetadata objectMetadata;
    private String process;
    private BosProgressCallback progressCallback;
    private String storageClass;

    public PutObjectRequest(String str, String str2, File file) {
        this(str, str2, file, null, new ObjectMetadata());
        CheckUtils.isNotNull(file, "file should not be null.");
    }

    public PutObjectRequest(String str, String str2, File file, ObjectMetadata objectMetadata) {
        this(str, str2, file, null, objectMetadata);
        CheckUtils.isNotNull(file, "file should not be null.");
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
    }

    public PutObjectRequest(String str, String str2, InputStream inputStream) {
        this(str, str2, null, inputStream, new ObjectMetadata());
        CheckUtils.isNotNull(inputStream, "inputStream should not be null.");
    }

    public PutObjectRequest(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        this(str, str2, null, inputStream, objectMetadata);
        CheckUtils.isNotNull(inputStream, "inputStream should not be null.");
        CheckUtils.isNotNull(objectMetadata, "metadata should not be null.");
    }

    public PutObjectRequest(String str, String str2, File file, InputStream inputStream, ObjectMetadata objectMetadata) {
        super(str, str2);
        new ObjectMetadata();
        this.progressCallback = null;
        this.file = file;
        this.inputStream = inputStream;
        this.objectMetadata = objectMetadata;
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public PutObjectRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public PutObjectRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public PutObjectRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public PutObjectRequest withFile(File file) {
        setFile(file);
        return this;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    public PutObjectRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        setObjectMetadata(objectMetadata);
        return this;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PutObjectRequest withInputStream(InputStream inputStream) {
        setInputStream(inputStream);
        return this;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(String str) {
        this.storageClass = str;
    }

    public PutObjectRequest withStorageClass(String str) {
        setStorageClass(str);
        return this;
    }

    public BosProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public <T extends PutObjectRequest> void setProgressCallback(BosProgressCallback<T> bosProgressCallback) {
        this.progressCallback = bosProgressCallback;
    }

    public <T extends PutObjectRequest> PutObjectRequest withProgressCallback(BosProgressCallback<T> bosProgressCallback) {
        this.progressCallback = bosProgressCallback;
        return this;
    }

    public String getProcess() {
        return this.process;
    }

    public void setProcess(String str) {
        this.process = str;
    }

    public long getTrafficLimitBitPS() {
        return this.trafficLimitBitPS;
    }

    public void setTrafficLimitBitPS(long j) {
        this.trafficLimitBitPS = j;
    }

    public PutObjectRequest withTrafficLimitBitPS(long j) {
        setTrafficLimitBitPS(j);
        return this;
    }
}
