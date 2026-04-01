package com.wanos.media.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.MainServer;
import com.wanos.media.ui.advertise.WebViewActivity;
import com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.ui.login.dialog.LoginDialog;
import com.wanos.media.ui.music.activity.MusicPlayActivity;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSetUp implements MainServer {
    @Override // com.wanos.media.MainServer
    public Activity getTopActivity() {
        return MainApplication.getTopActivity();
    }

    @Override // com.wanos.media.MainServer
    public BaseActContent baseActOnCreate(WanosBaseActivity base, Bundle savedInstanceState) {
        BaseActBussSetup baseActBussSetup = new BaseActBussSetup();
        baseActBussSetup.init(base, savedInstanceState);
        return baseActBussSetup;
    }

    @Override // com.wanos.media.MainServer
    public BaseFragContent baseFragOnCreate() {
        return new BaseFragBussSetup();
    }

    @Override // com.wanos.media.MainServer
    public void startMusicPlayActivity() {
        if (MainApplication.getTopActivity() != null) {
            MusicPlayActivity.startMusicPlayActivity(MainApplication.getTopActivity());
        }
    }

    @Override // com.wanos.media.MainServer
    public void startMusicPlayActivity(boolean isOpenPlayList) {
        if (MainApplication.getTopActivity() != null) {
            MusicPlayActivity.startMusicPlayActivity(MainApplication.getTopActivity(), isOpenPlayList);
        }
    }

    @Override // com.wanos.media.MainServer
    public void buildPlayPageFromBar() {
        if (MainApplication.getTopActivity() != null) {
            AudioBookPlayerActivity.buildPlayPageFromBar(MainApplication.getTopActivity());
        }
    }

    @Override // com.wanos.media.MainServer
    public void webActivityStart(Context context, String jumpUrl, String token, Messenger mMainMessenger) {
        WebViewActivity.start(context, jumpUrl, token, mMainMessenger);
    }

    @Override // com.wanos.media.MainServer
    public void showLoginDialog() {
        if (getTopActivity() != null) {
            LoginDialog loginDialog = new LoginDialog(getTopActivity());
            loginDialog.setCancelable(true);
            loginDialog.show();
        }
    }

    @Override // com.wanos.media.MainServer
    public void showVipDialog() {
        if (MainApplication.getTopActivity() != null) {
            if (!UserInfoUtil.isLogin()) {
                showLoginDialog();
            } else {
                MainApplication.getTopActivity().startActivity(MemberPayActivity.getIntent(MainApplication.getTopActivity()));
            }
        }
    }
}
