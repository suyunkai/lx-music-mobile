package com.wanos.media;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.wanos.media.databinding.WanosAppWidgetConfigureBinding;

/* JADX INFO: loaded from: classes3.dex */
public class WanosAppWidgetConfigureActivity extends Activity {
    private static final String PREFS_NAME = "com.wanos.media.WanosAppWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    private WanosAppWidgetConfigureBinding binding;
    EditText mAppWidgetText;
    int mAppWidgetId = 0;
    View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.wanos.media.WanosAppWidgetConfigureActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            WanosAppWidgetConfigureActivity wanosAppWidgetConfigureActivity = WanosAppWidgetConfigureActivity.this;
            WanosAppWidgetConfigureActivity.saveTitlePref(wanosAppWidgetConfigureActivity, WanosAppWidgetConfigureActivity.this.mAppWidgetId, wanosAppWidgetConfigureActivity.mAppWidgetText.getText().toString());
            AppWidgetManager.getInstance(wanosAppWidgetConfigureActivity);
            Intent intent = new Intent();
            intent.putExtra("appWidgetId", WanosAppWidgetConfigureActivity.this.mAppWidgetId);
            WanosAppWidgetConfigureActivity.this.setResult(-1, intent);
            WanosAppWidgetConfigureActivity.this.finish();
        }
    };

    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putString(PREF_PREFIX_KEY + appWidgetId, text);
        editorEdit.apply();
    }

    static String loadTitlePref(Context context, int appWidgetId) {
        String string = context.getSharedPreferences(PREFS_NAME, 0).getString(PREF_PREFIX_KEY + appWidgetId, null);
        return string != null ? string : context.getString(R.string.appwidget_text);
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.remove(PREF_PREFIX_KEY + appWidgetId);
        editorEdit.apply();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setResult(0);
        WanosAppWidgetConfigureBinding wanosAppWidgetConfigureBindingInflate = WanosAppWidgetConfigureBinding.inflate(getLayoutInflater());
        this.binding = wanosAppWidgetConfigureBindingInflate;
        setContentView(wanosAppWidgetConfigureBindingInflate.getRoot());
        this.mAppWidgetText = this.binding.appwidgetText;
        this.binding.addButton.setOnClickListener(this.mOnClickListener);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mAppWidgetId = extras.getInt("appWidgetId", 0);
        }
        int i = this.mAppWidgetId;
        if (i == 0) {
            finish();
        } else {
            this.mAppWidgetText.setText(loadTitlePref(this, i));
        }
    }
}
