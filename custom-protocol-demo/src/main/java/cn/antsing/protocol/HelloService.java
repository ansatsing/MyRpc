package cn.antsing.protocol;

public class HelloService implements IHello {
    @Override
    public String hello(String str) {
        return "hello "+str;
    }
}
