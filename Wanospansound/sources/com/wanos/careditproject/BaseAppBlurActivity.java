package com.wanos.careditproject;

import android.os.Bundle;
import com.wanos.commonlibrary.base.BaseBlurBgActivity;
import com.wanos.commonlibrary.event.PauseMusicEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAppBlurActivity extends BaseBlurBgActivity {
    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity() {
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity(int i, int i2) {
    }

    public boolean pauseMusicPlay() {
        return true;
    }

    @Override // com.wanos.commonlibrary.base.BaseBlurBgActivity, com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog() {
    }

    public void showLoginDialog(int i) {
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog(int i, String str) {
    }

    @Override // com.wanos.commonlibrary.base.BaseBlurBgActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (pauseMusicPlay()) {
            EventBus.getDefault().post(new PauseMusicEvent());
        }
    }
}
