package com.wanos.editmain.usb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.util.Log;
import com.wanos.editmain.usb.UsbHelper;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class USBBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_USB_PERMISSION = "com.wanos.media.USB_PERMISSION";
    public static final String ACTION_USB_STATE = "android.hardware.usb.action.USB_STATE";
    private static final String TAG = "zt";
    private UsbDevice usbDevice;
    private UsbListener usbListener;

    public interface UsbListener {
        default void getUsbDataFailed() {
        }

        default void getUsbDataSuccessful(List<File> list) {
        }

        default void insertUsb(UsbDevice usbDevice) {
        }

        default void onMountSuccessful() {
        }

        default void removeUsb(UsbDevice usbDevice) {
        }

        default void storagePermissionPass() {
        }

        default void usbPermissionFailed(UsbDevice usbDevice) {
        }

        default void usbPermissionPass(UsbDevice usbDevice) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                Log.i(TAG, "ACTION_USB_DEVICE_ATTACHED----插入");
                if (usbDevice != null && this.usbListener != null) {
                    Log.i(TAG, "插入U盘");
                    this.usbListener.insertUsb(usbDevice);
                    break;
                }
                break;
            case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                Log.i(TAG, "ACTION_USB_DEVICE_DETACHED----移除");
                if (usbDevice2 != null && this.usbListener != null) {
                    Log.i(TAG, "U盘已拔出");
                    this.usbListener.removeUsb(usbDevice2);
                    break;
                }
                break;
            case "android.hardware.usb.action.USB_STATE":
                this.usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                boolean booleanExtra = intent.getBooleanExtra("connected", false);
                Log.i(TAG, "ACTION_USB_STATE----已连接----" + intent.toString());
                if (booleanExtra && this.usbListener != null) {
                    Log.i(TAG, "插入U盘");
                    this.usbListener.insertUsb(this.usbDevice);
                    break;
                }
                break;
            case "com.wanos.media.USB_PERMISSION":
                this.usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                Log.i(TAG, "ACTION_USB_PERMISSION----权限验证");
                if (intent.getBooleanExtra("permission", false)) {
                    if (this.usbDevice != null) {
                        UsbHelper.getInstance().setUsbConnect(true);
                        if (this.usbListener != null) {
                            Log.i(TAG, "权限通过");
                            this.usbListener.usbPermissionPass(this.usbDevice);
                        }
                    } else {
                        Log.i(TAG, "usbDevice为空");
                    }
                    break;
                } else {
                    if (this.usbListener != null) {
                        Log.i(TAG, "权限未通过");
                        this.usbListener.usbPermissionFailed(this.usbDevice);
                    }
                    Log.i(TAG, "未获取到U盘权限");
                    break;
                }
                break;
        }
    }

    public void setUsbListener(UsbListener usbListener) {
        this.usbListener = usbListener;
    }

    public UsbListener getUsbListener() {
        return this.usbListener;
    }
}
