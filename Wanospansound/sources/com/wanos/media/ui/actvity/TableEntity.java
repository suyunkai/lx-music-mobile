package com.wanos.media.ui.actvity;

import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class TableEntity {
    private final int id;
    private boolean select = false;
    private int tableIcon;
    private int tableName;

    public TableEntity(int id) {
        this.id = id;
        if (id == 0) {
            this.tableName = R.string.ju_yi_hall;
            this.tableIcon = R.drawable.home_tab_ju_yi_selector;
            return;
        }
        if (id == 1) {
            this.tableName = R.string.music;
            this.tableIcon = R.drawable.home_tab_music_selector;
            return;
        }
        if (id == 2) {
            this.tableName = R.string.audio_book;
            this.tableIcon = R.drawable.home_tab_audiobook_selector;
            return;
        }
        if (id == 3) {
            this.tableName = R.string.video;
            this.tableIcon = R.drawable.home_tab_video_selector;
        } else if (id == 4) {
            this.tableName = R.string.zero;
            this.tableIcon = R.drawable.home_tab_zero_selector;
        } else {
            if (id != 5) {
                return;
            }
            this.tableName = R.string.creator;
            this.tableIcon = R.drawable.home_tab_creator_selector;
        }
    }

    public int getId() {
        return this.id;
    }

    public int getTableName() {
        return this.tableName;
    }

    public int getTableIcon() {
        return this.tableIcon;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
