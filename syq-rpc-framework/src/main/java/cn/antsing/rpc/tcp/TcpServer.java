package cn.antsing.rpc.tcp;

public interface TcpServer {
    void start(String ip,Integer port);
    void stop();
}
