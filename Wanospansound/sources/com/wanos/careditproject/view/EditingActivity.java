package com.wanos.careditproject.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.flyme.auto.sdk.restriction.RestrictionManager;
import com.google.gson.Gson;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.PlayerPosViewAdapter;
import com.wanos.careditproject.databinding.ActivityEditBinding;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.material.SearchActivity;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditProjectCache;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.StorageUtils;
import com.wanos.careditproject.utils.UploadFileUtils;
import com.wanos.careditproject.utils.cmd.CmdListCache;
import com.wanos.careditproject.utils.codec.AudioFileInfo;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.UploadSingleUtils;
import com.wanos.careditproject.view.audiotrackedit.EditingPageFragment;
import com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog;
import com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity;
import com.wanos.careditproject.view.dialog.ExitTipsDialog;
import com.wanos.careditproject.view.opengl.PosLeftGLRender;
import com.wanos.careditproject.view.opengl.PosRightGLRender;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import com.wanos.careditproject.view.playerUI.Player3dGLRender;
import com.wanos.careditproject.view.playerUI.PreviewPageFragment;
import com.wanos.careditproject.view.viewmodel.EditingActivityViewModel;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.editmain.usb.UsbHelper;
import com.wanos.editmain.usb.receiver.USBBroadcastReceiver;
import java.io.File;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class EditingActivity extends BaseAppActivity implements View.OnClickListener, USBBroadcastReceiver.UsbListener {
    public static final String TAG = "wanos:[EditingActivity]";
    public static String assetsDirName = "imgs/";
    ActivityEditBinding bindingEdit;
    private UsbHelper instance;
    private ActivityResultLauncher<Intent> launcher;
    private PlayerPosViewAdapter playerPosViewAdapter;
    private EditingActivityViewModel viewModel;
    private int selectTab = 0;
    private EditingPageFragment editFragment = null;
    private PreviewPageFragment previewPageFragment = null;
    private boolean ballNameVisible = false;
    private boolean isAI = false;
    private final RestrictionManager.OnRestrictionStateChangedListener mRestrictionStateCallback = new RestrictionManager.OnRestrictionStateChangedListener() { // from class: com.wanos.careditproject.view.EditingActivity.1
        @Override // com.flyme.auto.sdk.restriction.RestrictionManager.OnRestrictionStateChangedListener
        public void onRestrictionStateChange(int i) {
            Log.i(EditingActivity.TAG, "onRestrictionStateChange: 当前限制状态 = " + i);
            if (i == 2 || i == 3) {
                ToastUtil.showMsg(R.string.edit_restriction_run_toast);
                EditingActivity.finishEditActivity();
            }
        }
    };

    public static void finishEditActivity() {
        ActivityUtils.finishActivity((Class<? extends Activity>) CreatorProjectPublishActivity.class);
        ActivityUtils.finishActivity((Class<? extends Activity>) MaterialListActivity.class);
        ActivityUtils.finishActivity((Class<? extends Activity>) SearchActivity.class);
        ActivityUtils.finishActivity((Class<? extends Activity>) EditingActivity.class);
    }

    public static void openEditActivity(Context context, int i, String str, String str2) {
        int restrictionState = RestrictionManager.getInstance().getRestrictionState();
        Log.i(TAG, "openEditActivity: 当前限制状态 = " + restrictionState);
        if (restrictionState == 2 || restrictionState == 3) {
            ToastUtil.showMsg(R.string.edit_restriction_toast);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) EditingActivity.class);
        intent.putExtra("projectType", i);
        intent.putExtra("projectId", str);
        intent.putExtra("projectInfo", str2);
        intent.setFlags(537001984);
        context.startActivity(intent);
    }

    public static void openEditActivity(Context context, EditingUtils.EditProjectType editProjectType, String str, String str2, boolean z) {
        int restrictionState = RestrictionManager.getInstance().getRestrictionState();
        Log.i(TAG, "openEditActivity: 当前限制状态 = " + restrictionState);
        if (restrictionState == 2 || restrictionState == 3) {
            ToastUtil.showMsg(R.string.edit_restriction_toast);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) EditingActivity.class);
        intent.putExtra("projectType", editProjectType.value);
        intent.putExtra("projectId", str);
        intent.putExtra("projectInfo", str2);
        intent.putExtra("isAI", z);
        intent.setFlags(537001984);
        context.startActivity(intent);
    }

    @Override // com.wanos.careditproject.view.BaseAppActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("projectId");
        if (stringExtra == null) {
            stringExtra = "0";
        }
        String stringExtra2 = intent.getStringExtra("projectInfo");
        EditingUtils.EditProjectType editProjectType = EditingUtils.EditProjectType.getEditProjectType(intent.getIntExtra("projectType", 1));
        this.isAI = intent.getBooleanExtra("isAI", false);
        if (stringExtra2 != null) {
            EditingParams.getInstance().setCurProjectInfo((ProjectInfo) new Gson().fromJson(stringExtra2, ProjectInfo.class));
        } else {
            EditingParams.getInstance().setCurProjectInfo(null);
        }
        EditingParams.getInstance().setProjectId(stringExtra);
        EditingUtils.setEditProjectType(editProjectType);
        this.viewModel = (EditingActivityViewModel) new ViewModelProvider(this).get(EditingActivityViewModel.class);
        requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 1110);
        ActivityEditBinding activityEditBindingInflate = ActivityEditBinding.inflate(getLayoutInflater());
        this.bindingEdit = activityEditBindingInflate;
        setContentView(activityEditBindingInflate.getRoot());
        int restrictionState = RestrictionManager.getInstance().getRestrictionState();
        Log.i(TAG, "onCreate: 当前限制状态 = " + restrictionState);
        if (restrictionState == 2 || restrictionState == 3) {
            ToastUtil.showMsg(R.string.edit_restriction_toast);
            onBackPressed();
            return;
        }
        initUsbHelper();
        RecordUtils.getInstance().setActivity(this);
        UploadSingleUtils.initContext(this);
        UploadFileUtils.init();
        this.bindingEdit.editingBack.setOnClickListener(this);
        this.bindingEdit.btnShowName.setOnClickListener(this);
        this.bindingEdit.editingPublish.setOnClickListener(this);
        updateProjectName();
        EventBus.getDefault().register(this);
        copyAssetsImage();
        initTab();
        initSpinner();
        updateBallNameState();
        RestrictionManager.getInstance().registerListener(this.mRestrictionStateCallback);
        initListener();
    }

    private void initListener() {
        this.viewModel.curSampleNum.observe(this, new Observer<Long>() { // from class: com.wanos.careditproject.view.EditingActivity.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(Long l) {
                EditingActivity.this.bindingEdit.tvTime0.setText(EditingUtils.formatMilliseconds((l.longValue() * 1000) / ((long) EditingUtils.sampleRateDefault)));
            }
        });
        this.viewModel.maxSampleNum.observe(this, new Observer<Long>() { // from class: com.wanos.careditproject.view.EditingActivity.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Long l) {
                String milliseconds = EditingUtils.formatMilliseconds((l.longValue() * 1000) / ((long) EditingUtils.sampleRateDefault));
                if (milliseconds.equals(EditingActivity.this.bindingEdit.tvTime1.getText())) {
                    return;
                }
                EditingActivity.this.bindingEdit.tvTime1.setText(milliseconds);
            }
        });
    }

    public void retryGetEditData() {
        EditingPageFragment editingPageFragment = this.editFragment;
        if (editingPageFragment != null) {
            editingPageFragment.initData();
        }
    }

    public void initTab() {
        updateTab();
        this.bindingEdit.tab0Bg.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.EditingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditingActivity.this.selectTab = 0;
                EditingActivity.this.updateTab();
                EditingActivity editingActivity = EditingActivity.this;
                editingActivity.selectTabIndex(editingActivity.selectTab);
            }
        });
        this.bindingEdit.tab1Bg.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.EditingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditingActivity.this.editFragment == null || !EditingActivity.this.editFragment.isShowDialog()) {
                    EditingActivity.this.selectTab = 1;
                    EditingActivity.this.updateTab();
                    EditingActivity editingActivity = EditingActivity.this;
                    editingActivity.selectTabIndex(editingActivity.selectTab);
                }
            }
        });
        selectTabIndex(this.selectTab);
    }

    public void updateTab() {
        if (this.selectTab == 1) {
            this.bindingEdit.tab0Bg.setImageResource(R.drawable.edit_top_tab_normal_bg);
            this.bindingEdit.tab1Bg.setImageResource(R.drawable.edit_top_tab_select_bg);
            this.bindingEdit.tab0.setImageResource(R.drawable.edit_top_tab_00);
            this.bindingEdit.tab1.setImageResource(R.drawable.edit_top_tab_11);
            this.bindingEdit.editingBack.setVisibility(8);
            this.bindingEdit.layoutTime.setVisibility(8);
            this.bindingEdit.spinnerShowType.setVisibility(0);
            this.bindingEdit.btnShowName.setVisibility(0);
            return;
        }
        this.bindingEdit.tab0Bg.setImageResource(R.drawable.edit_top_tab_select_bg);
        this.bindingEdit.tab1Bg.setImageResource(R.drawable.edit_top_tab_normal_bg);
        this.bindingEdit.tab0.setImageResource(R.drawable.edit_top_tab_01);
        this.bindingEdit.tab1.setImageResource(R.drawable.edit_top_tab_10);
        this.bindingEdit.editingBack.setVisibility(0);
        this.bindingEdit.layoutTime.setVisibility(0);
        this.bindingEdit.spinnerShowType.setVisibility(8);
        this.bindingEdit.btnShowName.setVisibility(8);
    }

    private void updateBallNameState() {
        if (this.ballNameVisible) {
            this.bindingEdit.ivShowName.setImageResource(R.drawable.edit_name_hide);
            this.bindingEdit.tvShowName.setText("隐藏名称");
            Player3dGLRender.setPosNameVisible(true);
            PreviewPageFragment previewPageFragment = this.previewPageFragment;
            if (previewPageFragment != null) {
                previewPageFragment.drawGL();
                return;
            }
            return;
        }
        this.bindingEdit.ivShowName.setImageResource(R.drawable.edit_name_show);
        this.bindingEdit.tvShowName.setText("显示名称");
        Player3dGLRender.setPosNameVisible(false);
        PreviewPageFragment previewPageFragment2 = this.previewPageFragment;
        if (previewPageFragment2 != null) {
            previewPageFragment2.drawGL();
        }
    }

    public void initSpinner() {
        EditingUtils.log("position initSpinner");
        this.playerPosViewAdapter = new PlayerPosViewAdapter(this, R.layout.layout_spinner_player_show_item, EditingUtils.playerShowTypeList);
        this.bindingEdit.spinnerShowType.setAdapter((SpinnerAdapter) this.playerPosViewAdapter);
        this.bindingEdit.spinnerShowType.setSelection(0, false);
        this.bindingEdit.spinnerShowType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wanos.careditproject.view.EditingActivity.6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (EditingActivity.this.previewPageFragment != null) {
                    EditingUtils.log("CreatorProjectPublishDialog onItemClick position = " + i);
                    EditingActivity.this.playerPosViewAdapter.setSelectIndex(i);
                    EditingActivity.this.playerPosViewAdapter.notifyDataSetChanged();
                    EditingActivity.this.previewPageFragment.setShowType(i);
                }
            }
        });
    }

    public void copyAssetsImage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(assetsDirName + "bg.png");
        arrayList.add(assetsDirName + "flash.png");
        arrayList.add(assetsDirName + "headerTop.png");
        arrayList.add(assetsDirName + "headerBack.png");
        arrayList.add(assetsDirName + "leftObj.png");
        arrayList.add(assetsDirName + "rightObj.png");
        arrayList.add(assetsDirName + "midObj1.png");
        arrayList.add(assetsDirName + "whiteBg.png");
        arrayList.add(assetsDirName + "textLeft.png");
        arrayList.add(assetsDirName + "textRight.png");
        String str = EditingUtils.getAssetsLocalPath() + BceConfig.BOS_DELIMITER;
        EditingUtils.log("copyAssetsImage dir = " + str);
        String str2 = str + assetsDirName;
        PosLeftGLRender.setBasicPath(str2);
        PosRightGLRender.setBasicPath(str2);
        WaveUIGLRender.setBasicPath(str2);
        Player3dGLRender.setBasicPath(str2);
        WaveUIGLRender.setBeatSwitch(StorageUtils.getInstance().getBeatSwitch());
        WaveUIGLRender.setBeatSpeed(StorageUtils.getInstance().getBeatSpeed());
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            String str3 = (String) arrayList.get(i);
            if (!new File(str + str3).exists()) {
                ResourceUtils.copyFileFromAssets(str3, str + str3);
            }
        }
    }

    public void initData() {
        EditingParams.getInstance().init();
        EditingUtils.init(this);
        StorageUtils.getInstance().init(this);
    }

    private void initUsbHelper() {
        UsbHelper usbHelper = UsbHelper.getInstance();
        this.instance = usbHelper;
        usbHelper.init(this, this);
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wanos.careditproject.view.EditingActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.m419x971856ad((ActivityResult) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initUsbHelper$0$com-wanos-careditproject-view-EditingActivity, reason: not valid java name */
    /* synthetic */ void m419x971856ad(ActivityResult activityResult) {
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

    private void updateProjectName() {
        EditingParams.getInstance().getCurProjectInfo();
    }

    public void updateTime(String str, String str2) {
        this.bindingEdit.tvTime0.setText(str);
        this.bindingEdit.tvTime1.setText(str2);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.wanos.careditproject.view.BaseAppActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EditingUtils.log("EditingActivity onDestroy");
        UploadSingleUtils.destroyContext();
        UploadFileUtils.destory();
        UsbHelper usbHelper = this.instance;
        if (usbHelper != null) {
            usbHelper.finishUsbHelper();
        }
        deletePcmFile();
        AudioPcmData.getInstance().clearAllData();
        AudioFileInfo.getInstance().clear();
        RecordUtils.getInstance().setActivity(null);
        EditProjectCache.clear();
        CmdListCache.clear();
        EditingParams.getInstance().setCurProjectInfo(null);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void deletePcmFile() {
        File[] fileArrListFiles;
        File file = new File(EditingUtils.getPcmLocalPath());
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
    }

    private void initFragment() {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        this.editFragment = new EditingPageFragment(this.isAI);
        fragmentTransactionBeginTransaction.add(R.id.page_container_view, this.editFragment);
        fragmentTransactionBeginTransaction.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectTabIndex(int i) {
        EditingUtils.log("selectTabIndex 0");
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (i == 0) {
            PreviewPageFragment previewPageFragment = this.previewPageFragment;
            if (previewPageFragment != null) {
                fragmentTransactionBeginTransaction.hide(previewPageFragment);
            }
            EditingPageFragment editingPageFragment = this.editFragment;
            if (editingPageFragment == null) {
                this.editFragment = new EditingPageFragment(this.isAI);
                fragmentTransactionBeginTransaction.add(R.id.page_container_view, this.editFragment);
            } else {
                fragmentTransactionBeginTransaction.show(editingPageFragment);
            }
        } else {
            EditingPageFragment editingPageFragment2 = this.editFragment;
            if (editingPageFragment2 != null) {
                fragmentTransactionBeginTransaction.hide(editingPageFragment2);
            }
            PreviewPageFragment previewPageFragment2 = this.previewPageFragment;
            if (previewPageFragment2 == null) {
                this.previewPageFragment = new PreviewPageFragment();
                fragmentTransactionBeginTransaction.add(R.id.page_container_view, this.previewPageFragment);
            } else {
                fragmentTransactionBeginTransaction.show(previewPageFragment2);
            }
        }
        fragmentTransactionBeginTransaction.commit();
    }

    private boolean isNoPreviewPlay() {
        if (DataHelpAudioTrack.getTrackList().isEmpty()) {
            return true;
        }
        for (int i = 0; i < DataHelpAudioTrack.getTrackList().size(); i++) {
            if (!DataHelpAudioTrack.getClipListByTrackIndex(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditingPageFragment editingPageFragment;
        int id = view.getId();
        if (id == R.id.btn_show_name) {
            this.ballNameVisible = !this.ballNameVisible;
            updateBallNameState();
            return;
        }
        if (id == R.id.editing_back) {
            EditingPageFragment editingPageFragment2 = this.editFragment;
            if (editingPageFragment2 == null || !editingPageFragment2.isShowDialog()) {
                if (UploadFileUtils.isUploading()) {
                    ExitTipsDialog.show(getSupportFragmentManager()).setOnDialogClickListener(new ExitTipsDialog.OnDialogClickListener() { // from class: com.wanos.careditproject.view.EditingActivity.7
                        @Override // com.wanos.careditproject.view.dialog.ExitTipsDialog.OnDialogClickListener
                        public void onCancelClick() {
                        }

                        @Override // com.wanos.careditproject.view.dialog.ExitTipsDialog.OnDialogClickListener
                        public void onRightClick() {
                            UploadFileUtils.stop();
                            UploadFileUtils.removeDataOfLocalPath();
                            DataHelpAudioTrack.setToSaveServer(true);
                            EditingActivity.this.finish();
                        }
                    });
                    return;
                } else {
                    finish();
                    return;
                }
            }
            return;
        }
        if (id == R.id.editing_publish) {
            EditingPageFragment editingPageFragment3 = this.editFragment;
            if (editingPageFragment3 == null || !editingPageFragment3.isShowDialog()) {
                if (DataHelpAudioTrack.isEmpty()) {
                    ToastUtil.showMsg("空项目不能发布！");
                    return;
                }
                if (!PlayerUtils.isStop() && (editingPageFragment = this.editFragment) != null) {
                    editingPageFragment.playerStop();
                }
                EditingPageFragment editingPageFragment4 = this.editFragment;
                if (editingPageFragment4 != null) {
                    editingPageFragment4.hideDialog();
                }
                ProjectInfo curProjectInfo = EditingParams.getInstance().getCurProjectInfo();
                if (curProjectInfo != null) {
                    CreatorProjectPublishActivity.open(this, curProjectInfo.getId(), curProjectInfo.getTitle(), curProjectInfo.getContent(), curProjectInfo.getPicture(), EditingUtils.editProjectType);
                } else {
                    ToastUtil.showMsg("请稍后再试！");
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void createOrUpdateProjectInfo(ProjectChangeEvent projectChangeEvent) {
        if (projectChangeEvent != null) {
            ProjectInfo projectInfo = projectChangeEvent.getProjectInfo();
            projectChangeEvent.getOprateType();
            if (projectChangeEvent.getType() == 0) {
                EditingParams.getInstance().setCurProjectInfo(projectInfo);
                updateProjectName();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginStateChange(LoginOrLogoutEvent loginOrLogoutEvent) {
        if (loginOrLogoutEvent == null || loginOrLogoutEvent.isLogin()) {
            return;
        }
        finish();
    }

    protected void showPopEditWin() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("编辑信息");
        arrayList.add("发布到社区");
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.pop_audio_track_edit, (ViewGroup) null);
        int[] iArr = new int[2];
        this.bindingEdit.editingPublish.getLocationOnScreen(iArr);
        final PopupWindow popupWindow = new PopupWindow(viewInflate);
        popupWindow.setWidth(EditingUtils.dp2px(this, 308.0f));
        popupWindow.setHeight(-2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(this.bindingEdit.editingPublish, 0, (iArr[0] + this.bindingEdit.editingPublish.getWidth()) - EditingUtils.dp2px(this, 280.0f), iArr[1] + EditingUtils.dp2px(this, 100.0f));
        ListView listView = (ListView) viewInflate.findViewById(R.id.list_view);
        listView.setAdapter((ListAdapter) new ArrayAdapter(this, R.layout.pop_item_text, R.id.tv_item, arrayList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wanos.careditproject.view.EditingActivity.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    ProjectInfo curProjectInfo = EditingParams.getInstance().getCurProjectInfo();
                    if (curProjectInfo != null) {
                        new CreatorProjectModifyDialog(EditingActivity.this, true, false, curProjectInfo.getId(), curProjectInfo.getTitle(), curProjectInfo.getContent(), curProjectInfo.getPicture()).show();
                    } else {
                        ToastUtil.showMsg("请稍后再试！");
                    }
                } else if (i == 1) {
                    ProjectInfo curProjectInfo2 = EditingParams.getInstance().getCurProjectInfo();
                    if (curProjectInfo2 != null) {
                        CreatorProjectPublishActivity.open(EditingActivity.this, curProjectInfo2.getId(), curProjectInfo2.getTitle(), curProjectInfo2.getContent(), curProjectInfo2.getPicture(), EditingUtils.editProjectType);
                    } else {
                        ToastUtil.showMsg("请稍后再试！");
                    }
                }
                popupWindow.dismiss();
            }
        });
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

    @Override // com.wanos.careditproject.view.BaseAppActivity, com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        super.showLoadingView(true);
    }

    @Override // com.wanos.editmain.usb.receiver.USBBroadcastReceiver.UsbListener
    public void insertUsb(UsbDevice usbDevice) {
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
}
