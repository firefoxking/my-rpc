package com.firefox;

import client.RpcClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClient {
    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        logger.info("hello before: ");
        String res = helloService.hello(object);
        logger.info("hello after: ");
        System.out.println(res);
    }
}

