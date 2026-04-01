package com.wanos.media.ui.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.utils.Consts;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.util.AnitClick;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeMoviesListAdapter extends BaseRecycleAdapter<VideoInfo, ViewHolder> {
    public static final String TAG = "wanos:[HomeMoviesListAdapter]";
    private static final String imageSize = "_260_360";
    private final Context mContext;
    private final StringBuilder mStringBuilder;

    public HomeMoviesListAdapter(Context context, List<VideoInfo> datas) {
        super(datas);
        this.mStringBuilder = new StringBuilder();
        this.mContext = context;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movies_group_item, parent, false);
        AppCompatTextView appCompatTextView = (AppCompatTextView) viewInflate.findViewById(R.id.tv_name);
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) viewInflate.findViewById(R.id.tv_name_ex);
        Util.setTextWeight(appCompatTextView, 500);
        Util.setTextWeight(appCompatTextView2, 400);
        final ViewHolder viewHolder = new ViewHolder(viewInflate);
        viewInflate.setOnClickListener(new AnitClick() { // from class: com.wanos.media.ui.video.adapter.HomeMoviesListAdapter.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                if (HomeMoviesListAdapter.this.datas == null || bindingAdapterPosition >= HomeMoviesListAdapter.this.datas.size() || bindingAdapterPosition < 0) {
                    return;
                }
                HomeMoviesListAdapter homeMoviesListAdapter = HomeMoviesListAdapter.this;
                homeMoviesListAdapter.jumpUrl((VideoInfo) homeMoviesListAdapter.datas.get(bindingAdapterPosition));
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_THEATER_MUSIC_PLAY, "" + ((VideoInfo) HomeMoviesListAdapter.this.datas.get(bindingAdapterPosition)).getTvId(), "", "", "", 0);
            }
        });
        return viewHolder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(ViewHolder holder, int position) {
        VideoInfo videoInfo = (VideoInfo) this.datas.get(position);
        if (videoInfo == null) {
            return;
        }
        GlideUtil.setImageData(getCoverUrl(videoInfo.getAlbumPic()), holder.imCover);
        holder.tvName.setText(videoInfo.getName());
        holder.tvNameEx.setText(videoInfo.getFocus());
        if (CommonUtils.isChannelNot245()) {
            String strReplaceAll = videoInfo.getName().replaceAll(" ", "");
            if (videoInfo.getChnId() == 1) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.play_movie, strReplaceAll));
                holder.imCover.setContentDescription(this.mContext.getResources().getString(R.string.play_movie1, (position + 1) + ""));
            } else if (videoInfo.getChnId() == 2) {
                holder.itemView.setContentDescription(this.mContext.getResources().getString(R.string.play_teleplay, strReplaceAll));
                holder.imCover.setContentDescription(this.mContext.getResources().getString(R.string.play_teleplay1, (position + 1) + ""));
            }
        }
        if (videoInfo.isExclusive() == 1) {
            holder.imTag.setVisibility(0);
            holder.imTag.setImageResource(R.drawable.ic_exclusive);
        } else if (videoInfo.isVip() == 1) {
            holder.imTag.setVisibility(0);
            holder.imTag.setImageResource(R.drawable.ab_list_vip);
        } else {
            holder.imTag.setVisibility(4);
        }
        if (videoInfo.getChnId() == 1) {
            holder.tvJi.setVisibility(4);
            return;
        }
        if (videoInfo.getChnId() == 2) {
            holder.tvJi.setVisibility(0);
            if ((videoInfo.getCount() == null ? 0 : videoInfo.getCount().intValue()) == (videoInfo.getTotal() == null ? 0 : videoInfo.getTotal().intValue())) {
                holder.tvJi.setText(StringUtils.format(StringUtils.getString(R.string.iqiyi_assemble_all), videoInfo.getTotal()));
            } else {
                holder.tvJi.setText(StringUtils.format(StringUtils.getString(R.string.iqiyi_assemble_now), videoInfo.getCount()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpUrl(VideoInfo item) {
        String qipuId;
        if (item == null) {
            ToastUtil.showMsg(R.string.get_iqy_error);
            return;
        }
        String tvId = item.getTvId();
        String albumId = item.getAlbumId();
        String qipuId2 = item.getDefaultEpi() != null ? item.getDefaultEpi().getQipuId() : "";
        if (!"".equals(albumId) || "".equals(qipuId2)) {
            qipuId = tvId;
            tvId = albumId;
        } else {
            qipuId = item.getDefaultEpi().getQipuId();
        }
        this.mStringBuilder.setLength(0);
        this.mStringBuilder.append("iqiyi://com.qiyi.video.iv/play?command=play_card_video&albumId=");
        this.mStringBuilder.append(tvId);
        this.mStringBuilder.append("&qipuId=");
        this.mStringBuilder.append(qipuId);
        this.mStringBuilder.append("&channelId=");
        this.mStringBuilder.append(101);
        this.mStringBuilder.append("&albumName=");
        this.mStringBuilder.append(item.getAlbumName());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(this.mStringBuilder.toString()));
        intent.addFlags(268435456);
        try {
            this.mContext.startActivity(intent);
        } catch (Exception unused) {
            Log.e(TAG, "调起爱奇艺失败: " + ((Object) this.mStringBuilder));
        }
    }

    protected String getCoverUrl(String path) {
        if (StringUtils.isEmpty(path) || !path.contains(Consts.DOT)) {
            ToastUtil.showMsg(R.string.get_iqy_error);
            return "";
        }
        int iLastIndexOf = path.lastIndexOf(Consts.DOT);
        return path.substring(0, iLastIndexOf) + imageSize + path.substring(iLastIndexOf);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imCover;
        public ImageView imTag;
        public TextView tvJi;
        public TextView tvName;
        public TextView tvNameEx;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imCover = (ImageView) itemView.findViewById(R.id.im_cover);
            this.imTag = (ImageView) itemView.findViewById(R.id.im_tag);
            this.tvJi = (TextView) itemView.findViewById(R.id.tv_ji);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvNameEx = (TextView) itemView.findViewById(R.id.tv_name_ex);
        }
    }
}
