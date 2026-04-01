package com.just.agentweb;

import com.just.agentweb.AgentWeb;

/* JADX INFO: loaded from: classes2.dex */
public class HookManager {
    public static AgentWeb hookAgentWeb(AgentWeb agentWeb, AgentWeb.AgentBuilder agentBuilder) {
        return agentWeb;
    }

    public static boolean permissionHook(String str, String[] strArr) {
        return true;
    }
}
