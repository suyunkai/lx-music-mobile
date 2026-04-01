package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Builder;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.app.hubert.guide.model.HighlightOptions;
import com.app.hubert.guide.util.ScreenUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialDataInfo;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.AudioTrackLeftAdapter;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectGuideResponse;
import com.wanos.careditproject.data.response.EditProjectInitResponse;
import com.wanos.careditproject.databinding.FragmentEditingPageBinding;
import com.wanos.careditproject.databinding.LayoutAudioTrackAddBinding;
import com.wanos.careditproject.event.UpdateEditUIEvent;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.model.server.RootModel;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.DebounceClick;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.SaveProjectRunnable;
import com.wanos.careditproject.utils.StorageUtils;
import com.wanos.careditproject.utils.cmd.CmdSetClipDB;
import com.wanos.careditproject.utils.cmd.CmdSetClipFade;
import com.wanos.careditproject.utils.cmd.CmdSetTotalDB;
import com.wanos.careditproject.utils.codec.AudioCodec;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.careditproject.utils.metronome.metronome;
import com.wanos.careditproject.utils.rcDrag.RcItemTouchHelperCallback;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.careditproject.view.WanosRelativeGuide;
import com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView;
import com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView;
import com.wanos.careditproject.view.audiotrackedit.viewModel.EditPageFragmentViewModel;
import com.wanos.careditproject.view.dialog.CreatorNewTrackDialog;
import com.wanos.careditproject.view.dialog.CreatorObjPosDialog;
import com.wanos.careditproject.view.dialog.CreatorRecordDialog;
import com.wanos.careditproject.view.dialog.CreatorSettingDialog;
import com.wanos.careditproject.view.dialog.CreatorSettingFragment;
import com.wanos.careditproject.view.dialog.CreatorSpeedDialog;
import com.wanos.careditproject.view.dialog.EditLoadingDialog;
import com.wanos.careditproject.view.dialog.EditLoadingValue;
import com.wanos.careditproject.view.dialog.HelpPosDrawDialog;
import com.wanos.careditproject.view.dialog.MissResDialog;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIHelper;
import com.wanos.careditproject.view.viewmodel.EditingActivityViewModel;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.base.BaseFragment;
import com.wanos.commonlibrary.event.EditProjectEvent;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.util.NativeMethod;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class EditingPageFragment extends BaseFragment implements View.OnClickListener, OnRangeChangedListener, SeekBar.OnSeekBarChangeListener {
    public static final int MSGHIDEDIALOG = 11;
    public static final int MSGTOAST = 13;
    public static final int MSGUPDATEUI = 12;
    private static final int PICK_FILE_REQUEST_CODE = 101;
    public static final String TAG = "wanos:[EditingPageFragment]";
    private AudioTrackLeftAdapter audioTrackLeftAdapter;
    FragmentEditingPageBinding binding;
    private CreatorObjPosDialog creatorObjPosDialog;
    private CreatorSettingDialog creatorSettingDialog;
    private CreatorSettingFragment creatorSettingFragment;
    private int currentIndex;
    private List<String> downloadResList;
    private EditLoadingDialog editLoadingDialog;
    private EditingActivityViewModel editingActivityViewModel;
    private ActivityResultLauncher<String> filePickerLauncher;
    private boolean isAI;
    private LayoutAudioTrackAddBinding layoutAudioTrackAddBinding;
    private LinearLayoutManager leftLayoutMananger;
    List<AudioTrackLeftItem> leftTrackViewList;
    private PlayerUtils.PlayerListener playerListener;
    private View root;
    private SaveProjectRunnable saveProjectRunnable;
    private List<TrackItemModel> trackDataList;
    private EditPageFragmentViewModel viewModel;
    private int trackCount = 0;
    private boolean isDestroy = false;
    boolean isAdded = false;
    private List<String> downloadFailResList = new ArrayList();
    private boolean isRetry = false;
    private int leftRCY = 0;
    private int wavScrollY = 0;
    private boolean isResetting = false;
    private boolean isTouchLeft = true;

    private void initMidiPlayer() {
    }

    private boolean isMidiTrack() {
        return false;
    }

    @Override // com.wanos.commonlibrary.base.BaseFragment
    public void notifyMediaPlayBarVisiable(boolean z) {
    }

    public EditingPageFragment(boolean z) {
        this.isAI = z;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackCount = 0;
        this.leftTrackViewList = new ArrayList();
        this.editingActivityViewModel = (EditingActivityViewModel) new ViewModelProvider(requireActivity()).get(EditingActivityViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentEditingPageBinding.inflate(getLayoutInflater());
        this.layoutAudioTrackAddBinding = LayoutAudioTrackAddBinding.inflate(layoutInflater, viewGroup, false);
        this.viewModel = (EditPageFragmentViewModel) new ViewModelProvider(this).get(EditPageFragmentViewModel.class);
        init();
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        EditingUtils.log("EditingPageFragment onHiddenChanged hidden=" + z);
        if (z) {
            this.binding.btnPlay.setImageResource(R.drawable.editing_play);
            PlayerUtils.stop(true);
            hideDialog();
            stopSaveProjectThread();
            return;
        }
        EditingParams.getInstance().setProgressSampleNum(EditingParams.getInstance().getProgressSampleNum());
        this.binding.viewAudioTrack.drawAll();
        startSaveProjectThread();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        EditingUtils.log("EditingPageFragment onPause");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        EditingUtils.log("EditingPageFragment onResume");
        updateUI();
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGuide() {
        HighlightOptions highlightOptions;
        GuidePage guidePageAddHighLightWithOptions;
        GuidePage guidePageAddHighLightWithOptions2;
        GuidePage guidePageAddHighLightWithOptions3;
        ViewGroup viewGroup;
        View viewFindViewById;
        EditingActivity editingActivity = (EditingActivity) getActivity();
        GuidePage guidePageAddHighLightWithOptions4 = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(this.binding.llRes, HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 30.0d), Color.parseColor("#1368FB"), 4, new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide1, 48)).setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        }).build());
        RecyclerView recyclerView = (RecyclerView) editingActivity.findViewById(R.id.view_track_left);
        int i = 80;
        if (recyclerView.getChildCount() <= 0 || ((viewFindViewById = (viewGroup = (ViewGroup) recyclerView.getChildAt(0)).findViewById(R.id.view_add)) != null && viewFindViewById.isShown())) {
            highlightOptions = null;
            guidePageAddHighLightWithOptions = null;
            guidePageAddHighLightWithOptions2 = null;
            guidePageAddHighLightWithOptions3 = null;
        } else {
            viewGroup.getLocationOnScreen(new int[2]);
            HighlightOptions highlightOptionsBuild = new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide2, i) { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.2
            }).build();
            int statusBarHeight = ScreenUtils.getStatusBarHeight(getContext());
            guidePageAddHighLightWithOptions2 = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(new RectF(com.blankj.utilcode.util.ScreenUtils.getScreenWidth() * 0.5f, (r5[1] - statusBarHeight) - UIUtil.dip2px(getContext(), 32.0d), (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() * 0.5f) + UIUtil.dip2px(getContext(), 450.0d), (r5[1] - statusBarHeight) + UIUtil.dip2px(getContext(), 32.0d) + viewGroup.getMeasuredHeight()), HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 40.0d), Color.parseColor("#1368FB"), 4, highlightOptionsBuild);
            guidePageAddHighLightWithOptions3 = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(viewGroup, HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 35.0d), Color.parseColor("#1368FB"), 4, new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide3, 80)).build());
            guidePageAddHighLightWithOptions = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(viewGroup.findViewById(R.id.ll_show_pos), HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 16.0d), Color.parseColor("#1368FB"), 4, new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide4, 5)).build());
            highlightOptions = highlightOptionsBuild;
        }
        GuidePage guidePage = guidePageAddHighLightWithOptions3;
        GuidePage guidePageAddHighLightWithOptions5 = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(editingActivity.findViewById(R.id.cl_top_layout), HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 33.0d), Color.parseColor("#1368FB"), 4, new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide5, 80)).build());
        GuidePage guidePageAddHighLightWithOptions6 = GuidePage.newInstance().setEverywhereCancelable(false).addHighLightWithOptions(editingActivity.findViewById(R.id.editing_publish), HighLight.Shape.ROUND_RECTANGLE, UIUtil.dip2px(getContext(), 20.0d), UIUtil.dip2px(getContext(), 33.0d), Color.parseColor("#1368FB"), 4, new HighlightOptions.Builder().setRelativeGuide(new WanosRelativeGuide(R.layout.layout_guide6, 3)).build());
        Builder builderAddGuidePage = NewbieGuide.with(this).setLabel("relative").alwaysShow(true).addGuidePage(guidePageAddHighLightWithOptions4);
        if (highlightOptions != null) {
            builderAddGuidePage.addGuidePage(guidePageAddHighLightWithOptions2);
        }
        if (guidePage != null) {
            builderAddGuidePage.addGuidePage(guidePage);
        }
        if (guidePageAddHighLightWithOptions != null) {
            builderAddGuidePage.addGuidePage(guidePageAddHighLightWithOptions);
        }
        builderAddGuidePage.addGuidePage(guidePageAddHighLightWithOptions5).addGuidePage(guidePageAddHighLightWithOptions6).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EditLoadingDialog editLoadingDialog = this.editLoadingDialog;
        if (editLoadingDialog != null) {
            editLoadingDialog.stopProgress();
            this.editLoadingDialog.hide();
            this.editLoadingDialog = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.isDestroy = true;
        DownloadUtils.getInstance().stop();
        NativeMethod.getInstance().ffmpegDecodeAudioStop();
        AudioCodec.destroy();
        EditingUtils.log("EditingPageFragment onDestroy");
        PlayerUtils.stop(false);
        stopSaveProjectThread();
        WaveUIHelper.destory();
        EventBus.getDefault().unregister(this);
        if (RecordUtils.getInstance().isRecording()) {
            RecordUtils.getInstance().stopRecord();
        }
        DataHelpAudioTrack.setUndoRedoStateCallback(null);
        AudioPcmData.getInstance().setAudioPcmListener(null);
        EditingParams.getInstance().setMetronome(null);
        WaveUIGLRender.destroyRender();
        super.onDestroy();
    }

    private void init() {
        initThirdParty();
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        this.viewModel.loadingState.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m420x6238bfa7((EditPageFragmentViewModel.EditLoadingState) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$0$com-wanos-careditproject-view-audiotrackedit-EditingPageFragment, reason: not valid java name */
    /* synthetic */ void m420x6238bfa7(EditPageFragmentViewModel.EditLoadingState editLoadingState) {
        EditingUtils.log("initListener state:" + editLoadingState);
        if (editLoadingState == EditPageFragmentViewModel.EditLoadingState.LOADING) {
            showLoading();
        } else if (editLoadingState == EditPageFragmentViewModel.EditLoadingState.LOADED) {
            closeLoading();
        } else if (editLoadingState == EditPageFragmentViewModel.EditLoadingState.ERROR) {
            showLoadingFail();
        }
    }

    public void initThirdParty() {
        NativeMethod.getInstance().ffmpegDecodeAudioStart();
        AudioCodec.init();
        EditingParams.getInstance().setMetronome(new metronome(getContext()));
        EditingParams.getInstance().setPosDrawClick(false);
        EventBus.getDefault().register(this);
        WaveUIHelper.init(this.binding.viewAudioTrack);
        EditingUtils.initCacheDir();
        DownloadUtils.getInstance().init(new DownloadUtils.DownloadCall() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.3
            @Override // com.wanos.careditproject.utils.http.DownloadUtils.DownloadCall
            public void onCall(String str, String str2, boolean z) {
                if (z) {
                    AudioCodec.add(str2, str);
                } else {
                    AudioCodec.add(str, str);
                }
            }
        });
        this.playerListener = new PlayerUtils.PlayerListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.4
            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void setWebBallInfo(List<WebBallInfoModel> list, boolean z) {
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onProgress(int i) {
                if (EditingPageFragment.this.isAlive()) {
                    EditingPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EditingPageFragment.this.getActivity() == null || EditingPageFragment.this.binding == null || !AppUtils.isAppForeground()) {
                                return;
                            }
                            EditingPageFragment.this.binding.viewAudioTrack.drawAll();
                            EditingPageFragment.this.updateTime();
                        }
                    });
                }
            }

            @Override // com.wanos.careditproject.utils.PlayerUtils.PlayerListener
            public void onStop() {
                if (!PlayerUtils.isStop()) {
                    PlayerUtils.stop(false);
                }
                if (EditingPageFragment.this.isAlive()) {
                    EditingPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EditingPageFragment.this.isAlive()) {
                                EditingPageFragment.this.binding.btnPlay.setImageResource(R.drawable.editing_play);
                            }
                        }
                    });
                }
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void editEvent(EditProjectEvent editProjectEvent) {
        if (editProjectEvent.type == EditProjectEvent.ProjectEventType.HideDialog) {
            hideDialog();
            return;
        }
        if (editProjectEvent.type == EditProjectEvent.ProjectEventType.UpdateUI) {
            updateUI();
            return;
        }
        if (editProjectEvent.type == EditProjectEvent.ProjectEventType.ShowTips) {
            showTips((String) editProjectEvent.param);
        } else if (editProjectEvent.type == EditProjectEvent.ProjectEventType.ShowTipsAndUpdateUI) {
            showTips((String) editProjectEvent.param);
            updateUI();
        }
    }

    public void startSaveProjectThread() {
        this.saveProjectRunnable = new SaveProjectRunnable();
        new Thread(this.saveProjectRunnable).start();
    }

    public void stopSaveProjectThread() {
        SaveProjectRunnable saveProjectRunnable = this.saveProjectRunnable;
        if (saveProjectRunnable != null) {
            saveProjectRunnable.stop();
            this.saveProjectRunnable = null;
        }
    }

    public void initData() {
        EditingParams.getInstance().setProjectDataIsInit(false);
        DataHelpAudioTrack.setRootModel(null);
        showLoading();
        if (EditingUtils.isConnectServer()) {
            CreatorRetrofitUtil.editInit(EditingParams.getInstance().getProjectId(), new ResponseCallBack<EditProjectInitResponse>(getActivity()) { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(EditProjectInitResponse editProjectInitResponse) {
                    if (EditingPageFragment.this.isAlive()) {
                        if (editProjectInitResponse.data == null || editProjectInitResponse.data.detail == null || editProjectInitResponse.data.project == null || editProjectInitResponse.data.project.equals("") || editProjectInitResponse.data.project.equals("null")) {
                            EditingPageFragment.this.viewModel.setLoadingState(EditPageFragmentViewModel.EditLoadingState.ERROR);
                            return;
                        }
                        ProjectInfo projectInfo = editProjectInitResponse.data.detail;
                        DataHelpAudioTrack.initBallRoutes(editProjectInitResponse.data.ballRoute);
                        EditingParams.getInstance().setProjectId(projectInfo.getId());
                        EditingParams.getInstance().setCurProjectInfo(projectInfo);
                        RootModel rootModel = (RootModel) new Gson().fromJson(editProjectInitResponse.data.project, RootModel.class);
                        DataHelpAudioTrack.setRootModel(rootModel, EditingPageFragment.this.isAI);
                        EditingUtils.log(rootModel.getChannelNum());
                        EditingUtils.log("WanOSRetrofitUtil.editInit onResponseSuccessful---2 id = " + projectInfo.getId());
                        EditingPageFragment.this.initProjectView();
                        if (!SharedPreferUtils.isFirstGuide() || DataHelpAudioTrack.getTrackSize() != 0) {
                            EditingPageFragment.this.downloadRes();
                        } else {
                            EditingPageFragment.this.requestGuideData();
                        }
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str) {
                    if (EditingPageFragment.this.isAlive()) {
                        EditingPageFragment.this.viewModel.setLoadingState(EditPageFragmentViewModel.EditLoadingState.ERROR);
                    }
                }
            });
        } else {
            new Thread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    EditingPageFragment.this.readLocalJson(EditingParams.getInstance().getProjectId());
                }
            }).start();
        }
    }

    public void requestGuideData() {
        CreatorRetrofitUtil.editGuide(new ResponseCallBack<EditProjectGuideResponse>(getContext()) { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.7
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectGuideResponse editProjectGuideResponse) {
                if (editProjectGuideResponse == null || editProjectGuideResponse.data == null || editProjectGuideResponse.data.AssetList == null || editProjectGuideResponse.data.AssetList.size() <= 0) {
                    EditingPageFragment.this.downloadRes();
                    return;
                }
                MaterialDataInfo materialDataInfo = editProjectGuideResponse.data.AssetList.get(0);
                String strRemoveExtension = EditingUtils.removeExtension(materialDataInfo.getName());
                DataHelpAudioTrack.newTrack(false);
                DataHelpAudioTrack.addTrackWithUrl(materialDataInfo.getUrlSrcWanos(), materialDataInfo.getFormat(), materialDataInfo.getSampleRate(), materialDataInfo.getChannel(), materialDataInfo.getSampleNum(), 0, 0L, strRemoveExtension);
                EditingPageFragment.this.downloadRes();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                EditingPageFragment.this.downloadRes();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readLocalJson(String str) {
        InputStream inputStreamOpen;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        EditingUtils.log("lei readLocalJson pId=" + str);
        if (!str.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            StringBuilder sb = new StringBuilder();
            try {
                inputStreamOpen = getResources().getAssets().open("0car2.json");
                inputStreamReader = new InputStreamReader(inputStreamOpen);
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    sb.append(line);
                }
                RootModel rootModel = (RootModel) new Gson().fromJson(sb.toString(), RootModel.class);
                DataHelpAudioTrack.setRootModel(rootModel);
                EditingUtils.log(rootModel.getChannelNum());
                downloadRes();
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStreamOpen.close();
            RootModel rootModel2 = (RootModel) new Gson().fromJson(sb.toString(), RootModel.class);
            DataHelpAudioTrack.setRootModel(rootModel2);
            EditingUtils.log(rootModel2.getChannelNum());
            downloadRes();
        } else {
            String file = EditingUtils.readFile("wanosproject.json");
            if (!file.equals("")) {
                RootModel rootModel3 = (RootModel) new Gson().fromJson(file, RootModel.class);
                DataHelpAudioTrack.setRootModel(rootModel3);
                EditingUtils.log(rootModel3.getChannelNum());
                downloadRes();
            } else {
                readLocalJson("0");
                return;
            }
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.8
            @Override // java.lang.Runnable
            public void run() {
                EditingPageFragment.this.initProjectView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadRes() {
        this.downloadFailResList.clear();
        this.isRetry = false;
        this.downloadResList = DataHelpAudioTrack.getAllResList();
        EditingUtils.log("downloadRes--downloadResList.size()--=" + this.downloadResList.size());
        if (this.downloadResList.isEmpty()) {
            showProjectView();
            return;
        }
        EditLoadingValue.getInstance().stopLoadJson(this.downloadResList);
        EditLoadingValue.getInstance().setResFileCount(this.downloadResList.size());
        AudioCodec.setCodecListener(new AudioCodec.CodecListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.9
            @Override // com.wanos.careditproject.utils.codec.AudioCodec.CodecListener
            public void onFinish() {
                EditingUtils.log("downloadRes--lei res 2 isFinish=" + AudioCodec.isFinish() + "--downloadResList---size---" + EditingPageFragment.this.downloadResList.size() + "--ThreadName---" + Thread.currentThread().getName());
                if (!EditingPageFragment.this.downloadResList.isEmpty() || !AudioCodec.isFinish() || EditingPageFragment.this.isDetached() || EditingPageFragment.this.isRemoving()) {
                    return;
                }
                EditingUtils.log("downloadRes--lei--showProjectView+");
                EditingPageFragment.this.showProjectView();
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.downloadResList);
        for (int i = 0; i < arrayList.size(); i++) {
            downloadUrl((String) arrayList.get(i));
        }
    }

    private void downloadUrl(String str) {
        DownloadUtils.getInstance().download(str, new DownloadUtils.DownloadCall() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.10
            @Override // com.wanos.careditproject.utils.http.DownloadUtils.DownloadCall
            public void onCall(String str2, String str3, boolean z) {
                if (EditingPageFragment.this.isAlive()) {
                    EditingPageFragment.this.downloadResList.remove(str2);
                    EditLoadingValue.getInstance().setFileProgressLoaded(str2);
                    if (!z) {
                        EditingPageFragment.this.downloadFailResList.add(str2);
                    } else {
                        if (PlayerUtils.testFilePath.equals("")) {
                            PlayerUtils.testFilePath = str3;
                        }
                        AudioCodec.add(str3, str2);
                    }
                    EditingUtils.log("downloadRes--lei res 3 isFinish=" + AudioCodec.isFinish() + "--downloadResList---size---" + EditingPageFragment.this.downloadResList.size() + "--ThreadName---" + Thread.currentThread().getName());
                    if (EditingPageFragment.this.downloadResList.isEmpty() && AudioCodec.isFinish()) {
                        EditingPageFragment.this.showProjectView();
                    }
                }
            }
        });
    }

    private boolean checkResIsSuccess() {
        if (this.isRetry) {
            this.isRetry = false;
            return true;
        }
        if (this.downloadFailResList.size() <= 0) {
            return true;
        }
        this.isRetry = true;
        Iterator<String> it = this.downloadFailResList.iterator();
        while (it.hasNext()) {
            this.downloadResList.add(it.next());
        }
        this.downloadFailResList.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.downloadResList);
        for (int i = 0; i < arrayList.size(); i++) {
            downloadUrl((String) arrayList.get(i));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProjectView() {
        if (isAlive() && checkResIsSuccess()) {
            DownloadUtils.getInstance().clearMoreFile();
            AudioCodec.setCodecListener(null);
            EditingUtils.log("lei res showProjectView");
            EditingParams.getInstance().setProjectDataIsInit(true);
            WaveUIGLRender.sLoadEnd();
            DataHelpAudioTrack.resetMaxSampleNum();
            if (this.binding.viewAudioTrack != null) {
                this.binding.viewAudioTrack.drawAll();
            }
            this.viewModel.setLoadingState(EditPageFragmentViewModel.EditLoadingState.LOADED);
            if (isAlive()) {
                getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.11
                    @Override // java.lang.Runnable
                    public void run() {
                        EditingPageFragment.this.updateTrackLeftView();
                        EditingPageFragment.this.initViewAudioTrack();
                        EditingPageFragment.this.updateTime();
                        if (!SharedPreferUtils.isFirstGuide()) {
                            if (EditingPageFragment.this.downloadFailResList.size() > 0) {
                                new Handler().postDelayed(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.11.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        EditingUtils.log("downloadRes--lei res 5 downloadFailResList=" + EditingPageFragment.this.downloadFailResList.toString());
                                        new MissResDialog().show(EditingPageFragment.this.getChildFragmentManager(), "MissResDialog");
                                    }
                                }, 200L);
                                return;
                            }
                            return;
                        }
                        new Handler().postDelayed(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                EditingPageFragment.this.showGuide();
                                SharedPreferUtils.setFirstGuide(false);
                            }
                        }, 500L);
                    }
                });
            }
            startSaveProjectThread();
        }
    }

    private void showLoading() {
        if (this.editLoadingDialog == null) {
            this.editLoadingDialog = new EditLoadingDialog(requireActivity());
        }
        this.editLoadingDialog.show();
        this.editLoadingDialog.showLoading();
    }

    private void closeLoading() {
        EditLoadingDialog editLoadingDialog = this.editLoadingDialog;
        if (editLoadingDialog != null) {
            editLoadingDialog.hide();
            this.editLoadingDialog = null;
        }
    }

    private void showLoadingFail() {
        if (this.editLoadingDialog == null) {
            showLoading();
        }
        this.editLoadingDialog.showFail();
    }

    private void initView() {
        initTrackLeftView();
        updatemetronome();
        this.binding.viewTrackClip.init(this.binding.viewAudioTrack, new AudioTrackClipView.AudioTrackClipViewListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.12
            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView.AudioTrackClipViewListener
            public void showDBUI() {
                int clipDB = DataHelpAudioTrack.getClipDB(EditingParams.getInstance().getCurSelectPcmWaveId());
                EditingUtils.log("showDBUI db = " + clipDB);
                EditingPageFragment.this.binding.pageDb.seekbarGainPop.setProgress(clipDB);
                EditingPageFragment.this.binding.pageDb.tvMusicDb.setText((clipDB >= 0 ? "+" : "") + clipDB + " dB");
                EditingPageFragment.this.binding.dbUi.setVisibility(0);
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView.AudioTrackClipViewListener
            public void showFadeUI() {
                DataHelpAudioTrack.FadeValue clipFade = DataHelpAudioTrack.getClipFade(EditingParams.getInstance().getCurSelectPcmWaveId());
                EditingPageFragment.this.binding.pageFade.seekbarFade.setProgress(clipFade.fadein, clipFade.fadeout);
                EditingUtils.log("CmdSetClipFade 2 fadeValue.fadein = " + clipFade.fadein + "," + clipFade.fadeout);
                EditingPageFragment.this.showFadeType();
                EditingPageFragment.this.binding.fadeUi.setVisibility(0);
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView.AudioTrackClipViewListener
            public void showSpeedUI() {
                EditingPageFragment.this.showSpeedDialog();
            }
        });
        this.binding.dbUi.setOnClickListener(this);
        this.binding.fadeUi.setOnClickListener(this);
        this.binding.totalDbUi.setOnClickListener(this);
        this.binding.pageDb.btnCloseDb.setOnClickListener(this);
        this.binding.pageDb.viewDbTop.setOnClickListener(this);
        this.binding.pageFade.btnCloseFade.setOnClickListener(this);
        this.binding.pageFade.viewFadeTop.setOnClickListener(this);
        this.binding.btnRecord.setOnClickListener(this);
        this.binding.btnPlay.setOnClickListener(new DebounceClick(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditingPageFragment.this.clickPlayBtn();
            }
        }, 400L));
        this.binding.btnSetting.setOnClickListener(this);
        this.binding.btnTotalDb.setOnClickListener(this);
        this.binding.imageMyRes.setOnClickListener(this);
        this.binding.imagePreviewAll.setOnClickListener(this);
        this.binding.btnPosTemplate.setOnClickListener(this);
        this.binding.btnMetronome.setOnClickListener(this);
        this.binding.pageDb.seekbarGainPop.setOnSeekBarChangeListener(this);
        this.binding.pageDb.tvMusicDb.setText("+0 dB");
        this.binding.pageFade.seekbarFade.setProgress(0.0f, 100.0f);
        this.binding.pageFade.seekbarFade.setOnRangeChangedListener(this);
        this.binding.pageFade.btnFade0.setOnClickListener(this);
        this.binding.pageFade.btnFade1.setOnClickListener(this);
        this.binding.pageFade.btnFade2.setOnClickListener(this);
        this.binding.pageFade.btnFade3.setOnClickListener(this);
        DataHelpAudioTrack.setUndoRedoStateCallback(new DataHelpAudioTrack.UndoRedoStateCallback() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.14
            @Override // com.wanos.careditproject.utils.DataHelpAudioTrack.UndoRedoStateCallback
            public void stateChange() {
                EditingPageFragment.this.setUndoState();
                EditingPageFragment.this.setRedoState();
            }
        });
        setUndoState();
        setRedoState();
        this.binding.imageUndo.setOnClickListener(this);
        this.binding.imageRedo.setOnClickListener(this);
        this.binding.imageRes.setOnClickListener(this);
        this.binding.btnReset.setOnClickListener(this);
        this.binding.btnToEnd.setOnClickListener(this);
        this.binding.viewAudioTrack.setLeftUIListener(new AudioTracksGLView.LeftUIListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.15
            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.LeftUIListener
            public void addTrack(int i) {
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.LeftUIListener
            public void moveX(int i) {
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.LeftUIListener
            public void redraw() {
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.LeftUIListener
            public void scaleX(float f) {
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.LeftUIListener
            public void moveY(int i) {
                EditingPageFragment.this.binding.viewTrackLeft.scrollBy(0, i);
            }
        });
        AudioPcmData.getInstance().setAudioPcmListener(new AudioPcmData.AudioPcmListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.16
            @Override // com.wanos.careditproject.utils.codec.AudioPcmData.AudioPcmListener
            public void onFinish(final String str) {
                if (EditingPageFragment.this.isAlive()) {
                    EditingPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!EditingPageFragment.this.isAlive() || EditingPageFragment.this.binding.viewAudioTrack == null) {
                                return;
                            }
                            EditingPageFragment.this.binding.viewAudioTrack.drawPcmWaveView(str);
                        }
                    });
                }
            }
        });
        this.binding.viewAudioTrack.postDelayed(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.17
            @Override // java.lang.Runnable
            public void run() {
                int width = EditingPageFragment.this.binding.viewAudioTrack.getWidth();
                int height = EditingPageFragment.this.binding.viewAudioTrack.getHeight();
                int dimension = (int) EditingPageFragment.this.requireActivity().getResources().getDimension(R.dimen.edit_total_gain_h);
                int dimension2 = (int) EditingPageFragment.this.requireActivity().getResources().getDimension(R.dimen.track_width);
                EditingUtils.log("binding.viewAudioTrack w = " + width + ", h = " + height + ", h2 = " + dimension + ", w2 = " + dimension2);
                EditingPageFragment.this.binding.viewAudioTrack.setRenderInitData(dimension2, height, dimension);
            }
        }, 20L);
    }

    public void initProjectView() {
        int totalDB = DataHelpAudioTrack.getTotalDB();
        this.binding.containerGainTotal.seekBarGain.setProgress(totalDB);
        this.binding.containerGainTotal.seekBarGain.setOnSeekBarChangeListener(this);
        setTotaldB(totalDB);
        updateTrackLeftView();
    }

    public void setTotaldB(int i) {
        String str = (i >= 0 ? "+" : "") + i + "dB";
        this.binding.containerGainTotal.tvDb.setText(str);
        this.binding.tvTotalDb.setText(str);
    }

    public void initViewAudioTrack() {
        this.binding.viewAudioTrack.update();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUndoState() {
        if (DataHelpAudioTrack.undoIsEmpty()) {
            this.binding.imageUndo.setAlpha(0.5f);
        } else {
            this.binding.imageUndo.setAlpha(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRedoState() {
        if (DataHelpAudioTrack.redoIsEmpty()) {
            this.binding.imageRedo.setAlpha(0.5f);
        } else {
            this.binding.imageRedo.setAlpha(1.0f);
        }
    }

    public void initTrackLeftView() {
        this.trackCount = 0;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.leftLayoutMananger = linearLayoutManager;
        linearLayoutManager.setRecycleChildrenOnDetach(false);
        this.binding.viewTrackLeft.setLayoutManager(this.leftLayoutMananger);
        AudioTrackLeftAdapter audioTrackLeftAdapter = new AudioTrackLeftAdapter(getContext(), DataHelpAudioTrack.getTrackList(), new AudioTrackLeftAdapter.AudioTrackLeftAdapterListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.18
            @Override // com.wanos.careditproject.adapter.AudioTrackLeftAdapter.AudioTrackLeftAdapterListener
            public void showObjPostionUI(int i) {
                EditingPageFragment.this.binding.viewAudioTrack.selectTrackLine(i);
                if (EditingPageFragment.this.objPosDialogIsShow()) {
                    return;
                }
                EditingPageFragment.this.hideDialog();
                EditingPageFragment.this.showObjPosDialog();
            }

            @Override // com.wanos.careditproject.adapter.AudioTrackLeftAdapter.AudioTrackLeftAdapterListener
            public void update(boolean z) {
                EditingUtils.log("binding.viewTrackLeft addTrack");
                EditingPageFragment.this.updateTrack(z);
            }

            @Override // com.wanos.careditproject.adapter.AudioTrackLeftAdapter.AudioTrackLeftAdapterListener
            public void onItemClicked(int i, TrackItemModel trackItemModel) {
                EditingPageFragment.this.currentIndex = i;
            }

            @Override // com.wanos.careditproject.adapter.AudioTrackLeftAdapter.AudioTrackLeftAdapterListener
            public void selectTrackIndex(int i) {
                EditingPageFragment.this.currentIndex = i;
                EditingPageFragment.this.binding.viewAudioTrack.selectTrackLine(i);
            }
        });
        this.audioTrackLeftAdapter = audioTrackLeftAdapter;
        audioTrackLeftAdapter.setFooterView(this.layoutAudioTrackAddBinding.getRoot());
        this.binding.viewTrackLeft.setAdapter(this.audioTrackLeftAdapter);
        RcItemTouchHelperCallback rcItemTouchHelperCallback = new RcItemTouchHelperCallback(this.audioTrackLeftAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(rcItemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(this.binding.viewTrackLeft);
        this.audioTrackLeftAdapter.attachItemTouchHelper(itemTouchHelper);
        rcItemTouchHelperCallback.setListener(new RcItemTouchHelperCallback.OnItemTouchListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.19
            @Override // com.wanos.careditproject.utils.rcDrag.RcItemTouchHelperCallback.OnItemTouchListener
            public void onLongClickStart() {
                EditingPageFragment.this.hideDialog();
            }
        });
        this.binding.viewTrackLeft.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.20
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                View childAt = EditingPageFragment.this.binding.viewTrackLeft.getChildAt(0);
                if (childAt == null) {
                    return;
                }
                int iFindFirstVisibleItemPosition = EditingPageFragment.this.leftLayoutMananger.findFirstVisibleItemPosition();
                int top = childAt.getTop();
                int height = (-top) + (childAt.getHeight() * iFindFirstVisibleItemPosition);
                EditingPageFragment.this.leftRCY = height;
                EditingUtils.log("binding.viewTrackLeft a = " + height + ",top = " + top + ", firstVisiblePosition=" + iFindFirstVisibleItemPosition);
                EditingPageFragment.this.binding.viewAudioTrack.moveView(EditingPageFragment.this.leftRCY);
            }
        });
        this.layoutAudioTrackAddBinding.viewAdd.setOnClickListener(new DebounceClick(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PlayerUtils.isStop()) {
                    EditingPageFragment.this.playerStop();
                }
                CreatorNewTrackDialog creatorNewTrackDialog = new CreatorNewTrackDialog();
                creatorNewTrackDialog.show(EditingPageFragment.this.getParentFragmentManager(), "newTrack");
                creatorNewTrackDialog.setOnDismissListener(new CreatorNewTrackDialog.DismissListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.21.1
                    @Override // com.wanos.careditproject.view.dialog.CreatorNewTrackDialog.DismissListener
                    public void onDismiss(int i) {
                        if (DataHelpAudioTrack.newTrack(false) != -1) {
                            EditingPageFragment.this.currentIndex = DataHelpAudioTrack.getTrackList().size() - 1;
                            EditingPageFragment.this.updateTrack(true);
                            EditingPageFragment.this.binding.viewAudioTrack.selectTrackLine(EditingPageFragment.this.currentIndex);
                            if (i == 1) {
                                EditingPageFragment.this.showRecordDialog();
                                return;
                            } else {
                                if (i == 2) {
                                    Intent intent = new Intent(EditingPageFragment.this.getContext(), (Class<?>) MaterialListActivity.class);
                                    intent.putExtra(MaterialListActivity.pageType, 1);
                                    ((BaseActivity) EditingPageFragment.this.getActivity()).startActivity(intent, true);
                                    return;
                                }
                                return;
                            }
                        }
                        EditingUtils.showTips("总声道数最大" + EditingUtils.getMaxChannelNum() + "个");
                    }
                });
            }
        }, 50L));
        if (DataHelpAudioTrack.getAllChannelNum() >= EditingUtils.getMaxChannelNum()) {
            this.layoutAudioTrackAddBinding.viewAdd.setVisibility(8);
        } else {
            this.layoutAudioTrackAddBinding.viewAdd.setVisibility(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateEditUIEvent updateEditUIEvent) {
        if (updateEditUIEvent.getType().equals(UpdateEditUIEvent.eventType.updateTrackLeftView)) {
            updateTrackLeftView();
        } else if (updateEditUIEvent.getType().equals(UpdateEditUIEvent.eventType.updateTime)) {
            updateTime();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackLeftView() {
        this.audioTrackLeftAdapter.setData(DataHelpAudioTrack.getTrackList());
        this.audioTrackLeftAdapter.notifyDataSetChanged();
    }

    public void updateUI() {
        updateTrack(true);
        int totalDB = DataHelpAudioTrack.getTotalDB();
        this.binding.containerGainTotal.seekBarGain.setProgress(totalDB);
        setTotaldB(totalDB);
        if (this.binding.dbUi.getVisibility() == 0) {
            int clipDB = DataHelpAudioTrack.getClipDB(EditingParams.getInstance().getCurSelectPcmWaveId());
            EditingUtils.log("showDBUI db = " + clipDB);
            this.binding.pageDb.seekbarGainPop.setProgress(clipDB);
            this.binding.pageDb.tvMusicDb.setText((clipDB >= 0 ? "+" : "") + clipDB + " dB");
        }
        if (this.binding.fadeUi.getVisibility() == 0) {
            DataHelpAudioTrack.FadeValue clipFade = DataHelpAudioTrack.getClipFade(EditingParams.getInstance().getCurSelectPcmWaveId());
            EditingUtils.log("CmdSetClipFade fadeValue.fadein = " + clipFade.fadein + ",out=" + clipFade.fadeout);
            this.binding.pageFade.seekbarFade.setProgress(clipFade.fadein, clipFade.fadeout);
            showFadeType();
        }
    }

    public void updateTrack(boolean z) {
        if (z) {
            this.trackCount = 0;
            updateTrackLeftView();
        } else {
            for (AudioTrackLeftItem audioTrackLeftItem : this.leftTrackViewList) {
                audioTrackLeftItem.update();
                audioTrackLeftItem.invalidate();
            }
        }
        this.binding.viewAudioTrack.update();
    }

    public void updateTime() {
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        long maxSampleNum = DataHelpAudioTrack.getMaxSampleNum();
        if (progressSampleNum > EditingUtils.getMaxSampleNum()) {
            progressSampleNum = EditingUtils.getMaxSampleNum();
        }
        this.editingActivityViewModel.curSampleNum.postValue(Long.valueOf(progressSampleNum));
        this.editingActivityViewModel.maxSampleNum.postValue(Long.valueOf(maxSampleNum));
    }

    public void playerPlayUI() {
        this.binding.btnPlay.setImageResource(R.drawable.editing_stop);
    }

    public void playerStop() {
        playerStopUI();
        PlayerUtils.stop(false);
    }

    public void playerStopUI() {
        this.binding.btnPlay.setImageResource(R.drawable.editing_play);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isHidden()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_close_db || id == R.id.view_db_top) {
            this.binding.dbUi.setVisibility(8);
            return;
        }
        if (id == R.id.btn_close_fade || id == R.id.view_fade_top) {
            this.binding.fadeUi.setVisibility(8);
            return;
        }
        if (id == R.id.btn_reset) {
            if (recordDialogIsShow()) {
                return;
            }
            hideToolBar();
            if (this.isResetting) {
                return;
            }
            if (!PlayerUtils.isStop()) {
                PlayerUtils.pause();
                this.isResetting = true;
                new Handler().postDelayed(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.22
                    @Override // java.lang.Runnable
                    public void run() {
                        EditingPageFragment.this.binding.viewAudioTrack.reset();
                        PlayerUtils.resume(true);
                        EditingPageFragment.this.updateObjPosUI();
                        EditingPageFragment.this.isResetting = false;
                    }
                }, 300L);
                return;
            } else {
                this.binding.viewAudioTrack.reset();
                updateObjPosUI();
                return;
            }
        }
        if (id == R.id.btn_to_end) {
            if (recordDialogIsShow()) {
                return;
            }
            hideToolBar();
            this.binding.viewAudioTrack.toEnd();
            updateObjPosUI();
            return;
        }
        if (id == R.id.btn_record) {
            if (EditingParams.getInstance().getSelectTrackIndex2() < 0) {
                ToastUtil.showMsg(getResources().getString(R.string.record_to_select_track));
                return;
            }
            if (!recordDialogIsShow() && EditingParams.getInstance().getProjectDataIsInit()) {
                if (objPosDialogIsShow()) {
                    EditingParams.getInstance().setPosDrawClick(!EditingParams.getInstance().isPosDrawClick());
                    updateObjPosRecordIcon();
                    return;
                }
                hideDialog();
                if (!PlayerUtils.isStop()) {
                    playerStop();
                    return;
                } else {
                    showRecordDialog();
                    return;
                }
            }
            return;
        }
        if (id == R.id.btn_play) {
            clickPlayBtn();
            return;
        }
        if (id == R.id.btn_setting) {
            if (!recordDialogIsShow() && EditingParams.getInstance().getProjectDataIsInit()) {
                hideDialog();
                stopPlayerAndRecord();
                showSettingsDialog();
                return;
            }
            return;
        }
        if (id == R.id.btn_fade_0) {
            setFadeTyp(EditingUtils.FADETYPE.FADETYPE0);
            return;
        }
        if (id == R.id.btn_fade_1) {
            setFadeTyp(EditingUtils.FADETYPE.FADETYPE1);
            return;
        }
        if (id == R.id.btn_fade_2) {
            setFadeTyp(EditingUtils.FADETYPE.FADETYPE2);
            return;
        }
        if (id == R.id.btn_fade_3) {
            setFadeTyp(EditingUtils.FADETYPE.FADETYPE3);
            return;
        }
        if (id == R.id.image_undo) {
            if (recordDialogIsShow() || PlayerUtils.isPlaying()) {
                return;
            }
            this.binding.viewTrackClip.hidePcmToolBar();
            if (DataHelpAudioTrack.undo()) {
                WaveUIGLRender.sCmdUndoRedo();
                updateUI();
                if (objPosDialogIsShow()) {
                    updateObjPosChannel();
                    return;
                }
                return;
            }
            return;
        }
        if (id == R.id.image_redo) {
            if (recordDialogIsShow() || PlayerUtils.isPlaying()) {
                return;
            }
            this.binding.viewTrackClip.hidePcmToolBar();
            if (DataHelpAudioTrack.redo()) {
                WaveUIGLRender.sCmdUndoRedo();
                updateUI();
                if (objPosDialogIsShow()) {
                    updateObjPosChannel();
                    return;
                }
                return;
            }
            return;
        }
        if (id == R.id.image_my_res) {
            if (recordDialogIsShow() || !EditingParams.getInstance().getProjectDataIsInit() || checkSelectTrack()) {
                return;
            }
            stopPlayerAndRecord();
            Intent intent = new Intent(getContext(), (Class<?>) MaterialListActivity.class);
            intent.putExtra(MaterialListActivity.pageType, 0);
            ((BaseActivity) getActivity()).startActivity(intent, true);
            return;
        }
        if (id == R.id.image_res) {
            if (recordDialogIsShow() || !EditingParams.getInstance().getProjectDataIsInit() || checkSelectTrack()) {
                return;
            }
            stopPlayerAndRecord();
            Intent intent2 = new Intent(getContext(), (Class<?>) MaterialListActivity.class);
            intent2.putExtra(MaterialListActivity.pageType, 1);
            ((BaseActivity) getActivity()).startActivity(intent2, true);
            return;
        }
        if (id == R.id.btn_total_db) {
            this.binding.totalDbUi.setVisibility(0);
            return;
        }
        if (id == R.id.image_my_res) {
            if (!recordDialogIsShow() && EditingParams.getInstance().getProjectDataIsInit()) {
                stopPlayerAndRecord();
                Intent intent3 = new Intent(getContext(), (Class<?>) MaterialListActivity.class);
                intent3.putExtra(MaterialListActivity.pageType, 1);
                ((BaseActivity) getActivity()).startActivity(intent3, true);
                return;
            }
            return;
        }
        if (id == R.id.image_preview_all) {
            this.binding.viewAudioTrack.showAllGLUI();
            return;
        }
        if (id == R.id.btn_pos_template) {
            return;
        }
        if (id == R.id.btn_metronome) {
            StorageUtils.getInstance().setBeatPlay(!StorageUtils.getInstance().getBeatPlay());
            updatemetronome();
        } else if (id == R.id.btn_draw_help) {
            new HelpPosDrawDialog().show(getChildFragmentManager(), "HelpPosDrawDialog");
        }
    }

    private boolean checkSelectTrack() {
        if (EditingParams.getInstance().getSelectTrackIndex2() >= 0) {
            return false;
        }
        ToastUtil.showMsg(getResources().getString(R.string.res_to_select_track));
        return true;
    }

    private void updatemetronome() {
        if (StorageUtils.getInstance().getBeatPlay()) {
            this.binding.btnMetronome.setSelected(true);
        } else {
            this.binding.btnMetronome.setSelected(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayBtn() {
        if (EditingParams.getInstance().getProjectDataIsInit() && !recordDialogIsShow()) {
            hideDialog2();
            if (PlayerUtils.isStop()) {
                playerPlayUI();
                PlayerUtils.play(this.playerListener, false, true, false);
            } else {
                playerStop();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void copyFileUsingIO(Context context, Uri uri, String str) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        FileChannel channel = fileInputStream.getChannel();
        fileOutputStream.getChannel().transferFrom(channel, 0L, channel.size());
        fileInputStream.close();
        fileOutputStream.close();
        parcelFileDescriptorOpenFileDescriptor.close();
    }

    private String readFileContent(Context context, Uri uri) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpenInputStream));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line).append("\n");
                }
                inputStreamOpenInputStream.close();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void stopPlayerAndRecord() {
        if (PlayerUtils.isStop()) {
            return;
        }
        playerStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSpeedDialog() {
        Fragment fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.speed_ui);
        if (fragmentFindFragmentById == null) {
            CreatorSpeedDialog creatorSpeedDialog = new CreatorSpeedDialog();
            creatorSpeedDialog.setOnSpeedDialogListener(new CreatorSpeedDialog.OnSpeedDialogListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.23
                @Override // com.wanos.careditproject.view.dialog.CreatorSpeedDialog.OnSpeedDialogListener
                public void onHide() {
                    EditingPageFragment.this.hideSpeedDialog();
                }
            });
            fragmentTransactionBeginTransaction.add(R.id.speed_ui, creatorSpeedDialog);
            fragment = creatorSpeedDialog;
        } else {
            ((CreatorSpeedDialog) fragmentFindFragmentById).updateView();
            fragment = fragmentFindFragmentById;
        }
        fragmentTransactionBeginTransaction.show(fragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSpeedDialog() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.speed_ui);
        if (fragmentFindFragmentById == null || !fragmentFindFragmentById.isVisible()) {
            return;
        }
        getChildFragmentManager().beginTransaction().hide(fragmentFindFragmentById).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRecordDialog() {
        Fragment fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.record_ui);
        if (fragmentFindFragmentById == null) {
            CreatorRecordDialog creatorRecordDialog = new CreatorRecordDialog();
            creatorRecordDialog.setonRecordeDialogListener(new CreatorRecordDialog.OnRecordeDialogListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.24
                @Override // com.wanos.careditproject.view.dialog.CreatorRecordDialog.OnRecordeDialogListener
                public void onHide(boolean z) {
                    EditingPageFragment.this.hideRecordDialog();
                    if (z) {
                        EditingPageFragment.this.updateUI();
                    }
                }
            });
            fragmentTransactionBeginTransaction.add(R.id.record_ui, creatorRecordDialog);
            fragment = creatorRecordDialog;
        } else {
            ((CreatorRecordDialog) fragmentFindFragmentById).init();
            fragment = fragmentFindFragmentById;
        }
        fragmentTransactionBeginTransaction.show(fragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideRecordDialog() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.record_ui);
        if (fragmentFindFragmentById == null || !fragmentFindFragmentById.isVisible()) {
            return;
        }
        getChildFragmentManager().beginTransaction().hide(fragmentFindFragmentById).commitAllowingStateLoss();
    }

    private boolean recordDialogIsShow() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.record_ui);
        return fragmentFindFragmentById != null && fragmentFindFragmentById.isVisible();
    }

    private void hideSettingsDialog() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.setting_ui);
        if (fragmentFindFragmentById == null || !fragmentFindFragmentById.isVisible()) {
            return;
        }
        getChildFragmentManager().beginTransaction().hide(fragmentFindFragmentById).commitAllowingStateLoss();
    }

    private void showSettingsDialog() {
        if (this.creatorSettingDialog == null) {
            this.creatorSettingDialog = new CreatorSettingDialog(getActivity(), com.wanos.commonlibrary.R.style.Dialog);
        }
        this.creatorSettingDialog.show(0);
    }

    public void saveTmpFile() {
        EditingUtils.saveFile(new Gson().toJson(DataHelpAudioTrack.getRootModel()), "0car.json");
    }

    public void setFadeTyp(EditingUtils.FADETYPE fadetype) {
        DataHelpAudioTrack.setClipFadeType(EditingParams.getInstance().getCurSelectPcmWaveId(), fadetype.getType());
        showFadeType();
        this.binding.viewAudioTrack.drawPcmWaveViewById(EditingParams.getInstance().getCurSelectPcmWaveId());
    }

    public void showFadeType() {
        this.binding.pageFade.btnFade0.setBackgroundColor(getResources().getColor(R.color.edit_pop_bottom_text_bg, null));
        this.binding.pageFade.btnFade1.setBackgroundColor(getResources().getColor(R.color.edit_pop_bottom_text_bg, null));
        this.binding.pageFade.btnFade2.setBackgroundColor(getResources().getColor(R.color.edit_pop_bottom_text_bg, null));
        this.binding.pageFade.btnFade3.setBackgroundColor(getResources().getColor(R.color.edit_pop_bottom_text_bg, null));
        String clipFadeType = DataHelpAudioTrack.getClipFadeType(EditingParams.getInstance().getCurSelectPcmWaveId());
        if (clipFadeType.equals(EditingUtils.FADETYPE.FADETYPE0.getType()) || clipFadeType.equals("")) {
            this.binding.pageFade.btnFade0.setBackgroundColor(getResources().getColor(R.color.edit_color_main, null));
            return;
        }
        if (clipFadeType.equals(EditingUtils.FADETYPE.FADETYPE1.getType())) {
            this.binding.pageFade.btnFade1.setBackgroundColor(getResources().getColor(R.color.edit_color_main, null));
            return;
        }
        if (clipFadeType.equals(EditingUtils.FADETYPE.FADETYPE2.getType())) {
            this.binding.pageFade.btnFade2.setBackgroundColor(getResources().getColor(R.color.edit_color_main, null));
        } else if (clipFadeType.equals(EditingUtils.FADETYPE.FADETYPE3.getType())) {
            this.binding.pageFade.btnFade3.setBackgroundColor(getResources().getColor(R.color.edit_color_main, null));
        } else {
            this.binding.pageFade.btnFade0.setBackgroundColor(getResources().getColor(R.color.edit_color_main, null));
        }
    }

    public void hideDialog() {
        this.binding.dbUi.setVisibility(8);
        this.binding.fadeUi.setVisibility(8);
        hideObjPosDialog();
        hideSettingsDialog();
        hideSpeedDialog();
        hideToolBar();
        this.binding.totalDbUi.setVisibility(8);
    }

    public void hideDialog2() {
        this.binding.dbUi.setVisibility(8);
        this.binding.fadeUi.setVisibility(8);
        hideSettingsDialog();
        hideSpeedDialog();
        hideToolBar();
    }

    private void hideToolBar() {
        this.binding.viewTrackClip.hidePcmToolBar();
        this.binding.viewTrackClip.hidePasteBar();
    }

    public boolean isShowDialog() {
        return this.binding.dbUi.getVisibility() == 0 || this.binding.fadeUi.getVisibility() == 0 || objPosDialogIsShow() || recordDialogIsShow();
    }

    @Override // com.jaygoo.widget.OnRangeChangedListener
    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
        if (EditingParams.getInstance().getProjectDataIsInit() && z && rangeSeekBar.getId() == R.id.seekbar_fade) {
            EditingUtils.log("AudioTracksGLView R.id.gain_item leftValue=" + f);
            String curSelectPcmWaveId = EditingParams.getInstance().getCurSelectPcmWaveId();
            DataHelpAudioTrack.setClipFade(curSelectPcmWaveId, (int) f, (int) f2);
            this.binding.viewAudioTrack.moveToClipFade(curSelectPcmWaveId, this.isTouchLeft);
        }
    }

    @Override // com.jaygoo.widget.OnRangeChangedListener
    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
        if (EditingParams.getInstance().getProjectDataIsInit()) {
            this.isTouchLeft = z;
            if (rangeSeekBar.getId() == R.id.seekbar_fade) {
                new CmdSetClipFade().saveOld(EditingParams.getInstance().getCurSelectPcmWaveId());
            }
        }
    }

    @Override // com.jaygoo.widget.OnRangeChangedListener
    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
        if (rangeSeekBar.getId() == R.id.seekbar_fade) {
            EditingParams.getInstance().getCurSelectPcmWaveId();
            CmdSetClipFade currentCmd = CmdSetClipFade.getCurrentCmd();
            if (currentCmd != null) {
                currentCmd.saveNew();
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (EditingParams.getInstance().getProjectDataIsInit()) {
            int id = seekBar.getId();
            if (id == R.id.seek_bar_gain) {
                DataHelpAudioTrack.setTotalDB(i);
                setTotaldB(i);
            } else if (id == R.id.seekbar_gain_pop) {
                DataHelpAudioTrack.setClipDB(EditingParams.getInstance().getCurSelectPcmWaveId(), i);
                this.binding.pageDb.tvMusicDb.setText((i >= 0 ? "+" : "") + i + " dB");
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (EditingParams.getInstance().getProjectDataIsInit()) {
            int id = seekBar.getId();
            if (id == R.id.seek_bar_gain) {
                new CmdSetTotalDB().saveOld();
            } else if (id == R.id.seekbar_gain_pop) {
                new CmdSetClipDB().saveOld(EditingParams.getInstance().getCurSelectPcmWaveId());
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        CmdSetClipDB currentCmd;
        int id = seekBar.getId();
        if (id == R.id.seek_bar_gain) {
            CmdSetTotalDB currentCmd2 = CmdSetTotalDB.getCurrentCmd();
            if (currentCmd2 != null) {
                currentCmd2.saveNew();
                return;
            }
            return;
        }
        if (id != R.id.seekbar_gain_pop || (currentCmd = CmdSetClipDB.getCurrentCmd()) == null) {
            return;
        }
        currentCmd.saveNew();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean objPosDialogIsShow() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.obj_pos_ui);
        if (fragmentFindFragmentById != null) {
            return fragmentFindFragmentById.isVisible();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateObjPosRecordIcon() {
        boolean zIsPosDrawClick = EditingParams.getInstance().isPosDrawClick();
        EditingUtils.log("posDrawShow isPosDraw = " + zIsPosDrawClick);
        if (zIsPosDrawClick) {
            this.binding.btnRecord.setImageResource(R.drawable.edit_btn_recording);
        } else {
            this.binding.btnRecord.setImageResource(R.drawable.edit_btn_record);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateObjPosUI() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.obj_pos_ui);
        if (fragmentFindFragmentById != null && fragmentFindFragmentById.isVisible() && (fragmentFindFragmentById instanceof CreatorObjPosDialog)) {
            ((CreatorObjPosDialog) fragmentFindFragmentById).updatePosUI(EditingParams.getInstance().getSelectTrackIndexForBall(), EditingParams.getInstance().getProgressSampleNum());
        }
        updateTrackLeftView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showObjPosDialog() {
        Fragment fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.obj_pos_ui);
        if (fragmentFindFragmentById == null) {
            CreatorObjPosDialog creatorObjPosDialog = new CreatorObjPosDialog();
            creatorObjPosDialog.setListener(new CreatorObjPosDialog.ObjPosDialogListener() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.25
                @Override // com.wanos.careditproject.view.dialog.CreatorObjPosDialog.ObjPosDialogListener
                public void toPlay() {
                    if (PlayerUtils.isStop()) {
                        if (EditingPageFragment.this.isAlive()) {
                            EditingPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.25.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (EditingPageFragment.this.isAlive()) {
                                        EditingPageFragment.this.playerPlayUI();
                                    }
                                }
                            });
                        }
                        PlayerUtils.play(EditingPageFragment.this.playerListener, false, true, false);
                    }
                }

                @Override // com.wanos.careditproject.view.dialog.CreatorObjPosDialog.ObjPosDialogListener
                public void updateUI() {
                    if (EditingPageFragment.this.isAlive()) {
                        EditingPageFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.audiotrackedit.EditingPageFragment.25.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (EditingPageFragment.this.isAlive()) {
                                    EditingPageFragment.this.binding.viewAudioTrack.update();
                                }
                            }
                        });
                    }
                }

                @Override // com.wanos.careditproject.view.dialog.CreatorObjPosDialog.ObjPosDialogListener
                public void updateRecordIcon() {
                    updateRecordIcon();
                }

                @Override // com.wanos.careditproject.view.dialog.CreatorObjPosDialog.ObjPosDialogListener
                public void onHide() {
                    EditingPageFragment.this.hideObjPosDialog();
                    EditingPageFragment.this.updateTrackLeftView();
                    EditingParams.getInstance().setPosDrawClick(false);
                    EditingPageFragment.this.updateObjPosRecordIcon();
                }
            });
            fragmentTransactionBeginTransaction.add(R.id.obj_pos_ui, creatorObjPosDialog);
            fragment = creatorObjPosDialog;
        } else {
            ((CreatorObjPosDialog) fragmentFindFragmentById).show();
            fragment = fragmentFindFragmentById;
        }
        fragmentTransactionBeginTransaction.show(fragment).commitAllowingStateLoss();
        EditingParams.getInstance().setPosDrawClick(false);
    }

    private void updateObjPosChannel() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.obj_pos_ui);
        if (fragmentFindFragmentById == null || !(fragmentFindFragmentById instanceof CreatorObjPosDialog)) {
            return;
        }
        ((CreatorObjPosDialog) fragmentFindFragmentById).updateChannelData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideObjPosDialog() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.obj_pos_ui);
        if (fragmentFindFragmentById == null || !fragmentFindFragmentById.isVisible()) {
            return;
        }
        getChildFragmentManager().beginTransaction().hide(fragmentFindFragmentById).commitAllowingStateLoss();
    }

    public boolean isAlive() {
        return (!isAdded() || isRemoving() || isDetached() || getActivity() == null || getActivity().isFinishing()) ? false : true;
    }

    public void showTips(String str) {
        ToastUtils.showShort(str);
    }

    public class RedrawList {
        public List<String> list;
        public int trackIndex;

        public RedrawList() {
        }
    }
}
