package com.wanos.careditproject.material.fragment;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialDataInfo;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoListBean;
import com.wanos.WanosCommunication.response.GetMaterialListResponse;
import com.wanos.WanosCommunication.response.GetMaterialTypeListResponse;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.databinding.FragmentMaterialListBinding;
import com.wanos.careditproject.event.MaterialPlayEvent;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.dialog.CloudManageDialog;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.editmain.usb.UsbHelper;
import com.wanos.editmain.usb.receiver.USBBroadcastReceiver;
import com.wanos.media.base.BaseLazyFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class MyMaterialFragment extends BaseLazyFragment implements MultiLevelTreeAdapter.OnTreeAdapterListener, USBBroadcastReceiver.UsbListener, CloudManageDialog.OnUpLoadMaterialListener {
    private static final String ARG_PARAM1 = "titleType";
    private static final String TAG = "wanos:[MaterialListFragment]";
    private MultiLevelTreeAdapter adapter;
    private FragmentMaterialListBinding binding;
    private CloudManageDialog cloudManageDialog;
    private UsbHelper instance;
    private ActivityResultLauncher<Intent> launcher;
    private MaterialPlayEvent materialPlayEvent;
    private int titleType;
    private final int mPageSize = 200;
    private int mPage = 1;
    private List<MaterialTypeInfoBean> materialDataList = new ArrayList();
    private HashMap<Integer, Integer> listSizeMap = new HashMap<>();

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void closeLoadingListener() {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentMaterialListBinding fragmentMaterialListBindingInflate = FragmentMaterialListBinding.inflate(getLayoutInflater());
        this.binding = fragmentMaterialListBindingInflate;
        ((SimpleItemAnimator) fragmentMaterialListBindingInflate.materialRecyclerList.getItemAnimator()).setSupportsChangeAnimations(false);
        this.binding.materialRecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.materialRecyclerList.setPadding(Util.px2dip(getContext(), 202.0f), 0, Util.px2dip(getContext(), 202.0f), 0);
        MultiLevelTreeAdapter multiLevelTreeAdapter = new MultiLevelTreeAdapter(getActivity(), this.materialDataList);
        this.adapter = multiLevelTreeAdapter;
        multiLevelTreeAdapter.setActivityFlag(this.titleType);
        this.adapter.setOnTreeAdapterListener(this);
        this.binding.materialRecyclerList.setAdapter(this.adapter);
        updatePlayerStatus(((MaterialListActivity) getActivity()).getMaterialPlayEvent());
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.m383x2b3cb549((ActivityResult) obj);
            }
        });
        return this.binding.getRoot();
    }

    /* JADX INFO: renamed from: lambda$onCreateContentView$0$com-wanos-careditproject-material-fragment-MyMaterialFragment, reason: not valid java name */
    /* synthetic */ void m383x2b3cb549(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                Log.i("zt", "外部存储权限通过");
                this.instance.setUsbConnect(true);
                showLoadingView(-1, R.color.loading_text_color, "");
                if (!this.instance.isMounted()) {
                    this.instance.startStatePoll();
                    return;
                } else {
                    getUsbMaterialData();
                    return;
                }
            }
            Log.i(TAG, "获取权限失败");
        }
    }

    @Override // com.wanos.media.base.BaseLazyFragment
    public void lazyLoad() {
        showLoadingView(-1, R.color.loading_text_color, "");
        if (this.titleType == 4) {
            showLoadingView(-1, R.color.loading_text_color, getResources().getString(R.string.native_material_reading));
        }
        requestData(this.titleType);
    }

    public void setTypeList(int i) {
        this.titleType = i;
        MultiLevelTreeAdapter multiLevelTreeAdapter = this.adapter;
        if (multiLevelTreeAdapter != null) {
            multiLevelTreeAdapter.setActivityFlag(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUsbMaterialData() {
        Log.i("zt", "getUsbMaterialData()");
        UsbHelper usbHelper = this.instance;
        if (usbHelper != null) {
            if (usbHelper.isUsbConnect() && this.instance.isMounted() && this.materialDataList.size() == 0) {
                this.instance.startDataPoll();
                return;
            }
            if (!this.instance.isUsbConnect()) {
                Log.i("zt", "未连接----");
            }
            if (!this.instance.isMounted()) {
                Log.i("zt", "未挂载----");
            }
            if (this.materialDataList.size() > 0) {
                Log.i("zt", "已如读取过U盘数据");
            }
            refreshListUI(false);
        }
    }

    public void requestData(int i) {
        this.materialDataList.clear();
        if (i == 3) {
            getCloudFileList();
            return;
        }
        if (this.materialDataList.size() == 0) {
            if (this.instance == null) {
                UsbHelper usbHelper = UsbHelper.getInstance();
                this.instance = usbHelper;
                usbHelper.setReceiverUsbListener(this);
            }
            if (!this.instance.isUsbConnect()) {
                HashMap<String, UsbDevice> deviceList = this.instance.getDeviceList();
                if (deviceList == null || deviceList.isEmpty()) {
                    refreshListUI(true);
                    return;
                }
                return;
            }
            if (!this.instance.isMounted()) {
                this.instance.startStatePoll();
            } else {
                if (getActivity() == null || !isAdded()) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.getUsbMaterialData();
                    }
                });
            }
        }
    }

    private void getCloudFileList() {
        CreatorRetrofitUtil.getCloudMaterialType(0, new ResponseCallBack<GetMaterialTypeListResponse>(getContext()) { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialTypeListResponse getMaterialTypeListResponse) {
                MaterialTypeInfoListBean materialTypeInfoListBean = getMaterialTypeListResponse.data;
                if (materialTypeInfoListBean != null) {
                    MyMaterialFragment.this.materialDataList.addAll(0, materialTypeInfoListBean.getList());
                    if (MyMaterialFragment.this.materialDataList == null) {
                        MyMaterialFragment.this.materialDataList = new ArrayList();
                    }
                    MyMaterialFragment.this.adapter.setData(MyMaterialFragment.this.materialDataList);
                    MyMaterialFragment.this.adapter.notifyDataSetChanged();
                    MyMaterialFragment.this.refreshListUI(true);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                Log.i(MyMaterialFragment.TAG, "请求素材库类型失败");
                ToastUtils.showShort(str);
                MyMaterialFragment.this.refreshListUI(true);
            }
        });
        getRootDirMaterialList();
    }

    private void getRootDirMaterialList() {
        CreatorRetrofitUtil.getCloudMaterialList(0, this.mPage, 200, new ResponseCallBack<GetMaterialListResponse>(getActivity()) { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialListResponse getMaterialListResponse) {
                List<MaterialDataInfo> list = getMaterialListResponse.data.getList();
                if (list == null || list.size() <= 0) {
                    return;
                }
                MyMaterialFragment.this.addMaterialDataToList(true, null, list);
                MyMaterialFragment.this.adapter.notifyDataSetChanged();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                Log.i(MyMaterialFragment.TAG, "getCloudMaterialList msg = " + str);
            }
        });
    }

    private void getCloudMaterialList(final boolean z, final int i, final MaterialTypeInfoBean materialTypeInfoBean) {
        CreatorRetrofitUtil.getCloudMaterialList(materialTypeInfoBean.getId(), this.mPage, 200, new ResponseCallBack<GetMaterialListResponse>(getActivity()) { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMaterialListResponse getMaterialListResponse) {
                List<MaterialDataInfo> list = getMaterialListResponse.data.getList();
                MaterialTypeInfoBean materialTypeInfoBeanAddMaterialDataToList = MyMaterialFragment.this.addMaterialDataToList(z, materialTypeInfoBean, list);
                if (materialTypeInfoBeanAddMaterialDataToList == null || list.size() == 0) {
                    MyMaterialFragment.this.adapter.updateListView(i, materialTypeInfoBean);
                    ToastUtil.showMsg(R.string.material_current_file_no_material);
                } else {
                    MyMaterialFragment.this.adapter.updateListView(i, materialTypeInfoBeanAddMaterialDataToList);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtils.showShort(str);
                MyMaterialFragment.this.closeLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MaterialTypeInfoBean addMaterialDataToList(boolean z, MaterialTypeInfoBean materialTypeInfoBean, List<MaterialDataInfo> list) {
        List<MaterialTypeInfoBean> arrayList;
        if (materialTypeInfoBean != null && this.listSizeMap.get(Integer.valueOf(materialTypeInfoBean.getId())) != null && !z && this.listSizeMap.get(Integer.valueOf(materialTypeInfoBean.getId())).intValue() == list.size()) {
            Log.i("zt", "该文件下素材数量相同");
            return null;
        }
        if (materialTypeInfoBean == null) {
            arrayList = null;
        } else if (this.titleType == 3) {
            arrayList = materialTypeInfoBean.getKids();
        } else {
            arrayList = materialTypeInfoBean.getChildren();
        }
        if (arrayList == null || arrayList.size() == 0) {
            arrayList = new ArrayList<>();
        } else {
            arrayList.removeIf(new Predicate() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MyMaterialFragment.lambda$addMaterialDataToList$1((MaterialTypeInfoBean) obj);
                }
            });
        }
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                MaterialTypeInfoBean materialTypeInfoBean2 = new MaterialTypeInfoBean();
                MaterialDataInfo materialDataInfo = list.get(i);
                materialTypeInfoBean2.setViewType(2);
                materialTypeInfoBean2.setType(materialDataInfo.getType());
                materialTypeInfoBean2.setM_id(materialDataInfo.getId());
                materialTypeInfoBean2.setSampleNum(materialDataInfo.getSampleNum());
                materialTypeInfoBean2.setChannel(materialDataInfo.getChannel());
                materialTypeInfoBean2.setM_name(materialDataInfo.getName());
                materialTypeInfoBean2.setUrlSrc(materialDataInfo.getUrlSrc());
                materialTypeInfoBean2.setTimeLen(materialDataInfo.getTimeLen());
                materialTypeInfoBean2.setFormat(materialDataInfo.getFormat());
                materialTypeInfoBean2.setUrlSrcWanos(materialDataInfo.getUrlSrcWanos());
                if (this.titleType == 3) {
                    materialTypeInfoBean2.setDirId(materialDataInfo.getDirId());
                } else {
                    materialTypeInfoBean2.setTypeId(materialDataInfo.getTypeId());
                }
                materialTypeInfoBean2.setSampleTate(materialDataInfo.getSampleTate());
                materialTypeInfoBean2.setCollect(materialDataInfo.isCollect());
                materialTypeInfoBean2.setUserId(materialDataInfo.getUserId());
                materialTypeInfoBean2.setM_parentId(materialDataInfo.getParentId());
                materialTypeInfoBean2.setIsWanosAudio(materialDataInfo.getIsWanosAudio());
                materialTypeInfoBean2.setUrlSize(materialDataInfo.getUrlSize());
                materialTypeInfoBean2.setUrlWanosSize(materialDataInfo.getUrlWanosSize());
                materialTypeInfoBean2.setSampleRate(materialDataInfo.getSampleRate());
                materialTypeInfoBean2.setBallRouteMap(materialDataInfo.getBallRouteMap());
                materialTypeInfoBean2.setTransitionWanosStatus(materialDataInfo.getTransitionWanosStatus());
                materialTypeInfoBean2.setCreateTime(materialDataInfo.getCreateTime());
                materialTypeInfoBean2.setUpdateTime(materialDataInfo.getUpdateTime());
                if (materialTypeInfoBean == null) {
                    this.materialDataList.add(materialTypeInfoBean2);
                } else {
                    arrayList.add(materialTypeInfoBean2);
                }
            }
            if (materialTypeInfoBean != null) {
                this.listSizeMap.put(Integer.valueOf(materialTypeInfoBean.getId()), Integer.valueOf(list.size()));
                if (this.titleType == 3) {
                    materialTypeInfoBean.setKids(arrayList);
                } else {
                    materialTypeInfoBean.setChildren(arrayList);
                }
                return materialTypeInfoBean;
            }
        }
        return null;
    }

    static /* synthetic */ boolean lambda$addMaterialDataToList$1(MaterialTypeInfoBean materialTypeInfoBean) {
        return materialTypeInfoBean.getViewType() == 2;
    }

    @Override // com.wanos.media.base.BaseLazyFragment, com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.listSizeMap.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePlayerStatus(MaterialPlayEvent materialPlayEvent) {
        this.materialPlayEvent = materialPlayEvent;
        this.adapter.updatePlayStatus(materialPlayEvent.getStatus(), materialPlayEvent.getMaterialDataInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDateCloudMaterialChange(Message message) {
        if (this.titleType == 3) {
            if (message.what == 18 || message.what == 19) {
                this.materialDataList.clear();
                getCloudFileList();
            }
        }
    }

    @Override // com.wanos.careditproject.view.dialog.CloudManageDialog.OnUpLoadMaterialListener
    public void onUpdateCloudMaterial() {
        Message message = new Message();
        message.what = 18;
        EventBus.getDefault().post(message);
    }

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void onUpload(int i, MaterialTypeInfoBean materialTypeInfoBean) {
        String urlSrcWanos = materialTypeInfoBean.getUrlSrcWanos();
        Log.i("zt", "文件路径----" + urlSrcWanos);
        File file = new File(urlSrcWanos);
        if (!file.exists()) {
            Log.i("zt", "当前文件不存在");
            return;
        }
        CloudManageDialog cloudManageDialog = new CloudManageDialog(i, file);
        this.cloudManageDialog = cloudManageDialog;
        cloudManageDialog.setOnUpLoadMaterialListener(this);
        this.cloudManageDialog.showDialog(getChildFragmentManager());
    }

    @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.OnTreeAdapterListener
    public void onItemClick(int i, MaterialTypeInfoBean materialTypeInfoBean) {
        getCloudMaterialList(true, i, materialTypeInfoBean);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 596) {
            if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                this.instance.setUsbConnect(true);
                Log.i("zt", "外部存储权限通过");
                showLoadingView(-1, R.color.loading_text_color, "");
                if (!this.instance.isMounted()) {
                    this.instance.startStatePoll();
                    return;
                } else {
                    getUsbMaterialData();
                    return;
                }
            }
            Log.i(TAG, "存储权限获取失败");
        }
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void insertUsb(UsbDevice usbDevice) {
        if (this.instance == null) {
            UsbHelper usbHelper = UsbHelper.getInstance();
            this.instance = usbHelper;
            usbHelper.setReceiverUsbListener(this);
        }
        this.instance.getDeviceList();
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void removeUsb(UsbDevice usbDevice) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        CloudManageDialog cloudManageDialog = this.cloudManageDialog;
        if (cloudManageDialog != null) {
            cloudManageDialog.closeLoadingView();
            this.cloudManageDialog.dismiss();
            this.cloudManageDialog = null;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m384x8077afe8();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$removeUsb$2$com-wanos-careditproject-material-fragment-MyMaterialFragment, reason: not valid java name */
    /* synthetic */ void m384x8077afe8() {
        UsbHelper usbHelper = this.instance;
        if (usbHelper != null) {
            usbHelper.setUsbConnect(false);
            this.instance.setMounted(false);
        }
        if (this.materialDataList.size() > 0) {
            this.materialDataList.clear();
        }
        refreshListUI(true);
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void usbPermissionPass(UsbDevice usbDevice) {
        Log.i("zt", "USB权限通过");
        this.instance.requestStoragePermission(this.launcher);
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void storagePermissionPass() {
        Log.i("zt", "外部存储权限通过");
        if (isVisible()) {
            showLoadingView(-1, R.color.loading_text_color, "");
        }
        if (!this.instance.isMounted()) {
            this.instance.startStatePoll();
        } else {
            getUsbMaterialData();
        }
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void usbPermissionFailed(UsbDevice usbDevice) {
        Log.i("zt", "权限未通过");
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void onMountSuccessful() {
        Log.i("zt", "onMountSuccessful()");
        if (getActivity() == null || !isAdded()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment.4
            @Override // java.lang.Runnable
            public void run() {
                MyMaterialFragment.this.showLoadingView(-1, R.color.loading_text_color, "");
                MyMaterialFragment.this.getUsbMaterialData();
            }
        });
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void getUsbDataSuccessful(final List<File> list) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m382xc2a3bef5(list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getUsbDataSuccessful$3$com-wanos-careditproject-material-fragment-MyMaterialFragment, reason: not valid java name */
    /* synthetic */ void m382xc2a3bef5(List list) {
        if (list.size() > 0) {
            List<MaterialTypeInfoBean> listFileToMaterialDataInfo = this.instance.FileToMaterialDataInfo(list, this.materialDataList);
            this.materialDataList = listFileToMaterialDataInfo;
            this.adapter.setData(listFileToMaterialDataInfo);
            this.adapter.notifyDataSetChanged();
        } else {
            Log.i("zt", "文件列表为空");
        }
        refreshListUI(true);
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void getUsbDataFailed() {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.fragment.MyMaterialFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m381x9f44d759();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getUsbDataFailed$4$com-wanos-careditproject-material-fragment-MyMaterialFragment, reason: not valid java name */
    /* synthetic */ void m381x9f44d759() {
        refreshListUI(true);
    }

    @Override // com.wanos.careditproject.view.dialog.CloudManageDialog.OnUpLoadMaterialListener
    public boolean onUpLoadClick() throws Throwable {
        CloudManageDialog cloudManageDialog = this.cloudManageDialog;
        if (cloudManageDialog != null) {
            File file = cloudManageDialog.getFile();
            String str = EditingUtils.getResLocalPath() + BceConfig.BOS_DELIMITER + file.getName();
            boolean zCopyFile = EditingUtils.copyFile(file.getAbsolutePath(), str);
            File file2 = new File(str);
            if (!file2.exists()) {
                Log.i("zt", "当前文件不存在");
                return false;
            }
            if (zCopyFile) {
                return this.cloudManageDialog.upLoadMaterial(file2);
            }
        }
        return false;
    }

    @Override // com.wanos.careditproject.view.dialog.CloudManageDialog.OnUpLoadMaterialListener
    public void onUpLoadSuccess(int i) {
        if (this.adapter != null && this.cloudManageDialog != null) {
            new MaterialDataInfo().setCollect(true);
            this.adapter.setUploadStatus(i);
        }
        CloudManageDialog cloudManageDialog = this.cloudManageDialog;
        if (cloudManageDialog != null && cloudManageDialog.isVisible()) {
            this.cloudManageDialog.dismiss();
        }
        Message message = new Message();
        message.what = 19;
        EventBus.getDefault().post(message);
        ToastUtil.showMsg(getString(R.string.cloud_dialog_upLoad_success));
    }

    @Override // com.wanos.careditproject.view.dialog.CloudManageDialog.OnUpLoadMaterialListener
    public void onUpLoadFailed() {
        ToastUtil.showMsg(getString(R.string.cloud_dialog_upload_fail));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshListUI(boolean z) {
        if (z) {
            closeLoadingView();
        }
        if (this.materialDataList.size() == 0) {
            this.binding.materialRecyclerList.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_str);
        } else {
            this.binding.materialRecyclerList.setVisibility(0);
            this.binding.emptyView.getRoot().setVisibility(8);
        }
    }
}
