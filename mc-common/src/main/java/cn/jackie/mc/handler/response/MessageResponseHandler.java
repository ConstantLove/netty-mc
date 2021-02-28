package cn.jackie.mc.handler.response;

import cn.jackie.mc.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息响应（接收消息）处理器，用于客户端
 * @author Jackie Ke
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println("收到来自[" + messageResponsePacket.getFromUserId() + "]" + messageResponsePacket.getUsername() + "的消息："
                + messageResponsePacket.getMessage());
    }

}
