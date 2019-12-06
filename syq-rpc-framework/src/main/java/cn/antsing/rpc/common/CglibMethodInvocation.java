package cn.antsing.rpc.common;

public class CglibMethodInvocation implements MethodInvocation {
    public Object invoke(InvocationInput input) {
        String interfac = input.getInterfac();
        Object service = ServiceContainer.getService(interfac);
        if(service == null){
            //todo 反射生成服务实例
        }

        return null;
    }
}
