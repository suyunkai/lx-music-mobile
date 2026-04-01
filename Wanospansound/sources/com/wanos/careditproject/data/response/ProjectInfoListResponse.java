package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.bean.ProjectInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectInfoListResponse extends BaseResponse {
    public Data data;

    public static class Data {
        public List<ProjectInfo> list;
        int page;
        int pageSize;
        public int total;
    }
}
