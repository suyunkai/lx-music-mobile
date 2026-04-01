package com.wanos.media.juyihall.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.manager.AppViewModelProviders;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.Recommend2ViewModel;
import com.wanos.media.juyihall.databinding.LayoutRec2DailyMusicBinding;
import com.wanos.media.viewmodel.PlayerSourceViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class DailyMusicView extends ConstraintLayout {
    private static int MAX_ITEM_COUNT = 4;
    private static final String TAG = "wanos:[DailyMusicView]";
    private LayoutRec2DailyMusicBinding binding;
    private DailyAdapter mAdapter;
    private int mCurrentTime;
    private PlayableLayout playableLayout;
    private PlayerSourceViewModel sourceViewModel;
    private Recommend2ViewModel viewModel;

    public DailyMusicView(Context context) {
        super(context);
        this.mCurrentTime = 0;
        init();
    }

    public DailyMusicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentTime = 0;
        init();
    }

    public DailyMusicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentTime = 0;
        init();
    }

    public DailyMusicView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentTime = 0;
        init();
    }

    private void init() {
        MAX_ITEM_COUNT = getResources().getInteger(R.integer.daily_max_count);
        this.sourceViewModel = (PlayerSourceViewModel) AppViewModelProviders.getApplicationViewModel((Activity) getContext(), PlayerSourceViewModel.class);
        this.viewModel = (Recommend2ViewModel) new ViewModelProvider((ViewModelStoreOwner) getContext()).get(Recommend2ViewModel.class);
        this.binding = LayoutRec2DailyMusicBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.playableLayout = (PlayableLayout) findViewById(R.id.btn_music_play);
        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new DailyAdapter();
        this.binding.recyclerView.setAdapter(this.mAdapter);
        this.playableLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.juyihall.view.DailyMusicView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m479lambda$init$0$comwanosmediajuyihallviewDailyMusicView(view);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.wanos.media.juyihall.view.DailyMusicView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PageRouter.toMusicListPage(String.valueOf(-12L));
            }
        };
        this.binding.getRoot().setOnClickListener(onClickListener);
        this.binding.title.setOnClickListener(onClickListener);
        this.sourceViewModel.getPlayStatus().observe((AppCompatActivity) getContext(), new Observer() { // from class: com.wanos.media.juyihall.view.DailyMusicView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m480lambda$init$2$comwanosmediajuyihallviewDailyMusicView((MediaPlayerEnum.CallBackState) obj);
            }
        });
        this.sourceViewModel.getLiveMediaInfo().observe((AppCompatActivity) getContext(), new Observer() { // from class: com.wanos.media.juyihall.view.DailyMusicView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m481lambda$init$3$comwanosmediajuyihallviewDailyMusicView((MediaInfo) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$init$0$com-wanos-media-juyihall-view-DailyMusicView, reason: not valid java name */
    /* synthetic */ void m479lambda$init$0$comwanosmediajuyihallviewDailyMusicView(View view) {
        MediaPlayerEnum.CallBackState playStatus = getPlayStatus();
        if (playStatus == null || playStatus == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.viewModel.playAll(102);
        } else if (playStatus == MediaPlayerEnum.CallBackState.PAUSE || playStatus == MediaPlayerEnum.CallBackState.PREPARE) {
            ServiceRouter.getMediaPlayService().resume();
        } else if (playStatus == MediaPlayerEnum.CallBackState.STARTED) {
            ServiceRouter.getMediaPlayService().pause();
        }
        Log.i("zt", "每日推荐播放按钮埋点点击");
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_DAY_RECOMMEND_QUICK_PLAY_BUTTON, "", "", "", "", 0);
    }

    /* JADX INFO: renamed from: lambda$init$2$com-wanos-media-juyihall-view-DailyMusicView, reason: not valid java name */
    /* synthetic */ void m480lambda$init$2$comwanosmediajuyihallviewDailyMusicView(MediaPlayerEnum.CallBackState callBackState) {
        setCallBackState();
    }

    /* JADX INFO: renamed from: lambda$init$3$com-wanos-media-juyihall-view-DailyMusicView, reason: not valid java name */
    /* synthetic */ void m481lambda$init$3$comwanosmediajuyihallviewDailyMusicView(MediaInfo mediaInfo) {
        setCallBackState();
    }

    public void refresh() {
        if (this.viewModel.dailyList == null || this.viewModel.dailyList.isEmpty()) {
            return;
        }
        this.mAdapter.notifyDataSetChanged();
        GlideUtil.loadImage(this.viewModel.dailyList.get(0).getAvatar(), this.binding.ivAvatar);
    }

    private MediaPlayerEnum.CallBackState getPlayStatus() {
        MediaInfo value = this.sourceViewModel.getLiveMediaInfo().getValue();
        LogUtils.d(TAG, "mediaInfo:" + value);
        if (value == null || value.getMediaGroupType() != -12) {
            return null;
        }
        return this.sourceViewModel.getPlayStatus().getValue();
    }

    private void setCallBackState() {
        MediaPlayerEnum.CallBackState playStatus = getPlayStatus();
        LogUtils.d(TAG, "setCallBackState:" + playStatus);
        if (playStatus == MediaPlayerEnum.CallBackState.PREPARING) {
            this.playableLayout.showAsLoading();
            return;
        }
        if (playStatus == null || playStatus == MediaPlayerEnum.CallBackState.PAUSE || playStatus == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.playableLayout.showAsPause();
        } else if (playStatus == MediaPlayerEnum.CallBackState.STARTED) {
            this.playableLayout.showAsPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DailyAdapter extends RecyclerView.Adapter<DailyHolder> {
        private DailyAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public DailyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_daily, viewGroup, false);
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.juyihall.view.DailyMusicView$DailyAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PageRouter.toMusicListPage(String.valueOf(-12L));
                }
            });
            return new DailyHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(DailyHolder dailyHolder, int i) {
            MusicInfoBean musicInfoBean = DailyMusicView.this.viewModel.dailyList.get(i);
            if (musicInfoBean == null) {
                return;
            }
            String name = musicInfoBean.getName();
            if (musicInfoBean.getSingerList() != null && !musicInfoBean.getSingerList().isEmpty()) {
                name = name + " " + musicInfoBean.getSingerList().get(0).getName();
            }
            dailyHolder.tvName.setText(name);
            dailyHolder.tvName.setSelected(musicInfoBean.getPlayStatus() != null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (DailyMusicView.this.viewModel.dailyList == null) {
                return 0;
            }
            int size = DailyMusicView.this.viewModel.dailyList.size();
            return size > DailyMusicView.MAX_ITEM_COUNT ? DailyMusicView.MAX_ITEM_COUNT : size;
        }
    }

    private static class DailyHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public DailyHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
