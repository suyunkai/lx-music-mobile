package com.wanos.media.ui.search;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentSearchLabelBinding;
import com.wanos.media.ui.search.dialog.CommonDialog;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.util.FlowLayout;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SearchLabelFragment extends WanosBaseFragment {
    private FragmentSearchLabelBinding binding;
    private CommonDialog dialog;
    private EditText et;
    private InputMethodManager imm;
    private OnClickSearchListener listener;
    private ResultViewModel viewModel;

    public interface OnClickSearchListener {
        void onSearch(String string);
    }

    private void initListener() {
    }

    public SearchLabelFragment() {
    }

    public SearchLabelFragment(EditText view) {
        this.et = view;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = (ResultViewModel) new ViewModelProvider(getActivity()).get(ResultViewModel.class);
        this.binding = FragmentSearchLabelBinding.inflate(inflater, container, false);
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initTextWeight();
        initDate();
        initListener();
        onHideSoftInput();
        this.viewModel.recordList.observe(this, new Observer() { // from class: com.wanos.media.ui.search.SearchLabelFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m536lambda$initView$0$comwanosmediauisearchSearchLabelFragment((List) obj);
            }
        });
        this.viewModel.hotList.observe(this, new Observer() { // from class: com.wanos.media.ui.search.SearchLabelFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m537lambda$initView$1$comwanosmediauisearchSearchLabelFragment((List) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-ui-search-SearchLabelFragment, reason: not valid java name */
    /* synthetic */ void m536lambda$initView$0$comwanosmediauisearchSearchLabelFragment(List list) {
        initRecordView(list.size() != 0, list);
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-wanos-media-ui-search-SearchLabelFragment, reason: not valid java name */
    /* synthetic */ void m537lambda$initView$1$comwanosmediauisearchSearchLabelFragment(List list) {
        initHotView((list == null || list.size() == 0) ? false : true, list);
    }

    private void initTextWeight() {
        this.binding.searchRecordText.setTypeface(Typeface.create(Typeface.DEFAULT, 500, false));
        this.binding.searchHotWord.setTypeface(Typeface.create(Typeface.DEFAULT, 500, false));
    }

    public void showDialog() {
        CommonDialog commonDialogNewInstance = CommonDialog.newInstance();
        this.dialog = commonDialogNewInstance;
        commonDialogNewInstance.setFm(getParentFragmentManager());
        this.dialog.setOnClickDialogListener(new CommonDialog.OnClickListener() { // from class: com.wanos.media.ui.search.SearchLabelFragment.1
            @Override // com.wanos.media.ui.search.dialog.CommonDialog.OnClickListener
            public void onLeftClick() {
                SearchLabelFragment.this.viewModel.deleteRecordList();
                SearchLabelFragment.this.initRecordView(false, null);
                SearchLabelFragment.this.viewModel.isDialog.setValue(false);
            }

            @Override // com.wanos.media.ui.search.dialog.CommonDialog.OnClickListener
            public void onRightClick() {
                SearchLabelFragment.this.viewModel.isDialog.setValue(false);
            }
        });
        this.viewModel.isDialog.setValue(true);
        this.dialog.showDialog();
    }

    private void initDate() {
        this.viewModel.getRecordList();
        this.viewModel.getHotRecommendWords();
    }

    private void onHideSoftInput() {
        this.binding.searchScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.search.SearchLabelFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m538xa96c3d42(view, motionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onHideSoftInput$2$com-wanos-media-ui-search-SearchLabelFragment, reason: not valid java name */
    /* synthetic */ boolean m538xa96c3d42(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || getActivity().getCurrentFocus() == null || getActivity().getCurrentFocus().getWindowToken() == null) {
            return false;
        }
        hideSoftInput();
        return false;
    }

    private void hideSoftInput() {
        if (this.et.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
            this.et.clearFocus();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRecordView(boolean show, List<String> recordList) {
        this.binding.searchRecordText.setVisibility(show ? 0 : 8);
        this.binding.flowlayoutRecord.setVisibility(show ? 0 : 8);
        if (recordList != null) {
            recordList.add("清除");
        }
        this.binding.flowlayoutRecord.setFlowLayout(true, recordList, new FlowLayout.OnItemClickListener() { // from class: com.wanos.media.ui.search.SearchLabelFragment$$ExternalSyntheticLambda2
            @Override // com.wanos.media.util.FlowLayout.OnItemClickListener
            public final void onItemClick(String str, boolean z) {
                this.f$0.m535x498e23a4(str, z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initRecordView$3$com-wanos-media-ui-search-SearchLabelFragment, reason: not valid java name */
    /* synthetic */ void m535x498e23a4(String str, boolean z) {
        OnClickSearchListener onClickSearchListener;
        if (!z && (onClickSearchListener = this.listener) != null) {
            onClickSearchListener.onSearch(str);
        } else {
            hideSoftInput();
            showDialog();
        }
    }

    private void initHotView(boolean show, List<String> hotList) {
        this.binding.searchHotWord.setVisibility(show ? 0 : 8);
        this.binding.flowlayoutHot.setVisibility(show ? 0 : 8);
        FragmentSearchLabelBinding fragmentSearchLabelBinding = this.binding;
        if (fragmentSearchLabelBinding != null) {
            fragmentSearchLabelBinding.flowlayoutHot.setFlowLayout(false, hotList, new FlowLayout.OnItemClickListener() { // from class: com.wanos.media.ui.search.SearchLabelFragment.2
                @Override // com.wanos.media.util.FlowLayout.OnItemClickListener
                public void onItemClick(String content, boolean isClear) {
                    if (SearchLabelFragment.this.listener != null) {
                        SearchLabelFragment.this.listener.onSearch(content);
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_SEARCH_KEYWORD, "" + content, "", "", "", 0);
                    }
                }
            });
        }
    }

    public void setOnClickSearchListener(OnClickSearchListener listener) {
        this.listener = listener;
    }
}
