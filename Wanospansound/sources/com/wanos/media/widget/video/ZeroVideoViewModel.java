package com.wanos.media.widget.video;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.VideoCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.video.ZeroVideoPageView;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroVideoViewModel extends ViewModel implements IThemeLoadedCallback, ISceneLoadedCallback {
    private static final String TAG = "ZeroVideoViewModel";
    private final MutableLiveData<Uri> _BackgroundVideoUri;
    private final MutableLiveData<Integer> _IndicatorVisibilityState;
    public final LiveData<Uri> backgroundVideoUri;
    private IThemePageChangeListener iThemePageChangeListener;
    public final LiveData<Integer> indicatorVisibilityState;
    private int mCurrentId;
    private AudioSceneInfoEntity mCurrentSceneInfo;
    private ThemeInfoEntity mCurrentThemeInfo;
    private int mPageScrollState;
    private ZeroPageMode mEnterMode = null;
    private String[] mMingXiangDuration = null;
    private int mNowIndex = 0;
    private List<ZeroThemeInfo> mThemeList = null;

    public interface IThemePageChangeListener {
        void onDurationLoaded(String[] strArr);

        void onLoadError(String str);

        void onSceneLoaded(AudioSceneInfoEntity audioSceneInfoEntity);
    }

    public ZeroVideoViewModel() {
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
        this._IndicatorVisibilityState = mutableLiveData;
        this.indicatorVisibilityState = mutableLiveData;
        MutableLiveData<Uri> mutableLiveData2 = new MutableLiveData<>();
        this._BackgroundVideoUri = mutableLiveData2;
        this.backgroundVideoUri = mutableLiveData2;
        this.mPageScrollState = 0;
    }

    public void setPageScrollState(int i) {
        this.mPageScrollState = i;
    }

    public int getPageScrollState() {
        return this.mPageScrollState;
    }

    public void setIndicatorState(int i) {
        this._IndicatorVisibilityState.setValue(Integer.valueOf(i));
    }

    public int setThemeList(List<ZeroThemeInfo> list, long j, ZeroPageMode zeroPageMode) {
        if (this.mEnterMode != null) {
            return this.mNowIndex;
        }
        this.mEnterMode = zeroPageMode;
        this.mThemeList = list;
        if (list.isEmpty()) {
            this.mNowIndex = -1;
            return -1;
        }
        int i = 0;
        if (list.size() == 1) {
            this.mNowIndex = 0;
            return 0;
        }
        int size = (Integer.MAX_VALUE / list.size()) / 2;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (list.get(i).getThemeId() == j) {
                this.mNowIndex = (size * list.size()) + i;
                break;
            }
            i++;
        }
        return this.mNowIndex;
    }

    public void setCurrentThemeId(int i) {
        if (this.mCurrentId == i) {
            return;
        }
        this.mCurrentId = i;
        this.mCurrentThemeInfo = null;
        this.mCurrentSceneInfo = null;
    }

    public void doRequestThemeInfo(int i) {
        ZeroThemeInfo next;
        Iterator<ZeroThemeInfo> it = this.mThemeList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.getThemeId() == i) {
                    break;
                }
            }
        }
        if (next == null) {
            return;
        }
        ZeroThemeDataHelp.getInstance().onLoadThemeData(this.mEnterMode, i, this);
    }

    @Override // com.wanos.media.widget.video.IThemeLoadedCallback
    public void onThemeInfo(ThemeInfoEntity themeInfoEntity) {
        int i;
        if (themeInfoEntity.getThemeId() != this.mCurrentId) {
            ZeroLogcatTools.w(TAG, "onThemeInfo: 加载主题ID = " + themeInfoEntity.getThemeId() + ", 当前选中主题ID = " + this.mCurrentId);
            return;
        }
        this.mCurrentThemeInfo = themeInfoEntity;
        this.mCurrentSceneInfo = null;
        ZeroLogcatTools.i(TAG, themeInfoEntity.getThemeName() + "-主题加载成功~");
        if (this.mEnterMode == ZeroPageMode.MING_XIANG_PRO || this.mEnterMode == ZeroPageMode.MING_XIANG_STANDARD) {
            if (themeInfoEntity.getAudioInfo() == null || themeInfoEntity.getAudioInfo().isEmpty()) {
                i = 5;
            } else {
                List<AudioSceneInfoEntity> audioInfo = themeInfoEntity.getAudioInfo();
                for (int i2 = 0; i2 < audioInfo.size(); i2++) {
                    i = 10;
                    if (audioInfo.get(i2).getDurtion() == 10) {
                        break;
                    }
                }
                i = 5;
            }
            ZeroThemeDataHelp.getInstance().onLoadSceneDataForDuration(this.mEnterMode, this.mCurrentThemeInfo, i, this);
            return;
        }
        ZeroThemeDataHelp.getInstance().onLoadSceneData(this.mEnterMode, this.mCurrentThemeInfo, 0, this);
    }

    public void setSceneIndex(int i, final ZeroVideoPageView.ISceneLoadListener iSceneLoadListener) {
        if (this.mCurrentThemeInfo == null || this.mCurrentSceneInfo == null) {
            iSceneLoadListener.onSceneLoadError(StringUtils.getString(R.string.theme_source_not_ready));
        } else {
            ZeroThemeDataHelp.getInstance().onLoadSceneDataForDuration(this.mEnterMode, this.mCurrentThemeInfo, i, new ISceneLoadedCallback() { // from class: com.wanos.media.widget.video.ZeroVideoViewModel.1
                @Override // com.wanos.media.widget.video.ISceneLoadedCallback
                public void onSceneInfo(ThemeInfoEntity themeInfoEntity, AudioSceneInfoEntity audioSceneInfoEntity) {
                    if (ZeroVideoViewModel.this.mCurrentThemeInfo == null || ZeroVideoViewModel.this.mCurrentThemeInfo.getThemeId() != themeInfoEntity.getThemeId()) {
                        ZeroLogcatTools.e(ZeroVideoViewModel.TAG, "onSceneInfo: 主题信息验证失败，当前主题ID=" + ZeroVideoViewModel.this.mCurrentThemeInfo + ", 加载主题ID=" + themeInfoEntity.getThemeId());
                    } else if (ZeroVideoViewModel.this.mCurrentSceneInfo == null || ZeroVideoViewModel.this.mCurrentSceneInfo.getDurtion() != audioSceneInfoEntity.getDurtion()) {
                        ZeroVideoViewModel.this.mCurrentSceneInfo = audioSceneInfoEntity;
                        iSceneLoadListener.onSceneLoaded(audioSceneInfoEntity);
                    } else {
                        ZeroLogcatTools.e(ZeroVideoViewModel.TAG, "onSceneInfo: 相同场景，当前场景ID=" + ZeroVideoViewModel.this.mCurrentSceneInfo.getAudioId() + ", 加载场景ID=" + audioSceneInfoEntity.getAudioId());
                    }
                }

                @Override // com.wanos.media.widget.video.ISceneLoadedCallback
                public void onSceneErrorInfo(String str) {
                    iSceneLoadListener.onSceneLoadError(str);
                }
            });
        }
    }

    public void setRandomScene(final ZeroVideoPageView.ISceneLoadListener iSceneLoadListener) {
        if (this.mCurrentThemeInfo == null || this.mCurrentSceneInfo == null) {
            iSceneLoadListener.onSceneLoadError(StringUtils.getString(R.string.theme_source_not_ready));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mCurrentThemeInfo.getAudioInfo().size(); i++) {
            if (this.mCurrentThemeInfo.getAudioInfo().get(i).getAudioId() != this.mCurrentSceneInfo.getAudioId()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (arrayList.isEmpty()) {
            iSceneLoadListener.onSceneLoadError(StringUtils.getString(R.string.error_scene_not_more));
            return;
        }
        Collections.shuffle(arrayList);
        int iIntValue = ((Integer) arrayList.get(0)).intValue();
        ZeroLogcatTools.d(TAG, "setRandomScene: 随机场景Index = " + iIntValue);
        ZeroThemeDataHelp.getInstance().onLoadSceneData(this.mEnterMode, this.mCurrentThemeInfo, iIntValue, new ISceneLoadedCallback() { // from class: com.wanos.media.widget.video.ZeroVideoViewModel.2
            @Override // com.wanos.media.widget.video.ISceneLoadedCallback
            public void onSceneInfo(ThemeInfoEntity themeInfoEntity, AudioSceneInfoEntity audioSceneInfoEntity) {
                if (ZeroVideoViewModel.this.mCurrentThemeInfo == null || ZeroVideoViewModel.this.mCurrentThemeInfo.getThemeId() == themeInfoEntity.getThemeId()) {
                    ZeroVideoViewModel.this.mCurrentSceneInfo = audioSceneInfoEntity;
                    iSceneLoadListener.onSceneLoaded(audioSceneInfoEntity);
                } else {
                    ZeroLogcatTools.e(ZeroVideoViewModel.TAG, "onSceneInfo: 主题信息验证失败，当前主题ID=" + ZeroVideoViewModel.this.mCurrentThemeInfo.getThemeId() + ", 加载主题ID=" + themeInfoEntity.getThemeId());
                }
            }

            @Override // com.wanos.media.widget.video.ISceneLoadedCallback
            public void onSceneErrorInfo(String str) {
                iSceneLoadListener.onSceneLoadError(str);
            }
        });
    }

    public int findSceneIndex(long j) {
        List<AudioSceneInfoEntity> audioInfo;
        ThemeInfoEntity themeInfoEntity = this.mCurrentThemeInfo;
        if (themeInfoEntity != null && (audioInfo = themeInfoEntity.getAudioInfo()) != null && !audioInfo.isEmpty()) {
            for (int i = 0; i < audioInfo.size(); i++) {
                if (audioInfo.get(i).getAudioId() == j) {
                    return i;
                }
            }
        }
        return -1;
    }

    public ZeroPageMode getEnterMode() {
        return this.mEnterMode;
    }

    public List<ZeroThemeInfo> getThemeList() {
        List<ZeroThemeInfo> list = this.mThemeList;
        return list == null ? Collections.emptyList() : list;
    }

    public ThemeInfoEntity getNowThemeInfo() {
        return this.mCurrentThemeInfo;
    }

    public int getNowThemeId() {
        return this.mCurrentId;
    }

    public AudioSceneInfoEntity getNowSceneInfo() {
        return this.mCurrentSceneInfo;
    }

    public String[] getMingXiangDuration() {
        return this.mMingXiangDuration;
    }

    public void setCallback(IThemePageChangeListener iThemePageChangeListener) {
        this.iThemePageChangeListener = iThemePageChangeListener;
    }

    @Override // com.wanos.media.widget.video.IThemeLoadedCallback
    public void onVideoInfo(ThemeInfoEntity themeInfoEntity) {
        this._BackgroundVideoUri.setValue(Uri.parse(VideoCacheUtils.getVideoCachePath(themeInfoEntity.getVideoResource())));
    }

    @Override // com.wanos.media.widget.video.IThemeLoadedCallback
    public void onThemeDurationInfo(String[] strArr) {
        if (ZeroPageMode.XIAO_QI_PRO == this.mEnterMode || ZeroPageMode.XIAO_QI_STANDARD == this.mEnterMode) {
            return;
        }
        ZeroLogcatTools.d(TAG, "onThemeDurationInfo: 冥想可选时长 = " + Arrays.toString(strArr));
        this.mMingXiangDuration = strArr;
        IThemePageChangeListener iThemePageChangeListener = this.iThemePageChangeListener;
        if (iThemePageChangeListener == null || strArr == null) {
            return;
        }
        iThemePageChangeListener.onDurationLoaded(strArr);
    }

    @Override // com.wanos.media.widget.video.IThemeLoadedCallback
    public void onThemeErrorInfo(String str) {
        IThemePageChangeListener iThemePageChangeListener = this.iThemePageChangeListener;
        if (iThemePageChangeListener != null) {
            iThemePageChangeListener.onLoadError(str);
        }
    }

    @Override // com.wanos.media.widget.video.ISceneLoadedCallback
    public void onSceneInfo(ThemeInfoEntity themeInfoEntity, AudioSceneInfoEntity audioSceneInfoEntity) {
        if (this.mCurrentId != themeInfoEntity.getThemeId()) {
            ZeroLogcatTools.w(TAG, "onThemeInfo: 加载主题ID = " + themeInfoEntity.getThemeId() + ", 当前选中主题ID = " + this.mCurrentId);
            return;
        }
        this.mCurrentThemeInfo = themeInfoEntity;
        this.mCurrentSceneInfo = audioSceneInfoEntity;
        if (audioSceneInfoEntity == null) {
            onSceneErrorInfo(StringUtils.getString(R.string.error_scene_load));
            return;
        }
        ZeroLogcatTools.i(TAG, themeInfoEntity.getThemeName() + "-主题下场景加载成功~");
        IThemePageChangeListener iThemePageChangeListener = this.iThemePageChangeListener;
        if (iThemePageChangeListener != null) {
            iThemePageChangeListener.onSceneLoaded(audioSceneInfoEntity);
        }
    }

    @Override // com.wanos.media.widget.video.ISceneLoadedCallback
    public void onSceneErrorInfo(String str) {
        IThemePageChangeListener iThemePageChangeListener = this.iThemePageChangeListener;
        if (iThemePageChangeListener != null) {
            iThemePageChangeListener.onLoadError(str);
        }
    }
}
