package com.wanos.viewmodel;

import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class SearchActivityViewModel extends ViewModel {
    private String keyword;
    boolean isShowingSearchResult = false;
    boolean isKeyboardFocused = true;
    boolean isFirst = true;

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean isFirst() {
        return this.isFirst;
    }

    public boolean isShowingSearchResult() {
        return this.isShowingSearchResult;
    }

    public void setShowingSearchResult(boolean showingSearchResult) {
        this.isShowingSearchResult = showingSearchResult;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public boolean isKeyboardFocused() {
        return this.isKeyboardFocused;
    }

    public void setKeyboardFocused(boolean keyboardFocused) {
        this.isKeyboardFocused = keyboardFocused;
    }
}
