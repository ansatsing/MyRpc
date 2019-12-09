package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.util.SerializeUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class NettyServerHandler  extends SimpleChannelInboundHandler<SyqProtocol> {
    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    private IHello hello = new HelloService();
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SyqProtocol protocol = (SyqProtocol) msg;
//        //todo 调用方法获得结果返回
//        logger.info("服务端读取到消息：",protocol);
//        ctx.writeAndFlush(protocol);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SyqProtocol syqProtocol) throws Exception {
        //todo 调用方法获得结果返回
        logger.info("服务端读取到消息：{}",syqProtocol);
        InvocationInput invocationInput = (InvocationInput) SerializeUtils.unserialize(syqProtocol.getData());
        if(invocationInput != null){
            SyqProtocol protocol = new SyqProtocol();
            protocol.setType((byte)2);
            String data = hello.hello(invocationInput.getMethodParameters()[0].toString());
            logger.info("方法调用返回结果：{}",data);
            byte[] bytes = SerializeUtils.serialize(data);
            protocol.setLength(bytes.length);
            protocol.setData(bytes);
            channelHandlerContext.writeAndFlush(protocol).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        logger.info("服务端发送信息成功");
                    }
                }
            });
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("新的消费方连接上来了。");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
