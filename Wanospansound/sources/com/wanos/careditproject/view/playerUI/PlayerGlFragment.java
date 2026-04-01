package com.wanos.careditproject.view.playerUI;

import android.os.Handler;
import android.os.Looper;
import com.wanos.commonlibrary.base.BaseFragment;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerGlFragment extends BaseFragment {
    private Handler handler = null;
    private Runnable runnable = null;
    private boolean isDrawing = true;
    protected int showTypeAnimateNum = 0;

    public void drawGLView() {
    }

    @Override // com.wanos.commonlibrary.base.BaseFragment
    public void notifyMediaPlayBarVisiable(boolean z) {
    }

    public void startDrawThread() {
        if (this.handler == null || this.runnable == null) {
            this.isDrawing = true;
            this.handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() { // from class: com.wanos.careditproject.view.playerUI.PlayerGlFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PlayerGlFragment.this.isDrawing) {
                        PlayerGlFragment.this.drawGLView();
                        PlayerGlFragment.this.handler.postDelayed(this, 60L);
                    }
                }
            };
            this.runnable = runnable;
            this.handler.post(runnable);
        }
    }

    public void stopDrawThread() {
        this.isDrawing = false;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    public void showTypeAniStart() {
        this.showTypeAnimateNum = 0;
    }
}
