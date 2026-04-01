package com.bumptech.glide.repackaged.com.google.common.collect;

import com.bumptech.glide.repackaged.com.google.common.base.Function;
import com.bumptech.glide.repackaged.com.google.common.base.Joiner;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class Maps {
    static final Joiner.MapJoiner STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");

    private enum EntryFunction implements Function<Map.Entry<?, ?>, Object> {
        KEY { // from class: com.bumptech.glide.repackaged.com.google.common.collect.Maps.EntryFunction.1
            @Override // com.bumptech.glide.repackaged.com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE { // from class: com.bumptech.glide.repackaged.com.google.common.collect.Maps.EntryFunction.2
            @Override // com.bumptech.glide.repackaged.com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        };

        /* synthetic */ EntryFunction(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    static <V> Function<Map.Entry<?, V>, V> valueFunction() {
        return EntryFunction.VALUE;
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.bumptech.glide.repackaged.com.google.common.collect.Maps$1, reason: invalid class name */
    static class AnonymousClass1<K, V> extends TransformedIterator<K, Map.Entry<K, V>> {
        final /* synthetic */ Function val$function;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bumptech.glide.repackaged.com.google.common.collect.TransformedIterator
        public Map.Entry<K, V> transform(K k) {
            return Maps.immutableEntry(k, this.val$function.apply(k));
        }
    }

    public static <K, V> Map.Entry<K, V> immutableEntry(K k, V v) {
        return new ImmutableEntry(k, v);
    }

    static boolean equalsImpl(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    static String toStringImpl(Map<?, ?> map) {
        StringBuilder sbAppend = Collections2.newStringBuilderForCollection(map.size()).append('{');
        STANDARD_JOINER.appendTo(sbAppend, map);
        return sbAppend.append('}').toString();
    }
}
