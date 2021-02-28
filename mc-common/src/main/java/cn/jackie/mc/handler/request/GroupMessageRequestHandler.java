package cn.jackie.mc.handler.request;

import cn.jackie.mc.protocol.packet.request.GroupMessageRequestPacket;
import cn.jackie.mc.protocol.packet.response.GroupMessageResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 群聊消息请求（发送）处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        ChannelGroup channelGroup;
        // 如果group存在，向group中所有用户发送信息
        if ((channelGroup = SessionUtil.getGroup(groupId)) != null) {
            GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
            groupMessageResponsePacket.setGroupId(groupId);
            groupMessageResponsePacket.setFromUserId(groupMessageRequestPacket.getFromUserId());
            groupMessageResponsePacket.setUsername(groupMessageRequestPacket.getUsername());
            groupMessageResponsePacket.setMessage(groupMessageRequestPacket.getMessage());
            channelGroup.writeAndFlush(groupMessageResponsePacket);
        } else {
            // TODO 返回发送失败的响应
        }
    }

}
