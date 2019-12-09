package cn.antsing.rpc.tcp;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.schema.Consumer;

public interface TcpClient {
    //void connect(Consumer consumer);

    void sendRequest(InvocationInput invocationInput, Consumer consumer);
}
