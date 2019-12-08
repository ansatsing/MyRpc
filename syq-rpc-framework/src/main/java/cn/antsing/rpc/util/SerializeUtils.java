package cn.antsing.rpc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象流序列化工具
 */
public class SerializeUtils {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    /**
     * 序列化
     */
    public static byte[] serialize(Object object) {
        if (null == object) {
            return null;
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 反序列化
     */
    @SuppressWarnings("unchecked")
    public static <T> T unserialize(Class<T> clazz, byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (null == bytes) {
                return null;
            }
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 反序列化
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (null == bytes) {
                return null;
            }
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }
}