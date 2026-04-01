package com.wanos.media.ui.video.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.iqy.IQYVideoCache;
import com.wanos.media.databinding.ActivityMoviesListBinding;
import com.wanos.media.presenter.VideoPresenter;
import com.wanos.media.ui.video.adapter.HomeMoviesListAdapter;
import com.wanos.media.view.VideoBaseView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IQYVideoListActivity extends WanosBaseActivity<VideoPresenter> implements VideoBaseView, View.OnClickListener {
    public static String strChnId = "chnId";
    private ActivityMoviesListBinding binding;
    private int chnId;
    private HomeMoviesListAdapter homeMoviesListAdapter;
    private List<VideoInfo> videoList = new ArrayList();

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    public static void build(Context context, int chnId) {
        Intent intent = new Intent(context, (Class<?>) IQYVideoListActivity.class);
        intent.putExtra(strChnId, chnId);
        context.startActivity(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMoviesListBinding activityMoviesListBindingInflate = ActivityMoviesListBinding.inflate(getLayoutInflater());
        this.binding = activityMoviesListBindingInflate;
        setContentView(activityMoviesListBindingInflate.getRoot());
        this.mPresenter = new VideoPresenter(this, this);
        initData();
        initView();
    }

    private void initData() {
        int intExtra = getIntent().getIntExtra(strChnId, 1);
        this.chnId = intExtra;
        if (intExtra == 1) {
            if (IQYVideoCache.getMoviesList() == null) {
                ((VideoPresenter) this.mPresenter).getIQYList();
                return;
            } else {
                this.videoList = IQYVideoCache.getMoviesList();
                return;
            }
        }
        if (IQYVideoCache.getTvList() == null) {
            ((VideoPresenter) this.mPresenter).getIQYList();
        } else {
            this.videoList = IQYVideoCache.getTvList();
        }
    }

    private void initView() {
        if (this.chnId == 1) {
            setTitleText(R.string.wanos_movies);
        } else {
            setTitleText(R.string.wanos_tv);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        this.homeMoviesListAdapter = new HomeMoviesListAdapter(this, this.videoList);
        this.binding.videoList.setLayoutManager(gridLayoutManager);
        this.binding.videoList.setAdapter(this.homeMoviesListAdapter);
        this.activityWanosBaseBinding.getRoot().removeView(this.activityWanosBaseBinding.playBar);
    }

    @Override // com.wanos.media.view.VideoBaseView
    public void updateView(List<VideoInfo> videoInfos) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < videoInfos.size(); i++) {
            VideoInfo videoInfo = videoInfos.get(i);
            if (videoInfo.getChnId() == 1) {
                arrayList.add(videoInfo);
            } else if (videoInfo.getChnId() == 2) {
                arrayList2.add(videoInfo);
            }
        }
        arrayList.sort(new Comparator<VideoInfo>() { // from class: com.wanos.media.ui.video.activity.IQYVideoListActivity.1
            @Override // java.util.Comparator
            public int compare(VideoInfo videoInfo2, VideoInfo t1) {
                return t1.getHot().intValue() - videoInfo2.getHot().intValue();
            }
        });
        arrayList2.sort(new Comparator<VideoInfo>() { // from class: com.wanos.media.ui.video.activity.IQYVideoListActivity.2
            @Override // java.util.Comparator
            public int compare(VideoInfo videoInfo2, VideoInfo t1) {
                return t1.getHot().intValue() - videoInfo2.getHot().intValue();
            }
        });
        IQYVideoCache.setMoviesList(arrayList);
        IQYVideoCache.setTvList(arrayList2);
        runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.video.activity.IQYVideoListActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (IQYVideoListActivity.this.chnId == 1) {
                    IQYVideoListActivity.this.homeMoviesListAdapter.setData(arrayList);
                } else {
                    IQYVideoListActivity.this.homeMoviesListAdapter.setData(arrayList2);
                }
                IQYVideoListActivity.this.homeMoviesListAdapter.notifyDataSetChanged();
            }
        });
    }
}
