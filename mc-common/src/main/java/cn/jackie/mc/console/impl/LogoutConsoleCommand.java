package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.LogoutRequestPacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登出指令
 * @author Jackie Ke
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        // 1. 删除本地session
        SessionUtil.removeSession(channel);

        // 2. 删除服务端session
        channel.writeAndFlush(new LogoutRequestPacket());

        // 3. 睡眠一秒，等待客户端删除session
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
