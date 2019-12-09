package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.tcp.TcpServer;
import cn.antsing.rpc.tcp.netty.NettyTcpServer;
import org.junit.Test;

public class TestServer {
    @Test
    public void startServer(){
        TcpServer tcpServer = new NettyTcpServer();
        tcpServer.start("127.0.0.1",8080);
        while (true){

        }
    }

    public static void main(String[] args) {
        TcpServer tcpServer = new NettyTcpServer();
        tcpServer.start("127.0.0.1",8080);
    }
}
