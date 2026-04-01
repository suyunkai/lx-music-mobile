package com.wanos.media.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.icu.text.DateTimePatternGenerator;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.wanos.media.zero_p.R;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class WanosClockView extends LinearLayoutCompat {
    private CharSequence mFormat;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private ContentObserver mFormatChangeObserver;
    private boolean mHasSeconds;
    private final WanosTextView mHourView;
    private final BroadcastReceiver mIntentReceiver;
    private final WanosTextView mMinuteView;
    private boolean mRegistered;
    private boolean mShouldRunTicker;
    private final Runnable mTicker;
    private Calendar mTime;

    private static CharSequence abc(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return charSequence == null ? charSequence2 == null ? charSequence3 : charSequence2 : charSequence;
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            WanosClockView.this.chooseFormat();
            WanosClockView.this.onTimeChanged();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            WanosClockView.this.chooseFormat();
            WanosClockView.this.onTimeChanged();
        }
    }

    public WanosClockView(Context context) {
        this(context, null);
    }

    public WanosClockView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WanosClockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIntentReceiver = new BroadcastReceiver() { // from class: com.wanos.media.widget.WanosClockView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (!"android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                    if (!WanosClockView.this.mShouldRunTicker && ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()))) {
                        return;
                    }
                } else if (Build.VERSION.SDK_INT < 30) {
                    WanosClockView.this.createTime(null);
                } else {
                    WanosClockView.this.createTime(intent.getStringExtra("time-zone"));
                }
                WanosClockView.this.onTimeChanged();
            }
        };
        this.mTicker = new Runnable() { // from class: com.wanos.media.widget.WanosClockView.2
            @Override // java.lang.Runnable
            public void run() {
                ZonedDateTime zonedDateTimeWithNano;
                WanosClockView.this.removeCallbacks(this);
                if (WanosClockView.this.mShouldRunTicker) {
                    WanosClockView.this.onTimeChanged();
                    Instant instant = WanosClockView.this.mTime.toInstant();
                    ZoneId zoneId = WanosClockView.this.mTime.getTimeZone().toZoneId();
                    if (WanosClockView.this.mHasSeconds) {
                        zonedDateTimeWithNano = instant.atZone(zoneId).plusSeconds(1L).withNano(0);
                    } else {
                        zonedDateTimeWithNano = instant.atZone(zoneId).plusMinutes(1L).withSecond(0).withNano(0);
                    }
                    long millis = Duration.between(instant, zonedDateTimeWithNano.toInstant()).toMillis();
                    if (millis <= 0) {
                        millis = 1000;
                    }
                    WanosClockView.this.postDelayed(this, millis);
                }
            }
        };
        setGravity(17);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.widget_clock, (ViewGroup) this, true);
        this.mHourView = (WanosTextView) findViewById(R.id.tv_hour);
        this.mMinuteView = (WanosTextView) findViewById(R.id.tv_minute);
        if (this.mFormat12 == null) {
            this.mFormat12 = getBestDateTimePattern("hm");
        }
        if (this.mFormat24 == null) {
            this.mFormat24 = getBestDateTimePattern("Hm");
        }
        createTime(null);
        chooseFormat();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRegistered) {
            return;
        }
        this.mRegistered = true;
        registerReceiver();
        registerObserver();
        createTime(null);
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        boolean z2 = this.mShouldRunTicker;
        if (!z2 && z) {
            this.mShouldRunTicker = true;
            this.mTicker.run();
        } else {
            if (!z2 || z) {
                return;
            }
            this.mShouldRunTicker = false;
            removeCallbacks(this.mTicker);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRegistered) {
            unregisterReceiver();
            unregisterObserver();
            this.mRegistered = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createTime(String str) {
        if (str != null) {
            this.mTime = Calendar.getInstance(TimeZone.getTimeZone(str));
        } else {
            this.mTime = Calendar.getInstance();
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        getContext().registerReceiver(this.mIntentReceiver, intentFilter, null, getHandler());
    }

    private void registerObserver() {
        if (this.mRegistered) {
            if (this.mFormatChangeObserver == null) {
                this.mFormatChangeObserver = new FormatChangeObserver(getHandler());
            }
            getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("time_12_24"), true, this.mFormatChangeObserver);
        }
    }

    private void unregisterReceiver() {
        getContext().unregisterReceiver(this.mIntentReceiver);
    }

    private void unregisterObserver() {
        if (this.mFormatChangeObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mFormatChangeObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeChanged() {
        this.mTime.setTimeInMillis(System.currentTimeMillis());
        if (8 == getVisibility() || 4 == getVisibility()) {
            return;
        }
        String[] strArrSplit = DateFormat.format(this.mFormat, this.mTime).toString().split(":");
        if (strArrSplit.length >= 2) {
            this.mHourView.setText(strArrSplit[0]);
            this.mMinuteView.setText(strArrSplit[1]);
        } else {
            this.mHourView.setText("");
            this.mMinuteView.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chooseFormat() {
        CharSequence charSequenceAbc = abc(this.mFormat24, this.mFormat12, getBestDateTimePattern("Hm"));
        this.mFormat = charSequenceAbc;
        boolean z = this.mHasSeconds;
        boolean zHasSeconds = hasSeconds(charSequenceAbc);
        this.mHasSeconds = zHasSeconds;
        if (!this.mShouldRunTicker || z == zHasSeconds) {
            return;
        }
        this.mTicker.run();
    }

    private String getBestDateTimePattern(String str) {
        DateTimePatternGenerator dateTimePatternGenerator;
        LocaleList locales = getContext().getResources().getConfiguration().getLocales();
        if (locales.isEmpty()) {
            dateTimePatternGenerator = DateTimePatternGenerator.getInstance(getContext().getResources().getConfiguration().locale);
        } else {
            dateTimePatternGenerator = DateTimePatternGenerator.getInstance(locales.get(0));
        }
        return dateTimePatternGenerator.getBestPattern(str);
    }

    private boolean hasSeconds(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '\'') {
                z = !z;
            } else if (!z && cCharAt == 's') {
                return true;
            }
        }
        return false;
    }
}
