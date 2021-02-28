package cn.jackie.mc.handler.response;

import cn.jackie.mc.protocol.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 退出群聊响应处理器，用于客户端
 * @author Jackie Ke
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        System.out.println(quitGroupResponsePacket.getMessage());
    }

}
