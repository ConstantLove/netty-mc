package cn.jackie.mc.handler.request;

import cn.jackie.mc.protocol.packet.request.HeartBeatRequestPacket;
import cn.jackie.mc.protocol.packet.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 心跳检测处理器，用于服务端
 * @author Jackie Ke
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        // 收到心跳检测请求之后，返回心跳检测响应，用于客户端检测心跳
        channelHandlerContext.writeAndFlush(new HeartBeatResponsePacket());
    }

}
