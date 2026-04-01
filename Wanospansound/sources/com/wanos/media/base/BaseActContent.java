package com.wanos.media.base;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import com.wanos.media.base.WanosBaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public interface BaseActContent {
    boolean aiMusic();

    void dismissLoginDialog();

    void hideAiMusicMv();

    void init(WanosBaseActivity wanosBaseActivity, Bundle bundle);

    void onBackPressed();

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onRestoreInstanceState(Bundle bundle);

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void openAudioBookPlayActivity(WanosBaseActivity wanosBaseActivity);

    void openMusicPlayActivity(WanosBaseActivity wanosBaseActivity);

    void openWeChatPayActivity();

    void openWeChatPayActivity(int i, int i2);

    void showAiMusicMv();

    void showLoadDialog(WanosBaseActivity wanosBaseActivity, WanosBaseActivity.DialogShowType dialogShowType, boolean z);

    void showLoginDialog(int i, String str);

    void showLoginDialog(DialogInterface.OnDismissListener onDismissListener, boolean z);
}
