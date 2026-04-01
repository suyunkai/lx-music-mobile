package com.wanos.media.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.wanos.media.R;
import com.wanos.media.databinding.LayoutImageCardInListBinding;

/* JADX INFO: loaded from: classes3.dex */
public class ImageCardInListLayout extends FrameLayout {
    private LayoutImageCardInListBinding binding;
    private int cardType;
    private int leftTopIconResId;
    private boolean leftTopIconVisible;
    private boolean moreBtnVisible;
    private boolean playBtnVisible;
    private boolean subTitleVisible;
    private boolean titleVisible;

    public enum CardPlayStatus {
        PREPARING,
        PLAYING,
        STOP
    }

    public ImageCardInListLayout(Context context) {
        super(context);
        this.cardType = 0;
        this.playBtnVisible = false;
        this.leftTopIconVisible = false;
        this.moreBtnVisible = false;
        this.titleVisible = false;
        this.subTitleVisible = false;
        this.leftTopIconResId = -1;
        this.binding = LayoutImageCardInListBinding.inflate(LayoutInflater.from(context), this, true);
        initView();
    }

    public ImageCardInListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cardType = 0;
        this.playBtnVisible = false;
        this.leftTopIconVisible = false;
        this.moreBtnVisible = false;
        this.titleVisible = false;
        this.subTitleVisible = false;
        this.leftTopIconResId = -1;
        this.binding = LayoutImageCardInListBinding.inflate(LayoutInflater.from(context), this, true);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        initAttrs(context, attributeSet);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ImageCardInListLayout, 0, 0);
        try {
            this.playBtnVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ImageCardInListLayout_playBtnVisible, false);
            this.leftTopIconVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ImageCardInListLayout_vipVisible, false);
            this.moreBtnVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ImageCardInListLayout_moreBtnVisible, false);
            this.titleVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ImageCardInListLayout_titleVisible, false);
            this.subTitleVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ImageCardInListLayout_subTitleVisible, false);
            this.leftTopIconResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ImageCardInListLayout_leftTopIcon, -1);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void initView() {
        this.binding.btnPlay.setVisibility(this.playBtnVisible ? 0 : 8);
        this.binding.imLeftTop.setVisibility(this.leftTopIconVisible ? 0 : 8);
        this.binding.btnMore.setVisibility(this.moreBtnVisible ? 0 : 8);
        this.binding.tvTitle.setVisibility(this.titleVisible ? 0 : 8);
        this.binding.tvTitleSub.setVisibility(this.subTitleVisible ? 0 : 8);
        int i = this.leftTopIconResId;
        if (i != -1) {
            setLeftTopIcon(i);
        }
    }

    public void setCoverImage(String str) {
        this.binding.imCover.setImage(str);
    }

    public void setLeftTopIcon(int i) {
        this.binding.imLeftTop.setVisibility(0);
        setLeftTopImage(i);
    }

    public void showVipStatus() {
        this.binding.imLeftTop.setVisibility(0);
        setLeftTopImage(R.drawable.ab_list_vip);
    }

    public void showNoStatus() {
        this.binding.imLeftTop.setVisibility(8);
    }

    protected void setLeftTopImage(int i) {
        this.binding.imLeftTop.setImageResource(i);
    }

    public void setTitleTextMaxLines(int i) {
        this.binding.tvTitle.setMaxLines(i);
    }

    public void setTitleText(String str) {
        this.binding.tvTitle.setText(str);
    }

    public void setSubTitleText(String str) {
        this.binding.tvTitleSub.setText(str);
    }

    public void showMoreBtn(boolean z) {
        this.binding.btnMore.setVisibility(z ? 0 : 8);
    }

    public void setOnClickPlayBtn(View.OnClickListener onClickListener) {
        this.binding.btnPlay.setOnClickListener(onClickListener);
    }

    public void setOnMoreBtnClickListener(View.OnClickListener onClickListener) {
        this.binding.btnMore.setOnClickListener(onClickListener);
    }

    public View getPlayBtn() {
        return this.binding.btnPlay;
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.widget.ImageCardInListLayout$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$ui$widget$ImageCardInListLayout$CardPlayStatus;

        static {
            int[] iArr = new int[CardPlayStatus.values().length];
            $SwitchMap$com$wanos$media$ui$widget$ImageCardInListLayout$CardPlayStatus = iArr;
            try {
                iArr[CardPlayStatus.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$ui$widget$ImageCardInListLayout$CardPlayStatus[CardPlayStatus.PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$ui$widget$ImageCardInListLayout$CardPlayStatus[CardPlayStatus.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void setPlayStatus(CardPlayStatus cardPlayStatus) {
        int i = AnonymousClass1.$SwitchMap$com$wanos$media$ui$widget$ImageCardInListLayout$CardPlayStatus[cardPlayStatus.ordinal()];
        if (i == 1) {
            this.binding.ivPlayState.setVisibility(8);
            this.binding.pbAudiobookPlay.setVisibility(0);
            this.binding.ivPlayState.pauseAnimation();
        } else {
            if (i == 2) {
                this.binding.ivPlayState.setAnimation(R.raw.lottie_white);
                this.binding.ivPlayState.resumeAnimation();
                this.binding.ivPlayState.setVisibility(0);
                this.binding.pbAudiobookPlay.setVisibility(8);
                return;
            }
            if (i != 3) {
                return;
            }
            this.binding.ivPlayState.setImageResource(R.drawable.group_card_pause);
            this.binding.ivPlayState.pauseAnimation();
            this.binding.ivPlayState.setVisibility(0);
            this.binding.pbAudiobookPlay.setVisibility(8);
        }
    }
}
