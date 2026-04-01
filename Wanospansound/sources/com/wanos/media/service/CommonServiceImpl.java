package com.wanos.media.service;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.wanos.commonlibrary.service.CommonService;
import com.wanos.media.MainApplication;

/* JADX INFO: loaded from: classes3.dex */
public class CommonServiceImpl implements IProvider, CommonService {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.wanos.commonlibrary.service.CommonService
    public Application getApplication() {
        return MainApplication.getInstance();
    }
}
