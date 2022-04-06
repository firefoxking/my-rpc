package rpc;

import com.firefox.entity.RpcRequest;
import com.firefox.entity.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpc.socket.client.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);
    // private String host;
    // private int port;
    private RpcClient rpcClient;

    public RpcClientProxy(String host, int port) {
//        this.host = host;
//        this.port = port;
        rpcClient = new SocketClient(host, port);
    }

    public RpcClientProxy(RpcClient rpcClient) {
        this.rpcClient= rpcClient;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        logger.info("ClassLoader: {}", clazz.getClassLoader());
        logger.info("Interfaces: {}", clazz.getInterfaces());
        logger.info("Interfaces: {}", new Class<?>[]{clazz});
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        logger.info("method.methodName: {}", method.getName());
        logger.info("rpcRequest: {}", rpcRequest);
        return rpcClient.sendRequest(rpcRequest);
    }
}
