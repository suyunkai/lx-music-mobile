package com.wanos.media.widget.sound;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wanos.bean.PressureBean;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.sound.SoundSeekBar;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class SoundView extends FrameLayout implements ISoundBallMove {
    private static final String TAG = "SoundView";
    private boolean isModifyVolume;
    private float mAnimScaleWidth;
    private final float mAnimViewDiff;
    private final float mAnimViewMaxSize;
    private final float mAnimViewMinSize;
    private AppCompatImageView mAnimaImage;
    private BallMateMove mBallMateMove;
    private final float mDefaultBottom;
    private final float mDefaultElevation;
    private float mDefaultScaleSize;
    private final float mDefaultTop;
    private AppCompatImageView mDeleteImage;
    private ISoundViewCallback mISoundViewCallback;
    private float mIconAnimScale;
    private View mIconBackground;
    private float mIconBgScale;
    private AppCompatImageView mIconImage;
    private float mIconImageScale;
    private float mIconScaleWidth;
    private final float mIconViewDiff;
    private final float mIconViewImageDiff;
    private final float mIconViewImageMaxSize;
    private final float mIconViewImageMinSize;
    private final float mIconViewMaxSize;
    private final float mIconViewMinSize;
    private final ValueAnimator.AnimatorUpdateListener mScaleUpdate;
    private SoundSeekBar mSoundSeekbar;
    private SoundViewMate mSoundViewMate;
    private final ValueAnimator mValueAnimator;
    private final int mViewSize;

    public interface ISoundViewCallback {
        void onEditModeChange(SoundView soundView, boolean z, float f);

        void onMoveChange(SoundView soundView, float f, float f2, float f3, float f4, BallMoveWay ballMoveWay);

        boolean onSoundDelete(SoundView soundView);

        void onUserTouch();

        void onVolumeChange(SoundView soundView, float f, BallMoveWay ballMoveWay);
    }

    public SoundView(Context context) {
        this(context, null);
    }

    public SoundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SoundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mViewSize = (int) getResources().getDimension(R.dimen.zero_sound_anim_max);
        float dimension = getResources().getDimension(R.dimen.zero_sound_icon_max);
        this.mIconViewMaxSize = dimension;
        float dimension2 = getResources().getDimension(R.dimen.zero_sound_icon_min);
        this.mIconViewMinSize = dimension2;
        float dimension3 = getResources().getDimension(R.dimen.zero_layer_three);
        this.mDefaultElevation = dimension3;
        this.mDefaultBottom = getResources().getDimension(R.dimen.zero_layer_two);
        this.mDefaultTop = getResources().getDimension(R.dimen.zero_layer_four);
        float dimension4 = getResources().getDimension(R.dimen.zero_sound_icon_image_max);
        this.mIconViewImageMaxSize = dimension4;
        float dimension5 = getResources().getDimension(R.dimen.zero_sound_icon_image_min);
        this.mIconViewImageMinSize = dimension5;
        float dimension6 = getResources().getDimension(R.dimen.zero_sound_anim_min);
        this.mAnimViewMinSize = dimension6;
        float dimension7 = getResources().getDimension(R.dimen.zero_sound_anim_max);
        this.mAnimViewMaxSize = dimension7;
        this.mValueAnimator = new ValueAnimator();
        this.mIconViewImageDiff = dimension4 - dimension5;
        this.mAnimViewDiff = dimension7 - dimension6;
        this.mIconViewDiff = dimension - dimension2;
        this.mAnimScaleWidth = dimension7;
        this.mIconScaleWidth = dimension;
        this.mDefaultScaleSize = 1.0f;
        this.isModifyVolume = false;
        this.mScaleUpdate = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.media.widget.sound.SoundView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SoundView.this.mAnimaImage.setScaleX(fFloatValue);
                SoundView.this.mAnimaImage.setScaleY(fFloatValue);
            }
        };
        this.mSoundViewMate = new SoundViewMate();
        this.mBallMateMove = new BallMateMove(context, this);
        LayoutInflater.from(context).inflate(R.layout.weight_sound_child, (ViewGroup) this, true);
        this.mAnimaImage = (AppCompatImageView) findViewById(R.id.iv_animation);
        this.mIconImage = (AppCompatImageView) findViewById(R.id.iv_icon);
        this.mDeleteImage = (AppCompatImageView) findViewById(R.id.iv_delete);
        this.mSoundSeekbar = (SoundSeekBar) findViewById(R.id.seekbar_sound);
        this.mIconBackground = findViewById(R.id.v_icon);
        setEditState(false);
        setElevation(dimension3);
        this.mSoundSeekbar.setOnSeekChangeListener(new SoundSeekBar.OnSeekChangeListener() { // from class: com.wanos.media.widget.sound.SoundView.2
            private boolean isRestartAnim = false;

            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekDown() {
                if (SoundView.this.mValueAnimator.isRunning()) {
                    this.isRestartAnim = true;
                    SoundView.this.outBreathAnim();
                }
            }

            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekChange(float f, BallMoveWay ballMoveWay) {
                SoundView.this.mBallMateMove.onSeekBarChange(f, ballMoveWay);
            }

            @Override // com.wanos.media.widget.sound.SoundSeekBar.OnSeekChangeListener
            public void onSeekUp() {
                if (this.isRestartAnim) {
                    SoundView.this.inBreathAnim();
                }
                this.isRestartAnim = false;
            }
        });
        this.mDeleteImage.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.widget.sound.SoundView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m652lambda$new$0$comwanosmediawidgetsoundSoundView(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-widget-sound-SoundView, reason: not valid java name */
    /* synthetic */ void m652lambda$new$0$comwanosmediawidgetsoundSoundView(View view) {
        ISoundViewCallback iSoundViewCallback = this.mISoundViewCallback;
        if (iSoundViewCallback == null) {
            return;
        }
        iSoundViewCallback.onSoundDelete(this);
    }

    private void setViewTranslationZ(float f) {
        if (f > 0.0f) {
            float elevation = getElevation();
            float f2 = this.mDefaultTop;
            if (elevation != f2) {
                setElevation(f2);
            }
        } else if (f < 0.0f) {
            float elevation2 = getElevation();
            float f3 = this.mDefaultBottom;
            if (elevation2 != f3) {
                setElevation(f3);
            }
        } else {
            float elevation3 = getElevation();
            float f4 = this.mDefaultElevation;
            if (elevation3 != f4) {
                setElevation(f4);
            }
        }
        float fAbs = 1.0f - ((this.mIconViewImageDiff / this.mIconViewImageMaxSize) * (1.0f - Math.abs(f)));
        if (Math.abs(fAbs - this.mIconImageScale) >= 0.01d) {
            this.mIconImageScale = fAbs;
            this.mIconImage.setScaleX(fAbs);
            this.mIconImage.setScaleY(this.mIconImageScale);
        }
        float fAbs2 = 1.0f - ((this.mIconViewDiff / this.mIconViewMaxSize) * (1.0f - Math.abs(f)));
        if (Math.abs(fAbs2 - this.mIconBgScale) >= 0.01d) {
            this.mIconBgScale = fAbs2;
            this.mIconScaleWidth = this.mIconViewMaxSize * fAbs2;
            this.mIconBackground.setScaleX(fAbs2);
            this.mIconBackground.setScaleY(this.mIconBgScale);
        }
        this.mBallMateMove.setBreathWidth((int) ((this.mAnimViewMaxSize - (this.mIconViewMaxSize * fAbs2)) / 2.0f));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        BallMateMove ballMateMove = this.mBallMateMove;
        int i5 = this.mViewSize;
        ballMateMove.onSizeChange(i5, i5, (View) getParent());
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        ZeroLogcatTools.d(TAG, "onWindowFocusChanged: ");
        if (z && !this.mSoundViewMate.isEditState()) {
            inBreathAnim();
        } else {
            outBreathAnim();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mBallMateMove.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mBallMateMove.onTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.mViewSize;
        setMeasuredDimension(i3, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inBreathAnim() {
        outBreathAnim();
        float f = this.mAnimScaleWidth;
        float f2 = this.mAnimViewMaxSize;
        float f3 = f / f2;
        this.mDefaultScaleSize = f3;
        this.mValueAnimator.setFloatValues(this.mIconScaleWidth / f2, f3);
        this.mValueAnimator.setRepeatCount(-1);
        this.mValueAnimator.setRepeatMode(2);
        this.mValueAnimator.setDuration(1400L);
        this.mValueAnimator.addUpdateListener(this.mScaleUpdate);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void outBreathAnim() {
        ZeroLogcatTools.d(TAG, "呼吸效果: 停止");
        this.mAnimaImage.setScaleX(this.mDefaultScaleSize);
        this.mAnimaImage.setScaleY(this.mDefaultScaleSize);
        this.mValueAnimator.removeUpdateListener(this.mScaleUpdate);
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
    }

    public VolumeModifyEntity getVolumeModifyInfo() {
        return this.mSoundViewMate.getVolumeModifyEntity();
    }

    public boolean setEditState(boolean z) {
        ZeroLogcatTools.d(TAG, "setEditState: " + z);
        if (this.mSoundViewMate.isEditState() == z) {
            return true;
        }
        if (z && (getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) getParent();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof SoundView) {
                    ((SoundView) childAt).setEditState(false);
                }
            }
        }
        this.mSoundViewMate.setEditState(z);
        this.mBallMateMove.setEditState(z);
        this.mSoundSeekbar.setVisibility(z ? 0 : 4);
        this.mDeleteImage.setVisibility(z ? 0 : 4);
        if (this.mISoundViewCallback != null) {
            ZeroLogcatTools.d(TAG, "setEditState: " + this.mSoundViewMate.getVolume());
            this.mISoundViewCallback.onEditModeChange(this, z, this.mSoundViewMate.getVolume());
        }
        return true;
    }

    public void setAutoMove(float f, float f2) {
        this.mBallMateMove.setAutoMove(f, f2);
    }

    public void setVolume(float f, boolean z) {
        this.mSoundViewMate.setVolume(f);
        if (!z) {
            this.isModifyVolume = true;
            ISoundViewCallback iSoundViewCallback = this.mISoundViewCallback;
            if (iSoundViewCallback != null) {
                iSoundViewCallback.onUserTouch();
            }
        }
        ISoundViewCallback iSoundViewCallback2 = this.mISoundViewCallback;
        if (iSoundViewCallback2 != null) {
            iSoundViewCallback2.onVolumeChange(this, this.mSoundViewMate.getVolume(), z ? BallMoveWay.WAY_INIT : BallMoveWay.WAY_USER_TOUCH);
        }
    }

    public float getVolume() {
        return this.mSoundViewMate.getVolume();
    }

    public void setIconInfo(String str, String str2, String str3) {
        int color;
        try {
            color = Color.parseColor(str2);
        } catch (Exception unused) {
            color = Color.parseColor("#CC1E1E1E");
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(R.dimen.zero_sound_icon_min);
        gradientDrawable.setColor(color);
        this.mIconBackground.setBackground(gradientDrawable);
        GlideUtil.setImageData(str, this.mIconImage, 80, 80);
        this.mSoundViewMate.setIconImageUrl(str);
        this.mSoundViewMate.setIconBg(str2);
        this.mSoundViewMate.setAudioName(str3);
    }

    public void setCallback(ISoundViewCallback iSoundViewCallback) {
        this.mISoundViewCallback = iSoundViewCallback;
    }

    public void setPosition(float f, float f2, float f3) {
        this.mBallMateMove.initViewPosition(f, f2, f3);
    }

    public void setMediaPosition(PressureBean.PressureLine pressureLine, PressureBean.PressureLine pressureLine2, double d2) {
        if (this.mSoundViewMate.isUserTouch() || pressureLine == null) {
            return;
        }
        this.mBallMateMove.setMediaTrack(pressureLine, pressureLine2, d2);
    }

    public void setSoundId(long j) {
        this.mSoundViewMate.setSoundId(j);
    }

    public long getSoundId() {
        return this.mSoundViewMate.getSoundId();
    }

    public boolean isUserTouch() {
        ZeroLogcatTools.d(TAG, "isUserTouch: 是否操作音源:" + this.mBallMateMove.isClickBall() + "  是否修改音量:" + this.isModifyVolume);
        return this.mBallMateMove.isClickBall() || this.isModifyVolume;
    }

    public boolean isModifyVolume() {
        return this.isModifyVolume;
    }

    public boolean isTouchBall() {
        return this.mBallMateMove.isClickBall();
    }

    public float getSpeed() {
        return this.mSoundViewMate.getAutoSpeed();
    }

    public float getDb() {
        return this.mSoundViewMate.getVolume();
    }

    public float getAngle() {
        return this.mSoundViewMate.getAutoAngle();
    }

    SoundViewMate getSoundMate() {
        return this.mSoundViewMate;
    }

    public boolean isEditState() {
        return this.mSoundViewMate.isEditState();
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void onEditModeChange(boolean z) {
        setEditState(z);
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void onMoveInfoChange(boolean z, float f, int i) {
        this.mSoundViewMate.setAutoMove(z);
        this.mSoundViewMate.setAutoSpeed(f);
        this.mSoundViewMate.setAutoAngle(i);
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void onBallMotionDown() {
        ISoundViewCallback iSoundViewCallback = this.mISoundViewCallback;
        if (iSoundViewCallback != null) {
            iSoundViewCallback.onUserTouch();
        }
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void onBallAudioPosition(float f, float f2, float f3, BallMoveWay ballMoveWay) {
        this.mSoundViewMate.setX(f);
        this.mSoundViewMate.setY(f2);
        this.mSoundViewMate.setZ(f3);
        ISoundViewCallback iSoundViewCallback = this.mISoundViewCallback;
        if (iSoundViewCallback != null) {
            iSoundViewCallback.onMoveChange(this, this.mSoundViewMate.getX(), this.mSoundViewMate.getY(), this.mSoundViewMate.getZ(), this.mSoundViewMate.getVolume(), ballMoveWay);
        }
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void setViewTranslationXY(float f, float f2) {
        if (getTranslationX() != f) {
            setTranslationX(f);
        }
        if (getTranslationY() != f2) {
            setTranslationY(f2);
        }
    }

    @Override // com.wanos.media.widget.sound.ISoundBallMove
    public void setViewTranslationZ(float f, BallMoveWay ballMoveWay) {
        if (this.mSoundSeekbar == null) {
            return;
        }
        if (BallMoveWay.WAY_USER_TOUCH != ballMoveWay) {
            this.mSoundSeekbar.setProgress(f);
        }
        setViewTranslationZ(f);
    }

    @Override // android.view.View
    public void setId(int i) {
        super.setId(i);
        this.mSoundViewMate.setId(i);
    }

    public static SoundView getInstance(Context context, final AudioInfoEntity audioInfoEntity, ISoundViewCallback iSoundViewCallback) {
        final SoundView soundView = new SoundView(context);
        soundView.setCallback(iSoundViewCallback);
        soundView.setIconInfo(audioInfoEntity.getAudioIconUrl(), audioInfoEntity.getAudioIconBackgroundColor(), audioInfoEntity.getAudioName());
        soundView.setVolume(audioInfoEntity.getVolume(), true);
        soundView.setId(audioInfoEntity.getPlayId());
        soundView.setSoundId(audioInfoEntity.getId());
        soundView.setVisibility(4);
        soundView.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));
        soundView.post(new Runnable() { // from class: com.wanos.media.widget.sound.SoundView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SoundView.lambda$getInstance$1(this.f$0, audioInfoEntity);
            }
        });
        return soundView;
    }

    static /* synthetic */ void lambda$getInstance$1(SoundView soundView, AudioInfoEntity audioInfoEntity) {
        soundView.setPosition(audioInfoEntity.getX(), audioInfoEntity.getY(), audioInfoEntity.getZ());
        soundView.setVisibility(0);
        soundView.inBreathAnim();
    }
}
