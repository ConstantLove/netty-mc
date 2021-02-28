package cn.jackie.mc.handler.request;

import cn.jackie.mc.protocol.packet.request.LogoutRequestPacket;
import cn.jackie.mc.protocol.packet.response.LogoutResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登出请求处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.removeSession(channelHandlerContext.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setStatus(200);
        logoutResponsePacket.setMessage("登出成功");
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);
    }

}
