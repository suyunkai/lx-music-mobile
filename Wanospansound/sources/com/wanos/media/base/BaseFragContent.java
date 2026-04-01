package com.wanos.media.base;

import android.app.Activity;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes3.dex */
public interface BaseFragContent {
    void init(Activity activity);

    void onPause();

    void onResume();

    void openWeChatPayActivity();

    void openWeChatPayActivity(int i, int i2);

    void showLoginDialog(int i, String str);

    void showLoginDialog(DialogInterface.OnDismissListener onDismissListener, boolean z);
}
