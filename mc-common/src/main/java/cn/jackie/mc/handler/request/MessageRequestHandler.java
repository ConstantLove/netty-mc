package cn.jackie.mc.handler.request;

import cn.jackie.mc.protocol.packet.request.MessageRequestPacket;
import cn.jackie.mc.protocol.packet.response.MessageResponsePacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 发送信息处理器，服务端中转，然后发送给对应的客户端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        // 1. 找到对应的user channel
        String userId = messageRequestPacket.getToUserId();
        Channel toUserChannel = SessionUtil.getChannel(userId);

        // 2. 发送消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(messageRequestPacket.getFromUserId());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        messageResponsePacket.setUsername(SessionUtil.getSession(channelHandlerContext.channel()).getUsername());
        toUserChannel.writeAndFlush(messageResponsePacket);
    }

}
