package cn.jackie.mc;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.handler.*;
import cn.jackie.mc.handler.response.*;
import cn.jackie.mc.utils.ConsoleCommandUtil;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author Jackie Ke
 */
public class Client {

    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    // TODO 实现外部配置导入
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9001;

    public static void main(String[] args) {

        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new MCIdleStateHandler())            // 心跳检测
                                          .addLast(new PacketCutter())                  // 粘包拆包处理器
                                          .addLast(PacketCodecHandler.INSTANCE)         // 编码处理
                                          .addLast(new LoginResponseHandler())          // 登录响应
                                          .addLast(new MessageResponseHandler())        // 消息响应
                                          .addLast(new CreateGroupResponseHandler())    // 创建群聊响应
                                          .addLast(new JoinGroupResponseHandler())      // 加入群聊响应
                                          .addLast(new QuitGroupResponseHandler())      // 退出群聊响应
                                          .addLast(new GroupMessageResponseHandler())   // 群聊信息响应
                                          .addLast(new LogoutResponseHandler())         // 登出响应
                                          .addLast(new HeartBeatTimerHandler());        // 定时发送心跳检测
                    }
                });

        connect(bootstrap, HOST, PORT);

    }

    private static void connect(Bootstrap bootstrap, String host, int port) {

        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                LOGGER.info("连接端口 [{}] 成功", port);
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else {
                //TODO 失败重连
                LOGGER.info("连接端口 [{}] 失败", port);
            }
        });

    }

    private static void startConsoleThread(Channel channel) {

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                if (SessionUtil.existsSession(channel)) {
                    System.out.println("请输入指令");
                    String consoleCommand = scanner.nextLine();
                    ConsoleCommandUtil.execute(scanner, consoleCommand, channel);
                } else {
                    ConsoleCommandUtil.execute(scanner, ConsoleCommand.LOGIN, channel);
                    waitForLogin();
                }
            }

        }).start();

    }

    private static void waitForLogin() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
