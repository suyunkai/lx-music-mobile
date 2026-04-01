package com.wanos.media.widget.video;

import android.os.Handler;
import android.os.Message;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.util.ZeroLogcatTools;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroVideoHandlerCallback implements Handler.Callback {
    public static final int MESSAGE_PAGE_SEL = 3002;
    public static final int MESSAGE_VIDEO_PLAY = 3001;
    private static final String TAG = "ZeroVideoPageView";
    private final WeakReference<ZeroVideoPageView> mWeakReference;

    public ZeroVideoHandlerCallback(ZeroVideoPageView zeroVideoPageView) {
        this.mWeakReference = new WeakReference<>(zeroVideoPageView);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        ZeroVideoPageView zeroVideoPageView = this.mWeakReference.get();
        if (zeroVideoPageView == null) {
            return false;
        }
        int i = message.what;
        boolean z = true;
        if (i == 3001) {
            ThemeInfoEntity currentThemeInfo = zeroVideoPageView.getCurrentThemeInfo();
            long jLongValue = ((Long) message.obj).longValue();
            if (currentThemeInfo != null && currentThemeInfo.getThemeId() != jLongValue) {
                z = false;
            }
            if (z && zeroVideoPageView.getPageScrollState() == 0) {
                ZeroLogcatTools.d(TAG, "onVideoInfo: 可正常展示视频");
                zeroVideoPageView.setCurrentItemState(false);
            } else {
                ZeroLogcatTools.e(TAG, "onVideoInfo: 该视频与当前主题不一致或者正在拖动，不进行视频展示");
            }
        } else if (i == 3002) {
            String str = (String) message.obj;
            String[] strArrSplit = str.split("[|]");
            if (Integer.parseInt(strArrSplit[0]) != zeroVideoPageView.getViewPageCurrentPosition()) {
                ZeroLogcatTools.e(TAG, "onThemeInfo: 待加载主题与当前显示主题不一致,mMsgPosition=" + str + ",当前ViewPagePosition=" + zeroVideoPageView.getViewPageCurrentPosition());
                return false;
            }
            zeroVideoPageView.initThemeInfoData(Integer.parseInt(strArrSplit[1]));
        }
        return false;
    }
}
