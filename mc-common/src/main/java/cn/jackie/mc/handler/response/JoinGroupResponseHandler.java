package cn.jackie.mc.handler.response;

import cn.jackie.mc.protocol.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群聊响应处理器，用于客户端
 * @author Jackie Ke
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        System.out.println(joinGroupResponsePacket.getMessage());
    }

}
