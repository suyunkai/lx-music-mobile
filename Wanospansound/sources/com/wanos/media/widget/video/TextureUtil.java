package com.wanos.media.widget.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.wanos.media.util.ZeroLogcatTools;

/* JADX INFO: loaded from: classes3.dex */
public class TextureUtil {
    private static final String TAG = "TextureHelper";

    public static int createOESTextureId() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return iArr[0];
    }

    public static TextureBean loadTexture(Context context, int i) {
        TextureBean textureBean = new TextureBean();
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        if (iArr[0] == 0) {
            ZeroLogcatTools.d(TAG, "Could not generate a new OpenGL texture object.");
            return textureBean;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i, options);
        if (bitmapDecodeResource == null) {
            ZeroLogcatTools.d(TAG, "Resource ID " + i + " could not be decoded.");
            GLES20.glDeleteTextures(1, iArr, 0);
            return textureBean;
        }
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameteri(3553, 10241, 9987);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLUtils.texImage2D(3553, 0, bitmapDecodeResource, 0);
        GLES20.glGenerateMipmap(3553);
        textureBean.setWidth(bitmapDecodeResource.getWidth());
        textureBean.setHeight(bitmapDecodeResource.getHeight());
        bitmapDecodeResource.recycle();
        GLES20.glBindTexture(3553, 0);
        textureBean.setTextureId(iArr[0]);
        return textureBean;
    }

    public static class TextureBean {
        private int mHeight;
        private int mTextureId;
        private int mWidth;

        public int getTextureId() {
            return this.mTextureId;
        }

        void setTextureId(int i) {
            this.mTextureId = i;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public void setWidth(int i) {
            this.mWidth = i;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public void setHeight(int i) {
            this.mHeight = i;
        }
    }
}
