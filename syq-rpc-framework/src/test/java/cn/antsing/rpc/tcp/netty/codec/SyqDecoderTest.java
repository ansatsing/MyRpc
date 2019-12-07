package cn.antsing.rpc.tcp.netty.codec;

import cn.antsing.rpc.common.SyqProtocol;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SyqDecoderTest {
    @Mock
    ChannelHandlerContext context;
    @Test
    public void testDecode(){
        SyqProtocol protocol = generateSyqProtocolObject("my decoder");

    }

    private SyqProtocol generateSyqProtocolObject(String data) {
        SyqProtocol protocol = new SyqProtocol();
        protocol.setType((byte)1);
        byte[] bytes = data.getBytes();
        protocol.setData(bytes);
        protocol.setLength(bytes.length);
        return protocol;
    }
}
