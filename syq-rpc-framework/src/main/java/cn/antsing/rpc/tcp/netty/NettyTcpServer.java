package cn.antsing.rpc.tcp.netty;

import cn.antsing.rpc.tcp.TcpServer;
import cn.antsing.rpc.tcp.netty.codec.SyqDecoder;
import cn.antsing.rpc.tcp.netty.codec.SyqEncoder;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.remoting.transport.netty.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NettyTcpServer implements TcpServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private ServerBootstrap bootstrap;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    public void start(String ip,Integer port) {
        bootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
        workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(),new DefaultThreadFactory("NettyServerWorker", true));
        final NettyServerHandler nettyServerHandler = new NettyServerHandler();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()//.addLast("logging",new LoggingHandler(LogLevel.INFO))//for debug
                                .addLast("decoder", new SyqDecoder())
                                .addLast("encoder", new SyqEncoder())
                                .addLast("handler", nettyServerHandler);
                    }
                });
        // bind
        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(ip,port));
        channelFuture.syncUninterruptibly().channel();
    }

    public void stop() {
        try {
            if (bootstrap != null) {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
