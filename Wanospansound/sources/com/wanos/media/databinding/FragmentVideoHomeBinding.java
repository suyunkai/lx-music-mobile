package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentVideoHomeBinding implements ViewBinding {
    public final RecyclerView listMovies;
    public final RecyclerView listTv;
    private final ScrollView rootView;
    public final TextView tvMovies;
    public final TextView tvTv;

    private FragmentVideoHomeBinding(ScrollView rootView, RecyclerView listMovies, RecyclerView listTv, TextView tvMovies, TextView tvTv) {
        this.rootView = rootView;
        this.listMovies = listMovies;
        this.listTv = listTv;
        this.tvMovies = tvMovies;
        this.tvTv = tvTv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentVideoHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentVideoHomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_video_home, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentVideoHomeBinding bind(View rootView) {
        int i = R.id.list_movies;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.list_movies);
        if (recyclerView != null) {
            i = R.id.list_tv;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.list_tv);
            if (recyclerView2 != null) {
                i = R.id.tv_movies;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_movies);
                if (textView != null) {
                    i = R.id.tv_tv;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tv);
                    if (textView2 != null) {
                        return new FragmentVideoHomeBinding((ScrollView) rootView, recyclerView, recyclerView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
