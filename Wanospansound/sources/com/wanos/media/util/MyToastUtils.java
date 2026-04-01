package com.wanos.media.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.media3.exoplayer.DefaultLoadControl;
import com.loopj.android.http.AsyncHttpClient;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import com.wanos.media.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class MyToastUtils {
    public static final int LENGTH_LONG = 3;
    public static final int LENGTH_LONGLONG = 4;
    public static final int LENGTH_SHORT = 2;
    public static final int LENGTH_SHORTSHORT = 1;
    private static int LayoutParamsGravity = 48;
    private static int LayoutParamsX = 0;
    private static int LayoutParamsY = 120;
    private static int Radius = 15;
    public static final String TAG = "wanos:[MyToastUtils]";
    private static String Text = "";
    private static float TextSize = 28.0f;
    private int BackGroundColor;
    int DelayMillis_sh;
    private final int HIDE;
    private int TextColor;
    volatile boolean isViewAdded;
    private Handler mHandler;
    private WindowManager.LayoutParams mLayoutParams;
    private TextView mView;
    private WindowManager mWindowManager;

    public MyToastUtils BackGroundColor(String backgroundcolor) {
        return this;
    }

    public MyToastUtils TextColor(String color) {
        return this;
    }

    private MyToastUtils() {
        this.HIDE = UUID.randomUUID().hashCode();
        this.TextColor = R.color.toast_text_color;
        this.BackGroundColor = R.drawable.toast_bg;
        this.DelayMillis_sh = 2000;
        this.mHandler = new Handler() { // from class: com.wanos.media.util.MyToastUtils.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == MyToastUtils.this.HIDE) {
                    MyToastUtils.this.hideView();
                }
            }
        };
        this.isViewAdded = false;
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static MyToastUtils make() {
        Activity activityCurrentActivity = AppManager.getAppManager().currentActivity();
        if (activityCurrentActivity == null) {
            return new MyToastUtils(getApplication());
        }
        return new MyToastUtils(activityCurrentActivity);
    }

    public MyToastUtils Text(String toastString) {
        Text = toastString;
        return this;
    }

    public MyToastUtils TextSize(int size) {
        if (size != 0) {
            TextSize = size;
        }
        return this;
    }

    public MyToastUtils TextColor(int color) {
        if (color != 0) {
            this.TextColor = color;
        }
        return this;
    }

    public MyToastUtils RoundedCorners(int radius) {
        Radius = radius;
        return this;
    }

    public MyToastUtils BackGroundColor(int backgroundcolor) {
        if (backgroundcolor != 0) {
            this.BackGroundColor = backgroundcolor;
        }
        return this;
    }

    public MyToastUtils LayoutParamsX(int x) {
        if (x != 0) {
            LayoutParamsX = x;
        }
        return this;
    }

    public MyToastUtils LayoutParamsY(int y) {
        if (y != 0) {
            LayoutParamsY = y;
        }
        return this;
    }

    public MyToastUtils Gravity(int gravity) {
        if (gravity != 0) {
            LayoutParamsGravity = gravity;
        }
        return this;
    }

    public MyToastUtils ShowTime(int i) {
        if (i != 0) {
            if (i == 1) {
                this.DelayMillis_sh = AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS;
            } else if (i == 2) {
                this.DelayMillis_sh = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
            } else if (i == 3) {
                this.DelayMillis_sh = 3500;
            } else if (i == 4) {
                this.DelayMillis_sh = 4500;
            }
        }
        return this;
    }

    private MyToastUtils(Context context) {
        this.HIDE = UUID.randomUUID().hashCode();
        this.TextColor = R.color.toast_text_color;
        this.BackGroundColor = R.drawable.toast_bg;
        this.DelayMillis_sh = 2000;
        this.mHandler = new Handler() { // from class: com.wanos.media.util.MyToastUtils.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == MyToastUtils.this.HIDE) {
                    MyToastUtils.this.hideView();
                }
            }
        };
        this.isViewAdded = false;
        this.mView = new TextView(context);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mLayoutParams = new WindowManager.LayoutParams(-2, -2, 0, 0, 2002, 56, 1);
        this.mLayoutParams.type = 2038;
    }

    public void show() {
        try {
            TextView textView = this.mView;
            if (textView != null) {
                textView.setText(Text);
                this.mView.setTextColor(getApplication().getColor(this.TextColor));
                this.mView.setPadding(40, 24, 40, 24);
                this.mView.setBackground(getApplication().getDrawable(this.BackGroundColor));
                this.mView.setTextSize(TextSize);
            }
            this.mLayoutParams.gravity = LayoutParamsGravity;
            this.mLayoutParams.x = LayoutParamsX;
            this.mLayoutParams.y = LayoutParamsY;
            showView();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "show: 出现异常，e = " + e.getMessage());
        }
    }

    private void showView() {
        if (this.mView.getParent() == null && !this.isViewAdded) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            Log.i(TAG, "showView:  addView");
        }
        if (!this.isViewAdded) {
            this.mHandler.removeMessages(this.HIDE);
            this.mHandler.sendEmptyMessageDelayed(this.HIDE, this.DelayMillis_sh);
            this.isViewAdded = true;
            Log.i(TAG, "showView: isViewAdded = true,hide =" + this.HIDE);
            return;
        }
        Log.i(TAG, "showView:  isViewAdded = false");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideView() {
        if (this.mView.getParent() != null) {
            this.mWindowManager.removeView(this.mView);
            Log.i(TAG, "hideView:  removeView");
        }
        this.isViewAdded = false;
        Log.i(TAG, "hideView:  isViewAdded = false");
    }

    public static Application getApplication() {
        Application application;
        try {
            Method declaredMethod = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.CURRENT_APPLICATION, new Class[0]);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            application = null;
        }
        if (application != null) {
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.GET_INITIAL_APPLICATION, new Class[0]);
            declaredMethod2.setAccessible(true);
            return (Application) declaredMethod2.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
            return application;
        }
    }
}
