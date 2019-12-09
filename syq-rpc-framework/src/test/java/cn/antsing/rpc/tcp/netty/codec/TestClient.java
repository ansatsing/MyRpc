package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.cosumer.CosumerRefer;
import cn.antsing.rpc.cosumer.NettyRpcRequest;
import cn.antsing.rpc.cosumer.RpcRequest;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.tcp.netty.IHello;
import cn.antsing.rpc.tcp.netty.NettyTcpClient;
import org.junit.Test;

import java.io.IOException;

public class TestClient {
    @Test
    public void testClient() throws ClassNotFoundException {
        NettyTcpClient tcpClient = new NettyTcpClient();
        RpcRequest rpcRequest = new NettyRpcRequest();
        ((NettyRpcRequest) rpcRequest).setTcpClient(tcpClient);
        CosumerRefer cosumerRefer = new CosumerRefer();
        cosumerRefer.setRpcRequest(rpcRequest);
        Consumer consumer = new Consumer();
        consumer.setInterfac("cn.antsing.rpc.tcp.netty.IHello");
        consumer.setId("1");
        consumer.setAddress("127.0.0.1:8080");
        System.out.println("开始rpc方法调用");
        IHello o = (IHello) cosumerRefer.refer(consumer);
        System.out.println("?>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(tcpClient.channels.size());
        System.out.println(o.hello("antsing"));
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {

    }
}
