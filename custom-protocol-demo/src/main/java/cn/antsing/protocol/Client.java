package cn.antsing.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Client {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;
    private static final int LENGTH_FIELD_LENGTH = 4;
    private static final int LENGTH_FIELD_OFFSET = 2;
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;

    private Channel channel;
    //private final Consumer consumer;
//
//    public Client(Consumer consumer) {
//        this.consumer = consumer;
//    }

    public static void main(String[] args) throws Exception {

//        Channel channel = new Client().connect();
//        String data = "Hello,Netty";
//        byte[] bytes = data.getBytes();
//        // MyProtocol customMsg = new MyProtocol((byte)0xAB, (byte)0xCD, bytes.length, bytes);
//        MyProtocol customMsg = new MyProtocol((byte)0xAB, bytes.length, bytes);
//        channel.writeAndFlush(customMsg);
//        while (true){
//
//        }

    }

    public Channel getChannel() throws InterruptedException {
//        if(channel == null){
//            channel = connect(this.consumer);
//        }
        return channel;
    }

    private   Channel connect(Consumer consumer)  {
        String[] ipPort = consumer.getAddress().split(":");
        String ip = ipPort[0];
        Integer port = Integer.parseInt(ipPort[1]);
        EventLoopGroup group = new NioEventLoopGroup();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyProtocolEncoder());
                            ch.pipeline().addLast(new MyProtocolDecoder(MAX_FRAME_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false));
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

        ChannelFuture future = null;
        try {
            future = b.connect(ip, port).sync();
            boolean ret = future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);
            if(future.isSuccess()){
                return future.channel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            //future.channel().writeAndFlush("Hello Netty Server ,I am a common client");
            //future.channel().closeFuture().sync();
        return null;
    }
    public MyProtocol sendRpcRequest(InvocationInput invocationInput, Consumer consumer){
        Channel channel = connect(consumer);
        MyProtocol myProtocol = new MyProtocol();
        myProtocol.setType((byte)1);
        byte[] data = SerializeUtils.serialize(invocationInput);
        myProtocol.setLength(data.length);
        myProtocol.setBody(data);
        channel.writeAndFlush(myProtocol);
        Callable<MyProtocol> callable = new Callable<MyProtocol>() {
            public MyProtocol call() throws Exception {
                while (ResultContainer.result.size() == 0){

                }
                return ResultContainer.result.get(0);
            }
        };
        FutureTask<MyProtocol> futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        try {
            MyProtocol o = futureTask.get();
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
