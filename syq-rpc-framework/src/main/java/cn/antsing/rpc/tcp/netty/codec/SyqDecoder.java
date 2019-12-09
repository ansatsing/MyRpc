package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 协议解码器
 */
public class SyqDecoder extends LengthFieldBasedFrameDecoder {
    private Logger logger = LoggerFactory.getLogger(SyqDecoder.class);
//    private final int maxFrameLength = 4*1024;//4k
//    private final int lengthFieldOffset = 0;
//    private final int lengthFieldLength = 4;
//    private final int lengthAdjustment = 0;
//    private final int initialBytesToStrip = 0;
//    private final boolean failFast = true;
    private final static int HEADER_SIZE = 5;
    public SyqDecoder() {
        super(  1024*1024, 1, 4, 0, 0, true);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        logger.info("开始解码。。。");
        if( in == null){
            return null;
        }
        int readableBytes = in.readableBytes();
        if(readableBytes < HEADER_SIZE){
            throw new CodecException("需解码的帧非法[其长度小于头长度]");
        }
        SyqProtocol syqProtocol = new SyqProtocol();
        byte type = in.readByte();
        syqProtocol.setType(type);
        int len = in.readInt();
        syqProtocol.setLength(len);
        byte[] data = new byte[len];
        in.readBytes(data);
        syqProtocol.setData(data);
        logger.info("结束解码。。。");
        return syqProtocol;
    }
}
