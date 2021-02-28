package cn.jackie.mc.handler.response;

import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.protocol.packet.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 创建群聊响应处理器，用于客户端
 * @author Jackie Ke
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        if (createGroupResponsePacket.getStatus() == ResponseStatus.SUCCESS) {
            System.out.println("用户[" + createGroupResponsePacket.getCreateUserId() + "] 创建群聊，群聊id：" + createGroupResponsePacket.getGroupId()
                    + ", 群成员[" + createGroupResponsePacket.getUserNamesStr() + "]");
        }
    }

}
