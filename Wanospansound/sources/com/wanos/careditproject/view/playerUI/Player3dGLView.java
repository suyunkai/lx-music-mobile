package com.wanos.careditproject.view.playerUI;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.playerUI.Player3dGLRender;

/* JADX INFO: loaded from: classes3.dex */
public class Player3dGLView extends GLSurfaceView {
    private Player3dGLRender.IGlCreatedCallback glCreatedCallback;
    private boolean isDestroy;
    private int isTouch;
    private float lastDistance;
    private Player3dGLRender render;
    private int touchNum;

    public Player3dGLView(Context context) {
        super(context, null);
        this.isDestroy = false;
        this.glCreatedCallback = null;
        this.touchNum = 0;
        this.isTouch = 0;
        this.lastDistance = 0.0f;
    }

    public Player3dGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDestroy = false;
        this.glCreatedCallback = null;
        this.touchNum = 0;
        this.isTouch = 0;
        this.lastDistance = 0.0f;
        setEGLContextClientVersion(3);
        initRender(context);
        setRenderMode(0);
    }

    public void destroy() {
        this.isDestroy = true;
        Player3dGLRender player3dGLRender = this.render;
        if (player3dGLRender != null) {
            player3dGLRender.destory();
        }
    }

    public void setShowType(int i) {
        if (this.render != null) {
            EditingUtils.log("Player3dGLView setShowType draw");
            this.render.setShowType(i);
        }
    }

    public void setGlCreatedCallback(Player3dGLRender.IGlCreatedCallback iGlCreatedCallback) {
        this.glCreatedCallback = iGlCreatedCallback;
    }

    protected void initRender(Context context) {
        EditingUtils.log("initRender Player3dGLView");
        Player3dGLRender player3dGLRender = new Player3dGLRender(context.getAssets(), new Player3dGLRender.IGlCreatedCallback() { // from class: com.wanos.careditproject.view.playerUI.Player3dGLView.1
            @Override // com.wanos.careditproject.view.playerUI.Player3dGLRender.IGlCreatedCallback
            public void onCallback() {
                if (Player3dGLView.this.glCreatedCallback != null) {
                    Player3dGLView.this.glCreatedCallback.onCallback();
                }
            }
        });
        this.render = player3dGLRender;
        setRenderer(player3dGLRender);
    }

    public void draw() {
        if (this.isDestroy) {
            return;
        }
        requestRender();
    }

    public boolean setPlayerPos(WebBallInfoModel[] webBallInfoModelArr, int i) {
        Player3dGLRender player3dGLRender = this.render;
        if (player3dGLRender == null || this.isDestroy) {
            return false;
        }
        return player3dGLRender.setPlayerPosV2(webBallInfoModelArr, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00d4  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.view.playerUI.Player3dGLView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private float getDistance(PointF pointF, PointF pointF2) {
        float f = pointF.x - pointF2.x;
        float f2 = pointF.y - pointF2.y;
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }
}
