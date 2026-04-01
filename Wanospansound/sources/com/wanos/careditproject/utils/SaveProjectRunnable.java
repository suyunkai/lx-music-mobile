package com.wanos.careditproject.utils;

import android.content.Context;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.careditproject.data.bean.ProjectTrackSaveBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectSaveResponse;
import com.wanos.careditproject.model.server.BallRoute;
import com.wanos.careditproject.model.server.RootModel;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class SaveProjectRunnable implements Runnable {
    private boolean isRunning = false;
    private String preSaveStr = "";
    private String saveProjectId = "";
    private long preSaveTime = 0;
    private List<BallRoute> saveBallRouteList = new ArrayList();

    public void setPreSaveTime() {
        this.preSaveTime = System.currentTimeMillis();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.isRunning = true;
        this.saveProjectId = EditingParams.getInstance().getProjectId();
        setPreSaveTime();
        while (this.isRunning) {
            try {
                Thread.sleep(5000L);
                if (this.isRunning) {
                    save();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save() {
        if (!PlayerUtils.isPlaying() && DataHelpAudioTrack.isToSaveServer() && WanOSRetrofitUtil.isNetConnect) {
            if (UploadFileUtils.isUploadFailed()) {
                UploadFileUtils.reStart();
            }
            String projectId = EditingParams.getInstance().getProjectId();
            if (!this.saveProjectId.equals(projectId)) {
                this.isRunning = false;
                return;
            }
            DataHelpAudioTrack.saveBefore();
            RootModel rootModel = DataHelpAudioTrack.getRootModel();
            if (rootModel == null) {
                return;
            }
            try {
                String json = new GsonBuilder().registerTypeAdapter(TrackItemModel.class, new TrackItemModelSerializer()).create().toJson(rootModel);
                setPreSaveTime();
                if (json.equals(this.preSaveStr) && this.saveBallRouteList.size() == 0) {
                    return;
                }
                if (json.equals("null")) {
                    EditingUtils.log("save null");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Gson gson = new Gson();
                for (int i = 0; i < this.saveBallRouteList.size(); i++) {
                    BallRoute ballRoute = this.saveBallRouteList.get(i);
                    arrayList.add(new ProjectTrackSaveBean(ballRoute.getTrackId(), ballRoute.getId(), gson.toJson(ballRoute)));
                }
                if (EditingUtils.isConnectServer()) {
                    Context context = null;
                    CreatorRetrofitUtil.editSave(projectId, json, new ResponseCallBack<EditProjectSaveResponse>(context) { // from class: com.wanos.careditproject.utils.SaveProjectRunnable.1
                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseSuccessful(EditProjectSaveResponse editProjectSaveResponse) {
                        }

                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseFailure(int i2, String str) {
                            ToastUtils.showShort(str);
                            if (i2 == 20005) {
                                EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                            }
                        }
                    });
                    if (arrayList.size() > 0) {
                        CreatorRetrofitUtil.editPosSave(projectId, arrayList, new ResponseCallBack<EditProjectSaveResponse>(context) { // from class: com.wanos.careditproject.utils.SaveProjectRunnable.2
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int i2, String str) {
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(EditProjectSaveResponse editProjectSaveResponse) {
                            }
                        });
                    }
                } else {
                    EditingUtils.saveFile(json, "wanosproject.json");
                    this.saveBallRouteList.clear();
                    this.preSaveStr = json;
                }
                this.saveBallRouteList.clear();
                this.preSaveStr = json;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
        save();
    }

    public class TrackItemModelSerializer implements JsonSerializer<TrackItemModel> {
        public TrackItemModelSerializer() {
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(TrackItemModel trackItemModel, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ID", Long.valueOf(trackItemModel.getID()));
            jsonObject.addProperty("Name", trackItemModel.getName());
            jsonObject.addProperty("Parent", Integer.valueOf(trackItemModel.getParent()));
            jsonObject.addProperty("IsPos", Boolean.valueOf(trackItemModel.getIsPos()));
            jsonObject.addProperty("IsMute", Boolean.valueOf(trackItemModel.getIsMute()));
            jsonObject.addProperty("IsShow", Boolean.valueOf(trackItemModel.getIsShow()));
            jsonObject.addProperty("IsSolo", Boolean.valueOf(trackItemModel.getIsSolo()));
            jsonObject.addProperty("Color", trackItemModel.getColor());
            jsonObject.addProperty("DB", Integer.valueOf(trackItemModel.getDB()));
            jsonObject.addProperty("SampleNum", Long.valueOf(trackItemModel.getSampleNum()));
            jsonObject.addProperty("NumChannels", Integer.valueOf(trackItemModel.getNumChannels()));
            jsonObject.addProperty("Usage", Integer.valueOf(trackItemModel.getUsage()));
            jsonObject.addProperty("Channel_int", Integer.valueOf(trackItemModel.getChannel_int()));
            jsonObject.addProperty("Channel_out", Integer.valueOf(trackItemModel.getChannel_out()));
            jsonObject.addProperty("Wrap_int", Integer.valueOf(trackItemModel.getWrap_int()));
            jsonObject.addProperty("Wrap_out", Integer.valueOf(trackItemModel.getWrap_out()));
            jsonObject.addProperty("colorIndex", Integer.valueOf(trackItemModel.getColor_index()));
            jsonObject.add("metaData", jsonSerializationContext.serialize(trackItemModel.getMetaData()));
            jsonObject.add("clips", jsonSerializationContext.serialize(trackItemModel.getClips()));
            jsonObject.add("BallRouteArr", jsonSerializationContext.serialize(trackItemModel.getBallRouteArr()));
            JsonObject jsonObject2 = new JsonObject();
            for (Map.Entry<String, BallRoute> entry : trackItemModel.getBallRouteMap().entrySet()) {
                jsonObject2.add(entry.getKey(), new JsonObject());
                BallRoute value = entry.getValue();
                if (value.getUpdateTime() > SaveProjectRunnable.this.preSaveTime) {
                    value.setTrackId(trackItemModel.getID());
                    SaveProjectRunnable.this.saveBallRouteList.add(value);
                }
            }
            jsonObject.add("BallRouteMap", jsonObject2);
            return jsonObject;
        }
    }
}
