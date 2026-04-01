package com.wanos.media.ui.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.BuildConfig;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityUserInfoBinding;
import com.wanos.media.databinding.FragmentUserInfoBinding;
import com.wanos.media.presenter.MainPresenter;
import com.wanos.media.ui.login.OrderInfoFragment;
import com.wanos.media.ui.login.QuestionFragment;
import com.wanos.media.ui.login.UserInfoFragment;
import com.wanos.media.util.ChannelUtils;
import com.wanos.media.view.MainView;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoAndOrderActivity extends WanosBaseActivity<MainPresenter> implements MainView, View.OnClickListener {
    private ActivityUserInfoBinding binding;
    private Fragment currentFragment;
    private InputMethodManager imm;
    private OnBackPressedCallBack onBackPressedCallBack;
    private OrderInfoFragment orderInfoFragment;
    private QuestionFragment questionFragment;
    private UserInfoFragment userInfoFragment;
    private int selectedNum = -1;
    public boolean isConfig = false;

    public interface OnBackPressedCallBack {
        boolean onBackPressed();
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedNum", this.selectedNum);
        outState.putBoolean("isConfig", this.isConfig);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserInfoBinding activityUserInfoBindingInflate = ActivityUserInfoBinding.inflate(getLayoutInflater());
        this.binding = activityUserInfoBindingInflate;
        setContentView(activityUserInfoBindingInflate.getRoot());
        iniView();
        initVisibleIsSpoken();
        if (savedInstanceState == null) {
            initTabView();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.selectedNum = savedInstanceState.getInt("selectedNum", -1);
        this.isConfig = savedInstanceState.getBoolean("isConfig", false);
        int i = this.selectedNum;
        if (i == 0) {
            this.binding.tvPersonInfo.performClick();
        } else if (i == 1) {
            this.binding.tvOrderInfo.performClick();
        } else if (i == 2) {
            this.binding.tvQaList.performClick();
        }
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.tvPersonInfo.setContentDescription(getResources().getString(R.string.switch_person_info));
            this.binding.tvOrderInfo.setContentDescription(getResources().getString(R.string.switch_order_info));
        }
    }

    private void iniView() {
        getWindow().setSoftInputMode(16);
        setPlayBarVisibility(8);
        setTitleText(getText(R.string.user_info_title));
        this.imm = (InputMethodManager) getSystemService("input_method");
        this.binding.tvVersion.setText(getString(R.string.version_name, new Object[]{BuildConfig.VERSION_NAME}));
        this.binding.tvChannel.setText(getString(R.string.channel_name, new Object[]{"WANOS_245"}));
        this.binding.tvPersonInfo.setOnClickListener(this);
        this.binding.tvOrderInfo.setOnClickListener(this);
        this.binding.tvQaList.setOnClickListener(this);
        Util.setTextWeight(this.binding.tvPersonInfo, 500);
        Util.setTextWeight(this.binding.tvOrderInfo, 500);
        this.binding.tvVersion.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.login.activity.UserInfoAndOrderActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m510xc4b53b26(view, motionEvent);
            }
        });
        this.binding.tvVersion2.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.login.activity.UserInfoAndOrderActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m511xcab90685(view, motionEvent);
            }
        });
        ChannelUtils.getInstance().setChannelPollListener(new AnonymousClass1());
        Util.setTextWeight(this.binding.tvQaList, 500);
    }

    /* JADX INFO: renamed from: lambda$iniView$0$com-wanos-media-ui-login-activity-UserInfoAndOrderActivity, reason: not valid java name */
    /* synthetic */ boolean m510xc4b53b26(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            ChannelUtils.getInstance().longPressPoll();
        } else if (action == 1) {
            ChannelUtils.getInstance().clearHandler();
            this.binding.tvChannel.setVisibility(8);
        }
        return true;
    }

    /* JADX INFO: renamed from: lambda$iniView$1$com-wanos-media-ui-login-activity-UserInfoAndOrderActivity, reason: not valid java name */
    /* synthetic */ boolean m511xcab90685(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            ChannelUtils.getInstance().longPressPoll();
        } else if (action == 1) {
            ChannelUtils.getInstance().clearHandler();
            this.binding.tvChannel.setVisibility(8);
        }
        return true;
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.login.activity.UserInfoAndOrderActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ChannelUtils.ChannelPollListener {
        AnonymousClass1() {
        }

        /* JADX INFO: renamed from: lambda$longPressTimeOut$0$com-wanos-media-ui-login-activity-UserInfoAndOrderActivity$1, reason: not valid java name */
        /* synthetic */ void m512x8ebd3648() {
            UserInfoAndOrderActivity.this.binding.tvChannel.setVisibility(0);
        }

        @Override // com.wanos.media.util.ChannelUtils.ChannelPollListener
        public void longPressTimeOut() {
            UserInfoAndOrderActivity.this.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.login.activity.UserInfoAndOrderActivity$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m512x8ebd3648();
                }
            });
        }
    }

    private void initTabView() {
        Uri data = getIntent().getData();
        if (data != null) {
            String queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE);
            if (queryParameter != null) {
                if (queryParameter.equals("0")) {
                    this.selectedNum = 0;
                    this.binding.tvPersonInfo.performClick();
                    return;
                } else if (queryParameter.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    this.selectedNum = 1;
                    this.binding.tvOrderInfo.performClick();
                    return;
                } else {
                    if (queryParameter.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                        this.selectedNum = 2;
                        this.binding.tvQaList.performClick();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        this.selectedNum = 0;
        this.binding.tvPersonInfo.performClick();
    }

    public void changeTitleVis(boolean vis) {
        this.activityWanosBaseBinding.titleBar.tvTitle.setVisibility(vis ? 0 : 8);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        OnBackPressedCallBack onBackPressedCallBack = this.onBackPressedCallBack;
        if (onBackPressedCallBack == null || !onBackPressedCallBack.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, (Class<?>) UserInfoAndOrderActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_person_info) {
            if (this.binding.tvPersonInfo.isSelected()) {
                return;
            }
            this.selectedNum = 0;
            this.binding.hintPer.setVisibility(0);
            this.binding.hintQa.setVisibility(8);
            this.binding.hintOrder.setVisibility(8);
            this.binding.tvPersonInfo.setBackgroundResource(R.drawable.p_user_left_hint);
            this.binding.tvOrderInfo.setBackgroundResource(0);
            this.binding.tvQaList.setBackgroundResource(0);
            initUserInfoFragment();
            return;
        }
        if (view.getId() == R.id.tv_order_info) {
            if (this.binding.tvOrderInfo.isSelected()) {
                return;
            }
            this.selectedNum = 1;
            this.binding.hintPer.setVisibility(8);
            this.binding.hintQa.setVisibility(8);
            this.binding.hintOrder.setVisibility(0);
            this.binding.tvPersonInfo.setBackgroundResource(0);
            this.binding.tvQaList.setBackgroundResource(0);
            this.binding.tvOrderInfo.setBackgroundResource(R.drawable.p_user_left_hint);
            initOrderInfoFragment();
            return;
        }
        if (view.getId() != R.id.tv_qa_list || this.binding.tvQaList.isSelected()) {
            return;
        }
        this.selectedNum = 2;
        this.binding.hintQa.setVisibility(0);
        this.binding.hintOrder.setVisibility(8);
        this.binding.hintPer.setVisibility(8);
        this.binding.tvQaList.setBackgroundResource(R.drawable.p_user_left_hint);
        this.binding.tvOrderInfo.setBackgroundResource(0);
        this.binding.tvPersonInfo.setBackgroundResource(0);
        initQAListFragment();
    }

    private void onHideSoftInput() {
        if (getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null) {
            return;
        }
        hideSoftInput();
    }

    private void hideSoftInput() {
        UserInfoFragment userInfoFragment = this.userInfoFragment;
        if (userInfoFragment == null || userInfoFragment.getBinding() == null) {
            return;
        }
        FragmentUserInfoBinding binding = this.userInfoFragment.getBinding();
        if (binding.etNickName.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            binding.etNickName.clearFocus();
        }
    }

    private void initLeftMenu() {
        this.binding.tvPersonInfo.setSelected(false);
        this.binding.tvOrderInfo.setSelected(false);
        this.binding.tvQaList.setSelected(false);
    }

    private void initUserInfoFragment() {
        initLeftMenu();
        this.binding.tvPersonInfo.setSelected(true);
        if (this.userInfoFragment == null) {
            this.userInfoFragment = new UserInfoFragment();
        }
        showFragment(this.userInfoFragment);
    }

    private void initOrderInfoFragment() {
        initLeftMenu();
        this.binding.tvOrderInfo.setSelected(true);
        if (this.orderInfoFragment == null) {
            this.orderInfoFragment = new OrderInfoFragment();
        }
        showFragment(this.orderInfoFragment);
    }

    private void initQAListFragment() {
        initLeftMenu();
        this.binding.tvQaList.setSelected(true);
        if (this.questionFragment == null) {
            this.questionFragment = new QuestionFragment();
        }
        showFragment(this.questionFragment);
    }

    private void showFragment(Fragment fragment) {
        if (fragment == null || fragment.equals(this.currentFragment)) {
            return;
        }
        this.currentFragment = fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.layout_right_bottom, fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    public void setOnBackPressedCallBack(OnBackPressedCallBack onBackPressedCallBack) {
        this.onBackPressedCallBack = onBackPressedCallBack;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        onHideSoftInput();
        return event.getPointerCount() == 2;
    }
}
