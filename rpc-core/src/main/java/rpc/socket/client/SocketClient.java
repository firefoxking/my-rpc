package rpc.socket.client;

import com.firefox.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpc.RpcClient;

import java.io.*;
import java.net.Socket;

public class SocketClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private final String host;
    private final int port;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object sendRequest(RpcRequest rpcRequest) {
        try {
            Socket socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            return objectInputStream.readObject();
        } catch (IOException e) {
            logger.error("socket new 失败!" + e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("readObject 失败" + e.toString());
            return null;
        }
    }
}
