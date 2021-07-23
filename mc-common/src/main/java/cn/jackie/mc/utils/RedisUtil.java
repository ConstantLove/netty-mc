package cn.jackie.mc.utils;

import redis.clients.jedis.Jedis;

/**
 * Redis工具类
 * @author Jackie Ke
 */
public class RedisUtil {

    private static final String HOST = "192.168.153.197";

    private static final int PORT = 6379;

    private static Jedis jedis;

    /**
     * 对外暴露 Jedis的单实例
     * @return
     */
    public static Jedis getJedisInstance() {
        if (jedis == null) {
            synchronized (RedisUtil.class) {
                if (jedis == null) {
                    jedis = new Jedis(HOST, PORT);
                }
            }
        }
        return jedis;
    }

}
