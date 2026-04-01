package com.wanos.careditproject.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.careditproject.utils.BaiduCloudUtils;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.RecordUtils;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorRecordDialogViewModel extends ViewModel {
    public final MutableLiveData<RecordUtils.RecordResult> recordResult = new MutableLiveData<>();
    public final MutableLiveData<RecordUtils.RecordRes> recordRes = new MutableLiveData<>();
    public final MutableLiveData<RecordUploadResult> recordUploadResult = new MutableLiveData<>();

    public String startRecord() {
        return RecordUtils.getInstance().startRecord(new RecordUtils.RecordListener() { // from class: com.wanos.careditproject.view.viewmodel.CreatorRecordDialogViewModel.1
            @Override // com.wanos.careditproject.utils.RecordUtils.RecordListener
            public void onStop(RecordUtils.RecordResult recordResult) {
                CreatorRecordDialogViewModel.this.recordResult.postValue(recordResult);
            }

            @Override // com.wanos.careditproject.utils.RecordUtils.RecordListener
            public void onProgress(RecordUtils.RecordRes recordRes) {
                CreatorRecordDialogViewModel.this.recordRes.postValue(recordRes);
            }

            @Override // com.wanos.careditproject.utils.RecordUtils.RecordListener
            public void savePcmData(String str, String str2) {
                EditingUtils.log("EditingPageFragment---------UploadUtils savePcmData");
            }
        });
    }

    public void uploadRes(File file, int i) {
        BaiduCloudUtils.getInstance().getTempToken(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, i, file);
        BaiduCloudUtils.getInstance().setOnUpLoadListener(new BaiduCloudUtils.OnUpLoadListener() { // from class: com.wanos.careditproject.view.viewmodel.CreatorRecordDialogViewModel.2
            @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
            public void upLoadSucceed(BaiduCloudUtils.UploadSuccessBean uploadSuccessBean) {
                CreatorRecordDialogViewModel.this.recordUploadResult.postValue(new RecordUploadResult(true, uploadSuccessBean, ""));
            }

            @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
            public void upLoadFail(String str) {
                CreatorRecordDialogViewModel.this.recordUploadResult.postValue(new RecordUploadResult(false, null, str));
            }
        });
    }

    public static class RecordUploadResult {
        public boolean isSuccess;
        public String msg;
        public BaiduCloudUtils.UploadSuccessBean uploadSuccessBean;

        public RecordUploadResult(boolean z, BaiduCloudUtils.UploadSuccessBean uploadSuccessBean, String str) {
            this.isSuccess = z;
            this.uploadSuccessBean = uploadSuccessBean;
            this.msg = str;
        }
    }
}
