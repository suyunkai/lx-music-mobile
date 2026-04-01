package com.google.protobuf;

import com.baidubce.BceConfig;
import com.google.protobuf.Descriptors;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class TypeRegistry {
    private static final Logger logger = Logger.getLogger(TypeRegistry.class.getName());
    private final Map<String, Descriptors.Descriptor> types;

    private static class EmptyTypeRegistryHolder {
        private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());

        private EmptyTypeRegistryHolder() {
        }
    }

    public static TypeRegistry getEmptyTypeRegistry() {
        return EmptyTypeRegistryHolder.EMPTY;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Descriptors.Descriptor find(String name) {
        return this.types.get(name);
    }

    public final Descriptors.Descriptor getDescriptorForTypeUrl(String typeUrl) throws InvalidProtocolBufferException {
        return find(getTypeName(typeUrl));
    }

    TypeRegistry(Map<String, Descriptors.Descriptor> types) {
        this.types = types;
    }

    private static String getTypeName(String typeUrl) throws InvalidProtocolBufferException {
        String[] strArrSplit = typeUrl.split(BceConfig.BOS_DELIMITER);
        if (strArrSplit.length == 1) {
            throw new InvalidProtocolBufferException("Invalid type url found: " + typeUrl);
        }
        return strArrSplit[strArrSplit.length - 1];
    }

    public static final class Builder {
        private final Set<String> files;
        private Map<String, Descriptors.Descriptor> types;

        private Builder() {
            this.files = new HashSet();
            this.types = new HashMap();
        }

        public Builder add(Descriptors.Descriptor messageType) {
            if (this.types == null) {
                throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
            }
            addFile(messageType.getFile());
            return this;
        }

        public Builder add(Iterable<Descriptors.Descriptor> messageTypes) {
            if (this.types == null) {
                throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
            }
            Iterator<Descriptors.Descriptor> it = messageTypes.iterator();
            while (it.hasNext()) {
                addFile(it.next().getFile());
            }
            return this;
        }

        public TypeRegistry build() {
            TypeRegistry typeRegistry = new TypeRegistry(this.types);
            this.types = null;
            return typeRegistry;
        }

        private void addFile(Descriptors.FileDescriptor file) {
            if (this.files.add(file.getFullName())) {
                Iterator<Descriptors.FileDescriptor> it = file.getDependencies().iterator();
                while (it.hasNext()) {
                    addFile(it.next());
                }
                Iterator<Descriptors.Descriptor> it2 = file.getMessageTypes().iterator();
                while (it2.hasNext()) {
                    addMessage(it2.next());
                }
            }
        }

        private void addMessage(Descriptors.Descriptor message) {
            Iterator<Descriptors.Descriptor> it = message.getNestedTypes().iterator();
            while (it.hasNext()) {
                addMessage(it.next());
            }
            if (this.types.containsKey(message.getFullName())) {
                TypeRegistry.logger.warning("Type " + message.getFullName() + " is added multiple times.");
            } else {
                this.types.put(message.getFullName(), message);
            }
        }
    }
}
