package com.wanos.commonlibrary.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.wanos.commonlibrary.fragment.LoadingFragment;
import com.wanos.commonlibrary.fragment.MessageDialogFragment;
import com.wanos.commonlibrary.utils.BitmapUtil;
import com.wanos.commonlibrary.utils.FileUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseActivity extends AppCompatActivity implements MessageDialogFragment.Callback {
    public boolean isResume = false;
    protected LoadingFragment loadingFragment;

    @Override // com.wanos.commonlibrary.fragment.MessageDialogFragment.Callback
    public void onCancel(int i) {
    }

    @Override // com.wanos.commonlibrary.fragment.MessageDialogFragment.Callback
    public void onSuccess(int i, int i2) {
    }

    public abstract void openWeChatPayActivity();

    public abstract void openWeChatPayActivity(int i, int i2);

    public abstract void showLoginDialog();

    public abstract void showLoginDialog(int i, String str);

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LoadingFragment loadingFragment = this.loadingFragment;
        if (loadingFragment != null) {
            loadingFragment.dismiss();
            this.loadingFragment = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isResume = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isResume = false;
    }

    public void showLoadingView() {
        LoadingFragment loadingFragment = new LoadingFragment();
        this.loadingFragment = loadingFragment;
        loadingFragment.show(getSupportFragmentManager());
    }

    public void showLoadingView(boolean z) {
        LoadingFragment loadingFragment = this.loadingFragment;
        if (loadingFragment != null && loadingFragment.isAdded()) {
            this.loadingFragment.dismiss();
        }
        this.loadingFragment = new LoadingFragment();
        if (z) {
            Bundle bundle = new Bundle();
            Objects.requireNonNull(this.loadingFragment);
            bundle.putBoolean("text_color_white", true);
            this.loadingFragment.setArguments(bundle);
        }
        this.loadingFragment.show(getSupportFragmentManager());
    }

    public void closeLoadingView() {
        this.loadingFragment.dismiss();
    }

    public void startActivity(Intent intent, boolean z) {
        if (z && intent != null) {
            intent.putExtra("bgUri", FileUtil.saveBitmapToPrivateDir(this, "ActivityScreenShot.jpg", BitmapUtil.loadBitmapFromView(getWindow().getDecorView())));
        }
        super.startActivity(intent);
    }

    public void closeAllowingStateLossLoadingView() {
        LoadingFragment loadingFragment = this.loadingFragment;
        if (loadingFragment != null) {
            loadingFragment.dismissAllowingStateLoss();
            this.loadingFragment = null;
        }
    }
}
