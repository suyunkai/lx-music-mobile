package com.wanos.media;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import com.wanos.media.base.BaseActContent;
import com.wanos.media.base.BaseFragContent;
import com.wanos.media.base.WanosBaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public interface MainServer {
    BaseActContent baseActOnCreate(WanosBaseActivity wanosBaseActivity, Bundle bundle);

    BaseFragContent baseFragOnCreate();

    void buildPlayPageFromBar();

    Activity getTopActivity();

    void showLoginDialog();

    void showVipDialog();

    void startMusicPlayActivity();

    void startMusicPlayActivity(boolean z);

    void webActivityStart(Context context, String str, String str2, Messenger messenger);
}
