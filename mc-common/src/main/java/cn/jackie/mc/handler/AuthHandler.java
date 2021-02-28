package cn.jackie.mc.handler;


import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 身份（登录）验证处理器
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(AuthHandler.class);

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        // 1. 判断是否登录，如果已经登录，移除登录验证处理器
        if (SessionUtil.existsSession(channelHandlerContext.channel())) {
            channelHandlerContext.pipeline().remove(AuthHandler.class);
            super.channelRead(channelHandlerContext, obj);
        } else {
            // 2. 如果未登录，关闭连接
            channelHandlerContext.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (SessionUtil.existsSession(channelHandlerContext.channel())) {
            LOGGER.info("用户已登录，移除 AuthHandler");
        } else {
            LOGGER.info("用户未登录，强制关闭连接");
        }
    }
}
