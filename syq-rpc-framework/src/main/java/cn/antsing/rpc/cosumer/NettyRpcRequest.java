package cn.antsing.rpc.cosumer;

import cn.antsing.rpc.common.InvocationInput;
import cn.antsing.rpc.common.SyqProtocol;
import cn.antsing.rpc.schema.Consumer;
import cn.antsing.rpc.tcp.TcpClient;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NettyRpcRequest implements RpcRequest {
    private TcpClient tcpClient;

    public TcpClient getTcpClient() {
        return tcpClient;
    }

    public void setTcpClient(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    public SyqProtocol request(InvocationInput invocationInput, Consumer consumer) {
        tcpClient.connect(consumer);
        tcpClient.sendRequest(invocationInput);
        Callable<SyqProtocol> callable = new Callable<SyqProtocol>() {
            public SyqProtocol call() throws Exception {
                while (ResultContainer.result.size() == 0){

                }
                return ResultContainer.result.get(0);
            }
        };
        FutureTask<SyqProtocol> futureTask = new FutureTask<SyqProtocol>(callable);
        new Thread(futureTask).start();
        try {
            SyqProtocol o = futureTask.get();
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
