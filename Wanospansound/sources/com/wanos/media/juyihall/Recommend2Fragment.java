package com.wanos.media.juyihall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.DailyExpiredEvent;
import com.wanos.commonlibrary.manager.AppViewModelProviders;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.juyihall.adapter.Recommend2Adapter;
import com.wanos.media.juyihall.databinding.FragmentRecommend2Binding;
import com.wanos.media.viewmodel.PlayerSourceViewModel;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class Recommend2Fragment extends WanosBaseFragment {
    private static final String TAG = "Recommend2Fragment";
    private Recommend2Adapter adapter;
    private FragmentRecommend2Binding binding;
    private PlayerSourceViewModel sourceViewModel;
    private Recommend2ViewModel viewModel;

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.viewModel = (Recommend2ViewModel) new ViewModelProvider(getActivity()).get(Recommend2ViewModel.class);
        this.sourceViewModel = (PlayerSourceViewModel) AppViewModelProviders.getApplicationViewModel(this, PlayerSourceViewModel.class);
        this.binding = FragmentRecommend2Binding.inflate(layoutInflater, viewGroup, false);
        if (this.viewModel.recommendList.isEmpty()) {
            showLoadingView();
        }
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        Recommend2Adapter recommend2Adapter = new Recommend2Adapter(this.viewModel);
        this.adapter = recommend2Adapter;
        recommend2Adapter.setData(this.viewModel.recommendList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.binding.recyclerView.setAdapter(this.adapter);
        this.binding.recyclerView.setLayoutManager(linearLayoutManager);
        this.binding.recyclerView.setItemAnimator(null);
        this.viewModel.listRefreshLive.observe(this, new Observer() { // from class: com.wanos.media.juyihall.Recommend2Fragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m474lambda$initView$0$comwanosmediajuyihallRecommend2Fragment((Integer) obj);
            }
        });
        this.viewModel.loadErrorLive.observe(this, new Observer() { // from class: com.wanos.media.juyihall.Recommend2Fragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m476lambda$initView$2$comwanosmediajuyihallRecommend2Fragment((String) obj);
            }
        });
        this.sourceViewModel.getLiveMediaInfo().observe(this, new Observer() { // from class: com.wanos.media.juyihall.Recommend2Fragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m477lambda$initView$3$comwanosmediajuyihallRecommend2Fragment((MediaInfo) obj);
            }
        });
        this.sourceViewModel.getPlayStatus().observe(this, new Observer() { // from class: com.wanos.media.juyihall.Recommend2Fragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m478lambda$initView$4$comwanosmediajuyihallRecommend2Fragment((MediaPlayerEnum.CallBackState) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-juyihall-Recommend2Fragment, reason: not valid java name */
    /* synthetic */ void m474lambda$initView$0$comwanosmediajuyihallRecommend2Fragment(Integer num) {
        if (num.intValue() == -1) {
            if (!this.viewModel.recommendList.isEmpty()) {
                closeLoadingView();
            }
            this.adapter.setData(this.viewModel.recommendList);
            this.adapter.notifyDataSetChanged();
            return;
        }
        this.adapter.notifyItemChanged(num.intValue());
    }

    /* JADX INFO: renamed from: lambda$initView$2$com-wanos-media-juyihall-Recommend2Fragment, reason: not valid java name */
    /* synthetic */ void m476lambda$initView$2$comwanosmediajuyihallRecommend2Fragment(String str) {
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.juyihall.Recommend2Fragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m475lambda$initView$1$comwanosmediajuyihallRecommend2Fragment(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-wanos-media-juyihall-Recommend2Fragment, reason: not valid java name */
    /* synthetic */ void m475lambda$initView$1$comwanosmediajuyihallRecommend2Fragment(View view) {
        Log.i(TAG, "initView: load error, reload 点击重试");
        this.viewModel.requestData();
    }

    /* JADX INFO: renamed from: lambda$initView$3$com-wanos-media-juyihall-Recommend2Fragment, reason: not valid java name */
    /* synthetic */ void m477lambda$initView$3$comwanosmediajuyihallRecommend2Fragment(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return;
        }
        this.viewModel.setCurrentPlayId(mediaInfo);
    }

    /* JADX INFO: renamed from: lambda$initView$4$com-wanos-media-juyihall-Recommend2Fragment, reason: not valid java name */
    /* synthetic */ void m478lambda$initView$4$comwanosmediajuyihallRecommend2Fragment(MediaPlayerEnum.CallBackState callBackState) {
        this.viewModel.updatePlayStatus(callBackState);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.viewModel.requestData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListDataExpired(DailyExpiredEvent dailyExpiredEvent) {
        Log.i(TAG, "list expired, reload and play");
        Recommend2ViewModel recommend2ViewModel = this.viewModel;
        if (recommend2ViewModel != null) {
            recommend2ViewModel.requestDaily();
        }
    }
}
