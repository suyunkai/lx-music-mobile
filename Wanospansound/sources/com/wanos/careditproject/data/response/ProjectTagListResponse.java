package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectTagListResponse extends BaseResponse {
    public Data data;

    public static class Data {
        public int id;
        public List<ProjectTagBean> list;
        public String name;
        int total;
    }
}
