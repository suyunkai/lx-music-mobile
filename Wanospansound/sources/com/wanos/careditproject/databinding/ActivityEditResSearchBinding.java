package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;
import com.wanos.media.util.SearchEditText;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityEditResSearchBinding implements ViewBinding {
    public final TextView btnSearch;
    private final LinearLayout rootView;
    public final SearchEditText searchEdit;
    public final ViewPager2 searchViewpager;
    public final ImageView turnBack;
    public final WanosTextView tvSearchTitle;

    private ActivityEditResSearchBinding(LinearLayout linearLayout, TextView textView, SearchEditText searchEditText, ViewPager2 viewPager2, ImageView imageView, WanosTextView wanosTextView) {
        this.rootView = linearLayout;
        this.btnSearch = textView;
        this.searchEdit = searchEditText;
        this.searchViewpager = viewPager2;
        this.turnBack = imageView;
        this.tvSearchTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEditResSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityEditResSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_edit_res_search, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEditResSearchBinding bind(View view) {
        int i = R.id.btn_search;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.search_edit;
            SearchEditText searchEditText = (SearchEditText) ViewBindings.findChildViewById(view, i);
            if (searchEditText != null) {
                i = R.id.search_viewpager;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                if (viewPager2 != null) {
                    i = R.id.turn_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.tv_search_title;
                        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView != null) {
                            return new ActivityEditResSearchBinding((LinearLayout) view, textView, searchEditText, viewPager2, imageView, wanosTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
