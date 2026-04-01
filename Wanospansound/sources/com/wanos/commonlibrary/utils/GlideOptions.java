package com.wanos.commonlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wanos.commonlibrary.GlideApp;
import com.wanos.commonlibrary.R;
import cz.msebera.android.httpclient.HttpHost;

/* JADX INFO: loaded from: classes3.dex */
public class GlideOptions {
    private static final String TAG = "wanos[GlideOptions]";
    private int height;
    private OnLoadListener mOnLoadListener;
    private int width;
    private Transformation<Bitmap> mTransformation = null;
    private int placeholder = R.drawable.src_img_loading;
    private int error = R.drawable.src_img_loading;
    private Priority mPriority = Priority.NORMAL;
    private boolean isFormatPath = true;
    private boolean isSkipCache = false;
    private boolean isCovertDimens = false;

    public interface OnLoadListener {
        default void onLoadError(String str) {
        }

        void onLoadSuccess(Bitmap bitmap);
    }

    public GlideOptions setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        return this;
    }

    public GlideOptions setCallback(OnLoadListener onLoadListener) {
        this.mOnLoadListener = onLoadListener;
        return this;
    }

    public GlideOptions setSize(int i) {
        this.width = i;
        this.height = i;
        return this;
    }

    public GlideOptions setTransformation(Transformation<Bitmap> transformation) {
        this.mTransformation = transformation;
        return this;
    }

    public GlideOptions setPriority(Priority priority) {
        this.mPriority = priority;
        return this;
    }

    public GlideOptions setFormatPath(boolean z) {
        this.isFormatPath = z;
        return this;
    }

    public GlideOptions setSkipCache(boolean z) {
        this.isSkipCache = z;
        return this;
    }

    public GlideOptions setCovertDimens(boolean z) {
        this.isCovertDimens = z;
        return this;
    }

    public GlideOptions setPlaceholder(int i) {
        this.placeholder = i;
        return this;
    }

    public GlideOptions setError(int i) {
        this.error = i;
        return this;
    }

    public void onLoadGif(int i, ImageView imageView) {
        if (imageView == null) {
            Log.d(TAG, "ImageView is Nullable");
            return;
        }
        Context context = imageView.getContext();
        if (i == -1) {
            Log.d(TAG, "Gif Resources id Error");
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed()) {
                Log.d(TAG, "View Attach Activity is Destroyed");
                return;
            }
        }
        GlideApp.with(context).asGif().load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(this.isSkipCache).diskCacheStrategy(this.isSkipCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.RESOURCE).priority(this.mPriority).format(DecodeFormat.PREFER_RGB_565).onlyRetrieveFromCache(false)).into(imageView);
    }

    public void onLoadGif(String str, ImageView imageView) {
        if (imageView == null) {
            Log.d(TAG, "ImageView is Nullable");
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed()) {
                Log.d(TAG, "View Attach Activity is Destroyed");
                return;
            }
        }
        GlideApp.with(context).asGif().load(str).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(this.isSkipCache).diskCacheStrategy(this.isSkipCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.RESOURCE).priority(this.mPriority).format(DecodeFormat.PREFER_RGB_565).onlyRetrieveFromCache(false)).into(imageView);
    }

    public void onLoad(String str, ImageView imageView) {
        if (imageView == null) {
            Log.d(TAG, "Context or ImageView is Nullable");
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed()) {
                Log.d(TAG, "View Attach Activity is Destroyed");
                return;
            }
        }
        RequestOptions requestOptionsOnlyRetrieveFromCache = new RequestOptions().skipMemoryCache(this.isSkipCache).diskCacheStrategy(this.isSkipCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.RESOURCE).priority(this.mPriority).format(DecodeFormat.PREFER_RGB_565).onlyRetrieveFromCache(false);
        int i = this.placeholder;
        if (i != -1) {
            requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.placeholder(i);
        }
        int i2 = this.error;
        if (i2 != -1) {
            requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.error(i2);
        }
        int size = formatSize(context, this.width, this.isCovertDimens);
        int size2 = formatSize(context, this.height, this.isCovertDimens);
        if (size > 0 && size2 > 0) {
            requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.override(size, size2);
        }
        Transformation<Bitmap> transformation = this.mTransformation;
        if (transformation != null) {
            requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.transform(transformation);
        }
        GlideApp.with(context).asBitmap().load(formatImagePath(str, size, size2, this.isFormatPath)).apply((BaseRequestOptions<?>) requestOptionsOnlyRetrieveFromCache).listener(new RequestListener<Bitmap>() { // from class: com.wanos.commonlibrary.utils.GlideOptions.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                if (GlideOptions.this.mOnLoadListener == null) {
                    return false;
                }
                GlideOptions.this.mOnLoadListener.onLoadError(glideException == null ? "Glide Load Error" : glideException.getMessage());
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                if (GlideOptions.this.mOnLoadListener == null) {
                    return false;
                }
                GlideOptions.this.mOnLoadListener.onLoadSuccess(bitmap);
                return false;
            }
        }).into(imageView);
    }

    public void onPreload(Context context, String str) {
        if (context == null) {
            Log.d(TAG, "Context or ImageView is Nullable");
            return;
        }
        RequestOptions requestOptionsOnlyRetrieveFromCache = new RequestOptions().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).onlyRetrieveFromCache(false);
        Transformation<Bitmap> transformation = this.mTransformation;
        if (transformation != null) {
            requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.transform(transformation);
        }
        int size = formatSize(context, this.width, this.isCovertDimens);
        int size2 = formatSize(context, this.height, this.isCovertDimens);
        GlideApp.with(context).asBitmap().load(formatImagePath(str, size, size2, this.isFormatPath).trim()).apply((BaseRequestOptions<?>) requestOptionsOnlyRetrieveFromCache).listener(new RequestListener<Bitmap>() { // from class: com.wanos.commonlibrary.utils.GlideOptions.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                if (GlideOptions.this.mOnLoadListener == null) {
                    return false;
                }
                GlideOptions.this.mOnLoadListener.onLoadError(glideException == null ? "Glide Preload Error" : glideException.getMessage());
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                if (GlideOptions.this.mOnLoadListener == null) {
                    return false;
                }
                GlideOptions.this.mOnLoadListener.onLoadSuccess(bitmap);
                return false;
            }
        }).preload(size, size2);
    }

    private int formatSize(Context context, int i, boolean z) {
        if (i <= 0) {
            return Integer.MIN_VALUE;
        }
        return z ? (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics()) : i;
    }

    private String formatImagePath(String str, int i, int i2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if ((str.startsWith("https") || str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) && z && i > 0 && i2 > 0) {
            return str.trim();
        }
        return str.trim();
    }

    public static String formatImagePaths(String str, int i, int i2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if ((str.startsWith("https") || str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) && z && i > 0 && i2 > 0) {
            return str.trim();
        }
        return str.trim();
    }
}
