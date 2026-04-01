package com.wanos.media.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.adapter.SoundMenuAdapter;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.GetMaterialAudioInfo;
import com.wanos.media.entity.IMenuLibCallback;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.presenter.SoundMenuPresenter;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.FragmentSoundMenuBinding;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class SoundMenuFragment extends WanosBaseFragment<SoundMenuPresenter> implements SoundMenuView {
    private static final String TAG = "SoundMenuFragment";
    private IMenuLibCallback mMenuLibCallback;
    private SoundMenuAdapter mSoundMenuAdapter;
    private FragmentSoundMenuBinding mViewBinding;
    private ZeroPageMode mZeroPageMode = ZeroPageMode.XIAO_QI_STANDARD;

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String str) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    public static Fragment getInstance(GetAudioInfoResp.TagGroupDTO tagGroupDTO, ZeroPageMode zeroPageMode) {
        SoundMenuFragment soundMenuFragment = new SoundMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tagGroup", tagGroupDTO);
        bundle.putSerializable("zeroPageMode", zeroPageMode);
        soundMenuFragment.setArguments(bundle);
        return soundMenuFragment;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mPresenter = new SoundMenuPresenter(this);
        FragmentSoundMenuBinding fragmentSoundMenuBindingInflate = FragmentSoundMenuBinding.inflate(layoutInflater, viewGroup, false);
        this.mViewBinding = fragmentSoundMenuBindingInflate;
        return fragmentSoundMenuBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity instanceof ZeroInfoActivity) {
            this.mMenuLibCallback = ((ZeroInfoActivity) activity).getMenuLibCallback();
        }
        Bundle arguments = getArguments();
        if (this.mViewBinding == null || arguments == null || this.mMenuLibCallback == null) {
            return;
        }
        GetAudioInfoResp.TagGroupDTO tagGroupDTO = (GetAudioInfoResp.TagGroupDTO) arguments.getSerializable("tagGroup");
        this.mZeroPageMode = (ZeroPageMode) arguments.getSerializable("zeroPageMode");
        if (tagGroupDTO == null || tagGroupDTO.getSounds().isEmpty()) {
            ZeroLogcatTools.d(TAG, "tagGroupDTO is null or tagGroupDTO.getSounds().isEmpty()");
            return;
        }
        this.mSoundMenuAdapter = new SoundMenuAdapter(tagGroupDTO.getSounds(), this.mMenuLibCallback.getSceneAudioId());
        this.mViewBinding.rvSoundMenu.setLayoutManager(new GridLayoutManager(getContext(), 8));
        this.mViewBinding.rvSoundMenu.setAdapter(this.mSoundMenuAdapter);
        this.mSoundMenuAdapter.setOnItemClickListener(new SoundMenuAdapter.OnItemClickListener() { // from class: com.wanos.media.view.SoundMenuFragment$$ExternalSyntheticLambda0
            @Override // com.wanos.media.adapter.SoundMenuAdapter.OnItemClickListener
            public final void onItemClick(int i) {
                this.f$0.m598lambda$onViewCreated$0$comwanosmediaviewSoundMenuFragment(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-view-SoundMenuFragment, reason: not valid java name */
    /* synthetic */ void m598lambda$onViewCreated$0$comwanosmediaviewSoundMenuFragment(int i) {
        if (this.mPresenter == 0) {
            return;
        }
        SoundMenuAdapter.State itemState = this.mSoundMenuAdapter.getItemState(i);
        if (itemState.isDownloadState()) {
            ToastUtil.showMsg(R.string.zero_down_loading);
            return;
        }
        if (!itemState.isCache()) {
            ZeroLogcatTools.d(TAG, "未下载资源，不进行身份验证");
            ((SoundMenuPresenter) this.mPresenter).setMediaOptions(i, this.mSoundMenuAdapter.isVip(i));
            return;
        }
        int size = this.mMenuLibCallback.getSceneAudioId().size();
        if (this.mSoundMenuAdapter.getItemState(i).isSelect()) {
            if (size <= 1) {
                ToastUtil.showMsg(R.string.sound_min_size);
                return;
            }
        } else if (size >= getMaxSelectCode()) {
            ToastUtil.showMsg(getSelectTopErrorMsg());
            return;
        }
        ZeroLogcatTools.d(TAG, "选择资源，开始验证");
        ((SoundMenuPresenter) this.mPresenter).setMediaOptions(i, this.mSoundMenuAdapter.isVip(i));
    }

    @Override // com.wanos.media.view.SoundMenuView
    public void onLaunchPlayTask(final int i) {
        SoundMenuAdapter.State itemState = this.mSoundMenuAdapter.getItemState(i);
        final ThemeAudioInfoEntity itemData = this.mSoundMenuAdapter.getItemData(i);
        if (itemState.isSelect()) {
            if (this.mMenuLibCallback.deleteAudioSound(itemData.getSoundId())) {
                this.mSoundMenuAdapter.setItemSelectState(i, false);
                return;
            } else {
                ToastUtil.showMsg(getString(R.string.material_remove_fail));
                return;
            }
        }
        if (itemState.isCache()) {
            if (this.mMenuLibCallback.addAudioSound(itemData)) {
                this.mSoundMenuAdapter.setItemSelectState(i, true);
                return;
            } else {
                ToastUtil.showMsg(getString(R.string.material_add_fail));
                return;
            }
        }
        if (this.mPresenter == 0) {
            return;
        }
        this.mSoundMenuAdapter.setItemDownLoadState(i, true);
        HttpTools.getInstance().getMaterialAudioInfo((int) itemData.getSoundId(), new HttpCallback<GetMaterialAudioInfo>() { // from class: com.wanos.media.view.SoundMenuFragment.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseEntity<GetMaterialAudioInfo> baseEntity) {
                ArrayList arrayList = new ArrayList();
                if (baseEntity != null && baseEntity.getData() != null) {
                    Log.d(SoundMenuFragment.TAG, "onResponseSuccessful: 最新下载地址 = " + baseEntity.getData().getWanosPath());
                    itemData.setWanosPath(baseEntity.getData().getWanosPath());
                }
                Log.d(SoundMenuFragment.TAG, "onResponseSuccessful: 实际下载地址 = " + itemData.getWanosPath());
                arrayList.add(itemData);
                MusicCacheUtils.onLaunchSceneTask(arrayList, i, new MusicCacheUtils.IMultiAudioCacheCallback() { // from class: com.wanos.media.view.SoundMenuFragment.1.1
                    @Override // com.wanos.media.util.MusicCacheUtils.IMultiAudioCacheCallback
                    public void onTaskSuccess(ArrayList<ThemeAudioInfoEntity> arrayList2, int i2) {
                        SoundMenuFragment.this.mSoundMenuAdapter.setItemDownLoadState(i2, false);
                    }

                    @Override // com.wanos.media.util.MusicCacheUtils.IMultiAudioCacheCallback
                    public void onTaskError(String str) {
                        ToastUtil.showMsg(R.string.zero_down_error);
                        SoundMenuFragment.this.mSoundMenuAdapter.setItemDownLoadState(i, false);
                    }
                });
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtil.showMsg(str);
                SoundMenuFragment.this.mSoundMenuAdapter.setItemDownLoadState(i, false);
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
    }

    private int getMaxSelectCode() {
        return (this.mZeroPageMode == ZeroPageMode.MING_XIANG_PRO || this.mZeroPageMode == ZeroPageMode.MING_XIANG_STANDARD) ? 5 : 10;
    }

    private String getSelectTopErrorMsg() {
        return (this.mZeroPageMode == ZeroPageMode.MING_XIANG_PRO || this.mZeroPageMode == ZeroPageMode.MING_XIANG_STANDARD) ? StringUtils.format(StringUtils.getString(R.string.zero_toast_sound_select_hint), "5") : StringUtils.format(StringUtils.getString(R.string.zero_toast_sound_select_hint), "10");
    }
}
