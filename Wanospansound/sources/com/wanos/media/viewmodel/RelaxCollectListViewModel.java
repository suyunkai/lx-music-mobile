package com.wanos.media.viewmodel;

import android.content.Intent;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.EmptyEntity;
import com.wanos.media.entity.LoadState;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ThemeEvent;
import com.wanos.media.entity.ZeroThemeListEntity;
import com.wanos.media.repository.RelaxHomeRepo;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.RelaxCollectListViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxCollectListViewModel extends ViewModel {
    public static final String KEY_TABLE_ID = "table_id";
    private static final String TAG = "RelaxCollectListViewMod";
    private final MutableLiveData<PageState> _PageState;
    private final SingleLiveEvent<Integer> _UpDataItemList;
    private int mCurrentPage;
    private final List<SpaceThemeBaseInfo> mItemList;
    private volatile int mTotal;
    public final LiveData<PageState> pageState;
    private final RelaxHomeRepo relaxHomeRepo;
    private int tableId;
    public final LiveData<Integer> upDataItemList;

    public RelaxCollectListViewModel(Intent intent) {
        MutableLiveData<PageState> mutableLiveData = new MutableLiveData<>(PageState.LOADING);
        this._PageState = mutableLiveData;
        this.pageState = mutableLiveData;
        SingleLiveEvent<Integer> singleLiveEvent = new SingleLiveEvent<>();
        this._UpDataItemList = singleLiveEvent;
        this.upDataItemList = singleLiveEvent;
        this.mItemList = new ArrayList();
        this.tableId = -1;
        this.mCurrentPage = 1;
        this.mTotal = 0;
        EventBus.getDefault().register(this);
        this.relaxHomeRepo = new RelaxHomeRepo();
        if (intent == null) {
            return;
        }
        this.tableId = intent.getIntExtra(KEY_TABLE_ID, -1);
        ZeroLogcatTools.i(TAG, "RelaxCollectListViewModel: tableId = " + this.tableId);
        initThemeList(LoadState.INIT);
    }

    public List<SpaceThemeBaseInfo> getItemList() {
        return this.mItemList;
    }

    public int getTotal() {
        return this.mTotal;
    }

    public void initThemeList(final LoadState loadState) {
        if (this.tableId == -1) {
            return;
        }
        int i = AnonymousClass4.$SwitchMap$com$wanos$media$entity$LoadState[loadState.ordinal()];
        if (i == 1) {
            this.mCurrentPage = 1;
            this._PageState.setValue(PageState.LOADING);
        } else if (i == 2) {
            this.mCurrentPage++;
        } else if (i == 3) {
            this.mCurrentPage = 1;
        }
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<SpaceThemeBaseInfo>>() { // from class: com.wanos.media.viewmodel.RelaxCollectListViewModel.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<SpaceThemeBaseInfo> doInBackground() throws Throwable {
                ZeroThemeListEntity collectTheme = RelaxCollectListViewModel.this.relaxHomeRepo.getCollectTheme(RelaxCollectListViewModel.this.tableId, RelaxCollectListViewModel.this.mCurrentPage, 24);
                RelaxCollectListViewModel.this.mTotal = collectTheme.getTotal();
                return RelaxCollectListViewModel.this.relaxHomeRepo.formatCollectThemeList(collectTheme);
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<SpaceThemeBaseInfo> list) {
                ZeroLogcatTools.i(RelaxCollectListViewModel.TAG, "initThemeList: result = " + list);
                if (list == null || list.isEmpty()) {
                    if (loadState == LoadState.INIT || loadState == LoadState.REFRESH) {
                        RelaxCollectListViewModel.this._PageState.setValue(PageState.EMPTY);
                        return;
                    }
                    return;
                }
                ZeroLogcatTools.i(RelaxCollectListViewModel.TAG, "initThemeList: Size = " + list.size());
                RelaxCollectListViewModel.this._PageState.setValue(PageState.SUCCESS);
                if (loadState != LoadState.MORE) {
                    RelaxCollectListViewModel.this.mItemList.clear();
                }
                RelaxCollectListViewModel.this.mItemList.addAll(list);
                RelaxCollectListViewModel.this._UpDataItemList.setValue(Integer.valueOf(list.size()));
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                ToastUtil.showMsg(th.getMessage());
                if (loadState != LoadState.MORE) {
                    RelaxCollectListViewModel.this._PageState.setValue(PageState.ERROR);
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.wanos.media.viewmodel.RelaxCollectListViewModel$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$LoadState;

        static {
            int[] iArr = new int[LoadState.values().length];
            $SwitchMap$com$wanos$media$entity$LoadState = iArr;
            try {
                iArr[LoadState.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$LoadState[LoadState.MORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$LoadState[LoadState.REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.viewmodel.RelaxCollectListViewModel$2, reason: invalid class name */
    class AnonymousClass2 extends HttpCallback<EmptyEntity> {
        final /* synthetic */ long val$themeId;

        AnonymousClass2(long j) {
            this.val$themeId = j;
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseSuccessful(BaseEntity<EmptyEntity> baseEntity) {
            ZeroLogcatTools.i(RelaxCollectListViewModel.TAG, "deleteTheme: " + baseEntity.getMsg());
            ThemeEvent themeEvent = new ThemeEvent(101);
            themeEvent.setThemeId(this.val$themeId);
            EventBus.getDefault().post(themeEvent);
            List list = RelaxCollectListViewModel.this.mItemList;
            final long j = this.val$themeId;
            list.removeIf(new Predicate() { // from class: com.wanos.media.viewmodel.RelaxCollectListViewModel$2$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return RelaxCollectListViewModel.AnonymousClass2.lambda$onResponseSuccessful$0(j, (SpaceThemeBaseInfo) obj);
                }
            });
            if (RelaxCollectListViewModel.this.mItemList.isEmpty()) {
                RelaxCollectListViewModel.this._PageState.setValue(PageState.EMPTY);
            }
            RelaxCollectListViewModel.this._UpDataItemList.setValue(-1);
        }

        static /* synthetic */ boolean lambda$onResponseSuccessful$0(long j, SpaceThemeBaseInfo spaceThemeBaseInfo) {
            return spaceThemeBaseInfo.getThemeId() == j;
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseFailure(int i, String str) {
            ToastUtil.showMsg(str);
        }
    }

    public void deleteTheme(long j) {
        HttpTools.getInstance().deleteTheme(j, new AnonymousClass2(j));
    }

    public void setTopTheme(final SpaceThemeBaseInfo spaceThemeBaseInfo) {
        HttpTools.getInstance().setTopTheme(spaceThemeBaseInfo.getThemeId(), new HttpCallback<EmptyEntity>() { // from class: com.wanos.media.viewmodel.RelaxCollectListViewModel.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseEntity<EmptyEntity> baseEntity) {
                ZeroLogcatTools.i(RelaxCollectListViewModel.TAG, "setTopTheme: " + baseEntity.getMsg());
                ThemeEvent themeEvent = new ThemeEvent(102);
                themeEvent.setThemeId(spaceThemeBaseInfo.getThemeId());
                EventBus.getDefault().post(themeEvent);
                for (int i = 0; i < RelaxCollectListViewModel.this.mItemList.size(); i++) {
                    if (((SpaceThemeBaseInfo) RelaxCollectListViewModel.this.mItemList.get(i)).getThemeId() == spaceThemeBaseInfo.getThemeId()) {
                        RelaxCollectListViewModel.this.mItemList.add(0, (SpaceThemeBaseInfo) RelaxCollectListViewModel.this.mItemList.remove(i));
                        RelaxCollectListViewModel.this._UpDataItemList.setValue(-1);
                        return;
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ToastUtil.showMsg(str);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeChangeEvent(ThemeEvent themeEvent) {
        if (themeEvent.getEventType() == 103) {
            ZeroLogcatTools.i(TAG, "onThemeChangeEvent: EVENT_TYPE_REFRESH");
            initThemeList(LoadState.REFRESH);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
