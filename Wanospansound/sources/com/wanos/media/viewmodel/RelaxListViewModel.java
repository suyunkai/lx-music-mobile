package com.wanos.media.viewmodel;

import android.content.Intent;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.repository.RelaxHomeRepo;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxListViewModel extends ViewModel {
    public static final String KEY_OPEN_PARAMS = "entryWay";
    private final MutableLiveData<PageState> _PageState;
    private final MutableLiveData<String> _TitleText;
    private final SingleLiveEvent<List<SpaceThemeBaseInfo>> _UpDataItemList;
    private int entryWay;
    private final List<SpaceThemeBaseInfo> mItemList;
    public final LiveData<PageState> pageState;
    private final RelaxHomeRepo relaxHomeRepo;
    public final LiveData<String> titleText;
    public final LiveData<List<SpaceThemeBaseInfo>> upDataItemList;

    public RelaxListViewModel(Intent intent) {
        MutableLiveData<PageState> mutableLiveData = new MutableLiveData<>(PageState.LOADING);
        this._PageState = mutableLiveData;
        this.pageState = mutableLiveData;
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>("");
        this._TitleText = mutableLiveData2;
        this.titleText = mutableLiveData2;
        SingleLiveEvent<List<SpaceThemeBaseInfo>> singleLiveEvent = new SingleLiveEvent<>();
        this._UpDataItemList = singleLiveEvent;
        this.upDataItemList = singleLiveEvent;
        this.mItemList = new ArrayList();
        this.entryWay = -1;
        this.relaxHomeRepo = new RelaxHomeRepo();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra(KEY_OPEN_PARAMS, -1);
        this.entryWay = intExtra;
        if (intExtra == 1001 || intExtra == 1002) {
            mutableLiveData2.setValue(StringUtils.getString(R.string.xq_space_str));
        } else if (intExtra == 2001 || intExtra == 2002) {
            mutableLiveData2.setValue(StringUtils.getString(R.string.mx_space_str));
        }
        initThemeList(false);
    }

    public int getEntryWay() {
        return this.entryWay;
    }

    public List<SpaceThemeBaseInfo> getItemList() {
        return this.mItemList;
    }

    public void initThemeList(boolean z) {
        if (this.entryWay == -1) {
            return;
        }
        if (!z) {
            this._PageState.setValue(PageState.LOADING);
        }
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<SpaceThemeBaseInfo>>() { // from class: com.wanos.media.viewmodel.RelaxListViewModel.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<SpaceThemeBaseInfo> doInBackground() throws Throwable {
                if (RelaxListViewModel.this.entryWay == 2001) {
                    return RelaxListViewModel.this.relaxHomeRepo.getMingXiangThemeList();
                }
                if (RelaxListViewModel.this.entryWay == 1001) {
                    return RelaxListViewModel.this.relaxHomeRepo.getXiaoQiThemeList();
                }
                return Collections.emptyList();
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<SpaceThemeBaseInfo> list) {
                if (list == null || list.isEmpty()) {
                    RelaxListViewModel.this._PageState.setValue(PageState.EMPTY);
                    return;
                }
                RelaxListViewModel.this._PageState.setValue(PageState.SUCCESS);
                RelaxListViewModel.this.mItemList.clear();
                RelaxListViewModel.this.mItemList.addAll(list);
                RelaxListViewModel.this._UpDataItemList.setValue(list);
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                ToastUtil.showMsg(th.getMessage());
                RelaxListViewModel.this._PageState.setValue(PageState.ERROR);
            }
        });
    }
}
