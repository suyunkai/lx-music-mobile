package com.wanos.media.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.cacheData.iqy.IQYVideoCache;
import com.wanos.media.databinding.FragmentVideoHomeBinding;
import com.wanos.media.presenter.VideoPresenter;
import com.wanos.media.ui.video.activity.IQYVideoListActivity;
import com.wanos.media.ui.video.adapter.HomeMoviesListAdapter;
import com.wanos.media.view.VideoBaseView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VideoHomeFragment extends WanosBaseFragment<VideoPresenter> implements VideoBaseView {
    private FragmentVideoHomeBinding binding;
    private HomeMoviesListAdapter homeMoviesListAdapter;
    private HomeMoviesListAdapter homeTvListAdapter;
    private List<VideoInfo> moviesInfoList = new ArrayList();
    private List<VideoInfo> tvInfoList = new ArrayList();
    private boolean isFirstLoading = true;

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentVideoHomeBinding fragmentVideoHomeBindingInflate = FragmentVideoHomeBinding.inflate(inflater, container, false);
        this.binding = fragmentVideoHomeBindingInflate;
        ScrollView root = fragmentVideoHomeBindingInflate.getRoot();
        this.mPresenter = new VideoPresenter(getContext(), this);
        initViewList();
        initData();
        initVisibleIsSpoken();
        initListener();
        return root;
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.tvMovies.setContentDescription(getResources().getString(R.string.open_movie_more));
            this.binding.tvTv.setContentDescription(getResources().getString(R.string.open_teleplay_more));
        }
    }

    public void initData() {
        if (this.isFirstLoading) {
            showLoadingView();
            this.isFirstLoading = false;
        }
        if (this.mPresenter == 0) {
            return;
        }
        ((VideoPresenter) this.mPresenter).getIQYList();
    }

    private void initViewList() {
        Util.setTextWeight(this.binding.tvMovies, 500);
        Util.setTextWeight(this.binding.tvTv, 500);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
        this.homeMoviesListAdapter = new HomeMoviesListAdapter(getActivity(), this.moviesInfoList);
        this.binding.listMovies.setLayoutManager(gridLayoutManager);
        this.binding.listMovies.setAdapter(this.homeMoviesListAdapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 6);
        this.homeTvListAdapter = new HomeMoviesListAdapter(getActivity(), this.tvInfoList);
        this.binding.listTv.setLayoutManager(gridLayoutManager2);
        this.binding.listTv.setAdapter(this.homeTvListAdapter);
    }

    private void initListener() {
        this.binding.tvMovies.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.video.VideoHomeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IQYVideoListActivity.build(VideoHomeFragment.this.getContext(), 1);
            }
        });
        this.binding.tvTv.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.video.VideoHomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IQYVideoListActivity.build(VideoHomeFragment.this.getContext(), 2);
            }
        });
    }

    @Override // com.wanos.media.view.VideoBaseView
    public void updateView(List<VideoInfo> videoInfos) {
        if (isVisible()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.video.VideoHomeFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    VideoHomeFragment.this.closeLoadingView();
                }
            });
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < videoInfos.size(); i++) {
                VideoInfo videoInfo = videoInfos.get(i);
                if (videoInfo.getChnId() == 1) {
                    arrayList.add(videoInfo);
                } else if (videoInfo.getChnId() == 2) {
                    arrayList2.add(videoInfo);
                }
            }
            arrayList.sort(new Comparator<VideoInfo>() { // from class: com.wanos.media.ui.video.VideoHomeFragment.4
                @Override // java.util.Comparator
                public int compare(VideoInfo videoInfo2, VideoInfo t1) {
                    return t1.getHot().intValue() - videoInfo2.getHot().intValue();
                }
            });
            arrayList2.sort(new Comparator<VideoInfo>() { // from class: com.wanos.media.ui.video.VideoHomeFragment.5
                @Override // java.util.Comparator
                public int compare(VideoInfo videoInfo2, VideoInfo t1) {
                    return t1.getHot().intValue() - videoInfo2.getHot().intValue();
                }
            });
            IQYVideoCache.setMoviesList(arrayList);
            IQYVideoCache.setTvList(arrayList2);
            this.moviesInfoList = arrayList.subList(0, Math.min(arrayList.size(), 6));
            this.tvInfoList = arrayList2.subList(0, Math.min(arrayList2.size(), 6));
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.video.VideoHomeFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        int i2 = 8;
                        if (VideoHomeFragment.this.moviesInfoList != null) {
                            VideoHomeFragment.this.binding.listMovies.setVisibility(VideoHomeFragment.this.moviesInfoList.size() == 0 ? 8 : 0);
                            VideoHomeFragment.this.homeMoviesListAdapter.setData(VideoHomeFragment.this.moviesInfoList);
                            VideoHomeFragment.this.homeMoviesListAdapter.notifyDataSetChanged();
                        }
                        if (VideoHomeFragment.this.tvInfoList != null) {
                            RecyclerView recyclerView = VideoHomeFragment.this.binding.listTv;
                            if (VideoHomeFragment.this.tvInfoList.size() != 0) {
                                i2 = 0;
                            }
                            recyclerView.setVisibility(i2);
                            VideoHomeFragment.this.homeTvListAdapter.setData(VideoHomeFragment.this.tvInfoList);
                            VideoHomeFragment.this.homeTvListAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.video.VideoHomeFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoHomeFragment.this.isFirstLoading = true;
                VideoHomeFragment.this.initData();
            }
        });
    }
}
