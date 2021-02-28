package cn.jackie.mc.serialize.impl;

import cn.jackie.mc.cons.SerializerMethod;
import cn.jackie.mc.serialize.Serializer;
import com.alibaba.fastjson.JSON;

/**
 * Json序列化实现类，默认使用该实现类
 * @author Jackie Ke
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerMethod() {
        return SerializerMethod.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

}
