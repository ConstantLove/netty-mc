package cn.jackie.mc;

import cn.jackie.mc.handler.*;
import cn.jackie.mc.handler.request.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Jackie Ke
 */
public class Server {

    private static final int PORT = 9001;

    public static void main(String[] args) {

        NioEventLoopGroup main = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(main, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new MCIdleStateHandler())            // 心跳检测
                                          .addLast(new PacketCutter())                  // 粘包拆包处理器
                                          .addLast(PacketCodecHandler.INSTANCE)         // 编码处理
                                          .addLast(LoginRequestHandler.INSTANCE)        // 登录请求处理
                                          .addLast(HeartBeatRequestHandler.INSTANCE)    // 心跳请求处理
                                          .addLast(AuthHandler.INSTANCE)                // 校验是否登录
                                          .addLast(MCHandler.INSTANCE);                 // 功能统一管理
                    }
                }).bind(PORT);

    }

}
