package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView;
import com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentEditingPageBinding implements ViewBinding {
    public final ImageView btnMetronome;
    public final ImageView btnPlay;
    public final ImageView btnPosTemplate;
    public final ImageView btnRecord;
    public final ImageView btnReset;
    public final ImageView btnSetting;
    public final ImageView btnToEnd;
    public final LinearLayout btnTotalDb;
    public final LayoutGainTotalBinding containerGainTotal;
    public final FrameLayout dbUi;
    public final LinearLayout fadeUi;
    public final ImageView imageMyRes;
    public final ImageView imagePreviewAll;
    public final ImageView imageRedo;
    public final ImageView imageRes;
    public final ImageView imageUndo;
    public final FrameLayout keyboardContainer;
    public final LinearLayout llRes;
    public final FrameLayout objPosUi;
    public final PopBottomDbBinding pageDb;
    public final PopBottomFadeBinding pageFade;
    public final PopPasteBinding popPaste;
    public final PopPcmToolbarBinding popPcmToolbar;
    public final FrameLayout posdrawTemplateUi;
    public final FrameLayout recordUi;
    private final LinearLayout rootView;
    public final FrameLayout settingUi;
    public final FrameLayout speedUi;
    public final FrameLayout totalDbUi;
    public final TextView tvTotalDb;
    public final AudioTracksGLView viewAudioTrack;
    public final LinearLayout viewLeft;
    public final ConstraintLayout viewTop;
    public final AudioTrackClipView viewTrackClip;
    public final RecyclerView viewTrackLeft;

    private FragmentEditingPageBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout2, LayoutGainTotalBinding layoutGainTotalBinding, FrameLayout frameLayout, LinearLayout linearLayout3, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, FrameLayout frameLayout2, LinearLayout linearLayout4, FrameLayout frameLayout3, PopBottomDbBinding popBottomDbBinding, PopBottomFadeBinding popBottomFadeBinding, PopPasteBinding popPasteBinding, PopPcmToolbarBinding popPcmToolbarBinding, FrameLayout frameLayout4, FrameLayout frameLayout5, FrameLayout frameLayout6, FrameLayout frameLayout7, FrameLayout frameLayout8, TextView textView, AudioTracksGLView audioTracksGLView, LinearLayout linearLayout5, ConstraintLayout constraintLayout, AudioTrackClipView audioTrackClipView, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.btnMetronome = imageView;
        this.btnPlay = imageView2;
        this.btnPosTemplate = imageView3;
        this.btnRecord = imageView4;
        this.btnReset = imageView5;
        this.btnSetting = imageView6;
        this.btnToEnd = imageView7;
        this.btnTotalDb = linearLayout2;
        this.containerGainTotal = layoutGainTotalBinding;
        this.dbUi = frameLayout;
        this.fadeUi = linearLayout3;
        this.imageMyRes = imageView8;
        this.imagePreviewAll = imageView9;
        this.imageRedo = imageView10;
        this.imageRes = imageView11;
        this.imageUndo = imageView12;
        this.keyboardContainer = frameLayout2;
        this.llRes = linearLayout4;
        this.objPosUi = frameLayout3;
        this.pageDb = popBottomDbBinding;
        this.pageFade = popBottomFadeBinding;
        this.popPaste = popPasteBinding;
        this.popPcmToolbar = popPcmToolbarBinding;
        this.posdrawTemplateUi = frameLayout4;
        this.recordUi = frameLayout5;
        this.settingUi = frameLayout6;
        this.speedUi = frameLayout7;
        this.totalDbUi = frameLayout8;
        this.tvTotalDb = textView;
        this.viewAudioTrack = audioTracksGLView;
        this.viewLeft = linearLayout5;
        this.viewTop = constraintLayout;
        this.viewTrackClip = audioTrackClipView;
        this.viewTrackLeft = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentEditingPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentEditingPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_editing_page, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentEditingPageBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.btn_metronome;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_play;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.btn_pos_template;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.btn_record;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView4 != null) {
                        i = R.id.btn_reset;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView5 != null) {
                            i = R.id.btn_setting;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView6 != null) {
                                i = R.id.btn_to_end;
                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView7 != null) {
                                    i = R.id.btn_total_db;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.container_gain_total))) != null) {
                                        LayoutGainTotalBinding layoutGainTotalBindingBind = LayoutGainTotalBinding.bind(viewFindChildViewById);
                                        i = R.id.db_ui;
                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                        if (frameLayout != null) {
                                            i = R.id.fade_ui;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout2 != null) {
                                                i = R.id.image_my_res;
                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView8 != null) {
                                                    i = R.id.image_preview_all;
                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView9 != null) {
                                                        i = R.id.image_redo;
                                                        ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView10 != null) {
                                                            i = R.id.image_res;
                                                            ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                            if (imageView11 != null) {
                                                                i = R.id.image_undo;
                                                                ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView12 != null) {
                                                                    i = R.id.keyboard_container;
                                                                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                    if (frameLayout2 != null) {
                                                                        i = R.id.ll_res;
                                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                        if (linearLayout3 != null) {
                                                                            i = R.id.obj_pos_ui;
                                                                            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                            if (frameLayout3 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.page_db))) != null) {
                                                                                PopBottomDbBinding popBottomDbBindingBind = PopBottomDbBinding.bind(viewFindChildViewById2);
                                                                                i = R.id.page_fade;
                                                                                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                                                                                if (viewFindChildViewById3 != null) {
                                                                                    PopBottomFadeBinding popBottomFadeBindingBind = PopBottomFadeBinding.bind(viewFindChildViewById3);
                                                                                    i = R.id.pop_paste;
                                                                                    View viewFindChildViewById4 = ViewBindings.findChildViewById(view, i);
                                                                                    if (viewFindChildViewById4 != null) {
                                                                                        PopPasteBinding popPasteBindingBind = PopPasteBinding.bind(viewFindChildViewById4);
                                                                                        i = R.id.pop_pcm_toolbar;
                                                                                        View viewFindChildViewById5 = ViewBindings.findChildViewById(view, i);
                                                                                        if (viewFindChildViewById5 != null) {
                                                                                            PopPcmToolbarBinding popPcmToolbarBindingBind = PopPcmToolbarBinding.bind(viewFindChildViewById5);
                                                                                            i = R.id.posdraw_template_ui;
                                                                                            FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                            if (frameLayout4 != null) {
                                                                                                i = R.id.record_ui;
                                                                                                FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                if (frameLayout5 != null) {
                                                                                                    i = R.id.setting_ui;
                                                                                                    FrameLayout frameLayout6 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                    if (frameLayout6 != null) {
                                                                                                        i = R.id.speed_ui;
                                                                                                        FrameLayout frameLayout7 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                        if (frameLayout7 != null) {
                                                                                                            i = R.id.total_db_ui;
                                                                                                            FrameLayout frameLayout8 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                            if (frameLayout8 != null) {
                                                                                                                i = R.id.tv_total_db;
                                                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (textView != null) {
                                                                                                                    i = R.id.view_audio_track;
                                                                                                                    AudioTracksGLView audioTracksGLView = (AudioTracksGLView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (audioTracksGLView != null) {
                                                                                                                        i = R.id.view_left;
                                                                                                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (linearLayout4 != null) {
                                                                                                                            i = R.id.view_top;
                                                                                                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (constraintLayout != null) {
                                                                                                                                i = R.id.view_track_clip;
                                                                                                                                AudioTrackClipView audioTrackClipView = (AudioTrackClipView) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (audioTrackClipView != null) {
                                                                                                                                    i = R.id.view_track_left;
                                                                                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (recyclerView != null) {
                                                                                                                                        return new FragmentEditingPageBinding((LinearLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, linearLayout, layoutGainTotalBindingBind, frameLayout, linearLayout2, imageView8, imageView9, imageView10, imageView11, imageView12, frameLayout2, linearLayout3, frameLayout3, popBottomDbBindingBind, popBottomFadeBindingBind, popPasteBindingBind, popPcmToolbarBindingBind, frameLayout4, frameLayout5, frameLayout6, frameLayout7, frameLayout8, textView, audioTracksGLView, linearLayout4, constraintLayout, audioTrackClipView, recyclerView);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
