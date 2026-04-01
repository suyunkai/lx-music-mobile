package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.audiotrackedit.objpossetting.ObjPosSettingViewLeft;
import com.wanos.careditproject.view.audiotrackedit.objpossetting.ObjPosSettingViewRight;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomObjPositionBinding implements ViewBinding {
    public final ImageView btnClose;
    public final ImageView btnDrawHelp;
    public final ObjPosSettingViewLeft objLeft;
    public final ObjPosSettingViewRight objRight;
    private final LinearLayout rootView;
    public final TextView tvDraw;
    public final View viewObjPosTop;
    public final ConstraintLayout viewRoot;

    private PopBottomObjPositionBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ObjPosSettingViewLeft objPosSettingViewLeft, ObjPosSettingViewRight objPosSettingViewRight, TextView textView, View view, ConstraintLayout constraintLayout) {
        this.rootView = linearLayout;
        this.btnClose = imageView;
        this.btnDrawHelp = imageView2;
        this.objLeft = objPosSettingViewLeft;
        this.objRight = objPosSettingViewRight;
        this.tvDraw = textView;
        this.viewObjPosTop = view;
        this.viewRoot = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomObjPositionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomObjPositionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_obj_position, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomObjPositionBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_draw_help;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.obj_left;
                ObjPosSettingViewLeft objPosSettingViewLeft = (ObjPosSettingViewLeft) ViewBindings.findChildViewById(view, i);
                if (objPosSettingViewLeft != null) {
                    i = R.id.obj_right;
                    ObjPosSettingViewRight objPosSettingViewRight = (ObjPosSettingViewRight) ViewBindings.findChildViewById(view, i);
                    if (objPosSettingViewRight != null) {
                        i = R.id.tv_draw;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_obj_pos_top))) != null) {
                            i = R.id.view_root;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout != null) {
                                return new PopBottomObjPositionBinding((LinearLayout) view, imageView, imageView2, objPosSettingViewLeft, objPosSettingViewRight, textView, viewFindChildViewById, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
