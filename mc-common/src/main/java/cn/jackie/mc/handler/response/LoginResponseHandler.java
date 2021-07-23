package cn.jackie.mc.handler.response;

import cn.jackie.mc.cons.Attributes;
import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.entity.Session;
import cn.jackie.mc.protocol.packet.response.LoginResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 登录响应处理器，用于客户端
 * @author Jackie Ke
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private static final Logger LOGGER = LogManager.getLogger(LoginResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

        if (loginResponsePacket.getStatus() == ResponseStatus.SUCCESS) {
            LOGGER.info("{} 登录成功, 用户id [{}]", loginResponsePacket.getUsername(), loginResponsePacket.getUserId());
            SessionUtil.addSession(new Session(loginResponsePacket.getUserId(), loginResponsePacket.getUsername()), channelHandlerContext.channel());
        } else {
            LOGGER.info("{} 登录失败", loginResponsePacket.getUsername());
        }

    }

}
