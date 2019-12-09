package cn.antsing.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class MyProtocolEncoder  extends MessageToByteEncoder<MyProtocol> {
    protected void encode(ChannelHandlerContext channelHandlerContext, MyProtocol myProtocol, ByteBuf byteBuf) throws Exception {
        if(null == myProtocol){
            throw new Exception("msg is null");
        }

        //String body = myProtocol.getBody();
        //byte[] bodyBytes = body.getBytes(Charset.forName("utf-8"));
        byteBuf.writeByte(myProtocol.getType());
      //  byteBuf.writeByte(myProtocol.getFlag());
        byteBuf.writeInt(myProtocol.getBody().length);
        byteBuf.writeBytes(myProtocol.getBody());
    }
}
