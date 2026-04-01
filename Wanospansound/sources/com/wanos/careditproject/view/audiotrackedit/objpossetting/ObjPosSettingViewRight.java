package com.wanos.careditproject.view.audiotrackedit.objpossetting;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.opengl.BaseObjPosGLView;
import com.wanos.careditproject.view.opengl.PosRightGLRender;

/* JADX INFO: loaded from: classes3.dex */
public class ObjPosSettingViewRight extends BaseObjPosGLView {
    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    protected boolean isLeft() {
        return false;
    }

    public ObjPosSettingViewRight(Context context) {
        super(context);
    }

    public ObjPosSettingViewRight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    public void setPosToUI(float f, float f2, float f3) {
        setPos(new PointF(0.0f, f3), false);
    }

    public void destroy() {
        if (this.render instanceof PosRightGLRender) {
            ((PosRightGLRender) this.render).destroy();
        }
    }

    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    protected void initRender(Context context) {
        this.outW = getResources().getDimensionPixelSize(R.dimen.at_pos_bg_out_size);
        this.inW = getResources().getDimensionPixelSize(R.dimen.at_pos_bg_size);
        PosRightGLRender.init(this.outW, this.inW);
        this.render = new PosRightGLRender(context.getAssets());
        setRenderer(this.render);
    }
}
