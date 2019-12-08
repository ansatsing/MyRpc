package cn.antsing.rpc.common;

import java.io.Serializable;
import java.util.Arrays;

/**
 * syq协议，类似dubbo协议的概念
 */
public class SyqProtocol implements Serializable {

    private static final long serialVersionUID = -6716726771172858250L;
    /**
     * 类型：0-心跳 1-请求 2-响应
     */
    private byte type;
    /**
     * 数据长度
     */
    private int length;
    /**
     * 数据内容
     */
    private byte[] data;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SyqProtocol{" +
                "type=" + type +
                ", length=" + length +
                '}';
    }

    public static void main(String[] args) {
        int a  = 9;
        System.out.println(a& 0xFFFFFF);
    }
}
