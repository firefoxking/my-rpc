package com.firefox;

import rpc.RpcClient;
import rpc.RpcClientProxy;
import rpc.netty.client.NettyClient;

public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient rpcClient = new NettyClient("localhost", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(666, "This is rpc netty message");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}
