package com.wanos.careditproject.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.PlayerPosViewAdapter;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.databinding.ActivityWanosPlayerBinding;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.view.playerUI.Player3dGLRender;
import com.wanos.careditproject.view.playerUI.PlayerPageFragment;
import com.wanos.careditproject.view.playerUI.PlayerPageFragment2;
import com.wanos.careditproject.view.playerUI.PreviewPageFragment;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class WanosPlayerActivity extends WanosBaseActivity implements View.OnClickListener {
    private static final String KEY_FRAGMENT_TAG = "PlayerPageFragment";
    private static final String PROCESS = "com.wanos.web";
    public static int TYPE_PLAY = 1;
    public static int TYPE_PREVIEW = 0;
    public static String assetsDirName = null;
    public static boolean webIsInit = false;
    private AudioManager audioManager;
    private ActivityWanosPlayerBinding binding;
    private AgentWeb mAgentWeb;
    private MediaPlayerService mMediaPlayerHelper;
    private int maxVolume;
    private PlayerUtils.PlayerListener playerListener;
    private PlayerPosViewAdapter playerPosViewAdapter;
    private ProjectInfo projectInfo;
    private String url = "";
    private String id = "";
    private String name = "";
    private String jsonPath = "";
    private float duration = 0.0f;
    private Boolean edit = false;
    private int mediaId = -1;
    private long audioDuration = 0;
    private long curPos = 0;
    private int pageType = 0;
    private boolean ballNameVisible = false;
    private Fragment curFragment = null;

    static {
        EditingUtils.loadEditLibrariesOnce();
        assetsDirName = "imgs/";
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EditingUtils.init(this);
        EditingUtils.initCacheDir();
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("type", 1);
        this.pageType = intExtra;
        if (intExtra == TYPE_PLAY) {
            ProjectInfo projectInfo = (ProjectInfo) intent.getSerializableExtra("projectInfo");
            this.projectInfo = projectInfo;
            if (projectInfo != null) {
                this.url = projectInfo.getWanosPath();
                this.id = this.projectInfo.getId();
                this.jsonPath = this.projectInfo.getHistoryPath();
                this.edit = Boolean.valueOf(intent.getBooleanExtra("EDIT", false));
                this.duration = this.projectInfo.getDuration();
                this.name = this.projectInfo.getTitle();
                Log.i(WanosBaseActivity.TAG, "onCreate: name = " + this.name);
            }
        }
        ActivityWanosPlayerBinding activityWanosPlayerBindingInflate = ActivityWanosPlayerBinding.inflate(getLayoutInflater());
        this.binding = activityWanosPlayerBindingInflate;
        setContentView(activityWanosPlayerBindingInflate.getRoot());
        if (!webIsInit) {
            initPieWebView();
            webIsInit = true;
        }
        copyAssetsImage();
        initView();
        initListenner();
    }

    public void copyAssetsImage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(assetsDirName + "bg.png");
        String str = EditingUtils.getAssetsLocalPath() + BceConfig.BOS_DELIMITER;
        EditingUtils.log("copyAssetsImage dir = " + str);
        String str2 = str + assetsDirName;
        Player3dGLRender.setBasicPath(str2);
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

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EditingUtils.destory();
        super.onDestroy();
    }

    public void initView() {
        setPlayBarVisibility(8);
        setTitleBarVisibility(8);
        initPlayerView();
        updateProjectName();
        initSpinner();
    }

    public void initSpinner() {
        EditingUtils.log("position initSpinner");
        this.playerPosViewAdapter = new PlayerPosViewAdapter(this, R.layout.layout_spinner_player_show_item, EditingUtils.playerShowTypeList);
        this.binding.spinnerShowType.setAdapter((SpinnerAdapter) this.playerPosViewAdapter);
        this.binding.spinnerShowType.setSelection(0, false);
        this.binding.spinnerShowType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wanos.careditproject.view.WanosPlayerActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditingUtils.log("CreatorProjectPublishDialog onItemClick position = " + i);
                WanosPlayerActivity.this.playerPosViewAdapter.setSelectIndex(i);
                WanosPlayerActivity.this.playerPosViewAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initPlayerView() {
        Fragment fragment;
        int i = this.pageType;
        if (i == TYPE_PREVIEW) {
            PreviewPageFragment previewPageFragment = new PreviewPageFragment();
            this.binding.btnEdit.setVisibility(8);
            fragment = previewPageFragment;
        } else if (i == TYPE_PLAY) {
            PlayerPageFragment2 playerPageFragment2 = new PlayerPageFragment2();
            playerPageFragment2.setData(this.url, this.id, this.jsonPath, this.duration);
            playerPageFragment2.setProjectInfo(this.projectInfo);
            playerPageFragment2.setEdit(this.edit);
            playerPageFragment2.setOnCreateAnotherListener(new PlayerPageFragment2.OnCreateAnotherListener() { // from class: com.wanos.careditproject.view.WanosPlayerActivity.2
                @Override // com.wanos.careditproject.view.playerUI.PlayerPageFragment2.OnCreateAnotherListener
                public void onCreateAnotheClick() {
                    WanosPlayerActivity.this.onEditClick();
                }
            });
            fragment = playerPageFragment2;
        } else {
            fragment = null;
        }
        if (fragment != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(KEY_FRAGMENT_TAG);
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            if (fragmentFindFragmentByTag != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            }
            fragmentTransactionBeginTransaction.add(R.id.page_player, fragment, KEY_FRAGMENT_TAG);
            fragmentTransactionBeginTransaction.commit();
            this.curFragment = fragment;
        }
    }

    private void updateProjectName() {
        ProjectInfo curProjectInfo = EditingParams.getInstance().getCurProjectInfo();
        if (curProjectInfo != null) {
            this.binding.topLayout.setName(curProjectInfo.getTitle());
        } else {
            this.binding.topLayout.setName(this.name);
        }
    }

    public void initListenner() {
        this.binding.editingBack.setOnClickListener(this);
        this.binding.btnEdit.setOnClickListener(this);
        this.binding.ivShowName.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.editing_back) {
            EditingUtils.log("Player3dGLRender editing_back");
            finish();
        } else if (id == R.id.btn_edit) {
            onEditClick();
        } else if (id == R.id.iv_show_name) {
            this.ballNameVisible = !this.ballNameVisible;
            updateBallNameState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEditClick() {
        if (!UserInfoUtil.isLogin()) {
            showLoginDialog();
        } else if (this.pageType == TYPE_PLAY) {
            CreatorRetrofitUtil.workCopy(this.id, new ResponseCallBack<EditProjectCopyResponse>(this) { // from class: com.wanos.careditproject.view.WanosPlayerActivity.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                    if (WanosPlayerActivity.this.isFinishing() || WanosPlayerActivity.this.isDestroyed() || editProjectCopyResponse.data == null) {
                        return;
                    }
                    EditingActivity.openEditActivity(WanosPlayerActivity.this, editProjectCopyResponse.data.getProjectType(), editProjectCopyResponse.data.getId(), new Gson().toJson(editProjectCopyResponse.data));
                    WanosPlayerActivity.this.finish();
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    WanosPlayerActivity.this.closeLoadingView();
                    ToastUtil.showMsg(R.string.to_edit_fail);
                }
            });
        }
    }

    private void updateBallNameState() {
        if (this.ballNameVisible) {
            this.binding.ivShowName.setImageResource(R.drawable.edit_name_show);
            Player3dGLRender.setPosNameVisible(true);
            Fragment fragment = this.curFragment;
            if (fragment == null || !(fragment instanceof PlayerPageFragment)) {
                return;
            }
            ((PlayerPageFragment) fragment).drawGL();
            return;
        }
        this.binding.ivShowName.setImageResource(R.drawable.edit_name_hide);
        Player3dGLRender.setPosNameVisible(false);
        Fragment fragment2 = this.curFragment;
        if (fragment2 == null || !(fragment2 instanceof PlayerPageFragment)) {
            return;
        }
        ((PlayerPageFragment) fragment2).drawGL();
    }

    private void initPieWebView() {
        String processName = getProcessName(this);
        if (PROCESS.equals(processName)) {
            return;
        }
        WebView.setDataDirectorySuffix(getString(processName, "sunzn"));
    }

    public String getProcessName(Context context) {
        if (context == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(WanosJsBridge.H5_KEY_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public String getString(String str, String str2) {
        return isEmpty(str) ? str2 : str;
    }

    public boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
