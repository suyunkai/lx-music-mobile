package com.wanos.media.ui.theme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.presenter.MeThemePresenter;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.InputShareCodeDialog;
import com.wanos.media.view.RelaxCollectListFragment;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ActivityMeThemeBinding;

/* JADX INFO: loaded from: classes3.dex */
public class MeThemeActivity extends WanosBaseActivity<MeThemePresenter> {
    private static final String TAG = "MeThemeActivity";
    private ActivityMeThemeBinding mBinding;
    private RelaxCollectListFragment mMeMingXiangFragment;
    private RelaxCollectListFragment mMeXiaoQiFragment;

    public static void onLaunchActivity(Fragment fragment) {
        fragment.startActivity(new Intent(fragment.getContext(), (Class<?>) MeThemeActivity.class));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter = new MeThemePresenter();
        ActivityMeThemeBinding activityMeThemeBindingInflate = ActivityMeThemeBinding.inflate(getLayoutInflater());
        this.mBinding = activityMeThemeBindingInflate;
        setContentView(activityMeThemeBindingInflate.getRoot());
        setPlayBarVisibility(8);
        setZeroTitleBarListener(new CustomClick() { // from class: com.wanos.media.ui.theme.MeThemeActivity.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                InputShareCodeDialog.showInputCode(MeThemeActivity.this.getSupportFragmentManager());
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_ADD_SHARE_CODE);
            }
        });
        setTitleText(R.string.my_sound_space);
        if (bundle != null) {
            Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag("XiaoQi");
            if (fragmentFindFragmentByTag instanceof RelaxCollectListFragment) {
                this.mMeXiaoQiFragment = (RelaxCollectListFragment) fragmentFindFragmentByTag;
            }
            Fragment fragmentFindFragmentByTag2 = getSupportFragmentManager().findFragmentByTag("MingXiang");
            if (fragmentFindFragmentByTag2 instanceof RelaxCollectListFragment) {
                this.mMeMingXiangFragment = (RelaxCollectListFragment) fragmentFindFragmentByTag2;
            }
        }
        this.mBinding.btnXiaoQi.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.theme.MeThemeActivity.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                MeThemeActivity.this.showFragment(1);
            }
        });
        this.mBinding.btnMingXiang.setOnClickListener(new CustomClick() { // from class: com.wanos.media.ui.theme.MeThemeActivity.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                MeThemeActivity.this.showFragment(2);
            }
        });
        showFragment(1);
    }

    public void showFragment(int i) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (i == 1) {
            this.mBinding.btnXiaoQi.setSelected(true);
            this.mBinding.btnMingXiang.setSelected(false);
            if (this.mMeXiaoQiFragment == null) {
                this.mMeXiaoQiFragment = RelaxCollectListFragment.newInstance(1);
                fragmentTransactionBeginTransaction.add(R.id.fl_me_theme, this.mMeXiaoQiFragment, "XiaoQi");
            }
            fragmentTransactionBeginTransaction.show(this.mMeXiaoQiFragment);
            RelaxCollectListFragment relaxCollectListFragment = this.mMeMingXiangFragment;
            if (relaxCollectListFragment != null) {
                fragmentTransactionBeginTransaction.hide(relaxCollectListFragment);
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            return;
        }
        if (i == 2) {
            this.mBinding.btnXiaoQi.setSelected(false);
            this.mBinding.btnMingXiang.setSelected(true);
            if (this.mMeMingXiangFragment == null) {
                this.mMeMingXiangFragment = RelaxCollectListFragment.newInstance(2);
                fragmentTransactionBeginTransaction.add(R.id.fl_me_theme, this.mMeMingXiangFragment, "MingXiang");
            }
            fragmentTransactionBeginTransaction.show(this.mMeMingXiangFragment);
            RelaxCollectListFragment relaxCollectListFragment2 = this.mMeXiaoQiFragment;
            if (relaxCollectListFragment2 != null) {
                fragmentTransactionBeginTransaction.hide(relaxCollectListFragment2);
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            return;
        }
        ZeroLogcatTools.d(TAG, "showFragment: tableId = " + i);
    }
}
