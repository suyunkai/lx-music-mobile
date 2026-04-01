package com.wanos.media.juyihall.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.TagInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.Recommend2ViewModel;
import com.wanos.media.juyihall.utils.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2TagVH extends Rec2PlayableVH<TagInfoBean> {
    public TextView tvName;

    public Rec2TagVH(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
    }

    @Override // com.wanos.media.juyihall.viewholder.Rec2PlayableVH
    protected void realPlay() {
        super.realPlay();
        ((Recommend2ViewModel) new ViewModelProvider((ViewModelStoreOwner) this.itemView.getContext()).get(Recommend2ViewModel.class)).getRepository().getTagMusicList(1, 1, String.valueOf(((TagInfoBean) this.mData).getTagId()), new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.juyihall.viewholder.Rec2TagVH.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse getMusicListResponse) {
                MusicInfoBean musicInfoBean = getMusicListResponse.data.getMusicList().get(0);
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                mediaInfo.setGroupId(Utils.setFormatId(((TagInfoBean) Rec2TagVH.this.mData).getTagId()));
                mediaInfo.setMediaGroupType(-9L);
                mediaInfo.setMusicInfoBean(musicInfoBean);
                ServiceRouter.getMediaPlayService().playMediaInfo(mediaInfo);
            }
        });
    }
}
