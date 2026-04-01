package com.ecarx.eas.framework.sdk.proto;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class MapFactories {
    private static volatile MapFactory mapFactory = new a(0);

    public interface MapFactory {
        <K, V> Map<K, V> forMap(Map<K, V> map);
    }

    static void setMapFactory(MapFactory mapFactory2) {
        mapFactory = mapFactory2;
    }

    public static MapFactory getMapFactory() {
        return mapFactory;
    }

    private static class a implements MapFactory {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // com.ecarx.eas.framework.sdk.proto.MapFactories.MapFactory
        public final <K, V> Map<K, V> forMap(Map<K, V> map) {
            return map == null ? new HashMap() : map;
        }
    }

    private MapFactories() {
    }
}
