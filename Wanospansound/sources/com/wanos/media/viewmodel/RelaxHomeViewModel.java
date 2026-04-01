package com.wanos.media.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.EmptyEntity;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.repository.RelaxHomeRepo;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxHomeViewModel extends ViewModel {
    private static final String KEY_ME = "me_theme";
    private static final String KEY_MINGX_XIANG = "ming_xiang";
    private static final String KEY_XIAO_QI = "xiao_qi";
    private static final String TAG = "RelaxHomeViewModel";
    private final MutableLiveData<Integer> _mHomeUpDataThemeList;
    private final MutableLiveData<PageState> _mPageState;
    public final LiveData<Integer> homeUpDataThemeList;
    public final LiveData<PageState> pageState;
    private final RelaxHomeRepo relaxHomeRepo;
    private final List<SpaceThemeBaseInfo> mXiaoQiList = new ArrayList();
    private final List<SpaceThemeBaseInfo> mMingXiangList = new ArrayList();
    private final List<SpaceThemeBaseInfo> mMeCollectList = new ArrayList();

    public RelaxHomeViewModel() {
        MutableLiveData<PageState> mutableLiveData = new MutableLiveData<>(PageState.LOADING);
        this._mPageState = mutableLiveData;
        this.pageState = mutableLiveData;
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(-1);
        this._mHomeUpDataThemeList = mutableLiveData2;
        this.homeUpDataThemeList = mutableLiveData2;
        this.relaxHomeRepo = new RelaxHomeRepo();
        initHomeData(false);
    }

    public void initHomeData(boolean z) {
        if (!z) {
            this._mPageState.setValue(PageState.LOADING);
        }
        ThreadUtils.executeByIo(new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.wanos.media.viewmodel.RelaxHomeViewModel$1, reason: invalid class name */
    class AnonymousClass1 extends ThreadUtils.SimpleTask<HashMap<String, List<SpaceThemeBaseInfo>>> {
        AnonymousClass1() {
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public HashMap<String, List<SpaceThemeBaseInfo>> doInBackground() throws Throwable {
            HashMap<String, List<SpaceThemeBaseInfo>> map = new HashMap<>();
            map.put(RelaxHomeViewModel.KEY_XIAO_QI, RelaxHomeViewModel.this.relaxHomeRepo.getXiaoQiThemeList());
            map.put(RelaxHomeViewModel.KEY_MINGX_XIANG, RelaxHomeViewModel.this.relaxHomeRepo.getMingXiangThemeList());
            if (LoginUtils.getInstance().isLogin()) {
                map.put(RelaxHomeViewModel.KEY_ME, RelaxHomeViewModel.this.relaxHomeRepo.getCollectThemeList(12, 1, 6));
            }
            return map;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(HashMap<String, List<SpaceThemeBaseInfo>> map) {
            ZeroLogcatTools.i(RelaxHomeViewModel.TAG, "请求结果 = " + map.size());
            RelaxHomeViewModel.this.mXiaoQiList.clear();
            RelaxHomeViewModel.this.mMingXiangList.clear();
            RelaxHomeViewModel.this.mMeCollectList.clear();
            map.forEach(new BiConsumer() { // from class: com.wanos.media.viewmodel.RelaxHomeViewModel$1$$ExternalSyntheticLambda0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.m601x3cdcd1d6((String) obj, (List) obj2);
                }
            });
            ZeroLogcatTools.i(RelaxHomeViewModel.TAG, "小憩内容长度 = " + RelaxHomeViewModel.this.mXiaoQiList.isEmpty() + ", 冥想内容长度 = " + RelaxHomeViewModel.this.mMingXiangList.isEmpty() + ", 收藏内容长度 = " + RelaxHomeViewModel.this.mMeCollectList.isEmpty());
            if (!RelaxHomeViewModel.this.mXiaoQiList.isEmpty() || !RelaxHomeViewModel.this.mMingXiangList.isEmpty() || !RelaxHomeViewModel.this.mMeCollectList.isEmpty()) {
                RelaxHomeViewModel.this._mPageState.setValue(PageState.SUCCESS);
                RelaxHomeViewModel.this._mHomeUpDataThemeList.setValue(0);
            } else {
                RelaxHomeViewModel.this._mPageState.setValue(PageState.EMPTY);
                RelaxHomeViewModel.this._mHomeUpDataThemeList.setValue(-1);
            }
        }

        /* JADX INFO: renamed from: lambda$onSuccess$0$com-wanos-media-viewmodel-RelaxHomeViewModel$1, reason: not valid java name */
        /* synthetic */ void m601x3cdcd1d6(String str, List list) {
            ZeroLogcatTools.i(RelaxHomeViewModel.TAG, "KEY = " + str + ", Value = " + list.size());
            str.hashCode();
            switch (str) {
                case "xiao_qi":
                    RelaxHomeViewModel.this.mXiaoQiList.addAll(list);
                    break;
                case "me_theme":
                    RelaxHomeViewModel.this.mMeCollectList.addAll(list);
                    break;
                case "ming_xiang":
                    RelaxHomeViewModel.this.mMingXiangList.addAll(list);
                    break;
            }
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
        public void onFail(Throwable th) {
            super.onFail(th);
            ToastUtil.showMsg(th.getMessage());
            RelaxHomeViewModel.this._mPageState.setValue(PageState.ERROR);
            RelaxHomeViewModel.this._mHomeUpDataThemeList.setValue(-1);
        }
    }

    public List<SpaceThemeBaseInfo> getHomeDataList(int i) {
        ArrayList arrayList = new ArrayList();
        if (!this.mXiaoQiList.isEmpty()) {
            arrayList.add(SpaceThemeBaseInfo.getTitleEntity(R.string.xq_space_str, 1001));
            arrayList.addAll(this.mXiaoQiList.subList(0, Math.min(i, this.mXiaoQiList.size())));
        }
        if (!this.mMingXiangList.isEmpty()) {
            arrayList.add(SpaceThemeBaseInfo.getTitleEntity(R.string.mx_space_str, 2001));
            arrayList.addAll(this.mMingXiangList.subList(0, Math.min(i, this.mMingXiangList.size())));
        }
        arrayList.add(SpaceThemeBaseInfo.getTitleEntity(R.string.my_sound_space, 1002));
        arrayList.add(SpaceThemeBaseInfo.getAddButton());
        if (!this.mMeCollectList.isEmpty()) {
            arrayList.addAll(this.mMeCollectList.subList(0, Math.min(i - 1, this.mMeCollectList.size())));
        }
        return arrayList;
    }

    public void deleteTheme(long j) {
        HttpTools.getInstance().deleteTheme(j, new HttpCallback<EmptyEntity>() { // from class: com.wanos.media.viewmodel.RelaxHomeViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseEntity<EmptyEntity> baseEntity) {
                RelaxHomeViewModel.this.initHomeData(true);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ToastUtil.showMsg(str);
            }
        });
    }
}
