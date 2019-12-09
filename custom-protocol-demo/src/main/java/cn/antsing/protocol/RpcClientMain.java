package cn.antsing.protocol;

public class RpcClientMain {
    public static void main(String[] args) throws ClassNotFoundException {
        RpcClient rpcClient = new RpcClient();
        rpcClient.setClient(new Client());
        Consumer cosumer = new Consumer();
        cosumer.setAddress("127.0.0.1:8081");
        cosumer.setId("1");
        cosumer.setInterfac("cn.antsing.protocol.IHello");
        IHello hello = (IHello) rpcClient.refer(cosumer);
        System.out.println(hello.hello("syq"));
    }
}
