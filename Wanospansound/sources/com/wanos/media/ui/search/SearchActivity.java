package com.wanos.media.ui.search;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.tencent.bugly.crashreport.CrashReport;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.ActivitySearchBinding;
import com.wanos.media.presenter.SearchPresenter;
import com.wanos.media.ui.search.SearchLabelFragment;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.util.SearchEditText;

/* JADX INFO: loaded from: classes3.dex */
public class SearchActivity extends WanosBaseActivity {
    private ActivitySearchBinding binding;
    private Fragment currentFragment;
    private InputMethodManager imm;
    private boolean isSoftKeyboradVisible = false;
    private SearchPresenter presenter;
    private View root;
    private SearchEditText searchEdit;
    private SearchLabelFragment searchLabelFragment;
    private SearchResultFragment searchResultFragment;
    private TextWatcher textWatcher;
    private ResultViewModel viewModel;

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (ResultViewModel) new ViewModelProvider(this).get(ResultViewModel.class);
        ActivitySearchBinding activitySearchBindingInflate = ActivitySearchBinding.inflate(getLayoutInflater());
        this.binding = activitySearchBindingInflate;
        setContentView(activitySearchBindingInflate.getRoot());
        initView();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SearchLabelFragment searchLabelFragment = this.searchLabelFragment;
        if (searchLabelFragment != null && searchLabelFragment.isAdded() && this.viewModel.isDialog.getValue().booleanValue()) {
            this.searchLabelFragment.showDialog();
        }
    }

    private void initView() {
        initEdit();
        initFragment();
        initListener();
    }

    private void initEdit() {
        this.imm = (InputMethodManager) getSystemService("input_method");
        SearchEditText searchEditText = (SearchEditText) getTitleRightView();
        this.searchEdit = searchEditText;
        searchEditText.setFocusable(true);
        this.searchEdit.setFocusableInTouchMode(true);
        this.searchEdit.requestFocus();
        this.viewModel.isShowSoftInput.observe(this, new Observer() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m529lambda$initEdit$0$comwanosmediauisearchSearchActivity((Boolean) obj);
            }
        });
        this.searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda3
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.m530lambda$initEdit$1$comwanosmediauisearchSearchActivity(textView, i, keyEvent);
            }
        });
        this.searchEdit.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m531lambda$initEdit$2$comwanosmediauisearchSearchActivity(view);
            }
        });
        this.searchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.m532lambda$initEdit$3$comwanosmediauisearchSearchActivity(view, z);
            }
        });
        setOnClickSearchListener(new View.OnClickListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m533lambda$initEdit$4$comwanosmediauisearchSearchActivity(view);
            }
        });
        TextWatcher textWatcher = new TextWatcher() { // from class: com.wanos.media.ui.search.SearchActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (SearchActivity.this.searchEdit.hasFocus()) {
                    if (s == null || s.length() == 0) {
                        SearchActivity.this.clearResultInfo();
                        SearchActivity.this.initLabelFragment();
                    }
                }
            }
        };
        this.textWatcher = textWatcher;
        this.searchEdit.addTextChangedListener(textWatcher);
        setOnClickClearListener(new SearchEditText.OnClickClearListener() { // from class: com.wanos.media.ui.search.SearchActivity.2
            @Override // com.wanos.media.util.SearchEditText.OnClickClearListener
            public void onClear() {
                SearchActivity.this.initLabelFragment();
                SearchActivity.this.clearResultInfo();
            }

            @Override // com.wanos.media.util.SearchEditText.OnClickClearListener
            public void onDispatchKeyEventPreIme() {
                SearchActivity.this.hideSoftInput();
            }
        });
        autoScrollView();
    }

    /* JADX INFO: renamed from: lambda$initEdit$0$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m529lambda$initEdit$0$comwanosmediauisearchSearchActivity(Boolean bool) {
        if (bool.booleanValue()) {
            this.imm.showSoftInput(this.searchEdit, 1);
        } else {
            hideSoftInput();
        }
    }

    /* JADX INFO: renamed from: lambda$initEdit$1$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ boolean m530lambda$initEdit$1$comwanosmediauisearchSearchActivity(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 0 && i != 3) {
            return true;
        }
        String strTrim = this.searchEdit.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showMsg(R.string.search_content_empty);
            return true;
        }
        hideSoftInput();
        this.searchResultFragment = null;
        initResultFragment();
        this.searchEdit.clearFocus();
        SharedPreferUtils.saveSearchRecord(strTrim);
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_SEARCH, "" + strTrim, "", "", "", 0);
        return false;
    }

    /* JADX INFO: renamed from: lambda$initEdit$2$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m531lambda$initEdit$2$comwanosmediauisearchSearchActivity(View view) {
        showSoftInput();
    }

    /* JADX INFO: renamed from: lambda$initEdit$3$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m532lambda$initEdit$3$comwanosmediauisearchSearchActivity(View view, boolean z) {
        this.viewModel.isShowSoftInput.setValue(Boolean.valueOf(z));
        if (z && this.imm != null) {
            showSoftInput();
        } else {
            hideSoftInput();
        }
    }

    /* JADX INFO: renamed from: lambda$initEdit$4$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m533lambda$initEdit$4$comwanosmediauisearchSearchActivity(View view) {
        String strTrim = this.searchEdit.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showMsg(R.string.search_content_empty);
            return;
        }
        if (isRepeatKey(strTrim)) {
            hideSoftInput();
            this.searchEdit.clearFocus();
            return;
        }
        this.viewModel.keyword.setValue(strTrim);
        hideSoftInput();
        initResultFragment();
        this.searchEdit.clearFocus();
        SharedPreferUtils.saveSearchRecord(strTrim);
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_SEARCH, "" + strTrim, "", "", "", 0);
    }

    private void initFragment() {
        if (this.viewModel.pageNum.getValue().intValue() == 0) {
            initLabelFragment();
        } else {
            initResultFragment();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLabelFragment() {
        if (this.searchLabelFragment == null) {
            this.searchLabelFragment = new SearchLabelFragment(this.searchEdit);
        }
        this.viewModel.pageNum.setValue(0);
        initListener();
        showFragment(this.searchLabelFragment);
    }

    private void initResultFragment() {
        if (this.searchResultFragment == null) {
            this.searchResultFragment = new SearchResultFragment(this.searchEdit);
        }
        this.viewModel.pageNum.setValue(1);
        showFragment(this.searchResultFragment);
    }

    private void initListener() {
        SearchLabelFragment searchLabelFragment = this.searchLabelFragment;
        if (searchLabelFragment != null) {
            searchLabelFragment.setOnClickSearchListener(new SearchLabelFragment.OnClickSearchListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda1
                @Override // com.wanos.media.ui.search.SearchLabelFragment.OnClickSearchListener
                public final void onSearch(String str) {
                    this.f$0.m534lambda$initListener$5$comwanosmediauisearchSearchActivity(str);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$initListener$5$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m534lambda$initListener$5$comwanosmediauisearchSearchActivity(String str) {
        if (TextUtils.isEmpty(str.trim())) {
            return;
        }
        this.viewModel.keyword.setValue(str);
        this.searchEdit.setText(str);
        initResultFragment();
        hideSoftInput();
        SharedPreferUtils.saveSearchRecord(str);
    }

    private void showFragment(Fragment fragment) {
        this.currentFragment = fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.layout_search_bottom, this.currentFragment);
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        onHideSoftInput(event);
        return super.onTouchEvent(event);
    }

    public void onHideSoftInput(MotionEvent event) {
        if (event.getAction() != 0 || getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null) {
            return;
        }
        hideSoftInput();
    }

    public void showSoftInput() {
        InputMethodManager inputMethodManager = this.imm;
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(this.searchEdit, 1);
        }
    }

    public void hideSoftInput() {
        if (this.searchEdit.hasFocus()) {
            InputMethodManager inputMethodManager = this.imm;
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            this.searchEdit.clearFocus();
        }
    }

    private boolean isRepeatKey(String keyword) {
        return keyword.equals(this.viewModel.keyword.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResultInfo() {
        this.viewModel.pageNum.setValue(0);
        this.viewModel.keyword.setValue("");
        this.viewModel.pageNum1.setValue(0);
        this.viewModel.musicList.setValue(null);
        this.viewModel.musicGroupList.setValue(null);
        this.viewModel.audioBookList.setValue(null);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        Fragment fragment = this.currentFragment;
        if (fragment == null || !(fragment instanceof WanosBaseFragment)) {
            return;
        }
        ((WanosBaseFragment) fragment).onMediaInfoCallbackAppNext(type, mediaInfo);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
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

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        hideSoftInput();
    }

    public void autoScrollView() {
        this.root = getWindow().getDecorView().findViewById(android.R.id.content);
        getWindow().setSoftInputMode(32);
        this.root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.wanos.media.ui.search.SearchActivity$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.m528lambda$autoScrollView$6$comwanosmediauisearchSearchActivity();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$autoScrollView$6$com-wanos-media-ui-search-SearchActivity, reason: not valid java name */
    /* synthetic */ void m528lambda$autoScrollView$6$comwanosmediauisearchSearchActivity() {
        Rect rect = new Rect();
        this.root.getWindowVisibleDisplayFrame(rect);
        if (this.root.getRootView().getHeight() - rect.bottom > Util.dip2px(CrashReport.getContext(), 150.0f)) {
            if (this.isSoftKeyboradVisible) {
                return;
            }
            this.isSoftKeyboradVisible = true;
        } else if (this.isSoftKeyboradVisible) {
            this.isSoftKeyboradVisible = false;
            if (this.searchEdit.hasFocus()) {
                this.searchEdit.clearFocus();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        TextWatcher textWatcher;
        SearchEditText searchEditText = this.searchEdit;
        if (searchEditText != null && (textWatcher = this.textWatcher) != null) {
            searchEditText.removeTextChangedListener(textWatcher);
        }
        super.onDestroy();
    }
}
