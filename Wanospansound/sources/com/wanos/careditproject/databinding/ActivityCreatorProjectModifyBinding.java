package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityCreatorProjectModifyBinding implements ViewBinding {
    public final LinearLayout btnBack;
    public final LinearLayout btnChangeCover;
    public final LinearLayout btnComfirm;
    public final EditText etProjectDes;
    public final EditText etProjectName;
    public final ImageView ivComfirm;
    public final ImageView ivCover;
    public final LinearLayout llEditDes;
    public final LinearLayout llEditName;
    public final LinearLayout llProgress;
    public final ProgressBar progressPull;
    private final LinearLayout rootView;
    public final RecyclerView rvCoverImgList;
    public final TextView tvComfirm;
    public final TextView tvProgressPercentage;
    public final TextView tvProjectDes;
    public final TextView tvProjectName;

    private ActivityCreatorProjectModifyBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, EditText editText, EditText editText2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, ProgressBar progressBar, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.btnBack = linearLayout2;
        this.btnChangeCover = linearLayout3;
        this.btnComfirm = linearLayout4;
        this.etProjectDes = editText;
        this.etProjectName = editText2;
        this.ivComfirm = imageView;
        this.ivCover = imageView2;
        this.llEditDes = linearLayout5;
        this.llEditName = linearLayout6;
        this.llProgress = linearLayout7;
        this.progressPull = progressBar;
        this.rvCoverImgList = recyclerView;
        this.tvComfirm = textView;
        this.tvProgressPercentage = textView2;
        this.tvProjectDes = textView3;
        this.tvProjectName = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCreatorProjectModifyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityCreatorProjectModifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_creator_project_modify, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCreatorProjectModifyBinding bind(View view) {
        int i = R.id.btn_back;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.btn_change_cover;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout2 != null) {
                i = R.id.btn_comfirm;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout3 != null) {
                    i = R.id.et_project_des;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.et_project_name;
                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
                        if (editText2 != null) {
                            i = R.id.iv_comfirm;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                i = R.id.iv_cover;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.ll_edit_des;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout4 != null) {
                                        i = R.id.ll_edit_name;
                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout5 != null) {
                                            i = R.id.ll_progress;
                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout6 != null) {
                                                i = R.id.progress_pull;
                                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                if (progressBar != null) {
                                                    i = R.id.rv_cover_img_list;
                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                    if (recyclerView != null) {
                                                        i = R.id.tv_comfirm;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView != null) {
                                                            i = R.id.tv_progress_percentage;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_project_des;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_project_name;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView4 != null) {
                                                                        return new ActivityCreatorProjectModifyBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, editText, editText2, imageView, imageView2, linearLayout4, linearLayout5, linearLayout6, progressBar, recyclerView, textView, textView2, textView3, textView4);
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
