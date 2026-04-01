package com.wanos.careditproject.utils;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.android.arouter.utils.Consts;
import com.baidubce.BceClientException;
import com.baidubce.BceConfig;
import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceSessionCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.util.BLog;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.careditproject.data.bean.TempTokenBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CreateCloudResponse;
import com.wanos.careditproject.data.response.CredentialResponse;
import com.wanos.careditproject.data.response.ProjectUploadResponse;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.util.NativeMethod;
import java.io.File;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class BaiduCloudUtils {
    private static final String TAG = "wanos:[BaiduCloudUtils]";
    private TempTokenBean bean;
    private BosClient client;
    private String cloudDirType;
    private PutObjectRequest cloudRequest;
    private OnUpLoadListener listener;
    private Call<CredentialResponse> tokenRequest;
    private Call<CreateCloudResponse> upLoadRequest;

    public interface OnUpLoadListener {
        default void upLoadFail(String str) {
        }

        default void upLoadSucceed(UploadSuccessBean uploadSuccessBean) {
        }
    }

    public class UploadSuccessBean {
        private int assetId;
        private int taskId;
        private String url;

        public UploadSuccessBean(String str, int i, int i2) {
            this.url = str;
            this.assetId = i;
            this.taskId = i2;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getAssetId() {
            return this.assetId;
        }

        public void setAssetId(int i) {
            this.assetId = i;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public void setTaskId(int i) {
            this.taskId = i;
        }
    }

    private BaiduCloudUtils() {
    }

    public static BaiduCloudUtils getInstance() {
        return BaiduCloudHolder.instance;
    }

    private static class BaiduCloudHolder {
        private static final BaiduCloudUtils instance = new BaiduCloudUtils();

        private BaiduCloudHolder() {
        }
    }

    public void getTempToken(String str, final int i, final File file) {
        this.cloudDirType = str;
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(Consts.DOT);
        String strSubstring = name.substring(iLastIndexOf + 1);
        final String strSubstring2 = name.substring(0, iLastIndexOf);
        Log.i(TAG, "getTempToken----dirType=" + str + "\n\r----suffix=" + strSubstring + "\n\r----fileName=" + strSubstring2);
        ResponseCallBack<CredentialResponse> responseCallBack = new ResponseCallBack<CredentialResponse>(null) { // from class: com.wanos.careditproject.utils.BaiduCloudUtils.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CredentialResponse credentialResponse) {
                if (credentialResponse == null || credentialResponse.data == null) {
                    return;
                }
                BaiduCloudUtils.this.initBosClient(i, strSubstring2, credentialResponse.data, file);
                BaiduCloudUtils.this.tokenRequest = null;
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str2) {
                if (BaiduCloudUtils.this.listener != null) {
                    BaiduCloudUtils.this.listener.upLoadFail(str2);
                    BaiduCloudUtils.this.tokenRequest = null;
                }
            }
        };
        if (this.cloudDirType.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.tokenRequest = CreatorRetrofitUtil.getUploadTempToken(str, strSubstring, responseCallBack);
        } else if (this.cloudDirType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            this.tokenRequest = CreatorRetrofitUtil.getUploadTempToken(str, strSubstring, i, responseCallBack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBosClient(int i, String str, TempTokenBean tempTokenBean, File file) {
        this.bean = tempTokenBean;
        BLog.enableLog();
        BosClientConfiguration bosClientConfiguration = new BosClientConfiguration();
        bosClientConfiguration.setCredentials(new DefaultBceSessionCredentials(tempTokenBean.getAccessKeyId(), tempTokenBean.getSecretAccessKey(), tempTokenBean.getSessionToken()));
        bosClientConfiguration.setEndpoint(tempTokenBean.getEndPoint());
        bosClientConfiguration.setProtocol(Protocol.HTTPS);
        bosClientConfiguration.setMaxConnections(10);
        bosClientConfiguration.setConnectionTimeoutInMillis(10000);
        bosClientConfiguration.setSocketTimeoutInMillis(5000);
        this.client = new BosClient(bosClientConfiguration);
        putObject(i, str, tempTokenBean, file);
    }

    public void setOnUpLoadListener(OnUpLoadListener onUpLoadListener) {
        this.listener = onUpLoadListener;
    }

    private void putObject(final int i, final String str, final TempTokenBean tempTokenBean, File file) {
        Log.i(TAG, "开始上传素材");
        if (this.client == null) {
            Log.i(TAG, "BosClient为空");
            return;
        }
        this.cloudRequest = new PutObjectRequest(tempTokenBean.getBucketName(), tempTokenBean.getObjectKey(), file);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/octet-stream");
        this.cloudRequest.setObjectMetadata(objectMetadata);
        this.cloudRequest.setProgressCallback(new BosProgressCallback<PutObjectRequest>() { // from class: com.wanos.careditproject.utils.BaiduCloudUtils.2
            @Override // com.baidubce.services.bos.callback.BosProgressCallback, com.baidubce.callback.BceProgressCallback
            public void onProgress(PutObjectRequest putObjectRequest, long j, long j2) {
                super.onProgress(putObjectRequest, j, j2);
                Log.i(BaiduCloudUtils.TAG, "currentSize = " + j + "----totalSize = " + j2);
            }
        });
        new Thread(new Runnable() { // from class: com.wanos.careditproject.utils.BaiduCloudUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.m409x564c2341(tempTokenBean, i, str);
            }
        }).start();
    }

    /* JADX INFO: renamed from: lambda$putObject$0$com-wanos-careditproject-utils-BaiduCloudUtils, reason: not valid java name */
    /* synthetic */ void m409x564c2341(TempTokenBean tempTokenBean, int i, String str) throws Throwable {
        String str2;
        try {
            PutObjectResponse putObjectResponsePutObject = this.client.putObject(this.cloudRequest);
            if (putObjectResponsePutObject != null) {
                String str3 = tempTokenBean.getEndPoint() + BceConfig.BOS_DELIMITER + tempTokenBean.getObjectKey();
                if (("ETag: " + putObjectResponsePutObject.getETag()) != null) {
                    str2 = putObjectResponsePutObject.getETag() + "";
                } else {
                    str2 = new StringBuilder("ETag为空\n\rCar32: ").append(putObjectResponsePutObject.getCrc32()).toString() != null ? putObjectResponsePutObject.getCrc32() + "" : "Car32为空\n\rurl: " + str3;
                }
                Log.i(TAG, str2);
                this.cloudRequest = null;
                if (this.cloudDirType.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    uploadCloudAsset(i, str, str3, putObjectResponsePutObject.getETag());
                } else if (this.cloudDirType.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    uploadProject(i, str, str3, putObjectResponsePutObject.getETag());
                }
            }
        } catch (BceServiceException e) {
            OnUpLoadListener onUpLoadListener = this.listener;
            if (onUpLoadListener != null) {
                onUpLoadListener.upLoadFail(e.getMessage());
                this.cloudRequest = null;
            }
            Log.i(TAG, "Error Message: " + e.getMessage());
            Log.i(TAG, "Error ErrorType: " + e.getErrorType());
        } catch (BceClientException e2) {
            OnUpLoadListener onUpLoadListener2 = this.listener;
            if (onUpLoadListener2 != null) {
                onUpLoadListener2.upLoadFail(e2.getMessage());
                this.cloudRequest = null;
            }
            Log.i(TAG, "Error Message: " + e2.getMessage());
        }
    }

    private void uploadProject(int i, String str, final String str2, String str3) {
        CreatorRetrofitUtil.projectUploadAsset(i, str, str2, str3, new ResponseCallBack<ProjectUploadResponse>(null) { // from class: com.wanos.careditproject.utils.BaiduCloudUtils.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(ProjectUploadResponse projectUploadResponse) {
                if (projectUploadResponse.data != null) {
                    if (BaiduCloudUtils.this.listener != null) {
                        BaiduCloudUtils.this.listener.upLoadSucceed(BaiduCloudUtils.this.new UploadSuccessBean(str2, projectUploadResponse.data.assetId, projectUploadResponse.data.taskId));
                        BaiduCloudUtils.this.upLoadRequest = null;
                        return;
                    }
                    return;
                }
                if (BaiduCloudUtils.this.listener != null) {
                    BaiduCloudUtils.this.listener.upLoadFail("数据错误！");
                    BaiduCloudUtils.this.upLoadRequest = null;
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str4) {
                if (BaiduCloudUtils.this.listener != null) {
                    BaiduCloudUtils.this.listener.upLoadFail(str4);
                    BaiduCloudUtils.this.upLoadRequest = null;
                }
            }
        });
    }

    private void uploadCloudAsset(int i, String str, String str2, String str3) {
        this.upLoadRequest = CreatorRetrofitUtil.cloudUpLoadAsset(i, str, str2, str3, new ResponseCallBack<CreateCloudResponse>(null) { // from class: com.wanos.careditproject.utils.BaiduCloudUtils.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CreateCloudResponse createCloudResponse) {
                if (BaiduCloudUtils.this.listener != null) {
                    BaiduCloudUtils.this.listener.upLoadSucceed(null);
                    BaiduCloudUtils.this.upLoadRequest = null;
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str4) {
                if (BaiduCloudUtils.this.listener != null) {
                    BaiduCloudUtils.this.listener.upLoadFail(str4);
                    BaiduCloudUtils.this.upLoadRequest = null;
                }
            }
        });
    }

    public boolean isSupportFileFormat(File file) {
        String path = file.getPath();
        String fileExtension = EditingUtils.getFileExtension(path);
        if (file.exists()) {
            long length = file.length();
            EditingUtils.log("addLocalResToProject size = " + length);
            if (length > EditingUtils.DEFAULT_SHORT_FILE_MAX_CACHE_SIZE) {
                EditingUtils.log("addLocalResToProject size");
                ToastUtil.showMsg("文件太大，请检查文件！");
                return true;
            }
            if (!fileExtension.equals("mp3") && !fileExtension.equals("aac") && !fileExtension.equals("wanos") && !fileExtension.equals("wav") && !fileExtension.equals("m4a")) {
                EditingUtils.log("addLocalResToProject a");
                ToastUtil.showMsg("文件无效，请检查文件！");
                return true;
            }
            int iAudioIsValid = NativeMethod.getInstance().audioIsValid(path);
            if (iAudioIsValid == 0) {
                return false;
            }
            if (iAudioIsValid == -10) {
                EditingUtils.log("addLocalResToProject b");
                ToastUtil.showMsg("该文件为多声道，请选择其它文件！");
            } else {
                EditingUtils.log("addLocalResToProject b");
                ToastUtil.showMsg("文件无效，请检查文件！");
            }
            return true;
        }
        EditingUtils.log("addLocalResToProject file");
        ToastUtil.showMsg("文件已不存在，请检查文件！");
        return true;
    }

    public void cancelUpload() {
        Call<CredentialResponse> call = this.tokenRequest;
        if (call != null && !call.isCanceled()) {
            this.tokenRequest.cancel();
        }
        PutObjectRequest putObjectRequest = this.cloudRequest;
        if (putObjectRequest != null && !putObjectRequest.isCanceled()) {
            this.cloudRequest.cancel();
        }
        Call<CreateCloudResponse> call2 = this.upLoadRequest;
        if (call2 == null || call2.isCanceled()) {
            return;
        }
        this.upLoadRequest.cancel();
    }
}
