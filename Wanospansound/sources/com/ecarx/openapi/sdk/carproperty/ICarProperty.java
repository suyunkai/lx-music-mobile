package com.ecarx.openapi.sdk.carproperty;

import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarProperty {
    int getPropertyAdaptValue(int i, int i2, int i3) throws EASFrameworkException;

    int getPropertyId(int i, int i2) throws EASFrameworkException;

    int getPropertyValue(int i, int i2, int i3) throws EASFrameworkException;
}
