package com.wanos.careditproject.data.bean;

import com.wanos.bean.ProjectInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EditProjectListBean {
    private List<ProjectInfo> list;
    private int page;
    private int page_size;
    private int total;

    public List<ProjectInfo> getList() {
        return this.list;
    }

    public void setList(List<ProjectInfo> list) {
        this.list = list;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public int getPage_size() {
        return this.page_size;
    }

    public void setPage_size(int i) {
        this.page_size = i;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }
}
