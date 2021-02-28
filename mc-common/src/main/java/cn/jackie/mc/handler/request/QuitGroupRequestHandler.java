package cn.jackie.mc.handler.request;

import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.protocol.packet.request.QuitGroupRequestPacket;
import cn.jackie.mc.protocol.packet.response.JoinGroupResponsePacket;
import cn.jackie.mc.protocol.packet.response.QuitGroupResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 退出群聊请求处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        Channel channel;
        ChannelGroup channels;
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        // 1. 判断是否存在对应的group
        String groupId = quitGroupRequestPacket.getGroupId();
        if ((channel = SessionUtil.getChannel(quitGroupRequestPacket.getUserId())) != null && (channels = SessionUtil.getGroup(groupId)) != null) {
            // 2. 如果存在，退出群聊，并向群聊中的所有成员发送通知
            SessionUtil.quitGroup(groupId, channel);
            String username = SessionUtil.getSession(channel).getUsername();
            quitGroupResponsePacket.setStatus(ResponseStatus.SUCCESS);
            quitGroupResponsePacket.setMessage("[" + username + "] 退出群聊: " + groupId);
            channels.writeAndFlush(quitGroupResponsePacket);
        } else {
            // 3. 如果不存在，返回失败信息给请求用户
            quitGroupResponsePacket.setStatus(ResponseStatus.FAILURE);
            quitGroupResponsePacket.setMessage("退出群聊失败，不存在该groupId");
        }
        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }

}
