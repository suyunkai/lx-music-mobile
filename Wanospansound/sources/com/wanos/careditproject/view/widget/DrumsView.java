package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class DrumsView extends RecyclerView {
    private OnDrumNoteListener listener;
    private SparseArray<Integer> pressedPos;

    public interface OnDrumNoteListener {
        void noteOff(int i);

        void noteOn(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public DrumsView(Context context) {
        super(context);
        this.pressedPos = new SparseArray<>();
    }

    public DrumsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pressedPos = new SparseArray<>();
    }

    public DrumsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pressedPos = new SparseArray<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b  */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            int r1 = r9.getActionIndex()
            float r2 = r9.getX(r1)
            float r3 = r9.getY(r1)
            r4 = -1
            r5 = 1
            if (r0 == 0) goto L7b
            if (r0 == r5) goto L68
            r6 = 2
            if (r0 == r6) goto L23
            r9 = 3
            if (r0 == r9) goto L68
            r9 = 5
            if (r0 == r9) goto L7b
            r9 = 6
            if (r0 == r9) goto L68
            goto L91
        L23:
            int r0 = r9.getPointerCount()
            r1 = 0
        L28:
            if (r1 >= r0) goto L91
            int r2 = r9.getPointerId(r1)
            float r3 = r9.getX(r1)
            float r6 = r9.getY(r1)
            int r3 = r8.checkTouchInPos(r3, r6)
            android.util.SparseArray<java.lang.Integer> r6 = r8.pressedPos
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L65
            int r7 = r6.intValue()
            if (r3 == r7) goto L65
            com.wanos.careditproject.view.widget.DrumsView$OnDrumNoteListener r7 = r8.listener
            if (r7 == 0) goto L65
            int r6 = r6.intValue()
            r7.noteOff(r6)
            if (r3 <= r4) goto L65
            com.wanos.careditproject.view.widget.DrumsView$OnDrumNoteListener r6 = r8.listener
            r6.noteOn(r3)
            android.util.SparseArray<java.lang.Integer> r6 = r8.pressedPos
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r6.put(r2, r3)
        L65:
            int r1 = r1 + 1
            goto L28
        L68:
            int r9 = r8.checkTouchInPos(r2, r3)
            if (r9 <= r4) goto L91
            com.wanos.careditproject.view.widget.DrumsView$OnDrumNoteListener r0 = r8.listener
            if (r0 == 0) goto L91
            r0.noteOff(r9)
            android.util.SparseArray<java.lang.Integer> r9 = r8.pressedPos
            r9.remove(r1)
            goto L91
        L7b:
            int r9 = r8.checkTouchInPos(r2, r3)
            if (r9 <= r4) goto L91
            com.wanos.careditproject.view.widget.DrumsView$OnDrumNoteListener r0 = r8.listener
            if (r0 == 0) goto L91
            r0.noteOn(r9)
            android.util.SparseArray<java.lang.Integer> r0 = r8.pressedPos
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r0.put(r1, r9)
        L91:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.view.widget.DrumsView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private int checkTouchInPos(float f, float f2) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (f > childAt.getLeft() && f < childAt.getRight() && f2 > childAt.getTop() && f2 < childAt.getBottom()) {
                return i;
            }
        }
        return -1;
    }

    public void setOnDrumNoteListener(OnDrumNoteListener onDrumNoteListener) {
        this.listener = onDrumNoteListener;
    }
}
