package chapter.second.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 1) {
            System.err.println(
                    "Usage: " + EchoServer.class.getSimpleName() +
                            "<port>"
            );
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 1. 创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 2. 创建ServerBootstrap
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
                // 3. 指定所使用的NIO传输Channel
                .channel(NioServerSocketChannel.class)
                // 4. 使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(port))
                // 5. 添加一个EchoServerHandler到子Channel的ChannelPipline
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(serverHandler);
                    }
                });
        try {
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
