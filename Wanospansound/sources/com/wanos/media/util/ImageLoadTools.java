package com.wanos.media.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;
import com.blankj.utilcode.util.Utils;
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
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public class ImageLoadTools {
    private static final float SIZE_SCALE_SIZE = 0.8f;
    private static final String TAG = "ImageLoadTools";

    public static class Builder {
        private int height;
        private int width;
        private Transformation<Bitmap> mTransformation = null;
        private int placeholder = R.drawable.src_img_loading;
        private int error = R.drawable.src_img_loading;
        private Priority mPriority = Priority.NORMAL;
        private boolean isFormatPath = true;
        private boolean isSkipCache = false;
        private boolean isCovertDimens = false;

        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        public Builder setSize(int i) {
            this.width = i;
            this.height = i;
            return this;
        }

        public Builder setTransformation(Transformation<Bitmap> transformation) {
            this.mTransformation = transformation;
            return this;
        }

        public Builder setPriority(Priority priority) {
            this.mPriority = priority;
            return this;
        }

        public Builder setFormatPath(boolean z) {
            this.isFormatPath = z;
            return this;
        }

        public Builder setSkipCache(boolean z) {
            this.isSkipCache = z;
            return this;
        }

        public Builder setCovertDimens(boolean z) {
            this.isCovertDimens = z;
            return this;
        }

        public Builder setPlaceholder(int i) {
            this.placeholder = i;
            return this;
        }

        public Builder setError(int i) {
            this.error = i;
            return this;
        }

        public void onLoad(Context context, String str, ImageView imageView) {
            if (imageView == null) {
                ZeroLogcatTools.d(ImageLoadTools.TAG, "Context or ImageView is Nullable");
                return;
            }
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing() || activity.isDestroyed()) {
                    ZeroLogcatTools.d(ImageLoadTools.TAG, "View Attach Activity is Destroyed");
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
            int size = ImageLoadTools.formatSize(this.width, this.isCovertDimens);
            int size2 = ImageLoadTools.formatSize(this.height, this.isCovertDimens);
            if (size > 0 && size2 > 0) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.override(size, size2);
            }
            Transformation<Bitmap> transformation = this.mTransformation;
            if (transformation != null) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.transform(transformation);
            }
            GlideApp.with(context).asBitmap().load(ImageLoadTools.formatImagePath(str, size, size2, this.isFormatPath)).apply((BaseRequestOptions<?>) requestOptionsOnlyRetrieveFromCache).format(DecodeFormat.PREFER_RGB_565).addListener(new RequestListener<Bitmap>() { // from class: com.wanos.media.util.ImageLoadTools.Builder.1
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                    return false;
                }
            }).into(imageView);
        }

        public Bitmap getBitmap(String str) {
            RequestOptions requestOptionsOnlyRetrieveFromCache = new RequestOptions().skipMemoryCache(this.isSkipCache).diskCacheStrategy(this.isSkipCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.RESOURCE).priority(this.mPriority).format(DecodeFormat.PREFER_RGB_565).onlyRetrieveFromCache(false);
            int i = this.placeholder;
            if (i != -1) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.placeholder(i);
            }
            int i2 = this.error;
            if (i2 != -1) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.error(i2);
            }
            int size = ImageLoadTools.formatSize(this.width, this.isCovertDimens);
            int size2 = ImageLoadTools.formatSize(this.height, this.isCovertDimens);
            if (size > 0 && size2 > 0) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.override(size, size2);
            }
            Transformation<Bitmap> transformation = this.mTransformation;
            if (transformation != null) {
                requestOptionsOnlyRetrieveFromCache = requestOptionsOnlyRetrieveFromCache.transform(transformation);
            }
            try {
                return GlideApp.with(Utils.getApp()).asBitmap().load(ImageLoadTools.formatImagePath(str, size, size2, this.isFormatPath)).apply((BaseRequestOptions<?>) requestOptionsOnlyRetrieveFromCache).submit().get();
            } catch (InterruptedException | ExecutionException e) {
                ZeroLogcatTools.e(ImageLoadTools.TAG, "getBitmap: " + e.getMessage());
                return null;
            }
        }
    }

    public static int formatSize(int i, boolean z) {
        if (i <= 0) {
            return Integer.MIN_VALUE;
        }
        return (int) ((z ? TypedValue.applyDimension(1, i, Utils.getApp().getResources().getDisplayMetrics()) : i) * SIZE_SCALE_SIZE);
    }

    public static String formatImagePath(String str, int i, int i2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if ((str.startsWith("https") || str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) && z && i > 0 && i2 > 0) {
            return str.trim() + "?imageMogr2/format/png/thumbnail/" + i + "x" + i2;
        }
        return str.trim();
    }
}
