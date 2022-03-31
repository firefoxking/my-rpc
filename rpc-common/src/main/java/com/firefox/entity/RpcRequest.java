package com.firefox.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder    // build属性
public class RpcRequest implements Serializable {

    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
