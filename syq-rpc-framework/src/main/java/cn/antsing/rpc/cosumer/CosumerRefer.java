package cn.antsing.rpc.cosumer;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.schema.Consumer;
import org.springframework.cglib.proxy.Enhancer;

public class CosumerRefer {
    public <T> T refer(Consumer consumer) throws ClassNotFoundException {
        T t= null;
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{Class.forName(consumer.getInterfac())});
        return t;
    }

}
