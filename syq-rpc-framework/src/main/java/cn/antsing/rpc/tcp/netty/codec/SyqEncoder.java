package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SyqEncoder extends MessageToByteEncoder<SyqProtocol> {
    protected void encode(ChannelHandlerContext channelHandlerContext, SyqProtocol syqProtocol, ByteBuf byteBuf) throws Exception {
        if(null == syqProtocol){
            throw new RuntimeException("发送的协议消息不能为空");
        }
        byteBuf.writeByte(syqProtocol.getType());
        byteBuf.writeInt(syqProtocol.getLength());
        byteBuf.writeBytes(syqProtocol.getData());
    }
}
