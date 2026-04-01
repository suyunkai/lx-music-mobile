package com.wanos.media.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.entity.ThemeSoundInfoEntity;
import com.wanos.media.util.CacheTools;
import com.wanos.media.util.PictureCacheUtils;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SoundMaterialViewModel extends ViewModel {
    public static final String KEY_MATERIAL_CACHE = "MaterialCache";
    private final MutableLiveData<List<GetAudioInfoResp.TagGroupDTO>> mItemData = new MutableLiveData<>();
    private final MutableLiveData<Drawable> mBackgroundImage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoadingState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mErrorState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mContentState = new MutableLiveData<>();

    public MutableLiveData<List<GetAudioInfoResp.TagGroupDTO>> getItemData() {
        return this.mItemData;
    }

    public MutableLiveData<Drawable> getBackgroundImage() {
        return this.mBackgroundImage;
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return this.mLoadingState;
    }

    public MutableLiveData<Boolean> getErrorState() {
        return this.mErrorState;
    }

    public MutableLiveData<Boolean> getContentState() {
        return this.mContentState;
    }

    public SoundMaterialViewModel() {
        initSoundMaterialData();
    }

    public void initSoundMaterialData() {
        this.mErrorState.setValue(false);
        this.mLoadingState.setValue(true);
        this.mContentState.setValue(false);
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<GetAudioInfoResp.TagGroupDTO>>() { // from class: com.wanos.media.viewmodel.SoundMaterialViewModel.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<GetAudioInfoResp.TagGroupDTO> doInBackground() throws Throwable {
                GetAudioInfoResp data;
                Thread.sleep(400L);
                String str = (String) CacheTools.getLocalObject(Utils.getApp(), SoundMaterialViewModel.KEY_MATERIAL_CACHE, String.class);
                if (StringUtils.isEmpty(str)) {
                    BaseEntity<GetAudioInfoResp> audioLibrarySync = HttpTools.getInstance().getAudioLibrarySync();
                    if (audioLibrarySync == null || (data = audioLibrarySync.getData()) == null) {
                        return null;
                    }
                    CacheTools.toObjectLocal(Utils.getApp(), SoundMaterialViewModel.KEY_MATERIAL_CACHE, GsonUtils.toJson(data));
                    return SoundMaterialViewModel.this.formatMaterialData(data.getTagGroup());
                }
                GetAudioInfoResp getAudioInfoResp = (GetAudioInfoResp) GsonUtils.fromJson(str, GetAudioInfoResp.class);
                if (getAudioInfoResp == null) {
                    return null;
                }
                return SoundMaterialViewModel.this.formatMaterialData(getAudioInfoResp.getTagGroup());
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<GetAudioInfoResp.TagGroupDTO> list) {
                SoundMaterialViewModel.this.mLoadingState.setValue(false);
                if (list != null) {
                    SoundMaterialViewModel.this.mErrorState.setValue(false);
                    SoundMaterialViewModel.this.mContentState.setValue(true);
                    SoundMaterialViewModel.this.mItemData.setValue(list);
                } else {
                    SoundMaterialViewModel.this.mErrorState.setValue(true);
                    SoundMaterialViewModel.this.mContentState.setValue(false);
                }
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                onSuccess((List<GetAudioInfoResp.TagGroupDTO>) null);
            }
        });
    }

    public void setBackgroundImage(final String str) {
        ThreadUtils.executeByCpu(new ThreadUtils.SimpleTask<Bitmap>() { // from class: com.wanos.media.viewmodel.SoundMaterialViewModel.2
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public Bitmap doInBackground() throws Throwable {
                File file = new File(PictureCacheUtils.getImageCachePath(str));
                if (!file.exists() || !file.isFile()) {
                    return null;
                }
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
                float height = bitmapDecodeFile.getHeight() * 0.72f;
                return Bitmap.createBitmap(bitmapDecodeFile, 0, (int) (bitmapDecodeFile.getHeight() - height), bitmapDecodeFile.getWidth(), (int) height);
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(Bitmap bitmap) {
                if (bitmap == null) {
                    SoundMaterialViewModel.this.mBackgroundImage.setValue(new ColorDrawable(Color.parseColor("#9A9A9A")));
                } else {
                    SoundMaterialViewModel.this.mBackgroundImage.setValue(new BitmapDrawable(bitmap));
                }
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                onSuccess((Bitmap) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<GetAudioInfoResp.TagGroupDTO> formatMaterialData(List<GetAudioInfoResp.TagGroupDTO> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            String tagName = list.get(i).getTagName();
            List<ThemeAudioInfoEntity> sounds = list.get(i).getSounds();
            for (int i2 = 0; i2 < sounds.size(); i2++) {
                sounds.get(i2).setTagName(tagName);
                ThemeSoundInfoEntity soundInfo = sounds.get(i2).getSoundInfo();
                if (soundInfo != null) {
                    soundInfo.setVolume(0.0f);
                }
            }
        }
        return list;
    }
}
