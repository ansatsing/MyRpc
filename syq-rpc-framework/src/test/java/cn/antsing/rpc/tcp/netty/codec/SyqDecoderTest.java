package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.EventExecutorGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@RunWith(MockitoJUnitRunner.class)
public class SyqDecoderTest {
    @Mock
    ChannelHandlerContext context;

    @Test
    public void testDecode() {
        SyqProtocol protocol = generateSyqProtocolObject("my decoder");

    }

    private SyqProtocol generateSyqProtocolObject(String data) {
        SyqProtocol protocol = new SyqProtocol();
        protocol.setType((byte) 1);
        byte[] bytes = data.getBytes();
        protocol.setData(bytes);
        protocol.setLength(bytes.length);
        return protocol;
    }

    @Test
    public void testLengthFieldBasedFrameDecoder() throws UnsupportedEncodingException, InterruptedException {
       final LengthFieldBasedFrameDecoder spliter=new LengthFieldBasedFrameDecoder(1024,0,4,0,4);
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            protected void initChannel(EmbeddedChannel ch) {
                ch.pipeline().addLast(spliter);
                ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(i);
        for (int j = 0; j < 1; j++) {
            ByteBuf buf = Unpooled.buffer();
            String s = "呵呵,I am " + j;
            byte[] bytes = s.getBytes("UTF-8");
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
            channel.writeInbound(buf);
        }

        Thread.sleep(10000000);
    }

    public static final class StringProcessHandler
            extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx,
                                 ByteBuf msg) throws Exception {
            System.out.println("=================");
            int len = msg.readableBytes();
            byte[] data = new byte[len];
            msg.readBytes(data);
            System.out.println(new String(data));
        }
    }
}