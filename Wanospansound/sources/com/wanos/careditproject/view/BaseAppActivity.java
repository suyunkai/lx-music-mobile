package com.wanos.careditproject.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.base.BaseActivity;
import java.util.Objects;
import me.jessyan.autosize.AutoSizeCompat;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAppActivity extends BaseActivity {
    protected EditLoadingFragment editLoadingFragment;

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity() {
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity(int i, int i2) {
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog() {
    }

    public void showLoginDialog(int i) {
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog(int i, String str) {
    }

    static {
        EditingUtils.loadEditLibrariesOnce();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        EditingUtils.init(this);
        super.onCreate(bundle);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        EditingUtils.init(this);
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        EditingUtils.destory();
        super.onStop();
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        closeLoadingView();
        super.onDestroy();
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        showLoadingView(false, false);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView(boolean z) {
        showLoadingView(z, false);
    }

    public void showLoadingView(boolean z, boolean z2) {
        if (isShowLoadingView()) {
            this.editLoadingFragment.dismiss();
        }
        this.editLoadingFragment = new EditLoadingFragment();
        Bundle bundle = new Bundle();
        Objects.requireNonNull(this.editLoadingFragment);
        bundle.putBoolean("text_color_white", z);
        Objects.requireNonNull(this.editLoadingFragment);
        bundle.putBoolean("is_cancel", z2);
        this.editLoadingFragment.setArguments(bundle);
        this.editLoadingFragment.show(getSupportFragmentManager());
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void closeLoadingView() {
        EditLoadingFragment editLoadingFragment = this.editLoadingFragment;
        if (editLoadingFragment != null) {
            editLoadingFragment.dismissAllowingStateLoss();
            this.editLoadingFragment = null;
        }
    }

    public boolean isShowLoadingView() {
        EditLoadingFragment editLoadingFragment = this.editLoadingFragment;
        return editLoadingFragment != null && editLoadingFragment.isAdded();
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void closeAllowingStateLossLoadingView() {
        EditLoadingFragment editLoadingFragment = this.editLoadingFragment;
        if (editLoadingFragment != null) {
            editLoadingFragment.dismissAllowingStateLoss();
            this.editLoadingFragment = null;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());
        } else {
            Log.i("AutoSizeCompat", "非主线程调用getResources()");
        }
        return super.getResources();
    }
}
