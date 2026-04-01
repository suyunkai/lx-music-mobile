package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomRecordBinding;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.codec.AudioFileInfo;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.careditproject.view.viewmodel.CreatorRecordDialogViewModel;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.manager.GlobalAudioFocusManager;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.media.util.ToastUtil;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.io.File;
import java.nio.file.Paths;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorRecordDialog extends Fragment implements View.OnClickListener, OnStatusCallbackListener {
    private PopBottomRecordBinding binding;
    private RecordUtils.RecordRes curRecordRes = null;
    private GlobalAudioFocusManager.IPlayer focusLiener = new GlobalAudioFocusManager.IPlayer() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.1
        @Override // com.wanos.commonlibrary.manager.GlobalAudioFocusManager.IPlayer
        public void onAudioFocusLost() {
            if (CreatorRecordDialog.this.getActivity() == null || CreatorRecordDialog.this.viewIsDestroyed()) {
                return;
            }
            CreatorRecordDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.1.1
                @Override // java.lang.Runnable
                public void run() {
                    CreatorRecordDialog.this.stopRecord();
                }
            });
        }
    };
    private MediaPlayerHelper mMediaPlayerHelper;
    private OnRecordeDialogListener onRecordeDialogListener;
    private CreatorRecordDialogViewModel viewModel;

    public interface OnRecordeDialogListener {
        void onHide(boolean z);
    }

    public void setonRecordeDialogListener(OnRecordeDialogListener onRecordeDialogListener) {
        this.onRecordeDialogListener = onRecordeDialogListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = PopBottomRecordBinding.inflate(layoutInflater, viewGroup, false);
        this.curRecordRes = null;
        this.viewModel = (CreatorRecordDialogViewModel) new ViewModelProvider(this).get(CreatorRecordDialogViewModel.class);
        initView();
        initListener();
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (RecordUtils.getInstance().isRecording()) {
            RecordUtils.getInstance().stopRecord();
        }
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null) {
            mediaPlayerHelper.stop();
            this.mMediaPlayerHelper.release();
            this.mMediaPlayerHelper = null;
        }
        super.onDestroy();
    }

    private void initView() {
        this.binding.tvTimeLast.setText(BceConfig.BOS_DELIMITER + EditingUtils.stringForSecond(EditingUtils.getTotalTimeOfSecond()));
        showRecordUI();
        updateView();
    }

    private void initListener() {
        this.binding.btnCloseRecord.setOnClickListener(this);
        this.binding.viewRoot.setOnClickListener(this);
        this.binding.btnRecord.setOnClickListener(this);
        this.binding.btnPlay.setOnClickListener(this);
        this.binding.btnDelete.setOnClickListener(this);
        this.binding.btnSelect.setOnClickListener(this);
        this.binding.viewTop.setOnClickListener(this);
        this.viewModel.recordResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m429x8e1dbd3e((RecordUtils.RecordResult) obj);
            }
        });
        this.viewModel.recordRes.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m430x1b0ad45d((RecordUtils.RecordRes) obj);
            }
        });
        this.viewModel.recordUploadResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m431xa7f7eb7c((CreatorRecordDialogViewModel.RecordUploadResult) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$0$com-wanos-careditproject-view-dialog-CreatorRecordDialog, reason: not valid java name */
    /* synthetic */ void m429x8e1dbd3e(RecordUtils.RecordResult recordResult) {
        GlobalAudioFocusManager.getInstance().abandonFocus(this.focusLiener);
        if (recordResult.isSuccess) {
            this.curRecordRes = recordResult.recordRes;
            showPlayerUI();
        } else {
            showToastMsg(recordResult.msg);
        }
    }

    /* JADX INFO: renamed from: lambda$initListener$1$com-wanos-careditproject-view-dialog-CreatorRecordDialog, reason: not valid java name */
    /* synthetic */ void m430x1b0ad45d(RecordUtils.RecordRes recordRes) {
        this.curRecordRes = recordRes;
        this.binding.viewRecordWav.setData(recordRes);
        this.binding.viewRecordWav.invalidate();
        updateView();
    }

    /* JADX INFO: renamed from: lambda$initListener$2$com-wanos-careditproject-view-dialog-CreatorRecordDialog, reason: not valid java name */
    /* synthetic */ void m431xa7f7eb7c(CreatorRecordDialogViewModel.RecordUploadResult recordUploadResult) {
        if (getContext() != null && (getContext() instanceof EditingActivity)) {
            ((EditingActivity) getContext()).closeLoadingView();
        }
        if (recordUploadResult.isSuccess) {
            uploadSuccess(recordUploadResult.uploadSuccessBean.getUrl(), this.curRecordRes.localPath);
        } else {
            showToastMsg("上传失败，请重试！");
        }
    }

    public void init() {
        this.curRecordRes = null;
        this.binding.viewRecordWav.setData(null);
        this.binding.viewRecordWav.invalidate();
        initView();
    }

    public void updateView() {
        RecordUtils.RecordRes recordRes = this.curRecordRes;
        final String strStringForSampleNum = recordRes != null ? EditingUtils.stringForSampleNum(recordRes.sampleNum, 48000) : "00:00";
        if (strStringForSampleNum.equals(this.binding.tvTimePre.getText())) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.2
            @Override // java.lang.Runnable
            public void run() {
                CreatorRecordDialog.this.binding.tvTimePre.setText(strStringForSampleNum);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtils.d("onClick 1 id = " + view.getId() + ",close id = " + R.id.btn_close_record);
        int id = view.getId();
        if (id == R.id.btn_close_record) {
            LogUtils.d("onClick 2");
            if (RecordUtils.getInstance().isRecording()) {
                showToastMsg(getResources().getString(R.string.record_no_close));
                return;
            }
            deleteRecordFile();
            OnRecordeDialogListener onRecordeDialogListener = this.onRecordeDialogListener;
            if (onRecordeDialogListener != null) {
                onRecordeDialogListener.onHide(false);
                return;
            }
            return;
        }
        if (id == R.id.btn_record) {
            if (RecordUtils.getInstance().isRecording()) {
                stopRecord();
                return;
            } else {
                startRecord();
                return;
            }
        }
        if (id == R.id.btn_play) {
            clickPlayBtn();
            return;
        }
        if (id == R.id.btn_delete) {
            MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
            if (mediaPlayerHelper != null && mediaPlayerHelper.isPlaying()) {
                playerStop();
            }
            deleteRecord();
            return;
        }
        if (id == R.id.btn_select) {
            MediaPlayerHelper mediaPlayerHelper2 = this.mMediaPlayerHelper;
            if (mediaPlayerHelper2 != null && mediaPlayerHelper2.isPlaying()) {
                playerStop();
            }
            selectRecord();
        }
    }

    public void clickPlayBtn() {
        MediaPlayerHelper mediaPlayerHelper = this.mMediaPlayerHelper;
        if (mediaPlayerHelper != null && mediaPlayerHelper.isPlaying()) {
            playerStop();
        } else {
            playerStart();
        }
    }

    public void stopRecord() {
        EditingUtils.log("stopRecord");
        RecordUtils.getInstance().stopRecord();
        initRecordUI();
    }

    private void startRecordUI() {
        this.binding.btnRecord.setImageResource(R.drawable.edit_record_1);
        this.binding.tvRecord.setText(getString(R.string.record_1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRecordUI() {
        this.binding.btnRecord.setImageResource(R.drawable.edit_record_0);
        this.binding.tvRecord.setText(getString(R.string.record_0));
    }

    public void showPlayerUI() {
        if (getActivity() == null || viewIsDestroyed()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.3
            @Override // java.lang.Runnable
            public void run() {
                CreatorRecordDialog.this.binding.viewRecord.setVisibility(8);
                CreatorRecordDialog.this.binding.viewPlayer.setVisibility(0);
                CreatorRecordDialog.this.binding.tvTimeLast.setVisibility(8);
                CreatorRecordDialog.this.binding.tvRecord.setText(CreatorRecordDialog.this.getString(R.string.record_2));
            }
        });
    }

    public void showRecordUI() {
        getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.4
            @Override // java.lang.Runnable
            public void run() {
                CreatorRecordDialog.this.binding.viewRecord.setVisibility(0);
                CreatorRecordDialog.this.binding.viewPlayer.setVisibility(8);
                CreatorRecordDialog.this.binding.tvTimeLast.setVisibility(0);
                CreatorRecordDialog.this.initRecordUI();
            }
        });
    }

    public void playerStart() {
        RecordUtils.RecordRes recordRes = this.curRecordRes;
        if (recordRes != null) {
            String str = recordRes.localPath;
            if (this.mMediaPlayerHelper == null) {
                MediaPlayerHelper mediaPlayerHelperCreate = MediaPlayerHelperUtil.create();
                this.mMediaPlayerHelper = mediaPlayerHelperCreate;
                mediaPlayerHelperCreate.setProgressInterval(300);
                this.mMediaPlayerHelper.setOnStatusCallbackListener(this);
            }
            this.mMediaPlayerHelper.playUrl(getContext(), str, false, -1);
        }
    }

    public void playerStop() {
        this.mMediaPlayerHelper.pause();
    }

    public void startRecord() {
        EditingUtils.log("startRecord");
        GlobalAudioFocusManager.getInstance().requestFocus(this.focusLiener);
        startRecordUI();
        if (!this.viewModel.startRecord().equals("-1")) {
            this.binding.btnRecord.setImageResource(R.drawable.edit_record_1);
            this.binding.tvRecord.setText(getString(R.string.record_1));
        } else {
            showToastMsg("录音功能受限，请检查权限！");
        }
    }

    private void deleteRecord() {
        deleteRecordFile();
        init();
    }

    private void deleteRecordFile() {
        if (this.curRecordRes != null) {
            new File(this.curRecordRes.localPath).delete();
        }
    }

    private void selectRecord() {
        if (this.curRecordRes == null) {
            showToastMsg("请重新录制！");
            return;
        }
        if (requireContext() instanceof EditingActivity) {
            ((EditingActivity) requireContext()).showLoadingView();
        }
        this.viewModel.uploadRes(new File(this.curRecordRes.localPath), Integer.parseInt(EditingParams.getInstance().getProjectId()));
    }

    private void uploadSuccess(String str, String str2) {
        if (viewIsDestroyed()) {
            return;
        }
        DownloadUtils.getInstance().put(str, str2);
        AudioFileInfo.getInstance().add(str, 48000, 1, this.curRecordRes.sampleNum);
        AudioPcmData.getInstance().initV2(str2, str, 48000, RecordUtils.recordChannel, ((int) this.curRecordRes.sampleNum) * EditingUtils.sizeOfShort, "", 1);
        AudioPcmData.getInstance().addRecordWavPcm(str, this.curRecordRes.recordWavData, this.curRecordRes.recordWavDataLen);
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        int selectTrackIndex2 = EditingParams.getInstance().getSelectTrackIndex2();
        String string = Paths.get(str2, new String[0]).getFileName().toString();
        String strSubstring = string.substring(0, string.lastIndexOf(46));
        EditingUtils.log("fileNameWithoutExtension = " + strSubstring);
        DataHelpAudioTrack.DataHelpResult dataHelpResultAddRecordWithUrl = DataHelpAudioTrack.addRecordWithUrl(str, 48000, RecordUtils.recordChannel, this.curRecordRes.sampleNum, selectTrackIndex2, progressSampleNum, strSubstring);
        if (requireContext() instanceof EditingActivity) {
            ((EditingActivity) requireContext()).closeLoadingView();
        }
        if (dataHelpResultAddRecordWithUrl.isSuccess()) {
            OnRecordeDialogListener onRecordeDialogListener = this.onRecordeDialogListener;
            if (onRecordeDialogListener != null) {
                onRecordeDialogListener.onHide(true);
                return;
            }
            return;
        }
        showToastMsg(dataHelpResultAddRecordWithUrl.getErrMsg());
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        if (viewIsDestroyed()) {
            return;
        }
        if (callBackState != MediaPlayerEnum.CallBackState.PROGRESS) {
            EditingUtils.log("onStatusonStatusCallbackNext status = " + callBackState);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PROGRESS || callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            this.binding.btnPlayImg.setVisibility(8);
            this.binding.btnPlayLottie.setVisibility(0);
            if (!this.binding.btnPlayLottie.isAnimating()) {
                this.binding.btnPlayLottie.setAnimation(R.raw.edit_record_play);
                this.binding.btnPlayLottie.playAnimation();
            }
            this.binding.tvRecord.setText("");
            return;
        }
        this.binding.btnPlayImg.setImageResource(R.drawable.edit_record_play_pause);
        this.binding.btnPlayImg.setVisibility(0);
        this.binding.btnPlayLottie.setVisibility(8);
        if (this.binding.btnPlayLottie.isAnimating()) {
            this.binding.btnPlayLottie.cancelAnimation();
        }
        this.binding.tvRecord.setText(getString(R.string.record_2));
    }

    public boolean viewIsDestroyed() {
        return ((BaseActivity) getContext()).isFinishing() || ((BaseActivity) getContext()).isDestroyed();
    }

    private static void showToastMsg(final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ToastUtil.showMsg(str);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorRecordDialog.5
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtil.showMsg(str);
                }
            });
        }
    }
}
