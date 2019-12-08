package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class NettyServerHandler  extends SimpleChannelInboundHandler<SyqProtocol> {
    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SyqProtocol protocol = (SyqProtocol) msg;
//        //todo 调用方法获得结果返回
//        logger.info("服务端读取到消息：",protocol);
//        ctx.writeAndFlush(protocol);
//    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SyqProtocol syqProtocol) throws Exception {
        //todo 调用方法获得结果返回
        logger.info("服务端读取到消息：",syqProtocol);
        channelHandlerContext.writeAndFlush(syqProtocol);
    }
}
