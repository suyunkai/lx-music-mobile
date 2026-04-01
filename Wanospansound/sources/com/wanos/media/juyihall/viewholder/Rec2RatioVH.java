package com.wanos.media.juyihall.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.utils.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2RatioVH extends Rec2PlayableVH<AudioBookAlbumInfoBean> {
    public ImageView ivAlbumState;
    public ImageView ivAvatar;
    public TextView tvDesc;
    public TextView tvEpisodes;
    public TextView tvEpisodes2;
    public TextView tvName;

    public Rec2RatioVH(View view) {
        super(view);
        this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        this.ivAlbumState = (ImageView) view.findViewById(R.id.iv_album_state);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        this.tvEpisodes = (TextView) view.findViewById(R.id.tv_episodes);
        this.tvEpisodes2 = (TextView) view.findViewById(R.id.tv_episodes2);
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == this.itemView.getId()) {
            PageRouter.toAudioBoolAlbumPage(Utils.getFormatId(((AudioBookAlbumInfoBean) this.mData).getId()));
        }
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH
    protected void realPlay() {
        super.realPlay();
        ServiceRouter.getMediaPlayService().playAudioBook((AudioBookAlbumInfoBean) this.mData, this.itemView.getContext());
    }
}
