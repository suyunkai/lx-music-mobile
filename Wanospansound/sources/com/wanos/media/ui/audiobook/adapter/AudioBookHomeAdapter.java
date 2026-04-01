package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookHomeAdapter extends AudioBookAlbumListBaseAdapter {
    private Context mContext;

    public AudioBookHomeAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
        super(context, datas);
    }

    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public AudioBookAlbumListBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 4371) {
            return new AudioBookAlbumListBaseAdapter.ViewHolder(this.mFooterView);
        }
        if (viewType == 4370) {
            return new AudioBookAlbumListBaseAdapter.ViewHolder(this.mHeaderView);
        }
        return new AudioBookAlbumListBaseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_audio_book_home_album_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(AudioBookAlbumListBaseAdapter.ViewHolder holder, int position) {
        super.bindData(holder, position);
    }
}
