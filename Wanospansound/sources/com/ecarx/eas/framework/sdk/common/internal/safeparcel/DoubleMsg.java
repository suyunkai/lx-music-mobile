package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Parcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.AutoSafeParcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes2.dex */
public class DoubleMsg extends AutoSafeParcelable {
    public static final Parcelable.Creator<DoubleMsg> CREATOR = new AutoSafeParcelable.AutoCreator(DoubleMsg.class);

    @SafeParcelable.Field(1)
    public double param;
}
