package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.wanos.careditproject.R;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView;
import com.wanos.commonlibrary.base.BaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackClipView extends FrameLayout implements View.OnClickListener {
    private AudioTracksGLView audioTracksView;
    private AudioTrackClipViewListener listener;
    private Point pasteBarPoint;
    private View pcmPasteView;
    private View pcmToolsView;
    private int showToolsType;
    private int toolBarOffH;
    private Point toolBarPoint;

    public interface AudioTrackClipViewListener {
        void showDBUI();

        void showFadeUI();

        void showSpeedUI();
    }

    public AudioTrackClipView(Context context) {
        super(context);
        this.toolBarPoint = new Point(0, 0);
        this.pasteBarPoint = new Point(0, 0);
        this.showToolsType = 0;
        this.toolBarOffH = 0;
        this.toolBarOffH = context.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_h);
    }

    public AudioTrackClipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.toolBarPoint = new Point(0, 0);
        this.pasteBarPoint = new Point(0, 0);
        this.showToolsType = 0;
        this.toolBarOffH = 0;
        this.toolBarOffH = context.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_h);
    }

    public void init(AudioTracksGLView audioTracksGLView, AudioTrackClipViewListener audioTrackClipViewListener) {
        this.audioTracksView = audioTracksGLView;
        this.listener = audioTrackClipViewListener;
        addPcmToolBar();
        hidePcmToolBar();
        this.audioTracksView.setPcmWaveViewListener(new AudioTracksGLView.PcmWaveViewListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackClipView.1
            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.PcmWaveViewListener
            public void showPcmWaveViewTools(int i, int i2, int i3, boolean z) {
                int dimensionPixelSize;
                AudioTrackClipView.this.showToolsType = i;
                int i4 = i3 - AudioTrackClipView.this.toolBarOffH;
                int width = 5;
                if (i4 < 5) {
                    i4 = 5;
                }
                if (i == 0) {
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_db).setVisibility(0);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_fade).setVisibility(0);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_replace).setVisibility(0);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_speed).setVisibility(8);
                    if (z) {
                        dimensionPixelSize = ((AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_btn_w) + (AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_padding_h) * 2)) * 6) + 0;
                        AudioTrackClipView.this.showSplitBtn();
                    } else {
                        dimensionPixelSize = ((AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_btn_w) + (AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_padding_h) * 2)) * 5) + 0;
                        AudioTrackClipView.this.hideSplitBtn();
                    }
                } else {
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_del).setVisibility(0);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_copy).setVisibility(0);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_split).setVisibility(8);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_db).setVisibility(8);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_fade).setVisibility(8);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_replace).setVisibility(8);
                    AudioTrackClipView.this.pcmToolsView.findViewById(R.id.btn_speed).setVisibility(8);
                    dimensionPixelSize = ((AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_btn_w) + (AudioTrackClipView.this.getResources().getDimensionPixelSize(R.dimen.edit_wav_tools_padding_h) * 2)) * 2) + 0;
                }
                int i5 = i2 - (dimensionPixelSize / 2);
                if (i5 >= 5) {
                    width = i5 > (AudioTrackClipView.this.getWidth() - dimensionPixelSize) + (-25) ? (AudioTrackClipView.this.getWidth() - dimensionPixelSize) - 25 : i5;
                }
                AudioTrackClipView.this.showPcmToolBar(i, width, i4);
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.PcmWaveViewListener
            public void hidePcmWaveViewTools() {
                AudioTrackClipView.this.hidePcmToolBar();
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.PcmWaveViewListener
            public void showPasteView(int i, int i2) {
                EditingUtils.log("showPasteView");
                AudioTrackClipView.this.showPasteBar(i, i2);
            }

            @Override // com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.PcmWaveViewListener
            public void hidePasteView() {
                AudioTrackClipView.this.hidePasteBar();
            }
        });
    }

    private void addPcmToolBar() {
        View viewFindViewById = findViewById(R.id.pop_pcm_toolbar);
        this.pcmToolsView = viewFindViewById;
        viewFindViewById.findViewById(R.id.btn_del).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_split).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_copy).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_db).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_fade).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_replace).setOnClickListener(this);
        this.pcmToolsView.findViewById(R.id.btn_speed).setOnClickListener(this);
        View viewFindViewById2 = findViewById(R.id.pop_paste);
        this.pcmPasteView = viewFindViewById2;
        viewFindViewById2.findViewById(R.id.btn_paste).setOnClickListener(this);
    }

    public void showPcmToolBar(int i, int i2, int i3) {
        this.pcmToolsView.setX(i2);
        if (i == 0) {
            this.pcmToolsView.setY(i3);
        } else {
            this.pcmToolsView.setY(i3);
        }
        this.pcmToolsView.setVisibility(0);
    }

    public void hidePcmToolBar() {
        this.pcmToolsView.setVisibility(4);
    }

    public void showSplitBtn() {
        this.pcmToolsView.findViewById(R.id.btn_split).setVisibility(0);
    }

    public void hideSplitBtn() {
        this.pcmToolsView.findViewById(R.id.btn_split).setVisibility(8);
    }

    public void showPasteBar(int i, int i2) {
        this.pasteBarPoint.x = i;
        this.pasteBarPoint.y = i2;
        if (this.pasteBarPoint.y < 2) {
            this.pasteBarPoint.y = 2;
        }
        this.pcmPasteView.setVisibility(0);
        this.pcmPasteView.setX(this.pasteBarPoint.x);
        this.pcmPasteView.setY(this.pasteBarPoint.y);
    }

    public void hidePasteBar() {
        this.pcmPasteView.setVisibility(8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AudioTrackClipViewListener audioTrackClipViewListener;
        String curSelectPcmWaveId = EditingParams.getInstance().getCurSelectPcmWaveId();
        int id = view.getId();
        if (id == R.id.btn_del) {
            EditingUtils.log("addPcmToolBar btn_del curSelectPcmWaveId = " + curSelectPcmWaveId);
            if (this.showToolsType == 0) {
                DataHelpAudioTrack.deleteClip(curSelectPcmWaveId);
            } else {
                DataHelpAudioTrack.deleteBallSample(curSelectPcmWaveId);
            }
            this.audioTracksView.update();
        } else if (id == R.id.btn_split) {
            this.audioTracksView.splitPcmWaveView();
        } else {
            if (id == R.id.btn_copy) {
                EditingParams.getInstance().setCopyClipOrBallId(curSelectPcmWaveId, this.showToolsType == 0);
            } else if (id == R.id.btn_db) {
                AudioTrackClipViewListener audioTrackClipViewListener2 = this.listener;
                if (audioTrackClipViewListener2 != null) {
                    audioTrackClipViewListener2.showDBUI();
                }
            } else if (id == R.id.btn_fade) {
                AudioTrackClipViewListener audioTrackClipViewListener3 = this.listener;
                if (audioTrackClipViewListener3 != null) {
                    audioTrackClipViewListener3.showFadeUI();
                }
            } else if (id == R.id.btn_paste) {
                this.audioTracksView.copyPcmWaveView();
            } else if (id == R.id.btn_replace) {
                Intent intent = new Intent(getContext(), (Class<?>) MaterialListActivity.class);
                intent.putExtra(MaterialListActivity.pageType, 1);
                intent.putExtra(MaterialListActivity.KeyReplaceClipId, curSelectPcmWaveId);
                ((BaseActivity) getContext()).startActivity(intent, true);
            } else if (id == R.id.btn_speed && (audioTrackClipViewListener = this.listener) != null) {
                audioTrackClipViewListener.showSpeedUI();
            }
        }
        hidePcmToolBar();
    }
}
