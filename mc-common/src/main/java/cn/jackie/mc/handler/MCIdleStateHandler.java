package cn.jackie.mc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 连接空闲检测，一段时间没有接受到信息则自动关闭连接
 * @author Jackie Ke
 */
public class MCIdleStateHandler extends IdleStateHandler {

    /**
     * 最大空闲时间，超过该时间会关闭连接
     */
    private static final int READER_IDLE_TIME = 300;

    public MCIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒没有读取到数据，关闭连接");
        ctx.channel().close();
    }
}
