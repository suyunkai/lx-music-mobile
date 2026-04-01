package com.wanos.WanosCommunication;

import android.content.Context;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.SignUtilsEntity;
import com.wanos.WanosCommunication.service.ExceptionHandle;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Headers;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ResponseCallBack<T extends BaseResponse> implements Callback<T> {
    private static final String TAG = "wanos:[ResponseCallBack]";
    private final Context context;
    private long udid;

    public abstract void onResponseFailure(int i, String str);

    public abstract void onResponseSuccessful(T t);

    public ResponseCallBack(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.context = context;
        this.udid = jCurrentTimeMillis + 1;
    }

    public long getUdid() {
        return this.udid;
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<T> call, Response<T> response) {
        Date date;
        T tBody = response.body();
        if (tBody != null) {
            if (tBody.code == 0) {
                onResponseSuccessful(tBody);
            } else {
                onResponseFailure(tBody.code, formatErrorResult(this.context, tBody.code, tBody.msg));
            }
        } else {
            Log.d(TAG, "on Resp Failure : -1:请求失败");
            onResponseFailure(-1, "请求失败");
        }
        Headers headers = response.headers();
        if (headers == null || (date = headers.getDate("date")) == null) {
            return;
        }
        SystemAndServerTimeSynchUtil.systemAndServerTimeCz = System.currentTimeMillis() - date.getTime();
        Log.d(TAG, "onResponse time : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<T> call, Throwable th) {
        ExceptionHandle.ResponseException responseExceptionHandleException = ExceptionHandle.handleException(th);
        onResponseFailure(responseExceptionHandleException.code, responseExceptionHandleException.message);
        Log.d(TAG, "onFailure : " + responseExceptionHandleException.code + ":" + responseExceptionHandleException.message);
    }

    public static String formatErrorResult(Context context, int i, String str) {
        if (i != 0) {
            Log.i(TAG, "formatErrorResult: code = " + i + ", msg = " + str);
        }
        if (i == 20000 || i == 20001 || i == 20005) {
            UserInfoUtil.logout();
            EventBus.getDefault().post(new LoginOrLogoutEvent(false));
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).showLoginDialog();
            }
            Log.i(TAG, "formatErrorResult: 账号异常，请重新登录");
            return "账号异常，请重新登录";
        }
        if (i != 400 || ((StringUtils.isEmpty(str) || !str.contains("鉴权")) && (StringUtils.isEmpty(str) || !str.contains("已过期")))) {
            return i == 0 ? "" : str;
        }
        SPUtils.getInstance(SignUtilsEntity.SP_NAME).remove(SignUtilsEntity.KEY_DEVICE_TOKEN);
        SignUtilsEntity.getInstance().setDeviceTokenEntity(null);
        Log.i(TAG, "formatErrorResult: Token信息异常，请重试~");
        return "Token信息异常，请重试~";
    }
}
