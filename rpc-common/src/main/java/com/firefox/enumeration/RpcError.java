package com.firefox.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RpcError {

    SERVICE_INVOCATION_FAILURE("服务调用出现失败"),
    SERVICE_NOT_FOUND("找不到对应的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("注册的服务未实现接口"),
    UNKNOWN_PROTOCOL("未知编码协议"),
    UNKNOWN_PACKAGE_TYPE("未知数据包"),
    UNKNOWN_SERIALIZER("未知序列号器");

    private final String message;
}
