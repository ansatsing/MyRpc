package cn.antsing.protocol;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RpcClient {
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public  Object refer(final Consumer consumer) throws ClassNotFoundException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Class.forName(consumer.getInterfac()));
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                InvocationInput input = new InvocationInput();
                input.setInterfac(consumer.getInterfac());
                input.setMethodName(method.getName());
                input.setMethodParameters(objects);
                MyProtocol rqcResult = client.sendRpcRequest(input,consumer);
                Object result = SerializeUtils.unserialize(rqcResult.getBody());
                return result;
            }
        });
        return enhancer.create();
    }
}
