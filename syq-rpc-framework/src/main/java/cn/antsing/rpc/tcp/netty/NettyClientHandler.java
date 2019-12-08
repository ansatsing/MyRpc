package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.cosumer.ResultContainer;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler   extends ChannelDuplexHandler {
    private Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SyqProtocol syqProtocol = (SyqProtocol) msg;
        logger.info("收到信息:[{}]",syqProtocol);
        ResultContainer.result.add(syqProtocol);
    }
}
