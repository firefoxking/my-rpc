package com.firefox;

import rpc.RpcServer;
import rpc.netty.server.NettyServer;
import rpc.registry.DefaultServiceRegistry;
import rpc.registry.ServiceRegistry;

public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer server = new NettyServer();
        server.start(9999);
    }
}
