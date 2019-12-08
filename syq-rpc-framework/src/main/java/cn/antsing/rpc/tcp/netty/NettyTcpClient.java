package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.tcp.TcpClient;
import cn.antsing.rpc.tcp.netty.codec.SyqDecoder;
import cn.antsing.rpc.tcp.netty.codec.SyqEncoder;
import cn.antsing.rpc.util.SerializeUtils;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NettyTcpClient implements TcpClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private Map<String, List<Channel>> channels = new HashMap<String, List<Channel>>();

    private static final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(Constants.DEFAULT_IO_THREADS, new DefaultThreadFactory("NettyClientWorker", true));

    private Bootstrap bootstrap;
    public void connect(Consumer consumer) {
        String interfaceFullName = consumer.getInterfac();
        List<Channel> channelList = channels.get(interfaceFullName);
        if(channelList==null || channelList.isEmpty()){
            String addresses = consumer.getAddress();
            String[] ipAddres = addresses.split(",");
            if(ipAddres!=null && ipAddres.length>0){
                channelList = new ArrayList<Channel>();
                for(String ipAddre:ipAddres){
                    String[] ipPort = ipAddre.split(":");
                    if(ipPort == null || ipPort.length!=2){
                        logger.error("ip地址非法,正确地址[192.168.0.8:8080,192.168.0.9:8080]");
                        throw new RuntimeException("ip地址非法");
                    }
                    String ip=ipPort[0];
                    Integer port = Integer.parseInt(ipPort[1]);
                    InetSocketAddress socketAddress = new InetSocketAddress(ip,port);
                    final NettyClientHandler nettyClientHandler = new NettyClientHandler();
                    bootstrap = new Bootstrap();
                    bootstrap.group(nioEventLoopGroup)
                            .option(ChannelOption.SO_KEEPALIVE, true)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                            //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout())
                            .channel(NioSocketChannel.class);

                    // if (getTimeout() < 3000) {
                    bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
                    // } else {
                    //    bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout());
                    //}

                    bootstrap.handler(new ChannelInitializer() {

                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()//.addLast("logging",new LoggingHandler(LogLevel.INFO))//for debug
                                    .addLast("decoder", new SyqDecoder())
                                    .addLast("encoder", new SyqEncoder())
                                    .addLast("handler", nettyClientHandler);
                        }
                    });
                    Channel channel= bootstrap.connect(socketAddress).channel();
                    channelList.add(channel);
                    channels.put(consumer.getInterfac(),channelList);
                    logger.info("连接rpc服务器成功[interaceName={},ip={},port={}]",interfaceFullName,ip,port);
                }

            }else{
                logger.error("ip地址非法,正确地址[192.168.0.8:8080,192.168.0.9:8080]");
                throw new RuntimeException("ip地址非法");
            }
        }


    }

    public void sendRequest(InvocationInput invocationInput) {
        String interfaceFullName = invocationInput.getInterfac();
        SyqProtocol syqProtocol = new SyqProtocol();
        syqProtocol.setType((byte)1);
        byte[] data = SerializeUtils.serialize(invocationInput);
        syqProtocol.setLength(data.length);
        syqProtocol.setData(data);
        channels.get(interfaceFullName).get(0).writeAndFlush(syqProtocol);
        logger.info("往接口服务方推送信息[{}]",syqProtocol);
    }

    public Map<String, List<Channel>> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, List<Channel>> channels) {
        this.channels = channels;
    }
}
