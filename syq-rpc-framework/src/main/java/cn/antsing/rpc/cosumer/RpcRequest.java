package cn.antsing.rpc.cosumer;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.schema.Consumer;

public interface RpcRequest {
    SyqProtocol request(InvocationInput invocationInput, Consumer consumer);
}
