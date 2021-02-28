package cn.jackie.mc.handler.request;

import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.protocol.packet.request.JoinGroupRequestPacket;
import cn.jackie.mc.protocol.packet.response.JoinGroupResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 加入群聊请求处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        Channel channel;
        ChannelGroup channels;
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        // 1. 判断是否存在对应的group
        String groupId = joinGroupRequestPacket.getGroupId();
        if ((channel = SessionUtil.getChannel(joinGroupRequestPacket.getUserId())) != null && (channels = SessionUtil.getGroup(groupId)) != null) {
            // 2. 如果存在，加入群聊，并向群聊中的所有成员发送通知
            SessionUtil.joinGroup(groupId, channel);
            String username = SessionUtil.getSession(channel).getUsername();
            joinGroupResponsePacket.setStatus(ResponseStatus.SUCCESS);
            joinGroupResponsePacket.setMessage("[" + username + "] 加入群聊: " + groupId);
            channels.writeAndFlush(joinGroupResponsePacket);
        } else {
            // 3. 如果不存在，返回失败信息给请求用户
            joinGroupResponsePacket.setStatus(ResponseStatus.FAILURE);
            joinGroupResponsePacket.setMessage("加入群聊失败，不存在该groupId");
            channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);
        }
    }

}
