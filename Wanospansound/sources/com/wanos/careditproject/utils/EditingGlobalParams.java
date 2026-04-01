package com.wanos.careditproject.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class EditingGlobalParams {
    private static EditingGlobalParams instance;
    public Map<String, PublishProjectInfo> publishProjectInfoList = new HashMap();

    public static EditingGlobalParams getInstance() {
        if (instance == null) {
            instance = new EditingGlobalParams();
        }
        return instance;
    }

    public void addPublishProject(String str, String str2) {
        PublishProjectInfo publishProjectInfo = new PublishProjectInfo();
        publishProjectInfo.projectId = str;
        publishProjectInfo.id = str2;
        publishProjectInfo.publishTime = System.currentTimeMillis();
        this.publishProjectInfoList.put(str, publishProjectInfo);
    }

    public void removePublishProject(String str) {
        this.publishProjectInfoList.remove(str);
    }

    public PublishProjectInfo getPublishInfo(String str) {
        if (this.publishProjectInfoList.containsKey(str)) {
            return this.publishProjectInfoList.get(str);
        }
        return null;
    }

    public class PublishProjectInfo {
        public String id;
        public String projectId;
        public long publishTime;

        public PublishProjectInfo() {
        }
    }
}
