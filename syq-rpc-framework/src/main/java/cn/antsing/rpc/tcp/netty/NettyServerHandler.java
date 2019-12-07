package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler  extends ChannelDuplexHandler {
    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SyqProtocol protocol = (SyqProtocol) msg;
        //todo 调用方法获得结果返回
        logger.info("服务端读取到消息：",protocol);
        ctx.writeAndFlush("echo.....");
    }
}
