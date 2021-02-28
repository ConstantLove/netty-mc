package cn.jackie.mc.handler.response;

import cn.jackie.mc.protocol.packet.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登出响应处理器，用于客户端
 * @author Jackie Ke
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        System.out.println(logoutResponsePacket.getMessage());
    }

}
