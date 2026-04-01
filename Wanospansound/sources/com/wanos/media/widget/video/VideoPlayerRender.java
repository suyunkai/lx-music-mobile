package com.wanos.media.widget.video;

import android.graphics.SurfaceTexture;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.zero_p.R;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPlayerRender extends BaseGLSurfaceViewRenderer {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final String TAG = "VideoPlayerRender";
    private static final int TEXTURE_COMPONENT_COUNT = 2;
    private final GLSurfaceView mGLSurfaceView;
    private boolean mNeedUpdateSize;
    private int mSurfaceHeight;
    private SurfaceTexture mSurfaceTexture;
    private int mSurfaceWidth;
    private int mTextureId;
    private IVideoTextureRenderListener mTextureRenderListener;
    private int mVideoHeight;
    private int mVideoWidth;
    private int samplerTexturePosition;
    private int texturePosition;
    private int vertexPosition;
    private static final float[] POINT_DATA = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] TEXTURE_DATA = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private final FloatBuffer mVertextBuffer = BufferUtil.getFloatBuffer(POINT_DATA);
    private final FloatBuffer mTextureBuffer = BufferUtil.getFloatBuffer(TEXTURE_DATA);

    public interface IVideoTextureRenderListener {
        void onCreate(SurfaceTexture surfaceTexture);
    }

    public VideoPlayerRender(GLSurfaceView gLSurfaceView) {
        this.mGLSurfaceView = gLSurfaceView;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        handleProgram(Utils.getApp(), R.raw.video_vertex_shader, R.raw.video_fragment_shader);
        this.vertexPosition = glGetAttribLocation("vPosition");
        this.texturePosition = glGetAttribLocation("vCoordPosition");
        this.samplerTexturePosition = glGetUniformLocation("uSamplerTexture");
        this.mTextureId = TextureUtil.createOESTextureId();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureId);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setDefaultBufferSize(this.mGLSurfaceView.getWidth(), this.mGLSurfaceView.getHeight());
        this.mSurfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: com.wanos.media.widget.video.VideoPlayerRender$$ExternalSyntheticLambda0
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                this.f$0.m658xbc8b37b2(surfaceTexture2);
            }
        });
        IVideoTextureRenderListener iVideoTextureRenderListener = this.mTextureRenderListener;
        if (iVideoTextureRenderListener != null) {
            iVideoTextureRenderListener.onCreate(this.mSurfaceTexture);
        }
    }

    /* JADX INFO: renamed from: lambda$onSurfaceCreated$0$com-wanos-media-widget-video-VideoPlayerRender, reason: not valid java name */
    /* synthetic */ void m658xbc8b37b2(SurfaceTexture surfaceTexture) {
        this.mGLSurfaceView.requestRender();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        adjustVideoSize();
        glViewport(0, 0, i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        glClear(16640);
        adjustVideoSize();
        glVertexAttribPointer(this.vertexPosition, 2, 5126, false, 0, this.mVertextBuffer);
        glVertexAttribPointer(this.texturePosition, 2, 5126, false, 0, this.mTextureBuffer);
        this.mSurfaceTexture.updateTexImage();
        GLES30.glEnableVertexAttribArray(this.vertexPosition);
        GLES30.glEnableVertexAttribArray(this.texturePosition);
        GLES30.glUniform1i(this.samplerTexturePosition, 0);
        GLES30.glDrawArrays(5, 0, 4);
        GLES30.glFlush();
        GLES30.glDisableVertexAttribArray(this.vertexPosition);
        GLES30.glDisableVertexAttribArray(this.texturePosition);
    }

    public void setVideoSize(int i, int i2) {
        if (this.mVideoWidth == i && this.mVideoHeight == i2) {
            return;
        }
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        this.mNeedUpdateSize = true;
    }

    private void adjustVideoSize() {
        int i;
        int i2;
        int i3;
        int i4 = this.mVideoWidth;
        if (i4 == 0 || (i = this.mVideoHeight) == 0 || (i2 = this.mSurfaceHeight) == 0 || (i3 = this.mSurfaceWidth) == 0 || !this.mNeedUpdateSize) {
            return;
        }
        this.mNeedUpdateSize = false;
        float fMax = Math.max(i3 / i4, i2 / i);
        int iRound = Math.round(this.mVideoWidth * fMax);
        float f = iRound / this.mSurfaceWidth;
        float fRound = Math.round(this.mVideoHeight * fMax) / this.mSurfaceHeight;
        float[] fArr = POINT_DATA;
        float[] fArr2 = {fArr[0] / fRound, fArr[1] / f, fArr[2] / fRound, fArr[3] / f, fArr[4] / fRound, fArr[5] / f, fArr[6] / fRound, fArr[7] / f};
        this.mVertextBuffer.clear();
        this.mVertextBuffer.put(fArr2);
        this.mVertextBuffer.position(0);
    }

    public void setIVideoTextureRenderListener(IVideoTextureRenderListener iVideoTextureRenderListener) {
        this.mTextureRenderListener = iVideoTextureRenderListener;
    }
}
