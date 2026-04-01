package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.ErrorBinding;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.R;
import net.center.blurview.ShapeBlurView;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityWanosBaseBinding implements ViewBinding {
    public final FrameLayout baseContentViewgroup;
    public final ErrorBinding baseErrorView;
    public final FragmentLoadingBinding baseLoadingView;
    public final LinearLayout baseParentLl;
    public final ShapeBlurView blurView;
    public final ImageView btnPlayNext;
    public final ImageView btnPlayPause;
    public final ImageView ivAiMusicmv;
    public final ImageFilterView ivMediaCover;
    public final FrameLayout parentGroupContent;
    public final CardView playBar;
    private final FrameLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityWanosBaseBinding(FrameLayout frameLayout, FrameLayout frameLayout2, ErrorBinding errorBinding, FragmentLoadingBinding fragmentLoadingBinding, LinearLayout linearLayout, ShapeBlurView shapeBlurView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageFilterView imageFilterView, FrameLayout frameLayout3, CardView cardView, LayoutTitleBarBinding layoutTitleBarBinding) {
        this.rootView = frameLayout;
        this.baseContentViewgroup = frameLayout2;
        this.baseErrorView = errorBinding;
        this.baseLoadingView = fragmentLoadingBinding;
        this.baseParentLl = linearLayout;
        this.blurView = shapeBlurView;
        this.btnPlayNext = imageView;
        this.btnPlayPause = imageView2;
        this.ivAiMusicmv = imageView3;
        this.ivMediaCover = imageFilterView;
        this.parentGroupContent = frameLayout3;
        this.playBar = cardView;
        this.titleBar = layoutTitleBarBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWanosBaseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityWanosBaseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_wanos_base, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWanosBaseBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.base_content_viewgroup;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.base_error_view))) != null) {
            ErrorBinding errorBindingBind = ErrorBinding.bind(viewFindChildViewById);
            i = R.id.base_loading_view;
            View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
            if (viewFindChildViewById3 != null) {
                FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById3);
                i = R.id.base_parent_ll;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.blur_view;
                    ShapeBlurView shapeBlurView = (ShapeBlurView) ViewBindings.findChildViewById(view, i);
                    if (shapeBlurView != null) {
                        i = R.id.btn_play_next;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.btn_play_pause;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView2 != null) {
                                i = R.id.iv_ai_musicmv;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView3 != null) {
                                    i = R.id.iv_media_cover;
                                    ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(view, i);
                                    if (imageFilterView != null) {
                                        i = R.id.parent_group_content;
                                        FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                        if (frameLayout2 != null) {
                                            i = R.id.play_bar;
                                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                                            if (cardView != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.title_bar))) != null) {
                                                return new ActivityWanosBaseBinding((FrameLayout) view, frameLayout, errorBindingBind, fragmentLoadingBindingBind, linearLayout, shapeBlurView, imageView, imageView2, imageView3, imageFilterView, frameLayout2, cardView, LayoutTitleBarBinding.bind(viewFindChildViewById2));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
