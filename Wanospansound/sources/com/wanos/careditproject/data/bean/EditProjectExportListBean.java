package com.wanos.careditproject.data.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EditProjectExportListBean {
    private List<ExportProjectInfo> list;

    public List<ExportProjectInfo> getList() {
        return this.list;
    }

    public void setList(List<ExportProjectInfo> list) {
        this.list = list;
    }

    public class ExportProjectInfo {
        private String content;
        private String isVideo;
        private String picture;
        private String progress;
        private String projectId;
        private String size;
        private String status;
        private String title;
        private String url;
        private String workId;
        private String workType;

        public ExportProjectInfo() {
        }

        public void setWorkId(String str) {
            this.workId = str;
        }

        public String getWorkId() {
            return this.workId;
        }

        public void setProjectId(String str) {
            this.projectId = str;
        }

        public String getProjectId() {
            return this.projectId;
        }

        public void setIsVideo(String str) {
            this.isVideo = str;
        }

        public String getIsVideo() {
            return this.isVideo;
        }

        public void setSize(String str) {
            this.size = str;
        }

        public String getSize() {
            return this.size;
        }

        public void setProgress(String str) {
            this.progress = str;
        }

        public String getProgress() {
            return this.progress;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setWorkType(String str) {
            this.workType = str;
        }

        public String getWorkType() {
            return this.workType;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getTitle() {
            return this.title;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getPicture() {
            return this.picture;
        }

        public void setPicture(String str) {
            this.picture = str;
        }
    }
}
