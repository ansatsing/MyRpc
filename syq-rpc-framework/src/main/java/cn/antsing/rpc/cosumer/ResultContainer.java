package cn.antsing.rpc.cosumer;

import cn.antsing.rpc.common.SyqProtocol;

import java.util.LinkedList;
import java.util.List;

/**
 * 存放响应结果
 */
public final class ResultContainer {
    public static volatile  List<SyqProtocol> result = new LinkedList<SyqProtocol>();
}
