package com.wanos.media.ui.actvity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.jessyan.autosize.AutoSizeCompat;

/* JADX INFO: loaded from: classes3.dex */
public class SplashActivity extends AppCompatActivity {
    public static final String TAG = "wanos:[SplashActivity]";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "SplashActivity----onCreate()----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date(System.currentTimeMillis())));
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "SplashActivity----onResume()----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date(System.currentTimeMillis())));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && event.getAction() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());
        } else {
            Log.i(TAG, "非主线程调用getResources()");
        }
        return super.getResources();
    }
}
