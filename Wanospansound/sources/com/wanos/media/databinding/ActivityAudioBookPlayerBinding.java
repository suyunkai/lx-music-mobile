package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAudioBookPlayerBinding implements ViewBinding {
    public final CardView albumCover;
    public final MaterialAutoRefreshLayout audiobookRefreshLayout;
    public final ImageView btnBack;
    public final RecyclerView chapterList;
    public final EmptyViewBinding emptyView;
    public final ImageView imCover;
    public final ImageView ivAlbumState;
    public final ImageView ivChapterNext15;
    public final FrameLayout ivChapterPlay;
    public final ImageView ivChapterPlayState;
    public final ImageView ivChapterPre15;
    public final ImageView ivChapterSkipNext;
    public final ImageView ivChapterSkipPre;
    public final ImageView ivChapterSort;
    public final ImageView ivChapterSpeed;
    public final ImageView ivCollect;
    public final ImageView ivPayState;
    public final LinearLayout llTop;
    public final ProgressBar pbAudiobookPlay;
    public final ConstraintLayout playPage;
    private final LinearLayout rootView;
    public final SeekBar seekbarAudioProgress;
    public final TextView tvAlbumName;
    public final TextView tvAudioBookAuthorName;
    public final TextView tvDuration;
    public final TextView tvPlayTime;

    private ActivityAudioBookPlayerBinding(LinearLayout rootView, CardView albumCover, MaterialAutoRefreshLayout audiobookRefreshLayout, ImageView btnBack, RecyclerView chapterList, EmptyViewBinding emptyView, ImageView imCover, ImageView ivAlbumState, ImageView ivChapterNext15, FrameLayout ivChapterPlay, ImageView ivChapterPlayState, ImageView ivChapterPre15, ImageView ivChapterSkipNext, ImageView ivChapterSkipPre, ImageView ivChapterSort, ImageView ivChapterSpeed, ImageView ivCollect, ImageView ivPayState, LinearLayout llTop, ProgressBar pbAudiobookPlay, ConstraintLayout playPage, SeekBar seekbarAudioProgress, TextView tvAlbumName, TextView tvAudioBookAuthorName, TextView tvDuration, TextView tvPlayTime) {
        this.rootView = rootView;
        this.albumCover = albumCover;
        this.audiobookRefreshLayout = audiobookRefreshLayout;
        this.btnBack = btnBack;
        this.chapterList = chapterList;
        this.emptyView = emptyView;
        this.imCover = imCover;
        this.ivAlbumState = ivAlbumState;
        this.ivChapterNext15 = ivChapterNext15;
        this.ivChapterPlay = ivChapterPlay;
        this.ivChapterPlayState = ivChapterPlayState;
        this.ivChapterPre15 = ivChapterPre15;
        this.ivChapterSkipNext = ivChapterSkipNext;
        this.ivChapterSkipPre = ivChapterSkipPre;
        this.ivChapterSort = ivChapterSort;
        this.ivChapterSpeed = ivChapterSpeed;
        this.ivCollect = ivCollect;
        this.ivPayState = ivPayState;
        this.llTop = llTop;
        this.pbAudiobookPlay = pbAudiobookPlay;
        this.playPage = playPage;
        this.seekbarAudioProgress = seekbarAudioProgress;
        this.tvAlbumName = tvAlbumName;
        this.tvAudioBookAuthorName = tvAudioBookAuthorName;
        this.tvDuration = tvDuration;
        this.tvPlayTime = tvPlayTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAudioBookPlayerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAudioBookPlayerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_audio_book_player, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAudioBookPlayerBinding bind(View rootView) {
        int i = R.id.album_cover;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.album_cover);
        if (cardView != null) {
            i = R.id.audiobook_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.audiobook_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                i = R.id.btn_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
                if (imageView != null) {
                    i = R.id.chapter_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.chapter_list);
                    if (recyclerView != null) {
                        i = R.id.empty_view;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
                        if (viewFindChildViewById != null) {
                            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
                            i = R.id.im_cover;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_cover);
                            if (imageView2 != null) {
                                i = R.id.iv_album_state;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_album_state);
                                if (imageView3 != null) {
                                    i = R.id.iv_chapter_next_15;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_next_15);
                                    if (imageView4 != null) {
                                        i = R.id.iv_chapter_play;
                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_play);
                                        if (frameLayout != null) {
                                            i = R.id.iv_chapter_play_state;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_play_state);
                                            if (imageView5 != null) {
                                                i = R.id.iv_chapter_pre_15;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_pre_15);
                                                if (imageView6 != null) {
                                                    i = R.id.iv_chapter_skip_next;
                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_skip_next);
                                                    if (imageView7 != null) {
                                                        i = R.id.iv_chapter_skip_pre;
                                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_skip_pre);
                                                        if (imageView8 != null) {
                                                            i = R.id.iv_chapter_sort;
                                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_sort);
                                                            if (imageView9 != null) {
                                                                i = R.id.iv_chapter_speed;
                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_chapter_speed);
                                                                if (imageView10 != null) {
                                                                    i = R.id.iv_collect;
                                                                    ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                                                                    if (imageView11 != null) {
                                                                        i = R.id.iv_pay_state;
                                                                        ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pay_state);
                                                                        if (imageView12 != null) {
                                                                            i = R.id.ll_top;
                                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_top);
                                                                            if (linearLayout != null) {
                                                                                i = R.id.pb_audiobook_play;
                                                                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_audiobook_play);
                                                                                if (progressBar != null) {
                                                                                    i = R.id.play_page;
                                                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.play_page);
                                                                                    if (constraintLayout != null) {
                                                                                        i = R.id.seekbar_audio_progress;
                                                                                        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.seekbar_audio_progress);
                                                                                        if (seekBar != null) {
                                                                                            i = R.id.tv_album_name;
                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_album_name);
                                                                                            if (textView != null) {
                                                                                                i = R.id.tv_audio_book_author_name;
                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_author_name);
                                                                                                if (textView2 != null) {
                                                                                                    i = R.id.tv_duration;
                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_duration);
                                                                                                    if (textView3 != null) {
                                                                                                        i = R.id.tv_play_time;
                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_play_time);
                                                                                                        if (textView4 != null) {
                                                                                                            return new ActivityAudioBookPlayerBinding((LinearLayout) rootView, cardView, materialAutoRefreshLayout, imageView, recyclerView, emptyViewBindingBind, imageView2, imageView3, imageView4, frameLayout, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, linearLayout, progressBar, constraintLayout, seekBar, textView, textView2, textView3, textView4);
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
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
