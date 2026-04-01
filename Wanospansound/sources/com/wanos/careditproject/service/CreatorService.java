package com.wanos.careditproject.service;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.wanos.WanosCommunication.BaseResponse2;
import com.wanos.WanosCommunication.service.ICreatorService;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CloudInfoResponse;
import com.wanos.careditproject.data.response.WorkDetail;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorService implements ICreatorService, IProvider {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.wanos.WanosCommunication.service.ICreatorService
    public String getCreatorResUrl(String str, int i) {
        try {
            Log.d("getWorkDetailUrl", "resType:" + i);
            if (i == 2) {
                Response<CloudInfoResponse> cloudFileInfo = CreatorRetrofitUtil.getCloudFileInfo(Integer.parseInt(str));
                if (!cloudFileInfo.isSuccessful() || cloudFileInfo.body() == null || cloudFileInfo.body().data == null || cloudFileInfo.body().data.getInfo() == null) {
                    return "";
                }
                Log.d("getWorkDetailUrl", "resType:" + i + ", url = " + cloudFileInfo.body().data.getInfo().getUrlSrc());
                return cloudFileInfo.body().data.getInfo().getUrlSrc();
            }
            if (i == 3) {
                Response<CloudInfoResponse> assetsInfo = CreatorRetrofitUtil.getAssetsInfo(Integer.parseInt(str));
                if (!assetsInfo.isSuccessful() || assetsInfo.body() == null || assetsInfo.body().data == null || assetsInfo.body().data.getInfo() == null) {
                    return "";
                }
                Log.d("getWorkDetailUrl", "resType:" + i + ", url = " + assetsInfo.body().data.getInfo().getUrlSrc());
                return assetsInfo.body().data.getInfo().getUrlSrcWanos();
            }
            Response<BaseResponse2<WorkDetail>> workDetail = CreatorRetrofitUtil.getWorkDetail(str);
            if (!workDetail.isSuccessful() || workDetail.body() == null || workDetail.body().data == null || workDetail.body().data.getInfo() == null) {
                return "";
            }
            Log.d("getWorkDetailUrl", "resType:" + i + ", url = " + workDetail.body().data.getInfo().getWanosPath());
            return workDetail.body().data.getInfo().getWanosPath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
