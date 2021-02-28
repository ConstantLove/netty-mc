package cn.jackie.mc.handler.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一管理RequestHandler，服务端专用，客户端一般是单连接，所以不需要实现单例和统一管理
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class MCHandler extends SimpleChannelInboundHandler<Packet> {

    public static final MCHandler INSTANCE = new MCHandler();

    private static Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private MCHandler() {
    }

    static {
        handlerMap = new HashMap<>();

        handlerMap.put(Command.LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
