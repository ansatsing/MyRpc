package cn.antsing.rpc.common;

public class InvocationInput {
    private String interfac;
    private String methodName;
    private Object[] methodParameters;

    public String getInterfac() {
        return interfac;
    }

    public void setInterfac(String interfac) {
        this.interfac = interfac;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(Object[] methodParameters) {
        this.methodParameters = methodParameters;
    }
}
