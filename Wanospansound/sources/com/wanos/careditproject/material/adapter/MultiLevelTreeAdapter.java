package com.wanos.careditproject.material.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.utils.Consts;
import com.app.hubert.guide.util.LogUtil;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.WanosCommunication.response.GetMaterialCollectResponse;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.CloudInfoResponse;
import com.wanos.careditproject.data.response.ProjectUploadAssetTaskResponse;
import com.wanos.careditproject.databinding.ActivityMaterialListBinding;
import com.wanos.careditproject.material.MaterialListActivity;
import com.wanos.careditproject.material.SearchActivity;
import com.wanos.careditproject.utils.BaiduCloudUtils;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.UploadFileUtils;
import com.wanos.careditproject.utils.anim.ExpansionListUtils;
import com.wanos.careditproject.utils.codec.AudioCodec;
import com.wanos.careditproject.utils.codec.AudioFileInfo;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.util.AnitClick;
import com.wanos.util.NativeMethod;
import cz.msebera.android.httpclient.HttpHost;
import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public class MultiLevelTreeAdapter extends RecyclerView.Adapter {
    private static final int ITEM_TYPE_FILE = 1;
    private static final int ITEM_TYPE_MATERIAL = 2;
    private String TAG;
    private ActivityMaterialListBinding binding;
    private MultiLevelTreeAdapter childrenAdapter;
    private Map<String, MultiLevelTreeAdapter> childrenAdapterList;
    private Context context;
    private MaterialTypeInfoBean currentPlayMaterialDataInfo;
    private int getTaskStateCount;
    private boolean isDecoded;
    private boolean isGetTaskStateRunning;
    private boolean isUploaded;
    ExpansionListUtils.KeepOneH<FileViewHolder> keepOne;
    private LayoutInflater layoutInflater;
    private OnTreeAdapterListener listener;
    private Map<String, Boolean> localPath2IsDecodeMap;
    private Map<String, String> localPath2UrlMap;
    private Map<String, String> localPath2WanosPathMap;
    private int mActivityFlag;
    private List<MaterialTypeInfoBean> materialDataList;
    private List<MaterialTypeInfoBean> parentMaterialDataList;
    private int selectId;
    private MediaPlayerEnum.CallBackState status;

    public interface DecodeAudioListener {
        void callback(boolean z, String str, long j);
    }

    public interface OnTreeAdapterListener {
        default void closeLoadingListener() {
        }

        default void onCollectChange(int i, int i2, MaterialTypeInfoBean materialTypeInfoBean) {
        }

        default void onItemClick(int i, MaterialTypeInfoBean materialTypeInfoBean) {
        }

        default void onUpload(int i, MaterialTypeInfoBean materialTypeInfoBean) {
        }
    }

    public MultiLevelTreeAdapter(Context context, List<MaterialTypeInfoBean> list) {
        this.TAG = "MaterialSecondAdapter";
        this.mActivityFlag = -1;
        this.selectId = -1;
        this.childrenAdapterList = new HashMap();
        this.keepOne = new ExpansionListUtils.KeepOneH<>();
        this.isUploaded = false;
        this.isDecoded = false;
        this.localPath2UrlMap = new ArrayMap();
        this.localPath2WanosPathMap = new ArrayMap();
        this.localPath2IsDecodeMap = new ArrayMap();
        this.isGetTaskStateRunning = false;
        this.getTaskStateCount = 0;
        this.materialDataList = list;
        this.context = context;
    }

    public MultiLevelTreeAdapter(Context context, List<MaterialTypeInfoBean> list, List<MaterialTypeInfoBean> list2, int i, MaterialTypeInfoBean materialTypeInfoBean, MediaPlayerEnum.CallBackState callBackState) {
        this.TAG = "MaterialSecondAdapter";
        this.mActivityFlag = -1;
        this.selectId = -1;
        this.childrenAdapterList = new HashMap();
        this.keepOne = new ExpansionListUtils.KeepOneH<>();
        this.isUploaded = false;
        this.isDecoded = false;
        this.localPath2UrlMap = new ArrayMap();
        this.localPath2WanosPathMap = new ArrayMap();
        this.localPath2IsDecodeMap = new ArrayMap();
        this.isGetTaskStateRunning = false;
        this.getTaskStateCount = 0;
        this.materialDataList = list2;
        this.parentMaterialDataList = list;
        this.selectId = i;
        this.currentPlayMaterialDataInfo = materialTypeInfoBean;
        this.status = callBackState;
        this.context = context;
    }

    public void setOnTreeAdapterListener(OnTreeAdapterListener onTreeAdapterListener) {
        this.listener = onTreeAdapterListener;
    }

    public void setSelectId(int i) {
        this.selectId = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        if (i == 1) {
            return new FileViewHolder(layoutInflaterFrom.inflate(R.layout.material_list_file_item, viewGroup, false));
        }
        return new MaterialViewHolder(layoutInflaterFrom.inflate(R.layout.material_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        final MaterialTypeInfoBean materialTypeInfoBean = this.materialDataList.get(i);
        if (viewHolder instanceof MaterialViewHolder) {
            final MaterialViewHolder materialViewHolder = (MaterialViewHolder) viewHolder;
            String m_name = materialTypeInfoBean.getM_name();
            if (m_name.contains(Consts.DOT)) {
                m_name = m_name.substring(0, m_name.lastIndexOf(Consts.DOT));
            }
            materialViewHolder.musicName.setText(m_name);
            if (materialTypeInfoBean.getTimeLen().equals("")) {
                materialViewHolder.mTotalTime.setText("00:00");
            } else {
                materialViewHolder.mTotalTime.setText(materialTypeInfoBean.getTimeLen());
            }
            materialViewHolder.mSize.setVisibility(this.mActivityFlag == 4 ? 8 : 0);
            if (this.mActivityFlag != 3) {
                materialViewHolder.mSize.setText(calculateSize(materialTypeInfoBean.getUrlWanosSize()));
                if (materialTypeInfoBean.isCollect()) {
                    materialViewHolder.mCollect.setImageResource(this.mActivityFlag == 4 ? R.drawable.ic_upload_finish : R.drawable.ic_collected);
                } else {
                    materialViewHolder.mCollect.setImageResource(this.mActivityFlag == 4 ? R.drawable.ic_upload_unfinish : R.drawable.ic_uncollect);
                }
            } else {
                setTransCodeStatusUI(materialViewHolder, materialTypeInfoBean, materialTypeInfoBean.getTransitionWanosStatus());
                materialViewHolder.mCollect.setVisibility(8);
            }
            materialViewHolder.itemView.setOnClickListener(new AnitClick(500L) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.1
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    if (MultiLevelTreeAdapter.this.isCanPlay(materialTypeInfoBean)) {
                        if (MultiLevelTreeAdapter.this.mActivityFlag != 5 || !(MultiLevelTreeAdapter.this.context instanceof SearchActivity)) {
                            if (MultiLevelTreeAdapter.this.context instanceof MaterialListActivity) {
                                if (MultiLevelTreeAdapter.this.selectId != materialTypeInfoBean.getM_id()) {
                                    ((MaterialListActivity) MultiLevelTreeAdapter.this.context).pause();
                                }
                                if (materialViewHolder.playState == 0) {
                                    ((MaterialListActivity) MultiLevelTreeAdapter.this.context).play(materialTypeInfoBean);
                                } else {
                                    ((MaterialListActivity) MultiLevelTreeAdapter.this.context).pause();
                                }
                            }
                        } else {
                            if (MultiLevelTreeAdapter.this.selectId != materialTypeInfoBean.getM_id()) {
                                ((SearchActivity) MultiLevelTreeAdapter.this.context).pause();
                            }
                            if (materialViewHolder.playState == 0) {
                                ((SearchActivity) MultiLevelTreeAdapter.this.context).play(materialTypeInfoBean);
                            } else {
                                ((SearchActivity) MultiLevelTreeAdapter.this.context).pause();
                            }
                        }
                        MultiLevelTreeAdapter.this.selectId = materialTypeInfoBean.getM_id();
                        MultiLevelTreeAdapter.this.notifyDataSetChanged();
                        return;
                    }
                    if (materialTypeInfoBean.getTransitionWanosStatus() == 1 || materialTypeInfoBean.getTransitionWanosStatus() == 2) {
                        ToastUtil.showMsg("转码中，暂不支持播放预览");
                    } else if (materialTypeInfoBean.getTransitionWanosStatus() == 4) {
                        ToastUtil.showMsg("转码失败，暂不支持播放预览");
                    }
                }
            });
            int i2 = this.selectId;
            if (i2 != -1 && i2 == materialTypeInfoBean.getM_id()) {
                if (!isCanPlay(materialTypeInfoBean)) {
                    return;
                }
                materialViewHolder.viewItem.setBackgroundResource(R.drawable.edit_res_bg_select);
                materialViewHolder.mAdd.setBackgroundResource(R.drawable.ic_material_add_selected);
                if (this.currentPlayMaterialDataInfo != null && (this.status == MediaPlayerEnum.CallBackState.STARTED || this.status == MediaPlayerEnum.CallBackState.PREPARE || this.status == MediaPlayerEnum.CallBackState.PREPARING)) {
                    if (this.status == MediaPlayerEnum.CallBackState.PREPARE || this.status == MediaPlayerEnum.CallBackState.PREPARING) {
                        materialViewHolder.pdPlay.setVisibility(0);
                        materialViewHolder.mPlayState.setVisibility(8);
                        materialViewHolder.mPlayState.pauseAnimation();
                        materialViewHolder.mPlay.setVisibility(8);
                    } else if (this.status == MediaPlayerEnum.CallBackState.STARTED) {
                        materialViewHolder.mPlay.setVisibility(8);
                        materialViewHolder.mPlayState.setVisibility(0);
                        materialViewHolder.mPlayState.resumeAnimation();
                        materialViewHolder.pdPlay.setVisibility(8);
                    } else {
                        materialViewHolder.mPlay.setImageResource(R.drawable.ic_material_pause);
                        materialViewHolder.mPlay.setVisibility(0);
                        materialViewHolder.mPlayState.setVisibility(8);
                        materialViewHolder.mPlayState.pauseAnimation();
                        materialViewHolder.pdPlay.setVisibility(8);
                    }
                    materialViewHolder.playState = 1;
                } else {
                    materialViewHolder.mPlay.setVisibility(0);
                    materialViewHolder.mPlayState.setVisibility(8);
                    materialViewHolder.mPlayState.pauseAnimation();
                    materialViewHolder.pdPlay.setVisibility(8);
                    materialViewHolder.playState = 0;
                }
            } else {
                materialViewHolder.viewItem.setBackgroundResource(R.drawable.edit_res_bg);
                materialViewHolder.mAdd.setBackgroundResource(R.drawable.ic_material_add);
                materialViewHolder.mPlay.setVisibility(0);
                materialViewHolder.mPlayState.setVisibility(8);
                materialViewHolder.mPlayState.pauseAnimation();
                materialViewHolder.pdPlay.setVisibility(8);
                materialViewHolder.playState = 0;
            }
            long j = 500;
            materialViewHolder.mAdd.setOnClickListener(new AnitClick(j) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.2
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    EditingUtils.log("holder.mAdd");
                    if (MultiLevelTreeAdapter.this.isCanPlay(materialTypeInfoBean)) {
                        if (EditingParams.getInstance().getSelectTrackIndex2() >= 0) {
                            MultiLevelTreeAdapter.this.showLoading();
                            if (MultiLevelTreeAdapter.this.getMaterialInfo(materialTypeInfoBean)) {
                                return;
                            }
                            MultiLevelTreeAdapter.this.hideLoading();
                            return;
                        }
                        ToastUtil.showMsg("请选择一个音轨再操作！");
                    }
                }
            });
            materialViewHolder.mCollect.setOnClickListener(new AnitClick(j) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.3
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    MultiLevelTreeAdapter.this.collect(i, materialTypeInfoBean);
                }
            });
            return;
        }
        if (viewHolder instanceof FileViewHolder) {
            final FileViewHolder fileViewHolder = (FileViewHolder) viewHolder;
            initRecycler(fileViewHolder, materialTypeInfoBean);
            fileViewHolder.fileName.setText(materialTypeInfoBean.getName());
            if (materialTypeInfoBean.getViewType() == 1) {
                fileViewHolder.expansionArrows.setImageResource(materialTypeInfoBean.isExpansion() ? com.wanos.media.R.drawable.ic_file_item_down_arrows : com.wanos.media.R.drawable.ic_file_item_right_arrows);
                this.keepOne.toggle(materialTypeInfoBean.isExpansion(), fileViewHolder);
            }
            fileViewHolder.itemView.setOnClickListener(new AnitClick(500L) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.4
                @Override // com.wanos.media.util.AnitClick
                public void onAnitClick(View view) {
                    if (materialTypeInfoBean.getViewType() == 1) {
                        MaterialTypeInfoBean materialTypeInfoBean2 = materialTypeInfoBean;
                        materialTypeInfoBean2.setExpansion(true ^ materialTypeInfoBean2.isExpansion());
                        fileViewHolder.expansionArrows.setImageResource(materialTypeInfoBean.isExpansion() ? com.wanos.media.R.drawable.ic_file_item_down_arrows : com.wanos.media.R.drawable.ic_file_item_right_arrows);
                        if (MultiLevelTreeAdapter.this.listener != null && materialTypeInfoBean.isExpansion()) {
                            fileViewHolder.expansionArrows.setVisibility(8);
                            fileViewHolder.filePb.setVisibility(0);
                            MultiLevelTreeAdapter.this.listener.onItemClick(i, materialTypeInfoBean);
                            return;
                        }
                        MultiLevelTreeAdapter.this.keepOne.toggle(materialTypeInfoBean.isExpansion(), fileViewHolder);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCanPlay(MaterialTypeInfoBean materialTypeInfoBean) {
        return this.mActivityFlag != 3 || materialTypeInfoBean.getTransitionWanosStatus() == 3;
    }

    private void setTransCodeStatusUI(MaterialViewHolder materialViewHolder, MaterialTypeInfoBean materialTypeInfoBean, int i) {
        if (i == 1 || i == 2) {
            materialViewHolder.musicName.setTextColor(this.context.getColor(R.color.material_item_time_text_color));
            materialViewHolder.mPlay.setImageResource(R.drawable.ic_material_pause_trans_code);
            materialViewHolder.mSize.setTextColor(this.context.getColor(R.color.cloud_material_transcoding));
            materialViewHolder.mSize.setText(this.context.getString(R.string.cloud_material_transcoding));
            return;
        }
        if (i == 3) {
            materialViewHolder.mPlay.setImageResource(R.drawable.ic_material_pause);
            materialViewHolder.musicName.setTextColor(this.context.getColor(R.color.material_item_title_text_color));
            materialViewHolder.mSize.setText(calculateSize(materialTypeInfoBean.getUrlSize()));
            materialViewHolder.mSize.setTextColor(this.context.getColor(R.color.material_item_time_text_color));
            return;
        }
        if (i != 4) {
            return;
        }
        materialViewHolder.mSize.setTextColor(this.context.getColor(R.color.cloud_material_transcode_fail));
        materialViewHolder.musicName.setTextColor(this.context.getColor(R.color.material_item_time_text_color));
        materialViewHolder.mPlay.setImageResource(R.drawable.ic_material_pause_trans_code);
        materialViewHolder.mSize.setText(this.context.getString(R.string.cloud_material_transcode_fail));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getMaterialInfo(final MaterialTypeInfoBean materialTypeInfoBean) {
        int i = this.mActivityFlag;
        if (i == 3) {
            CreatorRetrofitUtil.getCloudFileInfo(materialTypeInfoBean.getM_id(), 2, new ResponseCallBack<CloudInfoResponse>(this.context) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(CloudInfoResponse cloudInfoResponse) {
                    if (MultiLevelTreeAdapter.this.isDestoryed()) {
                        return;
                    }
                    if (cloudInfoResponse.data == null || cloudInfoResponse.data.getInfo() == null) {
                        MultiLevelTreeAdapter.this.hideLoading();
                        return;
                    }
                    materialTypeInfoBean.setUrlSrc(cloudInfoResponse.data.getInfo().getUrlSrc());
                    if (MultiLevelTreeAdapter.this.addMaterial(materialTypeInfoBean)) {
                        return;
                    }
                    MultiLevelTreeAdapter.this.hideLoading();
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                    Log.i(MultiLevelTreeAdapter.this.TAG, "素材详情----" + i2 + InternalFrame.ID + str);
                    ToastUtil.showMsg(str);
                    MultiLevelTreeAdapter.this.hideLoading();
                }
            });
            return true;
        }
        if (i == 4) {
            return addMaterial(materialTypeInfoBean);
        }
        CreatorRetrofitUtil.getAssetsInfo(materialTypeInfoBean.getM_id(), 2, new ResponseCallBack<CloudInfoResponse>(this.context) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(CloudInfoResponse cloudInfoResponse) {
                if (MultiLevelTreeAdapter.this.isDestoryed() || cloudInfoResponse.data == null || cloudInfoResponse.data.getInfo() == null) {
                    return;
                }
                materialTypeInfoBean.setUrlSrcWanos(cloudInfoResponse.data.getInfo().getUrlSrcWanos());
                if (MultiLevelTreeAdapter.this.addMaterial(materialTypeInfoBean)) {
                    return;
                }
                MultiLevelTreeAdapter.this.hideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                Log.i(MultiLevelTreeAdapter.this.TAG, "素材详情----" + i2 + InternalFrame.ID + str);
                ToastUtil.showMsg(str);
                MultiLevelTreeAdapter.this.hideLoading();
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        ActivityUtils.finishActivity((Class<? extends Activity>) MaterialListActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addMaterial(MaterialTypeInfoBean materialTypeInfoBean) {
        String urlSrc = this.mActivityFlag == 3 ? materialTypeInfoBean.getUrlSrc() : materialTypeInfoBean.getUrlSrcWanos();
        String strEncode = Uri.encode(urlSrc, "-![.:/,%?&=]");
        String strRemoveExtension = EditingUtils.removeExtension(materialTypeInfoBean.getM_name());
        EditingUtils.log("holder.mAdd url = " + urlSrc);
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        int selectTrackIndex2 = EditingParams.getInstance().getSelectTrackIndex2();
        if (urlSrc.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            AudioFileInfo.DecodeAudioInfo decodeAudioInfo = AudioFileInfo.getInstance().get(strEncode);
            if (decodeAudioInfo != null) {
                String replaceClipId = EditingParams.getInstance().getReplaceClipId();
                if (replaceClipId == null || replaceClipId.equals("")) {
                    DataHelpAudioTrack.DataHelpResult dataHelpResultAddTrackWithUrl = DataHelpAudioTrack.addTrackWithUrl(strEncode, materialTypeInfoBean.getFormat(), decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, selectTrackIndex2, progressSampleNum, strRemoveExtension);
                    if (dataHelpResultAddTrackWithUrl.isSuccess()) {
                        showToastMsg(this.context.getString(R.string.material_add_material), true);
                    } else {
                        showToastMsg(dataHelpResultAddTrackWithUrl.getErrMsg());
                    }
                } else {
                    DataHelpAudioTrack.DataHelpResult dataHelpResultReplaceClip = DataHelpAudioTrack.replaceClip(replaceClipId, strEncode, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, materialTypeInfoBean.getFormat(), strRemoveExtension);
                    if (dataHelpResultReplaceClip.isSuccess()) {
                        showToastMsg("替换成功！", true);
                    } else {
                        showToastMsg(dataHelpResultReplaceClip.getErrMsg());
                    }
                }
            } else {
                if (DownloadUtils.getInstance().checkAdd(materialTypeInfoBean.getUrlWanosSize())) {
                    return addResToProject(strEncode, materialTypeInfoBean.getFormat(), strRemoveExtension);
                }
                showToastMsg(this.context.getString(R.string.material_no_mem));
            }
            return false;
        }
        File file = new File(urlSrc);
        if (file.exists() && file.isFile()) {
            if (!DownloadUtils.getInstance().checkAdd(file.length())) {
                showToastMsg(this.context.getString(R.string.material_no_mem));
                return false;
            }
        }
        return addLocalResToProject(urlSrc, progressSampleNum, strRemoveExtension);
    }

    private void initRecycler(FileViewHolder fileViewHolder, MaterialTypeInfoBean materialTypeInfoBean) {
        if (fileViewHolder != null) {
            ((SimpleItemAnimator) fileViewHolder.expansionList.getItemAnimator()).setSupportsChangeAnimations(false);
            fileViewHolder.expansionList.setLayoutManager(new LinearLayoutManager(this.context));
            MultiLevelTreeAdapter multiLevelTreeAdapter = new MultiLevelTreeAdapter(this.context, this.materialDataList, this.mActivityFlag == 3 ? materialTypeInfoBean.getKids() : materialTypeInfoBean.getChildren(), this.selectId, this.currentPlayMaterialDataInfo, this.status);
            this.childrenAdapter = multiLevelTreeAdapter;
            multiLevelTreeAdapter.setActivityFlag(this.mActivityFlag);
            this.childrenAdapter.setOnTreeAdapterListener(this.listener);
            fileViewHolder.expansionList.setAdapter(this.childrenAdapter);
            fileViewHolder.expansionList.setPadding(Util.px2dip(this.context, 80.0f), 0, 0, 0);
            this.childrenAdapterList.put(materialTypeInfoBean.getId() + "", this.childrenAdapter);
        }
    }

    private String calculateSize(int i) {
        return new DecimalFormat("0.0").format((((double) i) / 1024.0d) / 1024.0d) + "M";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collect(final int i, final MaterialTypeInfoBean materialTypeInfoBean) {
        int i2 = this.mActivityFlag;
        if (i2 == 4) {
            if (this.listener == null || checkFileValid(materialTypeInfoBean.getUrlSrcWanos())) {
                return;
            }
            this.listener.onUpload(i, materialTypeInfoBean);
            return;
        }
        if (i2 != 3) {
            if (!materialTypeInfoBean.isCollect()) {
                if (this.mActivityFlag == 2) {
                    return;
                }
                CreatorRetrofitUtil.collectMaterialMusic(materialTypeInfoBean.getId(), new ResponseCallBack<GetMaterialCollectResponse>(this.context) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.7
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMaterialCollectResponse getMaterialCollectResponse) {
                        if (MultiLevelTreeAdapter.this.isDestoryed() || MultiLevelTreeAdapter.this.listener == null) {
                            return;
                        }
                        materialTypeInfoBean.setCollect(true);
                        MultiLevelTreeAdapter.this.updateListView(true, false, i, materialTypeInfoBean);
                        ToastUtil.showMsg(com.wanos.media.R.string.music_collect_complete);
                        MultiLevelTreeAdapter.this.listener.onCollectChange(MultiLevelTreeAdapter.this.mActivityFlag, i, materialTypeInfoBean);
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i3, String str) {
                        ToastUtil.showMsg(str);
                    }
                });
                return;
            }
            CreatorRetrofitUtil.cancelMaterialCollect(materialTypeInfoBean.getId(), new ResponseCallBack<GetMaterialCollectResponse>(this.context) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.8
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMaterialCollectResponse getMaterialCollectResponse) {
                    if (MultiLevelTreeAdapter.this.isDestoryed() || MultiLevelTreeAdapter.this.listener == null) {
                        return;
                    }
                    materialTypeInfoBean.setCollect(false);
                    MultiLevelTreeAdapter multiLevelTreeAdapter = MultiLevelTreeAdapter.this;
                    multiLevelTreeAdapter.updateListView(true, multiLevelTreeAdapter.mActivityFlag == 2, i, materialTypeInfoBean);
                    ToastUtil.showMsg(com.wanos.media.R.string.music_cancel_collect_complete);
                    MultiLevelTreeAdapter.this.listener.onCollectChange(MultiLevelTreeAdapter.this.mActivityFlag, i, materialTypeInfoBean);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i3, String str) {
                    ToastUtil.showMsg(str);
                }
            });
        }
    }

    public boolean addLocalResToProject(String str, long j, String str2) {
        String fileExtension = EditingUtils.getFileExtension(str);
        if (checkFileValid(str)) {
            return false;
        }
        EditingUtils.log("addLocalResToProject sss");
        this.isUploaded = false;
        this.isDecoded = false;
        String str3 = EditingUtils.getResLocalPath() + BceConfig.BOS_DELIMITER + str2 + Consts.DOT + fileExtension;
        if (EditingUtils.copyFile(str, str3)) {
            int iAudioIsWanos = NativeMethod.getInstance().audioIsWanos(str3);
            EditingUtils.log("decodeLocalAudio isWanos = " + iAudioIsWanos);
            upLoadLocalFile(str3, iAudioIsWanos);
            decodeLocalAudio(str3, str2, iAudioIsWanos, new DecodeAudioListener() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.9
                @Override // com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.DecodeAudioListener
                public void callback(boolean z, String str4, long j2) {
                    DataHelpAudioTrack.setToSaveServer(true);
                    if (z) {
                        UploadFileUtils.setLocalPath2Id(str4, j2);
                    }
                    MultiLevelTreeAdapter.this.hideLoading();
                }
            });
            return true;
        }
        ToastUtil.showMsg("请检查文件权限！");
        closeEditLoadingView();
        return false;
    }

    private boolean checkFileValid(String str) {
        File file = new File(str);
        String fileExtension = EditingUtils.getFileExtension(str);
        if (file.exists()) {
            long length = file.length();
            EditingUtils.log("addLocalResToProject size = " + length);
            if (length > EditingUtils.DEFAULT_SHORT_FILE_MAX_CACHE_SIZE) {
                EditingUtils.log("addLocalResToProject size");
                showToastMsg("文件太大，请检查文件！");
                return true;
            }
            if (!fileExtension.equals("mp3") && !fileExtension.equals("aac") && !fileExtension.equals("wanos") && !fileExtension.equals("wav") && !fileExtension.equals("m4a")) {
                EditingUtils.log("addLocalResToProject 文件无效，请检查文件！");
                showToastMsg("文件无效，请检查文件！");
                return true;
            }
            int iAudioIsValid = NativeMethod.getInstance().audioIsValid(str);
            if (iAudioIsValid == 0) {
                return false;
            }
            if (iAudioIsValid == -10) {
                EditingUtils.log("addLocalResToProject 该文件为多声道，请选择其它文件！");
                showToastMsg("该文件为多声道，请选择其它文件！");
            } else {
                EditingUtils.log("addLocalResToProject 文件无效，请检查文件");
                showToastMsg("文件无效，请检查文件！");
            }
            return true;
        }
        EditingUtils.log("addLocalResToProject file 文件已不存在，请检查文件！");
        showToastMsg("文件已不存在，请检查文件！");
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MaterialTypeInfoBean> list = this.materialDataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.materialDataList.get(i).getViewType();
    }

    public void setData(List<MaterialTypeInfoBean> list) {
        this.materialDataList = list;
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout btnPlay;
        public ImageView mAdd;
        public ImageView mCollect;
        public ImageView mPlay;
        public LottieAnimationView mPlayState;
        public TextView mSize;
        public TextView mTotalTime;
        public TextView musicName;
        public ProgressBar pdPlay;
        public int playState;
        public ConstraintLayout viewItem;

        MaterialViewHolder(View view) {
            super(view);
            this.playState = 0;
            this.mPlay = (ImageView) view.findViewById(R.id.material_play);
            this.mPlayState = (LottieAnimationView) view.findViewById(R.id.material_play_state);
            this.btnPlay = (FrameLayout) view.findViewById(R.id.btn_play);
            this.musicName = (TextView) view.findViewById(R.id.material_music_name);
            this.mAdd = (ImageView) view.findViewById(R.id.material_add);
            this.mSize = (TextView) view.findViewById(R.id.material_size);
            this.mTotalTime = (TextView) view.findViewById(R.id.material_total_time);
            this.mCollect = (ImageView) view.findViewById(R.id.material_collect);
            this.viewItem = (ConstraintLayout) view.findViewById(R.id.view_item);
            this.pdPlay = (ProgressBar) view.findViewById(R.id.pb_play);
        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder implements ExpansionListUtils.Expandable {
        public ImageView expansionArrows;
        public RecyclerView expansionList;
        public TextView fileName;
        public ProgressBar filePb;
        private FileViewHolder mHolder;
        public LinearLayout viewFileItem;

        FileViewHolder(View view) {
            super(view);
            this.mHolder = this;
            this.viewFileItem = (LinearLayout) view.findViewById(R.id.layout_file_item);
            this.fileName = (TextView) view.findViewById(R.id.material_file_name);
            this.expansionArrows = (ImageView) view.findViewById(R.id.material_file_arrows);
            this.filePb = (ProgressBar) view.findViewById(R.id.material_file_pb);
            this.expansionList = (RecyclerView) view.findViewById(R.id.expansion_recycler_list);
        }

        @Override // com.wanos.careditproject.utils.anim.ExpansionListUtils.Expandable
        public View getExpandView() {
            return this.expansionList;
        }

        @Override // com.wanos.careditproject.utils.anim.ExpansionListUtils.Expandable
        public View getProgressView() {
            return this.filePb;
        }

        @Override // com.wanos.careditproject.utils.anim.ExpansionListUtils.Expandable
        public View getArrowView() {
            return this.expansionArrows;
        }
    }

    private boolean addResToProject(final String str, final String str2, final String str3) {
        if ((this.context instanceof BaseActivity) && isDestoryed()) {
            return false;
        }
        DownloadUtils.getInstance().download(str, new DownloadUtils.DownloadCall() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.10
            @Override // com.wanos.careditproject.utils.http.DownloadUtils.DownloadCall
            public void onCall(String str4, String str5, boolean z) {
                if (MultiLevelTreeAdapter.this.isDestoryed()) {
                    return;
                }
                MultiLevelTreeAdapter.this.decodeAudio(str4, str2, !z ? str4 : str5, str, str3, null);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        if (!(this.context instanceof BaseActivity) || isDestoryed()) {
            return;
        }
        ((BaseActivity) this.context).showLoadingView(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        if (!(this.context instanceof BaseActivity) || isDestoryed()) {
            return;
        }
        ((BaseActivity) this.context).closeLoadingView();
    }

    public boolean isDestoryed() {
        Context context = this.context;
        if (context == null || !(context instanceof BaseActivity)) {
            return true;
        }
        BaseActivity baseActivity = (BaseActivity) context;
        return baseActivity.isFinishing() || baseActivity.isDestroyed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeAudio(String str, final String str2, String str3, final String str4, final String str5, final DecodeAudioListener decodeAudioListener) {
        AudioCodec.getPCMFromAudio(str3, str, 0, new AudioCodec.AudioDecodeListener() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.11
            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeOver(String str6) {
                if (MultiLevelTreeAdapter.this.isDestoryed()) {
                    return;
                }
                EditingUtils.log("decodeAudio decodeOver");
                AudioFileInfo.DecodeAudioInfo decodeAudioInfo = AudioFileInfo.getInstance().get(str4);
                MultiLevelTreeAdapter.this.closeEditLoadingView();
                if (decodeAudioInfo != null) {
                    int selectTrackIndex2 = EditingParams.getInstance().getSelectTrackIndex2();
                    if (selectTrackIndex2 < 0) {
                        DecodeAudioListener decodeAudioListener2 = decodeAudioListener;
                        if (decodeAudioListener2 != null) {
                            decodeAudioListener2.callback(false, str6, 0L);
                            return;
                        }
                        return;
                    }
                    long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
                    String url = UploadFileUtils.getUrl(str4);
                    if (url.equals("")) {
                        url = str4;
                    }
                    String str7 = url;
                    String replaceClipId = EditingParams.getInstance().getReplaceClipId();
                    if (replaceClipId == null || replaceClipId.equals("")) {
                        DataHelpAudioTrack.DataHelpResult dataHelpResultAddTrackWithUrl = DataHelpAudioTrack.addTrackWithUrl(str7, str2, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, selectTrackIndex2, progressSampleNum, str5);
                        if (dataHelpResultAddTrackWithUrl.isSuccess()) {
                            DecodeAudioListener decodeAudioListener3 = decodeAudioListener;
                            if (decodeAudioListener3 != null) {
                                decodeAudioListener3.callback(true, str6, dataHelpResultAddTrackWithUrl.getId());
                            }
                            MultiLevelTreeAdapter multiLevelTreeAdapter = MultiLevelTreeAdapter.this;
                            multiLevelTreeAdapter.showToastMsg(multiLevelTreeAdapter.context.getString(R.string.material_add_material), true);
                            return;
                        }
                        DecodeAudioListener decodeAudioListener4 = decodeAudioListener;
                        if (decodeAudioListener4 != null) {
                            decodeAudioListener4.callback(false, str6, 0L);
                        }
                        MultiLevelTreeAdapter.this.showToastMsg(dataHelpResultAddTrackWithUrl.getErrMsg());
                        return;
                    }
                    DataHelpAudioTrack.DataHelpResult dataHelpResultReplaceClip = DataHelpAudioTrack.replaceClip(replaceClipId, str7, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, str2, str5);
                    if (dataHelpResultReplaceClip.isSuccess()) {
                        DecodeAudioListener decodeAudioListener5 = decodeAudioListener;
                        if (decodeAudioListener5 != null) {
                            decodeAudioListener5.callback(true, str6, dataHelpResultReplaceClip.getId());
                        }
                        MultiLevelTreeAdapter.this.showToastMsg("替换成功！", true);
                        return;
                    }
                    DecodeAudioListener decodeAudioListener6 = decodeAudioListener;
                    if (decodeAudioListener6 != null) {
                        decodeAudioListener6.callback(false, str6, 0L);
                    }
                    MultiLevelTreeAdapter.this.showToastMsg(dataHelpResultReplaceClip.getErrMsg());
                    return;
                }
                DecodeAudioListener decodeAudioListener7 = decodeAudioListener;
                if (decodeAudioListener7 != null) {
                    decodeAudioListener7.callback(false, str6, 0L);
                }
            }

            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeFail(String str6) {
                if (MultiLevelTreeAdapter.this.isDestoryed()) {
                    return;
                }
                EditingUtils.log("decodeAudio decodeFail");
                if (MultiLevelTreeAdapter.this.context instanceof BaseActivity) {
                    ((BaseActivity) MultiLevelTreeAdapter.this.context).runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.11.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MultiLevelTreeAdapter.this.showToastMsg("添加失败！");
                            ((BaseActivity) MultiLevelTreeAdapter.this.context).closeLoadingView();
                        }
                    });
                }
                DecodeAudioListener decodeAudioListener2 = decodeAudioListener;
                if (decodeAudioListener2 != null) {
                    decodeAudioListener2.callback(false, str6, 0L);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastMsg(String str) {
        showToastMsg(str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastMsg(final String str, final boolean z) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ToastUtil.showMsg(str);
            if (z) {
                finishActivity();
                return;
            }
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.12
            @Override // java.lang.Runnable
            public void run() {
                ToastUtil.showMsg(str);
                if (z) {
                    MultiLevelTreeAdapter.this.finishActivity();
                }
            }
        });
    }

    private void upLoadLocalFile(final String str, final int i) {
        EditingUtils.log("upLoadLocalFile start");
        File file = new File(str);
        BaiduCloudUtils.getInstance().getTempToken(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, Integer.parseInt(EditingParams.getInstance().getProjectId()), file);
        BaiduCloudUtils.getInstance().setOnUpLoadListener(new BaiduCloudUtils.OnUpLoadListener() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.13
            @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
            public void upLoadSucceed(BaiduCloudUtils.UploadSuccessBean uploadSuccessBean) {
                EditingUtils.log("upLoadLocalFile upLoadSucceed");
                if (i == 1) {
                    MultiLevelTreeAdapter.this.localPath2UrlMap.put(str, uploadSuccessBean.getUrl());
                    MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                } else {
                    MultiLevelTreeAdapter.this.startGetTaskState(uploadSuccessBean, str);
                }
            }

            @Override // com.wanos.careditproject.utils.BaiduCloudUtils.OnUpLoadListener
            public void upLoadFail(String str2) {
                EditingUtils.log("upLoadLocalFile upLoadFail");
                MultiLevelTreeAdapter.this.localPath2UrlMap.put(str, "");
                MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
            }
        });
    }

    private void decodeLocalAudio(final String str, String str2, int i, DecodeAudioListener decodeAudioListener) {
        if (i != 1) {
            final String str3 = EditingUtils.getResLocalPath() + BceConfig.BOS_DELIMITER + str2 + "_wanos.m4a";
            EditingUtils.log("decodeLocalAudio wanosLocalPath = " + str3);
            AudioCodec.encodeAudioToWanos(str, str3, new AudioCodec.AudioDecodeListener() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.14
                @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
                public void decodeOver(String str4) {
                    MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, true);
                    MultiLevelTreeAdapter.this.localPath2WanosPathMap.put(str, str3);
                    EditingUtils.log("decodeAudio decodeOver");
                    MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                }

                @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
                public void decodeFail(String str4) {
                    MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, false);
                    EditingUtils.log("decodeAudio decodeOver");
                    MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                }
            });
            return;
        }
        decodeLocalWanosAudio(str, str2, decodeAudioListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeOrUploadFinish(String str) {
        AudioFileInfo.DecodeAudioInfo decodeAudioInfo;
        if (this.localPath2IsDecodeMap.containsKey(str) && this.localPath2UrlMap.containsKey(str)) {
            closeEditLoadingView();
            boolean zBooleanValue = this.localPath2IsDecodeMap.get(str).booleanValue();
            String str2 = this.localPath2UrlMap.get(str);
            if (zBooleanValue) {
                String string = Paths.get(str, new String[0]).getFileName().toString();
                String strSubstring = string.substring(0, string.lastIndexOf(46));
                if (this.localPath2WanosPathMap.containsKey(str)) {
                    String str3 = this.localPath2WanosPathMap.get(str);
                    decodeAudioInfo = AudioFileInfo.getInstance().get(str3);
                    AudioPcmData.getInstance().changeUrl(str3, str2);
                    DownloadUtils.getInstance().put(str2, this.localPath2WanosPathMap.get(str));
                    AudioFileInfo.getInstance().add(str3, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum);
                } else {
                    decodeAudioInfo = AudioFileInfo.getInstance().get(str);
                    AudioPcmData.getInstance().changeUrl(str, str2);
                    DownloadUtils.getInstance().put(str2, str);
                }
                if (decodeAudioInfo != null) {
                    int selectTrackIndex2 = EditingParams.getInstance().getSelectTrackIndex2();
                    if (selectTrackIndex2 < 0) {
                        return;
                    }
                    long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
                    String replaceClipId = EditingParams.getInstance().getReplaceClipId();
                    if (replaceClipId == null || replaceClipId.equals("")) {
                        DataHelpAudioTrack.DataHelpResult dataHelpResultAddTrackWithUrl = DataHelpAudioTrack.addTrackWithUrl(str2, null, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, selectTrackIndex2, progressSampleNum, strSubstring);
                        if (dataHelpResultAddTrackWithUrl.isSuccess()) {
                            showToastMsg(this.context.getString(R.string.material_add_material), true);
                            return;
                        } else {
                            showToastMsg(dataHelpResultAddTrackWithUrl.getErrMsg());
                            return;
                        }
                    }
                    DataHelpAudioTrack.DataHelpResult dataHelpResultReplaceClip = DataHelpAudioTrack.replaceClip(replaceClipId, str, decodeAudioInfo.sampleRate, decodeAudioInfo.channelNum, decodeAudioInfo.sampleNum, null, strSubstring);
                    if (dataHelpResultReplaceClip.isSuccess()) {
                        showToastMsg("替换成功！", true);
                        return;
                    } else {
                        showToastMsg(dataHelpResultReplaceClip.getErrMsg());
                        return;
                    }
                }
                showToastMsg("添加失败！");
                return;
            }
            showToastMsg("添加失败！");
        }
    }

    private void decodeLocalWanosAudio(final String str, String str2, final DecodeAudioListener decodeAudioListener) {
        AudioCodec.getPCMFromAudio(str, str, 0, new AudioCodec.AudioDecodeListener() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.15
            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeOver(String str3) {
                MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, true);
                EditingUtils.log("decodeAudio decodeOver");
                MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
            }

            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeFail(String str3) {
                EditingUtils.log("decodeAudio decodeFail");
                MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, false);
                MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                DecodeAudioListener decodeAudioListener2 = decodeAudioListener;
                if (decodeAudioListener2 != null) {
                    decodeAudioListener2.callback(false, str3, 0L);
                }
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeEditLoadingView() {
        Context context = this.context;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.16
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseActivity) MultiLevelTreeAdapter.this.context).closeLoadingView();
                }
            });
        }
    }

    public void updatePlayStatus(MediaPlayerEnum.CallBackState callBackState, MaterialTypeInfoBean materialTypeInfoBean) {
        if (this.currentPlayMaterialDataInfo != null && this.selectId != materialTypeInfoBean.getM_id()) {
            updateSelfStatus(MediaPlayerEnum.CallBackState.COMPLETE, this.currentPlayMaterialDataInfo);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED || callBackState == MediaPlayerEnum.CallBackState.PREPARE || callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
            if (materialTypeInfoBean != null) {
                this.selectId = materialTypeInfoBean.getM_id();
                this.currentPlayMaterialDataInfo = materialTypeInfoBean;
            }
            this.status = callBackState;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.status = callBackState;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION) {
            this.status = callBackState;
        }
        updateSelfStatus(callBackState, materialTypeInfoBean);
    }

    public void updateSelfStatus(MediaPlayerEnum.CallBackState callBackState, MaterialTypeInfoBean materialTypeInfoBean) {
        if (this.materialDataList != null && materialTypeInfoBean != null) {
            for (int i = 0; i < this.materialDataList.size(); i++) {
                if (this.materialDataList.get(i).getM_id() == materialTypeInfoBean.getM_id()) {
                    notifyItemChanged(i);
                    return;
                }
            }
        }
        updateChildStatus(callBackState, materialTypeInfoBean);
    }

    public void updateChildStatus(MediaPlayerEnum.CallBackState callBackState, MaterialTypeInfoBean materialTypeInfoBean) {
        Map<String, MultiLevelTreeAdapter> map = this.childrenAdapterList;
        if (map != null) {
            Iterator<Map.Entry<String, MultiLevelTreeAdapter>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().updatePlayStatus(callBackState, materialTypeInfoBean);
            }
        }
    }

    public void setActivityFlag(int i) {
        this.mActivityFlag = i;
    }

    public void setUploadStatus(int i) {
        MaterialTypeInfoBean materialTypeInfoBean = this.materialDataList.get(i);
        materialTypeInfoBean.setCollect(true);
        this.materialDataList.set(i, materialTypeInfoBean);
        notifyItemChanged(i);
    }

    public void updateListView(int i, MaterialTypeInfoBean materialTypeInfoBean) {
        updateListView(false, false, i, materialTypeInfoBean);
    }

    public void updateListView(boolean z, boolean z2, int i, final MaterialTypeInfoBean materialTypeInfoBean) {
        MaterialTypeInfoBean materialTypeInfoBean2;
        List<MaterialTypeInfoBean> children;
        if (materialTypeInfoBean == null) {
            notifyItemChanged(i);
            return;
        }
        List<MaterialTypeInfoBean> list = this.materialDataList;
        if (list != null && list.size() != 0) {
            if (z2 && materialTypeInfoBean.getViewType() == 2) {
                if (i >= this.materialDataList.size() || (children = (materialTypeInfoBean2 = this.materialDataList.get(i)).getChildren()) == null || children.size() == 0) {
                    return;
                }
                children.removeIf(new Predicate() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return MultiLevelTreeAdapter.lambda$updateListView$0(materialTypeInfoBean, (MaterialTypeInfoBean) obj);
                    }
                });
                materialTypeInfoBean2.setChildren(children);
                this.materialDataList.set(i, materialTypeInfoBean2);
                notifyDataSetChanged();
                return;
            }
            if (materialTypeInfoBean.getViewType() == 1) {
                if ((this.mActivityFlag == 3 ? materialTypeInfoBean.getParentId() : materialTypeInfoBean.getParent()) == 0) {
                    this.materialDataList.set(i, materialTypeInfoBean);
                    notifyItemChanged(i);
                    return;
                }
            }
            for (int i2 = 0; i2 < this.materialDataList.size(); i2++) {
                MaterialTypeInfoBean materialTypeInfoBean3 = this.materialDataList.get(i2);
                if (materialTypeInfoBean3.getId() == (z ? materialTypeInfoBean.getId() : this.mActivityFlag == 3 ? materialTypeInfoBean.getParentId() : materialTypeInfoBean.getParent())) {
                    List<MaterialTypeInfoBean> kids = this.mActivityFlag == 3 ? materialTypeInfoBean3.getKids() : materialTypeInfoBean3.getChildren();
                    if (kids != null) {
                        kids.set(i, materialTypeInfoBean);
                        if (this.mActivityFlag == 3) {
                            materialTypeInfoBean3.setKids(kids);
                        } else {
                            materialTypeInfoBean3.setChildren(kids);
                        }
                    }
                    List<MaterialTypeInfoBean> list2 = this.materialDataList;
                    if (!z) {
                        materialTypeInfoBean = materialTypeInfoBean3;
                    }
                    list2.set(i2, materialTypeInfoBean);
                    MultiLevelTreeAdapter multiLevelTreeAdapter = this.childrenAdapterList.get(materialTypeInfoBean3.getId() + "");
                    if (multiLevelTreeAdapter != null) {
                        multiLevelTreeAdapter.notifyItemChanged(i);
                        return;
                    }
                    MultiLevelTreeAdapter multiLevelTreeAdapter2 = this.childrenAdapter;
                    if (multiLevelTreeAdapter2 != null) {
                        multiLevelTreeAdapter2.notifyItemChanged(i);
                    }
                    notifyItemChanged(i);
                    return;
                }
            }
            return;
        }
        this.materialDataList.add(0, materialTypeInfoBean);
        notifyItemChanged(0);
    }

    static /* synthetic */ boolean lambda$updateListView$0(MaterialTypeInfoBean materialTypeInfoBean, MaterialTypeInfoBean materialTypeInfoBean2) {
        return materialTypeInfoBean2.getId() == materialTypeInfoBean.getId();
    }

    public void startGetTaskState(final BaiduCloudUtils.UploadSuccessBean uploadSuccessBean, final String str) {
        new Thread(new Runnable() { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m379xca83d927(str, uploadSuccessBean);
            }
        }).start();
    }

    /* JADX INFO: renamed from: lambda$startGetTaskState$1$com-wanos-careditproject-material-adapter-MultiLevelTreeAdapter, reason: not valid java name */
    /* synthetic */ void m379xca83d927(final String str, BaiduCloudUtils.UploadSuccessBean uploadSuccessBean) {
        this.isGetTaskStateRunning = true;
        this.getTaskStateCount = 0;
        while (this.isGetTaskStateRunning) {
            EditingUtils.log("startGetTaskState getTaskStateCount=" + this.getTaskStateCount);
            int i = this.getTaskStateCount;
            if (i > 120) {
                this.isGetTaskStateRunning = false;
                this.localPath2IsDecodeMap.put(str, false);
                decodeOrUploadFinish(str);
                return;
            } else {
                this.getTaskStateCount = i + 1;
                CreatorRetrofitUtil.getProjectUploadAssetTaskList(uploadSuccessBean.getAssetId(), uploadSuccessBean.getTaskId(), new ResponseCallBack<ProjectUploadAssetTaskResponse>(null) { // from class: com.wanos.careditproject.material.adapter.MultiLevelTreeAdapter.17
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(ProjectUploadAssetTaskResponse projectUploadAssetTaskResponse) {
                        if (!MultiLevelTreeAdapter.this.isDestoryed() && MultiLevelTreeAdapter.this.isGetTaskStateRunning) {
                            LogUtil.d("getProjectUploadAssetTaskList onResponseSuccessful: ");
                            if (projectUploadAssetTaskResponse.data != null && projectUploadAssetTaskResponse.data.taskStatus == 3) {
                                MultiLevelTreeAdapter.this.isGetTaskStateRunning = false;
                                MultiLevelTreeAdapter.this.localPath2UrlMap.put(str, projectUploadAssetTaskResponse.data.urlSrc);
                                MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                            } else {
                                if (projectUploadAssetTaskResponse.data == null || projectUploadAssetTaskResponse.data.taskStatus != 4) {
                                    return;
                                }
                                MultiLevelTreeAdapter.this.isGetTaskStateRunning = false;
                                MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, false);
                                MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                            }
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i2, String str2) {
                        if (MultiLevelTreeAdapter.this.isGetTaskStateRunning) {
                            MultiLevelTreeAdapter.this.isGetTaskStateRunning = false;
                            MultiLevelTreeAdapter.this.localPath2IsDecodeMap.put(str, false);
                            MultiLevelTreeAdapter.this.decodeOrUploadFinish(str);
                        }
                    }
                });
                try {
                    Thread.sleep(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopGetTaskState() {
        this.isGetTaskStateRunning = false;
    }
}
