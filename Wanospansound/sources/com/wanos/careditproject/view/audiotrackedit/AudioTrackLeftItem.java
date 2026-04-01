package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.LayoutAudioTrackLeftItemBinding;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.DebounceClick;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.cmd.CmdSetTrackDB;
import com.wanos.careditproject.view.dialog.TrackNameEditDialog;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import com.wanos.commonlibrary.event.EditProjectEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackLeftItem extends LinearLayout {
    LayoutAudioTrackLeftItemBinding binding;
    private int dbValue;
    public int index;
    private String[] instrumentArr;
    private List<String> listTools;
    private String[] listToolsStr;
    private AudioTrackLeftItemListener listener;
    TrackItemModel trackItemData;

    public interface AudioTrackLeftItemListener {
        void showObjPostionUI(int i);

        void update(boolean z);
    }

    public AudioTrackLeftItem(Context context) {
        super(context);
        this.index = 0;
        this.listToolsStr = new String[]{"上移", "下移", "重命名", "复制", "删除"};
        this.dbValue = 0;
        init(context);
    }

    public AudioTrackLeftItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.index = 0;
        this.listToolsStr = new String[]{"上移", "下移", "重命名", "复制", "删除"};
        this.dbValue = 0;
        init(context);
    }

    private void init(Context context) {
        this.listTools = new ArrayList();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.instrumentArr = context.getResources().getStringArray(R.array.instruments);
        LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBindingInflate = LayoutAudioTrackLeftItemBinding.inflate(layoutInflaterFrom, this, true);
        this.binding = layoutAudioTrackLeftItemBindingInflate;
        layoutAudioTrackLeftItemBindingInflate.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.binding.llMore.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecordUtils.getInstance().isRecording()) {
                    return;
                }
                EventBus.getDefault().post(new EditProjectEvent(EditProjectEvent.ProjectEventType.HideDialog));
                AudioTrackLeftItem.this.showPopWin();
            }
        });
        this.binding.btnM.setOnClickListener(new DebounceClick(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioTrackLeftItem.this.setMEnable(DataHelpAudioTrack.setTrackM(AudioTrackLeftItem.this.index, true));
                AudioTrackLeftItem.this.listener.update(false);
            }
        }, 300L));
        this.binding.btnS.setOnClickListener(new DebounceClick(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioTrackLeftItem.this.setSEnable(DataHelpAudioTrack.setTrackS(AudioTrackLeftItem.this.index, true));
                AudioTrackLeftItem.this.listener.update(false);
            }
        }, 300L));
        this.binding.llShowPos.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecordUtils.getInstance().isRecording() || AudioTrackLeftItem.this.listener == null) {
                    return;
                }
                EditingParams.getInstance().setSelectTrackIndexForBall(AudioTrackLeftItem.this.index);
                AudioTrackLeftItem.this.listener.showObjPostionUI(AudioTrackLeftItem.this.index);
            }
        });
        this.binding.seekBarGain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                AudioTrackLeftItem.this.dbValue = i;
                DataHelpAudioTrack.setTrackDB(AudioTrackLeftItem.this.index, AudioTrackLeftItem.this.dbValue);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                new CmdSetTrackDB().saveOld(AudioTrackLeftItem.this.index);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                CmdSetTrackDB.getCurrentCmd().saveNew();
            }
        });
    }

    public void setListener(AudioTrackLeftItemListener audioTrackLeftItemListener) {
        this.listener = audioTrackLeftItemListener;
    }

    public void setIndex(int i) {
        this.index = i;
        update();
    }

    public void update() {
        TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(this.index);
        this.trackItemData = trackByIndex;
        if (trackByIndex != null) {
            int color_index = trackByIndex.getColor_index() % EditingUtils.trackColorList.length;
            if (color_index == -1) {
                color_index = 0;
            }
            boolean zTrackIsPlay = DataHelpAudioTrack.trackIsPlay(this.index);
            this.binding.btnM.setBackground(getContext().getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            this.binding.btnS.setBackground(getContext().getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            this.binding.llNameAndGain.setBackground(getContext().getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            this.binding.llMore.setBackground(getContext().getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            this.binding.llShowPos.setBackground(getContext().getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            setMEnable(this.trackItemData.getIsMute());
            setSEnable(this.trackItemData.getIsSolo());
            this.binding.seekBarGain.setProgress(this.trackItemData.getDB());
        }
        this.listTools.clear();
        if (this.index != 0) {
            this.listTools.add(this.listToolsStr[0]);
        }
        if (this.index != DataHelpAudioTrack.getTrackList().size() - 1) {
            this.listTools.add(this.listToolsStr[1]);
        }
        int i = 2;
        while (true) {
            String[] strArr = this.listToolsStr;
            if (i >= strArr.length) {
                return;
            }
            this.listTools.add(strArr[i]);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMEnable(boolean z) {
        if (z) {
            this.binding.btnM.setForeground(getResources().getDrawable(R.color.sm_foreground_select, null));
        } else {
            this.binding.btnM.setForeground(getResources().getDrawable(R.color.sm_foreground_normal, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSEnable(boolean z) {
        if (z) {
            this.binding.btnS.setForeground(getResources().getDrawable(R.color.sm_foreground_select, null));
        } else {
            this.binding.btnS.setForeground(getResources().getDrawable(R.color.sm_foreground_normal, null));
        }
    }

    protected void showPopWin() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.pop_audio_track_edit, (ViewGroup) null);
        int[] iArr = new int[2];
        this.binding.ivMore.getLocationInWindow(iArr);
        final PopupWindow popupWindow = new PopupWindow(viewInflate);
        popupWindow.setWidth(EditingUtils.dp2px(getContext(), 200.0f));
        popupWindow.setHeight(-2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(this.binding.ivMore, 0, iArr[0] + this.binding.ivMore.getWidth(), iArr[1] - (popupWindow.getHeight() / 5));
        ListView listView = (ListView) viewInflate.findViewById(R.id.list_view);
        listView.setAdapter((ListAdapter) new ArrayAdapter(getContext(), R.layout.pop_item_text, R.id.tv_item, this.listTools));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String str = (String) AudioTrackLeftItem.this.listTools.get(i);
                EditingUtils.log("onItemClick: " + str);
                if (!str.equals(AudioTrackLeftItem.this.listToolsStr[0])) {
                    if (!str.equals(AudioTrackLeftItem.this.listToolsStr[1])) {
                        if (!str.equals(AudioTrackLeftItem.this.listToolsStr[2])) {
                            if (!str.equals(AudioTrackLeftItem.this.listToolsStr[3])) {
                                if (str.equals(AudioTrackLeftItem.this.listToolsStr[4])) {
                                    DataHelpAudioTrack.deleteTrack(AudioTrackLeftItem.this.index);
                                    WaveUIGLRender.sDeleteTrackIndex(AudioTrackLeftItem.this.index);
                                    AudioTrackLeftItem.this.listener.update(true);
                                }
                            } else if (DataHelpAudioTrack.copyTrack(AudioTrackLeftItem.this.index)) {
                                AudioTrackLeftItem.this.listener.update(true);
                            } else {
                                EditingUtils.showTips("复制音轨失败！");
                            }
                        } else {
                            AudioTrackLeftItem audioTrackLeftItem = AudioTrackLeftItem.this;
                            audioTrackLeftItem.rename(audioTrackLeftItem.index, AudioTrackLeftItem.this.trackItemData.getName());
                        }
                    } else {
                        DataHelpAudioTrack.setTrackDown(AudioTrackLeftItem.this.index);
                        AudioTrackLeftItem.this.listener.update(false);
                    }
                } else {
                    DataHelpAudioTrack.setTrackUp(AudioTrackLeftItem.this.index);
                    AudioTrackLeftItem.this.listener.update(false);
                }
                popupWindow.dismiss();
            }
        });
    }

    public void rename(final int i, String str) {
        new TrackNameEditDialog(getContext(), str, new TrackNameEditDialog.TrackNameChangeListener() { // from class: com.wanos.careditproject.view.audiotrackedit.AudioTrackLeftItem.8
            @Override // com.wanos.careditproject.view.dialog.TrackNameEditDialog.TrackNameChangeListener
            public void onChange(String str2) {
                DataHelpAudioTrack.renameTrackName(i, str2, true);
                AudioTrackLeftItem.this.listener.update(false);
            }
        }).show();
    }
}
