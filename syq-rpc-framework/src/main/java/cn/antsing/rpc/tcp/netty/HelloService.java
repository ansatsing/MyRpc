package cn.antsing.rpc.tcp.netty;

public class HelloService implements IHello {
    @Override
    public String hello(String str) {
        return "hello "+str;
    }
}
