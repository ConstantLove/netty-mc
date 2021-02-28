package cn.jackie.mc.handler;

import cn.jackie.mc.protocol.packet.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 客户端定时发送心跳检测给服务端
 * @author Jackie Ke
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 心跳检测间隔
     */
    private static final int HEART_BEAT_INTERVAL = 60;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {

        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);

    }

}
