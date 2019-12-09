package cn.antsing.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<MyProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyProtocol protocol) throws Exception {
               //MyProtocol protocol = (MyProtocol) msg;
        System.out.println("客户端收到数据：\n"+new String(protocol.getBody()));
        ResultContainer.result.add(protocol);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String data = "Hello,Netty";
//        byte[] bytes = data.getBytes();
//       // MyProtocol customMsg = new MyProtocol((byte)0xAB, (byte)0xCD, bytes.length, bytes);
//        MyProtocol customMsg = new MyProtocol((byte)0xAB, bytes.length, bytes);
//        ctx.writeAndFlush(customMsg);
    }
    //    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String data = "Hello,Netty";
//        byte[] bytes = data.getBytes();
//       // MyProtocol customMsg = new MyProtocol((byte)0xAB, (byte)0xCD, bytes.length, bytes);
//        MyProtocol customMsg = new MyProtocol((byte)0xAB, bytes.length, bytes);
//        ctx.writeAndFlush(customMsg);
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        MyProtocol protocol = (MyProtocol) msg;
//        System.out.println("客户端收到数据：\n"+new String(protocol.getBody()));
//    }
}
