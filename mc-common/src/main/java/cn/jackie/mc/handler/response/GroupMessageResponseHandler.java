package cn.jackie.mc.handler.response;

import cn.jackie.mc.protocol.packet.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Jackie Ke
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        System.out.println("群聊id：" + groupMessageResponsePacket.getGroupId() + ", ["+ groupMessageResponsePacket.getUsername() +"] : "
                + groupMessageResponsePacket.getMessage());
    }
}
