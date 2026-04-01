package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorProjectPublishBinding implements ViewBinding {
    public final ImageView btnBack;
    public final TextView btnDialogLeft;
    public final TextView btnDialogRight;
    public final LinearLayout btnToCommunity;
    public final LinearLayout btnToMine;
    public final CardView cvIcon;
    public final LinearLayout cvTemplete;
    public final EditText etProjectIntroduction;
    public final EditText etProjectName;
    public final ImageView imIcon;
    public final ImageView ivSelect;
    public final ProgressBar pbLoading;
    public final FrameLayout publishLoadingView;
    private final ConstraintLayout rootView;
    public final Spinner spinnerType;
    public final ImageView switchOpen;
    public final ImageView switchToCommunity;
    public final ImageView switchToMine;
    public final TextView tvAgreement;
    public final TextView tvForGroup;
    public final TextView tvName;
    public final TextView tvProjectIntroduction;
    public final TextView tvSelectIcon;
    public final TextView tvTemplete;
    public final TextView tvTitle;
    public final TextView tvType;
    public final ConstraintLayout viewRoot;
    public final LinearLayout viewRule;
    public final LinearLayout viewToCommunity;

    private DialogCreatorProjectPublishBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, LinearLayout linearLayout, LinearLayout linearLayout2, CardView cardView, LinearLayout linearLayout3, EditText editText, EditText editText2, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, FrameLayout frameLayout, Spinner spinner, ImageView imageView4, ImageView imageView5, ImageView imageView6, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, ConstraintLayout constraintLayout2, LinearLayout linearLayout4, LinearLayout linearLayout5) {
        this.rootView = constraintLayout;
        this.btnBack = imageView;
        this.btnDialogLeft = textView;
        this.btnDialogRight = textView2;
        this.btnToCommunity = linearLayout;
        this.btnToMine = linearLayout2;
        this.cvIcon = cardView;
        this.cvTemplete = linearLayout3;
        this.etProjectIntroduction = editText;
        this.etProjectName = editText2;
        this.imIcon = imageView2;
        this.ivSelect = imageView3;
        this.pbLoading = progressBar;
        this.publishLoadingView = frameLayout;
        this.spinnerType = spinner;
        this.switchOpen = imageView4;
        this.switchToCommunity = imageView5;
        this.switchToMine = imageView6;
        this.tvAgreement = textView3;
        this.tvForGroup = textView4;
        this.tvName = textView5;
        this.tvProjectIntroduction = textView6;
        this.tvSelectIcon = textView7;
        this.tvTemplete = textView8;
        this.tvTitle = textView9;
        this.tvType = textView10;
        this.viewRoot = constraintLayout2;
        this.viewRule = linearLayout4;
        this.viewToCommunity = linearLayout5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorProjectPublishBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorProjectPublishBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_project_publish, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorProjectPublishBinding bind(View view) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_dialog_left;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.btn_dialog_right;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.btn_to_community;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.btn_to_mine;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R.id.cv_icon;
                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                            if (cardView != null) {
                                i = R.id.cv_templete;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout3 != null) {
                                    i = R.id.et_project_introduction;
                                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                                    if (editText != null) {
                                        i = R.id.et_project_name;
                                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
                                        if (editText2 != null) {
                                            i = R.id.im_icon;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView2 != null) {
                                                i = R.id.iv_select;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView3 != null) {
                                                    i = R.id.pb_loading;
                                                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                    if (progressBar != null) {
                                                        i = R.id.publish_loading_view;
                                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                        if (frameLayout != null) {
                                                            i = R.id.spinner_type;
                                                            Spinner spinner = (Spinner) ViewBindings.findChildViewById(view, i);
                                                            if (spinner != null) {
                                                                i = R.id.switch_open;
                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView4 != null) {
                                                                    i = R.id.switch_to_community;
                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (imageView5 != null) {
                                                                        i = R.id.switch_to_mine;
                                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (imageView6 != null) {
                                                                            i = R.id.tv_agreement;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                            if (textView3 != null) {
                                                                                i = R.id.tv_for_group;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                if (textView4 != null) {
                                                                                    i = R.id.tv_name;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.tv_project_introduction;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (textView6 != null) {
                                                                                            i = R.id.tv_select_icon;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                            if (textView7 != null) {
                                                                                                i = R.id.tv_templete;
                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                if (textView8 != null) {
                                                                                                    i = R.id.tv_title;
                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (textView9 != null) {
                                                                                                        i = R.id.tv_type;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (textView10 != null) {
                                                                                                            ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                                                                                            i = R.id.view_rule;
                                                                                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                            if (linearLayout4 != null) {
                                                                                                                i = R.id.view_to_community;
                                                                                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                if (linearLayout5 != null) {
                                                                                                                    return new DialogCreatorProjectPublishBinding(constraintLayout, imageView, textView, textView2, linearLayout, linearLayout2, cardView, linearLayout3, editText, editText2, imageView2, imageView3, progressBar, frameLayout, spinner, imageView4, imageView5, imageView6, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, constraintLayout, linearLayout4, linearLayout5);
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
