package com.wanos.media.ui.audiobook;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.AudioBookCollectEvent;
import com.wanos.commonlibrary.event.AudioBookCollectUpdateEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentAudioBookLikeChapterBinding;
import com.wanos.media.presenter.AudioBookMinePresenter;
import com.wanos.media.presenter.IABCallBack;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListMineAdapter;
import com.wanos.media.view.AudioBookMineView;
import com.wanos.viewmodel.AudioBookLikeChapterFragmentViewModel;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookLikeChapterFragment extends WanosBaseFragment<AudioBookMinePresenter> implements AudioBookMineView {
    public static final String TAG = "wanos:[AudioBookLikeChapterFragment]";
    private static final int pageSize = 30;
    private AudioBookChapterListMineAdapter audioBookMineChapterListAdapter;
    private FragmentAudioBookLikeChapterBinding binding;
    private AudioBookLikeChapterFragmentViewModel viewModel;
    private List<AudioBookMineChapterItemBean> audioBookChapterItemBeanList = new ArrayList();
    private int curPage = 1;

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
    public void updateAlbumView(List<AudioBookAlbumInfoBean> list, boolean isLoadMore) {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updatePlayedView(List<AudioBookMineChapterItemBean> list, boolean isForce) {
    }

    static /* synthetic */ int access$108(AudioBookLikeChapterFragment audioBookLikeChapterFragment) {
        int i = audioBookLikeChapterFragment.curPage;
        audioBookLikeChapterFragment.curPage = i + 1;
        return i;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = (AudioBookLikeChapterFragmentViewModel) new ViewModelProvider(getActivity()).get(AudioBookLikeChapterFragmentViewModel.class);
        FragmentAudioBookLikeChapterBinding fragmentAudioBookLikeChapterBindingInflate = FragmentAudioBookLikeChapterBinding.inflate(inflater, container, false);
        this.binding = fragmentAudioBookLikeChapterBindingInflate;
        FrameLayout root = fragmentAudioBookLikeChapterBindingInflate.getRoot();
        this.mPresenter = new AudioBookMinePresenter(getContext(), this);
        initData();
        initView();
        initListener();
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.viewModel.setRvState(this.binding.audiobookMineChapterList.getLayoutManager().onSaveInstanceState());
        this.viewModel.setPage(this.curPage);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setCallBackState();
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initData() {
        this.curPage = 1;
        AudioBookGlobalParams.getInstance().setLikeChapterIsUpdate(false);
        showLoadingView();
        ((AudioBookMinePresenter) this.mPresenter).requestLikeChapterData(this.curPage, 30, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment.1
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(boolean isSuccess) {
                if (isSuccess) {
                    return;
                }
                AudioBookLikeChapterFragment.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        AudioBookLikeChapterFragment.this.initData();
                    }
                });
            }
        });
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initView() {
        this.binding.emptyView.getRoot().setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        this.binding.audiobookMineChapterList.setLayoutManager(new LinearLayoutManager(getContext()));
        AudioBookChapterListMineAdapter audioBookChapterListMineAdapter = new AudioBookChapterListMineAdapter(getContext(), this.audioBookChapterItemBeanList);
        this.audioBookMineChapterListAdapter = audioBookChapterListMineAdapter;
        audioBookChapterListMineAdapter.setCollect(true);
        this.audioBookMineChapterListAdapter.setType(AudioBookChapterListMineAdapter.MineType.LikeChapter);
        this.binding.audiobookMineChapterList.setAdapter(this.audioBookMineChapterListAdapter);
        initRefreshView();
        setCallBackState();
    }

    private void initRefreshView() {
        this.binding.audiobookRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment.2
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookLikeChapterFragment.this.binding.audiobookRefreshLayout.finishRefresh();
                AudioBookLikeChapterFragment.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookLikeChapterFragment.access$108(AudioBookLikeChapterFragment.this);
                ((AudioBookMinePresenter) AudioBookLikeChapterFragment.this.mPresenter).requestLikeChapterData(AudioBookLikeChapterFragment.this.curPage, 30, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment.2.1
                    @Override // com.wanos.media.presenter.IABCallBack
                    public void requestFinish(boolean isSuccess) {
                        if (AudioBookLikeChapterFragment.this.isVisible()) {
                            AudioBookLikeChapterFragment.this.binding.audiobookRefreshLayout.finishRefresh();
                            AudioBookLikeChapterFragment.this.binding.audiobookRefreshLayout.finishRefreshLoadMore();
                        }
                    }
                });
            }
        });
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updateChapterView(List<AudioBookMineChapterItemBean> list, boolean isLoadMore) {
        FragmentAudioBookLikeChapterBinding fragmentAudioBookLikeChapterBinding = this.binding;
        if (fragmentAudioBookLikeChapterBinding == null || fragmentAudioBookLikeChapterBinding.audiobookRefreshLayout == null) {
            return;
        }
        this.binding.audiobookRefreshLayout.setLoadMore(isLoadMore);
        if (list.size() == 0) {
            this.binding.audiobookRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
            closeLoadingView();
            return;
        }
        this.audioBookChapterItemBeanList.clear();
        this.audioBookChapterItemBeanList.addAll(list);
        this.binding.audiobookRefreshLayout.setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
        this.audioBookMineChapterListAdapter.setData(list);
        this.audioBookMineChapterListAdapter.notifyDataSetChanged();
        Parcelable rvState = this.viewModel.getRvState();
        if (rvState == null) {
            closeLoadingView();
            return;
        }
        if (rvState == null || this.viewModel.getPage() != this.curPage) {
            return;
        }
        closeLoadingView();
        this.binding.audiobookMineChapterList.getLayoutManager().onRestoreInstanceState(rvState);
        this.viewModel.setRvState(null);
        this.viewModel.setPage(1);
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
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        if (mediaInfo == null || this.audioBookMineChapterListAdapter == null || this.audioBookChapterItemBeanList == null) {
            return;
        }
        AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
        long id = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook || audioBookInfoBean == null) ? -1L : audioBookInfoBean.getId();
        if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || id == -1) {
            return;
        }
        if (this.audioBookChapterItemBeanList.size() == 0) {
            if (audioBookInfoBean.getIsCollect() == 1) {
                this.audioBookChapterItemBeanList.add(0, audioBookInfoBean);
                this.audioBookMineChapterListAdapter.notifyDataSetChanged();
            }
        } else {
            int i = -1;
            for (int i2 = 0; i2 < this.audioBookChapterItemBeanList.size(); i2++) {
                AudioBookMineChapterItemBean audioBookMineChapterItemBean = this.audioBookChapterItemBeanList.get(i2);
                if (audioBookMineChapterItemBean != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && audioBookMineChapterItemBean.getId() == id) {
                    i = i2;
                }
            }
            if (audioBookInfoBean.getIsCollect() == 1) {
                if (i <= -1) {
                    this.audioBookChapterItemBeanList.add(0, audioBookInfoBean);
                    this.audioBookMineChapterListAdapter.notifyDataSetChanged();
                }
            } else if (i > -1) {
                this.audioBookChapterItemBeanList.remove(i);
                this.audioBookMineChapterListAdapter.notifyDataSetChanged();
            }
        }
        if (this.audioBookChapterItemBeanList.size() == 0) {
            this.binding.audiobookRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
        } else {
            this.binding.audiobookRefreshLayout.setVisibility(0);
            this.binding.emptyView.getRoot().setVisibility(8);
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        setCallBackState();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioBookCollectEvent(AudioBookCollectUpdateEvent event) {
        initData();
        Log.i(TAG, "AudioBookCollectUpdateEvent: ");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectChange(AudioBookCollectEvent event) {
        if (event.isFromBar() && AudioBookGlobalParams.getInstance().isLikeChapterIsUpdate()) {
            initData();
            this.audioBookMineChapterListAdapter.setFromBar(false);
        }
        if (event.getAudioBookId() != -1) {
            long audioBookId = event.getAudioBookId();
            if (this.audioBookChapterItemBeanList.size() != 0) {
                int i = -1;
                for (int i2 = 0; i2 < this.audioBookChapterItemBeanList.size(); i2++) {
                    AudioBookMineChapterItemBean audioBookMineChapterItemBean = this.audioBookChapterItemBeanList.get(i2);
                    if (audioBookMineChapterItemBean != null && audioBookMineChapterItemBean.getId() == audioBookId) {
                        i = i2;
                    }
                }
                if (i > -1) {
                    this.audioBookChapterItemBeanList.remove(i);
                    this.audioBookMineChapterListAdapter.notifyDataSetChanged();
                }
                if (this.audioBookChapterItemBeanList.size() == 0) {
                    this.binding.audiobookRefreshLayout.setVisibility(8);
                    this.binding.emptyView.getRoot().setVisibility(0);
                    this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
                } else {
                    this.binding.audiobookRefreshLayout.setVisibility(0);
                    this.binding.emptyView.getRoot().setVisibility(8);
                }
            }
        }
    }

    public void setCallBackState() {
        long radioId;
        long id;
        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
        if (playingItemBean != null) {
            radioId = playingItemBean.getRadioId();
            id = playingItemBean.getId();
        } else {
            radioId = -1;
            id = -1;
        }
        this.audioBookMineChapterListAdapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), radioId, id);
    }
}
