package com.wanos.autoserviceImpl;

import com.wanos.autoservice.MediaPlayer2AppInterface;
import com.wanos.media.ui.BootBroadReceiver;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCenter2AppInterfaceImpl implements MediaPlayer2AppInterface {
    @Override // com.wanos.autoservice.MediaPlayer2AppInterface
    public long getBootCompletedTime() {
        return BootBroadReceiver.bootCompleteTime;
    }
}
