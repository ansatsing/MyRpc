package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.cosumer.ResultContainer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<SyqProtocol> {
    private Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SyqProtocol syqProtocol) throws Exception {
        logger.info("收到信息:[{}]",syqProtocol);
        ResultContainer.result.add(syqProtocol);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        SyqProtocol syqProtocol = new SyqProtocol();
//        syqProtocol.setType((byte) 1);
//        InvocationInput invocationInput = new InvocationInput();
//        invocationInput.setInterfac("cn.antsing.IUser");
//        invocationInput.setMethodName("print");
//        invocationInput.setMethodParameters(new Object[]{"syq"});
//        byte[] data = SerializeUtils.serialize(invocationInput);
//        syqProtocol.setLength(data.length);
//        syqProtocol.setData(data);
//        ctx.writeAndFlush(syqProtocol);
    }
}
