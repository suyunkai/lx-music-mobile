package com.wanos.media.ui.audiobook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityAudioBookAlbumListBinding;
import com.wanos.media.presenter.AudioBookAlbumListPresenter;
import com.wanos.media.presenter.IABCallBack;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListAdapter;
import com.wanos.media.view.AudioBookAlbumListView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumListActivity extends WanosBaseActivity<AudioBookAlbumListPresenter> implements AudioBookAlbumListView, View.OnClickListener {
    public static TYPE listType = TYPE.ALL;
    private static final int pageSize = 28;
    public static String strType = "type";
    private List<AudioBookAlbumInfoBean> audioBookAlbumInfoBeanList;
    private AudioBookAlbumListAdapter audioBookAlbumListAdapter;
    private ActivityAudioBookAlbumListBinding binding;
    private int curPage = 1;

    public enum TYPE {
        ALL,
        CHILDREN
    }

    public void getData(int page) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    protected void initListener() {
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

    static /* synthetic */ int access$008(AudioBookAlbumListActivity audioBookAlbumListActivity) {
        int i = audioBookAlbumListActivity.curPage;
        audioBookAlbumListActivity.curPage = i + 1;
        return i;
    }

    public static void build(Context context, TYPE type) {
        Intent intent = new Intent(context, (Class<?>) AudioBookAlbumListActivity.class);
        intent.putExtra(strType, type);
        context.startActivity(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAudioBookAlbumListBinding activityAudioBookAlbumListBindingInflate = ActivityAudioBookAlbumListBinding.inflate(getLayoutInflater());
        this.binding = activityAudioBookAlbumListBindingInflate;
        setContentView(activityAudioBookAlbumListBindingInflate.getRoot());
        initData();
        initView();
    }

    protected void initData() {
        showLoadingView("正在加载中");
        listType = (TYPE) getIntent().getSerializableExtra(strType);
        this.mPresenter = new AudioBookAlbumListPresenter(this, this);
        this.audioBookAlbumInfoBeanList = new ArrayList();
        ((AudioBookAlbumListPresenter) this.mPresenter).requestAlbumList(this.curPage, 28, 0, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumListActivity.1
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(boolean isSuccess) {
                AudioBookAlbumListActivity.this.closeLoadingView();
                if (isSuccess) {
                    AudioBookAlbumListActivity.access$008(AudioBookAlbumListActivity.this);
                } else {
                    AudioBookAlbumListActivity.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumListActivity.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            AudioBookAlbumListActivity.this.initData();
                        }
                    });
                }
            }
        });
    }

    protected void initView() {
        setTitleText(R.string.ab_home_all);
        initRecyclerView();
        setCallBackState();
    }

    private void initRecyclerView() {
        this.binding.audiobookList.setLayoutManager(new GridLayoutManager(this, 7));
        this.audioBookAlbumListAdapter = new AudioBookAlbumListAdapter(this, this.audioBookAlbumInfoBeanList);
        this.binding.audiobookList.setAdapter(this.audioBookAlbumListAdapter);
        initRefreshView();
    }

    private void initRefreshView() {
        this.binding.audiobookRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumListActivity.2
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefresh();
                AudioBookAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ((AudioBookAlbumListPresenter) AudioBookAlbumListActivity.this.mPresenter).requestAlbumList(AudioBookAlbumListActivity.this.curPage, 28, 0, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumListActivity.2.1
                    @Override // com.wanos.media.presenter.IABCallBack
                    public void requestFinish(boolean isSuccess) {
                        if (isSuccess) {
                            AudioBookAlbumListActivity.access$008(AudioBookAlbumListActivity.this);
                        }
                        AudioBookAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefresh();
                        AudioBookAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
                    }
                });
            }
        });
    }

    @Override // com.wanos.media.view.AudioBookAlbumListView
    public void updateView(List<AudioBookAlbumInfoBean> beanList, boolean isLoadMore) {
        if (isFinishing()) {
            return;
        }
        if (isLoadMore) {
            Log.d(WanosBaseActivity.TAG, "binding.audiobookRefreshLayout.setLoadMore(true)");
            this.binding.audiobookRefreshLayout.setLoadMore(true);
        } else {
            Log.d(WanosBaseActivity.TAG, "binding.audiobookRefreshLayout.setLoadMore(false)");
            this.binding.audiobookRefreshLayout.setLoadMore(false);
        }
        if (beanList.size() == 0) {
            this.binding.audiobookRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
        } else {
            this.binding.audiobookRefreshLayout.setVisibility(0);
            this.binding.emptyView.getRoot().setVisibility(8);
            this.audioBookAlbumInfoBeanList = beanList;
            this.audioBookAlbumListAdapter.setData(beanList);
            this.audioBookAlbumListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            setCallBackState();
        }
    }

    public void setCallBackState() {
        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
        this.audioBookAlbumListAdapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), playingItemBean != null ? playingItemBean.getRadioId() : -1L);
    }
}
