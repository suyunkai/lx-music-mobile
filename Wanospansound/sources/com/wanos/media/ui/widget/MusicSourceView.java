package com.wanos.media.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.flyme.auto.music.source.MusicSourceEntity;
import com.flyme.auto.music.source.MusicSourceRepository;
import com.flyme.auto.music.source.util.ActivityExt;
import com.flyme.auto.music.source.util.MusicSourceLog;
import com.flyme.auto.music.source.util.PressAnimationHelper;
import com.wanos.media.ui.popup.MusicSourcePopupWindow;
import com.wanosj.musicsourceview.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class MusicSourceView extends FrameLayout {
    private static final boolean SOURCE_CLICKABLE = true;
    private final String TAG;
    private final ImageView arrowImageView;
    private boolean autoHidePopup;
    private int bgColor;
    private float bgRadius;
    private boolean isSupportMultiUser;
    private long lastClickTime;
    private final MusicSourceRepository.MusicSourceCallback musicSourceCallback;
    private final List<MusicSourceEntity> musicSourceList;
    private final MusicSourceRepository musicSourceRepository;
    private final int popupStart;
    private final int popupTop;
    private MusicSourcePopupWindow popupWindow;
    private PressAnimationHelper pressAnimationHelper;
    private int pressedColor;
    private boolean rebuildPopupWindow;
    private final String referrerAuthority;
    private int textColor;
    private final View viewRoot;

    private boolean clickDebounce() {
        if (System.currentTimeMillis() - this.lastClickTime <= 500) {
            return false;
        }
        this.lastClickTime = System.currentTimeMillis();
        return true;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MusicSourceView(Context context) {
        this(context, null);
    }

    public MusicSourceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public MusicSourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "MusicSource-View";
        this.bgRadius = 0.0f;
        this.bgColor = 0;
        this.textColor = ViewCompat.MEASURED_STATE_MASK;
        this.popupWindow = null;
        this.lastClickTime = 0L;
        this.autoHidePopup = true;
        this.rebuildPopupWindow = false;
        this.isSupportMultiUser = false;
        this.musicSourceCallback = new MusicSourceRepository.MusicSourceCallback() { // from class: com.wanos.media.ui.widget.MusicSourceView.1
            @Override // com.flyme.auto.music.source.MusicSourceRepository.MusicSourceCallback
            public void onMusicSourceChanged(List<MusicSourceEntity> list) {
                MusicSourceLog.d("MusicSource-View", "onMusicSourceChanged" + list);
                if (MusicSourceView.this.musicSourceList.toString().equals(list.toString())) {
                    return;
                }
                synchronized (MusicSourceView.this.musicSourceList) {
                    MusicSourceView.this.musicSourceList.clear();
                    MusicSourceView.this.musicSourceList.addAll(list);
                    MusicSourceView.this.rebuildPopupWindow = true;
                }
                if (MusicSourceView.this.isSupportMultiUser && MusicSourceView.this.popupWindow != null && MusicSourceView.this.popupWindow.isShowing()) {
                    MusicSourceView.this.popupWindow.notifyDataSetChanged(MusicSourceView.this.musicSourceList);
                }
            }
        };
        MusicSourceLog.initVersionName(context);
        this.viewRoot = LayoutInflater.from(context).inflate(R.layout.view_music_source, (ViewGroup) this, true);
        this.popupStart = context.getResources().getDimensionPixelSize(R.dimen.source_view_popup_start);
        this.popupTop = context.getResources().getDimensionPixelSize(R.dimen.source_view_popup_top);
        this.pressedColor = ContextCompat.getColor(context, R.color.music_source_press_tint);
        this.referrerAuthority = ActivityExt.referrerAuthority(ActivityExt.getActivity(context));
        this.musicSourceRepository = new MusicSourceRepository(context.getApplicationContext());
        this.musicSourceList = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MusicSourceView, i, R.style.MusicSourceViewDefaultStyle);
        boolean z = context.getResources().getBoolean(R.bool.is_support_multi_user);
        setSupportMultiUser(typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_isSupportMultiUser) ? typedArrayObtainStyledAttributes.getBoolean(R.styleable.MusicSourceView_isSupportMultiUser, z) : z);
        this.textColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MusicSourceView_textColor, ContextCompat.getColor(context, R.color.source_text_color));
        this.pressedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MusicSourceView_pressedColor, ContextCompat.getColor(context, R.color.music_source_press_tint));
        View viewFindViewById = findViewById(R.id.music_source_pressed_item);
        ImageView imageView = (ImageView) findViewById(R.id.arrow_image_view);
        this.arrowImageView = imageView;
        TextView textView = (TextView) findViewById(R.id.name_text_view);
        ImageView imageView2 = (ImageView) findViewById(R.id.name_image_view);
        ImageView imageView3 = (ImageView) findViewById(R.id.icon_image_view);
        textView.setTextColor(this.textColor);
        imageView2.setColorFilter(this.textColor);
        if (imageView != null) {
            imageView.setColorFilter(this.textColor);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appName)) {
            String string = typedArrayObtainStyledAttributes.getString(R.styleable.MusicSourceView_appName);
            textView.setText(string);
            updateVrContentDescription(string);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appBrandImage)) {
            textView.setTextColor(0);
            imageView2.setVisibility(0);
            imageView2.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.MusicSourceView_appBrandImage));
            Resources resources = context.getResources();
            float dimension = resources.getDimension(R.dimen.source_name_image_width);
            dimension = typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appBrandImageWidth) ? typedArrayObtainStyledAttributes.getDimension(R.styleable.MusicSourceView_appBrandImageWidth, dimension) : dimension;
            float dimension2 = resources.getDimension(R.dimen.source_name_image_height);
            dimension2 = typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appBrandImageHeight) ? typedArrayObtainStyledAttributes.getDimension(R.styleable.MusicSourceView_appBrandImageHeight, dimension2) : dimension2;
            ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
            layoutParams.width = (int) dimension;
            layoutParams.height = (int) dimension2;
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
                    ((ConstraintLayout.LayoutParams) layoutParams2).startToEnd = R.id.name_image_view;
                    imageView.setLayoutParams(layoutParams2);
                }
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appIcon)) {
            imageView3.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.MusicSourceView_appIcon));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_bgColor)) {
            this.bgColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MusicSourceView_bgColor, ContextCompat.getColor(context, R.color.background_music_source));
        } else {
            this.bgColor = ContextCompat.getColor(context, R.color.background_music_source);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_bgRadius)) {
            this.bgRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.MusicSourceView_bgRadius, context.getResources().getDimension(R.dimen.background_radius));
        } else {
            this.bgRadius = context.getResources().getDimension(R.dimen.background_radius);
        }
        boolean z2 = typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_isSourceClickable) ? typedArrayObtainStyledAttributes.getBoolean(R.styleable.MusicSourceView_isSourceClickable, true) : true;
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appSourcePaddingStart)) {
            viewFindViewById.setPadding((int) typedArrayObtainStyledAttributes.getDimension(R.styleable.MusicSourceView_appSourcePaddingStart, context.getResources().getDimension(R.dimen.source_item_left_padding)), 0, typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_appSourcePaddingEnd) ? (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.MusicSourceView_appSourcePaddingEnd, 0.0f) : 0, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.MusicSourceView_isSourceHideArrow) && imageView != null) {
            if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.MusicSourceView_isSourceHideArrow, false)) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        this.pressAnimationHelper = new PressAnimationHelper();
        setClickAble(z2);
    }

    public void setClickAble(boolean z) {
        View viewFindViewById = this.viewRoot.findViewById(R.id.music_source_pressed_item);
        if (z) {
            PressAnimationHelper pressAnimationHelper = this.pressAnimationHelper;
            if (pressAnimationHelper != null) {
                pressAnimationHelper.addTargetView(viewFindViewById, false);
            }
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.widget.MusicSourceView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m562lambda$setClickAble$0$comwanosmediauiwidgetMusicSourceView(view);
                }
            });
            return;
        }
        PressAnimationHelper pressAnimationHelper2 = this.pressAnimationHelper;
        if (pressAnimationHelper2 != null) {
            pressAnimationHelper2.setPressedAnimationEnabled(false);
        }
        viewFindViewById.setOnClickListener(null);
    }

    /* JADX INFO: renamed from: lambda$setClickAble$0$com-wanos-media-ui-widget-MusicSourceView, reason: not valid java name */
    /* synthetic */ void m562lambda$setClickAble$0$comwanosmediauiwidgetMusicSourceView(View view) {
        if (clickDebounce()) {
            toggleSelector();
        } else {
            MusicSourceLog.d("MusicSource-View", "click debounce false");
        }
    }

    public void setSupportMultiUser(boolean z) {
        this.isSupportMultiUser = z;
        this.musicSourceRepository.setSupportMultiUser(z);
    }

    public void setSourceArrowHide(boolean z) {
        ImageView imageView = this.arrowImageView;
        if (imageView != null) {
            if (z) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
        }
    }

    private void initPopupWindow(List<MusicSourceEntity> list) {
        synchronized (this.musicSourceList) {
            MusicSourceLog.d("MusicSource-View", "initPopupWindow rebuild = " + this.rebuildPopupWindow + ", data = " + list);
            MusicSourcePopupWindow musicSourcePopupWindow = this.popupWindow;
            if (musicSourcePopupWindow == null || !musicSourcePopupWindow.isShowing() || this.rebuildPopupWindow) {
                MusicSourceLog.d("MusicSource-View", "initPopupWindow build");
                this.rebuildPopupWindow = false;
                MusicSourcePopupWindow musicSourcePopupWindow2 = new MusicSourcePopupWindow(getContext(), list, this.referrerAuthority);
                this.popupWindow = musicSourcePopupWindow2;
                musicSourcePopupWindow2.setStyle(this.bgRadius, this.bgColor, this.textColor, this.pressedColor);
                this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wanos.media.ui.widget.MusicSourceView$$ExternalSyntheticLambda2
                    @Override // android.widget.PopupWindow.OnDismissListener
                    public final void onDismiss() {
                        this.f$0.m559xcf5e4f3();
                    }
                });
                this.popupWindow.setMusicSourceSelectCallback(new MusicSourcePopupWindow.MusicSourceSelectCallback() { // from class: com.wanos.media.ui.widget.MusicSourceView$$ExternalSyntheticLambda3
                    @Override // com.wanos.media.ui.popup.MusicSourcePopupWindow.MusicSourceSelectCallback
                    public final void onMusicSourceSelect(MusicSourceEntity musicSourceEntity, int i) {
                        this.f$0.m561xd778c275(musicSourceEntity, i);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initPopupWindow$1$com-wanos-media-ui-widget-MusicSourceView, reason: not valid java name */
    /* synthetic */ void m559xcf5e4f3() {
        this.arrowImageView.setRotation(0.0f);
    }

    /* JADX INFO: renamed from: lambda$initPopupWindow$3$com-wanos-media-ui-widget-MusicSourceView, reason: not valid java name */
    /* synthetic */ void m561xd778c275(final MusicSourceEntity musicSourceEntity, final int i) {
        this.popupWindow.setAnimationStyle(R.style.MusicSourcePopupStyle);
        this.popupWindow.dismiss();
        if (Objects.equals(getContext().getPackageName(), musicSourceEntity.getPackageName())) {
            MusicSourceLog.d("MusicSource-View", "initPopupWindow packageName is the same");
        } else {
            this.viewRoot.postDelayed(new Runnable() { // from class: com.wanos.media.ui.widget.MusicSourceView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m560xf23753b4(musicSourceEntity, i);
                }
            }, 250L);
        }
    }

    /* JADX INFO: renamed from: lambda$initPopupWindow$2$com-wanos-media-ui-widget-MusicSourceView, reason: not valid java name */
    /* synthetic */ void m560xf23753b4(MusicSourceEntity musicSourceEntity, int i) {
        this.musicSourceRepository.startMusicSource(getContext(), musicSourceEntity, i, this.referrerAuthority);
    }

    public void applyDefaultDayNightMode(boolean z) {
        if (z) {
            setColors(getContext().getColor(R.color.background_music_source), getContext().getColor(R.color.source_text_color), getContext().getColor(R.color.music_source_press_tint));
            this.musicSourceRepository.setDayNightMode(16);
        } else {
            setColors(getContext().getColor(R.color.background_music_source_night), getContext().getColor(R.color.source_text_color_night), getContext().getColor(R.color.music_source_press_tint_night));
            this.musicSourceRepository.setDayNightMode(32);
        }
    }

    public void setColors(int i, int i2, int i3) {
        this.bgColor = i;
        this.textColor = i2;
        this.pressedColor = i3;
        updateStyle();
    }

    public void hidePopupWindow() {
        MusicSourcePopupWindow musicSourcePopupWindow = this.popupWindow;
        if (musicSourcePopupWindow != null) {
            musicSourcePopupWindow.dismiss();
        }
    }

    private void updateStyle() {
        TextView textView = (TextView) findViewById(R.id.name_text_view);
        ImageView imageView = (ImageView) findViewById(R.id.name_image_view);
        textView.setTextColor(this.textColor);
        imageView.setColorFilter(this.textColor);
        ImageView imageView2 = this.arrowImageView;
        if (imageView2 != null) {
            imageView2.setColorFilter(this.textColor);
        }
        MusicSourcePopupWindow musicSourcePopupWindow = this.popupWindow;
        if (musicSourcePopupWindow != null) {
            musicSourcePopupWindow.dismiss();
            this.popupWindow = null;
        }
    }

    private void updateVrContentDescription(String str) {
        try {
            Context context = getContext();
            if (context != null) {
                setContentDescription(context.getString(R.string.vr_content_description, context.getPackageName(), str));
            }
        } catch (Exception e) {
            MusicSourceLog.e("MusicSource-View", "updateVrContentDescription failed");
            e.printStackTrace();
        }
    }

    private void toggleSelector() {
        MusicSourceEntity musicSourceEntity;
        MusicSourcePopupWindow musicSourcePopupWindow = this.popupWindow;
        if (musicSourcePopupWindow != null && musicSourcePopupWindow.isShowing()) {
            MusicSourceLog.d("MusicSource-View", "dismiss popupWindow");
            this.popupWindow.dismiss();
            return;
        }
        if (this.isSupportMultiUser) {
            this.musicSourceRepository.fetchSupportMusicSource(true);
        }
        initPopupWindow(this.musicSourceList);
        this.popupWindow.setAnimationStyle(-1);
        this.popupWindow.showAsDropDown(this, this.popupStart, -this.popupTop, GravityCompat.START);
        this.arrowImageView.setRotation(180.0f);
        int i = 0;
        while (true) {
            try {
                if (i >= this.musicSourceList.size()) {
                    musicSourceEntity = null;
                    break;
                } else {
                    if (this.musicSourceList.get(i).getPackageName().equals(getContext().getPackageName())) {
                        musicSourceEntity = this.musicSourceList.get(i);
                        break;
                    }
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int iIndexOf = this.musicSourceList.indexOf(musicSourceEntity);
        if (musicSourceEntity == null || iIndexOf == -1) {
            return;
        }
        this.musicSourceRepository.reportMusicSourceClick(musicSourceEntity, iIndexOf, this.referrerAuthority);
    }

    public void setAutoHidePopup(boolean z) {
        this.autoHidePopup = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("MusicSource-View", "onAttachedToWindow");
        this.musicSourceRepository.registerCallback(this.musicSourceCallback);
        this.musicSourceRepository.fetchSupportMusicSource(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.musicSourceRepository.unRegisterCallback(this.musicSourceCallback);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (!this.autoHidePopup || i == 0) {
            return;
        }
        hidePopupWindow();
    }
}
