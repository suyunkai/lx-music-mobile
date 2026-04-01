package com.wanos.media.util;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.ZeroApplication;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.zero_p.R;
import com.wanos.zero.ZeroAudioPlayerTools;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AlarmTools {
    private static final String DEFAULT_ALARM_NAME = "zero_default_alarm.m4a";
    private static final String TAG = "AlarmTools";

    public interface IDataLoadCallback {
        void onSuccess(List<BellsEntity.BellsInfoEntity> list);
    }

    public static void playAlarmMusic() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() { // from class: com.wanos.media.util.AlarmTools.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public String doInBackground() throws Throwable {
                String strFindAlarmPath = ZeroSettingMateData.findAlarmPath();
                if (!FileUtils.isFileExists(strFindAlarmPath)) {
                    ZeroLogcatTools.d(AlarmTools.TAG, "onPlayAlarmMusic: 默认音源未缓存，准备缓存默认音源");
                    strFindAlarmPath = AlarmTools.initDefaultAlarmToLocal();
                }
                ZeroLogcatTools.d(AlarmTools.TAG, "onPlayAlarmMusic: 音源准备就绪");
                return strFindAlarmPath;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(String str) {
                ZeroLogcatTools.d(AlarmTools.TAG, "onPlayAlarmMusic: 开始播放闹铃，文件路径=" + str);
                ZeroAudioPlayerTools.getInstance().onMediaLaunch(new File(str), true);
            }
        });
    }

    public static void stopAlarmMusic() {
        ZeroAudioPlayerTools.getInstance().onMediaStop(0);
    }

    public static void pauseAlarmMusic() {
        ZeroAudioPlayerTools.getInstance().onMediaPause();
    }

    public static void resumeAlarmMusic() {
        ZeroAudioPlayerTools.getInstance().onMediaResume();
    }

    public static void onLoadAlarmData(final IDataLoadCallback iDataLoadCallback) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<BellsEntity.BellsInfoEntity>>() { // from class: com.wanos.media.util.AlarmTools.2
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<BellsEntity.BellsInfoEntity> doInBackground() throws Throwable {
                List<BellsEntity.BellsInfoEntity> netAlarmData = AlarmTools.getNetAlarmData();
                for (int i = 0; i < netAlarmData.size(); i++) {
                    netAlarmData.get(i).setCache(MusicCacheUtils.isCache(netAlarmData.get(i).getPath()));
                }
                String strInitDefaultAlarmToLocal = AlarmTools.initDefaultAlarmToLocal();
                if (netAlarmData.isEmpty()) {
                    netAlarmData.add(AlarmTools.getDefaultAlarm(strInitDefaultAlarmToLocal));
                } else {
                    netAlarmData.add(0, AlarmTools.getDefaultAlarm(strInitDefaultAlarmToLocal));
                }
                return netAlarmData;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<BellsEntity.BellsInfoEntity> list) {
                iDataLoadCallback.onSuccess(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String initDefaultAlarmToLocal() {
        File file = new File(getDefaultAlarmCacheFilePath(DEFAULT_ALARM_NAME));
        if (!FileUtils.isFileExists(file)) {
            ZeroLogcatTools.d(TAG, "initDefaultAlarmToLocal: 默认音源不存在，开始复制默认音源到缓存");
            try {
                InputStream inputStreamOpenRawResource = ZeroApplication.getApplication().getResources().openRawResource(R.raw.music_default_album);
                try {
                    OutputStream outputStreamNewOutputStream = Files.newOutputStream(file.toPath(), new OpenOption[0]);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamOpenRawResource.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            outputStreamNewOutputStream.write(bArr, 0, i);
                        }
                        if (outputStreamNewOutputStream != null) {
                            outputStreamNewOutputStream.close();
                        }
                        if (inputStreamOpenRawResource != null) {
                            inputStreamOpenRawResource.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (IOException unused) {
                ZeroLogcatTools.e(TAG, "initDefaultAlarmToLocal: 闹铃文件复制失败");
            }
            ZeroLogcatTools.d(TAG, "initDefaultAlarmToLocal: 默认音源复制结束");
        }
        if (StringUtils.isEmpty(ZeroSettingMateData.findAlarmPath())) {
            ZeroLogcatTools.d(TAG, "initDefaultAlarmToLocal: 设置闹铃播放默认音源");
            ZeroSettingMateData.insertAlarmPath(file.getAbsolutePath());
        }
        return file.getAbsolutePath();
    }

    private static String getDefaultAlarmCacheFilePath(String str) {
        return MusicCacheUtils.initAudioCachePath() + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<BellsEntity.BellsInfoEntity> getNetAlarmData() {
        BaseEntity<BellsEntity> bellsListSync = HttpTools.getInstance().getBellsListSync();
        ArrayList arrayList = new ArrayList();
        if (bellsListSync == null) {
            ZeroLogcatTools.e(TAG, "getNetAlarmData: 接口请求失败");
            return arrayList;
        }
        BellsEntity data = bellsListSync.getData();
        if (data == null) {
            ZeroLogcatTools.e(TAG, "getNetAlarmData: 接口数据错误");
            return arrayList;
        }
        List<BellsEntity.BellsInfoEntity> bellList = data.getBellList();
        if (bellList == null || bellList.isEmpty()) {
            ZeroLogcatTools.e(TAG, "getNetAlarmData: 接口未返回闹铃数据");
            return arrayList;
        }
        arrayList.addAll(bellList);
        if (CacheTools.toObjectLocal(ZeroApplication.getApplication(), TAG, GsonUtils.toJson(bellsListSync.getData()))) {
            ZeroLogcatTools.d(TAG, "getNetAlarmData: 缓存数据更新成功");
            ZeroSettingMateData.setNowDate();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BellsEntity.BellsInfoEntity getDefaultAlarm(String str) {
        BellsEntity.BellsInfoEntity bellsInfoEntity = new BellsEntity.BellsInfoEntity();
        bellsInfoEntity.setId(-1);
        bellsInfoEntity.setName(StringUtils.getString(R.string.default_sound));
        bellsInfoEntity.setPath(str);
        bellsInfoEntity.setCache(true);
        bellsInfoEntity.setVip(false);
        bellsInfoEntity.setDownloading(false);
        bellsInfoEntity.setCoverImg("");
        return bellsInfoEntity;
    }
}
