package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.tcp.TcpClient;
import cn.antsing.rpc.tcp.netty.codec.SyqDecoder;
import cn.antsing.rpc.tcp.netty.codec.SyqEncoder;
import cn.antsing.rpc.util.SerializeUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NettyTcpClient implements TcpClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyTcpClient.class);

    public volatile Map<String, List<Channel>> channels = new HashMap<String, List<Channel>>();

    private void connect(Consumer consumer) {
        String interfaceFullName = consumer.getInterfac();
        List<Channel> channelList = channels.get(interfaceFullName);
        if (channelList == null || channelList.isEmpty()) {
            String addresses = consumer.getAddress();
            String[] ipAddres = addresses.split(",");
            if (ipAddres != null && ipAddres.length > 0) {
                channelList = new ArrayList<Channel>();
                channels.put(interfaceFullName, channelList);
                for (String ipAddre : ipAddres) {
                    String[] ipPort = ipAddre.split(":");
                    if (ipPort == null || ipPort.length != 2) {
                        logger.error("ip地址非法,正确地址[192.168.0.8:8080,192.168.0.9:8080]");
                        throw new RuntimeException("ip地址非法");
                    }
                    String ip = ipPort[0];
                    Integer port = Integer.parseInt(ipPort[1]);
                    channelList.add(connectProducer(interfaceFullName, ip, port));
                }

            } else {
                logger.error("ip地址非法,正确地址[192.168.0.8:8080,192.168.0.9:8080]");
                throw new RuntimeException("ip地址非法");
            }
        }


    }

    private Channel connectProducer(String interfaceFullName, String ip, Integer port) {
        InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
        final NettyClientHandler nettyClientHandler = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SyqEncoder());
                        ch.pipeline().addLast(new SyqDecoder());
                        ch.pipeline().addLast(nettyClientHandler);
                    }
                });
        try {
            ChannelFuture future = b.connect(socketAddress).sync();
            boolean ret = future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);
            if (ret && future.isSuccess()) {
                logger.info("连接rpc服务器成功[interaceName={},ip={},port={}]", interfaceFullName, ip, port);
                return future.channel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.error("连接rpc服务器失败[interaceName={},ip={},port={}]", interfaceFullName, ip, port);
        return null;
    }

    public void sendRequest(InvocationInput invocationInput, Consumer consumer) {
        String interfaceFullName = invocationInput.getInterfac();
        List<Channel> channelList = channels.get(interfaceFullName);
        if (channelList == null || channelList.isEmpty()) {
            connect(consumer);
        }

        Channel channel = channels.get(interfaceFullName).get(0);

        SyqProtocol syqProtocol = new SyqProtocol();
        syqProtocol.setType((byte) 1);
        byte[] data = SerializeUtils.serialize(invocationInput);
        syqProtocol.setLength(data.length);
        syqProtocol.setData(data);
        channel.writeAndFlush(syqProtocol);
        logger.info("往接口服务方推送信息[{}]", syqProtocol);

    }

    public Map<String, List<Channel>> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, List<Channel>> channels) {
        this.channels = channels;
    }
}
