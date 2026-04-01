package com.wanos.media.ui.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentSearchResultBinding;
import com.wanos.media.ui.search.result.ResultAudioBookFragment;
import com.wanos.media.ui.search.result.ResultMusicFragment;
import com.wanos.media.ui.search.result.ResultMusicGroupFragment;
import com.wanos.media.ui.search.result.adapter.SearchResultPagerAdapter;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.view.IBaseView;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SearchResultFragment extends WanosBaseFragment implements View.OnClickListener, IBaseView {
    private SearchResultPagerAdapter adapter;
    private ResultAudioBookFragment audioBookFragment;
    private FragmentSearchResultBinding binding;
    private Fragment currentFragment;
    private EditText et;
    private InputMethodManager imm;
    private boolean isKeyChange;
    private ResultMusicFragment musicFragment;
    private ResultMusicGroupFragment musicGroupFragment;
    private ResultViewModel viewModel;

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    public SearchResultFragment() {
    }

    public SearchResultFragment(EditText et) {
        this.et = et;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = (ResultViewModel) new ViewModelProvider(getActivity()).get(ResultViewModel.class);
        this.binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initTextWeight();
        initListener();
        initData();
        initVisibleIsSpoken();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.llSong.setContentDescription(getResources().getString(R.string.switch_search_music));
            this.binding.llSongList.setContentDescription(getResources().getString(R.string.switch_search_music_group));
            this.binding.llAudioBook.setContentDescription(getResources().getString(R.string.switch_audiobook));
        }
    }

    private void initTextWeight() {
        Util.setTextWeight(this.binding.tvSongText, 500);
        Util.setTextWeight(this.binding.tvSongListText, 500);
        Util.setTextWeight(this.binding.tvAudioVideo, 500);
    }

    private void initData() {
        initMusicFragment();
        initMusicGroupFragment();
        initAudioBookFragment();
        final List listAsList = Arrays.asList(this.musicFragment, this.musicGroupFragment, this.audioBookFragment);
        this.viewModel.pageNum1.observe(this, new Observer() { // from class: com.wanos.media.ui.search.SearchResultFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m539lambda$initData$0$comwanosmediauisearchSearchResultFragment((Integer) obj);
            }
        });
        this.viewModel.keyword.observe(this, new Observer() { // from class: com.wanos.media.ui.search.SearchResultFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m540lambda$initData$1$comwanosmediauisearchSearchResultFragment((String) obj);
            }
        });
        this.adapter = new SearchResultPagerAdapter(getActivity(), listAsList);
        this.binding.viewPager.setAdapter(this.adapter);
        this.binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.media.ui.search.SearchResultFragment.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (SearchResultFragment.this.viewModel.pageNum1.getValue().intValue() != position) {
                    SearchResultFragment.this.viewModel.pageNum1.setValue(Integer.valueOf(position));
                }
                SearchResultFragment searchResultFragment = SearchResultFragment.this;
                searchResultFragment.currentFragment = (Fragment) listAsList.get(searchResultFragment.viewModel.pageNum1.getValue().intValue());
                SearchResultFragment searchResultFragment2 = SearchResultFragment.this;
                searchResultFragment2.updateTabSelection(searchResultFragment2.viewModel.pageNum1.getValue().intValue());
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initData$0$com-wanos-media-ui-search-SearchResultFragment, reason: not valid java name */
    /* synthetic */ void m539lambda$initData$0$comwanosmediauisearchSearchResultFragment(Integer num) {
        updateTabSelection(num.intValue());
        if (this.isKeyChange || this.viewModel.isListNull(num.intValue())) {
            this.viewModel.requestData();
            this.isKeyChange = false;
        }
        this.binding.viewPager.setCurrentItem(num.intValue(), false);
    }

    /* JADX INFO: renamed from: lambda$initData$1$com-wanos-media-ui-search-SearchResultFragment, reason: not valid java name */
    /* synthetic */ void m540lambda$initData$1$comwanosmediauisearchSearchResultFragment(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.isKeyChange = true;
        if (this.viewModel.pageNum1.getValue().intValue() == this.binding.viewPager.getCurrentItem()) {
            ResultViewModel resultViewModel = this.viewModel;
            if (resultViewModel.isListNull(resultViewModel.pageNum1.getValue().intValue())) {
                return;
            }
            this.viewModel.requestData();
            ResultViewModel resultViewModel2 = this.viewModel;
            resultViewModel2.resetOtherList(resultViewModel2.pageNum1.getValue().intValue());
            this.isKeyChange = false;
        }
    }

    private void hideSoftInput() {
        if (this.et.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
            this.et.clearFocus();
        }
    }

    public void initMusicFragment() {
        if (this.musicFragment == null) {
            this.musicFragment = new ResultMusicFragment(this.et);
        }
    }

    public void initMusicGroupFragment() {
        if (this.musicGroupFragment == null) {
            this.musicGroupFragment = new ResultMusicGroupFragment(this.et);
        }
    }

    public void initAudioBookFragment() {
        if (this.audioBookFragment == null) {
            this.audioBookFragment = new ResultAudioBookFragment(this.et);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTabSelection(int position) {
        this.binding.llSong.setSelected(position == 0);
        this.binding.llSongList.setSelected(position == 1);
        this.binding.llAudioBook.setSelected(position == 2);
    }

    private void initListener() {
        this.binding.llSong.setOnClickListener(this);
        this.binding.llSongList.setOnClickListener(this);
        this.binding.llAudioBook.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        hideSoftInput();
        switch (view.getId()) {
            case R.id.ll_audio_book /* 2131362528 */:
                if (!this.binding.llAudioBook.isSelected()) {
                    this.binding.viewPager.setCurrentItem(2, false);
                }
                break;
            case R.id.ll_song /* 2131362567 */:
                if (!this.binding.llSong.isSelected()) {
                    this.binding.viewPager.setCurrentItem(0, false);
                }
                break;
            case R.id.ll_song_list /* 2131362568 */:
                if (!this.binding.llSongList.isSelected()) {
                    this.binding.viewPager.setCurrentItem(1, false);
                }
                break;
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        Fragment fragment = this.currentFragment;
        if (fragment == null || !(fragment instanceof WanosBaseFragment)) {
            return;
        }
        ((WanosBaseFragment) fragment).onMediaInfoCallbackAppNext(type, mediaInfo);
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        Fragment fragment = this.currentFragment;
        if (fragment == null || !fragment.isResumed()) {
            return;
        }
        Fragment fragment2 = this.currentFragment;
        if (fragment2 instanceof WanosBaseFragment) {
            ((WanosBaseFragment) fragment2).onStatusonStatusCallbackNext(status, args);
        }
    }
}
