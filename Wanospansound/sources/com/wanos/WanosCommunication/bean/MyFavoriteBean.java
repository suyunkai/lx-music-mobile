package com.wanos.WanosCommunication.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MyFavoriteBean {
    private List<AudioInfoBean> List;
    private int Page;
    private int PageSize;
    private String QueryStr;
    private int Total;

    public String getQueryStr() {
        return this.QueryStr;
    }

    public void setQueryStr(String str) {
        this.QueryStr = str;
    }

    public int getPage() {
        return this.Page;
    }

    public void setPage(int i) {
        this.Page = i;
    }

    public int getPageSize() {
        return this.PageSize;
    }

    public void setPageSize(int i) {
        this.PageSize = i;
    }

    public int getTotal() {
        return this.Total;
    }

    public void setTotal(int i) {
        this.Total = i;
    }

    public List<AudioInfoBean> getList() {
        return this.List;
    }

    public void setList(List<AudioInfoBean> list) {
        this.List = list;
    }
}
