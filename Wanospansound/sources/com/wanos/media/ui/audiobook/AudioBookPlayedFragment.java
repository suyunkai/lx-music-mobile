package com.wanos.media.ui.audiobook;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.AudioBookCollectEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentAudioBookPlayedBinding;
import com.wanos.media.presenter.AudioBookMinePresenter;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListMineAdapter;
import com.wanos.media.view.AudioBookMineView;
import com.wanos.viewmodel.AudioBookPlayedFragmentViewModel;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookPlayedFragment extends WanosBaseFragment<AudioBookMinePresenter> implements AudioBookMineView {
    public static final String TAG = "wanos:[AudioBookPlayedFragment]";
    private AudioBookChapterListMineAdapter audioBookMineChapterListAdapter;
    private FragmentAudioBookPlayedBinding binding;
    private AudioBookPlayedFragmentViewModel viewMode;
    private List<AudioBookMineChapterItemBean> audioBookChapterItemBeanList = new ArrayList();
    private boolean isFirstLoad = true;

    @Override // com.wanos.media.view.AudioBookMineView
    public void initListener() {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updateAlbumView(List<AudioBookAlbumInfoBean> list, boolean isLoadMore) {
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updateChapterView(List<AudioBookMineChapterItemBean> list, boolean isLoadMore) {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewMode = (AudioBookPlayedFragmentViewModel) new ViewModelProvider(getActivity()).get(AudioBookPlayedFragmentViewModel.class);
        FragmentAudioBookPlayedBinding fragmentAudioBookPlayedBindingInflate = FragmentAudioBookPlayedBinding.inflate(inflater, container, false);
        this.binding = fragmentAudioBookPlayedBindingInflate;
        FrameLayout root = fragmentAudioBookPlayedBindingInflate.getRoot();
        this.mPresenter = new AudioBookMinePresenter(getContext(), this);
        initData();
        initView();
        initListener();
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.viewMode.setSaveInstanceState(this.binding.audiobookMinePlayedList.getLayoutManager().onSaveInstanceState());
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mPresenter != 0) {
            showLoadingView();
            ((AudioBookMinePresenter) this.mPresenter).requestPlayedData();
        }
        setCallBackState();
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initData() {
        if (this.isFirstLoad) {
            showLoadingView();
            this.isFirstLoad = false;
        }
        ((AudioBookMinePresenter) this.mPresenter).requestPlayedData();
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void initView() {
        this.binding.emptyView.getRoot().setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        this.binding.audiobookMinePlayedList.setLayoutManager(new LinearLayoutManager(getContext()));
        AudioBookChapterListMineAdapter audioBookChapterListMineAdapter = new AudioBookChapterListMineAdapter(getContext(), this.audioBookChapterItemBeanList);
        this.audioBookMineChapterListAdapter = audioBookChapterListMineAdapter;
        audioBookChapterListMineAdapter.setCollect(false);
        this.audioBookMineChapterListAdapter.setType(AudioBookChapterListMineAdapter.MineType.PlayedChapter);
        this.binding.audiobookMinePlayedList.setAdapter(this.audioBookMineChapterListAdapter);
        setCallBackState();
    }

    @Override // com.wanos.media.view.AudioBookMineView
    public void updatePlayedView(List<AudioBookMineChapterItemBean> list, boolean isForce) {
        closeLoadingView();
        if ((isForce || isVisible()) && this.binding != null) {
            if (list.size() == 0) {
                showEmptyView();
                return;
            }
            hideEmptyView(list);
            if (this.viewMode.getSaveInstanceState() != null) {
                this.binding.audiobookMinePlayedList.getLayoutManager().onRestoreInstanceState(this.viewMode.getSaveInstanceState());
                this.viewMode.setSaveInstanceState(null);
            }
        }
    }

    private void showEmptyView() {
        this.binding.audiobookMinePlayedList.setVisibility(8);
        this.binding.emptyView.getRoot().setVisibility(0);
        this.binding.emptyView.tvEmpty.setText(R.string.empty_audiobook);
    }

    private void hideEmptyView(List<AudioBookMineChapterItemBean> list) {
        this.binding.audiobookMinePlayedList.setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
        this.audioBookChapterItemBeanList = list;
        this.audioBookMineChapterListAdapter.setData(list);
        this.audioBookMineChapterListAdapter.notifyDataSetChanged();
    }

    public void updateProgress(long albumId, long chapterId, long progress) {
        for (int i = 0; i < this.audioBookChapterItemBeanList.size(); i++) {
            AudioBookMineChapterItemBean audioBookMineChapterItemBean = this.audioBookChapterItemBeanList.get(i);
            if (audioBookMineChapterItemBean.getRadioId() == albumId) {
                if (audioBookMineChapterItemBean.getId() == chapterId) {
                    this.audioBookMineChapterListAdapter.updateProgress(i, progress);
                } else {
                    initData();
                }
            }
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

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        closeLoadingView();
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.AudioBookPlayedFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioBookPlayedFragment.this.initData();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectChange(AudioBookCollectEvent event) {
        if (event.isCollected()) {
            return;
        }
        Log.i(TAG, "refreshCollectChange: ");
        List<AudioBookMineChapterItemBean> datas = this.audioBookMineChapterListAdapter.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getId() == event.getAudioBookId()) {
                datas.get(i).setIsCollect(0);
                this.audioBookMineChapterListAdapter.notifyItemChanged(i);
                return;
            }
        }
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        long musicId;
        if (mediaInfo == null || this.audioBookChapterItemBeanList == null || this.audioBookMineChapterListAdapter == null) {
            return;
        }
        AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && audioBookInfoBean != null) {
            musicId = audioBookInfoBean.getId();
        } else {
            musicId = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || mediaInfo.getMusicInfoBean() == null || mediaInfo.getMusicInfoBean().getContentType() <= 2) ? -1L : mediaInfo.getMusicInfoBean().getMusicId();
        }
        if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
            if (musicId == -1 || this.audioBookChapterItemBeanList.size() == 0) {
                return;
            }
            for (int i = 0; i < this.audioBookChapterItemBeanList.size(); i++) {
                AudioBookMineChapterItemBean audioBookMineChapterItemBean = this.audioBookChapterItemBeanList.get(i);
                if (audioBookMineChapterItemBean.getId() == musicId) {
                    audioBookMineChapterItemBean.setIsCollect(audioBookInfoBean.getIsCollect());
                    this.audioBookMineChapterListAdapter.notifyItemChanged(i);
                }
            }
            return;
        }
        if (type != MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType || mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || mediaInfo.getMusicInfoBean() == null || mediaInfo.getMusicInfoBean().getContentType() <= 2) {
            return;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.audioBookChapterItemBeanList.size(); i2++) {
            if (this.audioBookChapterItemBeanList.get(i2).getId() == musicId) {
                List<AudioBookMineChapterItemBean> list = this.audioBookChapterItemBeanList;
                list.add(0, list.remove(i2));
                this.audioBookMineChapterListAdapter.notifyDataSetChanged();
                z = true;
            }
        }
        if (z) {
            return;
        }
        hideEmptyView(this.audioBookChapterItemBeanList);
        this.audioBookChapterItemBeanList.add(0, mediaInfo.getAudioBookInfoBean());
        this.audioBookMineChapterListAdapter.notifyItemInserted(0);
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        setCallBackState();
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
