package com.wanos.editmain.usb;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.constant.TimeConstants;
import com.ecarx.eas.sdk.mediacenter.SemanticsLevelOneType;
import com.just.agentweb.AgentActionFragment;
import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.editmain.usb.receiver.USBBroadcastReceiver;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class UsbHelper {
    private static final String TAG = "zt";
    private Activity context;
    private Runnable dataRunnable;
    HashMap<String, UsbDevice> deviceList;
    private List<File> fileList;
    private boolean isMounted;
    private boolean isUsbConnect;
    private USBBroadcastReceiver mUsbReceiver;
    private Handler payHandler;
    private HandlerThread pollThread;
    private Runnable statusRunnable;
    private USBBroadcastReceiver.UsbListener usbListener;
    private StorageVolume volume;

    private UsbHelper() {
        this.deviceList = new HashMap<>();
        this.fileList = new ArrayList();
    }

    public static UsbHelper getInstance() {
        return UsbHelperHolder.instance;
    }

    private static class UsbHelperHolder {
        private static final UsbHelper instance = new UsbHelper();

        private UsbHelperHolder() {
        }
    }

    public void init(Activity activity, USBBroadcastReceiver.UsbListener usbListener) {
        this.context = activity;
        if (this.usbListener == null) {
            this.usbListener = usbListener;
        }
        registerReceiver();
    }

    private void registerReceiver() {
        if (this.mUsbReceiver == null) {
            Log.i(TAG, "注册Usb监听广播");
            USBBroadcastReceiver uSBBroadcastReceiver = new USBBroadcastReceiver();
            this.mUsbReceiver = uSBBroadcastReceiver;
            uSBBroadcastReceiver.setUsbListener(this.usbListener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            intentFilter.addAction(USBBroadcastReceiver.ACTION_USB_PERMISSION);
            intentFilter.addAction(USBBroadcastReceiver.ACTION_USB_STATE);
            if (Build.VERSION.SDK_INT >= 33) {
                this.context.registerReceiver(this.mUsbReceiver, intentFilter, 4);
            } else {
                this.context.registerReceiver(this.mUsbReceiver, intentFilter);
            }
        }
    }

    public void setReceiverUsbListener(USBBroadcastReceiver.UsbListener usbListener) {
        USBBroadcastReceiver uSBBroadcastReceiver = this.mUsbReceiver;
        if (uSBBroadcastReceiver != null) {
            this.usbListener = usbListener;
            uSBBroadcastReceiver.setUsbListener(usbListener);
        } else {
            Log.i(TAG, "mUsbReceiver----广播为空");
        }
    }

    public HashMap<String, UsbDevice> requestStoragePermission(ActivityResultLauncher<Intent> activityResultLauncher) {
        if (Build.VERSION.SDK_INT >= 30) {
            if (Environment.isExternalStorageManager()) {
                Log.i(TAG, "有外部权限");
                setUsbConnect(true);
                this.usbListener.storagePermissionPass();
                return null;
            }
            Log.i(TAG, "没有外部权限");
            setUsbConnect(true);
            this.usbListener.storagePermissionPass();
            return null;
        }
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this.context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            setUsbConnect(true);
            this.usbListener.storagePermissionPass();
            return null;
        }
        ActivityCompat.requestPermissions(this.context, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, AgentActionFragment.REQUEST_CODE);
        return null;
    }

    public HashMap<String, UsbDevice> getDeviceList() {
        UsbManager usbManager;
        int i;
        Activity activity = this.context;
        if (activity != null && (usbManager = (UsbManager) activity.getSystemService("usb")) != null) {
            this.deviceList = usbManager.getDeviceList();
            StringBuilder sb = new StringBuilder("deviceList----");
            Object obj = this.deviceList;
            if (obj == null) {
                obj = "null";
            }
            Log.i(TAG, sb.append(obj).toString());
            HashMap<String, UsbDevice> map = this.deviceList;
            if (map != null && map.size() != 0) {
                if (Build.VERSION.SDK_INT >= 31) {
                    Log.i(TAG, "版本>=12");
                    i = SemanticsLevelOneType.NETRADIO;
                } else {
                    Log.d(TAG, "version=" + Build.VERSION.SDK_INT + ", strRelease=" + Build.VERSION.RELEASE);
                    Log.i(TAG, "版本<12");
                    i = 0;
                }
                PendingIntent broadcast = PendingIntent.getBroadcast(this.context, 0, new Intent(USBBroadcastReceiver.ACTION_USB_PERMISSION), i);
                for (UsbDevice usbDevice : this.deviceList.values()) {
                    if (usbDevice != null) {
                        int deviceClass = usbDevice.getDeviceClass();
                        Log.i(TAG, "deviceClass----" + deviceClass);
                        if (deviceClass == 0) {
                            UsbInterface usbInterface = usbDevice.getInterface(0);
                            Log.i(TAG, "anInterface----" + usbInterface);
                            int interfaceClass = usbInterface.getInterfaceClass();
                            Log.i(TAG, "设备真实名称----" + usbDevice.getProductName() + "----interfaceClass----" + interfaceClass);
                            if (interfaceClass == 8) {
                                if (!usbManager.hasPermission(usbDevice)) {
                                    usbManager.requestPermission(usbDevice, broadcast);
                                } else if (this.usbListener != null) {
                                    Log.i(TAG, "已有权限");
                                    this.usbListener.usbPermissionPass(usbDevice);
                                }
                                return this.deviceList;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Log.i(TAG, "device为空");
                    }
                }
            }
        }
        return null;
    }

    public Handler getPayHandler() {
        HandlerThread handlerThread = new HandlerThread("pollThread");
        this.pollThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.pollThread.getLooper());
        this.payHandler = handler;
        return handler;
    }

    public void startStatePoll() {
        clearHandler();
        getPayHandler();
        Runnable runnable = new Runnable() { // from class: com.wanos.editmain.usb.UsbHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m453lambda$startStatePoll$0$comwanoseditmainusbUsbHelper();
            }
        };
        this.statusRunnable = runnable;
        this.payHandler.post(runnable);
    }

    /* JADX INFO: renamed from: lambda$startStatePoll$0$com-wanos-editmain-usb-UsbHelper, reason: not valid java name */
    /* synthetic */ void m453lambda$startStatePoll$0$comwanoseditmainusbUsbHelper() {
        try {
            getStorageVolumeInfo();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void startDataPoll() {
        clearHandler();
        getPayHandler();
        Runnable runnable = new Runnable() { // from class: com.wanos.editmain.usb.UsbHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m452lambda$startDataPoll$1$comwanoseditmainusbUsbHelper();
            }
        };
        this.dataRunnable = runnable;
        this.payHandler.post(runnable);
    }

    /* JADX INFO: renamed from: lambda$startDataPoll$1$com-wanos-editmain-usb-UsbHelper, reason: not valid java name */
    /* synthetic */ void m452lambda$startDataPoll$1$comwanoseditmainusbUsbHelper() {
        try {
            getFileData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStorageVolumeInfo() {
        List<StorageVolume> storageVolumes = ((StorageManager) this.context.getSystemService("storage")).getStorageVolumes();
        if (storageVolumes == null || storageVolumes.size() <= 0) {
            return;
        }
        Log.i(TAG, "storageVolumes数量----" + storageVolumes.size());
        for (StorageVolume storageVolume : storageVolumes) {
            Log.i(TAG, "volume----" + storageVolume.toString());
            Log.i(TAG, "name:" + storageVolume.getDescription(this.context));
            String state = storageVolume.getState();
            Log.i(TAG, "status:" + state);
            Log.i(TAG, "isEmulated:" + storageVolume.isEmulated());
            boolean zIsRemovable = storageVolume.isRemovable();
            Log.i(TAG, "isRemovable:" + zIsRemovable);
            if (zIsRemovable) {
                if (state.equals("mounted")) {
                    this.volume = storageVolume;
                    setMounted(true);
                    this.payHandler.removeCallbacks(this.statusRunnable);
                    this.pollThread.quitSafely();
                    if (this.usbListener != null) {
                        Log.i(TAG, "挂载成功");
                        this.usbListener.onMountSuccessful();
                    }
                } else {
                    Log.i(TAG, "当前未挂载，再次查询");
                    this.payHandler.postDelayed(this.statusRunnable, 1000L);
                }
            } else {
                Log.i(TAG, "当前非可移除设备");
            }
        }
    }

    public void getFileData() {
        File file;
        if (this.volume == null || Build.VERSION.SDK_INT < 30) {
            return;
        }
        this.fileList.clear();
        String absolutePath = this.volume.getDirectory().getAbsolutePath();
        if (absolutePath.isEmpty()) {
            file = null;
        } else {
            file = new File(absolutePath);
            Log.i(TAG, "currentFolder----" + absolutePath);
            Log.i(TAG, "directory----" + file);
        }
        this.fileList = scanFiles(file, new FileFilter() { // from class: com.wanos.editmain.usb.UsbHelper.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isFile() && !file2.isHidden() && (file2.getName().endsWith(".wanos") || file2.getName().endsWith(".mp3") || file2.getName().endsWith(".wav") || file2.getName().endsWith(".aac") || file2.getName().endsWith(".m4a"));
            }
        });
        this.payHandler.removeCallbacks(this.dataRunnable);
        this.pollThread.quitSafely();
        if (this.usbListener != null) {
            List<File> list = this.fileList;
            if (list != null && list.size() > 0) {
                this.usbListener.getUsbDataSuccessful(this.fileList);
            } else {
                this.usbListener.getUsbDataFailed();
            }
        }
    }

    private List<File> scanFiles(File file, FileFilter fileFilter) {
        Log.i(TAG, "scanFiles()----" + file.getName() + "当前文件是否存在" + file.exists());
        if (file.exists()) {
            Log.i(TAG, "当前是文件----" + file.getName() + "是否为目录" + file.isDirectory());
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                Log.i(TAG, "文件列表----" + (fileArrListFiles != null ? Integer.valueOf(fileArrListFiles.length) : "null"));
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        scanFiles(file2, fileFilter);
                    }
                }
            } else {
                Log.i(TAG, "当前非目录");
                if (fileFilter.accept(file)) {
                    this.fileList.add(file);
                    Log.i(TAG, "过滤音频----" + file.getName() + "----过滤音频时长----" + timeParse(getAudioDuration(file)) + "\n\r");
                } else {
                    Log.i(TAG, "不满足文件过滤条件");
                }
            }
        } else {
            Log.i(TAG, "根目录文件不存在");
        }
        return this.fileList;
    }

    public List<MaterialTypeInfoBean> FileToMaterialDataInfo(List<File> list, List<MaterialTypeInfoBean> list2) {
        int i = 0;
        while (i < list.size()) {
            File file = list.get(i);
            MaterialTypeInfoBean materialTypeInfoBean = new MaterialTypeInfoBean();
            materialTypeInfoBean.setViewType(2);
            i++;
            materialTypeInfoBean.setM_id(i);
            materialTypeInfoBean.setTypeId(i);
            materialTypeInfoBean.setM_name(file.getName());
            materialTypeInfoBean.setCollect(false);
            materialTypeInfoBean.setTimeLen(timeParse(getAudioDuration(file)));
            materialTypeInfoBean.setUrlSrcWanos(file.getAbsolutePath());
            list2.add(materialTypeInfoBean);
        }
        return list2;
    }

    public int getAudioDuration(File file) {
        int duration = 0;
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            duration = mediaPlayer.getDuration();
            mediaPlayer.release();
            return duration;
        } catch (IOException e) {
            e.printStackTrace();
            return duration;
        }
    }

    public String timeParse(int i) {
        long j = i / TimeConstants.MIN;
        long j2 = ((long) (i % TimeConstants.MIN)) / 1000;
        String str = (j < 10 ? "0" : "") + j + ":";
        if (j2 < 10) {
            str = str + "0";
        }
        return str + new DecimalFormat("#.##").format(j2);
    }

    public void finishUsbHelper() {
        if (this.mUsbReceiver != null) {
            Log.i(TAG, "注销Usb监听广播");
            setMounted(false);
            if (this.fileList.size() > 0) {
                this.fileList.clear();
            }
            if (this.volume != null) {
                this.volume = null;
            }
            if (this.usbListener != null) {
                this.mUsbReceiver.setUsbListener(null);
                this.usbListener = null;
            }
            try {
                try {
                    this.context.unregisterReceiver(this.mUsbReceiver);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.mUsbReceiver = null;
            }
        }
    }

    public boolean isUsbConnect() {
        return this.isUsbConnect;
    }

    public void setUsbConnect(boolean z) {
        this.isUsbConnect = z;
    }

    public boolean isMounted() {
        return this.isMounted;
    }

    public void setMounted(boolean z) {
        this.isMounted = z;
    }

    public StorageVolume getVolume() {
        return this.volume;
    }

    private void clearHandler() {
        HandlerThread handlerThread = this.pollThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            Log.d("开始轮询", "停止上次轮询线程");
            this.pollThread.quitSafely();
        }
        if (this.payHandler != null) {
            Log.d("开始轮询", "移除上次Callbacks");
            this.payHandler.removeCallbacks(this.statusRunnable);
            this.payHandler.removeCallbacks(this.dataRunnable);
            this.payHandler.removeCallbacksAndMessages(null);
            this.payHandler = null;
        }
    }
}
