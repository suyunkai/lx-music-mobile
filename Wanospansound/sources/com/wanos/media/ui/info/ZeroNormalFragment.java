package com.wanos.media.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.base.BaseZeroFragment;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.presenter.ZeroNormalPresenter;
import com.wanos.media.util.ZeroPressureDataManager;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter;
import com.wanos.media.widget.video.ZeroVideoPageView;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.FragmentZeroNormalBinding;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroNormalFragment extends BaseZeroFragment<FragmentZeroNormalBinding, ZeroNormalPresenter> {
    private static final String TAG = "ZeroNormalFragment";

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initSetting(Bundle bundle) {
    }

    public static ZeroNormalFragment newInstance(long j, ZeroPageMode zeroPageMode) {
        ZeroNormalFragment zeroNormalFragment = new ZeroNormalFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(SendShareCodeViewModel.KEY_THEME_ID, j);
        bundle.putSerializable("zeroPageMode", zeroPageMode);
        zeroNormalFragment.setArguments(bundle);
        return zeroNormalFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.BaseZeroFragment
    public FragmentZeroNormalBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return FragmentZeroNormalBinding.inflate(layoutInflater, viewGroup, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.BaseZeroFragment
    public ZeroNormalPresenter initPresenter(Bundle bundle) {
        return new ZeroNormalPresenter();
    }

    public void setNextTheme() {
        if (this.mViewBinding != 0) {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.nextTheme();
        }
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initListener(Bundle bundle) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setCallback(new ZeroVideoPageView.IPageChangeListener() { // from class: com.wanos.media.ui.info.ZeroNormalFragment.1
            @Override // com.wanos.media.widget.video.ZeroVideoPageView.IPageChangeListener
            public void onPageChange(ZeroThemeInfo zeroThemeInfo) {
                if (ZeroNormalFragment.this.mBaseAction == null) {
                    return;
                }
                ZeroNormalFragment.this.mBaseAction.setSelectInfo(zeroThemeInfo);
            }

            @Override // com.wanos.media.widget.video.ZeroVideoPageView.IPageChangeListener
            public void onSceneInfoBeReady(AudioSceneInfoEntity audioSceneInfoEntity) {
                if (ZeroNormalFragment.this.mBaseAction == null) {
                    return;
                }
                ZeroNormalFragment.this.mBaseAction.setAudioData(audioSceneInfoEntity);
            }

            @Override // com.wanos.media.widget.video.ZeroVideoPageView.IPageChangeListener
            public void onSceneDuration(String[] strArr) {
                if (ZeroNormalFragment.this.mBaseAction == null) {
                    return;
                }
                ZeroNormalFragment.this.mBaseAction.setDurationInfo(strArr);
            }

            @Override // com.wanos.media.widget.video.ZeroVideoPageView.IPageChangeListener
            public void onError(String str) {
                if (ZeroNormalFragment.this.mBaseAction == null) {
                    return;
                }
                ZeroNormalFragment.this.mBaseAction.onThemeLoadError(str);
            }
        });
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    protected void initData(Bundle bundle) {
        Bundle arguments;
        if (this.mPresenter == 0 || this.mViewBinding == 0 || (arguments = getArguments()) == null) {
            return;
        }
        ZeroPageMode zeroPageMode = (ZeroPageMode) arguments.getSerializable("zeroPageMode");
        long j = arguments.getLong(SendShareCodeViewModel.KEY_THEME_ID, -1L);
        if (zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || zeroPageMode == ZeroPageMode.MING_XIANG_PRO) {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setThemeList(ZeroPressureDataManager.getInstance().getMxData(), j, zeroPageMode);
        } else if (zeroPageMode == ZeroPageMode.XIAO_QI_STANDARD || zeroPageMode == ZeroPageMode.XIAO_QI_PRO) {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setThemeList(ZeroPressureDataManager.getInstance().getXqData(), j, zeroPageMode);
        } else {
            ToastUtil.showMsg(getString(R.string.unknown_state));
        }
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    public int findSceneIndex(long j) {
        if (this.mViewBinding == 0) {
            return -1;
        }
        return ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.findSceneIndex(j);
    }

    @Override // com.wanos.media.base.BaseZeroFragment
    public void setControlState(boolean z, boolean z2) {
        if (this.mViewBinding == 0) {
            return;
        }
        if (z || z2) {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setIndicatorVisibility(4);
        } else {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setIndicatorVisibility(0);
        }
    }

    public void setAudioPosition(int i, final WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
        Bundle arguments;
        ZeroPageMode zeroPageMode;
        if (this.mViewBinding == 0 || (arguments = getArguments()) == null || (zeroPageMode = (ZeroPageMode) arguments.getSerializable("zeroPageMode")) == ZeroPageMode.XIAO_QI_PRO || zeroPageMode == ZeroPageMode.XIAO_QI_STANDARD) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setSceneIndex(i, new ZeroVideoPageView.ISceneLoadListener() { // from class: com.wanos.media.ui.info.ZeroNormalFragment.2
            @Override // com.wanos.media.widget.video.ZeroVideoPageView.ISceneLoadListener
            public void onSceneLoaded(AudioSceneInfoEntity audioSceneInfoEntity) {
                FragmentActivity activity = ZeroNormalFragment.this.getActivity();
                if (activity instanceof ZeroInfoActivity) {
                    ((ZeroInfoActivity) activity).setAudioData(audioSceneInfoEntity);
                }
                WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback2 = iItemStateCallback;
                if (iItemStateCallback2 != null) {
                    iItemStateCallback2.onItemState(true);
                }
            }

            @Override // com.wanos.media.widget.video.ZeroVideoPageView.ISceneLoadListener
            public void onSceneLoadError(String str) {
                WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback2 = iItemStateCallback;
                if (iItemStateCallback2 != null) {
                    iItemStateCallback2.onItemState(false);
                }
                ToastUtil.showMsg(str);
            }
        });
    }

    public void setViewPageEnable(boolean z) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setViewPageEnable(z);
    }

    public void setRandomScene(ZeroVideoPageView.ISceneLoadListener iSceneLoadListener) {
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.setRandomScene(iSceneLoadListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.onViewResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mViewBinding == 0) {
            return;
        }
        ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.onViewPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (this.mViewBinding == 0) {
            return;
        }
        if (z) {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.onViewPause();
        } else {
            ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.onViewResume();
        }
    }

    public int getPageScrollState() {
        if (this.mViewBinding == 0) {
            return 0;
        }
        return ((FragmentZeroNormalBinding) this.mViewBinding).wanosPage.getPageScrollState();
    }
}
