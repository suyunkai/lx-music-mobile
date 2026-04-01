package com.flyme.auto.music.source;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import com.ecarx.eas.sdk.mediacenter.MediaCenterAPI;
import com.flyme.auto.music.source.MusicSourceRepository;
import com.flyme.auto.music.source.usage.MusicSourceType;
import com.flyme.auto.music.source.usage.MusicSourceUsageConstants;
import com.flyme.auto.music.source.usage.MusicSourceUsageManager;
import com.flyme.auto.music.source.util.BitmapUtil;
import com.flyme.auto.music.source.util.IconStorage;
import com.flyme.auto.music.source.util.MusicSourceLog;
import com.flyme.auto.music.source.util.SystemProperties;
import ecarx.xsf.mediacenter.session.AppSourceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MusicSourceRepository {
    private static final int MSG_NOTIFY_CALLBACK = 5000;
    private static final int MSG_UPDATE_ICON = 5001;
    private static final String TAG = "MusicSource-Repository";
    private final Context context;
    private Thread fetchDataThread;
    private boolean isFrontDhu;
    private MediaCenterAPI mMediaCenterApi;
    private Thread updateIconThread;
    private Handler handler = null;
    private final Uri uri = Uri.parse("content://com.flyme.auto.mediacontrol.provider/AppSourceList");
    private int dayNightMode = 0;
    private boolean isSupportMultiUser = false;
    private final ContentObserver contentObserver = new AnonymousClass1(this.handler);
    private final List<MusicSourceCallback> callbackList = new ArrayList();
    private List<MusicSourceEntity> musicSourceList = new ArrayList();

    public interface MusicSourceCallback {
        void onMusicSourceChanged(List<MusicSourceEntity> list);
    }

    /* JADX INFO: renamed from: com.flyme.auto.music.source.MusicSourceRepository$1, reason: invalid class name */
    class AnonymousClass1 extends ContentObserver {
        AnonymousClass1(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            MusicSourceLog.d(MusicSourceRepository.TAG, "contentObserver onChange");
            MusicSourceRepository.this.startFetchDataThread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m236xbeed2191();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onChange$0$com-flyme-auto-music-source-MusicSourceRepository$1, reason: not valid java name */
        /* synthetic */ void m236xbeed2191() {
            MusicSourceRepository.this.fetchMusicSourceFromMediaControl();
        }
    }

    public MusicSourceRepository(Context context) {
        this.isFrontDhu = true;
        this.context = context;
        if (context.getResources().getBoolean(R.bool.is_support_double_dhu)) {
            this.isFrontDhu = SystemProperties.isFrontDhu();
        }
    }

    public void registerCallback(MusicSourceCallback musicSourceCallback) {
        this.callbackList.add(musicSourceCallback);
        try {
            this.context.getContentResolver().registerContentObserver(this.uri, false, this.contentObserver);
        } catch (Exception e) {
            e.printStackTrace();
            MusicSourceLog.w(TAG, "cannot registerContentObserver: " + e.getMessage());
            if (this.isSupportMultiUser) {
                startFetchDataThread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.fetchMusicSourceFromMediaCenter();
                    }
                });
            }
        }
    }

    public void unRegisterCallback(MusicSourceCallback musicSourceCallback) {
        this.callbackList.remove(musicSourceCallback);
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(5000);
            this.handler = null;
        }
    }

    private void notNullAppSourceList(String str) {
        List<MusicSourceEntity> musicSourceEntityList = toMusicSourceEntityList(str);
        if (musicSourceEntityList.isEmpty()) {
            notifyLatestMusicSource();
            return;
        }
        notifyMusicSourceCallback(musicSourceEntityList);
        saveLatestMusicSource(str);
        this.handler.removeMessages(5001);
        this.handler.sendEmptyMessageDelayed(5001, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchMusicSourceFromMediaControl() {
        MusicSourceLog.i(TAG, "fetchMusicSourceFromMediaControl");
        try {
            Cursor cursorQuery = this.context.getApplicationContext().getContentResolver().query(this.uri, null, null, null, null);
            try {
                if (cursorQuery != null) {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(0);
                        MusicSourceLog.i(TAG, "fetchMusicSourceFromMediaControl appSourceList: " + string);
                        if (string == null) {
                            notifyLatestMusicSource();
                        } else {
                            notNullAppSourceList(string);
                        }
                    } else {
                        notifyLatestMusicSource();
                    }
                } else {
                    MusicSourceLog.i(TAG, "fetchMusicSourceFromMediaControl cursor null");
                    if (this.isSupportMultiUser) {
                        fetchMusicSourceFromMediaCenter();
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            MusicSourceLog.w(TAG, "cannot fetchMusicSourceFromMediaControl: " + e.getMessage());
            if (this.isSupportMultiUser) {
                fetchMusicSourceFromMediaCenter();
            }
        }
    }

    /* JADX INFO: renamed from: com.flyme.auto.music.source.MusicSourceRepository$2, reason: invalid class name */
    class AnonymousClass2 extends Handler {
        AnonymousClass2(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 5000) {
                MusicSourceLog.d(MusicSourceRepository.TAG, "notifyMusicSourceCallback: " + MusicSourceRepository.this.callbackList.size());
                synchronized (MusicSourceRepository.this.callbackList) {
                    MusicSourceRepository.this.callbackList.forEach(new Consumer() { // from class: com.flyme.auto.music.source.MusicSourceRepository$2$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.f$0.m237x5673bd08((MusicSourceRepository.MusicSourceCallback) obj);
                        }
                    });
                }
                return;
            }
            if (message.what == 5001) {
                MusicSourceRepository.this.startUpdateIconThread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        this.f$0.m238xe3ae6e89();
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$handleMessage$0$com-flyme-auto-music-source-MusicSourceRepository$2, reason: not valid java name */
        /* synthetic */ void m237x5673bd08(MusicSourceCallback musicSourceCallback) {
            MusicSourceRepository musicSourceRepository = MusicSourceRepository.this;
            musicSourceCallback.onMusicSourceChanged(musicSourceRepository.filterValid(musicSourceRepository.musicSourceList));
        }

        /* JADX INFO: renamed from: lambda$handleMessage$1$com-flyme-auto-music-source-MusicSourceRepository$2, reason: not valid java name */
        /* synthetic */ void m238xe3ae6e89() throws Throwable {
            for (MusicSourceEntity musicSourceEntity : MusicSourceRepository.this.musicSourceList) {
                IconStorage.updateIconFromNet(MusicSourceRepository.this.context, musicSourceEntity.getImageUrl(), musicSourceEntity.getPackageName());
            }
        }
    }

    private Handler obtainHandler() {
        return new AnonymousClass2(Looper.getMainLooper());
    }

    private void notifyMusicSourceCallback(List<MusicSourceEntity> list) {
        if (this.handler == null) {
            this.handler = obtainHandler();
        }
        this.musicSourceList = supplementIcon(list);
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(5000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFetchDataThread(final Runnable runnable) {
        Thread thread = this.fetchDataThread;
        if (thread != null) {
            try {
                if (thread.isAlive()) {
                    MusicSourceLog.d(TAG, "startFetchDataThread is not null, interrupt now");
                    this.fetchDataThread.interrupt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.fetchDataThread = null;
        }
        Thread thread2 = new Thread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                MusicSourceRepository.lambda$startFetchDataThread$0(runnable);
            }
        });
        this.fetchDataThread = thread2;
        thread2.start();
    }

    static /* synthetic */ void lambda$startFetchDataThread$0(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            MusicSourceLog.d(TAG, "startFetchDataThread exception: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUpdateIconThread(final Runnable runnable) {
        Thread thread = this.updateIconThread;
        if (thread != null) {
            try {
                if (thread.isAlive()) {
                    MusicSourceLog.d(TAG, "updateIconThread is not null, interrupt now");
                    this.updateIconThread.interrupt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.updateIconThread = null;
        }
        Thread thread2 = new Thread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MusicSourceRepository.lambda$startUpdateIconThread$1(runnable);
            }
        });
        this.updateIconThread = thread2;
        thread2.start();
    }

    static /* synthetic */ void lambda$startUpdateIconThread$1(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            MusicSourceLog.d(TAG, "updateIconThread exception: " + e.getMessage());
        }
    }

    private List<MusicSourceEntity> supplementIcon(List<MusicSourceEntity> list) {
        list.forEach(new Consumer() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m235xcc5586a9((MusicSourceEntity) obj);
            }
        });
        return list;
    }

    /* JADX INFO: renamed from: lambda$supplementIcon$2$com-flyme-auto-music-source-MusicSourceRepository, reason: not valid java name */
    /* synthetic */ void m235xcc5586a9(MusicSourceEntity musicSourceEntity) {
        try {
            Bitmap icon = IconStorage.getIcon(this.context, musicSourceEntity.getImageUrl(), musicSourceEntity.getPackageName(), this.dayNightMode);
            musicSourceEntity.setIcon(BitmapUtil.toRoundBitmap(icon, icon.getWidth()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchSupportMusicSource(final boolean z) {
        startFetchDataThread(new Runnable() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m234x7b4ed969(z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$fetchSupportMusicSource$3$com-flyme-auto-music-source-MusicSourceRepository, reason: not valid java name */
    /* synthetic */ void m234x7b4ed969(boolean z) {
        MusicSourceLog.d(TAG, "fetchSupportMusicSource isForceRefresh=" + z);
        List<MusicSourceEntity> list = this.musicSourceList;
        if (z || list.isEmpty()) {
            notifyLatestMusicSource();
            fetchMusicSourceFromMediaControl();
        } else {
            notifyMusicSourceCallback(list);
        }
    }

    private void notifyLatestMusicSource() {
        MusicSourceLog.d(TAG, "notifyLatestMusicSource");
        String string = this.context.getSharedPreferences("SP_MUSIC_SOURCE", 0).getString("LATEST_MUSIC_SOURCE_V1", null);
        if (TextUtils.isEmpty(string)) {
            notifyMusicSourceCallback(fetchSupportMusicSourceDefault());
            return;
        }
        List<MusicSourceEntity> musicSourceEntityList = toMusicSourceEntityList(string);
        if (musicSourceEntityList.isEmpty()) {
            notifyMusicSourceCallback(fetchSupportMusicSourceDefault());
        } else {
            notifyMusicSourceCallback(musicSourceEntityList);
        }
    }

    private void saveLatestMusicSource(String str) {
        this.context.getSharedPreferences("SP_MUSIC_SOURCE", 0).edit().putString("LATEST_MUSIC_SOURCE_V1", str).apply();
    }

    private List<MusicSourceEntity> fetchSupportMusicSourceDefault() {
        MusicSourceLog.d(TAG, "fetchSupportMusicSourceDefault");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MusicSourceEntity(this.context.getString(R.string.name_qq_music), this.context.getString(R.string.pkg_qq_music), "", MusicSourceType.SOURCE_TYPE_ONLINE.name()));
        if (this.isFrontDhu) {
            arrayList.add(new MusicSourceEntity(this.context.getString(R.string.name_bluetooth_music), this.context.getString(R.string.pkg_bluetooth_music), "", MusicSourceType.SOURCE_TYPE_BT.name()));
            arrayList.add(new MusicSourceEntity(this.context.getString(R.string.name_usb_music), this.context.getString(R.string.pkg_usb_music), "", MusicSourceType.SOURCE_TYPE_USB.name()));
        }
        return arrayList;
    }

    private MusicSourceEntity analysisJSONObject(JSONObject jSONObject) throws JSONException {
        boolean zAdd;
        String string = jSONObject.getString("appName");
        String string2 = jSONObject.getString("iconUrl");
        String string3 = jSONObject.getString("packageName");
        int iOptInt = jSONObject.optInt("cpId", -1);
        JSONArray jSONArray = jSONObject.getJSONArray("mediaSourceTypeList");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = jSONArray.getInt(i);
            MusicSourceType[] musicSourceTypeArrValues = MusicSourceType.values();
            int length = musicSourceTypeArrValues.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    zAdd = false;
                    break;
                }
                MusicSourceType musicSourceType = musicSourceTypeArrValues[i3];
                if (musicSourceType.getType() == i2) {
                    zAdd = arrayList.add(musicSourceType.name());
                    break;
                }
                i3++;
            }
            if (!zAdd) {
                arrayList.add(String.valueOf(i2));
            }
        }
        return new MusicSourceEntity(string, string3, string2, iOptInt, String.join(",", arrayList));
    }

    private List<MusicSourceEntity> toMusicSourceEntityList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getBoolean("isAlwaysDisplay")) {
                    arrayList.add(analysisJSONObject(jSONObject));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void startMusicSource(Context context, MusicSourceEntity musicSourceEntity, int i, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = musicSourceEntity.getPackageName();
            if (this.isSupportMultiUser && Build.VERSION.SDK_INT >= 30) {
                Display display = context.getDisplay();
                int displayId = display != null ? display.getDisplayId() : 0;
                MusicSourceLog.d(TAG, "startMusicSource displayId: " + displayId);
                if (getMediaCenterApi() == null) {
                    MusicSourceLog.w(TAG, "startMusicSource mMediaCenterApi null");
                    return;
                }
                this.mMediaCenterApi.startActivityAsUser(packageName, displayId);
            } else {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
                if (launchIntentForPackage == null) {
                    MusicSourceLog.w(TAG, "startMusicSource intent is null: " + packageName);
                    return;
                } else {
                    launchIntentForPackage.addFlags(268435456);
                    context.startActivity(launchIntentForPackage);
                }
            }
            MusicSourceLog.d(TAG, "startMusicSource start " + packageName);
            reportMusicSourceClick(musicSourceEntity, i, str);
        } catch (Exception e) {
            MusicSourceLog.w(TAG, "startMusicSource " + musicSourceEntity.getPackageName() + " failed: " + e.getMessage());
        }
    }

    public void reportMusicSourceClick(MusicSourceEntity musicSourceEntity, int i, String str) {
        HashMap map = new HashMap();
        map.put(MusicSourceUsageConstants.MusicSourceUsageKey.SOUND_SOURCE_NAME, musicSourceEntity.getName());
        map.put(MusicSourceUsageConstants.MusicSourceUsageKey.SOUND_SOURCE_ID, Integer.valueOf(musicSourceEntity.getCpId()));
        map.put(MusicSourceUsageConstants.MusicSourceUsageKey.SOUND_SOURCE_POS, Integer.valueOf(i));
        map.put(MusicSourceUsageConstants.MusicSourceUsageKey.SOUND_SOURCE_TYPE, musicSourceEntity.getSourceType());
        map.put(MusicSourceUsageConstants.MusicSourceUsageKey.SOUND_SOURCE_SOURCE_ENTRANCE, str);
        MusicSourceUsageManager.reportEvent(MusicSourceUsageConstants.MusicSourceUsageEvent.EVENT_CLICK_SOUND_SOURCE, null, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MusicSourceEntity> filterValid(List<MusicSourceEntity> list) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(list);
        linkedHashSet.removeIf(new Predicate() { // from class: com.flyme.auto.music.source.MusicSourceRepository$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MusicSourceRepository.lambda$filterValid$4((MusicSourceEntity) obj);
            }
        });
        return new ArrayList(linkedHashSet);
    }

    static /* synthetic */ boolean lambda$filterValid$4(MusicSourceEntity musicSourceEntity) {
        return musicSourceEntity.getIcon() == null || TextUtils.isEmpty(musicSourceEntity.getName()) || TextUtils.isEmpty(musicSourceEntity.getPackageName());
    }

    public void setDayNightMode(int i) {
        this.dayNightMode = i;
    }

    public void setSupportMultiUser(boolean z) {
        MusicSourceLog.i(TAG, "setSupportMultiUser flag: " + z);
        this.isSupportMultiUser = z;
    }

    private MediaCenterAPI getMediaCenterApi() {
        if (this.mMediaCenterApi == null) {
            this.mMediaCenterApi = MediaCenterAPI.get(this.context.getApplicationContext());
        }
        return this.mMediaCenterApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchMusicSourceFromMediaCenter() {
        List<AppSourceInfo> customAppSourceInfo;
        MusicSourceLog.i(TAG, "fetchMusicSourceFromMediaCenter");
        try {
            JSONArray jSONArray = new JSONArray();
            if (getMediaCenterApi() != null) {
                customAppSourceInfo = this.mMediaCenterApi.getCustomAppSourceInfo();
            } else {
                MusicSourceLog.w(TAG, "fetchMusicSourceFromMediaCenter mMediaCenterApi is null");
                customAppSourceInfo = null;
            }
            if (customAppSourceInfo == null || customAppSourceInfo.isEmpty()) {
                MusicSourceLog.w(TAG, "fetchMusicSourceFromMediaCenter appSourceInfoList null");
            } else {
                for (AppSourceInfo appSourceInfo : customAppSourceInfo) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("appName", appSourceInfo.getAppName());
                    jSONObject.put("packageName", appSourceInfo.getPackageName());
                    jSONObject.put("iconUrl", appSourceInfo.getIconPath());
                    boolean z = true;
                    if (appSourceInfo.getPriorityLevel() != 1) {
                        z = false;
                    }
                    jSONObject.put("isAlwaysDisplay", z);
                    JSONArray jSONArray2 = new JSONArray();
                    int[] sourceTypeList = appSourceInfo.getSourceTypeList();
                    if (sourceTypeList != null && sourceTypeList.length > 0) {
                        int length = sourceTypeList.length;
                        for (int i = 0; i < length; i++) {
                            jSONArray2.put(i);
                        }
                    }
                    jSONObject.put("mediaSourceTypeList", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
            notNullAppSourceList(jSONArray.toString());
        } catch (Exception e) {
            MusicSourceLog.w(TAG, "fetchMusicSourceFromMediaCenter error: " + e.getMessage());
        }
    }
}
