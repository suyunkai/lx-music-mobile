package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomObjPositionBinding;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.opengl.BaseObjPosGLView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorObjPosDialog extends Fragment implements View.OnClickListener {
    private PopBottomObjPositionBinding binding;
    private ObjPosDialogListener listener;
    private RedrawList redrawList;
    private boolean ballPosThreadIsRunning = true;
    private boolean ballIsTouch = false;

    public interface ObjPosDialogListener {
        void onHide();

        void toPlay();

        void updateRecordIcon();

        void updateUI();
    }

    public static class RedrawList {
        public List<String> list;
        public int trackIndex;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = PopBottomObjPositionBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        show();
        return this.binding.getRoot();
    }

    public void setListener(ObjPosDialogListener objPosDialogListener) {
        this.listener = objPosDialogListener;
    }

    public void show() {
        startBallPosThread();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        EditingUtils.log("CreatorObjPosDialog, onHiddenChanged = " + z);
        if (z) {
            stopBallPosThread();
            this.binding.objLeft.setVisibility(4);
            this.binding.objRight.setVisibility(4);
        } else {
            this.binding.objLeft.setVisibility(0);
            this.binding.objRight.setVisibility(0);
        }
    }

    private void initView() {
        initListener();
    }

    private void initListener() {
        this.binding.btnClose.setOnClickListener(this);
        this.binding.viewRoot.setOnClickListener(this);
        this.binding.viewObjPosTop.setOnClickListener(this);
        this.binding.btnDrawHelp.setOnClickListener(this);
        this.binding.objLeft.setListener(new BaseObjPosGLView.ObjPosGlViewListener() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.1
            @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView.ObjPosGlViewListener
            public void toPlay() {
                if (CreatorObjPosDialog.this.listener != null) {
                    CreatorObjPosDialog.this.listener.toPlay();
                }
            }

            @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView.ObjPosGlViewListener
            public void updateUI() {
                if (CreatorObjPosDialog.this.listener != null) {
                    CreatorObjPosDialog.this.listener.updateUI();
                }
            }
        });
        this.binding.objRight.setListener(new BaseObjPosGLView.ObjPosGlViewListener() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.2
            @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView.ObjPosGlViewListener
            public void toPlay() {
                if (CreatorObjPosDialog.this.listener != null) {
                    CreatorObjPosDialog.this.listener.toPlay();
                }
            }

            @Override // com.wanos.careditproject.view.opengl.BaseObjPosGLView.ObjPosGlViewListener
            public void updateUI() {
                if (CreatorObjPosDialog.this.listener != null) {
                    CreatorObjPosDialog.this.listener.updateUI();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        stopBallPosThread();
        super.onDestroy();
        this.binding.objRight.destroy();
        this.binding.objLeft.destroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close || id == R.id.view_obj_pos_top) {
            ObjPosDialogListener objPosDialogListener = this.listener;
            if (objPosDialogListener != null) {
                objPosDialogListener.onHide();
                return;
            }
            return;
        }
        if (id == R.id.btn_draw_help) {
            new HelpPosDrawDialog().show(getChildFragmentManager(), "HelpPosDrawDialog");
        }
    }

    public void updateChannelData() {
        int selectTrackIndexForBall = EditingParams.getInstance().getSelectTrackIndexForBall();
        if (this.binding.objLeft == null || selectTrackIndexForBall == -1) {
            return;
        }
        boolean channelState = this.binding.objLeft.getChannelState();
        boolean zTrackIsMono = DataHelpAudioTrack.trackIsMono(selectTrackIndexForBall);
        if (channelState != zTrackIsMono) {
            if (zTrackIsMono) {
                this.binding.objLeft.setChannelState(true);
            } else {
                this.binding.objLeft.setChannelState(false);
            }
        }
    }

    protected void startBallPosThread() {
        new Thread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.3
            @Override // java.lang.Runnable
            public void run() {
                CreatorObjPosDialog.this.ballPosThreadIsRunning = true;
                CreatorObjPosDialog.this.redrawList = new RedrawList();
                CreatorObjPosDialog.this.redrawList.trackIndex = -1;
                CreatorObjPosDialog.this.redrawList.list = new ArrayList();
                EditingParams.getInstance().setOperateBallPosTrackIndex(-1);
                final int selectTrackIndexForBall = EditingParams.getInstance().getSelectTrackIndexForBall();
                if (selectTrackIndexForBall != CreatorObjPosDialog.this.redrawList.trackIndex) {
                    CreatorObjPosDialog.this.redrawList.trackIndex = selectTrackIndexForBall;
                    CreatorObjPosDialog.this.redrawList.list.clear();
                    if (CreatorObjPosDialog.this.getActivity() != null && CreatorObjPosDialog.this.isAdded()) {
                        CreatorObjPosDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (CreatorObjPosDialog.this.getActivity() != null) {
                                    if (DataHelpAudioTrack.trackIsMono(selectTrackIndexForBall)) {
                                        CreatorObjPosDialog.this.binding.objLeft.setChannelState(true);
                                    } else {
                                        CreatorObjPosDialog.this.binding.objLeft.setChannelState(false);
                                    }
                                }
                            }
                        });
                    }
                }
                CreatorObjPosDialog.this.updatePosUI(selectTrackIndexForBall, EditingParams.getInstance().getProgressSampleNum());
                while (CreatorObjPosDialog.this.ballPosThreadIsRunning) {
                    if (!CreatorObjPosDialog.this.binding.objLeft.isTouching()) {
                        if (CreatorObjPosDialog.this.binding.objRight.isTouching()) {
                            CreatorObjPosDialog.this.updatePosUINew(selectTrackIndexForBall, 2);
                        } else {
                            CreatorObjPosDialog.this.updatePosUINew(selectTrackIndexForBall, 0);
                        }
                    } else {
                        CreatorObjPosDialog.this.updatePosUINew(selectTrackIndexForBall, 1);
                    }
                    try {
                        Thread.sleep(60L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                EditingParams.getInstance().setOperateBallPosTrackIndex(-1);
            }
        }).start();
    }

    public List<Float> updatePosUI(int i, long j) {
        final List<Float> ballPos = DataHelpAudioTrack.getBallPos(i, j);
        if (ballPos == null || ballPos.size() < 3) {
            ballPos = DataHelpAudioTrack.getDefaultBallPos();
        }
        if (getActivity() != null && isAdded()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.4
                @Override // java.lang.Runnable
                public void run() {
                    if (CreatorObjPosDialog.this.getActivity() != null) {
                        CreatorObjPosDialog.this.binding.objLeft.setPosToUI(((Float) ballPos.get(0)).floatValue(), ((Float) ballPos.get(1)).floatValue(), ((Float) ballPos.get(2)).floatValue());
                        CreatorObjPosDialog.this.binding.objRight.setPosToUI(((Float) ballPos.get(0)).floatValue(), ((Float) ballPos.get(1)).floatValue(), ((Float) ballPos.get(2)).floatValue());
                    }
                }
            });
        }
        return ballPos;
    }

    public List<Float> updatePosUINew(int i, final int i2) {
        final List<Float> ballPos = DataHelpAudioTrack.getBallPos(i, EditingParams.getInstance().getProgressSampleNum());
        if (ballPos == null || ballPos.size() < 3) {
            ballPos = DataHelpAudioTrack.getDefaultBallPos();
        }
        if (getActivity() != null && isAdded()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.view.dialog.CreatorObjPosDialog.5
                @Override // java.lang.Runnable
                public void run() {
                    if (CreatorObjPosDialog.this.getActivity() != null) {
                        if (i2 != 1) {
                            CreatorObjPosDialog.this.binding.objLeft.setPosToUI(((Float) ballPos.get(0)).floatValue(), ((Float) ballPos.get(1)).floatValue(), ((Float) ballPos.get(2)).floatValue());
                        }
                        if (i2 != 2) {
                            CreatorObjPosDialog.this.binding.objRight.setPosToUI(((Float) ballPos.get(0)).floatValue(), ((Float) ballPos.get(1)).floatValue(), ((Float) ballPos.get(2)).floatValue());
                        }
                    }
                }
            });
        }
        return ballPos;
    }

    protected void stopBallPosThread() {
        this.ballPosThreadIsRunning = false;
    }
}
