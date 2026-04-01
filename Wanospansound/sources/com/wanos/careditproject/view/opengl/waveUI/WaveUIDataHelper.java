package com.wanos.careditproject.view.opengl.waveUI;

import android.util.ArrayMap;
import com.google.gson.Gson;
import com.wanos.careditproject.model.server.BallRoute;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class WaveUIDataHelper {
    public static int getTrackCount() {
        return DataHelpAudioTrack.getTrackList().size();
    }

    public static long[] getTrackIdList() {
        List<TrackItemModel> trackList = DataHelpAudioTrack.getTrackList();
        if (trackList == null) {
            return null;
        }
        long[] jArr = new long[trackList.size()];
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            if (trackItemModel != null) {
                jArr[i] = trackItemModel.getID();
            } else {
                jArr[i] = 0;
            }
        }
        return jArr;
    }

    public static String getClipListByTrackIndex(int i) {
        try {
            return new Gson().toJson(DataHelpAudioTrack.getClipListByTrackIndex(i));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getBallPosByTrackIndex(int i) {
        ArrayMap arrayMap = new ArrayMap();
        for (Map.Entry<String, BallRoute> entry : DataHelpAudioTrack.getBallRouteMap(i).entrySet()) {
            String key = entry.getKey();
            BallRoute value = entry.getValue();
            if (value.getStart() != value.getEnd()) {
                arrayMap.put(key, new BallRouteNativeModel(value.getId(), value.getStart(), value.getEnd(), value.getBallRouteName()));
            }
        }
        try {
            return new Gson().toJson(arrayMap);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getColorIndexByTrackIndex(int i) {
        TrackItemModel trackByIndex;
        if (i < 0 || (trackByIndex = DataHelpAudioTrack.getTrackByIndex(i)) == null) {
            return 0;
        }
        if (DataHelpAudioTrack.trackIsPlay(i)) {
            return trackByIndex.getColor_index();
        }
        return -2;
    }

    public static boolean trackIsPlay(int i) {
        return DataHelpAudioTrack.trackIsPlay(i);
    }

    public static boolean setClipToTrack(String str, int i, long j, long j2) {
        EditingUtils.log("WaveUIHelper id=" + str + ",rowNum=" + i + ",start=" + j);
        DataHelpAudioTrack.DataHelpResult clipToTrack = DataHelpAudioTrack.setClipToTrack(str, i, j < 0 ? 0L : j, j2);
        if (!clipToTrack.isSuccess() && clipToTrack.getErrMsg() != null && !clipToTrack.getErrMsg().equals("")) {
            ToastUtil.showMsg(clipToTrack.getErrMsg());
        }
        return clipToTrack.isSuccess();
    }

    public static boolean setBallPosToTrack(String str, int i, long j) {
        if (j < 0) {
            j = 0;
        }
        return DataHelpAudioTrack.setBallPosToTrack(str, i, j);
    }
}
