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
import com.wanos.media.databinding.ActivityAudioBookChildrenListBinding;
import com.wanos.media.presenter.AudioBookAlbumListPresenter;
import com.wanos.media.presenter.IABCallBack;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.adapter.AudioBookChildrenAlbumListAdapter;
import com.wanos.media.view.AudioBookAlbumListView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChildrenAlbumListActivity extends WanosBaseActivity<AudioBookAlbumListPresenter> implements AudioBookAlbumListView, View.OnClickListener {
    public static final String TAG = "wanos:[AudioBookChildrenAlbumListActivity]";
    public static final int childTag = 21;
    public static TYPE listType = TYPE.ALL;
    private static final int pageSize = 28;
    public static String strType = "type";
    private AudioBookChildrenAlbumListAdapter adapter;
    private List<AudioBookAlbumInfoBean> audioBookAlbumInfoBeanList;
    private ActivityAudioBookChildrenListBinding binding;
    private int curPage = 1;

    public enum TYPE {
        ALL,
        CHILDREN
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

    static /* synthetic */ int access$008(AudioBookChildrenAlbumListActivity audioBookChildrenAlbumListActivity) {
        int i = audioBookChildrenAlbumListActivity.curPage;
        audioBookChildrenAlbumListActivity.curPage = i + 1;
        return i;
    }

    public static void build(Context context) {
        context.startActivity(new Intent(context, (Class<?>) AudioBookChildrenAlbumListActivity.class));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAudioBookChildrenListBinding activityAudioBookChildrenListBindingInflate = ActivityAudioBookChildrenListBinding.inflate(getLayoutInflater());
        this.binding = activityAudioBookChildrenListBindingInflate;
        setContentView(activityAudioBookChildrenListBindingInflate.getRoot());
        setTitleText(R.string.ab_children);
        initData();
        initView();
    }

    protected void initData() {
        showLoadingView("正在加载中");
        this.audioBookAlbumInfoBeanList = new ArrayList();
        this.mPresenter = new AudioBookAlbumListPresenter(this, this);
        ((AudioBookAlbumListPresenter) this.mPresenter).requestAlbumList(this.curPage, 28, 21, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookChildrenAlbumListActivity.1
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(boolean isSuccess) {
                AudioBookChildrenAlbumListActivity.this.closeLoadingView();
                if (isSuccess) {
                    AudioBookChildrenAlbumListActivity.access$008(AudioBookChildrenAlbumListActivity.this);
                } else {
                    AudioBookChildrenAlbumListActivity.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookChildrenAlbumListActivity.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            AudioBookChildrenAlbumListActivity.this.initData();
                        }
                    });
                }
            }
        });
    }

    protected void initView() {
        initRecyclerView();
        setCallBackState();
    }

    private void initRecyclerView() {
        this.binding.audiobookList.setLayoutManager(new GridLayoutManager(this, 4));
        this.adapter = new AudioBookChildrenAlbumListAdapter(this, this.audioBookAlbumInfoBeanList);
        this.binding.audiobookList.setAdapter(this.adapter);
        initRefreshView();
    }

    private void initRefreshView() {
        this.binding.audiobookRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookChildrenAlbumListActivity.2
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookChildrenAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefresh();
                AudioBookChildrenAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ((AudioBookAlbumListPresenter) AudioBookChildrenAlbumListActivity.this.mPresenter).requestAlbumList(AudioBookChildrenAlbumListActivity.this.curPage, 28, 21, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookChildrenAlbumListActivity.2.1
                    @Override // com.wanos.media.presenter.IABCallBack
                    public void requestFinish(boolean isSuccess) {
                        if (isSuccess) {
                            AudioBookChildrenAlbumListActivity.access$008(AudioBookChildrenAlbumListActivity.this);
                        }
                        AudioBookChildrenAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefresh();
                        AudioBookChildrenAlbumListActivity.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
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
            Log.d(TAG, "binding.audiobookRefreshLayout.setLoadMore(true)");
            this.binding.audiobookRefreshLayout.setLoadMore(true);
        } else {
            Log.d(TAG, "binding.audiobookRefreshLayout.setLoadMore(false)");
            this.binding.audiobookRefreshLayout.setLoadMore(false);
        }
        if (beanList.size() == 0) {
            this.binding.audiobookRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
            return;
        }
        this.binding.audiobookRefreshLayout.setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
        if (this.audioBookAlbumInfoBeanList.size() != beanList.size()) {
            this.audioBookAlbumInfoBeanList = beanList;
            this.adapter.setData(beanList);
            this.adapter.notifyDataSetChanged();
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
        this.adapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), playingItemBean != null ? playingItemBean.getRadioId() : -1L);
    }
}
