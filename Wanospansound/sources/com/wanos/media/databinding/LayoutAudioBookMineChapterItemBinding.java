package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAudioBookMineChapterItemBinding implements ViewBinding {
    public final CardView albumCover;
    public final LinearLayout chapterItem;
    public final FrameLayout flIndexView;
    public final ImageView imCover;
    public final ImageView imPalyingStatus;
    public final ImageView ivChapterPayState;
    public final ImageView ivCollect;
    private final LinearLayout rootView;
    public final TextView tvAudioBookAuthorName;
    public final TextView tvChapterDuration;
    public final TextView tvChapterIndex;
    public final TextView tvChapterName;
    public final TextView tvChapterProgress;

    private LayoutAudioBookMineChapterItemBinding(LinearLayout rootView, CardView albumCover, LinearLayout chapterItem, FrameLayout flIndexView, ImageView imCover, ImageView imPalyingStatus, ImageView ivChapterPayState, ImageView ivCollect, TextView tvAudioBookAuthorName, TextView tvChapterDuration, TextView tvChapterIndex, TextView tvChapterName, TextView tvChapterProgress) {
        this.rootView = rootView;
        this.albumCover = albumCover;
        this.chapterItem = chapterItem;
        this.flIndexView = flIndexView;
        this.imCover = imCover;
        this.imPalyingStatus = imPalyingStatus;
        this.ivChapterPayState = ivChapterPayState;
        this.ivCollect = ivCollect;
        this.tvAudioBookAuthorName = tvAudioBookAuthorName;
        this.tvChapterDuration = tvChapterDuration;
        this.tvChapterIndex = tvChapterIndex;
        this.tvChapterName = tvChapterName;
        this.tvChapterProgress = tvChapterProgress;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioBookMineChapterItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAudioBookMineChapterItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_audio_book_mine_chapter_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioBookMineChapterItemBinding bind(View rootView) {
        int i = R.id.album_cover;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.album_cover);
        if (cardView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.fl_index_view;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_index_view);
            if (frameLayout != null) {
                i = R.id.im_cover;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_cover);
                if (imageView != null) {
                    i = R.id.im_palying_status;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_palying_status);
                    if (imageView2 != null) {
                        i = R.id.iv_chapter_pay_state;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_pay_state);
                        if (imageView3 != null) {
                            i = R.id.iv_collect;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                            if (imageView4 != null) {
                                i = R.id.tv_audio_book_author_name;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_author_name);
                                if (textView != null) {
                                    i = R.id.tv_chapter_duration;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_duration);
                                    if (textView2 != null) {
                                        i = R.id.tv_chapter_index;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_index);
                                        if (textView3 != null) {
                                            i = R.id.tv_chapter_name;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_name);
                                            if (textView4 != null) {
                                                i = R.id.tv_chapter_progress;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chapter_progress);
                                                if (textView5 != null) {
                                                    return new LayoutAudioBookMineChapterItemBinding(linearLayout, cardView, linearLayout, frameLayout, imageView, imageView2, imageView3, imageView4, textView, textView2, textView3, textView4, textView5);
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
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
