package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
@CheckReturnValue
public interface Message extends MessageLite, MessageOrBuilder {

    public interface Builder extends MessageLite.Builder, MessageOrBuilder {
        Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value);

        Message build();

        Message buildPartial();

        Builder clear();

        Builder clearField(Descriptors.FieldDescriptor field);

        Builder clearOneof(Descriptors.OneofDescriptor oneof);

        /* JADX INFO: renamed from: clone */
        Builder m368clone();

        @Override // com.google.protobuf.MessageOrBuilder
        Descriptors.Descriptor getDescriptorForType();

        Builder getFieldBuilder(Descriptors.FieldDescriptor field);

        Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index);

        @Override // com.google.protobuf.MessageLite.Builder
        boolean mergeDelimitedFrom(InputStream input) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

        Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException;

        Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

        Builder mergeFrom(CodedInputStream input) throws IOException;

        Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

        Builder mergeFrom(Message other);

        Builder mergeFrom(InputStream input) throws IOException;

        Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

        Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

        Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

        Builder mergeUnknownFields(UnknownFieldSet unknownFields);

        Builder newBuilderForField(Descriptors.FieldDescriptor field);

        Builder setField(Descriptors.FieldDescriptor field, Object value);

        Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value);

        Builder setUnknownFields(UnknownFieldSet unknownFields);
    }

    boolean equals(Object other);

    Parser<? extends Message> getParserForType();

    int hashCode();

    Builder newBuilderForType();

    Builder toBuilder();

    String toString();
}
