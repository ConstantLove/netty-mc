package cn.jackie.mc.handler;

import cn.jackie.mc.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 拆包粘包处理器，持续监控channel读取数据，直到数据长度达到条件
 * @author Jackie Ke
 */
public class PacketCutter extends LengthFieldBasedFrameDecoder {

    /**
     * 长度字段相对偏移量
     */
    private static final int LENGTH_FIELD_OFFSET = 7;

    /**
     * 长度字段的长度
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public PacketCutter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
