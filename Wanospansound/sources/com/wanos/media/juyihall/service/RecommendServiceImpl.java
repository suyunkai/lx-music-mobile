package com.wanos.media.juyihall.service;

import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MediaMusicDetailBean;
import com.wanos.WanosCommunication.response.GetMusicDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.media.juyihall.bean.GetRecDailyResponse;
import com.wanos.media.juyihall.bean.GetRecMediaDetailResponse;
import com.wanos.media.juyihall.bean.GetRecMusicListResponse;
import com.wanos.media.juyihall.repo.RecommendRepository;
import java.io.IOException;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public class RecommendServiceImpl implements com.wanos.WanosCommunication.service.RecommendService, IProvider {
    RecommendRepository repository = new RecommendRepository();

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.wanos.WanosCommunication.service.RecommendService
    public void getRecommendMusicList(int i, int i2, long j, final ResponseCallBack<GetMusicListResponse> responseCallBack) {
        Context context = null;
        if (j == -12) {
            this.repository.getRecommendDailyList(i, i2, new ResponseCallBack<GetRecDailyResponse>(context) { // from class: com.wanos.media.juyihall.service.RecommendServiceImpl.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetRecDailyResponse getRecDailyResponse) {
                    responseCallBack.onResponseSuccessful(getRecDailyResponse.toMusicListResponse());
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i3, String str) {
                    responseCallBack.onResponseFailure(i3, str);
                }
            });
        } else if (j == -10) {
            this.repository.getRecommendMusicList(false, new ResponseCallBack<GetRecMusicListResponse>(context) { // from class: com.wanos.media.juyihall.service.RecommendServiceImpl.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetRecMusicListResponse getRecMusicListResponse) {
                    responseCallBack.onResponseSuccessful(getRecMusicListResponse.toMusicListResponse());
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i3, String str) {
                    responseCallBack.onResponseFailure(i3, str);
                }
            });
        } else if (j == -11) {
            this.repository.getRecommendFunctionList(false, new ResponseCallBack<GetRecMusicListResponse>(context) { // from class: com.wanos.media.juyihall.service.RecommendServiceImpl.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetRecMusicListResponse getRecMusicListResponse) {
                    responseCallBack.onResponseSuccessful(getRecMusicListResponse.toMusicListResponse());
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i3, String str) {
                    responseCallBack.onResponseFailure(i3, str);
                }
            });
        }
    }

    @Override // com.wanos.WanosCommunication.service.RecommendService
    public Response<GetMusicListResponse> getRecommendMusicList(int i, int i2, long j, String str) throws IOException {
        if (j == -12) {
            Response<GetRecDailyResponse> recommendDailyList = this.repository.getRecommendDailyList(i, i2);
            if (recommendDailyList.isSuccessful()) {
                return Response.success(recommendDailyList.body().toMusicListResponse(), recommendDailyList.raw());
            }
            return Response.error(recommendDailyList.errorBody(), recommendDailyList.raw());
        }
        if (j == -11) {
            Response<GetRecMusicListResponse> recommendFunctionList = this.repository.getRecommendFunctionList(false);
            if (recommendFunctionList.isSuccessful()) {
                return Response.success(recommendFunctionList.body().toMusicListResponse(), recommendFunctionList.raw());
            }
            return Response.error(recommendFunctionList.errorBody(), recommendFunctionList.raw());
        }
        if (j == -9) {
            return this.repository.getTagMusicList(i, i2, str);
        }
        Response<GetRecMusicListResponse> recommendMusicList = this.repository.getRecommendMusicList(false);
        if (recommendMusicList.isSuccessful()) {
            return Response.success(recommendMusicList.body().toMusicListResponse(), recommendMusicList.raw());
        }
        return Response.error(recommendMusicList.errorBody(), recommendMusicList.raw());
    }

    @Override // com.wanos.WanosCommunication.service.RecommendService
    public Response<GetMusicDetailResponse> getRecommendMusicDetail(String str, int i) throws IOException {
        Response<GetRecMediaDetailResponse> recommendDailyMediaDetail = this.repository.getRecommendDailyMediaDetail(str, i);
        if (recommendDailyMediaDetail.isSuccessful()) {
            MusicInfoBean musicInfoBean = recommendDailyMediaDetail.body().getMusicInfoBean();
            MediaMusicDetailBean mediaMusicDetailBean = new MediaMusicDetailBean();
            mediaMusicDetailBean.setMusicInfo(musicInfoBean);
            GetMusicDetailResponse getMusicDetailResponse = new GetMusicDetailResponse();
            getMusicDetailResponse.code = recommendDailyMediaDetail.body().code;
            getMusicDetailResponse.msg = recommendDailyMediaDetail.body().msg;
            getMusicDetailResponse.data = mediaMusicDetailBean;
            return Response.success(getMusicDetailResponse, recommendDailyMediaDetail.raw());
        }
        return Response.error(recommendDailyMediaDetail.errorBody(), recommendDailyMediaDetail.raw());
    }
}
