package com.wanos.WanosCommunication;

import android.util.Log;
import com.wanos.WanosCommunication.service.ExceptionHandle;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes3.dex */
public class UnknownErrorRetryInterceptor implements Interceptor {
    public static final String TAG = "wanos:[UnknownErrorRetryInterceptor]";
    private final int maxRetryCount;

    public UnknownErrorRetryInterceptor(int i) {
        this.maxRetryCount = i;
    }

    private void waitForRetry(int i) {
        try {
            long jPow = ((long) Math.pow(2.0d, i)) * 500;
            Log.i(TAG, "waitForRetry: Waiting for " + jPow + "ms");
            Thread.sleep(jPow);
        } catch (InterruptedException e) {
            Log.i(TAG, "waitForRetry: InterruptedException");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response responseProceed = null;
        IOException e = null;
        for (int i = 1; i <= this.maxRetryCount; i++) {
            if (i > 1) {
                try {
                    Log.d(TAG, "Retrying request... Attempt: " + i + ", URL: " + request.url().encodedPath());
                    waitForRetry(i);
                } catch (IOException e2) {
                    e = e2;
                    ExceptionHandle.ResponseException responseExceptionHandleException = ExceptionHandle.handleException(e);
                    if (responseExceptionHandleException.code != 1000) {
                        throw e;
                    }
                    if (responseExceptionHandleException.getCause() instanceof SocketTimeoutException) {
                        Log.i(TAG, "intercept: 超时了, preparing to retry...");
                        Log.e(TAG, "IOException detected, retrying request... Attempt: " + i, e);
                    } else {
                        Log.i(TAG, "intercept: 未知 Unknown error detected, ");
                        throw e;
                    }
                }
            }
            responseProceed = chain.proceed(request);
            if (responseProceed.getIsSuccessful()) {
                return responseProceed;
            }
            ExceptionHandle.ResponseException responseExceptionHandleException2 = ExceptionHandle.handleException(new HttpException(toRetrofitResponse(responseProceed)));
            if (responseExceptionHandleException2.code != 1000) {
                return responseProceed;
            }
            if (responseExceptionHandleException2.getCause() instanceof SocketTimeoutException) {
                Log.i(TAG, "intercept: SocketTimeoutException, preparing to retry...超时了。。。");
                Log.w(TAG, "Unknown error detected, preparing to retry...");
            } else {
                Log.i(TAG, "intercept: 未知 Unknown error detected, preparing to retry...其他未知错误:" + responseExceptionHandleException2.getMessage());
                return responseProceed;
            }
        }
        if (responseProceed != null) {
            Log.i(TAG, "intercept: 返回最后一次的响应");
            return responseProceed;
        }
        if (e != null) {
            Log.i(TAG, "intercept: 抛出最后一次的异常");
            throw e;
        }
        Log.i(TAG, "intercept: 未知错误且无响应");
        throw new IOException("Unknown error and no response received.");
    }

    private retrofit2.Response<?> toRetrofitResponse(Response response) throws IOException {
        return retrofit2.Response.error(response.code(), response.body() != null ? ResponseBody.create(response.body().getMediaType(), response.body().bytes()) : null);
    }
}
