package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.blankj.utilcode.util.BarUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.CloudManageContentAdapter;
import com.wanos.careditproject.adapter.CloudManageTitleAdapter;
import com.wanos.careditproject.data.bean.CloudInfoEntity;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CloudResponse;
import com.wanos.careditproject.data.response.CreateCloudResponse;
import com.wanos.careditproject.databinding.DialogCloundBinding;
import com.wanos.careditproject.utils.BaiduCloudUtils;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ToastUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class CloudManageDialog extends DialogFragment implements BaiduCloudUtils.OnUpLoadListener {
    private static final String FRAGMENT_TAG = "CloudManageDialog";
    private static final String TAG = "wanos:[CloudManageDialog]";
    private BaiduCloudUtils cloudUtils;
    private Call<CreateCloudResponse> createReq;
    private int curId;
    private File file;
    private Call<CloudResponse> getCloudListReq;
    private OnUpLoadMaterialListener listener;
    private final List<CloudInfoEntity> mCloudDirList;
    private final List<CloudInfoEntity> mCloudDirTitleList;
    private CloudManageContentAdapter mCloudManageContentAdapter;
    private CloudManageTitleAdapter mCloudManageTitleAdapter;
    private int mCreateDirCount;
    private DialogCloundBinding mViewBinding;
    private int position;

    public interface OnUpLoadMaterialListener {
        default boolean onUpLoadClick() {
            return true;
        }

        default void onUpLoadFailed() {
        }

        default void onUpLoadSuccess(int i) {
        }

        default void onUpdateCloudMaterial() {
        }
    }

    static /* synthetic */ int access$308(CloudManageDialog cloudManageDialog) {
        int i = cloudManageDialog.mCreateDirCount;
        cloudManageDialog.mCreateDirCount = i + 1;
        return i;
    }

    public CloudManageDialog(int i, File file) {
        this.mCreateDirCount = 1;
        this.mCloudDirList = new ArrayList();
        this.mCloudDirTitleList = new ArrayList();
        this.position = i;
        this.file = file;
        this.cloudUtils = BaiduCloudUtils.getInstance();
        if (file == null) {
            Log.i("zt", "文件为空");
        }
    }

    public static void show(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            Log.e(TAG, "show: FragmentManager = NULL");
            return;
        }
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragmentFindFragmentByTag instanceof CloudManageDialog) {
            ((CloudManageDialog) fragmentFindFragmentByTag).dismiss();
        }
        new CloudManageDialog().show(fragmentManager, FRAGMENT_TAG);
    }

    public void showDialog(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            Log.e(TAG, "show: FragmentManager = NULL");
            return;
        }
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragmentFindFragmentByTag instanceof CloudManageDialog) {
            ((CloudManageDialog) fragmentFindFragmentByTag).dismiss();
        }
        this.mCloudDirTitleList.add(new CloudInfoEntity(0, "我的云空间"));
        show(fragmentManager, FRAGMENT_TAG);
    }

    public void setOnUpLoadMaterialListener(OnUpLoadMaterialListener onUpLoadMaterialListener) {
        this.listener = onUpLoadMaterialListener;
    }

    public CloudManageDialog() {
        this.mCreateDirCount = 1;
        this.mCloudDirList = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.mCloudDirTitleList = arrayList;
        arrayList.add(new CloudInfoEntity(0, "我的云空间"));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogCloundBinding dialogCloundBindingInflate = DialogCloundBinding.inflate(layoutInflater, viewGroup, false);
        this.mViewBinding = dialogCloundBindingInflate;
        return dialogCloundBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        DialogCloundBinding dialogCloundBinding = this.mViewBinding;
        if (dialogCloundBinding == null) {
            return;
        }
        Util.setTextWeight(dialogCloundBinding.etDirName, 500);
        this.mViewBinding.llCreateDir.setVisibility(8);
        this.mViewBinding.btnCreateDir.setVisibility(4);
        this.mViewBinding.btnCancel.setVisibility(4);
        this.mViewBinding.btnUpload.setEnabled(false);
        this.mViewBinding.btnUpload.setBackground(getContext().getDrawable(R.drawable.cloud_btn_2));
        this.mCloudManageContentAdapter = new CloudManageContentAdapter(this.mCloudDirList);
        this.mViewBinding.rvDirContent.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewBinding.rvDirContent.setAdapter(this.mCloudManageContentAdapter);
        this.mCloudManageTitleAdapter = new CloudManageTitleAdapter(this.mCloudDirTitleList);
        this.mViewBinding.rvDirTitle.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mViewBinding.rvDirTitle.setAdapter(this.mCloudManageTitleAdapter);
        this.mViewBinding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m421x8d4175(view2);
            }
        });
        this.mViewBinding.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m422x1c39454(view2);
            }
        });
        this.mViewBinding.btnCreateDir.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                if (CloudManageDialog.this.mViewBinding.llCreateDir.getVisibility() == 0) {
                    return;
                }
                CloudManageDialog.this.showEmptyView(false, "");
                if (CloudManageDialog.this.mCloudDirList.size() > 0) {
                    CloudManageDialog.this.mViewBinding.rvDirContent.scrollToPosition(CloudManageDialog.this.mCloudDirList.size() - 1);
                }
                CloudManageDialog.this.mViewBinding.etDirName.setHint("新建文件夹" + CloudManageDialog.this.mCreateDirCount);
                CloudManageDialog.this.mViewBinding.llCreateDir.setVisibility(0);
            }
        });
        this.mViewBinding.btnCreateDirCancel.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                CloudManageDialog.this.mViewBinding.llCreateDir.setVisibility(8);
                CloudManageDialog cloudManageDialog = CloudManageDialog.this;
                cloudManageDialog.showEmptyView(cloudManageDialog.mCloudDirList.isEmpty(), "");
            }
        });
        this.mViewBinding.btnCreateDirDone.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.3
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                CloudManageDialog cloudManageDialog = CloudManageDialog.this;
                cloudManageDialog.createCloudDir((CloudInfoEntity) cloudManageDialog.mCloudDirTitleList.get(CloudManageDialog.this.mCloudDirTitleList.size() - 1));
            }
        });
        this.mCloudManageTitleAdapter.setOnItemClickListener(new CloudManageTitleAdapter.OnItemClickListener() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda4
            @Override // com.wanos.careditproject.adapter.CloudManageTitleAdapter.OnItemClickListener
            public final void onItemClick(CloudInfoEntity cloudInfoEntity) {
                this.f$0.m423x2f9e733(cloudInfoEntity);
            }
        });
        this.mCloudManageContentAdapter.setOnItemClickListener(new CloudManageContentAdapter.OnItemClickListener() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda5
            @Override // com.wanos.careditproject.adapter.CloudManageContentAdapter.OnItemClickListener
            public final void onItemClick(CloudInfoEntity cloudInfoEntity) {
                this.f$0.m424x4303a12(cloudInfoEntity);
            }
        });
        this.mViewBinding.btnUpload.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.4
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                if (CloudManageDialog.this.listener != null) {
                    CloudManageDialog.this.showLoading();
                    if (CloudManageDialog.this.listener.onUpLoadClick()) {
                        return;
                    }
                    CloudManageDialog.this.closeLoadingView();
                }
            }
        });
        showEmptyView(true, getString(R.string.cloud_dialog_loading));
        initCloudDirList(this.mCloudDirTitleList.get(0));
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m421x8d4175(View view) {
        cancelRequest();
        this.cloudUtils.cancelUpload();
        dismiss();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m422x1c39454(View view) {
        cancelRequest();
        this.cloudUtils.cancelUpload();
        dismiss();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$2$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m423x2f9e733(CloudInfoEntity cloudInfoEntity) {
        showCreateDirBtn();
        showEmptyView(true, getString(R.string.cloud_dialog_loading));
        initCloudDirList(cloudInfoEntity);
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$3$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m424x4303a12(CloudInfoEntity cloudInfoEntity) {
        if (this.mCloudDirTitleList.size() >= 3) {
            return;
        }
        this.mCloudDirTitleList.add(cloudInfoEntity);
        this.mCloudManageTitleAdapter.notifyDataSetChanged();
        showCreateDirBtn();
        showEmptyView(true, getString(R.string.cloud_dialog_loading));
        initCloudDirList(cloudInfoEntity);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        BarUtils.transparentStatusBar(window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCloudDirList(final CloudInfoEntity cloudInfoEntity) {
        if (this.mViewBinding == null || cloudInfoEntity == null) {
            return;
        }
        Log.d(TAG, "initCloudDirList: 文件加名称=" + cloudInfoEntity.getName() + ", ID=" + cloudInfoEntity.getId());
        this.curId = -1;
        this.mViewBinding.llCreateDir.setVisibility(8);
        this.mViewBinding.btnUpload.setEnabled(false);
        Call<CloudResponse> call = this.getCloudListReq;
        if (call != null && !call.isCanceled()) {
            this.getCloudListReq.cancel();
        }
        this.getCloudListReq = CreatorRetrofitUtil.getCloudDirList(cloudInfoEntity.getId(), new ResponseCallBack<CloudResponse>(getContext()) { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CloudResponse cloudResponse) {
                if (CloudManageDialog.this.mViewBinding == null) {
                    return;
                }
                CloudManageDialog.this.showCreateDirBtn();
                CloudManageDialog.this.mViewBinding.btnCancel.setVisibility(0);
                CloudManageDialog.this.mViewBinding.btnUpload.setEnabled(true);
                CloudManageDialog.this.mViewBinding.btnUpload.setBackground(CloudManageDialog.this.getContext().getDrawable(R.drawable.cloud_btn_3));
                CloudManageDialog.this.curId = cloudInfoEntity.getId();
                CloudManageDialog.this.mCloudDirList.clear();
                CloudManageDialog.this.mCloudDirList.addAll(cloudResponse.data.getList());
                CloudManageDialog.this.mCloudManageContentAdapter.notifyDataSetChanged();
                CloudManageDialog cloudManageDialog = CloudManageDialog.this;
                cloudManageDialog.showEmptyView(cloudManageDialog.mCloudDirList.isEmpty(), "上传到 " + cloudInfoEntity.getName());
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                CloudManageDialog.this.showEmptyView(true, "上传到 " + cloudInfoEntity.getName());
                CloudManageDialog.this.mViewBinding.btnCreateDir.setVisibility(0);
                CloudManageDialog.this.mViewBinding.btnCancel.setVisibility(0);
                CloudManageDialog.this.mViewBinding.btnUpload.setEnabled(true);
                CloudManageDialog.this.mViewBinding.btnUpload.setBackground(CloudManageDialog.this.getContext().getDrawable(R.drawable.cloud_btn_3));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCreateDirBtn() {
        if (this.mCloudDirTitleList.size() >= 3) {
            this.mViewBinding.btnCreateDir.setVisibility(4);
        } else {
            this.mViewBinding.btnCreateDir.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView(boolean z, String str) {
        if (z) {
            this.mViewBinding.rvDirContent.setVisibility(8);
            this.mViewBinding.vEmpty.setVisibility(0);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.mViewBinding.tvEmptyDirName.setText(str);
            return;
        }
        this.mViewBinding.rvDirContent.setVisibility(0);
        this.mViewBinding.vEmpty.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createCloudDir(final CloudInfoEntity cloudInfoEntity) {
        DialogCloundBinding dialogCloundBinding = this.mViewBinding;
        if (dialogCloundBinding == null || cloudInfoEntity == null) {
            return;
        }
        Editable text = dialogCloundBinding.etDirName.getText();
        if (text == null || text.length() == 0) {
            ToastUtil.showMsg("请输入文件夹名称~");
            return;
        }
        String string = text.toString();
        this.mViewBinding.llCreateDir.setVisibility(8);
        this.createReq = CreatorRetrofitUtil.createCloudDir(string, cloudInfoEntity.getId(), new ResponseCallBack<CreateCloudResponse>(getContext()) { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CreateCloudResponse createCloudResponse) {
                if (CloudManageDialog.this.mViewBinding == null) {
                    return;
                }
                CloudManageDialog.access$308(CloudManageDialog.this);
                CloudManageDialog.this.mViewBinding.etDirName.getText().clear();
                CloudManageDialog.this.initCloudDirList(cloudInfoEntity);
                if (CloudManageDialog.this.listener != null) {
                    CloudManageDialog.this.listener.onUpdateCloudMaterial();
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                if (CloudManageDialog.this.mViewBinding == null) {
                    return;
                }
                ToastUtil.showMsg(str);
                if (CloudManageDialog.this.mCloudDirList.isEmpty()) {
                    CloudManageDialog.this.mViewBinding.vEmpty.setVisibility(0);
                }
            }
        });
    }

    public boolean upLoadMaterial(File file) {
        if (this.mViewBinding == null) {
            return false;
        }
        if (this.curId == -1) {
            Log.i("zt", "当前目录id未赋值");
            return false;
        }
        if (this.cloudUtils.isSupportFileFormat(file)) {
            return false;
        }
        this.mViewBinding.btnCancel.setEnabled(false);
        this.mViewBinding.btnUpload.setEnabled(false);
        this.mViewBinding.btnUpload.setBackground(getContext().getDrawable(R.drawable.cloud_btn_2));
        this.mViewBinding.llCreateDir.setVisibility(8);
        showLoading();
        Log.i("zt", "开始上传素材----" + this.curId);
        this.cloudUtils.setOnUpLoadListener(this);
        this.cloudUtils.getTempToken(ExifInterface.GPS_MEASUREMENT_2D, this.curId, file);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        showLoadingView(-1, R.color.white, "");
    }

    @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
    public void upLoadSucceed(BaiduCloudUtils.UploadSuccessBean uploadSuccessBean) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m426x8c8933d4();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$upLoadSucceed$4$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m426x8c8933d4() {
        if (this.listener != null) {
            DialogCloundBinding dialogCloundBinding = this.mViewBinding;
            if (dialogCloundBinding != null) {
                dialogCloundBinding.btnCancel.setEnabled(true);
                this.mViewBinding.btnUpload.setEnabled(true);
                this.mViewBinding.btnUpload.setBackground(getContext().getDrawable(R.drawable.cloud_btn_3));
                closeLoadingView();
            }
            this.listener.onUpLoadSuccess(this.position);
        }
    }

    @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
    public void upLoadFail(String str) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CloudManageDialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m425x8a11229b();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$upLoadFail$5$com-wanos-careditproject-view-dialog-CloudManageDialog, reason: not valid java name */
    /* synthetic */ void m425x8a11229b() {
        if (this.listener != null) {
            updateUpLoadStatus();
            this.listener.onUpLoadFailed();
        }
    }

    private void updateUpLoadStatus() {
        DialogCloundBinding dialogCloundBinding = this.mViewBinding;
        if (dialogCloundBinding != null) {
            dialogCloundBinding.btnCancel.setEnabled(true);
            this.mViewBinding.btnUpload.setEnabled(true);
            this.mViewBinding.btnUpload.setBackground(getContext().getDrawable(R.drawable.cloud_btn_3));
            closeLoadingView();
        }
    }

    public void showLoadingView(int i, int i2, String str) {
        if (i != -1) {
            this.mViewBinding.dialogLoadingView.pbLoading.setIndeterminateTintList(ContextCompat.getColorStateList(getActivity(), i));
        }
        if (i2 != -1) {
            this.mViewBinding.dialogLoadingView.tvLoading.setTextColor(getActivity().getColor(i2));
        }
        if (!str.isEmpty()) {
            this.mViewBinding.dialogLoadingView.tvLoading.setText(str);
        }
        this.mViewBinding.dialogLoadingView.getRoot().setVisibility(0);
        this.mViewBinding.dialogContentView.setVisibility(8);
    }

    public void closeLoadingView() {
        DialogCloundBinding dialogCloundBinding = this.mViewBinding;
        if (dialogCloundBinding != null) {
            dialogCloundBinding.dialogContentView.setVisibility(0);
            this.mViewBinding.dialogLoadingView.getRoot().setVisibility(8);
        }
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private void cancelRequest() {
        Call<CreateCloudResponse> call = this.createReq;
        if (call != null) {
            call.cancel();
            this.createReq = null;
        }
        Call<CloudResponse> call2 = this.getCloudListReq;
        if (call2 != null) {
            call2.cancel();
            this.getCloudListReq = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
        cancelRequest();
        this.cloudUtils.cancelUpload();
        this.cloudUtils.setOnUpLoadListener(null);
    }
}
