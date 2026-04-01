package com.wanos.careditproject.view.audiotrackedit.objpossetting;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import com.wanos.careditproject.R;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.opengl.BaseObjPosGLView;
import com.wanos.careditproject.view.opengl.PosLeftGLRender;

/* JADX INFO: loaded from: classes3.dex */
public class ObjPosSettingViewLeft extends BaseObjPosGLView {
    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    protected boolean isLeft() {
        return true;
    }

    public ObjPosSettingViewLeft(Context context) {
        super(context);
    }

    public ObjPosSettingViewLeft(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    public void setPosToUI(float f, float f2, float f3) {
        setPos(new PointF(f, f2), false);
    }

    public void destroy() {
        if (this.render instanceof PosLeftGLRender) {
            ((PosLeftGLRender) this.render).destroy();
        }
    }

    @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView
    protected void initRender(Context context) {
        this.outW = getResources().getDimensionPixelSize(R.dimen.at_pos_bg_out_size);
        this.inW = getResources().getDimensionPixelSize(R.dimen.at_pos_bg_size);
        PosLeftGLRender.init(this.outW, this.inW);
        EditingUtils.log("initRender left outW = " + this.outW + ", inW = " + this.inW);
        this.render = new PosLeftGLRender(context.getAssets());
        setRenderer(this.render);
    }
}
