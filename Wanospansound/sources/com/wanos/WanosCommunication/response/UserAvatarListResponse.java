package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.UserAvatarInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class UserAvatarListResponse extends BaseResponse {
    public UserAvatarListData data;

    public static class UserAvatarListData extends BaseResponse {
        public ArrayList<UserAvatarInfo> list;
    }
}
