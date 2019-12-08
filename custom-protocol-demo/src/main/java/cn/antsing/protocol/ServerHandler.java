package cn.antsing.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<MyProtocol> {
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol myProtocol) throws Exception {
        System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+myProtocol.getBody());
        ctx.writeAndFlush(myProtocol);
    }
}
