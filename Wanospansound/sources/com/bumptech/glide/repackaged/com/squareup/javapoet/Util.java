package com.bumptech.glide.repackaged.com.squareup.javapoet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: classes2.dex */
final class Util {
    static final Modifier DEFAULT;

    static {
        Modifier modifierValueOf;
        try {
            modifierValueOf = Modifier.valueOf("DEFAULT");
        } catch (IllegalArgumentException unused) {
            modifierValueOf = null;
        }
        DEFAULT = modifierValueOf;
    }

    static <K, V> Map<K, List<V>> immutableMultimap(Map<K, List<V>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                linkedHashMap.put(entry.getKey(), immutableList(entry.getValue()));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.format(str, objArr));
    }

    static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    static <T> List<T> immutableList(Collection<T> collection) {
        return Collections.unmodifiableList(new ArrayList(collection));
    }

    static <T> Set<T> immutableSet(Collection<T> collection) {
        return Collections.unmodifiableSet(new LinkedHashSet(collection));
    }

    static String join(String str, List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(str).append(list.get(i));
        }
        return sb.toString();
    }

    static <T> Set<T> union(Set<T> set, Set<T> set2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(set);
        linkedHashSet.addAll(set2);
        return linkedHashSet;
    }

    static void requireExactlyOneOf(Set<Modifier> set, Modifier... modifierArr) {
        int i = 0;
        for (Modifier modifier : modifierArr) {
            if ((modifier != null || DEFAULT != null) && set.contains(modifier)) {
                i++;
            }
        }
        checkArgument(i == 1, "modifiers %s must contain one of %s", set, Arrays.toString(modifierArr));
    }

    static boolean hasDefaultModifier(Collection<Modifier> collection) {
        Modifier modifier = DEFAULT;
        return modifier != null && collection.contains(modifier);
    }

    static String characterLiteralWithoutSingleQuotes(char c2) {
        if (c2 == '\f') {
            return "\\f";
        }
        if (c2 == '\r') {
            return "\\r";
        }
        if (c2 == '\"') {
            return "\"";
        }
        if (c2 == '\'') {
            return "\\'";
        }
        if (c2 == '\\') {
            return "\\\\";
        }
        switch (c2) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            default:
                return Character.isISOControl(c2) ? String.format("\\u%04x", Integer.valueOf(c2)) : Character.toString(c2);
        }
    }

    static String stringLiteralWithDoubleQuotes(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\"');
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\'') {
                sb.append("'");
            } else if (cCharAt == '\"') {
                sb.append("\\\"");
            } else {
                sb.append(characterLiteralWithoutSingleQuotes(cCharAt));
                if (cCharAt == '\n' && i + 1 < str.length()) {
                    sb.append("\"\n").append(str2).append(str2).append("+ \"");
                }
            }
        }
        sb.append('\"');
        return sb.toString();
    }
}
