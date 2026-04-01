package com.wanos.media.viewmodel;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.bean.SoundCollectionEntity;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.CollectCoveEntity;
import com.wanos.media.entity.PageState;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionDialogViewModel extends ViewModel {
    public static final String KEY_AUDIO_CONFIG = "audio_config";
    public static final String KEY_DEFAULT_COVER = "default_cover";
    public static final String KEY_TITLE = "title";
    private final MutableLiveData<PageState> _PageState;
    private final SingleLiveEvent<String> _Title;
    private final SingleLiveEvent<Integer> _UpDataCount;
    private String audioConfig;
    private String defaultCover;
    private final List<SoundCollectionEntity> mItemData;
    private int mSelectIndex;
    public final LiveData<PageState> pageState;
    public final LiveData<String> title;
    public final LiveData<Integer> upDataCount;

    public String getAudioConfig() {
        return this.audioConfig;
    }

    public List<SoundCollectionEntity> getItemData() {
        int i = 0;
        while (i < this.mItemData.size()) {
            this.mItemData.get(i).setSelect(i == this.mSelectIndex);
            i++;
        }
        return this.mItemData;
    }

    public void setSelectIndex(int i) {
        this.mSelectIndex = i;
    }

    public CollectionDialogViewModel(Bundle bundle) {
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        this._Title = singleLiveEvent;
        this.title = singleLiveEvent;
        MutableLiveData<PageState> mutableLiveData = new MutableLiveData<>(PageState.LOADING);
        this._PageState = mutableLiveData;
        this.pageState = mutableLiveData;
        SingleLiveEvent<Integer> singleLiveEvent2 = new SingleLiveEvent<>();
        this._UpDataCount = singleLiveEvent2;
        this.upDataCount = singleLiveEvent2;
        this.mItemData = new ArrayList();
        this.defaultCover = "";
        this.audioConfig = "";
        this.mSelectIndex = 0;
        if (bundle == null) {
            return;
        }
        singleLiveEvent.setValue(bundle.getString(KEY_TITLE));
        this.defaultCover = bundle.getString(KEY_DEFAULT_COVER);
        this.audioConfig = bundle.getString(KEY_AUDIO_CONFIG);
        initCoverListData();
    }

    /* JADX INFO: renamed from: com.wanos.media.viewmodel.CollectionDialogViewModel$1, reason: invalid class name */
    class AnonymousClass1 extends HttpCallback<CollectCoveEntity> {
        AnonymousClass1() {
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseSuccessful(BaseEntity<CollectCoveEntity> baseEntity) {
            if (baseEntity == null) {
                CollectionDialogViewModel.this._PageState.setValue(PageState.ERROR);
                return;
            }
            CollectCoveEntity data = baseEntity.getData();
            if (data == null) {
                CollectionDialogViewModel.this._PageState.setValue(PageState.ERROR);
                return;
            }
            if (data.getImages() == null || data.getImages().isEmpty()) {
                CollectionDialogViewModel.this._PageState.setValue(PageState.ERROR);
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (!StringUtils.isEmpty(CollectionDialogViewModel.this.defaultCover)) {
                arrayList.add(new SoundCollectionEntity(false, CollectionDialogViewModel.this.defaultCover));
            }
            List<CollectCoveEntity.ImagesDTO> images = data.getImages();
            for (int i = 0; i < images.size(); i++) {
                arrayList.add(new SoundCollectionEntity(false, images.get(i).getPath()));
            }
            arrayList.stream().findFirst().ifPresent(new Consumer() { // from class: com.wanos.media.viewmodel.CollectionDialogViewModel$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SoundCollectionEntity) obj).setSelect(true);
                }
            });
            CollectionDialogViewModel.this.mItemData.clear();
            CollectionDialogViewModel.this.mItemData.addAll(arrayList);
            CollectionDialogViewModel.this._PageState.setValue(PageState.SUCCESS);
            CollectionDialogViewModel.this._UpDataCount.setValue(Integer.valueOf(arrayList.size()));
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseFailure(int i, String str) {
            CollectionDialogViewModel.this._PageState.setValue(PageState.ERROR);
        }
    }

    public void initCoverListData() {
        HttpTools.getInstance().getCollectCoveImageList(new AnonymousClass1());
    }
}
