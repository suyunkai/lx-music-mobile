package com.wanos.careditproject.utils;

import android.graphics.PointF;
import android.util.ArrayMap;
import com.baidubce.BceConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.wanos.careditproject.event.UpdateEditUIEvent;
import com.wanos.careditproject.model.server.BallRoute;
import com.wanos.careditproject.model.server.BallRouteSample;
import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.model.server.DecodedModel;
import com.wanos.careditproject.model.server.OriginModel;
import com.wanos.careditproject.model.server.RootModel;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.model.server.TracksModel;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.cmd.CmdDeleteTrack;
import com.wanos.careditproject.utils.cmd.CmdListCache;
import com.wanos.careditproject.utils.cmd.CmdNewTrack;
import com.wanos.careditproject.utils.cmd.CmdRenameTrackName;
import com.wanos.careditproject.utils.cmd.CmdSetClipFadeType;
import com.wanos.careditproject.utils.cmd.CmdSetClipSize;
import com.wanos.careditproject.utils.cmd.CmdSetTrackM;
import com.wanos.careditproject.utils.cmd.CmdSetTrackS;
import com.wanos.careditproject.utils.cmd.CmdSwapTrackOrder;
import com.wanos.careditproject.utils.cmd.CmdTrackChange;
import com.wanos.careditproject.utils.cmd.CmdTracksChange;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class DataHelpAudioTrack {
    private static long baseClipId = 1;
    private static long baseTrackId = 0;
    private static boolean canUpload = true;
    private static List<Float> defaultBallPos = null;
    private static int minSampleNum = 4096;
    private static String recordTrackOld = null;
    private static long recordingId = -1;
    private static RootModel rootModel = null;
    private static int sTrackItemIndex = 0;
    private static boolean toSaveServer = true;
    private static UndoRedoStateCallback undoRedoStateCallback;
    private static PreViousClipInfo preClipInfoOfCopy = new PreViousClipInfo();
    private static PreViousClipInfo preClipInfoOfAddRes = new PreViousClipInfo();
    private static List<BallRoute> ballRoutes = new ArrayList();
    private static int tracksId = 1;
    private static String strOnlyMono = "当前轨道仅支持添加单声道素材";
    private static String strReSelectTrack = "选择的该轨有问题，请重新选择！";
    private static String strNoSpace = "移动位置失败！";
    private static String strTrackNoSpace = "轨道空间位置不足，请添加新轨道";
    private static ClipModel curSetClipModel = null;
    public static Map<Long, PlayingBallRoutTmp> playingBallRoutTmpMap = new ArrayMap();
    private static BallRoute preBallRoute = null;
    private static long firstSampleNum = -1;
    private static long lastSampleNum = -1;
    private static ContainBallInfo containBallInfo = null;
    private static BallRouteSample removeBallRouteSample = null;
    private static List<Float> preBallPos = null;
    private static BallRoute preContainBallRoute = null;
    private static boolean isBallPosStop = true;
    private static TrackItemModel newTrackItemModel = null;
    private static int newTrackItemModelIndex = 0;
    private static String sTrackItemModelStr = "";
    public static BallRoute preNewBallRoute = null;
    public static int testTrackIndex = 0;
    private static PointF posZero = new PointF(0.0f, 0.0f);
    private static List<String> sortedBallRouteList = null;
    private static BallRouteSampleTmp ballRouteSampleTmp = null;
    private static long saveMS = 0;

    public static class BallPos {
        public float x;
        public float y;
        public float z;
    }

    public static class BallRoutSampleTmp {
        public String ballRouteId;
        public List<String> ballRouteSampleKeyList;
        public long end;
        public int preSeekIndex;
        public long start;
    }

    public static class ContainBallInfo {
        public String ballId;
        public long end;
        public long start;
    }

    public static class FadeValue {
        public int fadein;
        public int fadeout;
    }

    public static class PlayingBallRoutSampleTmp {
        public String ballRoutSampleTmpId;
        public Iterator<Map.Entry<String, BallRouteSample>> curIterator;
        public Map.Entry<String, BallRouteSample> curSampleEntry;
        public boolean isNull = true;
        public Map.Entry<String, BallRouteSample> preSampleEntry;
    }

    public static class PlayingBallRoutTmp {
        public Map<String, BallRoutSampleTmp> ballRoutSampleTmpMap;
        public List<String> ballRouteKeyList;
        public boolean isInit;
        private List<Float> prePos;
        public long trackItemId;
        public PlayingBallRoutSampleTmp playingBallSampleTmp = new PlayingBallRoutSampleTmp();
        public long preSample = -1;
    }

    public static class PreViousClipInfo {
        public String clipId;
        public long end;
        public boolean isValid;
        public long start;
        public int trackIndex = -1;
    }

    public interface UndoRedoStateCallback {
        void stateChange();
    }

    public static void toSave() {
    }

    public static void initBallRoutes(String str) {
        EditingUtils.log("initBallRoutes ballRouteJson = " + str);
        List<BallRoute> list = (List) new Gson().fromJson(str, new TypeToken<List<BallRoute>>() { // from class: com.wanos.careditproject.utils.DataHelpAudioTrack.1
        }.getType());
        ballRoutes = list;
        if (list == null) {
            ballRoutes = new ArrayList();
        }
    }

    public static void setRootModel(RootModel rootModel2) {
        setRootModel(rootModel2, false);
    }

    public static void setRootModel(RootModel rootModel2, boolean z) {
        boolean z2;
        rootModel = rootModel2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            tracksId = tracksInfo.getID();
        }
        List<TrackItemModel> trackList = getTrackList();
        int i = 0;
        while (true) {
            if (i >= trackList.size()) {
                z2 = true;
                break;
            } else {
                if (trackList.get(i).getColor_index() != 0) {
                    z2 = false;
                    break;
                }
                i++;
            }
        }
        boolean z3 = (!z2 || trackList.size() < 3) ? z : true;
        for (int i2 = 0; i2 < trackList.size(); i2++) {
            TrackItemModel trackItemModel = trackList.get(i2);
            if (trackItemModel.getColor_index() == -1 || z3) {
                trackItemModel.setColor_index(i2);
            }
            if (baseTrackId < trackItemModel.getID()) {
                baseTrackId = trackItemModel.getID();
            }
            Iterator<Map.Entry<String, ClipModel>> it = trackItemModel.getClips().entrySet().iterator();
            while (it.hasNext()) {
                Long lValueOf = Long.valueOf(Long.parseLong(it.next().getKey()));
                if (baseClipId < lValueOf.longValue()) {
                    baseClipId = lValueOf.longValue();
                }
                if (!arrayList2.contains(lValueOf)) {
                    arrayList2.add(lValueOf);
                } else {
                    arrayList.add(lValueOf);
                }
            }
            List<Long> ballRouteArr = trackItemModel.getBallRouteArr();
            Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
            if (ballRouteMap == null) {
                trackItemModel.newBallRouteMap();
                ballRouteMap = trackItemModel.getBallRouteMap();
            }
            if (ballRouteArr == null) {
                trackItemModel.newBallRouteArr();
                ballRouteArr = trackItemModel.getBallRouteArr();
            }
            if (ballRouteArr.size() != ballRouteMap.size()) {
                for (int i3 = 0; i3 < ballRouteArr.size(); i3++) {
                    ballRouteMap.put(ballRouteArr.get(i3) + "", new BallRoute());
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (Map.Entry<String, BallRoute> entry : ballRouteMap.entrySet()) {
                Long lValueOf2 = Long.valueOf(Long.parseLong(entry.getKey()));
                boolean z4 = false;
                for (int i4 = 0; i4 < ballRoutes.size(); i4++) {
                    BallRoute ballRoute = ballRoutes.get(i4);
                    if (ballRoute.getTrackId() == trackItemModel.getID() && ballRoute.getId() == lValueOf2.longValue()) {
                        entry.setValue(ballRoute);
                        z4 = true;
                    }
                }
                if (!z4) {
                    arrayList3.add(entry.getKey());
                } else {
                    if (baseClipId < lValueOf2.longValue()) {
                        baseClipId = lValueOf2.longValue();
                    }
                    if (!arrayList2.contains(lValueOf2)) {
                        arrayList2.add(lValueOf2);
                    } else {
                        arrayList.add(lValueOf2);
                    }
                }
            }
            for (int i5 = 0; i5 < arrayList3.size(); i5++) {
                ballRouteMap.remove(arrayList3.get(i5));
            }
        }
        for (int i6 = 0; i6 < trackList.size(); i6++) {
            Map<String, BallRoute> ballRouteMap2 = trackList.get(i6).getBallRouteMap();
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                String str = ((Long) arrayList.get(i7)) + "";
                if (ballRouteMap2.containsKey(str)) {
                    ballRouteMap2.put(Long.valueOf(getBaseClipId()) + "", ballRouteMap2.get(str));
                    ballRouteMap2.remove(str);
                }
            }
        }
    }

    public static void saveBefore() {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            trackList.get(i).saveBallRouteArr();
        }
    }

    public static boolean isToSaveServer() {
        return toSaveServer;
    }

    public static RootModel getRootModel() {
        return rootModel;
    }

    public static synchronized long getBaseClipId() {
        long j;
        j = baseClipId + 1;
        baseClipId = j;
        return j;
    }

    public static long getBaseTrackId() {
        long j = baseTrackId + 1;
        baseTrackId = j;
        return j;
    }

    public static TracksModel getTracksInfo() {
        RootModel rootModel2 = rootModel;
        if (rootModel2 == null || rootModel2.getTracks() == null) {
            return null;
        }
        return rootModel.getTracks().get(0);
    }

    public static int getTotalDB() {
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            return tracksInfo.getDB();
        }
        return 0;
    }

    public static int getSampleRate() {
        RootModel rootModel2 = rootModel;
        if (rootModel2 == null) {
            return 48000;
        }
        return rootModel2.getSampleRate();
    }

    public static int getAllChannelNum() {
        int numChannels = 0;
        for (int i = 0; i < getTrackList().size(); i++) {
            TrackItemModel trackItemModel = getTrackList().get(i);
            if (trackItemModel != null) {
                numChannels += trackItemModel.getNumChannels() == 0 ? 2 : trackItemModel.getNumChannels();
            }
        }
        return numChannels;
    }

    public static int getRealChannelNum(long j) {
        int numChannels = 0;
        for (int i = 0; i < getTrackList().size(); i++) {
            TrackItemModel trackItemModel = getTrackList().get(i);
            if (trackItemModel != null && trackItemModel.getID() != j) {
                numChannels += trackItemModel.getNumChannels() == 0 ? 2 : trackItemModel.getNumChannels();
            }
        }
        return numChannels;
    }

    public static boolean checkTrackChannel(int i, int i2, boolean z) {
        EditingUtils.log("checkTrackChannel new = " + i + ", old = " + i2);
        if (!z && i2 == 0) {
            i2 = 2;
        }
        int i3 = i - i2;
        if (i3 <= 0) {
            return true;
        }
        int allChannelNum = getAllChannelNum();
        EditingUtils.log("checkTrackChannel2 total = " + allChannelNum + ", num = " + i3);
        return allChannelNum + i3 <= EditingUtils.getMaxChannelNum();
    }

    public static boolean checkTrackChannelV2(TrackItemModel trackItemModel, int i) {
        int realChannelNum = getRealChannelNum(trackItemModel.getID());
        EditingUtils.log("checkTrackChannel other=" + realChannelNum + ", channelNum=" + i);
        return realChannelNum + i <= EditingUtils.getMaxChannelNum();
    }

    public static List<TrackItemModel> getTrackList() {
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo == null || tracksInfo.getChildren() == null) {
            return Collections.emptyList();
        }
        return tracksInfo.getChildren();
    }

    public static int getTrackSize() {
        return getTrackList().size();
    }

    public static boolean isEmpty() {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList.size() == 0) {
            return true;
        }
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            if (trackItemModel.getClips() != null && !trackItemModel.getClips().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static long getMaxSampleNum() {
        RootModel rootModel2 = rootModel;
        if (rootModel2 != null && rootModel2.getSampleNum() > 0) {
            return rootModel.getSampleNum();
        }
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            return tracksInfo.getSampleNum();
        }
        return 1024L;
    }

    public static TrackItemModel getTrackByIndex(int i) {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList.size() <= i) {
            return null;
        }
        return trackList.get(i);
    }

    public static Map<String, ClipModel> getClipListByTrackIndex(int i) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null) {
            return new HashMap();
        }
        return trackByIndex.getClips();
    }

    public static boolean trackIsMono(int i) {
        Iterator<Map.Entry<String, ClipModel>> it = getClipListByTrackIndex(i).entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().getNumChannels() >= 2) {
                return false;
            }
        }
        return true;
    }

    public static int getTrackIndexByClipId(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            if (trackList.get(i).getClips().containsKey(str)) {
                return i;
            }
        }
        return -1;
    }

    public static TrackItemModel getTrackByClipId(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            if (trackItemModel.getClips().containsKey(str)) {
                return trackItemModel;
            }
        }
        return null;
    }

    public static ClipModel getClipById(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            Map<String, ClipModel> clips = trackList.get(i).getClips();
            if (clips.containsKey(str)) {
                return clips.get(str);
            }
        }
        return null;
    }

    public static Map<String, BallRoute> getBallRouteMap(int i) {
        return getTrackByIndex(i).getBallRouteMap();
    }

    public static List<String> getAllResList() {
        ArrayList arrayList = new ArrayList();
        Iterator<TrackItemModel> it = getTrackList().iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<String, ClipModel>> it2 = it.next().getClips().entrySet().iterator();
            while (it2.hasNext()) {
                OriginModel origin = it2.next().getValue().getOrigin();
                String url = origin.getUrl();
                if (origin != null && !origin.getUrl().equals("") && !arrayList.contains(url)) {
                    arrayList.add(origin.getUrl());
                }
            }
        }
        return arrayList;
    }

    public static int getTrackDB(int i) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex != null) {
            return trackByIndex.getDB();
        }
        return 0;
    }

    public static void setTotalDB(int i) {
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            tracksInfo.setDB(i);
        }
    }

    public static void setTrackDB(int i, int i2) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex != null) {
            trackByIndex.setDB(i2);
        }
        changeData();
    }

    public static void setMaxSampleNum(long j) {
        rootModel.setSampleNum(j);
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            tracksInfo.setSampleNum(j);
        }
    }

    public static void resetMaxSampleNum() {
        TracksModel tracksInfo = getTracksInfo();
        long jLongValue = 0;
        if (tracksInfo != null && tracksInfo.getChildren() != null) {
            List<TrackItemModel> children = tracksInfo.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Iterator<Map.Entry<String, ClipModel>> it = children.get(i).getClips().entrySet().iterator();
                while (it.hasNext()) {
                    Long lValueOf = Long.valueOf(it.next().getValue().getEnd());
                    if (lValueOf.longValue() > jLongValue) {
                        jLongValue = lValueOf.longValue();
                    }
                }
            }
            tracksInfo.setSampleNum(jLongValue);
        }
        rootModel.setSampleNum(jLongValue);
        EventBus.getDefault().post(new UpdateEditUIEvent(UpdateEditUIEvent.eventType.updateTime));
    }

    public static boolean swapTrackOrder(int i, int i2) {
        EditingUtils.log("cmd swapTrackOrder " + i + "," + i2);
        List<TrackItemModel> trackList = getTrackList();
        if (trackList == null || i < 0 || i2 < 0 || i >= trackList.size() || i >= trackList.size()) {
            return false;
        }
        Collections.swap(trackList, i, i2);
        changeData();
        return true;
    }

    public static List<String> getTrackIdList() {
        return (List) getTrackList().stream().map(new Function() { // from class: com.wanos.careditproject.utils.DataHelpAudioTrack$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DataHelpAudioTrack.lambda$getTrackIdList$0((TrackItemModel) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ String lambda$getTrackIdList$0(TrackItemModel trackItemModel) {
        return trackItemModel.getID() + "";
    }

    public static void changeTrackIdOrder(List<String> list) {
        TracksModel tracksInfo = getTracksInfo();
        if (tracksInfo != null) {
            ArrayList arrayList = new ArrayList();
            List<TrackItemModel> children = tracksInfo.getChildren();
            for (int i = 0; i < list.size(); i++) {
                Long lValueOf = Long.valueOf(Long.parseLong(list.get(i)));
                int i2 = 0;
                while (true) {
                    if (i2 < children.size()) {
                        TrackItemModel trackItemModel = children.get(i2);
                        if (lValueOf.longValue() == trackItemModel.getID()) {
                            arrayList.add(trackItemModel);
                            break;
                        }
                        i2++;
                    }
                }
            }
            tracksInfo.setChildren(arrayList);
        }
    }

    public static boolean setTrackUp(int i) {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList == null || i < 1) {
            return false;
        }
        int i2 = i - 1;
        new CmdSwapTrackOrder(Integer.valueOf(i), Integer.valueOf(i2)).save();
        Collections.swap(trackList, i2, i);
        changeData();
        return true;
    }

    public static boolean setTrackDown(int i) {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList == null || i >= trackList.size() - 1) {
            return false;
        }
        int i2 = i + 1;
        new CmdSwapTrackOrder(Integer.valueOf(i), Integer.valueOf(i2)).save();
        Collections.swap(trackList, i, i2);
        changeData();
        return true;
    }

    public static boolean trackSwap(int i, int i2) {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList == null || i2 >= trackList.size() - 1 || i >= trackList.size() - 1) {
            return false;
        }
        Collections.swap(trackList, i, i2);
        changeData();
        return true;
    }

    public static boolean renameTrackName(int i, String str, boolean z) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null) {
            return false;
        }
        if (z) {
            new CmdRenameTrackName(trackByIndex.getName(), str, i).save();
        }
        trackByIndex.setName(str);
        changeData();
        return true;
    }

    public static void addTrack(String str) {
        try {
            TrackItemModel trackItemModel = (TrackItemModel) new Gson().fromJson(str, TrackItemModel.class);
            if (trackItemModel != null) {
                getTrackList().add(trackItemModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertTrack(String str, int i) {
        try {
            TrackItemModel trackItemModel = (TrackItemModel) new Gson().fromJson(str, TrackItemModel.class);
            if (trackItemModel != null) {
                getTrackList().add(i, trackItemModel);
            }
            resetMaxSampleNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeTrack(final String str) {
        getTrackList().removeIf(new Predicate() { // from class: com.wanos.careditproject.utils.DataHelpAudioTrack$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DataHelpAudioTrack.lambda$removeTrack$1(str, (TrackItemModel) obj);
            }
        });
        resetMaxSampleNum();
    }

    static /* synthetic */ boolean lambda$removeTrack$1(String str, TrackItemModel trackItemModel) {
        return str.equals(new StringBuilder().append(trackItemModel.getID()).append("").toString());
    }

    public static void removeTrackByIndex(int i) {
        List<TrackItemModel> trackList = getTrackList();
        if (i < 0 || i >= trackList.size()) {
            return;
        }
        trackList.remove(i);
        resetMaxSampleNum();
        changeData();
    }

    public static long newTrack(boolean z) {
        TrackItemModel trackItemModelNewTrackItem = newTrackItem(z);
        if (trackItemModelNewTrackItem == null) {
            return -1L;
        }
        new CmdNewTrack(trackItemModelNewTrackItem.getID() + "", new Gson().toJson(trackItemModelNewTrackItem)).save();
        changeData();
        return trackItemModelNewTrackItem.getID();
    }

    public static TrackItemModel newRecordTrack(boolean z) {
        return newTrackItem(z);
    }

    private static TrackItemModel newTrackItem(boolean z) {
        return newTrackItem(z, -1);
    }

    private static TrackItemModel newTrackItem(boolean z, int i) {
        if (getAllChannelNum() >= EditingUtils.getMaxChannelNum()) {
            return null;
        }
        List<TrackItemModel> trackList = getTrackList();
        TrackItemModel trackItemModel = new TrackItemModel(tracksId);
        trackItemModel.setID(getBaseTrackId());
        if (z) {
            trackItemModel.setName("钢琴");
        } else {
            trackItemModel.setName("音频轨道");
        }
        trackItemModel.setColor_index(trackList.size());
        if (i < 0) {
            trackList.add(trackItemModel);
        } else {
            trackList.add(i, trackItemModel);
        }
        return trackItemModel;
    }

    public static boolean copyTrack(int i) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        List<TrackItemModel> trackList = getTrackList();
        if (trackByIndex == null || !checkTrackChannel(trackByIndex.getNumChannels(), 0, true)) {
            return false;
        }
        TrackItemModel trackItemModelM388clone = trackByIndex.m388clone();
        trackItemModelM388clone.setID(getBaseTrackId());
        if (trackByIndex.getClips() != null && trackByIndex.getClips().size() > 0) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            Iterator<Map.Entry<String, ClipModel>> it = trackByIndex.getClips().entrySet().iterator();
            while (it.hasNext()) {
                ClipModel clipModelM387clone = it.next().getValue().m387clone();
                clipModelM387clone.setID(getBaseClipId());
                linkedTreeMap.put(clipModelM387clone.getID() + "", clipModelM387clone);
            }
            trackItemModelM388clone.setClips(linkedTreeMap);
        }
        if (trackByIndex.getBallRouteMap() != null && trackByIndex.getBallRouteMap().size() > 0) {
            LinkedTreeMap linkedTreeMap2 = new LinkedTreeMap();
            Iterator<Map.Entry<String, BallRoute>> it2 = trackByIndex.getBallRouteMap().entrySet().iterator();
            while (it2.hasNext()) {
                BallRoute ballRouteM385clone = it2.next().getValue().m385clone();
                ballRouteM385clone.setId(getBaseClipId());
                linkedTreeMap2.put(ballRouteM385clone.getId() + "", ballRouteM385clone);
            }
            trackItemModelM388clone.setBallRouteMap(linkedTreeMap2);
        }
        trackList.add(trackItemModelM388clone);
        new CmdNewTrack(trackItemModelM388clone.getID() + "", new Gson().toJson(trackItemModelM388clone)).save();
        changeData();
        return true;
    }

    public static boolean deleteTrack(int i) {
        List<TrackItemModel> trackList = getTrackList();
        if (i >= trackList.size()) {
            return false;
        }
        TrackItemModel trackItemModelRemove = trackList.remove(i);
        resetMaxSampleNum();
        new CmdDeleteTrack(trackItemModelRemove.getID() + "", new Gson().toJson(trackItemModelRemove), i).save();
        changeData();
        return true;
    }

    public static boolean trackIsPlay(int i) {
        ArrayList arrayList = new ArrayList();
        List<TrackItemModel> trackList = getTrackList();
        boolean z = false;
        for (int i2 = 0; i2 < trackList.size(); i2++) {
            TrackItemModel trackItemModel = trackList.get(i2);
            if (trackItemModel != null) {
                if (!z) {
                    if (trackItemModel.getIsSolo()) {
                        arrayList.clear();
                        if (!trackItemModel.getIsMute()) {
                            arrayList.add(Integer.valueOf(i2));
                        }
                        z = true;
                    } else if (!trackItemModel.getIsMute()) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                } else if (trackItemModel.getIsSolo() && !trackItemModel.getIsMute()) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
        }
        return arrayList.contains(Integer.valueOf(i));
    }

    public static boolean setTrackM(int i, boolean z) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null) {
            return false;
        }
        trackByIndex.setIsMute(!trackByIndex.getIsMute());
        if (z) {
            new CmdSetTrackM(i).save();
        }
        changeData();
        return trackByIndex.getIsMute();
    }

    public static boolean setTrackS(int i, boolean z) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null) {
            return false;
        }
        trackByIndex.setIsSolo(!trackByIndex.getIsSolo());
        if (z) {
            new CmdSetTrackS(i).save();
        }
        changeData();
        return trackByIndex.getIsSolo();
    }

    public static void removeClip(int i, String str) {
        long baseClipId2 = getBaseClipId();
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex != null) {
            new ClipModel(baseClipId2, trackByIndex.getID());
            trackByIndex.getClips().remove(str);
        }
    }

    public static void insertClip(int i, String str) {
        ClipModel clipModel;
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null || (clipModel = (ClipModel) new Gson().fromJson(str, ClipModel.class)) == null) {
            return;
        }
        trackByIndex.getClips().put(clipModel.getID() + "", clipModel);
    }

    public static ClipModel newClip(TrackItemModel trackItemModel) {
        long baseClipId2 = getBaseClipId();
        ClipModel clipModel = new ClipModel(baseClipId2, trackItemModel.getID());
        trackItemModel.getClips().put(baseClipId2 + "", clipModel);
        changeData();
        return clipModel;
    }

    public static ClipModel recordInit(int i, long j) {
        toSaveServer = false;
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex == null) {
            return null;
        }
        recordTrackOld = new Gson().toJson(trackByIndex);
        ClipModel clipModelNewClip = newClip(trackByIndex);
        if (clipModelNewClip == null) {
            return null;
        }
        String str = clipModelNewClip.getID() + "";
        clipModelNewClip.setStart(j);
        clipModelNewClip.getOrigin().setUrl(str + "");
        clipModelNewClip.setNumChannels(1);
        OriginModel origin = clipModelNewClip.getOrigin();
        if (origin != null) {
            origin.setNumChannels(1);
        }
        DecodedModel decoded = clipModelNewClip.getDecoded();
        if (decoded != null) {
            decoded.setNumChannels(1);
        }
        changeNumChannel(trackByIndex, 1);
        recordingId = clipModelNewClip.getID();
        return clipModelNewClip;
    }

    private static void changeNumChannel(TrackItemModel trackItemModel, int i) {
        if (trackItemModel.getNumChannels() < i) {
            trackItemModel.setNumChannels(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean recordWrite(int r18, java.lang.String r19, long r20) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.DataHelpAudioTrack.recordWrite(int, java.lang.String, long):boolean");
    }

    public static boolean recordClose(String str, String str2) {
        changeClipUrl(str, str2);
        recordingId = -1L;
        return true;
    }

    public static void setToSaveServer(boolean z) {
        toSaveServer = z;
    }

    public static void changeClipUrl(String str, String str2) {
        List<TrackItemModel> trackList = getTrackList();
        ClipModel clipModel = null;
        int i = 0;
        TrackItemModel trackItemModel = null;
        int i2 = 0;
        while (true) {
            if (i2 >= trackList.size()) {
                break;
            }
            trackItemModel = trackList.get(i2);
            Map<String, ClipModel> clips = trackItemModel.getClips();
            if (clips.containsKey(str)) {
                clipModel = clips.get(str);
                i = i2;
                break;
            }
            i2++;
        }
        if (clipModel != null) {
            OriginModel origin = clipModel.getOrigin();
            if (origin != null) {
                origin.setUrl(str2);
            }
            DecodedModel decoded = clipModel.getDecoded();
            if (decoded != null) {
                decoded.setUrl(str2);
            }
            if (trackItemModel != null) {
                new CmdTrackChange(recordTrackOld, new Gson().toJson(trackItemModel), i).save();
            }
            resetMaxSampleNum();
        }
        changeData();
        toSaveServer = true;
    }

    public static void changeClipLocalPath(String str, String str2) {
        OriginModel origin;
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            Iterator<Map.Entry<String, ClipModel>> it = trackList.get(i).getClips().entrySet().iterator();
            while (it.hasNext()) {
                ClipModel value = it.next().getValue();
                if (value != null && (origin = value.getOrigin()) != null && origin.getUrl().equals(str)) {
                    origin.setUrl(str2);
                    DecodedModel decoded = value.getDecoded();
                    if (decoded != null) {
                        decoded.setUrl(str2);
                    }
                }
            }
        }
    }

    public static void removeClipLocalPath(String str) {
        OriginModel origin;
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            Iterator<Map.Entry<String, ClipModel>> it = trackList.get(i).getClips().entrySet().iterator();
            while (it.hasNext()) {
                ClipModel value = it.next().getValue();
                if (value != null && (origin = value.getOrigin()) != null && origin.getUrl().equals(str)) {
                    it.remove();
                }
            }
        }
    }

    public static void setClipSizeClose() {
        ClipModel clipModel;
        CmdSetClipSize currentCmd = CmdSetClipSize.getCurrentCmd();
        if (currentCmd != null && (clipModel = curSetClipModel) != null) {
            currentCmd.saveNew(clipModel);
        }
        curSetClipModel = null;
    }

    public static void setClipSize(String str, long j, long j2, long j3) {
        ClipModel clipById = getClipById(str);
        if (clipById != null) {
            clipById.setOriginStart(j3);
            clipById.setStart(j);
            clipById.setEnd(j2);
            clipById.setSampleNum(clipById.getEnd() - clipById.getStart());
            resetMaxSampleNum();
        }
    }

    public static boolean setClipStart(String str, long j) {
        TrackItemModel trackByClipId;
        long sampleNum = EditingUtils.formatSampleNum(j);
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return true;
        }
        if (curSetClipModel == null) {
            new CmdSetClipSize().saveOld(clipById);
            curSetClipModel = clipById;
        }
        long originStart = clipById.getOriginStart() + sampleNum;
        EditingUtils.log("setPcmViewStartOfEndOff setClipStart originStart = " + originStart);
        if (originStart < 0) {
            sampleNum = -clipById.getOriginStart();
            originStart = 0;
        }
        int fadeIn = clipById.getFadeIn() + clipById.getFadeOut();
        int i = minSampleNum;
        if (fadeIn < i) {
            fadeIn = i;
        }
        if (clipById.getSampleNum() - sampleNum < fadeIn || clipById.getStart() + sampleNum < 0 || (trackByClipId = getTrackByClipId(clipById.getID() + "")) == null || !clipStartIsValid(clipById.getStart() + sampleNum, clipById.getEnd(), trackByClipId.getClips(), str)) {
            return false;
        }
        clipById.setOriginStart(originStart);
        clipById.setStart(clipById.getStart() + sampleNum);
        clipById.setSampleNum(clipById.getEnd() - clipById.getStart());
        return true;
    }

    public static boolean setClipEnd(String str, long j) {
        ClipModel clipById = getClipById(str);
        if (clipById != null) {
            long sampleNum = clipById.getSampleNum() + j;
            if (clipById.getOrigin() != null) {
                if (curSetClipModel == null) {
                    new CmdSetClipSize().saveOld(clipById);
                    curSetClipModel = clipById;
                }
                if (clipById.getOriginStart() + sampleNum > clipById.getOrigin().getSampleNum()) {
                    sampleNum = clipById.getOrigin().getSampleNum() - clipById.getOriginStart();
                }
                TrackItemModel trackByClipId = getTrackByClipId(clipById.getID() + "");
                if (trackByClipId == null || !clipStartIsValid(clipById.getStart(), clipById.getStart() + sampleNum, trackByClipId.getClips(), str)) {
                    return false;
                }
                int fadeIn = clipById.getFadeIn() + clipById.getFadeOut();
                int i = minSampleNum;
                if (fadeIn < i) {
                    fadeIn = i;
                }
                long j2 = fadeIn;
                if (sampleNum < j2) {
                    sampleNum = j2;
                }
                clipById.setSampleNum(sampleNum);
                clipById.setEnd(clipById.getStart() + clipById.getSampleNum());
                resetMaxSampleNum();
            }
        }
        return true;
    }

    public static void updateTrack(int i, String str) {
        List<TrackItemModel> trackList = getTrackList();
        if (trackList.size() > i) {
            TrackItemModel trackItemModel = (TrackItemModel) new Gson().fromJson(str, TrackItemModel.class);
            if (trackItemModel != null) {
                trackList.set(i, trackItemModel);
                resetMaxSampleNum();
                return;
            }
            return;
        }
        TrackItemModel trackItemModel2 = (TrackItemModel) new Gson().fromJson(str, TrackItemModel.class);
        if (trackItemModel2 != null) {
            trackList.add(trackItemModel2);
            resetMaxSampleNum();
        }
    }

    public static boolean deleteClip(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            Map<String, ClipModel> clips = trackItemModel.getClips();
            if (clips.containsKey(str)) {
                String json = new Gson().toJson(trackItemModel);
                clips.remove(str);
                resetMaxSampleNum();
                new CmdTrackChange(json, new Gson().toJson(trackItemModel), i).save();
                changeData();
                return true;
            }
        }
        return false;
    }

    public static boolean splitClip(String str, long j) {
        long sampleNum = EditingUtils.formatSampleNum(j);
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            Map<String, ClipModel> clips = trackItemModel.getClips();
            if (clips.containsKey(str)) {
                ClipModel clipModel = clips.get(str);
                int fadeIn = clipModel.getFadeIn();
                int fadeOut = clipModel.getFadeOut();
                String json = new Gson().toJson(trackItemModel);
                ClipModel clipModelM387clone = clipModel.m387clone();
                clipModelM387clone.setID(getBaseClipId());
                clipModel.setSampleNum(sampleNum);
                clipModel.setEnd(clipModel.getStart() + clipModel.getSampleNum());
                clipModelM387clone.setStart(clipModelM387clone.getStart() + sampleNum);
                clipModelM387clone.setSampleNum(clipModelM387clone.getSampleNum() - sampleNum);
                clipModelM387clone.setEnd(clipModelM387clone.getStart() + clipModelM387clone.getSampleNum());
                clipModelM387clone.setOriginStart(clipModel.getOriginStart() + sampleNum);
                if (fadeIn != 0) {
                    clipModelM387clone.setFadeIn(0);
                    if (sampleNum < fadeIn) {
                        clipModel.setFadeIn((int) sampleNum);
                    }
                }
                if (fadeOut != 0) {
                    clipModel.setFadeOut(0);
                    if (clipModelM387clone.getSampleNum() < fadeOut) {
                        clipModelM387clone.setFadeOut((int) clipModelM387clone.getSampleNum());
                    }
                }
                clips.put(clipModelM387clone.getID() + "", clipModelM387clone);
                new CmdTrackChange(json, new Gson().toJson(trackItemModel), i).save();
                changeData();
                return true;
            }
        }
        return false;
    }

    public static DataHelpResult copyClip(String str, int i, long j) {
        long sampleNum = EditingUtils.formatSampleNum(j);
        ClipModel clipById = getClipById(str);
        if (clipById != null) {
            ClipModel clipModelM387clone = clipById.m387clone();
            clipModelM387clone.setID(getBaseClipId());
            TrackItemModel trackByIndex = getTrackByIndex(i);
            if (trackByIndex != null) {
                if (!checkTrackChannelV2(trackByIndex, clipModelM387clone.getNumChannels()) && trackByIndex.getNumChannels() != 2) {
                    return new DataHelpResult(false, strOnlyMono, 0L);
                }
                Map<String, ClipModel> clips = trackByIndex.getClips();
                if (clips != null) {
                    long clipStartV2 = getClipStartV2(sampleNum, clipModelM387clone.getSampleNum(), clips, "", sampleNum);
                    if (clipStartV2 >= 0) {
                        String json = new Gson().toJson(trackByIndex);
                        clipModelM387clone.setStart(clipStartV2);
                        long start = clipModelM387clone.getStart() + clipModelM387clone.getSampleNum();
                        clipModelM387clone.setEnd(start);
                        if (start > getMaxSampleNum()) {
                            setMaxSampleNum(start);
                        }
                        clips.put(clipModelM387clone.getID() + "", clipModelM387clone);
                        int numChannels = clipById.getNumChannels();
                        EditingUtils.log("copyClip channelNum = " + numChannels + ", toItem.getNumChannels()=" + trackByIndex.getNumChannels());
                        changeNumChannel(trackByIndex, numChannels);
                        resetMaxSampleNum();
                        new CmdTrackChange(json, new Gson().toJson(trackByIndex), i).save();
                        changeData();
                        return new DataHelpResult(true, "复制失败", 0L);
                    }
                }
            }
        }
        return new DataHelpResult(false, "复制失败", 2L);
    }

    public static DataHelpResult replaceClip(String str, String str2, int i, int i2, long j, String str3, String str4) {
        TrackItemModel trackItemModel;
        List<TrackItemModel> trackList = getTrackList();
        ClipModel clipModel = null;
        TrackItemModel trackItemModel2 = null;
        int i3 = 0;
        for (int i4 = 0; i4 < trackList.size(); i4++) {
            TrackItemModel trackItemModel3 = trackList.get(i4);
            Map<String, ClipModel> clips = trackItemModel3.getClips();
            if (clips.containsKey(str)) {
                clipModel = clips.get(str);
                i3 = i4;
                trackItemModel2 = trackItemModel3;
            }
        }
        if (clipModel != null && trackItemModel2 != null) {
            String json = new Gson().toJson(trackItemModel2);
            if (clipModel.getSampleNum() > j) {
                clipModel.setSampleNum(j);
                trackItemModel = trackItemModel2;
                clipModel.setEnd(clipModel.getStart() + j);
            } else {
                trackItemModel = trackItemModel2;
            }
            clipModel.setNumChannels(i2);
            clipModel.setOriginStart(0L);
            clipModel.setFadeIn(0);
            clipModel.setFadeOut(0);
            OriginModel origin = clipModel.getOrigin();
            DecodedModel decoded = clipModel.getDecoded();
            String fileExtension = getFileExtension(str2, str3);
            if (fileExtension.equals("")) {
                return new DataHelpResult(false, "替换失败！", 0L);
            }
            if (origin == null) {
                origin = new OriginModel();
            }
            origin.setUrl(str2);
            origin.setSampleNum(j);
            origin.setNumChannels(i2);
            origin.setSampleRate(i);
            origin.setName(str4);
            origin.setFormat(fileExtension);
            if (decoded == null) {
                decoded = new DecodedModel();
            }
            decoded.setUrl(str2);
            decoded.setSampleNum(j);
            decoded.setNumChannels(i2);
            decoded.setSampleRate(i);
            decoded.setName(str4);
            decoded.setFormat(fileExtension);
            resetMaxSampleNum();
            new CmdTrackChange(json, new Gson().toJson(trackItemModel), i3).save();
            return new DataHelpResult(true, "", clipModel.getID());
        }
        return new DataHelpResult(false, "替换失败！", 0L);
    }

    public static void clearPreClipInfoOfAddRes() {
        preClipInfoOfAddRes.trackIndex = -1;
    }

    public static DataHelpResult addTrackWithUrl(String str, String str2, int i, int i2, long j, int i3, long j2, String str3) {
        long sampleNum = EditingUtils.formatSampleNum(j2);
        getTrackList();
        if (i3 < 0) {
            return new DataHelpResult(false, "请选择一个音轨再操作！", 0L);
        }
        TrackItemModel trackByIndex = getTrackByIndex(i3);
        if (trackByIndex != null) {
            Map<String, ClipModel> clips = trackByIndex.getClips();
            if (!checkTrackChannelV2(trackByIndex, i2) && trackByIndex.getNumChannels() != 2) {
                return new DataHelpResult(false, strOnlyMono, 0L);
            }
            if (clips != null) {
                return newClipWithUrl(str, str2, i, i2, j, i3, str3, trackByIndex, clips, sampleNum, false);
            }
        }
        return new DataHelpResult(false, strReSelectTrack, 0L);
    }

    public static DataHelpResult addRecordWithUrl(String str, int i, int i2, long j, int i3, long j2, String str2) {
        int size;
        long j3;
        boolean z;
        TrackItemModel trackByIndex;
        Map<String, ClipModel> clips;
        long sampleNum = EditingUtils.formatSampleNum(j2);
        if (i3 < 0) {
            TrackItemModel trackItemModelNewRecordTrack = newRecordTrack(false);
            if (trackItemModelNewRecordTrack == null) {
                return new DataHelpResult(false, "轨道已达上限!", 0L);
            }
            trackByIndex = trackItemModelNewRecordTrack;
            size = getTrackList().size() - 1;
            j3 = 0;
            z = true;
        } else {
            size = i3;
            j3 = sampleNum;
            z = false;
            trackByIndex = getTrackByIndex(i3);
        }
        if (trackByIndex != null && (clips = trackByIndex.getClips()) != null) {
            return newClipWithUrl(str, null, i, i2, j, size, str2, trackByIndex, clips, j3, z);
        }
        return new DataHelpResult(false, strReSelectTrack, 0L);
    }

    private static DataHelpResult newClipWithUrl(String str, String str2, int i, int i2, long j, int i3, String str3, TrackItemModel trackItemModel, Map<String, ClipModel> map, long j2, boolean z) {
        int i4;
        TrackItemModel trackItemModelNewTrackItem;
        Map<String, ClipModel> clips;
        long j3;
        boolean z2;
        String strSubstring;
        long j4 = j2 + j;
        if (clipStartIsValid(j2, j4, map, "")) {
            i4 = i3;
            trackItemModelNewTrackItem = trackItemModel;
            clips = map;
            j3 = j2;
            z2 = z;
        } else {
            i4 = i3 + 1;
            trackItemModelNewTrackItem = newTrackItem(false, i4);
            if (trackItemModelNewTrackItem == null) {
                return new DataHelpResult(false, "轨道已达上限!", 0L);
            }
            clips = trackItemModelNewTrackItem.getClips();
            j3 = j4 > EditingUtils.getMaxSampleNum() ? 0L : j2;
            z2 = true;
        }
        String json = new Gson().toJson(trackItemModelNewTrackItem);
        if (clips.size() == 0) {
            strSubstring = str3.length() > EditingUtils.maxTrackNameLen ? str3.substring(0, EditingUtils.maxTrackNameLen) : str3;
            trackItemModelNewTrackItem.setName(strSubstring);
        } else {
            strSubstring = str3;
        }
        long baseClipId2 = getBaseClipId();
        boolean z3 = z2;
        ClipModel clipModel = new ClipModel(baseClipId2, trackItemModelNewTrackItem.getID());
        clipModel.setStart(j3);
        clipModel.setSampleNum(j);
        long j5 = j3 + j;
        clipModel.setEnd(j5);
        if (j5 > getMaxSampleNum()) {
            setMaxSampleNum(j5);
        }
        clipModel.setNumChannels(i2);
        clipModel.setOriginStart(0L);
        OriginModel origin = clipModel.getOrigin();
        String fileExtension = getFileExtension(str, str2);
        TrackItemModel trackItemModel2 = trackItemModelNewTrackItem;
        if (fileExtension.equals("")) {
            return new DataHelpResult(false, "添加失败！", 0L);
        }
        if (origin != null) {
            origin.setUrl(str);
            origin.setSampleNum(j);
            origin.setNumChannels(i2);
            origin.setSampleRate(i);
            origin.setName(strSubstring);
            origin.setFormat(fileExtension);
        }
        DecodedModel decoded = clipModel.getDecoded();
        if (decoded != null) {
            decoded.setUrl(str);
            decoded.setSampleNum(j);
            decoded.setNumChannels(i2);
            decoded.setSampleRate(i);
            decoded.setName(strSubstring);
            decoded.setFormat(fileExtension);
        }
        if (!preClipInfoOfAddRes.isValid) {
            preClipInfoOfAddRes.start = j3;
        }
        preClipInfoOfAddRes.end = j5;
        preClipInfoOfAddRes.trackIndex = i4;
        preClipInfoOfAddRes.clipId = baseClipId2 + "";
        clips.put(baseClipId2 + "", clipModel);
        changeNumChannel(trackItemModel2, i2);
        resetMaxSampleNum();
        new CmdTrackChange(json, new Gson().toJson(trackItemModel2), i4, z3).save();
        changeData();
        return new DataHelpResult(true, "", baseClipId2);
    }

    private static String getFileExtension(String str, String str2) {
        String[] strArrSplit = str.split("\\?");
        if (strArrSplit.length > 1) {
            str = strArrSplit[0];
        }
        String[] strArrSplit2 = str.split(BceConfig.BOS_DELIMITER);
        String str3 = strArrSplit2[strArrSplit2.length - 1];
        int iLastIndexOf = str3.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            return "";
        }
        str3.substring(0, iLastIndexOf);
        return str2 == null ? str3.substring(iLastIndexOf) : str2;
    }

    public static int getClipDB(String str) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return 0;
        }
        return clipById.getDB();
    }

    public static boolean setClipDB(String str, int i) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return false;
        }
        clipById.setDB(i);
        return true;
    }

    public static float getClipSpeed(String str) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return 1.0f;
        }
        return clipById.getSpeed();
    }

    public static boolean setClipSpeed(String str, float f) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return false;
        }
        clipById.setSpeed(f);
        return true;
    }

    public static FadeValue getClipFade(String str) {
        ClipModel clipById = getClipById(str);
        FadeValue fadeValue = new FadeValue();
        if (clipById != null) {
            long sampleNum = clipById.getSampleNum();
            fadeValue.fadein = (int) (((long) (clipById.getFadeIn() * 100)) / sampleNum);
            fadeValue.fadeout = 100 - ((int) (((long) (clipById.getFadeOut() * 100)) / sampleNum));
            if (fadeValue.fadeout == 0) {
                fadeValue.fadeout = 1;
            }
        } else {
            fadeValue.fadein = 0;
            fadeValue.fadeout = 100;
        }
        return fadeValue;
    }

    public static String getClipFadeType(String str) {
        ClipModel clipById = getClipById(str);
        return clipById != null ? clipById.getFadeInType() : "";
    }

    public static boolean setClipFade(String str, int i, int i2) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return true;
        }
        long sampleNum = clipById.getSampleNum();
        int fadeIn = (int) (((long) (clipById.getFadeIn() * 100)) / sampleNum);
        int fadeOut = 100 - ((int) (((long) (clipById.getFadeOut() * 100)) / sampleNum));
        int i3 = (int) ((((long) i) * sampleNum) / 100);
        int i4 = (int) ((sampleNum * ((long) (100 - i2))) / 100);
        if (fadeIn != i) {
            clipById.setFadeIn(i3);
        }
        if (fadeOut != i2) {
            clipById.setFadeOut(i4);
        }
        if (!clipById.getFadeInType().equals("")) {
            return true;
        }
        clipById.setFadeInType(EditingUtils.FADETYPE.FADETYPE0.getType());
        clipById.setFadeOutType(EditingUtils.FADETYPE.FADETYPE0.getType());
        return true;
    }

    public static void setClipFadeValue(String str, int i, int i2) {
        ClipModel clipById = getClipById(str);
        if (clipById != null) {
            clipById.setFadeIn(i);
            clipById.setFadeOut(i2);
        }
    }

    public static boolean setClipFadeType(String str, String str2) {
        ClipModel clipById = getClipById(str);
        if (clipById == null) {
            return true;
        }
        CmdSetClipFadeType cmdSetClipFadeType = new CmdSetClipFadeType();
        cmdSetClipFadeType.saveOld(str, clipById.getFadeInType(), clipById.getFadeOutType());
        clipById.setFadeInType(str2);
        clipById.setFadeOutType(str2);
        cmdSetClipFadeType.saveNew(clipById.getFadeInType(), clipById.getFadeOutType());
        changeData();
        return true;
    }

    public static void setClipFadeTypeValue(String str, String str2, String str3) {
        ClipModel clipById = getClipById(str);
        if (clipById != null) {
            clipById.setFadeInType(str2);
            clipById.setFadeOutType(str3);
        }
    }

    public static DataHelpResult setClipToTrack(String str, int i, long j, long j2) {
        int i2 = i;
        long sampleNum = EditingUtils.formatSampleNum(j);
        List<TrackItemModel> trackList = getTrackList();
        if (i2 >= trackList.size() || i2 < 0) {
            return new DataHelpResult(false, strReSelectTrack, 0L);
        }
        int i3 = 0;
        while (i3 < trackList.size()) {
            TrackItemModel trackItemModel = trackList.get(i3);
            Map<String, ClipModel> clips = trackItemModel.getClips();
            if (clips.containsKey(str)) {
                if (i3 == i2) {
                    String json = new Gson().toJson(trackItemModel);
                    ClipModel clipModel = clips.get(str);
                    long clipStartV2 = getClipStartV2(sampleNum, clipModel.getSampleNum(), clips, clipModel.getID() + "", j2);
                    if (clipStartV2 >= 0) {
                        clipModel.setStart(clipStartV2);
                        clipModel.setEnd(clipModel.getStart() + clipModel.getSampleNum());
                        resetMaxSampleNum();
                        new CmdTrackChange(json, new Gson().toJson(trackItemModel), i3).save();
                        changeData();
                        return new DataHelpResult(true, "", 0L);
                    }
                    return new DataHelpResult(false, "", 0L);
                }
                EditingUtils.log("setClipToTrack toTrackIndex=" + i2 + ", index=" + i3);
                TrackItemModel trackItemModel2 = trackList.get(i2);
                Map<String, ClipModel> clips2 = trackItemModel2.getClips();
                ClipModel clipModel2 = clips.get(str);
                if (!checkTrackChannelV2(trackItemModel2, clipModel2.getNumChannels()) && trackItemModel2.getNumChannels() != 2) {
                    return new DataHelpResult(false, strOnlyMono, 0L);
                }
                int i4 = i3;
                long clipStartV22 = getClipStartV2(sampleNum, clipModel2.getSampleNum(), clips2, "", j2);
                if (clipStartV22 >= 0) {
                    String json2 = new Gson().toJson(trackItemModel);
                    String json3 = new Gson().toJson(trackItemModel2);
                    clipModel2.setStart(clipStartV22);
                    clipModel2.setEnd(clipModel2.getStart() + clipModel2.getSampleNum());
                    clips2.put(str, clipModel2);
                    clips.remove(str);
                    changeNumChannel(trackItemModel2, clipModel2.getNumChannels());
                    resetMaxSampleNum();
                    new CmdTracksChange(json2, json3, new Gson().toJson(trackItemModel), new Gson().toJson(trackItemModel2), i4, i).save();
                    changeData();
                    return new DataHelpResult(true, "", 0L);
                }
                return new DataHelpResult(false, "", 0L);
            }
            i3++;
            i2 = i;
        }
        return new DataHelpResult(false, strReSelectTrack, 0L);
    }

    public static long getClipStartV2(long j, long j2, Map<String, ClipModel> map, String str, long j3) {
        long j4 = j + j2;
        if (j4 > EditingUtils.getMaxSampleNum()) {
            return -1L;
        }
        if (clipStartIsValid(j, j4, map, str)) {
            return j;
        }
        new ArrayList();
        Iterator<Map.Entry<String, ClipModel>> it = map.entrySet().iterator();
        boolean z = true;
        long sampleNumDown = -1;
        while (it.hasNext()) {
            ClipModel value = it.next().getValue();
            if (!str.equals(value.getID() + "")) {
                if (sampleNumDown != -1) {
                    if (Math.abs(value.getStart() - j3) < Math.abs(sampleNumDown - j3)) {
                        sampleNumDown = value.getStart();
                        z = true;
                    }
                    if (Math.abs(value.getEnd() - j3) < Math.abs(sampleNumDown - j3)) {
                        sampleNumDown = EditingUtils.formatSampleNumUp(value.getEnd());
                        z = false;
                    }
                } else if (Math.abs(value.getStart() - j3) < Math.abs(value.getEnd() - j3)) {
                    sampleNumDown = value.getStart();
                    z = true;
                } else {
                    sampleNumDown = EditingUtils.formatSampleNumUp(value.getEnd());
                    z = false;
                }
            }
        }
        if (sampleNumDown != -1) {
            if (z) {
                sampleNumDown = EditingUtils.formatSampleNumDown(sampleNumDown - j2);
            }
            if (sampleNumDown >= 0) {
                if (clipStartIsValid(sampleNumDown, sampleNumDown + j2, map, str)) {
                    if (sampleNumDown != EditingUtils.formatSampleNum(sampleNumDown)) {
                        EditingUtils.log("leiErr newStart = " + sampleNumDown + ", format = " + EditingUtils.formatSampleNum(sampleNumDown));
                    }
                    return sampleNumDown;
                }
            }
        }
        return -1L;
    }

    protected static long getClipStart(long j, long j2, Map<String, ClipModel> map, String str) {
        long sampleNum = EditingUtils.formatSampleNum(j);
        if (clipStartIsValid(sampleNum, sampleNum + j2, map, str)) {
            return sampleNum;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, ClipModel>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            ClipModel value = it.next().getValue();
            if (!str.equals(Long.valueOf(value.getID())) && value.getEnd() > sampleNum) {
                arrayList.add(Long.valueOf(value.getEnd()));
            }
        }
        arrayList.sort(new Comparator<Long>() { // from class: com.wanos.careditproject.utils.DataHelpAudioTrack.2
            @Override // java.util.Comparator
            public int compare(Long l, Long l2) {
                return (int) (l.longValue() - l2.longValue());
            }
        });
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            long jLongValue = ((Long) it2.next()).longValue();
            if (clipStartIsValid(jLongValue, jLongValue + j2, map, str)) {
                return jLongValue;
            }
        }
        return -1L;
    }

    protected static DataHelpResult clipStartIsValidV2(long j, long j2, Map<String, ClipModel> map, String str) {
        if (j2 > EditingUtils.getMaxSampleNum()) {
            return new DataHelpResult(false, strTrackNoSpace, 0L);
        }
        for (Map.Entry<String, ClipModel> entry : map.entrySet()) {
            ClipModel value = entry.getValue();
            if (!entry.getKey().equals(str)) {
                if (EditingUtils.containPos(value.getStart(), value.getEnd(), j, 0)) {
                    return new DataHelpResult(false, "轨道位置已占用，请选择空白区域添加", 0L);
                }
                if (EditingUtils.containPos(value.getStart(), value.getEnd(), j2, 1) || EditingUtils.containPos(j, j2, value.getStart(), 0)) {
                    return new DataHelpResult(false, strTrackNoSpace, 0L);
                }
            }
        }
        return new DataHelpResult(true, "", 0L);
    }

    protected static boolean clipStartIsValid(long j, long j2, Map<String, ClipModel> map, String str) {
        if (j2 > EditingUtils.getMaxSampleNum()) {
            return false;
        }
        for (Map.Entry<String, ClipModel> entry : map.entrySet()) {
            ClipModel value = entry.getValue();
            if (!entry.getKey().equals(str) && (EditingUtils.containPos(value.getStart(), value.getEnd(), j, 0) || EditingUtils.containPos(value.getStart(), value.getEnd(), j2, 1) || EditingUtils.containPos(j, j2, value.getStart(), 0))) {
                return false;
            }
        }
        return true;
    }

    protected static ClipModel getContainClip(long j, long j2, Map<String, ClipModel> map, String str) {
        for (Map.Entry<String, ClipModel> entry : map.entrySet()) {
            ClipModel value = entry.getValue();
            if (!entry.getKey().equals(str) && (EditingUtils.containPos(value.getStart(), value.getEnd(), j, 0) || EditingUtils.containPos(value.getStart(), value.getEnd(), j2, 1) || EditingUtils.containPos(j, j2, value.getStart(), 0))) {
                return value;
            }
        }
        return null;
    }

    protected static long moveBallRouteIsValid(String str, BallRoute ballRoute, long j, Map<String, BallRoute> map) {
        Long lValueOf = Long.valueOf(ballRoute.getStart());
        Long lValueOf2 = Long.valueOf(ballRoute.getEnd());
        Long lValueOf3 = Long.valueOf(lValueOf2.longValue() + (j - lValueOf.longValue()));
        if (lValueOf3.longValue() > EditingUtils.getMaxSampleNum()) {
            return -1L;
        }
        long jMoveBallRouteIsValid2 = moveBallRouteIsValid2(str, ballRoute, j, lValueOf3.longValue(), map);
        if (jMoveBallRouteIsValid2 == j) {
            return j;
        }
        long sampleNumUp4096 = EditingUtils.formatSampleNumUp4096(jMoveBallRouteIsValid2);
        if (moveBallRouteIsValid2(str, ballRoute, sampleNumUp4096, Long.valueOf(lValueOf2.longValue() + (sampleNumUp4096 - lValueOf.longValue())).longValue(), map) == sampleNumUp4096) {
            return sampleNumUp4096;
        }
        return -1L;
    }

    protected static long moveBallRouteIsValid2(String str, BallRoute ballRoute, long j, long j2, Map<String, BallRoute> map) {
        for (Map.Entry<String, BallRoute> entry : map.entrySet()) {
            String key = entry.getKey();
            BallRoute value = entry.getValue();
            if (!key.equals(str)) {
                long start = value.getStart();
                long end = value.getEnd();
                if (EditingUtils.containPosV2(start, end, j)) {
                    return end;
                }
                if (EditingUtils.containPosV2(start, end, j2) || EditingUtils.containPosV2(j, j2, start)) {
                    return start - (j2 - j);
                }
            }
        }
        return j;
    }

    public static int getBallRouteIndex(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            if (trackList.get(i).getBallRouteMap().containsKey(str)) {
                return i;
            }
        }
        return -1;
    }

    public static BallRoute getBallRoute(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            Map<String, BallRoute> ballRouteMap = trackList.get(i).getBallRouteMap();
            if (ballRouteMap.containsKey(str)) {
                return ballRouteMap.get(str);
            }
        }
        return null;
    }

    public static boolean setBallPosToTrack(String str, int i, long j) {
        BallRoute ballRoute;
        long sampleNumUp4096 = EditingUtils.formatSampleNumUp4096(j);
        List<TrackItemModel> trackList = getTrackList();
        if (i < trackList.size() && i >= 0) {
            for (int i2 = 0; i2 < trackList.size(); i2++) {
                TrackItemModel trackItemModel = trackList.get(i2);
                Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
                if (ballRouteMap.containsKey(str) && (ballRoute = ballRouteMap.get(str)) != null) {
                    TrackItemModel trackItemModel2 = trackList.get(i);
                    Map<String, BallRoute> ballRouteMap2 = trackItemModel2.getBallRouteMap();
                    if (trackItemModel.getNumChannels() != trackItemModel2.getNumChannels() && trackItemModel2.getNumChannels() != 0 && trackItemModel.getNumChannels() != 0) {
                        EditingUtils.showTips("请放置至相应轨道(声道数不一致）");
                        EditingUtils.log("toItem.getNumChannels() = " + trackItemModel2.getNumChannels() + ", item.getNumChannels()" + trackItemModel.getNumChannels());
                        return false;
                    }
                    long jMoveBallRouteIsValid = moveBallRouteIsValid(str, ballRoute, sampleNumUp4096, ballRouteMap2);
                    if (jMoveBallRouteIsValid < 0) {
                        return false;
                    }
                    String json = new Gson().toJson(trackItemModel);
                    String json2 = new Gson().toJson(trackItemModel2);
                    if (trackItemModel2.getNumChannels() == 0) {
                        trackItemModel2.setNumChannels(trackItemModel.getNumChannels());
                    }
                    long end = ballRoute.getEnd() - ballRoute.getStart();
                    ballRoute.setStart(jMoveBallRouteIsValid);
                    ballRoute.setEnd(jMoveBallRouteIsValid + end);
                    if (i2 != i) {
                        ballRouteMap2.put(str, ballRoute);
                        ballRouteMap.remove(str);
                        new CmdTracksChange(json, json2, new Gson().toJson(trackItemModel), new Gson().toJson(trackItemModel2), i2, i).save();
                    } else {
                        new CmdTrackChange(json, new Gson().toJson(trackItemModel), i2).save();
                    }
                    changeData();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean deleteBallSample(String str) {
        List<TrackItemModel> trackList = getTrackList();
        for (int i = 0; i < trackList.size(); i++) {
            TrackItemModel trackItemModel = trackList.get(i);
            Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
            if (ballRouteMap.containsKey(str)) {
                String json = new Gson().toJson(trackItemModel);
                ballRouteMap.remove(str);
                new CmdTrackChange(json, new Gson().toJson(trackItemModel), i).save();
                changeData();
                return true;
            }
        }
        return false;
    }

    public static int copyBallSample(String str, int i, long j) {
        BallRoute ballRouteM385clone;
        long sampleNumUp4096 = EditingUtils.formatSampleNumUp4096(j);
        List<TrackItemModel> trackList = getTrackList();
        for (int i2 = 0; i2 < trackList.size(); i2++) {
            TrackItemModel trackItemModel = trackList.get(i2);
            Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
            if (ballRouteMap.containsKey(str) && (ballRouteM385clone = ballRouteMap.get(str).m385clone()) != null) {
                TrackItemModel trackItemModel2 = trackList.get(i);
                Map<String, BallRoute> ballRouteMap2 = trackItemModel2.getBallRouteMap();
                if (trackItemModel.getNumChannels() != trackItemModel2.getNumChannels() && trackItemModel2.getNumChannels() != 0 && trackItemModel.getNumChannels() != 0) {
                    return -2;
                }
                if (moveBallRouteIsValid("", ballRouteM385clone, sampleNumUp4096, ballRouteMap2) != sampleNumUp4096) {
                    return -1;
                }
                String json = new Gson().toJson(trackItemModel2);
                if (trackItemModel2.getNumChannels() == 0) {
                    trackItemModel2.setNumChannels(trackItemModel.getNumChannels());
                }
                long end = ballRouteM385clone.getEnd() - ballRouteM385clone.getStart();
                ballRouteM385clone.setStart(sampleNumUp4096);
                ballRouteM385clone.setEnd(sampleNumUp4096 + end);
                long baseClipId2 = getBaseClipId();
                ballRouteM385clone.setId(baseClipId2);
                ballRouteMap2.put(baseClipId2 + "", ballRouteM385clone);
                new CmdTrackChange(json, new Gson().toJson(trackItemModel2), i).save();
                changeData();
                return 0;
            }
        }
        return -1;
    }

    public static List<Float> getDefaultBallPos() {
        if (defaultBallPos == null) {
            ArrayList arrayList = new ArrayList();
            defaultBallPos = arrayList;
            arrayList.add(Float.valueOf(0.0f));
            defaultBallPos.add(Float.valueOf(0.0f));
            defaultBallPos.add(Float.valueOf(0.0f));
        }
        return defaultBallPos;
    }

    public static List<Float> getBallPos(int i, long j) {
        TrackItemModel trackByIndex = getTrackByIndex(i);
        if (trackByIndex != null) {
            return getBallPosByTrackItem(trackByIndex, j).getPos();
        }
        return getDefaultBallPos();
    }

    public static BallPosResult getBallPosByTrackItem(TrackItemModel trackItemModel, long j) {
        Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
        ArrayList arrayList = new ArrayList(ballRouteMap.keySet());
        for (int i = 0; i < arrayList.size(); i++) {
            BallRoute ballRoute = ballRouteMap.get((String) arrayList.get(i));
            if (ballRoute != null) {
                if (EditingUtils.containPosV2(Long.valueOf(ballRoute.getStart()).longValue(), Long.valueOf(ballRoute.getEnd()).longValue(), j)) {
                    return new BallPosResult(ballRoute.getSectionDataBySampleNum(j), true);
                }
            }
        }
        long j2 = 0;
        List<Float> endPos = null;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            BallRoute ballRoute2 = ballRouteMap.get((String) arrayList.get(i2));
            long end = ballRoute2.getEnd();
            if (end <= j && j - end <= j - j2) {
                endPos = ballRoute2.getEndPos();
                j2 = end;
            }
        }
        if (endPos != null) {
            return new BallPosResult(endPos, false);
        }
        return new BallPosResult(getDefaultBallPos(), false);
    }

    public static class BallPosResult {
        private boolean in;
        private List<Float> pos;

        public BallPosResult(List<Float> list, boolean z) {
            this.pos = list;
            this.in = z;
        }

        public List<Float> getPos() {
            return this.pos;
        }

        public boolean isIn() {
            return this.in;
        }
    }

    public static void getBallPosByTrackItemV2Clear() {
        playingBallRoutTmpMap.clear();
    }

    public static BallRoutSampleTmp getPlayingBallRoutSampleData(String str, PlayingBallRoutTmp playingBallRoutTmp, Map<String, BallRouteSample> map) {
        if (playingBallRoutTmp.ballRoutSampleTmpMap != null && playingBallRoutTmp.ballRoutSampleTmpMap.containsKey(str)) {
            return playingBallRoutTmp.ballRoutSampleTmpMap.get(str);
        }
        BallRoutSampleTmp ballRoutSampleTmp = new BallRoutSampleTmp();
        ballRoutSampleTmp.ballRouteSampleKeyList = new ArrayList(map.keySet());
        if (ballRoutSampleTmp.ballRouteSampleKeyList.size() <= 0) {
            return null;
        }
        ballRoutSampleTmp.ballRouteId = str;
        ballRoutSampleTmp.start = Long.parseLong(ballRoutSampleTmp.ballRouteSampleKeyList.get(0));
        ballRoutSampleTmp.end = Long.parseLong(ballRoutSampleTmp.ballRouteSampleKeyList.get(ballRoutSampleTmp.ballRouteSampleKeyList.size() - 1));
        ballRoutSampleTmp.preSeekIndex = 0;
        playingBallRoutTmp.ballRoutSampleTmpMap.put(str, ballRoutSampleTmp);
        return ballRoutSampleTmp;
    }

    public static void setBallPosInit() {
        EditingUtils.log("leipos setBallPosInit 5 ");
        newTrackItemModel = null;
        isBallPosStop = false;
        setBallPosReset();
    }

    public static void setBallPosClose() {
        isBallPosStop = true;
        ballRouteSampleTmp = null;
        sortedBallRouteList = null;
        List<TrackItemModel> children = getTracksInfo().getChildren();
        TrackItemModel trackItemModel = newTrackItemModel;
        if (trackItemModel != null) {
            children.set(newTrackItemModelIndex, trackItemModel);
        } else {
            EditingUtils.log("leipos containBallInfo 6 ");
        }
        setBallPosReset();
        new CmdTrackChange(sTrackItemModelStr, new Gson().toJson(getTrackByIndex(sTrackItemIndex)), sTrackItemIndex).save();
        changeData();
        sTrackItemModelStr = "";
    }

    public static boolean getIsIsBallPosStop() {
        return isBallPosStop;
    }

    private static void setBallPosReset() {
        canUpload = true;
        firstSampleNum = -1L;
        lastSampleNum = -1L;
        preContainBallRoute = null;
        containBallInfo = null;
        preBallRoute = null;
        preNewBallRoute = null;
        preBallPos = null;
        removeBallRouteSample = null;
    }

    public static List<Float> setBallPosXY(int i, long j, float f, float f2) {
        if (sTrackItemModelStr.equals("")) {
            sTrackItemModelStr = new Gson().toJson(getTrackByIndex(i));
            sTrackItemIndex = i;
        }
        return setBallPosXYZV2(i, j, true, f, f2);
    }

    public static List<Float> setBallPosZ(int i, long j, float f) {
        if (sTrackItemModelStr.equals("")) {
            sTrackItemModelStr = new Gson().toJson(getTrackByIndex(i));
            sTrackItemIndex = i;
        }
        return setBallPosXYZV2(i, j, false, f, 0.0f);
    }

    private static void sortBallRouteList(final Map<String, BallRoute> map) {
        ArrayList arrayList = new ArrayList(map.keySet());
        sortedBallRouteList = arrayList;
        arrayList.sort(Comparator.comparingLong(new ToLongFunction() { // from class: com.wanos.careditproject.utils.DataHelpAudioTrack$$ExternalSyntheticLambda0
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((BallRoute) map.get((String) obj)).getStart();
            }
        }));
    }

    private static List<Float> getPreBallPos(long j, Map<String, BallRoute> map) {
        BallRouteSampleTmp ballRouteSampleTmp2 = ballRouteSampleTmp;
        if (ballRouteSampleTmp2 == null) {
            ballRouteSampleTmp = new BallRouteSampleTmp();
        } else if (ballRouteSampleTmp2.start <= j && ballRouteSampleTmp.end > j) {
            return ballRouteSampleTmp.pos;
        }
        int i = 0;
        while (true) {
            if (i >= sortedBallRouteList.size()) {
                break;
            }
            BallRoute ballRoute = map.get(sortedBallRouteList.get(i));
            if (ballRoute != null) {
                Long lValueOf = Long.valueOf(ballRoute.getStart());
                Long lValueOf2 = Long.valueOf(ballRoute.getEnd());
                if (lValueOf2.longValue() < j) {
                    ballRouteSampleTmp.start = lValueOf2.longValue();
                    ballRouteSampleTmp.pos = ballRoute.getEndPos();
                } else if (lValueOf.longValue() > j) {
                    ballRouteSampleTmp.end = lValueOf.longValue();
                    break;
                }
            }
            i++;
        }
        if (ballRouteSampleTmp.end == -1) {
            ballRouteSampleTmp.end = EditingUtils.getMaxSampleNum();
        }
        return ballRouteSampleTmp.pos;
    }

    public static List<Float> setBallPosXYZV2(int i, long j, boolean z, float f, float f2) {
        float fFloatValue;
        float fFloatValue2;
        boolean z2;
        testTrackIndex = i;
        float fFloatValue3 = !z ? f : 0.0f;
        if (newTrackItemModel == null) {
            TrackItemModel trackByIndex = getTrackByIndex(i);
            if (trackByIndex == null) {
                return preBallPos;
            }
            newTrackItemModel = trackByIndex.m388clone();
            newTrackItemModelIndex = i;
        }
        TrackItemModel trackItemModel = newTrackItemModel;
        if (trackItemModel != null) {
            canUpload = false;
            Map<String, BallRoute> ballRouteMap = trackItemModel.getBallRouteMap();
            if (sortedBallRouteList == null) {
                sortBallRouteList(ballRouteMap);
            }
            if (firstSampleNum == -1) {
                firstSampleNum = j;
            }
            lastSampleNum = j;
            if (preBallPos == null) {
                Iterator<Map.Entry<String, BallRoute>> it = ballRouteMap.entrySet().iterator();
                while (it.hasNext()) {
                    BallRoute value = it.next().getValue();
                    if (checkBallRouteContain(value, j)) {
                        preBallRoute = value;
                    }
                }
                if (preBallRoute == null) {
                    BallRoute ballRouteNewBallRoute = newBallRoute(ballRouteMap, i);
                    preBallRoute = ballRouteNewBallRoute;
                    ballRouteNewBallRoute.setStart(j);
                    preBallRoute.setEnd(j);
                }
            }
            if (z) {
                fFloatValue3 = getDefaultBallPos().get(2).floatValue();
                fFloatValue = f;
                fFloatValue2 = f2;
            } else {
                fFloatValue = getDefaultBallPos().get(0).floatValue();
                fFloatValue2 = getDefaultBallPos().get(1).floatValue();
            }
            if (!checkBallRouteContain(preBallRoute, j)) {
                Iterator<Map.Entry<String, BallRoute>> it2 = ballRouteMap.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    }
                    Map.Entry<String, BallRoute> next = it2.next();
                    BallRoute value2 = next.getValue();
                    if (checkBallRouteContain(value2, j)) {
                        EditingUtils.log("containBallInfo 1 sampleNum = " + j + ",start=" + value2.getStart() + ",end=" + value2.getEnd());
                        ballRouteSampleTmp = null;
                        preBallRoute.mergeBallRoute(value2);
                        ballRouteMap.remove(next.getKey());
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    List<Float> list = preBallPos;
                    if (list == null) {
                        List<Float> preBallPos2 = getPreBallPos(j, ballRouteMap);
                        if (z) {
                            fFloatValue3 = preBallPos2.get(2).floatValue();
                        } else {
                            fFloatValue = preBallPos2.get(0).floatValue();
                            fFloatValue2 = preBallPos2.get(1).floatValue();
                        }
                    } else if (z) {
                        fFloatValue3 = list.get(2).floatValue();
                    } else {
                        fFloatValue = list.get(0).floatValue();
                        fFloatValue2 = preBallPos.get(1).floatValue();
                    }
                }
            } else {
                ballRouteSampleTmp = null;
            }
            preBallPos = preBallRoute.insertSectionData(j, fFloatValue, fFloatValue2, fFloatValue3, z ? 1 : 2);
        }
        return preBallPos;
    }

    private static boolean checkBallRouteContain(BallRoute ballRoute, long j) {
        BallRoute ballRoute2 = preBallRoute;
        if (ballRoute2 == null || ballRoute2.getId() != ballRoute.getId()) {
            return EditingUtils.containPosV2(ballRoute.getStart(), ballRoute.getEnd(), j);
        }
        return false;
    }

    private static BallRoute newBallRoute(Map<String, BallRoute> map, int i) {
        long baseClipId2 = getBaseClipId();
        BallRoute ballRoute = new BallRoute();
        ballRoute.setId(baseClipId2);
        ballRoute.setTrackId(i);
        map.put(baseClipId2 + "", ballRoute);
        return ballRoute;
    }

    public static void setUndoRedoStateCallback(UndoRedoStateCallback undoRedoStateCallback2) {
        undoRedoStateCallback = undoRedoStateCallback2;
    }

    public static void undoRedoStateChanged() {
        EditingUtils.log("cmd undoRedoStateChanged");
        UndoRedoStateCallback undoRedoStateCallback2 = undoRedoStateCallback;
        if (undoRedoStateCallback2 != null) {
            undoRedoStateCallback2.stateChange();
        }
    }

    public static boolean undo() {
        boolean zUndo = CmdListCache.undo();
        UndoRedoStateCallback undoRedoStateCallback2 = undoRedoStateCallback;
        if (undoRedoStateCallback2 != null) {
            undoRedoStateCallback2.stateChange();
        }
        return zUndo;
    }

    public static boolean redo() {
        boolean zRedo = CmdListCache.redo();
        UndoRedoStateCallback undoRedoStateCallback2 = undoRedoStateCallback;
        if (undoRedoStateCallback2 != null) {
            undoRedoStateCallback2.stateChange();
        }
        return zRedo;
    }

    public static boolean undoIsEmpty() {
        EditingUtils.log("cmd undoIsEmpty CmdListCache.undoListSize()=" + CmdListCache.undoListSize());
        return CmdListCache.undoListSize() <= 0;
    }

    public static boolean redoIsEmpty() {
        return CmdListCache.redoListSize() <= 0;
    }

    public static long getSaveMS() {
        return saveMS;
    }

    private static void save() {
        try {
            EditProjectCache.save(new Gson().toJson(getRootModel()));
            UndoRedoStateCallback undoRedoStateCallback2 = undoRedoStateCallback;
            if (undoRedoStateCallback2 != null) {
                undoRedoStateCallback2.stateChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changeData() {
        saveMS = System.currentTimeMillis();
    }

    public static class DataHelpResult {
        private String errMsg;
        private long id;
        private boolean success;

        public DataHelpResult(boolean z, String str, long j) {
            this.success = z;
            this.errMsg = str;
            this.id = j;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean z) {
            this.success = z;
        }

        public String getErrMsg() {
            return this.errMsg;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public long getId() {
            return this.id;
        }
    }

    public static class BallRouteSampleTmp {
        public List<Float> pos;
        public long start = -1;
        public long end = -1;

        public BallRouteSampleTmp() {
            ArrayList arrayList = new ArrayList(3);
            this.pos = arrayList;
            Float fValueOf = Float.valueOf(0.0f);
            arrayList.add(fValueOf);
            this.pos.add(fValueOf);
            this.pos.add(fValueOf);
        }
    }
}
