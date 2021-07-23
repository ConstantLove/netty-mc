package cn.jackie.mc.utils;

import cn.jackie.mc.cons.Attributes;
import cn.jackie.mc.entity.Session;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户会话管理工具类
 * @author Jackie Ke
 */
public class SessionUtil {

    private static Map<String, Channel> userSessionMap = new ConcurrentHashMap<>();
    private static Map<String, ChannelGroup> userGroupMap = new ConcurrentHashMap<>();

    private static Jedis jedis = RedisUtil.getJedisInstance();

    /**
     * 创建群聊
     * @param groupId
     * @param channels
     */
    public static void createGroup(String groupId, ChannelGroup channels) {
        userGroupMap.putIfAbsent(groupId, channels);
    }

    /**
     * 加入群聊
     * @param groupId
     * @param channel
     */
    public static void joinGroup(String groupId, Channel channel) {
        if (userGroupMap.containsKey(groupId)) {
            ChannelGroup channels = userGroupMap.get(groupId);
            channels.add(channel);
            userGroupMap.put(groupId, channels);
        }
    }

    /**
     * 退出群聊
     * @param groupId
     * @param channel
     */
    public static void quitGroup(String groupId, Channel channel) {
        if (userGroupMap.containsKey(groupId)) {
            ChannelGroup channels = userGroupMap.get(groupId);
            channels.remove(channel);
            // 当某个群聊人数为0时，删除该群聊
            if (channels.isEmpty()) {
                removeGroup(groupId);
            } else {
                userGroupMap.put(groupId, channels);
            }
        }
    }

    /**
     * 获取一个群聊实例
     * @param groupId
     * @return
     */
    public static ChannelGroup getGroup(String groupId) {
        return userGroupMap.get(groupId);
    }

    /**
     * 移除群聊
     * 当群聊人数为0时，自动删除该群聊
     * @param groupId
     */
    public static void removeGroup(String groupId) {
        userGroupMap.remove(groupId);
        System.out.println("群聊：" + groupId + " 人数为0，删除该群聊");
    }

    /**
     * 添加用户会话
     * @param session
     * @param channel
     */
    public static void addSession(Session session, Channel channel) {
        channel.attr(Attributes.SESSION).set(session);
        jedis.set("user:" + session.getUserId(), session.getUsername());
        userSessionMap.putIfAbsent(session.getUserId(), channel);
    }

    /**
     * 删除用户会话
     * @param channel
     */
    public static void removeSession(Channel channel) {
        if (existsSession(channel)) {
            String userId = getSession(channel).getUserId();
            userSessionMap.remove(userId);
            jedis.del("user:" + userId);   // 删除用户会话
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /**
     * 判断用户会话是否存在
     * @param channel
     * @return
     */
    public static boolean existsSession(Channel channel) {
        if (!channel.hasAttr(Attributes.SESSION))
            return false;
        return jedis.get("user:" + channel.attr(Attributes.SESSION).get().getUserId()) != null;
    }

    /**
     * 获取用户会话
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userSessionMap.get(userId);
    }

}
