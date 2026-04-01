package com.wanos.careditproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.LayoutAudioTrackLeftItemBinding;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;
import com.wanos.careditproject.utils.RecordUtils;
import com.wanos.careditproject.utils.cmd.CmdSetTrackDB;
import com.wanos.careditproject.view.dialog.TrackNameEditDialog;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.event.EditProjectEvent;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackLeftAdapter extends BaseRecycleAdapter<TrackItemModel, AudioTrackLeftViewHolder> {
    public static final String TAG = "wanos:[AudioTrackLeftAdapter]";
    private static final int type_instrument = 9;
    TextView bubbleText;
    View bubbleView;
    private int bubbleViewHeight;
    private Context context;
    private String[] instrumentArr;
    private ItemTouchHelper itemTouchHelper;
    private LayoutInflater layoutInflater;
    private List<String> listTools;
    private String[] listToolsStr;
    private AudioTrackLeftAdapterListener listener;
    PopupWindow popupWindow;

    public interface AudioTrackLeftAdapterListener {
        void onItemClicked(int i, TrackItemModel trackItemModel);

        void selectTrackIndex(int i);

        void showObjPostionUI(int i);

        void update(boolean z);
    }

    public class AudioTrackLeftViewHolder extends RecyclerView.ViewHolder {
        private LayoutAudioTrackLeftItemBinding binding;
        boolean isItem;
        private String trackId;

        public AudioTrackLeftViewHolder(LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding) {
            super(layoutAudioTrackLeftItemBinding.getRoot());
            this.isItem = true;
            this.binding = layoutAudioTrackLeftItemBinding;
            AudioTrackLeftAdapter.this.instrumentArr = layoutAudioTrackLeftItemBinding.getRoot().getResources().getStringArray(R.array.instruments);
            layoutAudioTrackLeftItemBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.AudioTrackLeftViewHolder.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    EditingUtils.log("AudioTrackLeftAdapter onLongClick : " + RecordUtils.getInstance().isRecording());
                    if (RecordUtils.getInstance().isRecording() || PlayerUtils.isPlaying()) {
                        return false;
                    }
                    if (AudioTrackLeftAdapter.this.itemTouchHelper == null) {
                        return true;
                    }
                    AudioTrackLeftAdapter.this.itemTouchHelper.startDrag(AudioTrackLeftViewHolder.this);
                    return true;
                }
            });
        }

        public AudioTrackLeftViewHolder(View view) {
            super(view);
            this.binding = null;
            this.isItem = false;
        }

        public void setSelected(boolean z) {
            if (this.isItem) {
                if (z) {
                    this.binding.cardView.setForeground(AudioTrackLeftAdapter.this.context.getDrawable(R.drawable.bg_card_left_bg_selected));
                } else {
                    this.binding.cardView.setForeground(null);
                }
            }
        }

        public void selectTrack() {
            int iIndexOf = DataHelpAudioTrack.getTrackIdList().indexOf(this.trackId);
            if (iIndexOf >= 0) {
                WaveUIGLRender.sSelectTrack(iIndexOf);
            }
        }

        public String getTrackId() {
            return this.trackId;
        }

        public void setTrackId(String str) {
            this.trackId = str;
        }
    }

    public AudioTrackLeftAdapter(Context context, List<TrackItemModel> list, AudioTrackLeftAdapterListener audioTrackLeftAdapterListener) {
        super(list);
        this.listTools = new ArrayList();
        this.listToolsStr = new String[]{"上移", "下移", "重命名", "复制", "删除"};
        this.context = context;
        this.listener = audioTrackLeftAdapterListener;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_bubble, (ViewGroup) null);
        this.bubbleView = viewInflate;
        viewInflate.measure(0, 0);
        this.bubbleViewHeight = this.bubbleView.getMeasuredHeight();
        this.bubbleText = (TextView) this.bubbleView.findViewById(R.id.bubble_text);
        this.popupWindow = new PopupWindow(this.bubbleView, -2, -2, false);
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public AudioTrackLeftViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4371) {
            return new AudioTrackLeftViewHolder(this.mFooterView);
        }
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new AudioTrackLeftViewHolder(LayoutAudioTrackLeftItemBinding.inflate(this.layoutInflater, viewGroup, false));
    }

    public void attachItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(AudioTrackLeftViewHolder audioTrackLeftViewHolder, int i) {
        TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(i);
        if (trackByIndex != null) {
            audioTrackLeftViewHolder.setTrackId(trackByIndex.getID() + "");
            LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding = audioTrackLeftViewHolder.binding;
            int color_index = trackByIndex.getColor_index() % EditingUtils.trackColorList.length;
            if (color_index == -1) {
                color_index = 0;
            }
            boolean zTrackIsPlay = DataHelpAudioTrack.trackIsPlay(i);
            layoutAudioTrackLeftItemBinding.btnM.setBackground(this.context.getDrawable(trackByIndex.getIsMute() ? EditingUtils.trackDrawableGray : R.drawable.bg_color_normal));
            layoutAudioTrackLeftItemBinding.btnS.setBackground(this.context.getDrawable(trackByIndex.getIsSolo() ? EditingUtils.trackDrawableList[color_index] : R.drawable.bg_color_normal));
            layoutAudioTrackLeftItemBinding.llNameAndGain.setBackground(this.context.getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            layoutAudioTrackLeftItemBinding.llMore.setBackground(this.context.getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            layoutAudioTrackLeftItemBinding.llShowPos.setBackground(this.context.getDrawable(zTrackIsPlay ? EditingUtils.trackDrawableList[color_index] : EditingUtils.trackDrawableGray));
            int i2 = R.drawable.audio_track;
            layoutAudioTrackLeftItemBinding.tvName.setText(trackByIndex.getName());
            layoutAudioTrackLeftItemBinding.seekBarGain.setProgress(trackByIndex.getDB());
            initListener(layoutAudioTrackLeftItemBinding, i);
            updateBall(layoutAudioTrackLeftItemBinding, i);
        }
    }

    public void updateBall(LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding, int i) {
        List<Float> ballPos = DataHelpAudioTrack.getBallPos(i, EditingParams.getInstance().getProgressSampleNum());
        if (ballPos == null || ballPos.size() < 3) {
            ballPos = DataHelpAudioTrack.getDefaultBallPos();
        }
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.edit_left_pos_w);
        int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen.edit_left_pos_h);
        float dimensionPixelSize3 = this.context.getResources().getDimensionPixelSize(R.dimen.edit_left_ball_size) / 2;
        float fFloatValue = (dimensionPixelSize * ((ballPos.get(0).floatValue() + 1.0f) / 2.0f)) - dimensionPixelSize3;
        float fFloatValue2 = (dimensionPixelSize2 * (1.0f - ((ballPos.get(1).floatValue() + 1.0f) / 2.0f))) - dimensionPixelSize3;
        layoutAudioTrackLeftItemBinding.ivShowPosIcon.setTranslationX(fFloatValue);
        layoutAudioTrackLeftItemBinding.ivShowPosIcon.setTranslationY(fFloatValue2);
    }

    private void initListener(final LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding, final int i) {
        layoutAudioTrackLeftItemBinding.llMore.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerUtils.isPlaying() || RecordUtils.getInstance().isRecording()) {
                    return;
                }
                EventBus.getDefault().post(new EditProjectEvent(EditProjectEvent.ProjectEventType.HideDialog));
                AudioTrackLeftAdapter.this.showPopWin(layoutAudioTrackLeftItemBinding, i);
            }
        });
        layoutAudioTrackLeftItemBinding.btnM.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecordUtils.getInstance().isRecording()) {
                    return;
                }
                DataHelpAudioTrack.setTrackM(i, true);
                AudioTrackLeftAdapter.this.notifyDataSetChanged();
                AudioTrackLeftAdapter.this.listener.update(false);
                WaveUIGLRender.sChangeTrackSM();
            }
        });
        layoutAudioTrackLeftItemBinding.btnS.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecordUtils.getInstance().isRecording()) {
                    return;
                }
                DataHelpAudioTrack.setTrackS(i, true);
                AudioTrackLeftAdapter.this.notifyDataSetChanged();
                AudioTrackLeftAdapter.this.listener.update(false);
                WaveUIGLRender.sChangeTrackSM();
            }
        });
        layoutAudioTrackLeftItemBinding.llShowPos.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecordUtils.getInstance().isRecording() || AudioTrackLeftAdapter.this.listener == null) {
                    return;
                }
                EditingParams.getInstance().setSelectTrackIndexForBall(i);
                AudioTrackLeftAdapter.this.listener.showObjPostionUI(i);
            }
        });
        layoutAudioTrackLeftItemBinding.llNameAndGain.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AudioTrackLeftAdapter.this.listener != null) {
                    AudioTrackLeftAdapterListener audioTrackLeftAdapterListener = AudioTrackLeftAdapter.this.listener;
                    int i2 = i;
                    audioTrackLeftAdapterListener.onItemClicked(i2, DataHelpAudioTrack.getTrackByIndex(i2));
                }
            }
        });
        layoutAudioTrackLeftItemBinding.seekBarGain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    DataHelpAudioTrack.setTrackDB(i, i2);
                    AudioTrackLeftAdapter.this.bubbleText.setText("音量: " + i2 + "dB");
                    if (!AudioTrackLeftAdapter.this.popupWindow.isShowing()) {
                        AudioTrackLeftAdapter.this.popupWindow.showAsDropDown(seekBar, 0, -seekBar.getHeight());
                    }
                    AudioTrackLeftAdapter.this.popupWindow.update(seekBar, seekBar.getWidth() + UIUtil.dip2px(AudioTrackLeftAdapter.this.context, 6.0d), (int) ((AudioTrackLeftAdapter.this.bubbleViewHeight + layoutAudioTrackLeftItemBinding.seekBarGain.getMeasuredHeight()) * (-0.5f)), -1, -1);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                new CmdSetTrackDB().saveOld(i);
                if (AudioTrackLeftAdapter.this.popupWindow.isShowing()) {
                    return;
                }
                AudioTrackLeftAdapter.this.popupWindow.showAsDropDown(seekBar, 0, (-seekBar.getHeight()) - AudioTrackLeftAdapter.this.bubbleView.getHeight());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                CmdSetTrackDB.getCurrentCmd().saveNew();
                if (AudioTrackLeftAdapter.this.popupWindow.isShowing()) {
                    AudioTrackLeftAdapter.this.popupWindow.dismiss();
                }
            }
        });
    }

    private void setMEnable(LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding, boolean z) {
        if (z) {
            layoutAudioTrackLeftItemBinding.btnM.setBackground(this.context.getResources().getDrawable(EditingUtils.trackDrawableGray));
        } else {
            layoutAudioTrackLeftItemBinding.btnM.setBackground(this.context.getResources().getDrawable(R.drawable.bg_color_normal));
        }
    }

    private void setSEnable(LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding, boolean z) {
        if (z) {
            layoutAudioTrackLeftItemBinding.btnS.setBackground(this.context.getResources().getDrawable(EditingUtils.trackDrawableGray));
        } else {
            layoutAudioTrackLeftItemBinding.btnS.setBackground(this.context.getResources().getDrawable(R.drawable.bg_color_normal));
        }
    }

    protected void showPopWin(LayoutAudioTrackLeftItemBinding layoutAudioTrackLeftItemBinding, final int i) {
        this.listTools.clear();
        if (i != 0) {
            this.listTools.add(this.listToolsStr[0]);
        }
        if (i != DataHelpAudioTrack.getTrackList().size() - 1) {
            this.listTools.add(this.listToolsStr[1]);
        }
        int i2 = 2;
        while (true) {
            String[] strArr = this.listToolsStr;
            if (i2 < strArr.length) {
                this.listTools.add(strArr[i2]);
                i2++;
            } else {
                View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.pop_audio_track_edit, (ViewGroup) null);
                int[] iArr = new int[2];
                layoutAudioTrackLeftItemBinding.ivMore.getLocationInWindow(iArr);
                final PopupWindow popupWindow = new PopupWindow(viewInflate);
                popupWindow.setWidth(EditingUtils.dp2px(this.context, 200.0f));
                popupWindow.setHeight(-2);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(layoutAudioTrackLeftItemBinding.ivMore, 0, iArr[0] + layoutAudioTrackLeftItemBinding.ivMore.getWidth(), iArr[1] - (popupWindow.getHeight() / 5));
                ListView listView = (ListView) viewInflate.findViewById(R.id.list_view);
                listView.setAdapter((ListAdapter) new ArrayAdapter(this.context, R.layout.pop_item_text, R.id.tv_item, this.listTools));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.7
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j) {
                        String str = (String) AudioTrackLeftAdapter.this.listTools.get(i3);
                        EditingUtils.log("onItemClick: " + str + ", pos =" + i3);
                        if (!str.equals(AudioTrackLeftAdapter.this.listToolsStr[0])) {
                            if (!str.equals(AudioTrackLeftAdapter.this.listToolsStr[1])) {
                                if (!str.equals(AudioTrackLeftAdapter.this.listToolsStr[2])) {
                                    if (!str.equals(AudioTrackLeftAdapter.this.listToolsStr[3])) {
                                        if (str.equals(AudioTrackLeftAdapter.this.listToolsStr[4])) {
                                            DataHelpAudioTrack.deleteTrack(i);
                                            WaveUIGLRender.sDeleteTrackIndex(i);
                                            int selectTrackIndex2 = EditingParams.getInstance().getSelectTrackIndex2();
                                            int i4 = i;
                                            if (i4 < selectTrackIndex2) {
                                                WaveUIGLRender.sSelectTrack(selectTrackIndex2 - 1);
                                            } else if (i4 == selectTrackIndex2) {
                                                WaveUIGLRender.sSelectTrack(-1);
                                            }
                                            AudioTrackLeftAdapter.this.listener.update(true);
                                            AudioTrackLeftAdapter.this.notifyDataSetChanged();
                                        }
                                    } else if (DataHelpAudioTrack.copyTrack(i)) {
                                        AudioTrackLeftAdapter.this.listener.update(true);
                                        AudioTrackLeftAdapter.this.notifyDataSetChanged();
                                    } else {
                                        EditingUtils.showTips("复制音轨失败！");
                                    }
                                } else {
                                    TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(i);
                                    if (trackByIndex != null) {
                                        AudioTrackLeftAdapter.this.rename(i, trackByIndex.getName());
                                    } else {
                                        AudioTrackLeftAdapter.this.rename(i, "");
                                    }
                                }
                            } else {
                                if (DataHelpAudioTrack.setTrackDown(i)) {
                                    WaveUIGLRender.sSelectTrack(i + 1);
                                }
                                AudioTrackLeftAdapter.this.listener.update(false);
                                AudioTrackLeftAdapter.this.notifyDataSetChanged();
                            }
                        } else {
                            if (DataHelpAudioTrack.setTrackUp(i)) {
                                WaveUIGLRender.sSelectTrack(i - 1);
                            }
                            AudioTrackLeftAdapter.this.listener.update(false);
                            AudioTrackLeftAdapter.this.notifyDataSetChanged();
                        }
                        popupWindow.dismiss();
                    }
                });
                return;
            }
        }
    }

    public void rename(final int i, String str) {
        new TrackNameEditDialog(this.context, str, new TrackNameEditDialog.TrackNameChangeListener() { // from class: com.wanos.careditproject.adapter.AudioTrackLeftAdapter.8
            @Override // com.wanos.careditproject.view.dialog.TrackNameEditDialog.TrackNameChangeListener
            public void onChange(String str2) {
                DataHelpAudioTrack.renameTrackName(i, str2, true);
                AudioTrackLeftAdapter.this.notifyDataSetChanged();
                AudioTrackLeftAdapter.this.listener.update(false);
            }
        }).show();
    }

    public void updateTrackView() {
        AudioTrackLeftAdapterListener audioTrackLeftAdapterListener = this.listener;
        if (audioTrackLeftAdapterListener != null) {
            audioTrackLeftAdapterListener.update(true);
        }
    }

    public void selectTrackIndex(int i) {
        if (this.listener != null) {
            EditingUtils.log("selectTrackIndex index = " + i);
            this.listener.selectTrackIndex(i);
        }
    }
}
