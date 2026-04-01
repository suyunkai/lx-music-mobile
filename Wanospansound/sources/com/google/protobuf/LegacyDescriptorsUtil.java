package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX INFO: loaded from: classes2.dex */
public final class LegacyDescriptorsUtil {

    public static final class LegacyFileDescriptor {

        public enum Syntax {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3");

            final String name;

            Syntax(String name) {
                this.name = name;
            }
        }

        public static Syntax getSyntax(Descriptors.FileDescriptor descriptor) {
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[descriptor.getSyntax().ordinal()];
            if (i == 1) {
                return Syntax.UNKNOWN;
            }
            if (i == 2) {
                return Syntax.PROTO2;
            }
            if (i == 3) {
                return Syntax.PROTO3;
            }
            throw new IllegalArgumentException("Unexpected syntax");
        }

        private LegacyFileDescriptor() {
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.LegacyDescriptorsUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax;

        static {
            int[] iArr = new int[Descriptors.FileDescriptor.Syntax.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax = iArr;
            try {
                iArr[Descriptors.FileDescriptor.Syntax.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private LegacyDescriptorsUtil() {
    }
}
