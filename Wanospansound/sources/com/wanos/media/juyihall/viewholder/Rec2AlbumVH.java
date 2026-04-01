package com.wanos.media.juyihall.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.utils.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2AlbumVH extends Rec2PlayableVH<MusicGroupInfo> {
    public ImageView ivAvatar;
    public TextView tvDesc;
    public TextView tvName;

    public Rec2AlbumVH(View view) {
        super(view);
        this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.tvDesc = (TextView) view.findViewById(R.id.tv_desc);
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == this.itemView.getId()) {
            PageRouter.toMusicListPage(Utils.getFormatId(((MusicGroupInfo) this.mData).getMusicGroupId()));
        }
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH
    protected void realPlay() {
        super.realPlay();
        ServiceRouter.getMediaPlayService().playMusicAlbum(String.valueOf(((MusicGroupInfo) this.mData).getMusicGroupId()));
    }
}
