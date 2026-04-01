package com.wanos.media.util;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.wanos.media.MainApplication;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class DownTimer extends CountDownTimer {
    public TextView mTextView;
    private long remainingSeconds;

    public DownTimer(long millisInFuture, long countDownInterval, TextView mTextView) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long millisUntilFinished) {
        long j = millisUntilFinished / 1000;
        this.remainingSeconds = j;
        this.mTextView.setText(j + "秒后可重发");
        this.mTextView.setTextColor(MainApplication.getInstance().getResources().getColor(R.color.p_title_unselected, null));
        setRegisterCodeTextViewForEnabled(false);
    }

    public long getRemainingSeconds() {
        return this.remainingSeconds;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.mTextView.setTextColor(MainApplication.getInstance().getResources().getColor(R.color.p_login_get_sms_code, null));
        this.mTextView.setText(R.string.get_sms_code);
        setRegisterCodeTextViewForEnabled(true);
    }

    public void setRegisterCodeTextViewForEnabled(boolean isEnabled) {
        this.mTextView.setEnabled(isEnabled);
    }

    public void onCancel() {
        cancel();
        this.mTextView.setTextColor(MainApplication.getInstance().getResources().getColor(R.color.p_login_get_sms_code, null));
        this.mTextView.setText(R.string.get_sms_code);
        setRegisterCodeTextViewForEnabled(true);
    }
}
