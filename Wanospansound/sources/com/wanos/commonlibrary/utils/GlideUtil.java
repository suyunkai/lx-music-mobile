package com.wanos.commonlibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wanos.commonlibrary.R;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.glide.RightCropTransformation;
import com.wanos.commonlibrary.utils.GlideOptions;
import jp.wasabeef.glide.transformations.BitmapTransformation;

/* JADX INFO: loaded from: classes3.dex */
public class GlideUtil {
    private static boolean isSkipMemoryCache() {
        return false;
    }

    public static void setPreloadData(Context context, String str, SizeMode sizeMode, GlideOptions.OnLoadListener onLoadListener) {
        new GlideOptions().setSize(sizeMode.getWidth(), sizeMode.getHeight()).setCallback(onLoadListener).onPreload(context, str);
    }

    public static void setImageData(String str, ImageView imageView, int i, int i2) {
        new GlideOptions().setSize(i, i2).onLoad(str, imageView);
    }

    public static void setImageData(SizeMode sizeMode, String str, ImageView imageView, int i) {
        new GlideOptions().setSize(sizeMode.getWidth(), sizeMode.getHeight()).setPlaceholder(i).setError(i).onLoad(str, imageView);
    }

    public static void setImageData(String str, ImageView imageView) {
        new GlideOptions().onLoad(str, imageView);
    }

    public static void setImageData(SizeMode sizeMode, String str, ImageView imageView) {
        new GlideOptions().setSize(sizeMode.getWidth(), sizeMode.getHeight()).onLoad(str, imageView);
    }

    public static void setHomeImageData(SizeMode sizeMode, String str, ImageView imageView) {
        new GlideOptions().setPlaceholder(R.drawable.ic_home_loading).setError(R.drawable.ic_home_loading).setSize(sizeMode.getWidth(), sizeMode.getHeight()).onLoad(str, imageView);
    }

    public static void loadAdImage(Object obj, final ImageView imageView, final ImageView imageView2) {
        if (obj instanceof String) {
            Glide.with(imageView.getContext()).load(obj).apply((BaseRequestOptions<?>) initOptions()).skipMemoryCache(isSkipMemoryCache()).diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomViewTarget<View, Drawable>(imageView) { // from class: com.wanos.commonlibrary.utils.GlideUtil.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.CustomViewTarget
                protected void onResourceCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj2, Transition transition) {
                    onResourceReady((Drawable) obj2, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    imageView2.setVisibility(0);
                    imageView.setImageDrawable(drawable);
                }
            });
        }
    }

    public static void setImageData(SizeMode sizeMode, String str, ImageView imageView, Priority priority) {
        new GlideOptions().setSize(sizeMode.getWidth(), sizeMode.getHeight()).setPriority(priority).onLoad(str, imageView);
    }

    public static void setRightCropImageData(SizeMode sizeMode, String str, ImageView imageView, Priority priority) {
        new GlideOptions().setSize(sizeMode.getWidth(), sizeMode.getHeight()).setPriority(priority).setTransformation(new RightCropTransformation()).onLoad(str, imageView);
    }

    public static void setImageData(String str, ImageView imageView, int i, int i2, int i3) {
        new GlideOptions().setSize(i, i2).setPlaceholder(i3).setError(i3).onLoad(str, imageView);
    }

    public static void setImageGifData(int i, ImageView imageView) {
        new GlideOptions().onLoadGif(i, imageView);
    }

    public static void loadImage(Object obj, ImageView imageView) {
        loadImage(obj, imageView, getPlaceholder(), getErrorImage());
    }

    public static void loadImage(Object obj, ImageView imageView, int i, int i2) {
        if (obj instanceof String) {
            Glide.with(imageView.getContext()).load(obj).thumbnail(0.2f).apply((BaseRequestOptions<?>) initOptions()).skipMemoryCache(isSkipMemoryCache()).diskCacheStrategy(DiskCacheStrategy.DATA).error(i2).fallback(i).placeholder(i).into(imageView);
        }
    }

    private static int getErrorImage() {
        return R.drawable.src_img_loading;
    }

    private static int getPlaceholder() {
        return R.drawable.src_img_loading;
    }

    private static RequestOptions initOptions(BitmapTransformation bitmapTransformation) {
        return new RequestOptions().transform(bitmapTransformation).skipMemoryCache(isSkipMemoryCache()).onlyRetrieveFromCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    private static RequestOptions initOptions() {
        return new RequestOptions().skipMemoryCache(isSkipMemoryCache()).onlyRetrieveFromCache(false).diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    private static RequestOptions bitmapTransform(BitmapTransformation bitmapTransformation) {
        return new RequestOptions();
    }

    public static void loadScaleWebP(String str, ImageView imageView) {
        loadImage(ImageUtils.getUrlPath(str), imageView);
    }
}
