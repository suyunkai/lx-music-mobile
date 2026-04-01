package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class LongClickImageView extends AppCompatImageView {
    private MyHandler handler;
    private long intervalTime;
    private LongClickRepeatListener repeatListener;

    public interface LongClickRepeatListener {
        void repeatAction(View view);
    }

    public LongClickImageView(Context context) {
        super(context);
        init();
    }

    public LongClickImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LongClickImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.handler = new MyHandler(this);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wanos.careditproject.view.widget.LongClickImageView.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                new Thread(new LongClickThread()).start();
                return true;
            }
        });
    }

    private class LongClickThread implements Runnable {
        private int num;

        private LongClickThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (LongClickImageView.this.isPressed()) {
                int i = this.num + 1;
                this.num = i;
                if (i % 5 == 0) {
                    LongClickImageView.this.handler.sendEmptyMessage(1);
                }
                SystemClock.sleep(LongClickImageView.this.intervalTime / 5);
            }
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<LongClickImageView> ref;

        MyHandler(LongClickImageView longClickImageView) {
            this.ref = new WeakReference<>(longClickImageView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LongClickImageView longClickImageView = this.ref.get();
            if (longClickImageView == null || longClickImageView.repeatListener == null) {
                return;
            }
            longClickImageView.repeatListener.repeatAction(longClickImageView);
        }
    }

    public void setLongClickRepeatListener(LongClickRepeatListener longClickRepeatListener, long j) {
        this.repeatListener = longClickRepeatListener;
        this.intervalTime = j;
    }

    public void setLongClickRepeatListener(LongClickRepeatListener longClickRepeatListener) {
        setLongClickRepeatListener(longClickRepeatListener, 100L);
    }
}
