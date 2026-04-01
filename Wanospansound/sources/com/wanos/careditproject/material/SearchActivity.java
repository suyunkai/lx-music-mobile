package com.wanos.careditproject.material;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CloudInfoResponse;
import com.wanos.careditproject.databinding.ActivityEditResSearchBinding;
import com.wanos.careditproject.event.MaterialPlayEvent;
import com.wanos.careditproject.material.adapter.MaterialListAdpter;
import com.wanos.careditproject.material.fragment.CommonMaterialListFragment;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.view.BaseAppActivity;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.media.R;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.SearchEditText;
import com.wanos.media.util.ToastUtil;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class SearchActivity extends BaseAppActivity implements View.OnClickListener {
    public static final String TAG = "SearchActivity";
    protected ActivityEditResSearchBinding binding;
    private CommonMaterialListFragment commonMaterialFragment;
    private InputMethodManager imm;
    public String keyWord;
    private MediaPlayerHelper mMediaPlayerHelper;
    private MaterialTypeInfoBean materialDataInfo;
    private MaterialPlayEvent materialPlayEvent = new MaterialPlayEvent(MediaPlayerEnum.CallBackState.COMPLETE, null);

    @Override // com.wanos.careditproject.view.BaseAppActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityEditResSearchBinding activityEditResSearchBindingInflate = ActivityEditResSearchBinding.inflate(getLayoutInflater());
        this.binding = activityEditResSearchBindingInflate;
        setContentView(activityEditResSearchBindingInflate.getRoot());
        this.mMediaPlayerHelper = MediaPlayerHelperUtil.create(EditingUtils.getShortCacheDir(), EditingUtils.DEFAULT_SHORT_FILE_MAX_CACHE_SIZE);
        initView();
        initListener();
        updateView();
    }

    private void initListener() {
        this.binding.searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.careditproject.material.SearchActivity$$ExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.m376x4baaaa53(textView, i, keyEvent);
            }
        });
        this.binding.btnSearch.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.material.SearchActivity.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                SearchActivity searchActivity = SearchActivity.this;
                searchActivity.keyWord = searchActivity.binding.searchEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(SearchActivity.this.keyWord)) {
                    SearchActivity.this.hideSoftInput();
                    SearchActivity.this.binding.searchEdit.clearFocus();
                    SearchActivity searchActivity2 = SearchActivity.this;
                    searchActivity2.requestData(searchActivity2.keyWord);
                    return;
                }
                ToastUtil.showMsg(R.string.search_content_empty);
            }
        });
        this.binding.searchEdit.setOnClickClearListener(new SearchEditText.OnClickClearListener() { // from class: com.wanos.careditproject.material.SearchActivity$$ExternalSyntheticLambda1
            @Override // com.wanos.media.util.SearchEditText.OnClickClearListener
            public final void onClear() {
                this.f$0.m377x3d545072();
            }
        });
        this.binding.searchViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.careditproject.material.SearchActivity.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                super.onPageScrollStateChanged(i);
            }
        });
        this.mMediaPlayerHelper.setOnStatusCallbackListener(new OnStatusCallbackListener() { // from class: com.wanos.careditproject.material.SearchActivity$$ExternalSyntheticLambda2
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
            public final void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
                this.f$0.m378x2efdf691(callBackState, objArr);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$0$com-wanos-careditproject-material-SearchActivity, reason: not valid java name */
    /* synthetic */ boolean m376x4baaaa53(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6 && i != 3) {
            return false;
        }
        String strTrim = this.binding.searchEdit.getText().toString().trim();
        this.keyWord = strTrim;
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showMsg(R.string.search_content_empty);
            return true;
        }
        hideSoftInput();
        this.binding.searchEdit.clearFocus();
        requestData(this.keyWord);
        return false;
    }

    /* JADX INFO: renamed from: lambda$initListener$1$com-wanos-careditproject-material-SearchActivity, reason: not valid java name */
    /* synthetic */ void m377x3d545072() {
        requestData("");
    }

    /* JADX INFO: renamed from: lambda$initListener$2$com-wanos-careditproject-material-SearchActivity, reason: not valid java name */
    /* synthetic */ void m378x2efdf691(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
        this.materialPlayEvent = new MaterialPlayEvent(callBackState, this.materialDataInfo);
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED || callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.COMPLETE || callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            EventBus.getDefault().post(this.materialPlayEvent);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == com.wanos.careditproject.R.id.turn_back) {
            this.mMediaPlayerHelper.pause();
            finish();
        }
    }

    private void initView() {
        getWindow().setSoftInputMode(48);
        this.imm = (InputMethodManager) getSystemService("input_method");
        this.binding.tvSearchTitle.setText(getString(com.wanos.careditproject.R.string.material_title_library));
        this.binding.searchEdit.setmClearIcon(R.drawable.ic_material_search_close);
        this.binding.searchEdit.setmSearchIcon(com.wanos.careditproject.R.drawable.search_icon);
        this.binding.searchEdit.requestFocus();
        this.binding.turnBack.setOnClickListener(this);
    }

    public void updateView() {
        ArrayList arrayList = new ArrayList();
        CommonMaterialListFragment commonMaterialListFragment = new CommonMaterialListFragment();
        this.commonMaterialFragment = commonMaterialListFragment;
        commonMaterialListFragment.setTypeList(5, -1);
        arrayList.add(this.commonMaterialFragment);
        this.binding.searchViewpager.setAdapter(new MaterialListAdpter(getSupportFragmentManager(), arrayList, getLifecycle()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(String str) {
        this.commonMaterialFragment.requestSearchData(str);
    }

    public MaterialPlayEvent getMaterialPlayEvent() {
        return this.materialPlayEvent;
    }

    public void play(MaterialTypeInfoBean materialTypeInfoBean) {
        this.materialDataInfo = materialTypeInfoBean;
        if (materialTypeInfoBean != null) {
            getMaterialInfo(materialTypeInfoBean.getM_id(), this);
        }
    }

    private void getMaterialInfo(final int i, final Context context) {
        CreatorRetrofitUtil.getAssetsInfo(i, 2, new ResponseCallBack<CloudInfoResponse>(this) { // from class: com.wanos.careditproject.material.SearchActivity.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CloudInfoResponse cloudInfoResponse) {
                if (cloudInfoResponse.data == null || cloudInfoResponse.data.getInfo() == null) {
                    return;
                }
                SearchActivity.this.materialDataInfo.setUrlSrcWanos(cloudInfoResponse.data.getInfo().getUrlSrcWanos());
                SearchActivity.this.mMediaPlayerHelper.playUrl(context, SearchActivity.this.materialDataInfo.getUrlSrcWanos(), false, i, 3);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                Log.i(SearchActivity.TAG, "素材详情----" + i2 + InternalFrame.ID + str);
                ToastUtil.showMsg(str);
            }
        });
    }

    public void pause() {
        this.mMediaPlayerHelper.pause();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        onHideSoftInput(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    public void onHideSoftInput(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null) {
            return;
        }
        hideSoftInput();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftInput() {
        if (this.binding.searchEdit.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            this.binding.searchEdit.clearFocus();
        }
    }
}
