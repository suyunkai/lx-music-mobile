package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutCreatorDialogItemBinding implements ViewBinding {
    public final EditText etProjectIntroduction;
    public final EditText etProjectName;
    private final View rootView;
    public final RecyclerView rvCoverImgList;
    public final TextView tvCover;
    public final TextView tvName;
    public final TextView tvProjectIntroduction;

    private LayoutCreatorDialogItemBinding(View view, EditText editText, EditText editText2, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = view;
        this.etProjectIntroduction = editText;
        this.etProjectName = editText2;
        this.rvCoverImgList = recyclerView;
        this.tvCover = textView;
        this.tvName = textView2;
        this.tvProjectIntroduction = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static LayoutCreatorDialogItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.layout_creator_dialog_item, viewGroup);
        return bind(viewGroup);
    }

    public static LayoutCreatorDialogItemBinding bind(View view) {
        int i = R.id.et_project_introduction;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
        if (editText != null) {
            i = R.id.et_project_name;
            EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
            if (editText2 != null) {
                i = R.id.rv_cover_img_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.tv_cover;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tv_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.tv_project_introduction;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                return new LayoutCreatorDialogItemBinding(view, editText, editText2, recyclerView, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
