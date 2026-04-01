package com.wanos.media.widget.ball;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.BallCollectEntity;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.entity.ZeroBallInfoEntity;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.sound.BallMoveWay;
import com.wanos.media.widget.sound.SoundSeekBar;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class BallSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, IBallCallback {
    private static final String TAG = "BallSurfaceView";
    private final AssetManager assetManager;
    private IBallViewCallback iBallViewCallback;
    private int mEditViewSize;
    private PopupWindow mPopupWindow;
    private final BallSurfaceViewViewModel mViewModel;

    public interface IBallViewCallback {
        void onBallAudioPositionXY(BallMoveWay ballMoveWay, int i, float f, float f2);

        void onBallAudioPositionZ(BallMoveWay ballMoveWay, int i, float f);

        void onBallAudioVolume(BallMoveWay ballMoveWay, int i, float f);

        void onBallEdit();

        void onSurfaceCreate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BallSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        BallSurfaceViewViewModel ballSurfaceViewViewModel = (BallSurfaceViewViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(BallSurfaceViewViewModel.class);
        this.mViewModel = ballSurfaceViewViewModel;
        ballSurfaceViewViewModel.setIBallCallback(this);
        this.assetManager = context.getAssets();
        setEGLContextClientVersion(3);
        setRenderer(this);
        setRenderMode(0);
        this.mEditViewSize = (int) getResources().getDimension(R.dimen.relax_ball_size);
        ballSurfaceViewViewModel.setEditBallId(-1);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.mViewModel.onSurfaceCreated(this.assetManager);
        if (this.iBallViewCallback != null) {
            ThreadUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.wanos.media.widget.ball.BallSurfaceView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m607x7a025e13();
                }
            }, 400L);
        }
    }

    /* JADX INFO: renamed from: lambda$onSurfaceCreated$0$com-wanos-media-widget-ball-BallSurfaceView, reason: not valid java name */
    /* synthetic */ void m607x7a025e13() {
        this.iBallViewCallback.onSurfaceCreate();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        int dimension = (int) getResources().getDimension(R.dimen.zero_pro_ball_min_size);
        int dimension2 = (int) getResources().getDimension(R.dimen.zero_pro_ball_max_size);
        this.mViewModel.onSurfaceChanged(new Rect(getLeft(), getTop(), getRight(), getBottom()), dimension, dimension2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        this.mViewModel.onDrawFrame();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mViewModel.onDestroyOpenGl();
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mPopupWindow.dismiss();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            dismissEditView();
            int iOnViewTouchDown = this.mViewModel.onViewTouchDown(BallMoveHelp.getAudioXPos(getWidth(), motionEvent.getX()), BallMoveHelp.getAudioYPos(getHeight(), motionEvent.getY()));
            BallSurfaceViewViewModel ballSurfaceViewViewModel = this.mViewModel;
            ballSurfaceViewViewModel.setNowMate(ballSurfaceViewViewModel.findBallMoveHelp(iOnViewTouchDown));
            if (this.mViewModel.getNowMate() == null) {
                return true;
            }
            this.mViewModel.getNowMate().onTouchEventDown(motionEvent);
            IBallViewCallback iBallViewCallback = this.iBallViewCallback;
            if (iBallViewCallback != null) {
                iBallViewCallback.onBallEdit();
            }
        } else if (action == 1) {
            if (this.mViewModel.getNowMate() != null) {
                this.mViewModel.getNowMate().onTouchEventUp(motionEvent);
            }
            this.mViewModel.setNowMate(null);
        } else if (action != 2) {
            if (action == 3) {
                this.mViewModel.setNowMate(null);
            }
        } else {
            if (this.mViewModel.getNowMate() == null) {
                return true;
            }
            this.mViewModel.getNowMate().onTouchEventMove(motionEvent);
        }
        return true;
    }

    public int getBallSize() {
        return this.mViewModel.getBallInfoSize();
    }

    public void resetViewModelData() {
        this.mViewModel.resetViewModelData();
    }

    public void insertBall(AudioInfoEntity audioInfoEntity) {
        this.mViewModel.getBallMateWork().onInsertBall(audioInfoEntity);
    }

    public ArrayList<VolumeModifyEntity> getAudioVolumeInfo() {
        return this.mViewModel.getAudioVolumeInfo();
    }

    public ArrayList<BallCollectEntity> getBallCollectEntity() {
        return this.mViewModel.getBallCollectListEntity();
    }

    public boolean isEditBall(List<Long> list) {
        return this.mViewModel.isEditSceneBoll(list);
    }

    public boolean isControl() {
        return this.mViewModel.getEditBallId() != -1;
    }

    public void exitEditState() {
        dismissEditView();
    }

    public void setScenePositionFlatState(boolean z) {
        this.mViewModel.setPositionFlagState(z);
    }

    public void deleteBall(int i) {
        this.mViewModel.getBallMateWork().onDeleteBall(i);
        if (i == this.mViewModel.getEditBallId()) {
            dismissEditView();
        }
    }

    public void setBallCallback(IBallViewCallback iBallViewCallback) {
        this.iBallViewCallback = iBallViewCallback;
    }

    public void setBallVolume(int i, float f) {
        this.mViewModel.setBallVolume(i, f);
        IBallViewCallback iBallViewCallback = this.iBallViewCallback;
        if (iBallViewCallback != null) {
            iBallViewCallback.onBallAudioVolume(BallMoveWay.WAY_USER_TOUCH, i, f);
            this.iBallViewCallback.onBallEdit();
        }
    }

    public void setMediaTrack(int i, float f, float f2, float f3) {
        BallMoveHelp ballMoveHelpFindBallMoveHelp = this.mViewModel.findBallMoveHelp(i);
        if (ballMoveHelpFindBallMoveHelp != null) {
            ballMoveHelpFindBallMoveHelp.setMediaTrack(f, f2, f3);
        }
    }

    public void setBallAutoMove(int i, float f, float f2) {
        BallMoveHelp ballMoveHelpFindBallMoveHelp = this.mViewModel.findBallMoveHelp(i);
        if (ballMoveHelpFindBallMoveHelp == null || ballMoveHelpFindBallMoveHelp.isAutoMove()) {
            return;
        }
        ballMoveHelpFindBallMoveHelp.setAutoMove(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setZ(int i, float f, BallMoveWay ballMoveWay) {
        BallMoveHelp ballMoveHelpFindBallMoveHelp = this.mViewModel.findBallMoveHelp(i);
        if (ballMoveHelpFindBallMoveHelp == null) {
            return;
        }
        ballMoveHelpFindBallMoveHelp.setBallTranslationZ(f, ballMoveWay);
    }

    @Override // com.wanos.media.widget.ball.IBallCallback
    public void onBallPositionXY(BallMoveWay ballMoveWay, int i, float f, float f2) {
        this.mViewModel.setBallPositionXY(i, f, f2);
        IBallViewCallback iBallViewCallback = this.iBallViewCallback;
        if (iBallViewCallback != null) {
            iBallViewCallback.onBallAudioPositionXY(ballMoveWay, i, f, f2);
        }
    }

    @Override // com.wanos.media.widget.ball.IBallCallback
    public void onBallZ(BallMoveWay ballMoveWay, int i, float f) {
        this.mViewModel.setBallPositionZ(i, f);
        IBallViewCallback iBallViewCallback = this.iBallViewCallback;
        if (iBallViewCallback != null) {
            iBallViewCallback.onBallAudioPositionZ(ballMoveWay, i, f);
        }
    }

    @Override // com.wanos.media.widget.ball.IBallCallback
    public void onMotionClick(int i, float f, float f2, float f3) {
        if (this.mViewModel.getEditBallId() == i) {
            ZeroLogcatTools.e(TAG, "onMotionClick: 小球已处于编辑状态，无法再次编辑");
            return;
        }
        BallMoveHelp ballMoveHelpFindBallMoveHelp = this.mViewModel.findBallMoveHelp(i);
        if (ballMoveHelpFindBallMoveHelp == null) {
            ZeroLogcatTools.e(TAG, "onMotionClick: 小球不存在，无法编辑");
            return;
        }
        float[] fArrFindBallPosition = this.mViewModel.findBallPosition(i);
        if (fArrFindBallPosition == null) {
            ZeroLogcatTools.e(TAG, "onMotionClick: 未查询到小球中心点坐标，无法编辑");
        } else {
            this.mViewModel.setEditBallId(i);
            showEditView(ballMoveHelpFindBallMoveHelp.getScreenPositionX(fArrFindBallPosition[0]), ballMoveHelpFindBallMoveHelp.getScreenPositionY(fArrFindBallPosition[1]), fArrFindBallPosition[2]);
        }
    }

    @Override // com.wanos.media.widget.ball.IBallCallback
    public void onRequestRender() {
        requestRender();
    }

    public void setBackgroundImage(String str) {
        this.mViewModel.setBackgroundImage(str);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mEditViewSize = (int) getResources().getDimension(R.dimen.relax_ball_size);
    }

    private void showEditView(float f, float f2, float f3) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (!(topActivity instanceof ZeroInfoActivity)) {
            ZeroLogcatTools.d(TAG, "showEditView: 非ZeroInfoActivity");
            return;
        }
        if (topActivity.isFinishing() || topActivity.isDestroyed()) {
            ZeroLogcatTools.d(TAG, "showEditView: Activity已销毁");
            return;
        }
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
            this.mPopupWindow = null;
        }
        ZeroLogcatTools.w(TAG, "showEditView: screenCx = " + f + ", screenCy = " + f2 + ", isFocus = " + isFocused());
        View viewInflate = LayoutInflater.from(topActivity).inflate(R.layout.view_ball_edit, (ViewGroup) null);
        int i = this.mEditViewSize;
        PopupWindow popupWindow2 = new PopupWindow(viewInflate, i, i, false);
        this.mPopupWindow = popupWindow2;
        popupWindow2.setOutsideTouchable(true);
        PopupWindow popupWindow3 = this.mPopupWindow;
        View view = (View) getParent();
        int i2 = this.mEditViewSize;
        popupWindow3.showAtLocation(view, 0, (int) (f - (i2 / 2.0f)), (int) (f2 - (i2 / 2.0f)));
        SoundSeekBar soundSeekBar = (SoundSeekBar) viewInflate.findViewById(R.id.seekBar_edit);
        AppCompatImageView appCompatImageView = (AppCompatImageView) viewInflate.findViewById(R.id.iv_delete);
        BallSurfaceViewViewModel ballSurfaceViewViewModel = this.mViewModel;
        ZeroBallInfoEntity zeroBallInfoEntityFindBallInfo = ballSurfaceViewViewModel.findBallInfo(ballSurfaceViewViewModel.getEditBallId());
        if (zeroBallInfoEntityFindBallInfo != null) {
            appCompatImageView.setVisibility(zeroBallInfoEntityFindBallInfo.isSupportDelete() ? 0 : 8);
        }
        soundSeekBar.setProgress(f3);
        soundSeekBar.setOnSeekChangeListener(new SoundSeekBar.OnSeekChangeListener() { // from class: com.wanos.media.widget.ball.BallSurfaceView.1
            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekDown() {
            }

            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekUp() {
            }

            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekChange(float f4, BallMoveWay ballMoveWay) {
                BallSurfaceView ballSurfaceView = BallSurfaceView.this;
                ballSurfaceView.setZ(ballSurfaceView.mViewModel.getEditBallId(), f4, BallMoveWay.WAY_USER_TOUCH);
            }
        });
        appCompatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.widget.ball.BallSurfaceView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m608xadf9d446(view2);
            }
        });
        viewInflate.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.ball.BallSurfaceView.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view2) {
                BallSurfaceView.this.dismissEditView();
            }
        });
        this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wanos.media.widget.ball.BallSurfaceView.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                BallSurfaceView.this.mViewModel.setEditBallId(-1);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$showEditView$1$com-wanos-media-widget-ball-BallSurfaceView, reason: not valid java name */
    /* synthetic */ void m608xadf9d446(View view) {
        if (ZeroAudioBallTools.getInstance().getSceneSoundIds().size() > 1) {
            ZeroAudioBallTools.getInstance().deleteBall(this.mViewModel.getEditBallId());
        } else {
            ToastUtil.showMsg(getContext().getString(R.string.least_one_sound));
        }
        dismissEditView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissEditView() {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
