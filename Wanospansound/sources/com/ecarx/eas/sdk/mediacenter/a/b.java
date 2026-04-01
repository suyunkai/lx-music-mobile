package com.ecarx.eas.sdk.mediacenter.a;

import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.sdk.radio.Band;
import com.ecarx.eas.sdk.vr.radio.ICtrlLocalRadioIntent;
import ecarx.xsf.mediacenter.vr.QRadioResult;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class b implements ICtrlLocalRadioIntent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f109a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private QRadioResult f110b;

    public b(QRadioResult qRadioResult) {
        this.f110b = qRadioResult;
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ICtrlLocalRadioIntent
    public String getRawText() {
        try {
            return this.f110b.rawText;
        } catch (Exception e) {
            Log.e(f109a, " getRawText error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public Band getBand() {
        if (this.f110b.sourceType == 3) {
            try {
                return Band.values()[0];
            } catch (Exception e) {
                Log.e(f109a, " getBand error : " + e.getMessage());
                return null;
            }
        }
        if (this.f110b.sourceType != 4) {
            return null;
        }
        try {
            return Band.values()[1];
        } catch (Exception e2) {
            Log.e(f109a, " getBand error : " + e2.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public int getFrequency() {
        try {
            if (this.f110b.sourceType == 3) {
                if (a(this.f110b.freq)) {
                    int iDoubleValue = (int) (Double.valueOf(this.f110b.freq).doubleValue() * 1000.0d);
                    Log.i(f109a, "getFrequency():" + iDoubleValue);
                    return iDoubleValue;
                }
            } else if (this.f110b.sourceType == 4 && TextUtils.isDigitsOnly(this.f110b.freq)) {
                int iIntValue = Integer.valueOf(this.f110b.freq).intValue();
                Log.i(f109a, "getFrequency():" + iIntValue);
                return iIntValue;
            }
        } catch (Exception e) {
            Log.e(f109a, " getFrequency error : " + e.getMessage());
        }
        return 0;
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public String getName() {
        try {
            return this.f110b.name;
        } catch (Exception e) {
            Log.e(f109a, " getNickName error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public String getCategory() {
        try {
            return this.f110b.category;
        } catch (Exception e) {
            Log.e(f109a, " getCategory error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public String getLocation() {
        try {
            return this.f110b.location;
        } catch (Exception e) {
            Log.e(f109a, " getLocation error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public int getMediaType() {
        try {
            return this.f110b.mediaType;
        } catch (Exception e) {
            Log.e(f109a, " getMediaType error : " + e.getMessage());
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public int getSourceType() {
        try {
            return this.f110b.sourceType;
        } catch (Exception e) {
            Log.e(f109a, " getSourceType error : " + e.getMessage());
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.radio.ILocalRadioIntent
    public int getTargetPlayType() {
        try {
            return this.f110b.targetPlayType;
        } catch (Exception e) {
            Log.e(f109a, " getTargetPlayType error : " + e.getMessage());
            return -1;
        }
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return Pattern.compile("^(\\+)?\\d+(\\.\\d+)?$").matcher(str).matches();
        } catch (Exception e) {
            Log.e(f109a, " checkFreq (" + str + ") error : " + e.getMessage());
            return false;
        }
    }
}
