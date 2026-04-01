package com.wanos.media.juyihall.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.utils.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2RelaxVH extends Rec2PlayableVH<MusicInfoBean> {
    public ImageView ivAvatar;
    public TextView tvName;

    public Rec2RelaxVH(View view) {
        super(view);
        this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == this.itemView.getId()) {
            startPlay();
            PageRouter.toMusicPlayPage();
        }
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH
    protected void realPlay() {
        super.realPlay();
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
        mediaInfo.setMediaGroupType(-11L);
        mediaInfo.setGroupId(Utils.setFormatId(-11L));
        mediaInfo.setMusicInfoBean((MusicInfoBean) this.mData);
        ServiceRouter.getMediaPlayService().playMediaInfo(mediaInfo);
    }
}
