package cn.antsing.rpc.cosumer;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.util.SerializeUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CosumerRefer {
    private RpcRequest rpcRequest;

    public RpcRequest getRpcRequest() {
        return rpcRequest;
    }

    public void setRpcRequest(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    public Object refer(final Consumer consumer) throws ClassNotFoundException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Class.forName(consumer.getInterfac()));
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                InvocationInput input = new InvocationInput();
                input.setInterfac(consumer.getInterfac());
                input.setMethodName(method.getName());
                input.setMethodParameters(objects);
                SyqProtocol rqcResult = rpcRequest.request(input,consumer);
                Object result = SerializeUtils.unserialize(rqcResult.getData());
                return result;
            }
        });
        return enhancer.create();
    }

}
