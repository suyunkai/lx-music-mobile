package com.wanos.careditproject.utils.anim;

import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class ExpansionListUtils {

    public interface Expandable {
        View getArrowView();

        View getExpandView();

        View getProgressView();
    }

    public static void openH(View view, View view2, View view3) {
        view2.setVisibility(8);
        view3.setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f);
        objectAnimatorOfFloat.setDuration(500L);
        objectAnimatorOfFloat.start();
        view.setVisibility(0);
    }

    public static void closeH(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f);
        objectAnimatorOfFloat.setDuration(500L);
        objectAnimatorOfFloat.start();
        view.setVisibility(8);
    }

    public static class KeepOneH<VH extends RecyclerView.ViewHolder & Expandable> {
        public void toggle(boolean z, VH vh) {
            if (z) {
                VH vh2 = vh;
                ExpansionListUtils.openH(vh2.getExpandView(), vh2.getProgressView(), vh2.getArrowView());
            } else {
                ExpansionListUtils.closeH(vh.getExpandView());
            }
        }
    }
}
