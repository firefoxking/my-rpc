package com.firefox;

import rpc.registry.DefaultServiceRegistry;
import rpc.registry.ServiceRegistry;
import rpc.socket.server.SocketServer;

public class SocketTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
//        RpcServer rpcServer = new RpcServer();
//        rpcServer.register(helloService, 9000);
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer rpcServer = new SocketServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
