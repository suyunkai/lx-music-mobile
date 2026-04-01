package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX INFO: loaded from: classes2.dex */
public interface BlockingService {
    Message callBlockingMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request) throws ServiceException;

    Descriptors.ServiceDescriptor getDescriptorForType();

    Message getRequestPrototype(Descriptors.MethodDescriptor method);

    Message getResponsePrototype(Descriptors.MethodDescriptor method);
}
