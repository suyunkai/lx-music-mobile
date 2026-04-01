package com.wanos.commonlibrary.base;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wanos.commonlibrary.databinding.BaseDialogActivityBinding;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseBlurBgActivity extends BaseActivity {
    private BaseDialogActivityBinding baseDialogActivityBinding;

    public View getContentView() {
        return null;
    }

    public int getContentViewId() {
        return -1;
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public abstract void showLoginDialog();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Uri uri = (Uri) getIntent().getParcelableExtra("bgUri");
        super.onCreate(bundle);
        this.baseDialogActivityBinding = BaseDialogActivityBinding.inflate(getLayoutInflater());
        if (uri != null) {
            Glide.with((FragmentActivity) this).load(uri.getPath()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.baseDialogActivityBinding.ivBg);
        }
        if (getContentViewId() != -1) {
            LayoutInflater.from(this).inflate(getContentViewId(), this.baseDialogActivityBinding.baseContentViewgroup);
        } else if (getContentView() != null) {
            this.baseDialogActivityBinding.baseContentViewgroup.addView(getContentView());
        }
        setContentView(this.baseDialogActivityBinding.getRoot());
        getWindow().setLayout(-1, -1);
    }
}
