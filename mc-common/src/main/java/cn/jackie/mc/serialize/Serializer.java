package cn.jackie.mc.serialize;

import cn.jackie.mc.serialize.impl.JSONSerializer;

/**
 * 定义基础序列化逻辑
 * @author Jackie Ke
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化的方式
     * @return byte
     */
    byte getSerializerMethod();

    /**
     * Java对象转换成二进制 byte 数组
     * @param object Java对象
     * @return byte[]
     */
    byte[] serialize(Object object);

    /**
     * 二进制Byte数组转化成Java对象
     * @param clazz 目标对象的类型
     * @param bytes 二进制byte数组
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
