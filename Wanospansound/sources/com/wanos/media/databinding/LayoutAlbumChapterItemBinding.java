package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.util.MarqueeTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAlbumChapterItemBinding implements ViewBinding {
    public final LinearLayout chapterItem;
    public final FrameLayout flIndexView;
    public final ImageView imPalyingStatus;
    public final ImageView ivChapterPayState;
    public final ImageView ivCollect;
    private final LinearLayout rootView;
    public final TextView tvChapterDuration;
    public final TextView tvChapterIndex;
    public final MarqueeTextView tvChapterName;

    private LayoutAlbumChapterItemBinding(LinearLayout rootView, LinearLayout chapterItem, FrameLayout flIndexView, ImageView imPalyingStatus, ImageView ivChapterPayState, ImageView ivCollect, TextView tvChapterDuration, TextView tvChapterIndex, MarqueeTextView tvChapterName) {
        this.rootView = rootView;
        this.chapterItem = chapterItem;
        this.flIndexView = flIndexView;
        this.imPalyingStatus = imPalyingStatus;
        this.ivChapterPayState = ivChapterPayState;
        this.ivCollect = ivCollect;
        this.tvChapterDuration = tvChapterDuration;
        this.tvChapterIndex = tvChapterIndex;
        this.tvChapterName = tvChapterName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAlbumChapterItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAlbumChapterItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_album_chapter_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAlbumChapterItemBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.fl_index_view;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_index_view);
        if (frameLayout != null) {
            i = R.id.im_palying_status;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_palying_status);
            if (imageView != null) {
                i = R.id.iv_chapter_pay_state;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_pay_state);
                if (imageView2 != null) {
                    i = R.id.iv_collect;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                    if (imageView3 != null) {
                        i = R.id.tv_chapter_duration;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_duration);
                        if (textView != null) {
                            i = R.id.tv_chapter_index;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_index);
                            if (textView2 != null) {
                                i = R.id.tv_chapter_name;
                                MarqueeTextView marqueeTextView = (MarqueeTextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_name);
                                if (marqueeTextView != null) {
                                    return new LayoutAlbumChapterItemBinding(linearLayout, linearLayout, frameLayout, imageView, imageView2, imageView3, textView, textView2, marqueeTextView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
