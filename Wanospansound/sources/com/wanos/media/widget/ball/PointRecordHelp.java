package com.wanos.media.widget.ball;

import android.view.MotionEvent;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class PointRecordHelp implements Runnable {
    private static final int RECORD_POINT_NUM = 10;
    private final LinkedList<String> MOVE_POINT = new LinkedList<>();
    private final WeakReference<BallMoveHelp> mHelp;
    private MotionEvent mMoveEvent;

    public PointRecordHelp(BallMoveHelp ballMoveHelp) {
        this.mHelp = new WeakReference<>(ballMoveHelp);
    }

    public void resetTrack() {
        this.MOVE_POINT.clear();
    }

    public void setMotionEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.mMoveEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.mMoveEvent = motionEvent;
    }

    public LinkedList<String> getTrack() {
        return this.MOVE_POINT;
    }

    @Override // java.lang.Runnable
    public void run() {
        BallMoveHelp ballMoveHelp = this.mHelp.get();
        if (ballMoveHelp == null) {
            return;
        }
        ballMoveHelp.getTrackHandler().postDelayed(this, 16L);
        MotionEvent motionEvent = this.mMoveEvent;
        if (motionEvent == null || 2 != motionEvent.getAction()) {
            return;
        }
        this.MOVE_POINT.add(((int) this.mMoveEvent.getRawX()) + "|" + ((int) this.mMoveEvent.getRawY()));
        if (this.MOVE_POINT.size() >= 10) {
            this.MOVE_POINT.removeFirst();
        }
    }
}
