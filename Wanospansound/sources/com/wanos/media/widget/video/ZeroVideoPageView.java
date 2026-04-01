package com.wanos.media.widget.video;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import com.blankj.utilcode.util.BarUtils;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.InfiniteLoopAdapter;
import com.wanos.media.widget.video.ZeroVideoViewModel;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.CustomVideoPageBinding;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroVideoPageView extends FrameLayout implements ZeroVideoViewModel.IThemePageChangeListener {
    private static final String TAG = "ZeroVideoPageView";
    private IPageChangeListener iPageChangeListener;
    private InfiniteLoopAdapter mAdapter;
    private final ExoPlayer mExoPlayer;
    private final Handler mMainHandler;
    private int mTempPosition;
    private final Player.Listener mVideoPlayListener;
    private final CustomVideoPageBinding mViewBinding;
    private final ZeroVideoViewModel mViewModel;
    private final ZeroPageChangeCallback mZeroPageChangeCallback;

    public interface IPageChangeListener {
        void onError(String str);

        void onPageChange(ZeroThemeInfo zeroThemeInfo);

        void onSceneDuration(String[] strArr);

        void onSceneInfoBeReady(AudioSceneInfoEntity audioSceneInfoEntity);
    }

    public interface ISceneLoadListener {
        void onSceneLoadError(String str);

        void onSceneLoaded(AudioSceneInfoEntity audioSceneInfoEntity);
    }

    public ZeroVideoPageView(Context context) {
        this(context, null);
    }

    public ZeroVideoPageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ZeroVideoPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempPosition = -1;
        this.mVideoPlayListener = new Player.Listener() { // from class: com.wanos.media.widget.video.ZeroVideoPageView.1
            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int i2) {
                if (i2 == 1) {
                    ZeroLogcatTools.i(ZeroVideoPageView.TAG, "视频播放器初始状态");
                    return;
                }
                if (i2 == 2) {
                    ZeroLogcatTools.i(ZeroVideoPageView.TAG, "视频播放器等待数据加载");
                    return;
                }
                if (i2 != 3) {
                    if (i2 != 4) {
                        return;
                    }
                    ZeroLogcatTools.i(ZeroVideoPageView.TAG, "播放器已播放完所有媒体");
                } else {
                    ZeroLogcatTools.i(ZeroVideoPageView.TAG, "视频播放器准备就绪");
                    if (ZeroVideoPageView.this.mViewModel.getNowThemeInfo() == null) {
                        return;
                    }
                    Message messageObtainMessage = ZeroVideoPageView.this.mMainHandler.obtainMessage(3001);
                    messageObtainMessage.obj = Long.valueOf(ZeroVideoPageView.this.mViewModel.getNowThemeInfo().getThemeId());
                    ZeroVideoPageView.this.mMainHandler.sendMessageDelayed(messageObtainMessage, 100L);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException playbackException) {
                ZeroLogcatTools.e(ZeroVideoPageView.TAG, "onPlayerError: ErrorCodeName = " + playbackException.getErrorCodeName() + ", errorCode = " + playbackException.errorCode);
            }
        };
        ZeroVideoViewModel zeroVideoViewModel = (ZeroVideoViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(ZeroVideoViewModel.class);
        this.mViewModel = zeroVideoViewModel;
        zeroVideoViewModel.setCallback(this);
        CustomVideoPageBinding customVideoPageBindingInflate = CustomVideoPageBinding.inflate(LayoutInflater.from(context), this);
        this.mViewBinding = customVideoPageBindingInflate;
        customVideoPageBindingInflate.wanosVideo.setResizeMode(4);
        int dimension = (int) (getResources().getDimension(R.dimen.zero_top_action_inter_merge_top) + BarUtils.getStatusBarHeight());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) customVideoPageBindingInflate.wanosIndicator.getLayoutParams();
        layoutParams.topMargin = dimension;
        customVideoPageBindingInflate.wanosIndicator.setLayoutParams(layoutParams);
        this.mMainHandler = new Handler(Looper.getMainLooper(), new ZeroVideoHandlerCallback(this));
        this.mZeroPageChangeCallback = new ZeroPageChangeCallback(this);
        customVideoPageBindingInflate.wanosVideo.setUseController(false);
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(getContext()).build();
        this.mExoPlayer = exoPlayerBuild;
        exoPlayerBuild.setPlaybackParameters(new PlaybackParameters(1.0f, 1.0E-7f));
        exoPlayerBuild.setRepeatMode(1);
        exoPlayerBuild.setTrackSelectionParameters(exoPlayerBuild.getTrackSelectionParameters().buildUpon().setTrackTypeDisabled(1, true).build());
        customVideoPageBindingInflate.wanosVideo.setPlayer(exoPlayerBuild);
        LiveData<Integer> liveData = zeroVideoViewModel.indicatorVisibilityState;
        LifecycleOwner lifecycleOwner = (LifecycleOwner) context;
        final WanosIndicatorView wanosIndicatorView = customVideoPageBindingInflate.wanosIndicator;
        Objects.requireNonNull(wanosIndicatorView);
        liveData.observe(lifecycleOwner, new Observer() { // from class: com.wanos.media.widget.video.ZeroVideoPageView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                wanosIndicatorView.setVisibility(((Integer) obj).intValue());
            }
        });
        zeroVideoViewModel.backgroundVideoUri.observe(lifecycleOwner, new Observer() { // from class: com.wanos.media.widget.video.ZeroVideoPageView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m667lambda$new$0$comwanosmediawidgetvideoZeroVideoPageView((Uri) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-widget-video-ZeroVideoPageView, reason: not valid java name */
    /* synthetic */ void m667lambda$new$0$comwanosmediawidgetvideoZeroVideoPageView(Uri uri) {
        this.mViewBinding.ivGif.setVisibility(4);
        this.mViewBinding.wanosVideo.setVisibility(0);
        ZeroLogcatTools.i(TAG, "背景视频缓存结束，准备播放背景视频");
        this.mExoPlayer.setMediaItem(MediaItem.fromUri(uri));
        this.mExoPlayer.prepare();
        this.mExoPlayer.play();
    }

    public void setViewPageEnable(boolean z) {
        this.mViewBinding.wanosPage2.setUserInputEnabled(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ZeroLogcatTools.d(TAG, "onAttachedToWindow");
        ExoPlayer exoPlayer = this.mExoPlayer;
        if (exoPlayer != null) {
            exoPlayer.addListener(this.mVideoPlayListener);
        }
        this.mViewBinding.wanosPage2.registerOnPageChangeCallback(this.mZeroPageChangeCallback);
    }

    public void nextTheme() {
        this.mViewBinding.wanosPage2.getCurrentItem();
        this.mViewBinding.wanosPage2.setCurrentItem(this.mViewBinding.wanosPage2.getCurrentItem() + 1, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ZeroLogcatTools.d(TAG, "onDetachedFromWindow");
        ExoPlayer exoPlayer = this.mExoPlayer;
        if (exoPlayer != null) {
            exoPlayer.removeListener(this.mVideoPlayListener);
            this.mExoPlayer.release();
        }
        this.mViewBinding.wanosPage2.unregisterOnPageChangeCallback(this.mZeroPageChangeCallback);
        this.mMainHandler.removeMessages(3001);
        this.mMainHandler.removeMessages(3002);
    }

    public void onViewResume() {
        this.mViewBinding.wanosVideo.onResume();
    }

    public void onViewPause() {
        this.mViewBinding.wanosVideo.onPause();
    }

    WanosIndicatorView getIndicatorView() {
        return this.mViewBinding.wanosIndicator;
    }

    int getItemCount() {
        return this.mAdapter.getRealCount();
    }

    public void setThemeList(List<ZeroThemeInfo> list, long j, ZeroPageMode zeroPageMode) {
        int themeList = this.mViewModel.setThemeList(list, j, zeroPageMode);
        this.mAdapter = new InfiniteLoopAdapter(this.mViewModel.getThemeList());
        this.mViewBinding.wanosPage2.setAdapter(this.mAdapter);
        this.mViewBinding.wanosIndicator.setCode(this.mAdapter.getRealCount());
        ZeroLogcatTools.d(TAG, "setThemeList: mDefaultIndex = " + themeList + ", ViewPageIndex = " + this.mViewBinding.wanosPage2.getCurrentItem());
        this.mViewBinding.wanosPage2.setCurrentItem(themeList, false);
    }

    void initThemeInfoData(int i) {
        this.mViewModel.doRequestThemeInfo(i);
    }

    public void setRandomScene(ISceneLoadListener iSceneLoadListener) {
        this.mViewModel.setRandomScene(iSceneLoadListener);
    }

    public void setSceneIndex(int i, ISceneLoadListener iSceneLoadListener) {
        this.mViewModel.setSceneIndex(i, iSceneLoadListener);
    }

    public void setIndicatorVisibility(int i) {
        this.mViewModel.setIndicatorState(i);
    }

    public int findSceneIndex(long j) {
        return this.mViewModel.findSceneIndex(j);
    }

    public void setCallback(IPageChangeListener iPageChangeListener) {
        this.iPageChangeListener = iPageChangeListener;
    }

    @Override // com.wanos.media.widget.video.ZeroVideoViewModel.IThemePageChangeListener
    public void onDurationLoaded(String[] strArr) {
        IPageChangeListener iPageChangeListener;
        if (ZeroPageMode.XIAO_QI_PRO == this.mViewModel.getEnterMode() || ZeroPageMode.XIAO_QI_STANDARD == this.mViewModel.getEnterMode() || (iPageChangeListener = this.iPageChangeListener) == null) {
            return;
        }
        iPageChangeListener.onSceneDuration(strArr);
    }

    @Override // com.wanos.media.widget.video.ZeroVideoViewModel.IThemePageChangeListener
    public void onSceneLoaded(AudioSceneInfoEntity audioSceneInfoEntity) {
        IPageChangeListener iPageChangeListener = this.iPageChangeListener;
        if (iPageChangeListener != null) {
            iPageChangeListener.onSceneInfoBeReady(audioSceneInfoEntity);
        }
    }

    @Override // com.wanos.media.widget.video.ZeroVideoViewModel.IThemePageChangeListener
    public void onLoadError(String str) {
        IPageChangeListener iPageChangeListener = this.iPageChangeListener;
        if (iPageChangeListener != null) {
            iPageChangeListener.onError(str);
        }
    }

    ThemeInfoEntity getCurrentThemeInfo() {
        return this.mViewModel.getNowThemeInfo();
    }

    void setCurrentItemState(boolean z) {
        this.mAdapter.setCoveState(this.mViewBinding.wanosPage2.getCurrentItem(), z);
    }

    int getViewPageCurrentPosition() {
        return this.mViewBinding.wanosPage2.getCurrentItem();
    }

    void onViewPageSelected(int i) {
        Log.d(TAG, "onViewPageSelected: NowId = " + this.mViewModel.getNowThemeId() + ", Adapter Id = " + this.mAdapter.getItemData(i).getThemeId() + ", Name = " + this.mAdapter.getItemData(i).getThemeName());
        this.mTempPosition = i;
        if (this.mViewModel.getNowThemeId() != this.mAdapter.getItemData(i).getThemeId()) {
            ZeroLogcatTools.i(TAG, "onViewPageSelected: 选中不同主题，准备加载新主题数据");
            ExoPlayer exoPlayer = this.mExoPlayer;
            if (exoPlayer != null) {
                exoPlayer.pause();
            }
            ZeroAudioBallTools.getInstance().onMediaReset();
            ZeroThemeInfo itemData = this.mAdapter.getItemData(i);
            if (itemData == null) {
                ZeroLogcatTools.e(TAG, "onViewPageSelected: itemData is null");
                return;
            }
            Log.d(TAG, "onViewPageSelected: ThemeId = " + itemData.getThemeId());
            this.mViewModel.setCurrentThemeId((int) itemData.getThemeId());
            IPageChangeListener iPageChangeListener = this.iPageChangeListener;
            if (iPageChangeListener != null) {
                iPageChangeListener.onPageChange(itemData);
            }
            this.mMainHandler.removeMessages(3002);
            this.mMainHandler.removeMessages(3001);
            ZeroThemeDataHelp.getInstance().onPauseDownTask();
            Message messageObtain = Message.obtain(this.mMainHandler, 3002);
            messageObtain.obj = i + "|" + itemData.getThemeId();
            this.mMainHandler.sendMessageDelayed(messageObtain, 1200L);
            return;
        }
        ZeroLogcatTools.w(TAG, "onViewPageSelected: 选中相同主题");
    }

    void onPageScrollStateChanged(int i) {
        this.mViewModel.setPageScrollState(i);
        if (i != 0) {
            if (i == 1) {
                setCurrentItemState(true);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                setCurrentItemState(true);
                return;
            }
        }
        ExoPlayer exoPlayer = this.mExoPlayer;
        if (exoPlayer != null && exoPlayer.isPlaying()) {
            ZeroLogcatTools.i(TAG, "onPageScrollStateChanged: 视频正在播放，显示背景视频");
            setCurrentItemState(false);
        } else {
            ZeroLogcatTools.i(TAG, "onPageScrollStateChanged: 背景视频未播放，不显示背景视频");
        }
    }

    public int getPageScrollState() {
        return this.mViewModel.getPageScrollState();
    }
}
