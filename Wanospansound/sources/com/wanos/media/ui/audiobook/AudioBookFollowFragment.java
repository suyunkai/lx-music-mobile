package com.wanos.media.ui.audiobook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.event.AudioBookCollectEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentAudioBookFollowBinding;
import com.wanos.media.presenter.AudioBookMinePresenter;
import com.wanos.media.presenter.IABCallBack;
import com.wanos.media.ui.audiobook.adapter.AudioBookMineFollowListAdapter;
import com.wanos.media.view.AudioBookMineView;
import com.wanos.viewmodel.AudioBookFollowFragmentViewModel;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookFollowFragment extends WanosBaseFragment<AudioBookMinePresenter> implements AudioBookMineView {
    public static final String TAG = "wanos:[AudioBookFollowFragment]";
    private static final int pageSize = 30;
    private FragmentAudioBookFollowBinding binding;
    private AudioBookMineFollowListAdapter listAdapter;
    private AudioBookFollowFragmentViewModel viewModel;
    private final List<AudioBookAlbumInfoBean> beanList = new ArrayList();
    private int curPage = 1;
    private boolean needUpdate = false;

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initListener() {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updateChapterView(List<AudioBookMineChapterItemBean> list, boolean isLoadMore) {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updatePlayedView(List<AudioBookMineChapterItemBean> list, boolean isForce) {
    }

    static /* synthetic */ int access$108(AudioBookFollowFragment audioBookFollowFragment) {
        int i = audioBookFollowFragment.curPage;
        audioBookFollowFragment.curPage = i + 1;
        return i;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = (AudioBookFollowFragmentViewModel) new ViewModelProvider(getActivity()).get(AudioBookFollowFragmentViewModel.class);
        FragmentAudioBookFollowBinding fragmentAudioBookFollowBindingInflate = FragmentAudioBookFollowBinding.inflate(inflater, container, false);
        this.binding = fragmentAudioBookFollowBindingInflate;
        FrameLayout root = fragmentAudioBookFollowBindingInflate.getRoot();
        this.mPresenter = new AudioBookMinePresenter(getContext(), this);
        initData();
        initView();
        initListener();
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.viewModel.setSaveInstanceState(this.binding.audiobookMineFollowList.getLayoutManager().onSaveInstanceState());
        this.viewModel.setCurPage(this.curPage);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setCallBackState();
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initData() {
        this.curPage = 1;
        AudioBookGlobalParams.getInstance().setCollectAlbumIsUpdate(false);
        showLoadingView();
        ((AudioBookMinePresenter) this.mPresenter).requestAlbumListData(this.curPage, 30, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.AudioBookFollowFragment.1
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(boolean isSuccess) {
                if (isSuccess) {
                    return;
                }
                AudioBookFollowFragment.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.AudioBookFollowFragment.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        AudioBookFollowFragment.this.initData();
                    }
                });
            }
        });
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initView() {
        this.binding.emptyView.getRoot().setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        this.binding.audiobookMineFollowList.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        this.listAdapter = new AudioBookMineFollowListAdapter(getContext(), this.beanList);
        this.binding.audiobookMineFollowList.setAdapter(this.listAdapter);
        initRefreshView();
        setCallBackState();
    }

    private void initRefreshView() {
        this.binding.audiobookRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.audiobook.AudioBookFollowFragment.2
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookFollowFragment.this.binding.audiobookRefreshLayout.finishRefresh();
                AudioBookFollowFragment.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookFollowFragment.access$108(AudioBookFollowFragment.this);
                ((AudioBookMinePresenter) AudioBookFollowFragment.this.mPresenter).requestAlbumListData(AudioBookFollowFragment.this.curPage, 30, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.AudioBookFollowFragment.2.1
                    @Override // com.wanos.media.presenter.IABCallBack
                    public void requestFinish(boolean isSuccess) {
                        AudioBookFollowFragment.this.binding.audiobookRefreshLayout.finishRefresh();
                        AudioBookFollowFragment.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
                    }
                });
            }
        });
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updateAlbumView(List<AudioBookAlbumInfoBean> list, boolean isLoadMore) {
        FragmentAudioBookFollowBinding fragmentAudioBookFollowBinding = this.binding;
        if (fragmentAudioBookFollowBinding == null || fragmentAudioBookFollowBinding.audiobookRefreshLayout == null) {
            return;
        }
        this.binding.audiobookRefreshLayout.setLoadMore(isLoadMore);
        if (list.size() == 0) {
            Log.i(TAG, "updateAlbumView: 0");
            this.binding.audiobookRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
            closeLoadingView();
            return;
        }
        this.binding.audiobookRefreshLayout.setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
        this.listAdapter.setData(list);
        this.listAdapter.notifyDataSetChanged();
        Parcelable saveInstanceState = this.viewModel.getSaveInstanceState();
        if (saveInstanceState == null) {
            closeLoadingView();
            Log.i(TAG, "updateAlbumView: 1");
        } else {
            if (saveInstanceState != null && this.viewModel.getCurPage() == this.curPage) {
                closeLoadingView();
                this.binding.audiobookMineFollowList.getLayoutManager().onRestoreInstanceState(saveInstanceState);
                this.viewModel.setSaveInstanceState(null);
                this.viewModel.setCurPage(1);
                Log.i(TAG, "updateAlbumView: 2");
                return;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.wanos.media.ui.audiobook.AudioBookFollowFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    AudioBookFollowFragment.this.closeLoadingView();
                    Log.i(AudioBookFollowFragment.TAG, "updateAlbumView: 3");
                }
            }, 500L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        setCallBackState();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectChange(AudioBookCollectEvent event) {
        AudioBookGlobalParams.getInstance().isCollectAlbumIsUpdate();
        this.needUpdate = true;
        initData();
    }

    public void setCallBackState() {
        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
        this.listAdapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), playingItemBean != null ? playingItemBean.getRadioId() : -1L);
    }
}
