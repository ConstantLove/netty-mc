package cn.jackie.mc.utils;

import java.util.UUID;

/**
 * @author Jackie Ke
 */
public class IDUtil {

    /**
     * 截取UUID第一段字符
     * @return
     */
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
