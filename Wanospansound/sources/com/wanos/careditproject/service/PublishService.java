package com.wanos.careditproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.media3.exoplayer.ExoPlayer;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.data.bean.EditProjectExportListBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectExportListResponse;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.event.EditProjectEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class PublishService extends Service {
    private List<EditProjectExportListBean.ExportProjectInfo> preExportList;
    private Thread thread;
    private boolean isStop = true;
    private int num = 0;
    private PublishBinder binder = new PublishBinder();

    public class PublishBinder extends Binder {
        public PublishBinder() {
        }

        public PublishService getService() {
            return PublishService.this;
        }
    }

    public List<EditProjectExportListBean.ExportProjectInfo> getPreExportList() {
        return this.preExportList;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        EditingUtils.log("PublishService onCreate");
        this.isStop = false;
        this.preExportList = null;
        Thread thread = new Thread(new Runnable() { // from class: com.wanos.careditproject.service.PublishService.1
            @Override // java.lang.Runnable
            public void run() {
                while (!PublishService.this.isStop) {
                    try {
                        Thread.sleep(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                        CreatorRetrofitUtil.getEditExportList(new ResponseCallBack<EditProjectExportListResponse>(PublishService.this) { // from class: com.wanos.careditproject.service.PublishService.1.1
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(EditProjectExportListResponse editProjectExportListResponse) {
                                boolean z;
                                if (editProjectExportListResponse.data != null) {
                                    List<EditProjectExportListBean.ExportProjectInfo> list = editProjectExportListResponse.data.getList();
                                    ArrayList arrayList = new ArrayList();
                                    if (PublishService.this.preExportList != null && PublishService.this.preExportList.size() > 0) {
                                        for (EditProjectExportListBean.ExportProjectInfo exportProjectInfo : PublishService.this.preExportList) {
                                            int i = 0;
                                            while (true) {
                                                if (i >= list.size()) {
                                                    z = true;
                                                    break;
                                                } else {
                                                    if (exportProjectInfo.getWorkId().equals(list.get(i).getWorkId())) {
                                                        z = false;
                                                        break;
                                                    }
                                                    i++;
                                                }
                                            }
                                            if (z) {
                                                arrayList.add(exportProjectInfo);
                                            }
                                        }
                                    }
                                    if (arrayList.size() > 0) {
                                        String projectId = "";
                                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                            EditProjectExportListBean.ExportProjectInfo exportProjectInfo2 = (EditProjectExportListBean.ExportProjectInfo) arrayList.get(i2);
                                            if (exportProjectInfo2 != null) {
                                                ToastUtils.showShort("作品：" + exportProjectInfo2.getTitle() + "发布完成！");
                                                projectId = exportProjectInfo2.getProjectId();
                                            }
                                        }
                                        ProjectInfo projectInfo = new ProjectInfo();
                                        projectInfo.setId(projectId);
                                        projectInfo.setTitle("");
                                        projectInfo.setPicture("");
                                        EventBus.getDefault().post(new ProjectChangeEvent(0, 1, projectInfo));
                                        EditProjectEvent editProjectEvent = new EditProjectEvent(EditProjectEvent.ProjectEventType.ProjectExported);
                                        editProjectEvent.param = arrayList;
                                        EventBus.getDefault().post(editProjectEvent);
                                    }
                                    if (list.size() == 0) {
                                        PublishService.this.preExportList = list;
                                        PublishService.this.isStop = true;
                                        PublishService.this.stopSelf();
                                    } else {
                                        PublishService.this.preExportList = editProjectExportListResponse.data.getList();
                                    }
                                }
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int i, String str) {
                                ToastUtils.showShort(str);
                            }
                        });
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        this.thread = thread;
        thread.start();
    }

    public int getNum() {
        return this.num;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.isStop = true;
        super.onDestroy();
    }
}
