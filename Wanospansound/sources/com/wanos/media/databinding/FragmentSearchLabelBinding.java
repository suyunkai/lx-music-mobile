package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.util.FlowLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentSearchLabelBinding implements ViewBinding {
    public final FlowLayout flowlayoutHot;
    public final FlowLayout flowlayoutRecord;
    private final ConstraintLayout rootView;
    public final TextView searchHotWord;
    public final TextView searchRecordText;
    public final ScrollView searchScrollView;

    private FragmentSearchLabelBinding(ConstraintLayout rootView, FlowLayout flowlayoutHot, FlowLayout flowlayoutRecord, TextView searchHotWord, TextView searchRecordText, ScrollView searchScrollView) {
        this.rootView = rootView;
        this.flowlayoutHot = flowlayoutHot;
        this.flowlayoutRecord = flowlayoutRecord;
        this.searchHotWord = searchHotWord;
        this.searchRecordText = searchRecordText;
        this.searchScrollView = searchScrollView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchLabelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSearchLabelBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_search_label, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSearchLabelBinding bind(View rootView) {
        int i = R.id.flowlayout_hot;
        FlowLayout flowLayout = (FlowLayout) ViewBindings.findChildViewById(rootView, R.id.flowlayout_hot);
        if (flowLayout != null) {
            i = R.id.flowlayout_record;
            FlowLayout flowLayout2 = (FlowLayout) ViewBindings.findChildViewById(rootView, R.id.flowlayout_record);
            if (flowLayout2 != null) {
                i = R.id.search_hot_word;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.search_hot_word);
                if (textView != null) {
                    i = R.id.search_record_text;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.search_record_text);
                    if (textView2 != null) {
                        i = R.id.search_scroll_view;
                        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, R.id.search_scroll_view);
                        if (scrollView != null) {
                            return new FragmentSearchLabelBinding((ConstraintLayout) rootView, flowLayout, flowLayout2, textView, textView2, scrollView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
