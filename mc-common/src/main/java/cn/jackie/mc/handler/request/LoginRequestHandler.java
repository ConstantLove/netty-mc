package cn.jackie.mc.handler.request;

import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.entity.Session;
import cn.jackie.mc.protocol.packet.request.LoginRequestPacket;
import cn.jackie.mc.protocol.packet.response.LoginResponsePacket;
import cn.jackie.mc.utils.IDUtil;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 登录请求处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static final Logger LOGGER = LogManager.getLogger(LoginRequestHandler.class);

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        channelHandlerContext.channel().writeAndFlush(login(channelHandlerContext, loginRequestPacket));
    }

    private LoginResponsePacket login(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        String username = loginRequestPacket.getUsername();
        String password = loginRequestPacket.getPassword();
        if (doLogin(channelHandlerContext, username, password)) {
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setStatus(ResponseStatus.SUCCESS);
            loginResponsePacket.setMessage("登录成功");

            SessionUtil.addSession(new Session(userId, username), channelHandlerContext.channel());
            LOGGER.info("[{}] 登录成功, 用户id [{}]", username, userId);
        } else {
            loginResponsePacket.setStatus(ResponseStatus.FAILURE);
            loginResponsePacket.setMessage("登录失败");
        }
        loginResponsePacket.setUsername(username);

        return loginResponsePacket;
    }

    private boolean doLogin(ChannelHandlerContext channelHandlerContext, String username, String password) {
        // TODO 实际登录操作

        return true;
    }

}
