package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Parcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.AutoSafeParcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes2.dex */
public class LongMsg extends AutoSafeParcelable {
    public static final Parcelable.Creator<LongMsg> CREATOR = new AutoSafeParcelable.AutoCreator(LongMsg.class);

    @SafeParcelable.Field(1)
    public long param;
}
