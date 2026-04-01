package com.wanos.careditproject.data.bean;

import com.blankj.utilcode.util.StringUtils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AiPromptEntity {
    private List<PromptBean> prompts;

    public List<PromptBean> getPrompts() {
        List<PromptBean> list = this.prompts;
        return list == null ? Collections.emptyList() : list;
    }

    public static class PromptBean {
        private int id;
        private String promptContent;

        public PromptBean() {
        }

        public PromptBean(int i, String str) {
            this.id = i;
            this.promptContent = str;
        }

        public int getId() {
            return this.id;
        }

        public String getPrompt_content() {
            return StringUtils.isEmpty(this.promptContent) ? "" : this.promptContent;
        }
    }
}
