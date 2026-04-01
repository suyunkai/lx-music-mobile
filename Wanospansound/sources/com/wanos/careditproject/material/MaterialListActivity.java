package com.wanos.careditproject.material;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CloudInfoResponse;
import com.wanos.careditproject.databinding.ActivityMaterialListBinding;
import com.wanos.careditproject.event.MaterialPlayEvent;
import com.wanos.careditproject.event.SelectedStatusEvent;
import com.wanos.careditproject.material.adapter.MaterialListAdpter;
import com.wanos.careditproject.material.fragment.CommonMaterialFragment;
import com.wanos.careditproject.material.fragment.MyMaterialFragment;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.view.BaseAppActivity;
import com.wanos.careditproject.view.widget.CreatorPagerTitleView;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.editmain.usb.UsbHelper;
import com.wanos.editmain.usb.receiver.USBBroadcastReceiver;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialListActivity extends BaseAppActivity implements View.OnClickListener, USBBroadcastReceiver.UsbListener {
    public static final String KeyReplaceClipId = "replaceClipId";
    public static final String TAG = "MaterialList";
    public static final String pageType = "pageType";
    private ActivityMaterialListBinding binding;
    private HashMap<String, UsbDevice> deviceList;
    private UsbHelper instance;
    private ActivityResultLauncher<Intent> launcher;
    private MediaPlayerHelper mMediaPlayerHelper;
    private int mPageType;
    private MaterialTypeInfoBean materialDataInfo;
    private int titleType;
    List<MaterialTypeInfoBean> materialTypeList = new ArrayList();
    private MaterialPlayEvent materialPlayEvent = new MaterialPlayEvent(MediaPlayerEnum.CallBackState.COMPLETE, null);
    private Set<String> collectDataChangeList = new HashSet();

    @Override // com.wanos.careditproject.view.BaseAppActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityMaterialListBinding activityMaterialListBindingInflate = ActivityMaterialListBinding.inflate(getLayoutInflater());
        this.binding = activityMaterialListBindingInflate;
        setContentView(activityMaterialListBindingInflate.getRoot());
        this.mMediaPlayerHelper = MediaPlayerHelperUtil.create(EditingUtils.getShortCacheDir(), EditingUtils.DEFAULT_SHORT_FILE_MAX_CACHE_SIZE);
        Intent intent = getIntent();
        this.mPageType = intent.getIntExtra(pageType, -1);
        EditingParams.getInstance().setReplaceClipId(intent.getStringExtra(KeyReplaceClipId));
        initView();
    }

    @Override // com.wanos.careditproject.view.BaseAppActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    public MaterialPlayEvent getMaterialPlayEvent() {
        return this.materialPlayEvent;
    }

    private void updateCollectDataChangeList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.collectDataChangeList.contains(str)) {
            this.collectDataChangeList.remove(str);
        } else {
            this.collectDataChangeList.add(str);
        }
    }

    public void clearCollectDataChangeList() {
        this.collectDataChangeList.clear();
    }

    public boolean hasCollectDataChangeList() {
        return this.collectDataChangeList.size() > 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.turn_back) {
            Log.i(TAG, "退出素材库");
            this.mMediaPlayerHelper.pause();
            finish();
        } else if (id == R.id.search_btn) {
            pause();
            EventBus.getDefault().post(new SelectedStatusEvent());
            startActivity(new Intent(this, (Class<?>) SearchActivity.class), true);
        }
    }

    private void initView() {
        findViewById(R.id.turn_back).setOnClickListener(this);
        findViewById(R.id.search_btn).setOnClickListener(this);
        requestTypeData();
        if (this.mPageType == 1) {
            initUsbHelper();
        }
        this.mMediaPlayerHelper.setOnStatusCallbackListener(new OnStatusCallbackListener() { // from class: com.wanos.careditproject.material.MaterialListActivity$$ExternalSyntheticLambda0
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
            public final void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
                this.f$0.m374x695c89bf(callBackState, objArr);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-careditproject-material-MaterialListActivity, reason: not valid java name */
    /* synthetic */ void m374x695c89bf(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
        EditingUtils.log("setOnStatusCallbackListener status: " + callBackState);
        this.materialPlayEvent = new MaterialPlayEvent(callBackState, this.materialDataInfo);
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED || callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.COMPLETE || callBackState == MediaPlayerEnum.CallBackState.PREPARING || callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION) {
            EventBus.getDefault().post(this.materialPlayEvent);
        }
    }

    void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        initViewPager();
        commonNavigator.setAdapter(new AnonymousClass1());
        this.binding.materialMagicindicator.setNavigator(commonNavigator);
        this.binding.materialListViewpager.setUserInputEnabled(false);
        this.binding.materialListViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.careditproject.material.MaterialListActivity.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
                MaterialListActivity.this.binding.materialMagicindicator.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                MaterialListActivity materialListActivity = MaterialListActivity.this;
                materialListActivity.titleType = materialListActivity.materialTypeList.get(i).getId();
                MaterialListActivity.this.binding.materialMagicindicator.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                super.onPageScrollStateChanged(i);
                MaterialListActivity.this.binding.materialMagicindicator.onPageScrollStateChanged(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.material.MaterialListActivity$1, reason: invalid class name */
    class AnonymousClass1 extends CommonNavigatorAdapter {
        AnonymousClass1() {
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public int getCount() {
            if (MaterialListActivity.this.materialTypeList == null) {
                return 0;
            }
            return MaterialListActivity.this.materialTypeList.size();
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerTitleView getTitleView(Context context, final int i) {
            CreatorPagerTitleView creatorPagerTitleView = new CreatorPagerTitleView(context);
            creatorPagerTitleView.setNormalColor(MaterialListActivity.this.getColor(R.color.material_title_text_normal_color));
            creatorPagerTitleView.setSelectedColor(MaterialListActivity.this.getColor(R.color.material_title_text_selected_color));
            creatorPagerTitleView.setText(MaterialListActivity.this.materialTypeList.get(i).getName());
            creatorPagerTitleView.setPadding(UIUtil.dip2px(context, 0.0d), UIUtil.dip2px(context, 0.0d), UIUtil.dip2px(context, 80.0d), UIUtil.dip2px(context, 0.0d));
            creatorPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.material.MaterialListActivity$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m375x35f7033a(i, view);
                }
            });
            return creatorPagerTitleView;
        }

        /* JADX INFO: renamed from: lambda$getTitleView$0$com-wanos-careditproject-material-MaterialListActivity$1, reason: not valid java name */
        /* synthetic */ void m375x35f7033a(int i, View view) {
            MaterialListActivity.this.binding.materialListViewpager.setCurrentItem(i, false);
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerIndicator getIndicator(Context context) {
            LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
            linePagerIndicator.setColors(Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK));
            linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 3.0d));
            linePagerIndicator.setLineWidth(UIUtil.dip2px(context, 25.0d));
            linePagerIndicator.setMode(2);
            return linePagerIndicator;
        }
    }

    private void requestTypeData() {
        this.binding.tvTitle.setText(getString(this.mPageType == 0 ? R.string.material_title_my_material_library : R.string.material_title_library));
        this.binding.searchBtn.setVisibility(this.mPageType == 0 ? 8 : 0);
        if (this.materialTypeList.size() == 0) {
            if (this.mPageType == 0) {
                for (int i = 3; i <= 4; i++) {
                    MaterialTypeInfoBean materialTypeInfoBean = new MaterialTypeInfoBean();
                    materialTypeInfoBean.setId(i);
                    if (i == 3) {
                        materialTypeInfoBean.setName(getString(com.wanos.media.R.string.material_title_my_cloud_material));
                    } else {
                        materialTypeInfoBean.setName(getString(com.wanos.media.R.string.material_title_local_material));
                    }
                    this.materialTypeList.add(materialTypeInfoBean);
                }
            } else {
                for (int i2 = 0; i2 < 3; i2++) {
                    MaterialTypeInfoBean materialTypeInfoBean2 = new MaterialTypeInfoBean();
                    materialTypeInfoBean2.setId(i2);
                    if (i2 == 0) {
                        materialTypeInfoBean2.setName(getString(com.wanos.media.R.string.material_title_music_material));
                    } else if (i2 == 1) {
                        materialTypeInfoBean2.setName(getString(com.wanos.media.R.string.material_title_sound_effect_material));
                    } else {
                        materialTypeInfoBean2.setName(getString(com.wanos.media.R.string.material_title_my_collect));
                    }
                    this.materialTypeList.add(materialTypeInfoBean2);
                }
            }
            initMagicIndicator();
        }
    }

    private void initUsbHelper() {
        if (this.instance == null) {
            UsbHelper usbHelper = UsbHelper.getInstance();
            this.instance = usbHelper;
            usbHelper.setReceiverUsbListener(this);
        }
        if (!this.instance.isUsbConnect()) {
            this.instance.getDeviceList();
        }
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wanos.careditproject.material.MaterialListActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.m373x872df30b((ActivityResult) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initUsbHelper$1$com-wanos-careditproject-material-MaterialListActivity, reason: not valid java name */
    /* synthetic */ void m373x872df30b(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (ActivityCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                this.instance.setUsbConnect(true);
                if (this.instance.isMounted()) {
                    return;
                }
                this.instance.startStatePoll();
                return;
            }
            ToastUtil.showMsg("获取权限失败");
        }
    }

    private void initViewPager() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.materialTypeList.size(); i++) {
            if (this.mPageType == 0) {
                MyMaterialFragment myMaterialFragment = new MyMaterialFragment();
                myMaterialFragment.setTypeList(this.materialTypeList.get(i).getId());
                arrayList.add(myMaterialFragment);
            } else {
                CommonMaterialFragment commonMaterialFragment = new CommonMaterialFragment();
                commonMaterialFragment.setTypeList(this.materialTypeList.get(i).getId());
                arrayList.add(commonMaterialFragment);
            }
        }
        this.binding.materialListViewpager.setAdapter(new MaterialListAdpter(getSupportFragmentManager(), arrayList, getLifecycle()));
    }

    public void play(MaterialTypeInfoBean materialTypeInfoBean) {
        this.materialDataInfo = materialTypeInfoBean;
        if (materialTypeInfoBean != null) {
            if (this.titleType == 4) {
                this.mMediaPlayerHelper.playUrl(this, materialTypeInfoBean.getUrlSrcWanos(), false, -1);
            } else {
                getMaterialInfo(materialTypeInfoBean.getM_id(), this);
            }
        }
    }

    private void getMaterialInfo(final int i, final Context context) {
        if (this.titleType == 3) {
            CreatorRetrofitUtil.getCloudFileInfo(i, 2, new ResponseCallBack<CloudInfoResponse>(this) { // from class: com.wanos.careditproject.material.MaterialListActivity.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(CloudInfoResponse cloudInfoResponse) {
                    if (MaterialListActivity.this.isFinishing() || MaterialListActivity.this.isDestroyed() || cloudInfoResponse.data == null || cloudInfoResponse.data.getInfo() == null) {
                        return;
                    }
                    MaterialListActivity.this.materialDataInfo.setUrlSrc(cloudInfoResponse.data.getInfo().getUrlSrc());
                    MaterialListActivity.this.mMediaPlayerHelper.playUrl(context, MaterialListActivity.this.materialDataInfo.getUrlSrc(), false, i, 2);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                    Log.i(MaterialListActivity.TAG, "素材详情----" + i2 + InternalFrame.ID + str);
                    com.wanos.media.util.ToastUtil.showMsg(str);
                }
            });
        } else {
            CreatorRetrofitUtil.getAssetsInfo(i, 2, new ResponseCallBack<CloudInfoResponse>(this) { // from class: com.wanos.careditproject.material.MaterialListActivity.4
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(CloudInfoResponse cloudInfoResponse) {
                    if (MaterialListActivity.this.isFinishing() || MaterialListActivity.this.isDestroyed() || cloudInfoResponse.data == null || cloudInfoResponse.data.getInfo() == null) {
                        return;
                    }
                    MaterialListActivity.this.materialDataInfo.setUrlSrcWanos(cloudInfoResponse.data.getInfo().getUrlSrcWanos());
                    MaterialListActivity.this.mMediaPlayerHelper.playUrl(context, MaterialListActivity.this.materialDataInfo.getUrlSrcWanos(), false, i, 3);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                    Log.i(MaterialListActivity.TAG, "素材详情----" + i2 + InternalFrame.ID + str);
                    com.wanos.media.util.ToastUtil.showMsg(str);
                }
            });
        }
    }

    public void pause() {
        this.mMediaPlayerHelper.pause();
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void insertUsb(UsbDevice usbDevice) {
        if (this.instance == null) {
            UsbHelper usbHelper = UsbHelper.getInstance();
            this.instance = usbHelper;
            usbHelper.setReceiverUsbListener(this);
        }
        if (this.instance.isUsbConnect()) {
            return;
        }
        this.instance.getDeviceList();
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void removeUsb(UsbDevice usbDevice) {
        UsbHelper usbHelper = this.instance;
        if (usbHelper != null) {
            usbHelper.setUsbConnect(false);
            this.instance.setMounted(false);
        }
    }

    @Override // com.wanos.careditproject.view.BaseAppActivity, com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        super.showLoadingView(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 596) {
            if (ActivityCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                this.instance.setUsbConnect(true);
                if (this.instance.isMounted()) {
                    return;
                }
                this.instance.startStatePoll();
                return;
            }
            Log.i(TAG, "存储权限获取失败");
        }
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void usbPermissionPass(UsbDevice usbDevice) {
        this.instance.requestStoragePermission(this.launcher);
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void storagePermissionPass() {
        if (this.instance.isMounted()) {
            return;
        }
        this.instance.startStatePoll();
    }

    @Override // com.wanos.careditproject.view.BaseAppActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
            this.mMediaPlayerHelper.release();
            this.mMediaPlayerHelper = null;
        }
        super.onDestroy();
        EditingParams.getInstance().setReplaceClipId("");
        DataHelpAudioTrack.clearPreClipInfoOfAddRes();
        this.materialTypeList.clear();
    }
}
