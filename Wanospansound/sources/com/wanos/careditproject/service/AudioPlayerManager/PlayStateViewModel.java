package com.wanos.careditproject.service.AudioPlayerManager;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/* JADX INFO: loaded from: classes3.dex */
public class PlayStateViewModel extends AndroidViewModel {
    private MutableLiveData<PlayProgress> playProgressLiveData;
    private MutableLiveData<PlayState> playStateLiveData;

    public PlayStateViewModel(Application application) {
        super(application);
        this.playStateLiveData = new MutableLiveData<>(new PlayState());
        this.playProgressLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<PlayState> getPlayStateLiveData() {
        return this.playStateLiveData;
    }

    public MutableLiveData<PlayProgress> getPlayProgressLiveData() {
        return this.playProgressLiveData;
    }

    public void updatePlayState(PlayState playState) {
        this.playStateLiveData.postValue(playState);
    }

    public void updatePlayProgress(PlayProgress playProgress) {
        this.playProgressLiveData.postValue(playProgress);
    }

    public void updatePlayProgressImmediately(PlayProgress playProgress) {
        this.playProgressLiveData.setValue(playProgress);
    }
}
