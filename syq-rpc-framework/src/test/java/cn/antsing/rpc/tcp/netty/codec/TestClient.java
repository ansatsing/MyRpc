package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.cosumer.CosumerRefer;
import cn.antsing.rpc.cosumer.NettyRpcRequest;
import cn.antsing.rpc.cosumer.RpcRequest;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.tcp.TcpClient;
import cn.antsing.rpc.tcp.netty.NettyTcpClient;
import org.junit.Test;

public class TestClient {
    @Test
    public void testClient() throws ClassNotFoundException {
        TcpClient tcpClient = new NettyTcpClient();
        RpcRequest rpcRequest = new NettyRpcRequest();
        ((NettyRpcRequest) rpcRequest).setTcpClient(tcpClient);
        CosumerRefer cosumerRefer = new CosumerRefer();
        cosumerRefer.setRpcRequest(rpcRequest);
        Consumer consumer = new Consumer();
        consumer.setInterfac("cn.antsing.rpc.tcp.netty.codec.IHello");
        consumer.setId("1");
        consumer.setAddress("127.0.0.1:8080");
        IHello o = (IHello) cosumerRefer.refer(consumer);
        System.out.println(o.hello("antsing"));
    }
}
