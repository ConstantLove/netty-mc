package cn.jackie.mc.cons;

import cn.jackie.mc.entity.Session;
import io.netty.util.AttributeKey;


/**
 * @author Jackie Ke
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");

}
