package cn.antsing.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<MyProtocol> {
    private IHello hello = new HelloService();
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol myProtocol) throws Exception {
        System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+myProtocol.getBody());
        InvocationInput invocationInput = (InvocationInput) SerializeUtils.unserialize(myProtocol.getBody());
        if(invocationInput != null){
            MyProtocol protocol = new MyProtocol();
            protocol.setType((byte)1);
            String data = hello.hello(invocationInput.getMethodParameters()[0].toString());
            System.out.println("方法rpc调用返回值："+data);
            byte[] bytes = data.getBytes();
            protocol.setLength(bytes.length);
            protocol.setBody(bytes);
            ctx.writeAndFlush(protocol);
        }

    }
}
