package cn.antsing.protocol;

import java.util.LinkedList;
import java.util.List;

/**
 * 存放响应结果
 */
public final class ResultContainer {
    public static volatile   List<MyProtocol> result = new LinkedList<MyProtocol>();
}
