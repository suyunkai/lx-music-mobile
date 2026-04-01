package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicGorupListBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.GetMusicGroupListResponse;
import com.wanos.media.view.MusicGroupListView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicGroupListPresenter extends BasePresenter<MusicGroupListView> {
    public static final String TAG = "wanos:[MusicGroupListPresenter]";
    private final boolean isMyGroup;
    String json2;

    public MusicGroupListPresenter(MusicGroupListView view) {
        this(view, false);
    }

    public MusicGroupListPresenter(MusicGroupListView view, boolean isMyGroup) {
        this.json2 = "[\n            {\n                \"musicGroupId\": 17,\n                \"name\": \"此刻多么想你\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/17.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032270;1706672270&q-key-time=1698032270;1706672270&q-header-list=&q-url-param-list=&q-signature=ef3cb5e43310c046fe4ca173b67175becfbcc331\",\n                \"duration\": 250.82,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/17.m4a\",\n                \"isCollection\": true,\n                \"singerList\": [\n                    {\n                        \"singerId\": 81,\n                        \"name\": \"张韵鸷\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/17.txt?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032600;1706672600&q-key-time=1698032600;1706672600&q-header-list=&q-url-param-list=&q-signature=83a254c07f0dccbcf13abe6472f5e94d66125421\"\n            },\n            {\n                \"musicGroupId\": 18,\n                \"name\": \"清尘\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/18.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032280;1706672280&q-key-time=1698032280;1706672280&q-header-list=&q-url-param-list=&q-signature=be45c3219780e0d7413d26b72f24045a4da51836\",\n                \"duration\": 236.67,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/18.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 14,\n                        \"name\": \"井胧\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/18.lrc?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032609;1706672609&q-key-time=1698032609;1706672609&q-header-list=&q-url-param-list=&q-signature=16beb50ff081185900d3945dc4125f25fc619e13\"\n            },\n            {\n                \"musicGroupId\": 19,\n                \"name\": \"病症\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/19.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032289;1706672289&q-key-time=1698032289;1706672289&q-header-list=&q-url-param-list=&q-signature=e3414f17ee9650a2b6167c38f5254317783d7722\",\n                \"duration\": 188.2,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/19.m4a\",\n                \"isCollection\": true,\n                \"singerList\": [\n                    {\n                        \"singerId\": 9,\n                        \"name\": \"陈柯伶Kiana\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/19.lrc?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032617;1706672617&q-key-time=1698032617;1706672617&q-header-list=&q-url-param-list=&q-signature=a1a432b550b4a4779cd689bcbdcd22741dcd296a\"\n            },\n            {\n                \"musicGroupId\": 20,\n                \"name\": \"相思人断肠\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/20.png?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032296;1706672296&q-key-time=1698032296;1706672296&q-header-list=&q-url-param-list=&q-signature=d8d4caf80002b3d44d4d9129142ee50c57d56535\",\n                \"duration\": 217.34,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/20.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 15,\n                        \"name\": \"指尖笑\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/20.lrc?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032625;1706672625&q-key-time=1698032625;1706672625&q-header-list=&q-url-param-list=&q-signature=6e3153f602fc94ac531810f11fadd3c78b635f1a\"\n            },\n            {\n                \"musicGroupId\": 21,\n                \"name\": \"离了谁我都可以\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/21.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032304;1706672304&q-key-time=1698032304;1706672304&q-header-list=&q-url-param-list=&q-signature=a30911d14f606243df2071b5afd91e7bf17543e6\",\n                \"duration\": 206.04,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/21.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 81,\n                        \"name\": \"张韵鸷\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/21.txt?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032634;1706672634&q-key-time=1698032634;1706672634&q-header-list=&q-url-param-list=&q-signature=0f9ed296cb84a5e69c24908c427584b4e5e6267f\"\n            },\n            {\n                \"musicGroupId\": 22,\n                \"name\": \"紫色\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/22.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032313;1706672313&q-key-time=1698032313;1706672313&q-header-list=&q-url-param-list=&q-signature=33f16a51f8386fc63b1c703b57a2c06c2e5792a4\",\n                \"duration\": 208.49,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/22.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 9,\n                        \"name\": \"陈柯伶Kiana\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/22.lrc?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032642;1706672642&q-key-time=1698032642;1706672642&q-header-list=&q-url-param-list=&q-signature=1ad4403dcb68121b8cf600f7fb0e8fc049296bf4\"\n            },\n            {\n                \"musicGroupId\": 24,\n                \"name\": \"缺席\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/24.png?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032332;1706672332&q-key-time=1698032332;1706672332&q-header-list=&q-url-param-list=&q-signature=ba035b5ea498044681b471b2af1fe949e8d9c3a5\",\n                \"duration\": 254.63,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/24.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 17,\n                        \"name\": \"高振铭\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/23.lrc?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032659;1706672659&q-key-time=1698032659;1706672659&q-header-list=&q-url-param-list=&q-signature=23d23b5c7dabdd006b8c8510686cc2d70284ab5f\"\n            },\n            {\n                \"musicGroupId\": 25,\n                \"name\": \"荧荧\",\n                \"avatar\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/25.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032340;1706672340&q-key-time=1698032340;1706672340&q-header-list=&q-url-param-list=&q-signature=a41fe7d5f0c948112d152c00e6492758aa0d8ed2\",\n                \"duration\": 181.12,\n                \"tag\": null,\n                \"musicPath\": \"https://test-wanos-go-beer-1306012690.cos.ap-beijing.myqcloud.com/medialib/wanos/25.m4a\",\n                \"isCollection\": false,\n                \"singerList\": [\n                    {\n                        \"singerId\": 18,\n                        \"name\": \"高佳依\"\n                    }\n                ],\n                \"lrcPath\": \"https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/lrc/25.txt?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032668;1706672668&q-key-time=1698032668;1706672668&q-header-list=&q-url-param-list=&q-signature=e8dfd7f46c46580bd74a88f5c0eaf15975e21fd1\"\n            }\n        ]";
        this.mView = view;
        this.isMyGroup = isMyGroup;
    }

    public void requestData(final int page, Context context) {
        ((MusicGroupListView) this.mView).showLoading();
        if (!this.isMyGroup) {
            WanOSRetrofitUtil.getMusicGroupList(page, 100, new ResponseCallBack<GetMusicGroupListResponse>(context) { // from class: com.wanos.media.presenter.MusicGroupListPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicGroupListResponse response) {
                    MediaMusicGorupListBean mediaMusicGorupListBean = response.data;
                    if (mediaMusicGorupListBean != null && MusicGroupListPresenter.this.mView != 0) {
                        List<MusicGroupInfo> musicGroupList = mediaMusicGorupListBean.getMusicGroupList();
                        if (page == 1) {
                            ((MusicGroupListView) MusicGroupListPresenter.this.mView).updateView(mediaMusicGorupListBean.getTotal(), musicGroupList);
                        } else {
                            ((MusicGroupListView) MusicGroupListPresenter.this.mView).addDataView(page, musicGroupList);
                        }
                    }
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).hideLoading();
                    Log.i(MusicGroupListPresenter.TAG, "onResponseSuccessful: hideLoading 11");
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).hideLoading();
                    Log.i(MusicGroupListPresenter.TAG, "onResponseSuccessful: hideLoading 22");
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).showFailOrError(msg);
                }
            });
        } else {
            WanOSRetrofitUtil.getMusicGroupCollectList(page, 100, new ResponseCallBack<GetMusicGroupListResponse>(context) { // from class: com.wanos.media.presenter.MusicGroupListPresenter.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicGroupListResponse response) {
                    MediaMusicGorupListBean mediaMusicGorupListBean = response.data;
                    if (mediaMusicGorupListBean != null && MusicGroupListPresenter.this.mView != 0) {
                        List<MusicGroupInfo> musicGroupList = mediaMusicGorupListBean.getMusicGroupList();
                        if (page == 1) {
                            ((MusicGroupListView) MusicGroupListPresenter.this.mView).updateView(mediaMusicGorupListBean.getTotal(), musicGroupList);
                        } else {
                            ((MusicGroupListView) MusicGroupListPresenter.this.mView).addDataView(page, musicGroupList);
                        }
                    }
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).hideLoading();
                    Log.i(MusicGroupListPresenter.TAG, "onResponseSuccessful: hideLoading 33");
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).hideLoading();
                    Log.i(MusicGroupListPresenter.TAG, "onResponseSuccessful: hideLoading 44");
                    ((MusicGroupListView) MusicGroupListPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }

    private List<MusicGroupInfo> initMediaMusicGroupBeanList() {
        return (List) GsonUtils.fromJson(this.json2, new TypeToken<List<MusicGroupInfo>>() { // from class: com.wanos.media.presenter.MusicGroupListPresenter.3
        }.getType());
    }
}
